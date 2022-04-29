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
			.form-group label{
				width:130px;
				text-align:right;
			}
			.form-group select{
				height:29px;
				width: 170px;
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
						<li>内容管理</li><li>新闻管理</li>
					</ol>
				</div>
			</div>
			<div id="content">
				<div class="condition-wrapper">
					<div style="display:none;">
						<input type="text" id="openOptionType" value="${queryType}" />
					</div>
					<div class="divform">
						<label for="newsTitle_q">标题</label>
						<input id="newsTitle_q" type="text" placeholder="请输入标题"/>
					</div>
					<div class="divform">
						<label for="status_q">状态</label>
						<select id="status_q" class="select-query">

						</select>
					</div>

					<div class="divform">
						<button class="btn main-color-bluegreen" onclick="query()">查询</button>
						<fus:hasRight url="/news/addSportsNews">
							<button class="btn main-color-bluegreen" onclick="addInfoModel()">新增新闻</button>
						</fus:hasRight>
					</div>
				</div>
				<div id="tableRow" class="row" style="min-width:1000px;margin-top: 10px !important;">
					<table id="dataTable" class="table table-striped table-bordered">

					</table>
				</div>
			</div>

		</div>

		<div class="page-footer">

		</div>

		<div class="modal fade" id="newsShowModal" tabindex="-1" role="dialog" aria-labelledby="newsShowModalTitle" aria-hidden="false">
			<div class="modal-dialog" style="min-width:1000px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="closeShowModal();">
							&times;
						</button>
						<h4 class="modal-title">预览</h4>
					</div>
					<div class="modal-body">
						<div style="width: 100%;margin: 0 auto;">
							<iframe id="newsShowframe" style="width:100%;min-height: 800px;"></iframe>
						</div>
					</div>
					<div class="modal-footer">
						<button class="btn main-color-bluegreen" onclick="closeShowModal()">关闭</button>
					</div>
				</div>
			</div>
		</div>


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

<script type="text/javascript">
	var statusObj = {"0":"未发布","1":"已发布"};
	var dataTable;
	$(function() {
		initSelect();
        initTableData();
	});

	function initSelect(){
	    var allstr = '<option value="">请选择</option>';
		var str = "";
        $("#status_q").empty();
        $.each(statusObj,function(key,val){
            str += '<option value="'+key+'">'+val+'</option>';
        });
        $("#status_q").append(allstr + str);
        $("#status_q").val("");
	}

    function getObjValByKey(obj,key){
        var val = obj[key];
        return val == null || val == undefined ? "" : val;
    }

	<fus:hasRight url="/news/addSportsNews">
	function addInfoModel(){
		window.location.href = '${contextPath}/news/toSportsNews?operType=add';
	}
	</fus:hasRight>

    function openViewModel(id){
        $.ajax({
            url: "${contextPath}/news/getSportsNewsById",
            async: true, type: "Post", dataType: "json",
            data: {"id":id},
            success: function (response) {
                $.uwayProcess.close();
                if(response.success){
                    var result = response.result;
					var newsUrl;
					if(result.newsUrl.indexOf("/news/getNewsDetails") != -1){
						newsUrl = result.newsUrl + "?id=" + result.id + "&nowtime=" + new Date().getTime();
					}else{
						newsUrl = result.newsUrl;
					}
					$("#newsShowframe").attr("src",newsUrl);
					$("#newsShowModal").modal({
						backdrop: 'static',//点击空白处不关闭对话框
						keyboard: false,//键盘关闭对话框
						show:true//弹出对话框
					});
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
	function openEditorModel(id){
		// 修改时，在缓存中记录当前查询条件，方便返回时加载
		var pageNum = dataTable.page();   // 当前页码
		var order = dataTable.order();    // 当前排序
		// 查询条件
		var historyParam = queryParam();
		historyParam["pageNum"] = pageNum;
		historyParam["order"] = order;
		localStorage.setItem('historyParam_news', JSON.stringify(historyParam));
		window.location.href = '${contextPath}/news/toSportsNews?operType=editor&id=' + id;
	}
    </fus:hasRight>

	function queryParam(){
		var param = {
			newsTitle : $("#newsTitle_q").val(),
			status : $("#status_q").val()
		};
		return param;
	}

    //根据条件进行查询
    function query() {
        dataTable.ajax.reload();
    }

	function initTableData(){
		var openOptionType = $("#openOptionType").val();
		var historyPage = 0;
		var historyOrder = [[ 1, "desc" ]];
		var itemValue = localStorage.getItem('historyParam_news');
		if(openOptionType == "historyParam_news" && itemValue != undefined && itemValue != null && itemValue != ''){
			var historyParam = JSON.parse(itemValue);
			$("#newsTitle_q").val(historyParam.newsTitle);
			$("#status_q").val(historyParam.status);
			historyOrder = historyParam.order;
			historyPage = historyParam.pageNum;
			localStorage.removeItem("historyParam")
		}
		var startRow = historyPage * 10;
        dataTable = $('#dataTable').DataTable({
            "scrollX": true,
            "processing": true,
            "serverSide": true,
			"displayStart": startRow,
			"order": historyOrder,
            "ajax": {
                "url": "${contextPath}/news/getSportsNewsTable",
                "type": "post",
                "data": function (d) {
                    var param = queryParam();
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
                    var str = '<a style="margin-left:10px;" onclick="openViewModel('+ "'"+e.id+"',\'show\'" +')">预览</a>';
                    <fus:hasRight url="/news/updateSportsNews">
                    str += '<a style="margin-left:10px;" onclick="openEditorModel('+ "'"+e.id+"'" +')">编辑</a>';
                    </fus:hasRight>
                    <fus:hasRight url="/news/updateSportsNewsStatus">
                    if(e.status == 1){
                        str += '<a style="margin-left:10px;" onclick="openUpdateStatus('+ "'"+e.id+"','取消发布'" +',0)">取消发布</a>';
                    }else if(e.status == 0){
                        str += '<a style="margin-left:10px;" onclick="openUpdateStatus('+ "'"+e.id+"','发布'" +',1)">发布</a>';
                    }else{
                        str += '';
                    }
                    </fus:hasRight>
                    return str;
                },
                    title:'操作',
                    bSortable:false
                },
                {data: "id",bSearchable:false,title:'新闻ID',width:"100px",defaultContent:""},
                {data: "newsTitle",bSearchable:true,title:'新闻标题',width:"200px",defaultContent:""},
                {data: "subNewsTitle",bSearchable:true,title:'副标题',width:"200px",defaultContent:""},
                {data: "newsAuthor",bSearchable:true,title:'来源',width:"150px",defaultContent:""},
//                {data: "newsType",bSearchable:false,title:'类型',width:"100px",render:function(data){
//                    return getObjValByKey(newsTypeObj,data);
//                }},
				{title:"新闻地址",bSearchable:false,width:"200px",bSortable:false,data:function(e){
				    if(e.newsUrl.indexOf("/news/getNewsDetails") != -1){
						return e.newsUrl + "?id=" + e.id;
					}else{
				        return e.newsUrl;
					}
				}},
                {data: "readCnt",bSearchable:false,title:'阅读量',width:"50px",defaultContent:""},
                {data: "sort",bSearchable:false,title:'权重',width:"50px",defaultContent:""},
                {data: "optUserName",bSearchable:false,title:'操作人',width:"80px",bSortable:false,defaultContent:""},
                {data: "status",bSearchable:false,title:'状态',width:"80px",render:function(data){
                    return getObjValByKey(statusObj,data);
                }},
                {data: "inserttime",bSearchable:false,title:'创建时间',width:"100px",defaultContent:""}

            ],
            "order": [[ 1, "desc" ]]
        });
	}

    <fus:hasRight url="/news/updateSportsNewsStatus">
	function openUpdateStatus(id,title,status){
	    var title = "是否确认" + title;
	    uwayConfirm({
			content:title,
			confirm:function(){
                updateSportsNewsStatus(id,status);
			},
			cancel:function(){

			}
		});
	}

	function updateSportsNewsStatus(id,status){
        $.uwayProcess.show();
        $.ajax({
            url: "${contextPath}/news/updateSportsNewsStatus",
            async: true, type: "Post", dataType: "json",
            data: {"id":id,"status":status},
            success: function (response) {
                $.uwayProcess.close();
                if(response.success){
                    $("#infoModal").modal("hide");
                    dataTable.draw(false);
                    uwayNotice('通知',"操作成功");
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

	function closeShowModal(){
		$("#newsShowframe").empty();
        $('#newsShowModal').modal('hide');
	}
</script>
</body>
</html>