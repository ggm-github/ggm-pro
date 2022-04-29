
	/** 行转列通用方法
	 * jsonData:json数据对象
	 * idField:行转列主键,如:APPID
	 * fromField:指标列表,多个用","分隔
	 * toField:转换列,如:时间属性名
	 * defualtField:主键属性列,多个用","分隔
	 * */
	function CovertData(jsonData, idField, fromField, toField, defualtField) {
		var result = [], curRecord = null, num;
		var fromFields = fromField.split(',');
		var defualtFields = null;
		if (defualtField) {
			defualtFields = defualtField.split(',');
		}
		// 循环整个数组：[{...},{...},{...},...]
		for (var idx = 0; idx < jsonData.length; idx++) {
			num = findIdx(result, idField, jsonData[idx][idField]);
			if (num != -1) {
				curRecord = result[num];
			} else {
				curRecord = {};
			}
			;
			// 循环每个json对象中的字段
			for ( var key in jsonData[idx]) {
				// 处理转换的数据内容
				for (var i = 0; i < fromFields.length; i++) {
					if (key == fromFields[i]) {
						curRecord[jsonData[idx][toField] + '-' + fromFields[i]] = jsonData[idx][key];
						break;
					}
				}
				// 除数据内容外，只处理标识字段数据
				if (key == idField) {
					curRecord[key] = jsonData[idx][key];
				}
				if (defualtFields) {
					for (var i = 0; i < defualtFields.length; i++) {
						if (key == defualtFields[i]) {
							curRecord[key] = jsonData[idx][key];
							break;
						}
					}
				}

			}
			if (num == -1) {
				result.push(curRecord);
			}
		}
		return result;
	}

	function findIdx(jsonData, columnName, value) {
		for (var idx = 0; idx < jsonData.length; idx++) {
			if (jsonData[idx][columnName] == value)
				return idx;
		}
		return -1;
	}

	/**
	 * saas系统通用 通知 提示框 左下角
	 * @param title
	 * @param content
	 */
	function uwayNotice(title,content){
		    var noticeColors = ['green', 'blue', 'yellow'];
		    var color = noticeColors[uwayGetRandom(0,3)];
			new jBox('Notice', {
				minWidth:300,
				autoClose: 2000,
				attributes: {
					x: 'left',
					y: 'bottom'
				},
				audio:'/static/sound/smallbox',
				stack: false,
				animation: {open: 'tada', close: 'zoomIn'},
				title: '<span style="font-size:16px;">'+ title +'</span>',
				content: '<span style="font-size:13px;font-weight:600;">'+ content +'</span>',
				color:'blue'
			});
	}

	/**
	 * saas系统通用 警告 提示框 右上角
	 * @param title
	 * @param content
	 */
	function uwayWarning(title,content){
			new jBox('Notice', {
				minWidth:300,
				autoClose: 4000,
				attributes: {
					x: 'right',
					y: 'top'
				},
				audio:'/static/sound/smallbox',
				stack: false,
				animation: {open: 'slide', close: 'zoomIn'},
				title: '<span style="font-size:16px;">'+ title +'</span>',
				content: '<span style="font-size:13px;font-weight:600;">'+ content +'</span>',
				color:'yellow'
			});
	}

	/**
	 * saas系统通用 确认 提示框
	 * @param option 包含：content confirm cancel
	 */
	function uwayConfirm(option){
		new jBox('Confirm', {
		    confirmButton: '取消',
		    cancelButton: '确认',
			content: '<span style="font-size:17px;font-weight:700;">'+ option.content +'</span>',
			confirm: option.cancel,
			cancel: option.confirm
	  }).open();
	}

    /**
     * saas系统通用 获取 min max 之间随机数方法
     * @param min
     * @param max
     * @returns
     */
	function uwayGetRandom(min,max){
		var Range = max - min;
		var Rand = Math.random();
		return(min + Math.round(Rand * Range));
	}

	/**
	 * saas系统通用 阻止各浏览器事件冒泡
	 * @param evt
	 */
	function uwayStopBubble(evt){
		  var e=(evt)?evt:window.event;
		  if (window.event) {
		       e.cancelBubble=true;
		  } else {
		       e.stopPropagation();
		  }
	  }

	/**
	 * 校验是否是手机号码
	 * @param string
	 * @returns {Boolean}
	 */
	function uwayCheckMobile(string){
		var pattern = /^1[34578]\d{9}$/;
		if (pattern.test(string)) {
			return true;
		}
		return false;
	};

	/**
	 * 校验是否是邮箱
	 * @param string
	 * @returns {Boolean}
	 */
	function uwayCheckEmail(string){
		var pattern = /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))$/;
		if (pattern.test(string)) {
			return true;
		}
		return false;
	}

    /* 总体和日均的切换变更指标值    */
    function changeKpiToDefault(){
        targetkpis = defaultTargetkpis;
        targetkpiNames = defaultTargetkpiNames;
        seariseY1 = defaultSeariseY1;
        seariseY2 = defaultSeariseY2;
    }
    function changeKpiToDavg() {
        targetkpis = davgTargetkpis;
        targetkpiNames = davgTargetkpiNames;
        seariseY1 = davgSeariseY1;
        seariseY2 = davgSeariseY2;
    }
    // 总体日均按钮切换
    function clickCommon(){
        var condition = copyCondtion(getSearchCondition());

        if ($("span[name='spanChecked']").attr("id") == "totalButton") {
            choose = 0;
            changeKpiToDefault();
        } else if ($("span[name='spanChecked']").attr("id") == "averageButton") {
            choose = 1;
            changeKpiToDavg();
        }
        refreshTable(condition,contextPath, ctxStatic, moduleId);
    }
	/**
	 * 获取 chart的通用option
	 */
	function uwayGetCommonChartOption(title){
		return {
			title : {
				text : title,
				textStyle : {
					color : '#2a2f32',
					fontWeight : 'normal',
					fontSize : 14,
				},
				left : 30,
			},
			tooltip : {
				trigger : 'axis',
				extraCssText : 'z-index: 2;'
			},
			color : ['#b6db96', 'rgb(64, 211, 243)', 'rgb(219, 192, 51)', 'rgb(255, 242, 0)',
                '#91c7ae','#749f83',  '#ca8622', '#bda29a','#6e7074', '#546570', '#c4ccd3'],
			toolbox : {
				show : true,
				y : 50,
				orient : 'vertical',
                itemGap: 15,
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
				z:11,
			},
			calculable : true,
			legend : {
				data : [],
				textStyle : {
					color : '#2a2f32',
				},
				itemWidth : 20,
				itemGap : 20,
				top : 25,
			},
			grid : {
				left : '4%',
				right : '3%',
                top : '120',
				containLabel : true,
				color : '#24293D',

			},
			xAxis : [ {
				type : 'category',
				splitLine : {
					show : true,
					lineStyle : {
						color : '#24293D',
						type : 'dotted',
					}
				},
				axisTick : {
					show : false,
                    alignWithLabel: true
				},
				axisLabel : {
					interval : 0,
					rotate : 30,
                    margin: 15,
					textStyle : {
						color : '#2a2f32',
					},
				},
				data : []/*x轴数据*/
			} ],
			yAxis : [ {
				type : 'value',
				name : '活跃用户数(万)',
				nameTextStyle :{
					color : '#2a2f32'
				},
				splitLine : {
					show : true,
					lineStyle : {
						color : '#24293D',
						type : 'dotted',
					}
				},
				axisTick : {
					show : false,
				},
				axisLabel : {
					formatter : '{value}',
					textStyle:{
					 	color:'#2a2f32'
					}
				}
			}, {
				type : 'value',
				name : '比例(%)',
				nameTextStyle :{
					color : '#2a2f32'
				},
				splitLine : {
					show : false,
					lineStyle : {
						color : '#2a2f32',
						type : 'dotted',
					}
				},
				axisTick : {
					show : false,
				},
				axisLabel : {
					formatter : '{value}',
					textStyle:{
					 	color:'#2a2f32'
					}
				}
			}, ],
			series : []
		};
	};


	function uwayGetIndexChartOption(title,unit){
		return {
			title : {
				text : title,
				textStyle : {
					color : '#2a2f32',
					fontWeight : 'normal',
					fontSize : 14,
				},
				left : 10,
			},textStyle:{
                color:'#2a2f32'
            },
			tooltip : {
				trigger : 'axis',
				extraCssText : 'z-index: 2;'
			},
			color : ['rgb(64, 211, 243)','#b6db96',  'rgb(219, 192, 51)', 'rgb(255, 242, 0)', '#91c7ae','#749f83',  '#ca8622', '#bda29a','#6e7074', '#546570', '#c4ccd3'],
			toolbox : {
				show : true,
				y : 50,
				orient : 'vertical',
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
				z:11,
			},
			calculable : true,
			legend : {
				data : [],
				textStyle : {
					color : '#fff',
				},
				itemWidth : 20,
				itemGap : 20,
				top : 25,
			},
			grid : {
				left : '0%',
				right : '4%',
                top  : '120',
				containLabel : true,
				color : '#24293D',

			},
			xAxis : [ {
				type : 'category',
				splitLine : {
					show : true,
					lineStyle : {
						color : '#24293D',
						type : 'dotted',
					}
				},
				axisTick : {
					show : false,
				},
				axisLabel : {
					interval : 0,
					rotate : 30,
					textStyle : {
						color : '#2a2f32',
					}
				},
				data : []/*x轴数据*/
			} ],
			yAxis : [ {
				type : 'value',
				name : '活跃用户数('+unit+')',
				nameTextStyle :{
					color : '#2a2f32'
				},
				splitLine : {
					show : true,
					lineStyle : {
						color : '#24293D',
						type : 'dotted',
					}
				},
				axisTick : {
					show : false,
				},
				axisLabel : {
					formatter : '{value}',
					textStyle:{
					 	color:'white'
					}
				},
				max:'dataMax',
				min:'dataMin'
			}],
			series : []
		};
	};


	function uwayGetIndexAPPChartOption(title,unit){
		return {
			title : {
				text : title,
				textStyle : {
					color : '#2a2f32',
					fontWeight : 'normal',
					fontSize : 14,
				},
				left : 10,
			},textStyle:{
                color:'#2a2f32'
            },
			tooltip : {
				trigger : 'axis',
				extraCssText : 'z-index: 2;',
				/*formatter: function(params){
                    var res="";
                    res+=params[0][1]+":" ;
                    for (var i = 0, l = params.length; i < l; i++) {
                        res += '<br/>' + params[i][0] + ' : ' + params[i][2]+"%";
                    }
                    return res;
                }*/
			},
			color : ['rgb(237, 125, 49)', 'rgb(64, 211, 243)', 'rgb(219, 192, 51)', 'rgb(255, 242, 0)', '#91c7ae','#749f83',  '#ca8622', '#bda29a','#6e7074', '#546570', '#c4ccd3'],
			toolbox : {
				show : true,
				y : 50,
				orient : 'vertical',
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
				z:11,
			},
			calculable : true,
			legend : {
				data : [],
				textStyle : {
					color : '#2a2f32',
				},
				itemWidth : 20,
				itemGap : 20,
				top : 25,
			},
			grid : {
				left : '2.2%',
				right : '4%',
                top : '120',
				containLabel : true,
				color : '#24293D',

			},
			xAxis : [ {
				type : 'category',
				splitLine : {
					show : true,
					lineStyle : {
						color : '#24293D',
						type : 'dotted',
					}
				},
				axisTick : {
					show : false,
				},
				axisLabel : {
					interval : 0,
					rotate : 30,
					textStyle : {
						color : '#2a2f32',

					}
				},
				data : []/*x轴数据*/
			} ],
			yAxis : [ {
				type : 'value',
				name : 'APP新增用户数('+unit+')',
				nameTextStyle :{
					color : '#2a2f32'
				},
				splitLine : {
					show : true,
					lineStyle : {
						color : '#24293D',
						type : 'dotted',
					}
				},
				axisTick : {
					show : false,
				},
				axisLabel : {
					formatter : '{value}',
					textStyle:{
					 	color:'#2a2f32'
					}
				},
				max:'dataMax',
				min:'dataMin'

			}, {
				type : 'value',
				name : '行业新增用户数('+unit+')',
				nameTextStyle :{
					color : '#2a2f32'
				},
				splitLine : {
					show : false,
					lineStyle : {
						color : '#24293D',
						type : 'dotted',
					}
				},
				axisTick : {
					show : false,
				},
				axisLabel : {
					formatter : '{value}',
					textStyle:{
					 	color:'#2a2f32'
					}
				},
				max:'dataMax',
				min:'dataMin'

			}, ],
			series : []
		};
	};
	/**
	 * chart 数据加载中
	 */
	function uwayChartLoading(chartObject){
		chartObject.showLoading('default',{text: '数据加载中...',
			  color: 'rgb( 231, 231, 231)',
			  textColor: 'rgb( 231, 231, 231)',
			  maskColor: 'rgba( 35, 35, 35, 0.7)',
			  zlevel: 0});
	}


	/**
	 *
	 * @param legendName
	 * @param labelName
	 * @param y1
	 * @param y2
	 * @param chartObject
	 * @param tableObject
	 */
	function uwayRefreshChart(legendName, labelName, y1, y2,chartObject,tableObject) {
		var legendName = legendName;
		var json = tableObject.ajax.json();

		if(json == undefined){
			return;
		}
		//设置x轴数据：App名称
		var xData = [];
		var xLabelLen = [];

		$.each(json.data, function(i, v) {
			xData.push(v.appName);
			xLabelLen.push(strlen(xData[i]));
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
			legendData.push(legendName[i]);
		});
		option.legend['data'] = legendData;

		//设置y轴及指标数据
		//柱状图
		var labelName = labelName;
		var y1 = y1;
		var y2 = y2;
		option.series = [];
		$.each(labelName, function(i, va) { //1.遍历标签数组label：2

			var yta = [];
			$.each(json.data, function(j, v) {//2.传递遍历参数
				yta.push(v[labelName[i]]);
			});
			var yDataBar = new Object();
			yDataBar["name"] = legendName[i];

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

				// if (i % 2 != 0) {
				// 	yDataBar["type"] = 'line';
				// 	yDataBar["yAxisIndex"] = 1;
				// 	option.yAxis[1]['name'] = y2;
				// 	yDataBar["symbol"] = 'circle';
				// 	yDataBar["symbolSize"] = 8;
				// } else {
				// 	yDataBar["type"] = 'bar';
				// 	option.yAxis[0]['name'] = y1;
				// }

            json.data.length<10 ? yDataBar["barWidth"] = 45 : null;
			yDataBar["data"] = yta;
			option.series[i] = yDataBar;
		});
		chartObject.setOption(option);
        /*chartObject.on('click', function (params) {
            var hasDataDetail;
            try {
                if(dataDetail && typeof(dataDetail)=="function")
                    hasDataDetail = true;
            }catch (e) {
                hasDataDetail = false;
            }
            if (hasDataDetail) {
                dataDetail(params);
            }
        });*/
	}


	(function($) {
		   var format = function(fmt) {
		      fmt = fmt || "yyyy-MM-dd HH:mm";

		      var o = {
		         "M+": this.getMonth() + 1, //月份
		         "d+": this.getDate(), //日
		         "h+": this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, //小时
		         "H+": this.getHours(), //小时
		         "m+": this.getMinutes(), //分
		         "s+": this.getSeconds(), //秒
		         "q+": Math.floor((this.getMonth() + 3) / 3), //季度
		         "S": this.getMilliseconds() //毫秒
		      };
		      var week = {
		         "0": "/u65e5",
		         "1": "/u4e00",
		         "2": "/u4e8c",
		         "3": "/u4e09",
		         "4": "/u56db",
		         "5": "/u4e94",
		         "6": "/u516d"
		      };

		      if(/(y+)/.test(fmt)) {
		         fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
		      }

		      if(/(E+)/.test(fmt)) {
		         fmt = fmt.replace(RegExp.$1, ((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "/u661f/u671f" : "/u5468") : "") + week[this.getDay() + ""]);
		      }

		      for(var k in o) {
		         if(new RegExp("(" + k + ")").test(fmt)) {
		            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
		         }
		      }

		      return fmt;
		   };

		   var timeline = function() {
		      var interval = +new Date() - this.getTime();
		      var hour = 60 * 60 * 1000;
		      //小于1分钟
		      if(interval < 60 * 1000) {
		          return "刚刚";
		      }
		      else if(interval < hour) {
		         return  Math.floor(interval / (60 * 1000)) + "分钟前";
		      }
		      // 小于1天
		      else if(interval < 24 * hour) {
		         return  Math.floor(interval / hour) + "小时前";
		      }

		      return Math.floor(interval / (24 * hour)) + "天前";
		   };

		   Date.prototype.format = format;
		   Date.prototype.timeline = timeline;
		})(jQuery);

	Date.prototype.addDays = function(number){
	    var adjustDate = new Date(this.getTime() + 24*60*60*1000*number)
	    return adjustDate;
	}
	//得到每周的第一天(周一)
	Date.prototype.getFirstDateOfWeek = function (){
		var firstDateOfWeek = this;
		if (this.getDay() > 0) {
			firstDateOfWeek =this.setDate(this.getDate() - this.getDay() + 1);
		} else {
			firstDateOfWeek =this.setDate(this.getDate() - 6);
		}
		return firstDateOfWeek;
	}
	//得到每周的最后一天(周日)
	Date.prototype.getLastDateOfWeek = function (){
		var lastDateOfWeek = this;
		if (this.getDay() > 0) {
			lastDateOfWeek =this.setDate(this.getDate() + 7 - this.getDay());
		} else {
			lastDateOfWeek =this.setDate(this.getDate());
		}
		return lastDateOfWeek;
	}

	function copyCondtion(condition) {
		var retCon = {};
		var params=["selectType", "timeGran", "startTime","endTime","dataType","appList","indList","osType","searchdataType"];
		for (var i = 0; i< params.length; i++) {
		    if(condition[params[i]]){
                retCon[params[i]] = condition[params[i]];
            }
		}
		return retCon;
	}

	//计算中英文字符串的长度
	 function strlen(str) {
	        var len = 0;
	        for (var i = 0; i < str.length; i++) {
	            var c = str.charCodeAt(i);
	            //单字节加1
	            if ((c >= 0x0001 && c <= 0x007e) || (0xff60 <= c && c <= 0xff9f)) {
	                len++;
	            }
	            else {
	                len += 2;
	            }
	        }
	        return len;
	    }

	 var base64EncodeChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
	 var base64DecodeChars = new Array(-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1);
	 /**
	  * base64编码
	  * @param {Object} str
	  */
	 function base64encode(str){
	     var out, i, len;
	     var c1, c2, c3;
	     len = str.length;
	     i = 0;
	     out = "";
	     while (i < len) {
	         c1 = str.charCodeAt(i++) & 0xff;
	         if (i == len) {
	             out += base64EncodeChars.charAt(c1 >> 2);
	             out += base64EncodeChars.charAt((c1 & 0x3) << 4);
	             out += "==";
	             break;
	         }
	         c2 = str.charCodeAt(i++);
	         if (i == len) {
	             out += base64EncodeChars.charAt(c1 >> 2);
	             out += base64EncodeChars.charAt(((c1 & 0x3) << 4) | ((c2 & 0xF0) >> 4));
	             out += base64EncodeChars.charAt((c2 & 0xF) << 2);
	             out += "=";
	             break;
	         }
	         c3 = str.charCodeAt(i++);
	         out += base64EncodeChars.charAt(c1 >> 2);
	         out += base64EncodeChars.charAt(((c1 & 0x3) << 4) | ((c2 & 0xF0) >> 4));
	         out += base64EncodeChars.charAt(((c2 & 0xF) << 2) | ((c3 & 0xC0) >> 6));
	         out += base64EncodeChars.charAt(c3 & 0x3F);
	     }
	     return out;
	 }
	 /**
	  * base64解码
	  * @param {Object} str
	  */
	 function base64decode(str){
	     var c1, c2, c3, c4;
	     var i, len, out;
	     len = str.length;
	     i = 0;
	     out = "";
	     while (i < len) {
	         /* c1 */
	         do {
	             c1 = base64DecodeChars[str.charCodeAt(i++) & 0xff];
	         }
	         while (i < len && c1 == -1);
	         if (c1 == -1)
	             break;
	         /* c2 */
	         do {
	             c2 = base64DecodeChars[str.charCodeAt(i++) & 0xff];
	         }
	         while (i < len && c2 == -1);
	         if (c2 == -1)
	             break;
	         out += String.fromCharCode((c1 << 2) | ((c2 & 0x30) >> 4));
	         /* c3 */
	         do {
	             c3 = str.charCodeAt(i++) & 0xff;
	             if (c3 == 61)
	                 return out;
	             c3 = base64DecodeChars[c3];
	         }
	         while (i < len && c3 == -1);
	         if (c3 == -1)
	             break;
	         out += String.fromCharCode(((c2 & 0XF) << 4) | ((c3 & 0x3C) >> 2));
	         /* c4 */
	         do {
	             c4 = str.charCodeAt(i++) & 0xff;
	             if (c4 == 61)
	                 return out;
	             c4 = base64DecodeChars[c4];
	         }
	         while (i < len && c4 == -1);
	         if (c4 == -1)
	             break;
	         out += String.fromCharCode(((c3 & 0x03) << 6) | c4);
	     }
	     return out;
	 }

    Array.prototype.contains = function ( needle ) {
        for (i in this) {
            if (this[i] == needle) return true;
        }
        return false;
    }
    //判断路径是否存在
    function IsExist(url) {
        try {
            var xmlHttp;
            if (window.ActiveXObject) {
                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
            }
            else if (window.XMLHttpRequest) {
                xmlHttp = new XMLHttpRequest();
            }
            xmlHttp.open("Get", url, false);

            xmlHttp.send();
            if (xmlHttp.status == 404)
                return false;
            else
                return true;
        }
        catch (exception) {
            return false;
        }
    }

    var imgversion = "?v=20180109";
	 /**
      * 获取图片路径，没有返回空路径
      * **/
     function getImgUrl(id) {
        var url = ctxStatic +'/img/appicon/'+id+'.jpg'+imgversion;
        // if(!IsExist(url)){
        //     url = ctxStatic+'/img/appicon/null.png';
        // }
        return url;
    }

    /**
     * 对过长字符串进行截取,中文算两个字符，英文占一个字符
     * **/

    function getString(str, length) {
        var len = 0;
        if (str == null || str == "") {
            return "";
        }
        for (var i = 0; i < str.length; i++) {
            if (len >= length) {
                return str.substring(0, i) + "...";
            }
            var c = str.charCodeAt(i);
            //单字节加1
            if ((c >= 0x0001 && c <= 0x007e) || (0xff60 <= c && c <= 0xff9f)) {
                len++;
            }
            else {
                len += 2;
            }
        }
        return str;
    }

    function tableReload() {
        if (typeof  table != "undefined"){
            table.draw();
        }
    }

