package com.uway.sports.web.entity.dbentity;

import java.io.Serializable;
import java.util.Date;

public class Ranking implements Serializable {

    private Integer	 id;
    /** 战队id */
    private Integer	 teamId;
    /** 上周地域排名 */
    private Integer	 areaPreviousSort;
    /** 当前地域排名 */
    private Integer	 areaRanking;
    /** 上周行业排名 */
    private Integer	 matchPreviousSort;
    /** 当前行业排名 */
    private Integer matchRanking;
    /** 行业排名浮动  0是变 1是上升 2是下降 */
    private Integer	 matchTag;
    /** 地域排名浮动  0是变 1是上升 2是下降 */
    private Integer	 areaTag;
    /** 更新时间 */
    private String	updatetime;
    /** 排名状态 0无效 1有效  只能是同一个赛季的排名 */
    private Integer status;
	/** 地域id */
	private Integer areaId;
	/** 行业d */
	private Integer matchId;

	private Integer matchItemId;//项目id

	private Integer matchItemRanking;//项目当前排名

	private Integer matchItemPreviousSort;//项目上周排名

	private Integer matchItemTag;//赛事项目排名浮动  0是变 1是上升 2是下降

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public Integer getAreaPreviousSort() {
		return areaPreviousSort;
	}

	public void setAreaPreviousSort(Integer areaPreviousSort) {
		this.areaPreviousSort = areaPreviousSort;
	}

	public Integer getAreaRanking() {
		return areaRanking;
	}

	public void setAreaRanking(Integer areaRanking) {
		this.areaRanking = areaRanking;
	}

	public Integer getMatchPreviousSort() {
		return matchPreviousSort;
	}

	public void setMatchPreviousSort(Integer matchPreviousSort) {
		this.matchPreviousSort = matchPreviousSort;
	}

	public Integer getMatchRanking() {
		return matchRanking;
	}

	public void setMatchRanking(Integer matchRanking) {
		this.matchRanking = matchRanking;
	}

	public Integer getMatchTag() {
		return matchTag;
	}

	public void setMatchTag(Integer matchTag) {
		this.matchTag = matchTag;
	}

	public Integer getAreaTag() {
		return areaTag;
	}

	public void setAreaTag(Integer areaTag) {
		this.areaTag = areaTag;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
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

	public Integer getMatchItemPreviousSort() {
		return matchItemPreviousSort;
	}

	public void setMatchItemPreviousSort(Integer matchItemPreviousSort) {
		this.matchItemPreviousSort = matchItemPreviousSort;
	}

	public Integer getMatchItemTag() {
		return matchItemTag;
	}

	public void setMatchItemTag(Integer matchItemTag) {
		this.matchItemTag = matchItemTag;
	}
}
