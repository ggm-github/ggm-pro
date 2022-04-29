package com.uway.sports.web.entity.dbentity;

import java.io.Serializable;
import java.util.Date;


public class SysLog implements Serializable {

	public static final class Builder {
		private Long	id;
		/** 用户主键 */
		private String	userId;
		private String	loginName;
		/** 操作时间 */
		private Date	operateTime;
		/** 操作名称 */
		private String	operateName;
		/** 请求类型 */
		private String	method;
		/** 移动端请求url */
		private String	requestUrl;
		/** 请求参数 */
		private String params;
		/** 终端类型 1h5 2ios  3 android */
		private Integer	terminalType;
		/** 操作IP */
		private String	operateIp;

		private String type; 		// 日志类型（1：接入日志；2：错误日志）

		public Builder withId(Long id) {
			this.id = id;
			return this;
		}

		public Builder withUserId(String userId) {
			this.userId = userId;
			return this;
		}

		public Builder withLoginName(String loginName) {
			this.loginName = loginName;
			return this;
		}

		public Builder withOperateTime(Date operateTime) {
			this.operateTime = operateTime;
			return this;
		}

		public Builder withOperateName(String operateName) {
			this.operateName = operateName;
			return this;
		}

		public Builder withMethod(String method) {
			this.method = method;
			return this;
		}

		public Builder withRequestUrl(String requestUrl) {
			this.requestUrl = requestUrl;
			return this;
		}

		public Builder withParams(String params) {
			this.params = params;
			return this;
		}

		public Builder withTerminalType(Integer terminalType) {
			this.terminalType = terminalType;
			return this;
		}

		public Builder withOperateIp(String operateIp) {
			this.operateIp = operateIp;
			return this;
		}
		public Builder withType(String type) {
			this.type = type;
			return this;
		}

		public SysLog build() {
			SysLog sysLog = new SysLog();
			sysLog.setId(id);
			sysLog.setUserId(userId);
			sysLog.setLoginName(loginName);
			sysLog.setOperateTime(operateTime);
			sysLog.setOperateName(operateName);
			sysLog.setMethod(method);
			sysLog.setRequestUrl(requestUrl);
			sysLog.setParams(params);
			sysLog.setTerminalType(terminalType);
			sysLog.setOperateIp(operateIp);
			return sysLog;
		}
	}

	// 日志类型（1：接入日志；2：错误日志）
	public static final String TYPE_ACCESS = "1";
	public static final String TYPE_EXCEPTION = "2";

    private Long	id;
    /** 用户主键 */
    private String	userId;
    private String	loginName;
    /** 操作时间 */
    private Date	operateTime;
    private String operateTimeStr;
    /** 操作名称 */
    private String	operateName;
    /** 请求类型 */
    private String	method;
    /** 移动端请求url */
    private String	requestUrl;
    /** 请求参数 */
    private String	params;
    /** 终端类型 1h5 2ios  3 android */
    private Integer	terminalType;
    /** 操作IP */
    private String	operateIp;

	private String type; 		// 日志类型（1：接入日志；2：错误日志）

	private String userAgent;	// 操作用户代理信息

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

  	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getLoginName() {
		return loginName;
	}
	
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	public Date getOperateTime() {
		return operateTime;
	}
	
	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public String getOperateName() {
		return operateName;
	}
	
	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}
	
	public String getMethod() {
		return method;
	}
	
	public void setMethod(String method) {
		this.method = method;
	}
	
	public String getRequestUrl() {
		return requestUrl;
	}
	
	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public Integer getTerminalType() {
		return terminalType;
	}
	
	public void setTerminalType(Integer terminalType) {
		this.terminalType = terminalType;
	}
	
	public String getOperateIp() {
		return operateIp;
	}
	
	public void setOperateIp(String operateIp) {
		this.operateIp = operateIp;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

    public String getOperateTimeStr() {
        return operateTimeStr;
    }

    public void setOperateTimeStr(String operateTimeStr) {
        this.operateTimeStr = operateTimeStr;
    }
}
