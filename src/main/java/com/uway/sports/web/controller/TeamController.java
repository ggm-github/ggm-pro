package com.uway.sports.web.controller;

import com.uway.sports.common.config.Global;
import com.uway.sports.common.controller.BaseController;
import com.uway.sports.common.utils.*;
import com.uway.sports.web.entity.dbentity.SportsNews;
import com.uway.sports.web.entity.dbentity.Team;
import com.uway.sports.web.entity.paraentity.SportsNewsInfo;
import com.uway.sports.web.entity.paraentity.SportsNewsTable;
import com.uway.sports.web.entity.paraentity.TeamInfo;
import com.uway.sports.web.entity.paraentity.TeamTable;
import com.uway.sports.web.service.IAreaIndustryService;
import com.uway.sports.web.service.INewsService;
import com.uway.sports.web.service.ISysUserService;
import com.uway.sports.web.service.ITeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
 * @Date 2019/3/20 15:42
 * @Discription
 */
@Controller
@RequestMapping(value="team")
public class TeamController extends BaseController {
    @Autowired
    private ITeamService teamService;
    @Autowired
    private IAreaIndustryService iAreaIndustryService;
    @Autowired
    private ISysUserService sysUserServiceImpl;

    @RequestMapping(value="toTeamManage")
    public String toTeamManage(Model model){
        //查询行业进行加载
        model.addAttribute("areaTree",JSONUtils.toJson(iAreaIndustryService.getAreaTree()));
        //查询地域进行加载
        model.addAttribute("industryTree",JSONUtils.toJson(iAreaIndustryService.getIndustryTree()));

        return "jsp/teamManage";
    }

    @RequestMapping(value="getTeamTable",method = RequestMethod.POST)
    @ResponseBody
    public String getTeamTable(HttpServletRequest request){
        String condition = request.getParameter("condition");
        TeamTable param = JSONUtils.fromJson(condition,TeamTable.class);
        TableUtils.getTableEntityForLog(request,param);
        int cnt = teamService.getTeamCnt(param);
        List<TeamInfo> list;
        if(cnt > 0){
            list = teamService.getTeamTable(param);
        }else{
            list = new ArrayList<>();
        }
        return TableUtils.getTableJsonString(list,cnt,param.getDraw());
    }


    @RequestMapping(value="updateTeamInfo",method = RequestMethod.POST)
    @ResponseBody
    public RestResponse updateTeamInfo(HttpServletRequest request){
        String condition = request.getParameter("condition");

        if("1".equals(Global.getConfig("com.uway.isneedcheck"))) {
            boolean tag = UgcAntiSpam.checktext(Global.getConfig("com.uway.SecretId"), Global.getConfig("com.uway.SecretKey"), condition, Global.getConfig("com.uway.ip"));
            if (!tag) {
                return RestResponseFactory.newFailedResponse("队伍信息不符合要求！");
            }
        }

        Team team = JSONUtils.fromJson(condition,Team.class);
        teamService.updateTeamInfo(team);
        return RestResponseFactory.newSuccessfulEmptyResponse();
    }

    @RequestMapping(value="updateTeamStatus",method = RequestMethod.POST)
    @ResponseBody
    public RestResponse updateTeamStatus(HttpServletRequest request){
        String id = request.getParameter("id");
        String status = request.getParameter("status");
        String isVote = request.getParameter("isVote");
        Team team = new Team();
        team.setId(Integer.parseInt(id));
        if (StringUtils.isNotBlank(status)){
            team.setStatus(Integer.parseInt(status));
        }
        if (StringUtils.isNotBlank(isVote)){
            team.setIsVote(Integer.parseInt(isVote));
        }
        teamService.updateTeamBy(team);
        return RestResponseFactory.newSuccessfulEmptyResponse();
    }

    @RequestMapping(value="getTeamById",method = RequestMethod.POST)
    @ResponseBody
    public RestResponse getSportsNewsById(HttpServletRequest request){
        String id = request.getParameter("id");
        Team team = teamService.getTeamById(Integer.parseInt(id));
        return RestResponseFactory.newSuccessfulResponse(team);
    }

    @RequestMapping(value="updateTeamStatusMult",method = RequestMethod.POST)
    @ResponseBody
    public RestResponse updateTeamStatusMult(HttpServletRequest request){
        String condition = request.getParameter("param");
        TeamTable param = JSONUtils.fromJson(condition,TeamTable.class);
        param.setIsVoteParam(0);
        teamService.updateTeamCloseMult(param);
        return RestResponseFactory.newSuccessfulEmptyResponse();
    }

    @RequestMapping(value="updateTeamStatusCheck",method = RequestMethod.POST)
    @ResponseBody
    public RestResponse updateTeamStatusCheck(HttpServletRequest request){
        String id = request.getParameter("id");
        String checkStatus = request.getParameter("checkStatus");
        String checkReason = request.getParameter("checkReason");
        String seesionId = request.getSession().getId();
        String userid = sysUserServiceImpl.getToken(seesionId);
        if (StringUtils.isBlank(userid)){
            return RestResponseFactory.newFailedResponse("用户异常");
        }
        Team team = new Team();
        team.setId(Integer.parseInt(id));
        team.setCheckStatus(Integer.parseInt(checkStatus));
        team.setCheckReason(checkReason);
        team.setCheckUser(Integer.parseInt(userid));
        teamService.updateTeamStatusCheck(team);
        return RestResponseFactory.newSuccessfulEmptyResponse();
    }

    @RequestMapping(value="sendMatchFinalTeamRanking",method = RequestMethod.POST)
    @ResponseBody
    public RestResponse sendMatchFinalTeamRanking(HttpServletRequest request){
        String matchId = request.getParameter("matchId");
        String isMust = request.getParameter("isMust");
        if (StringUtils.isEmpty(isMust)){
            isMust = "0";
        }
        Map<String,String> result = teamService.sendMatchFinalTeamRanking(Integer.parseInt(matchId),Integer.parseInt(isMust));
        return RestResponseFactory.newSuccessfulResponse(result);
    }

}
