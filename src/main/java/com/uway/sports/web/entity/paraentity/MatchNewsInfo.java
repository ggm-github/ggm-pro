package com.uway.sports.web.entity.paraentity;

import com.uway.sports.web.entity.dbentity.MatchNews;
import com.uway.sports.web.entity.dbentity.TencentVideoInfo;

import java.util.Date;
import java.util.List;

/**
 * @Title
 * @Author liujingce
 * @Date 2019/3/23 11:41
 * @Discription
 */
public class MatchNewsInfo extends MatchNews{
    private String optUserName;
    private String matchIndustryName;
    private String matchAreaName;
    // 可投票战队数量（用来判断停止投票按钮显隐）
    private Integer canVoteCnt;
    private String sortDateStr;
    // 比赛地点（省、直辖市）
    private String matchProvinceName;
    // 比赛地点（市、直辖市的区）
    private String matchCityName;
    // 视频列表
    private List<TencentVideoInfo> videoList;
    // 项目列表
    private List<MatchItemRelInfo> itemList;

    public Integer getCanVoteCnt() {
        return canVoteCnt;
    }

    public void setCanVoteCnt(Integer canVoteCnt) {
        this.canVoteCnt = canVoteCnt;
    }

    public String getMatchIndustryName() {
        return matchIndustryName;
    }

    public void setMatchIndustryName(String matchIndustryName) {
        this.matchIndustryName = matchIndustryName;
    }

    public String getMatchAreaName() {
        return matchAreaName;
    }

    public void setMatchAreaName(String matchAreaName) {
        this.matchAreaName = matchAreaName;
    }

    public String getOptUserName() {
        return optUserName;
    }

    public void setOptUserName(String optUserName) {
        this.optUserName = optUserName;
    }

    public String getSortDateStr() {
        return sortDateStr;
    }

    public void setSortDateStr(String sortDateStr) {
        this.sortDateStr = sortDateStr;
    }

    public String getMatchProvinceName() {
        return matchProvinceName;
    }

    public void setMatchProvinceName(String matchProvinceName) {
        this.matchProvinceName = matchProvinceName;
    }

    public String getMatchCityName() {
        return matchCityName;
    }

    public void setMatchCityName(String matchCityName) {
        this.matchCityName = matchCityName;
    }

    public List<TencentVideoInfo> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<TencentVideoInfo> videoList) {
        this.videoList = videoList;
    }

    public List<MatchItemRelInfo> getItemList() {
        return itemList;
    }

    public void setItemList(List<MatchItemRelInfo> itemList) {
        this.itemList = itemList;
    }
}
