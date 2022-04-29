package com.uway.sports.web.controller;

import com.uway.sports.common.config.Global;
import com.uway.sports.common.controller.BaseController;
import com.uway.sports.common.utils.*;
import com.uway.sports.web.entity.CommConstantEntity;
import com.uway.sports.web.entity.dbentity.*;
import com.uway.sports.web.entity.paraentity.*;
import com.uway.sports.web.service.*;
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
@RequestMapping(value="news")
public class NewsController extends BaseController {
    @Autowired
    private INewsService newsService;
    @Autowired
    private ISysUserService sysUserServiceImpl;
    @Autowired
    private IThemeService iThemeService;
    @Autowired
    private ITeamService teamService;

    @RequestMapping(value="toSportsNews")
    public String toSportsNews(HttpServletRequest request,Model model){
        String operType = request.getParameter("operType");
        if ("add".equals(operType) || "editor".equals(operType)){
            model.addAttribute("operType",operType);
            model.addAttribute("id",request.getParameter("id"));
            return "jsp/addSportsNews";
        }
        model.addAttribute("queryType",request.getParameter("queryType"));
        return "jsp/sportsNewsManage";
    }

    @RequestMapping(value="getSportsNewsTable",method = RequestMethod.POST)
    @ResponseBody
    public String getSportsNewsTable(HttpServletRequest request){
        String condition = request.getParameter("condition");
        SportsNewsTable param = JSONUtils.fromJson(condition,SportsNewsTable.class);
        TableUtils.getTableEntityForLog(request,param);
        int cnt = newsService.getSportsNewsCnt(param);
        List<SportsNewsInfo> list;
        if(cnt > 0){
            list = newsService.getSportsNewsTable(param);
        }else{
            list = new ArrayList<>();
        }
        return TableUtils.getTableJsonString(list,cnt,param.getDraw());
    }

    @RequestMapping(value="addSportsNews",method = RequestMethod.POST)
    @ResponseBody
    public RestResponse addSportsNews(HttpServletRequest request){
        String condition = request.getParameter("condition");

        if("1".equals(Global.getConfig("com.uway.isneedcheck"))) {
            boolean tag = UgcAntiSpam.checktext(Global.getConfig("com.uway.SecretId"), Global.getConfig("com.uway.SecretKey"), condition, Global.getConfig("com.uway.ip"));
            if (!tag) {
                return RestResponseFactory.newFailedResponse("新闻信息不符合要求！");
            }
        }

        SportsNews param = JSONUtils.fromJson(condition,SportsNews.class);
        String seesionId = request.getSession().getId();
        String userid = sysUserServiceImpl.getToken(seesionId);
        if (StringUtils.isBlank(userid)){
            return RestResponseFactory.newFailedResponse("用户异常");
        }
        if(StringUtils.isBlank(param.getNewsUrl())){
            param.setNewsUrl(Global.getConfig("news.visit.url"));
        }
        param.setOptUser(Integer.parseInt(userid));
        newsService.addSportsNews(param);
        return RestResponseFactory.newSuccessfulEmptyResponse();
    }

    @RequestMapping(value="updateSportsNews",method = RequestMethod.POST)
    @ResponseBody
    public RestResponse updateSportsNews(HttpServletRequest request){
        String condition = request.getParameter("condition");

        if("1".equals(Global.getConfig("com.uway.isneedcheck"))) {
            boolean tag = UgcAntiSpam.checktext(Global.getConfig("com.uway.SecretId"), Global.getConfig("com.uway.SecretKey"), condition, Global.getConfig("com.uway.ip"));
            if (!tag) {
                return RestResponseFactory.newFailedResponse("新闻信息不符合要求！");
            }
        }

        SportsNews param = JSONUtils.fromJson(condition,SportsNews.class);
        String seesionId = request.getSession().getId();
        String userid = sysUserServiceImpl.getToken(seesionId);
        if (StringUtils.isBlank(userid)){
            return RestResponseFactory.newFailedResponse("用户异常");
        }
        if(StringUtils.isBlank(param.getNewsUrl())){
            param.setNewsUrl(Global.getConfig("news.visit.url"));
        }
        param.setOptUser(Integer.parseInt(userid));
        newsService.updateSportsNews(param);
        return RestResponseFactory.newSuccessfulEmptyResponse();
    }

    @RequestMapping(value="updateSportsNewsStatus",method = RequestMethod.POST)
    @ResponseBody
    public RestResponse updateSportsNewsStatus(HttpServletRequest request){
        String id = request.getParameter("id");
        String status = request.getParameter("status");
        SportsNews param = new SportsNews();
        param.setId(Integer.parseInt(id));
        param.setStatus(Integer.parseInt(status));
        String seesionId = request.getSession().getId();
        String userId = sysUserServiceImpl.getToken(seesionId);
        if (StringUtils.isBlank(userId)){
            return RestResponseFactory.newFailedResponse("用户异常");
        }
        param.setOptUser(Integer.parseInt(userId));
        newsService.updateSportsNewsBy(param);
        return RestResponseFactory.newSuccessfulEmptyResponse();
    }

    @RequestMapping(value="getSportsNewsById",method = RequestMethod.POST)
    @ResponseBody
    public RestResponse getSportsNewsById(HttpServletRequest request){
        String id = request.getParameter("id");
        return RestResponseFactory.newSuccessfulResponse(newsService.getSportsNewsById(Integer.parseInt(id)));
    }

    @RequestMapping("getNewsDetails")
    public String getNewsDetails(HttpServletRequest request,Model model){
        String id = request.getParameter("id");
        SportsNewsInfo sportsNewsInfo = newsService.getSportsNewsById(Integer.parseInt(id));
        model.addAttribute("NewsTitle",sportsNewsInfo.getNewsTitle());
        model.addAttribute("NewsDate",sportsNewsInfo.getInserttime().split(" ")[0]);
        model.addAttribute("NewsBody",sportsNewsInfo.getNewsContent());
        model.addAttribute("newsAuthor",sportsNewsInfo.getNewsAuthor());
        newsService.updateNewsReadCnt(Integer.parseInt(id));
        return "jsp/newsDetails";
    }

    /**************************************************************************/

    @RequestMapping(value="toMatchNews")
    public String toMatchNews(HttpServletRequest request,Model model){
        //查询行业进行加载
        //model.addAttribute("areaTree",JSONUtils.toJson(iAreaIndustryService.getAreaTree()));
        //查询地域进行加载
        //model.addAttribute("industryTree",JSONUtils.toJson(iAreaIndustryService.getIndustryTree()));
        String operType = request.getParameter("operType");
        if ("add".equals(operType) || "editor".equals(operType)){
            model.addAttribute("operType",operType);
            model.addAttribute("id",request.getParameter("id"));
            return "jsp/addMatchNews";
        }else if("show".equals(operType)){
            model.addAttribute("id",request.getParameter("id"));
            return "jsp/matchNewsShow";
        }
        model.addAttribute("queryType",request.getParameter("queryType"));
        return "jsp/matchNewsManage";
    }

    @RequestMapping(value="getMatchNewsTable",method = RequestMethod.POST)
    @ResponseBody
    public String getMatchNewsTable(HttpServletRequest request){
        String condition = request.getParameter("condition");
        MatchNewsTable param = JSONUtils.fromJson(condition,MatchNewsTable.class);
        TableUtils.getTableEntityForLog(request,param);
        int cnt = newsService.getMatchNewsCnt(param);
        List<MatchNewsInfo> list;
        if(cnt > 0){
            list = newsService.getMatchNewsTable(param);
        }else{
            list = new ArrayList<>();
        }
        return TableUtils.getTableJsonString(list,cnt,param.getDraw());
    }

    @RequestMapping(value="addMatchNews",method = RequestMethod.POST)
    @ResponseBody
    public RestResponse addMatchNews(HttpServletRequest request){
        String condition = request.getParameter("condition");

        if("1".equals(Global.getConfig("com.uway.isneedcheck"))) {
            boolean tag = UgcAntiSpam.checktext(Global.getConfig("com.uway.SecretId"), Global.getConfig("com.uway.SecretKey"), condition, Global.getConfig("com.uway.ip"));
            if (!tag) {
                return RestResponseFactory.newFailedResponse("赛事信息不符合要求！");
            }
        }

        MatchNews param = JSONUtils.fromJson(condition,MatchNews.class);
        String seesionId = request.getSession().getId();
        String userid = sysUserServiceImpl.getToken(seesionId);
        if (StringUtils.isBlank(userid)){
            return RestResponseFactory.newFailedResponse("用户异常");
        }
        param.setOptUser(Integer.parseInt(userid));
        String matchEndtime = param.getMatchEndtime();
        if(StringUtils.isNotBlank(matchEndtime)){
            param.setMatchEndtime(DateUtils.formatDate(DateUtils.parse(matchEndtime,"yyyy-MM-dd")) + " 23:59:59");
        }
        newsService.addMatchNews(param);
        return RestResponseFactory.newSuccessfulEmptyResponse();
    }

    @RequestMapping(value="updateMatchNews",method = RequestMethod.POST)
    @ResponseBody
    public RestResponse updateMatchNews(HttpServletRequest request){
        String condition = request.getParameter("condition");

        if("1".equals(Global.getConfig("com.uway.isneedcheck"))) {
            boolean tag = UgcAntiSpam.checktext(Global.getConfig("com.uway.SecretId"), Global.getConfig("com.uway.SecretKey"), condition, Global.getConfig("com.uway.ip"));
            if (!tag) {
                return RestResponseFactory.newFailedResponse("赛事信息不符合要求！");
            }
        }

        MatchNews param = JSONUtils.fromJson(condition,MatchNews.class);
        String seesionId = request.getSession().getId();
        String userid = sysUserServiceImpl.getToken(seesionId);
        if (StringUtils.isBlank(userid)){
            return RestResponseFactory.newFailedResponse("用户异常");
        }
        param.setOptUser(Integer.parseInt(userid));
        String matchEndtime = param.getMatchEndtime();
        if(StringUtils.isNotBlank(matchEndtime)){
            param.setMatchEndtime(DateUtils.formatDate(DateUtils.parse(matchEndtime,"yyyy-MM-dd")) + " 23:59:59");
        }
        newsService.updateMatchNews(param);
        return RestResponseFactory.newSuccessfulEmptyResponse();
    }

    @RequestMapping(value="updateMatchNewsBy",method = RequestMethod.POST)
    @ResponseBody
    public RestResponse updateMatchNewsBy(HttpServletRequest request){
        String condition = request.getParameter("condition");

        if("1".equals(Global.getConfig("com.uway.isneedcheck"))) {
            boolean tag = UgcAntiSpam.checktext(Global.getConfig("com.uway.SecretId"), Global.getConfig("com.uway.SecretKey"), condition, Global.getConfig("com.uway.ip"));
            if (!tag) {
                return RestResponseFactory.newFailedResponse("赛事信息不符合要求！");
            }
        }

        MatchNews param = JSONUtils.fromJson(condition,MatchNews.class);
        String seesionId = request.getSession().getId();
        String userid = sysUserServiceImpl.getToken(seesionId);
        if (StringUtils.isBlank(userid)){
            return RestResponseFactory.newFailedResponse("用户异常");
        }
        param.setOptUser(Integer.parseInt(userid));
        String matchEndtime = param.getMatchEndtime();
        if(StringUtils.isNotBlank(matchEndtime)){
            param.setMatchEndtime(DateUtils.formatDate(DateUtils.parse(matchEndtime,"yyyy-MM-dd")) + " 23:59:59");
        }
        newsService.updateMatchNewsBy(param);
        return RestResponseFactory.newSuccessfulEmptyResponse();
    }

    @RequestMapping(value="updateMatchNewsStatus",method = RequestMethod.POST)
    @ResponseBody
    public RestResponse updateMatchNewsStatus(HttpServletRequest request){
        Integer id = Integer.parseInt(request.getParameter("id"));
        String status = request.getParameter("status");
        String reason = request.getParameter("reason");
        MatchNews param = new MatchNews();
        param.setId(id);
        param.setStatus(Integer.parseInt(status));
        String seesionId = request.getSession().getId();
        String userid = sysUserServiceImpl.getToken(seesionId);
        if (StringUtils.isBlank(userid)){
            return RestResponseFactory.newFailedResponse("用户异常");
        }
        param.setOptUser(Integer.parseInt(userid));
        param.setReason(reason);
        newsService.updateMatchNewsBy(param);
        // 查询赛事活动信息
        MatchNewsInfo matchNewsInfo = newsService.getMatchNewsById(id);
        if ("1".equals(status) && matchNewsInfo != null){
            // 查询动态是否存在有效的话题
            ThemeInfoTable themeTable = new ThemeInfoTable();
            themeTable.setMatchId(id);
            themeTable.setThemeType(2);
            List<ThemeInfoResult> themeList = iThemeService.getThemeInfoList(themeTable);
            if (themeList.size() == 0){
                // 审核通过，添加一条话题
                ThemeInfo themeInfo = new ThemeInfo(id,2,matchNewsInfo.getMatchTitle(),matchNewsInfo.getMatchRemark(),matchNewsInfo.getMatchImgurl(),0, CommConstantEntity.SPORTS_USER_ID);
                iThemeService.addThemeInfo(themeInfo);
            }else{
                // 存在有效记录，就不做操作，否则，将其中一条改为有效
                boolean operTag = true;
                for(ThemeInfoResult info :themeList){
                    if (1 == info.getStatus()){
                        operTag = false;
                    }
                }
                if (operTag){
                    ThemeInfo themeTemp = new ThemeInfo();
                    themeTemp.setId(themeList.get(0).getId());
                    themeTemp.setStatus(1);
                    iThemeService.updateThemeInfoBy(themeTemp);
                }
            }
        }
        return RestResponseFactory.newSuccessfulEmptyResponse();
    }

    @RequestMapping(value="getMatchNewsById",method = RequestMethod.POST)
    @ResponseBody
    public RestResponse getMatchNewsById(HttpServletRequest request){
        String id = request.getParameter("id");
        return RestResponseFactory.newSuccessfulResponse(newsService.getMatchNewsById(Integer.parseInt(id)));
    }

    @RequestMapping(value="stopMatchTeamVote",method = RequestMethod.POST)
    @ResponseBody
    public RestResponse stopMatchTeamVote(HttpServletRequest request){
        String id = request.getParameter("id");
        Team team = new Team();
        team.setMatchId(Integer.parseInt(id));
        team.setIsVote(0);
        int n = teamService.updateTeamBy(team);
        if (n > 0){
            return RestResponseFactory.newSuccessfulEmptyResponse();
        }else{
            return RestResponseFactory.newFailedResponse("该赛事下已无可投票队伍");
        }
    }

    @RequestMapping(value="getMatchForSelect",method = RequestMethod.POST)
    @ResponseBody
    public List<Map<String,String>> getMatchForSelect(HttpServletRequest request){
        List<Map<String,String>> list = new ArrayList<>();
        MatchNewsTable param = new MatchNewsTable();
        param.setMatchTitle(request.getParameter("gname"));
        param.setStatus(1);
        List<MatchNewsInfo> matchList = newsService.getMatchNewsTable(param);
        if (matchList.size() > 0){
            for (MatchNewsInfo info : matchList){
                Map<String,String> map = new HashMap<>();
                map.put("id",info.getId().toString());
                map.put("name",info.getMatchTitle());
                list.add(map);
            }
        }
        return list;
    }



    /**************************** 赛事小组管理 ********************************************/
    @RequestMapping(value = "toMatchGroup")
    public String toMatchGroup(Model model){
        MatchNewsTable param = new MatchNewsTable();
        param.setStatus(1);
        param.setRowStart(null);
        param.setOrderBy(" id desc ");
        List<MatchNewsInfo> matchNewsList = newsService.getMatchNewsTable(param);
        model.addAttribute("matchNewsList",matchNewsList);
        return "jsp/matchGroupManage";
    }
    @RequestMapping(value = "getMatchGroupTable",method = RequestMethod.POST)
    @ResponseBody
    public String getMatchGroupTable(HttpServletRequest request){
        String condition = request.getParameter("condition");
        MatchGroupTable table = JSONUtils.fromJson(condition,MatchGroupTable.class);
        TableUtils.getTableEntityForLog(request,table);
        List<MatchGroup> list;
        int cnt = newsService.getMatchGroupCnt(table);
        if (cnt > 0){
            list = newsService.getMatchGroupList(table);
        }else {
            list = new ArrayList<>();
        }
        return TableUtils.getTableJsonString(list,cnt,table.getDraw());
    }

    @RequestMapping(value = "getMatchGroupList",method = RequestMethod.POST)
    @ResponseBody
    public RestResponse getMatchGroupList(HttpServletRequest request){
        String condition = request.getParameter("condition");
        MatchGroupTable table = JSONUtils.fromJson(condition,MatchGroupTable.class);
        table.setRowStart(null);
        table.setOrderBy(" id asc ");
        List<MatchGroup> list = newsService.getMatchGroupList(table);
        return RestResponseFactory.newSuccessfulResponse(list);
    }

    @RequestMapping(value = "getMatchGroupInfo",method = RequestMethod.POST)
    @ResponseBody
    public RestResponse getMatchGroupInfo(HttpServletRequest request){
        String id = request.getParameter("id");
        MatchGroup info = newsService.getMatchGroupById(Integer.parseInt(id));
        return RestResponseFactory.newSuccessfulResponse(info);
    }

    @RequestMapping(value = "addMatchGroup",method = RequestMethod.POST)
    @ResponseBody
    public RestResponse addMatchGroup(HttpServletRequest request){
        String condition = request.getParameter("condition");

        if("1".equals(Global.getConfig("com.uway.isneedcheck"))) {
            boolean tag = UgcAntiSpam.checktext(Global.getConfig("com.uway.SecretId"), Global.getConfig("com.uway.SecretKey"), condition, Global.getConfig("com.uway.ip"));
            if (!tag) {
                return RestResponseFactory.newFailedResponse("小组信息不符合要求！");
            }
        }

        MatchGroup param = JSONUtils.fromJson(condition,MatchGroup.class);
        newsService.addMatchGroup(param);
        return RestResponseFactory.newSuccessfulEmptyResponse();
    }

    @RequestMapping(value = "updateMatchGroupInfo",method = RequestMethod.POST)
    @ResponseBody
    public RestResponse updateMatchGroupInfo(HttpServletRequest request){
        String condition = request.getParameter("condition");

        if("1".equals(Global.getConfig("com.uway.isneedcheck"))) {
            boolean tag = UgcAntiSpam.checktext(Global.getConfig("com.uway.SecretId"), Global.getConfig("com.uway.SecretKey"), condition, Global.getConfig("com.uway.ip"));
            if (!tag) {
                return RestResponseFactory.newFailedResponse("赛事信息不符合要求！");
            }
        }

        MatchGroup param = JSONUtils.fromJson(condition,MatchGroup.class);
        newsService.updateMatchGroupInfo(param);
        return RestResponseFactory.newSuccessfulEmptyResponse();
    }

    @RequestMapping(value = "updateMatchGroupBy",method = RequestMethod.POST)
    @ResponseBody
    public RestResponse updateMatchGroupBy(HttpServletRequest request){
        String condition = request.getParameter("condition");
        MatchGroup param = JSONUtils.fromJson(condition,MatchGroup.class);
        newsService.updateMatchGroupBy(param);
        return RestResponseFactory.newSuccessfulEmptyResponse();
    }


}
