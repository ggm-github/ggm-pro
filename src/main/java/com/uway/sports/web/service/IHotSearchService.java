package com.uway.sports.web.service;

import com.uway.sports.web.entity.dbentity.HotSearch;
import com.uway.sports.web.entity.paraentity.HotSearchTable;

import java.util.HashMap;
import java.util.List;

/**
 * Auth: liujingce
 * Date: 2018/1/29 15:27
 * Title:
 * Describer:
 **/
public interface IHotSearchService {
    List<HotSearch> getHotSearchTable(HotSearchTable table);
    Integer getHotSearchTableCnt(HotSearchTable table);
    void updateHotSearchStatus(HashMap<String, Object> param);
    HashMap<String,String> addHotSearch(HotSearch param);
    HotSearch getHotSearchInfo(Integer id);
    HashMap<String,String> updateHotSearchInfo(HotSearch param);
}
