package com.uway.sports.web.entity.dbentity;

import java.io.Serializable;
import java.util.Date;

public class SportsNews implements Serializable {

    /** 比赛新闻id */
    private Integer id;
    /** 比赛标题 */
    private String	newsTitle;
	/** 副标题 */
    private String subNewsTitle;
    /** 列表头像url */
    private String	newsImgurl;
    /** 新闻来源 */
    private String	newsAuthor;
    /** 1是url 2是原生页面 */
    private Integer newsType;
    /** 新闻url */
    private String	newsUrl;
    /** 新闻内容 */
    private String	newsContent;
    /** 排序 */
    private Integer	 sort;
	/** 权重类型：  1-永不过期  2-按时间  */
    private Integer sortType;
	/** 权重时间 */
    private String sortDate;
	/** 新闻标签 */
    private String	tag;
    /** 状态 0无效 1有效 */
    private Integer status;
    /** 阅读数量 */
    private Long readCnt;
    /** 入库时间 */
    private String	inserttime;
    /** 操作人id */
    private Integer optUser;
    /** 操作时间 */
    private String	optTime;

	public String getSubNewsTitle() {
		return subNewsTitle;
	}

	public void setSubNewsTitle(String subNewsTitle) {
		this.subNewsTitle = subNewsTitle;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNewsTitle() {
		return newsTitle;
	}
	
	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
	
	public String getNewsImgurl() {
		return newsImgurl;
	}
	
	public void setNewsImgurl(String newsImgurl) {
		this.newsImgurl = newsImgurl;
	}
	
	public String getNewsAuthor() {
		return newsAuthor;
	}
	
	public void setNewsAuthor(String newsAuthor) {
		this.newsAuthor = newsAuthor;
	}
	
	public Integer getNewsType() {
		return newsType;
	}
	
	public void setNewsType(Integer newsType) {
		this.newsType = newsType;
	}
	
	public String getNewsUrl() {
		return newsUrl;
	}
	
	public void setNewsUrl(String newsUrl) {
		this.newsUrl = newsUrl;
	}
	
	public String getNewsContent() {
		return newsContent;
	}
	
	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}
	
	public Integer getSort() {
		return sort;
	}
	
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	public String getTag() {
		return tag;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
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

	public Integer getSortType() {
		return sortType;
	}

	public void setSortType(Integer sortType) {
		this.sortType = sortType;
	}

	public String getSortDate() {
		return sortDate;
	}

	public void setSortDate(String sortDate) {
		this.sortDate = sortDate;
	}
}
