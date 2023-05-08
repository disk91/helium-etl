package com.disk91.etl.helium.legacy.model.activity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountActivityPayment {

    protected String payee;
    protected long amount; // in bones
    protected String memo = ""; // this is not provided by the activity endpoint but is in the hashEndpoint

    // ---


    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
