package com.uway.sports.web.service;

import com.uway.sports.web.entity.dbentity.PubConfigure;

import java.util.List;
import java.util.Map;

public interface IPubConfigureService {

    List<PubConfigure> getPubConfigureList(PubConfigure pubConfigure);

    PubConfigure getPubConfigureOne(PubConfigure pubConfigure);

    Map<String,String> getPubConfigureMap(PubConfigure pubConfigure);

    int getPubConfigureCnt(PubConfigure param);
    int updatePubConfigre(PubConfigure param);

}
