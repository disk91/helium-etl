package com.disk91.etl.service;

import com.disk91.etl.data.object.Reward;
import com.disk91.etl.data.repository.RewardRepository;
import fr.ingeniousthings.tools.ITNotFoundException;
import fr.ingeniousthings.tools.ITParseException;
import fr.ingeniousthings.tools.Now;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class RewardService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected RewardRepository rewardRepository;


    public List<Reward> getHotspotRewards( String hsId, long from, long to)
    throws ITParseException {

        if ( to < from || (to - from) > 30*Now.ONE_FULL_DAY ) throw new ITParseException("invalid date between");

        long start = Now.NowUtcMs();
        Slice<Reward> rs = rewardRepository.findRewardsByHotspotIdAndStartPeriodBetweenOrderByStartPeriodAsc(
                hsId,
                from,
                to+ Now.ONE_FULL_DAY,
                PageRequest.of(0,100)
        );
        log.info(">> process reward search "+(Now.NowUtcMs()-start)+" ms");
        ArrayList<Reward> rewards = new ArrayList<>();
        if ( rs != null ) {
            do {
                rewards.addAll(rs.getContent());
                if ( rs.hasNext() ) {
                    rs = rewardRepository.findRewardsByHotspotIdAndStartPeriodBetweenOrderByStartPeriodAsc(
                            hsId, from, to + Now.ONE_FULL_DAY,
                            rs.nextPageable());
                }
            } while ( rs != null && rs.hasNext() );
        }

        return rewards;

    }


}