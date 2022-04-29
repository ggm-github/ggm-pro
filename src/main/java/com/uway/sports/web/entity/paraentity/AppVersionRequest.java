package com.uway.sports.web.entity.paraentity;

/**
 * Auth: liujingce
 * Date: 2018/2/6 13:55
 * Title:
 * Describer:
 **/
public class AppVersionRequest {
    private Integer versionId;
    private Integer versionType;
    private Integer status;

    public Integer getVersionId() {
        return versionId;
    }

    public void setVersionId(Integer versionId) {
        this.versionId = versionId;
    }

    public Integer getVersionType() {
        return versionType;
    }

    public void setVersionType(Integer versionType) {
        this.versionType = versionType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AppVersionRequest{" +
                "versionId=" + versionId +
                ", versionType=" + versionType +
                ", status=" + status +
                '}';
    }
}
