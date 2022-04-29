package com.uway.sports.web.controller;

import com.uway.sports.common.controller.BaseController;
import com.uway.sports.common.utils.*;
import com.uway.sports.web.entity.dbentity.Area;
import com.uway.sports.web.entity.paraentity.AreaRequest;
import com.uway.sports.web.service.IAreaIndustryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/areaIndustry")
public class AreaIndustryController extends BaseController {
    @Autowired
    private IAreaIndustryService iAreaIndustryService;

    @RequestMapping(value = "/toAreaPage")
    public String toAreaPage(){
        return "jsp/areaManage";
    }

    @ResponseBody
    @RequestMapping(value="/getAreaTable",method = RequestMethod.POST)
    public String getAreaTable(HttpServletRequest request){
        String condition = request.getParameter("condition");
        AreaRequest area = JSONUtils.fromJson(condition,AreaRequest.class);
        TableUtils.getTableEntityForLog(request,area);
        int cnt = iAreaIndustryService.getAreaCnt(area);
        List<Area> list;
        if (cnt > 0){
            list = iAreaIndustryService.getAreaList(area);
        }else{
            list = new ArrayList<>();
        }
        return TableUtils.getTableJsonString(list,cnt,area.getDraw());
    }

    @ResponseBody
    @RequestMapping(value="/getAreaById",method = RequestMethod.POST)
    public RestResponse getAreaById(HttpServletRequest request){
        String areaId = request.getParameter("areaId");
        if(StringUtils.isEmpty(areaId)){
            return RestResponseFactory.newFailedResponse("参数异常");
        }
        Area area = iAreaIndustryService.getAreaById(Integer.parseInt(areaId));
        return RestResponseFactory.newSuccessfulResponse(area);
    }

    @ResponseBody
    @RequestMapping(value="/updateAreaInfo",method = RequestMethod.POST)
    public RestResponse updateAreaInfo(HttpServletRequest request){
        String condition = request.getParameter("condition");
        Area area = JSONUtils.fromJson(condition,Area.class);
        iAreaIndustryService.updateAreaBy(area);
        return RestResponseFactory.newSuccessfulResponse("修改成功");
    }

    @ResponseBody
    @RequestMapping(value="/addAreaInfo",method = RequestMethod.POST)
    public RestResponse addAreaInfo(HttpServletRequest request){
        String condition = request.getParameter("condition");
        Area area = JSONUtils.fromJson(condition,Area.class);
        Map<String,String> result = iAreaIndustryService.addArea(area);
        if ("0".equals(result.get("success"))){
            return RestResponseFactory.newFailedResponse(result.get("message"));
        }
        return RestResponseFactory.newSuccessfulResponse("新增成功");
    }

    @ResponseBody
    @RequestMapping(value="/updateAreaStatus",method = RequestMethod.POST)
    public RestResponse updateAreaStatus(HttpServletRequest request){
        String status = request.getParameter("status");
        String areaId = request.getParameter("areaId");
        if (StringUtils.isBlank(status) || StringUtils.isBlank(areaId)){
            return RestResponseFactory.newFailedResponse("参数异常");
        }
        Area area = new Area();
        area.setAreaId(Integer.parseInt(areaId));
        area.setStatus(Integer.parseInt(status));
        iAreaIndustryService.updateAreaBy(area);
        return RestResponseFactory.newSuccessfulResponse("修改成功");
    }
}
