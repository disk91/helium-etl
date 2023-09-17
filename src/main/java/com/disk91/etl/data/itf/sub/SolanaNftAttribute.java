package com.disk91.etl.data.itf.sub;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SolanaNftAttribute {
    /*
        "ecc_compact": "11jaSQn",
        "rewardable": true
     */
    protected String ecc_compact;

    // ---


    public String getEcc_compact() {
        return ecc_compact;
    }

    public void setEcc_compact(String ecc_compact) {
        this.ecc_compact = ecc_compact;
    }
}
