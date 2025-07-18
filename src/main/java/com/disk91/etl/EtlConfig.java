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
    // MISC
    // ====================================
    @Value("${run.mode:UNKNONWN}")
    private String runMode;
    public String getRunMode() {
        return runMode;
    }

    @Value("${developer.mode.justload:false}")
    private boolean developerModeJustLoad;
    public boolean isDeveloperModeJustLoad() {
        return developerModeJustLoad;
    }

    @Value("${developer.mode.max.keys:250}")
    private int developerModeMaxKeys;
    public int getDeveloperModeMaxKeys() {
        return developerModeMaxKeys;
    }

    // ====================================
    // Helium legacy config
    // ====================================

    @Value("${helium.legacy.api.endpoint:}")
    private String heliumLegacyApiEndpoint;

    @Value("${helium.legacy.api.user:}")
    private String heliumLegacyApiUser;

    @Value("${helium.legacy.api.pass:}")
    private String heliumLegacyApiPass;

    @Value("${helium.legacy.api.credits:100}")
    private int heliumLegacyApiCredits;

    @Value("${helium.legacy.api.enable:false}")
    private boolean heliumLegacyApiEnable;

    public boolean isHeliumLegacyApiEnable() {
        return heliumLegacyApiEnable;
    }

    public String getHeliumLegacyApiEndpoint() {
        return heliumLegacyApiEndpoint;
    }

    public String getHeliumLegacyApiUser() {
        return heliumLegacyApiUser;
    }

    public String getHeliumLegacyApiPass() {
        return heliumLegacyApiPass;
    }

    public int getHeliumLegacyApiCredits() {
        return heliumLegacyApiCredits;
    }

    // ====================================
    // Index creation
    // ====================================

    @Value("${helium.hotspot.indexing.enable:false}")
    private boolean heliumHotspotIndexingEnable;

    public boolean isHeliumHotspotIndexingEnable() {
        return heliumHotspotIndexingEnable;
    }

    @Value("${etl.mongodb.sharding.enable:true}")
    private boolean etlMongodbShardingEnable;

    public boolean isEtlMongodbShardingEnable() {
        return etlMongodbShardingEnable;
    }

    // ====================================
    // Solana API key
    // ====================================

    @Value("${solana.api.url}")
    private String solanaApiUrl;

    @Value("${solana.api.apikey:}")
    private String solanaApiKey;

    @Value("${solana.api.credit.per.minute:10}")
    private int solanaApiCreditPerMinute;

    public String getSolanaApiUrl() {
        return solanaApiUrl;
    }

    public String getSolanaApiKey() {
        return solanaApiKey;
    }

    public int getSolanaApiCreditPerMinute() {
        return solanaApiCreditPerMinute;
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

    @Value("${reward.history.startdate}")
    private long rewardHistoryStartDate;

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

    @Value("${iotpoc.load.unselected}")
    private boolean iotpocLoadUnselected;

    @Value("${reward.load.enable}")
    private boolean rewardLoadEnable;

    @Value("${reward.load.parallel.workers}")
    private int rewardLoadParallelWorkers;

    @Value("${reward.load.parallel.queue.size}")
    private int rewardLoadParallelQueueSize;

    @Value("${mobile.reward.load.enable:false}")
    private boolean mobileRewardLoadEnable;

    @Value("${mobile.reward.load.parallel.workers:8}")
    private int mobileRewardLoadParallelWorkers;

    @Value("${mobile.reward.load.parallel.queue.size:32}")
    private int mobileRewardLoadParallelQueueSize;

    @Value("${exit.timeout.minutes}")
    private int exitTimeOutMinutes;


    public int getExitTimeOutMinutes() {
        return exitTimeOutMinutes;
    }

    public long getRewardHistoryStartDate() {
        return rewardHistoryStartDate;
    }

    public boolean isRewardLoadEnable() {
        return rewardLoadEnable;
    }

    public int getRewardLoadParallelWorkers() {
        return rewardLoadParallelWorkers;
    }

    public int getRewardLoadParallelQueueSize() {
        return rewardLoadParallelQueueSize;
    }

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

    public boolean isIotpocLoadUnselected() {
        return iotpocLoadUnselected;
    }

    public boolean isMobileRewardLoadEnable() {
        return mobileRewardLoadEnable;
    }

    public int getMobileRewardLoadParallelWorkers() {
        return mobileRewardLoadParallelWorkers;
    }

    public int getMobileRewardLoadParallelQueueSize() {
        return mobileRewardLoadParallelQueueSize;
    }
}
