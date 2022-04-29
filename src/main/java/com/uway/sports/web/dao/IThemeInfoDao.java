package com.uway.sports.web.dao;

import com.uway.sports.common.dao.MyBatisDao;
import com.uway.sports.web.entity.dbentity.ThemeInfo;
import com.uway.sports.web.entity.dbentity.ThemeMomentsRel;
import com.uway.sports.web.entity.paraentity.ThemeInfoResult;
import com.uway.sports.web.entity.paraentity.ThemeInfoTable;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao
public interface IThemeInfoDao {

	List<ThemeInfoResult> getThemeInfoList(ThemeInfoTable param);

	int getThemeInfoCnt(ThemeInfoTable param);

	ThemeInfoResult getThemeInfoById(Integer id);

	int updateThemeInfo(ThemeInfo param);
	int updateThemeInfoBy(ThemeInfo param);

	int addThemeInfo(ThemeInfo themeInfo);

	int updateThemeMomentsCnt(@Param(value="id") Integer id, @Param(value="num") int num);

	List<ThemeMomentsRel> getThemeMomentsRelList(ThemeMomentsRel param);

	int addThemeMomentsRel(ThemeMomentsRel param);

	int updateThemeMomentsRel(ThemeMomentsRel param);

}
