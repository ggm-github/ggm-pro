<%@ taglib prefix="fus" uri="http://java.sun.com/jsp/jstl/funs" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="background:url(${ctxStatic}/img/sysBg.png)">
	<head>
		<meta charset="utf-8">
		<title>消息管理</title>
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
            #messageTable td{
                white-space: nowrap;
                max-width:400px !important;
                min-width:120px;
                overflow-x:hidden;
                text-overflow: ellipsis;
            }
            #messageTable {
                table-layout: fixed;
            }
            .form-group label{
                width:130px;
                text-align:right;
            }
            .form-group select{
                width: 150px;
                height:30px;
            }
            .select-query{
                height: 30px;
                width: 150px;
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
						<li>APP通用管理</li><li>消息管理</li>
					</ol>
				</div>			
			</div>
            <div id="content">
                <!-- 查询条件DIV -->
                <div style="margin-top: 0px;margin-bottom: 10px;" >
                    <form id="logQuery" name="logQuery" style="min-width:1000px;" >
                        <div style="display: inline-block;">
                            <label style="margin-left: 10px;">类型：</label>
                            <select id="messageType" name="messageType" class="select-query">

                            </select>
                        </div>
                        <label style="display: inline-block;">
                            <label style="margin-left: 10px">状态：</label>
                            <select id="status" name="status" class="select-query">

                            </select>
                            <label class="btn main-color-bluegreen" style="margin-left: 15px; width: 80px" onclick="query()"> 查 询</label>
                            <fus:hasRight url="/message/addMessage">
                                <label class="btn main-color-bluegreen" style="margin-left: 15px; width: 80px" onclick="openAddModal()">新 增</label>
                            </fus:hasRight>
                            <%--<label class="btn main-color-bluegreen" style="margin-left: 15px; width: 80px;" onclick="statusOperMultiple(0)">批量下架</label>--%>
                            <%--<label class="btn main-color-bluegreen" style="margin-left: 15px; width: 80px;" onclick="statusOperMultiple(1)">批量上架</label>--%>
                        </label>
                    </form>
                </div>

                <div id="tableRow" class="row" style="min-width: 1000px;">
                    <table id="messageTable" class="table table-striped table-bordered">

                    </table>
                </div>

            </div>
			
		</div>

        <div class="modal fade" id="infoModal" tabindex="-1" role="dialog" aria-labelledby="infoModalTitle" aria-hidden="false" style="z-index:1000;">
            <div class="modal-dialog" style="width:600px;">
                <div class="modal-content" >
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                            &times;
                        </button>
                        <h4 class="modal-title">编辑</h4>
                    </div>
                    <div class="modal-body" id="infoModalBody">
                        <div style="width: 600px;margin: 0 auto;">
                            <div class="form-group">
                                <label>消息类型：</label>
                                <select id="messageType_info" name="messageType" onchange="chooseMessageType();">

                                </select>
                            </div>

                            <div class="form-group">
                                <label>标题：</label>
                                <input id="referenceInfo_info" name="referenceInfo" />
                            </div>

                            <div class="form-group msgInfoUM" style="display:none;">

                                <label>消息类型：</label>
                                <select id="UMType" name="UMType"  onchange="chooseType()">
                                    <option value="0" selected>Url页面</option>
                                    <option value="1">App页面</option>
                                </select>
                            </div>
                            <div class="form-group" id="openWayDiv" style="display:none;">
                                <label>跳转方式：</label>
                                <select id="openWay_info" onchange="openWayChg();">

                                </select>
                            </div>
                            <div id="referentUrldiv" class="form-group msgInfo" style="display:none;">
                                <label>访问地址：</label><input id="referentUrl_info" name="referentUrl">
                            </div>
                            <div id="openDescDiv" class="form-group" style="display:none;">
                                <label></label>
                                <span id="openDesc_0">链接跳转，请填写要跳转的链接地址</span>
                                <span id="openDesc_1">赛事原生，请填写要打开的赛事ID，请在【赛事管理】中，粘取赛事ID填入</span>
                            </div>
                            <div id="referentNamediv" class="form-group msgInfoUM" style="display:none;">
                                <label>App页面：</label><input id="referentName_info" name="referentName">
                            </div>
                            <div class="form-group">
                                <label>描述信息：</label><input id="referentDsc_info" name="referentDsc">
                            </div>
                            <div class="form-group msgInfoUM" style="float:left;display:none; width:800px;margin-top:10px;">
                                <label>终端类型：</label>
                                <label style="width:80px;">
                                    <input type="radio" name="UMradio" checked="checked" value="0"> 全部</label>
                                <label style="width:80px;">
                                    <input type="radio" name="UMradio" value="1">Android</label>
                                <label style="width:80px;">
                                    <input type="radio" name="UMradio" value="2">IOS</label>
                            </div>

                            <div style="display:none;">
                                <input id="messageId_info" name="id" />
                                <input type="hidden" id="picUrl_info"/>
                            </div>

                            <div class="form-group">
                                <label>区域：</label>
                                <input type="text" id="areaName_info" onfocus="showTree('1','areaId_info','areaName_info');" />
                                <input type="hidden" id="areaId_info" />
                            </div>
                            <div class="form-group">
                                <label>行业：</label>
                                <input type="text" id="industryName_info" onfocus="showTree('2','industryId_info','industryName_info');" />
                                <input type="hidden" id="industryId_info" />
                            </div>

                            <div class="form-group" id="imgUploadDiv">
                                <label style="">图片选择：</label>
                                <form id="insertform" method="post" action="/comm/upload"  enctype="multipart/form-data"  target="uploadframe">
                                    <label style="width:210px;">
                                        <input style="width: 200px" type="file" name="myfile" id="myfile" value="" onchange="preImg(this.id,'imgShow');" />
                                        <input type="hidden" name="path" value="prefix.upload.path.message"/>
                                    </label>
                                    <label>
                                        <input class="btn main-color-bluegreen" type="submit" value="上传" onclick="upload()"/>
                                    </label>
                                </form>
                            </div>
                            <div class="form-group" style="text-align:center; ">
                                <img id="imgShow" src="" style="display:none;max-width:200px;"/>
                            </div>

                            <div class="form-group">
                                <label>是否推送：</label>
                                <select id="isUmeng_info" class="select-query">
                                    <option value="0">否</option>
                                    <option value="1">是</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>接收人：</label><span id="receiveUserName"></span>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <input id="addBtn" class="btn main-color-bluegreen" type="button" value="新增" onclick="addMessage();"/>
                        <input id="backBtn" class="btn main-color-bluegreen" type="button" value="取消" onclick="$('#infoModal').modal('hide');"/>
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
        <iframe id="uploadframe" name="uploadframe" style="display:none;"></iframe>
	</body>
	
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

<script>

    var industryTreeList = JSON.parse(${fns:toJson(industryTree)});
    var areaTreeList = JSON.parse(${fns:toJson(areaTree)});
    var table;
    var typeObj = {"0":"闲散消息","1":"操场官方消息", "2":"队伍消息",/*"3":"友盟推送",*/"5":"@我"};
    var openWayObj = {"-1":"无跳转","0":"链接地址","1":"赛事详情","14":"话题详情","18":"动态详情"};
    var statusObj = {"0":"审核中","1":"有效","2":"无效"};

	$(document).ready(function(){

        initJsTree("industryTree_div",industryTreeList);
        initJsTree("areaTree_div",areaTreeList);

	    //初始化类型下拉
        var typeStr = '<option value="">全部</option>';
        var str = '';
	    $.each(typeObj,function(key,val){
            str += '<option value="'+key+'">'+val+'</option>';
        });
	    $("#messageType").empty();
        $("#messageType").append(typeStr + str);
        $("#messageType_info").empty();
        $("#messageType_info").append(str);
        //初始化状态下拉
        typeStr = '<option value="">全部</option>';
        $.each(statusObj,function(key,val){
            typeStr += '<option value="'+key+'">'+val+'</option>';
        });
        $("#status").empty();
        $("#status").append(typeStr);
        //默认查有效的
        $("#status").val("");

        str = '';
        $.each(openWayObj,function(key,val){
            str += '<option value="'+key+'">'+val+'</option>';
        });
        $("#openWay_info").empty();
        $("#openWay_info").append(str);
        $("#openWay_info").append(-1);

        initDataTable();

        //全选框事件绑定
        /*
        $("#chb_all").click(function(){
            if($("#chb_all").is(":checked")){
                $(".chb_check").prop('checked',"checked");
            }else{
                $(".chb_check").removeAttr("checked");
            }
        });
        */
    });

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

	//页面跳转
	function openAddModal(){

        window.location.href="${contextPath}/message/toPage/message?operType=add";

        /*
        $("#messageType_info").val(1);
        $("#referenceName_info").val("");
        $("#picUrl_info").val("");
        $("#imgShow").hide();
        $("#referenceInfo_info").val("");
        $("#referentDsc_info").val("");
        $("#referentUrl_info").val("");
        $("#myfile").val("");
        $("#areaId_info").val("");
        $("#areaName_info").val("");
        $("#industryId_info").val("");
        $("#industryName_info").val("");
        $("#imgUploadDiv").show();
        $("#addBtn").show();
        chooseMessageType();
        $("#openWay_info").val(-1);
        openWayChg();
        $("#infoModalBody :input").removeAttr("disabled");
        $("#isUmeng_info").val("0");
        $("#infoModal").modal("show");
         */
    }

    function chooseMessageType(){
        var messageType = $("#messageType_info").val();
        $(".msgInfoUM").hide();
        $("#openWayDiv").hide();
        if(messageType == 3){
            $(".msgInfoUM").show();
            chooseType();
        }else{
            $("#openWayDiv").show();
            openWayChg();
        }
    }

    function openWayChg(){
        var openWay = $("#openWay_info").val();
        $("[id^=openDesc_]").hide();
        if(openWay == -1){
            $("#referentUrldiv").hide();
            $("#openDescDiv").hide();
        }else{
            $("#openDesc_"+openWay).show();
            $("#referentUrldiv").show();
            $("#openDescDiv").show();
        }
    }

    function chooseType(){
        var value = $("#UMType").val();
        if( value == 1){
            $("#referentUrldiv").hide();
            $("#referentNamediv").show();
        } else{
            $("#referentUrldiv").show();
            $("#referentNamediv").hide();
        }
    }

    function openInfoModel(id){
        $.uwayProcess.show();
        $.ajax({
            url: "${contextPath}/message/getMessageInfo",
            async: true, type: "Post", dataType: "json",
            data: {messageId : id},
            success: function (response) {
                $.uwayProcess.close();
                var result = response.result;
                console.log(result);
                $("#messageType_info").val(result.messageType);
                $("#referenceInfo_info").val(result.referentName);
                $("#picUrl_info").val(result.picUrl);
                if(result.picUrl != null && result.picUrl != ''){
                    $("#imgShow").show();
                    $("#imgShow").attr("src",'<%=Global.getConfig("prefix.visit.path")%>' + result.picUrl);
                }else{
                    $("#imgShow").hide();
                }
                $("#imgUploadDiv").hide();
                $("#addBtn").hide();
                $("#referentDsc_info").val(result.referentDsc);
                $("#referentUrl_info").val(result.referentUrl);
                $("#areaId_info").val(result.areaId);
                $("#areaName_info").val(result.areaName);
                $("#industryId_info").val(result.industryId);
                $("#industryName_info").val(result.industryName);
                var isUmeng = result.isUmeng;
                $("#isUmeng_info").val(isUmeng);
                chooseMessageType();
                $("#openWay_info").val(result.openWay);
                openWayChg();
                $("#receiveUserName").text(result.userNames);
                $("#infoModalBody :input").attr("disabled","disabled");
                $("#infoModal").modal("show");

            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log(textStatus);
                $.uwayProcess.close();
            }
        });
    }


    function query(){
        table.ajax.reload();
    }

    //dataTable Ajax请求
    function initDataTable(){
        //否则直接查询
        table = $('#messageTable').DataTable({
            "scrollX": true,
            "serverSide": true,
            "searching":false,
            "order": [[1,"desc"]],
            "ajax": {
                "url": "${contextPath}/message/getMessageTable",
                "type": "POST",
                "data": function (d) {
                    $.uwayProcess.show();
                    var condition = {};
                    condition.messageType = $("#messageType").val();
                    condition.status = $("#status").val();
                    condition.relId = 0;
                    d.condition = JSON.stringify(condition);
                    console.log(d);
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
                    var operStr = "";
                    var sts = e.status;
                    operStr += '<a href="javascript:void(0);" style="margin-left:10px;" onclick="openInfoModel(\''+e.messageId+'\');">查看</a>';
                    <fus:hasRight url="/message/updateMessageForSend">
                    if(sts == 0){
                        operStr += '<a href="javascript:void(0);" style="margin-left:10px;" onclick="openCheckModel(\''+e.messageId+'\');">发布</a>';
                    }
                    </fus:hasRight>
                    <fus:hasRight url="/message/updateMessageStatus">
                    if(sts == 0 || sts == 1){
                        operStr += '<a href="javascript:void(0);" style="margin-left:10px;" onclick="statusOperOne(2,\''+e.messageId+'\');">无效</a>';
                    }
                    </fus:hasRight>

                    return operStr;
                },
                    title:'操作',
                    bSortable:false,width:"100px"
                },
                {data: "messageId",bSearchable:false,title:'消息id',width:"80px",defaultContent:""},
                {data: "messageType",bSearchable:false,title:'类型',width:"50px",render:function(data){
                    return typeObj[data];
                },defaultContent:""},
                {data: "referentName",bSearchable:false,title:'名称',width:"250px",render:function(data){
                    if(data != null && data.length > 30){
                        return '<p style="margin-bottom: 0px;" title="' + data + '">'+data.substr(0,26)+'...</p>';
                    }else if (data != null && data.length<=30) {
                        return '<p style="margin-bottom: 0px;" title="'+data+'">'+data+'</p>';
                    }else {
                        return '<p style="margin-bottom: 0px;" title=""></p>';
                    }
                },defaultContent:""},
                {data: "openWay",bSearchable:false,title:'跳转方式',width:"50px",render:function(data){
                    return openWayObj[data];
                }},
                {data: "status",bSearchable:false,title:'状态',width:"50px",render:function(data){
                    return statusObj[data];
                }},
                {data: "createTime",bSearchable:false,title:'创建时间',width:"100px"}
            ]
        });
    }
    //下架轮播图
    function statusOperMultiple(statusParam){
        var idArr = new Array();
        var param = {};
        var title;
        if(statusParam == 0){
            title = "确定要取消发布";
        }else if(statusParam == 1){
            title = "确定要发布";
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
            var messageType = $("#messageType").val();
            messageType = messageType == "" ? null:messageType;
            var status = $("#status").val();
            status = status == "" ? null:status;
            if(messageType == "" && status == ""){
                uwayNotice("通知","请选择要操作的记录或添加对应条件");
                return;
            }
            if(status == statusParam){
                uwayNotice("通知","筛选数据与操作相同，无需操作，请核实");
                return;
            }
            param["messageType"] = messageType;
            param["status"] = status;
            title += "当前条件下的记录吗？"
        }

        uwayConfirm({
            content:title,
            confirm:function(){
                updateStatus({"param":JSON.stringify(param)});
            },
            cancel:function(){

            }
        });
    }



    function statusOperOne(statusParam,id){
        var content = "";
        if(statusParam == 2){
            content = '确定要失效吗？';
        }else if(statusParam == 1){
            content = '确定要有效吗？';
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
        console.log("data:"+JSON.stringify(data));
        $.uwayProcess.show();
        $.ajax({
            url: "${contextPath}/message/updateMessageStatus",
            async: true, type: "Post", dataType: "json",
            data: data,
            success: function (response) {

                $.uwayProcess.close();
                if (response.success) {
                    uwayNotice('通知',response.result);
                    query();
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

    function openCheckModel(id){
        uwayConfirm({
            content:"是否确认发布此消息？",
            confirm:function(){
                updateStatusForSend(id);
            },
            cancel:function(){

            }
        });
    }

    function updateStatusForSend(id){
        $.uwayProcess.show();
        $.ajax({
            url: "${contextPath}/message/updateMessageForSend",
            async: true, type: "Post", dataType: "json",
            data: {id:id},
            success: function (response) {
                $.uwayProcess.close();
                if (response.success) {
                    uwayNotice('通知',"操作成功");
                    table.draw(false);
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


    //新增的信息的校验与获取
    function getParamInfo(){
        var messageType = $("#messageType_info").val();
        var picUrl = $("#picUrl_info").val();
        var referenceInfo = $("#referenceInfo_info").val();
        var referenceUrl = $("#referentUrl_info").val();
        var referenceDsc = $("#referentDsc_info").val();
        var areaId = $("#areaId_info").val();
        var industryId = $("#industryId_info").val();
        var openWay = $("#openWay_info").val();
        if(messageType == ""){
            uwayWarning("警告","类型不能为空");
            return null;
        }
        var isUmeng = $("#isUmeng_info").val();
        if(isUmeng == ""){
            uwayWarning("警告","请选择是否进行推送");
            return null;
        }
        //获取新增内容的基本信息
        var param = {};
        param["messageType"] = messageType;
        param["picUrl"] = picUrl;
        param["referentName"] = referenceInfo;
        param["referentUrl"] = referenceUrl;
        param["referentDsc"] = referenceDsc;
        param["areaId"] = areaId;
        param["industryId"] = industryId;
        param["openWay"] = openWay;
        param["isUmeng"] = isUmeng;
        return param;
    }

    //新增的信息的校验与获取
    function getPushInfo(){
        var messageType = $("#messageType_info").val();
        var referenceInfo = $("#referenceInfo_info").val();
        var referenceUrl = $("#referentUrl_info").val();
        var referenceDsc = $("#referentDsc_info").val();
        var referentName = $("#referentName_info").val();
        var selectV = $("#UMType").val();

        var radiovalue="";
        var UMradio = document.getElementsByName("UMradio");
        for(i=0;i<UMradio.length;i++){
            if(UMradio[i].checked)
            {
                radiovalue=UMradio[i].value;
            }
        }

        if(messageType == ""){
            uwayWarning("警告","类型不能为空");
            return null;
        }

        if(selectV == 1 && referentName == ''){
            uwayWarning("警告","请填写app页面完整路径");
            return null;
        }
        if(selectV == 0 && referenceUrl == ''){
            uwayWarning("警告","请填写Url完整路径");
            return null;
        }
        if(referenceInfo == ""){
            uwayWarning("警告","请填写标题");
            return null;
        }
        if(referenceDsc == ""){
            uwayWarning("警告","请填写消息正文");
            return null;
        }

        //获取新增内容的基本信息
        var Pushparam = {};
        Pushparam.title = referenceInfo;
        Pushparam.url = referenceUrl;
        Pushparam.body = referenceDsc;
        Pushparam.activity = referentName;
        Pushparam.type = selectV;
        Pushparam.messageType = radiovalue;
        Pushparam.imageurl=$("#picUrl_info").val()
        return Pushparam;
    }
    //新增请求
    function addMessage(){
        var messageType = $("#messageType_info").val();
        var param;
        var url;
        if(messageType == 3) {
            param = getPushInfo();
            if (param == null) {
                return;
            }
            url = "${contextPath}/message/push";
        }else{
            param = getParamInfo();
            if(param == null){
                return;
            }
            url = "${contextPath}/message/addMessage";
        }

        $.uwayProcess.show();
        $.ajax({
            url: url,
            async: true, type: "Post", dataType: "json",
            data: {param:JSON.stringify(param)},
            success: function (response) {
                $.uwayProcess.close();
                if (response.success) {
                    uwayNotice('通知',response.result);
                    table.draw(false);
                    $("#infoModal").modal("hide");
                }else{
                    uwayWarning('警告',response.message);
                    console.log(response.message);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log(textStatus);
                $.uwayProcess.close();
            }
        });
    }

    //文件上传
    function upload() {
        var t = setInterval(function() {
            //获取iframe标签里body元素里的文字。即服务器响应过来的"上传成功"或"上传失败"
            var word = $("iframe[id='uploadframe']").contents().find("body").text();
            var result;
            try {
                result = JSON.parse(word);
            } catch (e){
                console.log('等待上传请求返回信息......')
                return;
            }
            if (result.success == true) {
                var fName = result.result;
                $("#picUrl_info").val(fName)
                uwayNotice("通知", "上传成功！");
                clearInterval(t);
            } else {
                uwayNotice("通知", "上传失败！");
                clearInterval(t);
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
        $("#"+targetId).show();
        $("#"+targetId).attr("src",url);
        //每次更换时都将路径清空，在上传后填写
        $("#picUrl_info").val("");
        $("iframe[id='uploadframe']").contents().find("body").html("");
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
</html>