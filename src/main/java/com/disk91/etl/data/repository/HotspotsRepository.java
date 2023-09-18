package com.disk91.etl.data.repository;

import com.disk91.etl.data.object.Hotspot;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotspotsRepository extends MongoRepository<Hotspot, String> {

    public Hotspot findOneHotspotByHotspotId(String hsId);

    @Query("{ $or : [ {lastIndexed:{$exists:false}}, {lastIndexed: { $lt : ?0 }}] } ")
    public List<Hotspot> findFirst50HotspotToBeIndexed(long timeRef, Pageable p);


}
