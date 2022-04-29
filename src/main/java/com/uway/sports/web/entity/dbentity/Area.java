package com.uway.sports.web.entity.dbentity;

public class Area {

    private Integer areaId;//'地域id'
    private String areaName;//地域名称
    private Integer parentId;//父级id  工根节点为0
    private String letter;  // 首字母
    private Integer status;//`'0是无效 1有效',
    private Integer isHot; // 是否热门  0-否  1-是
    private Integer hotSort; // 热门权重

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public Integer getIsHot() {
        return isHot;
    }

    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

    public Integer getHotSort() {
        return hotSort;
    }

    public void setHotSort(Integer hotSort) {
        this.hotSort = hotSort;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
