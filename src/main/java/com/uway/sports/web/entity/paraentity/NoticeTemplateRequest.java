package com.uway.sports.web.entity.paraentity;

import java.util.List;

public class NoticeTemplateRequest {

    /** 通知表id */
    private Integer matchNoticeId;
    /** 通知类型：1app通知 2短信通知 3智能电话通知  */
    private List<Integer> noticeTypeList;

    public Integer getMatchNoticeId() {
        return matchNoticeId;
    }

    public void setMatchNoticeId(Integer matchNoticeId) {
        this.matchNoticeId = matchNoticeId;
    }

    public List<Integer> getNoticeTypeList() {
        return noticeTypeList;
    }

    public void setNoticeTypeList(List<Integer> noticeTypeList) {
        this.noticeTypeList = noticeTypeList;
    }
}
