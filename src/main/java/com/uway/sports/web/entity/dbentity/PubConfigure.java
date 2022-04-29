package com.uway.sports.web.entity.dbentity;


import com.uway.sports.web.entity.TableEntity;

import java.io.Serializable;

public class PubConfigure extends TableEntity implements Serializable {

	private Integer id;
	/** 参数类型  1-描述性文字参数   2-开关型  3-下拉值 */
	private Long commType;
	/** 参数名称 */
	private String commName;
	/** 参数描述 */
	private String commDes;
	/** 变量值 */
	private String paramValue;
	/** 变量描述 */
	private String paramName;
	/** 备注 */
	private String remarks;
	/** 排序 */
	private Integer sort;
	/** 状态  0-无效  1-有效 */
	private Integer status;

	public PubConfigure(String commName, String paramValue, Integer status) {
		this.commName = commName;
		this.paramValue = paramValue;
		this.status = status;
	}

	public PubConfigure() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getCommType() {
		return commType;
	}

	public void setCommType(Long commType) {
		this.commType = commType;
	}

	public String getCommName() {
		return commName;
	}

	public void setCommName(String commName) {
		this.commName = commName;
	}

	public String getCommDes() {
		return commDes;
	}

	public void setCommDes(String commDes) {
		this.commDes = commDes;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
}
