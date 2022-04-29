package com.uway.sports.web.service;

import com.uway.sports.common.service.IBaseService;
import com.uway.sports.web.entity.dbentity.Area;
import com.uway.sports.web.entity.dbentity.MatchNews;
import com.uway.sports.web.entity.dbentity.SportsNews;
import com.uway.sports.web.entity.paraentity.*;

import java.util.List;
import java.util.Map;

public interface IAreaIndustryService extends IBaseService {

    /*******************  区域    start *************************************/
    List<JsTreeEntity> getAreaTree();
    List<Area> getAreaList(AreaRequest area);
    int getAreaCnt(AreaRequest area);
    Area getAreaById(Integer areaId);
    int updateAreaBy(Area area);
    Map<String,String> addArea(Area area);
    /*******************  区域    end *************************************/


    /*******************  行业    start *************************************/
    List<JsTreeEntity> getIndustryTree();
    /*******************  行业    end *************************************/
}
