/**
 * 公共函数库，主要是一些JS工具函数，各种插件的公共设置
 * @author HenryYan
 */
(function($) {
	
	$.common = {};
	
	//-- 初始化方法 --//
	_initFunction();
	
	/*******************************************/
	/**				jqGrid插件--开始			  **/
	/*******************************************/
	var _plugin_jqGrid = {
		prmNames : {
			page : 'page.pageNo',
			rows : 'page.pageSize',
			sort : 'page.orderBy',
			order : 'page.order',
			search : 'page.search',
			id : 'jqid'
		},
		jsonReader : {
			root : 'page.result',
			page : 'page.pageNo',
			total : 'page.totalPages',
			records : 'page.totalCount',
			repeatitems : false
		},
		pager : {
			add: true,
	        edit: true,
			view: true,
	        del: true,
			searchtext: '查询',
			refreshtext: '刷新'
		},
		navGrid : {
			/**
			 * 显示对话框字段，如果不指定fields参数显示所有隐藏的字段
			 * @param {Object} formid	jqGrid表单ID
			 * @param {Object} includes	要显示的字段名称，规则tr_字段
			 * @param {Object} excludes	要显示的字段名称，规则tr_字段
			 */
			showAllField : function(formid, includes, excludes) {
				if (includes && includes.length > 0) {
					$.each(includes, function(i, n){
						// 编辑时
						$('tr[id=tr_' + n + ']', formid).show();
						// 查看时
						$('tr[id=trv_' + n + ']', formid).show();
					});
					return;
				}
				if (excludes && excludes.length > 0) {
					// 编辑时
					var $trs = $('tr[id^=tr_].FormData', formid);
					$.each($trs, function(){
						var fieldName = $(this).attr('id').substring(3);
						if($.inArray(fieldName, excludes) == -1) {
							$(this).show();
						}
					});
					
					// 查看时
					$trs = $('tr[id^=trv].FormData', formid);
					$.each($trs, function(){
						var fieldName = $(this).attr('id').substring(4);
						if($.inArray(fieldName, excludes) == -1) {
							$(this).show();
						}
					});
					return;
				}
				$('tr[id^=tr].FormData', formid).show();
			}
		},
		//-- jqGrid工具栏按钮 --//
		navButtonAdd : {
			//-- 显示/隐藏字段 --//
			setColumns : {
				caption : "字段",
				title : "设置列表显示的字段",
				buttonicon : "ui-icon-wrench",
				onClickButton : function() {
					$(this).jqGrid('columnChooser');
				}
			}
		},
		//-- 搜索比较符号 --/
		search: {
			text : ['eq', 'ne', 'cn'],
			select : ['eq', 'ne'],
			integer : ['eq', 'ne', 'lt', 'le', 'gt', 'ge'],
			float : ['eq', 'ne', 'lt', 'le', 'gt', 'ge'],
			date : ['eq', 'ne', 'lt', 'le', 'gt', 'ge'],
			// 初始化My97日期组件
			initDate : function(settings) {
				$(settings.elem).addClass('Wdate');
				$(settings.elem).click(function(){
					WdatePicker();
				});
			}
		},
		//-- 格式化 值--//
		formatter:{
			// 日期类型，例如：2010-08-19
			date: function(cellvalue, options, cellobject){
				if( cellvalue == null || cellvalue == 'null' ) return "";
				else{
					if(cellvalue.length >= 10){
						return cellvalue.substring(0,10);
					} else { 
						return cellvalue;
					}
				}
			},
			// 日期和时间类型，例如：2010-08-19 12:12:13
			datetime: function(cellvalue, options, cellobject){
				if (cellvalue == null || cellvalue == 'null'){ 
					return "";
				} else {
					var preCellValue = cellvalue.substring(0, 10);
					var postCellvalue = cellvalue.substring(11, cellvalue.length);
					return preCellValue + " " + postCellvalue;
				}
			},
			trueOrfalse:function(cellvalue, options, cellobject){
				if(cellvalue == null
						|| cellvalue == 'null'
						|| cellvalue == 0) return "否";
				return "是";
			},
			// 使用图片显示是否值，是显示图片，否不显示任何值
			trueOrFalseImg: function(cellvalue, options, cellobject){
				if(cellvalue == null
						|| cellvalue == 'null'
						|| cellvalue == 0) return "";
				var okImgPath = $.common.custom.getCtx() + "/images/tip/ok.gif";
				return "<img src='" + okImgPath + "'/>";
			},
			float2precent:function(cellvalue, options, cellobject){
				if(cellvalue == null || cellvalue == 'null') return "";
				return cellvalue * 100 + '%';
			}
		},
		form:{
			// 表单必填标志
			must: function(){
				return "<span class='must'>*</span>";
			},
			mustTip : "带 <span class='must'>*</span> 为必填(选)项。",
			// 设置表单的LABEL宽度，防止自动列宽在验证组件添加文字提示的时候表格会动的问题
			setLabelWidth : function(width) {
				$('.CaptionTD').width(width);
			},
			// 添加设置
			add: {
				closeAfterAdd: true,
				recreateForm: true,
				closeOnEscape: true,
				savekey : [true, 13],
				navkeys : [true, 38,40],
				bottominfo: "带 <span class='must'>*</span> 为必填(选)项。"
			},
			// 编辑设置
			edit: {
				closeAfterEdit: true,
				recreateForm: true,
				closeOnEscape: true,
				savekey : [true, 13],
				navkeys : [true, 38,40],
				bottominfo: "带 <span class='must'>*</span> 为必填(选)项。"
			},
			// 删除设置，因为delete是关键字，改为remove
			remove: {
				// empty
			},
			// 搜索设置
			search: {
				multipleSearch: true
			},
			// 查看设置
			view: {
				// empty
			}
		},
		/**
		 * 改变窗口大小的时候自动根据iframe大小设置jqGrid列表宽度和高度
		 * 参数说明：{
		 * 		dataGrid : jqGrid数据列表的ID
		 * 		callback : 计算完dataGrid需要的高度和宽度后的回调函数
		 * 		width : 默认为iframe的宽度，如果指定则设置为指定的宽度
		 * 		height : 默认为iframe的高度，如果指定则设置为指定的高度
		 * }
		 */
		autoResize: function(options) {
			// 第一次调用
			var size = getWidthAndHeigh();
			if ($.isFunction(options.callback)) {
				options.callback(size);
			}
			
			// 窗口大小改变的时候
			window.onresize = function() {
				var size = getWidthAndHeigh(true);
				$(options.dataGrid).jqGrid('setGridHeight', size.height).jqGrid('setGridWidth', size.width);
			};
			
			// 获取iframe大小
			function getWidthAndHeigh(resize) {
				
				var hasToolbar = !options.toolbar ? false : options.toolbar[0];
				if (hasToolbar) {
					var toolbarType = options.toolbar[1];
					if (!toolbarType) {
						alert('请设置工具栏的属性，toolbar ： [true, [top, both]]');
					}
				}
				
				var iframeHeight = !options.height ? document.body.clientHeight : options.height;
				var iframeWidth = !options.width ? document.body.clientWidth : options.width;
				// chrome
				if ($.common.browser.isChrome()) {
					if (hasToolbar) {
						if (toolbarType == 'top') {
							iframeWidth -= 8;
							iframeHeight -= 128;
						} else if (toolbarType == 'both') {
							iframeWidth -= 14;
							iframeHeight -= 140;
						}
					} else {
						iframeWidth -= 16;
						iframeHeight -= 85;
					}
				}
				// firefox
				else if ($.common.browser.isMozila()) {
					if (hasToolbar) {
						if (toolbarType == 'top') {
							iframeWidth -= 8;
							iframeHeight -= 125;
						} else if (toolbarType == 'both') {
							iframeWidth -= 14;
							iframeHeight -= 140;
						}
					} else {
						iframeWidth -= 15;
						iframeHeight -= 85;
					}
				} 
				// IE
				else {
					if (hasToolbar) {
						if (toolbarType == 'top') {
							iframeWidth -= 13;
							iframeHeight -= 118;
						} else if (toolbarType == 'both') {
							iframeWidth -= 14;
							iframeHeight -= 138;
						}
					} else {
						iframeWidth -= 12;
						iframeHeight -= 83;
					}
				}
				return {width: iframeWidth, height: iframeHeight};
			}
			
		},
		// jqGrid快捷键支持
		keys: {
			savekey: [true, 13],
			navkeys: [true, 38,40]
		},
		// checkbox工具
		checkbox : {
			/**
			 * 获取选中的checkbox
			 * @param {Object} listObj
			 */
			getChecked : function(listObj) {
				return $(listObj +' :checkbox[name^=jqg_list]:checked');
			},
			
			/**
			 * 获取选中的记录ID，以逗号分隔
			 * @param {Object} listObj	列表对象
			 * @param {Object} dealFn	过滤函数，如果加入选中的ID那么return true，否则return false
			 */
			convertToString : function(listObj, dealFn) {
				var chks = $(listObj +' :checkbox[name^=jqg_list]:checked');
				var ids = "";
				$.each(chks, function(i, n) {
					var tempId = $(this).attr('name').replace('jqg_list_', '');
					// 调用过滤函数
					if ($.isFunction(dealFn)) {
						var useId = dealFn(tempId);
						if (useId) {
							ids += tempId + ",";
						}
					} else {
						ids += tempId + ",";
					}
					
				});
				if (ids.indexOf(',') != -1) {
					ids = ids.substring(0, ids.length -1);
				}
				return ids;
			}
			
		}
	};
	
	/**
	 * jqGrid公共参数，供集成使用
	 */
	_plugin_jqGrid.settings = function(options) {
		return {
			datatype: "json",
			prmNames: _plugin_jqGrid.prmNames,
	        jsonReader: _plugin_jqGrid.jsonReader,
	        width: options.size.width,
	        height: options.size.height,
	        rowNum: 20,
	        rowList: [10, 20, 30, 40],
	        pager: '#pager',
	        viewrecords: true,
			rownumbers: true,
			loadError : function(xhr,st,err) {
    			//alert("很抱歉，出错了！\n错误类型: " + st + "； 错误内容: "+ xhr.status + " " + xhr.statusText);
				var s = "未知";
				if (xhr.status == 404) {
					s = "找不到数据源";
				} else if (xhr.status == 500) {
					s = "服务器内部错误";
				}
				alert("很抱歉，数据加载失败！\n错误类型: " + s);
    		}
		};
	};
	
	/*******************************************/
	/**				jqGrid插件--结束			  **/
	/*******************************************/
	
	/*******************************************/
	/**			jquery.validator插件--开始	  **/
	/*******************************************/
	var _plugin_validator = {
		// 错误信息显示位置
		error : function(error, element) {
            if (element.is(":radio")) {
                error.appendTo(element.parent());
            } else if (element.is(":checkbox")) {
                error.appendTo(element.parent());
            } else {
                error.appendTo(element.parent());
            }
        },
        success : function(label) {
            label.html("&nbsp;").addClass("checked");
			var forEle = label.attr('for');
			if (forEle == 'phone') {
				if ($.isFunction(callback)) {
					callback();
				}
			}
        }
	};
	/*******************************************/
	/**			jquery.validator插件--结束	  **/
	/*******************************************/
	
	/*******************************************/
	/**			jQuery UI--开始	  			  **/
	/*******************************************/
	var _plugin_jqui = {
		button: {
			onOff : function(options) {
				var defaults = {
					btnText : false // text
				};
				options = $.extend({}, defaults, options);
				var dlgButton = $('.ui-dialog-buttonpane button');
				if (options.btnText) {
					// TODO 查询优化，兼容有相同文字的情况
					dlgButton = $('.ui-button-text:contains(' + options.btnText + ')').parent();
				}
			    if (options.enable) {
			        dlgButton.attr('disabled', '');
			        dlgButton.removeClass('ui-state-disabled');
			    } else {
			        dlgButton.attr('disabled', 'disabled');
			        dlgButton.addClass('ui-state-disabled');
			    }
			}
		}
	};
	/*******************************************/
	/**			jQuery UI--结束	  			  **/
	/*******************************************/
	
	/*******************************************/
	/**			jstree --开始	  			  **/
	/*******************************************/
	
	var _plugin_jstree = {
		
		// 单击名称展开子节点
		clickNameToUnfold : function(jstreeObj) {
			$(jstreeObj).bind('click.jstree', function(event){
				var eventNodeName = event.target.nodeName;
				if (eventNodeName == 'INS') {
					return;
				} else if (eventNodeName == 'A') {
					var $city = $(event.target);
					
					// 点击A展开子节点
					$("#areaInfoTree").jstree('toggle_node', $city.parent().find('ins').get(0));
					
					if ($city.attr('leaf')) {
						$('#result').text($city.text() + "，ID=" + $city.parent().attr('id'));
					}
				}
			});
		}
	};
	
	/*******************************************/
	/**			jstree --结束	  			  **/
	/*******************************************/
	
	/*******************************************/
	/**			$.common--开始	  			  **/
	/*******************************************/
	var _common_plugins = {
		// jqGrid默认参数
		jqGrid : _plugin_jqGrid,
		validator : _plugin_validator,
		jqui : _plugin_jqui,
		jstree : _plugin_jstree	
	};
	
	// 插件扩展
	$.common.plugin = _common_plugins;
	
	//-- 窗口工具 --//
	$.common.window = {
		//-- 获得最上层的window对象 --//
		getTopWin: function() {
			if(parent) {
				var tempParent = parent;
				while(true) {
					if(tempParent.parent) {
						if(tempParent.parent == tempParent) {
							break;
						}
						tempParent = tempParent.parent;
					} else {
						break;
					}
				}
				return tempParent;
			} else {
				return window;
			}
		}
	};
		
	//-- 和系统有关的函数 --//
	$.common.system = {
		// 获取系统属性
		getProp : function(options) {
			var defaults = {
				url : ctx + '/common/sysprop!findProp.action',
				params : {
					key : ''
				},
				callback : null,
				error : null
			};
			
			$.extend(true, defaults, options);
			
			$.ajax({
				url : defaults.url,
				cache : false,
				dataType : 'json',
				data : defaults.params,
				success : function(prop, textStatus) {
					if ($.isFunction(defaults.callback)) {
						defaults.callback(prop);
					}
				},
				error : function (XMLHttpRequest, textStatus, errorThrown) {
					if ($.isFunction(defaults.error)) {
						defaults.error(XMLHttpRequest, textStatus, errorThrown);
					}
				}
			});
			
		},
		
		// 获取预定系统的URL ，以便调用JSONP服务
		getExerciseUrl : function(options) {
			if (window.exerciseUrl && window.exerciseUrl != '') {
				options.callback(exerciseUrl);
				return;
			}
			var defaults = {
				url : ctx + '/common/sysprop!findProp.action',
				params : {
					key : 'exercise-url'
				},
				callback : null
			};
			
			options = $.extend(true, defaults, options);
			
			$.ajax({
				url : options.url,
				cache : false,
				dataType : 'json',
				data : options.params,
				success : function(prop, textStatus) {
					var exerciseUrl = prop.propValue;
					window.exerciseUrl = exerciseUrl;
					if ($.isFunction(options.callback)) {
						options.callback(exerciseUrl);
					}
				},
				error : function (XMLHttpRequest, textStatus, errorThrown) {
					if ($.isFunction(options.error)) {
						options.error(XMLHttpRequest, textStatus, errorThrown);
					}
				}
			});
		}
	};
		
	//-- 浏览器工具 --//
	$.common.browser = {
		// 检测是否是IE浏览器
		isIE : function() {
			var _uaMatch = $.uaMatch(navigator.userAgent);
			var _browser = _uaMatch.browser;
			if (_browser == 'msie') {
				return true;
			} else {
				return false;
			}
		},
		// 检测是否是chrome浏览器
		isChrome : function() {
			var _uaMatch = $.uaMatch(navigator.userAgent);
			var _browser = _uaMatch.browser;
			if (_browser == 'webkit') {
				return true;
			} else {
				return false;
			}
		},
		// 检测是否是Firefox浏览器
		isMozila : function() {
			var _uaMatch = $.uaMatch(navigator.userAgent);
			var _browser = _uaMatch.browser;
			if (_browser == 'mozilla') {
				return true;
			} else {
				return false;
			}
		}
	};
		
	//-- 文件相关 --//
	$.common.file = {
		/**
		 * 下载文件 
		 * @fileName	相对于Web根路径
		 */
		download : function(fileName){
			var downUrl = $.common.custom.getCtx() + '/file/download.action?fileName=' + fileName;
    		open(encodeURI(encodeURI(downUrl)));
		}
	};
		
	//-- 数学工具 --//
	$.common.math = {
		/*
		 * 四舍五入
		 */
		round : function(dight, how) {
			return dight.toFixed(how);
		}
	};
		
	//-- 未分类 --//
	$.common.custom = {
		// 得到应用名
		getCtx : function() {
			var url = location.pathname;
			var contextPath = url.substr(0, url.indexOf('/', 1));
			return contextPath;
		},
		getLoadingImg : function() {
			return '<img src="' + $.common.custom.getCtx() + '/images/ajax/loading.gif" align="absmiddle"/>&nbsp;';
		},
		/**
		 * 创建小时下拉框
		 */
		createHourSelect : function(selectId, defaultValue) {
			var hours = new StringBuffer();
			var tempValue = "";
			for(var i = 0; i < 24; i++) {
				if(i < 10) {
					tempValue = "0" + i;
				} else {
					tempValue = i;
				}
				hours.append("<option value='" + tempValue + "'" + (defaultValue == tempValue ? " selected" : "") + ">" + tempValue + "</option>");
			}
			$(selectId).append(hours.toString());
		},
		/**
		 * 创建分钟下拉框
		 */
		createMinuteSelect : function(selectId, defaultValue) {
			var hours = new StringBuffer();
			var tempValue = "";
			for(var i = 0; i < 60; i++) {
				if(i < 10) {
					tempValue = "0" + i;
				} else {
					tempValue = i;
				}
				hours.append("<option value='" + tempValue + "'" + (defaultValue == tempValue ? " selected" : "") + ">" + tempValue + "</option>");
			}
			$(selectId).append(hours.toString());
		},
		
		/**
		 * 日期增加年数或月数或天数
		 * @param {String} BaseDate	要增加的日期
		 * @param {Object} interval	增加数量
		 * @param {Object} DatePart	增加哪一部分
		 * @param {String} returnType 返回类型strunt|date
		 */
		dateAdd : function(BaseDate, interval, DatePart, returnType) {
		    var dateObj;
			if(typeof BaseDate == 'object') {
				dateObj = BaseDate;
			} else {
				var strDs = BaseDate.split('-');
				var year = parseInt(strDs[0]);
				var month = parseInt(strDs[1]);
				var date = parseInt(strDs[2]);
				dateObj = new Date(year, month, date);
			}
			returnType = returnType || 'string';
		    var millisecond = 1;
		    var second = millisecond * 1000;
		    var minute = second * 60;
		    var hour = minute * 60;
		    var day = hour * 24;
		    var year = day * 365;

		    var newDate;
		    var dVal = new Date(dateObj);
		    var dVal = dVal.valueOf();
		    switch (DatePart) {
		        case "ms":
		            newDate = new Date(dVal + millisecond * interval);
		            break;
		        case "s":
		            newDate = new Date(dVal + second * interval);
		            break;
		        case "mi":
		            newDate = new Date(dVal + minute * interval);
		            break;
		        case "h":
		            newDate = new Date(dVal + hour * interval);
		            break;
		        case "d":
		            newDate = new Date(dVal + day * interval);
		            break;
		        case "y":
		            newDate = new Date(dVal + year * interval);
		            break;
		        default:
		            return escape("日期格式不对");
		    }
		    newDate = new Date(newDate);
			if (returnType == 'string') {
			    return newDate.getFullYear() + "-" + (newDate.getMonth() + 1) + "-" + newDate.getDate();
			} else if (returnType == 'date') {
				return newDate;
			}
		}
	};
	
	/*******************************************/
	/**			$.form--开始  	  			  **/
	/*******************************************/
	//-- 表单自定义功能 --//
	$.form = {};
	
	// 绑定form的ajax提交功能
	$.form.bindAjaxSubmit = function(settings) {
	
		var defaults = {
			formId : '',
	        beforeSubmit : showRequest,
	        success : showResponse,
	        clearForm : true
	    };
		
		settings = $.extend({}, defaults, settings);
		
		$('#' + settings.formId).submit(function() {
	        $(this).ajaxSubmit(settings);
	        return false;
	    });
		
	};
		
	// 表示层设置
	$.form.ui = {
		// 红色星号
		required : function() {
			return $.common.plugin.jqGrid.form.must();
		}
	};

	// -- 自定义插件 --//
	/**
	 * 插件名称：cursorInsert（光标处插入） 功能：可以在文本框的
	 */
	$.fn.cursorInsert = function(options) {

		// default settings
		var settings = {
			content : ''
		};

		if (options) {
			$.extend(settings, options);
		}

		return this.each(function() {
			var obj = $(this).get(0);
			if (document.selection) {
				obj.focus();
				var sel = document.selection.createRange();
				document.selection.empty();
				sel.text = options.content;
			} else {
				var prefix, main, suffix;
				prefix = obj.value.substring(0, obj.selectionStart);
				main = obj.value
						.substring(obj.selectionStart, obj.selectionEnd);
				suffix = obj.value.substring(obj.selectionEnd);
				obj.value = prefix + options.content + suffix;
			}
			obj.focus();
		});
	};
	
	/**
	 * 随滚动条滚动
	 */
	$.fn.autoScroll = function() {
		var _this = this;
		$(_this).css({
		    position: 'absolute'
		});
		
		$(window).scroll(function(){
		    $(_this).css({
		        top: $(this).scrollTop() + $(this).height() - 500
		    });
		});

	};
	
	/**
	 * 动态加载JavaScript
	 */
	$.loadScript = function(options) {
		var ga = document.createElement('script');
		ga.type = 'text/javascript';
		ga.async = true;
		ga.src = options.src;
		var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
	};

})(jQuery);

//-- 自定义函数 --//
function _initFunction() {
	
	// 全局ajax设置
	$.ajaxSetup({
		cache : false,
		global : true,
		complete: function(req, status) {
			
		},
		error: function(req, status) {
			var reqText = req.responseText;
			if(reqText == 'login') {
				return;
			}
			if(reqText == 'error') {
				alert('操作失败！');
			} else if (reqText != ''){
				alert("提示：" + reqText);
			}
		}
	});
	
};


//-- Javascript对象扩展--开始-//
/**
 * 去掉开头、结尾的空格
 *
 * @return {}
 */
String.prototype.trim = function() {
	return this.replace(/(^\s+)|\s+$/g, "");
};

String.prototype.toJson = function() {
	return eval('(' + this + ')');
};

/**
 * 输出2010-02-05格式的日期字符串
 *
 * @return {}
 */
Date.prototype.toDateStr = function() {
	return ($.common.browser.isMozila() || $.common.browser.isChrome() ? (this.getYear() + 1900) : this.getYear()) + "-"
			+ (this.getMonth() < 10 ? "0" + this.getMonth() : this.getMonth())
			+ "-" + (this.getDate() < 10 ? "0" + this.getDate() : this.getDate());
};

//+---------------------------------------------------
//| 日期计算
//+---------------------------------------------------
Date.prototype.DateAdd = function(strInterval, Number) {
    var dtTmp = this;
    switch (strInterval) {
        case 's' :return new Date(Date.parse(dtTmp) + (1000 * Number));
        case 'n' :return new Date(Date.parse(dtTmp) + (60000 * Number));
        case 'h' :return new Date(Date.parse(dtTmp) + (3600000 * Number));
        case 'd' :return new Date(Date.parse(dtTmp) + (86400000 * Number));
        case 'w' :return new Date(Date.parse(dtTmp) + ((86400000 * 7) * Number));
        case 'q' :return new Date(dtTmp.getFullYear(), (dtTmp.getMonth()) + Number*3, dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(), dtTmp.getSeconds());
        case 'm' :return new Date(dtTmp.getFullYear(), (dtTmp.getMonth()) + Number, dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(), dtTmp.getSeconds());
        case 'y' :return new Date((dtTmp.getFullYear() + Number), dtTmp.getMonth(), dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(), dtTmp.getSeconds());
    }
};

//-- Javascript对象扩展--结束 -//

//-- 自定义类-开始 --/
function StringBuffer() {
	this._strings_ = new Array();
}

StringBuffer.prototype.append = function(str) {
	this._strings_.push(str);
	return this;
};

StringBuffer.prototype.toString = function() {
	return this._strings_.join("").trim();
};

/**
 * 以键值对存储
 */
function Map() {
    var struct = function(key, value) {
        this.key = key;
        this.value = value;
    };

    var put = function(key, value) {
        for (var i = 0; i < this.arr.length; i++) {
            if (this.arr[i].key === key) {
                this.arr[i].value = value;
                return;
            }
        }
        this.arr[this.arr.length] = new struct(key, value);
		this._keys[this._keys.length] = key;
    };

    var get = function(key) {
        for (var i = 0; i < this.arr.length; i++) {
            if (this.arr[i].key === key) {
                return this.arr[i].value;
            }
        }
        return null;
    };

    var remove = function(key) {
        var v;
        for (var i = 0; i < this.arr.length; i++) {
            v = this.arr.pop();
            if (v.key === key) {
                continue;
            }
            this.arr.unshift(v);
			this._keys.unshift(v);
        }
    };

    var size = function() {
        return this.arr.length;
    };

	var keys = function() {
        return this._keys;
    };

    var isEmpty = function() {
        return this.arr.length <= 0;
    };

    this.arr = new Array();
	this._keys = new Array();
	this.keys = keys;
    this.get = get;
    this.put = put;
    this.remove = remove;
    this.size = size;
    this.isEmpty = isEmpty;
}

//-- 自定义类-结束 --/