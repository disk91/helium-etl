package com.disk91.taxminer.helium.model.account;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountResponse {
/*
    data: {
        staked_balance: 0,
                speculative_sec_nonce: 0,
                speculative_nonce: 93,
                sec_nonce: 0,
                sec_balance: 0,
                nonce: 93,
                dc_nonce: 0,
                dc_balance: 0,
                block: 1110603,
                balance: 1506481386,
                address: "13ZhVBrEJLAaHKQ2vKZPFAxyAMqxd2726UhSFzDWXpGEizSUixU"
    }
    */
    protected AccountDetails data;

    // ---


    public AccountDetails getData() {
        return data;
    }

    public void setData(AccountDetails data) {
        this.data = data;
    }
}
