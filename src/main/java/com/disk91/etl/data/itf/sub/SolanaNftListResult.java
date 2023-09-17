package com.disk91.etl.data.itf.sub;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SolanaNftListResult {

    /*
    "result": {
    "nfts": [
      {
     */

    protected List<SolanaNft> nfts;

    // ---


    public List<SolanaNft> getNfts() {
        return nfts;
    }

    public void setNfts(List<SolanaNft> nfts) {
        this.nfts = nfts;
    }
}
