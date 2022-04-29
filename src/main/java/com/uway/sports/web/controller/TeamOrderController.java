package com.uway.sports.web.controller;

import com.alibaba.fastjson.JSON;
import com.uway.sports.common.utils.*;
import com.uway.sports.web.entity.dbentity.Invoice;
import com.uway.sports.web.entity.dbentity.TeamOrder;
import com.uway.sports.web.entity.dbentity.UserPost;
import com.uway.sports.web.entity.excel.OrderExport;
import com.uway.sports.web.entity.excel.UserExport;
import com.uway.sports.web.entity.paraentity.TeamInfo;
import com.uway.sports.web.entity.paraentity.TeamOrderRequest;
import com.uway.sports.web.entity.paraentity.TeamTable;
import com.uway.sports.web.service.ITeamOrderService;
import com.uway.sports.web.utils.UserRightUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="teamorder")
public class TeamOrderController {
    @Autowired
    private ITeamOrderService iTeamOrderService;

    @RequestMapping(value="toTeamOrderManage")
    public String toTeamOrderManage(Model model){
        return "jsp/teamOrderManage";
    }

    @RequestMapping(value="getTeamTable",method = RequestMethod.POST)
    @ResponseBody
    public String getTeamTable(HttpServletRequest request){
        String condition = request.getParameter("condition");
        TeamOrderRequest param = JSONUtils.fromJson(condition,TeamOrderRequest.class);
        TableUtils.getTableEntityForLog(request,param);
        int cnt = iTeamOrderService.getTeamOrderListcount(param);
        List<TeamOrder> list;
        if(cnt > 0){
            list = iTeamOrderService.getTeamOrderList(param);
        }else{
            list = new ArrayList<>();
        }
        return TableUtils.getTableJsonString(list,cnt,param.getDraw());
    }

    @RequestMapping("exportOrderList")
    public void exportOrderList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String condStr = request.getParameter("condition");
        condStr = java.net.URLDecoder.decode(condStr, "utf-8");
        TeamOrderRequest param = JSONUtils.fromJson(condStr,TeamOrderRequest.class);
        List<OrderExport> list = iTeamOrderService.getExportOrderList(param);

        ReportExcel reportExcel = new ReportExcel();
        reportExcel.excelExport(list,"订单列表", OrderExport.class,1,response,request);
    }

    @RequestMapping(value="getInvoice",method = RequestMethod.POST)
    @ResponseBody
    public RestResponse getInvoice(HttpServletRequest request, @RequestParam Integer orderId){
        Invoice invoice = iTeamOrderService.getInvoice(orderId);
        return RestResponseFactory.newSuccessfulResponse(invoice);
    }
}
