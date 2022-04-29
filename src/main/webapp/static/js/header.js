/*
 * description:  系统公共方法 js 文件 （1、header中控件的处理；2、请求参数的获取和处理;3、新增我的关注分组;4、新增我关注的app;）
 * create date:  2016-05-23            
 *    email   :  weip@uway.cn;
 * 
 * modify man :
 * modify date:
 * description:  
 * window。localStorage中保存了5个变量：
 *                  HEADER_PARAMS
 *                  UWAY_MY_INTEREST
 *                  UWAY_INTEREST_GROUP
 *                  UWAY_TAG
 *                  UWAY_COMPARE_CONTROL
 * 
 */

jQuery.extend({
    uwayProcess: {
        show: function (params) {
            var w = $("#left-panel").width();
            $('#content').append(
                '<div id="uway-control-process-cover" style="position:fixed;z-index:19999;top:50px;width:100%;height:100%;opacity:0.8;background:#eaedf1 none repeat scroll 0% 0%;left:' + w + 'px;">' +
                '<img id="uway-control-process-img" style="z-index:20000;position:absolute;width:80px;height:80px;left:40%;top:35%;" src="' + window.localStorage.getItem("CONTEXT_PATH") + '/static/img/ajax-loader.gif"/></div>'
            );
            $("#uway-control-process-img").animate({top: '35%'}, "fast");
        },
        close: function (params) {
            $('#uway-control-process-img').remove();
            $('#uway-control-process-cover').remove();
        }
    }
});

function bindDatetimepicker() {
    $('.form_date').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0,
        startDate: new Date('2017-11-01'),
        endDate: new Date().addDays(-2),
        // initialDate: getInitialDate(),
        format: 'yyyy-mm-dd',
        initialDate: getInitialDate()
    });

}


function initmonthdate() {
    $("#monthdate").datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 3,
        minView: 3,
        forceParse: 0,
        format: 'yyyy-mm'
    });
}

$(document).ready(function () {
    createmenu();
    initmenuEvent();
    var loginspan = window.localStorage.getItem("LOGINSPAN");

    $("#loginspan").html(loginspan);

    $("b.collapse-sign").on('click',function(){
        var tempLi = $(this).parent().parent();
        var tempParent = $(this).parent().parent().parent();
        tempParent.children().not(tempLi).removeClass('open');
        tempParent.children().not(tempLi).find('.open').removeClass('open');
        tempParent.children().not(tempLi).find('.open-color').removeClass('open-color');
        tempParent.children().not(tempLi).children("i").addClass('normal-color');
        tempParent.children().not(tempLi).find('.fa-minus-square-o').removeClass('fa-minus-square-o').addClass('fa-plus-square-o');
        tempParent.children().not(tempLi).find('ul').css('display','none');

        var isOpen = $(this).children().hasClass('fa-minus-square-o');
        if(isOpen){
            tempLi.removeClass('open');
            tempLi.children("a").removeClass("open-color")
            tempLi.children("a").addClass("normal-color")
            tempLi.find("ul:first").css('display','none');
            $(this).find(".fa.fa-minus-square-o").attr("class","fa fa-plus-square-o");
        }else{
            tempLi.addClass('open');
            tempLi.find("ul:first").css('display','block');
            $(this).find(".fa.fa-plus-square-o").attr("class","fa fa-minus-square-o");
        }
        return false;
    });

    $(".menu-item-li").on('click',function(){
        var tempLi = $(this);
        var tempParent = $(this).parent().parent().parent();
        tempParent.children().not(tempLi).removeClass('open');
        tempParent.children().not(tempLi).find('.open').removeClass('open');
        tempParent.children().not(tempLi).find('.open-color').removeClass('open-color');
        tempParent.children().not(tempLi).children("i").addClass('normal-color');
        tempParent.children().not(tempLi).find('.fa-minus-square-o').removeClass('fa-minus-square-o').addClass('fa-plus-square-o');
        tempParent.children().not(tempLi).find('ul').css('display','none');

        var isOpen = $(this).children().hasClass('fa-minus-square-o');
        if(isOpen){
            tempLi.removeClass('open');
            tempLi.children("a").removeClass("open-color");
            tempLi.children("a").addClass("normal-color");
            tempLi.next("ul:first").css('display','none');
            $(this).children().find(".fa.fa-minus-square-o").attr("class","fa fa-plus-square-o");
        }else{
            tempLi.addClass('open');
            tempLi.next("ul:first").css('display','block');
            $(this).children().find(".fa.fa-plus-square-o").attr("class","fa fa-minus-square-o");
        }
        return false;
    });


});

$(function () {
    //createmenu();
    //initmenuEvent();
})
function createmenu() {
    var menujson = window.localStorage.getItem("MENU");
    var menuObj = JSON.parse(menujson);
    var menuHtml = "";

    var meauicon = ['<i class="normal-color icon iconfont icon-weibiaoti1"></i>','<i class="fa fa-lg fa-fw fa-pencil-square-o"></i>','<i class="fa fa-lg fa-fw fa-list-alt"></i>','<i class="fa fa-lg fa-fw fa-pencil-square-o"></i>']

    for (var i = 0; i < menuObj.length; i++) {
        menuHtml += '<li><a class="menu-item-li" href="#">'
            + meauicon[i]
            + '<span style="margin-left: 5px;" class="menu-item-parent">'
            + menuObj[i].groupname
            + '</span>' +
            '<b class="collapse-sign"><em class="fa fa-plus-square-o"></em></b>' +
            '</a><ul style="display: none;">';
        for (var j = 0; j < menuObj[i].menus.length; j++) {
            menuHtml += '<li><a href="'
                + menuObj[i].menus[j].mpath
                + '">'
                + menuObj[i].menus[j].mname
                + '</a></li>';
        }
        menuHtml += '</ul></li>';
    }
    $("#menu").html(menuHtml);
}

function initmenuEvent() {
    var urlstr = location.href;
    urlstr = urlstr.split("?")[0];
    var urlStrs = urlstr.split("/");
    var url = "";
    for (var i = 3; i < urlStrs.length; i++) {
        /*    	    	if (i == urlStrs.length - 1) {
                            url += urlStrs[i];
                        } else {*/
        url += "/" + urlStrs[i];
    }
    var urlstatus=false;
    $("#menu a").each(function () {
        var curUrl = $(this).attr('href');
        //curUrl = curUrl.substr(2,curUrl.length);
        if (url == curUrl && curUrl!='') {
            $(this).parent("li").addClass("active");
            $(this).closest("ul").css("display","block");
            $(this).parent("li").parent("ul").closest("li").addClass("open");
            $(this).parent("li").parent("ul").closest("li").closest("ul").css("display","block");
            $(this).parent("li").parent("ul").closest("li").closest("ul").closest("li").addClass("open");
            $(this).parent("li").parent("ul").closest("li").children("a").find(".fa.fa-plus-square-o").attr("class","fa fa-minus-square-o");
            $(this).children("i").removeClass("normal-color");
            $(this).children("i").addClass("open-color");
            if($(this).parents("ul").length > 2){
                $(this).parent("li").parent("ul").parent("li").parent("ul").parent("li").children("a").find(".fa.fa-plus-square-o").attr("class","fa fa-minus-square-o");
            }

            urlstatus = true;

            if($(this).parent("li").find("ul").length > 0){
                $(this).parent("li").find("ul").css("display","block");
                $(this).find(".fa.fa-plus-square-o").attr("class","fa fa-minus-square-o");
            }

        } else {
            $(this).removeClass('active');
            $(this).children("i").removeClass("open-color");
            $(this).children("i").addClass("normal-color");
        }
    });
    if (!urlstatus) {
        $("#menu a").eq(0).addClass('open');
        $(this).children("i").removeClass("normal-color");
        $(this).children("i").addClass("open-color");
    }
}
//获取当前日期函数
function getNowFormatDate() {
    var date = new Date().addDays(-2);
    var seperator1 = "-";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = year + seperator1 + month + seperator1 + strDate;
    return currentdate;
}

function updatePW(){
    $("#pwUserId").val( window.localStorage.getItem("USERID"));
    $("#pwUName").val( window.localStorage.getItem("LOGINNAME"));
    $("#pwNewPassword").val("");
    $("#pwNewPasswordConfirm").val("");
    $("#pwUserPasswordModal").modal("show");
}

function checkPasswordParams(){
    if($("#pwNewPassword").val().trim() == ""){
        uwayWarning("警告","密码不能为空");
        return false;
    }

    if($("#pwNewPassword").val().trim() == ""){
        uwayWarning("警告","请确认密码");
        return false;
    }

    if($("#pwNewPassword").val().trim() != $("#pwNewPasswordConfirm").val().trim()){
        uwayWarning("警告","两次密码输入不一致");
        return false;
    }

    return true;
}

function updatePWById(){
    if(!checkPasswordParams()){
        return;
    }

    $.uwayProcess.show();
    $.ajax({
        url: "/user/updatePasswordByUserId",
        async: true, type: "Post", dataType: "json",
        data: {userId:$("#pwUserId").val(),password:$("#pwNewPassword").val()},
        success: function (response) {
            $.uwayProcess.close();
            $("#pwUserPasswordModal").modal("hide");
            uwayNotice('通知',response.message);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log(textStatus);
            $.uwayProcess.close();
        }
    });
}