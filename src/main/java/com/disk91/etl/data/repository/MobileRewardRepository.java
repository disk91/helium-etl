package com.disk91.etl.data.repository;

import com.disk91.etl.data.object.MobileReward;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MobileRewardRepository extends MongoRepository<MobileReward, String> {

    public Slice<MobileReward> findMobileRewardsByHotspotIdAndStartPeriodBetweenOrderByStartPeriodAsc(
            String deviceId,
            long s_start,
            long e_start,
            Pageable pageable
    );

    public Slice<MobileReward> findMobileRewardByHotspotId(String deviceId, Pageable pageable);
}
