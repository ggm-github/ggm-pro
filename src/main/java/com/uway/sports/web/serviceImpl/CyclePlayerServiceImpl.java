package com.uway.sports.web.serviceImpl;

import com.uway.sports.common.redis.SharedRedisClient;
import com.uway.sports.common.service.BaseServiceImpl;
import com.uway.sports.common.utils.JSONUtils;
import com.uway.sports.web.dao.ICyclePlayerDao;
import com.uway.sports.web.entity.CommConstantEntity;
import com.uway.sports.web.entity.dbentity.CyclePlayerReference;
import com.uway.sports.web.entity.paraentity.CyclePlayerTable;
import com.uway.sports.web.service.ICyclePlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title
 * @Author liujingce
 * @Date 2019/3/26 11:01
 * @Discription
 */
@Service("iCyclePlayerService")
public class CyclePlayerServiceImpl extends BaseServiceImpl implements ICyclePlayerService {
    @Autowired
    private ICyclePlayerDao iCyclePlayerDao;

    @Override
    public List<CyclePlayerReference> getCyclePlayerTable(CyclePlayerTable table) {
        return iCyclePlayerDao.getCyclePlayerTable(table);
    }

    @Override
    public Integer getCyclePlayerTableCnt(CyclePlayerTable table) {
        return iCyclePlayerDao.getCyclePlayerTableCnt(table);
    }

    @Override
    public void updateCyclePlayerStatus(HashMap<String, Object> param) {
        iCyclePlayerDao.updateCyclePlayerStatus(param);
    }

    @Override
    public Map<String, String> addCyclePlayer(CyclePlayerReference param) {
        iCyclePlayerDao.addCyclePlayer(param);
        return null;
    }

    @Override
    public CyclePlayerReference getCyclePlayerInfo(Integer id) {
        return iCyclePlayerDao.getCyclePlayerInfo(id);
    }

    @Override
    public Map<String, String> updateCyclePlayerInfo(CyclePlayerReference param) {
        iCyclePlayerDao.updateCyclePlayer(param);
        return null;
    }
}
