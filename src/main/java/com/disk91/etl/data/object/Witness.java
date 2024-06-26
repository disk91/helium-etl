package com.disk91.etl.data.object;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Sharded;
import org.springframework.data.mongodb.core.mapping.ShardingStrategy;

@Document(collection = "etl_witnesses")
@CompoundIndexes({
        @CompoundIndex(name = "hotspotId", def = "{'hotspotId' : 'hashed'}"),
        @CompoundIndex(name = "hotspotId_Id", def = "{'hotspotId' : 1, 'id' : 1}")
})
@Sharded(shardKey = { "hotspotId", "id" }, shardingStrategy = ShardingStrategy.RANGE)
public class Witness {

    /*
      bytes pub_key = 2;
      bytes data = 3;
      // Timestamp of witness received in nanos since unix epoch
      uint64 timestamp = 4;
      uint32 tmst = 5;
      // Signal in ddbm
      sint32 signal = 6;
      int32 snr = 7;
      // Frequency in Hz
      uint64 frequency = 8;
      data_rate datarate = 10;
      bytes signature = 11;
     */

    @Id
    private String id;

    private int version;        // if document evolves

    private String beaconId;    // Beaconner Id
    private long beaconTime;    // beaconTime Stamp

    private String hotspotId;

    // Hex string of the data to find link beacon & witnesses
    private String data;

    // reception time in NS
    private long timestamp;
    private int tmst;

    private int signal;
    private int snr;
    private long frequency;
    private int datarate;

    private boolean valid;

    private int orderOfArrival = 0; // from the 1st received by Oracle, gives the posititon
    private long lateOfArrival = -1; // rom the 1st received by Oracle, gives the delay in ms of that one

    // ---


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHotspotId() {
        return hotspotId;
    }

    public void setHotspotId(String hotspotId) {
        this.hotspotId = hotspotId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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

    public int getSignal() {
        return signal;
    }

    public void setSignal(int signal) {
        this.signal = signal;
    }

    public int getSnr() {
        return snr;
    }

    public void setSnr(int snr) {
        this.snr = snr;
    }

    public long getFrequency() {
        return frequency;
    }

    public void setFrequency(long frequency) {
        this.frequency = frequency;
    }

    public int getDatarate() {
        return datarate;
    }

    public void setDatarate(int datarate) {
        this.datarate = datarate;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getBeaconId() {
        return beaconId;
    }

    public void setBeaconId(String beaconId) {
        this.beaconId = beaconId;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public long getBeaconTime() {
        return beaconTime;
    }

    public void setBeaconTime(long beaconTime) {
        this.beaconTime = beaconTime;
    }

    public int getOrderOfArrival() {
        return orderOfArrival;
    }

    public void setOrderOfArrival(int orderOfArrival) {
        this.orderOfArrival = orderOfArrival;
    }

    public long getLateOfArrival() {
        return lateOfArrival;
    }

    public void setLateOfArrival(long lateOfArrival) {
        this.lateOfArrival = lateOfArrival;
    }
}
