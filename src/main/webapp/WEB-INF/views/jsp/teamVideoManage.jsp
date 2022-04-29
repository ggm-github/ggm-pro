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
						<li>赛事管理</li><li>队伍视频管理</li>
					</ol>
				</div>
			</div>
			<div id="content">
				<div class="condition-wrapper">
					<div class="divform">
						<label for="type_q">内容分类</label>
						<select id="type_q" class="select-query">

						</select>
					</div>
					<div class="divform">
						<label for="status_q">状态</label>
						<select id="status_q" class="select-query">

						</select>
					</div>
					<div class="divform">
						<label for="checkStatus_q">审核状态</label>
						<select id="checkStatus_q" class="select-query">

						</select>
					</div>

					<div class="divform">
						<button class="btn main-color-bluegreen" onclick="query()">查询</button>
						<button class="btn main-color-bluegreen" onclick="toPage('goodClickCntEditor');" >修改点赞数</button>
					</div>
				</div>
				<div class="condition-wrapper" style="margin-top:10px;">
					<div class="divform">
						<label for="videoDisc_q">动态内容</label>
						<input id="videoDisc_q" class="select-query" />
					</div>
					<div class="divform">
						<label for="isSelected_q">是否推荐</label>
						<select id="isSelected_q" class="select-query">

						</select>
					</div>
					<div class="divform">
						<label for="videoType_q">动态类型</label>
						<select id="videoType_q" class="select-query">

						</select>
					</div>
                    <div class="divform">
                        <label for="operUserId_q">发布人ID</label>
                        <input id="operUserId_q" class="select-query" />
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
			<div class="modal-dialog" style="width:800px;">
				<div class="modal-content" >
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						&times;
						</button>
						<h4 class="modal-title" >编辑</h4>
					</div>
					<div class="modal-body">
						<div style="width: 600px;margin: 0 auto;">
							<div style="display:none;">
								<input id="id_info" type="text" class="emptyInfo"/>
								<input id="type_info" type="text" class="emptyInfo" />
							</div>
							<div class="form-group">
								<label>内容:</label>
								<span id="videoDiscTemp_info"></span>
							</div>
							<div class="form-group">
								<div id="videoDiv_info" style="width: 100%;margin: 0 auto;text-align: center;">

								</div>
							</div>
							<div class="form-group">
								<div id="imageDiv_info" style="width: 100%;margin: 0 auto;text-align: center;">

								</div>
							</div>
							<div class="form-group modifyInfo" id="checkDiv">
								<label for="checkStatus_info">审核状态:</label>
								<select id="checkStatus_info" class="select-query" onchange="checkStatusChg();">
									<option value="1">通过</option>
									<option value="2">驳回</option>
								</select>
							</div>
							<div class="form-group modifyInfo" id="reasonDiv" style="display:none;">
								<label>驳回原因:</label>
								<input id="checkReason_info" type="text" class="emptyInfo" />
							</div>
							<div class="form-group modifyInfo">
								<label for="isSelected_info">是否精选:</label>
								<select id="isSelected_info" class="select-query">
									<option value="0">否</option>
									<option value="1">是</option>
								</select>
							</div>
							<div class="form-group modifyInfo">
								<label for="sort_info">权重:</label>
								<input type="number" id="sort_info" class="numberInfo" placeholder="请输入权重" />
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button class="btn main-color-bluegreen modifyInfo" onclick="updateTeamVideoInfo()">提交</button>
						<button class="btn" onclick="$('#infoModal').modal('hide')">取消</button>
					</div>
				</div>
			</div>
		</div>


		<div class="modal fade" id="videoModal" tabindex="-1" role="dialog" aria-labelledby="videoModalTitle"
			 aria-hidden="false" >
			<div class="modal-dialog" style="width:600px;">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">播放</h4>
					</div>
					<div class="modal-body" style="margin:auto;">
						<div id="videoDiv" style="width: 100%;margin: 0 auto;text-align: center;">

						</div>
					</div>
					<div class="modal-footer">
						<button class="btn main-color-bluegreen" onclick="closeVideo();">关闭</button>
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
	var isSelectedObj = {"0":"否","1":"是"};
	var checkStatusObj = {"-1":"云审核不通过","0":"员工未审核","1":"审核通过","2":"审核不通过"};
	var videoTypeObj = {"1":"一般动态","2":"教学动态","3":"报名动态"};
	var typeObj = {"1":"图片动态","2":"视频动态","3":"文字动态"};
	var dataTable;
	$(function() {
        initJsTree("industryTree_div",industryTreeList);
        initJsTree("areaTree_div",areaTreeList);
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
        $("#isSelected_q").empty();
        $.each(isSelectedObj,function(key,val){
            str += '<option value="'+key+'">'+val+'</option>';
        });
        $("#isSelected_q").append(allstr + str);
        $("#isSelected_q").val("0");
        str = "";
        $("#checkStatus_q").empty();
        $.each(checkStatusObj,function(key,val){
            str += '<option value="'+key+'">'+val+'</option>';
        });
        $("#checkStatus_q").append(allstr + str);
        $("#checkStatus_q").val("");
        str = "";
        $("#videoType_q").empty();
        $.each(videoTypeObj,function(key,val){
            str += '<option value="'+key+'">'+val+'</option>';
        });
        $("#videoType_q").append(allstr + str);
        $("#videoType_q").val("");
		str = "";
		$("#type_q").empty();
		$.each(typeObj,function(key,val){
			str += '<option value="'+key+'">'+val+'</option>';
		});
		$("#type_q").append(allstr + str);
		$("#type_q").val("");
	}

    function getObjValByKey(obj,key){
        var val = obj[key];
        return val == null || val == undefined ? "" : val;
    }

    function toPage(openWay){
		window.location.href="${contextPath}/video/toTeamVideo?openWay=" + openWay;
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
                "url": "${contextPath}/video/getTeamVideoTable",
                "type": "post",
                "data": function (d) {
                    var param = {
						status : $("#status_q").val(),
                        checkStatus : $("#checkStatus_q").val(),
                        isSelected : $("#isSelected_q").val(),
						type :$("#type_q").val(),
						videoDisc : $("#videoDisc_q").val(),
                        operUserId : $("#operUserId_q").val(),
						videoType : $("#videoType_q").val()
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
                    <fus:hasRight url="/video/updateTeamVideoStatus">
					/*
                    if(e.status == 1){
                        str += '<a style="margin-left:10px;" onclick="openUpdateStatus(' + e.id + ',0)">无效</a>';
                    }else if(e.status == 0){
                        str += '<a style="margin-left:10px;" onclick="openUpdateStatus(' + e.id + ',1)">有效</a>';
                    }
                    */
					str += '<a style="margin-left:10px;" onclick="openUpdateCheck(' + e.id + ',1)">编辑</a>';

                    if(e.isSelected == 0){
						str += '<a style="margin-left:10px;" onclick="openUpdateCheck(' + e.id + ',2,1,'+e.sort+')">置为精选</a>';
					}else if(e.isSelected == 1){
						str += '<a style="margin-left:10px;" onclick="openUpdateCheck(' + e.id + ',2,0,0)">取消精选</a>';
					}
                    </fus:hasRight>
					str += '<a style="margin-left:10px;" onclick="openUpdateCheck(' + e.id + ',3)">查看</a>';
                    return str;
                },
                    title:'操作',
                    bSortable:false
                },
                {data: "id",bSearchable:false,title:'动态编号',width:"50px",defaultContent:""},
				{data: "videoName",bSearchable:false,title:'标题',bSortable:false,render:function(data){
						if(data == null || data == ''){
							return '';
						}else if(data.length > 20){
							return '<span titel="'+data+'">'+data.substring(0,20)+'......</span>';
						}else {
							return data;
						}
					},width:"150px"},
                {data: "videoDiscTemp",bSearchable:false,title:'内容',bSortable:false,render:function(data){
                    if(data == null || data == ''){
                        return '';
                    }else if(data.length > 50){
                        return '<span titel="'+data+'">'+data.substring(0,50)+'......</span>';
                    }else {
                    	return data;
					}
                },width:"150px"},
                {data: "videoType",bSearchable:false,title:'动态类型',width:"80px",render:function(data){
                    return getObjValByKey(videoTypeObj,data);
                }},
				{data: "type",bSearchable:false,title:'内容分类',width:"80px",render:function(data){
						return getObjValByKey(typeObj,data);
					}},
                {data: "checkStatus",bSearchable:false,title:'审核状态',bSortable:false,render:function(data){
                    return getObjValByKey(checkStatusObj,data);
                },width:"100px"},
                {data: "isSelected",bSearchable:true,title:'是否推荐',render:function(data){
                    return getObjValByKey(isSelectedObj,data);
                },width:"80px"},
                {data: "sort",bSearchable:false,title:'权重',defaultContent:"-",width:"50px"},
                {data: "readCnt",bSearchable:false,title:'阅读量',defaultContent:"",width:"50px"},
				{data: "replyCnt",bSearchable:false,title:'评论数',defaultContent:"",width:"50px"},
				{data: "shareCnt",bSearchable:false,title:'分享量',defaultContent:"",width:"50px"},
				{data: "voteCnt",bSearchable:false,title:'投票数',defaultContent:"",width:"50px"},
				{data: "goodCnt",bSearchable:false,title:'点赞数',defaultContent:"",width:"50px"},
                {data: "status",bSearchable:false,title:'状态',render:function(data){
                    return getObjValByKey(statusObj,data);
                },width:"50px"},
                {data: "inserttime",bSearchable:false,title:'创建时间',width:"100px",defaultContent:""}
            ],
            "order": [[ 1, "desc" ]]
        });
	}

    <fus:hasRight url="/video/updateTeamVideoStatus">
	function openUpdateStatus(id,status){
        var title = "是否确认";
	    if(status == 1){
			title += "有效视频";
		}else if(status == 0){
            title += "无效视频";
		}else{
		    uwayWarning("警告","视频状态异常");
		    return;
		}
	    uwayConfirm({
			content:title,
			confirm:function(){
			    var param = {status : status};
                updateTeamVideoStatus(id,param);
			},
			cancel:function(){

			}
		});
	}

	function updateTeamVideoStatus(id,param){
        $.uwayProcess.show();
        param.id = id;
        $.ajax({
            url: "${contextPath}/video/updateTeamVideoStatus",
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

    function openUpdateCheck(id,type,isSelected,sort){
		getTeamVideoInfoById(id);
	    $("#type_info").val(type);
	    if(type == 3){
	    	$(".modifyInfo").hide();
		}else{
			$(".modifyInfo").show();
		}
	    if(type == 2){
            $("#checkDiv").hide();
		}else if(type == 1){
            $("#checkDiv").show();
		}
        $("#checkStatus_info").val(1);
	    if (sort != undefined){
            $("#sort_info").val(sort);
		}else{
            $("#sort_info").val(0);
		}
		$("#isSelected_info").removeAttr("disabled");
        if (isSelected != undefined){
            $("#isSelected_info").val(isSelected);
			$("#isSelected_info").attr("disabled","true");
		}else{
            $("#isSelected_info").val(0);
		}
		checkStatusChg();
		$("#id_info").val(id);
		$("#infoModal").modal("show");
    }

    function updateTeamVideoInfo(){
        var type = $("#type_info").val();
        var checkStatus = $("#checkStatus_info").val();
        var checkReason = $("#checkReason_info").val();
		var isSelected = $("#isSelected_info").val();
        var title = "是否确认";
        if(type == 1){
            if(checkStatus == 1){
                title += "审核通过？";
            }else if(checkStatus == 2){
            	if(checkReason == ""){
					uwayWarning("警告","请填写驳回原因");
            		return;
				}
                title += "驳回？";
            }else{
                uwayWarning("警告","请选择审核状态");
                return;
            }
		}else{
        	if(isSelected == "1"){
				title += "设置为精选？";
			}else if(isSelected == "0"){
				title += "取消精选？";
			}
		}

        uwayConfirm({
            content:title,
            confirm:function(){
                var sort = $("#sort_info").val();
                var param = {sort : sort,isSelected : isSelected};
                if(type == 1){
                    param.checkStatus = checkStatus;
                    param.checkReason = checkReason;
				}
                updateTeamVideoStatus($("#id_info").val(),param);
            },
            cancel:function(){

            }
        });
    }
	</fus:hasRight>


	function getTeamVideoInfoById(id){
		$.uwayProcess.show();
		$.ajax({
			url: "${contextPath}/video/getTeamVideoById",
			async: true, type: "Post", dataType: "json",
			data: {id:id},
			success: function (response) {
				$.uwayProcess.close();
				if(response.success){
					$("#infoModal").modal("show");
					var info = response.result;
					console.log(info);
					$("#videoDiscTemp_info").text(info.videoDiscTemp);
					$("#videoDiv_info").empty();
					$("#imageDiv_info").empty();
					var type = info.type;
					console.log(type);
					if(type == 2){
						var str = '<video style="max-width:500px;max-height:400px;" controls="" autoplay="" name="media">' +
								'<source id="videoShow" src="'+info.videoUrl+'" type="video/mp4">' +
								'</video>';
						$("#videoDiv_info").append(str);
					}else if(type == 1){
						var str = "<table>";
						var imgLenght = info.imageList.length;
						for(var i=0;i<imgLenght;i++){
							if(i%3 == 0){
								str += "<tr>";
							}
							str += '<td style="width: 240px;padding:10px;"><img style="max-width:200px;max-height:200px;" src="<%=Global.getConfig("prefix.visit.path")%>'+info.imageList[i]['imageUrl']+'" /></td>';
							if (i%3 == 2 || i == imgLenght - 1){
								str += "</tr>";
							}
						}
						console.log(str);
						$("#imageDiv_info").append(str)
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

	function playVideo(url){
		$("#videoDiv").empty();
		var str = '<video style="max-width:500px;max-height:400px;" controls="" autoplay="" name="media">' +
            '<source id="videoShow" src="'+url+'" type="video/mp4">' +
            '</video>';
        $("#videoDiv").append(str);
	    $("#videoModal").modal({
			backdrop: false,//点击空白处不关闭对话框
            keyboard: false,//键盘关闭对话框
            show:true//弹出对话框
		});
	}
    function closeVideo(){
        $("#videoDiv").empty();
        $("#videoModal").modal("hide");
    }

	function checkStatusChg(){
		var checkStatus = $("#checkStatus_info").val();
		if(checkStatus == 2){
			$("#reasonDiv").show();
		}else{
			$("#reasonDiv").hide();
		}
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