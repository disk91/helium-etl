package com.disk91.etl.helium.legacy.model.reward;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RewardModelList {
/*
    data: [
{
timestamp: "2021-11-03T22:48:41.000000Z",
hash: "WR2yylvnuhCurHBOsLjMGyQO_QpVkrxztpLDM-_Fk3A",
gateway: "112b8DeTE2Tq4VeSJ6zFcyWqoGzJ3PUbRA5y5KjQYX5zDsWL3TfY",
block: 1083268,
amount: 2380341,
account: "13ZhVBrEJLAaHKQ2vKZPFAxyAMqxd2726UhSFzDWXpGEizSUixU"
},
{
timestamp: "2021-11-03T22:48:41.000000Z",
hash: "WR2yylvnuhCurHBOsLjMGyQO_QpVkrxztpLDM-_Fk3A",
gateway: "112sX8h38pti8dJz56UJ3kuiiAf4kG9A6HoNrigtgzbGBpyoAvhL",
block: 1083268,
amount: 2380341,
account: "13ZhVBrEJLAaHKQ2vKZPFAxyAMqxd2726UhSFzDWXpGEizSUixU"
},
{
timestamp: "2021-11-03T22:48:41.000000Z",
hash: "WR2yylvnuhCurHBOsLjMGyQO_QpVkrxztpLDM-_Fk3A",
gateway: "11dcm1fCYaUu1yLmUzE12bwySpSntFuNZz1KGq7nNx8CL4M9Uyi",
block: 1083268,
amount: 35,
account: "13ZhVBrEJLAaHKQ2vKZPFAxyAMqxd2726UhSFzDWXpGEizSUixU"
},
{
timestamp: "2021-11-03T22
        ...
        cursor: "eyJyZW1haW5pbmciOjk4LCJtaW5f...cxNTg2LCJibG9jayI6MTA3MTUwMCwiYW5jaG9yX2Jsb2NrIjoxMDcxNTAwfQ"
*/

    protected List<RewardModelDetail> data;
    protected String cursor;


    // ---


    public List<RewardModelDetail> getData() {
        return data;
    }

    public void setData(List<RewardModelDetail> data) {
        this.data = data;
    }

    public String getCursor() {
        return cursor;
    }

    public void setCursor(String cursor) {
        this.cursor = cursor;
    }
}
