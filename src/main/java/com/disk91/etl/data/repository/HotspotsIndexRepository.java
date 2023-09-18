package com.disk91.etl.data.repository;

import com.disk91.etl.data.object.HotspotIndex;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotspotsIndexRepository extends MongoRepository<HotspotIndex, String> {

    public HotspotIndex findOneHotspotIndexByHotspotId(String hsId);

    @Query("{ $text : { $search : ?0 } }")
    public List<HotspotIndex>  findHotspotIndexByAnimalNameLike(String search, Pageable p);

    @Query("{ animalName : { $regex : '^?0' } }")
    public List<HotspotIndex>  findHotspotIndexByAnimalNameStarts(String search, Pageable p);

}
