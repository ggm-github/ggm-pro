package com.uway.sports.web.entity.paraentity;

import com.uway.sports.web.entity.TableEntity;

import java.io.Serializable;

public class MatchNoticeTable extends TableEntity implements Serializable {

	private Long id;
	/** 赛事创建者id */
	private Long sysUserId;
	/** 赛事id */
	private Long matchId;
	/** 通知名称 */
	private String noticeTitle;
	/** 通知方式 1app通知 2短信通知 3智能电话通知  1,2,3 */
	private String noticeType;
	/** 通知类型对应审核状态  1,0,1  三个类型对应三个状态 */
	private String noticeTypeStatus;
	/** 通知状态  -1草稿 0审核中 1发送中 2发送完成 3审核不通过 */
	private Integer status;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(Long sysUserId) {
		this.sysUserId = sysUserId;
	}

	public Long getMatchId() {
		return matchId;
	}

	public void setMatchId(Long matchId) {
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
}
