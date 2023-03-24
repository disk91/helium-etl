package com.disk91.etl.service;

import com.disk91.etl.EtlApplication;
import fr.ingeniousthings.tools.Now;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.Lifecycle;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;

@Service
public class ExitService implements Lifecycle {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private boolean exiting = false;

    @Autowired
    protected HotspotCache hotspotCache;

    @Autowired
    protected AwsService awsService;

    @Autowired
    protected BeaconROCache beaconROCache;

    @Override
    public void start() {

    }

    @Override
    public void stop() {
        this.onCallExit();
    }

    @Override
    public boolean isRunning() {
        return (!this.exiting);
    }


    public void onCallExit() {
        if (this.exiting) return;
        this.exiting = true;
        log.error("------------------------");
        log.error("Exiting application");

        log.warn("Exit - stopping services");
        awsService.stopService();

        int services = 0;
        long s = Now.NowUtcMs();
        long d = s;
        do {
            services = 0;
            if ( ! awsService.hasStopped() ) services++;

            if ( (Now.NowUtcMs() - d) > 1000 ) {
                log.error("Exiting ... Waiting for "+services+" services to stop");
                d+=1000;
            }
            if ( (Now.NowUtcMs() - s) > 600_000 ) {
                log.error("Services not stopping, force stop");
                break;
            }
        } while (services > 0);

        log.warn("Exit - flush cache");
        hotspotCache.stopService();
        beaconROCache.stopService();


        log.info("Exit - completed");
        this.exiting = true;
        try {
            // not ideal but let some time to finish everythings
            Thread.sleep(1_000);
        } catch (InterruptedException e) {};
        EtlApplication.exit();

    }

}
