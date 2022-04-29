package com.uway.sports.web.dao;

import com.uway.sports.common.dao.MyBatisDao;
import com.uway.sports.web.entity.dbentity.HotSearch;
import com.uway.sports.web.entity.paraentity.HotSearchTable;

import java.util.HashMap;
import java.util.List;

/**
 * Auth: liujingce
 * Date: 2018/1/29 15:06
 * Title:
 * Describer:
 **/
@MyBatisDao
public interface IHotSearchDao {
    /**
     * Auth: liujingce
     * Date: 2018/1/29 15:09
     * Title: 热搜词管理功能的列表查询
     * Describer:
    **/
    List<HotSearch> getHotSearchTable(HotSearchTable table);
    /**
     * Auth: liujingce
     * Date: 2018/1/29 15:44
     * Title:热搜词管理功能的列表数量查询
     *Describer:
    **/
    Integer getHotSearchTableCnt(HotSearchTable table);

    /**
     * Auth: liujingce
     * Date: 2018/2/2 19:44
     * Title:条件查询，用于唯一性匹配
     *Describer:
     **/
    List<HotSearch> getHotSearchList(HotSearch param);
    /**
     * Auth: liujingce
     * Date: 2018/1/29 20:19
     * Title: 热搜词的删除、重启操作（将 status 置为 0 或 1）
     * @param param
     * Describer:
    **/
    void updateHotSearchStatus(HashMap<String, Object> param);
    /**
     * Auth: liujingce
     * Date: 2018/1/31 13:41
     * Title: 新增热搜词
     * Describer:
    **/
    void addHotSearch(HotSearch param);
    /**
     * Auth: liujingce
     * Date: 2018/1/31 19:21
     * Title: 根据id查询详细信息
     * @param id
     * @return HotSearch
     * Describer:
    **/
    HotSearch getHotSearchInfo(Integer id);
    /**
     * Auth: liujingce
     * Date: 2018/1/31 20:13
     * Title: 修改信息
     * result: map={"resultCode":"1","resultMsg":""}
     * Describer:
    **/
    void updateHotSearch(HotSearch info);
}
