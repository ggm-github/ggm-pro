package com.uway.sports.web.entity.paraentity;

import com.uway.sports.web.entity.TableEntity;


public class HotSearchTable extends TableEntity {

    private Integer id;
    /** 热搜词名称 */
    private String	hotName;
    /** 类型  */
    private Integer	 hotType;
    /** 入库时间 */
    private String inserttime;
    /** 无效:0 有效:1 */
    private Integer	 status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHotName() {
		return hotName;
	}

	public void setHotName(String hotName) {
		this.hotName = hotName;
	}

	public Integer getHotType() {
		return hotType;
	}

	public void setHotType(Integer hotType) {
		this.hotType = hotType;
	}

	public String getInserttime() {
		return inserttime;
	}

	public void setInserttime(String inserttime) {
		this.inserttime = inserttime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
