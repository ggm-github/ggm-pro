package com.uway.sports.web.entity.dbentity;

import java.io.Serializable;

public class MatchNoticeOperate implements Serializable {

	private Integer id;
	/** 通知id */
	private Integer matchNoticeId;
	/** 当前通知状态 -1创建 0提交审核 1审核完成，发送中 2发送完成  3审核不通过 4再次发送 */
	private Integer status;
	/** 操作人id */
	private Integer operateUserid;
	/** 操作时间 */
	private String inserttime;

	public MatchNoticeOperate() {
		super();
	}

	public MatchNoticeOperate(Integer matchNoticeId, Integer status, Integer operateUserid) {
		this.matchNoticeId = matchNoticeId;
		this.status = status;
		this.operateUserid = operateUserid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMatchNoticeId() {
		return matchNoticeId;
	}

	public void setMatchNoticeId(Integer matchNoticeId) {
		this.matchNoticeId = matchNoticeId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getOperateUserid() {
		return operateUserid;
	}

	public void setOperateUserid(Integer operateUserid) {
		this.operateUserid = operateUserid;
	}

	public String getInserttime() {
		return inserttime;
	}

	public void setInserttime(String inserttime) {
		this.inserttime = inserttime;
	}
}
