package com.disk91.etl.service;

import com.disk91.etl.EtlConfig;
import com.disk91.etl.data.object.Beacon;
import com.disk91.etl.data.object.Hotspot;
import com.disk91.etl.data.object.Param;
import com.disk91.etl.data.object.sub.BeaconHistory;
import com.disk91.etl.data.object.sub.Witness;
import com.disk91.etl.data.repository.BeaconsRepository;
import com.disk91.etl.data.repository.HotspotsRepository;
import fr.ingeniousthings.tools.HeliumHelper;
import fr.ingeniousthings.tools.HexaConverters;
import fr.ingeniousthings.tools.Now;
import fr.ingeniousthings.tools.ObjectCache;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import xyz.nova.grpc.lora_beacon_ingest_report_v1;
import xyz.nova.grpc.lora_witness_ingest_report_v1;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class BeaconROCache {

    // @TODO - we could have multiple beacon in cache with the same data
    //         so we should add a timestamp in the 5 minute round before
    //         to the key to avoid this risk of conflict

    @Autowired
    protected EtlConfig etlConfig;

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private MeterRegistry registry;
    public BeaconROCache(MeterRegistry registry) {
        this.registry = registry;
    }

    protected int runningJobs;
    protected boolean serviceEnable; // false to stop the services

    private ObjectCache<String, Beacon> beaconCache;

    @PostConstruct
    private void initBeaconCacheService() {

        initCache();

        Gauge.builder("etl.beacon.cache_total_time", this.beaconCache.getTotalCacheTime())
                .description("total time beacon cache execution")
                .register(registry);
        Gauge.builder("etl.beacon.cache_total", this.beaconCache.getTotalCacheTry())
                .description("total beacon cache try")
                .register(registry);
        Gauge.builder("etl.beacon.cache_miss", this.beaconCache.getCacheMissStat())
                .description("total beacon cache miss")
                .register(registry);

        this.serviceEnable = true;
    }

    private void initCache() {
        this.beaconCache = new ObjectCache<String, Beacon>(
                "BeaconCache",
                etlConfig.getCacheBeaconSize(),
                Now.ONE_HOUR
        ) {
            @Override
            public void onCacheRemoval(String key, Beacon obj) {

            }

            @Override
            public void bulkCacheUpdate(List<Beacon> objects) {

            }

        };

    }

    @Scheduled(fixedRateString = "${logging.cache.fixedrate}", initialDelay = 63_000)
    protected void cacheStatus() {
        if ( ! this.serviceEnable ) return;
        this.runningJobs++;
        try {
            this.beaconCache.log();
        } finally {
            this.runningJobs--;
        }
    }

    public void stopService() {
        this.serviceEnable = false;
    }
    public boolean hasStopped() {
        return (this.serviceEnable == false && this.runningJobs == 0);
    }

    @Autowired
    protected BeaconsRepository beaconsRepository;

    public final long ACCEPTED_TIME_DISTANCE = 10_000_000_000L;

    public Beacon getBeacon(String dataId, long closeTimestamp) {
        synchronized (beaconCache) {
            if (beaconCache.isTooLong()) {
                log.warn("BeaconROCache is too slow, destroy and recreate it");
                // wait a bit we need to make sure no other process is in the next part
                try { Thread.sleep(50); } catch (InterruptedException x) {}
                // after a while this cache is becoming really slow,
                // possibly related to swap & memory issue
                // in this case better deleting cache and recreate it
                beaconCache.deleteCache();
                initCache();
            }
        }

        Beacon b = beaconCache.get(dataId);
        if ( b == null || Math.abs(b.getTimestamp() - closeTimestamp) > ACCEPTED_TIME_DISTANCE ) {
            List<Beacon> bs = beaconsRepository.findBeaconByData(dataId);
            if ( bs != null && bs.size() > 0 ) {
                long tdist=ACCEPTED_TIME_DISTANCE;
                Beacon closest = null;
                for ( Beacon _b : bs ) {
                    long d = Math.abs(_b.getTimestamp() - closeTimestamp);
                    if ( d < tdist ) {
                        tdist = d;
                        closest = _b;
                    }
                }
                if ( closest != null ) {
                    beaconCache.put(closest, closest.getData());
                    //log.info((closeTimestamp/1_000_000)+"Found beacon with "+tdist/1_000_000+"ms");
                    return closest;
                }
            }
            //log.warn((closeTimestamp/1_000_000)+"Can't find beacon " + dataId);
            return null;
        }
        //log.info((closeTimestamp/1_000_000)+"found beacon");
        return b;
    }

    public void addBeacon(Beacon b) {
        this.beaconCache.put(b,b.getData());
    }

}
