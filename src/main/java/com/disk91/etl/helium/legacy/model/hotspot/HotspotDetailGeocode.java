package com.disk91.etl.helium.legacy.model.hotspot;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HotspotDetailGeocode {
/*

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

}
*/
    protected String short_street;
    protected String short_state;
    protected String short_country;
    protected String short_city;
    protected String long_street;
    protected String long_state;
    protected String long_country;
    protected String long_city;
    protected String city_id;

    // ---


    public String getShort_street() {
        return short_street;
    }

    public void setShort_street(String short_street) {
        this.short_street = short_street;
    }

    public String getShort_state() {
        return short_state;
    }

    public void setShort_state(String short_state) {
        this.short_state = short_state;
    }

    public String getShort_country() {
        return short_country;
    }

    public void setShort_country(String short_country) {
        this.short_country = short_country;
    }

    public String getShort_city() {
        return short_city;
    }

    public void setShort_city(String short_city) {
        this.short_city = short_city;
    }

    public String getLong_street() {
        return long_street;
    }

    public void setLong_street(String long_street) {
        this.long_street = long_street;
    }

    public String getLong_state() {
        return long_state;
    }

    public void setLong_state(String long_state) {
        this.long_state = long_state;
    }

    public String getLong_country() {
        return long_country;
    }

    public void setLong_country(String long_country) {
        this.long_country = long_country;
    }

    public String getLong_city() {
        return long_city;
    }

    public void setLong_city(String long_city) {
        this.long_city = long_city;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }
}
