package com.disk91.etl.data.object.sub;

import fr.ingeniousthings.tools.ClonnableObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "DenyList Historical change", description = "Trace the IN&OUT in Deny List")
public class DenyHistory implements ClonnableObject<DenyHistory> {

    @Schema(
            description = "Timestamp of the change",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private long timestamp;

    @Schema(
            description = "true when the hotspot was in deny list before this timestamp",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private boolean wasInDenyList;


    // --------

    public DenyHistory clone() {
        DenyHistory c = new DenyHistory();
        c.setTimestamp(timestamp);
        c.setWasInDenyList(wasInDenyList);
        return c;
    }


    // ---------


    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isWasInDenyList() {
        return wasInDenyList;
    }

    public void setWasInDenyList(boolean wasInDenyList) {
        this.wasInDenyList = wasInDenyList;
    }
}
