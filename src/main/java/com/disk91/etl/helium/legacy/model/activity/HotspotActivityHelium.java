package com.disk91.taxminer.helium.model.activity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HotspotActivityHelium {

    /*
    data: [
{
type: "rewards_v2",
time: 1637884166,
start_epoch: 1112523,
rewards: [
{
type: "poc_witnesses",
gateway: "11HoiKJMesTtcQ73rx9Yc1aLcrkLPMvXRSJKDfhV7qVr5R9nzzj",
amount: 702310,
account: "13ZhVBrEJLAaHKQ2vKZPFAxyAMqxd2726UhSFzDWXpGEizSUixU"
},
{
....
}
],
height: 1112554,
hash: "jT68xcM-Ev4MgCjpOC9uhhY3uZTv-bSKvUBJm_LEChg",
end_epoch: 1112553
},
,
cursor: "eyJtaW5fYmxvY2siOjEwNzg5NzYsIm1heF9ibG9jayI6MTExMjU2MywiYmxvY2siOjExMTI1MDAsImFuY2hvcl9ibG9jayI6MTExMjUwMH0"
     */

    protected List<HotspotActivityList> data;
    protected String cursor;

    public List<HotspotActivityList> getData() {
        return data;
    }

    public void setData(List<HotspotActivityList> data) {
        this.data = data;
    }

    public String getCursor() {
        return cursor;
    }

    public void setCursor(String cursor) {
        this.cursor = cursor;
    }
}
