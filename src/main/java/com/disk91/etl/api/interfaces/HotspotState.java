package com.disk91.etl.api.interfaces;

import com.disk91.etl.data.object.Hotspot;
import com.disk91.etl.data.object.sub.*;
import fr.ingeniousthings.tools.Gps;
import fr.ingeniousthings.tools.Now;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Hotspot Sate", description = "Information related to an hotspot (summarized)")
public class HotspotState {

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
            description = "Hotspot position, lat / lng, can be 0,0",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private LatLng position;


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
        description = "Number of beaconner for this hotspot",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private int beaconned;

    @Schema(
        description = "Number of witnesser for this hotspot",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private int witnessed;

    @Schema(
        description = "Max distance for RX seen",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private long maxRxDistance;

    @Schema(
        description = "Max distance for TX seen",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private long maxTxDistance;

    @Schema(
        description = "Max RX budget link seen",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private long maxRxBudgetLinkDB;


    @Schema(
            description = "Witness history, quantitative, per hour, max 48h",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private List<WitnessHistory> witnessesHistory;


    @Schema(
            description = "Rewards history, quantitative, per hour, for real, once a day, 5 days",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private List<RewardHistory> rewardHistories;

    @Schema(
            description = "Hotspot Brand",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Hotspot.HotspotBrand brand;

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
        if ( h.getOwner() != null ) {
            setOwner(h.getOwner().clone());
        } else setOwner(new Owner());
        setBrand(h.getBrand());

        // max Rx distance => max distance from witnessed
        if ( h.getWitnesses() != null ) {
            setWitnessed(h.getWitnesses().size());
            long d = 0;
            long budget = 0;
            for (Witness w : h.getWitnesses()) {
                if ( h.getPosition() != null && Gps.isAValidCoordinate(w.getLat(), w.getLng()) && Gps.isAValidCoordinate(h.getPosition().getLat(),h.getPosition().getLng()) ) {
                    double _d = Gps.distance(h.getPosition().getLat(), w.getLat(), h.getPosition().getLng(), w.getLng(),0,0);
                    if ( _d > d ) d = (long)_d;
                }
                long _budget = (long)(-w.getLastRssi() + w.getLastSnr());
                if ( _budget > budget ) budget = _budget;
            }
            this.maxRxDistance = d;
            this.maxRxBudgetLinkDB = budget;
        } else {
            setWitnessed(0);
        }

        // max TX distance
        if ( h.getBeaconned() != null ) {
            setBeaconned(h.getBeaconned().size());
            long d = 0;
            for (Witness w : h.getBeaconned()) {
                if ( h.getPosition() != null && Gps.isAValidCoordinate(w.getLat(), w.getLng()) && Gps.isAValidCoordinate(h.getPosition().getLat(),h.getPosition().getLng()) ) {
                    double _d = Gps.distance(h.getPosition().getLat(), w.getLat(), h.getPosition().getLng(), w.getLng(),0,0);
                    if ( _d > d ) d = (long)_d;
                }
            }
            this.maxTxDistance = d;
        }

        List<WitnessHistory> whs = new ArrayList<>();
        if ( h.getWitnessesHistory() != null ) {
            for (WitnessHistory wh : h.getWitnessesHistory()) {
                if ( wh.getTimeRef() > (Now.NowUtcMs() - 2*Now.ONE_FULL_DAY) ) {
                    whs.add(wh.clone());
                }
            }
        }
        setWitnessesHistory(whs);

        List<RewardHistory> rhs = new ArrayList<>();
        if ( h.getRewardHistories() != null ) {
            for (RewardHistory bh : h.getRewardHistories()) {
                if ( bh.getTimeRef() > (Now.NowUtcMs() - 5*Now.ONE_FULL_DAY) ) {
                    rhs.add(bh.clone());
                }
            }
        }
        setRewardHistories(rhs);

        if ( h.getLastErrorCause() != null ) {
            setLastErrorCause(new ArrayList<>(h.getLastErrorCause()));
        } else setLastErrorCause(new ArrayList<>());

    }


    // -------------------
    // Getter & Setters


    public String getHotspotId() {
        return hotspotId;
    }

    public void setHotspotId(String hotspotId) {
        this.hotspotId = hotspotId;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public LatLng getPosition() {
        return position;
    }

    public void setPosition(LatLng position) {
        this.position = position;
    }

    public long getFirstSeen() {
        return firstSeen;
    }

    public void setFirstSeen(long firstSeen) {
        this.firstSeen = firstSeen;
    }

    public long getLastBeacon() {
        return lastBeacon;
    }

    public void setLastBeacon(long lastBeacon) {
        this.lastBeacon = lastBeacon;
    }

    public long getLastWitness() {
        return lastWitness;
    }

    public void setLastWitness(long lastWitness) {
        this.lastWitness = lastWitness;
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

    public long getSumRewardDc() {
        return sumRewardDc;
    }

    public void setSumRewardDc(long sumRewardDc) {
        this.sumRewardDc = sumRewardDc;
    }

    public long getOffsetReward() {
        return offsetReward;
    }

    public void setOffsetReward(long offsetReward) {
        this.offsetReward = offsetReward;
    }

    public int getBeaconned() {
        return beaconned;
    }

    public void setBeaconned(int beaconned) {
        this.beaconned = beaconned;
    }

    public int getWitnessed() {
        return witnessed;
    }

    public void setWitnessed(int witnessed) {
        this.witnessed = witnessed;
    }

    public long getMaxRxDistance() {
        return maxRxDistance;
    }

    public void setMaxRxDistance(long maxRxDistance) {
        this.maxRxDistance = maxRxDistance;
    }

    public long getMaxTxDistance() {
        return maxTxDistance;
    }

    public void setMaxTxDistance(long maxTxDistance) {
        this.maxTxDistance = maxTxDistance;
    }

    public long getMaxRxBudgetLinkDB() {
        return maxRxBudgetLinkDB;
    }

    public void setMaxRxBudgetLinkDB(long maxRxBudgetLinkDB) {
        this.maxRxBudgetLinkDB = maxRxBudgetLinkDB;
    }

    public List<WitnessHistory> getWitnessesHistory() {
        return witnessesHistory;
    }

    public void setWitnessesHistory(List<WitnessHistory> witnessesHistory) {
        this.witnessesHistory = witnessesHistory;
    }

    public List<RewardHistory> getRewardHistories() {
        return rewardHistories;
    }

    public void setRewardHistories(List<RewardHistory> rewardHistories) {
        this.rewardHistories = rewardHistories;
    }

    public Hotspot.HotspotBrand getBrand() {
        return brand;
    }

    public void setBrand(Hotspot.HotspotBrand brand) {
        this.brand = brand;
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
