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
            .form-control{
                 margin-bottom:10px;
            }
			#userAdd{
				float:right;
				position:relative;
				z-index:99;
				top:-64px;
				margin-right:10px;
			}
			#tableRow{
				position:relative;
				top:-30px;
			}
			#cearchul {
				display: none !important;
			}
			.dt-toolbar {
				display: none !important;
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
						<li>系统管理</li><li>后台用户管理</li>
					</ol>
				</div>
			</div>
			<div id="content">
				<fus:hasRight url="/user/addUserInfo">
					<button id="userAdd" class="btn main-color-bluegreen" onclick="addUser()">新增用户</button>
				</fus:hasRight>
				<div id="tableRow" class="row" style="min-width:1000px;">
						<table id="userTable" class="table table-striped table-bordered">

						</table>
				</div>

			</div>

		</div>

		<div class="page-footer">

		</div>


<div class="modal fade" id="updateUserPasswordModal" tabindex="-1" role="dialog" aria-labelledby="updateUserPasswordModalTitle" aria-hidden="true">
	<div class="modal-dialog" style="width:400px;">
		<div class="modal-content" id="update-user-password-form">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
				&times;
				</button>
				<h4 class="modal-title" id="updateUserPasswordModalTitle">修改密码</h4>
			</div>
			<div class="modal-body">
			      <input id="modalUserId" type="hidden" value="" />
			      <input class="form-control" type="text" disabled="disabled" id="modalUserName" value="" />
			      <input class="form-control" type="password"  placeholder="请输入6-18位字母或数字新密码"
                         onkeyup="value = value.replace(/[^a-zA-Z0-9]/g, '')"
                         onbeforepaste="clipboardData.setData('text', clipboardData.getData('text').replace(/[^a-zA-Z0-9]/g, ''))"
                         maxlength="18" id="modalNewPassword" value="" />
			      <input class="form-control" type="password"  placeholder="请输入6-18位字母或数字新密码"
                         onkeyup="value = value.replace(/[^a-zA-Z0-9]/g, '')"
                         onbeforepaste="clipboardData.setData('text', clipboardData.getData('text').replace(/[^a-zA-Z0-9]/g, ''))"
                         maxlength="18" id="modalNewPasswordConfirm" value="" />
			</div>
			<div class="modal-footer">
				<button id="resetBtn" class="btn main-color-bluegreen" onclick="updatePasswordInModal(event)">
					提交
				</button>
                <button type="button" class="btn" data-dismiss="modal">
                    取消
                </button>
			</div>
		</div>
	</div>
</div>



<script src="${ctxStatic}/js/libs/jquery-2.0.2.min.js"></script>
<script src="${ctxStatic}/js/app.config.js"></script>
<script src="${ctxStatic}/js/bootstrap/bootstrap.min.js"></script>
<script src="${ctxStatic}/js/bootstrap/bootstrap-datetimepicker.js"></script>
<script src="${ctxStatic}/js/bootstrap/bootstrap-datetimepicker.zh-CN.js"></script>
<script src="${ctxStatic}/js/plugin/jBox/jBox.min.js"></script>
<script src="${ctxStatic}/js/app.min.js"></script>
<script src="${ctxStatic}/js/leftmenu.js${jsversion}"></script>
<script src="${ctxStatic}/js/plugin-datatables/jquery.dataTables.min.js"></script>
<script src="${ctxStatic}/js/plugin-datatables/dataTables.bootstrap.min.js"></script>
<script src="${ctxStatic}/js/header.js${jsversion}"></script>
<script src="${ctxStatic}/js/time/time.js${jsversion}"></script>
<script src="${ctxStatic}/js/uway.common.js${jsversion}"></script>

		<script type="text/javascript">
            var currentElement;
            $(function() {
                var hasinitmonthdate = initmonthdate?true:false;
                if(hasinitmonthdate){
                    initmonthdate();
                }
            });

            var userTable = $('#userTable').DataTable({
                "scrollX": false,
                "processing": false,
                "serverSide": true,
                "ordercolumn":{orderable:false, colunmn:[0,1,2,3,]},
                "ajax": {
                    "url": "${contextPath}/user/getUserTableData",
                    "type": "post",
                    "data": function (d) {
                    },
                    "error":function (e1,e2,e3) {
                        if(e1.responseText.indexOf("<!DOCTYPE html>") != -1){
                            window.location = window.localStorage.getItem("CONTEXT_PATH") + '/user/login';
                        }
                    }
                },
                "columns":[
                    {data: "userId",bSearchable:false,title:'用户ID',defaultContent:""},
                    {data: "userName",bSearchable:true,title:'登录名',defaultContent:""},
                    {data: "chName",bSearchable:true,title:'姓名',defaultContent:""},
                    {data: "phone",bSearchable:false,title:'手机号',defaultContent:""},
                    {data: "mail",bSearchable:false,title:'邮箱',defaultContent:""},
                    {data: "roleId",bSearchable:false,title:'角色ID',visible:false,defaultContent:""},
                    {data: "roleName",bSearchable:false,title:'角色',bSortable:false,defaultContent:""},
                    {data: "createTime",bSearchable:false,title:'创建时间',defaultContent:""},
                    {data: function(e){
                        if(e.status == 0){
                            return "无效";
                        }else if(e.status == 1){
                            return "有效";
                        }
                    },bSearchable:false,title:'状态',name:'status'},
                    {data: function(e){
                        var str = '';
                        <fus:hasRight url="/user/updateUserInfo">
                        str += '<a onclick="editUser('+ "'"+e.userId+"'" +')">编辑</a>';
                        </fus:hasRight>
                        <fus:hasRight url="/user/updatePasswordByUserId">
                        str += '<a style="margin-left:10px;" onclick="updatePassword('+ "'"+e.userId+"','"+ e.userName +"'" +',this)">修改密码</a>';
                        </fus:hasRight>
                        return str;
                    },
                        title:'操作',
                        bSortable:false
                    }
                ],
                "order": [[ 7, "desc" ]],
            });

            function editUser(userId){
                window.location.href='${contextPath}/user/userEdit?UserId='+userId;
            }

            function addUser(){
                window.location.href='${contextPath}/user/userAdd';
			}

            function updatePassword(userId,userName,element){
                $("#modalUserId").val(userId);
                $("#modalUserName").val(userName);
                $("#modalNewPassword").val("");
                $("#modalNewPasswordConfirm").val("");
                $("#updateUserPasswordModal").modal("show");
            }

            function updatePasswordInModal(event){
                if(checkUpdatePasswordParams()){
                    ajaxUpdatePasswordById();
                }

                uwayStopBubble(event);
            }

            function checkUpdatePasswordParams(callBack){
                if($("#modalNewPassword").val().trim() == ""){
                    uwayWarning("警告","密码不能为空");
                    return false;
                }

                if($("#modalNewPasswordConfirm").val().trim() == ""){
                    uwayWarning("警告","请确认密码");
                    return false;
                }

                if($("#modalNewPasswordConfirm").val().trim() != $("#modalNewPassword").val().trim()){
                    uwayWarning("警告","两次密码输入不一致");
                    return false;
                }

                return true;
            }

            function ajaxUpdatePasswordById(){
                $.uwayProcess.show();
                $.ajax({
                    url: "${contextPath}/user/updatePasswordByUserId",
                    async: true, type: "Post", dataType: "json",
                    data: {userId:$("#modalUserId").val(),password:$("#modalNewPassword").val()},
                    success: function (response) {
                        $.uwayProcess.close();
                        $("#updateUserPasswordModal").modal("hide");
                        uwayNotice('通知',response.message);
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        console.log(textStatus);
                        $.uwayProcess.close();
                    }
                });
            }


            function deleteUser(userId,userName,element){
                uwayConfirm({
                    content:'确定要删除用户: '+userName+"吗？",
                    confirm:function(){
                        ajaxDeleteUser(userId,userName,element);
                    },
                    cancel:function(){

                    }
                });
            }

            function ajaxDeleteUser(userId,userName,element){
                $.uwayProcess.show();
                currentElement = element;
                $.ajax({
                    url: "${contextPath}/user/deleteUserById",
                    async: true, type: "Post", dataType: "json",
                    data: {UserId:userId},
                    success: function (response) {
                        $.uwayProcess.close();
                        $(element).parent().parent().remove();
                        uwayNotice("通知","删除成功啦！");
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        $.uwayProcess.close();
                        console.log(textStatus);
                    }
                });
            }

            function goUserAddPage(){
                window.location.href='${contextPath}/user/userAddPage';
            }
            //TEST2
		</script>
</body>
</html>