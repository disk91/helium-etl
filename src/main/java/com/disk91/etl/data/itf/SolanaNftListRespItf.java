package com.disk91.etl.data.itf;

import com.disk91.etl.data.itf.sub.SolanaNftListResult;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

    /*
    {
        "success": true,
        "message": "All compressed NFTs present in the wallet: 6VgGEpxMfnKLi1qT2k9SPHnC177bPokC1nhj1Qcu7FJ6",
        "result": {
            "nfts": [
                {
                },
            ]
         }
     */


@JsonIgnoreProperties(ignoreUnknown = true)
public class SolanaNftListRespItf {

    protected boolean success;
    protected SolanaNftListResult result;

    // ---


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public SolanaNftListResult getResult() {
        return result;
    }

    public void setResult(SolanaNftListResult result) {
        this.result = result;
    }
}
