package com.uway.sports.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.uway.sports.common.config.Global;
import com.uway.sports.common.redis.SharedRedisClient;
import com.uway.sports.common.utils.*;
import com.uway.sports.web.entity.dbentity.FeedBack;
import com.uway.sports.web.entity.dbentity.User;
import com.uway.sports.web.entity.dbentity.UserPost;
import com.uway.sports.web.entity.excel.UserExport;
import com.uway.sports.web.entity.paraentity.TimeTable;
import com.uway.sports.web.service.IAppUserService;
import com.uway.sports.web.service.ISysUserService;
import com.uway.sports.web.utils.UserRightUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("app")
public class AppUserController {
    private static final Log log = LogFactory.getLog(AppUserController.class);
    @Autowired
    private IAppUserService iAppUserService;
    @Autowired
    private SharedRedisClient sharedRedisClient ;
    @Resource
    private ISysUserService sysUserService;

    /**
     * 去人员管理页面
     * @return
     */
    @RequestMapping("userManage")
    public String userManage(){
        return "jsp/userList";
    }


    /**
     * 按条件获取人员列表
     * @param request
     * @return
     */
    @RequestMapping("getListByUser")
    @ResponseBody
    public String userPage(HttpServletRequest request){
        String condStr = request.getParameter("condition");
        User condition = JSON.parseObject(condStr, User.class);
        TableUtils.getTableEntityForLog(request, condition);
        Long count = iAppUserService.getListCountByUser(condition);
        List<User> entityList = null;
        if(count>0) {
            // 判断查看权限
            boolean hasRight1 = UserRightUtils.hasRight(request,"/app/showCardNo");
            boolean hasRight2 = UserRightUtils.hasRight(request,"/app/showPhone");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            entityList =iAppUserService.getBindCardListByUser(condition);
            for (User u:entityList) {
                if (u.getCreateTime()!=null){
                    String createT = sdf.format(u.getCreateTime());
                    u.setCreateTimeStr(createT);
                }
                if (u.getBirthday()!=null){
                    String createT = sdf.format(u.getBirthday());
                    u.setBirthdayStr(createT);
                }
                if (u.getQuitTime()!=null){
                    String createT = sdf.format(u.getQuitTime());
                    u.setQuitTimeStr(createT);
                }
                if (u.getLoginTime()!=null){
                    String createT = sdf.format(u.getLoginTime());
                    u.setLoginTimeStr(createT);
                }
                if (!hasRight1){
                    u.setCardNo(null);
                    u.setRealName(null);
                }
                if(!hasRight2){
                    u.setLoginName(null);
                    u.setPhone(null);
                }

            }
        }else {
            return TableUtils.getTableJsonString("[]", count.intValue(), condition.getDraw());
        }
        String retData = TableUtils.getTableJsonString(entityList, count.intValue(), condition.getDraw());
        return retData;
    }

    /**
     * 添加人员
     * @param request
     * @return
     */
    @RequestMapping("addUser")
    @ResponseBody
    public RestResponse addUser(HttpServletRequest request){
        String condStr = request.getParameter("condition");
        User condition = JSON.parseObject(condStr, User.class);
        User para = new User();
        para.setLoginName(condition.getLoginName());
        List<User> eList = iAppUserService.getListByUser(para);
        if (eList!=null && eList.size()>0){
            return RestResponseFactory.newFailedResponse("用户名已存在!");
        }
        condition.setCreateTime(new Date());
        condition.setStatus(1);
        if(StringUtils.isNotEmpty(condition.getPassword())){
            String codePassword = MiscUtils.MD532(condition.getPassword());
            condition.setPassword(codePassword);
        }
        Long userId =  iAppUserService.addUser(condition);
        condition.setUserId(userId);
        sharedRedisClient.set("user:"+userId, JSONUtils.toJson(condition));
        sharedRedisClient.set("loginName:"+condition.getLoginName(), userId.toString());
        sharedRedisClient.sadd("loginNameList",condition.getLoginName());
        String seesionId = request.getSession().getId();
        String userid = sysUserService.getToken(seesionId);
        log.info("操作人userId:   "+userid+"  操作:添加用户操作");
        return RestResponseFactory.newSuccessfulResponse("添加成功");
    }

    /**
     * 通过用户主键获取用户详细信息
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("getUserById")
    public RestResponse getUserById(Long userId){
        User user = iAppUserService.getUserById(userId);
        if (user.getBirthday()!=null){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String bir = sdf.format(user.getBirthday());
            user.setBirthdayStr(bir);
        }
        return RestResponseFactory.newSuccessfulResponse(user);
    }

    /**
     * 通过用户主键更新,编辑用户信息
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("updateUserById")
    public RestResponse updateUserById(HttpServletRequest request){
        String condStr = request.getParameter("condition");

        if("1".equals(Global.getConfig("com.uway.isneedcheck"))) {
            boolean tag = UgcAntiSpam.checktext(Global.getConfig("com.uway.SecretId"), Global.getConfig("com.uway.SecretKey"), condStr, Global.getConfig("com.uway.ip"));
            if (!tag) {
                return RestResponseFactory.newFailedResponse("用户信息不符合要求！");
            }
        }

        User condition = JSON.parseObject(condStr, User.class);
        //db oprate
        iAppUserService.updateUserById(condition);
        User userDb = iAppUserService.getUserById(condition.getUserId());
        if(userDb != null && (userDb.getStatus() == null || userDb.getStatus()==0)){
            sharedRedisClient.del("user:"+userDb.getUserId());
            sharedRedisClient.del("loginName:"+userDb.getLoginName());
            sharedRedisClient.srem("loginNameList",userDb.getLoginName());
        }else if(userDb != null){
            sharedRedisClient.set("user:"+userDb.getUserId(),JSONUtils.toJson(userDb));
            sharedRedisClient.set("loginName:"+userDb.getLoginName(),userDb.getUserId().toString());
            sharedRedisClient.sadd("loginNameList",userDb.getLoginName());
        }
        String seesionId = request.getSession().getId();
        String userid = sysUserService.getToken(seesionId);
        log.info("操作人userId:   "+userid+"  操作:编辑用户操作");
        return RestResponseFactory.newSuccessfulResponse("");
    }

    /**
     * 去往意见处理页面
     * @return
     */
    @RequestMapping(value = "suggestion", method = RequestMethod.GET)
    public String feedbackManager(){
        return "jsp/feedbackManager";
    }

    /**
     * 意见处理查询
     * @param request
     * @return
     */
    @RequestMapping(value = "getFeedback", method = RequestMethod.POST)
    @ResponseBody
    public String getFeedbackList(HttpServletRequest request){
        TimeTable timeTable;
        try {
            timeTable = JSONUtils.fromJson(request.getParameter("condition"), TimeTable.class);
        }catch (JSONException e) {
            return JSON.toJSONString(RestResponseFactory.newFailedResponse("条件错误"));
        }
        TableUtils.getTableEntityForLog(request, timeTable);
        Long count = iAppUserService.getFeedbackCount(timeTable);
        if(count == 0){
            return TableUtils.getTableJsonString("[]", count.intValue(), timeTable.getDraw());
        }
        List<FeedBack> feedbackList = iAppUserService.getFeedbackTable(timeTable);
        String retData = TableUtils.getTableJsonString(feedbackList, count.intValue(), timeTable.getDraw());
        return retData;
    }

    /**
     * 根据ID查询意见反馈
     * @param id
     * @return
     */
    @RequestMapping(value = "getFeedbackById",method = RequestMethod.GET)
    @ResponseBody
    public RestResponse getFeedbackById(@RequestParam("id") String id){
        return RestResponseFactory.newSuccessfulResponse(
                iAppUserService.getFeedbackById(StringUtils.toLong(id))
        );
    }


    @RequestMapping("exportUserList")
    public void exportUserList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String condStr = request.getParameter("condition");
        condStr = java.net.URLDecoder.decode(condStr, "utf-8");
        UserPost condition = JSON.parseObject(condStr, UserPost.class);
        // 判断是否有查看用户电话的权限
        boolean hasRight1 = UserRightUtils.hasRight(request,"/app/showPhone");

        if (hasRight1){
            condition.setPhoneRightStatus(1);
        }
        List<UserExport> userExportList = iAppUserService.exportUserList(condition);

        ReportExcel reportExcel = new ReportExcel();
        reportExcel.excelExport(userExportList,"用户列表", UserExport.class,1,response,request);
    }

    @RequestMapping(value = "/getAppUserListForSelect",method = RequestMethod.POST)
    @ResponseBody
    public List<Map<String,String>> getAppUserListForSelect(HttpServletRequest request){
        User user = new User();
        user.setChName(request.getParameter("gname"));
        user.setStatus(1);
        List<User> list = iAppUserService.getListByUser(user);
        List<Map<String,String>> mapList = new ArrayList<>();
        if (list.size() > 0){
            for(User userInfo : list){
                Map<String,String> map = new HashMap<>();
                map.put("id",userInfo.getUserId().toString());
                map.put("name",userInfo.getChName());
                mapList.add(map);
            }
        }
        return mapList;
    }
}
