package com.uway.sports.web.service;

import com.uway.sports.common.service.IBaseService;
import com.uway.sports.web.entity.dbentity.AppVersion;
import com.uway.sports.web.entity.paraentity.AppVersionRequest;

import java.util.List;

/**
 * Auth: liujingce
 * Date: 2018/2/7 10:35
 * Title:
 * Describer:
 **/
public interface IAppVersionService extends IBaseService {
    /**
     * Auth: liujingce
     * Date: 2018/2/7 10:33
     * Title:  版本列表查询，以create_time排序，最新在前
     * @param param
     * @return List<AppVersion>
     * Describer:
     **/
    List<AppVersion> getAppVersionList(AppVersionRequest param);
    /**
     * Auth: liujingce
     * Date: 2018/2/7 14:57
     * Title: 添加app版本
     * @param param
     **/
    void addAppVersion(AppVersion param);
    /**
     * Auth: liujingce
     * Date: 2018/2/7 18:03
     * Title: 修改版本信息
     * Describer:
     **/
    void updateAppVersion(AppVersion param);
    /**
     * Auth: liujingce
     * Date: 2018/2/11 11:15
     * Title: 查询当前在用的版本信息，并将其存入redis中
     * Describer:
     **/
    void getVersionToRedis();
}
