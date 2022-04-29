package com.uway.sports.web.serviceImpl;

import com.uway.sports.common.redis.SharedRedisClient;
import com.uway.sports.web.dao.IHotSearchDao;
import com.uway.sports.web.entity.dbentity.HotSearch;
import com.uway.sports.web.entity.paraentity.HotSearchTable;
import com.uway.sports.web.service.IHotSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

/**
 * Auth: liujingce
 * Date: 2018/1/29 15:28
 * Title:
 * Describer:
 **/
@Service("hotSearchService")
@Transactional
public class HotSearchServiceImpl implements IHotSearchService {
    @Autowired
    IHotSearchDao hotSearchDao;

    @Autowired
    SharedRedisClient sharedRedisClient;



    @Override
    public List<HotSearch> getHotSearchTable(HotSearchTable table) {
        return hotSearchDao.getHotSearchTable(table);
    }

    @Override
    public Integer getHotSearchTableCnt(HotSearchTable table) {
        return hotSearchDao.getHotSearchTableCnt(table);
    }

    @Override
    public void updateHotSearchStatus(HashMap<String, Object> param) {
        hotSearchDao.updateHotSearchStatus(param);
    }

    @Override
    public HashMap<String,String> addHotSearch(HotSearch param) {
        HashMap<String,String> retmap = new HashMap<>();
        //校验热搜词是否重复（按类型区分）
        HotSearch chkParam = new HotSearch(param.getHotName(),param.getHotType(),param.getStatus());
        List<HotSearch> list = hotSearchDao.getHotSearchList(chkParam);
        if(list != null && list.size() > 0){
            retmap.put("resultCode","0");
            retmap.put("resultMsg","存在重复的热搜词，请核实");
            return retmap;
        }
        hotSearchDao.addHotSearch(param);
        retmap.put("resultCode","1");
        retmap.put("resultMsg","新增成功");
        return retmap;
    }

    @Override
    public HotSearch getHotSearchInfo(Integer id) {
        return hotSearchDao.getHotSearchInfo(id);
    }

    @Override
    public HashMap<String,String> updateHotSearchInfo(HotSearch param) {
        HashMap<String,String> retmap = new HashMap<>();
        //校验热搜词是否重复（按类型区分）
        HotSearch chkParam = new HotSearch(param.getHotName(),param.getHotType(),param.getStatus());
        List<HotSearch> list = hotSearchDao.getHotSearchList(chkParam);
        if(list != null && list.size() == 1){
            int idCheck = list.get(0).getId();
            int id = param.getId();
            if (idCheck != id){
                retmap.put("resultCode","0");
                retmap.put("resultMsg","存在重复的热搜词，请核实");
                return retmap;
            }
        }else if(list.size() > 1){
            retmap.put("resultCode","0");
            retmap.put("resultMsg","存在重复的热搜词，请核实");
            return retmap;
        }
        //不重复修改
        hotSearchDao.updateHotSearch(param);
        retmap.put("resultCode","1");
        retmap.put("resultMsg","修改成功");
        return retmap;
    }
}
