
/*
 * 根据时间粒度,将2016-10-10转换为10-10（天）,10.10（周,月）
 * */

function convertDateFormat(timeStr,timeGran){
	var timeDate=new Date(timeStr);
	if(timeGran==1){
		return timeDate.toShortDateStringNoYear("-");
	}else{
		
		var timeWM=timeStr.split("—")
		var timeValue=new Date(timeWM[0]).toShortDateStringNoYear(".")+'—'+new Date(timeWM[1]).toShortDateStringNoYear(".");
		return timeValue;
	}
}





 /* 返回四天前天的json数组【{cycle:3,time:2015-09-30};{..};{..};{..}】 */			

function fourDays(){
 	var d=new Date().getTime();
 	var minusDate = 1000 * 60 * 60 * 24;
 	var firstDay,secondDay,thirdDay,fourthDay; 
 	day1=new Date(d-minusDate*1).toShortDateString("-");
 	day2=new Date(d-minusDate*2).toShortDateString("-");
 	day3=new Date(d-minusDate*3).toShortDateString("-");
 	day4=new Date(d-minusDate*4).toShortDateString("-");	
 	day5=new Date(d-minusDate*5).toShortDateString("-");
 	day6=new Date(d-minusDate*6).toShortDateString("-");
 	day7=new Date(d-minusDate*7).toShortDateString("-");
 	day8=new Date(d-minusDate*8).toShortDateString("-");
 	day9=new Date(d-minusDate*9).toShortDateString("-");
 	day10=new Date(d-minusDate*10).toShortDateString("-");
 	day11=new Date(d-minusDate*11).toShortDateString("-");
 	day12=new Date(d-minusDate*12).toShortDateString("-");
 	day13=new Date(d-minusDate*13).toShortDateString("-");
 	day14=new Date(d-minusDate*14).toShortDateString("-");
 	day15=new Date(d-minusDate*15).toShortDateString("-");
 	day16=new Date(d-minusDate*16).toShortDateString("-");
 	
 	
 	var param1={};
 	param1.cycle=1;
 	param1.time=day1;
 	
 	var param2={};
 	param2.cycle=1;
 	param2.time=day2;
 	
 	var param3={};
 	param3.cycle=1;
 	param3.time=day3;
 	
 	var param4={};
 	param4.cycle=1;
 	param4.time=day4;
 	
	var param5={};
 	param5.cycle=1;
 	param5.time=day5;
 	
	var param6={};
 	param6.cycle=1;
 	param6.time=day6;
 	
	var param7={};
 	param7.cycle=1;
 	param7.time=day7;
 	
	var param8={};
 	param8.cycle=1;
 	param8.time=day8;
 	
	var param9={};
 	param9.cycle=1;
 	param9.time=day9;
 	
	var param10={};
 	param10.cycle=1;
 	param10.time=day10;
 	
	var param11={};
 	param11.cycle=1;
 	param11.time=day11;
 	
	var param12={};
 	param12.cycle=1;
 	param12.time=day12;
 	
	var param13={};
 	param13.cycle=1;
 	param13.time=day13;
 	
	var param14={};
 	param14.cycle=1;
 	param14.time=day14;
 	
 	
	var param15={};
 	param15.cycle=1;
 	param15.time=day15;
 	
 	
	var param16={};
 	param16.cycle=1;
 	param16.time=day16;
 	

 	
 	var date=[];
 	date.push(param1);
 	date.push(param2);
 	date.push(param3);
 	date.push(param4);
	date.push(param5);
 	date.push(param6);
 	date.push(param7);
 	date.push(param8);
	date.push(param9);
 	date.push(param10);
 	date.push(param11);
 	date.push(param12);
	date.push(param13);
 	date.push(param14);
 	date.push(param15);
 	date.push(param16);
 	return date;
}

 /* 
  * 返回以周为周期前四周的json数组
  * [{cycle:1,start:2015-09-28,end:2015-10-04};{..};{..};{..}]
  * 
  * */
 
 function weekDate() {
	 	var d = new Date()
	 	var minusDate = 1000 * 60 * 60 * 24;
	 	var monday;
	 	var firstMonday, firstWeekday, secondMonday, secondWeekday, thirdMonday, thirdWeekday, fourthMonday, fourthWeekday;
	 	monday = getMondayBySomeday(d).getTime();
	 	firstMonday = new Date(monday - minusDate * 7).toShortDateString("-");
	 	firstWeekday = new Date(monday - minusDate * 1).toShortDateString("-");

	 	var param1 = {};
	 	param1.cycle = 2;
	 	param1.start = firstMonday;
	 	param1.end = firstWeekday;

	 	secondMonday = new Date(monday - minusDate * 7 * 2).toShortDateString("-");
	 	secondWeekday = new Date(monday - minusDate * (1 + 7 * 1)).toShortDateString("-");

	 	var param2 = {};
	 	param2.cycle = 2;
	 	param2.start = secondMonday;
	 	param2.end = secondWeekday;

	 	thirdMonday = new Date(monday - minusDate * 7 * 3).toShortDateString("-");
	 	thirdWeekday = new Date(monday - minusDate * (1 + 7 * 2)).toShortDateString("-");

	 	var param3 = {};
	 	param3.cycle = 2;
	 	param3.start = thirdMonday;
	 	param3.end = thirdWeekday;

	 	fourthMonday = new Date(monday - minusDate * 7 * 4).toShortDateString("-");
	 	fourthWeekday = new Date(monday - minusDate * (1 + 7 * 3)).toShortDateString("-");

	 	var param4 = {};
	 	param4.cycle = 2;
	 	param4.start = fourthMonday;
	 	param4.end = fourthWeekday;
	 	
	 	fiveMonday = new Date(monday - minusDate * 7 * 5).toShortDateString("-");
	 	fiveWeekday = new Date(monday - minusDate * (1 + 7 * 4)).toShortDateString("-");
	 	
	 	
		var param5 = {};
	 	param5.cycle = 2;
	 	param5.start = fiveMonday;
	 	param5.end = fiveWeekday;
	 	
	 	sixMonday = new Date(monday - minusDate * 7 * 6).toShortDateString("-");
	 	sixWeekday = new Date(monday - minusDate * (1 + 7 * 5)).toShortDateString("-");
	 	
	 	
		var param6 = {};
	 	param6.cycle = 2;
	 	param6.start = sixMonday;
	 	param6.end = sixWeekday;
	 	
		sevenMonday = new Date(monday - minusDate * 7 * 7).toShortDateString("-");
	 	sevenWeekday = new Date(monday - minusDate * (1 + 7 * 6)).toShortDateString("-");
	 	
	 	
		var param7 = {};
	 	param7.cycle = 2;
	 	param7.start = sevenMonday;
	 	param7.end = sevenWeekday;

	 	var date = [];
	 	date.push(param1);
	 	date.push(param2);
	 	date.push(param3);
	 	date.push(param4);
	 	date.push(param5);
	 	date.push(param6);
	 	date.push(param7);
	 	return date;
	 };

 
 
 
 
 
 /*
  * 
  * 返回以月为周期前四个月的json数组 [{cycle:1,start:2015-09-01,end:2015-09-30};{..};{..};{..}]
  * 
  */

 function monthDate() {
	 	var d = new Date();
	 	var minusDate = 1000 * 60 * 60 * 24;
	 	var month;
	 	var firstMonthF, firstMonthL, secondMonthF, secondMonthL, thirdMonthF, thirdMonthL, fourthMonthF, fourthMonthL;
	 	month = getFirstDayOfMonth(d).getTime();
	 	var firstMonthLDate = new Date(month - minusDate * 1);
	 	firstMonthL = new Date(month - minusDate * 1).toShortDateString("-");
	 	var fMonthF = getFirstDayOfMonth(firstMonthLDate);
	 	firstMonthF = fMonthF.toShortDateString("-");

	 	var param1 = {};
	 	param1.cycle = 3;
	 	param1.start = firstMonthF;
	 	param1.end = firstMonthL;

	 	var secondMonthLDate = new Date(fMonthF - minusDate * 1);
	 	secondMonthL = new Date(fMonthF - minusDate * 1).toShortDateString("-");
	 	var sMonthF = getFirstDayOfMonth(secondMonthLDate);
	 	secondMonthF = sMonthF.toShortDateString("-");

	 	var param2 = {};
	 	param2.cycle = 3;
	 	param2.start = secondMonthF;
	 	param2.end = secondMonthL;

	 	var thirdMonthLDate = new Date(sMonthF - minusDate * 1);
	 	thirdMonthL = new Date(sMonthF - minusDate * 1).toShortDateString("-");
	 	var tMonthF = getFirstDayOfMonth(thirdMonthLDate);
	 	thirdMonthF = tMonthF.toShortDateString("-");

	 	var param3 = {};
	 	param3.cycle = 3;
	 	param3.start = thirdMonthF;
	 	param3.end = thirdMonthL;

	 	var fourthMonthLDate = new Date(tMonthF - minusDate * 1);
	 	fourthMonthL = new Date(tMonthF - minusDate * 1).toShortDateString("-");
	 	var fourMonthF=getFirstDayOfMonth(fourthMonthLDate);
	 	fourthMonthF = getFirstDayOfMonth(fourthMonthLDate).toShortDateString("-");

	 	var param4 = {};
	 	param4.cycle = 3;
	 	param4.start = fourthMonthF;
	 	param4.end = fourthMonthL;
	 	
	 	var fiveMonthLDate = new Date(fourMonthF - minusDate * 1);
	 	fiveMonthL = new Date(fourMonthF - minusDate * 1).toShortDateString("-");
	 	var fiMonthF = getFirstDayOfMonth(fiveMonthLDate);
	 	fiveMonthF = getFirstDayOfMonth(fiveMonthLDate).toShortDateString("-");

	 	var param5 = {};
	 	param5.cycle = 3;
	 	param5.start = fiveMonthF;
	 	param5.end = fiveMonthL;
	 	

	 	var sixMonthLDate = new Date(fiMonthF - minusDate * 1);
	 	sixMonthL = new Date(fiMonthF - minusDate * 1).toShortDateString("-");
	 	var siMonthF = getFirstDayOfMonth(sixMonthLDate);
	 	sixMonthF = getFirstDayOfMonth(sixMonthLDate).toShortDateString("-");

	 	var param6 = {};
	 	param6.cycle = 3;
	 	param6.start = sixMonthF;
	 	param6.end = sixMonthL;
	 	
		var sevenMonthLDate = new Date(siMonthF - minusDate * 1);
	 	sevenMonthL = new Date(siMonthF - minusDate * 1).toShortDateString("-");	 	
	 	sevenMonthF = getFirstDayOfMonth(sevenMonthLDate).toShortDateString("-");

	 	var param7 = {};
	 	param7.cycle = 3;
	 	param7.start = sevenMonthF;
	 	param7.end = sevenMonthL;

	 	var date = [];
	 	date.push(param1);
	 	date.push(param2);
	 	date.push(param3);
	 	date.push(param4);
	 	date.push(param5);
	 	date.push(param6);
	 	date.push(param7);
	 	return date;
	 }


 /*点击左箭头，时间向前推移*/
 function dayLast(){
 	 $(".uway-time-control").removeClass("time-line2").addClass("time-line1");
 	 var sGranularity=timeParams.timeSelected.selectGranularity;
 	 var sIndex=timeParams.timeSelected.selectIndex;
 	 var getTimeJson;
 	 var minusDate = 1000 * 60 * 60 * 24;
 	 var timeShow1,timeShow2,timeShow3,timeShow4;
 	 var labelNum=document.getElementById("cycle").innerHTML;
 	 if(labelNum==2){
 		var time1=$("#lw7").data('time-value');
 	 	var time2=$("#lw6").data('time-value');
 	 	var time3=$("#lw5").data('time-value');
 	 	var time4=$("#lw4").data('time-value');
 	 	var time5=$("#lw3").data('time-value');
 	 	var time6=$("#lw2").data('time-value');
 	 	var time7=$("#lw1").data('time-value');
 	 	
 		var timeWeek1={};
 		var timeWeek2={};
 		var timeWeek3={};
 		var timeWeek4={};
 		var timeWeek5={};
 		var timeWeek6={};
 		var timeWeek7={};
 		
 		timeWeek1=time1.split("—");
 		timeWeek2=time2.split("—");
 		timeWeek3=time3.split("—");
 		timeWeek4=time4.split("—");
 		timeWeek5=time5.split("—");
 		timeWeek6=time6.split("—");
 		timeWeek7=time7.split("—");
 		
 	
 		var weekShow1={};
 		weekShow1.cycle=2;		
 		weekShow1.start=new Date(Date.parse(timeWeek1[0])-minusDate*7*6).toShortDateString("-");
 		weekShow1.end=new Date(Date.parse(timeWeek1[1])-minusDate*7*6).toShortDateString("-");
 		
 		var weekShow2={};
 		weekShow2.cycle=2;
 		weekShow2.start=new Date(Date.parse(timeWeek2[0])-minusDate*7*6).toShortDateString("-");
 		weekShow2.end=new Date(Date.parse(timeWeek2[1])-minusDate*7*6).toShortDateString("-");
 		
 		var weekShow3={};
 		weekShow3.cycle=2;
 		weekShow3.start=new Date(Date.parse(timeWeek3[0])-minusDate*7*6).toShortDateString("-");
 		weekShow3.end=new Date(Date.parse(timeWeek3[1])-minusDate*7*6).toShortDateString("-");
 		
 		var weekShow4={};
 		weekShow4.cycle=2;
 		weekShow4.start=new Date(Date.parse(timeWeek4[0])-minusDate*7*6).toShortDateString("-");
 		weekShow4.end=new Date(Date.parse(timeWeek4[1])-minusDate*7*6).toShortDateString("-");
 		 
 		var weekShow5={};
 		weekShow5.cycle=2;
 		weekShow5.start=new Date(Date.parse(timeWeek5[0])-minusDate*7*6).toShortDateString("-");
 		weekShow5.end=new Date(Date.parse(timeWeek5[1])-minusDate*7*6).toShortDateString("-");
 		
 		var weekShow6={};
 		weekShow6.cycle=2;
 		weekShow6.start=new Date(Date.parse(timeWeek6[0])-minusDate*7*6).toShortDateString("-");
 		weekShow6.end=new Date(Date.parse(timeWeek6[1])-minusDate*7*6).toShortDateString("-");
 		
 		var weekShow7={};
 		weekShow7.cycle=2;
 		weekShow7.start=new Date(Date.parse(timeWeek7[0])-minusDate*7*6).toShortDateString("-");
 		weekShow7.end=new Date(Date.parse(timeWeek7[1])-minusDate*7*6).toShortDateString("-");
 		
 		
 		weekShow=[];
 		weekShow.push(weekShow7);
 		weekShow.push(weekShow6);
 		weekShow.push(weekShow5);
 		weekShow.push(weekShow4);
 		weekShow.push(weekShow3);
 		weekShow.push(weekShow2);
 		weekShow.push(weekShow1);
 		
 		timeShow1=weekShow[6].start+"—"+weekShow[6].end;
 		timeShow2=weekShow[5].start+"—"+weekShow[5].end;
 		timeShow3=weekShow[4].start+"—"+weekShow[4].end;
 		timeShow4=weekShow[3].start+"—"+weekShow[3].end;
 		timeShow5=weekShow[2].start+"—"+weekShow[2].end;
 		timeShow6=weekShow[1].start+"—"+weekShow[1].end;
 		timeShow7=weekShow[0].start+"—"+weekShow[0].end;
 	 	
 	 
 	 }else if(labelNum==3){
 		var time1=$("#lw7").data('time-value');
 	 	var time2=$("#lw6").data('time-value');
 	 	var time3=$("#lw5").data('time-value');
 	 	var time4=$("#lw4").data('time-value');
 	 	var time5=$("#lw3").data('time-value');
 	 	var time6=$("#lw2").data('time-value');
 	 	var time7=$("#lw1").data('time-value');
 		 var timeMonth1={};
 		 var timeMonth2={};
 		 var timeMonth3={};
 		 var timeMonth4={};
 		 var timeMonth5={};
 		 var timeMonth6={};
 		 var timeMonth7={};
 		 timeMonth1=time1.split("—");
 		 timeMonth2=time2.split("—");
 		 timeMonth3=time3.split("—");
 		 timeMonth4=time4.split("—");
 		 timeMonth5=time5.split("—");
 		 timeMonth6=time6.split("—");
 		 timeMonth7=time7.split("—");
 		
 		var monthShow7={};
		monthShow7.cycle=3;
		var monthEnd7=new Date(Date.parse(timeMonth2[0])-minusDate*1);
		var monthStart7=getFirstDayOfMonth(monthEnd7);
		monthShow7.start=monthStart7.toShortDateString("-");
		monthShow7.end=new Date(Date.parse(timeMonth2[0])-minusDate*1).toShortDateString("-");
 		 
 		 
 		 var monthShow6={};
		monthShow6.cycle=3;
		var monthEnd6=new Date(monthStart7-minusDate*1);
		var monthStart6=getFirstDayOfMonth(monthEnd6);
		monthShow6.start=monthStart6.toShortDateString("-");
		monthShow6.end=new Date(monthStart7-minusDate*1).toShortDateString("-");
 		 
 		var monthShow5={};
			monthShow5.cycle=3;
			var monthEnd5=new Date(monthStart6-minusDate*1);
			var monthStart5=getFirstDayOfMonth(monthEnd5);
			monthShow5.start=monthStart5.toShortDateString("-");
			monthShow5.end=new Date(monthStart6-minusDate*1).toShortDateString("-");
 		 
 		 
 			var monthShow4={};
 			monthShow4.cycle=3;
 			var monthEnd4=new Date(monthStart5-minusDate*1);
 			var monthStart4=getFirstDayOfMonth(monthEnd4);
 			monthShow4.start=monthStart4.toShortDateString("-");
 			monthShow4.end=new Date(monthStart5-minusDate*1).toShortDateString("-");
 			
 			var monthShow3={};
 			monthShow3.cycle=3;
 			var monthEnd3=new Date(monthStart4-minusDate*1);
 			var monthStart3=getFirstDayOfMonth(monthEnd3);
 			monthShow3.start=monthStart3.toShortDateString("-");
 			monthShow3.end=new Date(monthStart4-minusDate*1).toShortDateString("-");
 			
 			var monthShow2={};
 			monthShow2.cycle=3;
 			var monthEnd2=new Date(monthStart3-minusDate*1);
 			var monthStart2=getFirstDayOfMonth(monthEnd2);
 			monthShow2.start=monthStart2.toShortDateString("-");
 			monthShow2.end=new Date(monthStart3-minusDate*1).toShortDateString("-");
 			
 			var monthShow1={};
 			monthShow1.cycle=3;
 			var monthEnd1=new Date(monthStart2-minusDate*1);
 			var monthStart1=getFirstDayOfMonth(monthEnd1);
 			monthShow1.start=monthStart1.toShortDateString("-");
 			monthShow1.end=new Date(monthStart2-minusDate*1).toShortDateString("-");
 			
 			monthShow=[];
 			
 			monthShow.push(monthShow7);
 			monthShow.push(monthShow6);
 			monthShow.push(monthShow5);
 			monthShow.push(monthShow4);
 			monthShow.push(monthShow3);
 			monthShow.push(monthShow2);
 			monthShow.push(monthShow1);
 			
 			timeShow1=monthShow[6].start+"—"+monthShow[6].end;
 			timeShow2=monthShow[5].start+"—"+monthShow[5].end;
 			timeShow3=monthShow[4].start+"—"+monthShow[4].end;
 			timeShow4=monthShow[3].start+"—"+monthShow[3].end;
 			timeShow5=monthShow[2].start+"—"+monthShow[2].end;
 			timeShow6=monthShow[1].start+"—"+monthShow[1].end;
 			timeShow7=monthShow[0].start+"—"+monthShow[0].end;
 	 }else{		
 		var time1=$("#l16").data('time-value');
 	 	var time2=$("#l15").data('time-value');
 	 	var time3=$("#l14").data('time-value');
 	 	var time4=$("#l13").data('time-value'); 
 	 	var time5=$("#l12").data('time-value'); 
 	 	var time6=$("#l11").data('time-value'); 
 	 	var time7=$("#l10").data('time-value'); 
 	 	var time8=$("#l9").data('time-value'); 
 	 	var time9=$("#l8").data('time-value'); 
 	 	var time10=$("#l7").data('time-value'); 
 	 	var time11=$("#l6").data('time-value'); 
 	 	var time12=$("#l5").data('time-value'); 
 	 	var time13=$("#l4").data('time-value'); 
 	 	var time14=$("#l3").data('time-value'); 
 	 	var time15=$("#l2").data('time-value'); 
 	 	var time16=$("#l1").data('time-value'); 
 		 
 		var date1= Date.parse(time1);
		 var date2= Date.parse(time2);
		 var date3= Date.parse(time3);
		 var date4= Date.parse(time4);
		 var date5= Date.parse(time5);
		 var date6= Date.parse(time6);
		 var date7= Date.parse(time7);
		 var date8= Date.parse(time8);
		 var date9= Date.parse(time9);
		 var date10= Date.parse(time10);
		 var date11= Date.parse(time11);
		 var date12= Date.parse(time12);
		 var date13= Date.parse(time13);
		 var date14= Date.parse(time14);
		 var date15= Date.parse(time15);
		 var date16= Date.parse(time16);
		 var dayShow1={};	
		 dayShow1.cycle=1;
		 dayShow1.time=new Date(date1-minusDate*15).toShortDateString("-");
		 var dayShow2={};
		 dayShow2.cycle=1;
		 dayShow2.time=new Date(date2-minusDate*15).toShortDateString("-");
	     var dayShow3={};
	     dayShow3.cycle=1;
		 dayShow3.time=new Date(date3-minusDate*15).toShortDateString("-");
		 var dayShow4={};
		 dayShow4.cycle=1;
		 dayShow4.time=new Date(date4-minusDate*15).toShortDateString("-");
		 var dayShow5={};
		 dayShow5.cycle=1;
		 dayShow5.time=new Date(date5-minusDate*15).toShortDateString("-");
		 var dayShow6={};
		 dayShow6.cycle=1;
		 dayShow6.time=new Date(date6-minusDate*15).toShortDateString("-");
		 var dayShow7={};
		 dayShow7.cycle=1;
		 dayShow7.time=new Date(date7-minusDate*15).toShortDateString("-");
		 var dayShow8={};
		 dayShow8.cycle=1;
		 dayShow8.time=new Date(date8-minusDate*15).toShortDateString("-");
		 var dayShow9={};
		 dayShow9.cycle=1;
		 dayShow9.time=new Date(date9-minusDate*15).toShortDateString("-");
		 var dayShow10={};
		 dayShow10.cycle=1;
		 dayShow10.time=new Date(date10-minusDate*15).toShortDateString("-");
		 var dayShow11={};
		 dayShow11.cycle=1;
		 dayShow11.time=new Date(date11-minusDate*15).toShortDateString("-");
		 var dayShow12={};
		 dayShow12.cycle=1;
		 dayShow12.time=new Date(date12-minusDate*15).toShortDateString("-");
		 var dayShow13={};
		 dayShow13.cycle=1;
		 dayShow13.time=new Date(date13-minusDate*15).toShortDateString("-");
		 var dayShow14={};
		 dayShow14.cycle=1;
		 dayShow14.time=new Date(date14-minusDate*15).toShortDateString("-");
		 var dayShow15={};
		 dayShow15.cycle=1;
		 dayShow15.time=new Date(date15-minusDate*15).toShortDateString("-");
		 var dayShow16={};
		 dayShow16.cycle=1;
		 dayShow16.time=new Date(date16-minusDate*15).toShortDateString("-");

		 dayShow=[];
		 dayShow.push(dayShow16);
		 dayShow.push(dayShow15);
		 dayShow.push(dayShow14);
		 dayShow.push(dayShow13);
		 dayShow.push(dayShow12);
		 dayShow.push(dayShow11);
		 dayShow.push(dayShow10);
		 dayShow.push(dayShow9);
		 dayShow.push(dayShow8);
		 dayShow.push(dayShow7);
		 dayShow.push(dayShow6);
		 dayShow.push(dayShow5);
		 dayShow.push(dayShow4);
		 dayShow.push(dayShow3);
		 dayShow.push(dayShow2);
		 dayShow.push(dayShow1);
		timeShow1=dayShow[15].time;
		timeShow2=dayShow[14].time;	
		timeShow3=dayShow[13].time;	
		timeShow4=dayShow[12].time;
		timeShow5=dayShow[11].time;
		timeShow6=dayShow[10].time;
		timeShow7=dayShow[9].time;
		timeShow8=dayShow[8].time;
		timeShow9=dayShow[7].time;
		timeShow10=dayShow[6].time;
		timeShow11=dayShow[5].time;
		timeShow12=dayShow[4].time;
		timeShow13=dayShow[3].time;
		timeShow14=dayShow[2].time;
		timeShow15=dayShow[1].time;
		timeShow16=dayShow[0].time;
 	 }
 	 if(labelNum==1){
	 	 $("#l16").data('time-value',timeShow1);
		 $("#l15").data('time-value',timeShow2);
		 $("#l14").data('time-value',timeShow3);
		 $("#l13").data('time-value',timeShow4);
		 $("#l12").data('time-value',timeShow5);
		 $("#l11").data('time-value',timeShow6);
		 $("#l10").data('time-value',timeShow7);
		 $("#l9").data('time-value',timeShow8);
		 $("#l8").data('time-value',timeShow9);
		 $("#l7").data('time-value',timeShow10);
		 $("#l6").data('time-value',timeShow11);
		 $("#l5").data('time-value',timeShow12);
		 $("#l4").data('time-value',timeShow13);
		 $("#l3").data('time-value',timeShow14);
		 $("#l2").data('time-value',timeShow15);
		 $("#l1").data('time-value',timeShow16);
	
		 $("#l16").html(convertDateFormat(timeShow1,labelNum));
	     $("#l15").html(convertDateFormat(timeShow2,labelNum));
	     $("#l14").html(convertDateFormat(timeShow3,labelNum));
	     $("#l13").html(convertDateFormat(timeShow4,labelNum));
	     $("#l12").html(convertDateFormat(timeShow5,labelNum));
	     $("#l11").html(convertDateFormat(timeShow6,labelNum));
	     $("#l10").html(convertDateFormat(timeShow7,labelNum));
	     $("#l9").html(convertDateFormat(timeShow8,labelNum));
	     $("#l8").html(convertDateFormat(timeShow9,labelNum));
	     $("#l7").html(convertDateFormat(timeShow10,labelNum));
	     $("#l6").html(convertDateFormat(timeShow11,labelNum));
	     $("#l5").html(convertDateFormat(timeShow12,labelNum));
	     $("#l4").html(convertDateFormat(timeShow13,labelNum));
	     $("#l3").html(convertDateFormat(timeShow14,labelNum));
	     $("#l2").html(convertDateFormat(timeShow15,labelNum));
	     $("#l1").html(convertDateFormat(timeShow16,labelNum));
	 	  	 
	     addDayColor();
 	 }else{
		 $("#lw7").data('time-value',timeShow1);
		 $("#lw6").data('time-value',timeShow2);
		 $("#lw5").data('time-value',timeShow3);
		 $("#lw4").data('time-value',timeShow4);
		 $("#lw3").data('time-value',timeShow5);
		 $("#lw2").data('time-value',timeShow6);
		 $("#lw1").data('time-value',timeShow7);
	
		 $("#lw7").html(convertDateFormat(timeShow1,labelNum));
	     $("#lw6").html(convertDateFormat(timeShow2,labelNum));
	     $("#lw5").html(convertDateFormat(timeShow3,labelNum));
	     $("#lw4").html(convertDateFormat(timeShow4,labelNum));
	     $("#lw3").html(convertDateFormat(timeShow5,labelNum));
	     $("#lw2").html(convertDateFormat(timeShow6,labelNum));
	     $("#lw1").html(convertDateFormat(timeShow7,labelNum));	 	  	 
	     
	     addColor();
 	 }
 }
		
 /*点击右箭头时间向后推移*/
 function dayNext(){
 	 $(".uway-time-control").removeClass("time-line2").addClass("time-line1");
 	 var sGranularity=timeParams.timeSelected.selectGranularity;
 	 var sIndex=timeParams.timeSelected.selectIndex;
 	 var getTimeJson;
 	 var minusDate = 1000 * 60 * 60 * 24;
 	 var timeShow1,timeShow2,timeShow3,timeShow4;
 	 var labelNum=document.getElementById("cycle").innerHTML;
 	 if(labelNum==2){
 		var last=timeParams.lastTime.lastLabelW;
 		var time1=$("#lw7").data('time-value');
	 	 var time2=$("#lw6").data('time-value');
	 	 var time3=$("#lw5").data('time-value');
	 	 var time4=$("#lw4").data('time-value'); 
	 	 var time5=$("#lw3").data('time-value'); 
	 	 var time6=$("#lw2").data('time-value'); 
	 	 var time7=$("#lw1").data('time-value'); 
 		if(time7!=last){
	 		var timeWeek1={};
	 		var timeWeek2={};
	 		var timeWeek3={};
	 		var timeWeek4={};
	 		var timeWeek5={};
	 		var timeWeek6={};
	 		var timeWeek7={};
	 		timeWeek1=time1.split("—");
	 		timeWeek2=time2.split("—");
	 		timeWeek3=time3.split("—");
	 		timeWeek4=time4.split("—");
	 		timeWeek5=time5.split("—");
	 		timeWeek6=time6.split("—");
	 		timeWeek7=time7.split("—");
	 		
	 	
	 		var weekShow1={};
	 		weekShow1.cycle=2;		
	 		weekShow1.start=new Date(Date.parse(timeWeek1[0])+minusDate*7*6).toShortDateString("-");
	 		weekShow1.end=new Date(Date.parse(timeWeek1[1])+minusDate*7*6).toShortDateString("-");
	 		
	 		var weekShow2={};
	 		weekShow2.cycle=2;
	 		weekShow2.start=new Date(Date.parse(timeWeek2[0])+minusDate*7*6).toShortDateString("-");
	 		weekShow2.end=new Date(Date.parse(timeWeek2[1])+minusDate*7*6).toShortDateString("-");
	 		
	 		var weekShow3={};
	 		weekShow3.cycle=2;
	 		weekShow3.start=new Date(Date.parse(timeWeek3[0])+minusDate*7*6).toShortDateString("-");
	 		weekShow3.end=new Date(Date.parse(timeWeek3[1])+minusDate*7*6).toShortDateString("-");
	 		
	 		var weekShow4={};
	 		weekShow4.cycle=2;
	 		weekShow4.start=new Date(Date.parse(timeWeek4[0])+minusDate*7*6).toShortDateString("-");
	 		weekShow4.end=new Date(Date.parse(timeWeek4[1])+minusDate*7*6).toShortDateString("-");
	 		
	 		var weekShow5={};
	 		weekShow5.cycle=2;
	 		weekShow5.start=new Date(Date.parse(timeWeek5[0])+minusDate*7*6).toShortDateString("-");
	 		weekShow5.end=new Date(Date.parse(timeWeek5[1])+minusDate*7*6).toShortDateString("-");
	 		
	 		var weekShow6={};
	 		weekShow6.cycle=2;
	 		weekShow6.start=new Date(Date.parse(timeWeek6[0])+minusDate*7*6).toShortDateString("-");
	 		weekShow6.end=new Date(Date.parse(timeWeek6[1])+minusDate*7*6).toShortDateString("-");
	 		
	 		var weekShow7={};
	 		weekShow7.cycle=2;
	 		weekShow7.start=new Date(Date.parse(timeWeek7[0])+minusDate*7*6).toShortDateString("-");
	 		weekShow7.end=new Date(Date.parse(timeWeek7[1])+minusDate*7*6).toShortDateString("-");
	 		
	 	    weekShow=[];
	 	    weekShow.push(weekShow7);
	 	    weekShow.push(weekShow6);
	 	    weekShow.push(weekShow5);
	 		weekShow.push(weekShow4);
	 		weekShow.push(weekShow3);
	 		weekShow.push(weekShow2);
	 		weekShow.push(weekShow1);
	 		
	 		timeShow1=weekShow[6].start+"—"+weekShow[6].end;
	 		timeShow2=weekShow[5].start+"—"+weekShow[5].end;
	 		timeShow3=weekShow[4].start+"—"+weekShow[4].end;
	 		timeShow4=weekShow[3].start+"—"+weekShow[3].end; 
	 		timeShow5=weekShow[2].start+"—"+weekShow[2].end; 
	 		timeShow6=weekShow[1].start+"—"+weekShow[1].end; 
	 		timeShow7=weekShow[0].start+"—"+weekShow[0].end; 
	 		
	 		
	 		 $("#lw7").data('time-value',timeShow1);
 			 $("#lw6").data('time-value',timeShow2);
 			 $("#lw5").data('time-value',timeShow3);
 			 $("#lw4").data('time-value',timeShow4);
 			 $("#lw3").data('time-value',timeShow5);
 			 $("#lw2").data('time-value',timeShow6);
 			 $("#lw1").data('time-value',timeShow7);
 			
 		
 			 $("#lw7").html(convertDateFormat(timeShow1,labelNum));
 		     $("#lw6").html(convertDateFormat(timeShow2,labelNum));
 		     $("#lw5").html(convertDateFormat(timeShow3,labelNum));
 		     $("#lw4").html(convertDateFormat(timeShow4,labelNum));
 		     $("#lw3").html(convertDateFormat(timeShow5,labelNum));
 		     $("#lw2").html(convertDateFormat(timeShow6,labelNum));
 		     $("#lw1").html(convertDateFormat(timeShow7,labelNum)); 		 
 			
	 		
	 		
	 		  if(timeShow7==last && sGranularity!=labelNum){
					$("#timeW1").addClass("time-line2");				
			 }else{
				 addColor();
			 }
	 	}else{
	 		uwayWarning("提示","已经是最后日期啦！");
 			if(sGranularity!=labelNum){
				$("#timeW1").addClass("time-line2");				
 			}else{
 				addColor();
 			}
	 	}		
 	 
 	 }else if(labelNum==3){
 		var last=timeParams.lastTime.lastLabelM;
 		var time1=$("#lw7").data('time-value');
	 	 var time2=$("#lw6").data('time-value');
	 	 var time3=$("#lw5").data('time-value');
	 	 var time4=$("#lw4").data('time-value'); 
	 	 var time5=$("#lw3").data('time-value'); 
	 	 var time6=$("#lw2").data('time-value'); 
	 	 var time7=$("#lw1").data('time-value'); 
 		if(time7!=last){			 		
 		 var timeMonth6={};
 			timeMonth6=time6.split("—"); 					
 			var monthShow7={};
 			monthShow7.cycle=3;
 			var monthStart7=new Date(new Date(timeMonth6[1]).getTime()+minusDate*1);
 			var monthEnd7=getLastDayOfMonth(monthStart7);
 			monthShow7.start=monthStart7.toShortDateString("-");
 			monthShow7.end=monthEnd7.toShortDateString("-");
 			
 			var monthShow6={};
 			monthShow6.cycle=3;
 			var monthStart6=new Date(monthEnd7.getTime()+minusDate*1);
 			var monthEnd6=getLastDayOfMonth(monthStart6);
 			monthShow6.start=monthStart6.toShortDateString("-");
 			monthShow6.end=monthEnd6.toShortDateString("-");
 			
 			var monthShow5={};
 			monthShow5.cycle=3;
 			var monthStart5=new Date(monthEnd6.getTime()+minusDate*1);
 			var monthEnd5=getLastDayOfMonth(monthStart5);
 			monthShow5.start=monthStart5.toShortDateString("-");
 			monthShow5.end=monthEnd5.toShortDateString("-");
 			
 			
 			var monthShow4={};
 			monthShow4.cycle=3;
 			var monthStart4=new Date(monthEnd5.getTime()+minusDate*1);
 			var monthEnd4=getLastDayOfMonth(monthStart4);
 			monthShow4.start=monthStart4.toShortDateString("-");
 			monthShow4.end=monthEnd4.toShortDateString("-");
 			
 			var monthShow3={};
 			monthShow3.cycle=3;
 			var monthStart3=new Date(monthEnd4.getTime()+minusDate*1);
 			var monthEnd3=getLastDayOfMonth(monthStart3);
 			monthShow3.start=monthStart3.toShortDateString("-");
 			monthShow3.end=monthEnd3.toShortDateString("-");
 			
 			var monthShow2={};
 			monthShow2.cycle=3;
 			var monthStart2=new Date(monthEnd3.getTime()+minusDate*1);
 			var monthEnd2=getLastDayOfMonth(monthStart2);
 			monthShow2.start=monthStart2.toShortDateString("-");
 			monthShow2.end=monthEnd2.toShortDateString("-");
 			
 			var monthShow1={};
 			monthShow1.cycle=3;
 			var monthStart1=new Date(monthEnd2.getTime()+minusDate*1);
 			var monthEnd1=getLastDayOfMonth(monthStart1);
 			monthShow1.start=monthStart1.toShortDateString("-");
 			monthShow1.end=monthEnd1.toShortDateString("-");
 			
 			monthShow=[];
 			monthShow.push(monthShow7);
 			monthShow.push(monthShow6);
 			monthShow.push(monthShow5);
 			monthShow.push(monthShow4);
 			monthShow.push(monthShow3);
 			monthShow.push(monthShow2);
 			monthShow.push(monthShow1);
 			
 			timeShow1=monthShow[0].start+"—"+monthShow[0].end;
 			timeShow2=monthShow[1].start+"—"+monthShow[1].end;
 			timeShow3=monthShow[2].start+"—"+monthShow[2].end;
 			timeShow4=monthShow[3].start+"—"+monthShow[3].end;
 			timeShow5=monthShow[4].start+"—"+monthShow[4].end;
 			timeShow6=monthShow[5].start+"—"+monthShow[5].end;
 			timeShow7=monthShow[6].start+"—"+monthShow[6].end;
 			

	 		 $("#lw7").data('time-value',timeShow1);
			 $("#lw6").data('time-value',timeShow2);
			 $("#lw5").data('time-value',timeShow3);
			 $("#lw4").data('time-value',timeShow4);
			 $("#lw3").data('time-value',timeShow5);
			 $("#lw2").data('time-value',timeShow6);
			 $("#lw1").data('time-value',timeShow7);
			
		
			 $("#lw7").html(convertDateFormat(timeShow1,labelNum));
		     $("#lw6").html(convertDateFormat(timeShow2,labelNum));
		     $("#lw5").html(convertDateFormat(timeShow3,labelNum));
		     $("#lw4").html(convertDateFormat(timeShow4,labelNum));
		     $("#lw3").html(convertDateFormat(timeShow5,labelNum));
		     $("#lw2").html(convertDateFormat(timeShow6,labelNum));
		     $("#lw1").html(convertDateFormat(timeShow7,labelNum)); 	
 			
 			  if(timeShow7==last && sGranularity!=labelNum){
 					$("#timeW1").addClass("time-line2");				
 			 }else{
 				 addColor();
 			 }
 		}else{
 			uwayWarning("提示","已经是最后日期啦！");
 			if(sGranularity!=labelNum){
				$("#timeW1").addClass("time-line2");				
 			}else{
 				addColor();
 			}
 		}
 	 }else{		
 		var last=timeParams.lastTime.lastLabel;
 		var time1=$("#l16").data('time-value');
	 	 var time2=$("#l15").data('time-value');
	 	 var time3=$("#l14").data('time-value');
	 	 var time4=$("#l13").data('time-value'); 
	 	 var time5=$("#l12").data('time-value'); 
	 	 var time6=$("#l11").data('time-value'); 
	 	 var time7=$("#l10").data('time-value'); 
	 	 var time8=$("#l9").data('time-value'); 
	 	 var time9=$("#l8").data('time-value'); 
	 	 var time10=$("#l7").data('time-value'); 
	 	 var time11=$("#l6").data('time-value'); 
	 	 var time12=$("#l5").data('time-value'); 
	 	 var time13=$("#l4").data('time-value'); 
	 	 var time14=$("#l3").data('time-value'); 
	 	 var time15=$("#l2").data('time-value'); 
	 	 var time16=$("#l1").data('time-value'); 
 		if(time16!=last){ 				 		 
 	 		var date1= Date.parse(time1);
 			 var date2= Date.parse(time2);
 			 var date3= Date.parse(time3);
 			 var date4= Date.parse(time4);
 			 var date5= Date.parse(time5);
 			 var date6= Date.parse(time6);
 			 var date7= Date.parse(time7);
 			 var date8= Date.parse(time8);
 			 var date9= Date.parse(time9);
 			 var date10= Date.parse(time10);
 			 var date11= Date.parse(time11);
 			 var date12= Date.parse(time12);
 			 var date13= Date.parse(time13);
 			 var date14= Date.parse(time14);
 			 var date15= Date.parse(time15);
 			 var date16= Date.parse(time16);
 			 var dayShow1={};	
 			 dayShow1.cycle=1;
 			 dayShow1.time=new Date(date1+minusDate*15).toShortDateString("-");
 			 var dayShow2={};
 			 dayShow2.cycle=1;
 			 dayShow2.time=new Date(date2+minusDate*15).toShortDateString("-");
 		     var dayShow3={};
 		     dayShow3.cycle=1;
 			 dayShow3.time=new Date(date3+minusDate*15).toShortDateString("-");
 			 var dayShow4={};
 			 dayShow4.cycle=1;
 			 dayShow4.time=new Date(date4+minusDate*15).toShortDateString("-");
 			 var dayShow5={};
 			 dayShow5.cycle=1;
 			 dayShow5.time=new Date(date5+minusDate*15).toShortDateString("-");
 			 var dayShow6={};
 			 dayShow6.cycle=1;
 			 dayShow6.time=new Date(date6+minusDate*15).toShortDateString("-");
 			 var dayShow7={};
 			 dayShow7.cycle=1;
 			 dayShow7.time=new Date(date7+minusDate*15).toShortDateString("-");
 			 var dayShow8={};
 			 dayShow8.cycle=1;
 			 dayShow8.time=new Date(date8+minusDate*15).toShortDateString("-");
 			 var dayShow9={};
 			 dayShow9.cycle=1;
 			 dayShow9.time=new Date(date9+minusDate*15).toShortDateString("-");
 			 var dayShow10={};
 			 dayShow10.cycle=1;
 			 dayShow10.time=new Date(date10+minusDate*15).toShortDateString("-");
 			 var dayShow11={};
 			 dayShow11.cycle=1;
 			 dayShow11.time=new Date(date11+minusDate*15).toShortDateString("-");
 			 var dayShow12={};
 			 dayShow12.cycle=1;
 			 dayShow12.time=new Date(date12+minusDate*15).toShortDateString("-");
 			 var dayShow13={};
 			 dayShow13.cycle=1;
 			 dayShow13.time=new Date(date13+minusDate*15).toShortDateString("-");
 			 var dayShow14={};
 			 dayShow14.cycle=1;
 			 dayShow14.time=new Date(date14+minusDate*15).toShortDateString("-");
 			 var dayShow15={};
 			 dayShow15.cycle=1;
 			 dayShow15.time=new Date(date15+minusDate*15).toShortDateString("-");
 			 var dayShow16={};
 			 dayShow16.cycle=1;
 			 dayShow16.time=new Date(date16+minusDate*15).toShortDateString("-");

 			 dayShow=[];
 			 dayShow.push(dayShow16);
 			 dayShow.push(dayShow15);
 			 dayShow.push(dayShow14);
 			 dayShow.push(dayShow13);
 			 dayShow.push(dayShow12);
 			 dayShow.push(dayShow11);
 			 dayShow.push(dayShow10);
 			 dayShow.push(dayShow9);
 			 dayShow.push(dayShow8);
 			 dayShow.push(dayShow7);
 			 dayShow.push(dayShow6);
 			 dayShow.push(dayShow5);
 			 dayShow.push(dayShow4);
 			 dayShow.push(dayShow3);
 			 dayShow.push(dayShow2);
 			 dayShow.push(dayShow1);
 			timeShow1=dayShow[15].time;
 			timeShow2=dayShow[14].time;	
 			timeShow3=dayShow[13].time;	
 			timeShow4=dayShow[12].time;
 			timeShow5=dayShow[11].time;
 			timeShow6=dayShow[10].time;
 			timeShow7=dayShow[9].time;
 			timeShow8=dayShow[8].time;
 			timeShow9=dayShow[7].time;
 			timeShow10=dayShow[6].time;
 			timeShow11=dayShow[5].time;
 			timeShow12=dayShow[4].time;
 			timeShow13=dayShow[3].time;
 			timeShow14=dayShow[2].time;
 			timeShow15=dayShow[1].time;
 			timeShow16=dayShow[0].time;
 			
 			
 			 $("#l16").data('time-value',timeShow1);
 			 $("#l15").data('time-value',timeShow2);
 			 $("#l14").data('time-value',timeShow3);
 			 $("#l13").data('time-value',timeShow4);
 			 $("#l12").data('time-value',timeShow5);
 			 $("#l11").data('time-value',timeShow6);
 			 $("#l10").data('time-value',timeShow7);
 			 $("#l9").data('time-value',timeShow8);
 			 $("#l8").data('time-value',timeShow9);
 			 $("#l7").data('time-value',timeShow10);
 			 $("#l6").data('time-value',timeShow11);
 			 $("#l5").data('time-value',timeShow12);
 			 $("#l4").data('time-value',timeShow13);
 			 $("#l3").data('time-value',timeShow14);
 			 $("#l2").data('time-value',timeShow15);
 			 $("#l1").data('time-value',timeShow16);
 		
 			 $("#l16").html(convertDateFormat(timeShow1,labelNum));
 		     $("#l15").html(convertDateFormat(timeShow2,labelNum));
 		     $("#l14").html(convertDateFormat(timeShow3,labelNum));
 		     $("#l13").html(convertDateFormat(timeShow4,labelNum));
 		     $("#l12").html(convertDateFormat(timeShow5,labelNum));
 		     $("#l11").html(convertDateFormat(timeShow6,labelNum));
 		     $("#l10").html(convertDateFormat(timeShow7,labelNum));
 		     $("#l9").html(convertDateFormat(timeShow8,labelNum));
 		     $("#l8").html(convertDateFormat(timeShow9,labelNum));
 		     $("#l7").html(convertDateFormat(timeShow10,labelNum));
 		     $("#l6").html(convertDateFormat(timeShow11,labelNum));
 		     $("#l5").html(convertDateFormat(timeShow12,labelNum));
 		     $("#l4").html(convertDateFormat(timeShow13,labelNum));
 		     $("#l3").html(convertDateFormat(timeShow14,labelNum));
 		     $("#l2").html(convertDateFormat(timeShow15,labelNum));
 		     $("#l1").html(convertDateFormat(timeShow16,labelNum));
 			
	 		
	 		  if(timeShow16==last && sGranularity!=labelNum){
					$("#time1").addClass("time-line2");				
			 }else{
				 addDayColor();
			 }
 		}else{
 			uwayWarning("提示","已经是最后日期啦！");
 			if(sGranularity!=labelNum){
				$("#time4").addClass("time-line2");				
 			}else{
 				addDayColor();
 			}
 		}
 	 }
 	 
 }
 
 
function addColor(){
	 var i;
	 var labelS;
	 var labelNum=document.getElementById("cycle").innerHTML;
	 var sGranularity=timeParams.timeSelected.selectGranularity;
	 $(".uway-time-control").removeClass("time-line2").addClass("time-line1");
	if(labelNum==sGranularity){
		var lw1= $("#lw1").data('time-value');
		var lw2=$("#lw2").data('time-value');
		var lw3= $("#lw3").data('time-value');
		var lw4=$("#lw4").data('time-value');
		var lw5=$("#lw5").data('time-value');
		var lw6=$("#lw6").data('time-value');
		var lw7=$("#lw7").data('time-value');
		var list=[lw1,lw2,lw3,lw4,lw5,lw6,lw7];
		for(i=0;i<7;i++){
			if(list[i]== timeParams.timeSelected.labelSelected){
				labelS=i;
				break;
			}
		}
		if(labelS==6){
			$("#timeW7").addClass("time-line2");
		}else if(labelS==5){
			$("#timeW6").addClass("time-line2");
		}else if(labelS==4){
			$("#timeW5").addClass("time-line2");
		}else if(labelS==3){
			$("#timeW4").addClass("time-line2");
		}else if(labelS==2){
			$("#timeW3").addClass("time-line2");
		}else if(labelS==1){
			$("#timeW2").addClass("time-line2");
		}else if(labelS==0){
			$("#timeW1").addClass("time-line2");
		}
	}
}
 		
function addDayColor(){
	 var i;
	 var labelS;
	 var labelNum=document.getElementById("cycle").innerHTML;
	 var sGranularity=timeParams.timeSelected.selectGranularity;
	 $(".uway-time-control").removeClass("time-line2").addClass("time-line1");
	if(labelNum==sGranularity){
		var l1= $("#l1").data('time-value');
		var l2=$("#l2").data('time-value');
		var l3= $("#l3").data('time-value');
		var l4=$("#l4").data('time-value');
		var l5=$("#l5").data('time-value');
		var l6=$("#l6").data('time-value');
		var l7=$("#l7").data('time-value');
		var l8=$("#l8").data('time-value');
		var l9=$("#l9").data('time-value');
		var l10=$("#l10").data('time-value');
		var l11=$("#l11").data('time-value');
		var l12=$("#l12").data('time-value');
		var l13=$("#l13").data('time-value');
		var l14=$("#l14").data('time-value');
		var l15=$("#l15").data('time-value');
		var l16=$("#l16").data('time-value');
		var list=[l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15,l16];
		for(i=0;i<16;i++){
			if(list[i]== timeParams.timeSelected.labelSelected){
				labelS=i;
				break;
			}
		}
		if(labelS==15){
			$("#time16").addClass("time-line2");
		}else if(labelS==14){
			$("#time15").addClass("time-line2");
		}else if(labelS==13){
			$("#time14").addClass("time-line2");
		}else if(labelS==12){
			$("#time13").addClass("time-line2");
		}else if(labelS==11){
			$("#time12").addClass("time-line2");
		}else if(labelS==10){
			$("#time11").addClass("time-line2");
		}else if(labelS==9){
			$("#time10").addClass("time-line2");
		}else if(labelS==8){
			$("#time9").addClass("time-line2");
		}else if(labelS==7){
			$("#time8").addClass("time-line2");
		}else if(labelS==6){
			$("#time7").addClass("time-line2");
		}else if(labelS==5){
			$("#time6").addClass("time-line2");
		}else if(labelS==4){
			$("#time5").addClass("time-line2");
		}else if(labelS==3){
			$("#time4").addClass("time-line2");
		}else if(labelS==2){
			$("#time3").addClass("time-line2");
		}else if(labelS==1){
			$("#time2").addClass("time-line2");
		}else if(labelS==0){
			$("#time1").addClass("time-line2");
		}
	}
}
		



/*点击向上箭头的事件*/
function timeUp(){
		
	$("#default").removeClass("uway-time-hidden");
	$("#timeContrast").addClass("uway-time-hidden");
	var timer=null;
	var alpha=100;
	var alpha1=0;
	var oDiv=document.getElementById("default");
	var oDiv1=document.getElementById("timeContrast");
	oDiv1.style.filter='alpha(opacyiy:'+alpha1+')';
	oDiv1.style.opacity=alpha1/100;
	oDiv.style.filter='alpha(opacyiy:'+alpha+')';
	oDiv.style.opacity=alpha/100;	
		
}


function dayList(){
	 var timeDay=fourDays();
		var dayL={};
		var i;
		for(i=0;i<timeDay.length;i++){
			dayL[timeDay.length-i-1]=timeDay[i].time;
		}
	return dayL;
}

function colorChange(element,num){
	 $(".uway-time-control").removeClass("time-line2").addClass("time-line1");
	 $(element).find('div').addClass("time-line2");
	 
	 if($('#cycle').text()==1){
	 label1=$("#l16").data('time-value');
		label2=$("#l15").data('time-value');
		label3=$("#l14").data('time-value');
		label4=$("#l13").data('time-value');
		label5=$("#l12").data('time-value');	
		label6=$("#l11").data('time-value');	
		label7=$("#l10").data('time-value');	
		label8=$("#l9").data('time-value');	
		label9=$("#l8").data('time-value');
		label10=$("#l7").data('time-value');	
		label11=$("#l6").data('time-value');	
		label12=$("#l5").data('time-value');	
		label13=$("#l4").data('time-value');	
		label14=$("#l3").data('time-value');	
		label15=$("#l2").data('time-value');	
		label16=$("#l1").data('time-value');	
		
	 timeParams.timeSelected.labelList=[label1,label2,label3,label4,label5,label6,label7,label8,label9,label10,label11,label12,label13,label14,label15,label16];
	 }else{
		 label1=$("#lw7").data('time-value');
		 label2=$("#lw6").data('time-value');
		 label3=$("#lw5").data('time-value');
		 label4=$("#lw4").data('time-value');
		 label5=$("#lw3").data('time-value');	
		 label6=$("#lw2").data('time-value');	
		 label7=$("#lw1").data('time-value');
		 
			
		 timeParams.timeSelected.labelList=[label1,label2,label3,label4,label5,label6,label7];
	 }
	 
	 timeParams.timeSelected.selectGranularity=$("#cycle").text();
	 timeParams.timeSelected.labelSelected=timeParams.timeSelected.labelList[num];
	 $("#showTime").html(timeParams.timeSelected.labelList[num]);	
	  $("#timeSelected").html( $("#showTime").text());	
	 $("#labelNum").html(num);
	 
	 timeParams.timeSelected.selectIndex=num;
	 //保存全局变量 
	 window.localStorage.setItem("UWAY_TIME_DATA",JSON.stringify(timeParams));
	 
	$("#default").removeClass("uway-time-hidden");
	 $("#timeContrast").addClass("uway-time-hidden");
	var timer=null;
	var alpha=100;
	var alpha1=0;
	var oDiv=document.getElementById("default");
	var oDiv1=document.getElementById("timeContrast");
	oDiv1.style.filter='alpha(opacyiy:'+alpha1+')';
	oDiv1.style.opacity=alpha1/100;
	oDiv.style.filter='alpha(opacyiy:'+alpha+')';
	oDiv.style.opacity=alpha/100;
	refreshChartAndTable();
 };

/* 扇形点击事件，选择周期月/周 ，变更颜色,根据选择的周期类别改变时间轴上显示的时间段*/
 function timeCategaryColorChange(element){	
	 var value= $(element).text();	
	 $(".uway-time-control").removeClass("time-line2").addClass("time-line1");
	 var sGranularity=timeParams.timeSelected.selectGranularity;
	 var sIndex=timeParams.timeSelected.selectIndex;
	 var str;
	 if(value=="月"){
		    $(".rowntW").removeClass('uway-time-hidden');
		    $(".rownt").addClass('uway-time-hidden');
		     $("#day").css("background-color"," #40456F");
			 $("#dayS").css("background-color","#40456F");
			 $("#week").css("background-color","#40456F");
			 $("#month").css("background-color","#F9A93D");
			 document.getElementById("cycle").innerHTML=3;
			 timeParams.lastTime.lastLabel=monthDate()[0].start+"—"+monthDate()[0].end;			
	 }else if(value=="周"){
		 $(".rowntW").removeClass('uway-time-hidden');
		    $(".rownt").addClass('uway-time-hidden');
		     $("#day").css("background-color"," #40456F");
			 $("#dayS").css("background-color"," #40456F");
			 $("#week").css("background-color","#F9A93D");
			 $("#month").css("background-color","#40456F");
			document.getElementById("cycle").innerHTML=2;
			 timeParams.lastTime.lastLabel=weekDate()[0].start+"—"+weekDate()[0].end;
	 }else{
		 $(".rowntW").addClass('uway-time-hidden');
		 $(".rownt").removeClass('uway-time-hidden');
		 $("#day").css("background-color"," #F9A93D");
		 $("#dayS").css("background-color"," #F9A93D");
		 $("#week").css("background-color","#40456F");
		 $("#month").css("background-color","#40456F");
		document.getElementById("cycle").innerHTML=1;
		timeParams.lastTime.lastLabel=fourDays()[0].time;
 }
	 var Granularity=document.getElementById("cycle").innerHTML;
	 var list=timeParams.timeSelected.labelList;
	 var sIndex=timeParams.timeSelected.selectIndex;
	  
	 if( timeParams.timeSelected.selectGranularity==Granularity){
		 $("#timeSelected").html( $("#showTime").text());	
		if(Granularity==1){
			 $("#l16").data('time-value',list[0]);
			 $("#l15").data('time-value',list[1]);
			 $("#l14").data('time-value',list[2]);
			 $("#l13").data('time-value',list[3]);
			 $("#l12").data('time-value',list[4]);
			 $("#l11").data('time-value',list[5]);
			 $("#l10").data('time-value',list[6]);
			 $("#l9").data('time-value',list[7]);
			 $("#l8").data('time-value',list[8]);
			 $("#l7").data('time-value',list[9]);
			 $("#l6").data('time-value',list[10]);
			 $("#l5").data('time-value',list[11]);
			 $("#l4").data('time-value',list[12]);
			 $("#l3").data('time-value',list[13]);
			 $("#l2").data('time-value',list[14]);
			 $("#l1").data('time-value',list[15]);
			 
			 $("#cycle").html(timeGran);
			 $("#l16").html(convertDateFormat(list[0],Granularity));
		     $("#l15").html(convertDateFormat(list[1],Granularity));
		     $("#l14").html(convertDateFormat(list[2],Granularity));
		     $("#l13").html(convertDateFormat(list[3],Granularity));
		     $("#l12").html(convertDateFormat(list[4],Granularity));
		     $("#l11").html(convertDateFormat(list[5],Granularity));
		     $("#l10").html(convertDateFormat(list[6],Granularity));
		     $("#l9").html(convertDateFormat(list[7],Granularity));
		     $("#l8").html(convertDateFormat(list[8],Granularity));
		     $("#l7").html(convertDateFormat(list[9],Granularity));
		     $("#l6").html(convertDateFormat(list[10],Granularity));
		     $("#l5").html(convertDateFormat(list[11],Granularity));
		     $("#l4").html(convertDateFormat(list[12],Granularity));
		     $("#l3").html(convertDateFormat(list[13],Granularity));
		     $("#l2").html(convertDateFormat(list[14],Granularity));
		     $("#l1").html(convertDateFormat(list[15],Granularity));
		     if(sIndex==0){
					$("#time16").addClass("time-line2");
				}else if(sIndex==1){
					$("#time15").addClass("time-line2");
				}else if(sIndex==2){
					$("#time14").addClass("time-line2");
				}else if(sIndex==3){
					$("#time13").addClass("time-line2");
				}else if(sIndex==4){
					$("#time12").addClass("time-line2");
				}else if(sIndex==5){
					$("#time11").addClass("time-line2");
				}else if(sIndex==6){
					$("#time10").addClass("time-line2");
				}else if(sIndex==7){
					$("#time9").addClass("time-line2");
				}else if(sIndex==8){
					$("#time8").addClass("time-line2");
				}else if(sIndex==9){
					$("#time7").addClass("time-line2");
				}else if(sIndex==10){
					$("#time6").addClass("time-line2");
				}else if(sIndex==11){
					$("#time5").addClass("time-line2");
				}else if(sIndex==12){
					$("#time4").addClass("time-line2");
				}else if(sIndex==13){
					$("#time3").addClass("time-line2");
				}else if(sIndex==14){
					$("#time2").addClass("time-line2");
				}else{
					$("#time1").addClass("time-line2");
				}										
		}else{
			 $("#lw7").data('time-value',list[0]);
			 $("#lw6").data('time-value',list[1]);
			 $("#lw5").data('time-value',list[2]);
			 $("#lw4").data('time-value',list[3]);
			 $("#lw3").data('time-value',list[4]);
			 $("#lw2").data('time-value',list[5]);
			 $("#lw1").data('time-value',list[6]);
			 		 
			 $("#lw7").html(convertDateFormat(list[0],Granularity));
		     $("#lw6").html(convertDateFormat(list[1],Granularity));
		     $("#lw5").html(convertDateFormat(list[2],Granularity));
		     $("#lw4").html(convertDateFormat(list[3],Granularity));
		     $("#lw3").html(convertDateFormat(list[4],Granularity));
		     $("#lw2").html(convertDateFormat(list[5],Granularity));
		     $("#lw1").html(convertDateFormat(list[6],Granularity));
		     
		     if(sIndex==0){
					$("#timeW7").addClass("time-line2");
				}else if(sIndex==1){
					$("#timeW6").addClass("time-line2");
				}else if(sIndex==2){
					$("#timeW5").addClass("time-line2");
				}else if(sIndex==3){
					$("#timeW4").addClass("time-line2");
				}else if(sIndex==4){
					$("#timeW3").addClass("time-line2");
				}else if(sIndex==5){
					$("#timeW2").addClass("time-line2");
				}else{
					$("#timeW1").addClass("time-line2");
				}
		}
		 
		
	 }else{
		 if(Granularity==3){
			 var timeMonth=monthDate();
			 	
			 $("#lw7").data('time-value',timeMonth[6].start+"—"+timeMonth[6].end);
			 $("#lw6").data('time-value',timeMonth[5].start+"—"+timeMonth[5].end);
			 $("#lw5").data('time-value',timeMonth[4].start+"—"+timeMonth[4].end);
			 $("#lw4").data('time-value',timeMonth[3].start+"—"+timeMonth[3].end);
			 $("#lw3").data('time-value',timeMonth[2].start+"—"+timeMonth[2].end);
			 $("#lw2").data('time-value',timeMonth[1].start+"—"+timeMonth[1].end);
			 $("#lw1").data('time-value',timeMonth[0].start+"—"+timeMonth[0].end);
			 			 				 		 
			 $("#lw7").html(convertDateFormat(timeMonth[6].start+"—"+timeMonth[6].end,Granularity));
			 $("#lw6").html(convertDateFormat(timeMonth[5].start+"—"+timeMonth[5].end,Granularity));
			 $("#lw5").html(convertDateFormat(timeMonth[4].start+"—"+timeMonth[4].end,Granularity));
			 $("#lw4").html(convertDateFormat(timeMonth[3].start+"—"+timeMonth[3].end,Granularity));
			 $("#lw3").html(convertDateFormat(timeMonth[2].start+"—"+timeMonth[2].end,Granularity));
			 $("#lw2").html(convertDateFormat(timeMonth[1].start+"—"+timeMonth[1].end,Granularity));
			 $("#lw1").html(convertDateFormat(timeMonth[0].start+"—"+timeMonth[0].end,Granularity));
			 $("#cycle").html(timeMonth[0].cycle);
			 $("#timeW1").addClass("time-line2");
			 $("#timeSelected").html( $("#lw1").data('time-value'));	
				
		 }else if(Granularity==2){
			 var timeWeek=weekDate();
			 
				
			 $("#lw7").data('time-value',timeWeek[6].start+"—"+timeWeek[6].end);
			 $("#lw6").data('time-value',timeWeek[5].start+"—"+timeWeek[5].end);
			 $("#lw5").data('time-value',timeWeek[4].start+"—"+timeWeek[4].end);
			 $("#lw4").data('time-value',timeWeek[3].start+"—"+timeWeek[3].end);
			 $("#lw3").data('time-value',timeWeek[2].start+"—"+timeWeek[2].end);
			 $("#lw2").data('time-value',timeWeek[1].start+"—"+timeWeek[1].end);
			 $("#lw1").data('time-value',timeWeek[0].start+"—"+timeWeek[0].end);
			 
			 
			 
			 $("#lw7").html(convertDateFormat(timeWeek[6].start+"—"+timeWeek[6].end,Granularity));
			 $("#lw6").html(convertDateFormat(timeWeek[5].start+"—"+timeWeek[5].end,Granularity));
			 $("#lw5").html(convertDateFormat(timeWeek[4].start+"—"+timeWeek[4].end,Granularity));
			 $("#lw4").html(convertDateFormat(timeWeek[3].start+"—"+timeWeek[3].end,Granularity));
			 $("#lw3").html(convertDateFormat(timeWeek[2].start+"—"+timeWeek[2].end,Granularity));
			 $("#lw2").html(convertDateFormat(timeWeek[1].start+"—"+timeWeek[1].end,Granularity));
			 $("#lw1").html(convertDateFormat(timeWeek[0].start+"—"+timeWeek[0].end,Granularity));
			 $("#cycle").html(timeWeek[0].cycle);
			 $("#timeW1").addClass("time-line2");
			 $("#timeSelected").html( $("#lw1").data('time-value'));
		 }else{
			 var fourDay=fourDays()	;				 
				
			 $("#l16").data('time-value',fourDay[15].time);
			 $("#l15").data('time-value',fourDay[14].time);
			 $("#l14").data('time-value',fourDay[13].time);
			 $("#l13").data('time-value',fourDay[12].time);
			 $("#l12").data('time-value',fourDay[11].time);
			 $("#l11").data('time-value',fourDay[10].time);
			 $("#l10").data('time-value',fourDay[9].time);
			 $("#l9").data('time-value',fourDay[8].time);
			 $("#l8").data('time-value',fourDay[7].time);
			 $("#l7").data('time-value',fourDay[6].time);
			 $("#l6").data('time-value',fourDay[5].time);
			 $("#l5").data('time-value',fourDay[4].time);
			 $("#l4").data('time-value',fourDay[3].time);
			 $("#l3").data('time-value',fourDay[2].time);
			 $("#l2").data('time-value',fourDay[1].time);
			 $("#l1").data('time-value',fourDay[0].time);
			 			 
			 
			 $("#l16").html(convertDateFormat(fourDay[15].time,Granularity));
			 $("#l15").html(convertDateFormat(fourDay[14].time,Granularity));
			 $("#l14").html(convertDateFormat(fourDay[13].time,Granularity));
			 $("#l13").html(convertDateFormat(fourDay[12].time,Granularity));
			 $("#l12").html(convertDateFormat(fourDay[11].time,Granularity));
			 $("#l11").html(convertDateFormat(fourDay[10].time,Granularity));
			 $("#l10").html(convertDateFormat(fourDay[9].time,Granularity));
			 $("#l9").html(convertDateFormat(fourDay[8].time,Granularity));
			 $("#l8").html(convertDateFormat(fourDay[7].time,Granularity));
			 $("#l7").html(convertDateFormat(fourDay[6].time,Granularity));
			 $("#l6").html(convertDateFormat(fourDay[5].time,Granularity));
			 $("#l5").html(convertDateFormat(fourDay[4].time,Granularity));
			 $("#l4").html(convertDateFormat(fourDay[3].time,Granularity));
			 $("#l3").html(convertDateFormat(fourDay[2].time,Granularity));
			 $("#l2").html(convertDateFormat(fourDay[1].time,Granularity));
			 $("#l1").html(convertDateFormat(fourDay[0].time,Granularity));			 
			 
			 $("#cycle").html(fourDay[0].cycle);	
			 $("#time1").addClass("time-line2");
			 $("#timeSelected").html( $("#l1").data('time-value'));
			
	   }
		
	 }		
};	
/*获取json数组长度*/
function getJsonLength(jsonData){
 	var jsonLength = 0;
 	for(var item in jsonData){
 		jsonLength++; 
 	}
 	return jsonLength;
 }


/*
 * 点击时间控件选择时间段，返回list对象time：{时间粒度，起始时间，结束时间}
 */

function getTimePeriod() {
	// var labelNum=document.getElementById("cycle").innerHTML;
	// var labelShowTime = document.getElementById("showTime").innerHTML;
	// var time = {};
	// if(labelNum==1){
	// 	time.timeGran=labelNum;
	// 	time.startTime=labelShowTime;
	// 	time.endTime=labelShowTime;
	// }else{
	// var getTime={};
	// getTime=labelShowTime.split("—");
	// time.timeGran = labelNum;
	// time.startTime =getTime[0];
	// time.endTime = getTime[1];
	// }

    var defaultTimeParams = {};
    defaultTimeParams.timeGran=1;
    defaultTimeParams.startTime=new Date().toShortDateString('-');
    defaultTimeParams.endTime=new Date().toShortDateString('-');

    var time = {};
    var tmpTimeParams = window.localStorage.getItem("TIME_PARAMS")?JSON.parse(window.localStorage.getItem("TIME_PARAMS")):defaultTimeParams;
    time.timeGran=tmpTimeParams.timeGran;
    time.startTime=tmpTimeParams.startTime;
    time.endTime=tmpTimeParams.endTime;

    return time;
}


$(document).click(function(e){
	  if ($(e.target).parents("[id='timeContrast']").length > 0 || e.target.id == 'dayLast' || e.target.id == 'dayNext' || e.target.id == 'timeContrast') {
		    
      }	else{
    	    $("#default").removeClass("uway-time-hidden");
			$("#timeContrast").addClass("uway-time-hidden");
			var timer=null;
			var alpha=100;
			var alpha1=0;
			var oDiv=document.getElementById("default");
			var oDiv1=document.getElementById("timeContrast");
			oDiv1.style.filter='alpha(opacyiy:'+alpha1+')';
			oDiv1.style.opacity=alpha1/100;
			oDiv.style.filter='alpha(opacyiy:'+alpha+')';
			oDiv.style.opacity=alpha/100;	
      }
	  	
});
