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
			}
			.select-query{
				height: 30px;
				width: 150px;
			}
			.input-length-big{
				width: 50%;
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
						<li>赛事管理</li><li>小组管理</li>
					</ol>
				</div>
			</div>
			<div id="content">
				<div class="condition-wrapper">
					<div class="divform">
						<label for="matchId_q">赛事</label>
						<select id="matchId_q" class="select-query">
							<option value="">请选择</option>
							<c:forEach items="${matchNewsList}" var="item">
								<option value="${item.id}">${item.matchTitle}</option>
							</c:forEach>
						</select>
					</div>
					<div class="divform" style="display:none;">
						<label for="matchTitle_q">赛事名称</label>
						<input id="matchTitle_q" type="text" placeholder="请输入赛事名称"/>
					</div>
					<div class="divform">
						<label for="groupName_q">小组名称</label>
						<input id="groupName_q" type="text" placeholder="请输入小组名称"/>
					</div>

					<div class="divform">
						<label for="status_q">状态</label>
						<select id="status_q" class="select-query">

						</select>
					</div>

					<div class="divform">
						<button class="btn main-color-bluegreen" onclick="query()">查询</button>
						<fus:hasRight url="/news/addMatchGroup">
							<button class="btn main-color-bluegreen" onclick="addInfoModel()">新增小组</button>
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
						<h4 class="modal-title" id="modalTitle">小组</h4>
					</div>
					<div class="modal-body">
						<div style="width: 600px;margin: 0 auto;">
							<div style="display:none;">
								<input id="id_info" type="text" class="emptyInfo"/>
							</div>
							<div class="form-group">
								<label for="groupName_info">小组名称</label>
								<input type="text" id="groupName_info" class="emptyInfo input-length-big" placeholder="请输入小组名称" />
							</div>
							<div class="form-group">
								<label for="matchId_info">赛事</label>
								<select id="matchId_info" class="select-query input-length-big">
									<c:forEach items="${matchNewsList}" var="item">
										<option value="${item.id}">${item.matchTitle}</option>
									</c:forEach>
								</select>
							</div>

						</div>
					</div>
					<div class="modal-footer">
						<button id="addBtn" style="display:none;" class="btn main-color-bluegreen" onclick="addGroupInfo()">提交</button>
						<button id="updBtn" style="display:none;" class="btn main-color-bluegreen" onclick="updateGroupInfo()">提交</button>
						<button class="btn" onclick="$('#infoModal').modal('hide')">取消</button>
					</div>
				</div>
			</div>
		</div>

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
	var statusObj = {"0":"失效","1":"有效"};
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

	<fus:hasRight url="/news/addMatchGroup">
	function addInfoModel(){
	    // 初始化页面
		$(".emptyInfo").val("");
        $("#addBtn").show();
        $("#updBtn").hide();
		$("#infoModal").modal("show");
	}

	function addGroupInfo(){
	    var param = getParamAndCheck();
	    if(param == null){
	        return;
		}
        $.uwayProcess.show();
        $.ajax({
            url: "${contextPath}/news/addMatchGroup",
            async: true, type: "Post", dataType: "json",
            data: {"condition":JSON.stringify(param)},
            success: function (response) {
                $.uwayProcess.close();
                if(response.success){
                    $("#infoModal").modal("hide");
                    dataTable.draw(true);
                    uwayNotice('通知',"新增成功");
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

    <fus:hasRight url="/news/updateMatchGroupInfo">
    function openEditorModel(id){
        $.ajax({
            url: "${contextPath}/news/getMatchGroupInfo",
            async: true, type: "Post", dataType: "json",
            data: {"id":id},
            success: function (response) {
                $.uwayProcess.close();
                if(response.success){
                    $("#addBtn").hide();
                    $("#updBtn").show();
                    var result = response.result;
                    $("#id_info").val(result.id);
                    $("#matchTitle_info").val(result.matchTitle);
                    $("#matchId_info").val(result.matchId);
                    $("#groupName_info").val(result.groupName);
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

    function updateGroupInfo(){
        var param = getParamAndCheck();
        if(param == null){
            return;
        }
        var id = $("#id_info").val();
        if(id == ""){
            uwayWarning("警告","参数异常，刷新重试");
            return;
		}
        param.id = id;
        $.uwayProcess.show();
        $.ajax({
            url: "${contextPath}/news/updateMatchGroupInfo",
            async: true, type: "Post", dataType: "json",
            data: {"condition":JSON.stringify(param)},
            success: function (response) {
                $.uwayProcess.close();
                if(response.success){
                    $("#infoModal").modal("hide");
                    dataTable.draw(false);
                    uwayNotice('通知',"修改成功");
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

	function getParamAndCheck(){
		var matchId = $("#matchId_info").val();
	    var matchTitle = $("#matchId_info option:selected").text();
        if(matchId == ""){
            uwayWarning("提示","请选择赛事");
            return null;
        }
		var groupName = $("#groupName_info").val();
		if(groupName == ""){
		    uwayWarning("提示","请填写组名");
		    return null;
		}

        var param = {
            "matchTitle":matchTitle,"matchId":matchId,"groupName":groupName
		}
		return param;
	}

    //根据条件进行查询
    function query() {
        dataTable.ajax.reload();
    }

	function initTableData(){
        dataTable = $('#dataTable').DataTable({
            "scrollX": true,
            "processing": true,
            "serverSide": true,
            "ajax": {
                "url": "${contextPath}/news/getMatchGroupTable",
                "type": "post",
                "data": function (d) {
                    var param = {
						matchId : $("#matchId_q").val(),
						status : $("#status_q").val(),
						matchTitle : $("#matchTitle_q").val(),
						groupName : $("#groupName_q").val()
                    }
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
                    <fus:hasRight url="/news/updateMatchGroupInfo">
                    str += '<a onclick="openEditorModel('+ "'"+e.id+"'" +')">编辑</a>';
                    </fus:hasRight>

                    <fus:hasRight url="/news/updateMatchGroupBy">
                    if(e.status == 1){
                        str += '<a style="margin-left:10px;" onclick="openUpdateStatus('+ "'"+e.id+"','失效'" +',0)">失效</a>';
                    }else if(e.status == 0){
                        str += '<a style="margin-left:10px;" onclick="openUpdateStatus('+ "'"+e.id+"','有效'" +',1)">有效</a>';
                    }else{
                        str += '';
                    }
                    </fus:hasRight>
                    return str;
                },
                    title:'操作',width:"100px",
                    bSortable:false
                },
                {data: "id",bSearchable:false,title:'小组ID',width:"100px",defaultContent:""},
                {data: "matchTitle",bSearchable:true,title:'赛事标题',width:"400px",defaultContent:""},
                {data: "groupName",bSearchable:true,title:'小组名称',width:"200px",defaultContent:""},
                {data: "status",bSearchable:false,title:'状态',width:"100px",render:function(data){
                    return getObjValByKey(statusObj,data);
                }},
                {data: "inserttime",bSearchable:false,title:'创建时间',width:"100px",defaultContent:""}

            ],
            "order": [[ 1, "desc" ]],
        });
	}

    <fus:hasRight url="/news/updateMatchGroupBy">
	function openUpdateStatus(id,title,status){
	    var title = "是否确认" + title;
	    uwayConfirm({
			content:title,
			confirm:function(){
                updateGroupStatus(id,status);
			},
			cancel:function(){

			}
		});
	}

	function updateGroupStatus(id,status){
        $.uwayProcess.show();
        var param = {"id":id,"status":status};
        $.ajax({
            url: "${contextPath}/news/updateMatchGroupBy",
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
</script>
</body>
</html>