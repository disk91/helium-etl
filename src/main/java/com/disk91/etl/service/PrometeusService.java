package com.disk91.etl.service;

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
    protected Supplier<Number> getAwsFailure() {
        return ()->awsFailureCounter;
    }

    private long beaconProccessed = 0;
    synchronized public void addBeaconProcessed() {
        this.beaconProccessed++;
    }
    protected Supplier<Number> getBeaconProcesses() {
        return ()->beaconProccessed;
    }

    private long beaconProcessingTime = 0;
    synchronized public void addBeaconProcessedTime(long ms) {
        this.beaconProcessingTime+=ms;
    }
    protected Supplier<Number> getBeaconProcesseTimes() {
        return ()->beaconProcessingTime;
    }

    private long witnessProccessed = 0;
    synchronized public void addWitnessProcessed() {
        this.witnessProccessed++;
    }
    protected Supplier<Number> getWitnessProcesses() {
        return ()->witnessProccessed;
    }

    private long witnessProcessingTime = 0;
    synchronized public void addWitnessProcessedTime(long ms) {
        this.beaconProcessingTime+=ms;
    }
    protected Supplier<Number> getWitnessProcesseTimes() {
        return ()->beaconProcessingTime;
    }

    private long fileProccessed = 0;
    synchronized public void addFileProcessed() {
        this.fileProccessed++;
    }
    protected Supplier<Number> getFileProcesses() {
        return ()->fileProccessed;
    }

    private long fileProcessingTime = 0;
    synchronized public void addFileProcessedTime(long ms) {
        this.fileProcessingTime+=ms;
    }
    protected Supplier<Number> getFileProcesseTimes() {
        return ()->fileProcessingTime;
    }



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

        Gauge.builder("etl.witness.processed", getWitnessProcesses())
                .description("Counter number of witnesses processed")
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

    }



}
