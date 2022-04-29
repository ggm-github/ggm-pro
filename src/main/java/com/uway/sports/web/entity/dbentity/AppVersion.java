package com.uway.sports.web.entity.dbentity;

/**
 * Auth: liujingce
 * Date: 2018/2/6 13:55
 * Title:
 * Describer:
 **/
public class AppVersion {
    private Integer versionId;
    private Integer versionType;
    private Integer versionCode;
    private String lastVersion;
    private Integer lowerVersion;
    private String versionInfo;
    private String versionUrl;
    private Integer isMustUpdate;
    private String createTime;
    private String updateTime;
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

    public Integer getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(Integer versionCode) {
        this.versionCode = versionCode;
    }

    public String getLastVersion() {
        return lastVersion;
    }

    public void setLastVersion(String lastVersion) {
        this.lastVersion = lastVersion;
    }

    public Integer getLowerVersion() {
        return lowerVersion;
    }

    public void setLowerVersion(Integer lowerVersion) {
        this.lowerVersion = lowerVersion;
    }

    public String getVersionInfo() {
        return versionInfo;
    }

    public void setVersionInfo(String versionInfo) {
        this.versionInfo = versionInfo;
    }

    public String getVersionUrl() {
        return versionUrl;
    }

    public void setVersionUrl(String versionUrl) {
        this.versionUrl = versionUrl;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsMustUpdate() {
        return isMustUpdate;
    }

    public void setIsMustUpdate(Integer isMustUpdate) {
        this.isMustUpdate = isMustUpdate;
    }

    @Override
    public String toString() {
        return "AppVersion{" +
                "versionId=" + versionId +
                ", versionType=" + versionType +
                ", versionCode=" + versionCode +
                ", lastVersion='" + lastVersion + '\'' +
                ", lowerVersion=" + lowerVersion +
                ", versionInfo='" + versionInfo + '\'' +
                ", versionUrl='" + versionUrl + '\'' +
                ", isMustUpdate=" + isMustUpdate +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", status=" + status +
                '}';
    }
}
