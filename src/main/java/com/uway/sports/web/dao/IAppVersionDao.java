package com.uway.sports.web.dao;

import com.uway.sports.common.dao.MyBatisDao;
import com.uway.sports.web.entity.dbentity.AppVersion;
import com.uway.sports.web.entity.paraentity.AppVersionRequest;

import java.util.List;

@MyBatisDao
public interface IAppVersionDao {
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
     * Date: 2018/2/7 15:00
     * Title: 添加app版本
     * Describer:
    **/
    void addAppVersion(AppVersion param);
    /**
     * Auth: liujingce
     * Date: 2018/2/7 18:02
     * Title: 修改版本信息
     * Describer:
    **/
    void updateAppVersion(AppVersion param);

}
