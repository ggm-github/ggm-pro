<%@ taglib prefix="fus" uri="http://java.sun.com/jsp/jstl/funs" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="background:url(${ctxStatic}/img/sysBg.png)">
	<head>
		<meta charset="utf-8">
		<title>栏目内容管理</title>
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
            #cyclePlayTable td{
                white-space: nowrap;
                max-width:400px !important;
                min-width:120px;
                overflow-x:hidden;
                text-overflow: ellipsis;
            }
            #cyclePlayTable {
                table-layout: fixed;
            }
            .form-group label{
                width:130px;
                text-align:right;
            }
            .select-query,.input-length{
                height: 30px;
                width: 150px;
            }
            .input-length-big{
                height: 30px;
                width: 260px;
            }

            .note-info{
                color: red;
                width: 60%;
                text-align: left;
                font-size: 12px;
                display: inline-block;
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
						<li>内容管理</li><li>栏目内容管理</li>
					</ol>
				</div>			
			</div>
            <div id="content">
                <!-- 查询条件DIV -->
                <div style="margin-top: 0px;margin-bottom: 10px;" >
                    <form id="logQuery" name="logQuery" style="min-width:1000px;" >
                        <div style="display: inline-block;">
                            <label style="margin-left: 10px;">类型：</label>
                            <select id="referenceType" name="referenceType" class="select-query">

                            </select>
                        </div>

                        <div style="display: inline-block;">
                            <label style="margin-left: 10px">名称：</label>
                            <input id="referenceName" name="referenceName" class="input-length" placeholder="请输入要查询的名称" />
                        </div>
                        <div style="display: inline-block;">
                            <label style="margin-left: 10px">标签：</label>
                            <select id="tag" name="tag" class="select-query">

                            </select>
                        </div>
                        <label style="display: inline-block;">
                            <label style="margin-left: 10px">状态：</label>
                            <select id="status" name="status" class="select-query">

                            </select>
                            <label class="btn main-color-bluegreen" style="margin-left: 15px; width: 80px" onclick="query()"> 查 询</label>
                            <fus:hasRight url="/cyclePlayer/addCyclePlayer">
                                <label class="btn main-color-bluegreen" style="margin-left: 15px; width: 80px" onclick="toAddPage()">新 增</label>
                            </fus:hasRight>
                            <%--<fus:hasRight url="/cyclePlayer/updateStatus">--%>
                                <%--<label class="btn main-color-bluegreen" style="margin-left: 15px; width: 80px;" onclick="statusOperMultiple(0)">批量下架</label>--%>
                                <%--<label class="btn main-color-bluegreen" style="margin-left: 15px; width: 80px;" onclick="statusOperMultiple(1)">批量上架</label>--%>
                            <%--</fus:hasRight>--%>
                        </label>
                    </form>
                </div>

                <div id="tableRow" class="row" style="min-width: 1000px;">
                    <table id="cyclePlayTable" class="table table-striped table-bordered">

                    </table>
                </div>

                <div style="display:none">
                    <input type="hidden" id="openOptionType" value="${openOptionType}"/>
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
                    <div class="modal-body">
                        <div style="width: 100%;max-height:400px;margin: 0 auto;overflow-y: auto;">
                            <div style="display:none;">
                                <input id="id_info" type="text" class="emptyInfo"/>
                            </div>

                            <div class="form-group">
                                <label>栏目类型：</label>
                                <select id="referenceType_info" name="referenceType" class="select-query" onchange="referenceTypeChange();" >

                                </select>
                            </div>

                            <div class="form-group">
                                <label>展示位置：</label>
                                <input id="showSort_info" class="input-length emptyInfo" maxlength="2"/>
                            </div>
                            <div class="form-group">
                                <label></label>
                                <span class="note-info">当前记录展示位置，两位以内正整数，从小到大排列，同一置只取一条，由展示级别和权重、录入先后决定</span>
                            </div>
                            <div class="form-group index-4-div">
                                <label>展示内容：</label>
                                <input id="showIndex_info" class="input-length emptyInfo" maxlength="2"/>
                            </div>
                            <div class="form-group index-4-div">
                                <label></label>
                                <span class="note-info">1-推荐，查询推进动态；2-健康视频，查询教学动态；3-热门活动，查询活动列表；4-热门话题，查询话题列表</span>
                            </div>
                            <div class="form-group">
                                <label>名称：</label>
                                <input id="referenceName_info" name="referenceName" class="input-length emptyInfo" placeholder="请输入名称" />
                            </div>
                            <div class="form-group">
                                <label>描述：</label>
                                <input id="referenceDesc_info" name="referenceDesc" class="input-length emptyInfo" placeholder="请输入描述" />
                            </div>
                            <div class="form-group">
                                <label>展示范围：</label>
                                <select id="showCityChk_info" class="select-query" onchange="changeShowCity();">

                                </select>
                                <input type="text" id="showCityName_info" class="emptyInfo input-length" style="display:none;" onfocus="showTree('1','showCity_info','showCityName_info');" />
                                <input type="hidden" id="showCity_info" class="emptyInfo" />
                            </div>
                            <div class="form-group">
                                <label></label>
                                <span class="note-info">同一位置取值顺序，全局-本省市-全国</span>
                            </div>
                            <div class="form-group">
                                <label>权重：</label>
                                <input id="sort_info" name="sort" class="input-length" placeholder="请输入整数，数值大的排在前面" value="0" />
                            </div>
                            <div class="form-group">
                                <label>操作：</label>
                                <select id="operTag_info" name="operTag" class="select-query" onchange="operTagChg();">

                                </select>
                            </div>
                            <div class="form-group" style="display:none;">
                                <label>分享：</label>
                                <select id="operStatus_info" class="select-query" name="operStatus">

                                </select>
                            </div>

                            <div class="form-group shareTitle" style="display:none;">
                                <label>分享标题：</label>
                                <input id="shareFirstTitle_info" type="text" class="input-length-big emptyInfo"/>
                            </div>
                            <div class="form-group shareTitle" style="display:none;">
                                <label>分享副标题：</label>
                                <input id="shareSecondTitle_info" type="text" class="input-length-big emptyInfo" />
                            </div>

                            <div class="form-group">
                                <label>标签：</label>
                                <select id="tag_info" name="tag" class="select-query">

                                </select>
                            </div>
                            <div class="form-group">
                                <label id="referenceUrlName">地址：</label>
                                <input id="referenceUrl_info" name="referenceUrl" style="width:260px;" class="input-length-big emptyInfo"  placeholder="访问地址" />
                            </div>
                            <div class="form-group">
                                <label></label>
                                <span id="urlInfo_0" class="note-info" style="display:none;">地址跳转，若是操场新闻，则从【新闻管理】列表的新闻地址中粘取，若是广告，则填入第三方提供的广告地址</span>
                                <span id="urlInfo_1" class="note-info" style="display:none;">原生跳转，需要填入赛事ID，从【赛事管理】列表的赛事ID中粘取</span>
                                <span id="urlInfo_2" class="note-info" style="display:none;">原生跳转，需要填入教学视频ID，从【视频管理】列表的视频ID中粘取</span>
                                <span id="urlInfo_5" class="note-info" style="display:none;">原生跳转，需要填入队伍视频ID，从【队伍视频】列表的视频ID中粘取</span>
                                <span id="urlInfo_7" class="note-info" style="display:none;">原生跳转，需要填入队伍ID，从【队伍管理】列表的队伍ID中粘取</span>
                                <span id="urlInfo_10" class="note-info" style="display:none;">原生跳转，需要填入要播放的视频地址</span>
                            </div>

                            <div style="display:none;">
                                <input type="hidden" id="picUrl_info" class="emptyInfo" />
                            </div>
                            <div class="form-group">
                                <form method="post" action="/comm/upload"  enctype="multipart/form-data"  target="uploadframe">
                                    <label style="">图片选择：</label>
                                    <label style="width:210px;">
                                        <input style="width: 200px" type="file" name="myfile" id="myfile" value="" class="emptyInfo"  onchange="preImg(this.id,'imgShow');" />
                                        <input type="hidden" name="path" value="prefix.upload.path.cycleplayer"/>
                                    </label>
                                    <label style="width: 8%;min-width: 55px;">
                                        <input class="btn main-color-bluegreen" type="submit" value="上传" onclick="upload()"/>
                                    </label>
                                </form>
                            </div>
                            <div class="form-group" style="text-align:center; ">
                                <img id="imgShow" src="" style="display:none;max-width:200px;"/>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <input id="addBtn" class="btn main-color-bluegreen" type="button" value="新增" onclick="addCyclePlayer();"/>
                        <input id="editBtn" class="btn main-color-bluegreen" type="button" value="修改" onclick="updateCyclePlayer();"/>
                        <button class="btn" onclick="$('#infoModal').modal('hide')">取消</button>
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

    var table;
    var typeObj = {"1":"轮播图","2":"首页广告","3":"发现顶部方块","4":"发现列表条目"};
    var statusObj = {"0":"未发布","1":"已发布"};
    var operObj = {'-1':'无','0':'链接地址', '1':'赛事详情', '2':'教学详情', '3':'新闻列表', '4':'投票列表', '5':'投票详情',
                   '6':'队伍列表', '7':'队伍详情', '8':'赛事列表', '9':'教学列表', '10':'单一视频详情','11':'精选列表',
                   "14":"话题详情","15":"话题列表","16":"内链展示"};
    var operStatusObj = {"0":"否","1":"是"};
    var showCityObj = {"-1":"全局","0":"全国","2":"本省市"};
    var tagObj = {"0":"无","1":"新闻","2":"赛事","3":"广告"};
    var areaTreeList = JSON.parse(${fns:toJson(areaTree)});

	$(document).ready(function(){
	    //初始化类型下拉
        var typeStr = '<option value="">全部</option>';
        var str = '';
	    $.each(typeObj,function(key,val){
            str += '<option value="'+key+'">'+val+'</option>';
        });
	    $("#referenceType").empty();
        $("#referenceType").append(typeStr + str);
        $("#referenceType_info").empty();
        $("#referenceType_info").append(str);
        //初始化状态下拉
        str = '';
        $.each(statusObj,function(key,val){
            str += '<option value="'+key+'">'+val+'</option>';
        });
        $("#status").empty();
        $("#status").append(typeStr + str);
        //默认查有效的
        $("#status").val(1);

        str = '';
        $.each(operObj,function(key,val){
            str += '<option value="'+key+'">'+val+'</option>';
        });
        $("#operTag_info").empty();
        $("#operTag_info").append(str);
        //默认查有效的
        $("#operTag_info").val(0);
        str = '';
        $.each(operStatusObj,function(key,val){
            str += '<option value="'+key+'">'+val+'</option>';
        });
        $("#operStatus_info").empty();
        $("#operStatus_info").append(str);
        //默认不可分享
        $("#operStatus_info").val(0);

        str = '';
        $.each(showCityObj,function(key,val){
            str += '<option value="'+key+'">'+val+'</option>';
        });
        $("#showCityChk_info").empty();
        $("#showCityChk_info").append(str);
        $("#showCityChk_info").val(-1);
        str = '';
        $.each(tagObj,function(key,val){
            str += '<option value="'+key+'">'+val+'</option>';
        });
        $("#tag").empty();
        $("#tag").append(typeStr + str);
        $("#tag_info").empty();
        $("#tag_info").append(str);
        $("#tag_info").val(0);
	    //$.uwayProcess.show();
        //初始化dataTables
        initDataTable();

        initJsTree("areaTree_div",areaTreeList);

        //全选框事件绑定
        /*$("#chb_all").click(function(){
            if($("#chb_all").is(":checked")){
                $(".chb_check").prop('checked',"checked");
            }else{
                $(".chb_check").removeAttr("checked");
            }
        });*/
    });

    function toAddPage(){

        $("#sort_info").val(0);
        $("#tag_info").val(0);
        $("#operTag_info").val(0);
        $("#operStatus_info").val(0);
        $(".emptyInfo").val("");
        $("#imgShow").hide();
        $("#showCityChk_info").val("-1");
        changeShowCity();
        $("#myfile").val("");
        $("#addBtn").show();
        $("#editBtn").hide();
        operTagChg();
        referenceTypeChange();
        $("#infoModal").modal("show");
    }
    function query(){
        table.ajax.reload();
    }

    function changeShowCity(){
        var checkTag = $("#showCityChk_info").val();
        if (checkTag == '2'){
            $("#showCityName_info").show();
        } else{
            $("#showCity_info").val(checkTag);
            $("#showCityName_info").hide();
        }
    }
    
    function referenceTypeChange(){
        var type = $("#referenceType_info").val();
        if (type == 4) {
            $(".index-4-div").show();
        }else{
            $(".index-4-div").hide();
        }
    }

    //dataTable Ajax请求
    function initDataTable(){
        table = $('#cyclePlayTable').DataTable({
            "scrollX": true,
            "processing": true,
            "serverSide": true,
            "order": [[ 8, "desc" ]],
            "ajax": {
                "url": "${contextPath}/cyclePlayer/getCyclePlayerTable",
                "type": "POST",
                "data": function (d) {
                    $.uwayProcess.show();
                    var condition = {};
                    condition.referenceType = $("#referenceType").val();
                    condition.status = $("#status").val();
                    condition.name = $("#referenceName").val();
                    condition.tag = $("#tag").val();
                    d.condition = JSON.stringify(condition);
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
//                {
//                    data: function(e){
//                        return '<input type="checkbox" class="chb_check" value="'+e.id +'" />';
//                    },
//                    bSortable: false,
//                    title: '<input type="checkbox" id="chb_all"/>',
//                    width:"50px"
//                },
                {data: function(e){
                    var operStr = '';
                    <fus:hasRight url="/cyclePlayer/updateCyclePlayer">
                        operStr += '<a href="javascript:void(0);" onclick="getCyclePlayerInfo('+e.id+')">修改</a>&nbsp;&nbsp;&nbsp;';
                    </fus:hasRight>
                    <fus:hasRight url="/cyclePlayer/updateStatus">
                        var sts = e.status;
                        if(sts == 0){
                            operStr += '<a href="javascript:void(0);" onclick="statusOperOne(1,\''+e.id+'\');">发布</a>';
                        }else if(sts == 1){
                            operStr += '<a href="javascript:void(0);" onclick="statusOperOne(0,\''+e.id+'\');">取消发布</a>';
                        }
                    </fus:hasRight>
                    return operStr;
                },
                    title:'操作',
                    bSortable:false
                },
                {data: "name",bSearchable:false,width:"300px",title:'名称',render:function(data){
                    if(data != null && data.length > 30){
                        return '<p style="margin-bottom: 0px;" title="' + data + '">'+data.substr(0,26)+'...</p>';
                    }else{
                        return '<p style="margin-bottom: 0px;" title="'+data+'">'+data+'</p>';
                    }
                }},
                {data: "referenceType",bSearchable:false,title:'类型',render:function(data){
                    return typeObj[data];
                },width:"150px",defaultContent:""},
                {data: "showSort",bSearchable:false,title:'展示位置',width:"50px",defaultContent:""},
                {data: "showCityName",name:"showCity",bSearchable:false,title:'展示范围',width:"50px",defaultContent:""},
                {data: "tag",bSearchable:false,render:function(data){
                    return tagObj[data];
                 },title:'标签',width:"50px",defaultContent:""},
                {data: "status",bSearchable:false,title:'状态',render:function(data){
                    return statusObj[data];
                 },width:"80px"},
                {data: "sort",bSearchable:false,title:'权重',width:"50px",defaultContent:""},
                {data: "createTime",bSearchable:false,title:'创建时间',width:"150px",defaultContent:""}
            ]
        });
    }

    <fus:hasRight url="/cyclePlayer/updateStatus">
        //下架轮播图
        function statusOperMultiple(statusParam){
            var idArr = new Array();
            var param = {};
            var title;
            if(statusParam == 0){
                title = "确定取消发布";
            }else if(statusParam == 1){
                title = "确定发布";
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
                var referenceType = $("#referenceType").val();
                referenceType = referenceType == "" ? null:referenceType;
                var status = $("#status").val();
                status = status == "" ? null:status;
                var referenceName = $("#referenceName").val();
                if(referenceType == "" && status == "" && referenceName == ""){
                    uwayNotice("通知","请选择要操作的记录或添加对应条件");
                    return;
                }
                if(status == statusParam){
                    uwayNotice("通知","筛选数据与操作相同，无需操作，请核实");
                    return;
                }
                param["referenceType"] = referenceType;
                param["status"] = status;
                param["name"] = referenceName;
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
    </fus:hasRight>

    <fus:hasRight url="/cyclePlayer/updateStatus">
        function statusOperOne(statusParam,id){
        var content = "";
        if(statusParam == 0){
            content = '确定要下架吗？';
        }else if(statusParam == 1){
            content = '确定要上架吗？';
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
    </fus:hasRight>

    function updateStatus(data){
        console.log("data:"+JSON.stringify(data));
        $.uwayProcess.show();
        $.ajax({
            url: "${contextPath}/cyclePlayer/updateStatus",
            async: true, type: "Post", dataType: "json",
            data: data,
            success: function (response) {

                $.uwayProcess.close();
                if (response.success) {
                    uwayNotice('通知',response.result);
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
        var referenceType = $("#referenceType_info").val();
        var referenceName = $("#referenceName_info").val();
        var referenceDesc = $("#referenceDesc_info").val();
        var sort = $("#sort_info").val();
        var referenceUrl = $("#referenceUrl_info").val();
        var picUrl = $("#picUrl_info").val();
        var operTag = $("#operTag_info").val();
        var operStatus = $("#operStatus_info").val();
        var shareFirstTitle = $("#shareFirstTitle_info").val();
        var shareSecondTitle = $("#shareSecondTitle_info").val();
        var tag = $("#tag_info").val();
        var showIndex = $("#showIndex_info").val();
        var showSort = $("#showSort_info").val();
        var showCity = $("#showCity_info").val();
        if(referenceType == ""){
            uwayWarning("警告","类型不能为空");
            return null;
        }
        var regu =/^[0-9]{1,}$/;
        var re = new RegExp(regu);
        if (!re.test(sort)) {
            uwayWarning("警告","排序不能为空，且只能填写整数");
            return null;
        }
        if (!re.test(showSort) || showSort == '0') {
            uwayWarning("警告","展示位置不能为空，且只能填写大于0的整数");
            return null;
        }
        if(showCity == ''){
            uwayWarning("警告","请选择展示范围");
            return null;
        }
        if(referenceUrl == "" && operTag == 0){
            uwayWarning("警告","访问地址不能为空");
            return null;
        }
        if(picUrl == ""){
            uwayWarning("警告","请上传图片");
            return null;
        }
        if(operTag == ""){
            uwayWarning("警告","操作不能为空");
            return null;
        }
        if(operStatus == 1 && (shareFirstTitle == "" || shareSecondTitle == "")){
            uwayWarning("警告","轮播可分享，需要填写分享标题、副标题");
            return null;
        }

        //获取新增内容的基本信息
        var param = {};
        param["referenceType"] = referenceType;
        param["name"] = referenceName;
        param["referenceDesc"] = referenceDesc;
        param["sort"] = sort;
        param["referenceUrl"] = referenceUrl;
        param["picUrl"] = picUrl;
        param["operTag"] = operTag;
        param["operStatus"] = operStatus;
        param["shareFirstTitle"] = shareFirstTitle;
        param["shareSecondTitle"] = shareSecondTitle;
        param["tag"] = tag;
        param["showIndex"] = showIndex;
        param["showCity"] = showCity;
        param["showSort"] = showSort;
        return param;
    }
    //新增请求
    function addCyclePlayer(){
        var param = getParamInfo();
        if(param == null){
            return;
        }
        $.uwayProcess.show();
        $.ajax({
            url: "${contextPath}/cyclePlayer/addCyclePlayer",
            async: true, type: "Post", dataType: "json",
            data: {param:JSON.stringify(param)},
            success: function (response) {
                $.uwayProcess.close();
                if (response.success) {
                    uwayNotice('通知',"新增成功");
                    table.draw(true);
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

    //基本信息查询
    function getCyclePlayerInfo(id){
        $.uwayProcess.show();
        $.ajax({
            url: "${contextPath}/cyclePlayer/getCyclePlayerInfo",
            async: true, type: "Post", dataType: "json",
            data: {id:id},
            success: function (response) {
                if (response.success) {
                    $.uwayProcess.close();
                    var result = response.result.cyclePlayerInfo;
                    //回填值
                    $("#referenceType_info").val(result.referenceType);
                    referenceTypeChange();
                    $("#referenceName_info").val(result.name);
                    $("#referenceInfo_info").val(result.name);
                    $("#referenceDesc_info").val(result.referenceDesc);
                    $("#sort_info").val(result.sort);
                    $("#operTag_info").val(result.operTag);
                    $("#operStatus_info").val(result.operStatus);
                    $("#referenceUrl_info").val(result.referenceUrl);
                    $("#shareFirstTitle_info").val(result.shareFirstTitle);
                    $("#shareSecondTitle_info").val(result.shareSecondTitle);
                    $("#tag_info").val(result.tag);
                    $("#showIndex_info").val(result.showIndex);
                    $("#showSort_info").val(result.showSort);
                    $("#showCity_info").val(result.showCity);
                    $("#showCityName_info").val(result.showCityName);
                    if(result.showCity == -1 || result.showCity == 0){
                        $("#showCityChk_info").val(result.showCity);
                    }else{
                        $("#showCityChk_info").val(2);
                    }
                    changeShowCity();
                    operTagChg();
                    var picUrl = result.picUrl;
                    $("#picUrl_info").val(picUrl);
                    $("#id_info").val(result.id);
                    $("#imgShow").attr("src",'<%=Global.getConfig("prefix.visit.path")%>'+picUrl);
                    $("#imgShow").show();
                    $("#addBtn").hide();
                    $("#editBtn").show();
                    $("#infoModal").modal("show");
                }else{
                    console.log(response.message);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log(textStatus);
                $.uwayProcess.close();
            }
        });
    }

    //修改请求
    function updateCyclePlayer(){
        var param = getParamInfo();
        if(param == null){
            return;
        }
        var id = $("#id_info").val();
        param["id"] = id;
        $.uwayProcess.show();
        $.ajax({
            url: "${contextPath}/cyclePlayer/updateCyclePlayer",
            async: true, type: "Post", dataType: "json",
            data: {param:JSON.stringify(param)},
            success: function (response) {
                $.uwayProcess.close();
                if (response.success) {
                    uwayNotice('通知',"修改成功");
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

    function operTagChg(){
        var operTag = $("#operTag_info").val();
        $("[id^=urlInfo_]").hide();
        $("#urlInfo_" + operTag).show();
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
            }
            else {
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

    <%-- 区域选择  start --%>
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
    <%-- 区域选择  end --%>
</script>
</html>