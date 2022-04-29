package com.uway.sports.web.controller;

import com.alibaba.fastjson.JSON;
import com.uway.sports.common.utils.JSONUtils;
import com.uway.sports.common.utils.RestResponse;
import com.uway.sports.common.utils.RestResponseFactory;
import com.uway.sports.common.utils.TableUtils;
import com.uway.sports.web.entity.dbentity.HotSearch;
import com.uway.sports.web.entity.paraentity.HotSearchTable;
import com.uway.sports.web.service.IHotSearchService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * Auth: liujingce
 * Date: 2018/2/2 10:31
 * Title: 热搜词的相关功能的controller
 * Describer:
 **/
@Controller
@RequestMapping(value = "/hotSearch")
public class HotSearchController {
    private Logger logger = LoggerFactory.getLogger(HotSearchController.class);
    @Autowired
    private IHotSearchService hotSearchService;
    /**
     * Auth: liujingce
     * Date: 2018/2/2 10:36
     * Title: 用于页面的跳转
     *Describer:
    **/
    @RequestMapping(value = "/hotSearchManage")
    public String intoPage(HttpServletRequest request, Model model){
        return "jsp/hotSearchManage";
    }

    /**
     * Auth: liujingce
     * Date: 2018/2/2 11:01
     * Title: 栏目内容管理功能的列表查询
     * Describer:
    **/
    @RequestMapping(value = "getHotSearchTable")
    @ResponseBody
    public String getHotSearchTable(HttpServletRequest request, HttpServletResponse response){
        String condStr = request.getParameter("condition");
        HotSearchTable condition = JSON.parseObject(condStr, HotSearchTable.class);
        TableUtils.getTableEntityForLog(request,condition);
        Integer cnt = hotSearchService.getHotSearchTableCnt(condition);
        List<HotSearch> list = hotSearchService.getHotSearchTable(condition);
        return TableUtils.getTableJsonString(list,cnt,condition.getDraw());
    }


    /**
     * Auth: liujingce
     * Date: 2018/2/2 15:33
     * Title: 添加热搜词
     * Describer:
     **/
    @RequestMapping(value = "addHotSearch")
    @ResponseBody
    public RestResponse addHotSearch(HttpServletRequest request, HttpServletResponse response){
        String param = request.getParameter("param");
        HotSearch addInfo = JSONUtils.fromJson(param,HotSearch.class);
        HashMap<String,String> ret = hotSearchService.addHotSearch(addInfo);
        String retCode = ret.get("resultCode");
        String retMsg = ret.get("resultMsg");
        if("1".equals(retCode)){
            return RestResponseFactory.newSuccessfulResponse(retMsg);
        }
        return RestResponseFactory.newFailedResponse(retMsg);
    }

    /**
     * Auth: liujingce
     * Date: 2018/1/31 19:12
     * Title: 根据id查询出详细信息
     * Describer:
     **/
    @RequestMapping(value = "getHotSearchInfo")
    @ResponseBody
    public RestResponse getCyclePlayerInfo(Integer id){
        HotSearch info = hotSearchService.getHotSearchInfo(id);
        return RestResponseFactory.newSuccessfulResponse(info);
    }

    /**
     * Auth: liujingce
     * Date: 2018/1/29 20:11
     * Title: 轮播图的下架（删除）
     * Describer:
    **/
    @RequestMapping(value = "updateStatus")
    @ResponseBody
    public RestResponse deleteCyclePlayer(HttpServletRequest request){
        String param = request.getParameter("param");
        logger.debug("param:"+param);
        HashMap<String,Object> paramMap = JSONUtils.fromJson(param,HashMap.class);
        hotSearchService.updateHotSearchStatus(paramMap);
        return RestResponseFactory.newSuccessfulResponse("操作成功");
    }


    /**
     * Auth: liujingce
     * Date: 2018/1/29 20:11
     * Title: 修改热搜词
     * Describer:
     **/
    @RequestMapping(value = "updateHotSearch")
    @ResponseBody
    public RestResponse updateHotSearchInfo(HttpServletRequest request){

        String param = request.getParameter("param");
        HotSearch info = JSONUtils.fromJson(param,HotSearch.class);
        HashMap<String,String> ret = hotSearchService.updateHotSearchInfo(info);
        String retCode = ret.get("resultCode");
        String retMsg = ret.get("resultMsg");
        if("1".equals(retCode)){
            return RestResponseFactory.newSuccessfulResponse(retMsg);
        }else{
            return RestResponseFactory.newFailedResponse(retMsg);
        }
    }
}
