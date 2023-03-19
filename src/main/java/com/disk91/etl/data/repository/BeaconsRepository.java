package com.disk91.etl.data.repository;

import com.disk91.etl.data.object.Beacon;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeaconsRepository extends MongoRepository<Beacon, String> {

    // Return beacon by data, used to match with witness
    // we can have mulitple beacon with same data
    // timestamp distance my help
    public List<Beacon> findBeaconByData(String data);

}
