package com.uway.sports.web.entity.paraentity;

import java.io.Serializable;

public class PushMessage  implements Serializable {

    private String title;//标题
    private String subtitle;//副标题
    private String body;//题文 正文
    private String url;//访问的url
    private String activity;//访问的app原生页面
    private String type="0";//0 是url  1是app原生页面
    private String messageType;// 消息发送类型系统类型 0是全部  1是android  2是ios
    private String imageurl="";//访问的url
    private int sendType = 1;   // 1-广播 3-单播
    private String iosDeviceIds;
    private String androidDeviceIds;
    private String alias;
    private String modelId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public int getSendType() {
        return sendType;
    }

    public void setSendType(int sendType) {
        this.sendType = sendType;
    }

    public String getIosDeviceIds() {
        return iosDeviceIds;
    }

    public void setIosDeviceIds(String iosDeviceIds) {
        this.iosDeviceIds = iosDeviceIds;
    }

    public String getAndroidDeviceIds() {
        return androidDeviceIds;
    }

    public void setAndroidDeviceIds(String androidDeviceIds) {
        this.androidDeviceIds = androidDeviceIds;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }
}
