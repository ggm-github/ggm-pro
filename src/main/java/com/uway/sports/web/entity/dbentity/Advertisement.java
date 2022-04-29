package com.uway.sports.web.entity.dbentity;

import java.io.Serializable;


public class Advertisement implements Serializable {

    /** 广告id */
    private Integer id;
    /** 位置类型 */
    private Integer positionType;
    /** 广告名称 */
    private String	advName;
    /** 广告url */
    private String	advUrl;
    /** 广告图 */
    private String	picturePath;
    /** 广告商id */
    private Integer advertiser;
    /** 广告总量 */
    private Integer advCnt;
    /** 广告开始时间 */
    private String	advStarttime;
    /** 广告结束时间 */
    private String	advEndtime;
    /** 权重 */
    private Long	sort;
    /** 状态  0-待发布  1-已发布 */
    private Integer	 status;
    /** 备注 */
    private String	remark;
    /** 操作时间 */
    private String	opertime;
    /** 操作人id */
    private Integer operuser;
	/** 录入时间 */
    private String	inserttime;
    /** 录入人id */
    private Integer insertuser;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPositionType() {
		return positionType;
	}

	public void setPositionType(Integer positionType) {
		this.positionType = positionType;
	}

	public String getAdvName() {
		return advName;
	}

	public void setAdvName(String advName) {
		this.advName = advName;
	}

	public String getAdvUrl() {
		return advUrl;
	}

	public void setAdvUrl(String advUrl) {
		this.advUrl = advUrl;
	}

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	public Integer getAdvertiser() {
		return advertiser;
	}

	public void setAdvertiser(Integer advertiser) {
		this.advertiser = advertiser;
	}

	public Integer getAdvCnt() {
		return advCnt;
	}

	public void setAdvCnt(Integer advCnt) {
		this.advCnt = advCnt;
	}

	public String getAdvStarttime() {
		return advStarttime;
	}

	public void setAdvStarttime(String advStarttime) {
		this.advStarttime = advStarttime;
	}

	public String getAdvEndtime() {
		return advEndtime;
	}

	public void setAdvEndtime(String advEndtime) {
		this.advEndtime = advEndtime;
	}

	public Long getSort() {
		return sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOpertime() {
		return opertime;
	}

	public void setOpertime(String opertime) {
		this.opertime = opertime;
	}

	public Integer getOperuser() {
		return operuser;
	}

	public void setOperuser(Integer operuser) {
		this.operuser = operuser;
	}

	public String getInserttime() {
		return inserttime;
	}

	public void setInserttime(String inserttime) {
		this.inserttime = inserttime;
	}

	public Integer getInsertuser() {
		return insertuser;
	}

	public void setInsertuser(Integer insertuser) {
		this.insertuser = insertuser;
	}
}
