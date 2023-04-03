package com.disk91.etl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("application.properties")
@PropertySource(value = {"file:${config.file}"}, ignoreResourceNotFound = true)
public class EtlConfig {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    // =====================================
    // AWS
    // =====================================
    @Value("${aws.access.key}")
    private String awsAccessKey;

    @Value("${aws.secret.key}")
    private String awsSecretKey;

    @Value("${aws.bucket.name}")
    private String awsBucketName;

    public String getAwsAccessKey() {
        return awsAccessKey;
    }

    public String getAwsSecretKey() {
        return awsSecretKey;
    }

    public String getAwsBucketName() {
        return awsBucketName;
    }

    // ====================================
    // Internal settings
    // ====================================

    @Value("${hotspot.beacon.history.entries}")
    private int hotspotBeaconHistoryEntries;

    @Value("${hotspot.witness.history.entries}")
    private int hotspotWitnessHistoryEntries;

    @Value("${cache.hotspot.size}")
    private int cacheHotspotSize;

    @Value("${cache.beacon.size}")
    private int cacheBeaconSize;

    @Value("${cache.hotspot.commit}")
    private int cacheHotspotCommit;

    // Do not calculate hotspot update until x days before now, this is to optimize data loading
    @Value("${hotspot.update.prevent.until.days}")
    private int hotspotUpdatePreventUntilDays;

    @Value("${witness.load.parallel.workers}")
    private int witnessLoadParallelWorkers;

    @Value("${witness.load.parallel.queue.size}")
    private int witnessLoadParallelQueueSize;

    @Value("${beacon.history.startdate}")
    private long beaconHistoryStartDate;

    @Value("${witness.history.startdate}")
    private long witnessHistoryStartDate;

    @Value("${iotpoc.history.startdate}")
    private long iotpocHistoryStartDate;

    @Value("${beacon.load.enable}")
    private boolean beaconLoadEnable;

    @Value("${witness.load.enable}")
    private boolean witnessLoadEnable;

    @Value("${iotpoc.load.enable}")
    private boolean iotpocLoadEnable;

    @Value("${iotpoc.load.parallel.workers}")
    private int iotpocLoadParallelWorkers;

    @Value("${iotpoc.load.parallel.queue.size}")
    private int iotpocLoadParallelQueueSize;


    public boolean isBeaconLoadEnable() {
        return beaconLoadEnable;
    }

    public long getIotpocHistoryStartDate() {
        return iotpocHistoryStartDate;
    }

    public boolean isWitnessLoadEnable() {
        return witnessLoadEnable;
    }

    public boolean isIotpocLoadEnable() {
        return iotpocLoadEnable;
    }

    public int getIotpocLoadParallelWorkers() {
        return iotpocLoadParallelWorkers;
    }

    public int getIotpocLoadParallelQueueSize() {
        return iotpocLoadParallelQueueSize;
    }

    public long getBeaconHistoryStartDate() {
        return beaconHistoryStartDate;
    }

    public long getWitnessHistoryStartDate() {
        return witnessHistoryStartDate;
    }

    public int getWitnessLoadParallelWorkers() {
        return witnessLoadParallelWorkers;
    }

    public int getWitnessLoadParallelQueueSize() {
        return witnessLoadParallelQueueSize;
    }

    public int getHotspotUpdatePreventUntilDays() {
        return hotspotUpdatePreventUntilDays;
    }

    public int getHotspotBeaconHistoryEntries() {
        return hotspotBeaconHistoryEntries;
    }

    public int getHotspotWitnessHistoryEntries() {
        return hotspotWitnessHistoryEntries;
    }

    public int getCacheHotspotSize() {
        return cacheHotspotSize;
    }

    public int getCacheBeaconSize() {
        return cacheBeaconSize;
    }

    public int getCacheHotspotCommit() {
        return cacheHotspotCommit;
    }
}
