package com.uway.sports.web.entity.dbentity;

import java.util.Date;

public class RankingChange {
    /** 1-每日  2-每周  3-每月 */
    private Integer rankingType;
    /** 队伍id */
    private Integer teamId;
    private Integer matchId;
    /** 上次投票数 */
    private Integer voteCntOld;
    /** 上次排名 */
    private Integer rankingNumOld;
    /** 本次投票数 */
    private Integer voteCntNew;
    /** 本次排名值 */
    private Integer rankingNumNew;
    /** 浮动变化  0-无变化  1-上升  2-下降 */
    private Integer rankingTag;
    /** 0-未发送  1-已发送 */
    private Integer isSend;
    private Date updatetime;
    private Date inserttime;
    /** 状态 */
    private Integer status;
    private Integer voteDiff;

    public Integer getRankingType() {
        return rankingType;
    }

    public void setRankingType(Integer rankingType) {
        this.rankingType = rankingType;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getVoteCntOld() {
        return voteCntOld;
    }

    public void setVoteCntOld(Integer voteCntOld) {
        this.voteCntOld = voteCntOld;
    }

    public Integer getRankingNumOld() {
        return rankingNumOld;
    }

    public void setRankingNumOld(Integer rankingNumOld) {
        this.rankingNumOld = rankingNumOld;
    }

    public Integer getVoteCntNew() {
        return voteCntNew;
    }

    public void setVoteCntNew(Integer voteCntNew) {
        this.voteCntNew = voteCntNew;
    }

    public Integer getRankingNumNew() {
        return rankingNumNew;
    }

    public void setRankingNumNew(Integer rankingNumNew) {
        this.rankingNumNew = rankingNumNew;
    }

    public Integer getRankingTag() {
        return rankingTag;
    }

    public void setRankingTag(Integer rankingTag) {
        this.rankingTag = rankingTag;
    }

    public Integer getIsSend() {
        return isSend;
    }

    public void setIsSend(Integer isSend) {
        this.isSend = isSend;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Date getInserttime() {
        return inserttime;
    }

    public void setInserttime(Date inserttime) {
        this.inserttime = inserttime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    public Integer getVoteDiff() {
        return voteDiff;
    }

    public void setVoteDiff(Integer voteDiff) {
        this.voteDiff = voteDiff;
    }
}
