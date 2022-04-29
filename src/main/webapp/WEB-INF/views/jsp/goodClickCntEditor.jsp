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
    <link rel="stylesheet" type="text/css" media="screen" href="${ctxStatic}/js/plugin/zTree_v3-master/css/zTreeStyle/zTreeStyle.css${jsversion}">
    <link rel="stylesheet" type="text/css" media="screen" href="${ctxStatic}/css/bootstrap-datetimepicker.min.css${jsversion}" >
    <link rel="stylesheet" type="text/css"  href="${ctxStatic}/css/uway.common.css${jsversion}">
    <link rel="shortcut icon" href="${ctxStatic}/img/favicon/favicon.ico${jsversion}" type="image/x-icon">
    <link rel="icon" href="${ctxStatic}/img/favicon/favicon.ico${jsversion}" type="image/x-icon">
    <link rel="stylesheet" type="text/css" media="screen" href="${ctxStatic}/css/bootstrap-datetimepicker.min.css${jsversion}" >
    <style>
        .selectedDiv span{
            margin: 5px 10px;cursor:pointer;
        }
    </style>
</head>
<body class="desktop-detected pace-done" style="background:url(${ctxStatic}/img/sysBg.png)" >

<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/include/leftmenu.jsp"></jsp:include>
<div id="main" role="main">
    <div id="ribbon">
        <div class="col-lg-7 col-md-7 col-sm-7 col-xs-7">
            <ol class="breadcrumb">
                <li>系统管理</li><li>点赞数量变更</li>
            </ol>
        </div>
    </div>
    <div id="content">
        <div class="row" style="min-width:900px;">
            <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3" style="min-width:200px;">
                <ul id="roleTree" class="ztree uway-tree"></ul>
            </div>
            <div class="col-lg-3 col-md-3 col-sm-3 col-xs-3" style="min-width:200px;display: none;">
                <ul id="labelTree" class="ztree uway-tree"></ul>
            </div>
            <div id="userInfo" class="form-horizontal col-lg-6 col-md-6 col-sm-6 col-xs-6" style="margin-top:30px;">
                <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10">
                    <div class="form-group">
                        <label class="col-lg-4 col-md-4 col-sm-4 col-xs-4 control-label">变更范围：</label>
                        <div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
                            <input type="text" id="minNum" type="number" style="width:40%;height:32px;"/>
                            -
                            <input type="text" id="maxNum" type="number" style="width:40%;height:32px;"/>
                        </div>
                    </div>
                </div>
                <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10">
                    <div class="form-group">
                        <label class="col-lg-4 col-md-4 col-sm-4 col-xs-4 control-label">变更动态：</label>
                        <div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
                            <input class="form-control" type="text" id="teamVideoIds" onclick="openChooseModel()" />
                        </div>
                    </div>
                </div>

                <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10" style="margin-top:10px;text-align:center;">
                    <button id="saveBtn" style="width:60px;height:28px;margin-right:20px;" class="btn main-color-bluegreen" onclick="save()">提交</button>
                    <button id="backBtn" style="width:60px;height:28px" class="btn" onclick="goBack()">返回</button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="chooseModal" tabindex="-1" role="dialog" aria-labelledby="chooseModalTitle" aria-hidden="true">
        <div class="modal-dialog" style="min-width:1200px;">
            <div class="modal-content" id="choose-form">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="chooseModalTitle">动态选择</h4>
                </div>
                <div class="modal-body">
                    <div id="tableRow_1" class="row tableRowDiv" style="max-height:400px;overflow-y: auto;">
                        <div id="userContent">
                            <!-- 查询条件DIV -->
                            <div class="queryParamDiv">
                                <div style="display: inline-block;">
                                    <label for="type_q">内容分类</label>
                                    <select id="type_q" style="height: 30px;width: 100px;">

                                    </select>
                                </div>
                                <div style="display: inline-block;">
                                    <label for="status_q">状态</label>
                                    <select id="status_q" style="height: 30px;width: 100px;">

                                    </select>
                                </div>

                                <div style="display: inline-block;">
                                    <label for="checkStatus_q">审核状态</label>
                                    <select id="checkStatus_q" style="height: 30px;width: 100px;">

                                    </select>
                                </div>

                                <div style="display: inline-block;">
                                    <label for="videoDisc_q">动态内容</label>
                                    <input id="videoDisc_q" style="height: 30px;width: 100px;" />
                                </div>

                                <div style="display: inline-block;">
                                    <label for="isSelected_q">是否推荐</label>
                                    <select id="isSelected_q" style="height: 30px;width: 100px;">

                                    </select>
                                </div>

                                <div style="display: inline-block;">
                                    <label for="videoType_q">动态类型</label>
                                    <select id="videoType_q" style="height: 30px;width: 100px;">

                                    </select>
                                </div>

                                <div style="display: inline-block;">
                                    <btn  class="btn main-color-bluegreen" style="width: 80px" onclick="query()">查 询</btn>
                                </div>

                            </div>
                        </div>
                        <table id="teamVideoTable" class="listTable table table-striped table-bordered" style="max-height:400px;">

                        </table>

                    </div>
                    <div id="selectedTeamVideoDiv" class="row tableRowDiv selectedDiv" style="padding-top:20px;max-height:100px;overflow-y: auto;">

                    </div>
                </div>
                <div class="modal-footer">
                    <button id="resetBtn" class="btn main-color-bluegreen" onclick="getChooseInfo();">
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

<div class="page-footer">
    BingData
</div>
</body>

<script src="${ctxStatic}/js/libs/jquery-2.0.2.min.js${jsversion}"></script>
<script src="${ctxStatic}/js/app.config.js${jsversion}"></script>
<script src="${ctxStatic}/js/bootstrap/bootstrap.min.js${jsversion}"></script>
<script src="${ctxStatic}/js/bootstrap/bootstrap-datetimepicker.js${jsversion}"></script>
<script src="${ctxStatic}/js/bootstrap/bootstrap-datetimepicker.zh-CN.js${jsversion}"></script>
<script src="${ctxStatic}/js/plugin/jBox/jBox.min.js${jsversion}"></script>
<script src="${ctxStatic}/js/plugin/jquery-validate/jquery.validate.min.js${jsversion}"></script>
<script src="${ctxStatic}/js/plugin/zTree_v3-master/js/jquery.ztree.all.min.js${jsversion}"></script>
<script src="${ctxStatic}/js/app.min.js${jsversion}"></script>
<script src="${ctxStatic}/js/leftmenu.js${jsversion}"></script>
<script src="${ctxStatic}/js/plugin-datatables/jquery.dataTables.min.js${jsversion}"></script>
<script src="${ctxStatic}/js/plugin-datatables/dataTables.bootstrap.min.js${jsversion}"></script>
<script src="${ctxStatic}/js/header.js${jsversion}"></script>
<script src="${ctxStatic}/js/time/time.js${jsversion}"></script>
<script src="${ctxStatic}/js/uway.common.js${jsversion}"></script>

<script type="text/javascript">
    var statusObj = {"0":"失效","1":"有效"};
    var isSelectedObj = {"0":"否","1":"是"};
    var checkStatusObj = {"-1":"云审核不通过","0":"员工未审核","1":"审核通过","2":"审核不通过"};
    var videoTypeObj = {"1":"一般动态","2":"教学动态","3":"报名动态"};
    var typeObj = {"1":"图片动态","2":"视频动态","3":"文字动态"};
    var teamVideoTable;
    $(function() {
        initSelect();
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
        $("#isSelected_q").val("");
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


    //根据条件进行查询
    function query() {
        teamVideoTable.ajax.reload();
    }

    function openChooseModel(){
        if (teamVideoTable == null){
            initDataTable();
        } else{
            query();
        }
        $("#chooseModal").modal("show");
    }

    function initDataTable(){
        teamVideoTable = $('#teamVideoTable').DataTable({
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
                        var str = '<a onclick="selectedTeamVideo(\''+ e.id+'\')">选择</a>';
                        return str;
                    },
                    title:'操作',
                    bSortable:false
                },
                {data: "id",bSearchable:false,title:'动态编号',width:"50px",defaultContent:""},
                {data: "goodCnt",bSearchable:false,title:'点赞数',defaultContent:"",width:"50px"},
                {data: "videoDiscTemp",bSearchable:false,title:'内容',bSortable:false,render:function(data){
                        if(data == null || data == ''){
                            return '';
                        }else if(data.length > 25){
                            return '<span title="'+data+'">'+data.substring(0,25)+'......</span>';
                        }else {
                            return data;
                        }
                    },width:"110px"},
                {data: "videoType",bSearchable:false,title:'动态类型',width:"80px",render:function(data){
                        return getObjValByKey(videoTypeObj,data);
                    }},
                {data: "type",bSearchable:false,title:'内容分类',width:"80px",render:function(data){
                        return getObjValByKey(typeObj,data);
                    }},
                {data: "checkStatus",bSearchable:false,title:'审核状态',bSortable:false,render:function(data){
                        return getObjValByKey(checkStatusObj,data);
                    },width:"100px"},
                {data: "status",bSearchable:false,title:'状态',render:function(data){
                        return getObjValByKey(statusObj,data);
                    },width:"50px"},
                {data: "inserttime",bSearchable:false,title:'创建时间',width:"100px",defaultContent:""}
            ],
            "order": [[ 1, "desc" ]]
        });
    }

    //获取已选中对象的信息，并展示
    function getChooseInfo(){
        var names = "";
        var ids = "";
        if($("#selectedTeamVideoDiv span").length > 0){
            $("#selectedTeamVideoDiv span").each(function(i,obj){
                names += $(obj).text() + ",";
                ids += $(obj).attr("title") + ",";
            });
            names = names.substring(0,names.length - 1);
            ids = ids.substring(0,ids.length - 1);
        }
        $("#teamVideoIds").val(ids);
        $("#chooseModal").modal("hide");
    }

    function save(){
        uwayConfirm({
            content: '确定要提交修改吗？',
            confirm: function(){
                ajaxSave();
            },
            cancel: function(evt){

            }
        });
    }

    function selectedTeamVideo(id){
        var idstr = "teamVideo_" + id;
        if($("#" + idstr).length > 0){
            uwayWarning("提示","该动态已选中");
            return;
        }
        var str = '<span id="'+idstr+'" title="'+id+'" onclick="delSelected(\''+idstr+'\')">'+id+'</span>';
        $("#selectedTeamVideoDiv").append(str);
    }

    function delSelected(id){
        $("#" + id).remove();
    }

    function getSaveData(){
        var param = {};
        var minNum = $("#minNum").val();
        var maxNum = $("#maxNum").val();
        if (minNum == "" || maxNum == "" || Number(minNum) <= 0 || Number(minNum) > Number(maxNum)
            || minNum.indexOf(".") != -1 || maxNum.indexOf(".") != -1){
            uwayWarning("提示","请填写正确的点赞数");
            return null;
        }
        var ids = $("#teamVideoIds").val();
        if (ids == ""){
            uwayWarning("提示","请选择要修改的动态");
            return null;
        }
        param["minNum"] = minNum;
        param["maxNum"] = maxNum;
        param["ids"] = ids;
        return param;
    }

    function ajaxSave(){
        var paras = getSaveData();
        if (paras == null){
            return;
        }
        $.uwayProcess.show();
        $.ajax({
            url: "${contextPath}/video/updateTeamVideoGoodCnt",
            async: true, type: "Post", dataType: "json",
            data: paras,
            success: function (response) {
                $.uwayProcess.close();
                if(response.success){
                    uwayNotice('通知',"修改成功");
                    setTimeout(function(){
                        window.location.href=document.referrer;
                    },1000);
                }else{
                    uwayNotice('通知',response.message);
                }

            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log(textStatus);
            }
        });
    }

    function goBack(){
        window.location.href=document.referrer;
    }

</script>
</html>