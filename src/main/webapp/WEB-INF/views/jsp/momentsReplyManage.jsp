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
		<link rel="stylesheet" type="text/css" media="screen" href="${ctxStatic}/js/jstree/style.css${jsversion}">
        <style>
			.form-group label{
				width:130px;
				text-align:right;
			}
			.form-group .info-show{
				text-align : left;
				width: 60%;
				display: inline-block;
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
				width:150px;
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
						<li>动态管理</li><li>评论管理</li>
					</ol>
				</div>
			</div>
			<div id="content">
				<div class="condition-wrapper">
					<div style="display:none;">
						<input type="text" id="openOptionType" value="${queryType}" />
						<input type="text" id="replyId_q" value="0" />
					</div>
					<div class="divform">
						<label for="id_q">编号</label>
						<input id="id_q" type="text" class="input-length" placeholder="请输入编号"/>
					</div>
					<div class="divform">
						<label for="replyContent_q">内容</label>
						<input id="replyContent_q" type="text" class="input-length" placeholder="请输入内容"/>
					</div>
					<div class="divform">
						<label for="status_q">状态</label>
						<select id="status_q" class="select-query">

						</select>
					</div>
					<div class="divform">
						<label class="btn main-color-bluegreen" style="margin-left: 15px; width: 80px" onclick="query()"> 查 询</label>
						<label id="backBtn" class="btn main-color-bluegreen" style="margin-left: 15px; width: 80px" onclick="query(0)"> 返回评论</label>
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

		<div class="modal fade" id="modifyModal" tabindex="-1" role="dialog" aria-labelledby="infoModalModalTitle" aria-hidden="false" backdrop="false" style="z-index:1000;">
			<div class="modal-dialog" style="width:600px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
							&times;
						</button>
						<h4 class="modal-title">编辑</h4>
					</div>
					<div class="modal-body">
						<div style="width: 100%;max-height:400px;margin: 0 auto;overflow-y: auto;">
							<div style="display:none;">
								<input id="id_info" type="text" >
							</div>
							<div class="form-group">
								<label>权重:</label>
								<input type="number" id="sort_info" value="0" />
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button class="btn main-color-bluegreen" onclick="updateInfo()">提交</button>
						<button class="btn" onclick="$('#modifyModal').modal('hide')">取消</button>
					</div>
				</div>
			</div>
		</div>

		<div class="modal fade" id="checkModal" tabindex="-1" role="dialog" aria-labelledby="infoModalModalTitle" aria-hidden="false" backdrop="false" style="z-index:1000;">
			<div class="modal-dialog" style="width:600px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
							&times;
						</button>
						<h4 class="modal-title">取消发布</h4>
					</div>
					<div class="modal-body">
						<div style="width: 100%;max-height:400px;margin: 0 auto;overflow-y: auto;">
							<div style="display:none;">
								<input id="id_check" type="text" />
								<input id="status_check" type="text" />
							</div>
							<div class="form-group">
								<label>备注:</label>
								<input type="text" id="remarks_check" class="input-length"/>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button class="btn main-color-bluegreen" onclick="openUpdateStatusConfig()">提交</button>
						<button class="btn" onclick="$('#modifyModal').modal('hide')">取消</button>
					</div>
				</div>
			</div>
		</div>

		<div class="modal fade" id="infoModal" tabindex="-1" role="dialog" aria-labelledby="infoModalModalTitle" aria-hidden="false" backdrop="false" style="z-index:1000;">
			<div class="modal-dialog" style="width:600px;">
				<div class="modal-content" id="update-user-password-form">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						&times;
						</button>
						<h4 class="modal-title" id="modalTitle">查看</h4>
					</div>
					<div class="modal-body">
						<div style="width: 100%;max-height:400px;margin: 0 auto;overflow-y: auto;">
							<div class="form-group">
								<label>回复内容:</label>
								<span id="replyContent_show" class="info-show"></span>
							</div>
							<div class="form-group">
								<label>回复人:</label>
								<span id="operUserChName_show"  class="info-show"></span>
							</div>
							<div class="form-group">
								<label>回复时间:</label>
								<span id="inserttime_show"  class="info-show"></span>
							</div>
							<div class="form-group">
								<label>当前状态:</label>
								<span id="status_show"  class="info-show"></span>
							</div>
							<div class="form-group">
								<label>备注:</label>
								<span id="remarks_show"  class="info-show"></span>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button class="btn" onclick="$('#infoModal').modal('hide')">取消</button>
					</div>
				</div>
			</div>
		</div>

		<iframe id="imageframe" name="imageframe" style="display: none;"></iframe>

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
<script src="${ctxStatic}/js/jstree/jstree.min.js${jsversion}"></script>

<script type="text/javascript">
	var statusObj = {"0":"未发布","1":"已发布"};
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
        $("#status_q").val("");
	}

    function getObjValByKey(obj,key){
        var val = obj[key];
        return val == null || val == undefined ? "" : val;
    }

	function queryParam(){
		var replyId = $("#replyId_q").val();
		if (replyId == ''){
			replyId = "0";
		}
		if (replyId == "0"){
			$("#backBtn").hide();
		} else {
			$("#backBtn").show();
		}
		var param = {
			id : $("#id_q").val(),
			status : $("#status_q").val(),
			replyContent : $("#replyContent_q").val(),
			replyId : replyId
		};
		return param;
	}

    //根据条件进行查询
    function query(replyId) {
		if (replyId != undefined){
			$("#replyId_q").val(replyId);
		}
        dataTable.ajax.reload();
    }

	function initTableData(){
        dataTable = $('#dataTable').DataTable({
            "scrollX": true,
            "processing": true,
            "serverSide": true,
			"order": [[1,"desc"]],
            "ajax": {
                "url": "${contextPath}/reply/getMomentsReplyTable",
                "type": "post",
                "data": function (d) {
                    var param = queryParam();
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
					<fus:hasRight url="/reply/updateMomentsReplyInfo">
						str += '<a style="margin-left:10px;" onclick="goToShowInfoPage('+e.id+',\'modify\')">编辑</a>';
					</fus:hasRight>
                    <fus:hasRight url="/reply/updateMomentsReplyStatus">
                    if(e.status == 0) {
						str += '<a style="margin-left:10px;" onclick="openUpdateStatus(' + e.id + ',1)">重新发布</a>';
					}else if(e.status == 1){
                        str += '<a style="margin-left:10px;" onclick="openUpdateStatus(' + e.id + ',0)">取消发布</a>';
                    }
                    </fus:hasRight>
					str += '<a style="margin-left:10px;" onclick="goToShowInfoPage('+ "'"+e.id+"'" +',\'show\')">查看</a>';
                    return str;
                },
                    title:'操作',
					width:"80px",
                    bSortable:false
                },
                {data: "id",bSearchable:false,title:'编号',width:"80px",defaultContent:""},
                {data: "sort",bSearchable:true,title:'权重',width:"50px",defaultContent:""},
				{data: "replyContent",bSearchable:false,title:'评论内容',render:function(data){
						if (data == null){
							return "";
						}
						if (data.length < 30){
							return data;
						}
						return '<span title="'+data+'">'+data.substring(0,28)+'......</span>';
				},width:"350px"},
				{data: "operUserChName",bSearchable:false,title:'账号',width:"100px",defaultContent:""},
                {name: "replyCnt",bSearchable:false,title:'跟帖回复',width:"80px",bSortable:true,data:function(e){
                		if (e.replyCnt == null || e.replyCnt == 0){
                			return 0;
						}
                		return '<a onclick="query('+e.id+')">'+e.replyCnt+'</a>';
				}},
                {data: "status",bSearchable:false,title:'状态',render:function(data){
                    return getObjValByKey(statusObj,data);
                },width:"80px"},
				{data: "inserttime",bSearchable:false,title:'时间',width:"200px",defaultContent:""}
            ]
        });
	}

	function goToShowInfoPage(id,operType){
		$.uwayProcess.show();
		$.ajax({
			url: "${contextPath}/reply/getMomentsReplyById",
			async: true, type: "Post", dataType: "json",
			data: {"id":id},
			success: function (response) {
				$.uwayProcess.close();
				if(response.success){
					var data = response.result;
					if (operType == "show"){
						$("#replyContent_show").text(data.replyContent);
						$("#operUserChName_show").text(data.operUserChName);
						$("#inserttime_show").text(data.inserttime);
						$("#status_show").text(getObjValByKey(statusObj,data.status));
						$("#infoModal").modal("show");
					}else{
						$("#id_info").val(data.id);
						$("#sort_info").val(data.sort);
						$("#modifyModal").modal("show");
					}
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

	<fus:hasRight url="/reply/updateMomentsReplyInfo">
	function updateInfo(){
		var param = {"id":$("#id_info").val(),"sort":$("#sort_info").val()};
		$.uwayProcess.show();
		$.ajax({
			url: "${contextPath}/reply/updateMomentsReplyInfo",
			async: true, type: "Post", dataType: "json",
			data: param,
			success: function (response) {
				$.uwayProcess.close();
				if(response.success){
					$("#modifyModal").modal("hide");
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

    <fus:hasRight url="/reply/updateMomentsReplyStatus">
	function openUpdateStatus(id,status){
		$("#id_check").val(id);
		$("#status_check").val(status);
	    if (status == 0){
	    	$("#checkModal").modal("show");
		} else {
			openUpdateStatusConfig();
		}
	}

	function openUpdateStatusConfig(){
		var title;
		var id = $("#id_check").val();
		var status = $("#status_check").val();
		var remarks = $("#remarks_check").val();
		if (status == 1){
			title = "是否确认重新发布";
		} else {
			title = "是否确认取消发布";
		}
		uwayConfirm({
			content:title,
			confirm:function(){
				updateInfoStatus(id,status,remarks);
			},
			cancel:function(){

			}
		});
	}

	function updateInfoStatus(id,status,remarks){
        var param = {"id":id,"status":status,"remarks":remarks};
        $.uwayProcess.show();
        $.ajax({
            url: "${contextPath}/reply/updateMomentsReplyStatus",
            async: true, type: "Post", dataType: "json",
            data: param,
            success: function (response) {
                $.uwayProcess.close();
                if(response.success){
                    $("#checkModal").modal("hide");
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