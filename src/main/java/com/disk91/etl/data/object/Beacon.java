package com.disk91.etl.data.object;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Sharded;
import org.springframework.data.mongodb.core.mapping.ShardingStrategy;

@Document(collection = "etl_beacons")
@CompoundIndexes({
        @CompoundIndex(name = "hotspotId", def = "{'hotspotId' : 'hashed'}"),
        @CompoundIndex(name = "data", def = "{'data' : 1}"),
        @CompoundIndex(name = "hotspotId_Id", def = "{'hotspotId' : 'hashed', 'id' : 'hashed'}")
})
@Sharded(shardKey = { "hotspotId", "id" }, shardingStrategy = ShardingStrategy.HASH)
public class Beacon {

    /*
          bytes local_entropy = 3;
          bytes remote_entropy = 4;
          bytes data = 5;
          // Frequency in Hz
          uint64 frequency = 6;
          int32 channel = 7;
          data_rate datarate = 8;
          // The Conducted transmit power in ddbm. This is _not_ adjusted with the
          // asserted gain of the gateway
          int32 tx_power = 9;
          // Timestamp of beacon transmit in nanos since unix epoch
          uint64 timestamp = 10;
          bytes signature = 11;
          uint32 tmst = 12;
     */

    @Id
    private String id;

    private int version;

    private String hotspotId;

    // Hex string of the data to find link beacon & witnesses
    private String data;

    private long frequency;
    private int channel;
    private int datarate;
    private int tx_power;

    // reception time in NS
    private long timestamp;
    private int tmst;

    // ---


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHotspotId() {
        return hotspotId;
    }

    public void setHotspotId(String hotspotId) {
        this.hotspotId = hotspotId;
    }

    public long getFrequency() {
        return frequency;
    }

    public void setFrequency(long frequency) {
        this.frequency = frequency;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public int getDatarate() {
        return datarate;
    }

    public void setDatarate(int datarate) {
        this.datarate = datarate;
    }

    public int getTx_power() {
        return tx_power;
    }

    public void setTx_power(int tx_power) {
        this.tx_power = tx_power;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getTmst() {
        return tmst;
    }

    public void setTmst(int tmst) {
        this.tmst = tmst;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
