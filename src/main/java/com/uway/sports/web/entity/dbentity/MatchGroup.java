package com.uway.sports.web.entity.dbentity;

import java.io.Serializable;

public class MatchGroup implements Serializable {

    private Integer id;
    /** 赛事id */
    private Integer matchId;
    /** 赛事标题 */
    private String	matchTitle;
    /** 小组名称 */
    private String	groupName;
    /** 状态 */
    private Integer status;
    /** 创建时间 */
    private String	inserttime;

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

	public String getMatchTitle() {
		return matchTitle;
	}

	public void setMatchTitle(String matchTitle) {
		this.matchTitle = matchTitle;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
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
}
