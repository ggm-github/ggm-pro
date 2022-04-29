package com.uway.sports.web.entity.dbentity;

import java.io.Serializable;

public class TeamMember implements Serializable {

    /** id */
    private Integer	id;
    /** 战队id */
    private Integer	 teamId;
    /** 队员id */
    private Integer	 teamMemberId;
    /** 是否队长0否 1是 */
    private Integer	 isLeader;
    /** 队员状态 0待通过 1有效 -1无效  2待缴费*/
    private Integer	 status;
    /** 申请时间 */
    private String	applyTime;
    /** 通过时间 */
    private String	passTime;

    /** 队员名称*/
    private String teamMemberName;

	/** 队员身份证名称*/
	private String realName;

	/** 队员头像路径*/
	private String photoPath;

	/** 队员状态 当前状态 */
	private Integer nowstatus;
	// 战队名称
	private String teamName;
	// 赛事名称
	private String matchName;

	/** 审核状态 0是未审核 1是审核通过 2审核不通过 */
	private Integer checkStatus;
	// 是否已满员
	private Integer isFull;

	/** 赛事项目id */
	private Integer matchItemId;

	private Integer matchId;

	public Integer getIsFull() {
		return isFull;
	}

	public void setIsFull(Integer isFull) {
		this.isFull = isFull;
	}

	public String getMatchName() {
		return matchName;
	}

	public void setMatchName(String matchName) {
		this.matchName = matchName;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

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
	
	public Integer getTeamMemberId() {
		return teamMemberId;
	}
	
	public void setTeamMemberId(Integer teamMemberId) {
		this.teamMemberId = teamMemberId;
	}
	
	public Integer getIsLeader() {
		return isLeader;
	}
	
	public void setIsLeader(Integer isLeader) {
		this.isLeader = isLeader;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getApplyTime() {
		return applyTime;
	}
	
	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}
	
	public String getPassTime() {
		return passTime;
	}
	
	public void setPassTime(String passTime) {
		this.passTime = passTime;
	}

	public String getTeamMemberName() {
		return teamMemberName;
	}

	public void setTeamMemberName(String teamMemberName) {
		this.teamMemberName = teamMemberName;
	}

	public Integer getNowstatus() {
		return nowstatus;
	}

	public void setNowstatus(Integer nowstatus) {
		this.nowstatus = nowstatus;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public Integer getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}

	public Integer getMatchItemId() {
		return matchItemId;
	}

	public void setMatchItemId(Integer matchItemId) {
		this.matchItemId = matchItemId;
	}

	public Integer getMatchId() {
		return matchId;
	}

	public void setMatchId(Integer matchId) {
		this.matchId = matchId;
	}
}
