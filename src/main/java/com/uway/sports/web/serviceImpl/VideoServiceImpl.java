package com.uway.sports.web.serviceImpl;

import com.uway.sports.common.redis.SharedRedisClient;
import com.uway.sports.common.service.BaseServiceImpl;
import com.uway.sports.common.utils.DateUtils;
import com.uway.sports.common.utils.IdGen;
import com.uway.sports.common.utils.StringUtils;
import com.uway.sports.web.dao.IVideoDao;
import com.uway.sports.web.entity.CommConstantEntity;
import com.uway.sports.web.entity.dbentity.*;
import com.uway.sports.web.entity.paraentity.*;
import com.uway.sports.web.service.IMessageService;
import com.uway.sports.web.service.IThemeService;
import com.uway.sports.web.service.IVideoService;
import com.uway.sports.web.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("iVideoService")
public class VideoServiceImpl extends BaseServiceImpl implements IVideoService {
    @Autowired
    private IVideoDao iVideoDao;
    @Autowired
    private IThemeService iThemeService;
    @Autowired
    private IMessageService messageService;
    @Autowired
    private SharedRedisClient sharedRedisClient;

    @Override
    public List<TeamVideoInfo> getTeamVideoTable(TeamVideoTable table) {
        List<TeamVideoInfo> list = iVideoDao.getTeamVideoTable(table);
        if (list.size() > 0){
            for (TeamVideoInfo info : list){
                String themeIds = info.getThemeIds();
                String disc = info.getVideoDisc();
                if (StringUtils.isNotEmpty(themeIds) && StringUtils.isNotEmpty(disc)){
                    // 查询话题
                    String[] themeIdArr = themeIds.split(",");
                    for (String themeIdStr : themeIdArr){
                        if (StringUtils.isNotEmpty(themeIdStr)){
                            ThemeInfoResult themeInfo = iThemeService.getThemeInfoById(Integer.parseInt(themeIdStr));
                            if (themeInfo != null){
                                disc = disc.replaceAll("#ID="+themeInfo.getId()+"END#","#" + themeInfo.getThemeTitle());
                            }
                        }
                    }
                }
                info.setVideoDiscTemp(disc);
            }
        }
        return list;
    }

    @Override
    public int getTeamVideoCnt(TeamVideoTable table) {
        return iVideoDao.getTeamVideoCnt(table);
    }

    @Override
    public int updateTeamVideoBy(TeamVideo teamVideo) {
        return iVideoDao.updateTeamVideoBy(teamVideo);
    }

    @Override
    public int updateTeamVideoStatus(TeamVideo teamVideo) {
        int n = updateTeamVideoBy(teamVideo);
        // 将redis中视频清除
        if(n > 0 && teamVideo.getId() != null){
            sharedRedisClient.del(CommConstantEntity.VIDEO_KEY + teamVideo.getId());
        }
        // 若视频审核通过
        if (n > 0){
            String title;
            String desc;
            // 视频审核驳回发送消息，并减少话题的动态数量
            if(teamVideo.getCheckStatus() != null && teamVideo.getCheckStatus() == 2){
                TeamVideo videoInfo = getTeamVideoById(teamVideo.getId());
                // 审核不通过
                title = "系统通知";
                String videoDsc = videoInfo.getVideoDisc();
                if (StringUtils.isEmpty(videoDsc)){
                    if (videoInfo.getType() == 1){
                        videoDsc = "图片动态";
                    }else if (videoInfo.getType() == 2){
                        videoDsc = "视频动态";
                    }
                }else if(videoDsc.length() > 10){
                    videoDsc = videoDsc.substring(0,10) + "......";
                }
                String videoDateStr = DateUtils.formatDate(DateUtils.parse(videoInfo.getInserttime(),"yyyy-MM-dd HH:mm:ss"),"MM-dd HH:mm:ss");
                desc = String.format("您于%s 发布的%s的动态，因%s，系统已进行屏蔽将不予在平台显示，请您注意自己在公共平台的言论，内容多次不符合规定，将进行封号处理并追究相关法律责任。",videoDateStr,videoDsc,teamVideo.getCheckReason());
                // 给队长发送战队消息，审核结果通知
                messageService.addMessage(new MessagePush(0,2,-1,videoInfo.getOperUserId(),title,desc,1));

                // 查询动态关联的话题，并修改动态数
                ThemeMomentsRel relParam = new ThemeMomentsRel();
                relParam.setStatus(1);
                relParam.setVideoId(teamVideo.getId());
                List<ThemeMomentsRel> list = iThemeService.getThemeMomentsRelList(relParam);
                if (list.size() > 0){
                    for (ThemeMomentsRel rel : list){
                        iThemeService.updateThemeMomentsStatus(rel.getId(),0);
                        iThemeService.updateThemeMomentsCnt(rel.getThemeId(),-1);
                    }
                }
            }else if(teamVideo.getCheckStatus() != null && teamVideo.getCheckStatus() == 1){
                // 审核通过时，若动态的关联话题无效，则将其修改为有效
                ThemeMomentsRel relParam = new ThemeMomentsRel();
                relParam.setStatus(0);
                relParam.setVideoId(teamVideo.getId());
                List<ThemeMomentsRel> list = iThemeService.getThemeMomentsRelList(relParam);
                if (list.size() > 0){
                    for (ThemeMomentsRel rel : list){
                        iThemeService.updateThemeMomentsStatus(rel.getId(),1);
                        iThemeService.updateThemeMomentsCnt(rel.getThemeId(),1);
                    }
                }
                // 若动态是教学动态，将教学视频修改为有效
                TeamVideo videoInfo = getTeamVideoById(teamVideo.getId());
                if (videoInfo != null && videoInfo.getVideoType() == 2 && videoInfo.getRelationId() != null){
                    Integer relationId = videoInfo.getRelationId();
                    // 查询教学视频信息
                    TeachingVideo teachingVideoInfo = getTeachingVideoById(relationId);
                    if (teachingVideoInfo != null && teachingVideoInfo.getStatus() == 0){
                        TeachingVideo teachingVideo = new TeachingVideo();
                        teachingVideo.setStatus(1);
                        teachingVideo.setId(relationId);
                        iVideoDao.updateTeachingVideoBy(teachingVideo);
                    }
                }
            }
            // 若选为精选，则发送通知
            /*
            if (teamVideo.getIsSelected() != null && teamVideo.getIsSelected() == 1){
                title = "恭喜大家，我们队伍的风采视频已经被官方选为精选。";
                desc = "有更多人可以看到我们的精彩表现了~";
                // 给队长发送战队消息，审核结果通知
                MessagePush param = new MessagePush(2,3,-1,null,title,desc,1);
                param.setTeamId(teamInfo.getId());
                messageService.addMessageByTeam(param);
            }
            */
        }
        return n;
    }

    @Override
    public TeamVideoInfo getTeamVideoById(Integer id) {
        // 查询动态基本信息
        TeamVideoInfo info = iVideoDao.getTeamVideoById(id);
        if (info.getType() == 1){
            List<TeamImage> list = iVideoDao.getTeamImageListByTeamId(id);
            info.setImageList(list);
        }
        // 查询是否关联了话题
        ThemeMomentsRel relParam = new ThemeMomentsRel();
        relParam.setVideoId(id);
        relParam.setStatus(1);
        List<ThemeMomentsRel> relList = iThemeService.getThemeMomentsRelList(relParam);
        String content = info.getVideoDisc();
        if (relList.size() > 0){
            for (ThemeMomentsRel rel : relList){
                ThemeInfoResult themeInfo = iThemeService.getThemeInfoById(rel.getThemeId());
                content = content.replaceAll("#ID="+themeInfo.getId()+"END#","#" + themeInfo.getThemeTitle());
            }
        }
        info.setVideoDiscTemp(content);
        return info;
    }

    @Override
    public int addTeamVideo(TeamVideo teamVideo) {
        return iVideoDao.addTeamVideo(teamVideo);
    }

    @Override
    public int addVote(Vote vote){
        return iVideoDao.addVote(vote);
    }

    @Override
    public int addTeamVoteCnt(Integer id,Integer voteCnt){
        return iVideoDao.addTeamVoteCnt(id,voteCnt);
    }

    @Override
    public int addTeamVideoVoteCnt(Integer id,Integer voteCnt){
        return iVideoDao.addTeamVideoVoteCnt(id,voteCnt);
    }

    @Override
    public void updateTeamVideoGoodCnt(List<Integer> idList, Integer minNum, Integer maxNum) {
        TeamVideo teamVideo = new TeamVideo();
        for(Integer id : idList){
            teamVideo.setId(id);
            teamVideo.setGoodCnt(IdGen.randomInt(minNum,maxNum));
            iVideoDao.updateTeamVideoBy(teamVideo);
        }
    }

    @Override
    public List<MomentsSimpleInfo> getMomentsSimpleInfo(TeamVideo teamVideo) {
        return iVideoDao.getMomentsSimpleInfo(teamVideo);
    }

    @Override
    public int updateMomentsHotSet(List<MomentsSimpleInfo> list) {
        return iVideoDao.updateMomentsHotSet(list);
    }

    @Override
    public List<TeachingVideoInfo> getTeachingVideoTable(TeachingVideoTable table) {
        return iVideoDao.getTeachingVideoTable(table);
    }

    @Override
    public int getTeachingVideoCnt(TeachingVideoTable table) {
        return iVideoDao.getTeachingVideoCnt(table);
    }

    @Override
    public int updateTeachingVideoBy(TeachingVideo teachingVideo) {
        int n = iVideoDao.updateTeachingVideoBy(teachingVideo);
        if(n > 0 && teachingVideo.getCheckStatus() != null){
            // 查询是否有动态信息
            TeamVideoTable param = new TeamVideoTable();
            param.setVideoType(2);
            param.setRelationId(teachingVideo.getId());
            List<TeamVideoInfo> teamVideoList = getTeamVideoTable(param);
            // 审核通过
            List<Integer> themeIdList = new ArrayList<>();
            if (teachingVideo.getCheckStatus() == 1){
                TeachingVideo teachingVideoInfo = getTeachingVideoById(teachingVideo.getId());
                // 只给parentId为0的发动态
                if(teachingVideoInfo != null && teachingVideoInfo.getParentId() == 0){
                    Integer videoId;
                    if (teamVideoList.size() > 0){
                        // 修改动态信息及状态
                        TeamVideo teamVideo = teamVideoList.get(0);
                        teamVideo.setVideoName(teachingVideoInfo.getTeachingName());
                        teamVideo.setVideoDisc(teachingVideoInfo.getTeachingDisc());
                        teamVideo.setCoverUrl(teachingVideoInfo.getCoverUrl());
                        teamVideo.setVideoUrl(teachingVideoInfo.getTeachingUrl());
                        if (teachingVideoInfo.getVideotime() != null){
                            teamVideo.setVideotime(teachingVideoInfo.getVideotime().intValue());
                        }
                        teamVideo.setOperUserId(teachingVideoInfo.getOperUserId());
                        teamVideo.setCheckStatus(1);
                        teamVideo.setStatus(1);
                        updateTeamVideoBy(teamVideo);
                        videoId = teamVideo.getId();
                        sharedRedisClient.del(CommConstantEntity.VIDEO_KEY + videoId);
                    }else{
                        // 添加一条动态
                        TeamVideo teamVideo = new TeamVideo();
                        teamVideo.setTeamId(0);
                        teamVideo.setVideoName(teachingVideoInfo.getTeachingName());
                        teamVideo.setVideoDisc(teachingVideoInfo.getTeachingDisc());
                        teamVideo.setCoverUrl(teachingVideoInfo.getCoverUrl());
                        teamVideo.setVideoUrl(teachingVideoInfo.getTeachingUrl());
                        teamVideo.setType(2);
                        teamVideo.setVideoType(2);
                        teamVideo.setCheckStatus(1);
                        teamVideo.setStatus(1);
                        teamVideo.setCheckUser(teachingVideoInfo.getCheckUser());
                        teamVideo.setIsSelected(0);
                        teamVideo.setSort(0);
                        if (teachingVideoInfo.getVideotime() != null){
                            teamVideo.setVideotime(teachingVideoInfo.getVideotime().intValue());
                        }
                        teamVideo.setIsWorks(0);
                        teamVideo.setOperUserId(teachingVideoInfo.getOperUserId());
                        teamVideo.setRelationId(teachingVideo.getId());
                        addTeamVideo(teamVideo);
                        videoId = teamVideo.getId();
                    }
                    if (teachingVideoInfo.getThemeId() != null){
                        // 添加主题关系
                        themeIdList.add(teachingVideoInfo.getThemeId());
                        iThemeService.addOrModiThemeMomentsRel(themeIdList,videoId);
                        // 主题的动态数 +1
                        iThemeService.updateThemeMomentsCnt(teachingVideoInfo.getThemeId(),1);
                    }
                }
            }else if (teachingVideo.getCheckStatus() == 0){
                // 审核不通过
                if (teamVideoList.size() > 0){
                    // 动态驳回
                    TeamVideo teamVideo = new TeamVideo();
                    teamVideo.setId(teamVideoList.get(0).getId());
                    teamVideo.setCheckStatus(2);
                    teamVideo.setStatus(1);
                    updateTeamVideoStatus(teamVideo);
                    Integer videoId = teamVideoList.get(0).getId();
                    ThemeMomentsRel param1 = new ThemeMomentsRel();
                    param1.setStatus(1);
                    param1.setVideoId(videoId);
                    List<ThemeMomentsRel> relList = iThemeService.getThemeMomentsRelList(param1);

                    // 主题动态 -1
                    if (relList != null && relList.size() > 0) {
                        for(ThemeMomentsRel rel : relList){
                            themeIdList.add(rel.getThemeId());
                        }
                        iThemeService.addOrModiThemeMomentsRel(new ArrayList<Integer>(),videoId);
                        for (Integer themeId : themeIdList) {
                            iThemeService.updateThemeMomentsCnt(themeId,-1);
                        }
                    }
                }
            }
        }

        return n;
    }

    @Override
    public TeachingVideo getTeachingVideoById(Integer id) {
        return iVideoDao.getTeachingVideoById(id);
    }

    @Override
    public void addTeachingVideo(TeachingVideo teachingVideo) {
        String teachingUrl = teachingVideo.getTeachingUrl();
//        long videotime = FileUtils.getVideoFileTime("/data" + teachingUrl);
//        teachingVideo.setVideotime(videotime);
        iVideoDao.addTeachingVideo(teachingVideo);
    }

    @Override
    public int updateTeachingVideoInfo(TeachingVideo teachingVideo) {
        String teachingUrl = teachingVideo.getTeachingUrl();
        long videotime = FileUtils.getVideoFileTime("/data" + teachingUrl);
        teachingVideo.setVideotime(videotime);
        // 修改后要重新审核
        teachingVideo.setStatus(0);
        teachingVideo.setCheckStatus(0);
        return iVideoDao.updateTeachingVideoInfo(teachingVideo);
    }

    @Override
    public int updateTencentVideoBy(TeamVideo teamVideo) {
        return iVideoDao.updateTencentVideoBy(teamVideo);
    }
    @Override
    public List<TencentVideoInfo> getTencentVideoInfos() {
        return iVideoDao.getTencentVideoInfos();
    }

    @Override
    //通过文件id获取腾讯任务id
    public TxFiletask getvodTaskIdbyFileId(String fileId){
        return iVideoDao.getvodTaskIdbyFileId(fileId);
    }

    @Override
    public int updateTeachingVideoInfobyTX(TeachingVideo teamVideo){
        return iVideoDao.updateTeachingVideoInfobyTX(teamVideo);
    }

    @Override
    public List<TencentVideoInfo> getTencentVideoBy(TencentVideoRequest tencentVideoRequest) {
        return iVideoDao.getTencentVideoBy(tencentVideoRequest);
    }
}
