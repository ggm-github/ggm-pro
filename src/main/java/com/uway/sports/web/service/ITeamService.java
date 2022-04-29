package com.uway.sports.web.service;

import com.uway.sports.common.service.IBaseService;
import com.uway.sports.web.entity.dbentity.Ranking;
import com.uway.sports.web.entity.dbentity.Team;
import com.uway.sports.web.entity.dbentity.TeamMember;
import com.uway.sports.web.entity.paraentity.RankingRequest;
import com.uway.sports.web.entity.paraentity.TeamInfo;
import com.uway.sports.web.entity.paraentity.TeamTable;

import java.util.List;
import java.util.Map;

public interface ITeamService extends IBaseService {

    List<TeamInfo> getTeamTable(TeamTable table);
    int getTeamCnt(TeamTable table);
    int updateTeamBy(Team team);
    Team getTeamById(Integer id);
    int updateTeamCloseMult(TeamTable table);

    Map<String,String> updateTeamInfo(Team team);
    /**
     * @Title 战队审核
     *
     */
    Map<String,String> updateTeamStatusCheck(Team team);

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

    int getTeamMemberCnt(TeamMember teamMember);

    // 获取队伍昨日排名，并入表
    void getTeamRankingAndDeal();
    // 获取并发送最终排名信息
    Map<String,String> sendMatchFinalTeamRanking(int matchId,int isMust);
}
