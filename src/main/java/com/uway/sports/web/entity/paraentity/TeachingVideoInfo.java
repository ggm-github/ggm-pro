package com.uway.sports.web.entity.paraentity;

import com.uway.sports.web.entity.dbentity.TeachingVideo;

/**
 * @Title
 * @Author liujingce
 * @Date 2019/3/28 20:33
 * @Discription
 */
public class TeachingVideoInfo extends TeachingVideo {

    private String insertUserName;
    private String checkUserName;

    public String getCheckUserName() {
        return checkUserName;
    }

    public void setCheckUserName(String checkUserName) {
        this.checkUserName = checkUserName;
    }

    public String getInsertUserName() {
        return insertUserName;
    }

    public void setInsertUserName(String insertUserName) {
        this.insertUserName = insertUserName;
    }
}
