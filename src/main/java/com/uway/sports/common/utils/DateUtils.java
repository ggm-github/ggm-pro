/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.uway.sports.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 * @author ThinkGem
 * @version 2014-4-15
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

	private static final int[][] fields = new int[][]{{14}, {13}, {12}, {11, 10}, {5, 5, 9}, {2, 1001}, {1}, {0}};
	private static String[] parsePatterns = {
		"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", 
		"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
		"yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};
	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}
	
	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}
	
	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}
	
	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}
	
	/**
	 * 日期型字符串转化为日期 格式
	 * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", 
	 *   "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
	 *   "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null){
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取过去的天数
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(24*60*60*1000);
	}

	/**
	 * 获取过去的小时
	 * @param date
	 * @return
	 */
	public static long pastHour(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*60*1000);
	}
	
	/**
	 * 获取过去的分钟
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*1000);
	}
	
	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 * @param timeMillis
	 * @return
	 */
    public static String formatDateTime(long timeMillis){
		long day = timeMillis/(24*60*60*1000);
		long hour = (timeMillis/(60*60*1000)-day*24);
		long min = ((timeMillis/(60*1000))-day*24*60-hour*60);
		long s = (timeMillis/1000-day*24*60*60-hour*60*60-min*60);
		long sss = (timeMillis-day*24*60*60*1000-hour*60*60*1000-min*60*1000-s*1000);
		return (day>0?day+",":"")+hour+":"+min+":"+s+"."+sss;
    }
	
	/**
	 * 获取两个日期之间的天数
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	public static double getDistanceOfTwoDate(Date before, Date after) {
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
	}
    /**
     * 获取两个日期之间的自然天数(包括开始、结束时间)
     *   如：2018-01-01  与  2018-01-02  为2天
     * @param before
     * @param after
     * @return
     */
    public static int getNatureDayTotal(Date before, Date after){
        Calendar date1 = Calendar.getInstance();
        date1.setTime(before);
        Calendar date2 = Calendar.getInstance();
        date2.setTime(after);
        int day1 = date1.get(Calendar.DAY_OF_YEAR);
        int day2 = date2.get(Calendar.DAY_OF_YEAR);
        return Math.abs(day2 - day1) + 1;
    }

    /**
     * 获取两个日期之间相差的自然天数
     *   如：2018-01-01  与  2018-01-02  为1天
     * @param before
     * @param after
     * @return
     */
    public static int getNatureDayDistance(Date before, Date after){
        Calendar date1 = Calendar.getInstance();
        date1.setTime(before);
        Calendar date2 = Calendar.getInstance();
        date2.setTime(after);
        int day1 = date1.get(Calendar.DAY_OF_YEAR);
        int day2 = date2.get(Calendar.DAY_OF_YEAR);
        return Math.abs(day2 - day1);
    }

    /**
     * 获取两个日期之间的自然周数（不满一周按照一周算）
     *   如：2018-06-01  与  2018-06-06  间隔为1周
     *       2018-06-01  与  2018-06-08  间隔为2周
     * @param before
     * @param after
     * @return
     */
    public static int getNatureWeekDistance(Date before, Date after){
        int distanceDay = getNatureDayTotal(before,after);
        if(distanceDay % 7 == 0){
            return distanceDay / 7;
        }else{
            return distanceDay / 7 + 1;
        }
    }

    /**
     * 获取两个日期之间的自然月数
     *   如：2018-06-01  与  2018-06-06  为1月
     *       2018-06-29  与  2018-07-01  为2月
     * @param before
     * @param after
     * @return
     */
    public static int getNatureMonthTotal(Date before, Date after){
        Calendar c1 = Calendar.getInstance();
        c1.setTime(before);
        int n1 = c1.get(Calendar.MONTH);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(after);
        int n2 = c2.get(Calendar.MONTH);
        return Math.abs(n2 - n1) + 1;
    }

    /**
     * 获取两个日期之间的自然月数
     *   如：2018-06-01  与  2018-06-06  为1年
     *       2018-06-29  与  2019-07-01  为2年
     * @param before
     * @param after
     * @return
     */
    public static int getNatureYearTotal(Date before, Date after){
        Calendar c1 = Calendar.getInstance();
        c1.setTime(before);
        int n1 = c1.get(Calendar.YEAR);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(after);
        int n2 = c2.get(Calendar.YEAR);
        return Math.abs(n2 - n1) + 1;
    }
	
	/** 获取时间段中包含的所有周的星期一的时间集合
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @return
	 * @throws ParseException
	 */
	public static List<String> getWeekMondayList(String startDate, String endDate) throws ParseException {
		if (!StringUtils.isNoneBlank(startDate) || !StringUtils.isNoneBlank(endDate)) {
			return null;
		}
		List<String> list = new ArrayList<String>();
		String startMonDay = getWeekMonDay(startDate);
		String endMonDay = getWeekMonDay(endDate);
		int weeks = (int)getDistanceOfTwoDate(parseDate(startMonDay), parseDate(endMonDay))/7;
		for (int i = 0; i < weeks + 1; i++) {
			String date = formatDate(addDays(parseDate(startMonDay), 7*i), "yyyy-MM-dd");
			list.add(date);
		}
		return list;
	}
	
	/** 获取时间段中包含的所有周的星期一的时间集合
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @return
	 * @throws ParseException
	 */
	public static List<Date> getWeekMondayList(Date startDate, Date endDate) throws ParseException {
		if (startDate == null || endDate == null) {
			return null;
		}
		List<Date> list = new ArrayList<Date>();
		Date startMonDay = getWeekMonDay(startDate);
		Date endMonDay = getWeekMonDay(endDate);
		int weeks = (int)getDistanceOfTwoDate(startMonDay, endMonDay)/7;
		for (int i = 0; i < weeks + 1; i++) {
			Date date = addDays(parseDate(startMonDay), 7*i);
			list.add(date);
		}
		return list;
	}
	
	/** 返回指定日期所在周的时间集合
	 * @param date "yyyy-MM-dd"
	 * @return
	 * @throws ParseException
	 */
	public static String getWeekMonDay(String date) throws ParseException {
		if (!StringUtils.isNoneBlank(date)) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		Calendar cal = Calendar.getInstance();
		Date time = sdf.parse(date);
		cal.setTime(time);

		// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}

		cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一

		int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
	    //System.out.println(String.format("[%s]所在周的星期一:[%s]", date, sdf.format(cal.getTime())));
		return sdf.format(cal.getTime());
	}
	
	/** 返回指定日期所在周的时间集合
	 * @param date "yyyy-MM-dd"
	 * @return
	 * @throws ParseException
	 */
	public static Date getWeekMonDay(Date date) throws ParseException {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}

		cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一

		int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
	    //System.out.println(String.format("[%s]所在周的星期一:[%s]", date, sdf.format(cal.getTime())));
		return parseDate(sdf.format(cal.getTime()));
	}
	
	/** 返回指定日期所在周的时间集合
	 * @param date "yyyy-MM-dd"
	 * @return
	 * @throws ParseException
	 */
	public static List<String> getWeekDays(String date) throws ParseException {
		if (!StringUtils.isNoneBlank(date)) {
			return null;
		}
		List<String> list = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		Calendar cal = Calendar.getInstance();
		Date time = sdf.parse(date);
		cal.setTime(time);

		// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}

		cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一

		int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		list.add(sdf.format(cal.getTime()));
		for (int i = 1; i < 7;i++) {
			cal.add(Calendar.DATE, 1);
			list.add(sdf.format(cal.getTime()));
		}
		for (String str : list) {
			System.out.println("所在周的日期" + str);
		}
		return list;
	}
	
	/** 返回指定日期所在周的时间集合
	 * @param date "yyyy-MM-dd"
	 * @return
	 * @throws ParseException
	 */
	public static List<Date> getWeekDays(Date date) throws ParseException {
		if (date == null) {
			return null;
		}
		List<Date> list = new ArrayList<Date>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}

		cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一

		int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		list.add(parseDate(sdf.format(cal.getTime())));
		for (int i = 1; i < 7;i++) {
			cal.add(Calendar.DATE, 1);
			list.add(parseDate(sdf.format(cal.getTime())));
		}
		return list;
	}
	
	/** 
	* 转换成日期 
	*  
	* @param dateString 
	* @param formatString 
	* @return 
	*/  
	public static Date parse(String dateString, String formatString) {  
	   SimpleDateFormat df = new SimpleDateFormat(formatString);  
	   try {  
	    return df.parse(dateString);  
	   } catch (ParseException e) {  
	    return null;  
	   }  
	}  
	
	/** 
	* 昨天 
	*  
	* @return 
	*/  
	public static Date yesterday() {  
	   return addDay(-1);  
	}  
	/** 
	* 明天 
	*  
	* @return 
	*/  
	public static Date tomorrow() {  
	   return addDay(1);  
	}  
	/** 
	* 现在 
	*  
	* @return 
	*/  
	public static Date now() {  
	   return new Date(System.currentTimeMillis());  
	}  
	/** 
	* 按日加 
	*  
	* @param value 
	* @return 
	*/  
	public static Date addDay(int value) {  
	   Calendar now = Calendar.getInstance();  
	   now.add(Calendar.DAY_OF_YEAR, value);  
	   return now.getTime();  
	}  
	/** 
	* 按日加,指定日期 
	*  
	* @param date 
	* @param value 
	* @return 
	*/  
	public static Date addDay(Date date, int value) {  
	   Calendar now = Calendar.getInstance();  
	   now.setTime(date);  
	   now.add(Calendar.DAY_OF_YEAR, value);  
	   return now.getTime();  
	}  
	/** 
	* 按月加 
	*  
	* @param value 
	* @return 
	*/  
	public static Date addMonth(int value) {  
	   Calendar now = Calendar.getInstance();  
	   now.add(Calendar.MONTH, value);  
	   return now.getTime();  
	}  
	/** 
	* 按月加,指定日期 
	*  
	* @param date 
	* @param value 
	* @return 
	*/  
	public static Date addMonth(Date date, int value) {  
	   Calendar now = Calendar.getInstance();  
	   now.setTime(date);  
	   now.add(Calendar.MONTH, value);  
	   return now.getTime();  
	}  
	/** 
	* 按年加 
	*  
	* @param value 
	* @return 
	*/  
	public static Date addYear(int value) {  
	   Calendar now = Calendar.getInstance();  
	   now.add(Calendar.YEAR, value);  
	   return now.getTime();  
	}  
	/** 
	* 按年加,指定日期 
	*  
	* @param date 
	* @param value 
	* @return 
	*/  
	public static Date addYear(Date date, int value) {  
	   Calendar now = Calendar.getInstance();  
	   now.setTime(date);  
	   now.add(Calendar.YEAR, value);  
	   return now.getTime();  
	}  
	/** 
	* 按小时加 
	*  
	* @param value 
	* @return 
	*/  
	public static Date addHour(int value) {  
	   Calendar now = Calendar.getInstance();  
	   now.add(Calendar.HOUR_OF_DAY, value);  
	   return now.getTime();  
	}  
	/** 
	* 按小时加,指定日期 
	*  
	* @param date 
	* @param value 
	* @return 
	*/  
	public static Date addHour(Date date, int value) {  
	   Calendar now = Calendar.getInstance();  
	   now.setTime(date);  
	   now.add(Calendar.HOUR_OF_DAY, value);  
	   return now.getTime();  
	}  
	/** 
	* 按分钟加 
	*  
	* @param value 
	* @return 
	*/  
	public static Date addMinute(int value) {  
	   Calendar now = Calendar.getInstance();  
	   now.add(Calendar.MINUTE, value);  
	   return now.getTime();  
	}  
	/** 
	* 按分钟加,指定日期 
	*  
	* @param date 
	* @param value 
	* @return 
	*/  
	public static Date addMinute(Date date, int value) {  
	   Calendar now = Calendar.getInstance();  
	   now.setTime(date);  
	   now.add(Calendar.MINUTE, value);  
	   return now.getTime();  
	}  
	/** 
	* 年份 
	*  
	* @return 
	*/  
	public static int year() {  
	   Calendar now = Calendar.getInstance();  
	   return now.get(Calendar.YEAR);  
	}  
	/** 
	* 月份 
	*  
	* @return 
	*/  
	public static int month() {  
	   Calendar now = Calendar.getInstance();  
	   return now.get(Calendar.MONTH);  
	}  
	/** 
	* 日(号) 
	*  
	* @return 
	*/  
	public static int day() {  
	   Calendar now = Calendar.getInstance();  
	   return now.get(Calendar.DAY_OF_MONTH);  
	}  
	/** 
	* 小时(点) 
	*  
	* @return 
	*/  
	public static int hour() {  
	   Calendar now = Calendar.getInstance();  
	   return now.get(Calendar.HOUR);  
	}  
	/** 
	* 分钟 
	*  
	* @return 
	*/  
	public static int minute() {  
	   Calendar now = Calendar.getInstance();  
	   return now.get(Calendar.MINUTE);  
	}  
	/** 
	* 秒 
	*  
	* @return 
	*/  
	public static int second() {  
	   Calendar now = Calendar.getInstance();  
	   return now.get(Calendar.SECOND);  
	}  
	/** 
	* 星期几(礼拜几) 
	*  
	* @return 
	*/  
	public static int weekday() {  
	   Calendar now = Calendar.getInstance();  
	   return now.get(Calendar.DAY_OF_WEEK) - 1;  
	}  
	/** 
	* 是上午吗? 
	*  
	* @return 
	*/  
	public static boolean isAm() {  
	   Calendar now = Calendar.getInstance();  
	   return now.get(Calendar.AM_PM) == 0;  
	}  
	/** 
	* 是下午吗? 
	*  
	* @return 
	*/  
	public static boolean isPm() {  
	   Calendar now = Calendar.getInstance();  
	   return now.get(Calendar.AM_PM) == 1;  
	} 
	
	/**
	 * 根据传入日期获取所在周的周一
	 * @param date
	 * @return
	 */
	public static Date getMondayByDate(Date date){
		  Calendar calendar = Calendar.getInstance();
		  calendar.setTime(date);
		  calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);		  
		  return calendar.getTime();
	}
	
	/**
	 * 根据传入日期获取所在周的周日
	 * @param date
	 * @return
	 */
	public static Date getSundayByDate(Date date){
		  Calendar calendar = Calendar.getInstance();
		  calendar.setTime(date);
		  calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		  return calendar.getTime();
	}
	
	/**
	 * 获取当前日期  不包含 时 分 秒
	 * @return
	 */
	public static Date getNowOnlyDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    return parseDate(sdf.format(new Date()));
	}


    /**
     * 根据日期计算当月的第一天与最后一天
     *
     * @param date
     * @return
     */
    public static Map<String, String> convertMonthByDate(Date date) {
        Map<String, String> map = new HashMap<>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Date theDate = calendar.getTime();
        // 上个月第一天
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        String day_first = df.format(gcLast.getTime());
        // 上个月最后一天
        calendar.add(Calendar.MONTH, 1); // 加一个月
        calendar.set(Calendar.DATE, 1); // 设置为该月第一天
        calendar.add(Calendar.DATE, -1); // 再减一天即为该月最后一天
        String day_last = df.format(calendar.getTime());
        map.put("first", day_first);
        map.put("last", day_last);
        return map;
    }


    /**
     * 根据日期计算当年的第一天与最后一天
     *
     * @param date
     * @return
     */
    public static Map<String, String> convertYearByDate(Date date) {
        Map<String, String> map = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        calendar.set(Calendar.YEAR, year + 1);
        calendar.add(Calendar.DATE, -1);
        Date lastYearFirst = calendar.getTime();
        map.put("first", sdf.format(currYearFirst));
        map.put("last", sdf.format(lastYearFirst));
        return map;

    }
    /**
     * Auth: liujingce
     * Date: 2018/6/21 16:41
     * Title: 比较两日期的大小（先后）
     * @param date1,date2
     * @return int
     * Describer: int < 0,date1 < date2;
     *            int == 0,date1 == date2
     *            int > 0 ,date1 > date2
    **/
    public static int compareDate(Date date1,Date date2){
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date1);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(date2);
        return c1.compareTo(c2);
    }

    /**
     * Auth: liujingce
     * Date: 2018/6/21 16:41
     * Title: 比较两日期的大小（先后）
     * @param date1,date2
     * @return int
     * Describer: int < 0,date1 < date2;
     *            int == 0,date1 == date2
     *            int > 0 ,date1 > date2
     **/
    public static int compareDate(String date1,String date2,String format){
        if(StringUtils.isBlank(format)){
            format = "yyyy-MM-dd";
        }
        Calendar c1 = Calendar.getInstance();
        c1.setTime(parse(date1,format));
        Calendar c2 = Calendar.getInstance();
        c2.setTime(parse(date2,format));
        return c1.compareTo(c2);
    }

	/**
	 * 判断日期为本周的第几天（按照周一为第一天计算）
	 * @param time
	 * @return
	 */
	public static int getDayNumberInWeek(Date time){
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		// 获得当前日期是一个星期的第几天
		int n = cal.get(Calendar.DAY_OF_WEEK);
		if (n == 1){
			return 7;
		}else{
			return n - 1;
		}
	}

	/**
	 * 判断日期为本月的第几天
	 * @param time
	 * @return
	 */
	public static int getDayNumberInMonth(Date time){
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		// 获得当前日期是一个星期的第几天
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	public static void main(String[] args) throws ParseException {

		System.out.println(formatDate(DateUtils.yesterday(),"M.dd"));
	}

}
