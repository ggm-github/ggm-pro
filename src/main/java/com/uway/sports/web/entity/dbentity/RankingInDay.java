package com.uway.sports.web.entity.dbentity;

import java.io.Serializable;
import java.util.Date;

public class RankingInDay implements Serializable {

    private Integer id;
    private Integer matchId;
    private Integer teamId;
    private Date rankingDate;
    private Integer voteCnt;
    private Integer rankingNum;
    private Date inserttime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Date getRankingDate() {
        return rankingDate;
    }

    public void setRankingDate(Date rankingDate) {
        this.rankingDate = rankingDate;
    }

    public Integer getVoteCnt() {
        return voteCnt;
    }

    public void setVoteCnt(Integer voteCnt) {
        this.voteCnt = voteCnt;
    }

    public Integer getRankingNum() {
        return rankingNum;
    }

    public void setRankingNum(Integer rankingNum) {
        this.rankingNum = rankingNum;
    }

    public Date getInserttime() {
        return inserttime;
    }

    public void setInserttime(Date inserttime) {
        this.inserttime = inserttime;
    }
}
