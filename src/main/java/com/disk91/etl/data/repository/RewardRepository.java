package com.disk91.etl.data.repository;

import com.disk91.etl.data.object.Reward;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RewardRepository extends MongoRepository<Reward, String> {

    public Slice<Reward> findRewardsByHotspotIdAndStartPeriodBetweenOrderByStartPeriodAsc(
            String deviceId,
            long s_start,
            long e_start,
            Pageable pageable
    );

}
