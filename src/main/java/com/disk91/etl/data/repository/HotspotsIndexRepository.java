package com.disk91.etl.data.repository;

import com.disk91.etl.data.object.HotspotIndex;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotspotsIndexRepository extends MongoRepository<HotspotIndex, String> {

    public HotspotIndex findOneHotspotIndexByHotspotId(String hsId);

    @Query("{ $text : { $search : ?0 } }")
    public Slice<HotspotIndex>  findHotspotIndexByAnimalNameLike(String search, Pageable p);

    @Query("{ animalName : { $regex : '^?0' } }")
    public Slice<HotspotIndex>  findHotspotIndexByAnimalNameStarts(String search, Pageable p);

    @Query("{ $text : { $search : ?0 }, animalName : { $regex : '^?0' } }")
    public Slice<HotspotIndex> findHotspotIndexByAnimalNameLikeStarts(String search, Pageable p);

    @Query("{ 'position' : { '$geoWithin' : { '$geometry' : {" +
        " type: \"Polygon\", " +
        " coordinates: " +
        " [ [" +
        "    [?0, ?1], [?0, ?3], [?2, ?3], [?2, ?1], [?0, ?1]" +
        " ] ]" +
        "} } } }")
    public Slice<HotspotIndex> findByPositionNearbyBox(double bottomLeftLongitude, double bottomLeftLatitude, double topRightLongitude, double topRightLatitude, Pageable p);

}
