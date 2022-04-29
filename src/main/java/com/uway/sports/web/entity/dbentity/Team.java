package com.uway.sports.web.entity.dbentity;

import java.io.Serializable;

public class Team implements Serializable {

    private Integer	id;
    /** 赛事id */
    private Integer	 matchId;
	/** 小组id */
    private Integer groupId;
    /** 战队编号 */
    private String	teamNo;
    /** 战队名称 */
    private String	teamName;
    /** 战队缩略图 */
    private String	teamImgurl;
    /** 队长id */
    private Integer	teamLeaderid;
    /** 战队描述 */
    private String	teamDisc;
    /** 是否可投票 全国性开关 */
    private Integer	isVote;
    /** 投票数 */
    private Long	voteCnt;
	/** 赛事行业id */
	private Integer matchIndustryId;
	/** 赛事公司 */
	private String matchCompany;
	/** 赛事战区id */
	private Integer matchAreaId;
    /** 战队行业id */
    private Integer teamIndustryId;
	/** 战队公司 */
    private String teamCompany;
    /** 战队战区id */
    private Integer teamAreaId;
    /** 是否满员 */
    private Integer isFull;
    /** 审核状态 0是未审核 1是通过 2 是未通过 */
    private Integer checkStatus;
    /** 驳回原因 */
    private String checkReason;
	/** 审核人 */
	private Integer checkUser;
	/** 审核时间 */
	private String checkTime;
    /** 自身条件是否满足投票要求 0否 1是 */
    private Integer voteStatus;
    /** 战队状态 0是无效 1是有效 */
    private Integer status;
    /** 入库时间 */
    private String	inserttime;
    /** 排序 */
    private Integer sort;
    // 专家打分
    private Double score;
    // 队伍标签
    private String teamTag;
    // 队伍类型 1团体 2个人
    private Integer teamType;

	public String getTeamTag() {
		return teamTag;
	}

	public void setTeamTag(String teamTag) {
		this.teamTag = teamTag;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

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
	
	public String getTeamNo() {
		return teamNo;
	}
	
	public void setTeamNo(String teamNo) {
		this.teamNo = teamNo;
	}
	
	public String getTeamName() {
		return teamName;
	}
	
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	public String getTeamImgurl() {
		return teamImgurl;
	}
	
	public void setTeamImgurl(String teamImgurl) {
		this.teamImgurl = teamImgurl;
	}
	
	public Integer getTeamLeaderid() {
		return teamLeaderid;
	}
	
	public void setTeamLeaderid(Integer teamLeaderid) {
		this.teamLeaderid = teamLeaderid;
	}
	
	public String getTeamDisc() {
		return teamDisc;
	}
	
	public void setTeamDisc(String teamDisc) {
		this.teamDisc = teamDisc;
	}
	
	public Integer getIsVote() {
		return isVote;
	}
	
	public void setIsVote(Integer isVote) {
		this.isVote = isVote;
	}
	
	public Long getVoteCnt() {
		return voteCnt;
	}
	
	public void setVoteCnt(Long voteCnt) {
		this.voteCnt = voteCnt;
	}

	public Integer getMatchIndustryId() {
		return matchIndustryId;
	}

	public void setMatchIndustryId(Integer matchIndustryId) {
		this.matchIndustryId = matchIndustryId;
	}

	public String getMatchCompany() {
		return matchCompany;
	}

	public void setMatchCompany(String matchCompany) {
		this.matchCompany = matchCompany;
	}

	public Integer getMatchAreaId() {
		return matchAreaId;
	}

	public void setMatchAreaId(Integer matchAreaId) {
		this.matchAreaId = matchAreaId;
	}

	public Integer getTeamIndustryId() {
		return teamIndustryId;
	}

	public void setTeamIndustryId(Integer teamIndustryId) {
		this.teamIndustryId = teamIndustryId;
	}

	public String getTeamCompany() {
		return teamCompany;
	}

	public void setTeamCompany(String teamCompany) {
		this.teamCompany = teamCompany;
	}

	public Integer getTeamAreaId() {
		return teamAreaId;
	}
	
	public void setTeamAreaId(Integer teamAreaId) {
		this.teamAreaId = teamAreaId;
	}
	
	public Integer getIsFull() {
		return isFull;
	}
	
	public void setIsFull(Integer isFull) {
		this.isFull = isFull;
	}
	
	public Integer getCheckStatus() {
		return checkStatus;
	}
	
	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}
	
	public Integer getVoteStatus() {
		return voteStatus;
	}
	
	public void setVoteStatus(Integer voteStatus) {
		this.voteStatus = voteStatus;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getInserttime() {
		return inserttime;
	}
	
	public void setInserttime(String inserttime) {
		this.inserttime = inserttime;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getCheckReason() {
		return checkReason;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public void setCheckReason(String checkReason) {
		this.checkReason = checkReason;
	}

	public Integer getCheckUser() {
		return checkUser;
	}

	public void setCheckUser(Integer checkUser) {
		this.checkUser = checkUser;
	}

	public String getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}

	public Integer getTeamType() {
		return teamType;
	}

	public void setTeamType(Integer teamType) {
		this.teamType = teamType;
	}
}
