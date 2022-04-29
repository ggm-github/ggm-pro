package com.uway.sports.web.entity.dbentity;

import java.io.Serializable;

public class MatchNotice implements Serializable {

	private Integer id;
	/** 赛事创建者id */
	private Integer sysUserId;
	/** 赛事id */
	private Integer matchId;
	/** 通知名称 */
	private String noticeTitle;
	/** 通知方式 1app通知 2短信通知 3智能电话通知  1,2,3 */
	private String noticeType;
	/** 通知类型对应审核状态  1,0,1  三个类型对应三个状态 */
	private String noticeTypeStatus;
	/** 通知状态  -1草稿 0审核中 1发送中 2发送完成 3审核不通过 */
	private Integer status;
	private Integer checkUserId;
	private String remarks;
	/** 入库时间 */
	private String inserttime;
	/** 修改时间 */
	private String updatetime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(Integer sysUserId) {
		this.sysUserId = sysUserId;
	}

	public Integer getMatchId() {
		return matchId;
	}

	public void setMatchId(Integer matchId) {
		this.matchId = matchId;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}

	public String getNoticeTypeStatus() {
		return noticeTypeStatus;
	}

	public void setNoticeTypeStatus(String noticeTypeStatus) {
		this.noticeTypeStatus = noticeTypeStatus;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getInserttime() {
		return inserttime;
	}

	public void setInserttime(String inserttime) {
		this.inserttime = inserttime;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public Integer getCheckUserId() {
		return checkUserId;
	}

	public void setCheckUserId(Integer checkUserId) {
		this.checkUserId = checkUserId;
	}
}
