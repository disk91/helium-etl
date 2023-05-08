package com.disk91.etl.data.object.sub;

import fr.ingeniousthings.tools.ClonnableObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Hotspot Ownership", description = "Owner of the hotspot")
public class Owner implements ClonnableObject<Owner> {

    @Schema(
            description = "Hnt address of the owner",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String hntOwner = "";

    @Schema(
            description = "Sol address of the owner",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String solOwner = "";

    @Schema(
            description = "End of the ownership for this address",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private long timeMs = 0;

    // --------

    public Owner clone() {
        Owner c = new Owner();
        c.setHntOwner(hntOwner);
        c.setSolOwner(solOwner);
        c.setTimeMs(timeMs);
        return c;
    }


    // ---------


    public String getHntOwner() {
        return hntOwner;
    }

    public void setHntOwner(String hntOwner) {
        this.hntOwner = hntOwner;
    }

    public String getSolOwner() {
        return solOwner;
    }

    public void setSolOwner(String solOwner) {
        this.solOwner = solOwner;
    }

    public long getTimeMs() {
        return timeMs;
    }

    public void setTimeMs(long timeMs) {
        this.timeMs = timeMs;
    }
}
