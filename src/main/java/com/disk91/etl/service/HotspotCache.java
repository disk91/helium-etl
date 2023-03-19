package com.disk91.etl.service;

import com.disk91.etl.EtlConfig;
import com.disk91.etl.data.object.Beacon;
import com.disk91.etl.data.object.Hotspot;
import com.disk91.etl.data.object.Param;
import com.disk91.etl.data.object.sub.BeaconHistory;
import com.disk91.etl.data.object.sub.Witness;
import com.disk91.etl.data.object.sub.WitnessHistory;
import com.disk91.etl.data.repository.BeaconsRepository;
import com.disk91.etl.data.repository.HotspotsRepository;
import com.disk91.etl.data.repository.ParamRepository;
import com.disk91.etl.data.repository.WitnessesRepository;
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

    @PostConstruct
    private void initHotspotCacheService() {
        this.heliumHotspotCache = new ObjectCache<String, Hotspot>(
                "HotspotCache",
                500_000,
                24*Now.ONE_HOUR
        ) {
            @Override
            public void onCacheRemoval(String key, Hotspot obj) {
                Hotspot o = hotspotsRepository.save(obj);
                if ( obj.getId() == null ) obj.setId(o.getId());
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
        this.serviceEnable = true;
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
        this.heliumHotspotCache.flush();
        this.flushTopLines();
        this.serviceEnable = false;
    }
    public boolean hasStopped() {
        return (this.serviceEnable == false && this.runningJobs == 0);
    }


    @Autowired
    protected HotspotsRepository hotspotsRepository;

    public Hotspot getHotspot(String hotspotId, boolean cache) {
        Hotspot hs = heliumHotspotCache.get(hotspotId);
        if ( hs == null ) {
            hs = hotspotsRepository.findOneHotspotByHotspotId(hotspotId);
            if ( hs == null ) {
                // create it
                hs = new Hotspot();
                hs.setHotspotId(hotspotId);
                hs.setWitnesses(new ArrayList<>());
                hs.setBeaconHistory(new ArrayList<>());
                hs.setWitnessesHistory(new ArrayList<>());
                hs.setLastBeacon(0);
                hs.setLastWitness(0);
                hs.setVersion(1);
            }
            if ( cache && hs != null ) heliumHotspotCache.put(hs,hotspotId);
        }
        return hs;
    }

    public int modifications = 0;
    public void updateHotspot(Hotspot o) {
        heliumHotspotCache.put(o,o.getHotspotId());
        modifications++;
        if ( modifications > 1000 ) {
            heliumHotspotCache.commit();
            modifications = 0;
        }
    }

    // ---------------------------
    // Manage how we update the data into the hotspot elements


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

        String hsId = HeliumHelper.pubAddressToName(b.getReport().getPubKey());
        Hotspot h = this.getHotspot(hsId,true);
        h.setLastBeacon(b.getReceivedTimestamp());  // oracle reception time
        long hRef = Now.ThisHourUtc(b.getReceivedTimestamp());

        long oldest = Now.NowUtcMs();
        boolean updated = false;
        for (BeaconHistory bh :  h.getBeaconHistory()) {
            if ( bh.getTimeRef() == hRef ) {
                // update
                bh.setCountBeacon(bh.getCountBeacon()+1);
                updated = true;
                break;
            }
            if ( oldest > bh.getTimeRef() ) oldest = bh.getTimeRef();
        }
        if ( ! updated ) {
            // need to create a new one
            BeaconHistory bh = new BeaconHistory();
            bh.setTimeRef(hRef);
            bh.setCountBeacon(1);

            // need to clean an older one ?
            if ( h.getBeaconHistory().size() > etlConfig.getHotspotBeaconHistoryEntries() ) {
                ArrayList<BeaconHistory> nl = new ArrayList<>();
                nl.add(bh);
                for (BeaconHistory _bh :  h.getBeaconHistory()) {
                    if ( _bh.getTimeRef() != oldest ) {
                        nl.add(_bh);
                    }
                }
                h.setBeaconHistory(nl);
            }
        }
        // mark as updated
        this.updateHotspot(h);

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
        beaconsRepository.save(be);

        // Add in cache for witness search
        beaconROCache.addBeacon(be);
        if ( b.getReceivedTimestamp() > this.beaconTopTs ) {
            this.beaconTopTs = b.getReceivedTimestamp();
        }
        return true;
    }

    @Autowired
    protected WitnessesRepository witnessesRepository;

    public boolean addWitness(lora_witness_ingest_report_v1 w) {

        // already process
        if ( w.getReceivedTimestamp() < witnessTopLine.getLongValue() ) return false;

        String hsId = HeliumHelper.pubAddressToName(w.getReport().getPubKey());
        Hotspot h = this.getHotspot(hsId,true);
        h.setLastWitness(w.getReceivedTimestamp());
        long hRef = Now.ThisHourUtc(w.getReceivedTimestamp());

        // Search the related beacon
        String dataId = HexaConverters.byteToHexString(w.getReport().getData().toByteArray());
        Beacon b = beaconROCache.getBeacon(dataId, w.getReport().getTimestamp());
        if ( b != null && (w.getReport().getTimestamp() - b.getTimestamp()) > 10_000_000_000L) {
            // sounds good
            // find the hotspot in the witnesser
            boolean found = false;
            for ( Witness _w : h.getWitnesses() ) {
                if ( _w.getHs().compareTo(b.getHotspotId()) == 0 ) {
                    // found it... update it
                    _w.addWitness(
                            w.getReport().getTimestamp(),
                            w.getReport().getSignal(),
                            w.getReport().getSnr()
                    );
                    found = true;
                    break;
                }
            }
            if ( !found ) {
                // create one
                Witness _w = new Witness();
                _w.init(b.getHotspotId());
                _w.addWitness(
                        w.getReport().getTimestamp(),
                        w.getReport().getSignal(),
                        w.getReport().getSnr()
                );
                h.getWitnesses().add(_w);
            }
        } else {
            log.debug("Impossible to find the beaconer for witness "+w.getReceivedTimestamp());
        }

        long oldest = Now.NowUtcMs();
        boolean updated = false;
        for (WitnessHistory wh :  h.getWitnessesHistory()) {
            if ( wh.getTimeRef() == hRef ) {
                // update
                wh.setCountWitnesses(wh.getCountWitnesses()+1);
                updated = true;
                break;
            }
            if ( oldest > wh.getTimeRef() ) oldest = wh.getTimeRef();
        }
        if ( ! updated ) {
            // need to create a new one
            WitnessHistory wh = new WitnessHistory();
            wh.setTimeRef(hRef);
            wh.setCountWitnesses(1);

            // need to clean an older one ?
            if ( h.getWitnessesHistory().size() > etlConfig.getHotspotWitnessHistoryEntries() ) {
                ArrayList<WitnessHistory> nl = new ArrayList<>();
                nl.add(wh);
                for (WitnessHistory _wh :  h.getWitnessesHistory()) {
                    if ( _wh.getTimeRef() != oldest ) {
                        nl.add(_wh);
                    }
                }
                h.setWitnessesHistory(nl);
            }
        }
        // mark as updated
        this.updateHotspot(h);

        // add a line in global storage
        com.disk91.etl.data.object.Witness wi = new com.disk91.etl.data.object.Witness();
        wi.setHotspotId(hsId);
        if ( b != null ) wi.setBeaconId(b.getId());
        else wi.setBeaconId("Unknown");
        wi.setVersion(1);
        wi.setData(dataId);
        wi.setSignal(w.getReport().getSignal());
        wi.setSnr(w.getReport().getSnr());
        wi.setDatarate(w.getReport().getDatarate().getNumber());
        wi.setFrequency(w.getReport().getFrequency());
        wi.setTimestamp(w.getReport().getTimestamp());
        wi.setTmst(w.getReport().getTmst());
        witnessesRepository.save(wi);
        if ( w.getReceivedTimestamp() > this.witnessTopTs ) {
            this.witnessTopTs = w.getReceivedTimestamp();
        }
        return true;
    }


    public void flushTopLines() {
        // remove 10m to the max as the data are not always growing
        beaconTopLine.setLongValue(this.beaconTopTs-600_000);
        witnessTopLine.setLongValue(this.witnessTopTs-600_000);

        paramRepository.save(beaconTopLine);
        paramRepository.save(witnessTopLine);
    }

}
