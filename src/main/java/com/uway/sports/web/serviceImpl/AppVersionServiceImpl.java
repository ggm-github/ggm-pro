package com.uway.sports.web.serviceImpl;

import com.uway.sports.common.redis.SharedRedisClient;
import com.uway.sports.common.service.BaseServiceImpl;
import com.uway.sports.common.utils.JSONUtils;
import com.uway.sports.common.utils.StringUtils;
import com.uway.sports.web.dao.IAppVersionDao;
import com.uway.sports.web.entity.CommConstantEntity;
import com.uway.sports.web.entity.dbentity.AppVersion;
import com.uway.sports.web.entity.paraentity.AppVersionRequest;
import com.uway.sports.web.service.IAppVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Auth: liujingce
 * Date: 2018/2/7 10:36
 * Title:
 * Describer:
 **/
@Service("appVersionService")
public class AppVersionServiceImpl extends BaseServiceImpl implements IAppVersionService {

    @Autowired
    private IAppVersionDao versionDao;
    @Autowired
    private SharedRedisClient redisClient;
    @Override
    public List<AppVersion> getAppVersionList(AppVersionRequest param) {
        return versionDao.getAppVersionList(param);
    }

    @Override
    public void addAppVersion(AppVersion param) {
        //新增版本信息
        versionDao.addAppVersion(param);
        //同步redis
        getVersionToRedis();
    }

    @Override
    public void updateAppVersion(AppVersion param) {
        //更新版本信息
        versionDao.updateAppVersion(param);
        //同步redis
        getVersionToRedis();
    }

    @Override
    public void getVersionToRedis() {
        AppVersionRequest param = new AppVersionRequest();
        param.setStatus(CommConstantEntity.STATUS_USED);
        List<AppVersion> list = versionDao.getAppVersionList(param);
        if (list != null){
            int index1 = 0;
            int index2 = 0;
            String lastVersion1 = null;
            String lastVersion2 = null;
            Map<String,String> versionMap = new HashMap<>();
            int versionType;
            for (AppVersion version:list){
                versionType = version.getVersionType();
                if (CommConstantEntity.APP_VERSION_TYPE_IOS == versionType){
                    if (StringUtils.isBlank(lastVersion1)){
                        lastVersion1 = version.getLastVersion();
                    }
                }else if (CommConstantEntity.APP_VERSION_TYPE_ANDROID == versionType){
                    if (StringUtils.isBlank(lastVersion2)){
                        lastVersion2 = version.getLastVersion();
                    }
                }else{
                    continue;
                }
                versionMap.put(version.getLastVersion()+":"+versionType,JSONUtils.toJson(version));
            }
            //先清空
            redisClient.del(CommConstantEntity.VERSION_INFO_MAP_KEY);
            redisClient.del(CommConstantEntity.LAST_VERSION_KEY_IOS);
            redisClient.del(CommConstantEntity.LAST_VERSION_KEY_ANDROID);
            //再添加
            redisClient.hmset(CommConstantEntity.VERSION_INFO_MAP_KEY,versionMap);
            redisClient.set(CommConstantEntity.LAST_VERSION_KEY_IOS,lastVersion1);
            redisClient.set(CommConstantEntity.LAST_VERSION_KEY_ANDROID,lastVersion2);
        }
    }
}
