<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ page import="com.uway.sports.common.config.Global"%>
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
        .uway-person-div {
            border: solid 1px #e0e0e0;
            float: left;
            padding-bottom: 20px;
            height: 600px;
            width: 45%;
            min-width: 400px;
        }

        .list-ul{
            overflow:auto;
            max-height:500px;
        }
        .list-ul li{
            margin-top: 12px;
        }

        .form-group{
            display: flex;
        }
        .form-group label {
            flex:0 0 90px;
            text-align: right;
            margin-right: 20px;
        }
        .form-group input,.form-group select{
            width:260px;
            height:30px;
        }
        .form-group textarea{
            width:260px;
            height:60px;
            margin-bottom: -23px;
        }
        .form-group span{
            line-height:30px;
            margin-top:2px;
        }
        .form-line{
            height:1px;
            margin-bottom:5px;
            border-top:1px #7c807b solid
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
                <li>APP通用管理</li><li>APP通用设置</li>
            </ol>
        </div>
    </div>
    <div id="content">
        <div class="row" style="width:100%;">
            <div class="panel panel-default form-horizontal  uway-person-div main-color-fontcolor">
                <div class="panel-heading">
                    配置信息
                </div>
                <div class="panel-body">
                    <div class="form-group">
                        <span style="margin-left: 20px;">打卡标题：</span><input id="signTitleIn" type="text" style="margin-top: 2px;" value="${redisData.signTitle}" />
                    </div>
                    <div class="form-group">
                        <span style="margin-left: 20px;">打卡次数：</span><input id="signTotalIn" type="text" style="margin-top: 2px;width: 50px;" value="${redisData.signTotal}" />
                        <button class="btn main-color-bluegreen" style="margin-left:20px;" onclick="modifySignInfo();">修改</button>
                    </div>
                    <div class="form-group form-line"></div>
                    <div class="form-group">
                        <span style="margin-left: 20px;">每天投票次数：</span><input id="voteCntIn" type="text" style="margin-top: 2px;width: 50px;" value="${redisData.oneDayVoteCnt}" />
                        <button class="btn main-color-bluegreen" style="margin-left:20px;" onclick="modifyOneDayVoteCnt();">修改</button>
                    </div>
                </div>
            </div>

            <div class="panel panel-default form-horizontal  uway-person-div main-color-fontcolor">
                <div class="panel-heading">
                    升级设置
                </div>
                <div class="panel-body">
                    <button id="upgrade" class="btn main-color-bluegreen" style="margin-left:20px;" onclick="openVersionModal()">升级设置</button>
                    <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3" style="width:100%;">
                        <ul id="upgradeUL" class="list-ul">

                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="row" style="width:33%;">

        </div>

        <%--app版本新增框--%>
        <div class="modal fade" id="versionModal" tabindex="-1" role="dialog"
             aria-labelledby="versionLabel" >
            <div class="modal-dialog" style="width: 600px;">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="versionLabel">app版本</h4>
                    </div>
                    <div class="modal-body">
                        <div style="width: 400px;margin: 0 auto;">
                            <input id="versionId" name="versionId" type="hidden"/>
                            <div class="form-group">
                                <label for="versionType">类型:</label>
                                <select id="versionType" name="versionType"></select>
                            </div>
                            <div class="form-group">
                                <label for="lastVersion">版本号:</label>
                                <input type="text" id="lastVersion" placeholder="请输入版本号">
                            </div>
                            <div class="form-group">
                                <label for="versionUrl">更新地址:</label>
                                <input type="text" id="versionUrl" >
                            </div>
                            <div class="form-group">
                                <label for="isMustUpdate">是否强制:</label>
                                <select name="isMustUpdate" id="isMustUpdate" ></select>
                            </div>
                            <div class="form-group">
                                <label for="versionStatus">版本状态:</label>
                                <select name="status" id="versionStatus" ></select>
                            </div>
                            <div class="form-group" style="align-items:center;">
                                <label for="versionInfo">版本信息:</label>
                                <textarea name="versionInfo" id="versionInfo" cols="30" rows="3"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button id="addBtn" type="button" style="display:none;" class="btn main-color-bluegreen" onclick="addVersion()">提交</button>
                        <button id="updateBtn" type="button" style="display:none;" class="btn main-color-bluegreen" onclick="updateVersion()">提交</button>
                        <button id="closeBtn" type="buttom" class="btn main-color-bluegreen" onclick="closeVersionModal()">取消</button>
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
<script src="${ctxStatic}/js/app.config.js${jsversion}"></script>
<script src="${ctxStatic}/js/bootstrap/bootstrap.min.js${jsversion}"></script>
<script src="${ctxStatic}/js/bootstrap/bootstrap-datetimepicker.js${jsversion}"></script>
<script src="${ctxStatic}/js/bootstrap/bootstrap-datetimepicker.zh-CN.js${jsversion}"></script>
<script src="${ctxStatic}/js/plugin/jBox/jBox.min.js${jsversion}"></script>
<script src="${ctxStatic}/js/app.min.js${jsversion}"></script>
<script src="${ctxStatic}/js/leftmenu.js${jsversion}"></script>
<script src="${ctxStatic}/js/plugin-datatables/jquery.dataTables.min.js${jsversion}"></script>
<script src="${ctxStatic}/js/plugin-datatables/dataTables.bootstrap.min.js${jsversion}"></script>
<script src="${ctxStatic}/js/time/time.js${jsversion}"></script>
<script src="${ctxStatic}/js/header.js${jsversion}"></script>
<script src="${ctxStatic}/js/uway.common.js${jsversion}"></script>


<script type="text/javascript">
    var statusObj = {"0":"无效","1":"有效"};
    var versionTypeObj = {"1":"ios","2":"android"};
    var isMustUpdateObj = {"0":"否","1":"是"};
    var visitPath = '<%=Global.getConfig("prefix.visit.path")%>';
    $(document).ready(function(){
        initSelect();
        //获取版本列表，若需要添加版本的下拉，可在此方法中拼接
        getAppVersionList();
    });
    //下拉框的初始化
    function initSelect(){
        $("#versionType").empty();
        var htmlstr = "";
        $.each(versionTypeObj,function(key,val){
            htmlstr += '<option value="'+key+'">'+val+'</option>';
        });
        $("#versionType").append(htmlstr);
        $("#versionStatus").empty();
        htmlstr = "";
        $.each(statusObj,function(key,val){
            htmlstr += '<option value="'+key+'">'+val+'</option>';
        });
        $("#versionStatus").append(htmlstr);
        $("#versionStatus").val(1);
        htmlstr = "";
        $.each(isMustUpdateObj,function(key,val){
            htmlstr += '<option value="'+key+'">'+val+'</option>';
        });
        $("#isMustUpdate").append(htmlstr);
        $("#isMustUpdate").val(0);

    }

    //app版本列表查询
    function getAppVersionList(id){
        //$.uwayProcess.show();
        if(id == undefined){
            var inParam = {"status":1};
        }else{
            var inParam = {"status":1,"versionId":id};
        }

        $.ajax({
            url: "${contextPath}/comm/getAppVersionList",
            async: true, type: "Post", dataType: "json",
            data: {param:JSON.stringify(inParam)},
            success: function (data) {
                $.uwayProcess.close();
                if(data.success){
                    if(id == undefined){
                        var listhtml = "";
                        for(var i=0;i<data.result.length;i++) {
                            var version = data.result[i];
                            listhtml += '<li><a onclick="openVersionModal('+version.versionId+')">类型：'+versionTypeObj[version.versionType]+'&nbsp;&nbsp;&nbsp;版本号：'+version.lastVersion+'&nbsp;&nbsp;&nbsp;状态：'+statusObj[version.status]+' <br/>更新地址：'+version.versionUrl+'</a></li>';
                        }
                        $("#upgradeUL").empty();
                        $("#upgradeUL").html(listhtml)
                    }else{
                        var version = data.result[0];
                        $("#versionStatus").val(version.status)
                        $("#versionType").val(version.versionType);
                        $("#lastVersion").val(version.lastVersion);
                        $("#versionUrl").val(version.versionUrl);
                        $("#versionInfo").val(version.versionInfo);
                        $("#versionId").val(version.versionId);
                    }
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                $.uwayProcess.close();
                console.log(textStatus);
            }
        });
    }
    //打开版本modal框
    function openVersionModal(id){
        $("#versionId").val("");
        $("#versionStatus").val(1);
        $("#versionType").val(1);
        $("#lastVersion").val("");
        $("#versionUrl").val("");
        $("#versionInfo").val("");
        if(id != undefined){
            getAppVersionList(id);
            $("#updateBtn").show();
            $("#addBtn").hide();
            $("#versionType").attr("disabled","disabled");
        }else{
            $("#updateBtn").hide();
            $("#addBtn").show();
            $("#versionType").removeAttr("disabled");
        }
        $("#versionModal").modal("show");
    }

    //关闭版本modal
    function closeVersionModal(){
        $("#versionStatus").val(1)
        $("#versionType").val(1);
        $("#lastVersion").val("");
        $("#versionUrl").val("");
        $("#versionInfo").val("");
        $("#versionModal").modal("hide");
    }

    //新增版本
    function addVersion(){
        $.uwayProcess.show();
        var inParam = {
            "status":$("#versionStatus").val(),
            "versionType":$("#versionType").val(),
            "lastVersion":$("#lastVersion").val(),
            "versionUrl":$("#versionUrl").val(),
            "isMustUpdate":$("#isMustUpdate").val(),
            "versionInfo":$("#versionInfo").val()
        };
        $.ajax({
            url: "${contextPath}/comm/addAppVersion",
            async: true, type: "Post", dataType: "json",
            data: {param:JSON.stringify(inParam)},
            success: function (data) {
                $.uwayProcess.close();
                if(data.success){
                    uwayNotice("通知","新增成功");
                    closeVersionModal();
                    getAppVersionList();
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                $.uwayProcess.close();
                console.log(textStatus);
            }
        });
    }

    //修改版本
    function updateVersion(){
        $.uwayProcess.show();
        var inParam = {
            "versionId":$("#versionId").val(),
            "status":$("#versionStatus").val(),
//            "versionType":$("#versionType").val(),
            "lastVersion":$("#lastVersion").val(),
            "versionUrl":$("#versionUrl").val(),
            "isMustUpdate":$("#isMustUpdate").val(),
            "versionInfo":$("#versionInfo").val()
        };
        $.ajax({
            url: "${contextPath}/comm/updateAppVersion",
            async: true, type: "Post", dataType: "json",
            data: {param:JSON.stringify(inParam)},
            success: function (data) {
                $.uwayProcess.close();
                if(data.success){
                    uwayNotice("通知","修改成功");
                    closeVersionModal();
                    getAppVersionList();
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                $.uwayProcess.close();
                console.log(textStatus);
            }
        });
    }

    function modifySignInfo(){
        var signTotal = $("#signTotalIn").val();
        var signTitle = $("#signTitleIn").val();
        var requestUrl = '${contextPath}/comm/updateSignInfo';
        var param = {"signTotal":signTotal,"signTitle":signTitle};
        updateRedisInfo(requestUrl,param);
    }

    function modifyOneDayVoteCnt(){
        var voteCnt = $("#voteCntIn").val();
        var requestUrl = '${contextPath}/comm/updateOneDayVoteCnt';
        var param = {"voteCnt" : voteCnt};
        updateRedisInfo(requestUrl,param);
    }

    function updateRedisInfo(requestUrl,param){
        $.uwayProcess.show();
        $.ajax({
            url: requestUrl,
            async: true, type: "Post", dataType: "json",
            data: param,
            success: function (data) {
                $.uwayProcess.close();
                if(data.success){
                    uwayNotice("通知","修改成功")
                }else{
                    uwayWarning("提示",data.message);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                $.uwayProcess.close();
                console.log(textStatus);
            }
        });
    }

</script>
</html>
