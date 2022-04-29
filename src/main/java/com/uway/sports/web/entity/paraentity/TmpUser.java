package com.uway.sports.web.entity.paraentity;

import java.util.List;

public class TmpUser {

    /** 管理员id */
    private Integer userId;
    /** 登录用户名 */
    private String	userName;
    /** 中文名 */
    private String	chName;
    /** 邮箱 */
    private String	mail;
    /** 电话 */
    private String	phone;
    /** 角色id */
    private Integer roleId;

    private String roleName;
    /** 用户状态 0无效 1正常 */
    private Integer status;

    private String token;

    private List<MenuGroup> menuGroupList;

    private String	createTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getChName() {
        return chName;
    }

    public void setChName(String chName) {
        this.chName = chName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<MenuGroup> getMenuGroupList() {
        return menuGroupList;
    }

    public void setMenuGroupList(List<MenuGroup> menuGroupList) {
        this.menuGroupList = menuGroupList;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
