package com.uway.sports.web.controller;

import com.uway.sports.common.controller.BaseController;
import com.uway.sports.common.utils.*;
import com.uway.sports.web.entity.dbentity.PubConfigure;
import com.uway.sports.web.entity.paraentity.TeamVideoInfo;
import com.uway.sports.web.entity.paraentity.TeamVideoTable;
import com.uway.sports.web.service.IPubConfigureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="/configure")
public class PubConfigureController extends BaseController {
    @Autowired
    private IPubConfigureService iPubConfigureService;

    @RequestMapping(value="/toConfigurePage")
    public String toConfigurePage(HttpServletRequest request){


        return "jsp/configureManage";
    }

    @RequestMapping(value="/getPubConfigureTable")
    @ResponseBody
    public String getPubConfigureTable(HttpServletRequest request){
        String condition = request.getParameter("condition");
        PubConfigure param = JSONUtils.fromJson(condition, PubConfigure.class);
        TableUtils.getTableEntityForLog(request,param);
        int cnt = iPubConfigureService.getPubConfigureCnt(param);
        List<PubConfigure> list;
        if(cnt > 0){
            list = iPubConfigureService.getPubConfigureList(param);
        }else{
            list = new ArrayList<>();
        }
        return TableUtils.getTableJsonString(list,cnt,param.getDraw());
    }


    @RequestMapping(value="/getPubConfigureById")
    @ResponseBody
    public RestResponse getPubConfigureById(HttpServletRequest request){
        String id = request.getParameter("id");
        if (StringUtils.isEmpty(id)){
            return RestResponseFactory.newFailedResponse("未查询到参数信息");
        }
        PubConfigure param = new PubConfigure();
        param.setId(Integer.parseInt(id));
        PubConfigure info = iPubConfigureService.getPubConfigureOne(param);
        return RestResponseFactory.newSuccessfulResponse(info);
    }

    @RequestMapping(value="/updatePubConfigure")
    @ResponseBody
    public RestResponse updatePubConfigure(HttpServletRequest request){
        String condition = request.getParameter("condition");
        PubConfigure param = JSONUtils.fromJson(condition, PubConfigure.class);
        iPubConfigureService.updatePubConfigre(param);
        return RestResponseFactory.newSuccessfulResponse("");
    }

}
