package com.uway.sports.web.controller;

import com.uway.sports.common.config.Global;
import com.uway.sports.common.controller.BaseController;
import com.uway.sports.common.utils.*;
import com.uway.sports.web.entity.dbentity.Advertisement;
import com.uway.sports.web.entity.dbentity.TeachingVideo;
import com.uway.sports.web.entity.paraentity.AdvertisementInfo;
import com.uway.sports.web.entity.paraentity.AdvertisementTable;
import com.uway.sports.web.entity.paraentity.TeachingVideoInfo;
import com.uway.sports.web.service.IAdvertisementService;
import com.uway.sports.web.service.ISysUserService;
import com.uway.sports.web.service.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Title
 * @Author liujingce
 * @Date 2019/4/1 14:00
 * @Discription
 */
@RequestMapping("/ad")
@Controller
public class AdvertisementController extends BaseController {
    @Autowired
    private IAdvertisementService iAdvertisementService;
    @Autowired
    private ISysUserService sysUserServiceImpl;
    @Autowired
    private IVideoService iVideoService;

    @RequestMapping("/toAdManage")
    public String toAdManage(){
        return "jsp/adManage";
    }
    @RequestMapping(value = "/getAdTable",method = RequestMethod.POST)
    @ResponseBody
    public String getAdTable(HttpServletRequest request){
        String condition = request.getParameter("condition");
        AdvertisementTable table = JSONUtils.fromJson(condition,AdvertisementTable.class);
        TableUtils.getTableEntityForLog(request,table);
        int n = iAdvertisementService.getAdCnt(table);
        List<AdvertisementInfo> list;
        if (n > 0){
            list = iAdvertisementService.getAdTable(table);
        }else{
            list = new ArrayList<>();
        }
        return TableUtils.getTableJsonString(list,n,table.getDraw());
    }

    @RequestMapping(value = "/addAdInfo",method = RequestMethod.POST)
    @ResponseBody
    public RestResultResponse addAdInfo(HttpServletRequest request){
        String condition = request.getParameter("condition");

        if("1".equals(Global.getConfig("com.uway.isneedcheck"))) {
            boolean tag = UgcAntiSpam.checktext(Global.getConfig("com.uway.SecretId"), Global.getConfig("com.uway.SecretKey"), condition, Global.getConfig("com.uway.ip"));
            if (!tag) {
                return RestResponseFactory.newFailedResponse("广告信息不符合要求！");
            }
        }

        Advertisement info = JSONUtils.fromJson(condition,Advertisement.class);
        String seesionId = request.getSession().getId();
        String userId = sysUserServiceImpl.getToken(seesionId);
        if (StringUtils.isBlank(userId)){
            return RestResponseFactory.newFailedResponse("用户异常");
        }
        info.setOperuser(Integer.parseInt(userId));
        info.setInsertuser(Integer.parseInt(userId));
        int n = iAdvertisementService.addAdvertisement(info);
        if (n > 0){
            return RestResponseFactory.newSuccessfulEmptyResponse();
        }else{
            return RestResponseFactory.newFailedResponse("新增失败");
        }
    }

    @RequestMapping(value = "/addAdInfoByTeaching",method = RequestMethod.POST)
    @ResponseBody
    public RestResultResponse addAdInfoByTeaching(HttpServletRequest request){
        String idStr = request.getParameter("id");
        String seesionId = request.getSession().getId();
        String userId = sysUserServiceImpl.getToken(seesionId);
        if (StringUtils.isBlank(userId)){
            return RestResponseFactory.newFailedResponse("用户异常");
        }
        TeachingVideo teachingVideo = iVideoService.getTeachingVideoById(Integer.parseInt(idStr));

        Advertisement info = new Advertisement();
        info.setPositionType(1);
        info.setOperuser(Integer.parseInt(userId));
        info.setInsertuser(Integer.parseInt(userId));
        info.setAdvName(teachingVideo.getTeachingName());
        String headUrl = Global.getConfig("prefix.visit.path");
        info.setAdvUrl(headUrl + teachingVideo.getTeachingUrl());
        info.setPicturePath(teachingVideo.getCoverUrl());
        info.setSort(teachingVideo.getSort() == null ? 0L:teachingVideo.getSort().longValue());
        int n = iAdvertisementService.addAdvertisement(info);
        if (n > 0){
            return RestResponseFactory.newSuccessfulEmptyResponse();
        }else{
            return RestResponseFactory.newFailedResponse("新增失败");
        }
    }

    @RequestMapping(value = "/updateAdInfo",method = RequestMethod.POST)
    @ResponseBody
    public RestResultResponse updateAdInfo(HttpServletRequest request){
        String condition = request.getParameter("condition");

        if("1".equals(Global.getConfig("com.uway.isneedcheck"))) {
            boolean tag = UgcAntiSpam.checktext(Global.getConfig("com.uway.SecretId"), Global.getConfig("com.uway.SecretKey"), condition, Global.getConfig("com.uway.ip"));
            if (!tag) {
                return RestResponseFactory.newFailedResponse("广告信息不符合要求！");
            }
        }

        Advertisement info = JSONUtils.fromJson(condition,Advertisement.class);
        String seesionId = request.getSession().getId();
        String userId = sysUserServiceImpl.getToken(seesionId);
        if (StringUtils.isBlank(userId)){
            return RestResponseFactory.newFailedResponse("用户异常");
        }
        info.setOperuser(Integer.parseInt(userId));
        info.setInsertuser(Integer.parseInt(userId));
        int n = iAdvertisementService.updateAdInfoBy(info);
        if (n > 0){
            return RestResponseFactory.newSuccessfulEmptyResponse();
        }else{
            return RestResponseFactory.newFailedResponse("修改失败");
        }
    }

    @RequestMapping(value = "/updateAdForShow",method = RequestMethod.POST)
    @ResponseBody
    public RestResultResponse updateAdForShow(HttpServletRequest request){
        String id = request.getParameter("id");
        String status = request.getParameter("status");
        String remark = request.getParameter("remark");
        Advertisement info = new Advertisement();
        info.setId(Integer.parseInt(id));
        info.setStatus(Integer.parseInt(status));
        info.setRemark(remark);
        String seesionId = request.getSession().getId();
        String userId = sysUserServiceImpl.getToken(seesionId);
        if (StringUtils.isBlank(userId)){
            return RestResponseFactory.newFailedResponse("用户异常");
        }
        info.setOperuser(Integer.parseInt(userId));
        int n = iAdvertisementService.updateAdInfoBy(info);
        if (n > 0){
            return RestResponseFactory.newSuccessfulEmptyResponse();
        }else{
            return RestResponseFactory.newFailedResponse("发布失败");
        }
    }

    @RequestMapping(value = "/getAdInfoById",method = RequestMethod.POST)
    @ResponseBody
    public RestResultResponse getAdInfoById(HttpServletRequest request){
        String id = request.getParameter("id");
        Advertisement info = iAdvertisementService.getAdInfoById(Integer.parseInt(id));
        return RestResponseFactory.newSuccessfulResponse(info);
    }

}
