package com.uway.sports.web.entity.paraentity;

import com.uway.sports.web.entity.TableEntity;

import java.util.List;

/**
 * @Title
 * @Author liujingce
 * @Date 2019/3/20 16:01
 * @Discription
 */
public class TeamVideoTable extends TableEntity {

    private String videoName;
    private Integer	 videoType;
    private Integer	 checkStatus;
    private Integer	 isSelected;
    private Integer teamIndustryId;
    private Integer teamAreaId;
    private Integer status;
    private String teamNo;
    private String insertTime;
    private Integer relationId;
    private Integer type;
    private String videoDisc;
    private Integer operUserId;

    private List<Integer> teamIds;

    public List<Integer> getTeamIds() {
        return teamIds;
    }

    public void setTeamIds(List<Integer> teamIds) {
        this.teamIds = teamIds;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public Integer getVideoType() {
        return videoType;
    }

    public void setVideoType(Integer videoType) {
        this.videoType = videoType;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Integer getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(Integer isSelected) {
        this.isSelected = isSelected;
    }

    public Integer getTeamIndustryId() {
        return teamIndustryId;
    }

    public void setTeamIndustryId(Integer teamIndustryId) {
        this.teamIndustryId = teamIndustryId;
    }

    public Integer getTeamAreaId() {
        return teamAreaId;
    }

    public void setTeamAreaId(Integer teamAreaId) {
        this.teamAreaId = teamAreaId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTeamNo() {
        return teamNo;
    }

    public void setTeamNo(String teamNo) {
        this.teamNo = teamNo;
    }

    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }

    public Integer getRelationId() {
        return relationId;
    }

    public void setRelationId(Integer relationId) {
        this.relationId = relationId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getVideoDisc() {
        return videoDisc;
    }

    public void setVideoDisc(String videoDisc) {
        this.videoDisc = videoDisc;
    }

    public Integer getOperUserId() {
        return operUserId;
    }

    public void setOperUserId(Integer operUserId) {
        this.operUserId = operUserId;
    }
}
