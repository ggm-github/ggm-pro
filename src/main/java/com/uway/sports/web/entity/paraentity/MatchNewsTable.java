package com.uway.sports.web.entity.paraentity;

import com.uway.sports.web.entity.TableEntity;

import java.util.Date;

/**
 * @Title
 * @Author liujingce
 * @Date 2019/3/20 16:01
 * @Discription
 */
public class MatchNewsTable extends TableEntity {

    private String matchTitle;
    private Integer status;
    private Integer matchArea;
    private Integer matchIndustry;
    private Integer isVote;
    private Date checkDate;

    public String getMatchTitle() {
        return matchTitle;
    }

    public void setMatchTitle(String matchTitle) {
        this.matchTitle = matchTitle;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getMatchArea() {
        return matchArea;
    }

    public void setMatchArea(Integer matchArea) {
        this.matchArea = matchArea;
    }

    public Integer getMatchIndustry() {
        return matchIndustry;
    }

    public void setMatchIndustry(Integer matchIndustry) {
        this.matchIndustry = matchIndustry;
    }

    public Integer getIsVote() {
        return isVote;
    }

    public void setIsVote(Integer isVote) {
        this.isVote = isVote;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }
}
