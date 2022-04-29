package com.uway.sports.web.utils;

import com.uway.sports.common.utils.StringUtils;
import com.uway.sports.web.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

/**
 * @Title
 * @Author liujingce
 * @Date 2019/1/30 11:27
 * @Discription
 */
@Component
public class UserRightUtils {

    @Autowired
    private ISysUserService iSysUserService;

    private static UserRightUtils userRightUtils;

    @PostConstruct
    public void init() {
        userRightUtils = this;
        userRightUtils.iSysUserService = this.iSysUserService;
    }

    public static boolean hasRight(HttpServletRequest request,String url){
        String seesionId = request.getSession().getId();
        String userid = userRightUtils.iSysUserService.getToken(seesionId);
        if (StringUtils.isBlank(userid)){
            return false;
        }
        return userRightUtils.iSysUserService.hasRight(url,Integer.parseInt(userid));
    }


}
