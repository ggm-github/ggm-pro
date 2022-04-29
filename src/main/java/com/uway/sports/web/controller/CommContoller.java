package com.uway.sports.web.controller;

import com.uway.sports.common.config.Global;
import com.uway.sports.common.redis.SharedRedisClient;
import com.uway.sports.common.utils.*;
import com.uway.sports.web.entity.dbentity.*;
import com.uway.sports.web.entity.paraentity.*;
import com.uway.sports.web.service.*;
import com.uway.sports.web.service.IAppVersionService;
import com.uway.sports.web.service.IStartUpPictureService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

@Controller
@RequestMapping("comm")
public class CommContoller {
    @Resource
    private IAppVersionService appVersionService;

    @Resource
    private ICommonTaskService iCommonTaskService;

    @Resource
    private IStartUpPictureService startUpPictureService;

    @Autowired
    private SharedRedisClient sharedRedisClient;

    public static final String SIGN_TOTAL_KEY = "signTotal";
    public static final String SIGN_TITLE_KEY = "signTitle";
    public static final String ONEDAY_VOTECNT_KEY = "oneDayVoteCnt";

    private Logger logger = LoggerFactory.getLogger(CommContoller.class);

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse handleFormUpload(HttpServletRequest request, @RequestParam(value = "myfile",required = false) CommonsMultipartFile files[]) throws Exception {

        String prifix = Global.getConfig(request.getParameter("path"));
        File direc = new File(prifix);
        if(!direc.exists()){
            direc.mkdirs();
        }
        String newName = "";
        Date nowDate;
        for (int f = 0; f < files.length; f++) {
            if (!files[f].isEmpty()) {
                try {
                    // file.getOriginalFilename()为获得文件的名字
                    nowDate = new Date();
                    String fileUrl = DateUtils.formatDate(nowDate,"yyyyMMddHHmmss") + nowDate.getTime();
                    String fileName= files[f].getOriginalFilename();
                    newName = prifix + fileUrl + fileName.substring(fileName.lastIndexOf("."));

                    BufferedOutputStream stream = new BufferedOutputStream(
                            new FileOutputStream(newName));
                    int length=0;
                    byte[] buffer = new byte[1024];
                    InputStream inputStream = files[f].getInputStream();
                    while ((length = inputStream.read(buffer)) != -1) {
                        stream.write(buffer, 0, length);
                    }
                    stream.flush();
                    inputStream.close();
                    stream.close();
                    /*
                    FileOutputStream os = new FileOutputStream(newName);
                    InputStream in = files[f].getInputStream();
                    int b = 0;
                    while ((b = in.read()) != -1) { //读取文件
                        os.write(b);
                    }
                    os.flush(); //关闭流
                    in.close();
                    os.close();
                    */
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    return RestResponseFactory.newFailedResponse("没找到文件");
                } catch (IOException e) {
                    e.printStackTrace();
                    return RestResponseFactory.newFailedResponse("IO异常");
                }
            }else{
                return RestResponseFactory.newFailedResponse("没有文件");
            }
        }
        return RestResponseFactory.newSuccessfulResponse(newName.substring(5));
    }

    @RequestMapping(value = "/mutiupload", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse mutihandleFormUpload(HttpServletRequest request, @RequestParam(value = "myfile",required = false) CommonsMultipartFile files[]) throws Exception {

        String prifix = Global.getConfig(request.getParameter("path"));
        String cardfile = request.getParameter("cardfile");

        if(cardfile != null && !"".equals(cardfile))
        {
            prifix += cardfile +"/";
        }
        File direc = new File(prifix);
        if(!direc.exists()){
            direc.mkdirs();
        }
        Date nowDate;
        for (int f = 0; f < files.length; f++) {
            if (!files[f].isEmpty()) {
                try {
                    //定义输出流 将文件保存在D盘    file.getOriginalFilename()为获得文件的名字
                    nowDate = new Date();
                    String fileUrl = DateUtils.formatDateTime(nowDate) + nowDate.getTime();
                    String fileName= files[f].getOriginalFilename();
                    fileUrl += fileName.substring(fileName.lastIndexOf("."));

                    BufferedOutputStream stream = new BufferedOutputStream(
                            new FileOutputStream(prifix + fileUrl));
                    int length=0;
                    byte[] buffer = new byte[1024];
                    InputStream inputStream = files[f].getInputStream();
                    while ((length = inputStream.read(buffer)) != -1) {
                        stream.write(buffer, 0, length);
                    }
                    stream.flush();
                    inputStream.close();
                    stream.close();
                    /*
                    FileOutputStream os = new FileOutputStream(prifix + fileUrl);
                    InputStream in = files[f].getInputStream();
                    int b = 0;
                    while ((b = in.read()) != -1) { //读取文件
                        os.write(b);
                    }
                    os.flush(); //关闭流
                    in.close();
                    os.close();
                    */
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    return RestResponseFactory.newFailedResponse("没找到文件");
                } catch (IOException e) {
                    e.printStackTrace();
                    return RestResponseFactory.newFailedResponse("IO异常");
                }
            }else{
                return RestResponseFactory.newFailedResponse("没有文件");
            }
        }
        return RestResponseFactory.newSuccessfulResponse(files.length+"个文件上传成功");
    }

    private String CharacterConvert(String s){
        while (s.contains("'")) {
            s=s.replaceAll("'", "’");
        }
        if (s.contains("(")) {
            s=s.replaceAll("\\(", "（");
            s=s.replace(")", "）");
        }
        return s;
    }

    /**
     * 页面跳转
     * @return
     */
    @RequestMapping(value = "/goToPage/{pageName}")
    public String goToPage(@PathVariable("pageName") String pageName){

        return "jsp/" + pageName;
    }


    /**
     * Auth: liujingce
     * Date: 2018/2/7 11:04
     * Title: 查询app版本列表
     * Describer:
    **/
    @RequestMapping(value = "/getAppVersionList")
    @ResponseBody
    public RestResponse getAppVersionList(HttpServletRequest request){
        String param = request.getParameter("param");
        AppVersionRequest versionParam = JSONUtils.fromJson(param,AppVersionRequest.class);
        List<AppVersion> list = appVersionService.getAppVersionList(versionParam);
        return RestResponseFactory.newSuccessfulResponse(list);
    }
    /**
     * Auth: liujingce
     * Date: 2018/2/7 15:07
     * Title: 添加app版本
     * Describer:
    **/
    @RequestMapping(value = "/addAppVersion")
    @ResponseBody
    public RestResponse addAppVersion(HttpServletRequest request){
        String param = request.getParameter("param");

        if("1".equals(Global.getConfig("com.uway.isneedcheck"))) {
            boolean tag = UgcAntiSpam.checktext(Global.getConfig("com.uway.SecretId"), Global.getConfig("com.uway.SecretKey"), param, Global.getConfig("com.uway.ip"));
            if (!tag) {
                return RestResponseFactory.newFailedResponse("版本信息不符合要求！");
            }
        }

        AppVersion version = JSONUtils.fromJson(param,AppVersion.class);
        appVersionService.addAppVersion(version);
        return RestResponseFactory.newSuccessfulResponse("新增app版本成功");
    }

    /**
     * Auth: liujingce
     * Date: 2018/2/7 15:07
     * Title: 修改app版本信息
     * Describer:
     **/
    @RequestMapping(value = "/updateAppVersion")
    @ResponseBody
    public RestResponse updateAppVersion(HttpServletRequest request){
        String param = request.getParameter("param");

        if("1".equals(Global.getConfig("com.uway.isneedcheck"))) {
            boolean tag = UgcAntiSpam.checktext(Global.getConfig("com.uway.SecretId"), Global.getConfig("com.uway.SecretKey"), param, Global.getConfig("com.uway.ip"));
            if (!tag) {
                return RestResponseFactory.newFailedResponse("版本信息不符合要求！");
            }
        }

        AppVersion version = JSONUtils.fromJson(param,AppVersion.class);
        appVersionService.updateAppVersion(version);
        return RestResponseFactory.newSuccessfulResponse("修改app版本成功");
    }

    /**
     * Auth: liujingce
     * Date: 2018/2/8 11:31
     * Title: 启动页图片的列表查询
     * Describer:
    **/
    @RequestMapping(value = "/getStartUpPictureList")
    @ResponseBody
    public String getStartUpPictureList(HttpServletRequest request){
        String param = request.getParameter("condition");
        StartUpPictureRequest startupParam = JSONUtils.fromJson(param,StartUpPictureRequest.class);
        TableUtils.getTableEntityForLog(request,startupParam);
        int cnt = startUpPictureService.getStartUpPictureListCnt(startupParam);
        List<StartUpPicture> list;
        if (cnt > 0){
            list = startUpPictureService.getStartUpPictureList(startupParam);
        }else{
            list = new ArrayList<>();
        }
        return TableUtils.getTableJsonString(list,cnt,startupParam.getDraw());
    }

    @RequestMapping(value = "/getStartUpPactureInfoById")
    @ResponseBody
    public RestResponse getStartUpPactureInfoById(Integer id){
        StartUpPicture info = startUpPictureService.getStartUpPictureInfoById(id);
        return RestResponseFactory.newSuccessfulResponse(info);
    }

    /**
     * 修改启动图信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/updateStartUpPictureInfo")
    @ResponseBody
    public RestResponse updateStartUpPictureInfo(HttpServletRequest request){
        String param = request.getParameter("param");
        StartUpPicture startupParam = JSONUtils.fromJson(param,StartUpPicture.class);
        startUpPictureService.updateStartUpPicInfo(startupParam);
        return RestResponseFactory.newSuccessfulResponse("");
    }

    /**
     * Auth: liujingce
     * Date: 2018/2/8 11:31
     * Title: 新增启动页图片
     * Describer:
     **/
    @RequestMapping(value = "/addStartUpPicture")
    @ResponseBody
    public RestResponse addStartUpPicture(HttpServletRequest request){
        String param = request.getParameter("param");
        StartUpPicture startupParam = JSONUtils.fromJson(param,StartUpPicture.class);
        startUpPictureService.addStartUpPicture(startupParam);
        return RestResponseFactory.newSuccessfulResponse("新增成功");
    }

    /**
     * Auth: liujingce
     * Date: 2018/2/8 11:31
     * Title: 新增启动页图片
     * Describer:
     **/
    @RequestMapping(value = "/updateStartUpPictureStatus")
    @ResponseBody
    public RestResponse updateStartUpPictureStatus(HttpServletRequest request){
        String param = request.getParameter("param");
        StartUpPictureRequest startupParam = JSONUtils.fromJson(param,StartUpPictureRequest.class);
        startUpPictureService.updateStartUpPictureStatus(startupParam);
        return RestResponseFactory.newSuccessfulResponse("修改成功");
    }

    @RequestMapping("/updateBottomInfo")
    @ResponseBody
    public RestResponse updateBottomInfo(String iconType,String title){
        if(StringUtils.isBlank(iconType) || StringUtils.isBlank(title)){
            return RestResponseFactory.newFailedResponse("图片和标题不能为空");
        }
        iCommonTaskService.updateBottomInfo(iconType,title);
        return RestResponseFactory.newSuccessfulResponse("ok");
    }

    @RequestMapping(value = "shared", method = RequestMethod.GET)
    public String sharedH5(){
        return "jsp/myShareH5";
    }

    @RequestMapping(value = "/apiurl")
    @ResponseBody
    public RestResponse getapiurl(HttpServletRequest request){

        return RestResponseFactory.newSuccessfulResponse(Global.getConfig("common.api.url"));
    }
    @RequestMapping(value = "setting")
    public String toAppSetPage(Model model){
        Map<String,String> signMap = new HashMap<>();
        String signTitle = sharedRedisClient.get(SIGN_TITLE_KEY);
        if(StringUtils.isEmpty(signTitle)){
            signTitle = Global.getConfig("com.uway.signTitle");
        }
        signMap.put("signTitle",signTitle);
        String signTotal = sharedRedisClient.get(SIGN_TOTAL_KEY);
        if(StringUtils.isEmpty(signTotal)){
            signTotal = Global.getConfig("com.uway.signTotal");
        }
        signMap.put("signTotal",signTotal);
        String voteCnt = sharedRedisClient.get(ONEDAY_VOTECNT_KEY);
        if(StringUtils.isEmpty(voteCnt)){
            voteCnt = Global.getConfig("com.uway.OneDayVoteCnt");
        }
        signMap.put("oneDayVoteCnt",voteCnt);
        model.addAttribute("redisData",signMap);
        return "jsp/appsetting";
    }

    @RequestMapping("/updateSignInfo")
    @ResponseBody
    public RestResponse updateSignInfo(String signTitle,String signTotal){
        if(StringUtils.isBlank(signTitle) || StringUtils.isBlank(signTotal)){
            return RestResponseFactory.newFailedResponse("请将打卡信息填写完整");
        }
        sharedRedisClient.set(SIGN_TITLE_KEY,signTitle);
        sharedRedisClient.set(SIGN_TOTAL_KEY,signTotal);
        return RestResponseFactory.newSuccessfulResponse("ok");
    }

    @RequestMapping("/updateOneDayVoteCnt")
    @ResponseBody
    public RestResponse updateOneDayVoteCnt(String voteCnt){
        if(StringUtils.isBlank(voteCnt)){
            return RestResponseFactory.newFailedResponse("请将填写每天投票次数");
        }
        sharedRedisClient.set(ONEDAY_VOTECNT_KEY,voteCnt);
        return RestResponseFactory.newSuccessfulResponse("ok");
    }


}
