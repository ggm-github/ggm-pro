package com.uway.sports.web.controller;

import com.uway.sports.common.controller.BaseController;
import com.uway.sports.common.utils.JSONUtils;
import com.uway.sports.common.utils.RestResponse;
import com.uway.sports.common.utils.RestResponseFactory;
import com.uway.sports.common.utils.TableUtils;
import com.uway.sports.web.entity.dbentity.MomentsReply;
import com.uway.sports.web.entity.paraentity.MomentsReplyResult;
import com.uway.sports.web.entity.paraentity.MomentsReplyTable;
import com.uway.sports.web.entity.paraentity.ThemeInfoResult;
import com.uway.sports.web.entity.paraentity.ThemeInfoTable;
import com.uway.sports.web.service.IReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="reply")
public class ReplyController extends BaseController {
    @Autowired
    private IReplyService iReplyService;

    @RequestMapping(value="toReplyPage")
    public String toSportsNews(HttpServletRequest request, Model model){
        return "jsp/momentsReplyManage";
    }

    @RequestMapping(value="getMomentsReplyTable",method = RequestMethod.POST)
    @ResponseBody
    public String getMomentsReplyTable(HttpServletRequest request){
        String condition = request.getParameter("condition");
        MomentsReplyTable param = JSONUtils.fromJson(condition,MomentsReplyTable.class);
        TableUtils.getTableEntityForLog(request,param);
        int cnt = iReplyService.getMomentsReplyCnt(param);
        List<MomentsReplyResult> list;
        if(cnt > 0){
            list = iReplyService.getMomentsReplyList(param);
        }else{
            list = new ArrayList<>();
        }
        return TableUtils.getTableJsonString(list,cnt,param.getDraw());
    }

    @RequestMapping(value="getMomentsReplyById",method = RequestMethod.POST)
    @ResponseBody
    public RestResponse getMomentsReplyById(HttpServletRequest request){
        String id = request.getParameter("id");
        MomentsReplyResult info = iReplyService.getMomentsReplyById(Integer.parseInt(id));
        return RestResponseFactory.newSuccessfulResponse(info);
    }

    @RequestMapping(value="updateMomentsReplyStatus",method = RequestMethod.POST)
    @ResponseBody
    public RestResponse updateMomentsReplyStatus(HttpServletRequest request){
        String id = request.getParameter("id");
        String status = request.getParameter("status");
        String remarks = request.getParameter("remarks");
        MomentsReply reply = new MomentsReply();
        reply.setId(Integer.parseInt(id));
        reply.setStatus(Integer.parseInt(status));
        reply.setRemarks(remarks);
        iReplyService.updateMomentsReplyBy(reply);
        return RestResponseFactory.newSuccessfulResponse("操作成功");
    }

    @RequestMapping(value="updateMomentsReplyInfo",method = RequestMethod.POST)
    @ResponseBody
    public RestResponse updateMomentsReplyInfo(HttpServletRequest request){
        String id = request.getParameter("id");
        String sort = request.getParameter("sort");
        MomentsReply reply = new MomentsReply();
        reply.setId(Integer.parseInt(id));
        reply.setSort(Integer.parseInt(sort));
        iReplyService.updateMomentsReplyBy(reply);
        return RestResponseFactory.newSuccessfulResponse("操作成功");
    }
}
