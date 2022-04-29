package com.uway.sports.web.entity.dbentity;

public class TxFiletask {
    private String fileid;//'腾讯文件id'
    private String vodTaskId;//'腾讯任务id',
    private String videoUrl;//'转码后的视频url',
    private Integer isTranscode;//'是否转码1是  0否',
    private Integer videotime;//'视频时长',
    /** 视频腾讯云封面url */
    private String	cover_url;

    public String getFileid() {
        return fileid;
    }

    public void setFileid(String fileid) {
        this.fileid = fileid;
    }

    public String getVodTaskId() {
        return vodTaskId;
    }

    public void setVodTaskId(String vodTaskId) {
        this.vodTaskId = vodTaskId;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Integer getIsTranscode() {
        return isTranscode;
    }

    public void setIsTranscode(Integer isTranscode) {
        this.isTranscode = isTranscode;
    }

    public Integer getVideotime() {
        return videotime;
    }

    public void setVideotime(Integer videotime) {
        this.videotime = videotime;
    }

    public String getCover_url() {
        return cover_url;
    }

    public void setCover_url(String cover_url) {
        this.cover_url = cover_url;
    }
}
