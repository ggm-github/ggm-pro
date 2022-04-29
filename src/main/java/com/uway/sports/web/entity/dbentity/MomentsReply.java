package com.uway.sports.web.entity.dbentity;

import java.io.Serializable;
import java.util.Date;

/**
 * 动态的回复
 */
public class MomentsReply implements Serializable {
	/** 回复id */
	private Integer id;
	/** 话题id */
	private Integer themeId;
	/** 动态id   team_video的id */
	private Integer momentsId;
	/** 被回复的id 动态的回复为0 */
	private Integer replyId;
	/** 被回复人id（回复动态 则为0） */
	private Integer replyUserid;
	/** 回复人id */
	private Integer operUserId;
	/** 回复内容 */
	private String replyContent;
	/** 创建时间（回复时间） */
	private String inserttime;
	/** 被回复的次数 */
	private Integer replyCnt;
	/** 状态  1-有效   2-无效 */
	private Integer status;
	private String updatetime;
	private Integer updateUser;
	private Integer sort;
	private String remarks;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getThemeId() {
		return themeId;
	}

	public void setThemeId(Integer themeId) {
		this.themeId = themeId;
	}

	public Integer getMomentsId() {
		return momentsId;
	}

	public void setMomentsId(Integer momentsId) {
		this.momentsId = momentsId;
	}

	public Integer getReplyId() {
		return replyId;
	}

	public void setReplyId(Integer replyId) {
		this.replyId = replyId;
	}

	public Integer getReplyUserid() {
		return replyUserid;
	}

	public void setReplyUserid(Integer replyUserid) {
		this.replyUserid = replyUserid;
	}

	public Integer getOperUserId() {
		return operUserId;
	}

	public void setOperUserId(Integer operUserId) {
		this.operUserId = operUserId;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public String getInserttime() {
		return inserttime;
	}

	public void setInserttime(String inserttime) {
		this.inserttime = inserttime;
	}

	public Integer getReplyCnt() {
		return replyCnt;
	}

	public void setReplyCnt(Integer replyCnt) {
		this.replyCnt = replyCnt;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public Integer getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
