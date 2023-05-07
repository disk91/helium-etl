package com.disk91.taxminer.helium.model.blocks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BlocksData {

/*
{
data: [
{
transaction_count: 1479,
time: 1645300212,
snapshot_hash: "",
prev_hash: "Z0dOM73wxFwhzDei7sdvjubCNB4JeWlcHsFM3E4aDFc",
height: 1233171,
hash: "Mk7HSL2D7I8fNBlbeplgyhQ_PmjFKXJceWv1WIqEK8Y"
},
{
transaction_count: 1318,
time: 1645300162,
snapshot_hash: "",
prev_hash: "lpANoq3VGJ4oLx-N0k0WZ8GQ52hkiU2BHGFILXUPVj4",
height: 1233170,
hash: "Z0dOM73wxFwhzDei7sdvjubCNB4JeWlcHsFM3E4aDFc"
},
 */

    protected int transaction_count;
    protected long time;
    protected String snapshot_hash;
    protected String prev_hash;
    protected String hash;
    protected int height;

    // ===

    public int getTransaction_count() {
        return transaction_count;
    }

    public void setTransaction_count(int transaction_count) {
        this.transaction_count = transaction_count;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getSnapshot_hash() {
        return snapshot_hash;
    }

    public void setSnapshot_hash(String snapshot_hash) {
        this.snapshot_hash = snapshot_hash;
    }

    public String getPrev_hash() {
        return prev_hash;
    }

    public void setPrev_hash(String prev_hash) {
        this.prev_hash = prev_hash;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
