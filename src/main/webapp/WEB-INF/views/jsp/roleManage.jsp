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

        #roleAdd{
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
                <li>系统管理</li><li>角色管理</li>
            </ol>
        </div>
    </div>
    <div id="content">
        <fus:hasRight url="/user/addRole">
            <button id="roleAdd" class="btn main-color-bluegreen" onclick="addRole()">新增角色</button>
        </fus:hasRight>
        <div id="tableRow" class="row" style="min-width:1000px;">
            <table id="roleTable" class="table table-striped table-bordered">

            </table>
        </div>

    </div>

</div>

<div class="modal fade" id="updateRoleModal" tabindex="-1" role="dialog"
     aria-labelledby="appAddLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 600px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="roletype">新增角色</h4>
            </div>
            <input type="text" id="roleId" value="0" hidden>
            <div class="modal-body">
                <div style="width: 300px;margin: 0 auto;">
                    <div class="form-group">
                        <label for="roleName">角色名称:</label>
                        <input type="text" id="roleName" placeholder="请输入角色名称" >
                    </div>
                    <div class="form-group">
                        <label for="roleBak">角色说明:</label>
                        <input type="text" id="roleBak" placeholder="请输入角色说明">
                    </div>
                    <label for="menudiv">菜单名称:</label>
                    <%--<div id="menudiv" class="form-group" style="height: 200px;overflow-y: auto;">

                    </div>--%>
                    <div id="menudiv" class="form-group" style="height: 400px;overflow-y: auto;">
                        <div id="jstree_div">

                        </div>
                    </div>


                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn main-color-bluegreen" onclick="saveRole()">提交</button>
            </div>
        </div>
    </div>
</div>

<div class="page-footer">
</div>


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
<script src="${ctxStatic}/js/time/time.js${jsversion}"></script>
<script src="${ctxStatic}/js/header.js${jsversion}"></script>
<script src="${ctxStatic}/js/uway.common.js${jsversion}"></script>
<script src="${ctxStatic}/js/jstree/jstree.min.js${jsversion}"></script>

<script type="text/javascript">
    isShow = false;
    var rightList = JSON.parse(${fns:toJson(rightList)});
    $(function() {
//            isShow = false;//设置没有默认app时的提示不显示
        var hasinitmonthdate = initmonthdate?true:false;
        if(hasinitmonthdate){
            initmonthdate();
        }
        //角色数据权限，先初始化所有部门
        $('#jstree_div').data('jstree', false);//清空数据，必须
        $('#jstree_div').jstree({
            'plugins' : [ "checkbox" ], //出现选择框
            'checkbox': { cascade: "", three_state: false }, //不级联
            'core' : {
                'dblclick_toggle':false,   // 双击是否选中
                'data' : rightList,
                "themes": {
                    "icons":false,    // 是否显示图标
                    "variant" : "large",//加大
                    "ellipsis" : true, //文字多时省略
                    "responsive": false
                }
            }
        });
    });

    var roleTable = $('#roleTable').DataTable({
        "scrollX": false,
        "processing": false,
        "serverSide": true,
        "ajax": {
            "url": "${contextPath}/user/getRoleTableData",
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
            {data: "roleId",bSearchable:false,title:'角色ID',visible:false},
            {data: "roleName",bSearchable:false,title:'角色名称',bSortable:false},
            {data: "roleBak",bSearchable:false,title:'角色说明',bSortable:false},
            {data: "roleDetail",bSearchable:false,title:'detail',visible:false,bSortable:false},
            {data: function(e){
                var str = '';
                <fns:hasRight url="/user/editRole">
                str += '<a onclick="editRole('+"'"+ e.roleId + "'"+",'"+ e.roleName + "'"+",'"+ e.roleBak + "'"+",'"+ e.roleDetail + "'" +')">编辑</a>';
                </fns:hasRight>
                <fus:hasRight url="/user/deleteRoleById">
                str += '<a style="margin-left:10px;" onclick="deleteRole('+"'"+ e.roleId+"',"+ 'this)">删除</a>';
                </fus:hasRight>
                return str;
            },
                title:'操作',
                bSortable:false
            }
        ],
        "order": [[ 1, "desc" ]],
        "language":{
            "lengthMenu":"每页显示 _MENU_ 条记录"
        }
    });

    <fus:hasRight url="/user/editRole">
    function editRole(roleId,rolename,rolebak,roledetail){
        // 先清除所有选中的
        $('#jstree_div').jstree().deselect_all();
        // 选中当前角色的权限信息
        var menuids = roledetail.split(",");
        $('#jstree_div').jstree().select_node(menuids);
        // 填写角色相关信息
        $("#roletype").html('编辑角色');
        $("#roleId").val(roleId);
        $("#roleName").val(rolename);
        $("#roleBak").val(rolebak);
        $("#updateRoleModal").modal("show");
    }
    </fus:hasRight>



    /**
     * 使用循环的方式判断一个元素是否存在于一个数组中
     * @param {Object} arr 数组
     * @param {Object} value 元素值
     */
    function isInArray(arr,value){
        for(var i = 0; i < arr.length; i++){
            if(value == arr[i]){
                return true;
            }
        }
        return false;
    }
    <fus:hasRight url="/user/deleteRoleById">
    function deleteRole(roleId,element){
        uwayConfirm({
            content:'删除角色后,与此角色相关联的用户会受到影响,确定要删除该角色吗？',
            confirm:function(){
                ajaxDeleteRole(roleId,element);
            },
            cancel:function(){

            }
        });
    }

    function ajaxDeleteRole(roleId,element){
        $.uwayProcess.show();
        currentElement = element;
        $.ajax({
            url: "${contextPath}/user/deleteRoleById",
            async: true, type: "Post", dataType: "json",
            data: {roleId:roleId},
            success: function (response) {
                $.uwayProcess.close();
                roleTable.draw(false);
                uwayNotice("通知","删除成功啦！");
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                $.uwayProcess.close();
                console.log(textStatus);
            }
        });
    }
    </fus:hasRight>

    <fus:hasRight url="/user/addRole">
    function addRole(){
        $("#roletype").html('新增角色');
        $("#roleId").val("0");
        $("#roleName").val("");
        $("#roleBak").val("");
        $('#jstree_div').jstree().deselect_all();
        $("#updateRoleModal").modal("show");
    }

    function saveRole() {
        var roledetailstr = "";
        var ref = $('#jstree_div').jstree(true);
        var sel = ref.get_selected();
        if(!sel.length) {
            uwayNotice("通知", "请务必选择菜单！");
            return;
        }
        for(var i=0;i<sel.length;i++){
            roledetailstr += sel[i] + ",";
        }
        roledetailstr = roledetailstr.substring(0,roledetailstr.length-1);
        $.uwayProcess.show();
        if ($("#roleId").val() == 0) {
            var roleobj ={
                roleName:$("#roleName").val(),
                roleBak : $("#roleBak").val(),
                roleDetail : roledetailstr
            }
            $.ajax({
                url: "${contextPath}/user/addRole",
                async: true, type: "Post", dataType: "json",
                data: roleobj,
                success: function (data) {
                    $.uwayProcess.close();
                    if(data.success){
                        uwayNotice("通知", "保存成功！");
                        roleTable.draw(false);
                        $("#updateRoleModal").modal("hide");
                    }
                    else {
                        uwayNotice("通知",  data.message);
                    }

                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    $.uwayProcess.close();
                    console.log(textStatus);
                }
            });
        } else {
            var roleobj ={
                roleId:$("#roleId").val(),
                roleName:$("#roleName").val(),
                roleBak : $("#roleBak").val(),
                roleDetail : roledetailstr
            }
            $.ajax({
                url: "${contextPath}/user/editRole",
                async: true, type: "Post", dataType: "json",
                data: roleobj,
                success: function (data) {
                    $.uwayProcess.close();
                    if(data.success){
                        uwayNotice("通知", "保存成功！");
                        roleTable.draw(false);
                        $("#updateRoleModal").modal("hide");
                    }
                    else {
                        uwayNotice("通知", data.message);
                    }

                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    $.uwayProcess.close();
                    console.log(textStatus);
                }
            });
        }
    }
    </fus:hasRight>

</script>
</html>