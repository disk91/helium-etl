package com.disk91.etl.helium.legacy.model.hotspot;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HotspotDetailStatus {
/*

status: {
timestamp: "2021-11-13T16:42:20.204000Z",
online: "online",
listen_addrs: [
"/ip4/185.178.184.75/tcp/44158"
],
height: 1097205
},

*/

    protected String timestamp;
    // online | ...
    protected String online;
    protected List<String> listen_addrs;
    protected long height;

    // ---


    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }

    public List<String> getListen_addrs() {
        return listen_addrs;
    }

    public void setListen_addrs(List<String> listen_addrs) {
        this.listen_addrs = listen_addrs;
    }

    public long getHeight() {
        return height;
    }

    public void setHeight(long height) {
        this.height = height;
    }
}
