package com.disk91.etl.data.object;

import com.disk91.etl.data.object.sub.*;
import com.disk91.etl.data.object.sub.Witness;
import fr.ingeniousthings.tools.ClonnableObject;
import fr.ingeniousthings.tools.Now;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Sharded;
import org.springframework.data.mongodb.core.mapping.ShardingStrategy;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "etl_hotspots")
@CompoundIndexes({
        @CompoundIndex(name = "hotspotId", def = "{'hotspotId' : 'hashed'}"),
        @CompoundIndex(name = "animalName", def = "{'animalName' : 'hashed'}"),
        @CompoundIndex(name = "hotspotId_Id", def = "{'hotspotId' : 1, 'id' : 1}")
})
@Sharded(shardKey = { "hotspotId", "id" }, shardingStrategy = ShardingStrategy.RANGE)
public class Hotspot implements ClonnableObject<Hotspot> {

    @Id
    private String id;

    private String hotspotId;
    private String animalName;

    private Owner owner;
    private List<Owner> ownerHistory;

    private LatLng position;

    private List<LatLng> posHistory;
    private int version;

    private long firstSeen = 0;

    private long lastBeacon;

    private long lastWitness;

    private long lastReward;

    private long sumRewardBeacon;
    private long sumRewardWitness;

    private long sumRewardDc;

    private long offsetReward;

    // List of hotspots receiving this hotspot
    private List<Witness> beaconned;

    // List of hotspot this one is receiving
    private List<Witness> witnesses;

    // Quantitiative history
    private List<WitnessHistory> witnessesHistory;

    // Quantitative history
    private List<BeaconHistory> beaconHistory;

    // Reward history (once a day)
    private List<RewardHistory> rewardHistories;

    public enum HotspotBrand {
        UNKNOWN, HELIUM, RAK, NEBRA, KERLINK, COTX, SENSECAP, SYNCROBIT, BOBCAT, LONGAP,
        SMARTMIMIC, CALCHIP, DEWI, PISCES, CLODPI, LINXDOT, CONTROLLINO, HELTEC, FREEDOMFI, PANTHERX,
        HUMMINGBIRD, RISINGHF, BROWAN, MILESIGHT, DEEPER, MIDAS, DRAGINO, PYCOM
    };
    protected HotspotBrand brand = HotspotBrand.UNKNOWN;

    // ---------------------------------------------------------
    // Synchronous update

    synchronized public void updateReward(long tm, long beacon, long witness, long dcs) {
        this.lastReward = tm;
        if ( this.firstSeen == 0 ) this.firstSeen = tm;
        this.sumRewardBeacon += beacon;
        this.sumRewardWitness += witness;
        this.sumRewardDc += dcs;

        long hRef = Now.ThisHourUtc(tm);
        RewardHistory bh = new RewardHistory();
        bh.setTimeRef(hRef);
        bh.setDataReward(dcs);
        bh.setBeaconReward(beacon);
        bh.setWitnessReward(witness);

        // find older one
        long oldest = Now.NowUtcMs();
        for (RewardHistory _bh : this.getRewardHistories()) {
            if (_bh.getTimeRef() < oldest) {
               oldest = _bh.getTimeRef();
            }
        }

        // need to clean an older one ?
        if (this.getRewardHistories().size() > 30) {
            ArrayList<RewardHistory> nl = new ArrayList<>();
            nl.add(bh);
            for (RewardHistory _bh : this.getRewardHistories()) {
                if (_bh.getTimeRef() != oldest) {
                    nl.add(_bh);
                }
            }
            this.setRewardHistories(nl);
        } else {
            this.getRewardHistories().add(bh);
        }
    }



    synchronized public void updatePosition(long timestamp, double lat, double lng, double alt, double gain) {
        LatLng _p = this.getPosition();
        _p.setLastDatePosition(timestamp);
        this.getPosHistory().add(_p.clone());
        _p.setLat(lat);
        _p.setLng(lng);
        _p.setCity("");
        _p.setCountry("");
        _p.setAlt(alt);
        _p.setGain(gain);
    }

    synchronized public void updateLastBeacon(long tm) {
        this.setLastBeacon(tm);
    }

    synchronized public void addBeacon(long tm, int maxHistEntries) {
        long hRef = Now.ThisHourUtc(tm);
        boolean newHist =  ( (tm - this.getLastBeacon() ) > Now.ONE_HOUR ); // can't exist in history
        long oldest = Now.NowUtcMs();
        boolean updated = false;
        this.setLastBeacon(tm);
        if ( ! newHist ) {
            for (BeaconHistory bh : this.getBeaconHistory()) {
                if (bh.getTimeRef() == hRef) {
                    // update
                    bh.setCountBeacon(bh.getCountBeacon() + 1);
                    updated = true;
                    break;
                }
                if (oldest > bh.getTimeRef()) oldest = bh.getTimeRef();
            }
        }
        if (!updated) {
            // need to create a new one
            BeaconHistory bh = new BeaconHistory();
            bh.setTimeRef(hRef);
            bh.setCountBeacon(1);

            // need to clean an older one ?
            if (this.getBeaconHistory().size() > maxHistEntries) {
                ArrayList<BeaconHistory> nl = new ArrayList<>();
                nl.add(bh);
                for (BeaconHistory _bh : this.getBeaconHistory()) {
                    if (_bh.getTimeRef() != oldest) {
                        nl.add(_bh);
                    }
                }
                this.setBeaconHistory(nl);
            } else {
                this.getBeaconHistory().add(bh);
            }
        }
    }

    synchronized public void addBeaconed(String hsId, long tm, double signal, double snr, double lat, double lng, boolean selected) {
        boolean found = false;
        for (Witness _w : this.getBeaconned()) {
            if (_w.getHs().equals(hsId)) {
                // found it... update it
                _w.addWitness( tm, signal, snr, lat, lng, selected );
                found = true;
                break;
            }
        }
        if (!found) {
            // create one
            Witness _w = new Witness();
            _w.init(hsId);
            _w.addWitness( tm, signal, snr, lat, lng, selected );
            this.getBeaconned().add(_w);
        }
    }

    synchronized public void addWitness(String hsId, long tm, double signal, double snr, int maxHistEntries, double lat, double lng, boolean selected){
        long tmMs = tm / 1_000_000; // from nano to ms
        boolean newHist =  ( (tmMs - this.getLastWitness() ) > Now.ONE_HOUR ); // can't exist in history
        this.setLastWitness(tmMs);
        boolean found = false;
        for (Witness _w : this.getWitnesses()) {
            if (_w.getHs().equals(hsId) ) {
                // found it... update it
                _w.addWitness(tm,signal,snr,lat,lng, selected);
                found = true;
                break;
            }
        }
        if (!found) {
            // create one
            Witness _w = new Witness();
            _w.init(hsId);
            _w.addWitness(tm,signal,snr,lat,lng,selected);
            this.getWitnesses().add(_w);
        }

        long hRef = Now.ThisHourUtc(tmMs);
        long oldest = Now.NowUtcMs();
        boolean updated = false;
        if ( ! newHist ) {
            for (WitnessHistory wh : this.getWitnessesHistory()) {
                if (wh.getTimeRef() == hRef) {
                    // update
                    wh.setCountWitnesses(wh.getCountWitnesses() + 1);
                    // selected
                    if ( selected ) wh.setSeletedWitness(wh.getSeletedWitness() + 1);
                    updated = true;
                    break;
                }
                if (oldest > wh.getTimeRef()) oldest = wh.getTimeRef();
            }
        }
        if (!updated) {
            // need to create a new one
            WitnessHistory wh = new WitnessHistory();
            wh.setTimeRef(hRef);
            wh.setCountWitnesses(1);
            if ( selected ) wh.setSeletedWitness(1);

            // need to clean an older one ?
            if (this.getWitnessesHistory().size() > maxHistEntries ) {
                ArrayList<WitnessHistory> nl = new ArrayList<>();
                nl.add(wh);
                for (WitnessHistory _wh : this.getWitnessesHistory()) {
                    if (_wh.getTimeRef() != oldest) {
                        nl.add(_wh);
                    }
                }
                this.setWitnessesHistory(nl);
            } else {
                this.getWitnessesHistory().add(wh);
            }
        }
    }

    // ---

    // Clone the object
    public Hotspot clone() {
        Hotspot c = new Hotspot();
        c.setId(id);
        c.setHotspotId(hotspotId);
        c.setVersion(version);
        c.setFirstSeen(firstSeen);
        c.setLastBeacon(lastBeacon);
        c.setLastWitness(lastWitness);
        c.setAnimalName(animalName);
        c.setPosition(position.clone());
        c.setOwner(owner.clone());
        c.setLastReward(lastReward);
        c.setSumRewardBeacon(sumRewardBeacon);
        c.setSumRewardWitness(sumRewardWitness);
        c.setSumRewardDc(sumRewardDc);
        c.setOffsetReward(offsetReward);
        c.setBrand(brand);

        List<Owner> oh = new ArrayList<>();
        for ( Owner o : ownerHistory ) {
            oh.add(o.clone());
        }
        c.setOwnerHistory(oh);

        List<LatLng> ph = new ArrayList<>();
        for (LatLng p : posHistory) {
            ph.add(p.clone());
        }
        c.setPosHistory(ph);

        List<Witness> ws = new ArrayList<>();
        for (Witness w : witnesses) {
            ws.add(w.clone());
        }
        c.setWitnesses(ws);

        List<Witness> bs = new ArrayList<>();
        for (Witness w : beaconned) {
            bs.add(w.clone());
        }
        c.setBeaconned(bs);

        List<WitnessHistory> whs = new ArrayList<>();
        for (WitnessHistory wh : witnessesHistory) {
            whs.add(wh.clone());
        }
        c.setWitnessesHistory(whs);

        List<BeaconHistory> bhs = new ArrayList<>();
        for (BeaconHistory bh : beaconHistory) {
            bhs.add(bh.clone());
        }
        c.setBeaconHistory(bhs);

        List<RewardHistory> rhs = new ArrayList<>();
        for (RewardHistory rh : rewardHistories) {
            rhs.add(rh.clone());
        }
        c.setRewardHistories(rhs);

        return c;
    }

    // --

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getLastBeacon() {
        return lastBeacon;
    }

    public void setLastBeacon(long lastBeacon) {
        this.lastBeacon = lastBeacon;
    }

    public List<Witness> getWitnesses() {
        return witnesses;
    }

    public void setWitnesses(List<Witness> witnesses) {
        this.witnesses = witnesses;
    }

    public List<WitnessHistory> getWitnessesHistory() {
        return witnessesHistory;
    }

    public void setWitnessesHistory(List<WitnessHistory> witnessesHistory) {
        this.witnessesHistory = witnessesHistory;
    }

    public List<BeaconHistory> getBeaconHistory() {
        return beaconHistory;
    }

    public void setBeaconHistory(List<BeaconHistory> beaconHistory) {
        this.beaconHistory = beaconHistory;
    }

    public long getLastWitness() {
        return lastWitness;
    }

    public void setLastWitness(long lastWitness) {
        this.lastWitness = lastWitness;
    }

    public String getHotspotId() {
        return hotspotId;
    }

    public void setHotspotId(String hotspotId) {
        this.hotspotId = hotspotId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public List<Witness> getBeaconned() {
        return beaconned;
    }

    public void setBeaconned(List<Witness> beaconned) {
        this.beaconned = beaconned;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public LatLng getPosition() {
        return position;
    }

    public void setPosition(LatLng position) {
        this.position = position;
    }

    public List<LatLng> getPosHistory() {
        return posHistory;
    }

    public void setPosHistory(List<LatLng> posHistory) {
        this.posHistory = posHistory;
    }

    public long getLastReward() {
        return lastReward;
    }

    public void setLastReward(long lastReward) {
        this.lastReward = lastReward;
    }

    public long getSumRewardBeacon() {
        return sumRewardBeacon;
    }

    public void setSumRewardBeacon(long sumRewardBeacon) {
        this.sumRewardBeacon = sumRewardBeacon;
    }

    public long getSumRewardWitness() {
        return sumRewardWitness;
    }

    public void setSumRewardWitness(long sumRewardWitness) {
        this.sumRewardWitness = sumRewardWitness;
    }

    public long getOffsetReward() {
        return offsetReward;
    }

    public void setOffsetReward(long offsetReward) {
        this.offsetReward = offsetReward;
    }

    public long getSumRewardDc() {
        return sumRewardDc;
    }

    public void setSumRewardDc(long sumRewardDc) {
        this.sumRewardDc = sumRewardDc;
    }

    public long getFirstSeen() {
        return firstSeen;
    }

    public void setFirstSeen(long firstSeen) {
        this.firstSeen = firstSeen;
    }

    public List<RewardHistory> getRewardHistories() {
        return rewardHistories;
    }

    public void setRewardHistories(List<RewardHistory> rewardHistories) {
        this.rewardHistories = rewardHistories;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public List<Owner> getOwnerHistory() {
        return ownerHistory;
    }

    public void setOwnerHistory(List<Owner> ownerHistory) {
        this.ownerHistory = ownerHistory;
    }

    public HotspotBrand getBrand() {
        return brand;
    }

    public void setBrand(HotspotBrand brand) {
        this.brand = brand;
    }
}
