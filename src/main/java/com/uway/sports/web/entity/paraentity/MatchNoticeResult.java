package com.uway.sports.web.entity.paraentity;

import com.uway.sports.web.entity.dbentity.MatchNotice;

import java.io.Serializable;

public class MatchNoticeResult extends MatchNotice implements Serializable {

	private String matchTitle;

	private String sysUserChName;

	private String checkUserName;

	public String getMatchTitle() {
		return matchTitle;
	}

	public void setMatchTitle(String matchTitle) {
		this.matchTitle = matchTitle;
	}

	public String getSysUserChName() {
		return sysUserChName;
	}

	public void setSysUserChName(String sysUserChName) {
		this.sysUserChName = sysUserChName;
	}

	public String getCheckUserName() {
		return checkUserName;
	}

	public void setCheckUserName(String checkUserName) {
		this.checkUserName = checkUserName;
	}
}
