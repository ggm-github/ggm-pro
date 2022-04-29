package com.uway.sports.web.dao;

import com.uway.sports.common.dao.MyBatisDao;
import com.uway.sports.web.entity.dbentity.Area;
import com.uway.sports.web.entity.dbentity.Team;
import com.uway.sports.web.entity.paraentity.AreaRequest;
import com.uway.sports.web.entity.paraentity.JsTreeEntity;
import com.uway.sports.web.entity.paraentity.TeamInfo;
import com.uway.sports.web.entity.paraentity.TeamTable;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao
public interface IAreaIndustryDao {

    List<JsTreeEntity> getAreaTree(@Param(value = "parentId") Integer parentId);
    List<JsTreeEntity> getIndustryTree(@Param(value = "parentId") Integer parentId);
    List<Area> getAreaList(AreaRequest area);
    int getAreaCnt(AreaRequest area);
    Area getAreaById(Integer areaId);
    int updateAreaBy(Area area);
    void addArea(Area area);
}
