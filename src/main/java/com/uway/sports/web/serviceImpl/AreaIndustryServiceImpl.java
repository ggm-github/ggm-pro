package com.uway.sports.web.serviceImpl;

import com.uway.sports.common.redis.SharedRedisClient;
import com.uway.sports.common.service.BaseServiceImpl;
import com.uway.sports.common.utils.JSONUtils;
import com.uway.sports.common.utils.StringUtils;
import com.uway.sports.web.dao.IAreaIndustryDao;
import com.uway.sports.web.entity.dbentity.Area;
import com.uway.sports.web.entity.paraentity.AreaRequest;
import com.uway.sports.web.entity.paraentity.JsTreeEntity;
import com.uway.sports.web.service.IAreaIndustryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title
 * @Author liujingce
 * @Date 2019/3/23 16:58
 * @Discription
 */
@Service("iAreaIndustryService")
public class AreaIndustryServiceImpl extends BaseServiceImpl implements IAreaIndustryService {
    @Autowired
    private IAreaIndustryDao iAreaIndustryDao;
    @Autowired
    private SharedRedisClient sharedRedisClient;

    public final static String AREA_JSON_KEY = "areaTreeJson";
    public final static String AREA_ALL_KEY = "allAreaJson";
    public final static String INDUSTRY_JSON_KEY = "industryTreeJson";

    @Override
    public List<JsTreeEntity> getAreaTree() {
        String areaJson = sharedRedisClient.get(AREA_JSON_KEY);
        System.out.println(areaJson);
        if (StringUtils.isBlank(areaJson)){
            JsTreeEntity note1 = new JsTreeEntity("areaTree","区域");
            note1.getState().put("disabled",true);
            note1.getState().put("opened",true);
            List<JsTreeEntity> result = iAreaIndustryDao.getAreaTree(0);
            note1.setChildren(result);
            List<JsTreeEntity> list = new ArrayList<>();
            list.add(note1);
            sharedRedisClient.set(AREA_JSON_KEY, JSONUtils.toJson(list));
            return list;
        }else{
            return JSONUtils.parseArray(areaJson,JsTreeEntity.class);
        }
    }

    @Override
    public List<Area> getAreaList(AreaRequest area) {
        return iAreaIndustryDao.getAreaList(area);
    }

    @Override
    public int getAreaCnt(AreaRequest area) {
        return iAreaIndustryDao.getAreaCnt(area);
    }

    @Override
    public Area getAreaById(Integer areaId) {
        return iAreaIndustryDao.getAreaById(areaId);
    }

    @Override
    public int updateAreaBy(Area area) {
        int n = iAreaIndustryDao.updateAreaBy(area);
        if (n > 0){
            sharedRedisClient.del(AREA_JSON_KEY);
            sharedRedisClient.del(AREA_ALL_KEY);
        }
        return n;
    }

    @Override
    public Map<String,String> addArea(Area area) {
        Map<String,String> result = new HashMap<>();
        Area checkInfo = getAreaById(area.getAreaId());
        if (checkInfo != null){
            result.put("success","0");
            result.put("message","区域ID已存在【"+checkInfo.getAreaName()+"】");
            return result;
        }
        iAreaIndustryDao.addArea(area);
        sharedRedisClient.del(AREA_JSON_KEY);
        sharedRedisClient.del(AREA_ALL_KEY);
        result.put("success","1");
        return result;
    }

    @Override
    public List<JsTreeEntity> getIndustryTree() {
        String industryJson = sharedRedisClient.get(INDUSTRY_JSON_KEY);
        if (StringUtils.isBlank(industryJson)){
            JsTreeEntity note1 = new JsTreeEntity("industryTree","行业");
            note1.getState().put("disabled",true);
            note1.getState().put("opened",true);
            List<JsTreeEntity> result = iAreaIndustryDao.getIndustryTree(0);
            note1.setChildren(result);
            List<JsTreeEntity> list = new ArrayList<>();
            list.add(note1);
            sharedRedisClient.set(INDUSTRY_JSON_KEY, JSONUtils.toJson(list));
            return list;
        }else{
            return JSONUtils.parseArray(industryJson,JsTreeEntity.class);
        }
    }
}
