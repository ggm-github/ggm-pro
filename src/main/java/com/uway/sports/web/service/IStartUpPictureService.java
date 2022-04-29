package com.uway.sports.web.service;

import com.uway.sports.common.service.IBaseService;
import com.uway.sports.web.entity.dbentity.StartUpPicture;
import com.uway.sports.web.entity.paraentity.StartUpPictureRequest;

import java.util.List;

/**
 * Auth: liujingce
 * Date: 2018/2/8 10:55
 * Title:
 * Describer:
 **/
public interface IStartUpPictureService extends IBaseService{
    /**
     * Auth: liujingce
     * Date: 2018/2/8 10:56
     * Title: 查询启动图列表
     * Describer:
    **/
    List<StartUpPicture> getStartUpPictureList(StartUpPictureRequest param);
    int getStartUpPictureListCnt(StartUpPictureRequest param);

    StartUpPicture getStartUpPictureInfoById(Integer id);
    /**
     * Auth: liujingce
     * Date: 2018/2/8 14:38
     * Title: 新增启动图
     * Describer:
    **/
    void addStartUpPicture(StartUpPicture param);
    /**
     * Auth: liujingce
     * Date: 2018/2/11 10:56
     * Title: 将当前启动页图片存入redis中
     * Describer:
    **/
    void getStartUpPicToRedis();

    /**
     * 修改图片状态为无效
     * @param param
     */
    void updateStartUpPictureStatus(StartUpPictureRequest param);

    int updateStartUpPicInfo(StartUpPicture param);
}
