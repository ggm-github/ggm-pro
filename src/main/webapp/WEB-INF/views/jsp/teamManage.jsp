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
		<link rel="stylesheet" type="text/css" media="screen" href="${ctxStatic}/js/jstree/style.css${jsversion}" />
        <style>
			.form-group label{
				width:130px;
				text-align:right;
			}
			.divform label{
				width:60px;
				text-align:right;
			}
			.form-group select{
				height:29px;
			}
			.select-query{
				height: 30px;
				width: 180px;
			}
			.input-length{
				width: 180px;
			}
			.modal-backdrop{
				z-index:999;
			}
			.rule-tips{
				color : red;
				text-align : left;
				font-size : 12px;
				width: 50%;
				margin-left:5px;
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
						<li>赛事管理</li><li>队伍管理</li>
					</ol>
				</div>
			</div>
			<div id="content">
				<div class="condition-wrapper">
					<div class="divform">
						<label for="teamName_q">赛事名称</label>
						<input id="matchName_q" type="text" class="input-length" placeholder="请输入赛事名称"/>
					</div>
					<div class="divform">
						<label for="teamName_q">队伍名称</label>
						<input id="teamName_q" type="text" class="input-length" placeholder="请输入队伍名称"/>
					</div>
					<div class="divform">
						<label for="teamNo_q">队伍编号</label>
						<input id="teamNo_q" type="text" class="input-length" placeholder="请输入队伍编号"/>
					</div>

					<div class="divform">
						<button class="btn main-color-bluegreen" onclick="query()">查询</button>
						<fus:hasRight url="/team/updateTeamStatus">
							<button class="btn main-color-bluegreen" onclick="openUpdateStatusMotity()">批量关闭投票</button>
						</fus:hasRight>
					</div>
				</div>

				<div class="condition-wrapper" style="margin-top:10px;">
					<div class="divform">
						<label for="teamIndustryId_q">队伍行业</label>
						<input type="text" id="teamIndustryName_q" class="emptyInfo input-length" onfocus="showTree('2','teamIndustryId_q','teamIndustryName_q');" />
						<input type="hidden" id="teamIndustryId_q" class="emptyInfo" />
					</div>
					<div class="divform">
						<label for="teamAreaId_q">队伍区域</label>
						<input type="text" id="teamAreaName_q" class="emptyInfo input-length" onfocus="showTree('1','teamAreaId_q','teamAreaName_q');" />
						<input type="hidden" id="teamAreaId_q" class="emptyInfo" />
					</div>

					<div class="divform">
						<label for="isVote_q">投票状态</label>
						<select id="isVote_q" class="select-query">

						</select>
					</div>

					<div class="divform">
						<label for="checkStatus_q">审核状态</label>
						<select id="checkStatus_q" class="select-query">

						</select>
					</div>
				</div>

				<div class="condition-wrapper" style="margin-top:10px;">
					<div class="divform">
						<label for="status_q">队伍状态</label>
						<select id="status_q" class="select-query">

						</select>
					</div>
					<div class="divform">
						<label for="teamLeaderid_q">队长 ID:</label>
						<input type="text" id="teamLeaderid_q" class="emptyInfo input-length" />
					</div>
					<div class="divform">
						<label>投票增长:</label>
						<input type="text" id="voteIncreaseStart_q" value="" style="height: 30px;" class="emptyInfo input-length">
						<i class="icon-append fa fa-calendar" style="margin-left: -28px; height: 28px; width: 28px;"></i>
						~
						<input type="text" id="voteIncreaseEnd_q" value="" style="height: 30px;" class="emptyInfo input-length">
						<i class="icon-append fa fa-calendar" style="margin-left: -28px; height: 28px; width: 28px;"></i>
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

		<div class="modal fade" id="infoModal" tabindex="-1" role="dialog" aria-labelledby="infoModalModalTitle" aria-hidden="false" style="z-index:1000;">
			<div class="modal-dialog" style="width:600px;">
				<div class="modal-content" id="update-user-password-form">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						&times;
						</button>
						<h4 class="modal-title" id="updateUserPasswordModalTitle">编辑</h4>
					</div>
					<div class="modal-body">
						<div style="width: 100%;margin: 0 auto;">
							<div style="display:none;">
								<input id="id_info" type="text" class="emptyInfo"/>
							</div>
							<div class="form-group">
								<label for="teamNo_info">队伍编号:</label>
								<input type="text" id="teamNo_info" class="emptyInfo" placeholder="请输入队伍编号" />
							</div>
							<div class="form-group">
								<label for="teamName_info">队伍名称:</label>
								<input type="text" id="teamName_info" class="emptyInfo" placeholder="请输入队伍名称" />
							</div>
							<div class="form-group">
								<label for="teamDisc_info">队伍描述:</label>
								<textarea id="teamDisc_info" col="50" rows="5"></textarea>
							</div>
							<div class="form-group">
								<label for="groupId_info">小组:</label>
								<select id="groupId_info">

								</select>
							</div>
							<div class="form-group">
								<label for="score_info">专家评分:</label>
								<input type="number" id="score_info" class="numberInfo" placeholder="请输入评分" />
							</div>
							<div class="form-group">
								<label for="sort_info">排序:</label>
								<input type="number" id="sort_info" class="numberInfo" placeholder="请输入排序" />
							</div>
							<div class="form-group">
								<label for="teamTag_info">队伍标签:</label>
								<input type="text" id="teamTag_info" class="emptyInfo" placeholder="队伍标签" maxlength="30"/>
							</div>
							<div class="form-group">
								<label></label>
								<span class="rule-tips">最多填写30个字符，多个标签用“#”分隔，如：比赛#体操</span>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button class="btn main-color-bluegreen" onclick="updateTeamInfo()">提交</button>
						<button class="btn" onclick="$('#infoModal').modal('hide')">取消</button>
					</div>
				</div>
			</div>
		</div>

		<div class="modal fade" id="checkModal" tabindex="-1" role="dialog" aria-labelledby="checkModalTitle" aria-hidden="false">
			<div class="modal-dialog" style="width:600px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
							&times;
						</button>
						<h4 class="modal-title">审核</h4>
					</div>
					<div class="modal-body">
						<div style="width: 100%;margin: 0 auto;">
							<div style="display:none;">
								<input id="id_chk" type="text" class="emptyInfo"/>
							</div>
							<div class="form-group">
								<label for="teamName_chk">队伍名称:</label>
								<span id="teamName_chk"></span>
							</div>
							<div class="form-group">
								<label for="teamDisc_chk">队伍描述:</label>
								<span id="teamDisc_chk"></span>
							</div>
							<div class="form-group" style="text-align:center;">
								<img id="teamImgurl_chk" src="" style="max-height:200px" />
							</div>
							<div class="form-group">
								<label for="checkStatus_chk">审核:</label>
								<select id="checkStatus_chk" onchange="checkChg()">

								</select>
							</div>
							<div class="form-group" id="checkReasonDiv">
								<label for="checkReason_chk">驳回原因:</label>
								<input id="checkReason_chk" type="text" />
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button class="btn main-color-bluegreen" onclick="checkConfirm()">提交</button>
						<button class="btn" onclick="$('#checkModal').modal('hide')">取消</button>
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
							<div id="tree1" style="display:none;max-height:550px;overflow-y: auto;">
								<div id="areaTree_div"></div>
							</div>
							<div id="tree2" style="display:none;max-height:550px;overflow-y: auto;">
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

    var industryTreeList = JSON.parse(${fns:toJson(industryTree)});
    var areaTreeList = JSON.parse(${fns:toJson(areaTree)});
	var statusObj = {"0":"失效","1":"有效"};
	var isFullObj = {"0":"否","1":"是"};
	var voteStatusObj = {"0":"否","1":"是"};
	var checkStatusObj = {"0":"审核中","1":"审核通过","2":"审核未通过"};
	var isVoteObj = {"0":"未开启","1":"已开启"};
	var dataTable;
	$(function() {
        initJsTree("industryTree_div",industryTreeList);
        initJsTree("areaTree_div",areaTreeList);
		initSelect();
        initTableData();
		initTimer();
        //全选框事件绑定
        $("#chb_all").click(function(){
            if($("#chb_all").is(":checked")){
                $(".chb_check").prop('checked',"checked");
            }else{
                $(".chb_check").removeAttr("checked");
            }
        });
	});

	function initSelect(){
	    var allstr = '<option value="">请选择</option>';
        var str = "";
        $("#isVote_q").empty();
        $.each(isVoteObj,function(key,val){
            str += '<option value="'+key+'">'+val+'</option>';
        });
        $("#isVote_q").append(allstr + str);
        $("#isVote_q").val("");
        str = "";
        var str1 = "";
        $("#checkStatus_chk").empty();
        $("#checkStatus_q").empty();
        $.each(checkStatusObj,function(key,val){
            str += '<option value="'+key+'">'+val+'</option>';
            if(key != 0){
                str1 += '<option value="'+key+'">'+val+'</option>';
			}
        });
        $("#checkStatus_chk").append(str1);
        $("#checkStatus_chk").val("1");
        $("#checkStatus_q").append(allstr + str);
        $("#checkStatus_q").val("");

        str = "";
        $("#status_q").empty();
        $.each(statusObj,function(key,val){
            str += '<option value="'+key+'">'+val+'</option>';
            if(key != 0){
                str1 += '<option value="'+key+'">'+val+'</option>';
            }
        });
        $("#status_q").append(allstr + str);
        $("#status_q").val("1");
	}

	function checkChg(){
        var checkStatus = $("#checkStatus_chk").val();
        if (checkStatus == 2){
            $("#checkReasonDiv").show();
		}else{
            $("#checkReasonDiv").hide();
		}
	}

    function getObjValByKey(obj,key){
        var val = obj[key];
        return val == null || val == undefined ? "" : val;
    }


    <fus:hasRight url="/team/updateTeamInfo">
    function openEditorModel(id){
        $.ajax({
            url: "${contextPath}/team/getTeamById",
            async: true, type: "Post", dataType: "json",
            data: {"id":id},
            success: function (response) {
                $.uwayProcess.close();
                if(response.success){
                    var result = response.result;
                    $("#id_info").val(result.id);
                    $("#groupId_info").val(result.groupId);
                    $("#teamNo_info").val(result.teamNo);
                    $("#teamName_info").val(result.teamName);
                    $("#teamDisc_info").val(result.teamDisc);
                    $("#score_info").val(result.score);
                    $("#sort_info").val(result.sort);
                    $("#teamTag_info").val(result.teamTag);
                    getMatchGroupList(result.matchId,result.groupId);
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

    function getMatchGroupList(matchId,groupId){
        var param = {"matchId":matchId,"status":"1"}
        $.ajax({
            url: "${contextPath}/news/getMatchGroupList",
            async: true, type: "Post", dataType: "json",
            data: {"condition":JSON.stringify(param)},
            success: function (response) {
                $.uwayProcess.close();
                if(response.success){
                    var result = response.result;
                    console.log(result);
                    console.log(result.length)
                    $("#groupId_info").empty();
                    var str = '<option value="">请选择</option>';
                    if(result != null && result.length > 0){
                        for(var i=0;i<result.length;i++){
                            str += '<option value="'+result[i].id+'">'+result[i].groupName+'</option>';
                        }
                    }
                    $("#groupId_info").append(str);
                    if(groupId != null && groupId != undefined){
                        $("#groupId_info").val(groupId);
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

    function updateTeamInfo(){
        var teamNo = $("#teamNo_info").val();
        var teamName = $("#teamName_info").val();
        var teamDisc = $("#teamDisc_info").val();
        var groupId = $("#groupId_info").val();
        var sort = $("#sort_info").val();
        var id = $("#id_info").val();
        var score = $("#score_info").val();
        var teamTag = $("#teamTag_info").val();
        if(score != null && score < 0){
            uwayWarning("警告","请填写正确的评分");
            return;
		}
        if(id == ""){
            uwayWarning("警告","参数异常，刷新重试");
            return;
		}
		var param = {
            id : id,
			teamNo : teamNo,
            teamName : teamName,
            teamDisc : teamDisc,
            groupId : groupId,
            score : score,
			sort : sort,
			teamTag : teamTag
		};
        $.uwayProcess.show();
        $.ajax({
            url: "${contextPath}/team/updateTeamInfo",
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


    //根据条件进行查询
    function query() {
        dataTable.ajax.reload();
    }

    function getQueryParam(checkTag){
        var teamName = $("#teamName_q").val();
        var teamNo = $("#teamNo_q").val();
        var matchName = $("#matchName_q").val();
        var teamIndustryId = $("#teamIndustryId_q").val();
        var teamAreaId = $("#teamAreaId_q").val();
        var isVote = $("#isVote_q").val();
        var checkStatus = $("#checkStatus_q").val();
        var status = $("#status_q").val();
        var teamLeaderid = $("#teamLeaderid_q").val();
		var voteIncreaseStart = $("#voteIncreaseStart_q").val();
        var voteIncreaseEnd = $("#voteIncreaseEnd_q").val();
        var param = {
            teamName : teamName,
            teamNo : teamNo,
            matchName : matchName,
            teamIndustryId : teamIndustryId,
            teamAreaId : teamAreaId,
			checkStatus : checkStatus,
			status : status,
			teamLeaderid : teamLeaderid,
            isVote : isVote,
			voteIncreaseStart : voteIncreaseStart,
			voteIncreaseEnd : voteIncreaseEnd
        };
        if(checkTag == "1"){
            var isAllNull = true;
            $.each(param,function(key,val){
                if(val != ""){
                    isAllNull = false;
                }
            });
            if(isAllNull){
                return null;
			}
		}
        return param;
	}
	function initTableData(){
        dataTable = $('#dataTable').DataTable({
            "scrollX": true,
            "processing": true,
            "serverSide": true,
            "ajax": {
                "url": "${contextPath}/team/getTeamTable",
                "type": "post",
                "data": function (d) {
                    var param = getQueryParam();
                    d.condition = JSON.stringify(param);
                },
                "error":function (e1,e2,e3) {
                    if(e1.responseText.indexOf("<!DOCTYPE html>") != -1){
                        window.location = window.localStorage.getItem("CONTEXT_PATH") + '/user/login';
                    }
                }
            },
            "columns":[
                {
                    data: function(e){
                        return '<input type="checkbox" class="chb_check" value="'+e.id +'" />';
                    },
                    bSortable: false,
                    title: '<input type="checkbox" id="chb_all"/>',
                    width:"50px"
                },
                {data: function(e){
                    var str = '';
                    <fus:hasRight url="/team/updateTeamInfo">
                    str += '<a onclick="openEditorModel('+ "'"+e.id+"'" +')">编辑</a>';
                    </fus:hasRight>
					<fus:hasRight url="/team/updateTeamStatusCheck">
                    if(e.checkStatus == 0){
                        str += '<a style="margin-left:10px;" onclick="openCheckModel(' + e.id + ')">审核</a>';
                    }
					</fus:hasRight>
                    <fus:hasRight url="/team/updateTeamStatus">
                    if(e.status == 1){
                        str += '<a style="margin-left:10px;" onclick="openUpdateStatus(' + e.id + ',0)">失效</a>';
                    }else if(e.status == 0){
                        str += '<a style="margin-left:10px;" onclick="openUpdateStatus(' + e.id + ',1)">有效</a>';
                    }
                    if(e.isVote == 1){
                        str += '<a style="margin-left:10px;" onclick="openUpdateVote(' + e.id + ',0)">关闭投票</a>';
                    }else if(e.isVote == 0){
                        str += '<a style="margin-left:10px;" onclick="openUpdateVote(' + e.id + ',1)">开启投票</a>';
                    }
                    </fus:hasRight>
                    return str;
                },
                    title:'操作',
                    bSortable:false
                },
                {data: "id",bSearchable:false,title:'队伍ID',width:"80px",defaultContent:""},
                {data: "matchName",bSearchable:true,title:'赛事名称',width:"200px",bSortable:false,defaultContent:""},
                {data: "teamAreaName",bSearchable:true,title:'赛事所属区域',width:"100px",defaultContent:""},
                {data: "teamIndustryName",bSearchable:true,title:'赛事所属行业',width:"160px",defaultContent:""},
                {data: "teamName",bSearchable:true,title:'队伍名称',width:"260px",defaultContent:""},
                {data: "teamNo",bSearchable:true,title:'队伍编号',width:"80px",defaultContent:""},
				{data: "teamLeaderName",bSearchable:true,title:'队长',width:"100px",defaultContent:""},
                {data: "groupName",bSearchable:true,name:"groupId",title:'小组名称',width:"100px",defaultContent:""},
                {data: "isFull",bSearchable:false,title:'是否满员',render:function(data){
                    return getObjValByKey(isFullObj,data);
                },width:"50px"},
				{data: "teamTag",bSearchable:false,title:'标签',width:"100px",bSortable:false,defaultContent:""},
				{data: "voteIncrease",bSearchable:false,title:'投票增量',width:"50px",defaultContent:""},
                {data: "voteCnt",bSearchable:false,title:'投票数',width:"50px",defaultContent:""},
                {data: "score",bSearchable:false,title:'专家评分',width:"50px",render:function(data){
                    if (data == null){
                        return "未评分";
					}else{
                        return data;
					}
				}},
                {data: "isVote",bSearchable:false,title:'投票状态',width:"80px",bSortable:false,render:function(data){
                    return getObjValByKey(isVoteObj,data);
                }},
                {data: "voteStatus",bSearchable:false,title:'投票条件',width:"80px",bSortable:false,render:function(data){
                    if(data == 1){
                        return "达成";
					}else if(data == 0){
                        return "未达成";
					}else{
					    return "";
					}
                }},
                {data: "checkStatus",bSearchable:false,title:'审核状态',width:"80px",bSortable:false,render:function(data){
                    return getObjValByKey(checkStatusObj,data);
                }},
                {data: "status",bSearchable:false,title:'状态',width:"50px",render:function(data){
                    return getObjValByKey(statusObj,data);
                }},
                {data: "inserttime",bSearchable:false,title:'创建时间',width:"80px",defaultContent:""}

            ],
            "order": [[ 2, "desc" ]],
        });
	}

	<fus:hasRight url="/team/updateTeamStatusCheck">
    function openCheckModel(id){
        $.ajax({
            url: "${contextPath}/team/getTeamById",
            async: true, type: "Post", dataType: "json",
            data: {"id":id},
            success: function (response) {
                $.uwayProcess.close();
                if(response.success){
                    var result = response.result;
                    $("#id_chk").val(result.id);
                    $("#teamName_chk").text(result.teamName);
                    $("#teamDisc_chk").text(result.teamDisc);
                    $("#teamImgurl_chk").attr("src",'<%=Global.getConfig("prefix.visit.path")%>' + result.teamImgurl);
                    $("#checkStatus_chk").val(1);
                    $("#checkReason_chk").val("");
                    checkChg();
                    $('#checkModal').modal('show');
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

    function checkConfirm(){
        var checkStatus = $("#checkStatus_chk").val();
        var title = "是否确认";
        if(checkStatus == 1){
            title += "审核通过";
        }else if(checkStatus == 2){
            title += "审核不通过";
        }else{
            uwayWarning("警告","审核状态异常");
            return;
        }
        var checkReason = $("#checkReason_chk").val();
        var id = $("#id_chk").val();
        if(checkStatus == 2 && checkReason == ""){
            uwayWarning("警告","请填写驳回原因");
            return;
		}
        uwayConfirm({
            content:title,
            confirm:function(){
                var param = {
                    checkStatus : checkStatus,
					checkReason : checkReason,
					id : id
                };
                updateTeamStatusCheck(param);
            },
            cancel:function(){

            }
        });

        function updateTeamStatusCheck(param){
            $.uwayProcess.show();
            $.ajax({
                url: "${contextPath}/team/updateTeamStatusCheck",
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
	}
	</fus:hasRight>

    <fus:hasRight url="/team/updateTeamStatus">
	function openUpdateStatus(id,status){
        var title = "是否确认";
	    if(status == 1){
			title += "有效队伍";
		}else if(status == 0){
            title += "失效队伍";
		}else{
		    uwayWarning("警告","队伍状态异常");
		    return;
		}
	    uwayConfirm({
			content:title,
			confirm:function(){
			    var param = {status : status};
                updateTeamStatus(id,param);
			},
			cancel:function(){

			}
		});
	}

    function openUpdateVote(id,isVote){
        var title = "是否确认";
        if(isVote == 1){
            title += "开启投票";
        }else if(isVote == 0){
            title += "关闭投票";
        }else{
            uwayWarning("警告","队伍投票状态异常");
            return;
        }
        uwayConfirm({
            content:title,
            confirm:function(){
                var param = {isVote : isVote};
                updateTeamStatus(id,param);
            },
            cancel:function(){

            }
        });
    }

	function updateTeamStatus(id,param){
        $.uwayProcess.show();
        param.id = id;
        $.ajax({
            url: "${contextPath}/team/updateTeamStatus",
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
	<%-- 批量关闭投票 --%>
	function openUpdateStatusMotity(){
	    var title = "是否确定关闭"
        var ids = [];
        $(".chb_check:checked").each(function(i,obj){
            ids[i] = $(obj).val();
        });
        var param = {};
        if(ids.length > 0){
            title += "【"+$(".chb_check:checked").length+"】条的投票吗？"
			param.ids = ids;
        }else{
            param = getQueryParam("1");
            if(param == null){
                uwayNotice("通知","请选择要操作的记录或添加对应条件");
                return;
            }
            if(param.isVote == "0"){
                uwayNotice("通知","筛选数据与操作相同，无需操作，请核实");
                return;
            }
            title += "当前条件下的投票吗？"
        }

        uwayConfirm({
            content:title,
            confirm:function(){
                updateTeamStatusMult({"param":JSON.stringify(param)});
            },
            cancel:function(){

            }
        });
	}

    function updateTeamStatusMult(param){
        $.uwayProcess.show();
        $.ajax({
            url: "${contextPath}/team/updateTeamStatusMult",
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

	function initTimer(){
		var nowDate = getNowFormatDate();
		$('#voteIncreaseStart_q').val(nowDate);     //设置日历默认为当天
		$('#voteIncreaseEnd_q').val(nowDate);     //设置日历默认为当天
		$('#voteIncreaseStart_q').attr("readonly", "readonly").datepicker({   //日历初始化
			dateFormat: 'yy-mm-dd',
//            maxDate: 'd',
			showTime: true,
			monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
			dayNames: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
			dayNamesShort: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
			dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'],
			prevText: '<i class="fa fa-chevron-left"></i>',
			nextText: '<i class="fa fa-chevron-right"></i>',
			onSelect : function(selectedDate) {
				$("#voteIncreaseEnd_q").datepicker('option', 'minDate', selectedDate);
			}
		});

		$('#voteIncreaseEnd_q').attr("readonly", "readonly").datepicker({   //日历初始化
			dateFormat: 'yy-mm-dd',
//            maxDate: 'd',
			showTime: true,
			monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
			dayNames: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
			dayNamesShort: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
			dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'],
			prevText: '<i class="fa fa-chevron-left"></i>',
			nextText: '<i class="fa fa-chevron-right"></i>',
			onSelect : function(selectedDate) {
				$("#voteIncreaseStart_q").datepicker('option', 'maxDate', selectedDate);
			}
		});
	}

	//获取当前日期函数
	function getNowFormatDate() {
		var date = new Date().addDays(0);
		var seperator1 = "-";
		var year = date.getFullYear();
		var month = date.getMonth() + 1;
		var strDate = date.getDate();
		if (month >= 1 && month <= 9) {
			month = "0" + month;
		}
		if (strDate >= 0 && strDate <= 9) {
			strDate = "0" + strDate;
		}
		var currentdate = year + seperator1 + month + seperator1 + strDate;
		return currentdate;
	}

    //文件上传
    function upload() {
        $.uwayProcess.show();
        var t = setInterval(function() {
            //获取iframe标签里body元素里的文字。即服务器响应过来的"上传成功"或"上传失败"
            var word = $("iframe[id='imageframe']").contents().find("body").text();
            var result = JSON.parse(word);
            if (result.success == true) {
                var fName = result.result;
                $("#newsImgurl_info").val(fName)
                uwayNotice("通知", "上传成功！");
                clearInterval(t);
                $.uwayProcess.close();
            }
            else {
                uwayNotice("通知", "上传失败！");
                clearInterval(t);
                $.uwayProcess.close();
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
        $("#"+targetId).attr("src",url);
        $("#"+targetId).show();
        //每次更换时都将路径清空，在上传后填写
        $("#newsImgurl_info").val("");
    }

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
		if(idValue != ''){
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