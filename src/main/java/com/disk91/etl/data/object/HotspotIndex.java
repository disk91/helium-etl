package com.disk91.etl.data.object;

import fr.ingeniousthings.tools.Gps;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Sharded;
import org.springframework.data.mongodb.core.mapping.ShardingStrategy;

@Document(collection = "etl_hsIndex")
@CompoundIndexes({
    @CompoundIndex(name = "hotspotId", def = "{'hotspotId' : 'hashed'}"),
    @CompoundIndex(name = "animalName", def = "{'animalName' : 'text'}"),
    @CompoundIndex(name = "owner", def = "{'owner' : 'hashed'}"),
    @CompoundIndex(name = "hotspotId_Id", def = "{'hotspotId' : 1, 'id' : 1}")
})
@Sharded(shardKey = { "hotspotId", "id" }, shardingStrategy = ShardingStrategy.RANGE)
public class HotspotIndex {

    @Id
    private String id;
    private String hotspotId;
    private String animalName;
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private GeoJsonPoint position;
    private String hntOwner = "";
    private String solOwner = "";

    // --
    public void init(Hotspot h) {
        this.hotspotId = h.getHotspotId();
        this.animalName = h.getAnimalName();
        this.hntOwner = (h.getOwner()!=null)?h.getOwner().getHntOwner():"unknown";
        this.solOwner = (h.getOwner()!=null)?h.getOwner().getSolOwner():"unknown";
        if ( h.getPosition() != null && Gps.isAValidCoordinate(h.getPosition().getLat(),h.getPosition().getLng()) ) {
            this.position = new GeoJsonPoint(h.getPosition().getLng(),h.getPosition().getLat());
        } else {
            this.position = new GeoJsonPoint(0.0,0.0);
        }
    }


    // ---

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public GeoJsonPoint getPosition() {
        return position;
    }

    public void setPosition(GeoJsonPoint position) {
        this.position = position;
    }

    public String getHntOwner() {
        return hntOwner;
    }

    public void setHntOwner(String hntOwner) {
        this.hntOwner = hntOwner;
    }

    public String getSolOwner() {
        return solOwner;
    }

    public void setSolOwner(String solOwner) {
        this.solOwner = solOwner;
    }
}
