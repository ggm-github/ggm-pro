<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
        <meta name=renderer content=webkit>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <title>操场APP后台管理系统</title>
		<link rel="stylesheet" type="text/css" media="screen" href="${ctxStatic}/css/bootstrap.min.css${jsversion}">
		<link rel="stylesheet" type="text/css" media="screen" href="${ctxStatic}/css/font-awesome.min.css${jsversion}">
		<link rel="stylesheet" type="text/css" media="screen" href="${ctxStatic}/css/smartadmin-production.min.css${jsversion}">
		<link rel="stylesheet" type="text/css" media="screen" href="${ctxStatic}/css/smartadmin-skins.min.css${jsversion}">
		<link rel="stylesheet" type="text/css" media="screen" href="${ctxStatic}/js/plugin/jBox/jBox.css${jsversion}">
		<link rel="stylesheet" type="text/css"  href="${ctxStatic}/css/uway.common.css${jsversion}">
		<link rel="shortcut icon" href="${ctxStatic}/img/favicon/favicon.ico${jsversion}" type="image/x-icon">
		<%--<link rel="icon" href="${ctxStatic}/img/favicon/favicon.ico?per=1" type="image/x-icon">--%>

        <style>
            #header{
                height:60px;
                max-height:60px;
                position: inherit !important;
            }

            #loginLogo{
                float:left;
                width:152px;
                height: auto;
                margin-left:120px;
                margin-top:10px;
            }

            #welcomeText{
                font-size:30px;
                color:#dcdcdc;
                float:left;
                margin-left:10px;
                margin-top:13px;
                border-left:1px white solid;
                padding-left:10px;
            }

            #registerNow{
                color:red;
                font-size:15px;
                /*position:absolute;*/
                right:40px;
            }

            #forgetPSW{
                color:blue;
                float:right;
                right:10px;
            }

            #versionChange{
                float:right;
                margin-right:160px;
                margin-top:28px;
                font-size:20px;
                color:white;
                text-decoration:none;
            }

            #versionChange:hover{
                font-style:italic;
            }

            html,body{
                background: url("${ctxStatic}/img/bg.jpg") no-repeat;
                background-size:cover;
            }
            .row{
                /*background-color: #2f3742 !important;*/
                margin-top: 233px !important;
                width: 100%;
                //margin-left: -130px !important;
            }
            .login_left {
                position:relative;
                padding:0;
                height:448px;
                width: 50%;
            }
            .login_left .mask {
                position:absolute;
                left:0;
                top:0;
                bottom:0;
                right:0;
                width:100%;
                height:100%;
                background:#1d1d1d;
                opacity:0.5;
            }
            .login_logo {
                position:relative;
                z-index:9;
                width:200px;
                margin:106px 0 0 95px;
            }
            .logo_title {
                position:relative;
                z-index:9;
                font-size:45px;
                color:#fff;
                margin:30px 0 0 95px;
                font-weight:bold;
            }
            .text_des {
                position:relative;
                z-index:9;
                padding:30px 0 0 95px;
            }
            .text_des p {
                font-size:20px;
                color:#fff;
                opacity:0.3;
                font-family: Arial;
            }
            .login_fill {
                padding:0;
            }
            .login_fill .well {
                height:500px;
                margin-top:-28px;
                background:#fff;
            }
            .shade {
                width:80%;
                position:absolute;
                right:0;
                top:0;
                z-index:999;
            }
            #loginForm {
                padding:100px 50px 50px 50px;
            }
            .smart-form footer {
                background:none;
            }
            .smart-form section {
                margin-bottom: 25px !important;
            }
        </style>
	</head>
    <body>
    <%--<header id="header" class="secondary-color-black main-color-img">--%>
        <%--<img id="loginLogo" src="${ctxStatic}/img/logo.png" alt="BingData" style="width: 152px; height: 42px;">--%>
        <%--<span id="welcomeText">欢迎登录</span>--%>
        <%--<!-- <a id="versionChange" href="http://120.52.12.203:8080/reckon">推算版入口</a> -->--%>
    <%--</header>--%>
    <div class="container">
        <div class="row">
            <div class="col-xs-7 col-sm-7 col-md-7 col-lg-7 hidden-xs hidden-sm login_left">
                <!--<img style="margin-left:30px;height: 400px;" src="saas/static/img/loginbg.png"/>-->
                <div class="mask"></div>
                <br>
                <br>
                <br>
                <h3 class="logo_title">操场APP后台管理系统</h3>
                <div class="text_des">
                    <p>我们的成功</p>
                    <p>离不开您的每一份努力</p>
                    <p>加油吧！</p>
                </div>
            </div>
            <div class="col-xs-12 col-sm-10 col-md-6 col-lg-5 login_fill">
                <div class="well no-padding">
                    <form id="loginForm" class="smart-form client-form">
                        <img src="${ctxStatic}/img/shade.png" class="shade" />
                        <fieldset>
                            <section>
                                <label class="label"></label>
                                <label class="input">
                                    <i class="icon-append fa fa-user"></i>
                                    <input type="text" id="username" name="username" value="" placeholder="账号">
                                    <b class="tooltip tooltip-top-right">
                                        <i class="fa fa-user txt-color-teal"></i>
                                        请输入您的账号</b>
                                </label>
                            </section>

                            <section>
                                <label class="label"></label>
                                <label class="input">
                                    <i class="icon-append fa fa-lock"></i>
                                    <input type="password" id="password" name="password"  value=""  placeholder="密码">
                                    <b class="tooltip tooltip-top-right">
                                        <i class="fa fa-lock txt-color-teal"></i>
                                        请输入您的密码</b>
                                </label>
                            </section>

                        </fieldset>
                        <footer>
                            <label class="label" id="loginMessage" style="color:red;"></label>
                            <input id="loginButton" type="button" class="btn col-xs-12 col-sm-12 col-md-12 col-lg-12 main-color-bluegreen" onclick="userlogin()" style="color: white;" value="登录"/>
                        </footer>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <script type="text/JavaScript" src="${ctxStatic}/js/libs/jquery-2.0.2.min.js${jsversion}"></script>
    <script type="text/JavaScript" src="${ctxStatic}/js/app.config.js${jsversion}"></script>
    <script type="text/JavaScript" src="${ctxStatic}/js/bootstrap/bootstrap.min.js${jsversion}"></script>
    <script type="text/JavaScript" src="${ctxStatic}/js/plugin/jquery-validate/jquery.validate.min.js${jsversion}"></script>
    <script type="text/JavaScript" src="${ctxStatic}/js/app.min.js${jsversion}"></script>
    <script src="${ctxStatic}/js/plugin/jBox/jBox.min.js${jsversion}"></script>
    <script src="${ctxStatic}/js/uway.common.js${jsversion}"></script>

    <script>
        var caps=['A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z']
        $(document).ready(function() {
            document.getElementById('password').onkeydown = detectCapsLock;
            passwordMsg = new jBox('Tooltip', {
                animation: 'move',
                content: '大写字母锁定键已开启！',
                attach: $('#password')
            });
            passwordMsg.disable();
            var tempArray = window.location.href.split("/");
            var contentPath = tempArray[0]+"//"+tempArray[2];
            window.localStorage.setItem("CONTEXT_PATH",contentPath);
            window.localStorage.removeItem("MENU");
            window.localStorage.removeItem("LOGINSPAN");

            //pageSetUp();
            $("#loginForm").validate({
                rules : {
                    username : {
                        required : true
                    },
                    password : {
                        required : true
                    }
                },
                messages : {
                    username : {
                        required : '账号不能为空'
                    },
                    password : {
                        required : '密码不能为空'
                    }
                },
                // Do not change code below
                errorPlacement : function(error, element) {
                    error.insertAfter(element.parent());
                }
            });

            handleLoginResult();
            //getip();
        });
        //回车提交事件
        $("body").keydown(function() {
            if (event.keyCode == "13") {
                $('#loginButton').click();
            }
        });
        function handleLoginResult(){
            setTimeout("$('#loginMessage').text('');",3000)
        }

        function detectCapsLock(event)
        {
            var e = event||window.event;
            var o = e.target||e.srcElement;
            var keyCode = e.keyCode || e.which
            if(keyCode == 20){
                if(isCap){
                    passwordMsg.close();
                    passwordMsg.disable();
                }else{
                    passwordMsg.enable();
                    passwordMsg.open();
                }
                isCap = !isCap;
                return;
            }
            isCap = caps.indexOf(e.key) < 0 ? false : true;
            var isShift  =  e.shiftKey ||(keyCode  ==   16 ) || false;
            // 判断shift键是否按住
            if ((isCap && !isShift) || (!isCap && isShift)){
                passwordMsg.enable();
                passwordMsg.open();
            }
            else{
                passwordMsg.close();
                passwordMsg.disable();
            }
        }
        
        function userlogin() {

            var para = $("#loginForm").serialize();
            $.ajax({
                url: "${contextPath}/user/userLogin",
                async: true,
                type: "POST",
                dataType: "json",
                data: para,
                success: function (data) {
                    if(!data.success){
                        uwayNotice('通知',data.message);
                    }else{
                        window.localStorage.setItem("MENU",JSON.stringify(data.result.menuGroupList));
                        window.localStorage.setItem("LOGINSPAN","你好，"+data.result.chName);
                        window.location.href = "${contextPath}"+data.result.menuGroupList[0].menus[0].mpath;
                        window.localStorage.setItem("USERID",data.result.userId);
                        window.localStorage.setItem("LOGINNAME",data.result.chName);
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log(textStatus);
                }
            });
        }

    </script>
    </body>
</html>
