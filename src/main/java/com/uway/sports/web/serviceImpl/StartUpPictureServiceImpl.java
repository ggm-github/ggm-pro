package com.uway.sports.web.serviceImpl;

import com.uway.sports.common.redis.SharedRedisClient;
import com.uway.sports.common.service.BaseServiceImpl;
import com.uway.sports.common.utils.JSONUtils;
import com.uway.sports.web.dao.IStartUpPictureDao;
import com.uway.sports.web.entity.CommConstantEntity;
import com.uway.sports.web.entity.dbentity.StartUpPicture;
import com.uway.sports.web.entity.paraentity.StartUpPictureRequest;
import com.uway.sports.web.service.IStartUpPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Auth: liujingce
 * Date: 2018/2/8 11:01
 * Title:
 * Describer:
 **/
@Service("startUpPicture")
@Transactional
public class StartUpPictureServiceImpl extends BaseServiceImpl implements IStartUpPictureService {
    @Autowired
    private IStartUpPictureDao startUpPictureDao;
    @Autowired
    private SharedRedisClient redisClient;
    @Override
    public List<StartUpPicture> getStartUpPictureList(StartUpPictureRequest param) {
        return startUpPictureDao.getStartUpPictureList(param);
    }

    @Override
    public int getStartUpPictureListCnt(StartUpPictureRequest param) {
        return startUpPictureDao.getStartUpPictureListCnt(param);
    }

    @Override
    public StartUpPicture getStartUpPictureInfoById(Integer id) {
        return startUpPictureDao.getStartUpPictureInfoById(id);
    }

    @Override
    public void addStartUpPicture(StartUpPicture param) {
        StartUpPictureRequest tmps = new StartUpPictureRequest();
        tmps.setPictureType(param.getPictureType());
        startUpPictureDao.deleteStartUpPicByStatus(tmps);
        //再新增
        startUpPictureDao.addStartUpPicture(param);
        //更新redis中，启动页的图片信息
        getStartUpPicToRedis();
    }

    @Override
    public void getStartUpPicToRedis() {
        StartUpPictureRequest param = new StartUpPictureRequest();
        param.setStatus(CommConstantEntity.STATUS_USED);
        List<StartUpPicture> list = startUpPictureDao.getStartUpPictureList(param);
        if (list != null){
            String picStr = JSONUtils.toJson(list);
            redisClient.set(CommConstantEntity.START_UP_PICTURE,picStr);
        }
    }

    /**
     * 修改图片状态为无效
     * @param param
     */
    @Override
    public void updateStartUpPictureStatus(StartUpPictureRequest param){
        StartUpPictureRequest tmps = new StartUpPictureRequest();
        tmps.setPictureId(param.getPictureId());
        startUpPictureDao.deleteStartUpPicByStatus(tmps);
        //更新redis中，启动页的图片信息
        getStartUpPicToRedis();
    }

    @Override
    public int updateStartUpPicInfo(StartUpPicture param) {
        int n = startUpPictureDao.updateStartUpPicInfo(param);
        if (n > 0){
            //更新redis中，启动页的图片信息
            getStartUpPicToRedis();
        }
        return n;
    }
}
