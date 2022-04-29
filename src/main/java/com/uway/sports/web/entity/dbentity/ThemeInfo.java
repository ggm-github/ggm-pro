package com.uway.sports.web.entity.dbentity;

import java.io.Serializable;
import java.util.Date;


public class ThemeInfo implements Serializable {
	/** 话题id */
	private Integer id;
	/** 赛事id，与赛事无关联则为0 */
	private Integer matchId;
	/** 1-普通话题  2-赛事话题 */
	private Integer themeType;
	/** 话题标题 */
	private String themeTitle;
	/** 话题导语 */
	private String themeRemark;
	/** 话题封面 */
	private String themeImgurl;
	/** 阅读量 */
	private String readCnt;
	/** 动态数 */
	private Integer momentsCnt;
	/** 权重 */
	private Integer sort;
	/** 0-无效  1-有效 */
	private Integer status;
	/** 发布人id */
	private Integer userId;
	/** 发布时间 */
	private Date inserttime;
	/** 操作人 */
	private Integer updateUserId;
	/** 公告类型  0-无公告 1-图片公告  2-文字公告 */
	private Integer noticeType;
	/** 公告图片路径 */
	private String noticeImage;
	/** 公告文字信息 */
	private String noticeText;
	/** 公告打开方式 -1 无跳转  0-链接跳转 */
	private Integer noticeOpenWay;
	/** 公告跳转路径或位置 */
	private String noticeOpenData;

	public ThemeInfo() {
		super();
	}

	public ThemeInfo(Integer matchId, Integer themeType, String themeTitle, String themeRemark, String themeImgurl, Integer sort, Integer userId) {
		this.matchId = matchId;
		this.themeType = themeType;
		this.themeTitle = themeTitle;
		this.themeRemark = themeRemark;
		this.themeImgurl = themeImgurl;
		this.sort = sort;
		this.userId = userId;
	}

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

	public Integer getThemeType() {
		return themeType;
	}

	public void setThemeType(Integer themeType) {
		this.themeType = themeType;
	}

	public String getThemeTitle() {
		return themeTitle;
	}

	public void setThemeTitle(String themeTitle) {
		this.themeTitle = themeTitle;
	}

	public String getThemeRemark() {
		return themeRemark;
	}

	public void setThemeRemark(String themeRemark) {
		this.themeRemark = themeRemark;
	}

	public String getThemeImgurl() {
		return themeImgurl;
	}

	public void setThemeImgurl(String themeImgurl) {
		this.themeImgurl = themeImgurl;
	}

	public String getReadCnt() {
		return readCnt;
	}

	public void setReadCnt(String readCnt) {
		this.readCnt = readCnt;
	}

	public Integer getMomentsCnt() {
		return momentsCnt;
	}

	public void setMomentsCnt(Integer momentsCnt) {
		this.momentsCnt = momentsCnt;
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getInserttime() {
		return inserttime;
	}

	public void setInserttime(Date inserttime) {
		this.inserttime = inserttime;
	}

	public Integer getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Integer updateUserId) {
		this.updateUserId = updateUserId;
	}

	public Integer getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(Integer noticeType) {
		this.noticeType = noticeType;
	}

	public String getNoticeImage() {
		return noticeImage;
	}

	public void setNoticeImage(String noticeImage) {
		this.noticeImage = noticeImage;
	}

	public String getNoticeText() {
		return noticeText;
	}

	public void setNoticeText(String noticeText) {
		this.noticeText = noticeText;
	}

	public Integer getNoticeOpenWay() {
		return noticeOpenWay;
	}

	public void setNoticeOpenWay(Integer noticeOpenWay) {
		this.noticeOpenWay = noticeOpenWay;
	}

	public String getNoticeOpenData() {
		return noticeOpenData;
	}

	public void setNoticeOpenData(String noticeOpenData) {
		this.noticeOpenData = noticeOpenData;
	}
}
