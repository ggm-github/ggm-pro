<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="background:url(${ctxStatic}/img/sysBg.png)">
<head>
    <meta charset="utf-8">
    <title>操场</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

    <link rel="stylesheet" type="text/css" media="screen" href="${ctxStatic}/css/bootstrap.min.css${jsversion}">
    <link rel="stylesheet" type="text/css" media="screen" href="${ctxStatic}/css/font-awesome.min.css${jsversion}">
    <link rel="stylesheet" type="text/css" media="screen" href="${ctxStatic}/css/smartadmin-production.min.css${jsversion}">
    <link rel="stylesheet" type="text/css" media="screen" href="${ctxStatic}/css/smartadmin-skins.min.css${jsversion}">
    <link rel="stylesheet" type="text/css" media="screen" href="${ctxStatic}/js/plugin/jBox/jBox.css${jsversion}">
    <link rel="stylesheet" type="text/css"  href="${ctxStatic}/css/uway.common.css${jsversion}">
    <link rel="shortcut icon" href="${ctxStatic}/img/favicon/favicon.ico${jsversion}" type="image/x-icon">
    <link rel="icon" href="${ctxStatic}/img/favicon/favicon.ico${jsversion}" type="image/x-icon">
    <link rel="stylesheet" type="text/css" media="screen" href="${ctxStatic}/css/bootstrap-datetimepicker.min.css${jsversion}" >
    <style>
        .form-control{
            margin-bottom:10px;
        }
        #cearchul {
            display: none !important;
        }
    </style>
</head>
<body class="desktop-detected pace-done" style="background:url(${ctxStatic}/img/sysBg.png)">

<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/include/leftmenu.jsp"></jsp:include>
<div id="main" role="main">
    <div id="ribbon">
        <div class="col-sm-7">
            <ol class="breadcrumb">
                <li>系统管理</li><li>日志管理</li>
            </ol>
        </div>
    </div>
    <div id="content">
        <div class="condition_wrapper">
            <div class="divform">
                <label for="appUserId">APP用户ID：</label>
                <input type="text" name="appUserId" id="appUserId" placeholder="用户ID">
            </div>
            <div class="divform">
                <label for="terminalType">终端类型：</label>
                <select id="terminalType" style="height: 32px;">
                    <option value="">全部</option>
                    <option value="2">ios</option>
                    <option value="3">android</option>
                </select>
            </div>
            <div class="divform">
                <label style="margin-left: 10px;">时间范围：</label>
                <label class="data_time">
                    <input type="text" id="starttime" value="" style="height: 32px;width:115px;" name="starttime">
                    <i class="icon-append fa fa-calendar" style="margin-left: -28px; height: 28px; width: 28px;"></i>
                </label> <b>~ </b>
                <label class="data_time">
                    <input type="text"  id="endtime" value="" style="height: 32px;width:115px;" name="endtime">
                    <i class="icon-append fa fa-calendar" style="margin-left: -28px; height: 28px; width: 28px;"></i>
                </label>
            </div>
            <div class="divform">
                <input type="button" class="btn main-color-bluegreen" style="width: 80px;" onclick="query()"  value="查询">
            </div>
        </div>
        <div id="tableRow" style="min-width:1000px;margin-top: 10px;">
            <table id="logTable" class="table table-striped table-bordered">

            </table>
        </div>
    </div>
</div>

<div class="page-footer">

</div>
</body>

<script src="${ctxStatic}/js/libs/jquery-2.0.2.min.js${jsversion}"></script>
<script src="${ctxStatic}/js/libs/jquery-ui-1.10.3.min.js${jsversion}"></script>
<script src="${ctxStatic}/js/app.config.js${jsversion}"></script>
<script src="${ctxStatic}/js/bootstrap/bootstrap.min.js${jsversion}"></script>
<script src="${ctxStatic}/js/bootstrap/bootstrap-datetimepicker.js${jsversion}"></script>
<script src="${ctxStatic}/js/bootstrap/bootstrap-datetimepicker.zh-CN.js${jsversion}"></script>
<script src="${ctxStatic}/js/plugin/jBox/jBox.min.js${jsversion}"></script>
<script src="${ctxStatic}/js/app.min.js${jsversion}"></script>
<script src="${ctxStatic}/js/leftmenu.js${jsversion}"></script>
<script src="${ctxStatic}/js/plugin-datatables/jquery.dataTables.min.js${jsversion}"></script>
<script src="${ctxStatic}/js/plugin-datatables/dataTables.bootstrap.min.js${jsversion}"></script>
<script src="${ctxStatic}/js/header.js${jsversion}"></script>
<script src="${ctxStatic}/js/time/time.js${jsversion}"></script>
<script src="${ctxStatic}/js/uway.common.js${jsversion}"></script>

<script type="text/javascript">
    var table;
    function  getLog() {
        table = $('#logTable').DataTable({
            "scrollX": true,
            "processing": true,
            "serverSide": true,
            "ajax": {
                "url": "${contextPath}/user/getLogTable",
                "type": "post",
                "data": function (d) {
                    var  condition = {
                        userId: $("#appUserId").val(),
                        terminalType: $("#terminalType").val(),
                        startTime: $("#starttime").val(),
                        endTime: new Date($("#endtime").val()).addDays(1).format("yyyy-MM-dd")
                    }
                    d.condition = JSON.stringify(condition);
                },
                "dataSrc": function (data) {
                    if(data.success!=null && !data.success){
                        uwayWarning("警告",data.message);
                    }else {
                        return data.data;
                    }
                },
                "error":function (e1,e2,e3) {
                    if(e1.responseText.indexOf("<!DOCTYPE html>") != -1){
                        window.location = window.localStorage.getItem("CONTEXT_PATH") + '/user/login';
                    }
                }
            },
            "columns":[
                {data:"id",title:"ID"},
                {data:"userId",title:"用户ID"},
                {data:function (e) {
                return e.loginName!=null?e.loginName:"";
                },title:"登录名",name:"loginName"},
                {data:"operateTimeStr",title:"操作时间",name:"operateTime"},
                {data:function (e) {
                    return e.operateName!=null?e.operateName:"";
                },title:"用户操作",name:"operateName"},
                {data:"method",title:"请求方式"},
                {data:"requestUrl",title:"请求地址"},
                {data:function (e) {
                    return '<span title="'+e.params+'">'+getString(e.params,10)+'</span>'
                },title:"参数",name:"params"},
                {data:function (e) {
                    if(e.terminalType == 2){
                        return "ios";
                    }else if(e.terminalType == 3) {
                        return "android";
                    }
                },title:"操作系统类型",name:"terminalType"},
                {data:"operateIp",title:"IP"},
                {data:function (e) {
                    return '<span title="'+e.userAgent+'">'+getString(e.userAgent,10)+'</span>'
                },
                    title:"代理信息",name:"useragent"}
            ],
            "order": [[ 3, "desc" ]]
        });
    }
    $(document).ready(function () {
        $('#starttime').val(getNowFormatDate());   //设置日历默认为当天
        $('#endtime').val(getNowFormatDate());     //设置日历默认为当天
        $('#starttime').attr("readonly", "readonly").datepicker({   //日历初始化
            dateFormat : 'yy-mm-dd',
            maxDate : 'd',
            showTime : true,
            monthNames: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
            dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],
            dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],
            dayNamesMin: ['日','一','二','三','四','五','六'],
            prevText : '<i class="fa fa-chevron-left"></i>',
            nextText : '<i class="fa fa-chevron-right"></i>',
            onSelect : function(selectedDate) {
                $("#endtime").datepicker('option', 'minDate', selectedDate);
            }
        });
        $('#endtime').attr("readonly", "readonly").datepicker({   //日历初始化
            dateFormat : 'yy-mm-dd',
            maxDate : 'd',
            showTime : true,
            monthNames: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
            dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],
            dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],
            dayNamesMin: ['日','一','二','三','四','五','六'],
            prevText : '<i class="fa fa-chevron-left"></i>',
            nextText : '<i class="fa fa-chevron-right"></i>',
            onSelect : function(selectedDate) {
                $("#starttime").datepicker('option', 'maxDate', selectedDate);
            }
        });
        getLog();
    });
    function query() {
        table.ajax.reload();
    }
    //获取当前日期函数
    function getNowFormatDate() {
        var date = new Date().addDays(0);
        var seperator1 = "-";
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = year + seperator1 + month + seperator1 + strDate;
        return currentdate;
    }
</script>
</html>