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



    // ---
    // Prometeus
    public PrometeusService(MeterRegistry registry) {

        Gauge.builder("etl.aws.failure", getAwsFailure())
                .description("Counter of AWS access failure")
                .register(registry);

    }



}
