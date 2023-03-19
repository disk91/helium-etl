package com.disk91.etl.data.object.sub;

public class Witness {

    private long lastSeen;
    private String hs;
    private double lastRssi;
    private double lastSnr;

    private double totRssi;
    private double totSnr;
    private double countWitnesses;

    // -----

    public void init(String id) {
        this.hs = id;
        this.totRssi = 0;
        this.totSnr = 0;
        this.countWitnesses = 0;
    }

    public void addWitness(long _lastSeen, double _rssi, double _snr ) {
        this.lastSeen = lastSeen;
        this.lastRssi = _rssi;
        this.lastSnr = _snr;
        this.totRssi += _rssi;
        this.totSnr += _snr;
        this.countWitnesses++;
    }

    // ---------

    public long getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(long lastSeen) {
        this.lastSeen = lastSeen;
    }

    public String getHs() {
        return hs;
    }

    public void setHs(String hs) {
        this.hs = hs;
    }

    public double getLastRssi() {
        return lastRssi;
    }

    public void setLastRssi(double lastRssi) {
        this.lastRssi = lastRssi;
    }

    public double getLastSnr() {
        return lastSnr;
    }

    public void setLastSnr(double lastSnr) {
        this.lastSnr = lastSnr;
    }

    public double getTotRssi() {
        return totRssi;
    }

    public void setTotRssi(double totRssi) {
        this.totRssi = totRssi;
    }

    public double getTotSnr() {
        return totSnr;
    }

    public void setTotSnr(double totSnr) {
        this.totSnr = totSnr;
    }

    public double getCountWitnesses() {
        return countWitnesses;
    }

    public void setCountWitnesses(double countWitnesses) {
        this.countWitnesses = countWitnesses;
    }
}
