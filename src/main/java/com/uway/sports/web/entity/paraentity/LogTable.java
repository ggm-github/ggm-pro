package com.uway.sports.web.entity.paraentity;

import com.uway.sports.web.entity.TableEntity;

/**
 * Created by zhao on 2018/2/8.
 */
public class LogTable extends TableEntity {
    private Long userId;              //用户ID
    private Integer terminalType;       //操作系统类型
    private String startTime;           //条件:开始时间
    private String endTime;             //条件:结束时间

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(Integer terminalType) {
        this.terminalType = terminalType;
    }

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
