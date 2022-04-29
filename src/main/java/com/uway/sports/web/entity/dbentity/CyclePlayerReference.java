package com.uway.sports.web.entity.dbentity;

import java.io.Serializable;


public class CyclePlayerReference implements Serializable {
	/** 自增id */
    private Integer	 id;
    /** 1-轮播图 */
    private Integer referenceType;
    /** 商品id、活动id */
    private Long referenceId;
    /** 名称（重命名，若为空则取商品或活动中的name） */
    private String	name;
    /** 图片路径（若为空，则取商品或活动中的路径） */
    private String	picUrl;
    /** 访问地址 */
    private String referenceUrl;
    /** 排序值，由大到小 */
    private Integer sort;
    /** 状态 0-无效  1-有效 */
    private Integer status;
    /** 创建时间 */
    private String createTime;
    /** 操作标识  0-链接跳转  1-原生跳转 */
    private Integer operTag;
	/** 是否分享  0-否  1-是 */
    private Integer operStatus;
	/** 分享标题 */
	private String shareFirstTitle;
	/** 分享副标题 */
	private String shareSecondTitle;
	/** 轮播的标签 */
	private String tag;
	/** 展示范围 */
	private Integer showIndex;
	/** 展示范围 */
	private Integer showCity;
	/** 展示范围 指定省市时，省市的名称 */
	private String showCityName;
	/** 描述 */
	private String referenceDesc;
	/** 展示位置 */
	private Integer showSort;

	public String getShowCityName() {
		return showCityName;
	}

	public void setShowCityName(String showCityName) {
		this.showCityName = showCityName;
	}

	public Integer getShowIndex() {
		return showIndex;
	}

	public void setShowIndex(Integer showIndex) {
		this.showIndex = showIndex;
	}

	public Integer getShowCity() {
		return showCity;
	}

	public void setShowCity(Integer showCity) {
		this.showCity = showCity;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getReferenceType() {
		return referenceType;
	}
	
	public void setReferenceType(Integer referenceType) {
		this.referenceType = referenceType;
	}
	
	public Long getReferenceId() {
		return referenceId;
	}
	
	public void setReferenceId(Long referenceId) {
		this.referenceId = referenceId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPicUrl() {
		return picUrl;
	}
	
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getReferenceUrl() {
        return referenceUrl;
    }

    public Integer getOperTag() {
        return operTag;
    }

    public void setOperTag(Integer operTag) {
        this.operTag = operTag;
    }

    public void setReferenceUrl(String referenceUrl) {
        this.referenceUrl = referenceUrl;
    }

	public Integer getOperStatus() {
		return operStatus;
	}

	public void setOperStatus(Integer operStatus) {
		this.operStatus = operStatus;
	}

	public String getShareFirstTitle() {
		return shareFirstTitle;
	}

	public void setShareFirstTitle(String shareFirstTitle) {
		this.shareFirstTitle = shareFirstTitle;
	}

	public String getShareSecondTitle() {
		return shareSecondTitle;
	}

	public void setShareSecondTitle(String shareSecondTitle) {
		this.shareSecondTitle = shareSecondTitle;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getReferenceDesc() {
		return referenceDesc;
	}

	public void setReferenceDesc(String referenceDesc) {
		this.referenceDesc = referenceDesc;
	}

	public Integer getShowSort() {
		return showSort;
	}

	public void setShowSort(Integer showSort) {
		this.showSort = showSort;
	}
}
