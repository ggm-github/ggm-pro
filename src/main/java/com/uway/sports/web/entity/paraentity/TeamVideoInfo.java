package com.uway.sports.web.entity.paraentity;

import com.uway.sports.web.entity.TableEntity;
import com.uway.sports.web.entity.dbentity.TeamImage;
import com.uway.sports.web.entity.dbentity.TeamVideo;

import java.util.List;

/**
 * @Title
 * @Author liujingce
 * @Date 2019/3/20 16:01
 * @Discription
 */
public class TeamVideoInfo extends TeamVideo {

    private String teamNo;

    private String videoDiscTemp;

    private List<TeamImage> imageList;

    private String themeIds;

    public String getTeamNo() {
        return teamNo;
    }

    public void setTeamNo(String teamNo) {
        this.teamNo = teamNo;
    }

    public List<TeamImage> getImageList() {
        return imageList;
    }

    public void setImageList(List<TeamImage> imageList) {
        this.imageList = imageList;
    }

    public String getVideoDiscTemp() {
        return videoDiscTemp;
    }

    public void setVideoDiscTemp(String videoDiscTemp) {
        this.videoDiscTemp = videoDiscTemp;
    }

    public String getThemeIds() {
        return themeIds;
    }

    public void setThemeIds(String themeIds) {
        this.themeIds = themeIds;
    }
}
