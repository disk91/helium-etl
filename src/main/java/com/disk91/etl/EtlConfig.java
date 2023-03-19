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

    public int getHotspotBeaconHistoryEntries() {
        return hotspotBeaconHistoryEntries;
    }

    public int getHotspotWitnessHistoryEntries() {
        return hotspotWitnessHistoryEntries;
    }
}
