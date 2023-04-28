package com.disk91.etl.service;

import com.disk91.etl.api.interfaces.HotspotStat;
import com.disk91.etl.data.object.Param;
import com.disk91.etl.data.repository.ParamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MiscService {

    @Autowired
    protected ParamRepository paramRepository;

    public HotspotStat getStats() {
        HotspotStat s = new HotspotStat();

        Param iotPocFile = paramRepository.findOneParamByParamName("aws_last_iotpoc_sync");
        if ( iotPocFile != null ) {
            long fileDate = Long.parseLong(iotPocFile.getStringValue().split("\\.")[1]);
            s.setLastFileDate(fileDate);
        }

        return s;
    }
}
