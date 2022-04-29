package com.uway.sports.web.entity.dbentity;

import java.io.Serializable;
import java.util.Date;


public class UserAttention implements Serializable {

	private Integer userId;
	private Integer attentionUserId;
	/** 是否关注(表中存储值)  0-否  1-是    代码中处理的值  0-无关注  1-关注  2-互相关注  3-被关注  */
	private Integer isAttention;
	/** 0-无效  1-有效 */
	private Integer status;
	private Date inserttime;

	public UserAttention() {
		super();
	}

	public UserAttention(Integer userId, Integer attentionUserId, Integer isAttention,Integer status) {
		this.userId = userId;
		this.attentionUserId = attentionUserId;
		this.isAttention = isAttention;
		this.status = status;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getAttentionUserId() {
		return attentionUserId;
	}

	public void setAttentionUserId(Integer attentionUserId) {
		this.attentionUserId = attentionUserId;
	}

	public Integer getIsAttention() {
		return isAttention;
	}

	public void setIsAttention(Integer isAttention) {
		this.isAttention = isAttention;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getInserttime() {
		return inserttime;
	}

	public void setInserttime(Date inserttime) {
		this.inserttime = inserttime;
	}

}
