package com.disk91.etl.data.repository;

import com.disk91.etl.data.object.Beacon;
import com.disk91.etl.data.object.Witness;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WitnessesRepository extends MongoRepository<Witness, String> {


}
