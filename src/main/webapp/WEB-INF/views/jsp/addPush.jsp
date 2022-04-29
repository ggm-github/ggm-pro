<%@ taglib prefix="fus" uri="http://java.sun.com/jsp/jstl/funs" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="background:url(${ctxStatic}/img/sysBg.png)">
	<head>
		<meta charset="utf-8">
		<title>推送管理</title>
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
            #userTable td{
                white-space: nowrap;
                max-width:400px !important;
                min-width:120px;
                overflow-x:hidden;
                text-overflow: ellipsis;
            }
            #userTable {
                table-layout: fixed;
            }
            .form-group label{
                width:130px;
                text-align:right;
            }
            .form-group select{
                width: 150px;
                height:30px;
            }
            .select-query{
                height: 30px;
                width: 150px;
            }
            .input-big {
                width : 75%;
            }

            .tableRowDiv{
                min-width: 1000px;
            }

            .listTable td{
                white-space: nowrap;
                max-width:400px !important;
                min-width:120px;
                overflow-x:hidden;
                text-overflow: ellipsis;
            }
            .listTable {
                table-layout: fixed;
            }

            .queryParamDiv{
                margin-top: 0px;
                margin-bottom: 10px;
            }
            .queryParamDiv label{
                margin-left: 10px;
            }

            #selectedUserDiv span{
                margin: 5px 10px;cursor:pointer;
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
						<li>APP通用管理</li><li>推送管理</li>
					</ol>
				</div>			
			</div>
            <div id="content">
                <!-- 查询条件DIV -->
                <div style="margin-top: 0px;margin-bottom: 10px;" >
                    <div style="width: 600px;margin: 0 auto;">
                        <div class="form-group">
                            <label>标题：</label>
                            <input id="referenceInfo_info" name="referenceInfo" class="input-big"/>
                        </div>
                        <div class="form-group">
                            <label>内容：</label>
                            <input id="referentDsc_info" name="referentDsc" class="input-big">
                        </div>
                        <div class="form-group" id="openWayDiv" style="display:none;">
                            <label>跳转方式：</label>
                            <select id="openWay_info" onchange="openWayChg();">

                            </select>
                        </div>
                        <div id="referentUrldiv" class="form-group msgInfo" style="display:none;">
                            <label>访问地址：</label><input id="referentUrl_info" name="referentUrl" class="input-big">
                        </div>
                        <div class="form-group msgInfoUM" style="float:left;width:800px;margin-top:10px;">
                            <label>终端类型：</label>
                            <select id="messageType_info">
                                <option value="0">全部</option>
                                <option value="1">Android</option>
                                <option value="2">IOS</option>
                            </select>
                        </div>

                        <div style="display:none;">
                            <input id="messageId_info" name="id" />
                            <input type="hidden" id="picUrl_info"/>
                        </div>

                        <div class="form-group" id="imgUploadDiv">
                            <form id="insertform" method="post" action="/comm/upload"  enctype="multipart/form-data"  target="uploadframe">
                                <label style="">图片选择：</label>
                                <label style="width:210px;">
                                    <input style="width: 200px" type="file" name="myfile" id="myfile" value="" onchange="preImg(this.id,'imgShow');" />
                                    <input type="hidden" name="path" value="prefix.upload.path.message"/>
                                </label>
                                <label>
                                    <input class="btn main-color-bluegreen" type="submit" value="上传" onclick="upload()"/>
                                </label>
                            </form>
                        </div>
                        <div class="form-group" style="text-align:center; ">
                            <img id="imgShow" src="" style="display:none;max-width:200px;"/>
                        </div>

                        <div class="form-group">
                            <label>接收人：</label>
                            <input id="sendUsername_info" type="text" class="select-query input-big" readonly onfocus="openUserModel();" />
                            <input id="sendUserid_info" type="text" style="display:none;" />
                        </div>

                        <div class="modal-footer">
                            <input id="addBtn" class="btn main-color-bluegreen" type="button" value="新增" onclick="addMessage();"/>
                            <input id="backBtn" class="btn main-color-bluegreen" type="button" value="取消" onclick="goBack();"/>
                        </div>
                    </div>
                </div>
            </div>
		</div>

        <div class="modal fade" id="chooseModal" tabindex="-1" role="dialog" aria-labelledby="chooseModalTitle" aria-hidden="true">
            <div class="modal-dialog" style="min-width:1200px;">
                <div class="modal-content" id="choose-form">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                            &times;
                        </button>
                        <h4 class="modal-title" id="chooseModalTitle">用户选择</h4>
                    </div>
                    <div class="modal-body">
                        <div id="tableRow_1" class="row tableRowDiv" style="max-height:400px;overflow-y: auto;">
                            <div id="userContent">
                                <!-- 查询条件DIV -->
                                <div class="queryParamDiv">
                                    <div style="display: inline-block;">
                                        <label>用户名：</label>
                                        <input id="userNameSearch" style="height: 30px;width: 100px;" placeholder="用户名"/>
                                    </div>
                                    <div style="display: inline-block;">
                                        <label>是否实名：</label>
                                        <select id="isRealSearch" style="height: 30px;width: 100px;">
                                            <option selected="selected" value="">全部</option>
                                            <option  value="1">已实名</option>
                                            <option  value="0">未实名</option>
                                        </select>
                                    </div>

                                    <div style="display: inline-block;">
                                        <label for="chNameSearch">昵称</label>
                                        <input  id="chNameSearch" type="text" style="height: 30px;width: 100px;" placeholder="请输入用户昵称"/>
                                    </div>

                                    <div style="display: inline-block;">
                                        <btn  class="btn main-color-bluegreen" style="width: 80px" onclick="query()">查 询</btn>
                                    </div>

                                </div>
                            </div>
                            <table id="userTable" class="listTable table table-striped table-bordered" style="max-height:400px;">

                            </table>

                        </div>
                        <div style="display:none;">
                            <input id="userIdArr" type="text" />
                        </div>
                        <div id="selectedUserDiv" class="row tableRowDiv" style="padding-top:20px;max-height:100px;overflow-y: auto;">

                        </div>
                    </div>
                    <div class="modal-footer">
                        <button id="resetBtn" class="btn main-color-bluegreen" onclick="getChooseInfo();">
                            确定
                        </button>
                        <button type="button" class="btn" data-dismiss="modal">
                            取消
                        </button>
                    </div>
                </div>
            </div>
        </div>

        <iframe id="uploadframe" name="uploadframe" style="display:none;"></iframe>
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
<script src="${ctxStatic}/js/header.js${jsversion}"></script>
<script src="${ctxStatic}/js/time/time.js${jsversion}"></script>
<script src="${ctxStatic}/js/uway.common.js${jsversion}"></script>

<script>

    var userTable = null;
    var openWayObj = {"-1":"无跳转","0":"链接地址","1":"赛事详情","14":"话题详情","18":"动态详情"};

	$(document).ready(function(){
	    //初始化类型下拉
        var typeStr = '<option value="">全部</option>';
        var str = '';
        $.each(openWayObj,function(key,val){
            str += '<option value="'+key+'">'+val+'</option>';
        });
        $("#openWay_info").empty();
        $("#openWay_info").append(str);
        $("#openWay_info").append(-1);
        openAddModal();
    });


	//页面跳转
	function openAddModal(){
	    $("#messageType_info").val("0");
        $("#referenceName_info").val("");
        $("#picUrl_info").val("");
        $("#imgShow").hide();
        $("#referenceInfo_info").val("");
        $("#referentDsc_info").val("");
        $("#referentUrl_info").val("");
        $("#myfile").val("");
        $("#imgUploadDiv").show();
        $("#addBtn").show();
        chooseMessageType();
        $("#openWay_info").val(-1);
        openWayChg();
        $("#infoModalBody :input").removeAttr("disabled");
        $("#isUmeng_info").val("0");
        $("#sendUsername_info").val("");
        $("#sendUserid_info").val("");
        $("#selectedUserDiv").empty();
    }

    function chooseMessageType(){
        var messageType = $("#messageType_info").val();
        $(".msgInfoUM").hide();
        $("#openWayDiv").hide();
        if(messageType == 3){
            $(".msgInfoUM").show();
            chooseType();
        }else{
            $("#openWayDiv").show();
            openWayChg();
        }
    }

    function openWayChg(){
        var openWay = $("#openWay_info").val();
        if(openWay == -1){
            $("#referentUrldiv").hide();
        }else{
            $("#referentUrldiv").show();
        }
    }

    function openUserModel(){
        if (userTable == null){
            initDataTable();
        } else{
            query();
        }
        $("#chooseModal").modal("show");
    }

    function query(){
        userTable.ajax.reload();
    }

    //dataTable Ajax请求
    function initDataTable(){
        //否则直接查询
        userTable = $('#userTable').DataTable({
            "scrollX": true,
            "serverSide": true,
            "searching":false,
            "order": [[1,"desc"]],
            "ajax": {
                "url": "${contextPath}/app/getListByUser",
                "type": "POST",
                "data": function (d) {
                    $.uwayProcess.show();
                    var condition = {};
                    condition.loginName = $("#userNameSearch").val().trim();
                    condition.chName = $("#chNameSearch").val().trim();
                    condition.isReal = $("#isRealSearch").val();
                    condition.status = 1;
                    d.condition = JSON.stringify(condition);
                },
                "dataSrc" : function(data) {
                    $.uwayProcess.close();
                    return data.data;
                },
                "error":function (e1,e2,e3) {
                    if(e1.responseText.indexOf("<!DOCTYPE html>") != -1){
                        window.location = window.localStorage.getItem("CONTEXT_PATH") + '/user/login';
                    }
                }
            },
            "columns":[
                {data: function(e){
                        var str = '<a onclick="selectedUser(\''+ e.userId+'\',\''+e.chName+'\')">选择</a>';
                        return str;
                    },
                    width:"100px",
                    title:'操作',
                    bSortable:false
                },
                {data: "userId",bSearchable:false,bSortable:false,width:"50px",title:'编号'},
                {data: "loginName",bSearchable:false,title:'用户名',bSortable:false,width:"100px",defaultContent:""},
                {data: "chName",bSearchable:false,title:'昵称',bSortable:false,width:"100px",defaultContent:""},
                {data: function (e) {
                        if(!e.sex){
                            return '';
                        }else if(e.sex==1){
                            return '男';
                        }else if(e.sex==0){
                            return '女';
                        }
                    },bSearchable:false,title:'性别',bSortable:false,width:"50px"},
                {data: "phone",bSearchable:false,title:'手机',bSortable:false,width:"100px",defaultContent:""},
                {data: function (e) {
                        if (e.isReal==1){
                            return '已实名';
                        }else{
                            return '未实名';
                        }
                    },bSearchable:false,title:'实名状态',bSortable:false,width:"80px",defaultContent:""},
                {data: "createTimeStr",bSearchable:false,title:'创建时间',bSortable:false,width:"80px",defaultContent:""}
            ]
        });
    }

    function selectedUser(userId,chName){
        if($("#userId_"+userId).length > 0){
            uwayWarning("提示","该用户已选中");
            return;
        }
        var str = '<span id="userId_'+userId+'" title="'+userId+'" onclick="delSelectedUser(\'userId_'+userId+'\')">'+chName+'</span>';
        $("#selectedUserDiv").append(str);
    }

    function delSelectedUser(id){
        $("#" + id).remove();
    }

    //获取已选中对象的信息，并展示
    function getChooseInfo(){
        var userNames = "";
        var userIds = "";
        if($("#selectedUserDiv span").length > 0){
            $("#selectedUserDiv span").each(function(i,obj){
                userNames += $(obj).text() + ",";
                userIds += $(obj).attr("title") + ",";
            });
            userNames = userNames.substring(0,userNames.length - 1);
            userIds = userIds.substring(0,userIds.length - 1);
        }

        $("#sendUsername_info").val(userNames);
        $("#sendUserid_info").val(userIds);
        $("#chooseModal").modal("hide");
    }

    //新增的信息的校验与获取
    function getParamInfo(){
	    var messageType = $("#messageType_info").val();
        if(messageType == ""){
            uwayWarning("警告","请选择发送终端类型");
            return null;
        }
        var picUrl = $("#picUrl_info").val();
        var referenceInfo = $("#referenceInfo_info").val();
        if(referenceInfo == ""){
            uwayWarning("警告","请填写推送标题");
            return null;
        }
        var referenceDsc = $("#referentDsc_info").val();
        if(referenceDsc == ""){
            uwayWarning("警告","请填写推送内容");
            return null;
        }
        var openWay = $("#openWay_info").val();
        var type = null;
        var referenceUrl = null;
        if (openWay != -1) {
            if (openWay == 0) {
                type = 0;
            }else{
                type = 1;
            }
            referenceUrl = $("#referentUrl_info").val();
            if(referenceUrl == ""){
                uwayWarning("警告","请填写跳转地址或页面信息");
                return null;
            }
        }
        //获取新增内容的基本信息
        var param = {};
        param["messageType"] = messageType;
        param["imageurl"] = picUrl;
        param["title"] = referenceInfo;
        param["body"] = referenceDsc;
        param["activity"] = openWay;
        param["type"] = type;
        param["url"] = referenceUrl;
        var ids = $("#sendUserid_info").val();
        if (ids != ''){
            param["alias"] = ids;
        }
        return param;
    }

    //新增请求
    function addMessage(){
        var param = getParamInfo();
        if (param == null){
            return;
        }
        $.uwayProcess.show();
        $.ajax({
            url: "${contextPath}/message/push",
            async: true, type: "Post", dataType: "json",
            data: {param:JSON.stringify(param)},
            success: function (response) {
                $.uwayProcess.close();
                if (response.success) {
                    uwayNotice('通知',response.result);
                    goBack();
                }else{
                    uwayWarning('警告',response.message);
                    console.log(response.message);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log(textStatus);
                $.uwayProcess.close();
            }
        });
    }

    function goBack(){
        window.location.href = "${contextPath}/message/pushManagePage";
    }

    //文件上传
    function upload() {
        var t = setInterval(function() {
            //获取iframe标签里body元素里的文字。即服务器响应过来的"上传成功"或"上传失败"
            var word = $("iframe[id='uploadframe']").contents().find("body").text();
            var result;
            try {
                result = JSON.parse(word);
            } catch (e){
                console.log('等待上传请求返回信息......')
                return;
            }
            if (result.success == true) {
                var fName = result.result;
                $("#picUrl_info").val(fName)
                uwayNotice("通知", "上传成功！");
                clearInterval(t);
            } else {
                uwayNotice("通知", "上传失败！");
                clearInterval(t);
            }
        }, 1000);
    }

    // 从 file 域获取 本地图片 url
    function getFileUrl(sourceId) {
        var url;
        if (navigator.userAgent.indexOf("MSIE")>=1) { // IE
            url = document.getElementById(sourceId).value;
        } else if(navigator.userAgent.indexOf("Firefox")>0) { // Firefox
            if(document.getElementById(sourceId).files.length == 0){
                return "";
            }
            url = window.URL.createObjectURL(document.getElementById(sourceId).files.item(0));
        } else if(navigator.userAgent.indexOf("Chrome")>0) { // Chrome
            if(document.getElementById(sourceId).files.length == 0){
                return "";
            }
            url = window.URL.createObjectURL(document.getElementById(sourceId).files.item(0));
        }
        return url;
    }
    //将本地图片 显示到浏览器上
    function preImg(sourceId, targetId) {
        var url = getFileUrl(sourceId);
        $("#"+targetId).show();
        $("#"+targetId).attr("src",url);
        //每次更换时都将路径清空，在上传后填写
        $("#picUrl_info").val("");
        $("iframe[id='uploadframe']").contents().find("body").html("");
    }

</script>
</html>