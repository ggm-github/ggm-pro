package com.uway.sports.web.service;


import com.uway.sports.web.entity.dbentity.ThemeInfo;
import com.uway.sports.web.entity.dbentity.ThemeMomentsRel;
import com.uway.sports.web.entity.paraentity.ThemeInfoResult;
import com.uway.sports.web.entity.paraentity.ThemeInfoTable;

import java.util.List;

public interface IThemeService {

    ThemeInfoResult getThemeInfoById(Integer themeId);

    int updateThemeInfo(ThemeInfo themeInfo);
    int updateThemeInfoBy(ThemeInfo themeInfo);

    int addThemeInfo(ThemeInfo themeInfo);

    List<ThemeInfoResult> getThemeInfoList(ThemeInfoTable param);

    int getThemeInfoCnt(ThemeInfoTable param);

    int updateThemeMomentsCnt(Integer id, int cnt);

    List<ThemeMomentsRel> getThemeMomentsRelList(ThemeMomentsRel param);

    int addOrModiThemeMomentsRel(List<Integer> themeId,Integer videoId);

    int updateThemeMomentsStatus(Integer id,Integer status);
}
