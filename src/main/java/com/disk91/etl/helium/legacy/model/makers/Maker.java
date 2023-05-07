package com.disk91.taxminer.helium.model.makers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Maker {


    /*
    https://explorer-api.helium.com/api/makers
    {
id: 1,
name: "Helium Inc",
address: "13daGGWvDQyTyHFDCPz8zDSVTWgPNNfJ4oh31Teec4TRWfjMx53",
locationNonceLimit: 2,
createdAt: "2021-01-13T01:57:01.662Z",
updatedAt: "2021-01-13T01:57:01.662Z",
balanceInfo: {
client: {
network: {
baseURL: "https://sharkfed.api.stakejoy.com",
version: 1
},
retry: 3
},
speculativeNonce: 0,
stakedBalance: {
type: {
ticker: "HNT",
decimalPlaces: "8",
coefficient: "0.00000001"
},
integerBalance: 0,
bigInteger: "0",
bigBalance: "0",
floatBalance: 0
},
secNonce: 0,
secBalance: {
type: {
ticker: "HST",
decimalPlaces: "8",
coefficient: "0.00000001"
},
integerBalance: 0,
bigInteger: "0",
bigBalance: "0",
floatBalance: 0
},
nonce: 0,
dcNonce: 0,
dcBalance: {
type: {
ticker: "DC",
decimalPlaces: "0",
coefficient: "1"
},
integerBalance: 88715024,
bigInteger: "88715024",
bigBalance: "88715024",
floatBalance: 88715024
},
block: 1141076,
balance: {
type: {
ticker: "HNT",
decimalPlaces: "8",
coefficient: "0.00000001"
},
integerBalance: 71379,
bigInteger: "71379",
bigBalance: "0.00071379",
floatBalance: 0.00071379
},
address: "13daGGWvDQyTyHFDCPz8zDSVTWgPNNfJ4oh31Teec4TRWfjMx53"
},
txns: {
addGatewayTxns: 1266,
assertLocationTxns: 5148,
tokenBurnAmountInBones: 1515700000000
}
},
{
id: 2,
name: "Cal-Chip Connected Devices",
address: "13ENbEQPAvytjLnqavnbSAzurhGoCSNkGECMx7eHHDAfEaDirdY",
locationNonceLimit: 1,
createdAt: "2021-01-16T09:06:56.433Z",
updatedAt: "2021-01-16T09:06:56.433Z",
balanceInfo: {
client: {
network: {
baseURL: "https://sharkfed.api.stakejoy.com",
version: 1
},
retry: 3
},
     */

    protected String name;
    protected String address;

    // ---


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
