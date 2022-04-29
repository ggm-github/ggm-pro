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
						<li>内容管理</li><li>活动主题管理</li>
					</ol>
				</div>
			</div>
			<div id="content">
				<div class="condition-wrapper">
					<div style="display:none;">
						<input type="text" id="openOptionType" value="${queryType}" />
					</div>
					<div class="divform">
						<label for="themeTitle_q">标题</label>
						<input id="themeTitle_q" type="text" class="input-length" placeholder="请输入标题"/>
					</div>
					<div class="divform">
						<label for="status_q">状态</label>
						<select id="status_q" class="select-query">

						</select>
					</div>
					<div class="divform">
						<label for="themeType_q">是否关联赛事活动</label>
						<select id="themeType_q" class="select-query">

						</select>
					</div>
					<div class="divform">
						<label for="themeId_q">赛事活动</label>
						<input id="themeId_q" type="text" class="input-length" placeholder="赛事活动ID"/>
					</div>

					<div class="divform">
						<button class="btn main-color-bluegreen" onclick="query()">查询</button>
						<fus:hasRight url="/theme/addThemeInfo">
							<button class="btn main-color-bluegreen" onclick="addInfoModel()">新增赛事</button>
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

		<div class="modal fade" id="infoModal" tabindex="-1" role="dialog" aria-labelledby="infoModalModalTitle" aria-hidden="false" backdrop="false" style="z-index:1000;">
			<div class="modal-dialog" style="width:600px;">
				<div class="modal-content" id="update-user-password-form">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						&times;
						</button>
						<h4 class="modal-title" id="modalTitle">赛事</h4>
					</div>
					<div class="modal-body">
						<div style="width: 100%;max-height:400px;margin: 0 auto;overflow-y: auto;">
							<div style="display:none;">
								<input id="id_check" type="text" class="emptyInfo"/>
							</div>
							<div class="form-group">
								<label for="reason_check">驳回原因:</label>
								<input type="text" id="reason_check" class="emptyInfo input-length-big" maxlength="200" />
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button class="btn main-color-bluegreen" onclick="updateNewsInfoForCheck()">提交</button>
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
	var themeTypeObj = {"1":"未关联","2":"已关联"};
	var statusObj = {"0":"未发布","1":"已发布"};
	var isVoteObj = {"0":"未开启","1":"已开启"};
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

		str = "";
		$("#themeType_q").empty();
		$.each(themeTypeObj,function(key,val){
			str += '<option value="'+key+'">'+val+'</option>';
		});
		$("#themeType_q").append(allstr + str);
		$("#themeType_q").val("");
	}

    function getObjValByKey(obj,key){
        var val = obj[key];
        return val == null || val == undefined ? "" : val;
    }

    function goToShowInfoPage(id){
    	// 修改时，在缓存中记录当前查询条件，方便返回时加载
		var pageNum = dataTable.page();   // 当前页码
		var order = dataTable.order();    // 当前排序
		// 查询条件
		var historyParam = queryParam();
		historyParam["pageNum"] = pageNum;
		historyParam["order"] = order;
		localStorage.setItem('historyParam_theme', JSON.stringify(historyParam));
		window.location.href = '${contextPath}/theme/toThemePage?operType=show&id='+id;
	}

	<fus:hasRight url="/theme/addThemeInfo">
	function addInfoModel(){
		window.location.href = '${contextPath}/theme/toThemePage?operType=add';
	}
	</fus:hasRight>

    <fus:hasRight url="/theme/updateThemeInfo">
	function openEditorModel(id){
		window.location.href = '${contextPath}/theme/toThemePage?operType=editor&id=' + id;
	}
    </fus:hasRight>

	function queryParam(){
		var param = {
			themeTitle : $("#themeTitle_q").val(),
			status : $("#status_q").val(),
			themeType : $("#themeType_q").val(),
			themeId : $("#themeId_q").val()
		};
		return param;
	}

    //根据条件进行查询
    function query() {
        dataTable.ajax.reload();
    }

	function initTableData(){
		var openOptionType = $("#openOptionType").val();
		var historyPage = 0;
		var historyOrder = [[ 1, "desc" ]];
		var itemValue = localStorage.getItem('historyParam_theme');
		console.log("itemValue:" + itemValue);
		if(openOptionType == "historyParam_theme" && itemValue != undefined && itemValue != null && itemValue != ''){
			var historyParam = JSON.parse(itemValue);
			$("#themeTitle_q").val(historyParam.themeTitle);
			$("#status_q").val(historyParam.status);
			$("#themeType_q").val(historyParam.themeType);
			$("#themeId_q").val(historyParam.themeId);
			historyOrder = historyParam.order;
			historyPage = historyParam.pageNum;
			localStorage.removeItem("historyParam_theme")
		}
		var startRow = historyPage * 10;
        dataTable = $('#dataTable').DataTable({
            "scrollX": true,
            "processing": true,
            "serverSide": true,
			"displayStart": startRow,
			"order": historyOrder,
            "ajax": {
                "url": "${contextPath}/theme/getThemeTable",
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
                    <fus:hasRight url="/theme/updateThemeInfo">
                    str += '<a onclick="openEditorModel('+ "'"+e.id+"'" +')">编辑</a>';
                    </fus:hasRight>
                    <fus:hasRight url="/theme/updateThemeInfoStatus">
                    if(e.status == 0) {
						str += '<a style="margin-left:10px;" onclick="openUpdateStatus(' + "'" + e.id + "','发布'" + ',1)">发布</a>';
					}else if(e.status == 1){
                        str += '<a style="margin-left:10px;" onclick="openUpdateStatus('+ "'"+e.id+"','取消发布'" +',0)">取消发布</a>';
                    }
                    </fus:hasRight>
					// str += '<a style="margin-left:10px;" onclick="goToShowInfoPage('+ "'"+e.id+"'" +')">查看</a>';
                    return str;
                },
                    title:'操作',
                    bSortable:false
                },
                {data: "id",bSearchable:false,title:'主题ID',width:"80px",defaultContent:""},
                {data: "sort",bSearchable:true,title:'权重',width:"50px",defaultContent:""},
                {data: "themeTitle",bSearchable:true,title:'名称',width:"150px",defaultContent:""},
				{data: "readCnt",bSearchable:true,title:'阅读数',width:"80px",defaultContent:""},
                {data: "momentsCnt",bSearchable:false,title:'参与讨论数',width:"80px",bSortable:true,defaultContent:""},
				{data: "themeType",bSearchable:false,title:'是否关联赛事活动',render:function(data){
						return getObjValByKey(themeTypeObj,data);
				},width:"50px"},
                {data: "status",bSearchable:false,title:'状态',render:function(data){
                    return getObjValByKey(statusObj,data);
                },width:"50px"},
				{data: "themeRemark",bSearchable:false,title:'导语',render:function(data){
						if (data == null){
							return "";
						}
						if (data.length < 20){
							return data;
						}
						return '<span title="'+data+'">'+data.substring(0,18)+'......</span>';
				},width:"200px"}
            ]
        });
	}

    <fus:hasRight url="/theme/updateThemeInfoStatus">
	function openUpdateStatus(id,title,status){
	    var title = "是否确认" + title;
	    uwayConfirm({
			content:title,
			confirm:function(){
				updateThemeInfoStatus(id,status);
			},
			cancel:function(){

			}
		});
	}

	function updateThemeInfoStatus(id,status){
        var param = {"id":id,"status":status};
        $.uwayProcess.show();
        $.ajax({
            url: "${contextPath}/theme/updateThemeInfoStatus",
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