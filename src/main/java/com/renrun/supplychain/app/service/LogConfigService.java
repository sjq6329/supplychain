package com.renrun.supplychain.app.service;

import com.renrun.supplychain.app.entity.LogConfig;
import com.renrun.supplychain.app.mapper.LogConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chenjuqin on 2018/4/9.
 */
@Service
public class LogConfigService {

    @Autowired
    LogConfigMapper logConfigMapper;

    public void pclLogForNow(LogConfig logConfig){
        logConfigMapper.pclLogForNow(logConfig);
    }

    public void pclLogSingleForNow(LogConfig logConfig){
        logConfigMapper.pclLogSingleForNow(logConfig);
    }

    public void pclCTByHourForNow(LogConfig logConfig){
        logConfigMapper.pclCTByHourForNow(logConfig);
    }

    public void pclCTByDayForNow(LogConfig logConfig){
        logConfigMapper.pclCTByDayForNow(logConfig);
    }

    public void pclCTByMonthForNow(LogConfig logConfig){
        logConfigMapper.pclCTByMonthForNow(logConfig);
    }

    public void pclCTByTop(LogConfig logConfig){
        logConfigMapper.pclCTByTop(logConfig);
    }

    public void pclCTFromHourForNow(LogConfig logConfig){
        logConfigMapper.pclCTFromHourForNow(logConfig);
    }

    public void pclCTFromDayForNow(LogConfig logConfig){
        logConfigMapper.pclCTFromDayForNow(logConfig);
    }

    public void pclCTFromMonthForNow(LogConfig logConfig){
        logConfigMapper.pclCTFromMonthForNow(logConfig);
    }

}
