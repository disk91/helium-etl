package com.disk91.taxminer.helium.model.activity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ActivityModelEpochList {
    /*
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
     */

    protected String type;
    protected long time; // in seconds
    protected long start_epoch; // block
    protected List<ActivityModelDetail> rewards;

    // --


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

    public List<ActivityModelDetail> getRewards() {
        return rewards;
    }

    public void setRewards(List<ActivityModelDetail> rewards) {
        this.rewards = rewards;
    }
}
