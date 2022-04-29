package com.uway.sports.web.controller;

import com.alibaba.fastjson.JSON;
import com.uway.sports.common.config.Global;
import com.uway.sports.common.controller.BaseController;
import com.uway.sports.common.utils.*;
import com.uway.sports.web.entity.dbentity.CyclePlayerReference;
import com.uway.sports.web.entity.paraentity.CyclePlayerTable;
import com.uway.sports.web.service.IAreaIndustryService;
import com.uway.sports.web.service.ICyclePlayerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

/**
 * @Title
 * @Author liujingce
 * @Date 2019/3/26 10:44
 * @Discription
 */
@Controller
@RequestMapping(value="/cyclePlayer")
public class CyclePlayerController extends BaseController {
    @Autowired
    private ICyclePlayerService iCyclePlayerService;
    @Autowired
    private IAreaIndustryService iAreaIndustryService;

    @RequestMapping(value = "/toCyclePlayerManage")
    public String intoPage(HttpServletRequest request, Model model){
        //查询区域进行加载
        model.addAttribute("areaTree",JSONUtils.toJson(iAreaIndustryService.getAreaTree()));
        return "jsp/cyclePlayerManage";
    }

    /**
     * Auth: liujingce
     * Date: 2018/1/29 15:51
     * Title: 栏目内容管理功能的列表查询
     * Describer:
     **/
    @RequestMapping(value = "getCyclePlayerTable")
    @ResponseBody
    public String getCyclePlayerTable(HttpServletRequest request, HttpServletResponse response){
        logger.debug("================开始栏目内容管理功能的列表查询===============");
        String condStr = request.getParameter("condition");
        CyclePlayerTable condition = JSON.parseObject(condStr, CyclePlayerTable.class);
        TableUtils.getTableEntityForLog(request,condition);
        logger.debug("参数："+ JSONUtils.toJson(condition));
        Integer cnt = iCyclePlayerService.getCyclePlayerTableCnt(condition);
        List<CyclePlayerReference> list = iCyclePlayerService.getCyclePlayerTable(condition);
        logger.debug("================栏目内容管理功能的列表查询结束===============");
        return TableUtils.getTableJsonString(list,cnt,condition.getDraw());
    }

    /**
     * Auth: liujingce
     * Date: 2018/1/31 19:12
     * Title: 根据id查询出详细信息
     * Describer:
     **/
    @RequestMapping(value = "getCyclePlayerInfo")
    @ResponseBody
    public RestResponse getCyclePlayerInfo(HttpServletRequest request, HttpServletResponse response){
        try{
            String id = request.getParameter("id");
            CyclePlayerReference info = iCyclePlayerService.getCyclePlayerInfo(Integer.parseInt(id));
            HashMap<String,Object> ret = new HashMap<String,Object>();
            ret.put("cyclePlayerInfo",info);
            ret.put("referenceInfo",null);
            return RestResponseFactory.newSuccessfulResponse(ret);
        } catch (Exception e){
            e.printStackTrace();
            return RestResponseFactory.newFailedResponse(e.getMessage());
        }
    }


    /**
     * Auth: liujingce
     * Date: 2018/1/31 13:33
     * Title: 添加栏目中的信息
     * Describer:
     **/
    @RequestMapping(value = "addCyclePlayer")
    @ResponseBody
    public RestResponse addCyclePlayer(HttpServletRequest request, HttpServletResponse response){
        String param = request.getParameter("param");

        if("1".equals(Global.getConfig("com.uway.isneedcheck"))) {
            boolean tag = UgcAntiSpam.checktext(Global.getConfig("com.uway.SecretId"), Global.getConfig("com.uway.SecretKey"), param, Global.getConfig("com.uway.ip"));
            if (!tag) {
                return RestResponseFactory.newFailedResponse("栏目信息不符合要求！");
            }
        }

        CyclePlayerReference addInfo = JSONUtils.fromJson(param,CyclePlayerReference.class);
        iCyclePlayerService.addCyclePlayer(addInfo);
        return RestResponseFactory.newSuccessfulEmptyResponse();
    }

    /**
     * Auth: liujingce
     * Date: 2018/1/29 20:11
     * Title: 轮播图的下架（删除）
     * Describer:
     **/
    @RequestMapping(value = "updateStatus")
    @ResponseBody
    public RestResponse deleteCyclePlayer(HttpServletRequest request, HttpServletResponse response){
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
            iCyclePlayerService.updateCyclePlayerStatus(paramMap);
            return RestResponseFactory.newSuccessfulResponse("操作成功");
        }catch (Exception e){
            e.printStackTrace();
            return RestResponseFactory.newFailedResponse(e.getMessage());
        }
    }
    /**
     * Auth: liujingce
     * Date: 2018/1/29 20:11
     * Title: 轮播图的下架（删除）
     * Describer:
     **/
    @RequestMapping(value = "updateCyclePlayer")
    @ResponseBody
    public RestResponse updateCyclePlayerInfo(HttpServletRequest request, HttpServletResponse response){
        String param = request.getParameter("param");

        if("1".equals(Global.getConfig("com.uway.isneedcheck"))) {
            boolean tag = UgcAntiSpam.checktext(Global.getConfig("com.uway.SecretId"), Global.getConfig("com.uway.SecretKey"), param, Global.getConfig("com.uway.ip"));
            if (!tag) {
                return RestResponseFactory.newFailedResponse("栏目信息不符合要求！");
            }
        }

        CyclePlayerReference info = JSONUtils.fromJson(param,CyclePlayerReference.class);
        iCyclePlayerService.updateCyclePlayerInfo(info);
        return RestResponseFactory.newSuccessfulEmptyResponse();
    }
}
