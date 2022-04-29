package com.uway.sports.web.entity.paraentity;


import com.uway.sports.web.entity.TableEntity;

public class MomentsReplyTable extends TableEntity {
    private Integer id;
    /** 话题id */
    private Integer themeId;
    /** 动态id   team_video的id */
    private Integer momentsId;
    /** 被回复的id 动态的回复为0 */
    private Integer replyId;
    /** 被回复人id（回复动态 则为0） */
    private Integer replyUserid;
    /** 回复人id */
    private Integer operUserId;
    /** 状态 0-未发布  1-已发布 */
    private Integer status;

    public Integer getThemeId() {
        return themeId;
    }

    public void setThemeId(Integer themeId) {
        this.themeId = themeId;
    }

    public Integer getMomentsId() {
        return momentsId;
    }

    public void setMomentsId(Integer momentsId) {
        this.momentsId = momentsId;
    }

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public Integer getReplyUserid() {
        return replyUserid;
    }

    public void setReplyUserid(Integer replyUserid) {
        this.replyUserid = replyUserid;
    }

    public Integer getOperUserId() {
        return operUserId;
    }

    public void setOperUserId(Integer operUserId) {
        this.operUserId = operUserId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
