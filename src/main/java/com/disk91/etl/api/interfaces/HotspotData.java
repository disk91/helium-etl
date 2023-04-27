package com.disk91.etl.api.interfaces;

import com.disk91.etl.data.object.Hotspot;
import com.disk91.etl.data.object.sub.BeaconHistory;
import com.disk91.etl.data.object.sub.LatLng;
import com.disk91.etl.data.object.sub.Witness;
import com.disk91.etl.data.object.sub.WitnessHistory;
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
        setOffsetReward(h.getOffsetReward());

        List<LatLng> ph = new ArrayList<>();
        for (LatLng p : h.getPosHistory()) {
            ph.add(p.clone());
        }
        setPosHistory(ph);

        List<Witness> ws = new ArrayList<>();
        for (Witness w : h.getWitnesses()) {
            ws.add(w.clone());
        }
        setWitnesses(ws);

        List<Witness> bs = new ArrayList<>();
        for (Witness w : h.getBeaconned()) {
            bs.add(w.clone());
        }
        setBeaconned(bs);

        List<WitnessHistory> whs = new ArrayList<>();
        for (WitnessHistory wh : witnessesHistory) {
            whs.add(wh.clone());
        }
        setWitnessesHistory(whs);

        List<BeaconHistory> bhs = new ArrayList<>();
        for (BeaconHistory bh : beaconHistory) {
            bhs.add(bh.clone());
        }
        setBeaconHistory(bhs);
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
}
