package com.uway.sports.web.tags;

import com.uway.sports.web.utils.UserRightUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * @Title 判断用户权限
 * @Author liujingce
 * @Date 2019/1/29 16:58
 * @Discription
 */
public class HasRightTag extends BodyTagSupport{

    public String url;

    @Override
    public int doStartTag() throws JspException {
        HttpServletRequest request=(HttpServletRequest) pageContext.getRequest();
        boolean hasRight = UserRightUtils.hasRight(request,url);
        //判断是否有权限访问
        if (hasRight) {
            //允许访问标签body
            return BodyTagSupport.EVAL_BODY_INCLUDE;// 返回此则执行标签body中内容，SKIP_BODY则不执行
        } else {
            return BodyTagSupport.SKIP_BODY;
        }
    }

    @Override
    public int doEndTag() throws JspException {
        return BodyTagSupport.EVAL_BODY_INCLUDE;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
