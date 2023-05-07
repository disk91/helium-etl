package com.disk91.taxminer.helium.model.stats;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StatsDataCounts {

/*
{
data: {
token_supply: 112645079.23286769,
election_times: {
last_week: {
stddev: 117.431879661742,
avg: 1826.8942598187311
},
last_month: {
stddev: 932.370165967806,
avg: 1965.5690161527166
},
last_hour: {
stddev: 101.1162697096763,
avg: 1909.5
},
last_day: {
stddev: 126.826497178127,
avg: 1882.8695652173913
}
},
counts: {
validators: 3400,
transactions: 335282349,
ouis: 21,
hotspots_online: 438409,
hotspots_dataonly: 1176,
hotspots: 585140,
countries: 139,
consensus_groups: 32714,
coingecko_price_usd: 1251,
coingecko_price_gbp: 897,
coingecko_price_eur: 1054,
cities: 21504,
challenges: 104772597,
blocks: 1233140
},
challenge_counts: {
last_day: 683313,
active: 0
},
block_times: {
last_week: {
stddev: 16.807339348113047,
avg: 56.29932036123266
},
last_month: {
stddev: 158.004702680075,
avg: 60.590186186457935
},
last_hour: {
stddev: 11.394110477327308,
avg: 56.75806451612903
},
last_day: {
stddev: 15.521383104497165,
avg: 56.575360419397114
}
}
}
}
 */

    protected int  validators;
    protected long transactions;
    protected int ouis;
    protected int hotspots_online;
    protected int hotspots_dataonly;
    protected int hotspots;
    protected int countries;
    protected int consensus_groups;
    protected int coingecko_price_usd;
    protected int coingecko_price_gbp;
    protected int coingecko_price_eur;
    protected int cities;
    protected int challenges;
    protected int blocks;


    public int getValidators() {
        return validators;
    }

    public void setValidators(int validators) {
        this.validators = validators;
    }

    public long getTransactions() {
        return transactions;
    }

    public void setTransactions(long transactions) {
        this.transactions = transactions;
    }

    public int getOuis() {
        return ouis;
    }

    public void setOuis(int ouis) {
        this.ouis = ouis;
    }

    public int getHotspots_online() {
        return hotspots_online;
    }

    public void setHotspots_online(int hotspots_online) {
        this.hotspots_online = hotspots_online;
    }

    public int getHotspots_dataonly() {
        return hotspots_dataonly;
    }

    public void setHotspots_dataonly(int hotspots_dataonly) {
        this.hotspots_dataonly = hotspots_dataonly;
    }

    public int getHotspots() {
        return hotspots;
    }

    public void setHotspots(int hotspots) {
        this.hotspots = hotspots;
    }

    public int getCountries() {
        return countries;
    }

    public void setCountries(int countries) {
        this.countries = countries;
    }

    public int getConsensus_groups() {
        return consensus_groups;
    }

    public void setConsensus_groups(int consensus_groups) {
        this.consensus_groups = consensus_groups;
    }

    public int getCoingecko_price_usd() {
        return coingecko_price_usd;
    }

    public void setCoingecko_price_usd(int coingecko_price_usd) {
        this.coingecko_price_usd = coingecko_price_usd;
    }

    public int getCoingecko_price_gbp() {
        return coingecko_price_gbp;
    }

    public void setCoingecko_price_gbp(int coingecko_price_gbp) {
        this.coingecko_price_gbp = coingecko_price_gbp;
    }

    public int getCoingecko_price_eur() {
        return coingecko_price_eur;
    }

    public void setCoingecko_price_eur(int coingecko_price_eur) {
        this.coingecko_price_eur = coingecko_price_eur;
    }

    public int getCities() {
        return cities;
    }

    public void setCities(int cities) {
        this.cities = cities;
    }

    public int getChallenges() {
        return challenges;
    }

    public void setChallenges(int challenges) {
        this.challenges = challenges;
    }

    public int getBlocks() {
        return blocks;
    }

    public void setBlocks(int blocks) {
        this.blocks = blocks;
    }
}
