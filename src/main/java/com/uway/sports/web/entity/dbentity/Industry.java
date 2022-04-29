package com.uway.sports.web.entity.dbentity;

public class Industry {

    private Integer industryId;//'行业id'
    private String industryName;//行业名称
    private Integer parentId;//父级id  工根节点为0
    private Integer status;//`'0是无效 1有效',


    public Integer getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Integer industryId) {
        this.industryId = industryId;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
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
