package com.disk91.etl.api.interfaces;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Last Reward Recorded", description = "Get the last reward recorded timestamp")
public class LastRewardItf {

    @Schema(
            description = "Timestamp of the ending period for rewards in MS since EPOC",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private long lastReward;



    // ---------------------------------------------------------
    // init


    // -------------------
    // Getter & Setters


    public long getLastReward() {
        return lastReward;
    }

    public void setLastReward(long lastReward) {
        this.lastReward = lastReward;
    }
}
