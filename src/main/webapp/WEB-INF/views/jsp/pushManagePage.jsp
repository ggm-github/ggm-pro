<%@ taglib prefix="fus" uri="http://java.sun.com/jsp/jstl/funs" %>
<%--
  Created by IntelliJ IDEA.
  User: zhao
  Date: 2018/3/5
  Time: 9:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
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
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/css/uway.common.css${jsversion}">
    <link rel="shortcut icon" href="${ctxStatic}/img/favicon/favicon.ico${jsversion}" type="image/x-icon">
    <link rel="icon" href="${ctxStatic}/img/favicon/favicon.ico${jsversion}" type="image/x-icon">
    <link rel="stylesheet" type="text/css" media="screen" href="${ctxStatic}/css/bootstrap-datetimepicker.min.css${jsversion}">
    <style>
        .feedback-wrapper{
            margin-top: 10px;
            display: flex;
            font-size: 15px;
        }
        .feedback_title{
            flex: 0 0 100px;
        }
        .feedback_content{
            flex: 1;
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
                <li>APP通用管理</li>
                <li>友盟推送</li>
            </ol>
        </div>
    </div>
    <div id="content">
        <div class="condition_wrapper">
            <div class="divform">
                <label style="margin-left: 10px;">时间范围：</label>
                <label class="data_time">
                    <input type="text" id="starttime" value="" style="height: 32px;width:115px;" name="starttime">
                    <i class="icon-append fa fa-calendar" style="margin-left: -28px; height: 28px; width: 28px;"></i>
                </label> <b>~ </b>
                <label class="data_time">
                    <input type="text" id="endtime" value="" style="height: 32px;width:115px;" name="endtime">
                    <i class="icon-append fa fa-calendar" style="margin-left: -28px; height: 28px; width: 28px;"></i>
                </label>
            </div>
            <div class="divform">
                <input type="button" class="btn main-color-bluegreen" style="width: 80px;" onclick="query()" value="查询">
                <fus:hasRight url="/message/push">
                    <label class="btn main-color-bluegreen" style="margin-left: 15px; width: 80px" onclick="openAddModal()">添加推送</label>
                </fus:hasRight>
            </div>
        </div>
        <div id="tableRow" style="min-width:1000px;margin-top: 10px;">
            <table id="feedbackTable" class="table table-striped table-bordered">

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
<script>

    function hidePic(obj) {
        $(obj).hide();
    }

    function openAddModal(){
        window.location.href="${contextPath}/message/toAddPushPage";
    }

    var table;          //意见反馈表
    $(function () {
        $('#starttime').datepicker({   //日历初始化
            dateFormat: 'yy-mm-dd',
            maxDate: 'd',
            showTime: true,
            monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
            dayNames: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
            dayNamesShort: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
            dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'],
            prevText: '<i class="fa fa-chevron-left"></i>',
            nextText: '<i class="fa fa-chevron-right"></i>',
            onSelect: function (selectedDate) {
                $("#endtime").datepicker('option', 'minDate', selectedDate);
            }
        });
        $('#endtime').datepicker({   //日历初始化
            dateFormat: 'yy-mm-dd',
            maxDate: 'd',
            showTime: true,
            monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
            dayNames: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
            dayNamesShort: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
            dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'],
            prevText: '<i class="fa fa-chevron-left"></i>',
            nextText: '<i class="fa fa-chevron-right"></i>',
            onSelect: function (selectedDate) {
                $("#starttime").datepicker('option', 'maxDate', selectedDate);
            }
        });
        getPushMessage();
    });
    function getPushMessage() {
        table = $("#feedbackTable").DataTable({
            "scrollX": true,
            "processing": true,
            "serverSide": true,
            "ajax": {
                "url": "${contextPath}/message/getUmengPushList",
                "type": "post",
                "data": function (d) {
                    var endTime;
                    if ($("#endtime").val()!=null && $("#endtime").val()!='') {
                        endTime =  new Date($("#endtime").val()).addDays(1).format("yyyy-MM-dd")
                    }
                    var condition = {
                        startTime: $("#starttime").val(),
                        endTime:endTime
                    }
                    d.condition = JSON.stringify(condition);
                },
                "error": function (e1, e2, e3) {
                    if (e1.responseText.indexOf("<!DOCTYPE html>") != -1) {
                        window.location = window.localStorage.getItem("CONTEXT_PATH") + '/user/login';
                    }
                }
            },
            "columns": [
                {data: "id", title: "ID",width:"80px"},
                {data: "title", title: "标题",width:"150px",defaultContent:""},
                {data: "subtitle", title: "副标题",width:"150px",defaultContent:""},
                {data: "body", title: "正文",width:"200px",defaultContent:""},
                {data: "url", title: "跳转url",width:"150px",defaultContent:""},
                {data: "imageurl", title: "图片",width:"150px",defaultContent:""},
                {data: "activity", title: "跳转页面",width:"150px",defaultContent:""},
                {
                    data: "type", title: "类型", render: function (data) {
                    return data == 0 ? "url" : "原生";
                },width:"80px"
                },
                {
                    data: "messageType", title: "系统", render: function (data) {
                        return data == 0 ? "全部" : data == 1 ? "android":"ios";
                    },width:"80px"
                },
                {data:"inserttime",title:"入库时间",width:"100px",defaultContent:""}
            ],
            "order": [[0, "desc"]],
        });
    }
    function query() {
        table.ajax.reload();
    }

</script>
</html>
