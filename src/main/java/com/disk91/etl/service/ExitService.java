package com.disk91.etl.service;

import com.disk91.etl.EtlApplication;
import com.disk91.etl.EtlConfig;
import fr.ingeniousthings.tools.Now;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.Lifecycle;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;

@Service
public class ExitService implements Lifecycle {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private boolean exiting = false;

    private boolean inPause = false;

    @Autowired
    protected EtlConfig etlConfig;

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

    public boolean isInPause() { return (this.inPause); }
    public boolean requestPause = false;

    /**
     * Request to terminate the current operation, like an exit
     * but not quit, it will be resumed after. This operation is
     * required to have a coherent backup
     */
    public synchronized void onCallPause() {
        if (this.inPause || this.requestPause ) return;
        requestPause = true;
        log.info("### Pending pause request");
    }

    /**
     * better as async function but don't want to create a class for this
     */
    @Scheduled( fixedDelay = 10_000, initialDelay = 30_000)
    private void asyncPause() {
        if ( requestPause ) {
            log.info("### Start pause request");
            this.stopAwsService();
            hotspotCache.pauseService();
            Now.sleep(5_000);
            log.info("### Etl in pause");
            this.inPause = true;
            this.requestPause = false;
        }
    }


    public synchronized void onCallResume() {
        if ( !this.inPause ) return;
        log.info("### Start resume request");
        hotspotCache.resumeService();
        awsService.restartService();
        this.inPause = false;
        log.info("### Etl resumed");
    }

    public void onCallExit() {
        if (this.exiting) return;
        this.exiting = true;
        log.error("------------------------");
        log.error("Exiting application");

        log.warn("Exit - stopping services");
        this.stopAwsService();

        log.warn("Exit - flush cache");
        hotspotCache.stopService();
        beaconROCache.stopService();

        log.warn("Exit - completed");
        this.exiting = true;
        try {
            // not ideal but let some time to finish everythings
            Thread.sleep(1_000);
        } catch (InterruptedException e) {};
        EtlApplication.exit();

    }

    private void stopAwsService() {
        awsService.stopService();

        int services = 0;
        long s = Now.NowUtcMs();
        long d = s;
        do {
            services = 0;
            if ( ! awsService.hasStopped() ) services++;

            if ( (Now.NowUtcMs() - d) > 10_000 ) {
                log.error("Exiting/Pausing ... Waiting for "+services+" services to stop for "+(Now.NowUtcMs()-s)/60_000+"m");
                d+=10_000;
            }
            if ( (Now.NowUtcMs() - s) > etlConfig.getExitTimeOutMinutes()*60_000 ) {
                log.error("Services not stopping, force stop");
                break;
            }
        } while (services > 0);

    }


}
