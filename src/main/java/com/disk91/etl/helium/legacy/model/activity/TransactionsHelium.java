package com.disk91.taxminer.helium.model.activity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionsHelium {

    /*
    data: {
type: "payment_v2",
time: 1641757255,
payments: [
{
payee: "12wu6KrUuazURqZ3dCu9CMpFo6YJyZaFMe5S85c1LSk88VGNj8x",
memo: "dGVzdAAAAAA=",
amount: 1000000
}
],
payer: "13ZhVBrEJLAaHKQ2vKZPFAxyAMqxd2726UhSFzDWXpGEizSUixU",
nonce: 102,
height: 1173549,
hash: "c2fwXSTwm0F57kUQ7eGreFwrGagD3p4IjZ_4JlSKL5I",
fee: 35000
}
     */

    protected HotspotActivityList data;

    public HotspotActivityList getData() {
        return data;
    }

    public void setData(HotspotActivityList data) {
        this.data = data;
    }
}
