package com.disk91.etl.helium.legacy.model.hotspot;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HotspotResponse {
/*
data: {
    speculative_nonce: 2,
    lng: 3.099227811675724,
    lat: 45.79222332444398,
    timestamp_added: "2020-09-29T17:11:19.000000Z",
    status: {
        timestamp: "2021-11-13T16:42:20.204000Z",
        online: "online",
        listen_addrs: [
            "/ip4/185.178.184.75/tcp/44158"
        ],
        height: 1097205
    },
    reward_scale: 1,
    payer: "14fzfjFcHpDR1rTH8BNPvSi5dKBbgxaDnmsVPbCjuq9ENjpZbxh",
    owner: "13ZhVBrEJLAaHKQ2vKZPFAxyAMqxd2726UhSFzDWXpGEizSUixU",
nonce: 2,
name: "dazzling-pewter-lark",
mode: "full",
location_hex: "881f96af21fffff",
location: "8c1f96af212e7ff",
last_poc_challenge: 1098677,
last_change_block: 1098719,
geocode: {
short_street: "Allée de la Fontcimagne",
short_state: "Auvergne-Rhône-Alpes",
short_country: "FR",
short_city: "Clermont-Ferrand",
long_street: "Allée de la Fontcimagne",
long_state: "Auvergne-Rhône-Alpes",
long_country: "France",
long_city: "Clermont-Ferrand",
city_id: "Y2xlcm1vbnQtZmVycmFuZGF1dmVyZ25lLXJow7RuZS1hbHBlc2ZyYW5jZQ"
},
gain: 12,
elevation: 0,
block_added: 522271,
block: 1098733,
address: "11etKgw9Lb6FndJnU17pKQVtsgbPJRvzE8eHny4J5f78NFvEXUD"
}
*/

    protected HotspotDetail data;


    // ---


    public HotspotDetail getData() {
        return data;
    }

    public void setData(HotspotDetail data) {
        this.data = data;
    }
}
