package com.uway.sports.web.entity.paraentity;

import com.uway.sports.web.entity.TableEntity;

/**
 * Created by zhao on 2018/3/5.
 */
public class TimeTable extends TableEntity{
    private String startTime;           //条件:开始时间
    private String endTime;             //条件:结束时间

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
