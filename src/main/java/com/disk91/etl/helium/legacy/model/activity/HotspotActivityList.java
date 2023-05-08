package com.disk91.etl.helium.legacy.model.activity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HotspotActivityList {

    /*
{
type: "rewards_v2",
time: 1637884166,
start_epoch: 1112523,
rewards: [
{
type: "poc_witnesses",
gateway: "11HoiKJMesTtcQ73rx9Yc1aLcrkLPMvXRSJKDfhV7qVr5R9nzzj",
amount: 702310,
account: "13ZhVBrEJLAaHKQ2vKZPFAxyAMqxd2726UhSFzDWXpGEizSUixU"
},
{
....
}
],
height: 1112554,
hash: "jT68xcM-Ev4MgCjpOC9uhhY3uZTv-bSKvUBJm_LEChg",
end_epoch: 1112553
},
     */

    // Common
    protected String type;
    protected long time;            // time in S
    protected String hash;
    protected long height;          // rewarding block

    // Reward specific
    protected long start_epoch;     // reward from block
    protected long end_epoch;       // reward to block
    protected List<HotspotActivityDetail> rewards;


    // Payment_v2 specific
    protected String payer;
    protected long fee;
    protected List<AccountActivityPayment> payments;

    // ---


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getStart_epoch() {
        return start_epoch;
    }

    public void setStart_epoch(long start_epoch) {
        this.start_epoch = start_epoch;
    }

    public long getEnd_epoch() {
        return end_epoch;
    }

    public void setEnd_epoch(long end_epoch) {
        this.end_epoch = end_epoch;
    }

    public long getHeight() {
        return height;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public List<HotspotActivityDetail> getRewards() {
        return rewards;
    }

    public void setRewards(List<HotspotActivityDetail> rewards) {
        this.rewards = rewards;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public long getFee() {
        return fee;
    }

    public void setFee(long fee) {
        this.fee = fee;
    }

    public List<AccountActivityPayment> getPayments() {
        return payments;
    }

    public void setPayments(List<AccountActivityPayment> payments) {
        this.payments = payments;
    }
}
