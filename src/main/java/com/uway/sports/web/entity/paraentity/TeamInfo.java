package com.uway.sports.web.entity.paraentity;

import com.uway.sports.web.entity.dbentity.Team;

/**
 * @Title
 * @Author liujingce
 * @Date 2019/3/22 20:03
 * @Discription
 */
public class TeamInfo extends Team {

    private String teamAreaName;
    private String teamIndustryName;
    private String matchAreaName;
    private String matchIndustryName;
    private String matchName;
    private String groupName;
    private String teamLeaderName;
    private Integer voteIncrease;
    private Integer themeId;

    public Integer getVoteIncrease() {
        return voteIncrease;
    }

    public void setVoteIncrease(Integer voteIncrease) {
        this.voteIncrease = voteIncrease;
    }

    public String getTeamLeaderName() {
        return teamLeaderName;
    }

    public void setTeamLeaderName(String teamLeaderName) {
        this.teamLeaderName = teamLeaderName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getMatchAreaName() {
        return matchAreaName;
    }

    public void setMatchAreaName(String matchAreaName) {
        this.matchAreaName = matchAreaName;
    }

    public String getMatchIndustryName() {
        return matchIndustryName;
    }

    public void setMatchIndustryName(String matchIndustryName) {
        this.matchIndustryName = matchIndustryName;
    }

    public String getTeamAreaName() {
        return teamAreaName;
    }

    public void setTeamAreaName(String teamAreaName) {
        this.teamAreaName = teamAreaName;
    }

    public String getTeamIndustryName() {
        return teamIndustryName;
    }

    public void setTeamIndustryName(String teamIndustryName) {
        this.teamIndustryName = teamIndustryName;
    }

    public String getMatchName() {
        return matchName;
    }

    public void setMatchName(String matchName) {
        this.matchName = matchName;
    }

    public Integer getThemeId() {
        return themeId;
    }

    public void setThemeId(Integer themeId) {
        this.themeId = themeId;
    }
}
