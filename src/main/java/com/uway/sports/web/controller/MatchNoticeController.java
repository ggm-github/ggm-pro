package com.uway.sports.web.controller;

import com.uway.sports.common.utils.*;
import com.uway.sports.web.entity.dbentity.MatchNotice;
import com.uway.sports.web.entity.dbentity.NoticeTemplate;
import com.uway.sports.web.entity.paraentity.MatchNoticeResult;
import com.uway.sports.web.entity.paraentity.MatchNoticeTable;
import com.uway.sports.web.entity.paraentity.NoticeTemplateRequest;
import com.uway.sports.web.service.IMatchNoticeService;
import com.uway.sports.web.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/matchNotice")
public class MatchNoticeController {
    @Autowired
    private IMatchNoticeService iMatchNoticeService;
    @Autowired
    private ISysUserService sysUserServiceImpl;

    @RequestMapping(value = "/toMatchNoticePage")
    public String toMatchNoticePage(HttpServletRequest request){
        return "jsp/matchNoticeManage";
    }

    @RequestMapping(value = "/getMatchNoticeTable",method = RequestMethod.POST)
    @ResponseBody
    public String getMatchNoticeTable(HttpServletRequest request){
        String condition = request.getParameter("condition");
        MatchNoticeTable param = JSONUtils.fromJson(condition,MatchNoticeTable.class);
        TableUtils.getTableEntityForLog(request,param);
        int cnt = iMatchNoticeService.getMatchNoticeCnt(param);
        List<MatchNoticeResult> list;
        if(cnt > 0){
            list = iMatchNoticeService.getMatchNoticeList(param);
        }else{
            list = new ArrayList<>();
        }
        return TableUtils.getTableJsonString(list,cnt,param.getDraw());
    }

    @RequestMapping(value = "/updateMatchNoticeForAudit",method = RequestMethod.POST)
    @ResponseBody
    public RestResponse updateMatchNoticeForAudit(HttpServletRequest request){
        Integer id = Integer.parseInt(request.getParameter("id"));
        String noticeTypeStatus = request.getParameter("noticeTypeStatus");
        String remarks = request.getParameter("remarks");
        String seesionId = request.getSession().getId();
        String userid = sysUserServiceImpl.getToken(seesionId);
        if (StringUtils.isBlank(userid)){
            return RestResponseFactory.newFailedResponse("用户异常");
        }
        MatchNotice param = new MatchNotice();
        param.setId(id);
        param.setRemarks(remarks);
        param.setNoticeTypeStatus(noticeTypeStatus);
        param.setUpdatetime(DateUtils.getDateTime());
        iMatchNoticeService.updateMatchNoticeForAudit(param,Integer.parseInt(userid));
        return RestResponseFactory.newSuccessfulEmptyResponse();
    }
    @RequestMapping(value = "/getMatchNoticeById",method = RequestMethod.POST)
    @ResponseBody
    public RestResponse getMatchNoticeById(HttpServletRequest request){
        // 查询通知信息
        Integer id = Integer.parseInt(request.getParameter("id"));
        Map<String,Object> result = new HashMap<>();
        MatchNotice info = iMatchNoticeService.getMatchNoticeById(id);
        result.put("matchNotice",info);
        String noticeType = info.getNoticeType();
        // 查询通知内容信息
        if (StringUtils.isNotEmpty(noticeType)){
            String[] noticeTypeArr = noticeType.split(",");
            List<Integer> noticeTypeList = new ArrayList<>();
            for(int i=0;i<noticeTypeArr.length;i++){
                noticeTypeList.add(Integer.parseInt(noticeTypeArr[i]));
            }
            NoticeTemplateRequest param = new NoticeTemplateRequest();
            param.setMatchNoticeId(id);
            param.setNoticeTypeList(noticeTypeList);
            List<NoticeTemplate> list = iMatchNoticeService.getNoticeTemplateList(param);
            result.put("noticeTemplateList",list);
        }
        return RestResponseFactory.newSuccessfulResponse(result);
    }

}
