package com.disk91.etl.data.object.sub;

import fr.ingeniousthings.tools.ClonnableObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Hotspot Position", description = "Position and antenna information for a hotspot")
public class LatLng implements ClonnableObject<LatLng> {


    @Schema(
            description = "Timestamp UTC in ms where this position has been discovered",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private long lastDatePosition;  // timestamp where change have been detected

    @Schema(
            description = "Latitude - based on hex",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private double lat;

    @Schema(
            description = "Longitude - based on hex",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private double lng;

    @Schema(
            description = "Country",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String country = "";

    @Schema(
            description = "City",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String city = "";


    @Schema(
            description = "Altitude - not filled",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private double alt;

    @Schema(
            description = "Antenna gain - not filled",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private double gain;

    @Schema(
        description = "Hex Scale",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private double hexScale = 0;

    // --------

    public LatLng clone() {
        LatLng c = new LatLng();
        c.setLat(lat);
        c.setLng(lng);
        c.setAlt(alt);
        c.setGain(gain);
        c.setCountry(country);
        c.setCity(city);
        c.setLastDatePosition(lastDatePosition);
        c.setHexScale(hexScale);
        return c;
    }


    // ---------


    public long getLastDatePosition() {
        return lastDatePosition;
    }

    public void setLastDatePosition(long lastDatePosition) {
        this.lastDatePosition = lastDatePosition;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getAlt() {
        return alt;
    }

    public void setAlt(double alt) {
        this.alt = alt;
    }

    public double getGain() {
        return gain;
    }

    public void setGain(double gain) {
        this.gain = gain;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getHexScale() { return this.hexScale; }

    public void setHexScale(double hexScale) { this.hexScale = hexScale; }


}
