package com.uway.sports.web.entity.paraentity;

import java.util.Date;

/**
 * Auth: liujingce
 * Date: 2018/4/2 11:05
 * Title:
 * Describer:
 **/
public class MessageRequest {
    /** 用户id */
    public Integer userId;
    /** 阅读时间 */
    public Date readTime;
    /** 消息类型 */
    public Integer messageType;
    /** 积分卡id */
    public Integer cardId;

    public MessageRequest() {
        super();
    }

    public MessageRequest(Integer userId) {
        this.userId = userId;
    }

    public MessageRequest(Integer userId, Date readTime) {
        this.userId = userId;
        this.readTime = readTime;
    }

    public MessageRequest(Integer userId, Integer messageType, Integer cardId) {
        this.userId = userId;
        this.messageType = messageType;
        this.cardId = cardId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getReadTime() {
        return readTime;
    }

    public void setReadTime(Date readTime) {
        this.readTime = readTime;
    }

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }
}
