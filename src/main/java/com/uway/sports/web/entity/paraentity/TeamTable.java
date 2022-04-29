package com.uway.sports.web.entity.paraentity;

import com.uway.sports.web.entity.TableEntity;

import java.util.Date;
import java.util.List;

/**
 * @Title
 * @Author liujingce
 * @Date 2019/3/22 20:00
 * @Discription
 */
public class TeamTable extends TableEntity {

    private String teamNo;
    private Integer teamAreaId;
    private Integer teamIndustryId;
    private Integer matchAreaId;
    private Integer matchIndustryId;
    private String teamName;
    private String matchName;
    private Integer isVote;
    private Integer status;
    private Integer matchId;
    private Integer checkStatus;
    private Integer teamLeaderid;

    // 用来批量停止投票的参数
    private List<Integer> ids;
    private Integer isVoteParam;
    // 投票增量开始时间（用于统计此时间段内投票数的增量）
    private Date voteIncreaseStart;
    // 投票增量结束时间（用于统计此时间段内投票数的增量）
    private Date voteIncreaseEnd;

    public Date getVoteIncreaseStart() {
        return voteIncreaseStart;
    }

    public void setVoteIncreaseStart(Date voteIncreaseStart) {
        this.voteIncreaseStart = voteIncreaseStart;
    }

    public Date getVoteIncreaseEnd() {
        return voteIncreaseEnd;
    }

    public void setVoteIncreaseEnd(Date voteIncreaseEnd) {
        this.voteIncreaseEnd = voteIncreaseEnd;
    }

    public Integer getTeamLeaderid() {
        return teamLeaderid;
    }

    public void setTeamLeaderid(Integer teamLeaderid) {
        this.teamLeaderid = teamLeaderid;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Integer getIsVoteParam() {
        return isVoteParam;
    }

    public void setIsVoteParam(Integer isVoteParam) {
        this.isVoteParam = isVoteParam;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public Integer getMatchAreaId() {
        return matchAreaId;
    }

    public void setMatchAreaId(Integer matchAreaId) {
        this.matchAreaId = matchAreaId;
    }

    public Integer getMatchIndustryId() {
        return matchIndustryId;
    }

    public void setMatchIndustryId(Integer matchIndustryId) {
        this.matchIndustryId = matchIndustryId;
    }

    public String getMatchName() {
        return matchName;
    }

    public void setMatchName(String matchName) {
        this.matchName = matchName;
    }

    public Integer getIsVote() {
        return isVote;
    }

    public void setIsVote(Integer isVote) {
        this.isVote = isVote;
    }

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    public String getTeamNo() {
        return teamNo;
    }

    public void setTeamNo(String teamNo) {
        this.teamNo = teamNo;
    }

    public Integer getTeamAreaId() {
        return teamAreaId;
    }

    public void setTeamAreaId(Integer teamAreaId) {
        this.teamAreaId = teamAreaId;
    }

    public Integer getTeamIndustryId() {
        return teamIndustryId;
    }

    public void setTeamIndustryId(Integer teamIndustryId) {
        this.teamIndustryId = teamIndustryId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
