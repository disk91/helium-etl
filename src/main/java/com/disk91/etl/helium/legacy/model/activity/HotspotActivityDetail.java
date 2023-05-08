package com.disk91.etl.helium.legacy.model.activity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HotspotActivityDetail {

    /*

{
type: "poc_witnesses",
gateway: "11HoiKJMesTtcQ73rx9Yc1aLcrkLPMvXRSJKDfhV7qVr5R9nzzj",
amount: 702310,
account: "13ZhVBrEJLAaHKQ2vKZPFAxyAMqxd2726UhSFzDWXpGEizSUixU"
},

},
     */

    protected String type;
    protected String gateway;
    protected long amount;
    protected String account;

    // ---


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
