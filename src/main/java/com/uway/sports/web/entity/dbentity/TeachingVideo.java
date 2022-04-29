package com.uway.sports.web.entity.dbentity;

import java.io.Serializable;

public class TeachingVideo implements Serializable {

	private Integer id;
	/** 视频名称 */
	private String teachingName;
	// 视频类型 1-教学视频  2-新闻视频
	private Integer videoType;
	/** 视频url */
	private String	teachingUrl;
	/** 描述 */
	private String	teachingDisc;
	/** 父级id */
	private Integer parentId;
	/** 列表图 */
	private String coverUrl;
	/** 视频时长*/
	private Long videotime;
	// 视频标签
	private String	videoTag;
	/** 审核状态 0待审核 1 通过 2审核不通过 */
	private Integer	 checkStatus;
	/** 审核人id */
	private Integer	 checkUser;
	// 驳回原因
	private String checkReason;
	/** 权重 */
	private Integer	 sort;
	/** 状态 0是无效 1是有效 */
	private Integer	 status;
	/** 阅读数量 */
	private Long readCnt;
	/** 录入人id */
	private Integer	 insertUser;
	/** 入库时间 */
	private String	inserttime;
	// 是否打卡视频
	private Integer isTask;
	// 腾讯文件id
	private String fileId;
	// 发布人
	private Integer operUserId;
	// 主题
	private Integer themeId;

	public Integer getIsTask() {
		return isTask;
	}

	public void setIsTask(Integer isTask) {
		this.isTask = isTask;
	}

	public Integer getId() {
		return id;
	}

	public String getVideoTag() {
		return videoTag;
	}

	public void setVideoTag(String videoTag) {
		this.videoTag = videoTag;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTeachingName() {
		return teachingName;
	}

	public void setTeachingName(String teachingName) {
		this.teachingName = teachingName;
	}

	public Integer getVideoType() {
		return videoType;
	}

	public void setVideoType(Integer videoType) {
		this.videoType = videoType;
	}

	public String getTeachingUrl() {
		return teachingUrl;
	}

	public void setTeachingUrl(String teachingUrl) {
		this.teachingUrl = teachingUrl;
	}

	public String getTeachingDisc() {
		return teachingDisc;
	}

	public void setTeachingDisc(String teachingDisc) {
		this.teachingDisc = teachingDisc;
	}

	public Integer getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}

	public Integer getCheckUser() {
		return checkUser;
	}

	public void setCheckUser(Integer checkUser) {
		this.checkUser = checkUser;
	}

	public String getCheckReason() {
		return checkReason;
	}

	public void setCheckReason(String checkReason) {
		this.checkReason = checkReason;
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

	public Integer getInsertUser() {
		return insertUser;
	}

	public void setInsertUser(Integer insertUser) {
		this.insertUser = insertUser;
	}

	public String getInserttime() {
		return inserttime;
	}

	public void setInserttime(String inserttime) {
		this.inserttime = inserttime;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getCoverUrl() {
		return coverUrl;
	}

	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}

	public Long getVideotime() {
		return videotime;
	}

	public void setVideotime(Long videotime) {
		this.videotime = videotime;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public Integer getOperUserId() {
		return operUserId;
	}

	public void setOperUserId(Integer operUserId) {
		this.operUserId = operUserId;
	}

	public Integer getThemeId() {
		return themeId;
	}

	public void setThemeId(Integer themeId) {
		this.themeId = themeId;
	}
}
