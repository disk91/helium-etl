package com.disk91.etl.data.repository;

import com.disk91.etl.data.object.Param;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParamRepository extends MongoRepository<Param, String> {

    public Param findOneParamByParamName(String paramName);

}
