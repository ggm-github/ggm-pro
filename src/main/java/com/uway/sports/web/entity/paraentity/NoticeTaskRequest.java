package com.uway.sports.web.entity.paraentity;

import java.util.Date;

public class NoticeTaskRequest {
    private Date startDate;
    private Date endDate;

    public NoticeTaskRequest() {
        super();
    }

    public NoticeTaskRequest(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
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
