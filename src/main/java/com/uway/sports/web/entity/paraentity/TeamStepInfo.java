package com.uway.sports.web.entity.paraentity;

import java.util.Date;

public class TeamStepInfo {
    private Integer matchId;
    private Integer itemId;
    private Integer teamId;
    private Integer userId;
    private String userChName;
    private String tJson;
    private Integer rankNum;
    private Integer totalStepNum;
    private Integer totalMember;
    private Integer aveStepNum;
    private Integer maxStep = 20000;
    private Integer departId;
    private Date startDate;
    private Date endDate;

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String gettJson() {
        return tJson;
    }

    public void settJson(String tJson) {
        this.tJson = tJson;
    }

    public Integer getRankNum() {
        return rankNum;
    }

    public void setRankNum(Integer rankNum) {
        this.rankNum = rankNum;
    }

    public Integer getTotalStepNum() {
        return totalStepNum;
    }

    public void setTotalStepNum(Integer totalStepNum) {
        this.totalStepNum = totalStepNum;
    }

    public Integer getTotalMember() {
        return totalMember;
    }

    public void setTotalMember(Integer totalMember) {
        this.totalMember = totalMember;
    }

    public Integer getAveStepNum() {
        return aveStepNum;
    }

    public void setAveStepNum(Integer aveStepNum) {
        this.aveStepNum = aveStepNum;
    }

    public Integer getMaxStep() {
        return maxStep;
    }

    public void setMaxStep(Integer maxStep) {
        this.maxStep = maxStep;
    }

    public Integer getDepartId() {
        return departId;
    }

    public void setDepartId(Integer departId) {
        this.departId = departId;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getUserChName() {
        return userChName;
    }

    public void setUserChName(String userChName) {
        this.userChName = userChName;
    }
}
