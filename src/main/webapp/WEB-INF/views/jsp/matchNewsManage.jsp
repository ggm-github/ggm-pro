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
						<li>赛事管理</li><li>赛事管理</li>
					</ol>
				</div>
			</div>
			<div id="content">
				<div class="condition-wrapper">
					<div style="display:none;">
						<input type="text" id="openOptionType" value="${queryType}" />
					</div>
					<div class="divform">
						<label for="matchTitle_q">标题</label>
						<input id="matchTitle_q" type="text" class="input-length" placeholder="请输入标题"/>
					</div>
					<div class="divform">
						<label for="status_q">状态</label>
						<select id="status_q" class="select-query">

						</select>
					</div>
					<div class="divform" style="display:none;">
						<label for="matchIndustry_q">行业</label>
						<input type="text" id="matchIndustryName_q" class="emptyInfo input-length" onfocus="showTree('2','matchIndustry_q','matchIndustryName_q');" />
						<input type="hidden" id="matchIndustry_q" class="emptyInfo" />
					</div>
					<div class="divform" style="display:none;">
						<label for="matchArea_q">区域</label>
						<input type="text" id="matchAreaName_q" class="emptyInfo input-length" onfocus="showTree('1','matchArea_q','matchAreaName_q');" />
						<input type="hidden" id="matchArea_q" class="emptyInfo" />
					</div>

					<div class="divform">
						<button class="btn main-color-bluegreen" onclick="query()">查询</button>
						<fus:hasRight url="/news/addMatchNews">
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

		<div class="modal fade" id="editorModal" tabindex="-1" role="dialog" aria-labelledby="infoModalModalTitle" aria-hidden="false" backdrop="false" style="z-index:1000;">
			<div class="modal-dialog" style="width:600px;">
				<div class="modal-content" >
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
							&times;
						</button>
						<h4 class="modal-title">赛事</h4>
					</div>
					<div class="modal-body">
						<div style="width: 100%;max-height:400px;margin: 0 auto;overflow-y: auto;">
							<div style="display:none;">
								<input id="id_info" type="text" class="emptyInfo"/>
							</div>
							<div class="form-group">
								<label for="matchTag_info">标签:</label>
								<input type="text" id="matchTag_info" class="emptyInfo input-length" maxlength="30"/>
							</div>
							<div class="form-group">
								<label></label>
								<span class="rule-tips">最多填写30个字符，多个标签用“#”分隔，如：比赛#体操</span>
							</div>
							<div class="form-group">
								<label for="sort_info">权重:</label>
								<input type="number" id="sort_info" class="numberInfo input-length" placeholder="请输入权重" />

							</div>
							<div class="form-group" >
								<label for="sortType_info">权重设置:</label>
								<select id="sortType_info" onchange="sortTypeChg();">

								</select>

							</div>
							<div class="form-group sortDateLab">
								<label>过期时间:</label>
								<input type="text" id="sortDate_info" value="" style="height: 30px;" class="input-length">
								<i class="icon-append fa fa-calendar" style="margin-left: -25px; height: 28px; width: 28px;"></i>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button class="btn main-color-bluegreen" onclick="updateMatchInfo()">提交</button>
						<button class="btn" onclick="$('#editorModal').modal('hide')">取消</button>
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

		<div class="modal fade" id="treeModal" tabindex="-1" role="dialog" aria-labelledby="treeModalTitle" aria-hidden="false">
			<div class="modal-dialog" style="width:400px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
							&times;
						</button>
						<h4 class="modal-title">选择</h4>
					</div>
					<div class="modal-body">
						<div style="width: 100%;margin: 0 auto;">
							<div id="tree1" style="display:none;max-height:400px;min-height:200px;overflow-y: auto;">
								<div id="areaTree_div"></div>
							</div>
							<div id="tree2" style="display:none;max-height:400px;min-height:200px;overflow-y: auto;">
								<div id="industryTree_div"></div>
							</div>
						</div>
						<div style="display:none;">
							<input id="idTree" type="text" />
							<input id="idText" type="text" />
							<input id="nameText" type="text" />
						</div>
					</div>
					<div class="modal-footer">
						<button class="btn main-color-bluegreen" onclick="getTreeValue();">提交</button>
						<button class="btn" onclick="clearTreeValue();">清空</button>
						<button class="btn" onclick="$('#treeModal').modal('hide')">取消</button>
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
    //var industryTreeList = JSON.parse(${fns:toJson(industryTree)});
    //var areaTreeList = JSON.parse(${fns:toJson(areaTree)});
	var newsTypeObj = {"1":"URL","2":"原生"};
	var statusObj = {"-1":"暂存","0":"审核中","1":"审核通过","2":"审核驳回"};
    var sortTypeObj = {"1":"永不过期","2":"过期设置"};
    var isChargeObj = {"0":"免费","1":"收费"};
	var chargeTypeObj = {"0":"自选","1":"统一交费","2":"自己交费"};
	var isVoteObj = {"0":"未开启","1":"已开启"};
	var dataTable;
	$(function() {
        //initJsTree("industryTree_div",industryTreeList);
        //initJsTree("areaTree_div",areaTreeList);
		initSelect();
		initTimer();
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
		$("#sortType_info").empty();
		$.each(sortTypeObj,function(key,val){
			str += '<option value="'+key+'">'+val+'</option>';
		});
		$("#sortType_info").append(str);
		$("#sortType_info").val("1");
	}
	function initTimer(){
		$('#sortDate_info').attr("readonly", "readonly").datepicker({   //日历初始化
			dateFormat: 'yy-mm-dd',
            minDate: 'd',
			showTime: true,
			monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
			dayNames: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
			dayNamesShort: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
			dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'],
			prevText: '<i class="fa fa-chevron-left"></i>',
			nextText: '<i class="fa fa-chevron-right"></i>'
		});
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
		localStorage.setItem('historyParam_match', JSON.stringify(historyParam));
		window.location.href = '${contextPath}/news/toMatchNews?operType=show&id='+id;
	}

	<fus:hasRight url="/news/addMatchNews">
	function addInfoModel(){
		window.location.href = '${contextPath}/news/toMatchNews?operType=add';
	}
	</fus:hasRight>

	function sortTypeChg(){
		var type = $("#sortType_info").val();
		if(type == 1){
			$(".sortDateLab").hide();
		}else{
			$(".sortDateLab").show();
		}
	}

    <fus:hasRight url="/news/updateMatchNews">
    function openEditorModel(id){
		$.ajax({
			url: "${contextPath}/news/getMatchNewsById",
			async: true, type: "Post", dataType: "json",
			data: {"id":id},
			success: function (response) {
				$.uwayProcess.close();
				if(response.success){
					var result = response.result;
					$("#id_info").val(result.id);

					$("#matchTag_info").val(result.matchTag);
					$("#sort_info").val(result.sort);
					$("#sortType_info").val(result.sortType);
					sortTypeChg();
					if(result.sortType == 2){
						$("#sortDate_info").val(result.sortDateStr);
					}
					$("#editorModal").modal("show");
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

    function updateMatchInfo(){
		var sort = $("#sort_info").val();
		if(sort != "" && (sort.indexOf(".") > -1 || sort.indexOf("-") > -1)){
			uwayWarning("提示","排序不能为空，且为非负整数");
			return;
		}
		var matchTag = $("#matchTag_info").val();
		if (matchTag != "" && matchTag.length > 30){
			uwayWarning("提示","请按照提示填写标签");
			return;
		}
    	if(sort == "" && matchTag == ""){
			uwayWarning("提示","请填写信息");
			return;
		}
		var sortType = $("#sortType_info").val();
		var sortDate = $("#sortDate_info").val();
		if (sortType == 2 && sortDate == ''){
			uwayWarning("提示","请填写权重过期时间");
			return;
		}else if(sortType == 1){
			sortDate = null;
		}

		var param = {
			"id":$("#id_info").val(),
			"sortType":sortType,"sortDate":sortDate, "sort":sort,"matchTag":matchTag
		};

		$.uwayProcess.show();
		$.ajax({
			url: "${contextPath}/news/updateMatchNewsBy",
			async: true, type: "Post", dataType: "json",
			data: {"condition":JSON.stringify(param)},
			success: function (response) {
				$.uwayProcess.close();
				if(response.success){
					$("#editorModal").modal("hide");
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

	function queryParam(){
		var param = {
			matchTitle : $("#matchTitle_q").val(),
			status : $("#status_q").val(),
			matchArea : $("#matchArea_q").val(),
			matchIndustry : $("#matchIndustry_q").val()
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
		var itemValue = localStorage.getItem('historyParam_match');
		console.log("itemValue:" + itemValue);
		if(openOptionType == "historyParam_match" && itemValue != undefined && itemValue != null && itemValue != ''){
			var historyParam = JSON.parse(itemValue);
			$("#matchTitle_q").val(historyParam.matchTitle);
			$("#status_q").val(historyParam.status);
			$("#matchArea_q").val(historyParam.matchArea);
			$("#matchIndustry_q").val(historyParam.matchIndustry);
			historyOrder = historyParam.order;
			historyPage = historyParam.pageNum;
			localStorage.removeItem("historyParam")
		}
		var startRow = historyPage * 10;
        dataTable = $('#dataTable').DataTable({
            "scrollX": true,
            "processing": true,
            "serverSide": true,
			"displayStart": startRow,
			"order": historyOrder,
            "ajax": {
                "url": "${contextPath}/news/getMatchNewsTable",
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
                    <fus:hasRight url="/news/updateSportsNews">
                    str += '<a onclick="openEditorModel('+ "'"+e.id+"'" +')">编辑</a>';
                    </fus:hasRight>
                    <fus:hasRight url="/news/stopMatchTeamVote">
                    if(e.canVoteCnt > 0){
                        str += '<a style="margin-left:10px;" onclick="stopVoteModel('+ "'"+e.id+"'" +')">停止投票</a>';
                    }
                    </fus:hasRight>

                    <fus:hasRight url="/news/updateSportsNewsStatus">
                    if(e.status == 0){
                        str += '<a style="margin-left:10px;" onclick="openUpdateStatus('+ "'"+e.id+"','通过'" +',1)">通过</a>';
                        str += '<a style="margin-left:10px;" onclick="openUpdateStatusModal('+ "'"+e.id+"','驳回'" +',2)">驳回</a>';
                    }
                    </fus:hasRight>
					str += '<a style="margin-left:10px;" onclick="goToShowInfoPage('+ "'"+e.id+"'" +')">查看</a>';
					str += '<a style="margin-left:10px;" onclick="sendRankingConfirm('+ e.id +',\''+e.matchTitle+'\')">发送最终排名</a>';
                    return str;
                },
                    title:'操作',
                    bSortable:false
                },
                {data: "id",bSearchable:false,title:'赛事ID',width:"80px",defaultContent:""},
                {data: "matchTitle",bSearchable:true,title:'名称',width:"150px",defaultContent:""},
				{data: "itemNames",bSearchable:true,title:'项目',width:"80px",defaultContent:""},
                {data: "sort",bSearchable:true,title:'权重',width:"50px",defaultContent:""},
                {data: "optUserName",bSearchable:false,title:'操作人',width:"80px",bSortable:false,defaultContent:""},
                {data: "status",bSearchable:false,title:'状态',render:function(data){
                    return getObjValByKey(statusObj,data);
                },width:"50px"},
                {data: "reason",bSearchable:false,title:'驳回原因',width:"180px",defaultContent:""},
                {data: "matchTime",bSearchable:true,title:'报名开始时间',width:"80px",defaultContent:""},
                {data: "matchEndtime",bSearchable:true,title:'报名结束时间',width:"80px",defaultContent:""},
				{data: "dateBegin",bSearchable:true,title:'比赛开始时间',width:"80px",defaultContent:""},
				{data: "dateEnd",bSearchable:true,title:'比赛结束时间',width:"80px",defaultContent:""},
				{data: "matchTag",bSearchable:true,title:'标签',width:"80px",defaultContent:"",bSortable:false},
				{data: "isVote",bSearchable:true,title:'是否投票',render:function(data){
					return getObjValByKey(isVoteObj,data);
				},width:"50px"},
                {data: "readCnt",bSearchable:false,title:'阅读量',width:"80px",defaultContent:""},
                {data: "inserttime",bSearchable:false,title:'创建时间',width:"80px",defaultContent:""}
            ]
        });
	}

    <fus:hasRight url="/news/updateMatchNewsStatus">
    function openUpdateStatusModal(id,title,status){
        $("#id_check").val(id);
        $("#infoModal").modal("show");
    }

    function updateNewsInfoForCheck(){
        var id = $("#id_check").val();
        var reason = $("#reason_check").val();
        if(reason == ''){
            uwayWarning("提示","请填写驳回原因");
            return;
        }
        openUpdateStatus(id,'驳回',2,reason);
    }

    function sendRankingConfirm(matchId,matchTitle){
		uwayConfirm({
			content:"是否确认发送赛事活动【"+matchTitle+"】的最终排名？",
			confirm:function(){
				sendMatchFinalTeamRanking(matchId,0);
			},
			cancel:function(){

			}
		});
	}

	function sendMatchFinalTeamRanking(matchId,isMust){
		$.uwayProcess.show();
		$.ajax({
			url: "${contextPath}/team/sendMatchFinalTeamRanking",
			async: true, type: "Post", dataType: "json",
			data: {matchId:matchId,isMust:isMust},
			success: function (response) {
				$.uwayProcess.close();
				if(response.success){
					var result = response.result;
					if (result.success == 'ok') {
						uwayNotice('通知',"操作成功");
					}else if(result.success == '1'){
						uwayConfirm({
							content:result.message,
							confirm:function(){
								sendMatchFinalTeamRanking(matchId,1);
							},
							cancel:function(){

							}
						});
					}else{
						uwayWarning('警告',result.message);
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

	function openUpdateStatus(id,title,status,reason){
	    var title = "是否确认" + title;
	    uwayConfirm({
			content:title,
			confirm:function(){
                updateSportsNewsStatus(id,status,reason);
			},
			cancel:function(){

			}
		});
	}

	function updateSportsNewsStatus(id,status,reason){
        var param = {"id":id,"status":status};
        if(status == 2){
            param['reason'] = reason;
        }
        $.uwayProcess.show();
        $.ajax({
            url: "${contextPath}/news/updateMatchNewsStatus",
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

	<fus:hasRight url="/news/stopMatchTeamVote">

	function stopVoteModel(id){
	    uwayConfirm({
            content : "是否确认停止该赛事下所有队伍的投票？",
			confirm : function(){
                stopMatchTeamVote(id);
			},
			cancel : function(){

			}
		});
	}

    function stopMatchTeamVote(id){
        $.uwayProcess.show();
        $.ajax({
            url: "${contextPath}/news/stopMatchTeamVote",
            async: true, type: "Post", dataType: "json",
            data: {"id":id},
            success: function (response) {
                $.uwayProcess.close();
                if(response.success){
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

	function showTree(type,id,name){
		if(type == 1){
			$("#tree1").show();
			$("#tree2").hide();
			$("#idTree").val("areaTree_div");
		}else if(type == 2){
			$("#tree1").hide();
			$("#tree2").show();
			$("#idTree").val("industryTree_div");
		}
		var treaId = $("#idTree").val();
		var tree = $('#' + treaId).jstree();
		// 取消所有选中
		tree.deselect_all();
		// 关闭所有节点
		tree.close_all();
		var idValue = $("#" + id).val();
		if(idValue != '' && idValue != '-1' && idValue != '0'){
			tree.select_node(idValue);
		}
		$("#idText").val(id);
		$("#nameText").val(name);
		$("#treeModal").modal("show");
	}

	function initJsTree(id,jsList){
        $('#' + id).data('jstree', false);//清空数据，必须
        $('#' + id).jstree({
            'plugins' : [ "search" ],
            'checkbox': { cascade: "", three_state: false }, //不级联
            'core' : {
                'dblclick_toggle':false,   // 双击是否选中
                'data' : jsList,
                "themes": {
                    "icons":false,    // 是否显示图标
                    "variant" : "large",//加大
                    "ellipsis" : false, //文字多时省略
                    "responsive": false
                }
            }
        }).bind('dblclick.jstree',function(event){
            var tree = $('#' + id).jstree();
            var nodeId = $(event.target).parents('li').attr('id');
            if (tree.is_disabled(tree.get_node("#"+nodeId))) {
                uwayWarning("提示","请选择有效的节点");
                return;
            }
            getTreeValue();
        });
	}

	function getTreeValue(){
	    var idTree = $("#idTree").val();
	    var idText = $("#idText").val();
	    var nameText = $("#nameText").val();
        var ref = $('#' + idTree).jstree(true);
        var sel = ref.get_selected();
        if(!sel.length) {
            uwayNotice("通知", "请选择节点！");
            return;
        }
        $("#" + idText).val(sel[0]);
        $("#" + nameText).val(ref.get_text(sel[0]));
        $("#treeModal").modal("hide");
	}

	function clearTreeValue(){
        var idTree = $("#idTree").val();
        var idText = $("#idText").val();
        var nameText = $("#nameText").val();
        $('#' + idTree).jstree().deselect_all();
        $("#" + idText).val("");
        $("#" + nameText).val("");
        $("#treeModal").modal("hide");
	}

</script>
</body>
</html>