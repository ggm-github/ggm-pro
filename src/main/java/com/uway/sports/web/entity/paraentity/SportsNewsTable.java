package com.uway.sports.web.entity.paraentity;

import com.uway.sports.web.entity.TableEntity;

/**
 * @Title
 * @Author liujingce
 * @Date 2019/3/20 16:01
 * @Discription
 */
public class SportsNewsTable extends TableEntity {

    private String newsTitle;
    private Integer status;

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
