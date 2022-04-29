package com.uway.sports.web.service;

import com.uway.sports.common.service.IBaseService;
import com.uway.sports.web.entity.dbentity.CyclePlayerReference;
import com.uway.sports.web.entity.paraentity.CyclePlayerTable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ICyclePlayerService extends IBaseService{
    List<CyclePlayerReference> getCyclePlayerTable(CyclePlayerTable table);
    Integer getCyclePlayerTableCnt(CyclePlayerTable table);
    void updateCyclePlayerStatus(HashMap<String,Object> param);
    Map<String,String> addCyclePlayer(CyclePlayerReference param);
    CyclePlayerReference getCyclePlayerInfo(Integer id);
    Map<String,String> updateCyclePlayerInfo(CyclePlayerReference param);
}
