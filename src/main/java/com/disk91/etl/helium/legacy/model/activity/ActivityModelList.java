package com.disk91.taxminer.helium.model.activity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ActivityModelList {
/*
    data: [
    {
        type: "rewards_v2",
                time: 1635280320,
            start_epoch: 1071532,
            rewards: [
        {
            type: "poc_witnesses",
                    gateway: "112b8DeTE2Tq4VeSJ6zFcyWqoGzJ3PUbRA5y5KjQYX5zDsWL3TfY",
                amount: 1655041,
                account: "13ZhVBrEJLAaHKQ2vKZPFAxyAMqxd2726UhSFzDWXpGEizSUixU"
        },
...
        {
            type: "data_credits",
                    gateway: "11etKgw9Lb6FndJnU17pKQVtsgbPJRvzE8eHny4J5f78NFvEXUD",
                amount: 205,
                account: "13ZhVBrEJLAaHKQ2vKZPFAxyAMqxd2726UhSFzDWXpGEizSUixU"
        }
],
        height: 1071563,
                hash: "5hr1v0dyOQ4obJQyrY76CpVt4CarXNAvIiL-ktFp4-U",
            end_epoch: 1071562
    },
    {
        type: "rewards_v2",
                time: 1635278431,
            start_epoch: 1071500,
            rewards: [
        {
            type: "poc_witnesses",
                    gateway: "11etKgw9Lb6FndJnU17pKQVtsgbPJRvzE8eHny4J5f78NFvEXUD",
                amount: 1593775,
                account: "13ZhVBrEJLAaHKQ2vKZPFAxyAMqxd2726UhSFzDWXpGEizSUixU"
        },
        ...
        cursor: "eyJyZW1haW5pbmciOjk4LCJtaW5f...cxNTg2LCJibG9jayI6MTA3MTUwMCwiYW5jaG9yX2Jsb2NrIjoxMDcxNTAwfQ"
*/

    protected List<ActivityModelEpochList> data;
    protected String cursor;


    // ---


    public List<ActivityModelEpochList> getData() {
        return data;
    }

    public void setData(List<ActivityModelEpochList> data) {
        this.data = data;
    }

    public String getCursor() {
        return cursor;
    }

    public void setCursor(String cursor) {
        this.cursor = cursor;
    }
}
