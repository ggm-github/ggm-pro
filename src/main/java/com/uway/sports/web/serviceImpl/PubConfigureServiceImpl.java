package com.uway.sports.web.serviceImpl;

import com.uway.sports.web.dao.IPubConfigureDao;
import com.uway.sports.web.entity.dbentity.PubConfigure;
import com.uway.sports.web.service.IPubConfigureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service("iPubConfigureService")
public class PubConfigureServiceImpl implements IPubConfigureService {
    @Autowired
    private IPubConfigureDao iPubConfigureDao;
    @Override
    public List<PubConfigure> getPubConfigureList(PubConfigure pubConfigure) {
        return iPubConfigureDao.getPubConfigureList(pubConfigure);
    }

    @Override
    public PubConfigure getPubConfigureOne(PubConfigure pubConfigure) {
        List<PubConfigure> list = iPubConfigureDao.getPubConfigureList(pubConfigure);
        if (list.size() == 0){
            return null;
        }
        return list.get(0);
    }

    @Override
    public Map<String, String> getPubConfigureMap(PubConfigure pubConfigure) {
        Map<String,String> result = new HashMap<>();
        List<PubConfigure> list = iPubConfigureDao.getPubConfigureList(pubConfigure);
        if (list.size() > 0){
            for (PubConfigure info : list){
                result.put(info.getParamValue(),info.getParamName());
            }
        }
        return result;
    }

    @Override
    public int getPubConfigureCnt(PubConfigure param) {
        return iPubConfigureDao.getPubConfigureCnt(param);
    }

    @Override
    public int updatePubConfigre(PubConfigure param) {
        return iPubConfigureDao.updatePubConfigure(param);
    }
}
