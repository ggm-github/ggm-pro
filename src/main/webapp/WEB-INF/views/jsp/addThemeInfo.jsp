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
		<link rel="stylesheet" type="text/css" media="screen" href="${ctxStatic}/css/bootstrap-select.css${jsversion}">
		<link rel="stylesheet" type="text/css" media="screen" href="${ctxStatic}/css/bootstrap-select.less${jsversion}">
		<link rel="stylesheet" type="text/css" media="screen" href="${ctxStatic}/css/variables.less${jsversion}">
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
				height:35px;
				width:150px;
			}
			.input-length{
				height:35px;
				width:150px;
			}
			.input-length-small{
				height:35px;
				width:50px;
			}
			.input-length-big{
				height:35px;
				width:60%;
			}
			.hidden-part{
				display:none;
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
						<li>活动主题管理</li>
						<c:if test="${'add' == operType}">
							<li>新增主题</li>
						</c:if>
						<c:if test="${'editor' == operType}">
							<li>编辑主题</li>
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
								<input type="text" id="themeImgurl_info" class="emptyInfo" />
								<input type="text" id="noticeImage_info" class="emptyInfo" />
							</div>
							<div class="form-group">
								<label for="themeTitle_info">标题:</label>
								<input type="text" id="themeTitle_info" class="emptyInfo input-length-big" placeholder="请输入赛事标题" maxlength="200" />
							</div>

							<div class="form-group">
								<label>话题封面:</label>
								<form style="display:inline;" method="post" action="/comm/upload"  enctype="multipart/form-data"  target="imageframe">
									<input type="file" name="myfile" id="imgFile" onchange="preImg(this.id,'imgPre','imageframe','themeImgurl_info');" style="display: inline-block;width: 200px;" />
									<input type="hidden" name="path"  value="prefix.upload.path.themeInfo" />
									<input class="btn main-color-bluegreen" style="width: 70px;height: 30px;" type="submit" value="上传" onclick="upload('imageframe','themeImgurl_info')"/>
								</form>
							</div>
							<div style="margin: 0 auto;text-align:center;">
								<img id="imgPre" src="" style="display: none;max-height:150px" />
							</div>

							<div class="form-group">
								<label for="themeRemark_info">导语:</label>
								<textarea type="text" id="themeRemark_info" class="emptyInfo input-length-big" style="height:95px;" ></textarea>
							</div>
							<div class="form-group">
								<label for="sort_info">权重:</label>
								<input type="number" id="sort_info" class="numberInfo input-length" placeholder="请输入权重" />
							</div>
							<div class="form-group">
								<label for="sort_info">活动关联:</label>
								<label style="width: 50px;text-align: left;" for="themeType_1" onclick="themeTypeClik();"><input id="themeType_1" type="radio" name="themeType" value="1" /><span>否</span></label>
								<label style="width: 50px;text-align: left;" for="themeType_2" onclick="themeTypeClik();"><input id="themeType_2" type="radio" name="themeType" value="2" /><span>是</span></label>

							</div>
                            <div class="form-group" id="matchDiv" style="display:none;">
                                <label for="matchId_info">赛事活动:</label>
                                <select id="matchId_info" class="selectpicker input-length" data-live-search="true">

                                </select>

                            </div>
                            <div class="form-group">
                                <label for="userId_info">发表人:</label>
                                <select id="userId_info" class="selectpicker input-length" data-live-search="true">

                                </select>
                            </div>

							<div class="form-group">
								<label for="noticeType_info">公告类型:</label>
								<select id="noticeType_info" class="input-length" onchange="noticeTypeChg(1);">

								</select>
							</div>
							<div class="form-group hidden-part" id="noticeImgDiv">
								<label>公告图片:</label>
								<form style="display:inline;" method="post" action="/comm/upload"  enctype="multipart/form-data"  target="imageframe_notice">
									<input type="file" name="myfile" id="imgFile_notice" onchange="preImg(this.id,'imgPre_notice','imageframe_notice','noticeImage_info');" style="display: inline-block;width: 200px;" />
									<input type="hidden" name="path"  value="prefix.upload.path.themeInfo" />
									<input class="btn main-color-bluegreen" style="width: 70px;height: 30px;" type="submit" value="上传" onclick="upload('imageframe_notice','noticeImage_info')"/>
								</form>
							</div>
							<div style="margin: 0 auto;text-align:center;">
								<img id="imgPre_notice" src="" style="display: none;max-height:150px" />
							</div>
							<div class="form-group hidden-part" id="noticeTextDiv">
								<label>公告信息:</label>
								<textarea id="noticeText_info" rows="5" class="input-length-big"></textarea>
							</div>
							<div class="form-group hidden-part" id="noticeOpenWayDiv">
								<label for="noticeOpenWay_info">跳转方式:</label>
								<select id="noticeOpenWay_info" class="input-length" onchange="noticeTypeChg(2);">

								</select>
							</div>
							<div class="form-group hidden-part" id="noticeOpenDataDiv">
								<label for="noticeOpenData_info">跳转路径:</label>
								<input type="text" id="noticeOpenData_info" class="emptyInfo input-length-big" />
							</div>
						</div>
                        <div class="modal-footer" style="text-align: center;">
                            <button id="addBtn" style="display:none;" class="btn main-color-bluegreen" onclick="addThemeInfo()">提交</button>
                            <button id="updBtn" style="display:none;" class="btn main-color-bluegreen" onclick="updateThemeInfo()">提交</button>
                            <button class="btn" onclick="goback();">取消</button>
                        </div>
					</div>
				</div>
			</div>

		</div>

		<div class="page-footer">

		</div>


		<iframe id="imageframe" name="imageframe" style="display: none;"></iframe>
		<iframe id="imageframe_notice" name="imageframe_notice" style="display: none;"></iframe>

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
<script src="${ctxStatic}/js/bootstrap/bootstrap-select.js${jsversion}"></script>
<script src="${ctxStatic}/js/bootstrap/defaults-zh_CN.js${jsversion}"></script>

<script type="text/javascript">
	var operType = '${operType}';
	var noticeTypeObj = {"0":"无公告","1":"图片公告","2":"文字公告"};
	var noticeOpenWayObj = {"-1":"无跳转","0":"链接跳转","19":"人气榜单"};
	$(function() {
		initSelect();
		guestlist("matchId_info","${contextPath}/news/getMatchForSelect",{});
        guestlist("userId_info","${contextPath}/app/getAppUserListForSelect",{});
        initInfo();
	});

	function initSelect(){
		var allStr = "<option value=''>请选择</option>";
		var str = "";
		$.each(noticeTypeObj,function(key,val){
			str += '<option value="'+key+'">'+val+'</option>';
		});
		$("#noticeType_info").empty();
		$("#noticeType_info").append(str);
		str = "";
		$.each(noticeOpenWayObj,function(key,val){
			str += '<option value="'+key+'">'+val+'</option>';
		});
		$("#noticeOpenWay_info").empty();
		$("#noticeOpenWay_info").append(str);
	}

	function goback(){
		var url = "${contextPath}/theme/toThemePage";
		if (operType == 'editor'){
			url += "?queryType=historyParam_theme"
		}
		window.location.href = url;
	}

    function themeTypeClik(){
		var themeType = $("input[name='themeType']:checked").val();
		if(themeType == '2'){
			$("#matchDiv").show();
		}else{
			$("#matchDiv").hide();
		}
	}

	function initInfo(){
	    // 初始化页面
		$(".emptyInfo").val("");
		$(".numberInfo").val(0);
		$("input[name='themeType']").get(0).checked = true;
        $("input[name='themeType']").get(1).checked = false;
		themeTypeClik();
		$('#matchId_info').selectpicker('deselectAll');
        $('#userId_info').selectpicker('deselectAll');
        $("#imgPre").hide();
        $("#imgFile").val("");
		$("#imgFile_notice").val("");
		$("#imgPre_notice").hide();
		$("#noticeType_info").val(0);
		noticeTypeChg(1);
		$("#noticeOpenWay_info").val(-1);
		noticeTypeChg(2);
		if(operType == 'add'){
			$("#addBtn").show();
			$("#updBtn").hide();
		}else{
			var id = $("#id_info").val();
			openEditorModel(id);
		}
	}

	function noticeTypeChg(type){
		// 公告类型变更
		if (type == 1){
			$("#imgPre_notice").hide();
			$(".hidden-part").hide();
			var noticeType = $("#noticeType_info").val();
			if (noticeType == 1){
				$("#noticeImgDiv").show();
				$("#noticeOpenWayDiv").show();
			}else if (noticeType == 2){
				$("#noticeTextDiv").show();
			}
		} else if (type == 2){
			// 公告跳转方式变更
			var noticeOpenWay = $("#noticeOpenWay_info").val();
			if (noticeOpenWay == 0){
				$("#noticeOpenDataDiv").show();
			} else{
				$("#noticeOpenDataDiv").hide();
			}
		}
	}

	<fus:hasRight url="/theme/addThemeInfo">

	function addThemeInfo(){
	    var param = getParamAndCheck();
	    if(param == null){
	        return;
		}
        $.uwayProcess.show();
        $.ajax({
            url: "${contextPath}/theme/addThemeInfo",
            async: true, type: "Post", dataType: "json",
            data: {"condition":JSON.stringify(param)},
            success: function (response) {
                $.uwayProcess.close();
                if(response.success){
					initInfo();
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

    <fus:hasRight url="/theme/updateThemeInfo">
    function openEditorModel(id){
        $.ajax({
            url: "${contextPath}/theme/getThemeInfoById",
            async: true, type: "Post", dataType: "json",
            data: {"id":id},
            success: function (response) {
                $.uwayProcess.close();
                if(response.success){
                    $("#imgPre").hide();
					$("#imgPre_notice").hide();
                    $("#addBtn").hide();
                    $("#updBtn").show();
                    var result = response.result;
                	console.log(result)
                    $("#id_info").val(result.id);
                    $("#themeTitle_info").val(result.themeTitle);
                    $("#themeRemark_info").val(result.themeRemark);
                    $("#sort_info").val(result.sort);
                    var themeType = result.themeType;
                    if (themeType == 1){
                        $("input[name='themeType']").get(0).checked = true;
                        $("input[name='themeType']").get(1).checked = false;
                    }else if (themeType == 2){
                        $("input[name='themeType']").get(0).checked = false;
                        $("input[name='themeType']").get(1).checked = true;
                    }
                    themeTypeClik();
                    var matchId = result.matchId;
                    if (matchId != null && matchId != 0){
                        $('#matchId_info').selectpicker('val', matchId);
                    }
                    var userId = result.userId;
                    if (userId != null && userId != 0){
                        $('#userId_info').selectpicker('val', userId);
                    }
                    var imgUrl = result.themeImgurl;
                    $("#themeImgurl_info").val(imgUrl);
                    $("#imgPre").attr("src",'<%=Global.getConfig("prefix.visit.path") %>' + imgUrl);
                    $("#imgPre").show();
					var noticeType = result.noticeType;
					$("#noticeType_info").val(noticeType);
					noticeTypeChg(1);
					if (noticeType == 1) {
						$("#noticeImage_info").val(result.noticeImage);
						$("#imgPre_notice").attr("src",'<%=Global.getConfig("prefix.visit.path") %>' + result.noticeImage);
						$("#imgPre_notice").show();
					}else if (noticeType == 2){
						$("#noticeText_info").val(result.noticeText);
					}
					var noticeOpenWay = result.noticeOpenWay;
					$("#noticeOpenWay_info").val(noticeOpenWay);
					if (noticeOpenWay != -1) {
						$("#noticeOpenData_info").val(result.noticeOpenData);
					}
					noticeTypeChg(2);
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

    function updateThemeInfo(){
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
            url: "${contextPath}/theme/updateThemeInfo",
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
	    var themeTitle = $("#themeTitle_info").val();
	    if(themeTitle == ""){
	        uwayWarning("提示","请填写标题");
	        return null;
		}
	    var themeRemark = $("#themeRemark_info").val();
		if(themeRemark == ""){
			uwayWarning("提示","请填写导语");
			return null;
		}
	    var themeImgurl = $("#themeImgurl_info").val();
        if(themeImgurl == ""){
            uwayWarning("提示","请上传图片");
            return null;
        }
        var sort = $("#sort_info").val();
        if(sort == "" || sort.indexOf(".") > -1 || sort.indexOf("-") > -1){
            uwayWarning("提示","排序不能为空，且为非负整数");
            return null;
		}
        var userId = $("#userId_info").val();
        if (userId == ''){
			uwayWarning("提示","请选择发起人");
			return null;
		}
        var themeType = $("input[name='themeType']:checked").val();
        var matchId = $("#matchId_info").val();
        if (themeType == 2 && matchId == ''){
            uwayWarning("提示","请选择赛事活动");
            return null;
        }else if(themeType == 1){
        	matchId = 0;
		}
        var noticeType = $("#noticeType_info").val();
		var noticeOpenWay = $("#noticeOpenWay_info").val();
		if(noticeOpenWay == ''){
			noticeOpenWay = -1;
		}
        var noticeImage = null;
        var noticeText = null;
        if (noticeType == 1){
			noticeImage = $("#noticeImage_info").val();
			if (noticeImage == ''){
				uwayWarning("提示","请上传公告图片");
				return null;
			}
		} else if (noticeType == 2){
        	// 文字公告直接将跳转设置为-1
			noticeOpenWay = -1;
			noticeText = $("#noticeText_info").val();
			if (noticeText == ''){
				uwayWarning("提示","请填写公告信息");
				return null;
			}
		}
        var noticeOpenData = null;
		if (noticeOpenWay == "0") {
			noticeOpenData = $("#noticeOpenData_info").val();
			if (noticeOpenData == ''){
				uwayWarning("提示","请填写公告跳转路径或位置");
				return null;
			}
		}else if(noticeOpenWay == "19" && matchId == ''){
			uwayWarning("提示","请选择赛事活动");
			return null;
		}

        var param = {
            "themeTitle":themeTitle,"themeRemark":themeRemark,"themeImgurl":themeImgurl,
            "sort":sort,"themeType":themeType,"matchId":matchId,"userId":userId,"noticeType":noticeType,
			"noticeImage":noticeImage,"noticeText":noticeText,"noticeOpenWay":noticeOpenWay,
			"noticeOpenData":noticeOpenData
		};
		return param;
	}

    //文件上传
    function upload(iframeId,imgUrlId) {
        $.uwayProcess.show();
        var t = setInterval(function() {
            //获取iframe标签里body元素里的文字。即服务器响应过来的"上传成功"或"上传失败"
            var word = $("iframe[id='"+iframeId+"']").contents().find("body").text();
            var result;
            try {
                result = JSON.parse(word);
            } catch (e){
                console.log('等待上传请求返回信息......')
                return;
            }
            if (result.success == true) {
                var fName = result.result;
                $("#" + imgUrlId).val(fName)
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
    function preImg(sourceId, targetId,iframeId,imgUrlId) {
        var url = getFileUrl(sourceId);
        $("#"+targetId).attr("src",url);
        $("#"+targetId).show();
        //每次更换时都将路径清空，在上传后填写
        $("#" + imgUrlId).val("");
        $("iframe[id='"+iframeId+"']").contents().find("body").html("");
    }

	function guestlist(id,url,param){
		$("#" + id).empty();
		$.ajax({
			type: "post",
			url:url,//查询url
			data:param,
			async:false,
			dataType: "json",
			success: function(data){
			    console.log(data);
				if (data !='') {
					var html = '';
					$.each(data,function (index,ele) {
						html += '<option value="' + ele.id + '">' + ele.name+ '</option>';
					});
					$("#" + id).html(html);
				}
				//刷新select
				$("#" + id).selectpicker('refresh');
			}
		});
	}

</script>
</body>
</html>