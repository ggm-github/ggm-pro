package com.uway.sports.web.entity.paraentity;

public class TeamOrderParas {
    private Integer userId;
    private Integer matchId;
    private Integer teamId;
    /** 报名人数 */
    private Integer	numbers;
    /** 交费方式 1统一交费 2自己交费 */
    private Integer chargeType;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Integer getNumbers() {
        return numbers;
    }

    public void setNumbers(Integer numbers) {
        this.numbers = numbers;
    }

    public Integer getChargeType() {
        return chargeType;
    }

    public void setChargeType(Integer chargeType) {
        this.chargeType = chargeType;
    }
}
