package com.uway.sports.web.controller;

import com.uway.sports.common.config.Global;
import com.uway.sports.common.controller.BaseController;
import com.uway.sports.common.utils.*;
import com.uway.sports.web.entity.dbentity.TeachingVideo;
import com.uway.sports.web.entity.dbentity.TeamVideo;
import com.uway.sports.web.entity.paraentity.*;
import com.uway.sports.web.service.IAreaIndustryService;
import com.uway.sports.web.service.ISysUserService;
import com.uway.sports.web.service.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Title
 * @Author liujingce
 * @Date 2019/3/25 9:38
 * @Discription
 */
@Controller
@RequestMapping(value="/video")
public class VideoController extends BaseController {
    @Autowired
    private IVideoService iVideoService;
    @Autowired
    private ISysUserService sysUserServiceImpl;
    @Autowired
    private IAreaIndustryService iAreaIndustryService;

    @RequestMapping(value="/toTeamVideo")
    public String toVideoManage(Model model,HttpServletRequest request){
        if ("goodClickCntEditor".equals(request.getParameter("openWay"))){
            return "jsp/goodClickCntEditor";
        }
        //查询行业进行加载
        model.addAttribute("areaTree",JSONUtils.toJson(iAreaIndustryService.getAreaTree()));
        //查询地域进行加载
        model.addAttribute("industryTree",JSONUtils.toJson(iAreaIndustryService.getIndustryTree()));
        return "jsp/teamVideoManage";
    }

    @RequestMapping(value="getTeamVideoTable",method = RequestMethod.POST)
    @ResponseBody
    public String getTeamVideoTable(HttpServletRequest request){
        String condition = request.getParameter("condition");
        TeamVideoTable param = JSONUtils.fromJson(condition,TeamVideoTable.class);
        TableUtils.getTableEntityForLog(request,param);
        int cnt = iVideoService.getTeamVideoCnt(param);
        List<TeamVideoInfo> list;
        if(cnt > 0){
            list = iVideoService.getTeamVideoTable(param);
        }else{
            list = new ArrayList<>();
        }
        return TableUtils.getTableJsonString(list,cnt,param.getDraw());
    }


    @RequestMapping(value="updateTeamVideoStatus",method = RequestMethod.POST)
    @ResponseBody
    public RestResponse updateTeamStatus(HttpServletRequest request){
        String id = request.getParameter("id");
        String status = request.getParameter("status");
        String isSelected = request.getParameter("isSelected");
        String checkStatus = request.getParameter("checkStatus");
        String checkReason = request.getParameter("checkReason");
        String sort = request.getParameter("sort");

        TeamVideo teamVideo = new TeamVideo();
        teamVideo.setId(Integer.parseInt(id));
        if (StringUtils.isNotBlank(status)){
            teamVideo.setStatus(Integer.parseInt(status));
        }
        if (StringUtils.isNotBlank(isSelected)){
            teamVideo.setIsSelected(Integer.parseInt(isSelected));
        }
        if (StringUtils.isNotBlank(sort)){
            teamVideo.setSort(Integer.parseInt(sort));
        }
        if (StringUtils.isNotBlank(checkStatus)){
            String seesionId = request.getSession().getId();
            String userid = sysUserServiceImpl.getToken(seesionId);
            if (StringUtils.isBlank(userid)){
                return RestResponseFactory.newFailedResponse("用户异常");
            }
            teamVideo.setCheckStatus(Integer.parseInt(checkStatus));
            teamVideo.setCheckUser(Integer.parseInt(userid));
        }
        teamVideo.setCheckReason(checkReason);
        iVideoService.updateTeamVideoStatus(teamVideo);
        return RestResponseFactory.newSuccessfulEmptyResponse();
    }

    @RequestMapping(value="getTeamVideoById",method = RequestMethod.POST)
    @ResponseBody
    public RestResponse getTeamVideoById(HttpServletRequest request){
        String id = request.getParameter("id");
        TeamVideoInfo teamVideo = iVideoService.getTeamVideoById(Integer.parseInt(id));
        return RestResponseFactory.newSuccessfulResponse(teamVideo);
    }


    @RequestMapping(value="/toTeachingVideo")
    public String toTeachingVideoManage(HttpServletRequest request,Model model){
        String parentId = request.getParameter("parentId");
        if (StringUtils.isNotEmpty(parentId)){
            model.addAttribute("parentId",parentId);
        }
        return "jsp/teachingVideoManage";
    }

    @RequestMapping(value="getTeachingVideoTable",method = RequestMethod.POST)
    @ResponseBody
    public String getTeachingVideoTable(HttpServletRequest request){
        String condition = request.getParameter("condition");
        TeachingVideoTable param = JSONUtils.fromJson(condition,TeachingVideoTable.class);
        TableUtils.getTableEntityForLog(request,param);
        int cnt = iVideoService.getTeachingVideoCnt(param);
        List<TeachingVideoInfo> list;
        if(cnt > 0){
            list = iVideoService.getTeachingVideoTable(param);
        }else{
            list = new ArrayList<>();
        }
        return TableUtils.getTableJsonString(list,cnt,param.getDraw());
    }

    @RequestMapping(value="getTeachingVideoById",method = RequestMethod.POST)
    @ResponseBody
    public RestResponse getTeachingVideoById(HttpServletRequest request){
        String id = request.getParameter("id");
        return RestResponseFactory.newSuccessfulResponse(iVideoService.getTeachingVideoById(Integer.parseInt(id)));
    }

    @RequestMapping(value="addTeachingVideo",method = RequestMethod.POST)
    @ResponseBody
    public RestResponse addTeachingVideo(HttpServletRequest request){
        String condition = request.getParameter("condition");
        /*boolean tag = UgcAntiSpam.checktext(Global.getConfig("com.uway.SecretId"),Global.getConfig("com.uway.SecretKey"),condition,Global.getConfig("com.uway.ip"));
        if(!tag){
            return RestResponseFactory.newFailedResponse("视频信息不符合要求！");
        }*/
        TeachingVideo param = JSONUtils.fromJson(condition,TeachingVideo.class);
        String seesionId = request.getSession().getId();
        String userid = sysUserServiceImpl.getToken(seesionId);
        if (StringUtils.isBlank(userid)){
            return RestResponseFactory.newFailedResponse("用户异常");
        }
        param.setInsertUser(Integer.parseInt(userid));
        iVideoService.addTeachingVideo(param);
        return RestResponseFactory.newSuccessfulEmptyResponse();
    }

    @RequestMapping(value="updateTeachingVideoInfo",method = RequestMethod.POST)
    @ResponseBody
    public RestResponse updateTeachingVideoInfo(HttpServletRequest request){
        String condition = request.getParameter("condition");
        /*boolean tag = UgcAntiSpam.checktext(Global.getConfig("com.uway.SecretId"),Global.getConfig("com.uway.SecretKey"),condition,Global.getConfig("com.uway.ip"));
        if(!tag){
            return RestResponseFactory.newFailedResponse("视频信息不符合要求！");
        }*/
        TeachingVideo param = JSONUtils.fromJson(condition,TeachingVideo.class);
        int n = iVideoService.updateTeachingVideoInfo(param);
        if (n > 0){
            return RestResponseFactory.newSuccessfulEmptyResponse();
        }else{
            return RestResponseFactory.newFailedResponse("修改失败");
        }
    }

    @RequestMapping(value="updateTeachingVideoForDel",method = RequestMethod.POST)
    @ResponseBody
    public RestResponse updateTeachingVideoForDel(HttpServletRequest request){
        String id = request.getParameter("id");
        String status = request.getParameter("status");
        TeachingVideo param = new TeachingVideo();
        param.setId(Integer.parseInt(id));
        param.setStatus(Integer.parseInt(status));
        int n = iVideoService.updateTeachingVideoBy(param);
        if (n > 0){
            return RestResponseFactory.newSuccessfulEmptyResponse();
        }else{
            return RestResponseFactory.newFailedResponse("删除异常");
        }
    }

    @RequestMapping(value="updateTeachingVideoForCheck",method = RequestMethod.POST)
    @ResponseBody
    public RestResponse updateTeachingVideoForCheck(HttpServletRequest request){
        String seesionId = request.getSession().getId();
        String userid = sysUserServiceImpl.getToken(seesionId);
        if (StringUtils.isBlank(userid)){
            return RestResponseFactory.newFailedResponse("用户异常");
        }
        String id = request.getParameter("id");
        String checkStatus = request.getParameter("checkStatus");
        String checkReason = request.getParameter("checkReason");
        String sort = request.getParameter("sort");
        TeachingVideo param = new TeachingVideo();
        param.setId(Integer.parseInt(id));
        param.setCheckReason(checkReason);
        param.setCheckStatus(Integer.parseInt(checkStatus));
        // 审核通过，修改状态为已发布
        if ("1".equals(checkStatus)){
            param.setStatus(1);
        }
        param.setCheckUser(Integer.parseInt(userid));
        param.setSort(Integer.parseInt(sort));
        int n = iVideoService.updateTeachingVideoBy(param);
        if (n > 0){
            return RestResponseFactory.newSuccessfulEmptyResponse();
        }else{
            return RestResponseFactory.newFailedResponse("审核异常");
        }
    }

    @RequestMapping(value="updateTeamVideoGoodCnt",method = RequestMethod.POST)
    @ResponseBody
    public RestResponse updateTeamVideoGoodCnt(HttpServletRequest request){
        String ids = request.getParameter("ids");
        String minNum = request.getParameter("minNum");
        String maxNum = request.getParameter("maxNum");
        if (StringUtils.isEmpty(ids) || StringUtils.isEmpty(minNum) || StringUtils.isEmpty(maxNum)){
            return RestResponseFactory.newFailedResponse("数据异常");
        }
        String[] idArr = ids.split(",");
        int n = idArr.length;
        if (n == 0){
            return RestResponseFactory.newFailedResponse("数据异常");
        }
        List<Integer> idList = new ArrayList<>();
        for(int i=0;i<n;i++){
            idList.add(Integer.parseInt(idArr[i]));
        }
        iVideoService.updateTeamVideoGoodCnt(idList,Integer.parseInt(minNum),Integer.parseInt(maxNum));
        return RestResponseFactory.newSuccessfulEmptyResponse();
    }

}
