package com.uway.sports.web.controller;

import com.alibaba.fastjson.JSON;
import com.uway.sports.common.config.Global;
import com.uway.sports.common.controller.BaseController;
import com.uway.sports.common.utils.*;
import com.uway.sports.push.PushService;
import com.uway.sports.web.entity.CommConstantEntity;
import com.uway.sports.web.entity.dbentity.MessagePush;
import com.uway.sports.web.entity.dbentity.UmengPush;
import com.uway.sports.web.entity.dbentity.User;
import com.uway.sports.web.entity.paraentity.MessagePushTable;
import com.uway.sports.web.entity.paraentity.MessageResponse;
import com.uway.sports.web.entity.paraentity.PushMessage;
import com.uway.sports.web.service.IAppUserService;
import com.uway.sports.web.service.IAreaIndustryService;
import com.uway.sports.web.service.IMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/message")
public class MessageController extends BaseController{
    @Autowired
    private IMessageService messageService;
    @Autowired
    private IAreaIndustryService iAreaIndustryService;
    @Autowired
    private IAppUserService iAppUserService;

    @RequestMapping(value = "/toPage/message")
    public String intoPage(HttpServletRequest request, Model model){
        //查询行业进行加载
        model.addAttribute("areaTree",JSONUtils.toJson(iAreaIndustryService.getAreaTree()));
        //查询地域进行加载
        model.addAttribute("industryTree",JSONUtils.toJson(iAreaIndustryService.getIndustryTree()));
        String operType = request.getParameter("operType");
        if ("add".equals(operType)){
            return "jsp/addMessage";
        }
        return "jsp/message";
    }

    @RequestMapping(value = "getMessageTable")
    @ResponseBody
    public String getMessageTable(HttpServletRequest request, HttpServletResponse response){
        logger.debug("================开始消息列表查询===============");
        String condStr = request.getParameter("condition");
        MessagePushTable condition = JSON.parseObject(condStr, MessagePushTable.class);
        TableUtils.getTableEntityForLog(request,condition);
        logger.debug("参数："+ JSONUtils.toJson(condition));
        int cnt = messageService.getMessageTableCnt(condition);
        List<MessageResponse> list;
        if (cnt > 0){
            list = messageService.getMessageTable(condition);
        }else{
            list = new ArrayList<>();
        }
        logger.debug("================消息列表查询结束===============");
        return TableUtils.getTableJsonString(list,cnt,condition.getDraw());
    }

    @RequestMapping(value="/addMessage")
    @ResponseBody
    public RestResponse addMessage(HttpServletRequest request, HttpServletResponse response){
        String param = request.getParameter("param");

        if("1".equals(Global.getConfig("com.uway.isneedcheck"))) {
            // 敏感词汇校验
            boolean tag = UgcAntiSpam.checktext(Global.getConfig("com.uway.SecretId"), Global.getConfig("com.uway.SecretKey"), param, Global.getConfig("com.uway.ip"));
            if (!tag) {
                return RestResponseFactory.newFailedResponse("消息信息不符合要求！");
            }
        }

        MessagePush addInfo = JSONUtils.fromJson(param,MessagePush.class);
        addInfo.setMessageSecondType(6);
        addInfo.setStatus(0);
        addInfo.setRelId(0);
        return messageService.addMessage(addInfo);
    }

    @RequestMapping(value = "updateMessageStatus")
    @ResponseBody
    public RestResponse updateMessageStatus(HttpServletRequest request, HttpServletResponse response){
        try {
            String param = request.getParameter("param");
            logger.debug("param:"+param);
            HashMap<String,Object> paramMap = JSONUtils.fromJson(param,HashMap.class);
            String ids = (String) paramMap.get("ids");
            if (StringUtils.isNotBlank(ids)){
                String[] idArr = ids.split(",");
                paramMap.put("ids",idArr);
            }else{
                paramMap.put("ids",null);
            }
            messageService.updateMessageStatus(paramMap);
            return RestResponseFactory.newSuccessfulResponse("操作成功");
        }catch (Exception e){
            e.printStackTrace();
            return RestResponseFactory.newFailedResponse(e.getMessage());
        }
    }

    @RequestMapping(value = "updateMessageForSend")
    @ResponseBody
    public RestResponse updateMessageForSend(HttpServletRequest request){
        try {
            String id = request.getParameter("id");
            Map<String,String> result = messageService.updateMessageForSend(Integer.parseInt(id));
            if ("1".equals(result.get("success"))){
                return RestResponseFactory.newSuccessfulEmptyResponse();
            }else{
                return RestResponseFactory.newFailedResponse(result.get("message"));
            }
        }catch (Exception e){
            e.printStackTrace();
            return RestResponseFactory.newFailedResponse(e.getMessage());
        }
    }

    @RequestMapping(value="/getMessageInfo")
    @ResponseBody
    public RestResponse getMessageInfoById(HttpServletRequest request, HttpServletResponse response){
        String messageId = request.getParameter("messageId");
        MessageResponse info = messageService.getMessageById(Integer.parseInt(messageId));
        String addParamJson = info.getAddParamJson();
        String userNames = "全部";
        if (StringUtils.isNotEmpty(addParamJson)){
            User userparam = JSONUtils.fromJson(addParamJson,User.class);
            if (userparam != null && userparam.getIdList() != null && userparam.getIdList().size() > 0){
                List<Integer> userIdList = userparam.getIdList();
                User param = new User();
                param.setIdList(userIdList);
                param.setRowStart(null);
                List<User> list = iAppUserService.getListByUser(param);
                if (list.size() > 0){
                    StringBuffer sb = new StringBuffer();
                    for (User userInfo : list){
                        sb.append(userInfo.getChName()).append(",");
                    }
                    userNames = sb.substring(0,sb.length() - 1);
                }
            }
        }
        info.setUserNames(userNames);
        return RestResponseFactory.newSuccessfulResponse(info);
    }
/*
    @RequestMapping(value="/updateMessage")
    @ResponseBody
    public RestResponse updateMessage(HttpServletRequest request, HttpServletResponse response){
        String param = request.getParameter("param");
        MessagePush info = JSONUtils.fromJson(param,MessagePush.class);
        return messageService.updateMessage(info);
    }
*/

    @RequestMapping(value="/push")
    @ResponseBody
    public RestResponse push(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String condStr = request.getParameter("param");
        if("1".equals(Global.getConfig("com.uway.isneedcheck"))) {
            boolean tag = UgcAntiSpam.checktext(Global.getConfig("com.uway.SecretId"), Global.getConfig("com.uway.SecretKey"), condStr, Global.getConfig("com.uway.ip"));
            if (!tag) {
                return RestResponseFactory.newFailedResponse("消息信息不符合要求！");
            }
        }
        PushMessage condition = JSON.parseObject(condStr, PushMessage.class);
        String activity = condition.getActivity();
        if (StringUtils.isNotEmpty(activity) && !"-1".equals(activity)){
            if (!"0".equals(activity)){
                condition.setActivity(CommConstantEntity.umengActivityMap.get(Integer.parseInt(activity)));
                condition.setModelId(condition.getUrl());
            }
        }
        if (StringUtils.isNotEmpty(condition.getAlias())){
            condition.setSendType(3);
        }
        messageService.addUmenpush(condition);
        return RestResponseFactory.newSuccessfulResponse("操作成功");
    }
    @RequestMapping("/pushManagePage")
    public String pushManagePage(){

        return "jsp/pushManagePage";
    }
    @RequestMapping("/toAddPushPage")
    public String addPushPage(){
        return "jsp/addPush";
    }

    @RequestMapping("/getUmengPushList")
    @ResponseBody
    public String getUmengPushList(HttpServletRequest request){
        String condStr = request.getParameter("condition");
        UmengPush condition = JSON.parseObject(condStr, UmengPush.class);
        TableUtils.getTableEntityForLog(request, condition);
        Long Count = messageService.getListCountByUmengPush(condition);
        List<UmengPush> labelEntityList = null;
        if(Count>0) {
            labelEntityList  = messageService.getListByUmenPush(condition);
        }else {
            return TableUtils.getTableJsonString("[]", Count.intValue(), condition.getDraw());
        }
        String retData = TableUtils.getTableJsonString(labelEntityList, Count.intValue(), condition.getDraw());
        return retData;
    }

}
