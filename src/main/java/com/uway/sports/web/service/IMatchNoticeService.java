package com.uway.sports.web.service;

import com.uway.sports.web.entity.dbentity.MatchNotice;
import com.uway.sports.web.entity.dbentity.NoticeTemplate;
import com.uway.sports.web.entity.paraentity.MatchNoticeResult;
import com.uway.sports.web.entity.paraentity.MatchNoticeTable;
import com.uway.sports.web.entity.paraentity.NoticeTemplateRequest;

import java.util.List;

public interface IMatchNoticeService {

    List<MatchNoticeResult> getMatchNoticeList(MatchNoticeTable param);
    int getMatchNoticeCnt(MatchNoticeTable param);
    int updateMatchNoticeForAudit(MatchNotice param,Integer operUserId);
    MatchNotice getMatchNoticeById(Integer id);
    List<NoticeTemplate> getNoticeTemplateList(NoticeTemplateRequest param);

}
