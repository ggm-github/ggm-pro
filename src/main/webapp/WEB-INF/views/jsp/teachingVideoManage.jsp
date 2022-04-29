<%@ taglib prefix="fus" uri="http://java.sun.com/jsp/jstl/funs" %>
<%@ taglib prefix="funs" uri="http://java.sun.com/jsp/jstl/funs" %>
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
					<li>内容管理</li><li>视频上传</li>
				</ol>
			</div>
		</div>
		<div id="content">
			<div class="condition-wrapper">
				<div style="display:none;">
					<input type="text" id="parentId_q" value="${parentId}" />
				</div>
				<div class="divform">
					<label for="teachingName_q">视频名称</label>
					<input id="teachingName_q" type="text" class="input-length"/>
				</div>

				<div class="divform">
					<label for="checkStatus_q">审核状态</label>
					<select id="checkStatus_q" class="select-query">

					</select>
				</div>

				<div class="divform">
					<label for="status_q">视频类型</label>
					<select id="status_q" class="select-query">

					</select>
				</div>

				<div class="divform">
					<button class="btn main-color-bluegreen" onclick="query()">查询</button>
					<fus:hasRight url="/video/addTeachingVideo">
						<button class="btn main-color-bluegreen" onclick="showInfoModal()">上传视频</button>
					</fus:hasRight>
				</div>
			</div>
			<div class="condition-wrapper" style="margin-top:10px;" id="formDiv2">
				<div class="divform">
					<label for="videoType_q">视频类型</label>
					<select id="videoType_q" class="select-query">

					</select>
				</div>

				<div class="divform">
					<label for="isTask_q">是否打卡</label>
					<select id="isTask_q" class="select-query">

					</select>
				</div>

			</div>
			<div class="condition-wrapper" style="margin-top:10px;">

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
					<h4 class="modal-title" >视频</h4>
				</div>
				<div class="modal-body">
					<div style="width: 600px;margin: 0 auto;">
						<div style="display:none;">
							<input id="id_info" type="text" />
							<input type="hidden" id="teachingUrl_info"/>
							<input type="hidden" id="coverUrl_info"/>
						</div>
						<div class="form-group">
							<label for="teachingName_info">视频名称:</label>
							<input type="text" id="teachingName_info" class="input-length-big" />
						</div>
						<div class="form-group" style="display:none;">
							<label for="videoType_info">视频类型:</label>
							<select id="videoType_info" class="select-query" onchange="videoTypeChg();">

							</select>
						</div>
						<div class="form-group">
							<label for="videoTag_info">父级视频:</label>
							<input type="number" id="parentId_info" class="input-length" readonly/>
						</div>
						<div class="form-group">
							<label for="videoTag_info">视频标签:</label>
							<input type="text" id="videoTag_info" class="input-length-big" placeholder="标签录入格式：#+标签内容"/>
						</div>
						<div class="form-group">
							<label for="videoTag_info">导语:</label>
							<textarea type="text" id="teachingDisc_info" class="input-length-big" ></textarea>
						</div>
						<div class="form-group" id="isTaskDiv">
							<label for="isTask_info">是否打卡:</label>
							<select id="isTask_info" class="select-query">

							</select>
						</div>
						<div class="form-group" id="mainArea">
							<form ref="vExample">
								<input type="file" style="display:none;" ref="vExampleFile" @change="vExampleUpload" />
							</form>
							<label for="videoTag_info">视频上传:</label>
							<a href="javascript:void(0);" class="btn main-color-bluegreen" @click="vExampleAdd">上传视频</a>
							<div class="row" id="resultBox">
								<!-- 上传信息组件	 -->
								<div class="uploaderMsgBox" v-for="uploaderInfo in uploaderInfos">
									<div v-show="uploaderInfo.videoInfo">
										上传进度：{{Math.floor(uploaderInfo.progress * 100) + '%'}}；上传结果：{{uploaderInfo.isVideoUploadCancel ? '已取消' : uploaderInfo.isVideoUploadSuccess ? '上传成功' : '上传中'}}；
										<span style="display: none;" id="videoUrlspan">{{uploaderInfo.videoUrl}}</span>
										<span style="display: none;" id="fileIdspan">{{uploaderInfo.fileId}}</span>
										<a href="javascript:void(0);" class="cancel-upload" v-if="!uploaderInfo.isVideoUploadSuccess && !uploaderInfo.isVideoUploadCancel" @click="uploaderInfo.cancel()">取消上传</a><br>
									</div>
								</div>
							</div>
						</div>
						<%--<div class="form-group">--%>
							<%--<form id="insertform" method="post" action="/comm/upload"  enctype="multipart/form-data"  target="uploadframe">--%>
								<%--<label style="">视频选择:</label>--%>
								<%--<label style="width:210px;">--%>
									<%--<input style="width: 200px" type="file" name="myfile" id="myfile" value="" onchange="preImg(this.id,null,'teachingUrl_info','uploadframe');" />--%>
									<%--<input type="hidden" name="path" value="prefix.upload.path.teachingVideo"/>--%>
								<%--</label>--%>
								<%--<label>--%>
									<%--<input class="btn main-color-bluegreen" type="submit" value="上传" onclick="upload('uploadframe','teachingUrl_info')"/>--%>
								<%--</label>--%>
							<%--</form>--%>
						<%--</div>--%>
						<div id="videoUrlDiv" class="form-group" style="display:none">
							<div style="flex-wrap: nowrap;display: flex;">
								<label>视频路径:</label>
								<div style="width: 400px;word-break:break-all;" id="videoUrlShow"></div>
							</div>
						</div>
						<div class="form-group">
							<form method="post" action="/comm/upload"  enctype="multipart/form-data"  target="imageframe">
								<label style="">图片选择:</label>
								<label style="width:210px;">
									<input style="width: 200px" type="file" name="myfile" id="myfile1" value="" onchange="preImg(this.id,'imgPre','coverUrl_info','imageframe');" />
									<input type="hidden" name="path" value="prefix.upload.path.teachingVideo"/>
								</label>
								<label>
									<input class="btn main-color-bluegreen" type="submit" value="上传" onclick="upload('imageframe','coverUrl_info')"/>
								</label>
							</form>
						</div>
						<div style="margin: 0 auto;text-align:center;">
							<img id="imgPre" src="" style="display: none;max-height:150px" />
						</div>
						<div class="form-group">
							<label for="sort_chk">权重:</label>
							<input type="number" id="sort_info" class="numberInfo input-length" placeholder="请输入权重" />
						</div>


						<div class="form-group">
							<label for="themeId_info">关联话题:</label>
							<select id="themeId_info" class="selectpicker input-length" data-live-search="true">

							</select>
						</div>
						<div class="form-group">
							<label for="operUserId_info">发表人:</label>
							<select id="operUserId_info" class="selectpicker input-length" data-live-search="true">

							</select>
						</div>

					</div>
				</div>
				<div class="modal-footer">
					<button id="addBtn" class="btn main-color-bluegreen" style="display:none;" onclick="addVideoInfo()">新增</button>
					<button id="modBtn" class="btn main-color-bluegreen" style="display:none;" onclick="updateVideoInfo()">修改</button>
					<button class="btn" onclick="$('#infoModal').modal('hide')">取消</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="checkModal" tabindex="-1" role="dialog" aria-labelledby="infoModalTitle" aria-hidden="false">
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
							<input id="id_chk" type="text" class="emptyInfo"/>
							<input id="videoType_chk" type="text" class="emptyInfo" />
						</div>
						<div class="form-group" id="checkDiv">
							<label for="checkStatus_chk">审核状态:</label>
							<select id="checkStatus_chk" class="select-query">
								<option value="1">通过</option>
								<option value="2">驳回</option>
							</select>
						</div>
						<div class="form-group">
							<label for="checkReason_chk">审核备注:</label>
							<input type="text" id="checkReason_chk" />
						</div>
						<div class="form-group">
							<label for="sort_chk">权重:</label>
							<input type="number" id="sort_chk" class="numberInfo" placeholder="请输入权重" />
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn main-color-bluegreen" onclick="checkConfirm()">提交</button>
					<button class="btn" onclick="$('#checkModal').modal('hide')">取消</button>
				</div>
			</div>
		</div>
	</div>


	<div class="modal fade" id="videoModal" tabindex="-1" role="dialog" aria-labelledby="videoModalTitle"
		 aria-hidden="false" >
		<div class="modal-dialog" style="width:600px;">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">播放</h4>
				</div>
				<div class="modal-body">
					<div id="videoDiv" style="width: 100%;margin: 0 auto;text-align: center;">

					</div>
				</div>
				<div class="modal-footer">
					<button class="btn main-color-bluegreen" onclick="closeVideo();">关闭</button>
				</div>
			</div>
		</div>
	</div>

	<iframe id="uploadframe" name="uploadframe" style="display: none;"></iframe>
	<iframe id="imageframe" name="imageframe" style="display: none;"></iframe>


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
<script src="https://cdn.jsdelivr.net/npm/es6-promise@4/dist/es6-promise.auto.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/vue/2.5.21/vue.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.18.0/axios.js"></script>
<script src="https://unpkg.com/vod-js-sdk-v6"></script>
<script src="${ctxStatic}/js/bootstrap/bootstrap-select.js${jsversion}"></script>
<script src="${ctxStatic}/js/bootstrap/defaults-zh_CN.js${jsversion}"></script>
<script type="text/javascript">
	var checkStatusObj = {"0":"未审核","1":"审核通过","2":"审核不通过"};
	var videoTypeObj = {"1":"教学视频","2":"新闻视频"};
	var statusObj = {"0":"未发布","1":"已发布"};
	var isTaskObj = {"0":"否","1":"是"};
	var visitPath = '<%=Global.getConfig("prefix.visit.path")%>';
	var dataTable;
	var signature="";

	$(function() {
		initSelect();
        initTableData();
        (function() {
            /*计算签名。 https://s1.caochangapp.cn/user/getSignature  */
            function getSignature() {
                return axios.post("${contextPath}/user/getSignature").then(function(response) {
                    console.log(response.data.result);
                    return response.data.result
                })
            };

            var app = new Vue({
                el: '#mainArea',
                data: {
                    uploaderInfos: [],
                    vcExampleVideoName: '',
                    vcExampleCoverName: '',
                    cExampleFileId: '',
                },
                created: function() {
                    this.tcVod = new TcVod.default({
                        getSignature: getSignature
                    })
                },
                methods: {
                    /*vExample示例。添加视频*/
                    vExampleAdd: function() {
                        this.$refs.vExampleFile.click()
                    },
                    /*vExample示例。上传视频过程。*/
                    vExampleUpload: function() {
                        var self = this;
                        var mediaFile = this.$refs.vExampleFile.files[0]
                        var uploader = this.tcVod.upload({
                            mediaFile: mediaFile,
                        })
                        uploader.on('media_progress', function(info) {
                            uploaderInfo.progress = info.percent;
                        })
                        uploader.on('media_upload', function(info) {
                            uploaderInfo.isVideoUploadSuccess = true;
                        })
                        console.log(uploader, 'uploader')
                        var uploaderInfo = {
                            videoInfo: uploader.videoInfo,
                            isVideoUploadSuccess: false,
                            isVideoUploadCancel: false,
                            progress: 0,
                            fileId: '',
                            videoUrl: '',
                            cancel: function() {
                                uploaderInfo.isVideoUploadCancel = true;
                                uploader.cancel()
                            },
                        }
                        this.uploaderInfos=[];
                        this.uploaderInfos.push(uploaderInfo)
                        uploader.done().then(function(doneResult) {
                            console.log('doneResult', doneResult)
                            uploaderInfo.fileId = doneResult.fileId;
                            return doneResult.video.url;
                        }).then(function(videoUrl) {
                            console.log(uploaderInfo);
                            uploaderInfo.videoUrl = videoUrl
                            self.$refs.vExample.reset();
                        })
                    },
                    setVcExampleVideoName: function() {
                        this.vcExampleVideoName = this.$refs.vcExampleVideo.files[0].name;
                    },
                    setVcExampleCoverName: function() {
                        this.vcExampleCoverName = this.$refs.vcExampleCover.files[0].name;
                    },
                    /*vcExample上传过程*/
                    vcExampleAddUpload: function() {
                        var self = this;
                        var mediaFile = this.$refs.vcExampleVideo.files[0];
                        var coverFile = this.$refs.vcExampleCover.files[0];
                        var uploader = this.tcVod.upload({
                            mediaFile: mediaFile,
                            coverFile: coverFile,
                        })
                        uploader.on('media_progress', function(info) {
                            uploaderInfo.progress = info.percent;
                        })
                        uploader.on('media_upload', function(info) {
                            uploaderInfo.isVideoUploadSuccess = true;
                        })
                        uploader.on('cover_progress', function(info) {
                            uploaderInfo.coverProgress = info.percent;
                        })
                        uploader.on('cover_upload', function(info) {
                            uploaderInfo.isCoverUploadSuccess = true;
                        })
                        console.log(uploader, 'uploader')
                        var uploaderInfo = {
                            videoInfo: uploader.videoInfo,
                            coverInfo: uploader.coverInfo,
                            isVideoUploadSuccess: false,
                            isVideoUploadCancel: false,
                            isCoverUploadSuccess: false,
                            progress: 0,
                            coverProgress: 0,
                            fileId: '',
                            videoUrl: '',
                            coverUrl: '',
                            cancel: function() {
                                uploaderInfo.isVideoUploadCancel = true;
                                uploader.cancel()
                            },
                        }
                        this.uploaderInfos=[];
                        this.uploaderInfos.push(uploaderInfo)
                        uploader.done().then(function(doneResult) {
                            console.log('doneResult', doneResult)
                            uploaderInfo.fileId = doneResult.fileId;
                            uploaderInfo.coverUrl = doneResult.cover.url;
                            return doneResult.video.url;
                        }).then(function(videoUrl) {
                            uploaderInfo.videoUrl = videoUrl
                            self.$refs.vcExample.reset();
                            self.vcExampleVideoName = ''
                            self.vcExampleCoverName = ''
                        })
                    }
                },
            })
        })();

	});

    <%--function getSignature() {--%>
        <%--return $.ajax({--%>
            <%--url: "${contextPath}/user/getSignature",--%>
            <%--async: true, type: "Post", dataType: "json",--%>
            <%--data: {},--%>
            <%--success: function (response) {--%>
                <%--if(response.success){--%>
                    <%--return response.result;--%>
                <%--}else{--%>
                <%--}--%>
            <%--},--%>
            <%--error: function (XMLHttpRequest, textStatus, errorThrown) {--%>
            <%--}--%>
        <%--});--%>
    <%--}--%>

	function initSelect(){
	    var allstr = '<option value="">请选择</option>';
        var str = "";
        $("#checkStatus_q").empty();
        $.each(checkStatusObj,function(key,val){
            str += '<option value="'+key+'">'+val+'</option>';
        });
        $("#checkStatus_q").append(allstr + str);
        $("#checkStatus_q").val("");
        str = "";
        $("#videoType_q").empty();
        $("#videoType_info").empty();
        $.each(videoTypeObj,function(key,val){
            str += '<option value="'+key+'">'+val+'</option>';
        });
        $("#videoType_q").append(allstr + str);
        $("#videoType_q").val("");
        $("#videoType_info").append(str);
        $("#videoType_info").val(1);
        str = "";
        $("#isTask_q").empty();
        $("#isTask_info").empty();
        $.each(isTaskObj,function(key,val){
            str += '<option value="'+key+'">'+val+'</option>';
        });
        $("#isTask_q").append(allstr + str);
        $("#isTask_q").val("");
        $("#isTask_info").append(str);
        $("#isTask_info").val(0);

        str = "";
        $("#status_q").empty();
        $.each(statusObj,function(key,val){
            str += '<option value="'+key+'">'+val+'</option>';
        });
        $("#status_q").append(allstr + str);
        $("#status_q").val("");

		var parentId = $("#parentId_q").val();
		if(parentId == '' || parentId == '0'){
			$("#formDiv2").show();
		}else{
			$("#formDiv2").hide();
		}

		guestlist("themeId_info","${contextPath}/theme/getThemeForSelect",{});
		guestlist("operUserId_info","${contextPath}/app/getAppUserListForSelect",{});
	}

    function getObjValByKey(obj,key){
        var val = obj[key];
        return val == null || val == undefined ? "" : val;
    }

    function videoTypeChg(){
        var videoType = $("#videoType_info").val();
        if(videoType == 1){
			$("#isTaskDiv").show();
		}else{
            $("#isTaskDiv").hide();
		}
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
                "url": "${contextPath}/video/getTeachingVideoTable",
                "type": "post",
                "data": function (d) {
                	var parentId = $("#parentId_q").val();
                	if(parentId == ''){
                		parentId = '0';
					}
                    var param = {
						teachingName : $("#teachingName_q").val(),
						status : $("#status_q").val(),
                        startDate : $("#startDate_q").val(),
                        endDate : $("#endDate_q").val(),
                        checkStatus : $("#checkStatus_q").val(),
                        isTask : $("#isTask_q").val(),
						parentId : parentId,
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
                    var str = '';
                    <fus:hasRight url="/video/updateTeachingVideoInfo">
                    if(e.status == 0){
                        str += '<a style="margin-left:10px;" onclick="showVideoInfo(' + e.id + ')">编辑</a>';
                    }
                    </fus:hasRight>
                    <fus:hasRight url="/video/updateTeachingVideoForDel">
                    if(e.status == 1 && e.checkStatus == 1){
                        str += '<a style="margin-left:10px;" onclick="delConfirm(' + e.id + ',0,'+e.videoType+')">取消发布</a>';
                    }else if(e.status == 0 && e.checkStatus == 1){
                        str += '<a style="margin-left:10px;" onclick="delConfirm(' + e.id + ',1,'+e.videoType+')">发布</a>';
                    }
                    </fus:hasRight>

                    <fus:hasRight url="/video/updateTeachingVideoForCheck">
                    if(e.checkStatus == 0){
                        str += '<a style="margin-left:10px;" onclick="openUpdateCheck(' + e.id + ',' + e.sort + ','+e.videoType+')">审核</a>';
                    }
                    </fus:hasRight>

					if(e.parentId == 0 && e.videoType == 1){
						str += '<a style="margin-left:10px;" href="${contextPath}/video/toTeachingVideo?parentId=' + e.id + '" >分解视频</a>';
					}
                    return str;
                },
                    title:'操作',
                    bSortable:false
                },
                {data: "teachingUrl",bSearchable:false,title:'视频',bSortable:false,render:function(data){
                    if(data == null || data == ''){
                        return '';
                    }else{
                        return '<a style="margin-left:10px;" onclick="playVideo(\''+data+'\')">播放</a>';;
                    };
                }},
                {data: "id",bSearchable:false,title:'视频ID',width:"80px",defaultContent:""},
                {data: "teachingName",bSearchable:true,title:'视频名称',width:"100px",defaultContent:""},
                {data: "videoType",bSearchable:false,title:'视频类型',width:"80px",render:function(data){
                    return getObjValByKey(videoTypeObj,data);
                }},
                {data: "parentId",bSearchable:true,title:'父级视频',width:"50px",defaultContent:""},
                {data: "isTask",bSearchable:false,title:'是否打卡',width:"50px",bSortable:false,render:function(data){
                    return getObjValByKey(isTaskObj,data);
                }},
                {data: "teachingUrl",bSearchable:false,title:'视频url',width:"150px",defaultContent:"",render:function(data){
                    if(data == null || data == ""){
                        return "";
					}
					return data;
				}},
                {data: "checkStatus",bSearchable:false,title:'审核状态',width:"80px",bSortable:false,render:function(data){
                    return getObjValByKey(checkStatusObj,data);
                }},
                {data: "checkUserName",bSearchable:false,title:'审核人',width:"80px",defaultContent:"-"},
                {data: "sort",bSearchable:false,title:'权重',width:"50px",defaultContent:"-"},
                {data: "insertUserName",bSearchable:false,title:'添加人',width:"80px",defaultContent:"-"},
                {data: "inserttime",bSearchable:false,title:'添加时间',width:"80px",defaultContent:""}

            ],
            "order": [[ 2, "desc" ]]
        });
	}

	function getParamAndCheck(operTag){
	    var teachingName = $("#teachingName_info").val();
        var videoType = $("#videoType_info").val();
        var videoTag = $("#videoTag_info").val();
        var sort = $("#sort_info").val();
        var teachingUrl = $("#videoUrlspan").text();
        var fileId = $("#fileIdspan").text();
        var coverUrl = $("#coverUrl_info").val();
        var parentId = $("#parentId_info").val();
        var isTask = $("#isTask_info").val();
        var themeId = $("#themeId_info").val();
        var operUserId = $("#operUserId_info").val();
        var teachingDisc = $("#teachingDisc_info").val();
		if(videoType == ""){
		    uwayWarning("提醒","请选择视频类型");
		    return null;
		}
        if((teachingUrl == "" && operTag == "add") || (teachingUrl == "" && $("#videoUrlShow").text() == "")){
            uwayWarning("提醒","请上传视频");
            return null;
        }

        if(teachingUrl == "" && operTag == "modify"){
            teachingUrl = $("#videoUrlShow").text();
        }

        if(coverUrl == ""){
            uwayWarning("提醒","请上传图片");
            return null;
        }
        if(videoType == 1 && parentId == ""){
            parentId = 0;
		}
		if(themeId != "" && operUserId == ""){
			uwayWarning("提醒","请选择发布人");
			return null;
		}
		if(teachingDisc == ""){
			uwayWarning("提醒","请填写导语");
			return;
		}

        var param = {
            teachingName : teachingName,
			teachingDisc : teachingDisc,
            videoType : videoType,
            videoTag : videoTag,
            sort : sort,
            teachingUrl : teachingUrl,
            coverUrl : coverUrl,
            isTask : isTask,
            parentId : parentId,
            fileId:fileId,
			themeId : themeId,
			operUserId : operUserId
		};
        return param;
	}

	<fus:hasRight url="/video/addTeachingVideo">
	function showInfoModal(){
		$("#videoUrlspan").text("");
		$("#videoUrlShow").html("");
		$("#teachingName_info").val("");
		$("#teachingDisc_info").val("");
		$("#videoType_info").val(1);
		$("#videoTag_info").val("");
        $("#sort_info").val(0);
		$("#teachingUrl_info").val("");
        $("#coverUrl_info").val("");
        var parentId = $("#parentId_q").val();
        if(parentId == '' || parentId == '0'){
			parentId = "0";
			$("#videoType_info").removeAttr("disabled");
		}else{
			$("#videoType_info").val(1);
			$("#videoType_info").attr("disabled","true");
		}
		$('#themeId_info').selectpicker('deselectAll');
		$('#operUserId_info').selectpicker('deselectAll');
        $("#parentId_info").val(parentId);
        $("#isTask_info").val(0);
        videoTypeChg();
        $("#myfile1").val("");
        $("#myfile").val("");
        $("#imgPre").hide();
		$("#id_info").val("");
        $("#addBtn").show();
		$("#modBtn").hide();
		$("#videoUrlDiv").hide();
	    $("#infoModal").modal("show");
	}

    function addVideoInfo(){
	    var param = getParamAndCheck("add");
	    if (param == null){
	        return;
		}
        $.uwayProcess.show();
        $.ajax({
            url: "${contextPath}/video/addTeachingVideo",
            async: true, type: "Post", dataType: "json",
            data: {condition : JSON.stringify(param)},
            success: function (response) {
                $.uwayProcess.close();
                if(response.success){
                    dataTable.draw(false);
                    uwayNotice('通知',"操作成功");
                    $("#infoModal").modal("hide");
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

	<fus:hasRight url="/video/updateTeachingVideoInfo">
    function showVideoInfo(id){
        $.uwayProcess.show();
        $.ajax({
            url: "${contextPath}/video/getTeachingVideoById",
            async: true, type: "Post", dataType: "json",
            data: {id : id},
            success: function (response) {
                $.uwayProcess.close();
                if(response.success){
                    var info = response.result;
                    console.log(info);
                    $("#teachingName_info").val(info.teachingName);
                    $("#teachingDisc_info").val(info.teachingDisc);
                    $("#videoType_info").val(info.videoType);
                    $("#videoTag_info").val(info.videoTag);
                    $("#sort_info").val(info.sort);
                    $("#videoUrlspan").html(info.teachingUrl);
                    var coverUrl = info.coverUrl;
                    $("#coverUrl_info").val(coverUrl);
					$("#videoUrlShow").html(info.teachingUrl);
                    $("#videoUrlDiv").show();
                    $("#parentId_info").val(info.parentId);
                    $("#isTask_info").val(info.isTask);
                    videoTypeChg();
					var themeId = info.themeId;
					if (themeId != null){
						$('#themeId_info').selectpicker('val', themeId);
					}
					var userId = info.operUserId;
					if (userId != null){
						$('#operUserId_info').selectpicker('val', userId);
					}

                    $("#imgPre").attr("src",visitPath + coverUrl);
                    $("#imgPre").show();
					$("#myfile1").val("");
					$("#myfile").val("");
                    $("#id_info").val(info.id);
                    $("#addBtn").hide();
                    $("#modBtn").show();
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

    function updateVideoInfo(id){
        var param = getParamAndCheck("modify");
        if (param == null){
            return;
        }
        param.id = $("#id_info").val();
        $.uwayProcess.show();
        $.ajax({
            url: "${contextPath}/video/updateTeachingVideoInfo",
            async: true, type: "Post", dataType: "json",
            data: {condition : JSON.stringify(param)},
            success: function (response) {
                $.uwayProcess.close();
                if(response.success){
                    dataTable.draw(false);
                    uwayNotice('通知',"操作成功");
                    $("#infoModal").modal("hide");
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


    <fus:hasRight url="/video/updateTeachingVideoForDel">
	function delConfirm(id,status,videoType){
	    var title = "";
	    if (status == 0){
	        title = "是否确认取消发布此视频？"
        }else if (status == 1){
            title = "是否确认发布此视频？"
        }
	    uwayConfirm({
			content:title,
			confirm:function(){
                delTeachingVideo(id,status,videoType);
			},
			cancel:function(){

			}
		});
	}

	function delTeachingVideo(id,status,videoType){
        $.uwayProcess.show();
        $.ajax({
            url: "${contextPath}/video/updateTeachingVideoForDel",
            async: true, type: "Post", dataType: "json",
            data: {id:id,status:status},
            success: function (response) {
                $.uwayProcess.close();
                if(response.success){
                    dataTable.draw(false);
                    uwayNotice('通知',"操作成功");
                    if(videoType == 2 && status == 1){
                    	confirmAddAdv(id);
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
	</fus:hasRight>

	<fus:hasRight url="/video/updateTeachingVideoForCheck">
    function openUpdateCheck(id,sort,videoType){
        $("#checkStatus_chk").val(1);
	    if (sort != undefined){
            $("#sort_chk").val(sort);
		}else{
            $("#sort_chk").val(0);
		}
		$("#checkReason_chk").val("");
		$("#id_chk").val(id);
		$("#videoType_chk").val(videoType);
		$("#checkModal").modal("show");
    }

    function checkConfirm(){
        var checkStatus = $("#checkStatus_chk").val();
        var checkReason = $("#checkReason_chk").val();
        var title = "是否确认";
		if(checkStatus == 1){
			title += "审核通过？";
		}else if(checkStatus == 2){
			title += "驳回？";
			if(checkReason == ""){
                uwayWarning("警告","请填写驳回原因");
                return;
			}
		}else{
			uwayWarning("警告","请选择审核状态");
			return;
		}

        uwayConfirm({
            content:title,
            confirm:function(){
                var sort = $("#sort_chk").val();
				var videoType = $("#videoType_chk").val();
                var param = {id:$("#id_chk").val(),sort : sort,checkStatus : checkStatus,checkReason:checkReason};
                checkTeachingVideo(param,videoType);
            },
            cancel:function(){

            }
        });
    }

    function checkTeachingVideo(param,videoType){
    	var checkStatus = param.checkStatus;
    	var id = param.id;
        $.uwayProcess.show();
        $.ajax({
            url: "${contextPath}/video/updateTeachingVideoForCheck",
            async: true, type: "Post", dataType: "json",
            data: param,
            success: function (response) {
                $.uwayProcess.close();
                if(response.success){
                    dataTable.draw(false);
                    uwayNotice('通知',"操作成功");
                    $("#checkModal").modal("hide");
                    if(videoType == 2 && checkStatus == 1){
						confirmAddAdv(id);
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
	</fus:hasRight>

	function confirmAddAdv(id){
		uwayConfirm({
			content:"是否将该视频加入到精选新闻中？",
			confirm:function(){
				addAdvByVideo(id);
			},
			cancel:function(){

			}
		});
	}

	function addAdvByVideo(id){
		$.uwayProcess.show();
		$.ajax({
			url: "${contextPath}/ad/addAdInfoByTeaching",
			async: true, type: "Post", dataType: "json",
			data: {id : id},
			success: function (response) {
				$.uwayProcess.close();
				if(response.success){
					uwayNotice('通知',"操作成功");
					window.location.href = "${contextPath}/ad/toAdManage";
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

	function playVideo(url){
		$("#videoDiv").empty();
		var str = '<video style="max-width:500px;max-height:400px;" controls="" autoplay="" name="media">' +
            '<source id="videoShow" src="'  + url + '" type="video/mp4">' +
            '</video>';
        $("#videoDiv").append(str);
	    $("#videoModal").modal({
			backdrop: false,//点击空白处不关闭对话框
            keyboard: false,//键盘关闭对话框
            show:true//弹出对话框
		});
	}
    function closeVideo(){
        $("#videoDiv").empty();
        $("#videoModal").modal("hide");
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
					var html = '<option value="">请选择</option>';;
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



    //文件上传
    function upload(id,inputId) {
        $.uwayProcess.show();
        var t = setInterval(function() {
            //获取iframe标签里body元素里的文字。即服务器响应过来的"上传成功"或"上传失败"
            var word = $("iframe[id='"+id+"']").contents().find("body").text();
            var result;
			try {
				result = JSON.parse(word);
			} catch (e){
                console.log('等待上传请求返回信息......');
				return;
			}
			if (result.success == true) {
				$.uwayProcess.close();
				var fName = result.result;
				$("#" + inputId).val(fName)
				uwayNotice("通知", "上传成功！");
				clearInterval(t);
			} else {
				$.uwayProcess.close();
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
    function preImg(sourceId, targetId,inputId,frameId) {
        var url = getFileUrl(sourceId);
        if(targetId != null && targetId != undefined){
            $("#"+targetId).show();
            $("#"+targetId).attr("src",url);
		}
        //每次更换时都将路径清空，在上传后填写
		if(inputId != null && inputId != undefined){
            $("#" + inputId).val("");
		}
		if(frameId != null && frameId != undefined){
            $("iframe[id='"+frameId+"']").contents().find("body").html("");
		}

    }

</script>

</body>
</html>