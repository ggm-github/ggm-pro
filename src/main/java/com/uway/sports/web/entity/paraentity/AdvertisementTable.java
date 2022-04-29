package com.uway.sports.web.entity.paraentity;

import com.uway.sports.web.entity.TableEntity;

import java.util.Date;

/**
 * @Title
 * @Author liujingce
 * @Date 2019/4/1 11:16
 * @Discription
 */
public class AdvertisementTable extends TableEntity {

    private String advName;
    private Integer positionType;
    private Date insertStartDate;
    private Date insertEndDate;
    private Integer status;

    public String getAdvName() {
        return advName;
    }

    public void setAdvName(String advName) {
        this.advName = advName;
    }

    public Integer getPositionType() {
        return positionType;
    }

    public void setPositionType(Integer positionType) {
        this.positionType = positionType;
    }

    public Date getInsertStartDate() {
        return insertStartDate;
    }

    public void setInsertStartDate(Date insertStartDate) {
        this.insertStartDate = insertStartDate;
    }

    public Date getInsertEndDate() {
        return insertEndDate;
    }

    public void setInsertEndDate(Date insertEndDate) {
        this.insertEndDate = insertEndDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
