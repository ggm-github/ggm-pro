package com.uway.sports.web.service;

import com.uway.sports.common.service.IBaseService;
import com.uway.sports.web.entity.dbentity.Advertisement;
import com.uway.sports.web.entity.paraentity.AdvertisementInfo;
import com.uway.sports.web.entity.paraentity.AdvertisementTable;

import java.util.List;

public interface IAdvertisementService extends IBaseService {
    List<AdvertisementInfo> getAdTable(AdvertisementTable adParam);
    int getAdCnt(AdvertisementTable adParam);
    int addAdvertisement(Advertisement adParam);
    int updateAdInfoBy(Advertisement adParam);
    Advertisement getAdInfoById(Integer id);
}
