package com.uway.sports.web.entity.paraentity;

import com.uway.sports.web.entity.dbentity.MessagePush;
import com.uway.sports.web.entity.dbentity.User;

/**
 * Auth: liujingce
 * Date: 2018/4/3 18:45
 * Title:
 * Describer:
 **/
public class MessageResponse extends MessagePush{

    private String areaName;
    private String industryName;
    private String matchName;
    private String userNames;
    // 消息新增时，根据用户条件查询添加
    public User addMessageUser;

    public User getAddMessageUser() {
        return addMessageUser;
    }

    public void setAddMessageUser(User addMessageUser) {
        this.addMessageUser = addMessageUser;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    public String getMatchName() {
        return matchName;
    }

    public void setMatchName(String matchName) {
        this.matchName = matchName;
    }

    public String getUserNames() {
        return userNames;
    }

    public void setUserNames(String userNames) {
        this.userNames = userNames;
    }
}
