package com.uway.sports.web.entity.paraentity;


import com.uway.sports.web.entity.dbentity.MomentsReply;

import java.util.List;

public class MomentsReplyResult extends MomentsReply {

    /** 被回复人昵称 */
    private String replyUserChName;
    /** 回复人昵称 */
    private String operUserChName;
    /** 回复人头像 */
    private String operUserPhonePath;

    public String getReplyUserChName() {
        return replyUserChName;
    }

    public void setReplyUserChName(String replyUserChName) {
        this.replyUserChName = replyUserChName;
    }

    public String getOperUserChName() {
        return operUserChName;
    }

    public void setOperUserChName(String operUserChName) {
        this.operUserChName = operUserChName;
    }

    public String getOperUserPhonePath() {
        return operUserPhonePath;
    }

    public void setOperUserPhonePath(String operUserPhonePath) {
        this.operUserPhonePath = operUserPhonePath;
    }

}
