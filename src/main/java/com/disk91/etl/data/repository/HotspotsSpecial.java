package com.disk91.etl.data.repository;

import com.disk91.etl.data.object.Hotspot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HotspotsSpecial {

    @Autowired
    private MongoTemplate mongoTemplate;

    /*
    db.yourCollectionName.aggregate([
    {
        $match: {
            $or: [
                { "hotspot.value": { $exists: false } },
                { "hotspot.value": { $gt: X } }
            ]
        }
    },
    { $sample: { size: 50 } }
])
     */

    public List<Hotspot> get50RandomDocuments() {
        Aggregation aggregation = Aggregation.newAggregation(
            Aggregation.sample(50)
        );
        AggregationResults<Hotspot> results = mongoTemplate.aggregate(aggregation, "etl_hotspots", Hotspot.class);
        return results.getMappedResults();
    }

}
