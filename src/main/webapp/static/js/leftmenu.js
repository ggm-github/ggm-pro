$(function(){
    	    var urlstr = location.href;
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


});