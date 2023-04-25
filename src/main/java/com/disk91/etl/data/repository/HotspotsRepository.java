package com.disk91.etl.data.repository;

import com.disk91.etl.data.object.Hotspot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotspotsRepository extends MongoRepository<Hotspot, String> {

    public Hotspot findOneHotspotByHotspotId(String hsId);

    public Page<Hotspot> findAllHotspots(
            Pageable pageable
    );

}
