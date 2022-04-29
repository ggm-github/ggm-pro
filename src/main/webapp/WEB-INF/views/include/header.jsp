<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<header id="header" class="secondary-color-black main-color-img">
	<span id="logo" style="font-size: 21px;color: white;">
	    <img style="width: 30px;" src="/static/img/logo.png" alt="SmartAdmin">
        操场APP
	</span>
    <div id="headerMenuRight">
        <ul class="nav navbar-nav navbar-left">
            <li class="dropdown">
                <a id="loginspan" href="#" style="padding-top:8px;" class="dropdown-toggle header-a header-hand secondary-color-black" role="button" aria-expanded="false" ></a>
            </li>

            <li class="dropdown">
                <a id="restpw" href="#" onclick="updatePW()" style="padding-top:8px;" class="dropdown-toggle header-a header-hand secondary-color-black" role="button" aria-expanded="false" >修改密码</a>
            </li>

            <li class="dropdown">
                <a id="loginout" href="${contextPath}/user/login" style="padding-top:8px;" class="dropdown-toggle header-a header-hand main-color-bluegreen" role="button" aria-expanded="false" >退出</a>
            </li>
        </ul>
    </div>

</header>
<div class="curtain">

</div>


<div class="modal fade" id="pwUserPasswordModal" tabindex="-1" role="dialog" aria-labelledby="pwTitle" aria-hidden="true">
    <div class="modal-dialog" style="width:400px;">
        <div class="modal-content" id="update-pw-form">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="pwTitle">修改密码</h4>
            </div>
            <div class="modal-body">
                <input id="pwUserId" type="hidden" value="" />
                <input class="form-control" type="text" id="pwUName" value=""/>
                <input class="form-control" type="password"  placeholder="请输入6-18位字母或数字新密码"
                       onkeyup="value = value.replace(/[^a-zA-Z0-9]/g, '')"
                       onbeforepaste="clipboardData.setData('text', clipboardData.getData('text').replace(/[^a-zA-Z0-9]/g, ''))"
                       maxlength="18" id="pwNewPassword" value="" />
                <input class="form-control" type="password"  placeholder="请输入6-18位字母或数字新密码"
                       onkeyup="value = value.replace(/[^a-zA-Z0-9]/g, '')"
                       onbeforepaste="clipboardData.setData('text', clipboardData.getData('text').replace(/[^a-zA-Z0-9]/g, ''))"
                       maxlength="18" id="pwNewPasswordConfirm" value="" />
            </div>
            <div class="modal-footer">
                <button id="resetbt" class="btn main-color-bluegreen" onclick="updatePWById()">
                    提交
                </button>
                <button type="button" class="btn" data-dismiss="modal">
                    取消
                </button>
            </div>
        </div>
    </div>
</div>
