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
				font-weight:600;
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
						<li>赛事管理</li>
						<li>查看赛事活动</li>
					</ol>
				</div>
			</div>
			<div id="content">
				<div style="max-width: 1000px;margin: 0 auto;">
					<div class="modal-body">
						<div style="width: 100%;margin: 0 auto;">
							<div style="display:none;">
								<input type="text" id="matchId" value="${id}" />
							</div>
							<div class="form-group">
								<label>赛事标题:</label>
								<span type="text" id="matchTitle_info" class="info-big"></span>
							</div>
							<div class="form-group">
								<label>报名时间:</label>
								<span id="matchTime_info" class="info-small"></span><span>&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;</span><span id="matchEndtime_info" class="info-small"></span>
							</div>
							<div class="form-group">
								<label>比赛时间:</label>
								<span id="dateBegin_info" class="info-small"></span><span>&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;</span><span id="dateEnd_info" class="info-small"></span>
							</div>
							<div class="form-group">
								<label>赛事地点:</label>
								<span type="text" id="matchPlace_info" class="info-big"></span>
							</div>
							<div class="form-group">
								<label>导语:</label>
								<span id="matchRemark_info" class="info-big"></span>
							</div>

							<div class="form-group">
								<label>宣传封面:</label>
								<span id="matchImgurl_info" class="info-big"></span>
							</div>

							<div class="form-group">
								<label>宣传视频:</label>
								<span id="matchVideo_info" class="info-big"></span>
							</div>

							<div class="form-group" id="itemDiv">
								<p></p>
							</div>

							<div class="form-group">
								<label>赛事介绍:</label>
								<p id="matchContent_info" class="info-big" style="margin-left:130px;"></p>
							</div>
						</div>
                        <div class="modal-footer">
                            <button class="btn" onclick="goback();">取消</button>
                        </div>
					</div>
				</div>
			</div>

		</div>

		<div class="page-footer">

		</div>

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
	var statusObj = {"0":"未发布","1":"已发布"};
    var sortTypeObj = {"1":"永不过期","2":"过期设置"};
    var isChargeObj = {"0":"免费","1":"收费"};
	var isVoteObj = {"0":"否","1":"是"};
	var chargeTypeObj = {"0":"自选","1":"统一交费","2":"自己交费"};
	var isCheckObj = {"0":"不需要","1":"需要"};
	var teamOrPersonObj = {"1":"团体","2":"个人"};
	var operType = '${operTyspe}';
	$(function() {
		var id = $("#matchId").val();
		getMatchNewsInfo(id);
	});

	function goback(){
		var url = "${contextPath}/news/toMatchNews?queryType=historyParam_match";
		window.location.href = url;
	}

    function getObjValByKey(obj,key){
        var val = obj[key];
        return val == null || val == undefined ? "" : val;
    }

    function getMatchNewsInfo(id){
        $.ajax({
            url: "${contextPath}/news/getMatchNewsById",
            async: true, type: "Post", dataType: "json",
            data: {"id":id},
            success: function (response) {
                $.uwayProcess.close();
                if(response.success){
                    var result = response.result;
                    console.log(result);
                    $("#matchTitle_info").text(result.matchTitle);
                    var matchProvinceName = result.matchProvinceName;
                    var matchCityName = result.matchCityName;
                    $("#matchPlace_info").text((matchProvinceName == null ? "" : matchProvinceName) + " " + (matchCityName == null ? "" : matchCityName) + "   " + result.matchPlace);
                    $("#matchTime_info").text(result.matchTime.replace(/[-]{1}/,'/'));
                    $("#matchEndtime_info").text(result.matchEndtime.replace('-','/'));
					$("#dateBegin_info").text(result.dateBegin.replace('-','/'));
					$("#dateEnd_info").text(result.dateEnd.replace('-','/'));
					$("#matchTag_info").text(result.matchTag);
                    $("#matchRemark_info").text(result.matchRemark == null ? "" : result.matchRemark);
                    $("#matchContent_info").append(result.matchContent);
                    var imgHead = '<%=Global.getConfig("prefix.visit.path") %>';
                    $("#matchImgurl_info").append('<img src="'+imgHead + result.matchImgurl+'" style="width: 300px;" />');
					var isNeedVideo = result.isNeedVideo;
					var videoList = result.videoList;
					if (isNeedVideo == 1 && videoList != null && videoList.length > 0){
						$("#matchVideo_info").parent().show();
						var videoStr = "";
						for(var i=0;i<videoList.length;i++){
							videoStr += '<video style="max-width:300px;max-height:150px;" controls="" autoplay="" name="media">' +
									'<source id="videoShow" src="'+videoList[i]["videoUrl"]+'" type="video/mp4">' +
									'</video>';
						}
						$("#matchVideo_info").append(videoStr);
					}else{
						$("#matchVideo_info").parent().hide();
					}
					var itemList = result.itemList;
					$("#itemDiv").hide();
					if (itemList != null && itemList.length > 0) {
						console.log("------------------------");
						$("#itemDiv").show();
						var itemStr = "";
						for(var i=0;i<itemList.length;i++){
							itemStr += '<table style="margin-top:15px;">' +
									'<tr style="margin-top:15px;">' +
									'<th><label>比赛项目:</label></th><td><p>'+itemList[i]['itemName']+'</p></td>' +
									'<th><label>是否收费:</label></th><td><p>'+getObjValByKey(isChargeObj,itemList[i]['isCharge'])+'</p></td>' +
									'<th><label>费用:</label></th><td><p>'+itemList[i]['unitPrice']+'</p></td>' +
									'</tr>' +
									'<tr style="margin-top:15px;">' +
									'<th><label>报名审核:</label></th><td><p>'+getObjValByKey(isCheckObj,itemList[i]['isNeedAudit'])+'</p></td>' +
									'<th><label>单人/团体:</label></th><td><p>'+getObjValByKey(teamOrPersonObj,itemList[i]['teamOrPerson'])+'</p></td>' +
									'<th><label>每队人数:</label></th><td><p>'+(itemList[i]['minUser'] == itemList[i]['maxUser'] ? itemList[i]['minUser'] : itemList[i]['minUser'] + "~" + itemList[i]['maxUser'])+'</p></td>' +
									'</tr>' +
									'</table>';
						}
						$("#itemDiv p").append(itemStr);
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

</script>
</body>
</html>