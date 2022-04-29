package com.uway.sports.web.dao;

import com.uway.sports.common.dao.MyBatisDao;
import com.uway.sports.web.entity.dbentity.MatchNotice;
import com.uway.sports.web.entity.dbentity.MatchNoticeOperate;
import com.uway.sports.web.entity.dbentity.NoticeTemplate;
import com.uway.sports.web.entity.paraentity.MatchNoticeResult;
import com.uway.sports.web.entity.paraentity.MatchNoticeTable;
import com.uway.sports.web.entity.paraentity.NoticeTemplateRequest;

import java.util.List;

@MyBatisDao
public interface IMatchNoticeDao {

    List<MatchNoticeResult> getMatchNoticeList(MatchNoticeTable param);
    int getMatchNoticeCnt(MatchNoticeTable param);
    int updateMatchNoticeBy(MatchNotice param);
    int addMatchNoticeOperate(MatchNoticeOperate param);
    MatchNotice getMatchNoticeById(Integer id);
    List<NoticeTemplate> getNoticeTemplateList(NoticeTemplateRequest param);
}
