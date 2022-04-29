<%@ page language="java" import="java.util.*"
         contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/lefticonfont.css">
<aside id="left-panel" class="leftmenu">

    <nav id="nav">
        <ul id="menu">
            <%--<li><a href="#">--%>
                <%--<i class="normal-color icon iconfont icon-weibiaoti1"></i>--%>
                <%--<span style="margin-left: 5px;" class="menu-item-parent">系统管理</span></a>--%>
                <%--<ul>--%>
                    <%--<li><a href="${contextPath}/user/userManage">用户管理</a></li>--%>
                <%--</ul>--%>
            <%--</li>--%>
                <%--<li><a href="#"><i class="normal-color icon iconfont icon-weibiaoti1"></i><span style="margin-left: 5px;" class="menu-item-parent">内容管理</span></a><ul><li><a href="/app/business">运营管理</a></li><li><a href="/app/setting">产品设置</a></li><li><a href="/app/suggestion">意见处理</a></li><li><a href="/app/user">用户管理</a></li><li><a href="/app/colcontent">栏目内容管理</a></li><li><a href="/app/activity">活动管理</a></li><li><a href="/app/product">商品管理</a></li><li><a href="/app/card">积分卡管理</a></li></ul></li><li><a href="#"><i class="normal-color icon iconfont icon-weibiaoti1"></i><span style="margin-left: 5px;" class="menu-item-parent">系统管理</span></a><ul><li><a href="/user/logmanage">日志管理</a></li><li><a href="/user/rolemanage">角色管理</a></li><li><a href="/user/usermanage">账号管理</a></li></ul></li>--%>
        </ul>
    </nav>
    <span id="minifyMenu" class="minifyme" data-action="minifyMenu"> <i class="fa fa-arrow-circle-left hit"></i>
	</span>
    <div class="curtain">

    </div>
</aside>
<style>
    #menu > li:hover {display: block;}
</style>
