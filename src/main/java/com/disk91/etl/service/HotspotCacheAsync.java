package com.disk91.etl.service;

import com.disk91.etl.data.object.Beacon;
import com.disk91.etl.data.object.Hotspot;
import com.disk91.etl.data.repository.HotspotsRepository;
import com.mongodb.WriteConcern;
import com.mongodb.bulk.BulkWriteResult;
import fr.ingeniousthings.tools.Now;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HotspotCacheAsync {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected HotspotsRepository hotspotsRepository;

    protected volatile Integer running = Integer.valueOf(0);

    protected volatile int total;
    protected volatile int processed;

    public boolean isRunning() {
        synchronized (running) {
            return (running>0);
        }
    }

    public int getProgress() {
        return (100*processed)/total;
    }

    public int getProcessed() {
        return processed;
    }

    @Autowired
    MongoTemplate mongoTemplate;

    @Async
    public void processAsyncCacheCommit(List<Hotspot> list) {
        synchronized (running) {
            if (running == 1) {
                log.error("Hotspot Async commit - try to reenter");
                list.clear();
                return;
            }
            running = 1;
        }
        long lastProgress = Now.NowUtcMs();
        total = list.size();
        processed=0;
        log.info("Hotspot Async commit starts "+total+" elements");

        ArrayList<Hotspot> _hotspotDelayedInsert = new ArrayList<>();
        for ( Hotspot h : list ) {

            // Improve write with bulk insert
            if ( _hotspotDelayedInsert.size() < 1000 ) {
                _hotspotDelayedInsert.add(h);
            } else {
                mongoTemplate.setWriteConcern(WriteConcern.W1.withJournal(true));
                BulkOperations bulkInsert = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, Beacon.class);
                for ( Hotspot _h : _hotspotDelayedInsert ) {
                    bulkInsert.insert(_h);
                }
                BulkWriteResult bulkWriteResult = bulkInsert.execute();
                _hotspotDelayedInsert.clear();
            }
            //hotspotsRepository.save(h);
            processed++;
            if ( (Now.NowUtcMs() - lastProgress) > 30_000) {
                log.debug("Hotspot Async commit - progress "+getProgress()+"%");
                lastProgress = Now.NowUtcMs();
            }
        }
        if ( _hotspotDelayedInsert.size() > 0 ) {
            mongoTemplate.setWriteConcern(WriteConcern.W1.withJournal(true));
            BulkOperations bulkInsert = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, Hotspot.class);
            for ( Hotspot _h : _hotspotDelayedInsert ) {
                bulkInsert.insert(_h);
            }
            BulkWriteResult bulkWriteResult = bulkInsert.execute();
            _hotspotDelayedInsert.clear();
        }
        list.clear();
        log.debug("Hotspot Async commit done");
        synchronized (running) {
            running = 0;
        }
    }


}
