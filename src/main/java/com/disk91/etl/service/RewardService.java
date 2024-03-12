package com.disk91.etl.service;

import com.disk91.etl.api.interfaces.LastRewardItf;
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
import org.springframework.data.domain.Sort;
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
        log.debug(">> process reward search "+(Now.NowUtcMs()-start)+" ms");
        ArrayList<Reward> rewards = new ArrayList<>();
        if ( rs != null ) {
            boolean quit = false;
            do {
                rewards.addAll(rs.getContent());
                if ( rs.hasNext() ) {
                    rs = rewardRepository.findRewardsByHotspotIdAndStartPeriodBetweenOrderByStartPeriodAsc(
                            hsId, from, to + Now.ONE_FULL_DAY,
                            rs.nextPageable());
                } else quit = true;
            } while ( rs != null && !quit );
        }

        return rewards;

    }

    public LastRewardItf getLastReward( String hsId ) {

        PageRequest request = PageRequest.of(0,1, Sort.by("endPeriod").descending());

        Slice<Reward> rs = rewardRepository.findRewardByHotspotId(hsId,request);
        LastRewardItf lr = new LastRewardItf();
        if ( rs.hasContent() ) {
            lr.setLastReward(rs.getContent().get(0).getEndPeriod());
        } else {
            lr.setLastReward(0);
        }
        return lr;

    }

}
