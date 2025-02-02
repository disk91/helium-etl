package com.disk91.etl.data.object.sub;

import fr.ingeniousthings.tools.ClonnableObject;

public class RewardHistory implements ClonnableObject<RewardHistory> {

    private long timeRef;           // corresponding to the hour of the history
    private long witnessReward;
    private long beaconReward;
    private long dataReward;
    private int token = 0;  // 0 for native token, 1 for HNT

    // ---------

    public RewardHistory clone() {
        RewardHistory c = new RewardHistory();
        c.setTimeRef(timeRef);
        c.setWitnessReward(witnessReward);
        c.setBeaconReward(beaconReward);
        c.setDataReward(dataReward);
        c.setToken(token);
        return c;
    }


    // ---------


    public long getTimeRef() {
        return timeRef;
    }

    public void setTimeRef(long timeRef) {
        this.timeRef = timeRef;
    }


    public long getWitnessReward() {
        return witnessReward;
    }

    public void setWitnessReward(long witnessReward) {
        this.witnessReward = witnessReward;
    }

    public long getBeaconReward() {
        return beaconReward;
    }

    public void setBeaconReward(long beaconReward) {
        this.beaconReward = beaconReward;
    }

    public long getDataReward() {
        return dataReward;
    }

    public void setDataReward(long dataReward) {
        this.dataReward = dataReward;
    }

    public int getToken() {
        return token;
    }

    public void setToken(int token) {
        this.token = token;
    }
}
