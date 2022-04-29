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
                <li>赛事管理</li><li>订单管理</li>
            </ol>
        </div>
    </div>
    <div id="content">
        <div class="condition-wrapper">

            <div class="divform">
                <label class="lableClass" for="orderid"  >订单id</label>
                <input  id="orderid" type="text" class="lengthClass" placeholder="订单id"/>
            </div>
            <div class="divform">
                <label class="lableClass" for="orderNo"  >订单编号</label>
                <input  id="orderNo" type="text" class="lengthClass" placeholder="订单编号"/>
            </div>

            <div class="divform">
                <label class="lableClass" for="userId"  >用户id</label>
                <input  id="userId" type="text" class="lengthClass" placeholder="用户id"/>
            </div>

            <div class="divform">
                <label class="lableClass" for="matchId"  >赛事id</label>
                <input  id="matchId" type="text" class="lengthClass" placeholder="赛事id"/>
            </div>

            <div class="divform">
                <label class="lableClass" for="teamId"  >队伍id</label>
                <input  id="teamId" type="text" class="lengthClass" placeholder="队伍id"/>
            </div>

            <div class="divform">
                <label class="lableClass" for="orderStatus"  >订单状态</label>
                <select id="orderStatus" class="selectHight lengthClass">
                    <option selected="selected" value="">全部</option>
                    <option  value="0">待付款</option>
                    <option  value="1">已付款</option>
                    <option  value="2">无效订单</option>
                </select>
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
                <label class="lableClass">支付时间</label>
                <label class="data_time">
                    <input type="text" id="paystartDate" class="lengthClass" value="" style="height: 30px;" name="startDate">
                    <i class="icon-append fa fa-calendar" style="margin-left: -28px; height: 28px; width: 28px;"></i>
                </label> <b>~ </b>
                <label class="data_time">
                    <input type="text" id="payendDate" class="lengthClass" value="" style="height: 30px;" name="endDate">
                    <i class="icon-append fa fa-calendar" style="margin-left: -28px; height: 28px; width: 28px;"></i>
                </label>
            </div>
            <div class="divform">
                <button class="btn main-color-bluegreen" style="width: 80px;" onclick="query()">查 询</button>

                <button style="margin-left: 10px" class="btn main-color-bluegreen" onclick="exportOrder()">导出订单</button>

            </div>
        </div>
        <div id="tableRow" style="min-width:1000px;margin-top:10px;">
            <table id="orderTable" class="table table-striped table-bordered">

            </table>
        </div>
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
                            <label for="invoicePayable_info">发票抬头:</label>
                            <span id="invoicePayable_info" class="show-span"></span>
                        </div>
                        <div class="form-group">
                            <label for="taxpayerIdentity_info">纳税人识别号:</label>
                            <span id="taxpayerIdentity_info" class="show-span"></span>
                        </div>
                        <div class="form-group">
                            <label for="invoiceType_info">发票类型:</label>
                            <span id="invoiceType_info" class="show-span"></span>
                        </div>
                        <div class="form-group">
                            <label for="bake_info">备注说明:</label>
                            <span id="bake_info" class="show-span"></span>
                        </div>
                        <div class="form-group">
                            <label for="address_info">地址电话:</label>
                            <span id="address_info" class="show-span"></span>
                        </div>
                        <div class="form-group">
                            <label for="bank_info">开户行和账号:</label>
                            <span id="bank_info" class="show-span"></span>
                        </div>
                        <div class="form-group">
                            <label for="price_info">金额:</label>
                            <span id="price_info" class="show-span"></span>
                        </div>
                        <div class="form-group">
                            <label for="mail_info">电子邮箱:</label>
                            <span id="mail_info" class="show-span"></span>
                        </div>
                        <div class="form-group">
                            <label for="mailaddress_info">收件地址:</label>
                            <span id="mailaddress_info" class="show-span"></span>
                        </div>
                        <div class="form-group">
                            <label for="mailphone_info">联系电话:</label>
                            <span id="mailphone_info" class="show-span"></span>
                        </div>
                        <div class="form-group">
                            <label for="receiver_info">收件人:</label>
                            <span id="receiver_info" class="show-span"></span>
                        </div>
                        <div class="form-group">
                            <label for="status_info">开票状态:</label>
                            <span id="status_info" class="show-span"></span>
                        </div>
                        <div class="form-group">
                            <label for="inserttime_info">时间:</label>
                            <span id="inserttime_info" class="show-span"></span>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn" onclick="$('#infoModal').modal('hide')">取消</button>
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
        $('#paystartDate').attr("readonly", "readonly").datepicker({   //日历初始化
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
                $("#payendDate").datepicker('option', 'maxDate', selectedDate);
            }
        });
        $('#payendDate').attr("readonly", "readonly").datepicker({   //日历初始化
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
                $("#paystartDate").datepicker('option', 'maxDate', selectedDate);
            }
        });
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
    $(function() {
        var hasinitmonthdate = initmonthdate ? true : false;
        if (hasinitmonthdate) {
            initmonthdate();
        }
        table = initUserTable();
    });

    function getParamForQuery(){
        var condition = {};
        condition.id = $("#orderid").val().trim();
        condition.orderNo = $("#orderNo").val().trim();
        condition.userId = $("#userId").val();
        condition.startDate= $("#startDate").val();
        condition.endDate=$("#endDate").val().trim()==""?$("#endDate").val().trim():new Date($("#endDate").val().trim()).addDays(1).format("yyyy-MM-dd");
        condition.paystartDate= $("#paystartDate").val();
        condition.payendDate=$("#payendDate").val().trim()==""?$("#payendDate").val().trim():new Date($("#payendDate").val().trim()).addDays(1).format("yyyy-MM-dd");
        condition.matchId = $("#matchId").val();
        condition.teamId = $("#teamId").val();
        condition.orderStatus = $("#orderStatus").val();
        return condition;
    }

    //User表展示
    function initUserTable() {
        return orderTable = $('#orderTable').DataTable({
            "scrollX": true,
            "serverSide": true,
            "searching":false,
            "order": [[ 0, "desc" ]],
            "ajax": {
                "url": "${contextPath}/teamorder/getTeamTable",
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
                {data: "id",bSearchable:false,width:"50px",title:'订单id',defaultContent:""},
                {data: "orderNo",bSearchable:false,width:"50px",title:'订单编号',defaultContent:""},
                {data: function (e) {
                        if(!e.orderType){
                            return '';
                        }else if(e.orderType==1){
                            return '报名费';
                        }else if(e.orderType==2){
                            return '其他';
                        }
                    },bSearchable:false,title:'订单类型',bSortable:false,width:"80px"},
                {data: "userId",bSearchable:false,width:"50px",title:'userId',defaultContent:""},
                {data: "phone",bSearchable:false,width:"50px",title:'联系电话',defaultContent:""},
                {data: "matchId",bSearchable:false,width:"50px",title:'赛事id',defaultContent:""},
                {data: "matchTitle",bSearchable:false,width:"50px",title:'赛事名称',defaultContent:""},
                {data: function (e) {
                        if(!e.chargeType){
                            return '';
                        }else if(e.chargeType==1){
                            return '统一交费';
                        }else if(e.chargeType==2){
                            return '自己交费';
                        }
                    },bSearchable:false,title:'交费方式',bSortable:false,width:"80px"},
                {data: "numbers",bSearchable:false,width:"50px",title:'交费人数',defaultContent:""},
                {data: "unitPrice",bSearchable:false,width:"50px",title:'单价',defaultContent:""},
                {data: "totalPrice",bSearchable:false,width:"50px",title:'总价',defaultContent:""},
                {data: "payType",bSearchable:false,width:"50px",title:'支付方式',render:function(data){
                    if(data == 1){
                        return "微信";
                    }else if(data == 2){
                        return "支付宝";
                    }else if(data == 3){
                        return "微信小程序";
                    }else{
                        return "";
                    }
                }},
                {data: "payNo",bSearchable:false,width:"50px",title:'支付订单号',defaultContent:""},
                {data: "payTime",bSearchable:false,width:"50px",title:'支付时间',defaultContent:""},
                // {data: "payPrice",bSearchable:false,width:"50px",title:'支付金额',defaultContent:""},
                {data: function (e) {
                        if(!e.orderStatus){
                            return '';
                        }else if(e.orderStatus==1){
                            return '已付款';
                        }else if(e.orderStatus==0){
                            return '待付款';
                        }else if(e.orderStatus==2){
                            return '订单失效';
                        }
                    },bSearchable:false,title:'订单状态',bSortable:false,width:"50px"},
                {data: "inserttime",bSearchable:false,width:"50px",title:'创建时间',defaultContent:""},
                {data: function(e){
                        return '<a onclick="openInvoice('+e.id+')">查看发票</a>';
                    },bSearchable:false,width:"50px",title:'发票',defaultContent:""}
            ]
        });
    }
    //根据条件进行查询
    function query() {
        table.ajax.reload();
    }
    //清除modal框内容
    function clearModal(){
        $(".modal").find("input").val("");
    }

    function strNull(str){
        return str == null ? "":str;
    }

    function openInvoice(orderId){
        $.uwayProcess.show();
        $.ajax({
            url: "${contextPath}/teamorder/getInvoice",
            async: true, type: "Post", dataType: "json",
            data: {orderId : orderId},
            success: function (response) {
                $.uwayProcess.close();
                if(response.success){
                    var info = response.result;
                    $(".show-span").empty();
                    if(info == null){
                        $("#infoModal").modal("show");
                        return;
                    }
                    console.log(info);
                    $("#invoicePayable_info").text(strNull(info.invoicePayable));
                    $("#taxpayerIdentity_info").text(strNull(info.taxpayerIdentity));
                    var invoiceType = strNull(info.invoiceType);
                    if(invoiceType == '1'){
                        $("#invoiceType_info").text("公司");
                    }else if(invoiceType == '2'){
                        $("#invoiceType_info").text("个人");
                    }
                    $("#bake_info").text(strNull(info.bake));
                    $("#address_info").text(strNull(info.address));
                    $("#bank_info").text(strNull(info.bank));
                    $("#price_info").text(strNull(info.price));
                    $("#mail_info").text(strNull(info.mail));
                    $("#mailaddress_info").text(strNull(info.mailaddress));
                    $("#mailphone_info").text(strNull(info.mailphone));
                    $("#receiver_info").text(strNull(info.receiver));
                    $("#status_info").text(strNull(info.status_info));
                    var status = strNull(info.status);
                    if(status == '0'){
                        $("#status_info").text("未开票");
                    }else if(status == '1'){
                        $("#status_info").text("已开票");
                    }
                    $("#inserttime_info").text(strNull(info.inserttime));
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

    function exportOrder() {
        var condition = getParamForQuery();
        param = JSON.stringify(condition);
        window.location.href="${pageContext.request.contextPath}/teamorder/exportOrderList?condition="+encodeURI(encodeURI(param));
    }
</script>
</html>