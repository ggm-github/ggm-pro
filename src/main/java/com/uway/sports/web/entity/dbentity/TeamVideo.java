package com.uway.sports.web.entity.dbentity;

import java.io.Serializable;

public class TeamVideo implements Serializable {

	private Integer	id;
	/** 战队id */
	private Integer	teamId;
	/** 视频url */
	private String	videoUrl;
	/** 视频封面url */
	private String	coverUrl;
	/** 视频名称 */
	private String	videoName;
	/** 视频描述 */
	private String	videoDisc;
	/** 视频分类 1广播体操 2 战队风采 3 工间操 */
	private Integer	videoType;
	/** 审核状态 -1云审核不通过 0员工未审核 1是审核通过 2审核不通过 */
	private Integer	checkStatus;
	/** 审核人id */
	private Integer	checkUser;
	/** 是否精选 0否 1 是 */
	private Integer	isSelected;
	/** 权重 */
	private Integer	sort;
	/** 状态 0是无效 1是有效 */
	private Integer	status;
	/** 投票总数 */
	private Long	voteCnt;
	/** 战队行业id */
	private Integer	teamIndustryId;
	/** 战队区域id */
	private Integer	teamAreaId;
	/** 入库时间 */
	private String	inserttime;
	/** 腾讯云文件id */
	private String	fileid;
	/** 视频时长 秒 */
	private Integer	videotime;
	/** 上传类型 */
	private String	sourcetype;
	/** 转码是否成功 0是失败 1是成功 */
	private Integer	isTranscode;
	/** 原始视频文件路径 */
	private String	txOriginalUrl;
	private String	txMessage;
	/** 腾讯文件名称 */
	private String	txFilename;
	/** 文件上传回调json */
	private String	txNewfileuploadJson;
	/** 视频处理回调json */
	private String	txProcedureJson;
	/** 腾讯鉴黄分数 */
	private Integer	txPronPoint;
	/** 腾讯任务id */
	private String txVodTaskId;
	/**  驳回原因  */
	private String checkReason;
	private Integer readCnt;
	/** 1-图片  2-视频 */
	private Integer type;
	/** 是否作品  0-否 1-是 */
	private Integer isWorks;
	/** 发布人id */
	private Integer operUserId;
	/** 点赞数 */
	private Integer goodCnt;
	/** 关联的id  videoType=2时，为教学视频id（主视频的id） */
	private Integer relationId;
	/** 评论数 */
	private Integer replyCnt;
	/** 分享量 */
	private Integer	shareCnt;
	/** 发送动态的地址 */
	private String releaseAddress;
	/** 发送动态的位置名称 */
	private String releaseName;
	/** 发送动态的经纬度 */
	private String releaseLocation;
	/** 动态展示步数信息 */
	private String showInfo;
	/** 动态热度调整 */
	private Integer hotSet;

	public String getCheckReason() {
		return checkReason;
	}

	public void setCheckReason(String checkReason) {
		this.checkReason = checkReason;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public String getCoverUrl() {
		return coverUrl;
	}

	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public String getVideoDisc() {
		return videoDisc;
	}

	public void setVideoDisc(String videoDisc) {
		this.videoDisc = videoDisc;
	}

	public Integer getVideoType() {
		return videoType;
	}

	public void setVideoType(Integer videoType) {
		this.videoType = videoType;
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

	public Integer getIsSelected() {
		return isSelected;
	}

	public void setIsSelected(Integer isSelected) {
		this.isSelected = isSelected;
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

	public Long getVoteCnt() {
		return voteCnt;
	}

	public void setVoteCnt(Long voteCnt) {
		this.voteCnt = voteCnt;
	}

	public Integer getTeamIndustryId() {
		return teamIndustryId;
	}

	public void setTeamIndustryId(Integer teamIndustryId) {
		this.teamIndustryId = teamIndustryId;
	}

	public Integer getTeamAreaId() {
		return teamAreaId;
	}

	public void setTeamAreaId(Integer teamAreaId) {
		this.teamAreaId = teamAreaId;
	}

	public String getInserttime() {
		return inserttime;
	}

	public void setInserttime(String inserttime) {
		this.inserttime = inserttime;
	}

	public String getFileid() {
		return fileid;
	}

	public void setFileid(String fileid) {
		this.fileid = fileid;
	}

	public Integer getVideotime() {
		return videotime;
	}

	public void setVideotime(Integer videotime) {
		this.videotime = videotime;
	}

	public String getSourcetype() {
		return sourcetype;
	}

	public void setSourcetype(String sourcetype) {
		this.sourcetype = sourcetype;
	}

	public Integer getIsTranscode() {
		return isTranscode;
	}

	public void setIsTranscode(Integer isTranscode) {
		this.isTranscode = isTranscode;
	}

	public String getTxOriginalUrl() {
		return txOriginalUrl;
	}

	public void setTxOriginalUrl(String txOriginalUrl) {
		this.txOriginalUrl = txOriginalUrl;
	}

	public String getTxMessage() {
		return txMessage;
	}

	public void setTxMessage(String txMessage) {
		this.txMessage = txMessage;
	}

	public String getTxFilename() {
		return txFilename;
	}

	public void setTxFilename(String txFilename) {
		this.txFilename = txFilename;
	}

	public String getTxNewfileuploadJson() {
		return txNewfileuploadJson;
	}

	public void setTxNewfileuploadJson(String txNewfileuploadJson) {
		this.txNewfileuploadJson = txNewfileuploadJson;
	}

	public String getTxProcedureJson() {
		return txProcedureJson;
	}

	public void setTxProcedureJson(String txProcedureJson) {
		this.txProcedureJson = txProcedureJson;
	}

	public Integer getTxPronPoint() {
		return txPronPoint;
	}

	public void setTxPronPoint(Integer txPronPoint) {
		this.txPronPoint = txPronPoint;
	}

	public String getTxVodTaskId() {
		return txVodTaskId;
	}

	public void setTxVodTaskId(String txVodTaskId) {
		this.txVodTaskId = txVodTaskId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getIsWorks() {
		return isWorks;
	}

	public void setIsWorks(Integer isWorks) {
		this.isWorks = isWorks;
	}

	public Integer getOperUserId() {
		return operUserId;
	}

	public void setOperUserId(Integer operUserId) {
		this.operUserId = operUserId;
	}

	public Integer getGoodCnt() {
		return goodCnt;
	}

	public void setGoodCnt(Integer goodCnt) {
		this.goodCnt = goodCnt;
	}

	public Integer getRelationId() {
		return relationId;
	}

	public void setRelationId(Integer relationId) {
		this.relationId = relationId;
	}

	public Integer getReplyCnt() {
		return replyCnt;
	}

	public void setReplyCnt(Integer replyCnt) {
		this.replyCnt = replyCnt;
	}

	public Integer getShareCnt() {
		return shareCnt;
	}

	public void setShareCnt(Integer shareCnt) {
		this.shareCnt = shareCnt;
	}

	public Integer getReadCnt() {
		return readCnt;
	}

	public void setReadCnt(Integer readCnt) {
		this.readCnt = readCnt;
	}

	public String getReleaseAddress() {
		return releaseAddress;
	}

	public void setReleaseAddress(String releaseAddress) {
		this.releaseAddress = releaseAddress;
	}

	public String getReleaseName() {
		return releaseName;
	}

	public void setReleaseName(String releaseName) {
		this.releaseName = releaseName;
	}

	public String getReleaseLocation() {
		return releaseLocation;
	}

	public void setReleaseLocation(String releaseLocation) {
		this.releaseLocation = releaseLocation;
	}

	public String getShowInfo() {
		return showInfo;
	}

	public void setShowInfo(String showInfo) {
		this.showInfo = showInfo;
	}

	public Integer getHotSet() {
		return hotSet;
	}

	public void setHotSet(Integer hotSet) {
		this.hotSet = hotSet;
	}
}
