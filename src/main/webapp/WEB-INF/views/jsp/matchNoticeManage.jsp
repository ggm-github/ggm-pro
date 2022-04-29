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
			.form-group .rule-tips{
				color : red;
				text-align : left;
				font-size : 12px;
				width: 50%;

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
			.input-length-small{
				width:50px;
			}
			.input-length-big{
				width:60%;
			}
            .modal-backdrop{
                z-index:999;
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
						<li>赛事管理</li><li>活动通知管理</li>
					</ol>
				</div>
			</div>
			<div id="content">
				<div class="condition-wrapper">
					<div style="display:none;">
						<input type="text" id="openOptionType" value="${queryType}" />
					</div>
					<div class="divform">
						<label for="id_q">编号</label>
						<input id="id_q" type="text" class="input-length" placeholder="请输入编号"/>
					</div>
					<div class="divform">
						<label for="status_q">状态</label>
						<select id="status_q" class="select-query">

						</select>
					</div>
					<div class="divform">
						<label for="noticeTitle_q">标题</label>
						<input id="noticeTitle_q" type="text" class="input-length" placeholder="标题"/>
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

		<div class="modal fade" id="infoModal" tabindex="-1" role="dialog" aria-labelledby="infoModalModalTitle" aria-hidden="false" backdrop="false" style="z-index:1000;">
			<div class="modal-dialog" style="width:800px;">
				<div class="modal-content" id="update-user-password-form">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						&times;
						</button>
						<h4 class="modal-title" id="modalTitle">通知</h4>
					</div>
					<div class="modal-body">
						<div style="width: 100%;max-height:400px;margin: 0 auto;overflow-y: auto;">
							<div style="display:none;">
								<input id="id_check" type="text" class="emptyInfo"/>
								<input id="noticeType_check" type="text" class="emptyInfo"/>
							</div>

							<div id="showInfoDiv">

							</div>

							<div class="form-group checkRemarkDiv">
								<label for="remarks_check">备注:</label>
								<select id="remarksSel" class="select-query" onchange="remaksSelChg();">
									<option value="">请选择</option>
									<option value="内容不得包含辱骂色情暴力信息">内容不得包含辱骂色情暴力信息</option>
									<option value="内容不得包含营销广告信息">内容不得包含营销广告信息</option>
									<option value="内容疑存在虚假信息">内容疑存在虚假信息</option>
									<option value="内容存在错别字、表意不清">内容存在错别字、表意不清</option>
									<option value="0">其他</option>
								</select>
							</div>
							<div class="form-group" style="display:none;">
								<label></label>
								<input type="text" id="remarks_check" class="emptyInfo input-length-big" />
							</div>

							<div class="form-group showDiv">
								<label>审核结果:</label>
								<span id="auditResult_show"></span>
							</div>
							<div class="form-group showDiv">
								<label>审核备注:</label>
								<span id="remarks_show"></span>
							</div>

						</div>
					</div>
					<div class="modal-footer" id="btnDiv">
						<button class="btn main-color-bluegreen" onclick="updateInfoForCheck()">提交</button>
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
	var statusObj = {"0":"待审核","1":"发送中","2":"发送完成","3":"审核不通过"};
	var noticeTypeObj = {"1":"APP消息","2":"手机短信","3":"智能电话"};
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
		var param = {
			id : $("#id_q").val(),
			status : $("#status_q").val(),
			noticeTitle : $("#noticeTitle_q").val()
		};
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
			"order": [[1,"desc"]],
            "ajax": {
                "url": "${contextPath}/matchNotice/getMatchNoticeTable",
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
                    <fus:hasRight url="/matchNotice/updateMatchNoticeForAudit">
					if (e.status == 0){
						str += '<a onclick="showInfoModel('+ "'"+e.id+"'" +',1)">审核</a>';
					}
                    </fus:hasRight>
					str += '<a style="margin-left:10px;" onclick="showInfoModel('+ "'"+e.id+"'" +',2)">查看</a>';
                    return str;
                },
                    title:'操作',
					width:"100",
                    bSortable:false
                },
                {data: "id",bSearchable:false,title:'编号',width:"80px",defaultContent:""},
                {data: "inserttime",bSearchable:true,title:'通知创建时间',width:"80px",defaultContent:""},
                {data: "sysUserChName",name:"sysUserId",bSearchable:true,title:'发起人',width:"100px",defaultContent:""},
				{data: "noticeTitle",bSearchable:false,title:'标题',width:"100px",defaultContent:""},
                {data: "status",bSearchable:false,title:'状态',render:function(data){
                    return getObjValByKey(statusObj,data);
                },width:"50px"},
				{data: "checkUserName",name:"checkUserId",bSearchable:true,title:'审核人',width:"100px",defaultContent:""}
            ]
        });
	}

	function remaksSelChg(){
		var remarks = $("#remarksSel").val();
		if (remarks == "0"){
			$("#remarks_check").val("");
			$("#remarks_check").parent().show();
		} else{
			$("#remarks_check").parent().hide();
			$("#remarks_check").val(remarks);
		}
	}

	function showInfoModel(id,type){
		$.uwayProcess.show();
		$.ajax({
			url: "${contextPath}/matchNotice/getMatchNoticeById",
			async: true, type: "Post", dataType: "json",
			data: {id:id},
			success: function (response) {
				$.uwayProcess.close();
				if(response.success){
					$("#showInfoDiv").empty();
					var matchNotice = response.result.matchNotice;
					// 查看
					if (type != 1){
						$(".checkRemarkDiv").hide();
						$("#btnDiv").hide();
						$(".showDiv").show();
						if(matchNotice.status == 0){
							$("#auditResult_show").text("审核中");
						}else if(matchNotice.status == 3){
							$("#auditResult_show").text("驳回");
						}else{
							$("#auditResult_show").text("通过");
						}
						$("#remarks_show").text(matchNotice.remarks == null ? "":matchNotice.remarks);
					}else{
						// 审核
						$(".checkRemarkDiv").show();
						$("#btnDiv").show();
						$(".showDiv").hide();
						$("#remarks_check").val("");
						$("#remarksSel").val("");
						$("#id_check").val(matchNotice.id);
						$("#noticeType_check").val(matchNotice.noticeType);
					}
					$("#remarks_check").parent().hide();
					var templateList = response.result.noticeTemplateList;
					if (templateList != null && templateList.length > 0){
						var htmlStr = "";
						for(var i=0;i<templateList.length;i++){
							var info = templateList[i];
							htmlStr += '<div class="form-group">' +
									'<label>通知方式：</label>' +
									'<span>'+getObjValByKey(noticeTypeObj,info.noticeType)+'</span>' +
									'</div>' +
									'<div class="form-group">'
							if(info.noticeType == 1){
								htmlStr += '<label>通知标题：</label>' +
										'<span>'+info.title+'</span>' +
										'</div>';
							}
							var contextArr = info.context.split("&&");
							htmlStr += '<div class="form-group">' +
									'<label>通知内容：</label>' +
									'<span>'+contextArr[0]+'</span>';
							if(contextArr.length == 2){
								htmlStr += '<br/><img src="<%=Global.getConfig("prefix.visit.path")%>'+contextArr[1]+'" style="width: 300px;margin-left: 130px;"/>';
							}
							htmlStr += '</div>';
							if (type == 1){
								htmlStr += '<div class="form-group">' +
										'<label><input type="radio" name="noticeTypeStatus_'+info.noticeType+'" value="1">通过</label>' +
										'<label><input type="radio" name="noticeTypeStatus_'+info.noticeType+'" value="0">驳回</label>' +
										'</div>';
							}
							htmlStr += '<div class="form-group"><label></label></div>';
						}
						$("#showInfoDiv").append(htmlStr);
					}

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

    <fus:hasRight url="/matchNotice/updateMatchNoticeForAudit">
	function updateInfoForCheck(id,title,status){
	    var title = "是否确认提交审核？";
	    uwayConfirm({
			content:title,
			confirm:function(){
				updateInfoStatus(id,status);
			},
			cancel:function(){

			}
		});
	}

	function updateInfoStatus(id,status){
		var id = $("#id_check").val();
		var noticeType = $("#noticeType_check").val();
		var noticeTypeArr = noticeType.split(",");
		var noticeTypeStatus = "";
		for(var i=0;i<noticeTypeArr.length;i++){
			var val = $("input[name='noticeTypeStatus_"+noticeTypeArr[i]+"']:checked").val();
			if (val == undefined){
				uwayWarning("提示","请选择审核结果");
				return;
			}
			noticeTypeStatus += val + ",";
		}
		noticeTypeStatus = noticeTypeStatus.substring(0,noticeTypeStatus.length - 1);
		var remarks = $("#remarks_check").val();
        $.uwayProcess.show();
        $.ajax({
            url: "${contextPath}/matchNotice/updateMatchNoticeForAudit",
            async: true, type: "Post", dataType: "json",
            data: {id:id,noticeTypeStatus:noticeTypeStatus,remarks:remarks},
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