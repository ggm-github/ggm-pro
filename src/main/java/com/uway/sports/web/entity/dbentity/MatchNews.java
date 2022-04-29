package com.uway.sports.web.entity.dbentity;

import java.io.Serializable;
import java.util.Date;

public class MatchNews implements Serializable {
	/** 比赛新闻id */
	private Integer	id;
	/** 比赛标题 */
	private String	matchTitle;
	/** 列表头像url */
	private String	matchImgurl;
	/** 赛事主办单位 */
	private String	matchUnit;
	/** 赛事城市 */
	private String	matchCity;
	/** 赛事地点 */
	private String	matchPlace;
	/** 赛事时间 */
	private String	matchTime;
	/** 赛事备注 赛事组别及人数*/
	private String	matchBake;
	/** 行业id */
	private Integer	 matchIndustry;
	/** 公司名称 */
	private String	 matchCompany;
	/** 区域id */
	private Integer	 matchArea;
	/** 1是url 2是原生页面 */
	private Integer	 matchType;
	/** 比赛url */
	private String	matchUrl;
	/** 排序 */
	private Integer	 sort;
	/** 状态 0无效 1有效 */
	private Integer	 status;
	/** 阅读数量 */
	private Long readCnt;
	/** 入库时间 */
	private String	inserttime;
	/** 操作人id */
	private Integer	 optUser;
	/** 操作时间 */
	private String	optTime;
	/** 赛事介绍 */
	private String	matchContent;

	private Integer sortType;
	private Date sortDate;
	private String matchEndtime;
	/** 赛事比赛开始时间 */
	private String dateBegin;
	/** 赛事比赛结束时间 */
	private String dateEnd;
	/** 赛事标签 */
	private String matchTag;

	private Integer isCharge;//是否收费 0免费 1收费

	private Double unitPrice;//收费单价

	private Integer chargeType;//交费方式 0全选 1统一缴费 2自己缴费

	private Integer userLimit;//参数人员限制

	private Integer userMax;  // 参赛人数最大值

	private String chargeNotice;//交费须知

	private String signupNotice;//报名须知
	// 驳回原因
	private String reason;
	// 是否上传视频
	private Integer isNeedVideo;
	// 项目名称冗余字段
	private String itemNames;
	// 比赛地点（省、直辖市）
	private Integer matchProvinceId;
    // 比赛地点（市、直辖市的区）
	private Integer matchCityId;
	// 支付价格段
	private String chargeInterval;
	// 导语
	private String matchRemark;
	// 是否需要投票
	private Integer isVote;
	// 发布范围 1所有人可见  2本企业可见
	private String publishRange;
	// 是否发送每日排名消息
	private Integer rankingDay;
	// 是否发送每周排名消息
	private Integer rankingWeek;
	// 是否发送每月排名消息
	private Integer rankingMonth;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getUserMax() {
		return userMax;
	}

	public void setUserMax(Integer userMax) {
		this.userMax = userMax;
	}

	public Integer getIsCharge() {
		return isCharge;
	}

	public void setIsCharge(Integer isCharge) {
		this.isCharge = isCharge;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getChargeType() {
		return chargeType;
	}

	public void setChargeType(Integer chargeType) {
		this.chargeType = chargeType;
	}

	public Integer getUserLimit() {
		return userLimit;
	}

	public void setUserLimit(Integer userLimit) {
		this.userLimit = userLimit;
	}

	public String getChargeNotice() {
		return chargeNotice;
	}

	public void setChargeNotice(String chargeNotice) {
		this.chargeNotice = chargeNotice;
	}

	public String getSignupNotice() {
		return signupNotice;
	}

	public void setSignupNotice(String signupNotice) {
		this.signupNotice = signupNotice;
	}

	public Integer getSortType() {
		return sortType;
	}

	public void setSortType(Integer sortType) {
		this.sortType = sortType;
	}

	public Date getSortDate() {
		return sortDate;
	}

	public void setSortDate(Date sortDate) {
		this.sortDate = sortDate;
	}

	public String getMatchEndtime() {
		return matchEndtime;
	}

	public void setMatchEndtime(String matchEndtime) {
		this.matchEndtime = matchEndtime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMatchTitle() {
		return matchTitle;
	}

	public void setMatchTitle(String matchTitle) {
		this.matchTitle = matchTitle;
	}

	public String getMatchImgurl() {
		return matchImgurl;
	}

	public void setMatchImgurl(String matchImgurl) {
		this.matchImgurl = matchImgurl;
	}

	public String getMatchUnit() {
		return matchUnit;
	}

	public void setMatchUnit(String matchUnit) {
		this.matchUnit = matchUnit;
	}

	public String getMatchCity() {
		return matchCity;
	}

	public void setMatchCity(String matchCity) {
		this.matchCity = matchCity;
	}

	public String getMatchPlace() {
		return matchPlace;
	}

	public void setMatchPlace(String matchPlace) {
		this.matchPlace = matchPlace;
	}

	public String getMatchTime() {
		return matchTime;
	}

	public void setMatchTime(String matchTime) {
		this.matchTime = matchTime;
	}

	public String getMatchBake() {
		return matchBake;
	}

	public void setMatchBake(String matchBake) {
		this.matchBake = matchBake;
	}

	public Integer getMatchArea() {
		return matchArea;
	}

	public void setMatchArea(Integer matchArea) {
		this.matchArea = matchArea;
	}

	public Integer getMatchType() {
		return matchType;
	}

	public void setMatchType(Integer matchType) {
		this.matchType = matchType;
	}

	public String getMatchUrl() {
		return matchUrl;
	}

	public void setMatchUrl(String matchUrl) {
		this.matchUrl = matchUrl;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getReadCnt() {
		return readCnt;
	}

	public void setReadCnt(Long readCnt) {
		this.readCnt = readCnt;
	}

	public String getInserttime() {
		return inserttime;
	}

	public void setInserttime(String inserttime) {
		this.inserttime = inserttime;
	}

	public Integer getOptUser() {
		return optUser;
	}

	public void setOptUser(Integer optUser) {
		this.optUser = optUser;
	}

	public String getOptTime() {
		return optTime;
	}

	public void setOptTime(String optTime) {
		this.optTime = optTime;
	}

	public String getMatchContent() {
		return matchContent;
	}

	public void setMatchContent(String matchContent) {
		this.matchContent = matchContent;
	}

	public Integer getMatchIndustry() {
		return matchIndustry;
	}

	public void setMatchIndustry(Integer matchIndustry) {
		this.matchIndustry = matchIndustry;
	}

	public String getMatchCompany() {
		return matchCompany;
	}

	public void setMatchCompany(String matchCompany) {
		this.matchCompany = matchCompany;
	}

	public String getDateBegin() {
		return dateBegin;
	}

	public void setDateBegin(String dateBegin) {
		this.dateBegin = dateBegin;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	public String getMatchTag() {
		return matchTag;
	}

	public void setMatchTag(String matchTag) {
		this.matchTag = matchTag;
	}

    public Integer getIsNeedVideo() {
        return isNeedVideo;
    }

    public void setIsNeedVideo(Integer isNeedVideo) {
        this.isNeedVideo = isNeedVideo;
    }

    public String getItemNames() {
        return itemNames;
    }

    public void setItemNames(String itemNames) {
        this.itemNames = itemNames;
    }

    public Integer getMatchProvinceId() {
        return matchProvinceId;
    }

    public void setMatchProvinceId(Integer matchProvinceId) {
        this.matchProvinceId = matchProvinceId;
    }

    public Integer getMatchCityId() {
        return matchCityId;
    }

    public void setMatchCityId(Integer matchCityId) {
        this.matchCityId = matchCityId;
    }

    public String getChargeInterval() {
        return chargeInterval;
    }

    public void setChargeInterval(String chargeInterval) {
        this.chargeInterval = chargeInterval;
    }

    public String getMatchRemark() {
        return matchRemark;
    }

    public void setMatchRemark(String matchRemark) {
        this.matchRemark = matchRemark;
    }

    public Integer getIsVote() {
        return isVote;
    }

    public void setIsVote(Integer isVote) {
        this.isVote = isVote;
    }

    public String getPublishRange() {
        return publishRange;
    }

    public void setPublishRange(String publishRange) {
        this.publishRange = publishRange;
    }

	public Integer getRankingDay() {
		return rankingDay;
	}

	public void setRankingDay(Integer rankingDay) {
		this.rankingDay = rankingDay;
	}

	public Integer getRankingWeek() {
		return rankingWeek;
	}

	public void setRankingWeek(Integer rankingWeek) {
		this.rankingWeek = rankingWeek;
	}

	public Integer getRankingMonth() {
		return rankingMonth;
	}

	public void setRankingMonth(Integer rankingMonth) {
		this.rankingMonth = rankingMonth;
	}
}
