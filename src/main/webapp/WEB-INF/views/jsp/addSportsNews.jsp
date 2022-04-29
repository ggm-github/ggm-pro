<%@ taglib prefix="fus" uri="http://java.sun.com/jsp/jstl/funs" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="background:url(${ctxStatic}/img/sysBg.png);">
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
			.form-group select,.form-input{
				height:30px;
				width: 170px;
			}
			.form-input-long{
				height:30px;
				width:60%;
			}
		</style>
	</head>
	<body class="desktop-detected pace-done" style="background:url(${ctxStatic}/img/sysBg.png);">

		<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/views/include/leftmenu.jsp"></jsp:include>
		<div id="main" role="main">
			<div id="ribbon">
				<div class="col-sm-7">
					<ol class="breadcrumb">
						<li>内容管理</li>
						<c:if test="${'add' == operType}">
							<li>新增新闻</li>
						</c:if>
						<c:if test="${'editor' == operType}">
							<li>编辑新闻</li>
						</c:if>
					</ol>
				</div>
			</div>
			<div id="content" style="z-index: 1001;">
				<div style="max-width: 1000px;margin: 0 auto;">
					<div class="modal-body">
						<div style="width: 1000px;margin: 0 auto;">
							<div style="display:none;">
								<input id="id_info" type="text" value="${id}" />
								<input id="status_info" type="text" value="1"/>
								<input id="readCnt_info" type="text" class="numberInfo" value="0"/>
								<input type="text" id="newsImgurl_info" class="emptyInfo" />
							</div>
							<div class="form-group">
								<label for="newsTitle_info">标题:</label>
								<input type="text" id="newsTitle_info" class="emptyInfo form-input-long" placeholder="请输入新闻标题" />
							</div>
							<div class="form-group">
								<label for="subNewsTitle_info">副标题:</label>
								<input type="text" id="subNewsTitle_info" class="emptyInfo form-input-long" placeholder="请输入新闻副标题" />
							</div>

							<div class="form-group">
								<label for="newsAuthor_info">来源:</label>
								<input type="text" id="newsAuthor_info" class="emptyInfo form-input" placeholder="请输入来源">
								<label for="tag_info">新闻标签:</label>
								<input type="text" id="tag_info" class="emptyInfo form-input" placeholder="请输入新闻标签" />
							</div>
							<div class="form-group" style="display:none;">
								<label for="newsType_info">打开方式:</label>
								<select id="newsType_info">

								</select>
							</div>
							<div class="form-group">
								<label for="newsUrl_info">新闻地址:</label>
								<input type="text" id="newsUrl_info" class="emptyInfo form-input-long" placeholder="请输入新闻地址">
							</div>

							<div class="form-group">
								<label for="sortType_info">权重设置:</label>
								<select id="sortType_info" onchange="sortTypeChg();">

								</select>
								<label class="sortDateLab" style="display:none;">过期时间:</label>
								<label class="sortDateLab" style="display:none;width:180px;margin-left: -10px;">
									<input type="text" id="sortDate_info" value="" class="form-input">
									<i class="icon-append fa fa-calendar" style="margin-left: -37px; height: 28px; width: 28px;"></i>
								</label>
							</div>
							<div class="form-group">
								<label for="sort_info">权重:</label>
								<input type="number" id="sort_info" class="numberInfo form-input" placeholder="请输入排序" />
							</div>

							<div class="form-group">
								<label>列表图:</label>
								<form style="display:inline;" id="imageform" method="post" action="/comm/upload"  enctype="multipart/form-data"  target="imageframe">
									<input type="file" name="myfile" id="imgFile" onchange="preImg(this.id,'imgPre');" style="display: inline-block;width: 200px;" />
									<input type="hidden" name="path"  value="prefix.upload.path.sportsNews" />
									<input id="uploadBtn" class="btn main-color-bluegreen" style="width: 70px;height: 30px;" type="submit" value="上传" onclick="upload()"/>
								</form>
							</div>

							<div style="margin: 0 auto;text-align:center;">
								<img id="imgPre" src="" style="display: none;max-height:150px" />
							</div>

							<div class="form-group" style="margin-top:10px;">
								<div id="newsContent_info" style="width:900px;"></div>
							</div>
						</div>
					</div>
					<div class="modal-footer" style="text-align: center;">
						<button id="addBtn" style="display:none;" class="btn main-color-bluegreen" onclick="addNewsInfo()">提交</button>
						<button id="updBtn" style="display:none;" class="btn main-color-bluegreen" onclick="updateNewsInfo()">提交</button>
						<button class="btn" onclick="goback();">返回</button>
					</div>
				</div>
			</div>

		</div>

		<div class="page-footer">

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

<%-- 富文本便捷器   提示： 若富文本编辑器是放在model弹框中的，要记得改model弹框和modal-backdrop的z-index的值，要小于1009  --%>
<script type="text/javascript" charset="utf-8" src="${ctxStatic}/js/ueditor/ueditor.config.js${jsversion}"></script>
<script type="text/javascript" charset="utf-8" src="${ctxStatic}/js/ueditor/ueditor.all.js${jsversion}"> </script>
<%-- 手动加载语言文件，会覆盖在配置项目里添加的语言类型 --%>
<script type="text/javascript" charset="utf-8" src="${ctxStatic}/js/ueditor/lang/zh-cn/zh-cn.js${jsversion}"></script>

<script type="text/javascript">
	var newsTypeObj = {"1":"URL"/*,"2":"原生"*/};
	var statusObj = {"0":"未发布","1":"已发布"};
	var sortTypeObj = {"1":"永不过期","2":"过期设置"};
	var operType = '${operType}';
	var dataTable;
	var ue;
	$(function() {
        ue = UE.getEditor("newsContent_info");
		initSelect();
		initDateTimer();
		initInfo();
	});

	function goback(){
		var url = "${contextPath}/news/toSportsNews";
		if(operType == 'editor'){
			url += "?queryType=historyParam_news";
		}
		window.location.href = url;
	}

	function initSelect(){
	    var allstr = '<option value="">请选择</option>';
	    var str = "";
        $("#newsType_info").empty();
        $.each(newsTypeObj,function(key,val){
            str += '<option value="'+key+'">'+val+'</option>';
        });
        $("#newsType_info").append(str);
        $("#newsType_info").val("1");
        str = "";
        $("#sortType_info").empty();
        $.each(sortTypeObj,function(key,val){
            str += '<option value="'+key+'">'+val+'</option>';
        });
        $("#sortType_info").append(str);
        $("#sortType_info").val("1");
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

	function initInfo(){
	    // 初始化页面
		$(".emptyInfo").val("");
		$(".numberInfo").val(0);
		$("#status_info").val(1);
		$("#newsType_info").val(1);
        $("#sortType_info").val(1);
        $("#sortDate_info").val("");
        sortTypeChg();
        $("#imgPre").hide();
        $("#imgFile").val("");
        if(operType == 'add'){
			$("#addBtn").show();
			$("#updBtn").hide();
		}else{
        	var id = $("#id_info").val();
			openEditorModel(id);
		}
	}

	<fus:hasRight url="/news/addSportsNews">
	function addNewsInfo(){
	    var param = getParamAndCheck();
	    if(param == null){
	        return;
		}
        $.uwayProcess.show();
        $.ajax({
            url: "${contextPath}/news/addSportsNews",
            async: true, type: "Post", dataType: "json",
            data: {"condition":JSON.stringify(param)},
            success: function (response) {
                $.uwayProcess.close();
                if(response.success){
					initInfo();
					ue.setContent("");
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

    function openEditorModel(id){
        $.ajax({
            url: "${contextPath}/news/getSportsNewsById",
            async: true, type: "Post", dataType: "json",
            data: {"id":id},
            success: function (response) {
                $.uwayProcess.close();
                if(response.success){
                    var result = response.result;
                    $("#imgPre").hide();
                    $("#imgFile").val("");
                    $("#addBtn").hide();
                    $("#updBtn").show();
                    $("#id_info").val(result.id);
                    $("#newsTitle_info").val(result.newsTitle);
                    $("#subNewsTitle_info").val(result.subNewsTitle);
                    $("#newsAuthor_info").val(result.newsAuthor);
                    $("#newsType_info").val(result.newsType);
                    var imgUrl = result.newsImgurl;
                    $("#newsImgurl_info").val(imgUrl);
                    $("#imgPre").attr("src",'<%=Global.getConfig("prefix.visit.path")%>/' + imgUrl);
                    $("#imgPre").show();
                    $("#newsUrl_info").val(result.newsUrl);
                    $("#tag_info").val(result.tag);
                    $("#sort_info").val(result.sort);
                    $("#sortType_info").val(result.sortType);
                    if(result.sortType == 2){
                        $("#sortDate_info").val(result.sortDate);
                    }
                    if(result.newsContent != null && result.newsContent != undefined){
                    	ue.ready(function(){
							ue.setContent(result.newsContent);
						});
					}
					sortTypeChg();
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
    <fus:hasRight url="/news/updateSportsNews">
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
            url: "${contextPath}/news/updateSportsNews",
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
	    var newsTitle = $("#newsTitle_info").val();
	    if(newsTitle == ""){
	        uwayWarning("提示","请填写标题");
	        return null;
		}
	    var newsAuthor = $("#newsAuthor_info").val();
        if(newsAuthor == ""){
            uwayWarning("提示","请填写来源");
            return null;
        }
        var newsType = $("#newsType_info").val();
	    var newsImgurl = $("#newsImgurl_info").val();
        if(newsImgurl == ""){
            uwayWarning("提示","请上传图片");
            return null;
        }
        var newsUrl = $("#newsUrl_info").val();
        var tag = $("#tag_info").val();
        var sort = $("#sort_info").val();
        if(sort == "" || sort.indexOf(".") > -1 || sort.indexOf("-") > -1){
            uwayWarning("提示","排序不能为空，且为非负整数");
            return null;
		}
		var newsContent = ue.getContent();
        console.log(newsContent);
        if(newsContent == ""){
            uwayWarning("提示","请编辑新闻内容");
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
		var subNewsTitle = $("#subNewsTitle_info").val();
        var param = {
            "newsTitle":newsTitle,"subNewsTitle":subNewsTitle,"newsAuthor":newsAuthor,
			"newsType":newsType,"newsImgurl":newsImgurl,"newsUrl":newsUrl,"tag":tag,
			"sortType":sortType,"sortDate":sortDate,"sort":sort,"newsImgurl":newsImgurl,
			"newsContent":newsContent
		}
		return param;
	}

    function initDateTimer(){
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
                console.log('等待上传请求返回信息......');
                return;
            }
            if (result.success == true) {
                var fName = result.result;
                $("#newsImgurl_info").val(fName)
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
        $("#newsImgurl_info").val("");
        $("iframe[id='imageframe']").contents().find("body").html("");
    }


</script>
</body>
</html>