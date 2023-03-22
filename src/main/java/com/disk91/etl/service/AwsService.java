package com.disk91.etl.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.disk91.etl.EtlApplication;
import com.disk91.etl.EtlConfig;
import com.disk91.etl.data.object.Param;
import com.disk91.etl.data.repository.ParamRepository;
import fr.ingeniousthings.tools.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import xyz.nova.grpc.lora_beacon_ingest_report_v1;
import xyz.nova.grpc.lora_witness_ingest_report_v1;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.List;
import java.util.zip.GZIPInputStream;

@Service
public class AwsService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected EtlConfig etlConfig;

    @Autowired
    protected ParamRepository paramRepository;

    @Autowired
    protected PrometeusService prometeusService;

    protected AWSCredentials awsCredentials = null;
    protected AmazonS3 s3Client = null;

    @PostConstruct
    private void initAwsService() {

        if ( etlConfig.getAwsAccessKey().length() < 2 || etlConfig.getAwsSecretKey().length() < 2) {
            log.error("========= CONFIG ERROR ========");
            log.error(">> You need to setup your AWS credentials");
            EtlApplication.requestingExitForStartupFailure = true;
            return;
        }

        this.awsCredentials = new BasicAWSCredentials(
                etlConfig.getAwsAccessKey(),
                etlConfig.getAwsSecretKey()
        );

        if ( awsCredentials != null ) {
            this.s3Client = AmazonS3ClientBuilder
                    .standard()
                    .withCredentials(new AWSCredentialsProvider() {
                        @Override
                        public AWSCredentials getCredentials() {
                            return awsCredentials;
                        }

                        @Override
                        public void refresh() {
                            awsCredentials = new BasicAWSCredentials(
                                    etlConfig.getAwsAccessKey(),
                                    etlConfig.getAwsSecretKey()
                            );
                        }
                    })
                    .withRegion(Regions.US_WEST_2)
                    .build();
            if ( this.s3Client != null ) {
                this.readyToSync = true;
                this.serviceEnable = true;
                this.runningJobs = 0;
                //lor.putCustomRequestHeader("x-amz-request-payer", "requester");
            } else {
                log.error("Impossible to connect to AWS, no sync will be possible");
            }
        }

    }


    protected int runningJobs;
    protected boolean serviceEnable = false; // false to stop the services

    public void stopService() {
        this.serviceEnable = false;
    }
    public boolean hasStopped() {
        return (this.serviceEnable == false && this.runningJobs == 0);
    }



    @Autowired
    private HotspotCache hotspotCache;

    private boolean readyToSync = false;
    private boolean syncInProgress = false;

    @Scheduled(fixedDelay = 60_000, initialDelay = 10_000)
    protected void InitialAwsSync() {
        if ( ! readyToSync ) return;
        this.runningJobs++;
        log.info("Running AwsService Sync");

        synchronized (this) {
            this.syncInProgress = true;
        }

        long start = Now.NowUtcMs();
        long lastLog = start;

        try {
            Param p = paramRepository.findOneParamByParamName("aws_last_sync");

            final ListObjectsV2Request lor = new ListObjectsV2Request();
            lor.setBucketName(etlConfig.getAwsBucketName());
            lor.setPrefix("foundation-iot-ingest");
            if (p != null ) {
                lor.setStartAfter(p.getStringValue());
            } else {
                p = new Param();
                p.setParamName("aws_last_sync");
            }
            lor.setRequesterPays(true);
            ListObjectsV2Result list;
            long totalObject = 0;
            long totalSize = 0;
            long totalBeacon = 0;
            long totalWitness = 0;
            do {
                list = this.s3Client.listObjectsV2(lor);
                List<S3ObjectSummary> objects = list.getObjectSummaries();
                for (S3ObjectSummary object : objects) {
                    totalObject++;
                    totalSize+=object.getSize();
                    if ( object.getSize() == 0 ) continue;
                    long fileStart = Now.NowUtcMs();

                    // Identify the type of objects
                    //  iot_beacon_ingest_report => beacons
                    //  iot_witness_ingest_report => witnesses
                    if ( ! object.getKey().contains(".gz") ) continue; // not a file
                    String fileName = object.getKey().split("/")[1];
                    log.debug("Processing : "+fileName);

                    final GetObjectRequest or = new GetObjectRequest(object.getBucketName(), object.getKey());
                    or.setRequesterPays(true);
                    S3Object fileObject = this.s3Client.getObject(or);
                    int fileType = 0;
                    if ( fileName.startsWith("iot_beacon_ingest_report") ) {
                        fileType = 1;
                    } else if ( fileName.startsWith("iot_witness_ingest_report") ) {
                        fileType = 2;
                    } else {
                        log.warn("Unknown type of file discovered "+fileName);
                    }

                    try {
                        // File is GZiped Version of a stream of protobuf messages
                        // each protobuf messages is encapsulated with a header
                        // int4 containing the length of the protobuf message following.
                        GZIPInputStream stream = new GZIPInputStream(fileObject.getObjectContent());
                        BufferedInputStream bufferedInputStream = new BufferedInputStream(stream);
                        while ( bufferedInputStream.available() > 0 ) {
                            try {
                                byte[] sz = bufferedInputStream.readNBytes(4);
                                long len = Stuff.getLongValueFromBytes(sz);
                                if (len > 0) {
                                    byte[] r = bufferedInputStream.readNBytes((int) len);
                                    switch (fileType) {
                                        case 1: // beacon
                                            totalBeacon++;
                                            lora_beacon_ingest_report_v1 b = lora_beacon_ingest_report_v1.parseFrom(r);
                                            if (!hotspotCache.addBeacon(b)) {
                                                log.warn("beacon not processed " + b.getReceivedTimestamp());
                                            }

                                            // filter to 11etKgw9Lb6FndJnU17pKQVtsgbPJRvzE8eHny4J5f78NFvEXUD
                                            //if ( b.getReport().getPubKey().size() > 0 ) {
                                            //    String hs = HeliumHelper.pubAddressToName(b.getReport().getPubKey());
                                            //log.info(hs);
                                            //    if (hs.compareTo("11etKgw9Lb6FndJnU17pKQVtsgbPJRvzE8eHny4J5f78NFvEXUD") == 0) {
                                            //12h6o6fakZRPu5x6YHHBgMWrWCmcvrdxyLV3z7rVRQq83Q3Zw37
                                            //        log.info("TS :" + b.getReceivedTimestamp() + " Pub: " + b.getReport().getPubKey() + " Pwr: " + b.getReport().getTxPower());
                                            //    }
                                            //}

                                            break;
                                        case 2: // witness
                                            totalWitness++;
                                            lora_witness_ingest_report_v1 w = lora_witness_ingest_report_v1.parseFrom(r);
                                            if (!hotspotCache.addWitness(w)) {
                                                log.warn("witness not processed " + w.getReceivedTimestamp());
                                            }


                                            // filter to 11etKgw9Lb6FndJnU17pKQVtsgbPJRvzE8eHny4J5f78NFvEXUD
                                            //String hs = HeliumHelper.pubAddressToName(w.getReport().getPubKey());

                                            //if ( hs.startsWith("11etK") ) {
                                            //    log.info("Found "+hs+" Search 11etKgw9Lb6FndJnU17pKQVtsgbPJRvzE8eHny4J5f78NFvEXUD");
                                            //}

                                            //if ( hs.compareTo("11etKgw9Lb6FndJnU17pKQVtsgbPJRvzE8eHny4J5f78NFvEXUD") == 0 ) {
                                            //    log.info("TS :" + w.getReceivedTimestamp() + " Pub: " + hs + " Pwr: " + w.getReport().getSignal());
                                            //}

                                            break;

                                        default:
                                            break;
                                    }

                                } else {
                                    log.error("Found 0 len entry " + HexaConverters.byteToHexStringWithSpace(sz));
                                }

                                // print progress log on regular basis
                                if ((Now.NowUtcMs() - lastLog) > 30_000) {
                                    String distance_s = object.getKey().split("\\.")[1];
                                    long distance = Now.NowUtcMs() - Long.parseLong(distance_s);
                                    log.info("Dist: " + Math.floor(distance / Now.ONE_FULL_DAY) + " days, tObject: " + totalObject + " tBeacon: " + totalBeacon + " tWitness: " + totalWitness + " tSize: " + totalSize / (1024 * 1024) + "MB, Duration: " + (Now.NowUtcMs() - start) / 60_000 + "m");
                                    lastLog = Now.NowUtcMs();
                                }
                            } catch ( IOException x ) {
                                // in case of IOException Better skip the file
                                prometeusService.addAwsFailure();
                                log.error("Failed to process file "+object.getKey()+" "+x.getMessage());
                                if ( serviceEnable == false ) return;
                                else break;
                            } catch ( Exception x ) {
                                log.error(x.getMessage());
                                if ( serviceEnable == false ) return;
                                x.printStackTrace();
                            }

                        }
                        bufferedInputStream.close();
                        stream.close();
                        prometeusService.addFileProcessed();
                        prometeusService.addFileProcessedTime(Now.NowUtcMs() - fileStart);

                    } catch (IOException x) {
                        prometeusService.addAwsFailure();
                        log.error("Failed to gunzip for Key "+object.getKey()+" "+x.getMessage());
                    } catch (Exception x) {
                        prometeusService.addAwsFailure();
                        log.error("Failed to process file "+object.getKey()+" "+x.getMessage());
                    }

                    p.setStringValue(object.getKey());
                    paramRepository.save(p);
                    hotspotCache.flushTopLines();
                    if ( serviceEnable == false ) {
                        // we had a request to quit and at this point we can make it
                        // clean
                        return;
                    }
                }
                lor.setContinuationToken(list.getNextContinuationToken());

            } while (list.isTruncated());
        } catch (AmazonServiceException x) {
            prometeusService.addAwsFailure();
            log.error(x.getMessage());
            x.printStackTrace();
        } catch (AmazonClientException x) {
            prometeusService.addAwsFailure();
            log.error(x.getMessage());
            x.printStackTrace();
        } catch (Exception x) {
            prometeusService.addAwsFailure();
            log.error("Batch Failure "+x.getMessage());
        } finally {
            runningJobs--;
            synchronized (this) {
                this.syncInProgress = false;
            }
        }

    }


}
