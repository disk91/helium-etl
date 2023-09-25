package com.disk91.etl.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
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
import fr.ingeniousthings.tools.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.helium.grpc.lora_beacon_ingest_report_v1;
import com.helium.grpc.lora_witness_ingest_report_v1;

import javax.annotation.PostConstruct;
import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
    public void restartService() { this.serviceEnable = true; this.firstFile = true; }
    public boolean hasStopped() {
        return (!this.serviceEnable && this.runningJobs == 0);
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
        } else if ( fileName.startsWith("gateway_reward") ) {
           // log.info("Found gateway_reward file "+fileName);
        } else if ( fileName.startsWith("solana-migration-bad-data") ) {
           // log.info("Found solana_migration bad data file "+fileName);
        } else if ( fileName.startsWith("reward_manifest") ) {
            // log.info("Found reward manifest file "+fileName);
        } else if ( fileName.startsWith("non_rewardable") ) {
            // log.info("Found non rewardable packets "+fileName);
        } else {
            log.warn("Unknown type of file discovered "+fileName);
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

        synchronized (this) {
            this.runningJobs++;
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
            synchronized (this) {
                runningJobs--;
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

        synchronized (this) {
            this.runningJobs++;
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
            synchronized (this) {
                runningJobs--;
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
            log.debug("Closing iot_poc process thread "+id);
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

        synchronized (this) {
            this.runningJobs++;
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

        try {
            final ListObjectsV2Request lor = new ListObjectsV2Request();
            lor.setBucketName(etlConfig.getAwsBucketName());
            lor.setPrefix("foundation-iot-verified-rewards");
            lor.setStartAfter(iotPocFile.getStringValue());
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
                    while ( ! readOk && retry < 5 ) {
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
                    if ( retry == 5 ) {
                        log.warn("Impossible to process file " + object.getKey()+ " skip it");
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
                        } while ( pending > 0);
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
            synchronized (this) {
                runningJobs--;
            }
            log.info("IoTPoc - exit completed");
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

        synchronized (this) {
            this.runningJobs++;
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

        try {
            final ListObjectsV2Request lor = new ListObjectsV2Request();
            lor.setBucketName(etlConfig.getAwsBucketName());
            lor.setPrefix("foundation-iot-verified-rewards");
            lor.setStartAfter(rewardPocFile.getStringValue());
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
                    //  iot_poc => valid poc
                    if ( ! object.getKey().contains(".gz") ) continue; // not a file
                    String fileName = object.getKey().split("/")[1];
                    int fileType = getFileType(fileName);
                    long fileDate = Long.parseLong(object.getKey().split("\\.")[1]);
                    if ( fileType != 4 ) continue;
                    if ( fileDate/1000 < etlConfig.getRewardHistoryStartDate() ) {
                        rewardPocFile.setStringValue(object.getKey());
                        paramRepository.save(rewardPocFile);
                        continue;
                    }

                    log.debug("Processing type "+fileType+": "+fileName+"("+(Now.NowUtcMs() - fileDate )/(Now.ONE_FULL_DAY)+") days");

                    boolean readOk = false;
                    retry = 0;
                    ArrayList<iot_reward_share> toProcess = new ArrayList<>();
                    while ( ! readOk && retry < 5 ) {

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
                    if (retry == 5) {
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
                                while (queues[q].size() >= etlConfig.getRewardLoadParallelWorkers()) Thread.sleep(2);
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
                log.error("Cancelling Thread before ending enqueing");
            }
            synchronized (this) {
                runningJobs--;
            }
            log.info("Rewards - exit completed");
        }
    }

}
