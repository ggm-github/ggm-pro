package com.uway.sports.web.entity.paraentity;

import com.uway.sports.web.entity.TableEntity;

import java.util.Date;

/**
 * @Title
 * @Author liujingce
 * @Date 2019/3/28 20:34
 * @Discription
 */
public class TeachingVideoTable extends TableEntity {

    private String teachingName;
    private Integer videoType;
    private Date startDate;
    private Date endDate;
    private Integer status;
    private Integer checkStatus;
    private Integer isTask;
    private Integer parentId;

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Integer getIsTask() {
        return isTask;
    }

    public void setIsTask(Integer isTask) {
        this.isTask = isTask;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTeachingName() {
        return teachingName;
    }

    public void setTeachingName(String teachingName) {
        this.teachingName = teachingName;
    }

    public Integer getVideoType() {
        return videoType;
    }

    public void setVideoType(Integer videoType) {
        this.videoType = videoType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
