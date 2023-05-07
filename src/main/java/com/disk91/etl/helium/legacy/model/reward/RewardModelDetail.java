package com.disk91.taxminer.helium.model.reward;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RewardModelDetail {
/*

{
timestamp: "2021-11-03T22:48:41.000000Z",
hash: "WR2yylvnuhCurHBOsLjMGyQO_QpVkrxztpLDM-_Fk3A",
gateway: "112b8DeTE2Tq4VeSJ6zFcyWqoGzJ3PUbRA5y5KjQYX5zDsWL3TfY",
block: 1083268,
amount: 2380341,
account: "13ZhVBrEJLAaHKQ2vKZPFAxyAMqxd2726UhSFzDWXpGEizSUixU"
},

*/

    protected String timestamp;
    protected String hash;
    protected String gateway;
    protected long block;
    protected long amount;
    protected String account;

    // --

    public long getTimeStampMs() {
        TemporalAccessor ta = DateTimeFormatter.ISO_INSTANT.parse(this.timestamp);
        Instant i = Instant.from(ta);
        Date d = Date.from(i);
        return d.getTime();
    }


    // ---
    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public long getBlock() {
        return block;
    }

    public void setBlock(long block) {
        this.block = block;
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
