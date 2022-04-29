/*
 * description：   常用日期时间操作方法
 * create date:  2016-03-18             
 *    email   :  weip@uway.cn;
 * 
 * modify man :
 * modify date:
 * description： 
 * 
 */

/**
 * 将时间类型转化为字符串
 * 参数 eg: 'yyyy-MM-dd hh:mm:ss'
 * 天 和 分 区分大小写  MM   mm
 */
Date.prototype.Format = function (formatStr) {
	    var str = formatStr;
	    var Week = ['日', '一', '二', '三', '四', '五', '六'];
	
	    str = str.replace(/yyyy|YYYY/, this.getFullYear());
	    str = str.replace(/yy|YY/, (this.getYear() % 100) > 9 ? (this.getYear() % 100).toString() : '0' + (this.getYear() % 100));
	    var month = this.getMonth() + 1;
	    str = str.replace(/MM/, month > 9 ? month.toString() : '0' + month);
	    str = str.replace(/M/g, month);
	
	    str = str.replace(/w|W/g, Week[this.getDay()]);
	
	    str = str.replace(/dd|DD/, this.getDate() > 9 ? this.getDate().toString() : '0' + this.getDate());
	    str = str.replace(/d|D/g, this.getDate());
	
	    str = str.replace(/hh|HH/, this.getHours() > 9 ? this.getHours().toString() : '0' + this.getHours());
	    str = str.replace(/h|H/g, this.getHours());
	    str = str.replace(/mm/, this.getMinutes() > 9 ? this.getMinutes().toString() : '0' + this.getMinutes());
	    str = str.replace(/m/g, this.getMinutes());
	
	    str = str.replace(/ss|SS/, this.getSeconds() > 9 ? this.getSeconds().toString() : '0' + this.getSeconds());
	    str = str.replace(/s|S/g, this.getSeconds());
	    return str;
}

/*
*    将时间类型转化为  短字符串，separator为分隔符符号；
*    参数 ： null  或者   字符串
*    结果： 19870423 123658   或者     1987-04-23 12：36：58
*/

Date.prototype.toString=function(separator){ 
			var xYear=this.getYear(); 
			xYear=xYear+1900; 
			
			var xMonth=this.getMonth()+1; 
			if(xMonth<10){ 
			xMonth="0"+xMonth; 
			} 
			
			var xDay=this.getDate(); 
			if(xDay<10){ 
			xDay="0"+xDay; 
			} 
			
			var xHours=this.getHours(); 
			if(xHours<10){ 
			xHours="0"+xHours; 
			} 
			
			var xMinutes=this.getMinutes(); 
			if(xMinutes<10){ 
			xMinutes="0"+xMinutes; 
			} 
			
			var xSeconds=this.getSeconds(); 
			if(xSeconds<10){ 
			xSeconds="0"+xSeconds; 
			} 
			
			var returnString;
			if(separator){
			returnString = xYear+separator+xMonth+separator+xDay+" "+xHours+":"+xMinutes+":"+xSeconds; 
			}else{
			returnString = xYear+xMonth+xDay+" "+xHours+xMinutes+xSeconds; 
			}
			return returnString;
}

/*
*    将时间类型转化为  短字符串，separator为分隔符符号；
*    参数 ： null  或者   字符串
*    结果： 19870423 120000   或者     1987-04-23 12：00：00
       */
Date.prototype.toHourString=function(separator){ 
			var xYear=this.getYear(); 
			xYear=xYear+1900; 
			
			var xMonth=this.getMonth()+1; 
			if(xMonth<10){ 
			xMonth="0"+xMonth; 
			} 
			
			var xDay=this.getDate(); 
			if(xDay<10){ 
			xDay="0"+xDay; 
			} 
			
			var xHours=this.getHours(); 
			if(xHours<10){ 
			xHours="0"+xHours; 
			} 
			
			var xMinutes=this.getMinutes(); 
			if(xMinutes<10){ 
			xMinutes="0"+xMinutes; 
			} 
			
			var xSeconds=this.getSeconds(); 
			if(xSeconds<10){ 
			xSeconds="0"+xSeconds; 
			} 
			
			var returnString;
			if(separator){
			returnString = xYear+separator+xMonth+separator+xDay+" "+xHours+":00:00"; 
			}else{
			returnString = xYear+xMonth+xDay+" "+xHours+"0000"; 
			}
			return returnString;
}

/*
*    将时间类型转化为  短字符串，separator为分隔符符号；
*    参数 ：separator 
*    结果： 1987-04-23
       */
Date.prototype.toShortDateString=function(separator){ 
			var xYear=this.getYear(); 
			xYear=xYear+1900; 
			
			var xMonth=this.getMonth()+1; 
			if(xMonth<10){ 
			xMonth="0"+xMonth; 
			} 
			
			var xDay=this.getDate(); 
			if(xDay<10){ 
			xDay="0"+xDay; 
			} 
						
			return xYear+separator+xMonth+separator+xDay; 
}

Date.prototype.toShortDateStringNoYear=function(separator){ 
	var xYear=this.getYear(); 
	xYear=xYear+1900; 
	
	var xMonth=this.getMonth()+1; 
	if(xMonth<10){ 
	xMonth="0"+xMonth; 
	} 
	
	var xDay=this.getDate(); 
	if(xDay<10){ 
	xDay="0"+xDay; 
	} 
				
	return xMonth+separator+xDay; 
}

/*
       根据日期参数获取此日期所在月份的第一天
       传入参数类型：Date()
       输出结果类型：Date()
       */
function getFirstDayOfMonth(date) {
    date.setDate(1);
    return date;
}

/*
       根据日期参数获取此日期所在月份的最后一天
       传入参数类型：Date()
       输出结果类型：Date()
       */
function getLastDayOfMonth(someDay) {
    var month = someDay.getMonth();
    var nextMonth = ++month;

    var nextMonthFirstDay = new Date(someDay.getFullYear(), nextMonth, 1);
    var minusDate = 1000 * 60 * 60 * 24;

    return new Date(nextMonthFirstDay.getTime() - minusDate);
}

/*
       根据所选月份与第几周获取所选周的周一和周日
      */
function GetStartEndDateForQuery(firstDayOfSelectMonth, weekNumOfMonth) {
    var minusDate = 1000 * 60 * 60 * 24;

    var dayOfWeek = firstDayOfSelectMonth.getDay();

    if (dayOfWeek == 0) {
        var startDate = new Date(firstDayOfSelectMonth.getTime() + minusDate * ((weekNumOfMonth - 1) * 7 - (7 - 1)));
    } else {
        var startDate = new Date(firstDayOfSelectMonth.getTime() + minusDate * ((weekNumOfMonth - 1) * 7 - (dayOfWeek - 1)));
    }
    var endDate = new Date(startDate.getTime() + minusDate * 6);

    return {
        StartDate: startDate,
        EndDate: endDate
    }
}

/*
      获取某个日期所在周的周一
      */
function getMondayBySomeday(someday){
     var minusDate = 1000 * 60 * 60 * 24;
     var dayOfWeek = someday.getDay();
     var monday;
     
     if(dayOfWeek == 0){
     		monday = new Date(someday.getTime() - minusDate * 6);
     }else{
      		monday = new Date(someday.getTime() - minusDate * (dayOfWeek - 1));
     }
     return monday;
}

