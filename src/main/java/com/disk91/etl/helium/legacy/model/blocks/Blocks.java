package com.disk91.taxminer.helium.model.blocks;

import com.disk91.taxminer.helium.model.stats.StatsData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Blocks {

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

    protected List<BlocksData> data;

    public List<BlocksData> getData() {
        return data;
    }

    public void setData(List<BlocksData> data) {
        this.data = data;
    }
}
