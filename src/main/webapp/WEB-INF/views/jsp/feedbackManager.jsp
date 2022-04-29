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
                <li>系统管理</li>
                <li>意见处理</li>
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
            </div>
        </div>
        <div id="tableRow" style="min-width:1000px;margin-top: 10px;">
            <table id="feedbackTable" class="table table-striped table-bordered">

            </table>
        </div>
        <div id="feedbackModal" class="modal fade in" tabindex="-1" role="dialog"
             aria-labelledby="feedbackTitle" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <a class="close" data-dismiss="modal">×</a>
                        <h3 id="feedbackTitle">意见反馈详细信息</h3>
                    </div>
                    <div class="modal-body">
                        <div class="feedback-wrapper">
                            <span class="feedback_title">用户ID：</span>
                            <span class="feedback_content" id="feedback_user_id"></span>
                        </div>
                        <div class="feedback-wrapper">
                            <span class="feedback_title">意见内容：</span>
                            <span class="feedback_content" id="feedback_content"></span>
                        </div>
                        <div class="feedback-wrapper">
                            <span class="feedback_title">回复信息：</span>
                            <span class="feedback_content" id="feedback_response"></span>
                        </div>
                        <div class="feedback-wrapper">
                            <span class="feedback_title">创建时间：</span>
                            <span class="feedback_content" id="feedback_time"></span>
                        </div>
                        <div class="feedback-wrapper">
                            <span class="feedback_title">状态值：</span>
                            <span class="feedback_content" id="feedback_status"></span>
                        </div>
                        <div class="feedback-wrapper">
                            <span class="feedback_title">图片：</span>
                            <img onerror="hidePic(this);" style="width: 300px;"  id="feedback_client_pic"/>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <a href="#" class="btn main-color-bluegreen" data-dismiss="modal">确认</a>
                    </div>
                </div>
            </div>
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
    var table;          //意见反馈表
    $(function () {
        // $('#starttime').val(getNowFormatDate());   //设置日历默认为当天
        // $('#endtime').val(getNowFormatDate());     //设置日历默认为当天
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
        getFeedback();
    });
    function getFeedback() {
        table = $("#feedbackTable").DataTable({
            "scrollX": true,
            "processing": true,
            "serverSide": true,
            "ajax": {
                "url": "${contextPath}/app/getFeedback",
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
                "dataSrc": function (data) {
                    if (data.success != null && !data.success) {
                        uwayWarning("警告", data.message);
                    } else {
                        return data.data;
                    }
                },
                "error": function (e1, e2, e3) {
                    if (e1.responseText.indexOf("<!DOCTYPE html>") != -1) {
                        window.location = window.localStorage.getItem("CONTEXT_PATH") + '/user/login';
                    }
                }
            },
            "columns": [
                {data: "id", title: "ID"},
                {data: "userId", title: "用户ID"},
                {
                    data: "content", title: "意见内容", render: function (e) {
                    return strlen(e) > 20 ? getString(e, 20) : e;
                }
                },
                {
                    data: function (e) {
                        return e.responseMessage != null ?
                            (strlen(e.responseMessage) > 20 ?
                                getString(e.responseMessage, 20) : e.responseMessage)
                            : "未回复";
                    }, title: "回复信息"
                },
                {data:"versionCode",title:"版本号"},
                {data: "createTimeStr", title: "创建时间"},
                {
                    data: "status", title: "状态", render: function (data) {
                    return data == 1 ? "有效" : "无效";
                }
                },
                {
                    data: function (e) {
                        return '<a onclick="getFeedbackInfo(\'' + e.id + '\')">查看详细信息</a>';
                    }, title: "操作"
                }
            ],
            "order": [[0, "asc"]],
            "columnDefs": [{"orderable": false, "targets": [2, 3, 6]}],
        });
    }
    function query() {
        table.ajax.reload();
    }
    /**
     * 获取选中的意见反馈信息
     * @param id
     */
    function getFeedbackInfo(id) {
        $.get("${contextPath}/app/getFeedbackById", {id: id}, function (result) {
            if (result.success) {
                var data = result.result;
                $("#feedback_user_id").text(data.userId);
                $("#feedback_content").text(data.content);
                if (data.responseMessage != null) {
                    $("#feedback_response").text(data.responseMessage);
                }
                else {
                    $("#feedback_response").text("未回复");
                }
                $("#feedback_time").text(data.createTimeStr);
                $("#feedback_status").text(data.status == 1 ? "有效" : "无效");
                $("#feedback_client_pic").attr("src","<%=Global.getConfig("prefix.visit.path")%>"+data.clientPic);
            }
        }, "json");
        $("#feedbackModal").modal('show');
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
