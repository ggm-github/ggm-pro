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
				width: 150px;
			}
			.select-query{
				height: 30px;
				width: 150px;
			}
			.input-length{
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
						<li>内容管理</li><li>广告管理</li>
					</ol>
				</div>
			</div>
			<div id="content">
				<div class="condition-wrapper">
					<div class="divform">
						<label for="advName_q">广告标题</label>
						<input id="advName_q" type="text" class="input-length" />
					</div>
					<div class="divform">
						<label for="positionType_q">广告位置</label>
						<select id="positionType_q" class="select-query">

						</select>
					</div>
					<div class="divform">
						<label for="status_q">发布状态</label>
						<select id="status_q" class="select-query">

						</select>
					</div>

					<div class="divform">
						<button class="btn main-color-bluegreen" onclick="query()">查询</button>
						<fus:hasRight url="/ad/addAdInfo">
							<button class="btn main-color-bluegreen" onclick="openAddModal()">新增</button>
						</fus:hasRight>
					</div>
				</div>
				<div class="condition-wrapper" style="margin-top:10px;">
					<div class="divform">
						<label>创建时间</label>
						<label class="data_time">
							<input type="text" id="insertStartDate" value="" class="input-length" style="height: 30px;">
							<i class="icon-append fa fa-calendar" style="margin-left: -28px; height: 28px; width: 28px;"></i>
						</label> <b>~ </b>
						<label class="data_time">
							<input type="text" id="insertEndDate" value="" class="input-length" style="height: 30px;">
							<i class="icon-append fa fa-calendar" style="margin-left: -28px; height: 28px; width: 28px;"></i>
						</label>
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
							<div style="display:none;">
								<input id="id_info" type="text" class="emptyInfo"/>
							</div>
							<div class="form-group">
								<label for="advName_info">广告标题：</label>
								<input id="advName_info" type="text" class="input-length-big" maxlength="15"/>
							</div>
							<div class="form-group">
								<label for="positionType_info">广告位置：</label>
								<select id="positionType_info" class="select-query">

								</select>
							</div>
							<div class="form-group">
								<label for="sort_info">权重：</label>
								<input id="sort_info" type="text" class="input-length" />
							</div>
							<div class="form-group">
								<label for="advUrl_info">广告链接：</label>
								<input id="advUrl_info" type="text" class="input-length-big" placeholder="请填写链接地址" />
							</div>

							<div class="form-group">
								<label style="">图片选择：</label>
								<form id="insertform" style="display:inline;" method="post" action="/comm/upload"  enctype="multipart/form-data"  target="imageframe">
									<label style="width:210px;">
										<input style="width: 200px" type="file" name="myfile" id="myfile" value="" onchange="preImg(this.id,'imgPre');" />
										<input type="hidden" id="picturePath_info" value="" />
										<input type="hidden" name="path" value="prefix.upload.path.advertisement"/>
									</label>
									<label>
										<input class="btn main-color-bluegreen" type="submit" value="上传" onclick="upload()"/>
									</label>
								</form>
							</div>

							<div class="form-group" style="margin: 0 auto;text-align:center;">
								<img id="imgPre" src="" style="display: none;max-height:150px" />
							</div>

						</div>
					</div>
					<div class="modal-footer">
						<button id="addBtn" class="btn main-color-bluegreen" style="display:none;" onclick="addAdvInfo()">提交</button>
						<button id="updBtn" class="btn main-color-bluegreen" style="display:none;" onclick="updateAdvInfo()">提交</button>
						<button class="btn" onclick="$('#infoModal').modal('hide')">取消</button>
					</div>
				</div>
		</div>



		<iframe id="imageframe" name="imageframe" style="display: none;"></iframe>

<script src="${ctxStatic}/js/libs/jquery-2.0.2.min.js${jsversion}"></script>
<script src="${ctxStatic}/js/app.config.js${jsversion}"></script>
<script src="${ctxStatic}/js/libs/jquery-ui-1.10.3.min.js${jsversion}"></script>
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

	var statusObj = {"0":"待发布","1":"已发布"};
	var positionTypeObj = {"1":"精选新闻","2":"精选广告","3":"投票成功"};
	var dataTable;
	$(function() {
		initSelect();
		initDateTimer();
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
        $.each(positionTypeObj,function(key,val){
            str += '<option value="'+key+'">'+val+'</option>';
        });
        $("#positionType_q").empty();
        $("#positionType_q").append(allstr + str);
        $("#positionType_q").val("");
        $("#positionType_info").empty();
        $("#positionType_info").append(str);
        $("#positionType_info").val("");

	}

	function initDateTimer(){
        $('#insertStartDate').attr("readonly", "readonly").datepicker({   //日历初始化
            dateFormat: 'yy-mm-dd',
//            maxDate: 'd',
            showTime: true,
            monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
            dayNames: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
            dayNamesShort: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
            dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'],
            prevText: '<i class="fa fa-chevron-left"></i>',
            nextText: '<i class="fa fa-chevron-right"></i>',
            onSelect: function (selectedDate) {
                $("#insertEndDate").datepicker('option', 'minDate', selectedDate);
            }
        });
        $('#insertEndDate').attr("readonly", "readonly").datepicker({   //日历初始化
            dateFormat: 'yy-mm-dd',
//            maxDate: 'd',
            showTime: true,
            monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
            dayNames: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
            dayNamesShort: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
            dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'],
            prevText: '<i class="fa fa-chevron-left"></i>',
            nextText: '<i class="fa fa-chevron-right"></i>',
            onSelect: function (selectedDate) {
                $("#insertStartDate").datepicker('option', 'maxDate', selectedDate);
            }
        });
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
            "scrollX": true,
            "processing": true,
            "serverSide": true,
            "ajax": {
                "url": "${contextPath}/ad/getAdTable",
                "type": "post",
                "data": function (d) {
                    var param = {
						advName : $("#advName_q").val(),
						status : $("#status_q").val(),
                        insertStartDate : $("#insertStartDate_q").val(),
                        insertEndDate : $("#insertEndDate_q").val(),
						positionType : $("#positionType_q").val()
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

					<fus:hasRight url="/ad/updateAdInfo">
                    if(e.status == 0) {
                        str += '<a style="margin-left:10px;" onclick="openEditorModal(' + e.id + ')">编辑</a>';
                    }
					</fus:hasRight>
					<fus:hasRight url="/ad/updateAdForShow">
                    if(e.status == 0) {
                        str += '<a style="margin-left:10px;" onclick="openUpdateStatus(' + e.id + ',1)">发布</a>';
                    }else if (e.status == 1){
                        str += '<a style="margin-left:10px;" onclick="openUpdateStatus(' + e.id + ',0)">撤销</a>';
                    }
					</fus:hasRight>
                    return str;
                },
                    title:'操作',
                    bSortable:false
                },
                {data: "id",bSearchable:false,title:'广告ID',width:"100px",defaultContent:""},
                {data: "advName",bSearchable:true,title:'广告标题',width:"250px",defaultContent:""},
                {data: "positionType",bSearchable:false,title:'广告类型',width:"150px",render:function(data){
                    return getObjValByKey(positionTypeObj,data);
                }},
                {data: "advUrl",bSearchable:false,title:'广告链接',width:"300px",bSortable:false,defaultContent:""},
                {data: "status",bSearchable:false,title:'状态',render:function(data){
                    return getObjValByKey(statusObj,data);
                },width:"100px"},
                {data: "inserttime",bSearchable:false,title:'创建时间',width:"150px",defaultContent:""}

            ],
            "order": [[ 1, "desc" ]],
        });
	}

	function getParamAndCheck(){
	    var advName = $("#advName_info").val();
        var positionType = $("#positionType_info").val();
        var advUrl = $("#advUrl_info").val();
        var picturePath = $("#picturePath_info").val();
		var sort = $("#sort_info").val();
		if(sort == ''){
			sort = '0';
		}
        var param = {
            advName : advName,
            positionType : positionType,
            advUrl : advUrl,
			sort : sort,
            picturePath : picturePath
		};
        return param;
	}

	<fus:hasRight url="/ad/addAdInfo">
    function openAddModal(){
        $("#advName_info").val("");
        $("#positionType_info").val(1);
        $("#advUrl_info").val("");
        $("#picturePath_info").val("");
		$("#sort_info").val("0");
        $("#imgPre").hide();
        $("#imgFile").val("");
        $("#addBtn").show();
        $("#updBtn").hide();
        $("#infoModal").modal("show");
	}
	function addAdvInfo(){
        var param = getParamAndCheck();
        if (param == null){
            return;
		}
        $.uwayProcess.show();
        $.ajax({
            url: "${contextPath}/ad/addAdInfo",
            async: true, type: "Post", dataType: "json",
            data: {"condition" : JSON.stringify(param)},
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

	<fus:hasRight url="/ad/updateAdInfo">
	function openEditorModal(id){
        $.uwayProcess.show();
        $.ajax({
            url: "${contextPath}/ad/getAdInfoById",
            async: true, type: "Post", dataType: "json",
            data: {"id" : id},
            success: function (response) {
                $.uwayProcess.close();
                if(response.success){
                    $("#id_info").val(response.result.id);
                    $("#advName_info").val(response.result.advName);
                    $("#positionType_info").val(response.result.positionType);
                    $("#sort_info").val(response.result.sort);
                    $("#advUrl_info").val(response.result.advUrl);
                    var picturePath = response.result.picturePath;
                    $("#picturePath_info").val(picturePath);
                    $("#imgPre").attr("src",'<%=Global.getConfig("prefix.visit.path") %>' + picturePath);
                    $("#imgPre").show();
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

    function updateAdvInfo(){
        var param = getParamAndCheck();
        if (param == null){
            return;
        }
        param.id = $("#id_info").val();
        $.uwayProcess.show();
        $.ajax({
            url: "${contextPath}/ad/updateAdInfo",
            async: true, type: "Post", dataType: "json",
            data: {"condition" : JSON.stringify(param)},
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

    <fus:hasRight url="/ad/updateAdForShow">
	function openUpdateStatus(id,status){
        var title = "是否确认";
	    if(status == 1){
			title += "发布？";
		}else if(status == 0){
            title += "取消？";
		}else{
		    uwayWarning("警告","状态异常");
		    return;
		}
	    uwayConfirm({
			content:title,
			confirm:function(){
			    var param = {status : status};
                updateAdStatus(id,param);
			},
			cancel:function(){

			}
		});
	}

	function updateAdStatus(id,param){
        $.uwayProcess.show();
        param.id = id;
        $.ajax({
            url: "${contextPath}/ad/updateAdForShow",
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




    //文件上传
    function upload() {
        $.uwayProcess.show();
        var t = setInterval(function() {
            //获取iframe标签里body元素里的文字。即服务器响应过来的"上传成功"或"上传失败"
            var word = $("iframe[id='imageframe']").contents().find("body").text();
            var result;
            try {
                result = JSON.parse(word);
            } catch (e){
                console.log('等待上传请求返回信息......');
                return;
            }
            if (result.success == true) {
                var fName = result.result;
                $("#picturePath_info").val(fName)
                uwayNotice("通知", "上传成功！");
                clearInterval(t);
                $.uwayProcess.close();
            } else {
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
        $("#picturePath_info").val("");
        $("iframe[id='imageframe']").contents().find("body").html("");
    }
</script>
</body>
</html>