package com.uway.sports.web.entity.dbentity;

/**
 * Auth: liujingce
 * Date: 2018/2/6 16:07
 * Title:
 * Describer:
 **/
public class StartUpPicture {
    private Integer pictureId;
    private String pictureUrl;
    private String visitUrl;
    private Integer status;
    private Integer pictureType;
    private Integer openWay;
    private String createTime;
    private String remarks;

    public Integer getPictureType() {
        return pictureType;
    }

    public void setPictureType(Integer pictureType) {
        this.pictureType = pictureType;
    }

    public Integer getPictureId() {
        return pictureId;
    }

    public void setPictureId(Integer pictureId) {
        this.pictureId = pictureId;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getVisitUrl() {
        return visitUrl;
    }

    public void setVisitUrl(String visitUrl) {
        this.visitUrl = visitUrl;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOpenWay() {
        return openWay;
    }

    public void setOpenWay(Integer openWay) {
        this.openWay = openWay;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
