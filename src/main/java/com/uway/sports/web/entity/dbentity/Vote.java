package com.uway.sports.web.entity.dbentity;

import java.io.Serializable;

public class Vote implements Serializable {

    /** 投票id */
    private Integer	id;
    /** 战队id */
    private Integer	teamId;
    /** 视频id */
    private Integer	videoId;
    /** 投票用户id */
    private Integer	voteUserid;
	/** 状态 0是无效 1是有效 */
	private Integer	status;
    /** 投票时间 */
    private String	inserttime;
	/** 战队名称 */
    private String teamName;
	/** 战队头像 */
    private String teamImgurl;

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
	
	public Integer getVideoId() {
		return videoId;
	}
	
	public void setVideoId(Integer videoId) {
		this.videoId = videoId;
	}
	
	public Integer getVoteUserid() {
		return voteUserid;
	}
	
	public void setVoteUserid(Integer voteUserid) {
		this.voteUserid = voteUserid;
	}
	
	public String getInserttime() {
		return inserttime;
	}
	
	public void setInserttime(String inserttime) {
		this.inserttime = inserttime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
}
