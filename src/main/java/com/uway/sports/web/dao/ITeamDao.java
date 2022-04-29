package com.uway.sports.web.dao;

import com.uway.sports.common.dao.MyBatisDao;
import com.uway.sports.web.entity.dbentity.*;
import com.uway.sports.web.entity.paraentity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@MyBatisDao
public interface ITeamDao {

    List<TeamInfo> getTeamTable(TeamTable table);
    int getTeamCnt(TeamTable table);
    int updateTeamBy(Team team);
    Team getTeamById(Integer id);
    int updateTeamCloseMult(TeamTable table);

    /**
     * 获取战队所有的行业或地域id
     * @param type
     * @return
     */
    List<RankingRequest> getareaorindustryList(Integer type);

    /**
     * 获取战队当前排名
     * @param rankingRequest
     * @return
     */
    List<RankingRequest> getNowranking(RankingRequest rankingRequest);

    /**
     * 获取投票排名列表
     * @param teamId
     * @return
     */
    List<Ranking> getrankingList(Integer teamId);

    /**
     * 增加排名
     * @param ranking
     * @return
     */
    int addranking(Ranking ranking);

    /**
     * 更新排名
     * @param rankingRequest
     * @return
     */
    int updateRanking(RankingRequest rankingRequest);

    /**
     * 将当前排名更新到上周排名
     * @return
     */
    int updateRankingRowNo();

    /**
     * 比较当前排名和上周排名的大小并标记
     * @return
     */
    int updateRankingtag();

    /**
     * 获取所有战队id
     * @return
     */
    List<Integer> getAllTeamIds();

    /**
     * 修改队员状态
     * @param teamMember
     * @return
     */
    int updateTeamMemberStatus(TeamMember teamMember);

    /**
     * 根据条件查询队员数量
     * @param teamMember
     * @return
     */
    int getTeamMemberCnt(TeamMember teamMember);


    List<RankingInDay> getTeamRankingNow(@Param(value="matchId") Integer matchId,@Param(value="matchItemId") Integer matchItemId);
    int addTeamRankingDay(RankingInDay param);
    // 根据排名类型和队伍查询变更记录信息
    List<RankingChange> getRankingChangeList(@Param(value="rankingType") Integer rankingType,@Param(value="teamId") Integer teamId);
    // 将排名变更表的新纪录字段更新到就记录字段中，并清空新纪录字段，便于下一步的更新
    int updateRankingChangeNewToOld(@Param(value="rankingType") Integer rankingType);
    // 更新排名变更表，没有就新增
    int updateRankingChangeOrAdd(RankingChange param);

    int addRankingChange(RankingChange param);
    int updateRankingChange(RankingChange param);

    // 查询部门赛事的排名情况，用于徒步的单独处理  20191106
    List<TeamStepInfo> getDepartStepInfo(TeamStepInfo param);
    List<TeamStepInfo> getDepartMemberStepInfo(TeamStepInfo param);
    // 查询队伍创建超过半小时，且未超过1小时的（半小时跑一次，防止重复发送），需要上传参赛作品但未上传的队伍
    List<TeamInfo> getTeamlistForNotWorksMoments();
    List<TeamMember> getTeamMemberListForNotice(@Param(value="teamIds") List<Integer> teamIds);
}
