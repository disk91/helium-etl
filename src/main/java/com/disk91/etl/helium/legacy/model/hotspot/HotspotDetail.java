package com.disk91.taxminer.helium.model.hotspot;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HotspotDetail {
/*
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

    protected int speculative_nonce;
    protected double lng;
    protected double lat;

    protected String timestamp_added;
    protected double reward_scale;

    protected String payer;
    protected String owner;

    protected int nonce;
    protected String name;

    // full | ...
    protected String mode;
    protected long last_poc_challenge;
    protected long last_change_block;
    protected String location_hex;
    protected String location;

    // gain 12 = 1.2
    protected double gain;
    protected double elevation;
    protected long block_added;
    protected long block;
    protected String address;

    protected HotspotDetailStatus status;
    protected HotspotDetailGeocode geocode;

    // ---


    public int getSpeculative_nonce() {
        return speculative_nonce;
    }

    public void setSpeculative_nonce(int speculative_nonce) {
        this.speculative_nonce = speculative_nonce;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getTimestamp_added() {
        return timestamp_added;
    }

    public void setTimestamp_added(String timestamp_added) {
        this.timestamp_added = timestamp_added;
    }

    public double getReward_scale() {
        return reward_scale;
    }

    public void setReward_scale(double reward_scale) {
        this.reward_scale = reward_scale;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getNonce() {
        return nonce;
    }

    public void setNonce(int nonce) {
        this.nonce = nonce;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public long getLast_poc_challenge() {
        return last_poc_challenge;
    }

    public void setLast_poc_challenge(long last_poc_challenge) {
        this.last_poc_challenge = last_poc_challenge;
    }

    public long getLast_change_block() {
        return last_change_block;
    }

    public void setLast_change_block(long last_change_block) {
        this.last_change_block = last_change_block;
    }

    public String getLocation_hex() {
        return location_hex;
    }

    public void setLocation_hex(String location_hex) {
        this.location_hex = location_hex;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getGain() {
        return gain;
    }

    public void setGain(double gain) {
        this.gain = gain;
    }

    public double getElevation() {
        return elevation;
    }

    public void setElevation(double elevation) {
        this.elevation = elevation;
    }

    public long getBlock_added() {
        return block_added;
    }

    public void setBlock_added(long block_added) {
        this.block_added = block_added;
    }

    public long getBlock() {
        return block;
    }

    public void setBlock(long block) {
        this.block = block;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public HotspotDetailStatus getStatus() {
        return status;
    }

    public void setStatus(HotspotDetailStatus status) {
        this.status = status;
    }

    public HotspotDetailGeocode getGeocode() {
        return geocode;
    }

    public void setGeocode(HotspotDetailGeocode geocode) {
        this.geocode = geocode;
    }
}
