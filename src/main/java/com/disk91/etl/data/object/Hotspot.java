package com.disk91.etl.data.object;

import com.disk91.etl.data.object.sub.BeaconHistory;
import com.disk91.etl.data.object.sub.LatLng;
import com.disk91.etl.data.object.sub.Witness;
import com.disk91.etl.data.object.sub.WitnessHistory;
import fr.ingeniousthings.tools.ClonnableObject;
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
        @CompoundIndex(name = "hotspotId_Id", def = "{'hotspotId' : 1, 'id' : 1}")
})
@Sharded(shardKey = { "hotspotId", "id" }, shardingStrategy = ShardingStrategy.RANGE)
public class Hotspot implements ClonnableObject<Hotspot> {

    @Id
    private String id;

    private String hotspotId;
    private String animalName;

    private LatLng position;

    private List<LatLng> posHistory;
    private int version;

    private long lastBeacon;
    private long lastWitness;

    // List of hotspots receiving this hotspot
    private List<Witness> beaconned;

    // List of hotspot this one is receiving
    private List<Witness> witnesses;

    // Quantitiative history
    private List<WitnessHistory> witnessesHistory;

    // Quantitative history
    private List<BeaconHistory> beaconHistory;

    // ---

    // Clone the object
    public Hotspot clone() {
        Hotspot c = new Hotspot();
        c.setId(id);
        c.setHotspotId(hotspotId);
        c.setVersion(version);
        c.setLastBeacon(lastBeacon);
        c.setLastWitness(lastWitness);
        c.setAnimalName(animalName);
        c.setPosition(position.clone());

        List<LatLng> ph = new ArrayList<>();
        for (LatLng p : posHistory) {
            ph.add(p.clone());
        }

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
}
