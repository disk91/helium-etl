package com.disk91.etl.helium.legacy.model.stats;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Stats {

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

    protected StatsData  data;

    public StatsData getData() {
        return data;
    }

    public void setData(StatsData data) {
        this.data = data;
    }
}
