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
    <link rel="stylesheet" type="text/css" media="screen" href="${ctxStatic}/js/plugin/zTree_v3-master/css/zTreeStyle/zTreeStyle.css${jsversion}">
    <link rel="stylesheet" type="text/css" media="screen" href="${ctxStatic}/css/bootstrap-datetimepicker.min.css${jsversion}" >
    <link rel="stylesheet" type="text/css"  href="${ctxStatic}/css/uway.common.css${jsversion}">
    <link rel="shortcut icon" href="${ctxStatic}/img/favicon/favicon.ico${jsversion}" type="image/x-icon">
    <link rel="icon" href="${ctxStatic}/img/favicon/favicon.ico${jsversion}" type="image/x-icon">
    <link rel="stylesheet" type="text/css" media="screen" href="${ctxStatic}/css/bootstrap-datetimepicker.min.css${jsversion}" >
</head>
<body class="desktop-detected pace-done" style="background:url(${ctxStatic}/img/sysBg.png)" >

<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/include/leftmenu.jsp"></jsp:include>
<div id="main" role="main">
    <div id="ribbon">
        <div class="col-lg-7 col-md-7 col-sm-7 col-xs-7">
            <ol class="breadcrumb">
                <li>系统管理</li><li>新增用户</li>
            </ol>
        </div>
    </div>
    <div id="content">
        <div class="row" style="min-width:900px;">
            <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3" style="min-width:200px;">
                <ul id="roleTree" class="ztree uway-tree"></ul>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3" style="min-width:200px;display: none;">
                <ul id="labelTree" class="ztree uway-tree"></ul>
            </div>
            <div id="userInfo" class="form-horizontal col-lg-6 col-md-6 col-sm-6 col-xs-6" style="margin-top:30px;">
                <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10">
                    <div class="form-group">
                        <label class="col-lg-4 col-md-4 col-sm-4 col-xs-4 control-label" for="userName">登录名：</label>
                        <div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
                            <input class="form-control" type="text" id="userName" name="userName" placeholder="请输入登录名" value="" >
                        </div>
                    </div>
                </div>
                <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10">
                    <div class="form-group">
                        <label class="col-lg-4 col-md-4 col-sm-4 col-xs-4 control-label" for="chName">姓名：</label>
                        <div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
                            <input class="form-control" type="text" id="chName" name="chName" placeholder="请输入真实姓名" value="">
                        </div>
                    </div>
                </div>
                <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10">
                    <div class="form-group">
                        <label class="col-lg-4 col-md-4 col-sm-4 col-xs-4 control-label" for="phone">手机号：</label>
                        <div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
                            <input class="form-control" type="text" id="phone" name="phone" placeholder="请输入手机号" value="">
                        </div>
                    </div>
                </div>
                <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10">
                    <div class="form-group">
                        <label class="col-lg-4 col-md-4 col-sm-4 col-xs-4 control-label" for="mail">邮箱：</label>
                        <div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
                            <input class="form-control" type="text" id="mail" name="mail" placeholder="请输入邮箱" value="">
                        </div>
                    </div>
                </div>
                <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10">
                    <div class="form-group">
                        <label class="col-lg-4 col-md-4 col-sm-4 col-xs-4 control-label" for="roleId">角色：</label>
                        <div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
                            <select class="form-control" id="roleId" name="roleId">
                                <c:forEach items="${sysRoleList}" var="item">
                                    <option value="${item.roleId}">${item.roleName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10">
                    <div class="form-group">
                        <label class="col-lg-4 col-md-4 col-sm-4 col-xs-4 control-label" for="status">账号状态：</label>
                        <div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
                            <select class="form-control" id="status"  name="status">
                                <option value="0">无效</option>
                                <option selected="selected" value="1">有效</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10" style="margin-top:10px;">
                    <div style="float:right;">
                        <button id="saveBtn" style="width:60px;height:28px;margin-right:20px;" class="btn main-color-bluegreen" onclick="save()">提交</button>
                        <button id="backBtn" style="width:60px;height:28px" class="btn" onclick="goBack()">返回</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

<div class="page-footer">
    BingData
</div>
</body>

<script src="${ctxStatic}/js/libs/jquery-2.0.2.min.js${jsversion}"></script>
<script src="${ctxStatic}/js/app.config.js${jsversion}"></script>
<script src="${ctxStatic}/js/bootstrap/bootstrap.min.js${jsversion}"></script>
<script src="${ctxStatic}/js/bootstrap/bootstrap-datetimepicker.js${jsversion}"></script>
<script src="${ctxStatic}/js/bootstrap/bootstrap-datetimepicker.zh-CN.js${jsversion}"></script>
<script src="${ctxStatic}/js/plugin/jBox/jBox.min.js${jsversion}"></script>
<script src="${ctxStatic}/js/plugin/jquery-validate/jquery.validate.min.js${jsversion}"></script>
<script src="${ctxStatic}/js/plugin/zTree_v3-master/js/jquery.ztree.all.min.js${jsversion}"></script>
<script src="${ctxStatic}/js/app.min.js${jsversion}"></script>
<script src="${ctxStatic}/js/leftmenu.js${jsversion}"></script>
<script src="${ctxStatic}/js/plugin-datatables/jquery.dataTables.min.js${jsversion}"></script>
<script src="${ctxStatic}/js/plugin-datatables/dataTables.bootstrap.min.js${jsversion}"></script>
<script src="${ctxStatic}/js/header.js${jsversion}"></script>
<script src="${ctxStatic}/js/time/time.js${jsversion}"></script>
<script src="${ctxStatic}/js/uway.common.js${jsversion}"></script>

<script type="text/javascript">
    $(function() {
        var hasinitmonthdate = initmonthdate?true:false;
        if(hasinitmonthdate){
            initmonthdate();
        }
    });

    function save(){
        checkAndSubmit(function(){
            uwayConfirm({
                content: '确定要提交修改吗？',
                confirm: function(){
                    ajaxSave();
                },
                cancel: function(evt){
                }
            });
        });
    }

    function getSaveData(){
        var returnObj = {};
        returnObj.userName = $("#userName").val();
        returnObj.userId=$("#userId").val();
        returnObj.chName=$("#chName").val().trim();
        returnObj.phone=$("#phone").val().trim();
        returnObj.mail=$("#mail").val().trim();
        returnObj.status=$("#status").val();
        returnObj.roleId=$("#roleId").val();

        return returnObj;
    }

    function ajaxSave(){
        $.uwayProcess.show();
        var paras = getSaveData();
        $.ajax({
            url: "${contextPath}/user/addUserInfo",
            async: true, type: "Post", dataType: "json",
            data: paras,
            success: function (response) {
                $.uwayProcess.close();
                uwayNotice('通知',response.message);
                if(response.success){
                    setTimeout(function(){
                        window.location.href=document.referrer;
                    },2000);
                }

            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log(textStatus);
            }
        });
    }

    function checkAndSubmit(submitFun){
        if($("#userName").val().trim()==''){
            uwayWarning('警告','登录名不能为空！');
            return false;
        }

        if($("#chName").val().trim()==''){
            uwayWarning('警告','真实姓名不能为空！');
            return false;
        }
/*
        if($("#phone").val().trim()==''){
            uwayWarning('警告','手机号不能为空！');
            return false;
        }
*/

        if($("#phone").val().trim()!='' && !uwayCheckMobile($("#phone").val().trim())){
            uwayWarning('警告','请输入正确的手机号！');
            return false;
        }
/*
        if($("#mail").val().trim()==''){
            uwayWarning('警告','邮箱不能为空！');
            return false;
        }
*/

        if($("#mail").val().trim()!='' && !uwayCheckEmail($("#mail").val().trim())){
            uwayWarning('警告','邮箱格式不正确呦！');
            return false;
        }
        submitFun();

    }

    function goBack(){
        window.location.href=document.referrer;
    }

</script>
</html>