package com.uway.sports.web.entity.dbentity;

import java.io.Serializable;
import java.util.Date;


public class FeedBack implements Serializable {
    public static final class FeedBackBuilder {
        private Long id;
        private String userId;
        private String	content;
        private String	responseMessage;
        private Date createTime;
        private String createTimeStr;
        private Integer	 status;
        private String versionCode;

        private FeedBackBuilder() {
        }

        public static FeedBackBuilder aFeedBack() {
            return new FeedBackBuilder();
        }

        public FeedBackBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public FeedBackBuilder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public FeedBackBuilder withContent(String content) {
            this.content = content;
            return this;
        }

        public FeedBackBuilder withResponseMessage(String responseMessage) {
            this.responseMessage = responseMessage;
            return this;
        }

        public FeedBackBuilder withCreateTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }

        public FeedBackBuilder withCreateTimeStr(String createTimeStr) {
            this.createTimeStr = createTimeStr;
            return this;
        }

        public FeedBackBuilder withStatus(Integer status) {
            this.status = status;
            return this;
        }

        public FeedBackBuilder withversionCode(String versionCode) {
            this.versionCode = versionCode;
            return this;
        }

    }


    private Long id;
    /** 人员id */
    private String userId;
    /** 意见反馈内容 */
    private String	content;
    /** 回复信息 */
    private Long responseMessage;
    /** 创建时间 */
    private Date createTime;
    /** 创建时间字符串*/
    private String createTimeStr;
    /** 1:有效,0:无效 */
    private Integer	 status;
    /**版本号*/
    private String versionCode;

    private String clientPic;

    public String getClientPic() {
        return clientPic;
    }

    public void setClientPic(String clientPic) {
        this.clientPic = clientPic;
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
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public Long getResponseMessage() {
		return responseMessage;
	}
	
	public void setResponseMessage(Long responseMessage) {
		this.responseMessage = responseMessage;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }
}
