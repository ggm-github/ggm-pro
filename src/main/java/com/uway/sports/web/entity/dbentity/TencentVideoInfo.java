package com.uway.sports.web.entity.dbentity;

import java.io.Serializable;


public class TencentVideoInfo implements Serializable {

    /** 自增id */
    private Integer	id;
    /** 关联id */
    private Integer	relatedId;
    /** 视频原文件名(客户端上传前,本地文件名),如果超过100字符,那么在保留文件类型(eg: .mp4)的前提下进行截取 */
    private String	originalFileName;
    /** 视频名称(有些场景，用户上传视频时需要用户设置视频名称，目前赛事创建时上传的视频不需要此字段) */
    private String	videoName;
    /** 视频描述(有些场景,用户上传视频时需要用户对视频进行介绍，目前赛事创建时上传的视频不需要此字段) */
    private String	videoDisc;
    /** 视频类型  1:赛事宣传/教学视频 */
    private Integer	videoType;
    /** 视频状态  -1:云审核不通过 0:员工未审核 1:是审核通过 2:审核不通过 */
    private Integer	checkStatus;
    /** 视频审核说明 */
    private String	checkReason;
    /** 审核人id */
    private Integer	checkUser;
    /** 视频权重（视频排序依据字段） */
    private Integer	sort;
    /** 视频腾讯云url */
    private String	videoUrl;
    /** 视频腾讯云封面url */
    private String	coverUrl;
    /** 腾讯云fileId */
    private String	fileid;
    /** 视频时长(单位: 秒) */
    private Integer	videotime;
    /** 视频是否转码成功  0:失败 1:成功 */
    private Integer	isTranscode;
    /** 原始视频文件路径url */
    private String	txOriginalUrl;
    private String	txMessage;
    /** 腾讯文件名称 */
    private String	txFilename;
    /** 文件上传回调json */
    private String	txNewfileuploadJson;
    /** 视频上传任务处理json */
    private String	txNewfileprocedureJson;
    /** 视频处理回调json */
    private String	txProcedureJson;
    /** 腾讯鉴黄分数  -1:不合格 1:通过 2:需要审核 */
    private Integer	txPronPoint;
    /** 腾讯任务id */
    private String	txVodtaskid;
    /** 创建时间 */
    private String	inserttime;
    /** 查看次数 */
    private Integer	readCnt;

  	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getRelatedId() {
		return relatedId;
	}
	
	public void setRelatedId(Integer relatedId) {
		this.relatedId = relatedId;
	}
	
	public String getOriginalFileName() {
		return originalFileName;
	}
	
	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
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
	
	public String getCheckReason() {
		return checkReason;
	}
	
	public void setCheckReason(String checkReason) {
		this.checkReason = checkReason;
	}
	
	public Integer getCheckUser() {
		return checkUser;
	}
	
	public void setCheckUser(Integer checkUser) {
		this.checkUser = checkUser;
	}
	
	public Integer getSort() {
		return sort;
	}
	
	public void setSort(Integer sort) {
		this.sort = sort;
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
	
	public String getTxNewfileprocedureJson() {
		return txNewfileprocedureJson;
	}
	
	public void setTxNewfileprocedureJson(String txNewfileprocedureJson) {
		this.txNewfileprocedureJson = txNewfileprocedureJson;
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
	
	public String getTxVodtaskid() {
		return txVodtaskid;
	}
	
	public void setTxVodtaskid(String txVodtaskid) {
		this.txVodtaskid = txVodtaskid;
	}
	
	public String  getInserttime() {
		return inserttime;
	}
	
	public void setInserttime(String inserttime) {
		this.inserttime = inserttime;
	}
	
	public Integer getReadCnt() {
		return readCnt;
	}
	
	public void setReadCnt(Integer readCnt) {
		this.readCnt = readCnt;
	}
	
	
}
