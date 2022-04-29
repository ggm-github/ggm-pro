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
				margin-left:5px;
			}
			.form-group select{
				height:30px;
				width:150px;
			}
			.input-length{
				height:30px;
				width:150px;
			}
			.input-length-small{
				height:30px;
				width:50px;
			}
			.input-length-big{
				height:30px;
				width:60%;
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
						<c:if test="${'add' == operType}">
							<li>新增赛事</li>
						</c:if>
						<c:if test="${'editor' == operType}">
							<li>编辑赛事</li>
						</c:if>
					</ol>
				</div>
			</div>
			<div id="content">
				<div style="max-width: 1000px;margin: 0 auto;">
					<div class="modal-body">
						<div style="width: 100%;margin: 0 auto;">
							<div style="display:none;">
								<input id="id_info" type="text" value="${id}"/>
								<input type="text" id="matchImgurl_info" class="emptyInfo" />
							</div>
							<div class="form-group">
								<label for="matchTitle_info">赛事标题:</label>
								<input type="text" id="matchTitle_info" class="emptyInfo input-length-big" placeholder="请输入赛事标题" maxlength="200" />
							</div>
							<div class="form-group">
								<label for="matchCity_info">举办城市:</label>
								<input type="text" id="matchCity_info" class="emptyInfo input-length" placeholder="请输入举办城市" maxlength="20"/>
							</div>
							<div style="display:none">
								<label for="matchUnit_info">主办单位:</label>
								<input type="text" id="matchUnit_info" class="emptyInfo input-length" placeholder="请输入主办单位" maxlength="100">

								<label for="matchPlace_info">赛事地点:</label>
								<input type="text" id="matchPlace_info" class="emptyInfo input-length" placeholder="请输入赛事地点" maxlength="100"/>
							</div>


							<div class="form-group">
								<label for="matchTime_info">开放报名时间:</label>
								<input type="text" id="matchTime_info" value="" style="height: 30px;" name="matchTime_info" class="emptyInfo input-length">
								<i class="icon-append fa fa-calendar" style="margin-left: -28px; height: 28px; width: 28px;"></i>

								<label for="matchEndtime_info">结束报名时间:</label>
								<input type="text" id="matchEndtime_info" value="" style="height: 30px;" name="matchEndtime_info" class="emptyInfo input-length">
								<i class="icon-append fa fa-calendar" style="margin-left: -28px; height: 28px; width: 28px;"></i>
							</div>

							<div class="form-group">
								<label for="dateBegin_info">比赛开始时间:</label>
								<input type="text" id="dateBegin_info" value="" style="height: 30px;" name="dateBegin_info" class="emptyInfo input-length">
								<i class="icon-append fa fa-calendar" style="margin-left: -28px; height: 28px; width: 28px;"></i>
							</div>

							<div class="form-group" style="display:none;">
								<label for="dateEnd_info">比赛结束时间:</label>
								<input type="text" id="dateEnd_info" value="" style="height: 30px;" name="dateEnd_info" class="emptyInfo input-length">
								<i class="icon-append fa fa-calendar" style="margin-left: -28px; height: 28px; width: 28px;"></i>
							</div>

							<div class="form-group" style="display:none;">
								<label for="matchBake_info">赛事组别及人数:</label>
								<input type="text" id="matchBake_info" class="emptyInfo input-length" placeholder="请输入赛事备注" maxlength="100"/>
							</div>

							<div class="form-group">
								<label for="matchTag_info">标签:</label>
								<input type="text" id="matchTag_info" class="emptyInfo input-length" maxlength="30"/>
								<label class="rule-tips">最多填写30个字符，多个标签用“#”分隔，如：比赛#体操</label>
							</div>

							<div class="form-group" style="display:none;">
								<label for="matchCompany_info">公司:</label>
								<input type="text" id="matchCompany_info" class="emptyInfo input-length" placeholder="请输入公司名称" maxlength="50"/>

								<label for="matchArea_info">区域:</label>
								<input type="text" id="matchAreaName_info" class="emptyInfo input-length" onfocus="showTree('1','matchArea_info','matchAreaName_info');" />
								<input type="hidden" id="matchArea_info" class="emptyInfo" />
							</div>

							<div class="form-group" style="display:none;">
								<label for="matchIndustry_info">行业:</label>
								<input type="text" id="matchIndustryName_info" class="emptyInfo input-length" onfocus="showTree('2','matchIndustry_info','matchIndustryName_info');" />
								<input type="hidden" id="matchIndustry_info" class="emptyInfo" />

								<label for="matchType_info">打开方式:</label>
								<select id="matchType_info">

								</select>
							</div>

							<div class="form-group" style="display:none;">
								<label for="matchUrl_info">比赛url:</label>
								<input type="text" id="matchUrl_info" class="emptyInfo input-length-big" placeholder="请输入比赛url" maxlength="500" />
							</div>
							<div class="form-group">
								<label for="sort_info">权重:</label>
								<input type="number" id="sort_info" class="numberInfo input-length" placeholder="请输入权重" />

							</div>
							<div class="form-group" >
								<label for="sortType_info">权重设置:</label>
								<select id="sortType_info" onchange="sortTypeChg();">

								</select>
								<label class="sortDateLab" style="display:none;">过期时间:</label>
								<input type="text" id="sortDate_info" value="" style="height: 30px;display:none;" class="input-length sortDateLab">
								<i class="icon-append fa fa-calendar sortDateLab" style="margin-left: -25px; height: 28px; width: 28px;"></i>
							</div>

							<div class="form-group" >
								<label for="isCharge_info">是否收费:</label>
								<select id="isCharge_info" onchange="isChargeChg();">

								</select>
							</div>

							<div class="form-group chargeDiv" style="display:none;">
								<label for="unitPrice_info">人均收费:</label>
								<input type="number" id="unitPrice_info" class="numberInfo input-length" />

								<label for="chargeType_info">交费方式:</label>
								<select id="chargeType_info" >

								</select>
							</div>
							<div class="form-group chargeDiv" style="display:none">
								<table>
									<tr>
										<td style="width:130px;"><label for="chargeNotice_info">交费须知:</label></td>
										<td><textarea type="text" id="chargeNotice_info" rows="5" class="emptyInfo" style="height:130px;width:600px;"></textarea></td>
									</tr>
								</table>
							</div>

							<div class="form-group">
								<label>参赛限制:</label>
								至少<input type="number" id="userLimit_info" class="numberInfo input-length-small" />人，
								至多<input type="number" id="userMax_info" class="numberInfo input-length-small" />人
							</div>


							<div class="form-group">
								<label>列表图:</label>
								<form style="display:inline;" id="imageform" method="post" action="/comm/upload"  enctype="multipart/form-data"  target="imageframe">
									<input type="file" name="myfile" id="imgFile" onchange="preImg(this.id,'imgPre');" style="display: inline-block;width: 200px;" />
									<input type="hidden" name="path"  value="prefix.upload.path.matchNews" />
									<input id="uploadBtn" class="btn main-color-bluegreen" style="width: 70px;height: 30px;" type="submit" value="上传" onclick="upload()"/>
								</form>
							</div>

							<div style="margin: 0 auto;text-align:center;">
								<img id="imgPre" src="" style="display: none;max-height:150px" />
							</div>
							<div class="form-group">
								<label for="signupNotice_info">报名须知:</label>
							</div>
							<div class="form-group">
								<div id="signupNotice_info" style="width:900px;min-height:350px;"></div>
							</div>

							<div class="form-group">
								<label for="matchContent_info">赛事介绍:</label>
							</div>
							<div class="form-group">
								<div id="matchContent_info" style="width:900px;min-height:600px;"></div>
							</div>
						</div>
                        <div class="modal-footer">
                            <button id="addBtn" style="display:none;" class="btn main-color-bluegreen" onclick="addNewsInfo()">提交</button>
                            <button id="updBtn" style="display:none;" class="btn main-color-bluegreen" onclick="updateNewsInfo()">提交</button>
                            <button class="btn" onclick="goback();">取消</button>
                        </div>
					</div>
				</div>
			</div>

		</div>

		<div class="page-footer">

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

<%-- 富文本便捷器   提示： 若富文本编辑器是放在model弹框中的，要记得改model弹框和modal-backdrop的z-index的值，要小于1009  --%>
<script type="text/javascript" charset="utf-8" src="${ctxStatic}/js/ueditor/ueditor.config.js${jsversion}"></script>
<script type="text/javascript" charset="utf-8" src="${ctxStatic}/js/ueditor/ueditor.all.js${jsversion}"> </script>
<%-- 手动加载语言文件，会覆盖在配置项目里添加的语言类型 --%>
<script type="text/javascript" charset="utf-8" src="${ctxStatic}/js/ueditor/lang/zh-cn/zh-cn.js${jsversion}"></script>

<script type="text/javascript">
    //var industryTreeList = JSON.parse(${fns:toJson(industryTree)});
    //var areaTreeList = JSON.parse(${fns:toJson(areaTree)});
	var newsTypeObj = {"1":"URL","2":"原生"};
	var statusObj = {"0":"未发布","1":"已发布"};
    var sortTypeObj = {"1":"永不过期","2":"过期设置"};
    var isChargeObj = {"0":"免费","1":"收费"};
	var chargeTypeObj = {"0":"自选","1":"统一交费","2":"自己交费"};
	var operType = '${operType}';
	var ue;
	var signupUE;
	$(function() {
        //initJsTree("industryTree_div",industryTreeList);
        //initJsTree("areaTree_div",areaTreeList);
		initSelect();
        initTimer();
		ue = UE.getEditor("matchContent_info");
        /*ue.ready(function(){
            ue.setHeight(600);
        });*/
		signupUE = UE.getEditor("signupNotice_info");
		signupUE.ready(function(){
			signupUE.setHeight(300);
		});

        initInfo();
	});

	function goback(){
		var url = "${contextPath}/news/toMatchNews";
		if (operType == 'editor'){
			url += "?queryType=historyParam_match"
		}
		window.location.href = url;
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

	function initSelect(){
	    var allstr = '<option value="">请选择</option>';
	    var str = "";
        $("#matchType_info").empty();
        $.each(newsTypeObj,function(key,val){
            str += '<option value="'+key+'">'+val+'</option>';
        });
        $("#matchType_info").append(str);
        $("#matchType_info").val("1");

        str = "";
        $("#sortType_info").empty();
        $.each(sortTypeObj,function(key,val){
            str += '<option value="'+key+'">'+val+'</option>';
        });
        $("#sortType_info").append(str);
        $("#sortType_info").val("1");
		str = "";
		$("#isCharge_info").empty();
		$.each(isChargeObj,function(key,val){
			str += '<option value="'+key+'">'+val+'</option>';
		});
		$("#isCharge_info").append(str);
		$("#isCharge_info").val("0");
		str = "";
		$("#chargeType_info").empty();
		$.each(chargeTypeObj,function(key,val){
			str += '<option value="'+key+'">'+val+'</option>';
		});
		$("#chargeType_info").append(str);
		$("#chargeType_info").val("0");

	}

    function getObjValByKey(obj,key){
        var val = obj[key];
        return val == null || val == undefined ? "" : val;
    }

    function sortTypeChg(){
        var type = $("#sortType_info").val();
        if(type == 1){
            $(".sortDateLab").hide();
        }else{
            $(".sortDateLab").show();
        }
    }

    function isChargeChg(){
		var isCharge = $("#isCharge_info").val();
        $("#unitPrice_info").val(0);
        $("#chargeType_info").val("0");
        $("#chargeNotice_info").val("");
        $("#userLimit_info").val(0);
		if(isCharge == '0'){
			$(".chargeDiv").hide();
		}else{
			$(".chargeDiv").show();
		}
	}

	function initInfo(){
	    // 初始化页面
		$(".emptyInfo").val("");
		$(".numberInfo").val(0);
		$("#status_info").val(1);
		$("#matchType_info").val("1");
        $("#imgPre").hide();
        $("#imgFile").val("");
        $("#sortType_info").val("1");
        $("#sortDate_info").val("");
        sortTypeChg();
		$("#isCharge_info").val("0");
		isChargeChg();
		if(operType == 'add'){
			$("#addBtn").show();
			$("#updBtn").hide();
		}else{
			var id = $("#id_info").val();
			openEditorModel(id);
		}
	}
	<fus:hasRight url="/news/addMatchNews">

	function addNewsInfo(){
	    var param = getParamAndCheck();
	    if(param == null){
	        return;
		}
        $.uwayProcess.show();
        $.ajax({
            url: "${contextPath}/news/addMatchNews",
            async: true, type: "Post", dataType: "json",
            data: {"condition":JSON.stringify(param)},
            success: function (response) {
                $.uwayProcess.close();
                if(response.success){
					initInfo();
					ue.setContent("");
					signupUE.setContent("");
                    uwayNotice('通知',"新增成功");
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

    <fus:hasRight url="/news/updateMatchNews">
    function openEditorModel(id){
        $.ajax({
            url: "${contextPath}/news/getMatchNewsById",
            async: true, type: "Post", dataType: "json",
            data: {"id":id},
            success: function (response) {
                $.uwayProcess.close();
                if(response.success){
                    $("#imgPre").hide();
                    $("#imgFile").val("");
                    $("#addBtn").hide();
                    $("#updBtn").show();
                    var result = response.result;
                    $("#id_info").val(result.id);
                    $("#matchTitle_info").val(result.matchTitle);
                    $("#matchUnit_info").val(result.matchUnit);
                    $("#matchCity_info").val(result.matchCity);
                    $("#matchPlace_info").val(result.matchPlace);
                    $("#matchTime_info").val(result.matchTime);
                    $("#matchEndtime_info").val(result.matchEndtime);
                    $("#matchBake_info").val(result.matchBake);
                    $("#matchIndustry_info").val(result.matchIndustry);
                    $("#matchIndustryName_info").val(result.matchIndustryName);
                    $("#matchArea_info").val(result.matchArea);
                    $("#matchAreaName_info").val(result.matchAreaName);
                    $("#matchCompany_info").val(result.matchCompany);
                    // $("#matchContent_info").val(result.matchContent);
					if(result.matchContent != null && result.matchContent != undefined){
						ue.ready(function(){
							ue.setContent(result.matchContent);
						});
					}
                    $("#matchType_info").val(result.matchType);
                    $("#matchUrl_info").val(result.matchUrl);
					$("#dateBegin_info").val(result.dateBegin);
					$("#dateEnd_info").val(result.dateEnd);
					$("#matchTag_info").val(result.matchTag);
                    $("#sort_info").val(result.sort);
                    $("#sortType_info").val(result.sortType);
                    if(result.sortType == 2){
                        $("#sortDate_info").val(result.sortDate);
                    }
                    $("#isCharge_info").val(result.isCharge);
                    isChargeChg();
                    $("#unitPrice_info").val(result.unitPrice);
                    $("#chargeType_info").val(result.chargeType);
                    $("#chargeNotice_info").val(result.chargeNotice);
                    $("#userLimit_info").val(result.userLimit);
                    $("#userMax_info").val(result.userMax);
                    var signupNotice = result.signupNotice;
                    if(signupNotice != null && signupNotice != undefined){
                    	signupUE.ready(function(){
							signupUE.setContent(signupNotice);
						});
                    }
                    var imgUrl = result.matchImgurl;
                    $("#matchImgurl_info").val(imgUrl);
                    $("#imgPre").attr("src",'<%=Global.getConfig("prefix.visit.path") %>' + imgUrl);
                    $("#imgPre").show();

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

    function updateNewsInfo(){
        var param = getParamAndCheck();
        if(param == null){
            return;
        }
        var id = $("#id_info").val();
        if(id == ""){
            uwayWarning("警告","参数异常，刷新重试");
            return;
		}
        param.id = id;
        $.uwayProcess.show();
        $.ajax({
            url: "${contextPath}/news/updateMatchNews",
            async: true, type: "Post", dataType: "json",
            data: {"condition":JSON.stringify(param)},
            success: function (response) {
                $.uwayProcess.close();
                if(response.success){
                    uwayNotice('通知',"修改成功");
					goback();
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

	function getParamAndCheck(){
	    var matchTitle = $("#matchTitle_info").val();
	    if(matchTitle == ""){
	        uwayWarning("提示","请填写标题");
	        return null;
		}
	    var matchUnit = $("#matchUnit_info").val();
        // if(matchUnit == ""){
        //     uwayWarning("提示","请填写主办单位");
        //     return null;
        // }
        var matchCity = $("#matchCity_info").val();
        if(matchCity == ""){
            uwayWarning("提示","请填写赛事城市");
            return null;
        }
        var matchPlace = $("#matchPlace_info").val();
        // if(matchPlace == ""){
        //     uwayWarning("提示","请填写赛事地点");
        //     return null;
        // }
        var matchTime = $("#matchTime_info").val();
        if(matchTime == ""){
            uwayWarning("提示","请填写开放报名时间");
            return null;
        }
		var matchEndtime = $("#matchEndtime_info").val();
		if (matchEndtime == ""){
			uwayWarning("提示","请填写报名结束时间");
			return null;
		}
        var dateBegin = $("#dateBegin_info").val();
		if(dateBegin == ""){
			uwayWarning("提示","请填写比赛开始时间");
			return null;
		}
		// var dateEnd = $("#dateEnd_info").val();
		// if(dateEnd == ""){
		// 	uwayWarning("提示","请填写比赛结束时间");
		// 	return null;
		// }
        var matchBake = $("#matchBake_info").val();
        // if(matchBake == ""){
        //     uwayWarning("提示","请填写赛事组别及人数");
        //     return null;
        // }
        var matchIndustry = $("#matchIndustry_info").val();
        // if(matchIndustry == ""){
        //     uwayWarning("提示","请填写行业");
        //     return null;
        // }
        var matchArea = $("#matchArea_info").val();
        // if(matchArea == ""){
        //     uwayWarning("提示","请填写区域");
        //     return null;
        // }
        var matchCompany = $("#matchCompany_info").val();
        // if(matchCompany == ""){
        //     uwayWarning("提示","请填写公司名称");
        //     return null;
        // }
		var matchContent = ue.getContent();
        if(matchContent == ""){
            uwayWarning("提示","请填写赛事介绍");
            return null;
        }
        var matchType = $("#matchType_info").val();
	    var matchImgurl = $("#matchImgurl_info").val();
        if(matchImgurl == ""){
            uwayWarning("提示","请上传图片");
            return null;
        }
        var matchUrl = $("#matchUrl_info").val();
        var sort = $("#sort_info").val();
        if(sort == "" || sort.indexOf(".") > -1 || sort.indexOf("-") > -1){
            uwayWarning("提示","排序不能为空，且为非负整数");
            return null;
		}
        var matchTag = $("#matchTag_info").val();
        if (matchTag == "" || matchTag.length > 30){
			uwayWarning("提示","请按照提示填写标签");
			return null;
		}
        var sortType = $("#sortType_info").val();
        var sortDate = $("#sortDate_info").val();
        if (sortType == 2 && sortDate == ''){
            uwayWarning("提示","请填写权重过期时间");
            return null;
        }else if(sortType == 1){
            sortDate = null;
        }

        var isCharge = $("#isCharge_info").val();
		var unitPrice = $("#unitPrice_info").val();
		var chargeType = $("#chargeType_info").val();
		var chargeNotice = $("#chargeNotice_info").val();
		var userLimit = $("#userLimit_info").val();
		var userMax = $("#userMax_info").val();
		var signupNotice = signupUE.getContent();
		if(!(Number(userLimit) >= 0 && userLimit.indexOf(".") == -1)){
            uwayWarning("提示","请填写报名最小人数");
            return;
        }
		if(!(Number(userMax) >= 0 && userMax.indexOf(".") == -1)){
			uwayWarning("提示","请填写报名最小人数");
			return;
		}
        if(isCharge == "1"){
            if(unitPrice == "" || parseFloat(unitPrice) <= 0){
                uwayWarning("提示","请填写收费金额");
                return;
            }
            if(parseInt(userLimit) <= 0){
                uwayWarning("提示","请填写报名最小人数");
                return;
            }
        }
        var param = {
            "matchTitle":matchTitle,"matchUnit":matchUnit,"matchCity":matchCity,
            "matchPlace":matchPlace,"matchTime":matchTime,"matchBake":matchBake,
            "matchIndustry":matchIndustry,"matchArea":matchArea,"matchCompany":matchCompany,
			"matchContent":matchContent,"matchType":matchType,"matchImgurl":matchImgurl,
            "sortType":sortType,"sortDate":sortDate,"matchEndtime":matchEndtime,
			"matchUrl":matchUrl,"sort":sort,"dateBegin":dateBegin,/*"dateEnd":dateEnd,*/"matchTag":matchTag,
            "isCharge" : isCharge,"unitPrice" : unitPrice,"chargeType" : chargeType,"chargeNotice" : chargeNotice,
            "userLimit" : userLimit,"signupNotice" : signupNotice,"userMax":userMax
		}
		return param;
	}

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
                console.log('等待上传请求返回信息......')
                return;
            }
            if (result.success == true) {
                var fName = result.result;
                $("#matchImgurl_info").val(fName)
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
        $("#matchImgurl_info").val("");
        $("iframe[id='imageframe']").contents().find("body").html("");
    }

    function initTimer(){
        $('#matchTime_info').attr("readonly", "readonly").datepicker({   //日历初始化
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
				$("#matchEndtime_info").datepicker('option', 'minDate', selectedDate);
			}
        });

        $('#sortDate_info').attr("readonly", "readonly").datepicker({   //日历初始化
            dateFormat: 'yy-mm-dd',
//            maxDate: 'd',
            showTime: true,
            monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
            dayNames: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
            dayNamesShort: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
            dayNamesMin: ['日', '一', '二', '三', '四', '五', '六'],
            prevText: '<i class="fa fa-chevron-left"></i>',
            nextText: '<i class="fa fa-chevron-right"></i>'
        });

        $('#matchEndtime_info').attr("readonly", "readonly").datepicker({   //日历初始化
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
				$("#matchTime_info").datepicker('option', 'maxDate', selectedDate);
			}
        });

		$('#dateBegin_info').attr("readonly", "readonly").datepicker({   //日历初始化
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
				$("#dateEnd_info").datepicker('option', 'minDate', selectedDate);
			}
		});

		$('#dateEnd_info').attr("readonly", "readonly").datepicker({   //日历初始化
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
				$("#dateBegin_info").datepicker('option', 'maxDate', selectedDate);
			}
		});

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