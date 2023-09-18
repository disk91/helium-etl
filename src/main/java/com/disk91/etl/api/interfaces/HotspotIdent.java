package com.disk91.etl.api.interfaces;

import com.disk91.etl.data.object.Hotspot;
import com.disk91.etl.data.object.HotspotIndex;
import com.disk91.etl.data.object.sub.LatLng;
import com.disk91.etl.data.object.sub.Owner;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class HotspotIdent {

    @Schema(
        description = "Hexstring Base58 of the Hotspot public key, aka Hs address",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String hotspotId;

    @Schema(
        description = "Animal name of the hostpot with - between words",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String animalName;

    @Schema(
        description = "Hotspot Owner address",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private Owner owner;

    @Schema(
        description = "Hotspot position, lat / lng, can be 0,0",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private LatLng position;

    // ---

    public void init(Hotspot h) {
        this.hotspotId=h.getHotspotId();
        this.animalName=h.getAnimalName();
        this.owner=h.getOwner().clone();
        this.position=h.getPosition().clone();
        this.owner.setTimeMs(0);
        this.position.setHexScale(0);
        this.position.setAlt(0);
        this.position.setGain(0.0);
        this.position.setLastDatePosition(0);
        this.position.setCity("");
        this.position.setCountry("");
    }

    public void init(HotspotIndex hi) {
        this.hotspotId=hi.getHotspotId();
        this.animalName=hi.getAnimalName();
        this.owner = new Owner();
        this.owner.setHntOwner(hi.getHntOwner());
        this.owner.setSolOwner(hi.getSolOwner());
        this.position = new LatLng();
        this.position.setLat(hi.getPosition().getY());
        this.position.setLng(hi.getPosition().getX());
        this.owner.setTimeMs(0);
        this.position.setHexScale(0);
        this.position.setAlt(0);
        this.position.setGain(0.0);
        this.position.setLastDatePosition(0);
        this.position.setCity("");
        this.position.setCountry("");
    }

    // ---


    public String getHotspotId() {
        return hotspotId;
    }

    public void setHotspotId(String hotspotId) {
        this.hotspotId = hotspotId;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public LatLng getPosition() {
        return position;
    }

    public void setPosition(LatLng position) {
        this.position = position;
    }
}
