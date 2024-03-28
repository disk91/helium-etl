package com.disk91.etl.service;

import fr.ingeniousthings.tools.Now;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class PrometeusService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    // -----
    // AWS
    private long awsFailureCounter = 0;

    synchronized public void addAwsFailure() {
        this.awsFailureCounter++;
    }
    private Supplier<Number> getAwsFailure() {
        return ()->awsFailureCounter;
    }

    private long beaconProccessed = 0;
    synchronized public void addBeaconProcessed() {
        this.beaconProccessed++;
    }
    private Supplier<Number> getBeaconProcesses() {
        return ()->beaconProccessed;
    }

    private long beaconProcessingTime = 0;
    synchronized public void addBeaconProcessedTime(long ms) {
        this.beaconProcessingTime+=ms;
    }
    private Supplier<Number> getBeaconProcesseTimes() {
        return ()->beaconProcessingTime;
    }


    private long iotpocProccessed = 0;
    synchronized public void addIoTPocProcessed() {
        this.iotpocProccessed++;
    }
    private Supplier<Number> getIoTPocProcesses() {
        return ()->iotpocProccessed;
    }

    private long iotpocProcessingTime = 0;
    synchronized public void addIoTPocProcessedTime(long ms) {
        this.iotpocProcessingTime+=ms;
    }
    private Supplier<Number> getIoTPocProcesseTimes() {
        return ()->iotpocProcessingTime;
    }

    private long rewardProccessed = 0;
    synchronized public void addRewardProcessed() {
        this.rewardProccessed++;
    }
    private Supplier<Number> getRewardProcesses() {
        return ()->rewardProccessed;
    }

    private long rewardProcessingTime = 0;
    synchronized public void addRewardProcessedTime(long ms) {
        this.rewardProcessingTime+=ms;
    }
    private Supplier<Number> getRewardProcesseTimes() {
        return ()->rewardProcessingTime;
    }


    private long witnessProccessed = 0;
    synchronized public void addWitnessProcessed() {
        this.witnessProccessed++;
    }
    private Supplier<Number> getWitnessProcesses() {
        return ()->witnessProccessed;
    }

    private long witnessValidProcessed = 0;
    synchronized public void addValidWitnessProcessed() {
        this.witnessValidProcessed++;
    }
    private Supplier<Number> getValidWitnessProcesses() {
        return ()->witnessValidProcessed;
    }


    private long witnessProcessingTime = 0;
    synchronized public void addWitnessProcessedTime(long ms) {
        this.witnessProcessingTime+=ms;
    }
    private Supplier<Number> getWitnessProcesseTimes() {
        return ()->witnessProcessingTime;
    }

    private long fileProccessed = 0;
    synchronized public void addFileProcessed() {
        this.fileProccessed++;
    }
    private Supplier<Number> getFileProcesses() {
        return ()->fileProccessed;
    }

    private long fileProcessingTime = 0;
    synchronized public void addFileProcessedTime(long ms) {
        this.fileProcessingTime+=ms;
    }
    private Supplier<Number> getFileProcesseTimes() {
        return ()->fileProcessingTime;
    }

    private long lastFileBeaconTimestamp = 0;
    private long lastFileBeaconDistance = 0;
    synchronized public void changeFileBeaconTimestamp(long ms) {
        this.lastFileBeaconDistance= Now.NowUtcMs()-ms;
        this.lastFileBeaconTimestamp=ms;
    }
    private Supplier<Number> getLastFileBeaconTimestamp() {
        return ()->lastFileBeaconTimestamp;
    }
    private Supplier<Number> getLastFileBeaconDistance() {
        return ()->lastFileBeaconDistance;
    }

    private long lastFileWitnessTimestamp = 0;
    private long lastFileWitnessDistance = 0;
    synchronized public void changeFileWitnessTimestamp(long ms) {
        this.lastFileWitnessDistance= Now.NowUtcMs()-ms;
        this.lastFileWitnessTimestamp=ms;
    }
    private Supplier<Number> getLastFileWitnessTimestamp() {
        return ()->lastFileWitnessTimestamp;
    }
    private Supplier<Number> getLastFileWitnessDistance() {
        return ()->lastFileWitnessDistance;
    }

    private long lastFileIoTPocTimestamp = 0;
    private long lastFileIoTPocDistance = 0;
    synchronized public void changeFileIoTPocTimestamp(long ms) {
        this.lastFileIoTPocDistance= Now.NowUtcMs()-ms;
        this.lastFileIoTPocTimestamp=ms;
    }
    private Supplier<Number> getLastFileIoTPocTimestamp() {
        return ()->lastFileIoTPocTimestamp;
    }
    private Supplier<Number> getLastFileIoTPocDistance() {
        return ()->lastFileIoTPocDistance;
    }

    private long lastFileRewardTimestamp = 0;
    private long lastFileRewardDistance = 0;
    synchronized public void changeFileRewardTimestamp(long ms) {
        this.lastFileRewardDistance=Now.NowUtcMs()-ms;
        this.lastFileRewardTimestamp=ms;
    }
    private Supplier<Number> getLastFileRewardTimestamp() {
        return ()->lastFileRewardTimestamp;
    }
    private Supplier<Number> getLastFileRewardDistance() {
        return ()->lastFileRewardDistance;
    }


    private long lastFileMobileRewardTimestamp = 0;
    private long lastFileMobileRewardDistance = 0;
    synchronized public void changeFileMobileRewardTimestamp(long ms) {
        this.lastFileMobileRewardDistance=Now.NowUtcMs()-ms;
        this.lastFileMobileRewardTimestamp=ms;
    }
    private Supplier<Number> getLastFileMobileRewardTimestamp() {
        return ()->lastFileMobileRewardTimestamp;
    }
    private Supplier<Number> getLastFileMobileRewardDistance() {
        return ()->lastFileMobileRewardDistance;
    }


    private long pendingHotspotModifications = 0;
    synchronized public void changeHsModification(long modif) {
        this.pendingHotspotModifications=modif;
    }
    private Supplier<Number> getChangeHsModification() {
        return ()->pendingHotspotModifications;
    }


    // time to process 1 hour of data
    private long hourlyIotRate = 0;
    public void setHourlyIoTRate(long newValue) {
        this.hourlyIotRate = newValue;
    }
    private Supplier<Number> getHourlyIotRate() { return ()->hourlyIotRate;}


    // ---
    // Prometeus
    public PrometeusService(MeterRegistry registry) {

        Gauge.builder("etl.aws.failure", getAwsFailure())
                .description("Counter of AWS access failure")
                .register(registry);

        Gauge.builder("etl.beacon.processed", getBeaconProcesses())
                .description("Counter number of beacon processed")
                .register(registry);

        Gauge.builder("etl.beacon.processed.time", getBeaconProcesseTimes())
                .description("Processing time for beacons")
                .register(registry);

        Gauge.builder("etl.iotpoc.processed", getIoTPocProcesses())
                .description("Counter number of iot poc processed")
                .register(registry);

        Gauge.builder("etl.iotpoc.processed.time", getIoTPocProcesseTimes())
                .description("Processing time for iot poc")
                .register(registry);

        Gauge.builder("etl.iotpoc.hour.rate", getHourlyIotRate())
                .description("Server time to process 1 hour of IoT Poc Data")
                .register(registry);

        Gauge.builder("etl.reward.processed", getRewardProcesses())
                .description("Counter number of reward processed")
                .register(registry);

        Gauge.builder("etl.reward.processed.time", getRewardProcesseTimes())
                .description("Processing time for reward")
                .register(registry);

        Gauge.builder("etl.witness.processed", getWitnessProcesses())
                .description("Counter number of witnesses processed")
                .register(registry);

        Gauge.builder("etl.witness.valid", getValidWitnessProcesses())
                .description("Counter number of valid witnesses processed")
                .register(registry);

        Gauge.builder("etl.witness.processed.time", getWitnessProcesseTimes())
                .description("Processing time for witnesses")
                .register(registry);

        Gauge.builder("etl.file.processed", getFileProcesses())
                .description("Counter number of files processed")
                .register(registry);

        Gauge.builder("etl.file.processed.time", getFileProcesseTimes())
                .description("Processing time for files")
                .register(registry);

        Gauge.builder("etl.file.beacon.time", getLastFileBeaconTimestamp())
                .description("Timestamp for the last processed file")
                .register(registry);

        Gauge.builder("etl.file.beacon.distance", getLastFileBeaconDistance())
                .description("Distance from now for the last processed file")
                .register(registry);

        Gauge.builder("etl.file.witness.time", getLastFileWitnessTimestamp())
                .description("Timestamp for the last processed file")
                .register(registry);

        Gauge.builder("etl.file.witness.distance", getLastFileWitnessDistance())
                .description("Distance from now for the last processed file")
                .register(registry);

        Gauge.builder("etl.file.iotpoc.time", getLastFileIoTPocTimestamp())
                .description("Timestamp for the last processed file")
                .register(registry);

        Gauge.builder("etl.file.iotpoc.distance", getLastFileIoTPocDistance())
                .description("Distance from now for the last processed file")
                .register(registry);

        Gauge.builder("etl.file.reward.time", getLastFileRewardTimestamp())
                .description("Timestamp for the last processed file")
                .register(registry);

        Gauge.builder("etl.file.reward.distance", getLastFileRewardDistance())
                .description("Distance from now for the last processed file")
                .register(registry);

        Gauge.builder("etl.file.mobile.reward.time", getLastFileRewardTimestamp())
            .description("Timestamp for the last processed file")
            .register(registry);

        Gauge.builder("etl.file.mobile.reward.distance", getLastFileRewardDistance())
            .description("Distance from now for the last processed file")
            .register(registry);

        Gauge.builder("etl.cache.hotspot.pending", getChangeHsModification())
                .description("Pending modification into the hotspots cache")
                .register(registry);

    }



}
