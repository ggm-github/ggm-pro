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
        /*.condition-wrapper{*/
        /*display: flex;*/
        /*margin-top: 20px;*/
        /*}*/
        .selectHight{
            height: 32px;
        }
        .form-group{
            display: flex;
        }
        .form-group label {
            flex:0 0 90px;
            text-align: right;
            margin-right: 20px;
        }
        /*qiulinhe:2016年11月7日13:48:26*/
        /* dataTables列内容居中 */
        .table>tbody>tr>td{
            text-align: center;
        }
        .leftAlign{
            text-align: left !important;
            width: 200px;
        }
        .lableClass{
            width:60px;
        }
        .lengthClass{
            width:180px;
        }
        .lengthClass2{
            width:400px;
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
                <li>APP通用管理</li><li>用户管理</li>
            </ol>
        </div>
    </div>
    <div id="content">
        <div class="condition-wrapper">

            <div class="divform">
                <label class="lableClass" for="userNameSearch"  >用户名</label>
                <input  id="userNameSearch" type="text" class="lengthClass" placeholder="请输入用户名"/>
            </div>

            <input type="hidden" id="iconPath" name="iconPath"/>
            <div class="divform">
                <label class="lableClass" for="chooseStatus"  >用户状态</label>
                <select id="chooseStatus" class="selectHight lengthClass">
                    <option selected="selected" value="">全部</option>
                    <option  value="1">有效</option>
                    <option  value="0">无效</option>
                </select>
            </div>
            <div class="divform">
                <label class="lableClass" for="isRealSearch"  >实名状态</label>
                <select id="isRealSearch" class="selectHight lengthClass">
                    <option selected="selected" value="">全部</option>
                    <option  value="1">已实名</option>
                    <option  value="0">未实名</option>
                </select>
            </div>

            <div class="divform">
                <button class="btn main-color-bluegreen" onclick="query()">查 询</button>
                <fus:hasRight url="/app/exportUserList">
                    <button style="margin-left: 10px" class="btn main-color-bluegreen" onclick="exportUser()">导出用户</button>
                </fus:hasRight>
            </div>
        </div>
        <div class="condition-wrapper" style="margin-top:10px;">
            <div class="divform">
                <label class="lableClass" for="chNameSearch"  >用户昵称</label>
                <input  id="chNameSearch"  type="text" class="lengthClass" placeholder="请输入用户昵称"/>
            </div>
            <div class="divform">
                <label class="lableClass">创建时间</label>
                <label class="data_time">
                    <input type="text" id="startDate" class="lengthClass" value="" style="height: 30px;" name="startDate">
                    <i class="icon-append fa fa-calendar" style="margin-left: -28px; height: 28px; width: 28px;"></i>
                </label> <b>~ </b>
                <label class="data_time">
                    <input type="text" id="endDate" class="lengthClass" value="" style="height: 30px;" name="endDate">
                    <i class="icon-append fa fa-calendar" style="margin-left: -28px; height: 28px; width: 28px;"></i>
                </label>
            </div>
            <div class="divform">
                <label class="lableClass" for="teamId"  >队伍ID</label>
                <input  id="teamId"  type="text" class="lengthClass" placeholder="请输入队伍ID"/>
            </div>
        </div>
        <iframe id="uploadframe" name="uploadframe" style="display: none;"></iframe>
        <div id="tableRow" style="min-width:1000px;margin-top:10px;">
            <table id="saasTable" class="table table-striped table-bordered">

            </table>
        </div>
        <div class="modal fade" id="addUserModal" tabindex="-1" role="dialog" aria-labelledby="appAddLabel" >
            <div class="modal-dialog" style="width: 600px;">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="appAddLabel">新增用户</h4>
                    </div>
                    <div class="modal-body">
                        <div style="width: 300px;margin: 0 auto;">
                            <div class="form-group">
                                <label for="loginName">用户名:</label>
                                <input type="text" id="loginName" placeholder="请输入用户名">
                            </div>
                            <div class="form-group">
                                <label for="chName">昵称:</label>
                                <input type="text" id="chName" placeholder="请输入昵称">
                            </div>
                            <div class="form-group">
                                <label for="password">密码:</label>
                                <input type="text" id="password" placeholder="请输入密码">
                            </div>
                            <div class="form-group">
                                <label for="chooseSex">性别:</label>
                                <select id="chooseSex" >
                                    <option selected="selected" value="" style="display: none;">请选择</option>
                                    <option value="0">女</option>
                                    <option value="1">男</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="mail">mail:</label>
                                <input type="text" id="mail" >
                            </div>
                            <div class="form-group">
                                <label for="phone">手机:</label>
                                <input type="text" id="phone" >
                            </div>
                            <div class="form-group">
                                <label class="data_time" for="birthday">生日:</label>
                                <input type="text" id="birthday" value="" style="height: 28px;">
                                <i class="icon-append fa fa-calendar" style="margin-left: -19px; margin-top: 8px;height: 18px; width: 20px;"></i>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn main-color-bluegreen" onclick="addUser()">提交</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" id="editUserModal" tabindex="-1" role="dialog" aria-labelledby="appEditLabel" >
            <div class="modal-dialog" style="width: 600px;">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title" id="ppAddLabel">编辑用户</h4>
                    </div>
                    <div class="modal-body">
                        <div style="width: 300px;margin: 0 auto;">
                            <input type="hidden" id="userId" name="userId" />
                            <%--<div class="form-group">--%>
                            <%--<label for="eloginName">用户名:</label>--%>
                            <input type="hidden" id="eloginName" placeholder="请输入用户名">
                            <%--</div>--%>
                            <div class="form-group">
                                <label for="echName">昵称:</label>
                                <input type="text" id="echName" placeholder="请输入昵称">
                            </div>
                            <div class="form-group">
                                <label for="echooseSex">性别:</label>
                                <select id="echooseSex" >
                                    <option selected="selected" value="" style="display: none;">请选择</option>
                                    <option value="0">女</option>
                                    <option value="1">男</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="email">mail:</label>
                                <input type="text" id="email" >
                            </div>
                            <div class="form-group">
                                <label for="ephone">手机:</label>
                                <input type="text" id="ephone" >
                            </div>
                            <div class="form-group">
                                <label class="data_time" for="ebirthday">生日:</label>
                                <input type="text" id="ebirthday" value="" style="height: 28px;">
                                <i class="icon-append fa fa-calendar" style="margin-left: -19px; margin-top: 8px;height: 18px; width: 20px;"></i>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn  main-color-bluegreen" onclick="updateUser()">确认</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="infoModal" tabindex="-1" role="dialog" aria-labelledby="infoModalTitle" aria-hidden="false" style="z-index:1000;">
            <div class="modal-dialog" style="width:600px;">
                <div class="modal-content" >
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="false">
                            &times;
                        </button>
                        <h4 class="modal-title">消息</h4>
                    </div>
                    <div class="modal-body" id="infoModalBody">
                        <div style="width: 600px;margin: 0 auto;">
                            <div class="form-group">
                                <label>消息类型：</label>
                                <select id="messageType_info" name="messageType" class="selectHight lengthClass">

                                </select>
                            </div>

                            <div class="form-group">
                                <label>标题：</label>
                                <input id="referenceInfo_info" name="referenceInfo" class="lengthClass2"/>
                            </div>

                            <div class="form-group msgInfoUM" style="display:none;">

                                <label>消息类型：</label>
                                <select id="UMType" name="UMType"  onchange="chooseType()" class="selectHight lengthClass">
                                    <option value="0" selected>Url页面</option>
                                    <option value="1">App页面</option>
                                </select>
                            </div>
                            <div class="form-group" id="openWayDiv">
                                <label>跳转方式：</label>
                                <select id="openWay_info" onchange="openWayChg();" class="selectHight lengthClass">

                                </select>
                            </div>
                            <div id="referentUrldiv" class="form-group msgInfo" style="display:none;">
                                <label>访问地址：</label>
                                <input id="referentUrl_info" name="referentUrl" class="lengthClass2">
                            </div>
                            <div id="openDescDiv" class="form-group" style="display:none;">
                                <label></label>
                                <span id="openDesc_0" style="display:none;color: red;width: 60%;text-align: left;font-size: 12px;">链接跳转，请填写要跳转的链接地址</span>
                                <span id="openDesc_1" style="display:none;color: red;width: 60%;text-align: left;font-size: 12px;">赛事原生，请填写要打开的赛事ID，请在【赛事管理】中，粘取赛事ID填入</span>
                            </div>
                            <div id="referentNamediv" class="form-group msgInfoUM" style="display:none;">
                                <label>App页面：</label>
                                <input id="referentName_info" name="referentName" class="lengthClass">
                            </div>
                            <div class="form-group">
                                <label>描述信息：</label>
                                <input id="referentDsc_info" name="referentDsc" class="lengthClass2">
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

                            <div class="form-group" style="display:none;">
                                <label>区域：</label>
                                <input type="text" id="areaName_info" onfocus="showTree('1','areaId_info','areaName_info');" />
                                <input type="hidden" id="areaId_info" />
                            </div>
                            <div class="form-group" style="display:none;">
                                <label>行业：</label>
                                <input type="text" id="industryName_info" onfocus="showTree('2','industryId_info','industryName_info');" />
                                <input type="hidden" id="industryId_info" />
                            </div>

                            <div class="form-group" id="imgUploadDiv">
                                <label>图片选择：</label>
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
                                <img id="imgShow" src="" style="display:none;max-height:200px;margin:0 auto;"/>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <input id="addBtn" style="width:80px;" class="btn main-color-bluegreen" type="button" value="新增" onclick="addMessageConfirm();"/>
                        <input id="backBtn" style="width:80px;" class="btn main-color-bluegreen" type="button" value="取消" onclick="$('#infoModal').modal('hide');"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="page-footer"></div>
</body>

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
<script src="${ctxStatic}/js/time/time.js${jsversion}"></script>
<script src="${ctxStatic}/js/header.js${jsversion}"></script>
<script src="${ctxStatic}/js/uway.common.js${jsversion}"></script>
<script>
    var typeObj = {"0":"闲散消息","1":"操场官方消息", "2":"队伍消息",/*"3":"友盟推送",*/"5":"@我"};
    var openWayObj = {"-1":"无跳转","0":"链接地址","1":"原生赛事"};
    var table;
    $(document).ready(function() {
        // $('#startDate').val(getNowFormatDate());   //设置日历默认为当天
        $('#endDate').val(getNowFormatDate());     //设置日历默认为当天
        $('#startDate').attr("readonly", "readonly").datepicker({   //日历初始化
            dateFormat: 'yy-mm-dd',
            maxDate: 'd',
            showTime: true,
            monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
            dayNames: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
            dayNamesShort: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
            dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'],
            prevText: '<i class="fa fa-chevron-left"></i>',
            nextText: '<i class="fa fa-chevron-right"></i>',
            onSelect: function (selectedDate) {
                $("#endDate").datepicker('option', 'minDate', selectedDate);
            }
        });
        $('#endDate').attr("readonly", "readonly").datepicker({   //日历初始化
            dateFormat: 'yy-mm-dd',
            maxDate: 'd',
            showTime: true,
            monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
            dayNames: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
            dayNamesShort: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
            dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'],
            prevText: '<i class="fa fa-chevron-left"></i>',
            nextText: '<i class="fa fa-chevron-right"></i>',
            onSelect: function (selectedDate) {
                $("#startDate").datepicker('option', 'maxDate', selectedDate);
            }
        });
        $('#birthday').attr("readonly", "readonly").datepicker({   //日历初始化
            dateFormat: 'yy-mm-dd',
            maxDate: 'd',
            showTime: true,
            monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
            dayNames: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
            dayNamesShort: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
            dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'],
            prevText: '<i class="fa fa-chevron-left"></i>',
            nextText: '<i class="fa fa-chevron-right"></i>'
        });
        $('#ebirthday').attr("readonly", "readonly").datepicker({   //日历初始化
            dateFormat: 'yy-mm-dd',
            maxDate: 'd',
            showTime: true,
            monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
            dayNames: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
            dayNamesShort: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
            dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'],
            prevText: '<i class="fa fa-chevron-left"></i>',
            nextText: '<i class="fa fa-chevron-right"></i>'
        });
        //全选框事件绑定
        $("#chb_all").click(function(){
            if($("#chb_all").is(":checked")){
                $(".chb_check").prop('checked',"checked");
            }else{
                $(".chb_check").removeAttr("checked");
            }
        });

        var str = '';
        $.each(typeObj,function(key,val){
            str += '<option value="'+key+'">'+val+'</option>';
        });
        $("#messageType_info").empty();
        $("#messageType_info").append(str);
        $("#messageType_info").val(1);

        str = '';
        $.each(openWayObj,function(key,val){
            str += '<option value="'+key+'">'+val+'</option>';
        });
        $("#openWay_info").empty();
        $("#openWay_info").append(str);
        $("#openWay_info").append(-1);
    });
    //获取当前日期函数
    function getNowFormatDate() {
        var date = new Date();
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
    function updateUser(){
        if(updateInputCheck()){
            var condition = {};
            condition.userId=$("#userId").val();
            condition.loginName = $("#eloginName").val().trim();
            condition.chName=$("#echName").val().trim();
            condition.sex=$("#echooseSex").val();
            condition.mail= $("#email").val().trim();
            condition.phone= $("#ephone").val().trim();
            condition.birthday= $("#ebirthday").val();
            $.ajax({
                url: "${contextPath }/app/updateUserById",
                type:"post",
                async: true,
                dataType:"json",
                data: {
                    condition: JSON.stringify(condition)
                },
                success: function (data) {
                    if(data.success==true){
                        uwayNotice("通知", "修改成功");
                        query();
                        $("#editUserModal").modal("hide");
                        clearModal();
                    }else{
                        uwayWarning("提示",data.message);
                    }
                },
                error: function (e1,e2,e3) {
                    if(e1.responseText.indexOf("<!DOCTYPE html>") != -1){
                        window.location = window.localStorage.getItem("CONTEXT_PATH") + '/authority/login';
                    }
                }
            });
        }
    }
    function showModal() {
        /*        if(document.getElementById("newUserPhoto").files.item(0)==null){
                    $("#imgPre").hide();
                }*/
        $("#uploadInput").val("上传");
        $("#addUserModal").modal("show");
    }
    $(function() {
        var hasinitmonthdate = initmonthdate ? true : false;
        if (hasinitmonthdate) {
            initmonthdate();
        }
        table = initUserTable();
    });

    function getParamForQuery(){
        var condition = {};
        condition.loginName = $("#userNameSearch").val().trim();
        condition.chName = $("#chNameSearch").val().trim();
        condition.status = $("#chooseStatus").val();
        condition.startDate= $("#startDate").val();
        condition.endDate=$("#endDate").val().trim()==""?$("#endDate").val().trim():new Date($("#endDate").val().trim()).addDays(1).format("yyyy-MM-dd");
        condition.isReal = $("#isRealSearch").val();
        condition.teamId = $("#teamId").val();
        return condition;
    }

    //User表展示
    function initUserTable() {
        return saasTable = $('#saasTable').DataTable({
            "scrollX": true,
            /* "processing": true, */
            "serverSide": true,
            "searching":false,
            "order": [[ 2, "asc" ]],
            "ajax": {
                "url": "${contextPath}/app/getListByUser",
                "type": "POST",
                "data": function (d) {
                    console.log(d);
                    var condition = getParamForQuery();
                    d.condition = JSON.stringify(condition);
                    //$.uwayProcess.close();
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
                        var str = "";
                        <fus:hasRight url="/app/updateUserById">
                        str += '<a onclick="editUser(\''+ e.userId+'\')">编辑</a>'
                            + '<a style="margin-left:10px;" onclick="deleteUser(\''+ e.userId+'\')">停用</a>'
                            + '<a style="margin-left:10px;" onclick="startUser(\''+ e.userId+'\')">启用</a>';
                        </fus:hasRight>
                        return str;
                    },
                    width:"100px",
                    title:'操作',
                    bSortable:false
                },
                {data: "userId",bSearchable:false,width:"50px",title:'userId'},
                // {data: "loginName",bSearchable:false,title:'用户名'},
                {data: function (e) {
                        if(!e.loginName){
                            return '';
                        }else{
                            return e.loginName;
                        }
                    },bSearchable:false,title:'用户名',bSortable:false,width:"100px"},
                {data: function (e) {
                        if(!e.chName){
                            return '';
                        }else{
                            return e.chName;
                        }
                    },bSearchable:false,title:'昵称',bSortable:false,width:"100px"},
                {data: function (e) {
                        if(!e.sex){
                            return '';
                        }else if(e.sex==1){
                            return '男';
                        }else if(e.sex==0){
                            return '女';
                        }
                    },bSearchable:false,title:'性别',bSortable:false,width:"50px"},
                {data: function (e) {
                        // alert(JSON.stringify(e));
                        if(!e.mail){
                            return '';
                        }else{
                            return e.mail;
                        }
                    },bSearchable:false,title:'邮箱',bSortable:false,width:"100px"},
                {data: function (e) {
                        if(!e.phone){
                            return '';
                        }else{
                            return e.phone;
                        }
                    },bSearchable:false,title:'手机',bSortable:false,width:"100px"},
                {data: function (e) {
                        if(!e.birthdayStr){
                            return '';
                        }else{
                            return e.birthdayStr;
                        }
                    },bSearchable:false,title:'生日',bSortable:false,width:"80px"},
                {data: function (e) {
                        if (e.isReal==1){
                            return '已实名';
                        }else{
                            return '未实名';
                        }
                    },bSearchable:false,title:'实名状态',bSortable:false,width:"80px"},
                {data: function (e) {
                        if (!e.loginTimeStr){
                            return '';
                        }else{
                            return e.loginTimeStr;
                        }
                    },bSearchable:false,title:'登陆时间',bSortable:false,width:"80px"},
                {data: function (e) {
                        if (!e.quitTimeStr){
                            return '';
                        }else{
                            return e.quitTimeStr;
                        }
                    },bSearchable:false,title:'退出时间',bSortable:false,width:"80px"},
                {data: function (e) {
                        if (!e.createTimeStr){
                            return '';
                        }else{
                            return e.createTimeStr;
                        }
                    },bSearchable:false,title:'创建时间',bSortable:false,width:"80px"},
                {data: function (e) {
                        if (e.status==1){
                            return '有效';
                        }else{
                            return '无效';
                        }
                    },bSearchable:false,title:'用户状态',bSortable:false,width:"80px"}
            ]
        });
    }
    //根据条件进行查询
    function query() {
        table.ajax.reload();
    }
    function editUser(userId) {
        $.ajax({
            url: "${contextPath }/app/getUserById",
            type:"post",
            async: true,
            dataType:"json",
            data: {
                userId: userId
            },
            success: function (data) {
                if(data.success==true){
                    resultData = data.result;
                    $("#userId").val(resultData.userId);
                    $("#eloginName").val(resultData.loginName);
                    $("#echName").val(resultData.chName);
                    if(resultData.sex==1){
                        $("#echooseSex").val(1)
                    }else if(resultData.sex==0){
                        $("#echooseSex").val(0)
                    }
                    $("#email").val(resultData.mail);
                    $("#ephone").val(resultData.phone);
                    $("#ebirthday").val(resultData.birthdayStr);
                    $("#editUserModal").modal("show");
                }
            },
            error: function (e1,e2,e3) {
                if(e1.responseText.indexOf("<!DOCTYPE html>") != -1){
                    window.location = window.localStorage.getItem("CONTEXT_PATH") + '/authority/login';
                }
            }
        });
        return ;
    }
    //新增输入检查
    function addInputCheck() {
        var exi = $("#loginName").val().trim();
        if (!exi){
            uwayNotice("通知","用户名不能为空");
            return false;
        }
        return true;
    }
    function updateInputCheck() {
        return true;
    }
    function deleteUser(id) {
        uwayConfirm({
            content:"确定要使用户停用吗？",
            confirm:function () {
                ajaxdeleteUser(id);
            },
            cancel: function () {

            }
        });
    }

    function startUser(id) {
        uwayConfirm({
            content:"确定要启用用户吗？",
            confirm:function () {
                ajaxStartUser(id);
            },
            cancel: function () {

            }
        });
    }
    function ajaxdeleteUser(id) {
        var conditon ={};
        conditon.userId=id;
        conditon.status=0;
        $.ajax({
            url: "${contextPath}/app/updateUserById",
            type:"post",
            async: true,
            dataType:"json",
            data: {
                condition: JSON.stringify(conditon)
            },
            success: function (data) {
                if(data.success==true){
                    uwayNotice("通知", "删除成功");
                    query();
                    clearModal();
                }
            },
            error: function (e1,e2,e3) {
                if(e1.responseText.indexOf("<!DOCTYPE html>") != -1){
                    window.location = window.localStorage.getItem("CONTEXT_PATH") + '/authority/login';
                }
            }
        });
    }
    function ajaxStartUser(id) {
        var conditon ={};
        conditon.userId=id;
        conditon.status=1;
        $.ajax({
            url: "${contextPath}/app/updateUserById",
            type:"post",
            async: true,
            dataType:"json",
            data: {
                condition: JSON.stringify(conditon)
            },
            success: function (data) {
                console.log((data.success==true) + "" + data.message);
                if(data.success==true){
                    uwayNotice("通知", "启用成功");
                    query();
                    clearModal();
                }
            },
            error: function (e1,e2,e3) {
                if(e1.responseText.indexOf("<!DOCTYPE html>") != -1){
                    window.location = window.localStorage.getItem("CONTEXT_PATH") + '/authority/login';
                }
            }
        });
    }
    // 站内消息新增
    function openAddMessageConfirm(){
        var status = $("#chooseStatus").val();
        if (status != 1){
            uwayWarning("提示","用户状态，请选择有效用户");
            return;
        }
        var ids = [];
        $(".chb_check:checked").each(function(i,obj){
            ids.push($(obj).val());
        });
        var title;
        if(ids.length > 0){
            title = "是否为当前勾选的【" + ids.length + "】位用户新增消息？";
        }else{
            title = "是否按照当前查询条件,为所有用户新增消息？";
        }
        uwayConfirm({
            content : title,
            confirm: function(){
                openAddMessageModal();
            },
            cancel: function(evt){
            }
        });
    }

    function addMessageConfirm(){
        uwayConfirm({
            content : "是否确认新增消息？",
            confirm: function(){
                addMessageByUser();
            },
            cancel: function(evt){
            }
        })
    }

    function addMessageByUser(){
        var condition = getParamForQuery();
        var ids = [];
        $(".chb_check:checked").each(function(i,obj){
            ids.push($(obj).val());
        });
        condition['ids'] = ids;
        var param = {"addParamJson":condition};
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
        param["messageType"] = messageType;
        param["picUrl"] = picUrl;
        param["referentName"] = referenceInfo;
        param["referentUrl"] = referenceUrl;
        param["referentDsc"] = referenceDsc;
        param["areaId"] = areaId;
        param["industryId"] = industryId;
        param["openWay"] = openWay;

        $.ajax({
            url: "${contextPath}/message/addMessage",
            type:"post",
            async: true,
            dataType:"json",
            data: {
                "param" : JSON.stringify(param)
            },
            success: function (data) {
                console.log((data.success==true) + "" + data.message);
                if(data.success==true){
                    uwayNotice("通知", "新增成功");
                    $("#infoModal").modal("hide");
                }else{
                    uwayNotice("通知", data.message);
                }
            },
            error: function (e1,e2,e3) {
                if(e1.responseText.indexOf("<!DOCTYPE html>") != -1){
                    window.location = window.localStorage.getItem("CONTEXT_PATH") + '/authority/login';
                }
            }
        });
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

    function openAddMessageModal(){
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
        $("#openWay_info").val(-1);
        openWayChg();
        $("#infoModal").modal("show");
    }




    //清除modal框内容
    function clearModal(){
        $(".modal").find("input").val("");
    }

    //新增APP
    function addUser() {
        if(addInputCheck()){
            var condition = {};
            condition.loginName = $("#loginName").val().trim();
            condition.chName=$("#chName").val().trim();
            condition.password=$("#password").val();
            condition.sex=$("#chooseSex").val();
            condition.mail= $("#mail").val().trim();
            condition.phone= $("#phone").val().trim();
            condition.birthday= $("#birthday").val();
            console.log(condition);
            $.ajax({
                url: "${contextPath }/app/addUser",
                type:"post",
                async: true,
                dataType:"json",
                data: {
                    condition: JSON.stringify(condition)
                },
                success: function (data) {
                    if(data.success==true){
                        uwayNotice("通知", "添加成功");
                        query();
                        $("#addUserModal").modal("hide");
                        clearModal();
                    }else if (data.success==false){
                        uwayNotice("通知",data.message);
                        $("#addUserModal").modal("hide");
                        clearModal();
                    }
                },
                error: function (e1,e2,e3) {
                    if(e1.responseText.indexOf("<!DOCTYPE html>") != -1){
                        window.location = window.localStorage.getItem("CONTEXT_PATH") + '/authority/login';
                    }
                }
            });
        }
    }

    <fus:hasRight url="/app/exportUserList">
    function exportUser() {
        var condition = getParamForQuery();
        param = JSON.stringify(condition);
        window.location.href="${pageContext.request.contextPath}/app/exportUserList?condition="+encodeURI(encodeURI(param));
    }
    </fus:hasRight>

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

</script>
</html>