package com.uway.sports.web.serviceImpl;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.vod.v20180717.VodClient;
import com.tencentcloudapi.vod.v20180717.models.AiContentReviewResult;
import com.tencentcloudapi.vod.v20180717.models.DescribeTaskDetailRequest;
import com.tencentcloudapi.vod.v20180717.models.DescribeTaskDetailResponse;
import com.uway.sports.common.config.Global;
import com.uway.sports.common.redis.SharedRedisClient;
import com.uway.sports.common.service.BaseServiceImpl;
import com.uway.sports.common.utils.DateUtils;
import com.uway.sports.common.utils.JSONUtils;
import com.uway.sports.common.utils.RestResponse;
import com.uway.sports.web.controller.CommContoller;
import com.uway.sports.web.dao.IAppUserDao;
import com.uway.sports.web.dao.ITeamDao;
import com.uway.sports.web.dao.ITeamOrderDao;
import com.uway.sports.web.entity.CommConstantEntity;
import com.uway.sports.web.entity.dbentity.*;
import com.uway.sports.web.entity.paraentity.*;
import com.uway.sports.web.service.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service("iCommonTaskService")
public class CommonTaskServiceImpl extends BaseServiceImpl implements ICommonTaskService {

    @Autowired
    private SharedRedisClient sharedRedisClient ;
    @Autowired
    private IAppUserDao iAppUserDao;
    @Autowired
    private IAppUserService iAppUserService;
    @Autowired
    private ITeamOrderDao iTeamOrderDao;
    @Autowired
    private ITeamDao iTeamDao;
    @Autowired
    private ITeamService teamService;
    @Autowired
    private IVideoService iVideoService;
    @Autowired
    private INewsService newsService;
    @Autowired
    private IMessageService messageService;
    @Autowired
    private IThemeService iThemeService;
    @Autowired
    private IPubConfigureService iPubConfigureService;

    private org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(CommContoller.class);

    public static final String LOG_INFO_REDIS = "logset";
    public static final String ORDER_CHECK_KEY = "orderCheck";
    public static final String BOTTOM_INFO_KEY = "initAppBottom";
    public final static String VOTE_SET = "vote_set";
    public final static String TEAM = "uway_team:";
    public static final String VIDEO_KEY = "team_video:";

    public static final int LIMIT_NUM = 5000;
    public static final int DEL_LIMIT_NUM = 5000;
    @Override
    /**
     * ???????????????
     */
    public void addAppLog() {
        List<UrlMenu> urlMenuList = getAllurlMenu();
        Map<String,String> map = new HashMap<>();

        for (UrlMenu mu:urlMenuList) {
            map.put(mu.getUrl().toUpperCase(),mu.getUrl_name());
        }
        while (sharedRedisClient.scard(LOG_INFO_REDIS)>0){
           List<String> logstr = sharedRedisClient.srandmember(LOG_INFO_REDIS, 500);
           List<SysLog> sysLogs = new ArrayList<SysLog>();
           for (String log : logstr){
               SysLog sysLog = JSONUtils.fromJson(log, SysLog.class);
               sysLog.setOperateName(map.get(sysLog.getRequestUrl().toUpperCase()));
               if (StringUtils.isNotBlank(sysLog.getParams())) {
                   sysLog.setParams(sysLog.getParams().replaceAll("[\\x{10000}-\\x{10FFFF}]", ""));
               }
               sysLogs.add(sysLog);
           }
            iAppUserDao.addAppLog(sysLogs);
            String[] a = new String[logstr.size()];
            sharedRedisClient.srem(LOG_INFO_REDIS,  logstr.toArray(a));
        }
    }

    @Override
    public List<UrlMenu> getAllurlMenu(){
        return iAppUserDao.getAllurlMenu();
    }

    @Override
    public Map<String, Object> getRedisData() {
        Map<String,Object> result = new HashMap<>();
        Map<String,String> orderCheck = sharedRedisClient.hgetAll(ORDER_CHECK_KEY);
        result.put(ORDER_CHECK_KEY,orderCheck);
        Map<String,String> bottomInfo = sharedRedisClient.hgetAll(BOTTOM_INFO_KEY);
        result.put(BOTTOM_INFO_KEY,bottomInfo);
        return result;
    }

    @Override
    public RestResponse updateBottomInfo(String iconType, String title) {

        sharedRedisClient.del(BOTTOM_INFO_KEY);
        Map<String,String> map = new HashMap<>(2);
        map.put("bottom_title3",title);
        map.put("bottom_icon3",iconType);
        sharedRedisClient.hmset(BOTTOM_INFO_KEY,map);
        return null;
    }

    @Override
    public void updateRanking(){

        List<RankingRequest> rank_tmp = new ArrayList<>();
        teamService.updateRankingRowNo();
        for(int i=1;i<=3;i++){
            rank_tmp = teamService.getareaorindustryList(i);

            if(rank_tmp != null && rank_tmp.size() >0){

                for (RankingRequest rankingR:rank_tmp) {
                    RankingRequest rankingRequest = new RankingRequest();
                    if(i == 1){
                        rankingRequest.setAreaId(rankingR.getAreaId());
                        rankingRequest.setMatchId(rankingR.getMatchId());
                    }
                    if(i == 2){
                        rankingRequest.setMatchId(rankingR.getMatchId());
                    }
                    if(i == 3){
                        rankingRequest.setMatchId(rankingR.getMatchId());
                        rankingRequest.setMatchItemId(rankingR.getAreaId());
                    }

                   List<RankingRequest> rankingRequestList = teamService.getNowranking(rankingRequest);

                    if(rankingRequestList != null && rankingRequestList.size() >0){
                        for (RankingRequest rk:rankingRequestList) {
                            List<Ranking> rankingList = teamService.getrankingList(rk.getTeamId());
                            if(rankingList ==null || rankingList.size() == 0 ){
                                if(i == 1){
                                    Ranking ranking = new Ranking();
                                    ranking.setAreaId(rk.getAreaId());
                                    ranking.setTeamId(rk.getTeamId());
                                    ranking.setAreaRanking(rk.getRowNo());
                                    teamService.addranking(ranking);
                                }
                                else if(i==2){
                                    Ranking ranking = new Ranking();
                                    ranking.setMatchId(rk.getMatchId());
                                    ranking.setTeamId(rk.getTeamId());
                                    ranking.setMatchRanking(rk.getRowNo());
                                    teamService.addranking(ranking);
                                }else if(i==3){
                                    Ranking ranking = new Ranking();
                                    ranking.setMatchId(rk.getMatchId());
                                    ranking.setTeamId(rk.getTeamId());
                                    ranking.setMatchItemId(rk.getMatchItemId());
                                    ranking.setMatchItemRanking(rk.getRowNo());
                                    teamService.addranking(ranking);
                                }
                            }
                            else{
                                if(i == 1){
                                    RankingRequest rankingRequest1 = new RankingRequest();
                                    rankingRequest1.setTeamId(rk.getTeamId());
                                    rankingRequest1.setAreaId(rk.getAreaId());
                                    rankingRequest1.setRowNo(rk.getRowNo());
                                    teamService.updateRanking(rankingRequest1);
                                }
                                else if(i==2){
                                    RankingRequest rankingRequest1 = new RankingRequest();
                                    rankingRequest1.setTeamId(rk.getTeamId());
                                    rankingRequest1.setMatchId(rk.getMatchId());
                                    rankingRequest1.setRowNo(rk.getRowNo());
                                    teamService.updateRanking(rankingRequest1);
                                }else if(i==3){
                                    RankingRequest rankingRequest1 = new RankingRequest();
                                    rankingRequest1.setMatchId(rk.getMatchId());
                                    rankingRequest1.setTeamId(rk.getTeamId());
                                    rankingRequest1.setMatchItemId(rk.getMatchItemId());
                                    rankingRequest1.setMatchItemRanking(rk.getRowNo());
                                    teamService.updateRanking(rankingRequest1);
                                }
                            }
                        }
                    }
                }
            }
        }
        teamService.updateRankingtag();

        List<Integer> T_ids = teamService.getAllTeamIds();

        if(T_ids != null && T_ids.size()>0){
            for (Integer id : T_ids){
                sharedRedisClient.del(TEAM+id);
            }
        }
    }


    /**
     * ?????????????????????
     */
    @Override
    public void addVote(){
        while (sharedRedisClient.scard(VOTE_SET)>0){
            List<String> votestr = sharedRedisClient.srandmember(VOTE_SET, 500);
            Map<Integer,Integer> Teammap = new HashMap<>();
            Map<Integer,Integer> Videomap = new HashMap<>();

            for (String vo : votestr){
                Vote vote = JSONUtils.fromJson(vo, Vote.class);
                iVideoService.addVote(vote);

                if(Teammap.get(vote.getTeamId()) != null){
                    Teammap.put(vote.getTeamId(),Teammap.get(vote.getTeamId())+1);
                }
                else {
                    Teammap.put(vote.getTeamId(),1);
                }

                if(Videomap.get(vote.getVideoId()) != null){
                    Videomap.put(vote.getVideoId(),Videomap.get(vote.getVideoId())+1);
                }
                else {
                    if(vote.getVideoId() != null){
                        Videomap.put(vote.getVideoId(),1);
                    }
                }
            }

            for (Map.Entry<Integer, Integer> entry : Teammap.entrySet()) {
                iVideoService.addTeamVoteCnt(entry.getKey(),entry.getValue());
                sharedRedisClient.del(TEAM+entry.getKey());
            }

            for (Map.Entry<Integer, Integer> entry : Videomap.entrySet()) {
                iVideoService.addTeamVideoVoteCnt(entry.getKey(),entry.getValue());
            }

            String[] a = new String[votestr.size()];
            sharedRedisClient.srem(VOTE_SET,  votestr.toArray(a));
        }
    }

    /**
     * ??????????????????????????????
     */
    @Override
    public void updateTxTeamVideo() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        //????????????
        c.setTime(new Date());
        c.add(Calendar.DATE, -2);
        Date d = c.getTime();
        String day = format.format(d);

        TeamVideoTable param = new TeamVideoTable();
        param.setCheckStatus(-1);
        param.setInsertTime(day);
        List<TeamVideoInfo> list = iVideoService.getTeamVideoTable(param);

        if (list.size() > 0) {
            for(TeamVideoInfo t:list){

                if(StringUtils.isBlank(t.getTxVodTaskId()))
                {
                    continue;
                }

                try{

                    Credential cred = new Credential(Global.getConfig("com.uway.SecretId"), Global.getConfig("com.uway.SecretKey"));

                    HttpProfile httpProfile = new HttpProfile();
                    httpProfile.setEndpoint("vod.tencentcloudapi.com");

                    ClientProfile clientProfile = new ClientProfile();
                    clientProfile.setHttpProfile(httpProfile);

                    VodClient client = new VodClient(cred, "ap-beijing", clientProfile);

                    String params = "{\"TaskId\":\""+t.getTxVodTaskId()+"\"}";
                    DescribeTaskDetailRequest req = DescribeTaskDetailRequest.fromJsonString(params, DescribeTaskDetailRequest.class);

                    DescribeTaskDetailResponse resp = client.DescribeTaskDetail(req);

                    if(resp.getStatus().equals("FINISH") && resp.getTaskType().equals("Procedure")){

                        if(resp.getProcedureTask() != null && resp.getProcedureTask().getAiContentReviewResultSet().length >0){
                            TeamVideo teamVideo = new TeamVideo();
                            teamVideo.setFileid(resp.getProcedureTask().getFileId());

                            for(int i=0;i<resp.getProcedureTask().getAiContentReviewResultSet().length;i++){
                                AiContentReviewResult aiContentReviewResult = resp.getProcedureTask().getAiContentReviewResultSet()[i];

                                if(aiContentReviewResult.getType().equals("Porn")){
                                    if(aiContentReviewResult.getPornTask() != null && aiContentReviewResult.getPornTask().getStatus().equals("SUCCESS")) {
                                        boolean sendMessage = false;
                                        if (aiContentReviewResult.getPornTask().getOutput().getSuggestion().equals("pass")) {
                                            teamVideo.setCheckStatus(0);
                                            teamVideo.setTxPronPoint(1);
                                        }else if (aiContentReviewResult.getPornTask().getOutput().getSuggestion().equals("review")) {
                                            teamVideo.setCheckStatus(0);
                                            teamVideo.setTxPronPoint(2);
                                        }else{
                                            // ??????????????????????????????????????????????????????
                                            teamVideo.setCheckStatus(2);
                                            teamVideo.setTxPronPoint(-1);
                                            sendMessage = true;
                                        }
                                        iVideoService.updateTeamVideoBy(teamVideo);
                                        sharedRedisClient.del(VIDEO_KEY+teamVideo.getId());
                                        if (sendMessage){
                                            String title = "????????????";
                                            String videoDsc = t.getVideoDisc();
                                            if (StringUtils.isEmpty(videoDsc)){
                                                if (t.getType() == 1){
                                                    videoDsc = "????????????";
                                                }else if (t.getType() == 2){
                                                    videoDsc = "????????????";
                                                }
                                            }else if(videoDsc.length() > 10){
                                                videoDsc = videoDsc.substring(0,10) + "......";
                                            }
                                            String replyDateStr = DateUtils.formatDate(DateUtils.parse(t.getInserttime(),"yyyy-MM-dd HH:mm:ss"),"MM-dd HH:mm:ss");
                                            String desc = String.format("??????%s ?????????%s???????????????%s?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????",replyDateStr,videoDsc,"?????????????????????");
                                            // ????????????????????????????????????????????????
                                            messageService.addMessage(new MessagePush(0,2,-1,t.getOperUserId(),title,desc,1));

                                            // ????????????????????????????????????????????????
                                            ThemeMomentsRel relParam = new ThemeMomentsRel();
                                            relParam.setStatus(1);
                                            relParam.setVideoId(teamVideo.getId());
                                            List<ThemeMomentsRel> rellist = iThemeService.getThemeMomentsRelList(relParam);
                                            if (list.size() > 0){
                                                for (ThemeMomentsRel rel : rellist){
                                                    iThemeService.updateThemeMomentsStatus(rel.getId(),0);
                                                    iThemeService.updateThemeMomentsCnt(rel.getThemeId(),-1);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } catch (TencentCloudSDKException e) {
                    System.out.println(e.toString());
                }

            }
        }

        List<TencentVideoInfo> tencentVideoInfoList = iVideoService.getTencentVideoInfos();
        if (tencentVideoInfoList.size() > 0) {
            for(TencentVideoInfo t:tencentVideoInfoList){

                if(StringUtils.isBlank(t.getFileid()))
                {
                    continue;
                }

                TxFiletask txFiletask = iVideoService.getvodTaskIdbyFileId(t.getFileid());

                if(txFiletask == null || StringUtils.isBlank(txFiletask.getVodTaskId()))
                {
                    continue;
                }

                try{
                    TeamVideo teamVideo1 = new TeamVideo();

                    teamVideo1.setFileid(txFiletask.getFileid());
                    teamVideo1.setVideoUrl(txFiletask.getVideoUrl());
                    teamVideo1.setIsTranscode(txFiletask.getIsTranscode());
                    teamVideo1.setVideotime(txFiletask.getVideotime());
                    teamVideo1.setCoverUrl(txFiletask.getCover_url());
                    iVideoService.updateTencentVideoBy(teamVideo1);
                }catch (Exception ex){

                }

                try{

                    Credential cred = new Credential(Global.getConfig("com.uway.SecretId"), Global.getConfig("com.uway.SecretKey"));

                    HttpProfile httpProfile = new HttpProfile();
                    httpProfile.setEndpoint("vod.tencentcloudapi.com");

                    ClientProfile clientProfile = new ClientProfile();
                    clientProfile.setHttpProfile(httpProfile);

                    VodClient client = new VodClient(cred, "ap-beijing", clientProfile);

                    String params = "{\"TaskId\":\""+txFiletask.getVodTaskId()+"\"}";
                    DescribeTaskDetailRequest req = DescribeTaskDetailRequest.fromJsonString(params, DescribeTaskDetailRequest.class);

                    DescribeTaskDetailResponse resp = client.DescribeTaskDetail(req);

                    if(resp.getStatus().equals("FINISH") && resp.getTaskType().equals("Procedure")){

                        if(resp.getProcedureTask() != null && resp.getProcedureTask().getAiContentReviewResultSet().length >0){
                            TeamVideo teamVideo = new TeamVideo();
                            teamVideo.setFileid(resp.getProcedureTask().getFileId());

                            for(int i=0;i<resp.getProcedureTask().getAiContentReviewResultSet().length;i++){
                                AiContentReviewResult aiContentReviewResult = resp.getProcedureTask().getAiContentReviewResultSet()[i];

                                if(aiContentReviewResult.getType().equals("Porn")){
                                    if(aiContentReviewResult.getPornTask() != null && aiContentReviewResult.getPornTask().getStatus().equals("SUCCESS")) {
                                        if (aiContentReviewResult.getPornTask().getOutput().getSuggestion().equals("pass")) {
                                            teamVideo.setCheckStatus(1);
                                            teamVideo.setTxPronPoint(1);
                                        }else if (aiContentReviewResult.getPornTask().getOutput().getSuggestion().equals("review")) {
                                            teamVideo.setCheckStatus(1);
                                            teamVideo.setTxPronPoint(2);
                                        }else{
                                            teamVideo.setCheckStatus(2);
                                            teamVideo.setTxPronPoint(-1);
                                        }
                                        iVideoService.updateTencentVideoBy(teamVideo);
                                    }
                                }
                            }
                        }
                    }
                } catch (TencentCloudSDKException e) {
                    System.out.println(e.toString());
                }

            }
        }

        TeachingVideoTable table = new TeachingVideoTable();
        table.setCheckStatus(-1);
        List<TeachingVideoInfo> teachingVideoInfos = iVideoService.getTeachingVideoTable(table);
        if (teachingVideoInfos.size() > 0) {
            for(TeachingVideoInfo t:teachingVideoInfos){

                if(StringUtils.isBlank(t.getFileId()))
                {
                    continue;
                }

                TxFiletask txFiletask = iVideoService.getvodTaskIdbyFileId(t.getFileId());

                if(txFiletask == null || StringUtils.isBlank(txFiletask.getVodTaskId()))
                {
                    continue;
                }
                TeachingVideo teamVideo = new TeachingVideo();

                teamVideo.setFileId(txFiletask.getFileid());
                teamVideo.setTeachingUrl(txFiletask.getVideoUrl());
                teamVideo.setVideotime(Long.parseLong(txFiletask.getVideotime().toString()));
                teamVideo.setCheckStatus(0);
                iVideoService.updateTeachingVideoInfobyTX(teamVideo);
            }
        }
    }

    @Override
    public void updateExpireSort() {
        // ??????
        int n = newsService.updateSportsNewsExpireSort();
        logger.debug("????????????????????? n???" + n);
        // ??????
        n = newsService.updateMatchNewsExpireSort();
        logger.debug("????????????????????? n???" + n);
    }
    @Override
    public void updateExpireOrder() {

        int timelong = Integer.parseInt(Global.getConfig("teamorder.expired.time"));
        TeamOrderRequest teamOrderRequest = new TeamOrderRequest();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dt = new Date();
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);
        rightNow.add(Calendar.HOUR_OF_DAY, -1 * timelong);//????????????
        Date dt1 = rightNow.getTime();
        String reStr = sdf.format(dt1);
        teamOrderRequest.setEndDate(reStr);
        teamOrderRequest.setOrderStatus(0);
        List<TeamOrder> tos = iTeamOrderDao.getTeamOrders(teamOrderRequest);

        for (TeamOrder to: tos) {
            Team team = new Team();
            team.setId(to.getTeamId());
            team.setStatus(0);
            team.setTeamLeaderid(to.getUserId());
            team.setCheckReason("???????????????");
            iTeamDao.updateTeamBy(team);

            TeamMember teamMember = new TeamMember();
            teamMember.setTeamId(to.getTeamId());
            teamMember.setTeamMemberId(to.getUserId());
            teamMember.setStatus(-1);
            teamMember.setNowstatus(3);
            iTeamDao.updateTeamMemberStatus(teamMember);

            TeamOrder tm = new TeamOrder();
            tm.setOrderStatus(2);
            tm.setId(to.getId());
            logger.debug("?????????????????? id???" + to.getId());
            iTeamOrderDao.updateTeamOrder(tm);
        }

    }

    @Override
    public void getAndUpdateRanking() {
        teamService.getTeamRankingAndDeal();
    }

    @Override
    public void getMomentsHot() {
        // ????????????????????????????????????

        // ?????????500 ?????????????????????

        // ????????????id????????????????????????????????????????????????
        List<MomentsSimpleInfo> list = iVideoService.getMomentsSimpleInfo(null);
        if (list.size() > 0){
            // ???????????????
            Integer hotSetLast;
            // ?????????????????????
            Integer hotSetNumLast;
            // ?????????
            Integer replyCnt;
            // ?????????
            Integer goodCnt;
            // ?????????
            Integer voteCnt;
            // ?????????????????????
            Integer hotSetNumNow;
            int hotSet;
            List<MomentsSimpleInfo> modifyList = new ArrayList<>();
            for(MomentsSimpleInfo info : list){
                logger.debug("-------------info--------------" + JSONUtils.toJson(info));
                hotSetLast = info.getHotSet();
                replyCnt = info.getReplyCnt();
                goodCnt = info.getGoodCnt();
                voteCnt = info.getVoteCnt();
                // redis??????????????????????????????
                String hotSetNumLastStr = sharedRedisClient.get(CommConstantEntity.MOMENTS_HOT_SET_LAST_KEY + info.getId());
                if (StringUtils.isEmpty(hotSetNumLastStr)){
                    hotSetNumLastStr = "0";
                }
                logger.debug("----------hotSetNumLastStr-----------------" + hotSetNumLastStr);
                hotSetNumLast = Integer.parseInt(hotSetNumLastStr);
                hotSetNumNow = replyCnt + goodCnt + voteCnt;
                // redis????????????????????????????????????
                sharedRedisClient.set(CommConstantEntity.MOMENTS_HOT_SET_LAST_KEY + info.getId(),hotSetNumNow.toString(),7200);
                // ?????? ?????????+?????????+?????????-??????????????? = ??????????????????
                // ??????????????? = ??????????????? * 50% + ?????????????????? ?????????????????? * 50%  ????????? 1???
                hotSet = new BigDecimal((hotSetNumNow - hotSetNumLast + hotSetLast * 0.5) + "").setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
                if (hotSet <= 1){
                    hotSet = 1;
                }
                info.setHotSet(hotSet);
                modifyList.add(info);
                // ???????????????????????????????????????????????????????????????redis???teamVideo????????????????????????
                if (modifyList.size() >= 500){
                    iVideoService.updateMomentsHotSet(modifyList);
                    modifyList.clear();
                }
            }
            if (modifyList.size() > 0){
                iVideoService.updateMomentsHotSet(modifyList);
                modifyList.clear();
            }
        }
    }

    @Override
    public void pushSendAttention() {
        // ??????????????????
        Date nowTime = new Date();
        // ??????????????????????????????
        PubConfigure configParam = new PubConfigure();
        configParam.setCommName("umeng_push_tag");
        configParam.setParamName("attention_tag");
        configParam.setStatus(1);
        PubConfigure config = iPubConfigureService.getPubConfigureOne(configParam);
        if (config != null && "1".equals(config.getParamValue())){
            // ???????????????????????????????????????????????????
            Date startDate = getTaskQueryDate(nowTime);
            // ?????????????????????????????????????????????
            NoticeTaskRequest attenParam = new NoticeTaskRequest(startDate,nowTime);
            List<NoticeTaskResult> list = iAppUserService.getUserAttentionForNotice(attenParam);
            // ??????????????????list??????attentionUserId???key???userId???list???value????????????map???
            if (list.size() > 0){
                String userIdStr;
                String[] userIdArr;
                Integer attentionUserId;
                Integer cnt;
                StringBuffer sb = new StringBuffer();
                // ??????map????????????
                PushMessage pushMessage = new PushMessage();
                pushMessage.setMessageType("0");
                pushMessage.setType("1");
                pushMessage.setActivity(CommConstantEntity.umengActivityMap.get(23));
                pushMessage.setModelId("9");
                pushMessage.setSendType(3);
                User receiveUser;
                for(NoticeTaskResult atten : list){
                    sb.setLength(0);
                    attentionUserId = atten.getMainUserId();
                    cnt = atten.getCnt();
                    userIdStr = atten.getCntIdStr();
                    userIdArr = userIdStr.split(",");
                    sb.append("@");
                    sb.append(iAppUserService.getUserInRedisById(Integer.parseInt(userIdArr[0])).getChName());
                    if(cnt >= 2){
                        sb.append("???").append(cnt).append("???");
                    }
                    sb.append("??????????????????????????????~");
                    pushMessage.setBody(sb.toString());
                    receiveUser = iAppUserService.getUserInRedisById(attentionUserId);
                    if (receiveUser != null){
                        pushMessage.setTitle(String.format("Hi???%s~",receiveUser.getChName()));
                        pushMessage.setAlias(attentionUserId.toString());
                        messageService.addUmenpush(pushMessage);
                    }
                }
            }
        }
    }

    @Override
    public void pushSendGood() {
        // ??????????????????
        Date nowTime = new Date();
        // ??????????????????????????????
        PubConfigure configParam = new PubConfigure();
        configParam.setCommName("umeng_push_tag");
        configParam.setParamName("good_tag");
        configParam.setStatus(1);
        PubConfigure config = iPubConfigureService.getPubConfigureOne(configParam);
        if (config != null && "1".equals(config.getParamValue())){
            // ???????????????????????????????????????????????????
            Date startDate = getTaskQueryDate(nowTime);
            // ???????????????????????????????????????
            NoticeTaskRequest goodParam = new NoticeTaskRequest(startDate,nowTime);
            List<NoticeTaskResult> list = iAppUserService.getUserClickGoodForNotice(goodParam);
            if (list.size() > 0){
                List<Integer> userIdCheckList = new ArrayList<>();
                String userIdStr;
                String[] userIdArr;
                Integer mainUserId;
                Integer cnt;
                StringBuffer sb = new StringBuffer();
                // ??????????????????
                PushMessage pushMessage = new PushMessage();
                pushMessage.setMessageType("0");
                pushMessage.setType("1");
                pushMessage.setActivity(CommConstantEntity.umengActivityMap.get(23));
                pushMessage.setModelId("7");
                pushMessage.setSendType(3);
                User receiveUser;
                for(NoticeTaskResult atten : list){
                    sb.setLength(0);
                    mainUserId = atten.getMainUserId();
                    if (!userIdCheckList.contains(mainUserId)){
                        userIdCheckList.add(mainUserId);
                        cnt = atten.getCnt();
                        userIdStr = atten.getCntIdStr();
                        userIdArr = userIdStr.split(",");
                        sb.append("@");
                        sb.append(iAppUserService.getUserInRedisById(Integer.parseInt(userIdArr[0])).getChName());
                        if(cnt >= 2){
                            sb.append("???").append(cnt).append("???");
                        }
                        sb.append("???????????????????????????~");
                        pushMessage.setBody(sb.toString());
                        receiveUser = iAppUserService.getUserInRedisById(mainUserId);
                        if (receiveUser != null){
                            pushMessage.setTitle(String.format("Hi???%s~",receiveUser.getChName()));
                            pushMessage.setAlias(mainUserId.toString());
                            messageService.addUmenpush(pushMessage);
                        }
                    }
                }
            }

        }
    }

    @Override
    public void pushSendMoments() {
        // ??????????????????
        Date nowTime = new Date();
        // ??????????????????????????????
        PubConfigure configParam = new PubConfigure();
        configParam.setCommName("umeng_push_tag");
        configParam.setParamName("moments_tag");
        configParam.setStatus(1);
        PubConfigure config = iPubConfigureService.getPubConfigureOne(configParam);
        if (config != null && "1".equals(config.getParamValue())) {
            // ???????????????????????????????????????????????????
            Date startDate = getTaskQueryDate(nowTime);
            // ????????????????????????????????????????????????????????????????????????
            NoticeTaskRequest goodParam = new NoticeTaskRequest(startDate,nowTime);
            List<NoticeTaskResult> list = iAppUserService.getUserMomentsForNotice(goodParam);
            if (list.size() > 0){
                // ????????????????????????userId
                Set<String> sendUserId = new HashSet<>();
                Integer mainUserId;
                String cntIdStr;
                String momentsId;
                PushMessage pushMessage = new PushMessage();
                pushMessage.setMessageType("0");
                pushMessage.setType("1");
                pushMessage.setActivity(CommConstantEntity.umengActivityMap.get(18));
                pushMessage.setSendType(3);
                User fansUser;
                for (NoticeTaskResult info : list){
                    cntIdStr = info.getCntIdStr();
                    momentsId = cntIdStr.split(",")[0];
                    mainUserId = info.getMainUserId();
                    pushMessage.setBody(String.format("???????????? @%s ?????????????????????????????? >>",iAppUserService.getUserInRedisById(mainUserId).getChName()));
                    pushMessage.setModelId(momentsId);
                    // ???redis????????????????????????????????????
                    String key = CommConstantEntity.USER_FANS_LIST_HEAD + mainUserId;
                    Set<String> fansUserId = sharedRedisClient.zrevrange(key,0,-1);
                    if (fansUserId != null && fansUserId.size() > 0){
                        // ????????????id???????????????????????????????????????????????????alias??????????????????????????????
                        for(String fansId : fansUserId){
                            if (!sendUserId.contains(fansId)){
                                sendUserId.add(fansId);
                                fansUser = iAppUserService.getUserInRedisById(Integer.parseInt(fansId));
                                if (fansUser != null){
                                    pushMessage.setTitle(String.format("Hi???%s~",fansUser.getChName()));
                                    pushMessage.setAlias(fansId);
                                    messageService.addUmenpush(pushMessage);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void getDepartStepInfoToRedis() {
        // ?????????????????????????????????
        PubConfigure conf = new PubConfigure();
        conf.setStatus(1);
        conf.setCommName("special_match_rank");
        conf.setParamName("match_and_item_id");
        PubConfigure confInfo = iPubConfigureService.getPubConfigureOne(conf);
        if (confInfo != null && StringUtils.isNotBlank(confInfo.getParamValue())){
            String[] arr = confInfo.getParamValue().split(",");
            Integer matchId = Integer.parseInt(arr[0]);
            Integer itemId = Integer .parseInt(arr[1]);
            MatchNewsInfo matchNewsInfo = newsService.getMatchNewsById(matchId);
            if (matchNewsInfo == null){
                return;
            }
            TeamStepInfo param = new TeamStepInfo();
            param.setMatchId(matchId);
            param.setItemId(itemId);
            String maxNum = sharedRedisClient.get("stepMaxNum");
            if (StringUtils.isEmpty(maxNum)){
                maxNum = "20000";
            }
            param.setMaxStep(Integer.parseInt(maxNum));
            param.setStartDate(DateUtils.parse(matchNewsInfo.getMatchTime(),"yyyy-MM-dd HH:mm:ss"));
            param.setEndDate(DateUtils.parse(matchNewsInfo.getDateEnd(),"yyyy-MM-dd HH:mm:ss"));
            // ???????????????????????????????????????redis??????????????????
            List<TeamStepInfo> list = iTeamDao.getDepartStepInfo(param);
            Pattern pattern = Pattern.compile("\\d{2,}");
            if (list.size() > 0){
                String key = "special_match_rank";
                sharedRedisClient.del(key);
                for (TeamStepInfo info : list){
                    Matcher matcher = pattern.matcher(info.gettJson());
                    if (matcher.find()) {
                        String departId = matcher.group();
                        info.setDepartId(Integer.parseInt(departId));
                        info.setAveStepNum(info.getTotalStepNum()/info.getTotalMember());
                        sharedRedisClient.zadd(key,info.getAveStepNum(),departId);
                        sharedRedisClient.set("special_depart:" + departId,JSONUtils.toJson(info),2592000);
                    }
                }
                sharedRedisClient.expire(key,2592000);
            }
            List<TeamStepInfo> memberList = iTeamDao.getDepartMemberStepInfo(param);
            if (memberList.size() > 0){
                List<String> departIdList = new ArrayList<>();
                boolean hasDepart;
                for (TeamStepInfo info : memberList){
                    Matcher matcher = pattern.matcher(info.gettJson());
                    if (matcher.find()) {
                        String departId = matcher.group();
                        String key = "special_match_rank:" + departId;
                        hasDepart = departIdList.contains(departId);
                        if (!hasDepart){
                            sharedRedisClient.del(key);
                            departIdList.add(departId);
                        }
                        Integer userId = info.getUserId();
                        info.setDepartId(Integer.parseInt(departId));
                        sharedRedisClient.zadd(key,info.getTotalStepNum(),userId.toString());
                        sharedRedisClient.set("special_member_" + departId + ":" + userId.toString(),JSONUtils.toJson(info),2592000);
                        if (!hasDepart){
                            sharedRedisClient.expire(key,2592000);
                        }
                    }
                }

            }
        }
        sharedRedisClient.set("special_match_rank_time",DateUtils.getDateTime(),2592000);
    }

    @Override
    public void getTeamlistForNotWorksMoments() {
        // ??????????????????????????????
        PubConfigure configParam = new PubConfigure();
        configParam.setCommName("umeng_push_tag");
        configParam.setParamName("workMoments_tag");
        configParam.setStatus(1);
        PubConfigure config = iPubConfigureService.getPubConfigureOne(configParam);
        boolean needSend = false;
        if (config != null && "1".equals(config.getParamValue())) {
            needSend = true;
        }
        List<TeamInfo> teamList = iTeamDao.getTeamlistForNotWorksMoments();
        String title = "@%s ????????????????????????????????????????????????????????????????????????>>";
        if (teamList.size() > 0){
            // ???????????????????????????
            Map<Integer,Integer> teamThemeMap = new HashMap<>();
            // ??????????????????
            Integer themeId;
            Integer teamId;
            List<Integer> teamIdList = new ArrayList<>();
            for (TeamInfo teamInfo : teamList){
                teamId = teamInfo.getId();
                themeId = teamInfo.getThemeId();
                teamThemeMap.put(teamId,themeId);
                teamIdList.add(teamId);
            }
            // ???????????????????????????
            if (teamIdList.size() > 0){
                List<TeamMember> memberList = iTeamDao.getTeamMemberListForNotice(teamIdList);
                if (memberList.size() > 0){
                    MessagePush messagePush = new MessagePush(0,11,-1,null,null,null,1);
                    messagePush.setIsUmeng(1);
                    messagePush.setOpenWay(14);
                    PushMessage umengPush = new PushMessage();
                    umengPush.setMessageType("0");
                    umengPush.setType("1");
                    umengPush.setActivity(CommConstantEntity.umengActivityMap.get(14));
                    umengPush.setSendType(3);
                    for (TeamMember info : memberList){
                        String messageStr = String.format(title,info.getTeamMemberName());
                        // ????????????
                        messagePush.setReferentName(messageStr);
                        messagePush.setReferentDsc(messageStr);
                        messagePush.setReferentUrl(teamThemeMap.get(info.getTeamId()).toString());
                        messagePush.setUserId(info.getTeamMemberId());
                        messageService.addMessage(messagePush);
                        if (needSend){
                            // ????????????
                            umengPush.setBody(messageStr);
                            umengPush.setModelId(teamThemeMap.get(info.getTeamId()).toString());
                            umengPush.setAlias(info.getTeamMemberId().toString());
                            messageService.addUmenpush(umengPush);
                        }
                    }
                }
            }

        }


    }

    public Date getTaskQueryDate(Date nowTime){
        // ???????????????????????????????????????????????????
        String dateStr = DateUtils.formatDate(nowTime,"yyyy-MM-dd");
        Date date1 = DateUtils.parse(dateStr + " 06:59:00","yyyy-MM-dd HH:mm:dd");
        Date date2 = DateUtils.parse(dateStr + " 07:29:00","yyyy-MM-dd HH:mm:dd");
        Date startDate;
        // ????????????????????????????????????????????????????????????
        if (DateUtils.compareDate(nowTime,date1) > 0 && DateUtils.compareDate(nowTime,date2) < 0){
            startDate = DateUtils.addHour(nowTime,-9);
        }else{
            startDate = DateUtils.addMinute(nowTime,-30);
        }
        return startDate;
    }




}
