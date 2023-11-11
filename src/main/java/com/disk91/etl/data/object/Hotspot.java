package com.disk91.etl.data.object;

import com.disk91.etl.data.object.sub.*;
import com.disk91.etl.data.object.sub.Witness;
import fr.ingeniousthings.tools.ClonnableObject;
import fr.ingeniousthings.tools.Gps;
import fr.ingeniousthings.tools.HeliumHelper;
import fr.ingeniousthings.tools.Now;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Sharded;
import org.springframework.data.mongodb.core.mapping.ShardingStrategy;

import java.util.*;

@Document(collection = "etl_hotspots")
@CompoundIndexes({
        @CompoundIndex(name = "hotspotId", def = "{'hotspotId' : 'hashed'}"),
        @CompoundIndex(name = "animalName", def = "{'animalName' : 'hashed'}"),
        @CompoundIndex(name = "lastIndexed", def = "{'lastIndexed' : 1}"),
        @CompoundIndex(name = "hotspotId_Id", def = "{'hotspotId' : 1, 'id' : 1}")
})
@Sharded(shardKey = { "hotspotId", "id" }, shardingStrategy = ShardingStrategy.RANGE)
public class Hotspot implements ClonnableObject<Hotspot> {

    @Transient
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Id
    private String id;

    private String hotspotId;
    private String animalName;

    private Owner owner;
    private List<Owner> ownerHistory;

    private LatLng position;

    private List<LatLng> posHistory;

    private boolean inDenyList;

    private List<DenyHistory> denyHistories;

    private int version;

    private long lastIndexed = 0;

    private long firstSeen = 0;

    private long lastBeacon;

    private long lastWitness;

    private long lastDataReward;

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

    protected double beaconDist = 0.0;
    protected double witnessDist = 0.0;

    // ---------------------------------------------------------
    // Synchronous update

    synchronized public void updateOwner(String solOwner, String hntOwner, long time) {
        if ( solOwner == null && hntOwner == null ) return;
        if ( solOwner == null ) {
            // compute from hntOwner
            solOwner = HeliumHelper.solanaAddress(HeliumHelper.nameToPubAddress(hntOwner));
        } else if (hntOwner == null ) {
            hntOwner = HeliumHelper.pubAddressToName(HeliumHelper.solanaToPubAddress(solOwner));
        }
        if (this.owner != null && this.owner.getSolOwner().length() > 2) {
            if (this.owner.getSolOwner().compareTo(solOwner) != 0) {
                // different owner - save the previous one
                if (this.ownerHistory == null) this.ownerHistory = new ArrayList<>();
                this.ownerHistory.add(this.owner);
            } else {
                // same owner
                return;
            }
        }
        this.owner = new Owner();
        this.owner.setHntOwner(hntOwner);
        this.owner.setSolOwner(solOwner);
        if ( time == 0 ) time = Now.NowUtcMs();
        this.owner.setTimeMs(time);
    }

    synchronized public void updateReward(long tm, long beacon, long witness, long dcs) {
        this.lastReward = tm;
        if ( this.firstSeen == 0 ) this.firstSeen = tm;
        this.sumRewardBeacon += beacon;
        this.sumRewardWitness += witness;
        this.sumRewardDc += dcs;

        if ( dcs > 0 ) {
            this.lastDataReward = tm;
        }

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



    synchronized public void updatePosition(long timestamp, double lat, double lng, double alt, double gain, double hexScale) {
        int sizeLimit = 15;
        if ( this.position != null ) {
            this.position.setLastDatePosition(timestamp);
            // limit the position & info update to the last position change and the last value updates
            if ( posHistory.size() < sizeLimit ) {
                this.getPosHistory().add(this.position.clone());
            } else {
                // sort List by date, this sort from oldest position to newest
                this.posHistory.sort(new Comparator<LatLng>() {
                    @Override
                    public int compare(LatLng p1, LatLng p2) {
                        return (int) (p1.getLastDatePosition() - p2.getLastDatePosition());
                    }
                });
                // to Remove
                ArrayList<LatLng> newList = new ArrayList<>();
                int toRem = posHistory.size() - (sizeLimit-1);
                if ( toRem > 0 ) {
                    LatLng previous = null;
                    for ( LatLng p : this.posHistory ) {
                        if ( previous == null ) {
                            // keep the first
                            newList.add(p);
                        } else {
                            if ( Gps.distance(p.getLat(),previous.getLat(), p.getLng(), previous.getLng(), 0, 0) > 1000 ) {
                                // keep all the Hotspot movements
                                newList.add(p);
                            } else {
                                // remove the other old modification
                                // then keep the most recent up to table limit
                                if ( toRem == 0 ) {
                                    newList.add(p);
                                } else {
                                    // skip that one
                                    toRem--;
                                }
                            }
                        }
                        previous = p;
                    }
                    // add the new position
                    newList.add(this.position.clone());
                    this.posHistory = newList;
                }
            }
        } else {
            this.position = new LatLng();
        }
        if ( Gps.distance(this.position.getLat(),lat, this.position.getLng(), lng, 0, 0) > 1000 ) {
            // this is a new position, city/country can have changed
            this.position.setCity("");
            this.position.setCountry("");
        }
        this.position.setLat(lat);
        this.position.setLng(lng);
        this.position.setAlt(alt);
        this.position.setGain(gain);
        this.position.setHexScale(hexScale);
        // try to enrich position with past data (mostly to fix a previous change that make them lost in the history)
        if ( this.position.getCity().length() == 0 ) {
            for (LatLng p : this.posHistory) {
                if ( p.getCity().length() > 0 ) {
                    if ( Gps.distance(this.position.getLat(),p.getLat(), this.position.getLng(), p.getLng(), 0, 0) < 1000 ) {
                        this.position.setCity(p.getCity());
                        this.position.setCountry(p.getCountry());
                        break;
                    }
                }
            }
        }

    }

    synchronized public void updateDeny(long timestamp, boolean isDenied) {
        if ( this.inDenyList != isDenied ) {
            DenyHistory _d = new DenyHistory();
            _d.setTimestamp(timestamp/1_000_000); // back to ms
            _d.setWasInDenyList(this.inDenyList);
            if ( this.denyHistories.size() > 25 ) {
                // clean the previous list becoming too long
                // unclear yet why the status is switching
                ArrayList<DenyHistory> _denyHist = new ArrayList<>();
                for ( int i = this.denyHistories.size()-24 ; i < this.denyHistories.size() ; i++ ) {
                    DenyHistory _dh = this.denyHistories.get(i);
                    _dh.setTimestamp(_d.getTimestamp()/1_000_000); // convert to ms
                    _denyHist.add(_dh);
                }
                this.denyHistories = _denyHist;
            }
            this.denyHistories.add(_d);
            // keep only the last 25 only
            if ( this.denyHistories.size() > 25 ) {
                this.denyHistories = this.denyHistories.subList(this.denyHistories.size()-25, this.denyHistories.size());
            }
            this.inDenyList = isDenied;
        }
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
                // update TX overage radius when we have enough beacon for that hostpot
                if ( _w.getCountWitnesses() > 50 ) {
                    double d = Gps.distance(_w.getLat(),lat,_w.getLng(),lng,0,0);
                    if ( d > this.beaconDist ) this.beaconDist = d;
                }
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
                // update TX overage radius when we have enough beacon for that hostpot
                if ( _w.getCountWitnesses() > 50 ) {
                    double d = Gps.distance(_w.getLat(),lat,_w.getLng(),lng,0,0);
                    if ( d > this.witnessDist ) this.witnessDist = d;
                }
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
        c.setInDenyList(inDenyList);
        c.setLastDataReward(lastDataReward);
        c.setBeaconDist(beaconDist);
        c.setWitnessDist(witnessDist);

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

        List<DenyHistory> dhs = new ArrayList<>();
        for (DenyHistory dh : denyHistories) {
            dhs.add(dh.clone());
        }
        c.setDenyHistories(dhs);

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

    public boolean isInDenyList() {
        return inDenyList;
    }

    public void setInDenyList(boolean inDenyList) {
        this.inDenyList = inDenyList;
    }

    public List<DenyHistory> getDenyHistories() {
        return denyHistories;
    }

    public void setDenyHistories(List<DenyHistory> denyHistories) {
        this.denyHistories = denyHistories;
    }

    public long getLastDataReward() {
        return lastDataReward;
    }

    public void setLastDataReward(long lastDataReward) {
        this.lastDataReward = lastDataReward;
    }

    public double getBeaconDist() {
        return beaconDist;
    }

    public void setBeaconDist(double beaconDist) {
        this.beaconDist = beaconDist;
    }

    public double getWitnessDist() {
        return witnessDist;
    }

    public void setWitnessDist(double witnessDist) {
        this.witnessDist = witnessDist;
    }

    public long getLastIndexed() {
        return lastIndexed;
    }

    public void setLastIndexed(long lastIndexed) {
        this.lastIndexed = lastIndexed;
    }
}
