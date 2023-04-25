package com.disk91.etl.service;

import com.disk91.etl.EtlConfig;
import com.disk91.etl.data.object.Beacon;
import com.disk91.etl.data.object.Hotspot;
import com.disk91.etl.data.object.Param;
import com.disk91.etl.data.object.Reward;
import com.disk91.etl.data.repository.*;
import com.helium.grpc.*;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class HotspotCache {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private MeterRegistry registry;
    public HotspotCache(MeterRegistry registry) {
        this.registry = registry;
    }

    protected int runningJobs;
    protected boolean serviceEnable; // false to stop the services


    private ObjectCache<String, Hotspot> heliumHotspotCache;

    @Autowired
    protected ParamRepository paramRepository;

    protected Param beaconTopLine = null;
    protected long beaconTopTs = 0;
    protected Param witnessTopLine = null;
    protected long witnessTopTs = 0;

    protected Param iotpocTopLine = null;
    protected long iotpocTopTs = 0;

    protected volatile Boolean inAsyncWrite = Boolean.valueOf(false);

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

    public void stopService() {
        // flush pending write
        log.info("Flush Beacon & Witness pending");
        long s = Now.NowUtcMs();
        while ( inAsyncWrite == true  && (Now.NowUtcMs() - s) < 60_000 );
        bulkInsertRewards();
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

    synchronized public Hotspot getHotspot(String hotspotId, boolean cache) {
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
                hs.setWitnesses(new ArrayList<>());
                hs.setBeaconHistory(new ArrayList<>());
                hs.setWitnessesHistory(new ArrayList<>());
                hs.setBeaconned(new ArrayList<>());
                hs.setLastBeacon(0);
                hs.setLastWitness(0);
                hs.setVersion(1);
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
            }
            if ( cache && hs != null ) {
                heliumHotspotCache.put(hs,hotspotId,false);
            }
        }
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
    private boolean forceSyncUpdate = false;
    private long lastForceSync = Now.NowUtcMs();
    public synchronized void updateHotspot(Hotspot o) {
        heliumHotspotCache.put(o,o.getHotspotId());
        modifications++;
        prometeusService.changeHsModification(modifications);
        if ( ! hotspotCacheAsync.isRunning()
        && (    modifications > etlConfig.getCacheHotspotCommit()   // to commit the modifications
             || heliumHotspotCache.isInAsyncSync()                  // to terminate async sync in batch
           )
        ) {
            modifications = 0;
            long updated = heliumHotspotCache.commit(true,etlConfig.getCacheHotspotCommit()); // async commit to quit immediately
            // avoid to have a parallel request to start as the async process can take a few mx to start and another pending updateHotspot takes
            // the opportunity to run a concurrent request and consume memory we need
            long s = Now.NowUtcMs() ; while ( !hotspotCacheAsync.isRunning() && ((Now.NowUtcMs()-s) < 10_000) );

            if ( updated < etlConfig.getCacheHotspotCommit() ) {
                // basically means that this update takes all the pending updates
                modifications = 0;
            } else {
                // or we have some pending
                modifications = updated-etlConfig.getCacheHotspotCommit();
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
                        true
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
    public void delayedBeaconSave(Beacon b) {
        if ( _beaconDelayedInsert.size() > MIN_BEFORE_BATCH_BEACON_INSERT + 1_000 ) {
            // force wait when more than 2_000 pending
            while (_beaconDelayedInsert.size() > MIN_BEFORE_BATCH_BEACON_INSERT + 500 ) {
                try { Thread.sleep(100); } catch (InterruptedException x) {} ;
            };
        }
        _beaconDelayedInsert.add(b);
    }


    @Scheduled(fixedDelay = 2_000, initialDelay = 5_000)
    public void bulkInsertBeacons() {

        while ( _beaconDelayedInsert.size() > MIN_BEFORE_BATCH_BEACON_INSERT ) {
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

            long start = Now.NowUtcMs();
            // parallel write performance is between 1ms and 2ms per entry
            //_toWriteWitness.parallelStream().forEach(witnessesRepository::save);
            // bulk write performance is between
            mongoTemplate.setWriteConcern(WriteConcern.W1.withJournal(false));
            BulkOperations bulkInsert = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, Beacon.class);
            bulkInsert.insert(_toWriteBeacon);
            BulkWriteResult bulkWriteResult = bulkInsert.execute();
            long duration = Now.NowUtcMs() - start;
            int hadToSave = _toWriteBeacon.size();
            _toWriteBeacon.clear();

            log.warn("bulkInsertBeacons saves "+hadToSave+" in "+duration+"ms "+duration/(hadToSave/100.0)+"ms/ 100 units");

            if ( _beaconDelayedInsert.size() > MIN_BEFORE_BATCH_BEACON_INSERT ) {
                log.warn("bulkInsertBeacons going slow ! "+_beaconDelayedInsert.size()+" pending");
                log.warn("bulkInsertBeacons saves "+hadToSave+" in "+duration+"ms "+duration/(hadToSave/100.0)+"ms/ 100 units");
            }

        }
    }


    // Wait for end of the async update process
    // then commit the rest of the witnesses
    private boolean flushInsertBeacon() {
        // refuse it scheduled process has reason to start
        int loops = 0;
        while ( _beaconDelayedInsert.size() > MIN_BEFORE_BATCH_BEACON_INSERT && loops < 100 ) {
            try {
                Thread.sleep(500);
            } catch ( InterruptedException x ) {};
            loops++;
        }
        if ( loops < 100) {
            _beaconDelayedInsert.parallelStream().forEach(beaconsRepository::save);
            return true;
        }
        return false;
    }


    private static final int MIN_BEFORE_BATCH_WITNESS_INSERT = 5_000;

    protected ConcurrentLinkedQueue<com.disk91.etl.data.object.Witness> _witnessDelayedInsert = new ConcurrentLinkedQueue<>();

    public void delayedWitnessSave(com.disk91.etl.data.object.Witness b) {
        if ( _witnessDelayedInsert.size() > MIN_BEFORE_BATCH_WITNESS_INSERT + 5_000 ) {
            // force wait when more than 10_000 pending
            while (_witnessDelayedInsert.size() > MIN_BEFORE_BATCH_WITNESS_INSERT + 4_000 ) {
                try { Thread.sleep(100); } catch (InterruptedException x) {} ;
            };
        }
        _witnessDelayedInsert.add(b);
    }



    @Scheduled(fixedDelay = 1_000, initialDelay = 5_000)
    public void bulkInsertWitness() {

        while ( _witnessDelayedInsert.size() > MIN_BEFORE_BATCH_WITNESS_INSERT ) {
            // extract the Witnesses to process
            ArrayList<com.disk91.etl.data.object.Witness> _toWriteWitness = new ArrayList<>();
            int toRead = _witnessDelayedInsert.size();
            com.disk91.etl.data.object.Witness w = _witnessDelayedInsert.poll();
            int cnt = 0;
            while ( w != null && cnt < toRead ) {
                _toWriteWitness.add(w);
                w = _witnessDelayedInsert.poll();
                cnt++;
            }

            long start = Now.NowUtcMs();
            // parallel write performance is between 1ms and 2ms per entry
            //_toWriteWitness.parallelStream().forEach(witnessesRepository::save);
            // bulk write performance is between 0.53ms with journal enabled
            mongoTemplate.setWriteConcern(WriteConcern.W1.withJournal(false));
            BulkOperations bulkInsert = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, com.disk91.etl.data.object.Witness.class);
            bulkInsert.insert(_toWriteWitness);
            BulkWriteResult bulkWriteResult = bulkInsert.execute();
            long duration = Now.NowUtcMs() - start;
            int hadToSave = _toWriteWitness.size();
            _toWriteWitness.clear();

            log.warn("bulkInsertWitness saves "+hadToSave+" in "+duration+"ms "+duration/(hadToSave/100.0)+"ms/ 100 units");

            if ( _witnessDelayedInsert.size() > MIN_BEFORE_BATCH_WITNESS_INSERT ) {
                log.warn("bulkInsertWitness going slow ! "+_witnessDelayedInsert.size()+" pending");
                log.warn("bulkInsertWitness saves "+hadToSave+" in "+duration+"ms "+duration/(hadToSave/100.0)+"ms/ 100 units");
            }
        }
    }

    // Wait for end of the async update process
    // then commit the rest of the witnesses
    private boolean flushInsertWitness() {
        // refuse it scheduled process has reason to start
        int loops = 0;
        while ( _witnessDelayedInsert.size() > MIN_BEFORE_BATCH_WITNESS_INSERT && loops < 100 ) {
           try {
               Thread.sleep(500);
           } catch ( InterruptedException x ) {};
           loops++;
        }
        if ( loops < 100) {
            _witnessDelayedInsert.parallelStream().forEach(witnessesRepository::save);
            return true;
        }
        return false;
    }

    // ======================================================
    // Manage IoT PoC
    // ======================================================

    public boolean addIoTPoC(lora_poc_v1 p) {

        // do not proceed twice (try at least)
        if ( p.getBeaconReport().getReceivedTimestamp() < iotpocTopLine.getLongValue() ) return false;
        long start = Now.NowUtcMs();

        // contains POC and Witness information
        lora_valid_beacon_report_v1 beacon = p.getBeaconReport();

        // Update the beaconner
        String hsBeaconerId = HeliumHelper.pubAddressToName(beacon.getReport().getPubKey());
        Hotspot beaconner = this.getHotspot(hsBeaconerId, true);

        if ( (start - beaconner.getPosition().getLastDatePosition()) > 12*Now.ONE_HOUR ) {
            // no need to verify position on every beacon
            if (h3 != null) {
                LatLng pos = h3.cellToLatLng(Long.parseLong(beacon.getLocation()));
                if (pos != null && Gps.isAValidCoordinate(pos.lat, pos.lng) && Gps.distance(beaconner.getPosition().getLat(), pos.lat, beaconner.getPosition().getLng(), pos.lng, 0, 0) > 300) {
                    beaconner.updatePosition(beacon.getReceivedTimestamp(), pos.lat, pos.lng, 0.0, 3.0);
                    log.debug("Position change : " + pos.lat + " / " + pos.lng + " for " + hsBeaconerId);
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


        // Update the Witness information
        for ( lora_verified_witness_report_v1 v : p.getSelectedWitnessesList() ) {
            long wstart = Now.NowUtcMs();
            String witnesserId = HeliumHelper.pubAddressToName(v.getReport().getPubKey());
            Hotspot witnessed = this.getHotspot(witnesserId, true);
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
                    hsBeaconerId,
                    v.getReport().getTimestamp(),
                    v.getReport().getSignal()/10.0,
                    v.getReport().getSnr()/10.0,
                    etlConfig.getHotspotWitnessHistoryEntries(),
                    (beaconner != null)?beaconner.getPosition().getLat():0.0,
                    (beaconner != null)?beaconner.getPosition().getLng():0.0,
                    true
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
        for ( lora_verified_witness_report_v1 v : p.getUnselectedWitnessesList() ) {
            String witnesserId = HeliumHelper.pubAddressToName(v.getReport().getPubKey());
            Hotspot witnessed = this.getHotspot(witnesserId, true);
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
                    hsBeaconerId,
                    v.getReport().getTimestamp(),
                    v.getReport().getSignal()/10.0,
                    v.getReport().getSnr()/10.0,
                    etlConfig.getHotspotWitnessHistoryEntries(),
                    (beaconner != null)?beaconner.getPosition().getLat():0.0,
                    (beaconner != null)?beaconner.getPosition().getLng():0.0,
                    false
            );
            // mark as updated
            this.updateHotspot(witnessed);
        }
        this.updateHotspot(beaconner);

        // Update the invalid witnesses
        if ( ! etlConfig.isWitnessLoadEnable() && etlConfig.isIotpocLoadUnselected() ) {
            for (lora_verified_witness_report_v1 v : p.getUnselectedWitnessesList()) {
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

    public boolean addReward(iot_reward_share r) {
        long start = Now.NowUtcMs();

        String hsId = HeliumHelper.pubAddressToName(r.getGatewayReward().getHotspotKey());
        Hotspot rewarded = this.getHotspot(hsId, true);
        if( r.getStartPeriod()*1000 > rewarded.getLastReward() ) {
            rewarded.updateReward(r.getStartPeriod()*1000,r.getGatewayReward().getBeaconAmount(),r.getGatewayReward().getWitnessAmount(),r.getGatewayReward().getDcTransferAmount());
            this.updateHotspot(rewarded);

            // add the Reward data
            Reward _r = new Reward();
            _r.setHotspotId(hsId);
            _r.setStartPeriod(r.getStartPeriod()*1000);
            _r.setEndPeriod(r.getEndPeriod()*1000);
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

        synchronized (inAsyncWrite) {
            if ( inAsyncWrite == true ) return 0;
            inAsyncWrite = true;
        }
        try {
            if ( _rewardsDelayedInsert.size() > 0 ) {
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
            synchronized (inAsyncWrite) {
                inAsyncWrite = false;
            }
        }

    }


}
