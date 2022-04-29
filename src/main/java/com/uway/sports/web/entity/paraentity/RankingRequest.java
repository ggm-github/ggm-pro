package com.uway.sports.web.entity.paraentity;

public class RankingRequest {
    private Integer rowNo;//排名
    private Integer teamId;//战队id
    private Integer areaId;//地域id
    private Integer matchRanking;//行业排名
    private Integer areaRanking;//地域排名
    private Integer matchId;//赛事id
    private Integer matchItemId;//项目id
    private Integer matchItemRanking;//项目当前排名

    public Integer getRowNo() {
        return rowNo;
    }

    public void setRowNo(Integer rowNo) {
        this.rowNo = rowNo;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getMatchRanking() {
        return matchRanking;
    }

    public void setMatchRanking(Integer matchRanking) {
        this.matchRanking = matchRanking;
    }

    public Integer getAreaRanking() {
        return areaRanking;
    }

    public void setAreaRanking(Integer areaRanking) {
        this.areaRanking = areaRanking;
    }

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    public Integer getMatchItemId() {
        return matchItemId;
    }

    public void setMatchItemId(Integer matchItemId) {
        this.matchItemId = matchItemId;
    }

    public Integer getMatchItemRanking() {
        return matchItemRanking;
    }

    public void setMatchItemRanking(Integer matchItemRanking) {
        this.matchItemRanking = matchItemRanking;
    }
}
