package com.uway.sports.web.dao;

import com.uway.sports.common.dao.MyBatisDao;
import com.uway.sports.web.entity.dbentity.Advertisement;
import com.uway.sports.web.entity.paraentity.AdvertisementInfo;
import com.uway.sports.web.entity.paraentity.AdvertisementTable;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao
public interface IAdvertisementDao {

	List<AdvertisementInfo> getAdTable(AdvertisementTable adParam);
	int getAdCnt(AdvertisementTable adParam);
	int addAdvertisement(Advertisement adParam);
	int updateAdInfoBy(Advertisement adParam);
	Advertisement getAdInfoById(@Param(value="id") Integer id);
	

}
