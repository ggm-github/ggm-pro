package com.uway.sports.web.entity.dbentity;

import java.io.Serializable;


public class SysUser implements Serializable {

	public static final class Builder {
		/** 管理员id */
		private Integer userId;
		/** 登录用户名 */
		private String	userName;
		/** 密码 */
		private String	password;
		/** 中文名 */
		private String	chName;
		/** 邮箱 */
		private String	mail;
		/** 电话 */
		private String	phone;
		/** 登录时间 */
		private String	loginTime;
		/** 退出时间 */
		private String	quitTime;
		/** 创建时间 */
		private String	createTime;
		/** 角色id */
		private Integer roleId;
		/** 用户状态 0无效 1正常 */
		private Integer	status;

		public Builder withUserId(Integer userId) {
			this.userId = userId;
			return this;
		}

		public Builder withUserName(String userName) {
			this.userName = userName;
			return this;
		}

		public Builder withPassword(String password) {
			this.password = password;
			return this;
		}

		public Builder withChName(String chName) {
			this.chName = chName;
			return this;
		}

		public Builder withMail(String mail) {
			this.mail = mail;
			return this;
		}

		public Builder withPhone(String phone) {
			this.phone = phone;
			return this;
		}

		public Builder withLoginTime(String loginTime) {
			this.loginTime = loginTime;
			return this;
		}

		public Builder withQuitTime(String quitTime) {
			this.quitTime = quitTime;
			return this;
		}

		public Builder withCreateTime(String createTime) {
			this.createTime = createTime;
			return this;
		}

		public Builder withRoleId(Integer roleId) {
			this.roleId = roleId;
			return this;
		}

		public Builder withStatus(Integer status) {
			this.status = status;
			return this;
		}

		public SysUser build() {
			SysUser sysUser = new SysUser();
			sysUser.setUserId(userId);
			sysUser.setUserName(userName);
			sysUser.setPassword(password);
			sysUser.setChName(chName);
			sysUser.setMail(mail);
			sysUser.setPhone(phone);
			sysUser.setLoginTime(loginTime);
			sysUser.setQuitTime(quitTime);
			sysUser.setCreateTime(createTime);
			sysUser.setRoleId(roleId);
			sysUser.setStatus(status);
			return sysUser;
		}
	}

    /** 管理员id */
    private Integer userId;
    /** 登录用户名 */
    private String	userName;
    /** 密码 */
    private String	password;
    /** 中文名 */
    private String	chName;
    /** 邮箱 */
    private String	mail;
    /** 电话 */
    private String	phone;
    /** 登录时间 */
    private String	loginTime;
    /** 退出时间 */
    private String	quitTime;
    /** 创建时间 */
    private String	createTime;
    /** 角色id */
    private Integer roleId;
    /** 用户状态 0无效 1正常 */
    private Integer	status;

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
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
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
	
	public String getLoginTime() {
		return loginTime;
	}
	
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	
	public String getQuitTime() {
		return quitTime;
	}
	
	public void setQuitTime(String quitTime) {
		this.quitTime = quitTime;
	}
	
	public String getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return true;
		}

		SysUser sysUser = (SysUser) o;

		if (userId != null ? !userId.equals(sysUser.userId) : sysUser.userId != null){
			return true;
		}
		if (userName != null ? !userName.equals(sysUser.userName) : sysUser.userName != null) {
			return true;
		}
		if (password != null ? !password.equals(sysUser.password) : sysUser.password != null) {
			return true;
		}
		if (chName != null ? !chName.equals(sysUser.chName) : sysUser.chName != null) {
			return true;
		}
		if (mail != null ? !mail.equals(sysUser.mail) : sysUser.mail != null) {
			return true;
		}
		if (phone != null ? !phone.equals(sysUser.phone) : sysUser.phone != null) {
			return true;
		}
		if (loginTime != null ? !loginTime.equals(sysUser.loginTime) : sysUser.loginTime != null) {
			return true;
		}
		if (quitTime != null ? !quitTime.equals(sysUser.quitTime) : sysUser.quitTime != null){
			return true;
		}
		if (createTime != null ? !createTime.equals(sysUser.createTime) : sysUser.createTime != null) {
			return true;
		}
		if (roleId != null ? !roleId.equals(sysUser.roleId) : sysUser.roleId != null) {
			return true;
		}
		return status != null ? status.equals(sysUser.status) : sysUser.status == null;
	}

	@Override
	public int hashCode() {
		int result = userId != null ? userId.hashCode() : 0;
		result = 31 * result + (userName != null ? userName.hashCode() : 0);
		result = 31 * result + (password != null ? password.hashCode() : 0);
		result = 31 * result + (chName != null ? chName.hashCode() : 0);
		result = 31 * result + (mail != null ? mail.hashCode() : 0);
		result = 31 * result + (phone != null ? phone.hashCode() : 0);
		result = 31 * result + (loginTime != null ? loginTime.hashCode() : 0);
		result = 31 * result + (quitTime != null ? quitTime.hashCode() : 0);
		result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
		result = 31 * result + (roleId != null ? roleId.hashCode() : 0);
		result = 31 * result + (status != null ? status.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "SysUser{" +
				"userId=" + userId +
				", userName='" + userName + '\'' +
				", password='" + password + '\'' +
				", chName='" + chName + '\'' +
				", mail='" + mail + '\'' +
				", phone='" + phone + '\'' +
				", loginTime=" + loginTime +
				", quitTime=" + quitTime +
				", createTime=" + createTime +
				", roleId=" + roleId +
				", status=" + status +
				'}';
	}
}
