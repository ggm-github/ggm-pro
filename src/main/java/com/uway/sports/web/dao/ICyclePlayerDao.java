package com.uway.sports.web.dao;

import com.uway.sports.common.dao.MyBatisDao;
import com.uway.sports.web.entity.dbentity.CyclePlayerReference;
import com.uway.sports.web.entity.paraentity.CyclePlayerTable;

import java.util.HashMap;
import java.util.List;

@MyBatisDao
public interface ICyclePlayerDao {

    List<CyclePlayerReference> getCyclePlayerTable(CyclePlayerTable table);
    int getCyclePlayerTableCnt(CyclePlayerTable table);
    int updateCyclePlayerStatus(HashMap<String,Object> param);
    int addCyclePlayer(CyclePlayerReference param);
    CyclePlayerReference getCyclePlayerInfo(Integer id);
    int updateCyclePlayer(CyclePlayerReference info);

    /**
     * 栏目内容同步到redis中的几个查询方法
     * @param param
     * @return
     */
    List<HashMap<String,Object>> getCyclePlayerList(HashMap<String,Object> param);

}
