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
						<li>赛事管理</li><li>参数管理</li>
					</ol>
				</div>
			</div>
			<div id="content">
				<div class="condition-wrapper">
					<div class="divform">
						<label for="commDes_q">描述</label>
						<input id="commDes_q" type="text" />
					</div>

					<div class="divform">
						<button class="btn main-color-bluegreen" onclick="query()">查询</button>
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
			<div class="modal-dialog" style="width:900px;">
				<div class="modal-content" >
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						&times;
						</button>
						<h4 class="modal-title" id="modalTitle">编辑</h4>
					</div>
					<div class="modal-body">
						<div style="width: 900px;margin: 0 auto;">
							<div style="display:none;">
								<input id="id_info" type="text" class="emptyInfo"/>
							</div>
							<div class="form-group">
								<label for="paramName_info">参数内容</label>
								<textarea type="text" id="paramName_info" class="emptyInfo input-length-big" rows="5" ></textarea>
							</div>
							<div class="form-group">
								<div style="margin-left:125px;">
									<a style="margin-left:10px;" onclick="addTagInParam('#INFO=RANKING_NUM#')">插入排名</a>
									<a style="margin-left:10px;" onclick="addTagInParam('#INFO=VOTE_CNT#')">插入投票数</a>
									<a style="margin-left:10px;" onclick="addTagInParam('#INFO=VOTE_DIFF_CNT_LAST#')">插入与上一名投票数差值</a>
								    <br/>
									<a style="margin-left:10px;" onclick="addTagInParam('#INFO=WEEK_RANGE_M#')">插入上周时间范围（m.d）</a>
									<a style="margin-left:10px;" onclick="addTagInParam('#INFO=RANKING_TYPE#')">插入排名浮动类型（上升、下降、不变）</a>
									<br/>
									<a style="margin-left:10px;" onclick="addTagInParam('#INFO=RANKING_NUM_CHANGE#')">插入排名浮动值</a>
									<a style="margin-left:10px;" onclick="addTagInParam('#INFO=MATCH_TITLE#')">插入赛事活动标题</a>
								</div>
							</div>

						</div>
					</div>
					<div class="modal-footer">
						<button id="updBtn" class="btn main-color-bluegreen" onclick="updateConfigureInfo()">提交</button>
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
        initTableData();
	});

	function addTagInParam(value){
		var paramName = $("#paramName_info").val();
		$("#paramName_info").val(paramName + value);
		$("#paramName_info").focus();
	}

    <fus:hasRight url="/configure/updatePubConfigure">
    function openEditorModel(id){
        $.ajax({
            url: "${contextPath}/configure/getPubConfigureById",
            async: true, type: "Post", dataType: "json",
            data: {"id":id},
            success: function (response) {
                $.uwayProcess.close();
                if(response.success){
                    var result = response.result;
                    $("#id_info").val(result.id);
                    $("#paramName_info").val(result.paramName);
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

    function updateConfigureInfo(){
    	var paramName = $("#paramName_info").val();
    	if (paramName == ""){
    		uwayWarning("提示","请填写参数内容");
    		return;
		}
		var param = {
			id : $("#id_info").val(),
			paramName : paramName
		};
		updateConfigureForRequest(param,1);
    }

	function updateConfigureTag(id,tag){
		var param = {
			id : id,
			paramValue : tag
		};
		updateConfigureForRequest(param,1);
	}

    function updateConfigureForRequest(param,type){
		$.uwayProcess.show();
		$.ajax({
			url: "${contextPath}/configure/updatePubConfigure",
			async: true, type: "Post", dataType: "json",
			data: {"condition":JSON.stringify(param)},
			success: function (response) {
				$.uwayProcess.close();
				if(response.success){
					if (type == 1){
						$("#infoModal").modal("hide");
					}
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
                "url": "${contextPath}/configure/getPubConfigureTable",
                "type": "post",
                "data": function (d) {
                    var param = {
						status : 1,
						commDes : $("#commDes_q").val()
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
						var commType = e.commType;
						<fus:hasRight url="/configure/updatePubConfigure">
						if (commType == "1"){
							str += '<a onclick="openEditorModel('+ "'"+e.id+"'" +')">编辑</a>';
						}else if (commType == "2"){
							var paramValue = e.paramValue;
							if (paramValue == "1") {
								str += '<a onclick="updateConfigureTag('+ "'"+e.id+"'" +',0)">关闭</a>';
							}else if(paramValue == "0"){
								str += '<a onclick="updateConfigureTag('+ "'"+e.id+"'" +',1)">开启</a>';
							}
						}
						</fus:hasRight>
						return str;
					}, title:'操作',width:"50px", bSortable:false
                },
                {data: "id",bSearchable:false,title:'编号',width:"50px",defaultContent:""},
                {data: "commDes",bSearchable:true,title:'参数描述',width:"100px",defaultContent:""},
				{data: function(e){
						var commType = e.commType;
						var paramValue = e.paramValue;
						if (paramValue == null){
						    paramValue = "";
                        }
						if (commType == 2){
							if (paramValue == "1"){
								return "开启";
							} else if (paramValue == "0"){
								return "关闭";
							}
						}
						return paramValue;
					}, title:'参数值',width:"100px", bSortable:false
				},
                {data: "paramName",bSearchable:true,title:'参数内容',width:"400px",render:function(data){
                		if(data == null){
                			return "";
						}else if(data.length > 50){
                			return '<span title="'+data+'">' + data.substring(0,50) + "......</span>";
						}
						return data;
					}},
				{data: "remarks",bSearchable:true,title:'备注',width:"400px",render:function(data){
						if(data == null){
							return "";
						}else if(data.length > 50){
							return '<span title="'+data+'">' + data.substring(0,50) + "......</span>";
						}
						return data;
					}}

            ],
            "order": [[ 1, "asc" ]]
        });
	}
</script>
</body>
</html>