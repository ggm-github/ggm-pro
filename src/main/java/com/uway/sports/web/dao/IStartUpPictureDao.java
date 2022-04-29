package com.uway.sports.web.dao;

import com.uway.sports.common.dao.MyBatisDao;
import com.uway.sports.web.entity.dbentity.StartUpPicture;
import com.uway.sports.web.entity.paraentity.StartUpPictureRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Auth: liujingce
 * Date: 2018/2/8 10:49
 * Title:
 * Describer:
 **/
@MyBatisDao
public interface IStartUpPictureDao {
    /**
     * Auth: liujingce
     * Date: 2018/2/8 14:41
     * Title: 查询启动图列表
     * Describer:
    **/
    List<StartUpPicture> getStartUpPictureList(StartUpPictureRequest param);
    int getStartUpPictureListCnt(StartUpPictureRequest param);
    StartUpPicture getStartUpPictureInfoById(Integer id);
    /**
     * Auth: liujingce
     * Date: 2018/2/8 14:42
     * Title: 将有效的记录置为无效
     *
    **/
    void deleteStartUpPicByStatus(StartUpPictureRequest param);
    /**
     * Auth: liujingce
     * Date: 2018/2/8 14:46
     * Title: 新增记录
     * Describer:
    **/
    void addStartUpPicture(StartUpPicture param);

    int updateStartUpPicInfo(StartUpPicture param);
}
