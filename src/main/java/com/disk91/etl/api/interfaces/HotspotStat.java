package com.disk91.etl.api.interfaces;

import com.disk91.etl.data.object.Hotspot;
import com.disk91.etl.data.object.sub.*;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Hotspot Stat", description = "stat about hotspot loading")
public class HotspotStat {

    @Schema(
            description = "UTC Ms timestamp from the last loaded file",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private long lastFileDate;

    // ---


    public long getLastFileDate() {
        return lastFileDate;
    }

    public void setLastFileDate(long lastFileDate) {
        this.lastFileDate = lastFileDate;
    }
}
