package com.disk91.etl.helium.legacy.model.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountDetails {

    /*
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
    */

    protected int staked_balance;
    protected int speculative_sec_nonce;
    protected int speculative_nonce;
    protected int sec_nonce;
    protected int sec_balance;
    protected int nonce;
    protected int dc_balance;
    protected int dc_nonce;
    protected long block;
    protected long balance;
    protected String address;

    // ---


    public int getStaked_balance() {
        return staked_balance;
    }

    public void setStaked_balance(int staked_balance) {
        this.staked_balance = staked_balance;
    }

    public int getSpeculative_sec_nonce() {
        return speculative_sec_nonce;
    }

    public void setSpeculative_sec_nonce(int speculative_sec_nonce) {
        this.speculative_sec_nonce = speculative_sec_nonce;
    }

    public int getSpeculative_nonce() {
        return speculative_nonce;
    }

    public void setSpeculative_nonce(int speculative_nonce) {
        this.speculative_nonce = speculative_nonce;
    }

    public int getSec_nonce() {
        return sec_nonce;
    }

    public void setSec_nonce(int sec_nonce) {
        this.sec_nonce = sec_nonce;
    }

    public int getSec_balance() {
        return sec_balance;
    }

    public void setSec_balance(int sec_balance) {
        this.sec_balance = sec_balance;
    }

    public int getNonce() {
        return nonce;
    }

    public void setNonce(int nonce) {
        this.nonce = nonce;
    }

    public int getDc_balance() {
        return dc_balance;
    }

    public void setDc_balance(int dc_balance) {
        this.dc_balance = dc_balance;
    }

    public int getDc_nonce() {
        return dc_nonce;
    }

    public void setDc_nonce(int dc_nonce) {
        this.dc_nonce = dc_nonce;
    }

    public long getBlock() {
        return block;
    }

    public void setBlock(long block) {
        this.block = block;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
