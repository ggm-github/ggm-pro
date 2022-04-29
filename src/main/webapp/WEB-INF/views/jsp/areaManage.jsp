<%@ taglib prefix="fus" uri="http://java.sun.com/jsp/jstl/funs" %>
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
			.form-group label{
				width:130px;
				text-align:right;
			}
			.form-group select{
				height:29px;
				width:150px;
			}
			.select-query{
				height: 30px;
				width: 150px;
			}
			.input-length{
				width: 150px;
			}
			.divform label{
				width: 60px;
				text-align: right;
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
						<li>APP通用管理</li><li>区域管理</li>
					</ol>
				</div>
			</div>
			<div id="content">
				<div class="condition-wrapper">
					<div class="divform">
						<label for="areaName_q">区域名称</label>
						<input id="areaName_q" class="input-length" type="text" />
					</div>
					<div class="divform">
						<label for="isHot_q">是否热门</label>
						<select id="isHot_q" class="select-query">

						</select>
					</div>
					<div class="divform">
						<label for="status_q">状态</label>
						<select id="status_q" class="select-query">

						</select>
					</div>

					<div class="divform">
						<button class="btn main-color-bluegreen" onclick="query()">查询</button>
						<fus:hasRight url="/areaIndustry/addAreaInfo">
							<button class="btn main-color-bluegreen" onclick="openAddModal()">新增</button>
						</fus:hasRight>
					</div>
				</div>

				<div id="tableRow" class="row" style="min-width:1000px;margin-top: 10px !important;">
					<table id="dataTable" class="table table-striped table-bordered">

					</table>
				</div>
			</div>

		</div>

		<div class="page-footer">

		</div>

		<div class="modal fade" id="infoModal" tabindex="-1" role="dialog" aria-labelledby="infoModalTitle" aria-hidden="false">
			<div class="modal-dialog" style="width:600px;">
				<div class="modal-content" >
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						&times;
						</button>
						<h4 class="modal-title" >编辑</h4>
					</div>
					<div class="modal-body">
						<div style="width: 600px;margin: 0 auto;">
							<div class="form-group">
								<label>区域ID:</label>
								<input id="areaId_info" type="number" class="numberInfo input-length" />
							</div>
							<div class="form-group">
								<label>父级ID:</label>
								<input id="parentId_info" type="number" class="numberInfo input-length" />
							</div>
							<div class="form-group">
								<label>区域名称:</label>
								<input id="areaName_info" type="text" class="emptyInfo input-length" />
							</div>
							<div class="form-group">
								<label>首字母:</label>
								<input id="letter_info" type="text" class="emptyInfo input-length" />
							</div>
							<div class="form-group">
								<label>是否热门:</label>
								<select id="isHot_info" class="select-query" onchange="hotChg();">

								</select>
							</div>
							<div class="form-group" id="hotDiv">
								<label>热门权重:</label>
								<input type="number" id="hotSort_info" class="numberInfo input-length" placeholder="请输入权重" />
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button id="addBtn" class="btn main-color-bluegreen" style="display:none;" onclick="addAreaInfo()">新增</button>
						<button id="updBtn" class="btn main-color-bluegreen" style="display:none;" onclick="updateAreaInfo()">修改</button>
						<button class="btn" onclick="$('#infoModal').modal('hide')">取消</button>
					</div>
				</div>
			</div>
		</div>

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

<script type="text/javascript">

	var statusObj = {"0":"失效","1":"有效"};
	var isHotObj = {"0":"否","1":"是"};
	var dataTable;
	$(function() {
		initSelect();
        initTableData();
	});

	function initSelect(){
	    var allstr = '<option value="">请选择</option>';
        str = "";
        $("#status_q").empty();
        $.each(statusObj,function(key,val){
            str += '<option value="'+key+'">'+val+'</option>';
        });
        $("#status_q").append(allstr + str);
        $("#status_q").val("1");
        str = "";
        $("#isHot_q").empty();
		$("#isHot_info").empty();
        $.each(isHotObj,function(key,val){
            str += '<option value="'+key+'">'+val+'</option>';
        });
        $("#isHot_q").append(allstr + str);
		$("#isHot_info").append(str);
        $("#isHot_q").val("");
		$("#isHot_info").val("0");
	}

    function getObjValByKey(obj,key){
        var val = obj[key];
        return val == null || val == undefined ? "" : val;
    }

    //根据条件进行查询
    function query() {
        dataTable.ajax.reload();
    }

	function initTableData(){
        dataTable = $('#dataTable').DataTable({
            "scrollX": false,
            "processing": true,
            "serverSide": true,
            "ajax": {
                "url": "${contextPath}/areaIndustry/getAreaTable",
                "type": "post",
                "data": function (d) {
                    var param = {
						areaName : $("#areaName_q").val(),
						status : $("#status_q").val(),
                        isHot : $("#isHot_q").val()
                    };
                    d.condition = JSON.stringify(param);
                },
                "error":function (e1,e2,e3) {
                    if(e1.responseText.indexOf("<!DOCTYPE html>") != -1){
                        window.location = window.localStorage.getItem("CONTEXT_PATH") + '/user/login';
                    }
                }
            },
            "columns":[
                {data: function(e){
                    var str = '';
                    <fus:hasRight url="/areaIndustry/updateAreaStatus">
                    if(e.status == 1){
                        str += '<a style="margin-left:10px;" onclick="openUpdateStatus(' + e.areaId + ',0)">无效</a>';
                    }else if(e.status == 0){
                        str += '<a style="margin-left:10px;" onclick="openUpdateStatus(' + e.areaId + ',1)">有效</a>';
                    }
					</fus:hasRight>
					<fus:hasRight url="/areaIndustry/updateAreaInfo">
                    str += '<a style="margin-left:10px;" onclick="openUpdateModel(' + e.areaId + ')">编辑</a>';
                    </fus:hasRight>
                    return str;
                },
                    title:'操作',
					width:"10%",
                    bSortable:false
                },
                {data: "areaId",bSearchable:false,title:'区域ID',width:"10%",defaultContent:""},
                {data: "areaName",bSearchable:true,title:'区域名称',width:"35%",defaultContent:""},
				{data: "letter",bSearchable:true,title:'首字母',width:"5%",defaultContent:""},
				{data: "parentId",bSearchable:true,title:'父级ID',width:"10%",defaultContent:""},
                {data: "isHot",bSearchable:false,title:'是否热门',width:"5%",render:function(data){
                    return getObjValByKey(isHotObj,data);
                }},
                {data: "hotSort",bSearchable:true,title:'热门权重',width:"10%",defaultContent:""},
                {data: "status",bSearchable:false,title:'状态',render:function(data){
                    return getObjValByKey(statusObj,data);
                },width:"10%"}
            ],
            "order": [[ 2, "desc" ]]
        });
	}

	function hotChg(){
		var isHot = $("#isHot_info").val();
		if (isHot == '0'){
			$("#hotDiv").hide();

		}else{
			$("#hotDiv").show();
		}
		$("#hotSort_info").val("0");
	}

	function getParamAndCheck(){
		var areaId = $("#areaId_info").val();
		if(areaId == ''){
			uwayWarning("提示","请填写区域ID");
			return null;
		}
		var parentId = $("#parentId_info").val();
		if(parentId == ''){
			uwayWarning("提示","请填写上级区域ID，若没有填0");
			return null;
		}
		var areaName = $("#areaName_info").val();
		if(areaName == ''){
			uwayWarning("提示","请填写区域名称");
			return null;
		}
		var isHot = $("#isHot_info").val();
		var hotSort = $("#hotSort_info").val();
		var letter = $("#letter_info").val();
		if(letter == ''){
			uwayWarning("提示","请填写首字母");
			return null;
		}
		var param = {
			"areaId":areaId,"parentId":parentId,"areaName":areaName,
			"isHot":isHot,"hotSort":hotSort,"letter":letter
		}
		return param;
	}

	<fus:hasRight url="/areaIndustry/addAreaInfo">
	function openAddModal(){
		$(".emptyInfo").val("");
		$(".numberInfo").val(0);
		$("#isHot_info").val("0");
		hotChg();
		$("#areaId_info").removeAttr("readonly");
		$("#addBtn").show();
		$("#updBtn").hide();
		$("#infoModal").modal("show");
	}

	function addAreaInfo(){
		var param = getParamAndCheck();
		if (param == null){
			return;
		}
		param.status = 1;
		$.uwayProcess.show();
		$.ajax({
			url: "${contextPath}/areaIndustry/addAreaInfo",
			async: true, type: "Post", dataType: "json",
			data: {"condition" : JSON.stringify(param)},
			success: function (response) {
				$.uwayProcess.close();
				if(response.success){
					$("#infoModal").modal("hide");
					dataTable.draw(true);
					uwayNotice('通知',"操作成功");
				}else{
					uwayWarning("警告",response.message);
				}
			},
			error: function (XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
				$.uwayProcess.close();
			}
		});
	}
	</fus:hasRight>

	<fus:hasRight url="/areaIndustry/updateAreaInfo">

	function openUpdateModel(id){
		$.ajax({
			url: "${contextPath}/areaIndustry/getAreaById",
			async: true, type: "Post", dataType: "json",
			data: {"areaId":id},
			success: function (response) {
				$.uwayProcess.close();
				if(response.success){
					$("#parentId_info").val(response.result.parentId);
					$("#areaName_info").val(response.result.areaName);
					$("#isHot_info").val(response.result.isHot);
					hotChg();
					$("#hotSort_info").val(response.result.hotSort);
					$("#letter_info").val(response.result.letter);
					$("#areaId_info").val(response.result.areaId);
					$("#areaId_info").attr("readonly","readonly");
					$("#addBtn").hide();
					$("#updBtn").show();
					$("#infoModal").modal("show");
				}else{
					uwayWarning("警告",response.message);
				}
			},
			error: function (XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
				$.uwayProcess.close();
			}
		});
	}


	function openUpdateInfo(){
		var title = "是否确认修改区域信息？";
		uwayConfirm({
			content:title,
			confirm:function(){
				updateAreaInfo();
			},
			cancel:function(){

			}
		});
	}

	function updateAreaInfo(){
		var param = getParamAndCheck();
		console.log(param);
		if (param == null){
			return;
		}
		$.uwayProcess.show();
		$.ajax({
			url: "${contextPath}/areaIndustry/updateAreaInfo",
			async: true, type: "Post", dataType: "json",
			data: {"condition":JSON.stringify(param)},
			success: function (response) {
				$.uwayProcess.close();
				if(response.success){
					$("#infoModal").modal("hide");
					dataTable.draw(false);
					uwayNotice('通知',"操作成功");
				}else{
					uwayWarning("警告",response.message);
				}
			},
			error: function (XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
				$.uwayProcess.close();
			}
		});
	}
	</fus:hasRight>


    <fus:hasRight url="/areaIndustry/updateAreaStatus">
	function openUpdateStatus(id,status){
        var title = "是否确认";
	    if(status == 1){
			title += "有效该区域";
		}else if(status == 0){
            title += "无效该区域";
		}else{
		    uwayWarning("警告","视频状态异常");
		    return;
		}
	    uwayConfirm({
			content:title,
			confirm:function(){
			    var param = {status : status};
                updateAreaStatus(id,param);
			},
			cancel:function(){

			}
		});
	}

	function updateAreaStatus(id,param){
        $.uwayProcess.show();
        param.areaId = id;
        $.ajax({
            url: "${contextPath}/areaIndustry/updateAreaStatus",
            async: true, type: "Post", dataType: "json",
            data: param,
            success: function (response) {
                $.uwayProcess.close();
                if(response.success){
                    $("#infoModal").modal("hide");
                    dataTable.draw(false);
                    uwayNotice('通知',"操作成功");
                }else{
                    uwayWarning("警告",response.message);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log(textStatus);
                $.uwayProcess.close();
            }
        });
	}
	</fus:hasRight>

</script>
</body>
</html>