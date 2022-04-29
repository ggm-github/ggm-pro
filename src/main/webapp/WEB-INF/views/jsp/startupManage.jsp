<%@ taglib prefix="fus" uri="http://java.sun.com/jsp/jstl/funs" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="background:url(${ctxStatic}/img/sysBg.png)">
	<head>
		<meta charset="utf-8">
		<title>启动图管理</title>
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
            .form-group label{
                width:130px;
                text-align:right;
            }
            .select-query,.input-length{
                height: 30px;
                width: 150px;
            }
            .input-length-big{
                height: 30px;
                width: 260px;
            }

            .note-info{
                color: red;
                width: 60%;
                text-align: left;
                font-size: 12px;
                display: inline-block;
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
						<li>内容管理</li><li>启动图管理</li>
					</ol>
				</div>			
			</div>
            <div id="content">
                <!-- 查询条件DIV -->
                <div style="margin-top: 0px;margin-bottom: 10px;" >
                    <form id="logQuery" name="logQuery" style="min-width:1000px;" >
                        <div style="display: inline-block;">
                            <label style="margin-left: 10px;">类型：</label>
                            <select id="pictureType_q" class="select-query">

                            </select>
                        </div>
                        <div style="display: inline-block;">
                            <label style="margin-left: 10px">打卡方式：</label>
                            <select id="openWay_q" class="select-query">

                            </select>
                        </div>
                        <label style="display: inline-block;">
                            <label style="margin-left: 10px">状态：</label>
                            <select id="status_q" class="select-query">

                            </select>
                            <label class="btn main-color-bluegreen" style="margin-left: 15px; width: 80px" onclick="query()"> 查 询</label>
                            <fus:hasRight url="/comm/addStartUpPicture">
                                <label class="btn main-color-bluegreen" style="margin-left: 15px; width: 80px" onclick="toAddPage()">新 增</label>
                            </fus:hasRight>
                        </label>
                    </form>
                </div>

                <div id="tableRow" class="row" style="min-width: 1000px;">
                    <table id="dataTable" class="table table-striped table-bordered">

                    </table>
                </div>

            </div>
			
		</div>

        <div class="modal fade" id="infoModal" tabindex="-1" role="dialog" aria-labelledby="infoModalTitle" aria-hidden="false" style="z-index:1000;">
            <div class="modal-dialog" style="width:600px;">
                <div class="modal-content" >
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                            &times;
                        </button>
                        <h4 class="modal-title">编辑</h4>
                    </div>
                    <div class="modal-body">
                        <div style="width: 100%;max-height:400px;margin: 0 auto;overflow-y: auto;">
                            <div style="display:none;">
                                <input id="id_info" type="text" class="emptyInfo"/>
                                <input id="pictureUrl_info" type="text" class="emptyInfo" />
                            </div>

                            <div class="form-group">
                                <label>类型：</label>
                                <select id="pictureType_info" class="select-query">

                                </select>
                            </div>

                            <div class="form-group">
                                <label>打卡方式：</label>
                                <select id="openWay_info" class="select-query" onchange="openWayChg();">

                                </select>
                            </div>

                            <div class="form-group">
                                <label>访问地址:</label>
                                <input type="text" id="visitUrl_info" class="input-length-big emptyInfo" >
                            </div>

                            <div class="form-group">
                                <form method="post" action="/comm/upload"  enctype="multipart/form-data"  target="uploadframe">
                                    <label style="">图片选择：</label>
                                    <label style="width:210px;">
                                        <input style="width: 200px" type="file" name="myfile" id="myfile" value="" class="emptyInfo"  onchange="preImg(this.id,'imgShow','pictureUrl_info','uploadframe');" />
                                        <input type="hidden" name="path" value="prefix.upload.path.startup"/>
                                    </label>
                                    <label style="width: 8%;min-width: 55px;">
                                        <input class="btn main-color-bluegreen" type="submit" value="上传" onclick="upload('uploadframe','pictureUrl_info')"/>
                                    </label>
                                </form>
                            </div>
                            <div class="form-group" style="text-align:center; ">
                                <img id="imgShow" src="" style="display:none;max-width:200px;"/>
                            </div>

                            <div class="form-group">
                                <label>备注：</label>
                                <input id="remarks_info" class="input-length-big emptyInfo" />
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <input id="addBtn" class="btn main-color-bluegreen" type="button" value="新增" onclick="addDataInfo();"/>
                        <input id="editBtn" class="btn main-color-bluegreen" type="button" value="修改" onclick="updateDataInfo();"/>
                        <button class="btn" onclick="$('#infoModal').modal('hide')">取消</button>
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

    var table;
    var statusObj = {"0":"无效","1":"有效"};
    var openWayObj = {'-1':'无跳转','0':'链接地址', '1':'赛事活动详情',"14":"话题详情","18":"动态详情"};
    var pictureTypeObj = {"0":"苹果审核","1":"开机启动画面","2":"首页展示画面"};
	$(document).ready(function(){
	    //初始化类型下拉
        initSelect();
        //初始化dataTables
        initDataTable();
    });

	function initSelect(){
        var typeStr = '<option value="">全部</option>';
        var str = '';
        $.each(pictureTypeObj,function(key,val){
            str += '<option value="'+key+'">'+val+'</option>';
        });
        $("#pictureType_q").empty();
        $("#pictureType_q").append(typeStr + str);
        $("#pictureType_info").empty();
        $("#pictureType_info").append(typeStr + str);

        str = '';
        $.each(openWayObj,function(key,val){
            str += '<option value="'+key+'">'+val+'</option>';
        });
        $("#openWay_q").empty();
        $("#openWay_q").append(typeStr + str);
        $("#openWay_info").empty();
        $("#openWay_info").append(str);

        str = '';
        $.each(statusObj,function(key,val){
            str += '<option value="'+key+'">'+val+'</option>';
        });
        $("#status_q").empty();
        $("#status_q").append(typeStr + str);
        $("#status_q").val(1);
    }


    function query(){
        table.ajax.reload();
    }

    //dataTable Ajax请求
    function initDataTable(){
        table = $('#dataTable').DataTable({
            "scrollX": true,
            "processing": true,
            "serverSide": true,
            "order": [[ 6, "desc" ]],
            "ajax": {
                "url": "${contextPath}/comm/getStartUpPictureList",
                "type": "POST",
                "data": function (d) {
                    $.uwayProcess.show();
                    var condition = {};
                    condition.pictureType = $("#pictureType_q").val();
                    condition.status = $("#status_q").val();
                    condition.openWay = $("#openWay_q").val();
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
                    var operStr = '';
                    <fus:hasRight url="/comm/updateStartUpPictureInfo">
                        operStr += '<a href="javascript:void(0);" onclick="getDataInfo('+e.pictureId+')">修改</a>&nbsp;&nbsp;&nbsp;';
                    </fus:hasRight>
                    <fus:hasRight url="/comm/updateStartUpPictureStatus">
                        var sts = e.status;
                        if(sts == 1){
                            operStr += '<a href="javascript:void(0);" onclick="openStatusConfirm(0,\''+e.pictureId+'\');">失效</a>';
                        }
                    </fus:hasRight>
                    return operStr;
                },
                    title:'操作',
                    bSortable:false
                },
                {data: "pictureUrl",bSearchable:false,title:'图片',render:function(data){
                        if (data != null && data != ''){
                            return '<img src="<%=Global.getConfig("prefix.visit.path")%>' + data + '" style="max-width: 120px;max-height: 90px;" />';
                        }
                        return "";
                    },width:"150px",defaultContent:""},
                {data: "pictureType",bSearchable:false,title:'类型',render:function(data){
                    return pictureTypeObj[data];
                },width:"80px",defaultContent:""},
                {data: "openWay",bSearchable:false,render:function(data){
                    return openWayObj[data];
                 },title:'打开方式',width:"80px",defaultContent:""},
                {data: "visitUrl",bSearchable:false,title:'访问位置',width:"350px",defaultContent:""},
                {data: "status",bSearchable:false,title:'状态',render:function(data){
                    return statusObj[data];
                 },width:"80px"},
                {data: "createTime",bSearchable:false,title:'创建时间',width:"150px",defaultContent:""}

            ]
        });
    }


    function toAddPage(){
        $(".emptyInfo").val("");
        $("#pictureType_info").val("");
        $("#openWay_info").val("-1");
        openWayChg();
        $("#pictureType_info").attr("disabled",false);
        $("#myfile").val("");
        $("#imgShow").hide();
        $("#addBtn").show();
        $("#editBtn").hide();
        $("#infoModal").modal("show");
    }

    <fus:hasRight url="/comm/updateStartUpPictureStatus">
    function openStatusConfirm(statusParam,id){
        var content = "";
        if(statusParam == 0){
            content = '确定要下架吗？';
        }else if(statusParam == 1){
            content = '确定要上架吗？';
        }
        uwayConfirm({
            content:content,
            confirm:function(){
                var param = '{"pictureId":"'+id+'","statusParam":"'+statusParam+'"}';
                updateDateStatus({"param":param});
            },
            cancel:function(){

            }
        });
    }

    function updateDateStatus(data){
        $.uwayProcess.show();
        $.ajax({
            url: "${contextPath}/comm/updateStartUpPictureStatus",
            async: true, type: "Post", dataType: "json",
            data: data,
            success: function (response) {
                $.uwayProcess.close();
                if (response.success) {
                    uwayNotice('通知',response.result);
                    table.draw(false);
                }else{
                    uwayNotice('通知',"操作失败");
                    console.log(response.message);
                }

            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log(textStatus);
                $.uwayProcess.close();
            }
        });
    }
    </fus:hasRight>


    //新增的信息的校验与获取
    function getParamInfo(){
        var pictureType = $("#pictureType_info").val();
        var openWay = $("#openWay_info").val();
        var remarks = $("#remarks_info").val();
        var pictureUrl = $("#pictureUrl_info").val();
        var visitUrl = $("#visitUrl_info").val();
        if(openWay != '-1' && visitUrl == ""){
            uwayWarning("警告","请填写跳转路径或页面信息");
            return null;
        }
        if (pictureType == ""){
            uwayWarning("警告","请选择类型");
            return null;
        }
        if(pictureType != '0' && pictureUrl == ""){
            uwayWarning("警告","请上传图片");
            return null;
        }
        //获取新增内容的基本信息
        var param = {};
        param["pictureType"] = pictureType;
        param["openWay"] = openWay;
        param["remarks"] = remarks;
        param["pictureUrl"] = pictureUrl;
        param["visitUrl"] = visitUrl;
        return param;
    }
    <fus:hasRight url="/comm/addStartUpPicture">
    //新增请求
    function addDataInfo(){
        var param = getParamInfo();
        if(param == null){
            return;
        }
        $.uwayProcess.show();
        $.ajax({
            url: "${contextPath}/comm/addStartUpPicture",
            async: true, type: "Post", dataType: "json",
            data: {param:JSON.stringify(param)},
            success: function (response) {
                $.uwayProcess.close();
                if (response.success) {
                    uwayNotice('通知',"新增成功");
                    table.draw(true);
                    $("#infoModal").modal("hide");
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
    </fus:hasRight>
    //基本信息查询
    function getDataInfo(id){
        $.uwayProcess.show();
        $.ajax({
            url: "${contextPath}/comm/getStartUpPactureInfoById",
            async: true, type: "Post", dataType: "json",
            data: {id:id},
            success: function (response) {
                console.log(response);
                if (response.success) {
                    $.uwayProcess.close();
                    var result = response.result;
                    console.log(result);
                    //回填值
                    $("#pictureType_info").val(result.pictureType);
                    $("#pictureType_info").attr("disabled",true);
                    $("#openWay_info").val(result.openWay);
                    openWayChg();
                    $("#visitUrl_info").val(result.visitUrl);
                    $("#remarks_info").val(result.remarks);
                    var picUrl = result.pictureUrl;
                    $("#pictureUrl_info").val(picUrl);
                    $("#id_info").val(result.pictureId);
                    $("#imgShow").attr("src",'<%=Global.getConfig("prefix.visit.path")%>'+picUrl);
                    $("#imgShow").show();
                    $("#addBtn").hide();
                    $("#editBtn").show();
                    $("#infoModal").modal("show");
                }else{
                    console.log(response.message);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log(textStatus);
                $.uwayProcess.close();
            }
        });
    }
    <fus:hasRight url="/comm/updateStartUpPictureInfo">
    //修改请求
    function updateDataInfo(){
        var param = getParamInfo();
        if(param == null){
            return;
        }
        var id = $("#id_info").val();
        param["pictureId"] = id;
        $.uwayProcess.show();
        $.ajax({
            url: "${contextPath}/comm/updateStartUpPictureInfo",
            async: true, type: "Post", dataType: "json",
            data: {param:JSON.stringify(param)},
            success: function (response) {
                $.uwayProcess.close();
                if (response.success) {
                    uwayNotice('通知',"修改成功");
                    table.draw(false);
                    $("#infoModal").modal("hide");
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
    </fus:hasRight>

    function openWayChg(){
        var openWay = $("#openWay_info").val();
        if (openWay == -1){
            $("#visitUrl_info").attr("disabled",true);
        } else{
            $("#visitUrl_info").attr("disabled",false);
        }
    }

    //文件上传
    function upload(iframeId,imgPutId) {
        var t = setInterval(function() {
            //获取iframe标签里body元素里的文字。即服务器响应过来的"上传成功"或"上传失败"
            var word = $("iframe[id='"+iframeId+"']").contents().find("body").text();
            var result;
            try {
                result = JSON.parse(word);
            } catch (e){
                console.log('等待上传请求返回信息......')
                return;
            }
            if (result.success == true) {
                var fName = result.result;
                $("#" + imgPutId).val(fName)
                uwayNotice("通知", "上传成功！");
                clearInterval(t);
            }
            else {
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
    function preImg(sourceId, targetId,imgInputId,iframeId) {
        var url = getFileUrl(sourceId);
        $("#"+targetId).show();
        $("#"+targetId).attr("src",url);
        //每次更换时都将路径清空，在上传后填写
        $("#" + imgInputId).val("");
        $("iframe[id='"+iframeId+"']").contents().find("body").html("");
    }
</script>
</html>