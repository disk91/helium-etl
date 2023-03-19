package com.disk91.etl.data.object.sub;

public class BeaconHistory {

    private long timeRef;           // corresponding to the hour of the history
    private int countBeacon;        // number of beacon during this period of time


    // ---------


    public long getTimeRef() {
        return timeRef;
    }

    public void setTimeRef(long timeRef) {
        this.timeRef = timeRef;
    }

    public int getCountBeacon() {
        return countBeacon;
    }

    public void setCountBeacon(int countBeacon) {
        this.countBeacon = countBeacon;
    }
}
