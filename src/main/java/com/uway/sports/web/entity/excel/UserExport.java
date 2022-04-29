package com.uway.sports.web.entity.excel;

import com.uway.sports.common.utils.ExcelAnnotation;

public class UserExport {

    @ExcelAnnotation(id=1,name={"用户id"},width = 5000)
    private Long userId;

    @ExcelAnnotation(id=2,name={"昵称"},width = 5000)
    private String chName;

    @ExcelAnnotation(id=3,name={"性别"},width = 5000)
    private String sex;

    @ExcelAnnotation(id=4,name={"手机号码"},width = 5000)
    private String phone;

    @ExcelAnnotation(id=6,name={"注册时间"},width = 5000)
    private String  createTime;

    @ExcelAnnotation(id=7,name={"父级id"},width = 5000)
    private String  parentId;

    @ExcelAnnotation(id=8,name={"手机系统"},width = 5000)
    private String terminalType;

    @ExcelAnnotation(id=9,name={"最后登录时间"},width = 5000)
    private String loginTime;

    @ExcelAnnotation(id=10,name={"创建时间"},width = 5000)
    private String insertTime;

//    @ExcelAnnotation(id=11,name={"直接成员数"},width = 5000)
//    private Integer uchild;
//    @ExcelAnnotation(id=12,name={"间接成员数"},width = 5000)
//    private Integer indirect;


    public String getChName() {
        return chName;
    }

    public void setChName(String chName) {
        this.chName = chName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(String terminalType) {
        this.terminalType = terminalType;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }

}
