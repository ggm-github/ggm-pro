package com.uway.sports.web.service;

import com.uway.sports.common.service.IBaseService;
import com.uway.sports.web.entity.dbentity.*;
import com.uway.sports.web.entity.paraentity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IVideoService extends IBaseService {

    /******************** 战队视频 ***********************/
    List<TeamVideoInfo> getTeamVideoTable(TeamVideoTable table);
    int getTeamVideoCnt(TeamVideoTable table);
    int updateTeamVideoBy(TeamVideo teamVideo);
    int updateTeamVideoStatus(TeamVideo teamVideo);
    TeamVideoInfo getTeamVideoById(Integer id);
    int addTeamVideo(TeamVideo teamVideo);

    int addVote(Vote vote);
    int addTeamVoteCnt(Integer id,Integer voteCnt);
    int addTeamVideoVoteCnt(Integer id,Integer voteCnt);

    void updateTeamVideoGoodCnt(List<Integer> idList,Integer minNum,Integer maxNum);

    /**
     * 查询动态的评论数、点赞数、投票数、当前热度值，用于热度的变更处理
     * @param teamVideo
     * @return
     */
    List<MomentsSimpleInfo> getMomentsSimpleInfo(TeamVideo teamVideo);

    int updateMomentsHotSet(List<MomentsSimpleInfo> list);
    /******************** 其他视频（教学视频、新闻视频） ***********************/
    List<TeachingVideoInfo> getTeachingVideoTable(TeachingVideoTable table);
    int getTeachingVideoCnt(TeachingVideoTable table);
    int updateTeachingVideoBy(TeachingVideo teachingVideo);
    TeachingVideo getTeachingVideoById(Integer id);
    void addTeachingVideo(TeachingVideo teachingVideo);
    int updateTeachingVideoInfo(TeachingVideo teachingVideo);

    int updateTencentVideoBy(TeamVideo teamVideo);
    List<TencentVideoInfo> getTencentVideoInfos();

    //通过文件id获取腾讯任务id
    TxFiletask getvodTaskIdbyFileId(String fileId);

    int updateTeachingVideoInfobyTX(TeachingVideo teamVideo);

    List<TencentVideoInfo> getTencentVideoBy(TencentVideoRequest tencentVideoRequest);
}
