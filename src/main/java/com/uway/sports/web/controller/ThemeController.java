package com.uway.sports.web.controller;

import com.uway.sports.common.controller.BaseController;
import com.uway.sports.common.utils.*;
import com.uway.sports.web.entity.dbentity.ThemeInfo;
import com.uway.sports.web.entity.paraentity.ThemeInfoResult;
import com.uway.sports.web.entity.paraentity.ThemeInfoTable;
import com.uway.sports.web.service.ISysUserService;
import com.uway.sports.web.service.IThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title
 * @Author liujingce
 * @Date 2019/3/20 15:42
 * @Discription
 */
@Controller
@RequestMapping(value="/theme")
public class ThemeController extends BaseController {
    @Autowired
    private ISysUserService sysUserServiceImpl;
    @Autowired
    private IThemeService iThemeService;

    @RequestMapping(value="toThemePage")
    public String toSportsNews(HttpServletRequest request,Model model){
        String operType = request.getParameter("operType");
        if ("add".equals(operType) || "editor".equals(operType)){
            model.addAttribute("operType",operType);
            model.addAttribute("id",request.getParameter("id"));
            return "jsp/addThemeInfo";
        }
        model.addAttribute("queryType",request.getParameter("queryType"));
        return "jsp/themeManage";
    }

    @RequestMapping(value="getThemeTable",method = RequestMethod.POST)
    @ResponseBody
    public String getThemeTable(HttpServletRequest request){
        String condition = request.getParameter("condition");
        ThemeInfoTable param = JSONUtils.fromJson(condition,ThemeInfoTable.class);
        TableUtils.getTableEntityForLog(request,param);
        int cnt = iThemeService.getThemeInfoCnt(param);
        List<ThemeInfoResult> list;
        if(cnt > 0){
            list = iThemeService.getThemeInfoList(param);
        }else{
            list = new ArrayList<>();
        }
        return TableUtils.getTableJsonString(list,cnt,param.getDraw());
    }

    @RequestMapping(value="addThemeInfo",method = RequestMethod.POST)
    @ResponseBody
    public RestResponse addThemeInfo(HttpServletRequest request){
        String condition = request.getParameter("condition");
        ThemeInfo param = JSONUtils.fromJson(condition,ThemeInfo.class);
        String seesionId = request.getSession().getId();
        String userid = sysUserServiceImpl.getToken(seesionId);
        if (StringUtils.isBlank(userid)){
            return RestResponseFactory.newFailedResponse("用户异常");
        }
        param.setUpdateUserId(Integer.parseInt(userid));
        iThemeService.addThemeInfo(param);
        return RestResponseFactory.newSuccessfulEmptyResponse();
    }

    @RequestMapping(value="updateThemeInfo",method = RequestMethod.POST)
    @ResponseBody
    public RestResponse updateThemeInfo(HttpServletRequest request){
        String condition = request.getParameter("condition");
        ThemeInfo param = JSONUtils.fromJson(condition,ThemeInfo.class);
        String seesionId = request.getSession().getId();
        String userid = sysUserServiceImpl.getToken(seesionId);
        if (StringUtils.isBlank(userid)){
            return RestResponseFactory.newFailedResponse("用户异常");
        }
        param.setUpdateUserId(Integer.parseInt(userid));
        iThemeService.updateThemeInfo(param);
        return RestResponseFactory.newSuccessfulEmptyResponse();
    }

    @RequestMapping(value="updateThemeInfoStatus",method = RequestMethod.POST)
    @ResponseBody
    public RestResponse updateThemeInfoStatus(HttpServletRequest request){
        String id = request.getParameter("id");
        String status = request.getParameter("status");
        ThemeInfo param = new ThemeInfo();
        param.setId(Integer.parseInt(id));
        param.setStatus(Integer.parseInt(status));
        String seesionId = request.getSession().getId();
        String userId = sysUserServiceImpl.getToken(seesionId);
        if (StringUtils.isBlank(userId)){
            return RestResponseFactory.newFailedResponse("用户异常");
        }
        param.setUpdateUserId(Integer.parseInt(userId));
        iThemeService.updateThemeInfoBy(param);
        return RestResponseFactory.newSuccessfulEmptyResponse();
    }

    @RequestMapping(value="getThemeInfoById",method = RequestMethod.POST)
    @ResponseBody
    public RestResponse getSportsNewsById(HttpServletRequest request){
        String id = request.getParameter("id");
        return RestResponseFactory.newSuccessfulResponse(iThemeService.getThemeInfoById(Integer.parseInt(id)));
    }

    @RequestMapping(value="getThemeForSelect",method = RequestMethod.POST)
    @ResponseBody
    public List<Map<String,String>> getThemeForSelect(HttpServletRequest request){
        List<Map<String,String>> list = new ArrayList<>();
        ThemeInfoTable param = new ThemeInfoTable();
        param.setStatus(1);
        List<ThemeInfoResult> themeList = iThemeService.getThemeInfoList(param);
        if (themeList.size() > 0){
            for (ThemeInfoResult result : themeList){
                Map<String,String> map = new HashMap<>();
                map.put("id",result.getId().toString());
                map.put("name",result.getThemeTitle());
                list.add(map);
            }
        }
        return list;
    }

}
