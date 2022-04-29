package com.uway.sports.web.serviceImpl;

import com.uway.sports.common.redis.SharedRedisClient;
import com.uway.sports.common.service.BaseServiceImpl;
import com.uway.sports.common.utils.DateUtils;
import com.uway.sports.web.dao.ITeamDao;
import com.uway.sports.web.dao.IVideoDao;
import com.uway.sports.web.entity.CommConstantEntity;
import com.uway.sports.web.entity.dbentity.*;
import com.uway.sports.web.entity.paraentity.*;
import com.uway.sports.web.service.IMessageService;
import com.uway.sports.web.service.INewsService;
import com.uway.sports.web.service.IPubConfigureService;
import com.uway.sports.web.service.ITeamService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.*;

/**
 * @Title
 * @Author liujingce
 * @Date 2019/3/22 20:13
 * @Discription
 */
@Service("teamService")
public class TeamServiceImpl extends BaseServiceImpl implements ITeamService {
    @Autowired
    private ITeamDao iTeamDao;
    @Autowired
    private IVideoDao iVideoDao;
    @Autowired
    private IMessageService messageService;
    @Autowired
    private IPubConfigureService iPubConfigureService;
    @Autowired
    private INewsService newsService;
    @Autowired
    private SharedRedisClient sharedRedisClient;
    public final static String TEAM = "uway_team:";
    public static final String VIDEO_KEY = "team_video:";
    @Override
    public List<TeamInfo> getTeamTable(TeamTable table) {
        return iTeamDao.getTeamTable(table);
    }

    @Override
    public int getTeamCnt(TeamTable table) {
        return iTeamDao.getTeamCnt(table);
    }

    @Override
    public int updateTeamBy(Team team) {
        int n = iTeamDao.updateTeamBy(team);
        if (n > 0){
            // 根据id修改时， 清除id下的redis
            List<Integer> ids = new ArrayList<>();
            if (team.getId() != null){
                sharedRedisClient.del(TEAM + team.getId());
                ids.add(team.getId());
                // 清除视频缓存
                clearTeamVideoRedis(ids);
            }else if (team.getMatchId() != null){
                // 根据赛事id修改时，清除赛事下的redis
                TeamTable param = new TeamTable();
                param.setMatchId(team.getMatchId());
                List<TeamInfo> list = getTeamTable(param);
                for(TeamInfo info:list){
                    sharedRedisClient.del(TEAM + info.getId());
                    ids.add(info.getId());
                }
                // 清除视频缓存
                clearTeamVideoRedis(ids);
            }
        }
        return n;
    }

    @Override
    public Team getTeamById(Integer id) {
        return iTeamDao.getTeamById(id);
    }

    @Override
    public int updateTeamCloseMult(TeamTable table) {
        List<Integer> ids = table.getIds();
        List<TeamInfo> teamList = null;
        if (ids == null || ids.size() == 0){
            teamList = iTeamDao.getTeamTable(table);
        }
        int n = iTeamDao.updateTeamCloseMult(table);
        if (n > 0){
            if (ids != null && ids.size() > 0){
                for(Integer id : ids){
                    sharedRedisClient.del(TEAM + id);
                }
                clearTeamVideoRedis(ids);
            }else if (teamList != null && teamList.size() > 0){
                List<Integer> teamIds = new ArrayList<>();
                for(TeamInfo info : teamList){
                    teamIds.add(info.getId());
                    sharedRedisClient.del(TEAM + info.getId());
                }
                clearTeamVideoRedis(teamIds);
            }
        }
        return n;
    }

    @Override
    public Map<String, String> updateTeamInfo(Team team) {
        Map<String,String> result = new HashMap<>();
        Integer id = team.getId();
        Team teamInfo = getTeamById(id);
        // 判断编号是否存在
        TeamTable param = new TeamTable();
        param.setTeamNo(team.getTeamNo());
        param.setMatchId(teamInfo.getMatchId());
        List<TeamInfo> list = getTeamTable(param);
        // 修改
        if (list != null && list.size() > 1){
            result.put("success","0");
            result.put("message","该编号已占用");
            return result;
        }
        if (list != null && list.size() == 1 && list.get(0).getId().intValue() != id.intValue()){
            result.put("success","0");
            result.put("message","该编号已占用");
            return result;
        }
        updateTeamBy(team);
        result.put("success","1");
        return result;
    }

    @Override
    public Map<String, String> updateTeamStatusCheck(Team team) {
        Integer teamId = team.getId();
        Integer checkStatus = team.getCheckStatus();
        if (checkStatus == 2){
            // 审核驳回的队伍，置为无效
            team.setStatus(0);
        }
        updateTeamBy(team);
        String name;
        String desc;
        Team teamInfo = getTeamById(teamId);
        if (checkStatus == 1){
            name = "你的队伍"+teamInfo.getTeamName()+"已经通过操场官方审核。";
            desc = "上传队伍视频，展示队伍风采！为自己的队伍拉票，冲啊";
            messageService.addMessage(new MessagePush(2,2,-1,teamInfo.getTeamLeaderid(),name,desc,1));
        }else if (checkStatus == 2){
            name = "很抱歉的通知你，你创建的队伍"+teamInfo.getTeamName()+"未通过操场官方审核。";
            desc = "驳回原因：" + team.getCheckReason() + "。未通过队伍信息已被系统回收，请您重新创建队伍。";
            messageService.addMessage(new MessagePush(2,2,-1,teamInfo.getTeamLeaderid(),name,desc,1));
        }
        return null;
    }

    /**
     * 获取战队所有的行业或地域id
     * @param type
     * @return
     */
    @Override
    public List<RankingRequest> getareaorindustryList(Integer type){
        return iTeamDao.getareaorindustryList(type);
    }

    /**
     * 获取战队当前排名
     * @param rankingRequest
     * @return
     */
    @Override
    public List<RankingRequest> getNowranking(RankingRequest rankingRequest){
        return iTeamDao.getNowranking(rankingRequest);
    }

    /**
     * 获取投票排名列表
     * @param teamId
     * @return
     */
    @Override
    public List<Ranking> getrankingList(Integer teamId){
        return iTeamDao.getrankingList(teamId);
    }

    /**
     * 增加排名
     * @param ranking
     * @return
     */
    @Override
    public int addranking(Ranking ranking){
        return iTeamDao.addranking(ranking);
    }

    /**
     * 更新排名
     * @param rankingRequest
     * @return
     */
    @Override
    public int updateRanking(RankingRequest rankingRequest){
        return iTeamDao.updateRanking(rankingRequest);
    }

    /**
     * 将当前排名更新到上周排名
     * @return
     */
    @Override
    public int updateRankingRowNo(){
        return iTeamDao.updateRankingRowNo();
    }

    /**
     * 比较当前排名和上周排名的大小并标记
     * @return
     */
    @Override
    public int updateRankingtag(){
        return iTeamDao.updateRankingtag();
    }

    private long clearTeamVideoRedis(List<Integer> teamIds){
        // 根据teamIds 查询出所有的视频id
        TeamVideoTable param = new TeamVideoTable();
        param.setTeamIds(teamIds);
        param.setStatus(1);
        param.setCheckStatus(1);
        List<String> list = iVideoDao.getTeamVideoIds(param);
        long m = 0;
        if (list != null && list.size() > 0){
            for(String id : list){
                m += sharedRedisClient.del(VIDEO_KEY + id);
            }
        }
        return m;
    }

    /**
     * 获取所有战队id
     * @return
     */
    @Override
    public List<Integer> getAllTeamIds(){
        List<Integer> list = iTeamDao.getAllTeamIds();

        return list;
    }

    @Override
    public int getTeamMemberCnt(TeamMember teamMember) {
        return iTeamDao.getTeamMemberCnt(teamMember);
    }

    @Override
    public void getTeamRankingAndDeal() {
        Date yesterday = DateUtils.yesterday();
        // 读取消息发送模板
        PubConfigure conf = new PubConfigure(CommConstantEntity.CONF_RANKING_MESSAGE,null,1);
        Map<String,String> messageConf = iPubConfigureService.getPubConfigureMap(conf);
        String messageDay = messageConf.get(CommConstantEntity.CONF_RANKING_MESSAGE_DAY);
        String messageWeek = messageConf.get(CommConstantEntity.CONF_RANKING_MESSAGE_WEEK);
        String messageMonth = messageConf.get(CommConstantEntity.CONF_RANKING_MESSAGE_MONTH);
        // 将名次归整表（ranking_change）中类型为当天最新名次记录更新到旧数据字段中
        iTeamDao.updateRankingChangeNewToOld(1);
        // 若今天为周一，则将类型为周的最新记录更新到旧数据字段中
        int weekNum = DateUtils.getDayNumberInWeek(yesterday);
        if (weekNum == 1){
            iTeamDao.updateRankingChangeNewToOld(2);
        }
        // 若今天为1号，则将类型为周的最新记录更新到旧数据字段中
        int monthNum = DateUtils.getDayNumberInMonth(yesterday);
        if (monthNum == 1){
            iTeamDao.updateRankingChangeNewToOld(3);
        }
        // 获取所有需要投票的有效且进行中（报名、比赛中）的赛事
        MatchNewsTable matchNewsTable = new MatchNewsTable();
        matchNewsTable.setStatus(1);
        matchNewsTable.setIsVote(1);
        matchNewsTable.setCheckDate(yesterday);
        List<MatchNewsInfo> matchList =  newsService.getMatchNewsTable(matchNewsTable);
        if (matchList.size() == 0){
            return;
        }
        Integer matchId;
        Integer rankingDay;
        Integer rankingWeek;
        Integer rankingMonth;
        Integer teamId;
        String matchName;
        for (MatchNewsInfo matchInfo : matchList){
            // 查询赛事下所有的有效且审核通过队伍
            matchId = matchInfo.getId();
            matchName = matchInfo.getMatchTitle();
            rankingDay = matchInfo.getRankingDay();
            rankingWeek = matchInfo.getRankingWeek();
            rankingMonth = matchInfo.getRankingMonth();
            List<RankingInDay> rankingDayList = iTeamDao.getTeamRankingNow(matchId,null);
            if (rankingDayList.size() > 0){
                Integer lastVoteCnt = null;
                for (RankingInDay rankingInDay : rankingDayList){
                    teamId = rankingInDay.getTeamId();
                    // 添加昨日排名到名次记录表（ranking_in_day）中（定时每天凌晨进行，统计昨天的投票数，中间的误差就算作昨天的）
                    iTeamDao.addTeamRankingDay(rankingInDay);
                    // 每日排名变更处理
                    teamRankingChangeDealByType(1,teamId,rankingInDay.getRankingNum(),lastVoteCnt,rankingInDay.getVoteCnt(),rankingDay,messageDay,matchId,matchName,yesterday);
                    //  若今天为周一，在名次归整表（ranking_change）中记录或更新上周的记录
                    if (weekNum == 1){
                        teamRankingChangeDealByType(2,teamId,rankingInDay.getRankingNum(),lastVoteCnt,rankingInDay.getVoteCnt(),rankingWeek,messageWeek,matchId,matchName,yesterday);
                    }
                    // 若今天为1号，在名次归整表（ranking_change）中记录或更新上月的记录
                    if (monthNum == 1){
                        teamRankingChangeDealByType(3,teamId,rankingInDay.getRankingNum(),lastVoteCnt,rankingInDay.getVoteCnt(),rankingMonth,messageMonth,matchId,matchName,yesterday);
                    }
                    // 记录下上一个排名的投票数，便于下一个的比较
                    lastVoteCnt = rankingInDay.getVoteCnt();
                }
            }

        }
    }

    @Override
    public Map<String, String> sendMatchFinalTeamRanking(int matchId,int isMust) {
        Map<String,String> result = new HashMap<>();
        PubConfigure conf = new PubConfigure(CommConstantEntity.CONF_RANKING_MESSAGE,CommConstantEntity.CONF_RANKING_MESSAGE_FINAL,1);
        PubConfigure finalMessage = iPubConfigureService.getPubConfigureOne(conf);
        String messageFinal = finalMessage.getParamName();
        if (StringUtils.isEmpty(messageFinal)){
            result.put("success","0");
            result.put("message","请设置赛事活动最终排名消息模板");
            return result;
        }
        MatchNewsInfo matchInfo = newsService.getMatchNewsById(matchId);
        if (matchInfo.getStatus() != 1 || matchInfo.getIsVote() == 0){
            result.put("success","0");
            result.put("message","该赛事活动无需投票，或已失效");
            return result;
        }
        String endDate = matchInfo.getDateEnd();
        int n = DateUtils.compareDate(endDate,DateUtils.getDate(),null);
        if (n >= 0 && isMust != 1){
            result.put("success","1");
            result.put("message","该赛事活动尚未结束，是否确认发送排名结果？");
            return result;
        }
        String matchName = matchInfo.getMatchTitle();
        List<RankingInDay> rankingDayList = iTeamDao.getTeamRankingNow(matchId,null);
        if (rankingDayList.size() > 0){
            int rankingNum;
            int voteCnt;
            String dsc;
            for (RankingInDay rankingInDay : rankingDayList){
                rankingNum = rankingInDay.getRankingNum();
                voteCnt = rankingInDay.getVoteCnt();
                dsc = messageFinal.replaceAll("#INFO=MATCH_TITLE#",matchName).replaceAll("#INFO=RANKING_NUM#",rankingNum + "")
                        .replaceAll("#INFO=VOTE_CNT#",voteCnt + "");
                sendMessageAndEditSend(rankingInDay.getTeamId(),matchId,"最终排名结果",dsc);
            }
        }
        result.put("success","ok");
        return result;
    }

    private void sendMessageAndEditSend(Integer teamId,Integer matchId,String name,String dsc){
        MessagePush message = new MessagePush(0,9,-1,null,name,dsc,1);
        message.setTeamId(teamId);
        message.setReferentUrl(matchId.toString());
        message.setOpenWay(19);
        messageService.addMessageByTeam(message);
    }

    private String getMessageDsc(String dsc,String matchName,Integer rankingNum,Integer voteCnt,Integer voteDiff,
                                 Date date,Integer rankingType,Integer rankingNumChange){
        try {
            List<Date> dateList = DateUtils.getWeekDays(date);
            dsc = dsc.replaceAll("#INFO=WEEK_RANGE_M#",DateUtils.formatDate(dateList.get(0),"M.dd") + "-" + DateUtils.formatDate(dateList.get(6),"M.dd"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (rankingType == 0){
            dsc = dsc.replaceAll("#INFO=RANGING_TYPE#","不变").replaceAll("#INFO=RANKING_NUM_CHANGE#名","");
        }else if (rankingType == 1){
            dsc = dsc.replaceAll("#INFO=RANGING_TYPE#","上升").replaceAll("#INFO=RANKING_NUM_CHANGE#",rankingNumChange.toString());
        }else if (rankingType == 2){
            dsc = dsc.replaceAll("#INFO=RANGING_TYPE#","上升").replaceAll("#INFO=RANKING_NUM_CHANGE#",rankingNumChange.toString());
        }
        return dsc.replaceAll("#INFO=MATCH_TITLE#",matchName).replaceAll("#INFO=RANKING_NUM#",rankingNum.toString())
                .replaceAll("#INFO=VOTE_CNT#",voteCnt.toString()).replaceAll("#INFO=VOTE_DIFF_CNT_LAST#",voteDiff.toString());
    }


    private void teamRankingChangeDealByType(int rankingType,Integer teamId,int rankingNumNew,int voteCntNew,Integer lastVoteCnt,
                                             int isMessageSend,String messageDsc,int matchId,String matchName,Date date){
        // 查询队伍的变更记录
        List<RankingChange> checkList = iTeamDao.getRankingChangeList(rankingType,teamId);
        RankingChange rankingChange;
        int voteDiff;
        int rankingNumOld;
        int rankingTag;
        int rankingNumChange;
        if (checkList.size() > 0){
            rankingChange = checkList.get(0);
            // 与上一名的投票差值
            if (lastVoteCnt != null){
                rankingChange.setVoteDiff(lastVoteCnt);
                voteDiff = lastVoteCnt - voteCntNew;
            }else{
                rankingChange.setVoteDiff(0);
                voteDiff = -1;
            }
            // 判断排名变化
            rankingNumOld = rankingChange.getRankingNumOld();
            if(rankingNumOld < rankingNumNew){
                rankingTag = 1;
                rankingNumChange = rankingNumNew - rankingNumOld;
            }else if(rankingNumOld > rankingNumNew){
                rankingTag = 2;
                rankingNumChange = rankingNumOld - rankingNumNew;
            }else{
                rankingTag = 0;
                rankingNumChange = 0;
            }
            rankingChange.setRankingTag(rankingTag);
            iTeamDao.updateRankingChange(rankingChange);
        }else{
            // 在名次归整表（ranking_change）中记录或更新每天的记录
            rankingChange = new RankingChange();
            rankingChange.setRankingType(rankingType);
            rankingChange.setTeamId(teamId);
            rankingChange.setRankingNumNew(rankingNumNew);
            rankingChange.setVoteCntNew(voteCntNew);
            // 与上一名的投票差值
            if (lastVoteCnt != null){
                rankingChange.setVoteDiff(lastVoteCnt);
                voteDiff = lastVoteCnt - voteCntNew;
            }else{
                rankingChange.setVoteDiff(0);
                voteDiff = -1;
            }
            rankingTag = 0;
            rankingNumChange = 0;
            iTeamDao.addRankingChange(rankingChange);
        }
        // 消息发送
        if (rankingType == 1 && isMessageSend == 1 && StringUtils.isNotEmpty(messageDsc)){
            sendMessageAndEditSend(teamId,matchId,"昨日榜单出炉",getMessageDsc(messageDsc,matchName,rankingNumNew,voteCntNew,voteDiff,date,rankingTag,rankingNumChange));
        }else if(rankingType == 2 && isMessageSend == 1 && StringUtils.isNotEmpty(messageDsc)){
            sendMessageAndEditSend(teamId,matchId,"上周榜单出炉",getMessageDsc(messageDsc,matchName,rankingNumNew,voteCntNew,voteDiff,date,rankingTag,rankingNumChange));
        }else if(rankingType == 3 && isMessageSend == 1 && StringUtils.isNotEmpty(messageDsc)){
            sendMessageAndEditSend(teamId,matchId,"上月榜单出炉",getMessageDsc(messageDsc,matchName,rankingNumNew,voteCntNew,voteDiff,date,rankingTag,rankingNumChange));
        }
    }
}
