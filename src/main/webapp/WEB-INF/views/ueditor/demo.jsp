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

    </style>
</head>
<body class="desktop-detected pace-done" style="background:url(${ctxStatic}/img/sysBg.png)">

<jsp:include page="/WEB-INF/views/include/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/views/include/leftmenu.jsp"></jsp:include>
<div id="main" role="main">
    <div id="ribbon">
        <div class="col-sm-7">
            <ol class="breadcrumb">
                <li>系统管理</li><li>demo</li>
            </ol>
        </div>
    </div>
    <div id="content">
        <div id="tableRow" class="row" style="min-width:1000px;">

        </div>

        <div id="editor" style="width:100%;"></div>


        <div id="ckeditor" style="width:100%;"></div>

        <input type="button" onclick="getDate()" value="获取" />

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
<%-- 百度富文本编辑器
<script type="text/javascript" charset="utf-8" src="${ctxStatic}/js/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctxStatic}/js/ueditor/ueditor.all.min.js"> </script>
<!--手动加载语言文件，会覆盖在配置项目里添加的语言类型-->
<script type="text/javascript" charset="utf-8" src="${ctxStatic}/js/ueditor/lang/zh-cn/zh-cn.js"></script>

<script type="text/javascript">
    // 实例编辑器
    var ue;
    $(function() {
        ue = UE.getEditor('editor');
    });

</script>
--%>

<%-- ckeditor 富文本编辑器 --%>
<script type="text/javascript" charset="utf-8" src="${ctxStatic}/js/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
    var data;
    var ckeditor;
    var ckConfirm = {
        language: 'zh-cn'
    };
    ClassicEditor
        .create(document.querySelector('#ckeditor'),ckConfirm)
        .then(editor => {
            ckeditor = editor;
        })
        .catch(error => {
            console.error( error.stack );
        });



    $(function() {

    });

    function getDate(){
        alert(ckeditor.getData());
    }

    class UploadAdapter {
        constructor(loader) {
            this.loader = loader;
        }
        upload() {
            return new Promise((resolve, reject) => {
                const data = new FormData();
                data.append('file', this.loader.file);
                data.append('allowSize', 10);//允许图片上传的大小/兆
                $.ajax({
                    url: '/comm/uploadAjax',
                    type: 'POST',
                    data: data,
                    dataType: 'json',
                    processData: false,
                    contentType: false,
                    success: function (data) {
                        if (data.success) {
                            resolve({
                                default: data.result.url
                            });
                        } else {
                            reject(data.message);
                        }
                    }
                });
            });
        }
        abort() {
        }
    }

    // 加载了适配器
    editor.plugins.get('FileRepository').createUploadAdapter = (loader)=>{
        return new UploadAdapter(loader);
    };

</script>


</html>