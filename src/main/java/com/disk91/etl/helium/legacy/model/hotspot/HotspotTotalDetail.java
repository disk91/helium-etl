package com.disk91.taxminer.helium.model.hotspot;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HotspotTotalDetail {
/*
{
meta: {
min_time: "2020-01-01T00:00:00Z",
max_time: "2025-12-31T00:00:00Z"
},
data: {
total: 3165.36459418,
sum: 316536459418,
stddev: 0.966769278117,
min: 0,
median: 0.01655448,
max: 15,
avg: 0.30123378322992006
}

*/

    protected double total;
    protected long sum;
    protected double stddev;
    protected double min;
    protected double median;
    protected double max;
    protected double avg;


    // ---


    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }

    public double getStddev() {
        return stddev;
    }

    public void setStddev(double stddev) {
        this.stddev = stddev;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMedian() {
        return median;
    }

    public void setMedian(double median) {
        this.median = median;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }
}
