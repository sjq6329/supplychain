package com.renrun.supplychain.app.mapper;

import com.renrun.supplychain.app.entity.CtlogAll;
import com.renrun.supplychain.app.entity.CtlogAllSearch;
import com.renrun.supplychain.app.entity.CtlogTy;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CtlogAllMapper {

   Integer insertCtlogAll(CtlogAll ctlogAll);

   List<CtlogAll> getCtlogAllList(CtlogAllSearch ctlogAllSearch);

   Integer getCtlogAllCount(CtlogAllSearch ctlogAllSearch);

   CtlogTy getCtlogTy(@Param("ty") String ty);
}
