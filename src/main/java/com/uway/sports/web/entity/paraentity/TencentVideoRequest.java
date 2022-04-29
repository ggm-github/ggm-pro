package com.uway.sports.web.entity.paraentity;

public class TencentVideoRequest {
    /** 关联id */
    private Integer relatedId;
    /** 视频类型  1:赛事宣传/教学视频 */
    private Integer videoType;

    public Integer getRelatedId() {
        return relatedId;
    }

    public void setRelatedId(Integer relatedId) {
        this.relatedId = relatedId;
    }

    public Integer getVideoType() {
        return videoType;
    }

    public void setVideoType(Integer videoType) {
        this.videoType = videoType;
    }
}
