package com.uway.sports.web.entity.paraentity;

import com.uway.sports.web.entity.dbentity.Advertisement;

/**
 * @Title
 * @Author liujingce
 * @Date 2019/4/1 11:16
 * @Discription
 */
public class AdvertisementInfo extends Advertisement {

    private String insertUserName;

    public String getInsertUserName() {
        return insertUserName;
    }

    public void setInsertUserName(String insertUserName) {
        this.insertUserName = insertUserName;
    }
}
