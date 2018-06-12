package com.renrun.supplychain.app.mapper;

import com.renrun.supplychain.app.entity.LogConfig;

public interface LogConfigMapper {

    Integer insertDdos(String ip);

    // 记录一条数据日志条目
    void pclLogForNow(LogConfig logConfig);

    void pclLogSingleForNow(LogConfig logConfig);

    // 按小时来记录归总统计数据
    void pclCTByHourForNow(LogConfig logConfig);

    // 按天来记录归总统计数据
    void pclCTByDayForNow(LogConfig logConfig);

    // 按月来归总统计数据
    void pclCTByMonthForNow(LogConfig logConfig);

    // 最终的汇总统计数据
    void pclCTByTop(LogConfig logConfig);

    // 从小时单位开始往上(天-月-总)统计归总
    void pclCTFromHourForNow(LogConfig logConfig);

    // 从天为单位往上(月-总)归总
    void pclCTFromDayForNow(LogConfig logConfig);

    // 从月开始往上(还有一个总计)归总
    void pclCTFromMonthForNow(LogConfig logConfig);
}
