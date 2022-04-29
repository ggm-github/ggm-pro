<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="background:url(${ctxStatic}/img/sysBg.png)">
	<head>
		<meta charset="utf-8">
		<title>热搜词</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

		<link rel="stylesheet" type="text/css" media="screen" href="${ctxStatic}/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" media="screen" href="${ctxStatic}/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" media="screen" href="${ctxStatic}/css/smartadmin-production.min.css">
		<link rel="stylesheet" type="text/css" media="screen" href="${ctxStatic}/css/smartadmin-skins.min.css">
		<link rel="stylesheet" type="text/css" media="screen" href="${ctxStatic}/js/plugin/jBox/jBox.css">
		<link rel="stylesheet" type="text/css"  href="${ctxStatic}/css/uway.common.css">
		<link rel="shortcut icon" href="${ctxStatic}/img/favicon/favicon.ico" type="image/x-icon">
		<link rel="icon" href="${ctxStatic}/img/favicon/favicon.ico" type="image/x-icon">
        <link rel="stylesheet" type="text/css" media="screen" href="${ctxStatic}/css/bootstrap-datetimepicker.min.css" >
        <style>
            #dataTable td{
                white-space: nowrap;
                max-width:400px !important;
                min-width:120px;
                overflow-x:hidden;
                text-overflow: ellipsis;
            }
            #dataTable {
                table-layout: fixed;
            }
            .select-query,.input-length{
                height : 30px;
                width : 150px;
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
						<li>APP通用管理</li><li>热搜词管理</li>
					</ol>
				</div>			
			</div>
            <div id="content">
                <!-- 查询条件DIV -->
                <div style="margin-top: 0px;margin-bottom: 10px;" >
                    <form id="queryForm" name="queryForm" style="min-width:1000px;" >
                        <div style="display: none;">
                            <label style="margin-left: 10px;">类型：</label>
                            <select id="hotType_q" name="hotType" class="select-query">

                            </select>
                        </div>
                        <div style="display: inline-block;">
                            <label style="margin-left: 10px">热搜词：</label>
                            <input id="hotName_q" name="hotName" class="input-length" placeholder="请输入热搜词名称" />
                        </div>
                        <label style="display: inline-block;">
                            <label style="margin-left: 10px">状态：</label>
                            <select id="status_q" name="status" class="select-query">

                            </select>
                            <label class="btn main-color-bluegreen" style="margin-left: 15px; width: 80px" onclick="query()"> 查 询</label>
                            <label class="btn main-color-bluegreen" style="margin-left: 15px; width: 80px" onclick="openModal('add')" >新 增</label>
<%--                            <label class="btn main-color-bluegreen" style="margin-left: 15px; width: 80px;" onclick="statusOperMultiple(0)">批量删除</label>--%>
<%--                            <label class="btn main-color-bluegreen" style="margin-left: 15px; width: 80px;" onclick="statusOperMultiple(1)">批量重启</label>--%>
                        </label>
                    </form>
                </div>

                <div id="tableRow" class="row" style="min-width: 1000px;">
                    <table id="dataTable" class="table table-striped table-bordered">

                    </table>
                </div>
            </div>


            <%--热搜词编辑Modal框--%>
            <div class="modal fade" id="hotSearchModal" tabindex="-1" role="dialog" aria-labelledby="hotSearchModalTitle" aria-hidden="true">
                <div class="modal-dialog" style="min-width:600px;">
                    <div class="modal-content" id="hot-search-form">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                &times;
                            </button>
                            <h4 class="modal-title" id="hotSearchModalTitle">热搜词</h4>
                        </div>
                        <div class="modal-body">
                            <div style="width: 300px;margin: 0 auto;">
                                <div style="display:none;">
                                    <input id="hotSearchId" name="id" type="hidden"/>
                                </div>
                                <div class="form-group">
                                    <label style="width:80px;text-align:right;" for="hotName">热搜词:</label>
                                    <input type="text" id="hotName" class="input-length" placeholder="请输入热搜词" />
                                </div>

                                <div class="form-group">
                                    <label style="width:80px;text-align:right;" for="hotType">类型:</label>
                                    <select id="hotType" class="select-query">

                                    </select>
                                </div>
                                <div class="form-group">
                                    <label style="width:80px;text-align:right;" for="status">类型:</label>
                                    <select id="status" class="select-query">

                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button id="addBtn" class="btn main-color-bluegreen" onclick="addHotSearch();">
                                确定
                            </button>
                            <button id="editBtn" class="btn main-color-bluegreen" onclick="updateHotSearch();">
                                确定
                            </button>
                            <button type="button" class="btn" data-dismiss="modal">
                                取消
                            </button>
                        </div>
                    </div>
                </div>
            </div>

        </div>
	</body>
	
<script src="${ctxStatic}/js/libs/jquery-2.0.2.min.js"></script>
<script src="${ctxStatic}/js/app.config.js"></script>
<script src="${ctxStatic}/js/bootstrap/bootstrap.min.js"></script>
<script src="${ctxStatic}/js/bootstrap/bootstrap-datetimepicker.js"></script>
<script src="${ctxStatic}/js/bootstrap/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="${ctxStatic}/js/plugin/jBox/jBox.min.js"></script>
<script src="${ctxStatic}/js/app.min.js"></script>
<script src="${ctxStatic}/js/leftmenu.js${jsversion}"></script>
<script src="${ctxStatic}/js/plugin-datatables/jquery.dataTables.min.js"></script>
<script src="${ctxStatic}/js/plugin-datatables/dataTables.bootstrap.min.js"></script>
<script src="${ctxStatic}/js/header.js${jsversion}"></script>
<script src="${ctxStatic}/js/time/time.js${jsversion}"></script>
<script src="${ctxStatic}/js/uway.common.js${jsversion}"></script>

<script>

    var table;
    var typeObj = {"1":"首页搜索"};
    var statusObj = {"0":"无效","1":"有效"};

	$(document).ready(function(){
	    //初始化类型下拉
        var allOptionStr = '<option value="">全部</option>';
        var typeStr = '';
	    $.each(typeObj,function(key,val){
            typeStr += '<option value="'+key+'">'+val+'</option>';
        });
	    $("#hotType_q").empty();
        $("#hotType_q").append(allOptionStr+typeStr);
        $("#hotType").empty();
        $("#hotType").append(typeStr);
        //初始化状态下拉
        typeStr = '';
        $.each(statusObj,function(key,val){
            typeStr += '<option value="'+key+'">'+val+'</option>';
        });
        $("#status_q").empty();
        $("#status_q").append(allOptionStr+typeStr);
        //默认查有效的
        $("#status_q").val(1);
        $("#status").empty();
        $("#status").append(typeStr);
        //默认查有效的
        $("#status").val(1);

	    //$.uwayProcess.show();
        //初始化dataTables
        table=initDataTable();
    });
    //查询
    function query(){
        table.ajax.reload();
    }

    //dataTable初始化 Ajax请求
    function initDataTable(){
        return $('#dataTable').DataTable({
            "scrollX": true,
            "serverSide": true,
            "searching":false,
            "order": [[ 4, "desc" ]],
            "ajax": {
                "url": "${contextPath}/hotSearch/getHotSearchTable",
                "type": "POST",
                "data": function (d) {
                    $.uwayProcess.show();
                    var condition = {};
                    condition.hotType = $("#hotType_q").val();
                    condition.status = $("#status_q").val();
                    condition.name = $("#hotName_q").val();
                    d.condition = JSON.stringify(condition);
                },
                "dataSrc" : function(data) {
                    $.uwayProcess.close();
                    return data.data;
                },
                "error":function (e1,e2,e3) {
                    if(e1.responseText.indexOf("<!DOCTYPE html>") != -1){
                        window.location = window.localStorage.getItem("CONTEXT_PATH") + '/user/login';
                    }
                }
            },
            "columns":[
                {data: function(e){
                    var operStr = '<a href="javascript:void(0);" onclick="openModal(\'edit\','+e.id+');">修改</a>&nbsp;&nbsp;&nbsp;';
                    var sts = e.status;
                    if(sts == 0){
                        operStr += '<a href="javascript:void(0);" onclick="statusOperOne(1,\''+e.id+'\');">重启</a>';
                    }else if(sts == 1){
                        operStr += '<a href="javascript:void(0);" onclick="statusOperOne(0,\''+e.id+'\');">删除</a>';
                    }

                    return operStr;
                },
                    title:'操作',
                    bSortable:false
                },
                {data: "hotName",bSearchable:false,title:'名称',defaultContent:""
                },
                {data: "hotType",bSearchable:false,title:'类型',
                    render:function(data){
                        return typeObj[data];
                    }
                },
                {data: "status",bSearchable:false,title:'状态',render:function(data){
                        return statusObj[data];
                    }
                },
                {data: "inserttime",bSearchable:false,title:'创建时间',defaultContent:""}
            ]
        });
    }
    //关闭拟态框
    function closeModal(){
        //关闭
        $("#hotSearchModal").modal("hide");
    }
    //打开拟态框
    function openModal(oper,id){
        //初始化模态框中的信息
        $("#hotSearchId").val("");
        $("#hotName").val("");
        $("#hotType").val("1");
        $("#status").val("1");

        if(oper == "edit"){
            $("#addBtn").hide();
            $("#editBtn").show();
            //回填信息
            getHotSearchInfo(id)
        }else{
            $("#addBtn").show();
            $("#editBtn").hide();
        }
        $("#hotSearchModal").modal("show");
    }
    //查询热搜词的详细信息
    function getHotSearchInfo(id){
        $.uwayProcess.show();
        $.ajax({
            url: "${contextPath}/hotSearch/getHotSearchInfo",
            async: true, type: "Post", dataType: "json",
            data: {id:id},
            success: function (response) {
                $.uwayProcess.close();
                if (response.success) {
                    $("#hotSearchId").val(response.result.id);
                    $("#hotName").val(response.result.hotName);
                    $("#hotType").val(response.result.hotType);
                    $("#status").val(response.result.status);
                }else{
                    uwayNotice('通知',"操作失败");
                    console.log(response.message);
                }

            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log(textStatus);
                $.uwayProcess.close();
            }
        });
    }

    //获取参数并校验
    function getParamAndCheck(){
        var hotName = $("#hotName").val();
        if(hotName == ""){
            uwayWarning("警告","热搜词不能为空");
            return null;
        }
        var hotType = $("#hotType").val();
        if(hotType == ""){
            uwayWarning("警告","请选择热搜词类型");
            return null;
        }
        var status = $("#status").val();
        if(status == ""){
            uwayWarning("警告","状态不能为空");
        }
        var data = {};
        data["hotName"] = hotName;
        data["hotType"] = hotType;
        data["status"] = status;
        return data;
    }

    //新增热搜词
    function addHotSearch(){
        var data = getParamAndCheck();
        if(data == null){
            return;
        }

        $.uwayProcess.show();
        $.ajax({
            url: "${contextPath}/hotSearch/addHotSearch",
            async: true, type: "Post", dataType: "json",
            data: {param:JSON.stringify(data)},
            success: function (response) {
                $.uwayProcess.close();
                if (response.success) {
                    uwayNotice('通知',response.result);
                    closeModal();
                    query();
                }else{
                    uwayWarning('通知',response.message);
                }

            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log(textStatus);
                $.uwayProcess.close();
            }
        });
    }

    //修改热搜词信息
    function updateHotSearch(){
        var data = getParamAndCheck();
        if(data == null){
            return;
        }
        data["id"] = $("#hotSearchId").val();
        $.uwayProcess.show();
        $.ajax({
            url: "${contextPath}/hotSearch/updateHotSearch",
            async: true, type: "Post", dataType: "json",
            data: {param:JSON.stringify(data)},
            success: function (response) {
                $.uwayProcess.close();
                if (response.success) {
                    uwayNotice('通知',response.result);
                    closeModal();
                    query();
                }else{
                    uwayWarning('通知',response.message);
                }

            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log(textStatus);
                $.uwayProcess.close();
            }
        });
    }

    //修改热搜词的状态
    function statusOperMultiple(statusParam){
        var idArr = new Array();
        var param = {};
        var title;
        if(statusParam == 0){
            title = "确定要删除";
        }else if(statusParam == 1){
            title = "确定要重启";
        }

        param["statusParam"] = statusParam;
        var ids = "";

        $(".chb_check:checked").each(function(i,obj){
            ids += $(obj).val()+",";
        });
        if(ids != ""){
            param["ids"] = ids.substr(0,ids.length-1);
            title += "【"+$(".chb_check:checked").length+"】条记录吗？"
        }else{
            var hotType = $("#hotType_q").val();
            hotType = hotType == "" ? null:hotType;
            var status = $("#status_q").val();
            status = status == "" ? null:status;
            var hotName = $("#hotName").val();
            if(hotType == "" && status == "" && hotName == ""){
                uwayNotice("通知","请选择要操作的记录或添加对应条件");
                return;
            }
            if(status == statusParam){
                uwayNotice("通知","筛选数据与操作相同，无需操作，请核实");
                return;
            }
            param["hotType"] = hotType;
            param["status"] = status;
            param["hotName"] = hotName;
            title += "当前条件下的记录吗？"
        }

        uwayConfirm({
            content:title,
            confirm:function(){
                updateStatus({"param":JSON.stringify(param)});
                uwayStopBubble(event);
            },
            cancel:function(){

            }
        });

    }

    function statusOperOne(statusParam,id){
        var content = "";
        if(statusParam == 0){
            content = '确定要删除吗？';
        }else if(statusParam == 1){
            content = '确定要重启吗？';
        }
        uwayConfirm({
            content:content,
            confirm:function(){
                var param = '{"id":"'+id+'","statusParam":"'+statusParam+'"}';
                updateStatus({"param":param});
            },
            cancel:function(){

            }
        });
    }

    function updateStatus(data){
        $.uwayProcess.show();
        $.ajax({
            url: "${contextPath}/hotSearch/updateStatus",
            async: true, type: "Post", dataType: "json",
            data: data,
            success: function (response) {

                $.uwayProcess.close();
                if (response.success) {
                    uwayNotice('通知',response.result);
                    query();
                }else{
                    uwayWarning('通知',response.message);
                }

            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log(textStatus);
                $.uwayProcess.close();
            }
        });
    }

</script>
</html>