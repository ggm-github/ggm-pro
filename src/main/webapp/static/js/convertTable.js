	var timeGran;
	var contextPath;
	var ctxStatic;
	var myInterestAppsNow = getPanelAppData();
	$(document).ready(function() {
		$.uwayProcess.show();
	});
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
					height : 30,
					padding : 0
				},
				grid : {
					left : '3%',
					right : '4%',
					top : '30%',
					bottom : '5%',
					containLabel : true,
					borderColor : '#2a2f32',
				},
				toolbox : {
					show : true,
					y : 50,
					orient : 'vertical',
					feature : {
						mark : {
							show : true
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
							backgroundColor : 'black',
							iconStyle : {
								normal : {
									borderColor : '#D26732',
								},
								emphasis : {
									borderColor : '#82CF19',
								}
							},
						},
					}
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
					},
					axisLabel : {
						interval : 0,
						/* rotate : 25, */
						textStyle : {
							color : '#2a2f32'
						}
					},
					data : [],
				},
				yAxis : [ {
					type : 'value',
					name : yAxisName,
					min : 0,
					nameTextStyle : {
						color : '#2a2f32'
					},
					splitLine : {
						show : true,
						lineStyle : {
							color : '#2a2f32',
							type : 'dotted',
						}
					},
					nameLocation : 'middle',
					nameGap : 50,
					boundaryGap : ['50%', '20%'],
					axisTick : {
						show : false,
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
	};
	//刷新Table 
	function refreshTable(condition, contextPath, ctxStatic, indexId) {
		table.destroy();
		$("#tableContent").empty();
		var tableHtml = '<table id="saasTable" class="table table-striped table-bordered uway-bootstrap-table">'
			+ '<thead>'
			+ '<tr id="firstRow"></tr>'
		    + '<tr id="secondRow">'
			+ '</tr>'
			+ '</thead>'
			+ '<tbody>'
			+ '</tbody>'
			+ '</table>';
		$("#tableContent").append(tableHtml);
		$.uwayProcess.show();
		initDataTable(condition, contextPath, ctxStatic, indexId);
	}
	// ------------------------------DataTable处理  start------------------------------------------
	//构造DataTable数据绑定columns
	function createColumns(condition) {
		var cols = [];
		var app = [ 'app', 'appName'];
		//构造固定列
		for (var i = 0; i < app.length; i++) {
			var data = {};
			data.data = app[i];
			data.bSearchable = false;
			cols.push(data);
		}
		
		//添加关注列
		var data = {};
		data.data = function(e){
			 return uwayReturnTableMyInterestColumnHtml(e);	  
        };
        data.bSearchable = false;
		cols.push(data);
		
		//添加对比列
		var data = {};
		data.data = function(e){
	         return uwayReturnTableCompareColumnHtml(e);
        };
        data.bSearchable = false;
		cols.push(data);
		
		//1:天;2:周;3:月
		if (timeGran == 1) {
			/*var data = {};
			var strDate = new Date(condition.startTime).format('yyyyMMdd');
			data.data = target + "_" + strDate;
			data.bSearchable = false;
			cols.push(data);*/
			
			for (var i = 0; i < 5; i++) {
				var strDate = new Date(condition.startTime).addDays(-4+i).format('yyyyMMdd');
				var data = {};
				data.data = target + "_" + strDate;
				data.bSearchable = false;
				cols.push(data);
			}
			return cols;
		} else if (timeGran == 2) {
			for (var i = 0; i < 7; i++) {
				var strDate = new Date(condition.startTime).addDays(i).format('yyyyMMdd');
				var data = {};
				data.data = target + "_" + strDate;
				data.bSearchable = false;
				cols.push(data);
			}
			return cols;
		}
		//选月时，显示周
		//构造动态header
		var start = new Date(condition.startTime).getFirstDateOfWeek();
		var endWeek = new Date(condition.endTime).getLastDateOfWeek();
		while (start < endWeek) {
			var data = {};
			var strDate = new Date(start).format('yyyyMMdd');
			data.data = target + "_" + strDate;
			data.bSearchable = false;
			cols.push(data);
			start = new Date(start).addDays(7);
		}
		return cols;
	}
	function getHeaders(condition) {
		var headers = [];
		//1:天;2:周;3:月
		if (timeGran == 1) {
			/*headers.push(condition.startTime);
			return headers;*/
			for (var i = 0; i < 5; i++) {
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
			var header = new Date(start).format('yyyy-MM-dd') + "~" + end;
			headers.push(header);
			start = new Date(start).addDays(7);
			i++;
		}
		return headers;
	}
	function getChartXlables(condition) {
		var headers = [];
		//1:天;2:周;3:月
		if (timeGran == 1) {
			for (var i = 0; i < 5; i++) {
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
    
	function initDataTable(condition, contextPath, ctxStatic, indexId) {
		//获取动态表头
		var headers = getHeaders(condition);
		var cols = createColumns(condition);
		//重新加载表头
		initHeader(headers);
		table = $('#saasTable').DataTable({
			"sScrollX" : "100%",
			"autoWidth" : true,
			"scrollX" : true,
			"processing" : true,
			"serverSide" : true,
			"ajax" : {
				"url" : contextPath + "/app/appIndex/getAppIndex/" + indexId,
				"type" : "post",
				"data" : function(d) {
					if (timeGran == 2) {
						condition.timeGran = 1;
					} else if (timeGran == 3) {
						condition.timeGran = 2;
					}
					condition.dataType = 1; //1:app;2:行业;3:地域;4:终端
					condition.isConvertByDate = true;
					condition.perfList = [target];
					d.condition = JSON.stringify(condition);
				},
				"dataSrc" : function(data) {
					myChart.dispose();
					initChart();
					//重新加载图表
					bindChartData(condition, data.data);
					$.uwayProcess.close();
					return data.data;
				}
			},
			"createdRow" : function(row, data, index) {
				var j = table.page.info().page * 10;
				$('td', row).eq(0).html(index +j + 1);
			},
			"columnDefs": [{ "orderable": false, "targets": [0] }],
			"order": [[ 4, "desc" ]],
			"columns" : cols
		});
		//uwayChartLoading(myChart);
		table.on('preXhr',function(){
			$.uwayProcess.close();
			//uwayChartLoading(myChart);
			myInterestAppsNow = getPanelAppData();
		});	
	}
	
	//动态穿件表头
	function initHeader(headers) {
		var i = 0;
		var firstTh = "<th class='uway-th'>序号</th><th class='uway-th' style='width:150px;'>APP</th><th class='uway-th' style='width:30px;'>关注</th><th class='uway-th' style='width:30px;'>对比</th>";
		var secondTh = "<th></th><th class='uway-th'></th><th></th><th></th>";
		for (var i = 0; i < headers.length; i++) {
			firstTh += '<th class="uway-th">' + headers[i] + '</th>';
			secondTh += '<th class="uway-th">' + targetnum + '</th>';
		}
		$("#firstRow").html(firstTh);
		$("#secondRow").html(secondTh);
	}
	// ------------------------------DataTable处理  end------------------------------------------
	/**绑定chart数据
	*condition:查询条件对象;data:图表数据
	*/
	function bindChartData(condition, data) {
		//var option = myChart.getOption();
		
		var columns = createColumns(condition);
		//myChart = echarts.init(document.getElementById('mainChart'));
		//设置指标数据
		var legendData = [];
		$.each(data, function(i, v) {
			legendData.push(v.appName);
		});
		//x轴数据绑定
		if(data.length > 0){
		option.xAxis.data = getChartXlables(condition);
		}else{
			option.xAxis.data = [];
		}
		option.legend.data = legendData;
		option.yAxis[0]['name'] = targetnum;
		var k = 0;
		var nameGap = [];
		//y轴数据绑定
		$.each(data, function(j, v) {
			var yta = [];
			for (var i = 4; i < columns.length; i++) {
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
			option.yAxis[0].nameGap = 70;
		} else {
			option.yAxis[0].nameGap = 35;
		}
		
		myChart.setOption(option);
		myChart.hideLoading();
	}
	// ------------------------------chart处理  end----------------------------------------------