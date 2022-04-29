	var timeGran;
	var contextPath;
	var ctxStatic;
	var myInterestAppsNow = getPanelAppData();
//---------------------------------行转列通用js------------------------------------------------
	function commonChartOption(title,yAxisName){
		return {
			
				title : {
					text : title,
					textStyle : {
						color : '#2a2f32',
						fontWeight : 'normal',
						fontSize : 14,
					},
					left : 30,
					padding :5
				},
				tooltip : {
					trigger : 'axis',
					extraCssText : 'z-index: 2;'
				},
				legend : {
					/* 设置指标数据 */
					data : [],
					textStyle : {
						color : '#2a2f32',
					},
					itemWidth : 30,
					itemHeight : 14,
					itemGap : 15,
					top : 40,
					left : 30,
                    right: 40,
					height : 30,
					padding : 0
				},
				grid : {
					left : '4%',
					right : '3%',
					top : '120',
					// bottom : '5%',
					containLabel : true,
					borderColor : '#2a2f32',
				},
				toolbox : {
				    show : true,
					y : 50,
					orient : 'vertical',
                    itemGap : 15,
                    feature : {
                        mark : {
                            show : true,
                        },
                        dataView : {
                            show : false,
                            iconStyle : {
                                normal : {
                                    borderColor : '#1F61C1',
                                },
                                emphasis : {
                                    borderColor : '#82CF19',
                                }
                            },
                        },
                        magicType : {
                            show : true,
                            type : [ 'line', 'bar' ],
                            iconStyle : {
                                normal : {
                                    borderColor : '#1F61C1',
                                },
                                emphasis : {
                                    borderColor : '#82CF19',
                                }
                            },
                        },
                        dataZoom : {
                            show : true,
                            title:{
                                   zoom:"区域放大",
                                   back:"区域放大还原"
                            },
                            iconStyle : {
                                normal : {
                                    borderColor : '#1F61C1',
                                },
                                emphasis : {
                                    borderColor : '#82CF19',
                                }
                            },
                        },
                        restore : {
                            show : true,
                            title:'图表初始化',
                            iconStyle : {
                                normal : {
                                    borderColor : '#48277E',
                                },
                                emphasis : {
                                    borderColor : '#82CF19',
                                }
                            },
                        },
                        saveAsImage : {
                            show : true,
                            backgroundColor : '#fff',
                            iconStyle : {
                                normal : {
                                    borderColor : '#D26732',
                                },
                                emphasis : {
                                    borderColor : '#82CF19',
                                }
                            },
                        },
                    },
				},
				xAxis : {
					type : 'category',
					boundaryGap : false,
					splitLine : {
						show : true,
						lineStyle : {
							color : '#2a2f32',
							type : 'dotted',
						}
					},
					axisTick : {
						show : false,
                        alignWithLabel: true
					},
					axisLabel : {
						interval : 0,
                        margin: 15,
						rotate : 30,
						textStyle : {
							color : '#2a2f32'
						},
					},
					data : []
				},
				yAxis : [ {
					type : 'value',
					name : yAxisName,
					min : 0,
                    // max:100,
					nameTextStyle : {
						color : '#2a2f32'
					},
					splitLine : {
						show : true,
						lineStyle : {
							color : '#2a2f32',
							type : 'dotted'
						}
					},
					nameGap : 15,
					boundaryGap : ['50%', '20%'],
					axisTick : {
						show : false
					},
					axisLabel : {
						formatter : '{value}',
						textStyle : {
							color : '#2a2f32'
						}
					}
				} ],
				series : []
			}
	}
	//刷新Table 
	function refreshTable(condition, contextPath, ctxStatic,moduleId) {
	    if(table != null)
	        table.destroy();
		$("#tableContent").empty();
		var tableHtml = '<table id="saasTable" class="table table-striped table-bordered uway-bootstrap-table">'
			+ '<thead>'
			+ '<tr id="firstRow"></tr>'
		   // + '<tr id="secondRow"></tr>'
			+ '</thead>'
			+ '<tbody>'
			+ '</tbody>'
			+ '</table>';
		$("#tableContent").append(tableHtml);
		initDataTable(condition, contextPath, ctxStatic, moduleId);
	}
	// ------------------------------DataTable处理  start------------------------------------------
	//构造DataTable数据绑定columns
	function createColumns() {
		var cols = [];
		var app = [ 'app', 'appName',"industryTypeName"];
		//构造固定列
		for (var i = 0; i < app.length; i++) {
			var data = {};
			data.data = app[i];
			data.bSearchable = false;
			cols.push(data);
		}

        for (var i = 0; i<targetkpis.length; i++) {
            var data = {};
            data.data = targetkpis[i];
            data.bSearchable = false;
            data.render = function (data) {
                // if(data != ''){
                 //    var newDate = data.toFixed(2);
                 //    return newDate;
				// }
				return data;
            };
            cols.push(data);
        }
        return cols;
		//-----------------------------隐藏对比和关注列  zhaoyang20170426----------------------------
		// //添加关注列
		// var data = {};
		// data.data = function(e){
		// 	 return uwayReturnTableMyInterestColumnHtml(e);
        // };
        // data.bSearchable = false;
		// cols.push(data);
		//
		// //添加对比列
		// var data = {};
		// data.data = function(e){
	     //     return uwayReturnTableCompareColumnHtml(e);
        // };
        // data.bSearchable = false;
		// cols.push(data);
		
		//1:天;2:周;3:月
		if (timeGran == 1) {
			for (var i = 0; i < 5; i++) {
				var strDate = new Date(condition.startTime).addDays(-4+i).format('yyyyMMdd');
				var data = {};
				data.data = target + "_" + strDate;
				data.bSearchable = false;
				data.render = function(data) {
					var newDate=data.toFixed(2);
					return newDate;
				};
				cols.push(data);
				
				//添加环比列
				var data = {};
				data.data = targetRatio + "_" + strDate;
				data.bSearchable = false;
				data.render = function(data) {
					var newDate=data.toFixed(2);
					return newDate;
				};
				cols.push(data);
			}
			return cols;
		} else if (timeGran == 2) {
			for (var i = 0; i < 7; i++) {
				var strDate = new Date(condition.startTime).addDays(i).format('yyyyMMdd');
				var data = {};
				data.data = target + "_" + strDate;
				data.bSearchable = false;
				data.render = function(data) {
					var newDate=data.toFixed(2);
					return newDate;
				};
				cols.push(data);
				
				//添加环比列
				var data = {};
				data.data = targetRatio + "_" + strDate;
				data.bSearchable = false;
				data.render = function(data) {
					var newDate=data.toFixed(2);
					return newDate;
				};
				cols.push(data);
			}
			return cols;
		}
		//选月时，显示周
		var start = new Date(condition.startTime).getFirstDateOfWeek();
		var endWeek = new Date(condition.endTime).getLastDateOfWeek();
		while (start < endWeek) {
			var data = {};
			var strDate = new Date(start).format('yyyyMMdd');
			data.data = target + "_" + strDate;
			data.bSearchable = false;
			data.render = function(data) {
				var newDate=data.toFixed(2);
				return newDate;
			};
			cols.push(data);
			
			//添加环比列
			var data = {};
			data.data = targetRatio + "_" + strDate;
			data.bSearchable = false;
			data.render = function(data) {
				var newDate=data.toFixed(2);
				return newDate;
			};
			cols.push(data);
			start = new Date(start).addDays(7);
		}
		return cols;
	}

    //点击chart表 查看几天的趋势数据
    function createColumnsdetail() {
        var cols = [];
        var app = ['app','startTime',"industryTypeName"];

        //构造固定列
        for (var i = 0; i < app.length; i++) {
            var data = {};
            data.data = app[i];
            data.bSearchable = false;
            cols.push(data);
        }

        for (var i = 0; i<targetkpis.length; i++) {
            var data = {};
            data.data = targetkpis[i];
            data.bSearchable = false;
            data.render = function (data) {
                var newDate = data.toFixed(2);
                return newDate;
            };
            cols.push(data);
        }
        return cols;
    }

	function getHeaders(condition) {
		var headers = [];
        var header = new Date(condition.startTime).format('yyyy-MM-dd');
        headers.push(header);
        return headers;
		// //1:天;2:周;3:月
		// if (timeGran == 1) {
		// 	for (var i = 0; i < 5; i++) {
		// 		var header = new Date(condition.startTime).addDays(-4+i).format('yyyy-MM-dd');
		// 		headers.push(header);
		// 	}
		// 	return headers;
		// } else if (timeGran == 2) {
		// 	for (var i = 0; i < 7; i++) {
		// 		var header = new Date(condition.startTime).addDays(i).format('yyyy-MM-dd');
		// 		headers.push(header);
		// 	}
		// 	return headers;
		// }
		// //选月时，显示周
		// //构造动态header
		// var start = new Date(condition.startTime).getFirstDateOfWeek();
		// var endWeek = new Date(condition.endTime).getLastDateOfWeek();
		// var i = 0;
		// while (start < endWeek) {
		// 	var end = new Date(start).addDays(6).format('yyyy-MM-dd');
		// 	var header = new Date(start).format('yyyy-MM-dd') + "~" + end;
		// 	headers.push(header);
		// 	start = new Date(start).addDays(7);
		// 	i++;
		// }
		// return headers;
	}

	function getChartXlables(condition) {
		var headers = [];
		//1:天;2:周;3:月
		if (timeGran == 1) {
			for (var i = 0; i < 6; i++) {
				var header = new Date(condition.startTime).addDays(-4+i).format('yyyy-MM-dd');
				headers.push(header);
			}
			return headers;
		} else if (timeGran == 2) {
			for (var i = 0; i < 7; i++) {
				var header = new Date(condition.startTime).addDays(i).format('yyyy-MM-dd');
				headers.push(header);
			}
			return headers;
		}
		//选月时，显示周
		//构造动态header
		var start = new Date(condition.startTime).getFirstDateOfWeek();
		var endWeek = new Date(condition.endTime).getLastDateOfWeek();
		var i = 0;
		while (start < endWeek) {
			var end = new Date(start).addDays(6).format('yyyy-MM-dd');
			var header = new Date(start).format('yyyy-MM-dd') + "\n" + end;
			headers.push(header);
			start = new Date(start).addDays(7);
			i++;
		}
		return headers;
	}


    function initDataTable(condition, contextPath, ctxStatic, moduleId) {
		//获取动态表头
		var cols = createColumns();
		var sScrollXInner = "";
		cols.length > 8 ? sScrollXInner = "120%" : sScrollXInner = "100%";
		//重新加载表头
		initHeader(targetkpiNames);

		table = $('#saasTable').DataTable({
			"autoWidth" : true,
			"sScrollX" : "100%",
			"sScrollXInner" : sScrollXInner,
			"fixedColumns" : false,
			"fixedColumns" : {
				leftColumns : 0
			},
			"processing" : true,
			"serverSide" : true,
			"ajax" : {
				"url" : contextPath + "/app/appIndex/getAppIndex/"+moduleId,
				"type" : "post",
				"data" : function(d) {
					condition.dataType = 1; //1:app;2:行业;3:地域;4:终端
					condition.isConvertByDate = false;
					condition.perfList = targetkpis;
					d.condition = JSON.stringify(condition);
                    refreshConditionText(condition);
				},
				"dataSrc" : function(data) {
					myChart.dispose();
					initChart();
					//重新加载图表 采用双y轴形式展现数据
					//bindChartData(condition, data.data);-------------去掉之前的展现形式
                    if(chartype ==1){
                        uwayRefreshChart(targetkpiNames,targetkpis,seariseNames[0],seariseNames[1],myChart,table);
                    }
                   else if(chartype ==2){
                        bindChartData(condition, data.data);
                    }
                    else {
                        uwayRefreshChart(targetkpiNames,targetkpis,seariseNames[0],seariseNames[1],myChart,table);
                    }
                    $.uwayProcess.close();
					return data.data;
				},
				"error":function (e1,e2,e3) {
					if(e1.responseText.indexOf("<!DOCTYPE html>") != -1){
                        window.location = window.localStorage.getItem("CONTEXT_PATH") + '/user/login';
                    }
                }
			},
			"createdRow" : function(row, data, index) {
				//添加序号列
				var j = table.page.info().page * 10;
				if(index <3){
					var tmpindex  =index+ 1;
                    $('td', row).eq(0).html("<img src='/static/img/appicon/"+tmpindex+".png' height='30px'>");
				}else {
                    $('td', row).eq(0).html(index +j + 1);
				}

                $('td', row).eq(1).attr("name",data.appName);
                $('td', row).eq(1).attr("appId",data.app);
                //没有图片，则返回-1
                    $('td', row).eq(1).prepend("<img src='"+getImgUrl(data.app)+"' class='yj'> &nbsp;");

                $('td', row).eq(1).css('text-align',"left");
                $('td', row).eq(1).css('color',"#69c72b");
				//数据列中对齐
				// for(var i=2; i<cols.length; i++){
				// 	$('td', row).eq(i).css('text-align',"center");
				// }
			},
			"columnDefs": [{ "orderable": false, "targets": [0] }],
			"order": [[ 3, "desc" ]],
			"columns" : cols
		});
		$.uwayProcess.show();
		table.on('preXhr',function(){
			$.uwayProcess.show();
			myInterestAppsNow = getPanelAppData();
            $.uwayProcess.close();
		});
        if($(".dt-toolbar-footer")){
            $(".dt-toolbar-footer").css("display","none");
        }
        var lastIdx = null;
        $('#saasTable tbody')
            .on( 'mouseover', 'td', function () {
                var colIdx = table.cell(this).index().column;
                var rowIdx = table.cell(this).index().row;
                $(".highlight").removeClass( 'highlight' );

                if ( colIdx !== lastIdx ) {
                    //$( table.column( colIdx ).nodes() ).addClass( 'highlight' );
                }
                if ( rowIdx !== lastIdx ) {
                    $( table.rows( rowIdx ).nodes() ).addClass( 'highlight' );
                }
            } )
            .on( 'mouseleave', function () {
                $(".highlight").removeClass( 'highlight' );
            } );
    }
	
	//动态穿件表头
	function initHeader(cols) {
		var i = 0;
		// var firstTh = "<th class='uway-th'>序号</th><th class='uway-th' style='width:150px;'>APP</th><th class='uway-th' style='width:30px;'>关注</th><th class='uway-th' style='width:30px;'>对比</th>";
		// var secondTh = "<th style='background-color:#1B1E2C;'></th><th class='uway-th'></th><th style='background-color:#1B1E2C;'></th><th style='background-color:#1B1E2C;'></th>";
        var firstTh = "<th class='uway-th'>序号</th><th class='uway-th' >APP</th><th class='uway-th' >行业</th>";
        //var secondTh = "<th></th><th class='uway-th' ></th>";
		for (var i = 0; i < cols.length; i++) {
			firstTh += '<th class="uway-th">' + cols[i] + '</th>';
			//secondTh += '<th class="uway-th">' + targetName + '</th>' + '<th class="uway-th">' + targetRatioName + '</th>';
		}
		$("#firstRow").html(firstTh);
		//$("#secondRow").html(secondTh);
	}

    //点击chart表 查看几天的趋势数据
    function initHeaderdetail(cols) {
        var i = 0;
        // var firstTh = "<th class='uway-th'>序号</th><th class='uway-th' style='width:150px;'>APP</th><th class='uway-th' style='width:30px;'>关注</th><th class='uway-th' style='width:30px;'>对比</th>";
        // var secondTh = "<th style='background-color:#1B1E2C;'></th><th class='uway-th'></th><th style='background-color:#1B1E2C;'></th><th style='background-color:#1B1E2C;'></th>";
        var firstTh = "<th class='uway-th'>序号</th><th class='uway-th' >时间</th><th class='uway-th' >行业</th>";
        //var secondTh = "<th></th><th class='uway-th' ></th>";
        for (var i = 0; i < cols.length; i++) {
            firstTh += '<th class="uway-th">' + cols[i] + '</th>';
            //secondTh += '<th class="uway-th">' + targetName + '</th>' + '<th class="uway-th">' + targetRatioName + '</th>';
        }
        $("#firstRow").html(firstTh);
        //$("#secondRow").html(secondTh);
    }
	// ------------------------------DataTable处理  end------------------------------------------
	/**绑定chart数据
	*condition:查询条件对象;data:图表数据
	*/
	function bindChartData(condition, data) {
	    // debugger;
		var columns = createColumns(condition);
		//myChart = echarts.init(document.getElementById('mainChart'));
		//设置指标数据
		var legendData = [];
		$.each(data, function(i, v) {
			legendData.push(v.industryTypeName + ':' + v.appName);
		});
		//x轴数据绑定
		if(data.length > 0){
            var xlables = [];
            var xLabelLen = [];
            for (var i = 0; i < targetkpiNames.length; i++){
                xlables.push(targetkpiNames[i])
                xLabelLen.push(strlen(xlables[i]));
            }
            option.xAxis.data = xlables;
		}else{
			option.xAxis.data = [];
		}

        //设定x轴label的format
        var max1 = Math.max.apply(Math,xLabelLen)>15 ? Math.max.apply(Math,xLabelLen) : 44;

        option.xAxis.axisLabel.formatter = function(value, index){
            if (value != undefined) {
                var texts = [value.substring(0, parseInt(max1/4)), value.substring(parseInt(max1/4))];
                return texts.join('\n');
            } else {
                return null;
            }

        };
        //option.xAxis.axisLabel.rotate =30;
        option.legend.data = legendData;
		option.yAxis[0]['name'] = targetName;
		var k = 0;
		var nameGap = [];
		//y轴数据绑定
		$.each(data, function(j, v) {
			var yta = [];
			for (var i = 3; i < columns.length; i++) {
                yta.push(v[columns[i].data]);
				var yDataLine = new Object();
				yDataLine["name"] = legendData[j];
				yDataLine["data"] = yta;
				yDataLine["type"] = 'line';
				yDataLine["symbol"] = 'circle';
				yDataLine["symbolSize"] = 8;
			}
			option.series[k] = yDataLine;
			nameGap.push(Math.max.apply(Math,yta));
			k++;
		});

		//设定yAxis。nameGap
		var max = Math.max.apply(Math,nameGap);
		if (max/1000 > 1) {
			option.yAxis[0].nameGap = 15;
		} else {
			option.yAxis[0].nameGap = 15;
		}
		//新增留存分析最大值
        //新增留存分析最大值
        if(condition.maxValue){
            option.yAxis[0].max = 100;
        }

        //如果是新增流失分析,活跃留存分析从100开始
        if(condition.firstValue != null){
            var xData = option.xAxis.data;
            xData.unshift("");
            option.xAxis.data = xData;
            var ySeries = option.series;
            for(var i = 0;i<ySeries.length;i++){
                ySeries[i].data.unshift(condition.firstValue);
            }
            option.series = ySeries;
        }
		myChart.setOption(option);
		$.uwayProcess.close();
	}
	// ------------------------------chart处理  end----------------------------------------------


    function initDataTabledetail(condition, contextPath, ctxStatic, moduleId) {
        if(table){
            table.destroy();
            $("#tableContent").empty();
            var tableHtml = '<table id="saasTable" class="table table-striped table-bordered uway-bootstrap-table">'
                + '<thead>'
                + '<tr id="firstRow"></tr>'
                // + '<tr id="secondRow"></tr>'
                + '</thead>'
                + '<tbody>'
                + '</tbody>'
                + '</table>';
            $("#tableContent").append(tableHtml);
		}

        //获取动态表头
        var cols = createColumnsdetail(condition);
        var sScrollXInner = "";
        cols.length > 6 ? sScrollXInner = "120%" : sScrollXInner = "100%";
        //重新加载表头
        initHeaderdetail(targetkpiNames);

        table = $('#saasTable').DataTable({
            "autoWidth" : true,
            "sScrollX" : "100%",
            "sScrollXInner" : sScrollXInner,
            "fixedColumns" : false,
            "fixedColumns" : {
                leftColumns : 0
            },
            "processing" : true,
            "serverSide" : true,
            "ajax" : {
                "url" : contextPath + "/app/appIndex/getAppIndex/"+moduleId,
                "type" : "post",
                "data" : function(d) {
                    condition.dataType = 1; //1:app;2:行业;3:地域;4:终端
                    condition.isConvertByDate = false;
                    condition.perfList = targetkpis;
                    d.condition = JSON.stringify(condition);
                    refreshConditionText(condition);
                },
                "dataSrc" : function(data) {
                    myChart.dispose();
                    initChart();
                    //重新加载图表 采用双y轴形式展现数据
                    if(chartype ==1){
                        uwayRefreshChartdetail(targetkpiNames,targetkpis,seariseNames[0],seariseNames[1],myChart,data.data);
                    }
                    else if(chartype ==2){
                        bindChartData(condition, data.data);
                    }
                    else {
                        uwayRefreshChartdetail(targetkpiNames,targetkpis,seariseNames[0],seariseNames[1],myChart,data.data);
                    }
                    $.uwayProcess.close();
                    return data.data;
                },
                "error":function (e1,e2,e3) {
                    if(e1.responseText.indexOf("<!DOCTYPE html>") != -1){
                        window.location = window.localStorage.getItem("CONTEXT_PATH") + '/user/login';
                    }
                }
            },
            "createdRow" : function(row, data, index) {
                //添加序号列
                var j = table.page.info().page * 10;
                if(index <3){
                    var tmpindex  =index+ 1;
                    $('td', row).eq(0).html("<img src='/static/img/appicon/"+tmpindex+".png' height='30px'>");
                }else {
                    $('td', row).eq(0).html(index +j + 1);
                }
                //数据列中对齐
                for(var i=0; i<cols.length; i++){
                    $('td', row).eq(i).css('text-align',"center");
                }
            },
            "columnDefs": [{ "orderable": false, "targets": [0] }],
            "order": [[ 1, "asc" ]],
            "columns" : cols
        });
        $.uwayProcess.show();
        table.on('preXhr',function(){
            $.uwayProcess.show();
            myInterestAppsNow = getPanelAppData();
        });
        if($(".dt-toolbar-footer")){
            $(".dt-toolbar-footer").css("display","none");
        }

        var lastIdx = null;
        $('#saasTable tbody')
            .on( 'mouseover', 'td', function () {
                var colIdx = table.cell(this).index().column;
                var rowIdx = table.cell(this).index().row;
                $(".highlight").removeClass( 'highlight' );

                if ( colIdx !== lastIdx ) {
                    //$( table.column( colIdx ).nodes() ).addClass( 'highlight' );
                }
                if ( rowIdx !== lastIdx ) {
                    $( table.rows( rowIdx ).nodes() ).addClass( 'highlight' );
                }
            } )
            .on( 'mouseleave', function () {
                $(".highlight").removeClass( 'highlight' );
            } );
    }
    /**
     * 用户点击数据图后，会展现一个时间段数据详情
     * @param legendName
     * @param labelName
     * @param y1
     * @param y2
     * @param chartObject
     * @param tableObject
     */
    function uwayRefreshChartdetail(legendName, labelName, y1, y2,chartObject,tableObject) {
    	var legendName = legendName;
        var json = tableObject;
        //设置x轴数据：App名称
        var xData = [];
        var xLabelLen = [];
        var industry_type_name=[];
        if(json == null)
            return false;
        $.each(json, function(i, v) {
        	if(!xData.contains(v.startTime)){
                xData.push(v.startTime);
                xLabelLen.push(strlen(v.startTime));
			}
			if(!industry_type_name.contains(v.industryTypeName)){
                industry_type_name.push(v.industryTypeName)
			}
        });
        option.xAxis[0]['data'] = xData;

        //设定x轴label的format
        var max = Math.max.apply(Math,xLabelLen)>20 ? Math.max.apply(Math,xLabelLen) : 40;

        option.xAxis[0].axisLabel.formatter = function(value, index){

            if (value != undefined) {
                var texts = [value.substring(0, parseInt(max/4)), value.substring(parseInt(max/4))];
                return texts.join('\n');
            } else {
                return null;
            }
        };

        //设置指标数据
        var legendData = [];
        $.each(legendName, function(i) {
            $.each(industry_type_name, function(m) {
                legendData.push(industry_type_name[m]+":"+legendName[i]);
            });
        });
        option.legend['data'] = legendData;

        //设置y轴及指标数据
        //柱状图
        var labelName = labelName;
        var y1 = y1;
        var y2 = y2;
        option.series = [];
        var index=0;

        $.each(labelName, function(i, va) { //1.遍历标签数组label：2
            $.each(industry_type_name, function(n) {
                //legendData.push(industry_type_name[m]+":"+legendName[i]);
                var yta = [];
                $.each(json, function(j, v) {//2.传递遍历参数
                    if(v.industryTypeName == industry_type_name[n])
					{
                        yta.push(v[labelName[i]]);
					}
                });
                var yDataBar = new Object();
                yDataBar["name"] = industry_type_name[n] +":"+legendName[i];

                if($.inArray(labelName[i], seariseY1) != -1){
                    yDataBar["type"] = 'bar';
                    option.yAxis[0]['name'] = y1;
                }

                if($.inArray(labelName[i], seariseY2) != -1){
                    yDataBar["type"] = 'line';
                    yDataBar["yAxisIndex"] = 1;
                    option.yAxis[1]['name'] = y2;
                    yDataBar["symbol"] = 'circle';
                    yDataBar["symbolSize"] = 8;
                }

                json.length<10 ? yDataBar["barWidth"] = 45 : null;

                yDataBar["data"] = yta;
                option.series[index] = yDataBar;
                index++;
            });
        });
        chartObject.setOption(option,true);
    }

    function backpage() {
        // $("#backpage").css("display","none");
        // $("#totalButton").css("display","block");
        // $("#averageButton").css("display","block");
        $("#myTab").show();
        headerParams.appList = headerParams.tmpappList;
        var m = document.getElementsByName("appli");

        for(var i = 0; i <= m.length; i++) {
            m[i].parentNode.removeChild(m[i]);
        }

        window.localStorage.setItem("DATAFLAG",0);
        //复原点击的日均指标
        if (choose == 1){
            changeKpiToDavg();
        }
        $("#cearchul").show();
        refreshChartAndTable();
    }

    function getchartclcikdata() {
        var tmpappid = $("td[name="+headerParams.tmpappName+"]").attr("appId");
        if(tmpappid == null){
    	for (var i = 0; i < headerParams.appobjects.length; i++) {
            if (headerParams.appobjects[i].indexOf(","+headerParams.tmpappName+",") != -1) {
                tmpappid = headerParams.appobjects[i].split(",")[0];
                break;
            }
        }
        }

        var tmpappids = [];
        tmpappids.push(tmpappid);
        headerParams.appList = tmpappids;
        var condition = copyCondtion(headerParams);

        if(!condition.timeGran)
		{
            var timeCondition = getTimePeriod();
            $.extend(condition, timeCondition);
		}
        if(window.localStorage.getItem("DATAFLAG_HREF")==1){
            condition.startTime = new Date(condition.startTime).addDays(-6).toShortDateString('-');
            window.localStorage.setItem("DATAFLAG_HREF",0);
        }
        else {
            if (condition.timeGran == 1){
                condition.startTime = new Date(condition.startTime).addDays(-6).toShortDateString('-');
            }
        }
        condition.timeGran = 1;
        // 如果是日均界面点击，暂时将指标改为默认，在返回时改回
        if (choose != 0) {
            changeKpiToDefault();
        }
        $("#cearchul").hide();
        refreshConditionText(condition);
        initDataTabledetail(condition, contextPath, ctxStatic, 321);
        $(".breadcrumb").append('<li id="appli" name="appli"  onclick="backpage()" style="cursor:pointer; ">' + headerParams.tmpappName + '<i class="icon iconfont icon-chahao inline-icon"></i></li>');
        // $("#backpage").css("display", "block");
        $("#totalButton").css("display", "none");
        $("#averageButton").css("display", "none");
        $("#myTab").hide();
        window.localStorage.setItem("DATAFLAG", 1);
        //跳转结束，将headerParams时间条件重置
        $.extend(headerParams, getTimePeriod());
        window.localStorage.setItem("HEADER_PARAMS", JSON.stringify(headerParams));
    }
