package com.disk91.etl.data.object;


import fr.ingeniousthings.tools.ClonnableObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Sharded;
import org.springframework.data.mongodb.core.mapping.ShardingStrategy;

@Document(collection = "etl_rewards")
@CompoundIndexes({
        @CompoundIndex(name = "hotspotId", def = "{'hotspotId' : 'hashed'}"),
        @CompoundIndex(name = "hotspotId_Id", def = "{'hotspotId' : 1, 'id' : 1}")
})
@Sharded(shardKey = { "hotspotId", "id" }, shardingStrategy = ShardingStrategy.RANGE)
public class Reward implements ClonnableObject<Reward> {

    /*
          /// Public key of the hotspot
          bytes hotspot_key = 1;
          /// Amount credited to the hotspot for beaconing
          uint64 beacon_amount = 2;
          /// Amount credited to the hotspot for witnessing
          uint64 witness_amount = 3;
          /// Unix timestamp in seconds of the start of the reward period
          uint64 start_period = 4;
          /// Unix timestamp in seconds of the end of the reward period
          uint64 end_period = 5;
    */

    @Id
    private String id;
    private int version;
    private String hotspotId;

    private long startPeriod;
    private long endPeriod;

    private long beaconAmount;
    private long witnessAmount;


    public Reward clone() {
        Reward c = new Reward();
        c.setId(id);
        c.setVersion(version);
        c.setHotspotId(hotspotId);
        c.setStartPeriod(startPeriod);
        c.setEndPeriod(endPeriod);
        c.setBeaconAmount(beaconAmount);
        c.setWitnessAmount(witnessAmount);
        return c;
    }



    // ---


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getHotspotId() {
        return hotspotId;
    }

    public void setHotspotId(String hotspotId) {
        this.hotspotId = hotspotId;
    }

    public long getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(long startPeriod) {
        this.startPeriod = startPeriod;
    }

    public long getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(long endPeriod) {
        this.endPeriod = endPeriod;
    }

    public long getBeaconAmount() {
        return beaconAmount;
    }

    public void setBeaconAmount(long beaconAmount) {
        this.beaconAmount = beaconAmount;
    }

    public long getWitnessAmount() {
        return witnessAmount;
    }

    public void setWitnessAmount(long witnessAmount) {
        this.witnessAmount = witnessAmount;
    }
}
