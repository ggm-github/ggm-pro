package com.uway.sports.web.entity.paraentity;

import com.uway.sports.web.entity.dbentity.SportsNews;

/**
 * @Title
 * @Author liujingce
 * @Date 2019/3/20 16:01
 * @Discription
 */
public class SportsNewsInfo extends SportsNews {

    private String optUserName;

    public String getOptUserName() {
        return optUserName;
    }

    public void setOptUserName(String optUserName) {
        this.optUserName = optUserName;
    }
}
