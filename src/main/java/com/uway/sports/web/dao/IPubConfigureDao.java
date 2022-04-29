package com.uway.sports.web.dao;


import com.uway.sports.common.dao.MyBatisDao;
import com.uway.sports.web.entity.dbentity.PubConfigure;

import java.util.List;
@MyBatisDao
public interface IPubConfigureDao {

	List<PubConfigure> getPubConfigureList(PubConfigure param);

	int getPubConfigureCnt(PubConfigure param);

	int updatePubConfigure(PubConfigure param);

}
