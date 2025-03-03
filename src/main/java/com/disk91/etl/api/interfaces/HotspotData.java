package com.disk91.etl.api.interfaces;

import com.disk91.etl.data.object.Hotspot;
import com.disk91.etl.data.object.sub.*;
import fr.ingeniousthings.tools.ClonnableObject;
import fr.ingeniousthings.tools.Now;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Sharded;
import org.springframework.data.mongodb.core.mapping.ShardingStrategy;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Hotspot Data", description = "Information related to an hotspot")
public class HotspotData {

    @Schema(
            description = "Hexstring Base58 of the Hotspot public key, aka Hs address",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String hotspotId;

    @Schema(
            description = "Animal name of the hostpot with - between words",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String animalName;

    @Schema(
            description = "Hotspot Owner address",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Owner owner;

    @Schema(
            description = "Hotspot Owner history",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private List<Owner> ownerHistory;

    @Schema(
            description = "Hotspot position, lat / lng, can be 0,0",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private LatLng position;


    @Schema(
            description = "Historical position change for that hotspot",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private List<LatLng> posHistory;

    @Schema(
            description = "Timestamp ms / utc, First time a hotspot received rewards - all old initialized on April 19th 2023",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private long firstSeen = 0;

    @Schema(
            description = "Timestamp ms / utc, Last Time this hotspot had a beacon",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private long lastBeacon;

    @Schema(
            description = "Timestamp ms / utc, Last time this hotspot get a witness",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private long lastWitness;

    @Schema(
            description = "Timestamp ms / utc, Last Time this hotspot get a reward (once a day)",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private long lastReward;


    @Schema(
            description = "Total income since April 19th for beacon activities (in IoT Bones)",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private long sumRewardBeacon;
    @Schema(
            description = "Total income since April 19th for witness activities (in IoT bones)",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private long sumRewardWitness;

    @Schema(
            description = "Total income since April 19th for Dcs activities (in IoT bones)",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private long sumRewardDc;

    @Schema(
            description = "Total income since 2025 Jan for beacon activities (in HNT Bones)",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private long sumRewardHntBeacon;

    @Schema(
            description = "Total income since 2025 Jan for witness activities (in HNT bones)",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private long sumRewardHntWitness;
    @Schema(
            description = "Total income since 2025 Jan for Dcs activities (in HNT bones)",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private long sumRewardHntDc;


    @Schema(
            description = "Previous hostpot rewards (in HNT)",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private long offsetReward;

    @Schema(
            description = "List of hotspots beaconed by this hotspot (receiving it)",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private List<Witness> beaconned;

    @Schema(
            description = "List of hotspots witnessed by this hotspot (received by it)",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private List<Witness> witnesses;

    @Schema(
            description = "Witness history, quantitative, per hour",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private List<WitnessHistory> witnessesHistory;

    @Schema(
            description = "Beacon history, quantitative, per hour",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private List<BeaconHistory> beaconHistory;

    @Schema(
            description = "Rewards history, quantitative, per hour, for real, once a day",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private List<RewardHistory> rewardHistories;

    @Schema(
            description = "Hotspot Brand",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Hotspot.HotspotBrand brand;

    @Schema(
        description = "True when the hotspot is in denyList",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private boolean inDenyList;

    @Schema(
        description = "History of denyList in & out",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private List<DenyHistory> denyHistories;


    @Schema(
        description = "Beaconning Distance",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    protected double beaconDist;

    @Schema(
        description = "Witnessing Distance",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    protected double witnessDist;

    @Schema(
        description = "Last Witnessing 10 error cause",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    protected List<Integer> lastErrorCause;


    // ---------------------------------------------------------
    // clone

    public void initFrom(Hotspot h) {
        setHotspotId(h.getHotspotId());
        setFirstSeen(h.getFirstSeen());
        setLastBeacon(h.getLastBeacon());
        setLastWitness(h.getLastWitness());
        setAnimalName(h.getAnimalName());
        setPosition(h.getPosition().clone());
        setLastReward(h.getLastReward());
        setSumRewardBeacon(h.getSumRewardBeacon());
        setSumRewardWitness(h.getSumRewardWitness());
        setSumRewardDc(h.getSumRewardDc());
        setSumRewardHntBeacon(h.getSumRewardHntBeacon());
        setSumRewardHntWitness(h.getSumRewardHntWitness());
        setSumRewardHntDc(h.getSumRewardHntDc());
        setOffsetReward(h.getOffsetReward());
        setInDenyList(h.isInDenyList());
        setBeaconDist(h.getBeaconDist());
        setWitnessDist(h.getWitnessDist());

        if ( h.getLastErrorCause() != null ) {
            setLastErrorCause(new ArrayList<>(h.getLastErrorCause()));
        }

        if ( h.getOwner() != null ) {
            setOwner(h.getOwner().clone());
        } else setOwner(new Owner());
        setBrand(h.getBrand());

        List<Owner> oh = new ArrayList<>();
        if ( h.getOwnerHistory() != null ) {
            for ( Owner o : h.getOwnerHistory() ) {
                oh.add(o.clone());
            }
        }
        setOwnerHistory(oh);

        List<LatLng> ph = new ArrayList<>();
        if ( h.getPosHistory() != null ) {
            for (LatLng p : h.getPosHistory()) {
                ph.add(p.clone());
            }
        }
        setPosHistory(ph);

        List<Witness> ws = new ArrayList<>();
        if ( h.getWitnesses() != null ) {
            for (Witness w : h.getWitnesses()) {
                ws.add(w.clone());
            }
        }
        setWitnesses(ws);

        List<Witness> bs = new ArrayList<>();
        if ( h.getBeaconned() != null ) {
            for (Witness w : h.getBeaconned()) {
                bs.add(w.clone());
            }
        }
        setBeaconned(bs);

        List<WitnessHistory> whs = new ArrayList<>();
        if ( h.getWitnessesHistory() != null ) {
            for (WitnessHistory wh : h.getWitnessesHistory()) {
                whs.add(wh.clone());
            }
        }
        setWitnessesHistory(whs);

        List<BeaconHistory> bhs = new ArrayList<>();
        if ( h.getBeaconHistory() != null ) {
            for (BeaconHistory bh : h.getBeaconHistory()) {
                bhs.add(bh.clone());
            }
        }
        setBeaconHistory(bhs);

        List<RewardHistory> rhs = new ArrayList<>();
        if ( h.getRewardHistories() != null ) {
            for (RewardHistory bh : h.getRewardHistories()) {
                rhs.add(bh.clone());
            }
        }
        setRewardHistories(rhs);

        List<DenyHistory> dhs =  new ArrayList<>();
        if ( h.getDenyHistories() != null ) {
            for ( DenyHistory dh : h.getDenyHistories() ) {
                dhs.add(dh.clone());
            }
        }
        setDenyHistories(dhs);

    }


    // -------------------
    // Getter & Setters

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

    public long getFirstSeen() {
        return firstSeen;
    }

    public void setFirstSeen(long firstSeen) {
        this.firstSeen = firstSeen;
    }

    public long getSumRewardDc() {
        return sumRewardDc;
    }

    public void setSumRewardDc(long sumRewardDc) {
        this.sumRewardDc = sumRewardDc;
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

    public Hotspot.HotspotBrand getBrand() {
        return brand;
    }

    public void setBrand(Hotspot.HotspotBrand brand) {
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

    public List<Integer> getLastErrorCause() {
        return lastErrorCause;
    }

    public void setLastErrorCause(List<Integer> lastErrorCause) {
        this.lastErrorCause = lastErrorCause;
    }

    public long getSumRewardHntBeacon() {
        return sumRewardHntBeacon;
    }

    public void setSumRewardHntBeacon(long sumRewardHntBeacon) {
        this.sumRewardHntBeacon = sumRewardHntBeacon;
    }

    public long getSumRewardHntWitness() {
        return sumRewardHntWitness;
    }

    public void setSumRewardHntWitness(long sumRewardHntWitness) {
        this.sumRewardHntWitness = sumRewardHntWitness;
    }

    public long getSumRewardHntDc() {
        return sumRewardHntDc;
    }

    public void setSumRewardHntDc(long sumRewardHntDc) {
        this.sumRewardHntDc = sumRewardHntDc;
    }
}
