package com.uway.sports.web.serviceImpl;

import com.uway.sports.common.redis.SharedRedisClient;
import com.uway.sports.common.service.BaseServiceImpl;
import com.uway.sports.web.dao.IAdvertisementDao;
import com.uway.sports.web.entity.dbentity.Advertisement;
import com.uway.sports.web.entity.paraentity.AdvertisementInfo;
import com.uway.sports.web.entity.paraentity.AdvertisementTable;
import com.uway.sports.web.service.IAdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Title
 * @Author liujingce
 * @Date 2019/4/1 13:49
 * @Discription
 */
@Service("iAdvertisementService")
public class AdvertisementServiceImpl extends BaseServiceImpl implements IAdvertisementService{
    @Autowired
    private IAdvertisementDao iAdvertisementDao;
    @Autowired
    private SharedRedisClient sharedRedisClient;
    public final static String ADVERTISEMENT = "Advertisement:";
    @Override
    public List<AdvertisementInfo> getAdTable(AdvertisementTable adParam) {
        return iAdvertisementDao.getAdTable(adParam);
    }

    @Override
    public int getAdCnt(AdvertisementTable adParam) {
        return iAdvertisementDao.getAdCnt(adParam);
    }

    @Override
    public int addAdvertisement(Advertisement adParam) {
        return iAdvertisementDao.addAdvertisement(adParam);
    }

    @Override
    public int updateAdInfoBy(Advertisement adParam) {
        sharedRedisClient.del(ADVERTISEMENT + adParam.getId());
        return iAdvertisementDao.updateAdInfoBy(adParam);
    }

    @Override
    public Advertisement getAdInfoById(Integer id) {
        return iAdvertisementDao.getAdInfoById(id);
    }
}
