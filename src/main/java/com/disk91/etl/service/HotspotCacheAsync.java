package com.disk91.etl.service;

import com.disk91.etl.data.object.Hotspot;
import com.disk91.etl.data.repository.HotspotsRepository;
import fr.ingeniousthings.tools.Now;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

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

    @Async
    public void processAsyncCacheCommit(List<Hotspot> list) {
        synchronized (running) {
            if (running == 1) {
                log.error(">> Async commit - try to reenter");
                list.clear();
                return;
            }
            running = 1;
        }
        long lastProgress = Now.NowUtcMs();
        total = list.size();
        processed=0;
        log.info(">> Async commit "+total+" elements");
        for ( Hotspot h : list ) {
            hotspotsRepository.save(h);
            processed++;
            if ( (Now.NowUtcMs() - lastProgress) > 10_000) {
                log.info(">> Async commit - progress "+getProgress()+"%");
                lastProgress = Now.NowUtcMs();
            }
        }
        list.clear();
        log.info(">> Async commit done");
        synchronized (running) {
            running = 0;
        }
    }


}
