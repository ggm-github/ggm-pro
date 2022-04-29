package com.uway.sports.web.serviceImpl;

import com.uway.sports.common.redis.SharedRedisClient;
import com.uway.sports.common.utils.JSONUtils;
import com.uway.sports.common.utils.StringUtils;
import com.uway.sports.web.dao.IThemeInfoDao;
import com.uway.sports.web.entity.CommConstantEntity;
import com.uway.sports.web.entity.dbentity.ThemeInfo;
import com.uway.sports.web.entity.dbentity.ThemeMomentsRel;
import com.uway.sports.web.entity.paraentity.ThemeInfoResult;
import com.uway.sports.web.entity.paraentity.ThemeInfoTable;
import com.uway.sports.web.service.IThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("iThemeService")
public class ThemeServiceImpl implements IThemeService {
    @Autowired
    private IThemeInfoDao iThemeInfoDao;
    @Autowired
    private SharedRedisClient sharedRedisClient;

    @Override
    public ThemeInfoResult getThemeInfoById(Integer themeId) {
        String themeJson = sharedRedisClient.hget(CommConstantEntity.THEME_KEY,CommConstantEntity.THEME_HKEY_HEAD + themeId);
        ThemeInfoResult info;
        if (StringUtils.isNotEmpty(themeJson)){
            info = JSONUtils.fromJson(themeJson,ThemeInfoResult.class);
        }else{
            info = iThemeInfoDao.getThemeInfoById(themeId);
        }
        return info;
    }

    @Override
    public int updateThemeInfo(ThemeInfo themeInfo) {
        int n = iThemeInfoDao.updateThemeInfo(themeInfo);
        sharedRedisClient.hdel(CommConstantEntity.THEME_KEY,CommConstantEntity.THEME_HKEY_HEAD + themeInfo.getId());
        sharedRedisClient.hdel(CommConstantEntity.THEME_MOMENTS_CNT_KEY,CommConstantEntity.THEME_MOMENTS_CNT_HKEY_HEAD + themeInfo.getId());
        return n;
    }

    @Override
    public int updateThemeInfoBy(ThemeInfo themeInfo) {
        int n = iThemeInfoDao.updateThemeInfoBy(themeInfo);
        sharedRedisClient.hdel(CommConstantEntity.THEME_KEY,CommConstantEntity.THEME_HKEY_HEAD + themeInfo.getId());
        sharedRedisClient.hdel(CommConstantEntity.THEME_MOMENTS_CNT_KEY,CommConstantEntity.THEME_MOMENTS_CNT_HKEY_HEAD + themeInfo.getId());
        return n;
    }

    @Override
    public int addThemeInfo(ThemeInfo themeInfo) {
        return iThemeInfoDao.addThemeInfo(themeInfo);
    }

    @Override
    public List<ThemeInfoResult> getThemeInfoList(ThemeInfoTable param) {
        return iThemeInfoDao.getThemeInfoList(param);
    }

    @Override
    public int getThemeInfoCnt(ThemeInfoTable param) {
        return iThemeInfoDao.getThemeInfoCnt(param);
    }

    @Override
    public int updateThemeMomentsCnt(Integer id, int cnt) {
        int n = iThemeInfoDao.updateThemeMomentsCnt(id,cnt);
        if (n > 0){
            String commentsCnt = sharedRedisClient.hget(CommConstantEntity.THEME_MOMENTS_CNT_KEY,CommConstantEntity.THEME_MOMENTS_CNT_HKEY_HEAD + id);
            if (StringUtils.isEmpty(commentsCnt)){
                commentsCnt = "0";
            }
            sharedRedisClient.hset(CommConstantEntity.THEME_MOMENTS_CNT_KEY,CommConstantEntity.THEME_MOMENTS_CNT_HKEY_HEAD + id,(Integer.parseInt(commentsCnt) + cnt) + "",null);
        }
        return n;
    }

    @Override
    public List<ThemeMomentsRel> getThemeMomentsRelList(ThemeMomentsRel param) {
        return iThemeInfoDao.getThemeMomentsRelList(param);
    }

    @Override
    public int addOrModiThemeMomentsRel(List<Integer> themeIds, Integer videoId) {
        ThemeMomentsRel param = new ThemeMomentsRel();
        param.setVideoId(videoId);
        List<ThemeMomentsRel> list = getThemeMomentsRelList(param);
        if (list.size() > 0){
            List<Integer> themeIdInList = new ArrayList<>();
            for(ThemeMomentsRel rel : list){
                themeIdInList.add(rel.getThemeId());
                // 若查询到的主题id，不存在与传入的id中，将其置为无效
                if (!themeIds.contains(rel.getThemeId())){
                    param.setStatus(0);
                    param.setThemeId(rel.getThemeId());
                    iThemeInfoDao.updateThemeMomentsRel(param);
                }else if (themeIds.contains(rel.getThemeId()) && rel.getStatus() != 1){
                    // 主题存在，但处于无效状态，将其修改为有效
                    param.setStatus(1);
                    param.setThemeId(rel.getThemeId());
                    iThemeInfoDao.updateThemeMomentsRel(param);
                }
            }
            for(Integer themeId : themeIds){
                if (!themeIdInList.contains(themeId)){
                    param.setStatus(1);
                    param.setThemeId(themeId);
                    iThemeInfoDao.addThemeMomentsRel(param);
                }
            }
        }else{
            for(int i=0;i<themeIds.size();i++){
                param.setThemeId(themeIds.get(i));
                param.setStatus(1);
                iThemeInfoDao.addThemeMomentsRel(param);
            }
        }
        return 0;
    }

    @Override
    public int updateThemeMomentsStatus(Integer id, Integer status) {
        ThemeMomentsRel param = new ThemeMomentsRel();
        param.setId(id);
        param.setStatus(status);
        return iThemeInfoDao.updateThemeMomentsRel(param);
    }

/*
    public List<ThemeInfoResult> getThemeListFromRedisByIds(List<Integer> themeIds){
        List<ThemeInfoResult> list = new ArrayList<>();
        if (themeIds != null && themeIds.size() > 0){
            ThemeInfoResult info;
            String infoStr;
            String momentsCnt;
            // 从redis中，获取话题的信息，若没有再查询
            for(Integer id : themeIds){
                infoStr = sharedRedisClient.hget(BaseDataEntity.THEME_KEY,BaseDataEntity.THEME_HKEY_HEAD + id);
                momentsCnt = sharedRedisClient.hget(BaseDataEntity.THEME_MOMENTS_CNT_KEY,BaseDataEntity.THEME_MOMENTS_CNT_HKEY_HEAD + id);
                if (StringUtils.isNotEmpty(infoStr)){
                    info = JSONUtils.fromJson(infoStr,ThemeInfoResult.class);
                    info.setMomentsCnt(StringUtils.isEmpty(momentsCnt)?0:Integer.parseInt(momentsCnt));
                    list.add(info);
                }else{
                    info = themeInfoMapper.getThemeInfoById(id);
                    if (info.getMomentsCnt() != null){
                        sharedRedisClient.hset(BaseDataEntity.THEME_MOMENTS_CNT_KEY,BaseDataEntity.THEME_MOMENTS_CNT_HKEY_HEAD + id,info.getMomentsCnt() + "",null);
                    }
                    list.add(info);
                }
            }
        }
        return list;
    }

 */
}
