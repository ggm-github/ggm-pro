package com.uway.sports.web.entity.paraentity;


import com.uway.sports.web.entity.TableEntity;

/**
 * Auth: liujingce
 * Date: 2018/4/3 18:45
 * Title:
 * Describer:
 **/
public class MessagePushTable extends TableEntity {
    /** 消息id */
    public Integer messageId;
    /** 消息类型 */
    public Integer messageType;
    /** 积分卡id */
    public Integer cardId;
    /** 活动id、商品id */
    public Integer relId;
    public String relName;

    /** 状态 0-失效   1-有效 */
    public Integer status;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRelName() {
        return relName;
    }

    public void setRelName(String relName) {
        this.relName = relName;
    }
}
