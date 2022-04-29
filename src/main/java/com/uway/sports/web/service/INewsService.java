package com.uway.sports.web.service;

import com.uway.sports.common.service.IBaseService;
import com.uway.sports.web.entity.dbentity.MatchGroup;
import com.uway.sports.web.entity.dbentity.MatchNews;
import com.uway.sports.web.entity.dbentity.SportsNews;
import com.uway.sports.web.entity.paraentity.*;
import org.apache.xmlbeans.impl.regex.Match;

import java.util.List;

public interface INewsService extends IBaseService {

    /*******************  新闻    start *************************************/
    int addSportsNews(SportsNews sportsNews);
    int updateSportsNews(SportsNews sportsNews);
    int updateSportsNewsBy(SportsNews sportsNews);
    List<SportsNewsInfo> getSportsNewsTable(SportsNewsTable sportsNewsTable);
    int getSportsNewsCnt(SportsNewsTable sportsNewsTable);
    SportsNewsInfo getSportsNewsById(Integer id);
    /**
     * @Title 将新闻到期的权重归0
     * @Date 2019/4/4 9:43
     */
    int updateSportsNewsExpireSort();
    /*******************  新闻    end *************************************/


    /*******************  赛事    start *************************************/
    int addMatchNews(MatchNews matchNews);
    int updateMatchNews(MatchNews matchNews);
    int updateMatchNewsBy(MatchNews matchNews);
    List<MatchNewsInfo> getMatchNewsTable(MatchNewsTable matchNewsTable);
    int getMatchNewsCnt(MatchNewsTable matchNewsTable);
    MatchNewsInfo getMatchNewsById(Integer id);
    /**
     * @Title 将赛事到期的权重归0
     * @Date 2019/4/4 9:43
     */
    int updateMatchNewsExpireSort();
    /*******************  赛事    end *************************************/
    /**
     * 给新闻添加阅读量
     * @param id
     * @return
     */
    int updateNewsReadCnt(Integer id);


    /******************* 赛事小组 start ********************************/
    /**
     * @Title 获取赛事小组列表
     * @Author liujingce
     * @Date 2019/4/8 10:30
     * @Param matchGroupTable
     */
    List<MatchGroup> getMatchGroupList(MatchGroupTable matchGroupTable);
    /**
     * @Title 获取赛事小组数量
     * @Author liujingce
     * @Date 2019/4/8 10:30
     * @Param matchGroupTable
     */
    int getMatchGroupCnt(MatchGroupTable matchGroupTable);
    /**
     * @Title 根据id 获取赛事小组信息
     * @Author liujingce
     * @Date 2019/4/8 10:30
     * @Param id
     */
    MatchGroup getMatchGroupById(Integer id);
    /**
     * @Title 新增赛事小组
     * @Author liujingce
     * @Date 2019/4/8 10:30
     * @Param matchGroup
     */
    int addMatchGroup(MatchGroup matchGroup);
    /**
     * @Title 修改赛事小组
     * @Author liujingce
     * @Date 2019/4/8 10:30
     * @Param matchGroup
     */
    int updateMatchGroupInfo(MatchGroup matchGroup);
    /**
     * @Title 修改赛事小组
     * @Author liujingce
     * @Date 2019/4/8 10:30
     * @Param matchGroup
     */
    int updateMatchGroupBy(MatchGroup matchGroup);
    /******************* 赛事小组 end ********************************/

}
