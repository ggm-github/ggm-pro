package com.uway.sports.web.entity.dbentity;

public class UserPost extends User {
    private Long bindCards;

    public void setBindCards(Long bindCards) {
        this.bindCards = bindCards;
    }

    public Long getBindCards() {
        return bindCards;
    }

    private Integer terminalType;

    public Integer getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(Integer terminalType) {
        this.terminalType = terminalType;
    }

    private int uchild;//直接成员个数
    private int indirect;//间接成员个数
    // 用于处理电话查看权限
    private int phoneRightStatus;

    public int getUchild() {
        return uchild;
    }

    public void setUchild(int uchild) {
        this.uchild = uchild;
    }

    public int getIndirect() {
        return indirect;
    }

    public void setIndirect(int indirect) {
        this.indirect = indirect;
    }

    public int getPhoneRightStatus() {
        return phoneRightStatus;
    }

    public void setPhoneRightStatus(int phoneRightStatus) {
        this.phoneRightStatus = phoneRightStatus;
    }
}
