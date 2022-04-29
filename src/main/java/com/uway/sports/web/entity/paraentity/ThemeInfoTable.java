package com.uway.sports.web.entity.paraentity;

import com.uway.sports.web.entity.TableEntity;

public class ThemeInfoTable extends TableEntity {
    private Integer id;
    private Integer themeType;
    private Integer matchId;
    private String themeTitle;
    private Integer status;
    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getThemeType() {
        return themeType;
    }

    public void setThemeType(Integer themeType) {
        this.themeType = themeType;
    }

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    public String getThemeTitle() {
        return themeTitle;
    }

    public void setThemeTitle(String themeTitle) {
        this.themeTitle = themeTitle;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
