<%--
  Created by IntelliJ IDEA.
  User: zhaoyang
  Date: 2019/3/22
  Time: 19:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <meta name="format-detection" content="telephone=no">
    <link rel="shortcut icon" href="./img/favicon/favicon.ico${jsversion}" type="image/x-icon">
    <link rel="icon" href="./img/favicon/favicon.ico${jsversion}" type="image/x-icon">
    <style>
        .main-div{
            margin:0rem;
            height: 100%;
            width: 100%;
            top: 0px;
            bottom: 0px;
            text-align: left;
        }
        .titlediv{
            /*height: 15%;*/
            /*margin-top: 70%;*/
            padding-top: 15px;
            padding-left: 15px;
            padding-right: 15px;
            text-align: left;
            font-size: 25px;
            color: #333333;
        }
        .newsAuthor{
            width: 50%;
            font-size: 12px;
            text-align: left;
            float: left;
        }
        .timediv{
            width: 90px;
            font-size: 12px;
            text-align: right;
            float: right;
        }
        .newsBody {
            /*width: 100%;*/
            /*margin-top: 50px;*/
            padding: 15px;
        }
        .newsBody p{
            text-align: left;
        }
        image{
            max-width:100% !important;
        }
        video{
            min-width: 100%;
            min-height: 100%;
            height: auto;
            width: auto;
        }
    </style>
</head>
<body style="margin: 0;">

<div id="mainDiv" class="main-div">
    <div class="titlediv"><B>${NewsTitle}</B></div>
    <div style="padding: 15px;">
        <div class="newsAuthor">来源：${newsAuthor}</div>
        <div class="timediv">${NewsDate}</div>
    </div>

    <div class="newsBody">${NewsBody}</div>
</div>

<script src="${ctxStatic}/js/libs/jquery-2.0.2.min.js${jsversion}"></script>

<script type="text/javascript">

    $(document).ready(function () {
        var w = window.innerWidth;
        $("img").each(function () {
            var width = getWH(this, 'width')
            if(this.getAttribute("width") > w || width >w){
                $(this).removeAttr("style");
                this.setAttribute("width","100%");
                this.setAttribute("height","auto");
            }
        });

        $("video").each(function () {
                this.setAttribute("width","80%");
                this.setAttribute("height","auto");
        });
    });

    function getWH(el, name) {
        var val = name === "width" ? el.offsetWidth : el.offsetHeight,
            which = name === "width" ? ['Left', 'Right'] : ['Top', 'Bottom'];

        // display is none
        if(val === 0) {
            return 0;
        }

        for(var i = 0, a; a = which[i++];) {
            val -= parseFloat( getStyle(el, "border" + a + "Width") ) || 0;
            val -= parseFloat( getStyle(el, "padding" + a) ) || 0;
        }

        return val;
    }

    function getStyle(el,name) {
        if(window.getComputedStyle) {
            return window.getComputedStyle(el, null)[name];
        }else{
            return el.currentStyle[name];
        }
    }

    function geturlpara (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    }

    var browser = {
        versions: function () {
            var u = navigator.userAgent, app = navigator.appVersion;
            return {   //移动终端浏览器版本信息
                trident: u.indexOf('Trident') > -1, 	//IE内核
                presto: u.indexOf('Presto') > -1, 	   //opera内核
                webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
                gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
                mobile: !!u.match(/AppleWebKit.*Mobile.*/), //是否为移动终端
                ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
                android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或uc浏览器
                iPhone: u.indexOf('iPhone') > -1, //是否为iPhone或者QQHD浏览器
                iPad: u.indexOf('iPad') > -1, //是否iPad
                webApp: u.indexOf('Safari') == -1 //是否web应该程序，没有头部与底部
            };
        }(),
        language: (navigator.browserLanguage || navigator.language).toLowerCase()
    }
    //模拟toast弹框
    function Toast(msg,duration){
        duration=isNaN(duration)?3000:duration;
        var m = document.createElement('div');
        m.innerHTML = msg;
        m.style.cssText="width: 70%;min-width: 150px;opacity: 0.7;height: 30px;color: rgb(255, 255, 255);line-height: 30px;text-align: center;border-radius: 5px;position: fixed;top: 80%;left: 15%;z-index: 999999;background: rgb(0, 0, 0);font-size: 20px;";
        document.body.appendChild(m);
        setTimeout(function() {
            var d = 0.5;
            m.style.webkitTransition = '-webkit-transform ' + d + 's ease-in, opacity ' + d + 's ease-in';
            m.style.opacity = '0';
            setTimeout(function() { document.body.removeChild(m) }, d * 1000);
        }, duration);
    }
</script>

<div style="display:none;">
    <script type="text/javascript" src="https://s96.cnzz.com/z_stat.php?id=1276026976&web_id=1276026976"></script>
</div>
</body>
</html>
