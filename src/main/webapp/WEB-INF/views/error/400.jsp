<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<html>
<head>
	<title>400 - 请求出错</title>
</head>
<body>
	<div class="container-fluid">
		<div class="page-header"><h1>参数有误,服务器无法解析.</h1></div>
		<div class="errorMessage">
		</div>
		<a href="javascript:" onclick="history.go(-1);" class="btn">返回上一页</a> &nbsp;
		<br/> <br/>
		<script>try{top.$.jBox.closeTip();}catch(e){}</script>
	</div>
</body>
</html>
