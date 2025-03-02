package com.disk91.etl.service;

import com.disk91.etl.EtlConfig;
import com.disk91.etl.api.interfaces.HotspotIdent;
import com.disk91.etl.data.itf.WalletHotspotList;
import com.disk91.etl.data.object.*;
import com.disk91.etl.data.object.sub.Owner;
import com.disk91.etl.data.repository.*;
import com.disk91.etl.helium.legacy.model.MakerElement;
import com.disk91.etl.helium.legacy.model.hotspot.HotspotDetail;
import com.helium.grpc.*;
import com.mongodb.MongoBulkWriteException;
import com.mongodb.WriteConcern;
import com.mongodb.bulk.BulkWriteResult;
import com.uber.h3core.H3Core;
import com.uber.h3core.util.LatLng;
import fr.ingeniousthings.tools.*;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.security.SecureRandom;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class HotspotCache {

    public final static int  WIT_ERROR_NONE = 0;
    public final static int  WIT_ERROR_BAD_SIGNATURE = 1;
    public final static int  WIT_ERROR_NOT_ASSERTED = 2;
    public final static int  WIT_ERROR_ENTROPY_EXPIRED = 3;
    public final static int  WIT_ERROR_BAD_ENTROPY = 4;
    public final static int  WIT_ERROR_INVALID_CAPABILITY = 5;
    public final static int  WIT_ERROR_INVALID_PUBKEY = 6;
    public final static int  WIT_ERROR_IRREGULAR_INTERVAL = 7;
    public final static int  WIT_ERROR_GATEWAY_NOT_FOUND = 8;
    public final static int  WIT_ERROR_DENIED = 9;
    public final static int  WIT_ERROR_INVALID_PACKET = 10;
    public final static int  WIT_ERROR_BAD_RSSI = 11;
    public final static int  WIT_ERROR_INVALID_REGION = 12;
    public final static int  WIT_ERROR_MAX_DISTANCE_EXCEEDED = 13;
    public final static int  WIT_ERROR_INVALID_FREQUENCY = 14;
    public final static int  WIT_ERROR_SELF_WITNESS = 15;
    public final static int  WIT_ERROR_STALE = 16;
    public final static int  WIT_ERROR_SCALING_FACTOR_NOT_FOUND = 17;
    public final static int  WIT_ERROR_UNKNOWN = 18;
    public final static int  WIT_ERROR_BELOW_MIN_DISTANCE = 19;
    public final static int  WIT_ERROR_DUPLICATE = 20;
    public final static int  WIT_ERROR_DENIED_EDGE = 21;
    public final static int  WIT_ERROR_TOO_LATE = 22;
    public final static int  WIT_ERROR_NO_VALID_BEACON = 23;
    public final static int  WIT_ERROR_NO_VALID_WITNESS = 24;


    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private MeterRegistry registry;
    public HotspotCache(MeterRegistry registry) {
        this.registry = registry;
    }

    protected int runningJobs;
    protected boolean serviceEnable; // false to stop the services
    protected boolean stopRequested = false; // true when a stop is requested
    protected boolean pauseIndexing = false;


    private ObjectCache<String, Hotspot> heliumHotspotCache;

    @Autowired
    protected ParamRepository paramRepository;

    protected Param beaconTopLine = null;
    protected long beaconTopTs = 0;
    protected Param witnessTopLine = null;
    protected long witnessTopTs = 0;

    protected Param iotpocTopLine = null;
    protected long iotpocTopTs = 0;

    protected volatile Boolean inAsyncIoTWrite = Boolean.valueOf(false);
    private final Object asyncIoTWrite = new Object();
    protected volatile Boolean inAsyncMobileWrite = Boolean.valueOf(false);
    private final Object asyncMobileWrite = new Object();

    @Autowired
    protected HotspotCacheAsync hotspotCacheAsync;

    protected  H3Core h3;

    @PostConstruct
    private void initHotspotCacheService() {
        this.heliumHotspotCache = new ObjectCache<String, Hotspot>(
                "HotspotCache",
                etlConfig.getCacheHotspotSize(),
                72*Now.ONE_HOUR
        ) {

            private ArrayList<Hotspot> _toSave = new ArrayList<>();
            @Override
            public void onCacheRemoval(String key, Hotspot obj, boolean batch, boolean last) {
                if ( batch ) {
                    if ( obj != null ) _toSave.add(obj);
                    if ( _toSave.size() > 5000 || last ) {
                        _toSave.parallelStream().forEach(hotspotsRepository::save);
                        //hotspotsRepository.saveAll(_toSave);
                        _toSave.clear();
                    }
                } else {
                    hotspotsRepository.save(obj);
                }
            }

            @Override
            public void bulkCacheUpdate(List<Hotspot> objects) {
                hotspotCacheAsync.processAsyncCacheCommit(objects);
            }

        };

        Gauge.builder("etl.hotspot.cache_total_time", this.heliumHotspotCache.getTotalCacheTime())
                .description("total time device cache execution")
                .register(registry);
        Gauge.builder("etl.hotspot.cache_total", this.heliumHotspotCache.getTotalCacheTry())
                .description("total device cache try")
                .register(registry);
        Gauge.builder("etl.hotspot.cache_miss", this.heliumHotspotCache.getCacheMissStat())
                .description("total device cache miss")
                .register(registry);

        beaconTopLine = paramRepository.findOneParamByParamName("beacon_top_line");
        if ( beaconTopLine == null ) {
            beaconTopLine = new Param();
            beaconTopLine.setParamName("beacon_top_line");
            beaconTopLine.setLongValue(0);
            paramRepository.save(beaconTopLine);
        }
        witnessTopLine = paramRepository.findOneParamByParamName("witness_top_line");
        if ( witnessTopLine == null ) {
            witnessTopLine = new Param();
            witnessTopLine.setParamName("witness_top_line");
            witnessTopLine.setLongValue(0);
            paramRepository.save(witnessTopLine);
        }
        iotpocTopLine = paramRepository.findOneParamByParamName("iotpoc_top_line");
        if ( iotpocTopLine == null ) {
            iotpocTopLine = new Param();
            iotpocTopLine.setParamName("iotpoc_top_line");
            iotpocTopLine.setLongValue(0);
            paramRepository.save(iotpocTopLine);
        }

        // Preload Hotspots in cache
        /* --- really too slow ... like reading 1000 / minute
        log.info("Init Hotspot Cache");
        long current = Now.NowUtcMs();
        long cnt = 0;
        long max_hs = hotspotsRepository.count();
        Slice<Hotspot> allHotspot = hotspotsRepository.findAll(PageRequest.of(0, 1_000));
        if ( allHotspot != null && allHotspot.hasContent() ) {
            do {
                for ( Hotspot h : allHotspot.getContent() ) {
                    this.heliumHotspotCache.put(h,h.getHotspotId());
                    cnt++;
                    if( (Now.NowUtcMs() - current) > 30_000 ) {
                        log.info("Hostpot Cache init "+cnt+" elements - progress about "+(100*cnt)/max_hs+"%");
                        current = Now.NowUtcMs();
                    }
                }
                allHotspot = hotspotsRepository.findAll(allHotspot.nextPageable());
            } while (allHotspot.hasNext());
        }
        */
        this.serviceEnable = true;

        try {
            h3 = H3Core.newInstance();
        } catch (IOException x) {
            log.error("## Impossible de initialize h3 library");
            h3 = null;
        }

    }

    @Scheduled(fixedRateString = "${logging.cache.fixedrate}", initialDelay = 66_000)
    protected void cacheStatus() {
        if ( ! this.serviceEnable ) return;
        this.runningJobs++;
        try {
            this.heliumHotspotCache.log();
        } finally {
            this.runningJobs--;
        }
    }

    private boolean pauseCanceler=false;
    // to prevent never ending pausing
    public void forceResume() {
        pauseCanceler = true;
    }

    public void pauseService() {
        // flush pending write
        log.info("Flush Beacon & Witness pending");
        long s = Now.NowUtcMs();
        this.pauseIndexing = true;
        this.pauseCanceler = false;
        while ( (inAsyncIoTWrite || inAsyncMobileWrite)  && (Now.NowUtcMs() - s) < 120_000 ){
            Now.sleep(100);
        }
        bulkInsertRewards();
        bulkInsertMobileRewards();
        flushInsertWitness();
        flushInsertBeacon();

        while (hotspotCacheAsync.isRunning() ) {
            Now.sleep(100);
        }

        // commit all other modifications
        log.info("Commit hotspot cache");
        long updated;
        do {
            log.info("Currently unsaved objects: "+heliumHotspotCache.getUnsaved());
            updated = heliumHotspotCache.commit(true,20_000);
            if ( updated > 0 ) {
                // wait for async process to start
                long st = Now.NowUtcMs();
                while (!hotspotCacheAsync.isRunning() && ((Now.NowUtcMs() - st) < 10_000)) ;
                // wait for end of async process
                st = Now.NowUtcMs();
                while (hotspotCacheAsync.isRunning() && ((Now.NowUtcMs() - st) < Now.ONE_HOUR)) {
                    Now.sleep(1000);
                }
            }
        } while ( updated >= 20_000 && ! this.pauseCanceler);
        modifications = 0;

        log.info("Flush top lines");
        this.flushTopLines();
        this.serviceEnable = false;
    }

    public void resumeService() {
        this.pauseIndexing = false;
        this.serviceEnable = true;
    }

    public void stopService() {
        // flush pending write
        log.info("Flush Beacon & Witness pending");
        this.stopRequested = true;
        long s = Now.NowUtcMs();
        while ( (inAsyncIoTWrite || inAsyncMobileWrite)  && (Now.NowUtcMs() - s) < 120_000 ){
            Now.sleep(100);
        }
        bulkInsertRewards();
        bulkInsertMobileRewards();
        flushInsertWitness();
        flushInsertBeacon();

        while (hotspotCacheAsync.isRunning() ) {
           try { Thread.sleep(100); } catch ( InterruptedException x) {};
        };
        log.info("Flush hotspot cache");
        this.heliumHotspotCache.flush();
        log.info("Flush top lines");
        this.flushTopLines();
        this.serviceEnable = false;
    }
    public boolean hasStopped() {
        return (this.serviceEnable == false && this.runningJobs == 0);
    }

    public boolean isReady() {
        return (this.serviceEnable == true);
    }


    public void flushTopLines() {
        // remove 10m to the max as the data are not always growing
        beaconTopLine.setLongValue(this.beaconTopTs-600_000);
        witnessTopLine.setLongValue(this.witnessTopTs-600_000);
        iotpocTopLine.setLongValue(this.iotpocTopTs-600_000);

        paramRepository.save(beaconTopLine);
        paramRepository.save(witnessTopLine);
        paramRepository.save(iotpocTopLine);
    }

    @Autowired
    protected HotspotsRepository hotspotsRepository;

    public Hotspot getOneHotspotFaster(String hotspotId) throws ITNotFoundException {
        // Get a hotspot in cache only without any update and blocking dep for serving API
        Hotspot hs = heliumHotspotCache.getImmediate(hotspotId);
        if ( hs == null ) throw new ITNotFoundException();
        return hs;
    }

    // non blocking read only interface to hotspots
    public Hotspot getOneHotspot(String hotspotId, boolean allowDB) throws ITNotFoundException {
        Hotspot hs = heliumHotspotCache.get(hotspotId);
        if ( hs == null ) {
            if ( allowDB ) {
                // try db
                hs = hotspotsRepository.findOneHotspotByHotspotId(hotspotId);
            }
            if (hs == null) {
                throw new ITNotFoundException();
            }
        }
        return hs;
    }

    // non blocking interface to prevent init problem here all
    // the request are blocked time to get it from Db
    protected Hotspot getHotspot(String hotspotId, boolean cache) {
        Hotspot hs = heliumHotspotCache.get(hotspotId);
        if ( hs == null ) {
            return getHotspotSync(hotspotId, cache);
        }
        return hs;
    }

    synchronized protected Hotspot getHotspotSync(String hotspotId, boolean cache) {
        Hotspot hs = heliumHotspotCache.get(hotspotId);
        if ( hs == null ) {
            hs = hotspotsRepository.findOneHotspotByHotspotId(hotspotId);
            if ( hs == null ) {
                // create it
                hs = new Hotspot();
                hs.setHotspotId(hotspotId);
                hs.setAnimalName(Animal.getAnimalName(hotspotId,'-'));
                hs.setPosition(new com.disk91.etl.data.object.sub.LatLng());
                hs.setPosHistory(new ArrayList<>());
                hs.setOwner(new Owner());
                hs.setOwnerHistory(new ArrayList<>());
                hs.setWitnesses(new ArrayList<>());
                hs.setBeaconHistory(new ArrayList<>());
                hs.setWitnessesHistory(new ArrayList<>());
                hs.setBeaconned(new ArrayList<>());
                hs.setRewardHistories(new ArrayList<>());
                hs.setDenyHistories(new ArrayList<>());
                hs.setInDenyList(false);
                hs.setLastDataReward(0);
                hs.setLastBeacon(0);
                hs.setLastWitness(0);
                hs.setVersion(1);
                hs.setWitnessDist(0.0);
                hs.setBeaconDist(0.0);
                hs.setLastErrorCause(new ArrayList<>());
                hs = hotspotsRepository.save(hs);   // to get the Id provided, will be simple to manage later
            } else {
                // previous versions
                if ( hs.getBeaconned() == null ) {
                    hs.setBeaconned(new ArrayList<>());
                }
                if ( hs.getAnimalName() == null || hs.getAnimalName().length() < 2) {
                    hs.setAnimalName(Animal.getAnimalName(hotspotId,'-'));
                }
                if ( hs.getPosition() == null ) {
                    hs.setPosition(new com.disk91.etl.data.object.sub.LatLng());
                }
                if ( hs.getPosHistory() == null ) {
                    hs.setPosHistory(new ArrayList<>());
                }
                if ( hs.getRewardHistories() == null ) {
                    hs.setRewardHistories(new ArrayList<>());
                }
                if ( hs.getOwnerHistory() == null ) {
                    hs.setOwnerHistory(new ArrayList<>());
                }
                if ( hs.getOwner() == null ) {
                    hs.setOwner(new Owner());
                }
                if ( hs.getDenyHistories() == null ) {
                    hs.setDenyHistories(new ArrayList<>());
                    hs.setInDenyList(false);
                    hs.setLastDataReward(0);
                }
                if ( hs.getLastErrorCause() == null ) {
                    hs.setLastErrorCause(new ArrayList<>());
                }
                // init from legacy
                this.addForEnrichemnent(hs);
                this.addForIndexing(hs);
            }
            if ( cache && hs != null ) {
                heliumHotspotCache.put(hs,hotspotId,false);
            }
        } else this.addForEnrichemnent(hs);
        return hs;
    }

    /**
     * Purpose is to update the Hotspot Cache, but we want to push the modification
     * to the Database. Update takes time, so we need to do this in background
     * For this a copy of the Cache is made for having a snapshot of the data to
     * persist. As DB Update time is longer than Memory update rate, we have a risk
     * to have more object modified than what we can persist.
     * We only persist the cache.hotspot.commit number of objet to avoid memory
     * saturation and stay under control. With as a consequence a risk to never persisit
     * some o the objects (random selection of object to be persisted would be better but
     * it impacts process time, let see tha later). So on regular basis, we force a full
     * update, this one is blocking. We do this on every 2 hours.
     */
    private long modifications = 0;
    private long lastModification = Now.NowUtcMs();
    private boolean forceSyncUpdate = false;
    private long lastForceSync = Now.NowUtcMs();
    public synchronized void updateHotspot(Hotspot o) {
        heliumHotspotCache.put(o,o.getHotspotId());
        modifications++;
        prometeusService.changeHsModification(modifications);
        long now = Now.NowUtcMs();
        if ( ! hotspotCacheAsync.isRunning()
        && (    modifications > etlConfig.getCacheHotspotCommit()       // to commit the modifications
             || (now - lastModification) > 15*Now.ONE_MINUTE // make sure we commit on every 15 minutes at least
             || heliumHotspotCache.isInAsyncSync()                      // to terminate async sync in batch
           )
        ) {
            modifications = 0;
            lastModification = now;
            long updated = heliumHotspotCache.commit(true,20_000); // async commit to quit immediately, limit to 100 object every time
            // avoid to have a parallel request to start as the async process can take a few mx to start and another pending updateHotspot takes
            // the opportunity to run a concurrent request and consume memory we need
            long s = Now.NowUtcMs() ; while ( !hotspotCacheAsync.isRunning() && ((Now.NowUtcMs()-s) < 10_000) );

            if ( updated < 20_000 ) {
                // basically means that this update takes all the pending updates
                modifications = 0;
            } else {
                // or we have some pending
                modifications = updated-20_000;
            }
        }
    }



    // ---------------------------
    // Manage how we update the data into the hotspot elements

    @Autowired
    protected PrometeusService prometeusService;

    @Autowired
    private EtlConfig etlConfig;

    @Autowired
    private BeaconsRepository beaconsRepository;

    @Autowired
    private BeaconROCache beaconROCache;

    public boolean addBeacon(lora_beacon_ingest_report_v1 b) {

        // already process
        if ( b.getReceivedTimestamp() < beaconTopLine.getLongValue() ) {
            //log.info("TopLine is"+beaconTopLine.getLongValue());
            return false;
        }

        long start = Now.NowUtcMs();

        // beacon age > limit
        boolean fullUpdate = ((Now.NowUtcMs() - b.getReceivedTimestamp()) < etlConfig.getHotspotUpdatePreventUntilDays()*Now.ONE_FULL_DAY);

        String hsId = HeliumHelper.pubAddressToName(b.getReport().getPubKey());
        if (! etlConfig.isIotpocLoadEnable() && fullUpdate ) {
            Hotspot h = this.getHotspot(hsId, true);
            h.addBeacon(b.getReceivedTimestamp(),etlConfig.getHotspotBeaconHistoryEntries());

            // mark as updated
            this.updateHotspot(h);
        }

        if ( etlConfig.isBeaconLoadEnable() ) {

            // add a line in global storage
            Beacon be = new Beacon();
            be.setHotspotId(hsId);
            be.setVersion(1);
            be.setChannel(b.getReport().getChannel());
            be.setDatarate(b.getReport().getDatarate().getNumber());
            be.setFrequency(b.getReport().getFrequency());
            be.setTimestamp(b.getReport().getTimestamp()); // Nano Seconds
            be.setTmst(b.getReport().getTmst());
            be.setTx_power(b.getReport().getTxPower());
            be.setData(HexaConverters.byteToHexString(b.getReport().getData().toByteArray()));
            // Async bulk write
            delayedBeaconSave(be);
            //beaconsRepository.save(be);
            beaconROCache.addBeacon(be);
        }

        // Add in cache for witness search
        if ( b.getReceivedTimestamp() > this.beaconTopTs ) {
            this.beaconTopTs = b.getReceivedTimestamp();
        }

        prometeusService.addBeaconProcessed();
        prometeusService.addBeaconProcessedTime(Now.NowUtcMs()-start);
        return true;
    }

    @Autowired
    protected WitnessesRepository witnessesRepository;

    public boolean addWitness(lora_witness_ingest_report_v1 w) {

        // already process
        if ( w.getReceivedTimestamp() < witnessTopLine.getLongValue() ) return false;

        long start = Now.NowUtcMs();

        // beacon age > limit
        boolean fullUpdate = ((Now.NowUtcMs() - w.getReceivedTimestamp()) < etlConfig.getHotspotUpdatePreventUntilDays() * Now.ONE_FULL_DAY);

        String hsId = HeliumHelper.pubAddressToName(w.getReport().getPubKey());
        String dataId = HexaConverters.byteToHexString(w.getReport().getData().toByteArray());
        Beacon b = beaconROCache.getBeacon(dataId, w.getReport().getTimestamp());
        // control the beacon
        if (b != null
                && (w.getReport().getTimestamp() - b.getTimestamp()) > 0
                && (w.getReport().getTimestamp() - b.getTimestamp()) < beaconROCache.ACCEPTED_TIME_DISTANCE
        ) {
            // checks (self witnessing)
            if (b.getHotspotId().compareTo(hsId) == 0) return false;
        } else {
            log.debug("Impossible to find the beaconer for witness " + w.getReceivedTimestamp());
            b = null;
        }

        Hotspot beaconner = this.getHotspot(b.getHotspotId(),false);
        if (! etlConfig.isIotpocLoadEnable() && fullUpdate ) {
            // Update Hs information
            Hotspot h = this.getHotspot(hsId, true);

            // Search the related beacon
            if (b != null) {
                h.addWitness(
                        b.getHotspotId(),
                        w.getReport().getTimestamp(),
                        w.getReport().getSignal()/10.0,
                        w.getReport().getSnr()/10.0,
                        etlConfig.getHotspotWitnessHistoryEntries(),
                        (beaconner != null)?beaconner.getPosition().getLat():0.0,
                        (beaconner != null)?beaconner.getPosition().getLng():0.0,
                        true,
                        0,       // Late Response : undefined
                        WIT_ERROR_NONE                 // Unselected reason -> SELECTED
                );
                // mark as updated
                this.updateHotspot(h);
            }

        }

        // add a line in global storage
        if ( etlConfig.isWitnessLoadEnable() ) {

            com.disk91.etl.data.object.Witness wi = new com.disk91.etl.data.object.Witness();
            wi.setHotspotId(hsId);
            if (b != null) {
                wi.setBeaconId(b.getHotspotId());
                wi.setBeaconTime(b.getTimestamp());
                wi.setValid(true);
            } else {
                wi.setBeaconId("Unknown");
                wi.setValid(false);
            }
            wi.setVersion(1);
            wi.setData(dataId);
            wi.setSignal(w.getReport().getSignal());
            wi.setSnr(w.getReport().getSnr());
            wi.setDatarate(w.getReport().getDatarate().getNumber());
            wi.setFrequency(w.getReport().getFrequency());
            wi.setTimestamp(w.getReport().getTimestamp());
            wi.setTmst(w.getReport().getTmst());
            // bulk inserting
            delayedWitnessSave(wi);
            //witnessesRepository.save(wi);
            if ( wi.isValid() ) prometeusService.addValidWitnessProcessed();
        }
        if ( w.getReceivedTimestamp() > this.witnessTopTs ) {
            this.witnessTopTs = w.getReceivedTimestamp();
        }

        prometeusService.addWitnessProcessed();
        prometeusService.addWitnessProcessedTime(Now.NowUtcMs()-start);
        return true;
    }


    @Autowired
    MongoTemplate mongoTemplate;

    private static final int MIN_BEFORE_BATCH_BEACON_INSERT = 1_500;

    protected ConcurrentLinkedQueue<Beacon> _beaconDelayedInsert = new ConcurrentLinkedQueue<>();
    protected AtomicInteger _beaconDelayedInsertQueueSize = new AtomicInteger(0);
    public void delayedBeaconSave(Beacon b) {
        if ( _beaconDelayedInsertQueueSize.get() >= MIN_BEFORE_BATCH_BEACON_INSERT + 1_000 ) {
            // force wait when more than 2_000 pending
            long waitTime = Now.NowUtcMs();
            while (_beaconDelayedInsertQueueSize.get() >= MIN_BEFORE_BATCH_BEACON_INSERT + 500 ) {
                Now.sleep(100);
                if ( (Now.NowUtcMs() - waitTime) > 300_000 ) {
                    log.error("delayedBeaconSave - locked ! with {} vs {}", _beaconDelayedInsertQueueSize.get(), _beaconDelayedInsert.size());
                    waitTime = Now.NowUtcMs();
                }

            };
        }
        _beaconDelayedInsert.add(b);
        _beaconDelayedInsertQueueSize.addAndGet(1);
    }


    @Scheduled(fixedDelay = 2_000, initialDelay = 5_000)
    public void bulkInsertBeacons() {

        while ( _beaconDelayedInsertQueueSize.get() >= MIN_BEFORE_BATCH_BEACON_INSERT ) {
            // extract the Witnesses to process
            ArrayList<Beacon> _toWriteBeacon = new ArrayList<>(_beaconDelayedInsert.size());
            int toRead = _beaconDelayedInsert.size();
            Beacon b = _beaconDelayedInsert.poll();
            int cnt = 0;
            while ( b != null && cnt < toRead ) {
                _toWriteBeacon.add(b);
                b = _beaconDelayedInsert.poll();
                cnt++;
            }
            if ( b != null ) {
                _toWriteBeacon.add(b);
                cnt++;
                _beaconDelayedInsertQueueSize.addAndGet(-cnt);
            } else {
                _beaconDelayedInsertQueueSize.set(0);
            }

            long start = Now.NowUtcMs();
            try {
                // parallel write performance is between 1ms and 2ms per entry
                //_toWriteWitness.parallelStream().forEach(witnessesRepository::save);
                // bulk write performance is between 30ms / 100 => 0.3ms per entry
                mongoTemplate.setWriteConcern(WriteConcern.W1.withJournal(false));
                BulkOperations bulkInsert = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, Beacon.class);
                bulkInsert.insert(_toWriteBeacon);
                BulkWriteResult bulkWriteResult = bulkInsert.execute();
                long duration = Now.NowUtcMs() - start;
                int hadToSave = _toWriteBeacon.size();

                if ( _beaconDelayedInsertQueueSize.get() >= MIN_BEFORE_BATCH_BEACON_INSERT ) {
                    log.warn("bulkInsertBeacons going slow ! {} pending", _beaconDelayedInsertQueueSize.get());
                    log.warn("bulkInsertBeacons saves {} in {}ms {}ms/ 100 units", hadToSave, duration, duration / (hadToSave / 100.0));
                }

            } catch ( MongoBulkWriteException x ) {
                log.error("Error during beacon write {}", x.getMessage());
            } finally {
                _toWriteBeacon.clear();
            }

        }
    }


    // Wait for end of the async update process
    // then commit the rest of the witnesses
    private boolean flushInsertBeacon() {
        // refuse it scheduled process has reason to start
        int loops = 0;
        while ( _beaconDelayedInsert.size() > MIN_BEFORE_BATCH_BEACON_INSERT && loops < 300 ) {
            Now.sleep(1000);
            loops++;
        }
        if (!_beaconDelayedInsert.isEmpty()) {
            log.info("flushInsertBeacon - pending {}",_beaconDelayedInsert.size());
            _beaconDelayedInsert.parallelStream().forEach(beaconsRepository::save);
            _beaconDelayedInsertQueueSize.set(0);
        }
        log.info("flushInsertBeacon - completed");
        return true;
    }


    private static final int MIN_BEFORE_BATCH_WITNESS_INSERT = 5_000;

    protected ConcurrentLinkedQueue<com.disk91.etl.data.object.Witness> _witnessDelayedInsert = new ConcurrentLinkedQueue<>();
    protected AtomicInteger _witnessDelayedInsertQueueSize = new AtomicInteger(0);

    public void delayedWitnessSave(com.disk91.etl.data.object.Witness b) {
        if ( _witnessDelayedInsertQueueSize.get() >= MIN_BEFORE_BATCH_WITNESS_INSERT + 5_000 ) {
            // force wait when more than 10_000 pending and until we ger 8_000
            long waitTime = Now.NowUtcMs();
            while ( _witnessDelayedInsertQueueSize.get() >= MIN_BEFORE_BATCH_WITNESS_INSERT + 3_000 ) {
                Now.sleep(100);
                if ( (Now.NowUtcMs() - waitTime) > 300_000 ) {
                    log.warn("delayedWitnessSave - thread locked ! with {} vs {}", _witnessDelayedInsertQueueSize.get(), _witnessDelayedInsert.size());
                    waitTime = Now.NowUtcMs();
                }
            }
            log.info("delayedWitnessSave - thread unlocked");
        }
        _witnessDelayedInsert.add(b);
        _witnessDelayedInsertQueueSize.addAndGet(1);
    }



    @Scheduled(fixedDelay = 1_000, initialDelay = 5_000)
    public void bulkInsertWitness() {

        while ( _witnessDelayedInsertQueueSize.get() > MIN_BEFORE_BATCH_WITNESS_INSERT ) {
            // extract the Witnesses to process
            ArrayList<com.disk91.etl.data.object.Witness> _toWriteWitness = new ArrayList<>();
            int toRead = _witnessDelayedInsert.size(); // use right value to avoid pbm.
            com.disk91.etl.data.object.Witness w = _witnessDelayedInsert.poll();
            int cnt = 0;
            while ( w != null && cnt < toRead ) {
                _toWriteWitness.add(w);
                w = _witnessDelayedInsert.poll();
                cnt++;
            }
            if ( w != null ) {
                // exit due to cnt == toRead but we can have a last value polled
                _toWriteWitness.add(w);
                cnt++;
                if ( _witnessDelayedInsertQueueSize.get() > cnt ) _witnessDelayedInsertQueueSize.addAndGet(-cnt);
                else _witnessDelayedInsertQueueSize.set(0);
            } else {
                // all read, resync the counter
                _witnessDelayedInsertQueueSize.set(0);
            }
            // this may re-open the queue registration ( as soon as the _witnessDelayedInsertQueueSize has changed )


            long start = Now.NowUtcMs();
            // parallel write performance is between 1ms and 2ms per entry
            //_toWriteWitness.parallelStream().forEach(witnessesRepository::save);
            // bulk write performance is around 0.53ms with journal enabled
            // bulk write performance is arounf 0.40ms with journal disable (no real difference)
            try {
                mongoTemplate.setWriteConcern(WriteConcern.W1.withJournal(false));
                BulkOperations bulkInsert = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, com.disk91.etl.data.object.Witness.class);
                bulkInsert.insert(_toWriteWitness);
                BulkWriteResult bulkWriteResult = bulkInsert.execute();
                long duration = Now.NowUtcMs() - start;
                int hadToSave = _toWriteWitness.size();

                if (_witnessDelayedInsertQueueSize.get() > MIN_BEFORE_BATCH_WITNESS_INSERT) {
                    log.warn("bulkInsertWitness going slow ! {} pending", _witnessDelayedInsertQueueSize.get());
                    log.warn("bulkInsertWitness saves {} in {}ms {}ms/ 100 units", hadToSave, duration, duration / (hadToSave / 100.0));
                }
            } catch ( MongoBulkWriteException x ) {
                log.error("Error during witness write {}", x.getMessage());
            } finally {
                _toWriteWitness.clear();
            }
        }
    }

    // Wait for end of the async update process
    // then commit the rest of the witnesses
    private boolean flushInsertWitness() {
        // refuse it scheduled process has reason to start
        int loops = 0;
        while ( _witnessDelayedInsert.size() > MIN_BEFORE_BATCH_WITNESS_INSERT && loops < 300 ) {
           Now.sleep(1000);
           loops++;
        }
        if ( !_witnessDelayedInsert.isEmpty() ) {
            log.info("flushInsertWitness - pending {}",_witnessDelayedInsert.size());
            _witnessDelayedInsert.parallelStream().forEach(witnessesRepository::save);
            _witnessDelayedInsertQueueSize.set(0);
        }
        log.info("flushInsertWitness - completed");
        return true;
    }

    // ======================================================
    // Manage IoT PoC
    // ======================================================

    public boolean addIoTPoC(lora_poc_v1 p, boolean inFirstFile) {

        long start = Now.NowUtcMs();

        // contains POC and Witness information
        lora_valid_beacon_report_v1 beacon = p.getBeaconReport();

        // Update the beaconner
        String hsBeaconerId = HeliumHelper.pubAddressToName(beacon.getReport().getPubKey());
        Hotspot beaconner = this.getHotspot(hsBeaconerId, true);

        if ( inFirstFile ) {
            // search for beacon in case the file has been stopped in the middle
            Beacon b = beaconsRepository.findOneBeaconByHotspotIdAndTimestamp(
                    hsBeaconerId,
                    beacon.getReport().getTimestamp()
            );
            // already processed
            if ( b != null ) return true;
        }

        // init the hexScale & update the gateway info
        double hexScale = beacon.getHexScale() / 10_000.0;
        double elevation = beacon.getElevation();
        double gain = beacon.getGain() / 10.0;
        boolean forcePosUdate = false;
        if (   beaconner.getPosition() == null
            || Math.abs(hexScale - beaconner.getPosition().getHexScale()) > 0.1
            || elevation != beaconner.getPosition().getAlt()
            || gain != beaconner.getPosition().getGain()
        ) forcePosUdate = true;

        if ( forcePosUdate || (start - beaconner.getPosition().getLastDatePosition()) > 12*Now.ONE_HOUR ) {
            // no need to verify position on every beacon
            if (h3 != null) {
                LatLng pos = h3.cellToLatLng(Long.parseLong(beacon.getLocation()));
                if (pos != null && Gps.isAValidCoordinate(pos.lat, pos.lng) ) {
                    if ( beaconner.getPosition() == null ) {
                        beaconner.updatePosition(beacon.getReceivedTimestamp(), pos.lat, pos.lng, elevation, gain,hexScale);
                        log.debug("Position create : " + pos.lat + " / " + pos.lng + " for " + hsBeaconerId);
                    } else if ( forcePosUdate || Gps.distance(beaconner.getPosition().getLat(), pos.lat, beaconner.getPosition().getLng(), pos.lng, 0, 0) > 500) {
                        beaconner.updatePosition(beacon.getReceivedTimestamp(), pos.lat, pos.lng, elevation, gain,hexScale);
                        log.debug("Position change : " + pos.lat + " / " + pos.lng + " for " + hsBeaconerId);
                    }
                    // temp log for monitoring
                    //if ( beaconner.getPosHistory() != null && beaconner.getPosHistory().size() > 10 ) {
                    //    log.info("We have Hotspot "+beaconner.getHotspotId()+" with more than 10 positions in history");
                    //}
                }
            }
        }
        beaconner.addBeacon(beacon.getReceivedTimestamp(),etlConfig.getHotspotBeaconHistoryEntries());

        // Add the Raw beacon if not inserted by the previous process
        Beacon be = null;
        String beaconData = HexaConverters.byteToHexString(beacon.getReport().getData().toByteArray());
        if ( ! etlConfig.isBeaconLoadEnable() ) {
            be = new Beacon();
            be.setHotspotId(hsBeaconerId);
            be.setVersion(1);
            be.setChannel(beacon.getReport().getChannel());
            be.setDatarate(beacon.getReport().getDatarate().getNumber());
            be.setFrequency(beacon.getReport().getFrequency());
            be.setTimestamp(beacon.getReport().getTimestamp()); // Nano Seconds
            be.setTmst(beacon.getReport().getTmst());
            be.setTx_power(beacon.getReport().getTxPower());
            be.setData(beaconData);
            // Async bulk write
            delayedBeaconSave(be);
            //beaconsRepository.save(be);
            //beaconROCache.addBeacon(be); -- never used...
            prometeusService.addBeaconProcessed();
            prometeusService.addBeaconProcessedTime(Now.NowUtcMs()-start);
        }

        /*else {
            // search for existing beacon
            be = beaconROCache.getBeacon(beaconData, beacon.getReport().getTimestamp());
            // control the beacon
            if (be == null
                    || (beacon.getReport().getTimestamp() - be.getTimestamp()) < 0
                    || (beacon.getReport().getTimestamp() - be.getTimestamp()) >= beaconROCache.ACCEPTED_TIME_DISTANCE
            ) {
                be = null;
            }
        }
        */

        // Search for first arrival
        long firstArrival = Now.NowUtcMs();
        for ( lora_verified_witness_report_v1 v : p.getSelectedWitnessesList() ) {
            if ( v.getReceivedTimestamp() < firstArrival ) firstArrival = v.getReceivedTimestamp();
        }
        // sort the list by order of arrival to get the position -> ascending order
        ArrayList<lora_verified_witness_report_v1> selected = new ArrayList<>(p.getSelectedWitnessesList());
        ArrayList<lora_verified_witness_report_v1> unselected = new ArrayList<>(p.getUnselectedWitnessesList());
        selected.sort((p1, p2) -> (int) (p1.getReceivedTimestamp() - p2.getReceivedTimestamp()));
        unselected.sort((p1, p2) -> (int) (p1.getReceivedTimestamp() - p2.getReceivedTimestamp()));

        // Update the Witness information
        int order = 0;
        for ( lora_verified_witness_report_v1 v : selected ) {
            long wstart = Now.NowUtcMs();
            order++;
            String witnesserId = HeliumHelper.pubAddressToName(v.getReport().getPubKey());
            Hotspot witnessed = this.getHotspot(witnesserId, true);

            // check if removed from deny list
            if ( witnessed.isInDenyList() ) {
                log.debug("Found a hotspot to remove from deny list "+witnessed.getHotspotId());
                witnessed.updateDeny(v.getReport().getTimestamp(),false);
            }

            beaconner.addBeaconed(
                    witnesserId,
                    v.getReport().getTimestamp(),
                    v.getReport().getSignal()/10.0,
                    v.getReport().getSnr()/10.0,
                    witnessed.getPosition().getLat(),
                    witnessed.getPosition().getLng(),
                    true
            );

            // Add beaconner to witnessed
            witnessed.addWitness(
                    beaconner.getHotspotId(),
                    v.getReport().getTimestamp(),
                    v.getReport().getSignal()/10.0,
                    v.getReport().getSnr()/10.0,
                    etlConfig.getHotspotWitnessHistoryEntries(),
                    (beaconner != null)?beaconner.getPosition().getLat():0.0,
                    (beaconner != null)?beaconner.getPosition().getLng():0.0,
                    true,
                    v.getReceivedTimestamp() - firstArrival,
                    WIT_ERROR_NONE
            );
            // mark as updated
            this.updateHotspot(witnessed);

            if ( ! etlConfig.isWitnessLoadEnable() ) {
                com.disk91.etl.data.object.Witness wi = new com.disk91.etl.data.object.Witness();
                wi.setHotspotId(witnesserId);
                wi.setValid(true);
                wi.setBeaconId(beaconner.getHotspotId());
                wi.setBeaconTime(beacon.getReceivedTimestamp());
                wi.setVersion(1);
                wi.setData(beaconData);
                wi.setSignal(v.getReport().getSignal());
                wi.setSnr(v.getReport().getSnr());
                wi.setDatarate(v.getReport().getDatarate().getNumber());
                wi.setFrequency(v.getReport().getFrequency());
                wi.setTimestamp(v.getReport().getTimestamp());
                wi.setTmst(v.getReport().getTmst());
                wi.setOrderOfArrival(order);
                wi.setLateOfArrival(v.getReceivedTimestamp() - firstArrival);
                // bulk inserting
                delayedWitnessSave(wi);
                //witnessesRepository.save(wi);
                if ( v.getReceivedTimestamp() > this.witnessTopTs ) {
                    this.witnessTopTs = v.getReceivedTimestamp();
                }
                prometeusService.addValidWitnessProcessed();
                prometeusService.addWitnessProcessed();
                prometeusService.addWitnessProcessedTime(Now.NowUtcMs()-wstart);
            }

        }

        // Process the unselected
        for ( lora_verified_witness_report_v1 v : unselected ) {
            long wstart = Now.NowUtcMs();

            String witnesserId = HeliumHelper.pubAddressToName(v.getReport().getPubKey());
            Hotspot witnessed = this.getHotspot(witnesserId, true);

            // if the reason is related to denied list
            int reason = switch (v.getInvalidReasonValue()) {
                // case we do not want to track
                case WIT_ERROR_NONE, WIT_ERROR_SELF_WITNESS, WIT_ERROR_UNKNOWN -> WIT_ERROR_NONE;
                // Others we want to track
                default -> v.getInvalidReasonValue();
            };

            if ( v.hasInvalidDetails() ) {
                if ( v.getInvalidDetails().hasDenylistTag() ) {
                   if ( ! witnessed.isInDenyList() ) log.debug("Found a hotspot to add in deny list "+witnessed.getHotspotId());
                   witnessed.updateDeny(v.getReport().getTimestamp(),true);
                }
            }

            beaconner.addBeaconed(
                    witnesserId,
                    v.getReport().getTimestamp(),
                    v.getReport().getSignal()/10.0,
                    v.getReport().getSnr()/10.0,
                    witnessed.getPosition().getLat(),
                    witnessed.getPosition().getLng(),
                    false
            );

            // Add beaconner to witnessed
            witnessed.addWitness(
                    beaconner.getHotspotId(),
                    v.getReport().getTimestamp(),
                    v.getReport().getSignal()/10.0,
                    v.getReport().getSnr()/10.0,
                    etlConfig.getHotspotWitnessHistoryEntries(),
                    (beaconner != null)?beaconner.getPosition().getLat():0.0,
                    (beaconner != null)?beaconner.getPosition().getLng():0.0,
                    false,
                    v.getReceivedTimestamp() - firstArrival,
                    reason
            );
            // mark as updated
            this.updateHotspot(witnessed);

            prometeusService.addWitnessProcessed();
            prometeusService.addWitnessProcessedTime(Now.NowUtcMs()-wstart);

        }
        this.updateHotspot(beaconner);

        // Update the invalid witnesses
        if ( ! etlConfig.isWitnessLoadEnable() && etlConfig.isIotpocLoadUnselected() ) {
            for (lora_verified_witness_report_v1 v : unselected) {
                order++;
                String witnesserId = HeliumHelper.pubAddressToName(v.getReport().getPubKey());
                //Hotspot witnessed = this.getHotspot(witnesserId, true);

                com.disk91.etl.data.object.Witness wi = new com.disk91.etl.data.object.Witness();
                wi.setHotspotId(witnesserId);
                wi.setBeaconId(beaconner.getHotspotId());
                wi.setBeaconTime(beacon.getReceivedTimestamp());
                wi.setValid(false);
                wi.setVersion(1);
                wi.setData(beaconData);
                wi.setSignal(v.getReport().getSignal());
                wi.setSnr(v.getReport().getSnr());
                wi.setDatarate(v.getReport().getDatarate().getNumber());
                wi.setFrequency(v.getReport().getFrequency());
                wi.setTimestamp(v.getReport().getTimestamp());
                wi.setTmst(v.getReport().getTmst());
                wi.setOrderOfArrival(order);
                wi.setLateOfArrival(v.getReceivedTimestamp() - firstArrival);
                // bulk inserting
                delayedWitnessSave(wi);
                //witnessesRepository.save(wi);
                if ( v.getReceivedTimestamp() > this.witnessTopTs ) {
                    this.witnessTopTs = v.getReceivedTimestamp();
                }
                if ( wi.isValid() ) prometeusService.addValidWitnessProcessed();
            }
        }

        if ( beacon.getReceivedTimestamp() > this.iotpocTopTs ) {
            this.iotpocTopTs = beacon.getReceivedTimestamp();
        }

        prometeusService.addIoTPocProcessed();
        prometeusService.addIoTPocProcessedTime(Now.NowUtcMs()-start);
        return true;
    }

    // ============================================
    // Hotspot Rewards
    // ============================================

    @Autowired
    protected RewardRepository rewardRepository;

    /**
     * Add a reward to the Hotspot
     * @param r
     * @param token 0 for IoT and 1 for HNT
     * @return
     */
    public boolean addReward(iot_reward_share r, int token) {
        long start = Now.NowUtcMs();

        String hsId = HeliumHelper.pubAddressToName(r.getGatewayReward().getHotspotKey());
        Hotspot rewarded = this.getHotspot(hsId, true);
        if( r.getStartPeriod()*1000 > rewarded.getLastReward() ) {
            rewarded.updateReward(r.getStartPeriod()*1000,r.getGatewayReward().getBeaconAmount(),r.getGatewayReward().getWitnessAmount(),r.getGatewayReward().getDcTransferAmount(), token);
            this.updateHotspot(rewarded);

            // add the Reward data
            Reward _r = new Reward();
            _r.setHotspotId(hsId);
            _r.setStartPeriod(r.getStartPeriod()*1000);
            _r.setEndPeriod(r.getEndPeriod()*1000);
            _r.setToken(token);
            _r.setWitnessAmount(r.getGatewayReward().getWitnessAmount());
            _r.setBeaconAmount(r.getGatewayReward().getBeaconAmount());
            _r.setDcTransferAmount(r.getGatewayReward().getDcTransferAmount());
            _r.setVersion(1);
            delayedRewardSave(_r);
            //rewardRepository.save(_r);

            // stats
            prometeusService.addRewardProcessed();
            prometeusService.addRewardProcessedTime(Now.NowUtcMs()-start);

            return true;
        }
        return false;
    }

    protected ArrayList<Reward> _rewardsDelayedInsert = new ArrayList<>();
    public synchronized void delayedRewardSave(Reward b) {
        _rewardsDelayedInsert.add(b);
        if ( _rewardsDelayedInsert.size() > 8_000 ) {
            if ( bulkInsertRewards() > 0 ) {
                _rewardsDelayedInsert.clear();
            }
        }
    }

    public int bulkInsertRewards() {

        synchronized (asyncIoTWrite) {
            if (inAsyncIoTWrite) return 0;
            inAsyncIoTWrite = true;
        }
        try {
            if (!_rewardsDelayedInsert.isEmpty()) {
                mongoTemplate.setWriteConcern(WriteConcern.W1.withJournal(false));
                BulkOperations bulkInsert = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, Reward.class);
                for (Reward b : _rewardsDelayedInsert) {
                    bulkInsert.insert(b);
                }
                BulkWriteResult bulkWriteResult = bulkInsert.execute();
                return bulkWriteResult.getInsertedCount();
            }
            return 0;
        } finally {
            synchronized (asyncIoTWrite) {
                inAsyncIoTWrite = false;
            }
        }

    }


    // ============================================
    // Mobile Rewards
    // ============================================

    @Autowired
    protected MobileRewardRepository mobileRewardRepository;

    protected static final String BLANCK_KEY = "00000000000000000000000000000000";
    public boolean addMobileReward(AwsService.MobileReward r, int token) {
        long start = Now.NowUtcMs();

        MobileReward mr = new MobileReward();
        mr.setVersion(1);

        if ( r.oldReward != null ) {
            mr.setHotspotId(HeliumHelper.pubAddressToName(r.oldReward.getHotspotKey()));
            mr.setOwnerId(HeliumHelper.pubAddressToName(r.oldReward.getOwnerKey()));
            mr.setStartPeriod(r.oldReward.getStartEpoch()*1000);
            mr.setEndPeriod(r.oldReward.getEndEpoch()*1000);
            mr.setRadioPocReward(r.oldReward.getAmount());
            delayedMobileRewardSave(mr);
            //log.info(">"+r.oldReward.getHotspotKey().toString()+" "+r.oldReward.getAmount()+" "+r.oldReward.getStartEpoch());
        } else if ( r.newReward != null ) {
            mr.setStartPeriod(r.newReward.getStartPeriod()*1000);
            mr.setEndPeriod(r.newReward.getEndPeriod()*1000);
            mr.setToken(token);
            if ( r.newReward.hasRadioReward() ) {
                mr.setHotspotId(HeliumHelper.pubAddressToName(r.newReward.getRadioReward().getHotspotKey()));
                mr.setOwnerId(BLANCK_KEY);
                mr.setRadioPocReward(r.newReward.getRadioReward().getPocReward());
                mr.setRadioCovergePoint(r.newReward.getRadioReward().getCoveragePoints());
                delayedMobileRewardSave(mr);
                //log.info("ng> "+r.newReward.getGatewayReward().getHotspotKey().toString()+" "+r.newReward.getGatewayReward().getDcTransferReward()+" "+r.newReward.getStartPeriod());
            }
            if ( r.newReward.hasGatewayReward() ) {
                mr.setHotspotId(HeliumHelper.pubAddressToName(r.newReward.getGatewayReward().getHotspotKey()));
                mr.setOwnerId(BLANCK_KEY);
                mr.setDcTransferReward(r.newReward.getGatewayReward().getDcTransferReward());
                mr.setDcTransferPrice(r.newReward.getGatewayReward().getPrice());
                mr.setOwnerId(BLANCK_KEY);
                delayedMobileRewardSave(mr);
                //log.info("nr> "+r.newReward.getRadioReward().getHotspotKey().toString()+" "+r.newReward.getRadioReward().getPocReward()+" "+r.newReward.getStartPeriod());
            }
            if ( r.newReward.hasSubscriberReward() ) {
                mr.setHotspotId(BLANCK_KEY);
                mr.setOwnerId(HeliumHelper.pubAddressToName(r.newReward.getSubscriberReward().getSubscriberId()));
                mr.setSubscriberReward(r.newReward.getSubscriberReward().getDiscoveryLocationAmount());
                delayedMobileRewardSave(mr);
                //log.info("sr> "+r.newReward.getSubscriberReward().getSubscriberId().toString()+" ? "+" "+r.newReward.getStartPeriod());
            }
            if ( r.newReward.hasServiceProviderReward() ) {
                mr.setHotspotId(BLANCK_KEY);
                mr.setOwnerId("SP_"+r.newReward.getServiceProviderReward().getServiceProviderId().getNumber()+"_"+r.newReward.getServiceProviderReward().getServiceProviderId().name());
                mr.setServiceProviderReward(r.newReward.getServiceProviderReward().getAmount());
                delayedMobileRewardSave(mr);
                //log.info("sp> "+r.newReward.getServiceProviderReward().getServiceProviderId().name()+" "+r.newReward.getServiceProviderReward().getAmount()+" "+r.newReward.getStartPeriod());
            }
            if ( r.newReward.hasUnallocatedReward() ) {
                mr.setHotspotId(BLANCK_KEY);
                mr.setOwnerId(BLANCK_KEY);
                switch ( r.newReward.getUnallocatedReward().getRewardType() ) {
                    case unallocated_reward_type_poc: mr.setUnallocatedPoc(r.newReward.getUnallocatedReward().getAmount()); break;
                    case unallocated_reward_type_discovery_location: mr.setUnallocatedDiscovery(r.newReward.getUnallocatedReward().getAmount()); break;
                    case unallocated_reward_type_mapper: mr.setUnallocatedMapper(r.newReward.getUnallocatedReward().getAmount()); break;
                    case unallocated_reward_type_service_provider: mr.setUnallocatedService(r.newReward.getUnallocatedReward().getAmount()); break;
                    case unallocated_reward_type_oracle: mr.setUnallocatedOracle(r.newReward.getUnallocatedReward().getAmount()); break;
                    case unallocated_reward_type_data:mr.setUnallocatedData(r.newReward.getUnallocatedReward().getAmount()); break;
                    default: break;
                }
                delayedMobileRewardSave(mr);
            }
        }
        // stats
        prometeusService.addMobileRewardProcessed();
        prometeusService.addMobileRewardProcessedTime(Now.NowUtcMs()-start);
        return true;
    }

    protected ArrayList<MobileReward> _mobileRewardsDelayedInsert = new ArrayList<>();
    public synchronized void delayedMobileRewardSave(MobileReward b) {
        _mobileRewardsDelayedInsert.add(b);
        if ( _mobileRewardsDelayedInsert.size() > 8_000 ) {
            if ( bulkInsertMobileRewards() > 0 ) {
                _mobileRewardsDelayedInsert.clear();
            }
        }
    }

    public int bulkInsertMobileRewards() {

        synchronized (asyncMobileWrite) {
            if (inAsyncMobileWrite) return 0;
            inAsyncMobileWrite = true;
        }
        try {
            if (!_mobileRewardsDelayedInsert.isEmpty()) {
                mongoTemplate.setWriteConcern(WriteConcern.W1.withJournal(false));
                BulkOperations bulkInsert = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, MobileReward.class);
                for (MobileReward b : _mobileRewardsDelayedInsert) {
                    bulkInsert.insert(b);
                }
                BulkWriteResult bulkWriteResult = bulkInsert.execute();
                return bulkWriteResult.getInsertedCount();
            }
            return 0;
        } finally {
            synchronized (asyncMobileWrite) {
                inAsyncMobileWrite = false;
            }
        }

    }


    // ============================================
    // Hotspot Enrichment from Legacy
    // ============================================

    @Autowired
    protected LegacyEtlService legacyEtlService;

    protected ConcurrentLinkedQueue<Hotspot> asyncEnrichement = null;

    protected void addForEnrichemnent(Hotspot h) {
        if ( ! etlConfig.isHeliumLegacyApiEnable() ) return;

        // already updated
        if ( h.getOwner() != null && h.getOwner().getHntOwner().length() > 2 ) return;
        // check if not too much hotspot are pending
        if ( this.asyncEnrichement != null && this.asyncEnrichement.size() < 1000 ) this.asyncEnrichement.add(h);
    }

    @Scheduled(fixedDelay = 5_000, initialDelay = 60_000)
    private void enrich() {
        if ( ! etlConfig.isHeliumLegacyApiEnable() ) return;
        if ( !this.serviceEnable  || this.stopRequested ) return;
        if ( asyncEnrichement == null ) { this.asyncEnrichement = new ConcurrentLinkedQueue<>(); return; }

        try {
            while (this.asyncEnrichement.size() > 0) {
                if ( !this.serviceEnable || this.stopRequested ) return;
                Hotspot h = this.asyncEnrichement.poll();
                log.info("Enrich Hotspot - " + h.getHotspotId());
                if (h != null) {
                    // check if already updated
                    if (h.getOwner() != null && h.getOwner().getHntOwner().length() > 2) continue;

                    try {
                        HotspotDetail hd = legacyEtlService.getHotspotDetails(h.getHotspotId(), false);
                        long tot = legacyEtlService.getHotspotTotalReward(h.getHotspotId());
                        log.info("Found "+hd.getGeocode().getLong_city()+" ("+hd.getGeocode().getShort_country()+") with "+tot+" Hnt");

                        TemporalAccessor ta = DateTimeFormatter.ISO_INSTANT.parse(hd.getTimestamp_added());
                        Instant i = Instant.from(ta);
                        Date d = Date.from(i);
                        long addedMs = d.getTime();

                        h.updateOwner(null,hd.getOwner(),addedMs);
                        h.setOffsetReward(tot);

                        // Update insertion
                        if (addedMs < h.getFirstSeen() || h.getFirstSeen() == 0) {
                            h.setFirstSeen(addedMs);
                        }

                        // update brand
                        h.setBrand(MakerElement.GetHotspotBrand(hd.getPayer()));

                        // check position
                        com.disk91.etl.data.object.sub.LatLng p = new com.disk91.etl.data.object.sub.LatLng();
                        p.setLat(hd.getLat());
                        p.setLng(hd.getLng());
                        p.setCountry(hd.getGeocode().getShort_country());
                        p.setCity(hd.getGeocode().getLong_city());
                        p.setGain(hd.getGain() / 10.0); // dBi after division
                        p.setAlt(hd.getElevation()); // in meter
                        p.setLastDatePosition(Now.APRIL_19_2023);
                        if (h.getPosition() != null
                                && (  !Gps.isAValidCoordinate(h.getPosition().getLat(), h.getPosition().getLng())
                                    || Gps.distance(h.getPosition().getLat(), p.getLat(), h.getPosition().getLng(), p.getLng(), 0, 0) < 500
                                   )
                        ) {
                            // less than 500 m assuming same position, more details on this one
                            // or the previous coordinate was invalid
                            if (Gps.isAValidCoordinate(p.getLat(), p.getLng())) {
                                h.setPosition(p);
                            }
                        } else {
                            if (h.getPosition() == null) h.setPosition(p);
                            else {
                                // hotspot position is new compare to the current one, add in history
                                if (h.getPosHistory() == null) h.setPosHistory(new ArrayList<>());
                                h.getPosHistory().add(p);
                            }
                        }
                        this.updateHotspot(h);

                    } catch (ITNotFoundException x) {
                        log.warn("Enrich, not found for " + h.getHotspotId());
                    } catch (ITParseException x) {
                        log.warn("Enrich, parse for " + h.getHotspotId());
                    } catch (Exception x) {
                        log.error("Enrich " + x.getMessage());
                    }
                } else {
                    return;
                }
            }
            // we are empty, may be get some hotspots like 100 more ;)
            Iterator<String> i = heliumHotspotCache.list().asIterator();
            int cpt = 0;
            SecureRandom r = new SecureRandom();
            while (i.hasNext() && cpt < 200) {
                String hs = i.next();
                int v = r.nextInt(64);
                if ( v == 0 ) {
                    // add random to not be stuck with a list of non existing hotspot on backend
                    Hotspot h = this.getHotspot(hs, false);
                    if ( h.getOwner() == null || h.getOwner().getHntOwner() == null || h.getOwner().getHntOwner().length() < 2) {
                        // candidate
                        addForEnrichemnent(h);
                        cpt++;
                    }
                }
            }

        } catch (Exception x) {
            log.error("Exception in enrichement : "+x.getMessage() );
        }
    }

    // =====================================
    // Index Hotspot for easier way to search
    // =====================================

    @Autowired
    protected HotspotsIndexRepository hotspotsIndexRepository;

    @Autowired
    protected HotspotsSpecial hotspotsSpecial;

    protected ConcurrentLinkedQueue<Hotspot> asyncIndexing = null;

    protected void addForIndexing(Hotspot h) {
        if ( etlConfig.isHeliumHotspotIndexingEnable() && this.asyncIndexing != null && this.asyncIndexing.size() < 1000 ) this.asyncIndexing.add(h);
    }


    @Scheduled(fixedDelay = 120_000, initialDelay = 30_000)
    private void index() {
        if ( ! etlConfig.isHeliumHotspotIndexingEnable() ) return;     // no indexing
        log.debug("Running index");
        if (!this.serviceEnable || this.stopRequested || this.pauseIndexing ) return;

        // process the queue whatever
        if ( asyncIndexing == null ) { this.asyncIndexing = new ConcurrentLinkedQueue<>(); return; }
        while (this.asyncIndexing.size() > 0) {
            if (!this.serviceEnable || this.stopRequested) return;
            Hotspot h = this.asyncIndexing.poll();
            if ( hotspotsIndexRepository.findOneHotspotIndexByHotspotId(h.getHotspotId()) == null ) {
                // new one to add
                HotspotIndex hi = new HotspotIndex();
                hi.init(h);
                hotspotsIndexRepository.save(hi);
            }
        }

        // background update / verif...
        int processed = 0;
        int loops = 0;
        long tRef = Now.NowUtcMs()-(10*Now.ONE_FULL_DAY);

        PageRequest page = PageRequest.of(0,50);
        List<Hotspot> hs = hotspotsRepository.findFirst50HotspotToBeIndexed(tRef,page);
        while ( processed < 250 && loops < 10 ) {
            // last page ...
            if ( hs.size() < 50 ) processed = 250;

            log.debug("Process "+hs.get(0).getAnimalName()+" pos "+processed);
            for (Hotspot _h : hs) {
                // make sure we use the cache version
                Hotspot h = this.getHotspotSync(_h.getHotspotId(), true);
                if (h.getLastIndexed() < tRef) {
                    processed++;
                    // confirmed to be updated
                    h.setLastIndexed(Now.NowUtcMs());
                    this.updateHotspot(h);
                    // work on index
                    HotspotIndex hi = hotspotsIndexRepository.findOneHotspotIndexByHotspotId(_h.getHotspotId());
                    if (hi == null) {
                        hi = new HotspotIndex();
                        hi.init(h);
                        hotspotsIndexRepository.save(hi);
                    } else {
                        if (
                            (h.getOwner() != null && hi.getSolOwner().compareToIgnoreCase(h.getOwner().getSolOwner()) != 0)
                                || (h.getPosition() != null && (hi.getPosition().getX() != h.getPosition().getLng() || hi.getPosition().getY() != h.getPosition().getLat()))
                        ) {
                            hi.init(h);
                            hotspotsIndexRepository.save(hi);
                        }
                    }
                }
            }
            loops++;
            // load next page ramdomly
            hs = hotspotsSpecial.get50RandomDocuments();
        }
    }


    // =====================================
    // Find hotspots per owner
    // =====================================

    @Autowired
    protected SolanaAPiService solanaAPiService;
    public List<HotspotIdent> getAndUpdateHotspotsPerOwner(String owner)
    throws ITNotFoundException {
        ArrayList<HotspotIdent> ret = new ArrayList<>();
        try {
            WalletHotspotList w = solanaAPiService.getHotspotList(owner);
            // we have a list, let see if the Hotspots are up-to-date
            for ( String hId : w.getHotspotsECCId() ) {
                Hotspot h = this.getHotspotSync(hId,true);
                if ( h.getOwner() == null || h.getOwner().getSolOwner().compareToIgnoreCase(w.getWalletId()) != 0 ) {
                    // new or different owner
                    h.updateOwner(w.getWalletId(),null,0);
                    this.updateHotspot(h);
                    // also update the Index
                    HotspotIndex hi = hotspotsIndexRepository.findOneHotspotIndexByHotspotId(hId);
                    if ( hi != null ) {
                        hi.setHntOwner(h.getOwner().getHntOwner());
                        hi.setSolOwner(h.getOwner().getSolOwner());
                        hotspotsIndexRepository.save(hi);
                    }
                }
                HotspotIdent hi = new HotspotIdent();
                hi.init(h);
                ret.add(hi);
            }
            return ret;
        } catch (ITNotFoundException | ITParseException x) {
            log.debug("Failed to get the wallet information");
            throw new ITNotFoundException();
        }
    }

    // -----------------------------
    // Search hotspot by animal name
    // -----------------------------

    public List<HotspotIdent> getHotspotsByAnimal(String name) {

        name = name.replace(' ', '-');
        if ( ! name.matches("^[a-zA-Z\\-]+$") ) return new ArrayList<>();

        PageRequest pageRequest = PageRequest.of(0,10);

        ArrayList<HotspotIdent> ret = new ArrayList<>();
        List<HotspotIndex> his = hotspotsIndexRepository.findHotspotIndexByAnimalNameLikeStarts(name, pageRequest).getContent();
        if (his.isEmpty() && name.length() > 5) {
            // it's possible the term is not on hostpot name start but elsewhere
            his = hotspotsIndexRepository.findHotspotIndexByAnimalNameLike(name,pageRequest).getContent();
        }
        if (his.isEmpty() && name.length() < 10 ) {
            // it's possible we don't have a word in the search term, look for letter list
            his = hotspotsIndexRepository.findHotspotIndexByAnimalNameStarts(name,pageRequest).getContent();
        }
        for ( HotspotIndex hi : his ) {
            HotspotIdent id = new HotspotIdent();
            id.init(hi);
            ret.add(id);
        }

        // exact name potential, as the Index does not have all the hotspots have a look to the main table
        // Would be better index to have a full coverage, for this the idea could be an API to request indexing on
        // master ETL and a script to search missing on the api to not overload master
        if ( name.matches("^[a-zA-Z]+-[a-zA-Z]+-[a-zA-Z]+$") ) {
            String ref = null;
            boolean allSame = true;
            for ( HotspotIndex hi : his ) {
                if ( ref == null ) ref = hi.getAnimalName();
                if ( ref.compareToIgnoreCase(hi.getAnimalName()) != 0 ) allSame = false;
            }
            if (allSame) {
                // check out of index as index in not complete
                List<Hotspot> hss = hotspotsRepository.findHotspotsByAnimalName(name);
                if ( hss != null ) {
                    for ( Hotspot hs : hss) {
                        // is in the list already ?
                        boolean inIndex = false;
                        for ( HotspotIndex hi : his ) { if ( hi.getHotspotId().compareTo(hs.getHotspotId()) == 0 ) inIndex = true; }
                        if ( !inIndex ) {
                            addForIndexing(hs);
                            HotspotIdent id = new HotspotIdent();
                            id.init(hs);
                            ret.add(id);
                        }
                    }
                }
            }
        }

        return ret;
    }

    public List<HotspotIdent> getHotspotsByGeo(double latNW, double lonNW, double latSE, double lonSE) {
        // make sure we are on the right coodinates
        double latN = Math.max(latNW, latSE);
        double latS = Math.min(latNW, latSE);
        double lonW = Math.min(lonNW, lonSE);
        double lonE = Math.max(lonNW, lonSE);

        if ( !Gps.isAValidCoordinate(latN,lonW) || !Gps.isAValidCoordinate(latS,lonE) ) return new ArrayList<HotspotIdent>();

        ArrayList<HotspotIdent> ret = new ArrayList<>();
        PageRequest pageRequest = PageRequest.of(0,200); // Max 200
        List<HotspotIndex> his = hotspotsIndexRepository.findByPositionNearbyBox(lonW,latS,lonE,latN, pageRequest).getContent();
        for ( HotspotIndex hi : his ) {
            HotspotIdent id = new HotspotIdent();
            id.init(hi);
            ret.add(id);
        }

        return ret;

    }


}
