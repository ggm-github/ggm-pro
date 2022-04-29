package com.uway.sports.web.entity.dbentity;

import java.util.Date;

/**
 * Auth: liujingce
 * Date: 2018/4/2 09:44
 * Title: 消息信息
 * Describer:
 **/
public class MessagePush {
    /** 消息id */
    private Integer messageId;
    /** 消息类型 */
    private Integer messageType;
    /** 二级类型 */
    private Integer messageSecondType;
    /** 积分卡id */
    private Integer cardId;
    /** 主消息id （主消息为0） */
    private Integer relId;
    /** 图片路径 */
    private String picUrl;
    /** 创建时间 */
    private String createTime;
    /** 更新时间 */
    private String updateTime;
    /** 状态 0-失效   1-有效 */
    private Integer status;
    private Integer userId;
    private String referentName;
    private String referentUrl;
    private String referentDsc;
    /** 区域id */
    private Integer areaId;
    /** 行业id */
    private Integer industryId;
    /** 赛事id */
    private Integer matchId;
    /** 战队id */
    private Integer teamId;
    /** 打开方式  -1 无跳转  0-链接跳转 1-赛事原生 */
    private Integer openWay;
    /** 消息新增时，根据用户条件查询添加的条件json */
    public String addParamJson;
    /** 是否推送 */
    public Integer isUmeng;

    public String getAddParamJson() {
        return addParamJson;
    }

    public void setAddParamJson(String addParamJson) {
        this.addParamJson = addParamJson;
    }

    public Integer getOpenWay() {
        return openWay;
    }

    public void setOpenWay(Integer openWay) {
        this.openWay = openWay;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public Integer getMessageSecondType() {
        return messageSecondType;
    }

    public void setMessageSecondType(Integer messageSecondType) {
        this.messageSecondType = messageSecondType;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public Integer getRelId() {
        return relId;
    }

    public void setRelId(Integer relId) {
        this.relId = relId;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getReferentName() {
        return referentName;
    }

    public void setReferentName(String referentName) {
        this.referentName = referentName;
    }

    public String getReferentUrl() {
        return referentUrl;
    }

    public void setReferentUrl(String referentUrl) {
        this.referentUrl = referentUrl;
    }

    public String getReferentDsc() {
        return referentDsc;
    }

    public void setReferentDsc(String referentDsc) {
        this.referentDsc = referentDsc;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Integer industryId) {
        this.industryId = industryId;
    }

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    public Integer getIsUmeng() {
        return isUmeng;
    }

    public void setIsUmeng(Integer isUmeng) {
        this.isUmeng = isUmeng;
    }

    public MessagePush() {
        super();
    }

    public MessagePush(Integer messageType, Integer messageSecondType, Integer relId, Integer userId, String referentName, String referentDsc,Integer status) {
        this.messageType = messageType;
        this.messageSecondType = messageSecondType;
        this.relId = relId;
        this.userId = userId;
        this.referentName = referentName;
        this.referentDsc = referentDsc;
        this.status = status;
    }
}
