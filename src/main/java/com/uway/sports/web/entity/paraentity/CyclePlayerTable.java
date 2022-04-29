package com.uway.sports.web.entity.paraentity;


import com.uway.sports.web.entity.TableEntity;

/**
 * Auth: liujingce
 * Date: 2018/1/29 14:48
 * Title:
 * Describer:
 **/
public class CyclePlayerTable extends TableEntity {
    /** 自增id */
    private Integer	 id;
    /** 1 - 轮播图   2 - 首页广告*/
    private Integer	 referenceType;
    /** 名称（重命名，若为空则取商品或活动中的name） */
    private String	name;
    /** 状态 0-无效  1-有效 */
    private Integer	 status;
    /** 轮播标签 */
    private String tag;

    public Integer getReferenceType() {
        return referenceType;
    }

    public void setReferenceType(Integer referenceType) {
        this.referenceType = referenceType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
