package com.disk91.etl.helium.legacy.model.hotspot;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HotspotTotalResponse {
/*
{
meta: {
min_time: "2020-01-01T00:00:00Z",
max_time: "2025-12-31T00:00:00Z"
},
data: {
total: 3165.36459418,
sum: 316536459418,
stddev: 0.966769278117,
min: 0,
median: 0.01655448,
max: 15,
avg: 0.30123378322992006
}

*/

    protected HotspotTotalDetail data;


    // ---


    public HotspotTotalDetail getData() {
        return data;
    }

    public void setData(HotspotTotalDetail data) {
        this.data = data;
    }
}
