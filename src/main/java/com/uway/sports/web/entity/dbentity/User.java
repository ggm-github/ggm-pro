package com.uway.sports.web.entity.dbentity;

import com.uway.sports.common.utils.StringUtils;
import com.uway.sports.web.entity.TableEntity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class User extends TableEntity implements Serializable {

	public static final class Builder {
		/** 用户自增id */
		private Long	userId;
		/** 登录名（手机号） */
		private String	loginName;
		/** 登录密码 */
		private String	password;
		/** 昵称 */
		private String	chName;
		/** 头像路径 */
		private String	photoPath;
		/** 性别 */
		private Integer	sex;
		/** 邮箱 */
		private String	mail;
		/** 电话 */
		private String	phone;
		/** 生日 */
		private Date	birthday;
		/** 登录时间 */
		private Date	loginTime;
		/** 退出时间 */
		private Date	quitTime;
		/** 创建时间 */
		private Date	createTime;
		/** 标签（用户等级，金、银、铜等） */
		private Integer	tag;
		/** 用户状态（0是失效 1正常） */
		private Integer	status;

		public Builder withUserId(Long userId) {
			this.userId = userId;
			return this;
		}

		public Builder withLoginName(String loginName) {
			this.loginName = loginName;
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

		public Builder withPhotoPath(String photoPath) {
			this.photoPath = photoPath;
			return this;
		}

		public Builder withSex(Integer sex) {
			this.sex = sex;
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

		public Builder withBirthday(Date birthday) {
			this.birthday = birthday;
			return this;
		}

		public Builder withLoginTime(Date loginTime) {
			this.loginTime = loginTime;
			return this;
		}

		public Builder withQuitTime(Date quitTime) {
			this.quitTime = quitTime;
			return this;
		}

		public Builder withCreateTime(Date createTime) {
			this.createTime = createTime;
			return this;
		}

		public Builder withTag(Integer tag) {
			this.tag = tag;
			return this;
		}

		public Builder withStatus(Integer status) {
			this.status = status;
			return this;
		}

		public User build() {
			User user = new User();
			user.setUserId(userId);
			user.setLoginName(loginName);
			user.setPassword(password);
			user.setChName(chName);
			user.setPhotoPath(photoPath);
			user.setSex(sex);
			user.setMail(mail);
			user.setMail(phone);
			user.setBirthday(birthday);
			user.setLoginTime(loginTime);
			user.setQuitTime(quitTime);
			user.setCreateTime(createTime);
			user.setTag(tag);
			user.setStatus(status);
			return user;
		}
	}

	/** 用户自增id */
	private Long	userId;
	/** 登录名（手机号） */
	private String	loginName;
	/** 登录密码 */
	private String	password;
	/** 昵称 */
	private String	chName;
	/** 头像路径 */
	private String	photoPath;
	/** 性别 */
	private Integer	sex;
	/** 邮箱 */
	private String	mail;
	/** 电话 */
	private String	phone;
	/** 生日 */
	private Date	birthday;
	/** 登录时间 */
	private Date	loginTime;
	/** 退出时间 */
	private Date	quitTime;
	/** 创建时间 */
	private Date	createTime;
	/** 标签（用户等级，金、银、铜等） */
	private Integer	 tag;
	/** 用户状态（0是失效 1正常） */
	private Integer	 status;
	private String createTimeStr;
	private String loginTimeStr;
	private String quitTimeStr;
	private String startDate;
	private String endDate;
	private String birthdayStr;
	//父用户
	private Long parentId;

	private String mobCode;

	private String insertTime;//入库时间
	// 真实姓名
	private String realName;
	// 身份证号
	private String cardNo;
	// 是否实名
	private Integer isReal;

	private List<Integer> idList;
	private Integer teamId;

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public List<Integer> getIdList() {
		return idList;
	}

	public void setIdList(List<Integer> idList) {
		this.idList = idList;
	}

	public void setBirthdayStr(String birthdayStr) {
        this.birthdayStr = birthdayStr;
    }

    public String getBirthdayStr() {
        return birthdayStr;
    }

    public void setLoginTimeStr(String loginTimeStr) {
        this.loginTimeStr = loginTimeStr;
    }

    public void setQuitTimeStr(String quitTimeStr) {
        this.quitTimeStr = quitTimeStr;
    }

    public String getLoginTimeStr() {
        return loginTimeStr;
    }

    public String getQuitTimeStr() {
        return quitTimeStr;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
		if (StringUtils.isNotEmpty(endDate)){
			return endDate+" 23:59:59";
		}
        return endDate;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String getLoginName() {
		return loginName;
	}
	
	public void setLoginName(String loginName) {
		this.loginName = loginName;
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
	
	public String getPhotoPath() {
		return photoPath;
	}
	
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	
	public Integer getSex() {
		return sex;
	}
	
	public void setSex(Integer sex) {
		this.sex = sex;
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
	
	public Date getBirthday() {
		return birthday;
	}
	
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public Date getLoginTime() {
		return loginTime;
	}
	
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	
	public Date getQuitTime() {
		return quitTime;
	}
	
	public void setQuitTime(Date quitTime) {
		this.quitTime = quitTime;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Integer getTag() {
		return tag;
	}
	
	public void setTag(Integer tag) {
		this.tag = tag;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public Integer getIsReal() {
		return isReal;
	}

	public void setIsReal(Integer isReal) {
		this.isReal = isReal;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getMobCode() {
		return mobCode;
	}

	public void setMobCode(String mobCode) {
		this.mobCode = mobCode;
	}

	public String getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o){
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		User user = (User) o;

		if (userId != null ? !userId.equals(user.userId) : user.userId != null){
			return false;
		}
		if (loginName != null ? !loginName.equals(user.loginName) : user.loginName != null){
			return false;
		}
		if (password != null ? !password.equals(user.password) : user.password != null){
			return false;
		}
		if (chName != null ? !chName.equals(user.chName) : user.chName != null) {
			return false;
		}
		if (photoPath != null ? !photoPath.equals(user.photoPath) : user.photoPath != null){
			return false;
		}
		if (sex != null ? !sex.equals(user.sex) : user.sex != null){
			return false;
		}
		if (mail != null ? !mail.equals(user.mail) : user.mail != null) {
			return false;
		}
		if (phone != null ? !phone.equals(user.phone) : user.phone != null) {
			return false;
		}
		if (birthday != null ? !birthday.equals(user.birthday) : user.birthday != null) {
			return false;
		}
		if (loginTime != null ? !loginTime.equals(user.loginTime) : user.loginTime != null){
			return false;
		}
		if (quitTime != null ? !quitTime.equals(user.quitTime) : user.quitTime != null){
			return false;
		}
		if (createTime != null ? !createTime.equals(user.createTime) : user.createTime != null) {
			return false;
		}
		if (tag != null ? !tag.equals(user.tag) : user.tag != null) {
			return false;
		}
		return status != null ? status.equals(user.status) : user.status == null;
	}

	@Override
	public int hashCode() {
		int result = userId != null ? userId.hashCode() : 0;
		result = 31 * result + (loginName != null ? loginName.hashCode() : 0);
		result = 31 * result + (password != null ? password.hashCode() : 0);
		result = 31 * result + (chName != null ? chName.hashCode() : 0);
		result = 31 * result + (photoPath != null ? photoPath.hashCode() : 0);
		result = 31 * result + (sex != null ? sex.hashCode() : 0);
		result = 31 * result + (mail != null ? mail.hashCode() : 0);
		result = 31 * result + (phone != null ? phone.hashCode() : 0);
		result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
		result = 31 * result + (loginTime != null ? loginTime.hashCode() : 0);
		result = 31 * result + (quitTime != null ? quitTime.hashCode() : 0);
		result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
		result = 31 * result + (tag != null ? tag.hashCode() : 0);
		result = 31 * result + (status != null ? status.hashCode() : 0);
		return result;
	}
}
