package com.disk91.etl.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.SdkClientException;
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
import com.helium.grpc.iot_reward_share;
import com.helium.grpc.lora_poc_v1;
import com.helium.grpc.mobile.mobile_reward_share;
import com.helium.grpc.mobile.radio_reward_share;
import fr.ingeniousthings.tools.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.helium.grpc.lora_beacon_ingest_report_v1;
import com.helium.grpc.lora_witness_ingest_report_v1;

import jakarta.annotation.PostConstruct;
import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.zip.GZIPInputStream;

@Service
public class AwsService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    static final String BEACON_FIRST_OBJECT = "foundation-iot-ingest/iot_beacon_ingest_report.1674760089530.gz";
    static final String WITNESS_FIRST_OBJECT = "foundation-iot-ingest/iot_witness_ingest_report.1674760086500.gz";

    static final String IOTPOC_FIRST_OBJECT = "foundation-iot-verified-rewards/iot_poc.1674766530034.gz";

    // static final String REWARD_FIRST_OBJECT = "foundation-iot-verified-rewards/gateway_reward_share.1674766530034.gz";
    static final String REWARD_FIRST_OBJECT = "foundation-iot-verified-rewards/iot_reward_share.1674766530034.gz";

    static final String MOBILE_REWARD_FIRST_OBJECT = "foundation-mobile-verified/radio_reward_share.1674766530034.gz";
    static final String MOBILE_REWARD_LAST_OBJECT = "foundation-mobile-verified/radio_reward_share.1686188007722.gz";
    static final String MOBILE_REWARD_FIRST_NEW_OBJECT = "foundation-mobile-verified/mobile_reward_share.1686100000000.gz";



    @Autowired
    protected EtlConfig etlConfig;

    @Autowired
    protected ParamRepository paramRepository;

    @Autowired
    protected PrometeusService prometeusService;

    protected AWSCredentials awsCredentials = null;
    protected AmazonS3 s3Client = null;

    Param beaconFile = null;
    Param witnessFile = null;
    Param iotPocFile = null;

    Param rewardPocFile = null;

    Param mobileRewardFile = null;


    @PostConstruct
    private void initAwsService() {

        if ( etlConfig.getAwsAccessKey().length() < 2 || etlConfig.getAwsSecretKey().length() < 2) {
            log.error("========= CONFIG ERROR ========");
            log.error(">> You need to setup your AWS credentials");
            EtlApplication.requestingExitForStartupFailure = true;
            return;
        }

        beaconFile = paramRepository.findOneParamByParamName("aws_last_sync");
        if ( beaconFile == null ) {
            beaconFile = new Param();
            beaconFile.setParamName("aws_last_sync");
            beaconFile.setStringValue(BEACON_FIRST_OBJECT);
        }

        witnessFile = paramRepository.findOneParamByParamName("aws_last_wit_sync");
        if ( witnessFile == null ) {
            witnessFile = new Param();
            witnessFile.setParamName("aws_last_wit_sync");
            witnessFile.setStringValue(WITNESS_FIRST_OBJECT);
        }

        iotPocFile = paramRepository.findOneParamByParamName("aws_last_iotpoc_sync");
        if ( iotPocFile == null ) {
            iotPocFile = new Param();
            iotPocFile.setParamName("aws_last_iotpoc_sync");
            iotPocFile.setStringValue(IOTPOC_FIRST_OBJECT);
        }

        rewardPocFile = paramRepository.findOneParamByParamName("aws_last_reward_sync");
        if ( rewardPocFile == null ) {
            rewardPocFile = new Param();
            rewardPocFile.setParamName("aws_last_reward_sync");
            rewardPocFile.setStringValue(REWARD_FIRST_OBJECT);
        } else if ( rewardPocFile.getStringValue().contains("gateway_reward_share") ) {
            // update with the new name after file format changed
            rewardPocFile.setStringValue(REWARD_FIRST_OBJECT);
        }

        mobileRewardFile = paramRepository.findOneParamByParamName("aws_last_mobile_reward_sync");
        if ( mobileRewardFile == null ) {
            mobileRewardFile = new Param();
            mobileRewardFile.setParamName("aws_last_mobile_reward_sync");
            mobileRewardFile.setStringValue(MOBILE_REWARD_FIRST_OBJECT);
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
                    .withClientConfiguration( new ClientConfiguration()
                        .withMaxErrorRetry(5)
                        .withMaxConnections(10)
                        .withConnectionTimeout(10_000)
                        .withSocketTimeout(60_000)
                    )
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


    protected HashMap<String,Integer> runningJobsName = new HashMap<>();
    protected int runningJobs;
    protected boolean serviceEnable = false; // false to stop the services

    private final Object locker = new Object();

    public void stopService() {
        this.serviceEnable = false;
    }
    public void restartService() { this.serviceEnable = true; this.firstFile = true; }
    public boolean hasStopped() {
        return (!this.serviceEnable && this.runningJobs == 0);
    }

    public String listServices() {
        String s = "";
        for (String k : this.runningJobsName.keySet()) {
            s += k + "(" + this.runningJobsName.get(k) + ") ";
        }
        return s;
    }

    private int getFileType(String fileName) {
        if ( fileName.startsWith("iot_beacon_ingest_report") ) {
            return 1;
        } else if ( fileName.startsWith("iot_witness_ingest_report") ) {
            return 2;
        } else if ( fileName.startsWith("iot_poc") ) {
            return 3;
        } else if ( fileName.startsWith("iot_reward") ) {
            return 4;
        } else if ( fileName.startsWith("radio_reward") ) {
            return 5;
        } else if ( fileName.startsWith("mobile_reward") ) {
            return 6;
        } else if ( fileName.startsWith("gateway_reward") ) {
           // log.info("Found gateway_reward file "+fileName);
        } else if ( fileName.startsWith("solana-migration-bad-data") ) {
           // log.info("Found solana_migration bad data file "+fileName);
        } else if ( fileName.startsWith("reward_manifest") ) {
            // log.info("Found reward manifest file "+fileName);
        } else if ( fileName.startsWith("non_rewardable") ) {
            // log.info("Found non rewardable packets "+fileName);
        } else {
            log.debug("Unknown type of file discovered "+fileName);
        }
        return 0;
    }

    @Autowired
    private HotspotCache hotspotCache;

    private boolean readyToSync = false;

    @Scheduled(fixedDelay = 180_000, initialDelay = 10_000)
    protected void AwsBeaconSync() {
        if ( ! hotspotCache.isReady() ) return;
        if ( ! readyToSync || !serviceEnable ) return;
        if ( ! etlConfig.isBeaconLoadEnable() ) return;

        synchronized (locker) {
            this.runningJobs++;
            this.runningJobsName.merge("Beacon", 1, Integer::sum);
        }
        log.info("Running AwsBeaconService Sync");

        long start = Now.NowUtcMs();
        long lastLog = start;

        try {

            final ListObjectsV2Request lor = new ListObjectsV2Request();
            lor.setBucketName(etlConfig.getAwsBucketName());
            lor.setPrefix("foundation-iot-ingest");
            lor.setStartAfter(beaconFile.getStringValue());
            lor.setRequesterPays(true);

            ListObjectsV2Result list;
            long totalObject = 0;
            long totalSize = 0;
            long totalBeacon = 0;
            do {
                list = this.s3Client.listObjectsV2(lor);
                List<S3ObjectSummary> objects = list.getObjectSummaries();
                for (S3ObjectSummary object : objects) {
                    long cSize = object.getSize();
                    long rSize = 0;
                    totalObject++;
                    totalSize+=object.getSize();
                    if ( object.getSize() == 0 ) continue;
                    long fileStart = Now.NowUtcMs();

                    // Identify the type of objects
                    //  iot_beacon_ingest_report => beacons
                    //  iot_witness_ingest_report => witnesses
                    if ( ! object.getKey().contains(".gz") ) continue; // not a file
                    String fileName = object.getKey().split("/")[1];
                    int fileType = this.getFileType(fileName);
                    long fileDate = Long.parseLong(object.getKey().split("\\.")[1]);
                    if ( fileType != 1 ) continue;
                    if ( fileDate/1000 < etlConfig.getBeaconHistoryStartDate() ) {
                        beaconFile.setStringValue(object.getKey());
                        paramRepository.save(beaconFile);
                        continue;
                    }
                    //log.debug("Processing type "+fileType+": "+fileName+"("+(Now.NowUtcMs() - Long.parseLong(object.getKey().split("\\.")[1]) )/(Now.ONE_FULL_DAY)+") days");

                    final GetObjectRequest or = new GetObjectRequest(object.getBucketName(), object.getKey());
                    or.setRequesterPays(true);
                    S3Object fileObject = this.s3Client.getObject(or);

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
                                rSize += len+4; // read size total for progress
                                if (len > 0) {
                                    byte[] r = bufferedInputStream.readNBytes((int) len);
                                    totalBeacon++;
                                    lora_beacon_ingest_report_v1 b = lora_beacon_ingest_report_v1.parseFrom(r);
                                    if (!hotspotCache.addBeacon(b)) {
                                        log.warn("beacon not processed " + b.getReceivedTimestamp());
                                    }

                                } else {
                                    log.error("Found 0 len entry " + HexaConverters.byteToHexStringWithSpace(sz));
                                }

                                // print progress log on regular basis
                                if ((Now.NowUtcMs() - lastLog) > 30_000) {
                                    String distance_s = object.getKey().split("\\.")[1];
                                    long distance = Now.NowUtcMs() - Long.parseLong(distance_s);
                                    log.info("Beacon Dist: " + Math.floor(distance / Now.ONE_FULL_DAY) + " days, tObject: " + totalObject + " tBeacon: " + totalBeacon + " tSize: " + totalSize / (1024 * 1024) + "MB, Duration: " + (Now.NowUtcMs() - start) / 60_000 + "m");
                                    lastLog = Now.NowUtcMs();
                                }
                                // print process state on exit request
                                if ( serviceEnable == false && (Now.NowUtcMs() - lastLog) > 5_000) {
                                    log.info("Beacon - exit in progress - "+(Math.floor((100*rSize)/cSize))+"%" );
                                    lastLog = Now.NowUtcMs();
                                }

                            } catch ( IOException x ) {
                                // in case of IOException Better skip the file
                                prometeusService.addAwsFailure();
                                log.error("Failed to process file "+object.getKey()+" "+x.getMessage());
                                if ( serviceEnable == false ) return;
                                else break;
                            } catch ( Exception x ) {
                                log.error("AwsBeaconSync - "+x.getMessage());
                                if ( serviceEnable == false ) return;
                                x.printStackTrace();
                            }

                        }
                        bufferedInputStream.close();
                        stream.close();
                        prometeusService.addFileProcessed();
                        prometeusService.addFileProcessedTime(Now.NowUtcMs() - fileStart);
                        try {
                            String time_s = object.getKey().split("\\.")[1];
                            prometeusService.changeFileBeaconTimestamp(Long.parseLong(time_s));
                        } catch (Exception x) {
                            // don't care that is monitoring
                            log.error("Can't parse file timestamp for "+object.getKey());
                        }

                    } catch (IOException x) {
                        prometeusService.addAwsFailure();
                        log.error("Failed to gunzip for Key "+object.getKey()+" "+x.getMessage());
                    } catch (Exception x) {
                        prometeusService.addAwsFailure();
                        log.error("Failed to process file "+object.getKey()+" "+x.getMessage());
                    }

                    if ( fileType == 1 ) {
                        beaconFile.setStringValue(object.getKey());
                        paramRepository.save(beaconFile);
                    }
                    hotspotCache.flushTopLines();
                    if ( serviceEnable == false ) {
                        log.info("Beacon - exit done");
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
            log.error("Beacon Batch Failure "+x.getMessage());
        } finally {
            synchronized (locker) {
                runningJobs--;
                this.runningJobsName.put("Beacon", this.runningJobsName.get("Beacon")-1);
            }
        }

    }


    protected boolean witThreadEnable = true;
    public class ProcessWitness implements Runnable {

        ConcurrentLinkedQueue<lora_witness_ingest_report_v1> queue;
        Boolean status;
        int id;

        public ProcessWitness(int _id, ConcurrentLinkedQueue<lora_witness_ingest_report_v1> _queue, Boolean _status) {
            id = _id;
            queue = _queue;
            status = _status;
        }
        public void run() {
            this.status = true;
            log.info("Starting witness process thread "+id);
            lora_witness_ingest_report_v1 w;
            while ( (w = queue.poll()) != null || witThreadEnable ) {
                if ( w != null) {
                    if (!hotspotCache.addWitness(w)) {
                        log.debug("Th(" + id + ") witness not processed " + w.getReceivedTimestamp());
                    }
                } else {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException x) {x.printStackTrace();}
                }
            }
            log.info("Closing witness process thread "+id);
        }
    }

    @Scheduled(fixedDelay = 60_000, initialDelay = 15_000)
    protected void AwsWitnessSync() {
        if ( ! hotspotCache.isReady() ) return;
        if ( ! readyToSync || !serviceEnable ) return;
        if ( ! etlConfig.isWitnessLoadEnable() ) return;

        log.info("Running AwsWitnessService Sync");

        synchronized (locker) {
            this.runningJobs++;
            this.runningJobsName.merge("Witness", 1, Integer::sum);
        }
        long start = Now.NowUtcMs();
        long lastLog = start;
        this.witThreadEnable = true;


        // Create queues for parallelism
        @SuppressWarnings({"unchecked", "rawtypes"})
        ConcurrentLinkedQueue<lora_witness_ingest_report_v1> queues[] =  new ConcurrentLinkedQueue[etlConfig.getWitnessLoadParallelWorkers()];
        Boolean threadRunning[] = new Boolean[etlConfig.getWitnessLoadParallelWorkers()];
        Thread threads[] = new Thread[etlConfig.getWitnessLoadParallelWorkers()];
        for ( int q = 0 ; q < etlConfig.getWitnessLoadParallelWorkers() ; q++) {
            queues[q] = new ConcurrentLinkedQueue<lora_witness_ingest_report_v1>();
            threadRunning[q] = Boolean.FALSE;
            Runnable r = new ProcessWitness(q,queues[q],threadRunning[q]);
            threads[q] = new Thread(r);
            threads[q].start();
        }

        try {
            final ListObjectsV2Request lor = new ListObjectsV2Request();
            lor.setBucketName(etlConfig.getAwsBucketName());
            lor.setPrefix("foundation-iot-ingest");
            lor.setStartAfter(witnessFile.getStringValue());
            lor.setRequesterPays(true);
            ListObjectsV2Result list;
            long totalObject = 0;
            long totalSize = 0;
            long totalWitness = 0;
            do {
                list = this.s3Client.listObjectsV2(lor);
                List<S3ObjectSummary> objects = list.getObjectSummaries();
                for (S3ObjectSummary object : objects) {
                    long cSize = object.getSize();
                    long rSize = 0;
                    totalObject++;
                    totalSize+=object.getSize();
                    if ( object.getSize() == 0 ) continue;
                    long fileStart = Now.NowUtcMs();

                    // Identify the type of objects
                    //  iot_beacon_ingest_report => beacons
                    //  iot_witness_ingest_report => witnesses
                    if ( ! object.getKey().contains(".gz") ) continue; // not a file
                    String fileName = object.getKey().split("/")[1];
                    int fileType = getFileType(fileName);
                    long fileDate = Long.parseLong(object.getKey().split("\\.")[1]);
                    if ( fileType != 2 ) continue;
                    if ( fileDate/1000 < etlConfig.getWitnessHistoryStartDate() ) {
                        witnessFile.setStringValue(object.getKey());
                        paramRepository.save(witnessFile);
                        continue;
                    }

                    try {
                        long fileTimestamp = Long.parseLong(object.getKey().split("\\.")[1]);
                        long beaconTimestamp = Long.parseLong(beaconFile.getStringValue().split("\\.")[1]);
                        if ( fileTimestamp > (beaconTimestamp - 20*60_000) && etlConfig.isBeaconLoadEnable() ) {
                            // in this situation, the witness file can be too much fresh and we could
                            // have a problem to make the link with the beacon
                            // better waiting
                            // Only makes sense when raw beaons are loaded
                            return;
                        }
                    } catch (Exception x ) {
                        log.error("Impossible to parse file name for "+object.getKey());
                        // better skip it
                        continue;
                    }
                    //log.debug("Processing type "+fileType+": "+fileName+"("+(Now.NowUtcMs() - Long.parseLong(object.getKey().split("\\.")[1]) )/(Now.ONE_FULL_DAY)+") days");

                    final GetObjectRequest or = new GetObjectRequest(object.getBucketName(), object.getKey());
                    or.setRequesterPays(true);
                    S3Object fileObject = this.s3Client.getObject(or);

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
                                rSize += len+4;
                                if (len > 0) {
                                    byte[] r = bufferedInputStream.readNBytes((int) len);
                                    totalWitness++;
                                    lora_witness_ingest_report_v1 w = lora_witness_ingest_report_v1.parseFrom(r);
                                    // Add in queues - find it with a random element in the pub key
                                    // to make sure a single hotspot goes to the same queues to not
                                    // have collisions
                                    int q = w.getReport().getPubKey().byteAt(4);
                                    q &= (etlConfig.getWitnessLoadParallelWorkers()-1);
                                    try {
                                        // when a queue is full just wait, it should be balanced
                                        while (queues[q].size() >= etlConfig.getWitnessLoadParallelQueueSize()) Thread.sleep(2);
                                    } catch ( InterruptedException x ) {x.printStackTrace();};
                                    queues[q].add(w);

                                    /* -- if not parallelized
                                    if (!hotspotCache.addWitness(w)) {
                                        log.debug("witness not processed " + w.getReceivedTimestamp());
                                    }
                                    */

                                } else {
                                    log.error("Found 0 len entry " + HexaConverters.byteToHexStringWithSpace(sz));
                                }

                                // print progress log on regular basis
                                if ((Now.NowUtcMs() - lastLog) > 30_000) {
                                    String distance_s = object.getKey().split("\\.")[1];
                                    long distance = Now.NowUtcMs() - Long.parseLong(distance_s);
                                    log.info("Witness Dist: " + Math.floor(distance / Now.ONE_FULL_DAY) + " days, tObject: " + totalObject + " tWitness: " + totalWitness + " tSize: " + totalSize / (1024 * 1024) + "MB, Duration: " + (Now.NowUtcMs() - start) / 60_000 + "m");
                                    lastLog = Now.NowUtcMs();
                                }
                                // print process state on exit request
                                if ( serviceEnable == false && (Now.NowUtcMs() - lastLog) > 5_000) {
                                    log.info("Witness - exit in progress - "+(Math.floor((100*rSize)/cSize))+"%" );
                                    lastLog = Now.NowUtcMs();
                                }

                            } catch ( IOException x ) {
                                // in case of IOException Better skip the file
                                prometeusService.addAwsFailure();
                                log.error("Failed to process file "+object.getKey()+" "+x.getMessage());
                                if ( serviceEnable == false ) return;
                                else break;
                            } catch ( Exception x ) {
                                log.error("AwsWitnessSync - "+x.getMessage());
                                if ( serviceEnable == false ) return;
                                x.printStackTrace();
                            }

                        } // end of current file
                        bufferedInputStream.close();
                        stream.close();
                        prometeusService.addFileProcessed();
                        prometeusService.addFileProcessedTime(Now.NowUtcMs() - fileStart);
                        try {
                            String time_s = object.getKey().split("\\.")[1];
                            prometeusService.changeFileWitnessTimestamp(Long.parseLong(time_s));
                        } catch (Exception x) {
                            // don't care that is monitoring
                            log.error("Can't parse file timestamp for "+object.getKey());
                        }

                    } catch (IOException x) {
                        prometeusService.addAwsFailure();
                        log.error("Failed to gunzip for Key "+object.getKey()+" "+x.getMessage());
                    } catch (Exception x) {
                        prometeusService.addAwsFailure();
                        log.error("Failed to process file "+object.getKey()+" "+x.getMessage());
                    }

                    if ( fileType == 2 ) {
                        witnessFile.setStringValue(object.getKey());
                        paramRepository.save(witnessFile);
                    }
                    hotspotCache.flushTopLines();
                    if ( serviceEnable == false ) {
                        // we had a request to quit and at this point we can make it
                        // clean
                        log.info("Witness - exit ready");
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
            log.error("Witness Batch Failure "+x.getMessage());
        } finally {
            // wait the parallel Thread to stop max 5 minutes
            this.witThreadEnable = false;
            boolean terminated = false;
            long waitStart = Now.NowUtcMs();
            while ( !terminated && ((Now.NowUtcMs() - waitStart) < 600_000 )) {
                terminated = true;
                for (int t = 0; t < etlConfig.getWitnessLoadParallelWorkers(); t++) {
                    if (threads[t].getState() != Thread.State.TERMINATED) terminated = false;
                }
                try { Thread.sleep(500); } catch (InterruptedException x ) {};
            }
            if ( !terminated ) {
                log.error("Cancelling Thread before ending enqueing");
            }
            synchronized (locker) {
                runningJobs--;
                this.runningJobsName.put("Witness", this.runningJobsName.get("Witness")-1);

            }
            log.info("Witness - exit completed");
        }
    }

    // ---------------------------------------
    // IoT Poc
    // ---------------------------------------

    protected boolean pocThreadEnable = true;
    protected volatile boolean firstFile = true;

    public class ProcessIoTPoc implements Runnable {

        ConcurrentLinkedQueue<lora_poc_v1> queue;
        Boolean status;
        int id;
        long lastTrace = 0;

        public ProcessIoTPoc(int _id, ConcurrentLinkedQueue<lora_poc_v1> _queue, Boolean _status) {
            id = _id;
            queue = _queue;
            status = _status;
        }
        public void run() {
            this.status = true;
            log.debug("Starting iot_poc process thread "+id);
            lora_poc_v1 w;
            while ( (w = queue.poll()) != null || pocThreadEnable ) {
                if ( ! pocThreadEnable ) {
                    // trace shutdown
                    if ( (Now.NowUtcMs() - lastTrace) > 5_000 ) {
                        log.info("Th(" + id + ") shutting down - still "+queue.size()+" to process");
                        lastTrace = Now.NowUtcMs();
                    }
                }
                if ( w != null) {
                   if (!hotspotCache.addIoTPoC(w, firstFile)) {
                        log.debug("Th(" + id + ") iotpoc not processed " + w.getBeaconReport().getReceivedTimestamp());
                   }
                } else {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException x) {x.printStackTrace();}
                }
            }
            log.info("Closing iot_poc process thread "+id);
        }
    }

    private long currentHour = 0;
    private long currentHourTs = 0;

    @Scheduled(fixedDelay = 60_000, initialDelay = 17_000)
    protected void AwsIoTPocSync() {
        if ( ! hotspotCache.isReady() ) return;
        if ( ! readyToSync || !serviceEnable ) return;
        if ( ! etlConfig.isIotpocLoadEnable() ) return;
        log.info("Running AwsIoTPoc Sync");

        synchronized (locker) {
            this.runningJobs++;
            this.runningJobsName.merge("Poc", 1, Integer::sum);
        }
        long start = Now.NowUtcMs();
        long lastLog = start;
        this.pocThreadEnable = true;
        int retry = 0;

        // Create queues for parallelism
        @SuppressWarnings({"unchecked", "rawtypes"})
        ConcurrentLinkedQueue<lora_poc_v1> queues[] =  new ConcurrentLinkedQueue[etlConfig.getIotpocLoadParallelWorkers()];
        Boolean threadRunning[] = new Boolean[etlConfig.getIotpocLoadParallelWorkers()];
        Thread threads[] = new Thread[etlConfig.getIotpocLoadParallelWorkers()];
        for ( int q = 0 ; q < etlConfig.getIotpocLoadParallelWorkers() ; q++) {
            queues[q] = new ConcurrentLinkedQueue<lora_poc_v1>();
            threadRunning[q] = Boolean.FALSE;
            Runnable r = new ProcessIoTPoc(q,queues[q],threadRunning[q]);
            threads[q] = new Thread(r);
            threads[q].start();
        }

        long totalObject = 0;
        try {
            final ListObjectsV2Request lor = new ListObjectsV2Request();
            lor.setBucketName(etlConfig.getAwsBucketName());
            lor.setPrefix("foundation-iot-verified-rewards");
            lor.setStartAfter(iotPocFile.getStringValue());
            lor.setRequesterPays(true);
            ListObjectsV2Result list;
            long totalSize = 0;
            long totalWitness = 0;
            do {
                list = this.s3Client.listObjectsV2(lor);
                List<S3ObjectSummary> objects = list.getObjectSummaries();
                for (S3ObjectSummary object : objects) {

                    long cSize = object.getSize();
                    long rSize = 0;
                    totalObject++;
                    totalSize+=object.getSize();
                    if ( object.getSize() == 0 ) continue;
                    long fileStart = Now.NowUtcMs();

                    // Identify the type of objects
                    //  iot_poc => valid poc
                    if ( ! object.getKey().contains(".gz") ) continue; // not a file
                    String fileName = object.getKey().split("/")[1];
                    int fileType = getFileType(fileName);
                    long fileDate = Long.parseLong(object.getKey().split("\\.")[1]);
                    if ( fileType != 3 ) continue;
                    if ( fileDate/1000 < etlConfig.getIotpocHistoryStartDate() ) {
                        iotPocFile.setStringValue(object.getKey());
                        paramRepository.save(iotPocFile);
                        continue;
                    }

                    // prometheus update for getting time to process 1 hour as indicator
                    // round file date to 1 hour
                    long fileHour = fileDate / 3_600_000;
                    fileHour = fileHour * 3_600_000;
                    if ( currentHour == 0 ) {
                        currentHour = fileHour;
                        currentHourTs = Now.NowUtcMs();
                    }
                    if ( currentHour != fileHour ) {
                        // new hour
                        prometeusService.setHourlyIoTRate(Now.NowUtcMs()-currentHourTs);
                        currentHourTs = Now.NowUtcMs();
                        currentHour = fileHour;
                    }


                    /* --  no need to sync with beacon read as the verified poc are including Beacon & Poc all at once
                    try {
                        long fileTimestamp = Long.parseLong(object.getKey().split("\\.")[1]);
                        long beaconTimestamp = Long.parseLong(beaconFile.getStringValue().split("\\.")[1]);
                        if ( fileTimestamp > (beaconTimestamp - 20*60_000) ) {
                            // in this situation, the witness file can be too much fresh and we could
                            // have a problem to make the link with the beacon
                            // better waiting
                            return;
                        }
                    } catch (Exception x ) {
                        log.error("Impossible to parse file name for "+object.getKey());
                        // better skip it
                        continue;
                    }
                    */

                    log.debug("Processing type "+fileType+": "+fileName+"("+(Now.NowUtcMs() - fileDate )/(Now.ONE_FULL_DAY)+") days");

                    boolean readOk = false;
                    retry = 0;
                    ArrayList<lora_poc_v1> toProcess = new ArrayList<>();
                    while ( ! readOk && retry < 10 ) {
                        final GetObjectRequest or = new GetObjectRequest(object.getBucketName(), object.getKey());
                        or.setRequesterPays(true);
                        S3Object fileObject = this.s3Client.getObject(or);

                        GZIPInputStream stream = null;
                        BufferedInputStream bufferedInputStream = null;
                        try {
                            // File is GZiped Version of a stream of protobuf messages
                            // each protobuf messages is encapsulated with a header
                            // int4 containing the length of the protobuf message following.
                            stream = new GZIPInputStream(fileObject.getObjectContent());
                            bufferedInputStream = new BufferedInputStream(stream);
                            while (bufferedInputStream.available() > 0) {
                                try {
                                    byte[] sz = bufferedInputStream.readNBytes(4);
                                    long len = Stuff.getLongValueFromBytes(sz);
                                    rSize += len + 4;
                                    if (len > 0) {
                                        byte[] r = bufferedInputStream.readNBytes((int) len);
                                        lora_poc_v1 w = lora_poc_v1.parseFrom(r);
                                        toProcess.add(w);
                                    } else {
                                        log.error("Found 0 len entry " + HexaConverters.byteToHexStringWithSpace(sz));
                                    }
                                } catch (IOException x) {
                                    // in case of IOException Better skip the file
                                    prometeusService.addAwsFailure();
                                    log.error("Failed to process file " + object.getKey() + " at entry " + toProcess.size() + "(" + x.getMessage() + ")");
                                    if (serviceEnable == false) return;
                                    toProcess.clear();
                                    retry++;
                                    break;
                                } catch (Exception x) {
                                    log.error("AwsIoTPocSync - " + x.getMessage());
                                    if (serviceEnable == false) return;
                                    x.printStackTrace();
                                    toProcess.clear();
                                    retry++;
                                    break;
                                }
                            }
                            readOk = true;
                        } catch (IOException x) {
                            prometeusService.addAwsFailure();
                            log.error("Failed to gunzip for Key " + object.getKey() + " " + x.getMessage());
                            toProcess.clear();
                            retry++;
                        } catch (Exception x) {
                            prometeusService.addAwsFailure();
                            log.error("Failed to process file " + object.getKey() + " " + x.getMessage());
                            toProcess.clear();
                            retry++;
                        } finally {
                            if ( bufferedInputStream != null ) bufferedInputStream.close();
                            if (stream != null ) stream.close();
                        }
                    }
                    if ( retry == 10 ) {
                        log.error("[!!!] Impossible to process file " + object.getKey()+ " skip it");
                        continue;
                    }

                    log.info("IotPoc from "+new Date(new Timestamp(fileDate).getTime())+" has "+toProcess.size()+" objects" );
                    int current = 0;
                    for (lora_poc_v1 w : toProcess) {
                        try {
                            totalWitness++;

                            // Add in queues - find it with a random element in the pub key
                            // to make sure a single hotspot goes to the same queues to not
                            // have collisions
                            int q = w.getBeaconReport().getReport().getPubKey().byteAt(4)
                                    + w.getBeaconReport().getReport().getPubKey().byteAt(5)
                                    + w.getBeaconReport().getReport().getPubKey().byteAt(6)
                                    + w.getBeaconReport().getReport().getPubKey().byteAt(7);
                            q &= (etlConfig.getIotpocLoadParallelWorkers() - 1);
                            try {
                                // when a queue is full just wait, it should be balanced
                                while (queues[q].size() >= etlConfig.getIotpocLoadParallelQueueSize()) Thread.sleep(2);
                            } catch (InterruptedException x) {
                                x.printStackTrace();
                            }
                            queues[q].add(w);

                            // print progress log on regular basis
                            if ((Now.NowUtcMs() - lastLog) > 30_000) {
                                long distance = Now.NowUtcMs() - fileDate;
                                log.info("IoTPoc Dist: " + Math.floor(distance / Now.ONE_FULL_DAY) + " days,fpro : "+current+"/"+toProcess.size()+" tObject: " + totalObject + " tPoc: " + totalWitness + " tSize: " + totalSize / (1024 * 1024) + "MB, Duration: " + (Now.NowUtcMs() - start) / 60_000 + "m");
                                lastLog = Now.NowUtcMs();
                            }
                            // print process state on exit request
                            if (!serviceEnable && (Now.NowUtcMs() - lastLog) > 5_000) {
                                log.info("IoTPoc - exit in progress - " + (Math.floor((100 * current) / toProcess.size())) + "%");
                                lastLog = Now.NowUtcMs();
                            }
                            current++;
                        } catch ( Exception x ) {
                            log.error("Failed to process IoTPoc "+object.getKey()+" at "+current+"/"+toProcess.size()+"["+x.getMessage()+"]");
                        }
                    } // end of current file
                    toProcess.clear();

                    if ( this.firstFile ) {
                        // we need to wait for the queue to be empty
                        int pending;
                        do {
                            pending = 0;
                            for ( int q = 0; q < etlConfig.getIotpocLoadParallelWorkers() ; q++ ) {
                                pending += queues[q].size();
                            }
                            try { Thread.sleep(10_000); } catch (InterruptedException x) {};
                            log.info("> Waiting for 1st file to be finished... "+pending);
                        } while ( pending > 0 );
                        this.firstFile = false;
                    }

                    prometeusService.addFileProcessed();
                    prometeusService.addFileProcessedTime(Now.NowUtcMs() - fileStart);
                    prometeusService.changeFileIoTPocTimestamp(fileDate);

                    iotPocFile.setStringValue(object.getKey());
                    paramRepository.save(iotPocFile);

                    hotspotCache.flushTopLines();
                    if (!serviceEnable) {
                        // we had a request to quit and at this point we can make it
                        // clean
                        log.info("IoTPoc - exit ready");
                        return;
                    }
                }
                lor.setContinuationToken(list.getNextContinuationToken());

            } while (list.isTruncated());
        } catch (AmazonServiceException x) {
            prometeusService.addAwsFailure();
            log.error("AwsIoTPocSync - Service "+x.getMessage());
            //x.printStackTrace();
        } catch (AmazonClientException x) {
            prometeusService.addAwsFailure();
            log.error("AwsIoTPocSync - Client "+x.getMessage());
            //x.printStackTrace();
        } catch (Exception x) {
            prometeusService.addAwsFailure();
            log.error("IoTPoc Batch Failure "+x.getMessage());
            //x.printStackTrace();
        } finally {
            // wait the parallel Thread to stop max 5 minutes
            this.pocThreadEnable = false;
            boolean terminated = false;
            long waitStart = Now.NowUtcMs();
            while ( !terminated && ((Now.NowUtcMs() - waitStart) < 600_000 )) {
                terminated = true;
                for (int t = 0; t < etlConfig.getIotpocLoadParallelWorkers(); t++) {
                    if (threads[t].getState() != Thread.State.TERMINATED) terminated = false;
                }
                try { Thread.sleep(500); } catch (InterruptedException x ) {};
            }
            if ( !terminated ) {
                log.error("Cancelling Thread before ending enqueing");
            }
            synchronized (locker) {
                runningJobs--;
                this.runningJobsName.put("Poc", this.runningJobsName.get("Poc")-1);
            }
            log.info("IoTPoc - exit completed - objects seen "+totalObject);

        }
    }



    // ---------------------------------------
    // Hotspots Rewards
    // ---------------------------------------

    protected boolean rewardThreadEnable = true;

    public class ProcessRewards implements Runnable {

        ConcurrentLinkedQueue<iot_reward_share> queue;
        Boolean status;
        int id;

        public ProcessRewards(int _id, ConcurrentLinkedQueue<iot_reward_share> _queue, Boolean _status) {
            id = _id;
            queue = _queue;
            status = _status;
        }
        public void run() {
            this.status = true;
            log.debug("Starting Reward process thread "+id);
            iot_reward_share w;
            while ( (w = queue.poll()) != null || rewardThreadEnable ) {
                if ( w != null) {
                    if (!hotspotCache.addReward(w)) {
                        log.debug("Th(" + id + ") reward not processed " + w.getStartPeriod());
                    }
                } else {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException x) {x.printStackTrace();}
                }
            }
            log.debug("Closing rewards process thread "+id);
        }
    }


    // Reward are once a day, so no need to search faster than on every 20 minutes
    @Scheduled(fixedDelay = 3600_000, initialDelay = 13_000)
    protected void AwsRewardSync() {
        if ( ! hotspotCache.isReady() ) return;
        if ( ! readyToSync || !serviceEnable ) return;
        if ( ! etlConfig.isRewardLoadEnable() ) return;
        log.info("Running AwsRewardService Sync");

        synchronized (locker) {
            this.runningJobs++;
            this.runningJobsName.merge("IoTRew", 1, Integer::sum);
        }
        long start = Now.NowUtcMs();
        long lastLog = start;
        this.rewardThreadEnable = true;
        int retry = 0;

        // Create queues for parallelism
        @SuppressWarnings({"unchecked", "rawtypes"})
        ConcurrentLinkedQueue<iot_reward_share> queues[] =  new ConcurrentLinkedQueue[etlConfig.getRewardLoadParallelWorkers()];
        Boolean threadRunning[] = new Boolean[etlConfig.getRewardLoadParallelWorkers()];
        Thread threads[] = new Thread[etlConfig.getRewardLoadParallelWorkers()];
        for ( int q = 0 ; q < etlConfig.getRewardLoadParallelWorkers() ; q++) {
            queues[q] = new ConcurrentLinkedQueue<iot_reward_share>();
            threadRunning[q] = Boolean.FALSE;
            Runnable r = new ProcessRewards(q,queues[q],threadRunning[q]);
            threads[q] = new Thread(r);
            threads[q].start();
        }

        long totalObject = 0;
        try {
            final ListObjectsV2Request lor = new ListObjectsV2Request();
            lor.setBucketName(etlConfig.getAwsBucketName());
            lor.setPrefix("foundation-iot-verified-rewards");
            lor.setStartAfter(rewardPocFile.getStringValue());
            lor.setRequesterPays(true);
            ListObjectsV2Result list;
            long totalSize = 0;
            long totalWitness = 0;
            int notExpectedFile = 0;
            do {
                list = this.s3Client.listObjectsV2(lor);
                List<S3ObjectSummary> objects = list.getObjectSummaries();
                for (S3ObjectSummary object : objects) {
                    long cSize = object.getSize();
                    long rSize = 0;
                    totalObject++;
                    totalSize+=object.getSize();
                    if ( object.getSize() == 0 ) continue;
                    long fileStart = Now.NowUtcMs();

                    // Identify the type of objects
                    //  iot_poc => valid poc
                    if ( ! object.getKey().contains(".gz") ) continue; // not a file
                    String fileName = object.getKey().split("/")[1];
                    int fileType = getFileType(fileName);
                    long fileDate = Long.parseLong(object.getKey().split("\\.")[1]);
                    if ( fileType != 4 ) {
                        notExpectedFile++;
                        if ( notExpectedFile < 5 ) continue;
                        return; // end of the files
                    } else {
                        notExpectedFile = 0; // reset counter
                    }
                    if ( fileDate/1000 < etlConfig.getRewardHistoryStartDate() ) {
                        rewardPocFile.setStringValue(object.getKey());
                        paramRepository.save(rewardPocFile);
                        continue;
                    }

                    log.debug("Processing type "+fileType+": "+fileName+"("+(Now.NowUtcMs() - fileDate )/(Now.ONE_FULL_DAY)+") days");

                    boolean readOk = false;
                    retry = 0;
                    ArrayList<iot_reward_share> toProcess = new ArrayList<>();
                    while ( ! readOk && retry < 10 ) {

                        final GetObjectRequest or = new GetObjectRequest(object.getBucketName(), object.getKey());
                        or.setRequesterPays(true);
                        S3Object fileObject = this.s3Client.getObject(or);
                        GZIPInputStream stream = null;
                        BufferedInputStream bufferedInputStream = null;

                        try {
                            // File is GZiped Version of a stream of protobuf messages
                            // each protobuf messages is encapsulated with a header
                            // int4 containing the length of the protobuf message following.
                            stream = new GZIPInputStream(fileObject.getObjectContent());
                            bufferedInputStream = new BufferedInputStream(stream);
                            while (bufferedInputStream.available() > 0) {
                                try {
                                    byte[] sz = bufferedInputStream.readNBytes(4);
                                    long len = Stuff.getLongValueFromBytes(sz);
                                    rSize += len + 4;
                                    if (len > 0) {
                                        byte[] r = bufferedInputStream.readNBytes((int) len);
                                        iot_reward_share w = iot_reward_share.parseFrom(r);
                                        toProcess.add(w);
                                    } else {
                                        log.warn("Reward - Found 0 len entry " + HexaConverters.byteToHexStringWithSpace(sz));
                                    }
                                } catch (IOException x) {
                                    // in case of IOException Better skip the file
                                    prometeusService.addAwsFailure();
                                    log.error("Failed to process file " + object.getKey() + " " + x.getMessage());
                                    if (serviceEnable == false) return;
                                    toProcess.clear();
                                    retry++;
                                    break;
                                } catch (Exception x) {
                                    log.error("AwsRewardSync - " + x.getMessage());
                                    if (serviceEnable == false) return;
                                    x.printStackTrace();
                                    toProcess.clear();
                                    retry++;
                                    break;
                                }
                            }
                            readOk = true;
                        } catch (IOException x) {
                            prometeusService.addAwsFailure();
                            log.error("Failed to gunzip for Key " + object.getKey() + " " + x.getMessage());
                            toProcess.clear();
                            retry++;
                        } catch (Exception x) {
                            prometeusService.addAwsFailure();
                            log.error("Failed to process file " + object.getKey() + " " + x.getMessage());
                            toProcess.clear();
                            retry++;
                        } finally {
                            if (bufferedInputStream != null) bufferedInputStream.close();
                            if (stream != null) stream.close();
                        }
                    }
                    if (retry == 10) {
                        log.warn("Impossible to process file " + object.getKey() + " skip it");
                        continue;
                    }

                    log.info("Reward from "+new Date(new Timestamp(fileDate).getTime())+" has "+toProcess.size()+" objects" );
                    int current = 0;

                    for ( iot_reward_share w : toProcess ) {
                        try {
                            totalWitness++;
                            // Add in queues - find it with a random element in the pub key
                            // to make sure a single hotspot goes to the same queues to not
                            // have collisions
                            int q = 0;
                            if ( w.getGatewayReward().getHotspotKey().size() > 4 ) {
                                q = w.getGatewayReward().getHotspotKey().byteAt(4);
                                q &= (etlConfig.getRewardLoadParallelWorkers() - 1);
                            } else {
                                // sounds like operational reward, skip that one
                                log.warn("Potential operational reward");
                                continue;
                            }
                            try {
                                // when a queue is full just wait, it should be balanced
                                while (queues[q].size() >= etlConfig.getRewardLoadParallelQueueSize()) Thread.sleep(2);
                            } catch ( InterruptedException x ) {x.printStackTrace();};
                            queues[q].add(w);

                            // print progress log on regular basis
                            if ((Now.NowUtcMs() - lastLog) > 30_000) {
                                long distance = Now.NowUtcMs() - fileDate;
                                log.info("Rewards Dist: " + Math.floor(distance / Now.ONE_FULL_DAY) + " days, fpro : "+current+"/"+toProcess.size()+" tObject: " + totalObject + " tReward: " + totalWitness + " tSize: " + totalSize / (1024 * 1024) + "MB, Duration: " + (Now.NowUtcMs() - start) / 60_000 + "m");
                                lastLog = Now.NowUtcMs();
                            }
                            // print process state on exit request
                            if ( serviceEnable == false && (Now.NowUtcMs() - lastLog) > 5_000) {
                                log.info("Rewards - exit in progress - "+(Math.floor((100*current)/toProcess.size()))+"%" );
                                lastLog = Now.NowUtcMs();
                            }
                            current++;
                        } catch ( Exception x ) {
                            log.error("Failed to process IotReward "+object.getKey()+" at "+current+"/"+toProcess.size()+"["+x.getMessage()+"]");
                        }
                    } // end of current file
                    toProcess.clear();

                    prometeusService.addFileProcessed();
                    prometeusService.addFileProcessedTime(Now.NowUtcMs() - fileStart);
                    prometeusService.changeFileRewardTimestamp(fileDate);

                    rewardPocFile.setStringValue(object.getKey());
                    paramRepository.save(rewardPocFile);

                    if ( serviceEnable == false ) {
                        // we had a request to quit and at this point we can make it
                        // clean
                        log.info("Reward - exit ready");
                        return;
                    }
                }
                lor.setContinuationToken(list.getNextContinuationToken());

            } while (list.isTruncated());
        } catch (AmazonServiceException x) {
            prometeusService.addAwsFailure();
            log.error("AwsRewardSync - "+x.getMessage());
           // x.printStackTrace();
        } catch (AmazonClientException x) {
            prometeusService.addAwsFailure();
            log.error("AwsRewardSync - "+x.getMessage());
           // x.printStackTrace();
        } catch (Exception x) {
            prometeusService.addAwsFailure();
            log.error("Reward Batch Failure "+x.getMessage());
        } finally {
            // wait the parallel Thread to stop max 5 minutes
            this.rewardThreadEnable = false;
            boolean terminated = false;
            long waitStart = Now.NowUtcMs();
            while ( !terminated && ((Now.NowUtcMs() - waitStart) < 600_000 )) {
                terminated = true;
                for (int t = 0; t < etlConfig.getRewardLoadParallelWorkers(); t++) {
                    if (threads[t].getState() != Thread.State.TERMINATED) terminated = false;
                }
                try { Thread.sleep(500); } catch (InterruptedException x ) {};
            }
            if ( !terminated ) {
                log.error("Cancelling Reward Thread before ending dequeuing");
            } else {
                // flush pending rewards store
                hotspotCache.bulkInsertRewards();
            }
            synchronized (locker) {
                runningJobs--;
                this.runningJobsName.put("IoTRew", this.runningJobsName.get("IoTRew")-1);
            }
            log.info("Rewards - exit completed - object seen "+totalObject);
        }
    }


    // ---------------------------------------
    // Mobile Rewards
    // ---------------------------------------

    protected boolean mobileRewardThreadEnable = true;

    public static class MobileReward {
        public radio_reward_share oldReward;        // depreacted format
        public mobile_reward_share newReward;
    }


    public class ProcessMobileRewards implements Runnable {

        ConcurrentLinkedQueue<MobileReward> queue;
        Boolean status;
        int id;

        public ProcessMobileRewards(int _id, ConcurrentLinkedQueue<MobileReward> _queue, Boolean _status) {
            id = _id;
            queue = _queue;
            status = _status;
        }
        public void run() {
            this.status = true;
            log.debug("Starting Mobile Reward process thread "+id);
            MobileReward w;
            while ( (w = queue.poll()) != null || mobileRewardThreadEnable ) {
                if ( w != null) {
                    if (!hotspotCache.addMobileReward(w)) {
                        if ( w.oldReward != null ) log.debug("Th(" + id + ") mobile reward not processed " + w.oldReward.getStartEpoch());
                        else if (w.newReward != null ) log.debug("Th(" + id + ") mobile reward not processed " + w.newReward.getStartPeriod());
                    }
                } else {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException x) {x.printStackTrace();}
                }
            }
            log.debug("Closing mobile rewards process thread "+id);
        }
    }


    // Reward are once a day, so no need to search faster than on every 20 minutes
    @Scheduled(fixedDelay = 7200_000, initialDelay = 13_000)
    protected void AwsMobileRewardSync() {
        if ( ! readyToSync || !serviceEnable ) return;
        if ( ! etlConfig.isMobileRewardLoadEnable() ) return;
        log.info("Running AwsMobileRewardService Sync");

        synchronized (locker) {
            this.runningJobs++;
            this.runningJobsName.merge("MobileRew", 1, Integer::sum);
        }
        long start = Now.NowUtcMs();
        long lastLog = start;
        this.mobileRewardThreadEnable = true;
        int retry = 0;


        // Create queues for parallelism
        @SuppressWarnings({"unchecked", "rawtypes"})
        ConcurrentLinkedQueue<MobileReward> queues[] =  new ConcurrentLinkedQueue[etlConfig.getMobileRewardLoadParallelWorkers()];
        Boolean threadRunning[] = new Boolean[etlConfig.getMobileRewardLoadParallelWorkers()];
        Thread threads[] = new Thread[etlConfig.getMobileRewardLoadParallelWorkers()];
        for ( int q = 0 ; q < etlConfig.getMobileRewardLoadParallelWorkers() ; q++) {
            queues[q] = new ConcurrentLinkedQueue<MobileReward>();
            threadRunning[q] = Boolean.FALSE;
            Runnable r = new ProcessMobileRewards(q,queues[q],threadRunning[q]);
            threads[q] = new Thread(r);
            threads[q].start();
        }

        long totalObject = 0;
        try {
            if ( mobileRewardFile.getStringValue().compareToIgnoreCase(MOBILE_REWARD_LAST_OBJECT) == 0 ) {
                log.info("Mobile Reward - switching files");
                mobileRewardFile.setStringValue(MOBILE_REWARD_FIRST_NEW_OBJECT);
            }
            boolean isNewType = mobileRewardFile.getStringValue().contains("mobile_reward");
            final ListObjectsV2Request lor = new ListObjectsV2Request();
            lor.setBucketName(etlConfig.getAwsBucketName());
            lor.setPrefix("foundation-mobile-verified");
            lor.setStartAfter(mobileRewardFile.getStringValue());
            lor.setRequesterPays(true);
            ListObjectsV2Result list;
            long totalSize = 0;
            long totalMobileRewards = 0;
            int notExpectedFile = 0;
            do {
                list = this.s3Client.listObjectsV2(lor);
                List<S3ObjectSummary> objects = list.getObjectSummaries();
                for (S3ObjectSummary object : objects) {
                    long cSize = object.getSize();
                    long rSize = 0;
                    totalObject++;
                    totalSize+=object.getSize();
                    if ( object.getSize() == 0 ) continue;
                    long fileStart = Now.NowUtcMs();

                    // Identify the type of objects
                    //  iot_poc => valid poc
                    if ( ! object.getKey().contains(".gz") ) continue; // not a file
                    String fileName = object.getKey().split("/")[1];
                    int fileType = getFileType(fileName);
                    long fileDate = Long.parseLong(object.getKey().split("\\.")[1]);
                    if ( fileType != 5 && fileType != 6  ) {
                        notExpectedFile++;
                        if ( notExpectedFile < 5 ) continue;
                        return; // end of the files
                    } else {
                        notExpectedFile = 0; // reset counter
                    }

                    if ( isNewType && fileType == 5 ) continue; // do not reprocess file in past due to naming.

                    if ( fileDate/1000 < etlConfig.getRewardHistoryStartDate() ) {
                        mobileRewardFile.setStringValue(object.getKey());
                        paramRepository.save(mobileRewardFile);
                        continue;
                    }

                    log.debug("Processing type "+fileType+": "+fileName+"("+(Now.NowUtcMs() - fileDate )/(Now.ONE_FULL_DAY)+") days");

                    boolean readOk = false;
                    retry = 0;
                    ArrayList<MobileReward> toProcess = new ArrayList<>();
                    while ( ! readOk && retry < 10 ) {

                        final GetObjectRequest or = new GetObjectRequest(object.getBucketName(), object.getKey());
                        or.setRequesterPays(true);
                        S3Object fileObject = this.s3Client.getObject(or);
                        GZIPInputStream stream = null;
                        BufferedInputStream bufferedInputStream = null;

                        try {
                            // File is GZiped Version of a stream of protobuf messages
                            // each protobuf messages is encapsulated with a header
                            // int4 containing the length of the protobuf message following.
                            stream = new GZIPInputStream(fileObject.getObjectContent());
                            bufferedInputStream = new BufferedInputStream(stream);
                            while (bufferedInputStream.available() > 0) {
                                try {
                                    byte[] sz = bufferedInputStream.readNBytes(4);
                                    long len = Stuff.getLongValueFromBytes(sz);
                                    rSize += len + 4;
                                    if (len > 0) {
                                        byte[] r = bufferedInputStream.readNBytes((int) len);
                                        MobileReward mr = new MobileReward();
                                        if ( fileType == 5 ) {
                                            mr.oldReward = radio_reward_share.parseFrom(r);
                                            mr.newReward = null;
                                        } else {
                                            mr.oldReward = null;
                                            mr.newReward = mobile_reward_share.parseFrom(r);
                                        }
                                        toProcess.add(mr);
                                    } else {
                                        log.warn("Mobile Reward - Found 0 len entry " + HexaConverters.byteToHexStringWithSpace(sz));
                                    }
                                } catch (IOException x) {
                                    // in case of IOException Better skip the file
                                    prometeusService.addAwsFailure();
                                    log.error("Failed to process file " + object.getKey() + " " + x.getMessage());
                                    if (serviceEnable == false) return;
                                    toProcess.clear();
                                    retry++;
                                    break;
                                } catch (Exception x) {
                                    log.error("AwsMobileRewardSync - " + x.getMessage());
                                    if (serviceEnable == false) return;
                                    x.printStackTrace();
                                    toProcess.clear();
                                    retry++;
                                    break;
                                }
                            }
                            readOk = true;
                        } catch (IOException x) {
                            prometeusService.addAwsFailure();
                            log.error("Failed to gunzip for Key " + object.getKey() + " " + x.getMessage());
                            toProcess.clear();
                            retry++;
                        } catch (Exception x) {
                            prometeusService.addAwsFailure();
                            log.error("Failed to process file " + object.getKey() + " " + x.getMessage());
                            toProcess.clear();
                            retry++;
                        } finally {
                            if (bufferedInputStream != null) bufferedInputStream.close();
                            if (stream != null) stream.close();
                        }
                    }
                    if (retry == 10) {
                        log.warn("Impossible to process file " + object.getKey() + " skip it");
                        continue;
                    }

                    log.info("Mobile Reward from "+new Date(new Timestamp(fileDate).getTime())+" has "+toProcess.size()+" objects" );
                    int current = 0;

                    for ( MobileReward w : toProcess ) {
                        try {
                            totalMobileRewards++;
                            // Add in queues - find it with a random element in the pub key
                            // to make sure a single hotspot goes to the same queues to not
                            // have collisions
                            int q = 0;
                            if ( w.oldReward != null ) {
                                if ( w.oldReward.getHotspotKey().size() > 4 ) {
                                    q = w.oldReward.getHotspotKey().byteAt(4);
                                    q &= (etlConfig.getMobileRewardLoadParallelWorkers() - 1);
                                } else {
                                    // sounds like operational reward, skip that one
                                    log.warn("Potential operational mobile reward");
                                    continue;
                                }
                            } else {
                                if ( w.newReward.hasGatewayReward() && w.newReward.getGatewayReward().getHotspotKey().size() > 4 ) {
                                    q = w.newReward.getGatewayReward().getHotspotKey().byteAt(4);
                                    q &= (etlConfig.getMobileRewardLoadParallelWorkers() - 1);
                                } else if ( w.newReward.hasRadioReward() && w.newReward.getRadioReward().getHotspotKey().size() > 4 ) {
                                    q = w.newReward.getRadioReward().getHotspotKey().byteAt(4);
                                    q &= (etlConfig.getMobileRewardLoadParallelWorkers() - 1);
                                } else if ( w.newReward.hasSubscriberReward() && w.newReward.getSubscriberReward().getSubscriberId().size() > 4 ) {
                                    q = w.newReward.getSubscriberReward().getSubscriberId().byteAt(4);
                                    q &= (etlConfig.getMobileRewardLoadParallelWorkers() - 1);
                                } else if ( w.newReward.hasServiceProviderReward() || w.newReward.hasUnallocatedReward() ) {
                                    q = (int) (etlConfig.getMobileRewardLoadParallelWorkers() * Math.random()); // unclear how to get an address ramdomize it
                                    q &= (etlConfig.getMobileRewardLoadParallelWorkers() - 1); // make sure (too late)
                                } else {
                                    // sounds like strange reward, skip that one
                                    log.warn("Potential strange mobile reward");
                                    continue;
                                }
                            }
                            try {
                                // when a queue is full just wait, it should be balanced
                                while (queues[q].size() >= etlConfig.getMobileRewardLoadParallelQueueSize()) Thread.sleep(2);
                            } catch ( InterruptedException x ) {x.printStackTrace();};
                            queues[q].add(w);

                            // print progress log on regular basis
                            if ((Now.NowUtcMs() - lastLog) > 30_000) {
                                long distance = Now.NowUtcMs() - fileDate;
                                log.info("Mobile Rewards Dist: " + Math.floor(distance / Now.ONE_FULL_DAY) + " days, fpro : "+current+"/"+toProcess.size()+" tObject: " + totalObject + " tReward: " + totalMobileRewards + " tSize: " + totalSize / (1024 * 1024) + "MB, Duration: " + (Now.NowUtcMs() - start) / 60_000 + "m");
                                lastLog = Now.NowUtcMs();
                            }
                            // print process state on exit request
                            if ( serviceEnable == false && (Now.NowUtcMs() - lastLog) > 5_000) {
                                log.info("Mobile Rewards - exit in progress - "+(Math.floor((100*current)/toProcess.size()))+"%" );
                                lastLog = Now.NowUtcMs();
                            }
                            current++;
                        } catch ( Exception x ) {
                            log.error("Failed to process Mobile Reward "+object.getKey()+" at "+current+"/"+toProcess.size()+"["+x.getMessage()+"]");
                        }
                    } // end of current file
                    toProcess.clear();

                    prometeusService.addFileProcessed();
                    prometeusService.addFileProcessedTime(Now.NowUtcMs() - fileStart);
                    prometeusService.changeFileMobileRewardTimestamp(fileDate);

                    mobileRewardFile.setStringValue(object.getKey());
                    paramRepository.save(mobileRewardFile);

                    if ( serviceEnable == false ) {
                        // we had a request to quit and at this point we can make it
                        // clean
                        log.info("Mobile Reward - exit ready");
                        return;
                    }
                }
                lor.setContinuationToken(list.getNextContinuationToken());

            } while (list.isTruncated());
        } catch (AmazonServiceException x) {
            prometeusService.addAwsFailure();
            log.error("AwsMobileRewardSync - "+x.getMessage());
            // x.printStackTrace();
        } catch (AmazonClientException x) {
            prometeusService.addAwsFailure();
            log.error("AwsMobileRewardSync - "+x.getMessage());
            // x.printStackTrace();
        } catch (Exception x) {
            prometeusService.addAwsFailure();
            log.error("Mobile Reward Batch Failure "+x.getMessage());
        } finally {
            // wait the parallel Thread to stop max 5 minutes
            this.mobileRewardThreadEnable = false;
            boolean terminated = false;
            long waitStart = Now.NowUtcMs();
            while ( !terminated && ((Now.NowUtcMs() - waitStart) < 600_000 )) {
                terminated = true;
                for (int t = 0; t < etlConfig.getMobileRewardLoadParallelWorkers(); t++) {
                    if (threads[t].getState() != Thread.State.TERMINATED) terminated = false;
                }
                try { Thread.sleep(500); } catch (InterruptedException x ) {};
            }
            if ( !terminated ) {
                log.error("Cancelling Mobile Reward Thread before ending dequeuing");
            } else {
                // flush pending rewards store
                hotspotCache.bulkInsertMobileRewards();
            }
            synchronized (locker) {
                runningJobs--;
                this.runningJobsName.put("MobileRew", this.runningJobsName.get("MobileRew")-1);
            }
            log.info("Mobile Rewards - exit completed - objects seen "+totalObject);
        }
    }


}
