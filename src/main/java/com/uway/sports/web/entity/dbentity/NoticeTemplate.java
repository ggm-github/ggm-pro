package com.uway.sports.web.entity.dbentity;


import java.io.Serializable;

public class NoticeTemplate implements Serializable {

	private Integer id;
	/** 通知表id */
	private Integer matchNoticeId;
	/** 通知类型：1app通知 2短信通知 3智能电话通知  */
	private Integer noticeType;
	/** 标题 */
	private String title;
	/** 消息内容 */
	private String context;
	/** 入库时间 */
	private String inserttime;
	/** 更新时间 */
	private String updatetime;

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

	public Integer getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(Integer noticeType) {
		this.noticeType = noticeType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
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
}
