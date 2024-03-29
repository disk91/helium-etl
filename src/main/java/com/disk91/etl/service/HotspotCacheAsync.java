package com.disk91.etl.service;

import com.disk91.etl.data.object.Hotspot;
import com.disk91.etl.data.repository.HotspotsRepository;
import fr.ingeniousthings.tools.Now;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HotspotCacheAsync {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected HotspotsRepository hotspotsRepository;

    protected volatile Integer running = 0;
    private final Object syncRunning = new Object();

    protected volatile int total;
    protected volatile int processed;

    public boolean isRunning() {
        synchronized (syncRunning) {
            return (running>0);
        }
    }

    public int getProgress() {
        return (100*processed)/total;
    }

    public int getProcessed() {
        return processed;
    }

    @Async
    public void processAsyncCacheCommit(List<Hotspot> list) {
        synchronized (syncRunning) {
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

            _hotspotDelayedInsert.add(h);
            processed++;
            if ( _hotspotDelayedInsert.size() > 5000 ) {
                _hotspotDelayedInsert.parallelStream().forEach(hotspotsRepository::save);
                //hotspotsRepository.saveAll(_hotspotDelayedInsert);
                _hotspotDelayedInsert.clear();
            }

            if ( (Now.NowUtcMs() - lastProgress) > 30_000) {
                log.debug("Hotspot Async commit - progress "+getProgress()+"%");
                lastProgress = Now.NowUtcMs();
            }

        }
        if ( _hotspotDelayedInsert.size() > 0 ) {
            _hotspotDelayedInsert.parallelStream().forEach(hotspotsRepository::save);
            //hotspotsRepository.saveAll(_hotspotDelayedInsert);
            _hotspotDelayedInsert.clear();
        }

        list.clear();
        log.debug("Hotspot Async commit done");
        synchronized (syncRunning) {
            running = 0;
        }
    }


}
