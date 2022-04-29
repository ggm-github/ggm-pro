package com.uway.sports.web.service;

import com.uway.sports.common.utils.RestResponse;
import com.uway.sports.web.entity.dbentity.UrlMenu;

import java.util.List;
import java.util.Map;

public interface ICommonTaskService {

    /**
     * 将app日志入库
     */
    void addAppLog();

    List<UrlMenu> getAllurlMenu();

    Map<String,Object> getRedisData();

    RestResponse updateBottomInfo(String iconType,String title);

    void updateRanking();

    void addVote();

    void updateTxTeamVideo();

    void updateExpireSort();

    void updateExpireOrder();

    void getAndUpdateRanking();

    void getMomentsHot();

    void pushSendAttention();
    void pushSendGood();
    void pushSendMoments();

    void getDepartStepInfoToRedis();
    void getTeamlistForNotWorksMoments();
}
