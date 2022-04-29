package com.uway.sports.web.dao;

import com.uway.sports.common.dao.MyBatisDao;
import com.uway.sports.web.entity.dbentity.*;
import com.uway.sports.web.entity.paraentity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao
public interface IVideoDao {

    /******************** 战队视频 ***********************/
    List<TeamVideoInfo> getTeamVideoTable(TeamVideoTable table);
    int getTeamVideoCnt(TeamVideoTable table);
    int updateTeamVideoBy(TeamVideo teamVideo);
    TeamVideoInfo getTeamVideoById(Integer id);
    List<String> getTeamVideoIds(TeamVideoTable table);
    int addTeamVideo(TeamVideo teamVideo);

    int addVote(Vote vote);
    int addTeamVoteCnt(@Param("id")Integer id, @Param("voteCnt")Integer voteCnt);
    int addTeamVideoVoteCnt(@Param("id")Integer id,@Param("voteCnt")Integer voteCnt);

    List<MomentsSimpleInfo> getMomentsSimpleInfo(TeamVideo teamVideo);
    int updateMomentsHotSet(@Param("list") List<MomentsSimpleInfo> list);

    /******************** 其他视频（教学视频、新闻视频） ***********************/
    List<TeachingVideoInfo> getTeachingVideoTable(TeachingVideoTable table);
    int getTeachingVideoCnt(TeachingVideoTable table);
    int updateTeachingVideoBy(TeachingVideo teamVideo);
    TeachingVideo getTeachingVideoById(Integer id);
    void addTeachingVideo(TeachingVideo teachingVideo);
    int updateTeachingVideoInfo(TeachingVideo teachingVideo);

    int updateTencentVideoBy(TeamVideo teamVideo);
    List<TencentVideoInfo> getTencentVideoInfos();

    //通过文件id获取腾讯任务id
    TxFiletask getvodTaskIdbyFileId(String fileId);

    int updateTeachingVideoInfobyTX(TeachingVideo teamVideo);

    List<TencentVideoInfo> getTencentVideoBy(TencentVideoRequest tencentVideoRequest);

    List<TeamImage> getTeamImageListByTeamId(Integer teamId);
}
