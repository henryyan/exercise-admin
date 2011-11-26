/**
 * 场馆信息
 * 功能：场馆信息的列表以及CRUD操作
 * @author HenryYan 
 */
var exerciseUrl = '';
var $jqGrid;
$(function() {
	
	$.common.system.getExerciseUrl({
		callback : function(_exerciseUrl) {
			exerciseUrl = _exerciseUrl;
			// 自动根据窗口大小改变数据列表大小
			$.common.plugin.jqGrid.autoResize({
				dataGrid: '#list',
				toolbar: [true, 'top'],
				callback: listDatas
			});
		}
	});
	
});

var moduleAction = "venue";

/**
 * 加载系统属性列表
 * @return
 */
function listDatas(size) {
	$("#list").jqGrid(
	$.extend($.common.plugin.jqGrid.settings({size: size}), {
		url: moduleAction + '.action',
		colNames: ['场馆名称', '城市', '所在区县', '地址', '邮编', '商圈', 
                   '营业起始', '营业结束', '联系人', '电话', '手机号码', '电子邮件',
                   '是否验证', '协议客户', '场馆简介', '场馆照片'],
        colModel: [{
            name: 'venueName',
            editable: true,
            editoptions: {
                size: 40,
                maxlength: 50
            },
            searchoptions: {
				sopt : $.common.plugin.jqGrid.search.text
			}
        }, {
            name: 'city',
            editable: true,
			search: false
        }, {
            name: 'district',
            editable: true,
			search: false
        }, {
            name: 'adress',
            editable: true,
            editoptions: {
	            size: 40,
	            maxlength: 90
	        },
			hidden: true,
			searchoptions: {
				sopt : $.common.plugin.jqGrid.search.text
			}
        }, {
            name: 'zipcode',
            editable: true,
            editoptions: {
	        	size: 8,
	        	maxlength: 6
	        },
			hidden: true
        }, {
            name: 'area',
            editable: true,
			hidden : true
        }, {
            name: 'openTime',
            editable: true
        }, {
            name: 'closeTime',
            editable: true
        }, {
            name: 'contact',
            editable: true,
			hidden : true
        }, {
        	name: 'phone',
            editable: true
        }, {
        	name: 'cell',
            editable: true,
			hidden: true
        }, {
        	name: 'email',
            editable: true,
			hidden: true
        }, {
        	name: 'verification',
        	formatter: function(cellvalue, options, rowObject) {
        		return !cellvalue || cellvalue == '0' ? 
        				"<span class='unverify'>未验证</span>" : "<span class='verified'>已验证</span>";
	        },
			stype : 'select',
			searchoptions : {
				sopt : $.common.plugin.jqGrid.search.select,
				value : {1 : '是', 0 : '否'}
			}
        }, {
        	name: 'isProtocol',
        	formatter: function(cellvalue, options, rowObject) {
        		return cellvalue ? "<img alt='协议用户' title='已签订协议' src='" + ctx + "/images/validitor/onCorrect.gif' />" : '';
        	},
			stype : 'select',
			searchoptions : {
				sopt : $.common.plugin.jqGrid.search.select,
				value : {1 : '是', 0 : '否'}
			}
        }, {
        	name: 'intraduction',
			editable: true,
			edittype: 'textarea',
			editoptions: {
				rows: 5,
				cols: 40
			},
			hidden: true
        }, {
        	name: 'photoUrl',
        	formatter: function(cellvalue, options, rowObject) {
        		var content = '';
        		if (cellvalue != '' && cellvalue != null) {
					var linkPictureUrl = ctx + "/venue/pictures/" + rowObject.id + "/" + cellvalue;
					var smallPictureUrl = ctx + "/venue/pictures/" + rowObject.id + "/thumbnails/60/" + cellvalue;
        			content = "<a rel='" + linkPictureUrl + "' target='_blank' class='nyroModal' style='cursor:pointer'>" 
        			+ "<img width='50' src='" + smallPictureUrl + "' align='absmiddle' /></a>";
					content += "<br/><a href='#' rel='venue-picture.action?venueId=" + rowObject.id + "' class='vePicture'>查看编辑</a>";
        		}
        		return content;
        	},
			search : false
        }],
		caption: "场馆管理",
		multiselect: true,
		toolbar : [true, 'top'],
		editurl: moduleAction + '!save.action',
		gridComplete: function() {
			$('a.nyroModal').click(function(event) {
				var _this = this;
				$.nyroModalManual({
			        title: '查看场馆图片',
			        url: $(_this).attr('rel')
			    });
			});
			
			$('#t_list').html($('#btns').html());
			initToolbarBtns();
			
			$('.vePicture').live('click', viewAndEditPicture);
		}
	})
	).jqGrid('navGrid', '#pager', $.extend($.common.plugin.jqGrid.pager, {
		add: false
	}), 
	// edit options
    $.extend($.common.plugin.jqGrid.form.edit, {
		width: 500,
    	closeAfterEdit: true,
		modal: false,
    	beforeShowForm: function(formid) {
			$.common.plugin.jqGrid.navGrid.showAllField(formid, [], ['email']);
			
			/*
			 * 处理时间
			 */
			$('#openTime').click(function(){
				WdatePicker({
					dateFmt: 'HH:mm'
				});
			});
			
    		validator();
		},
		beforeSubmit: function() {
			var valid = $("#FrmGrid_list").valid();
			return [valid, '请将表单填写完整'];
		}
	}),
	
	// add options
    {}, 
	
    // delete options
    $.extend($.common.plugin.jqGrid.form.remove, {
		caption: '删除场馆所有相关信息',
    	url: 'venue!delete.action',
		width: 330,
		msg: '<span class="waring">警告</span>：当前操作会<span class="waring">删除</span>所有和<b>选中场馆</b>'
				 + '的所有信息；<br/>例如：场地、会员、订单、以及各项和场馆相关的设置！<br/><br/>您确定要删除吗？'
	}),
	
	// search optios
	$.extend($.common.plugin.jqGrid.form.search, {}), 
	
	// view options
	$.extend($.common.plugin.jqGrid.form.view, {
		caption: '查看场馆信息',
    	width: 450,
    	navkeys: [true, 38, 40],
    	closeOnEscape: true,
    	dataheight: 450,
    	beforeShowForm: function(formid) {
	    	$.common.plugin.jqGrid.navGrid.showAllField(formid);
	    }
	})).jqGrid('navButtonAdd', '#pager', $.common.plugin.jqGrid.navButtonAdd.setColumns);
};

/**
 * 表单验证
 */
function validator() {
	// 开始、结束时间
    $.validator.addMethod("openAndCloseTime", function(value, element) {
        var length = value.length;
        return length > 1;
    }, function(result, element){
		if (element.id.indexOf('open') != -1) {
			return "请选择开始时间";
		} else {
			return "请选择结束时间";
		}
	});
	
	$("#FrmGrid_list").validate({
        rules: {
			venueName: {
				required: true,
		        minlength: 2,
		        remote: {
		            url: 'venue!checkVenueNameExist.action',
		            type: 'post',
		            data: {
		                venueName: function() {
		                    return $('#venueName').val();
		                }
		            },
		            dataType: 'json',
		            dataFilter: function(data) {
		                data = eval("(" + data + ")");
		                if (data.exist) {
		                    // 已经存在的场馆ID和当前ID相等返回true
		                    if (data.infoId == $('#id_g').val()) {
		                        return true;
		                    }
		                    return false;
		                } else {
		                    return true;
		                }
		            }
		        }
			},
			city: {
				required: true
			},
			district: {
				required: true
			},
            adress: {
                required: true,
                minlength: 5
            },
            zipcode: {
                isZipCode: true
            },
            contact: {
                required: true,
                minlength: 2
            },
			cell: {
				isMobile: true
			},
			email: {
				email: true
			}
        },
        messages: {
	        venueName: {
	            required: "请输入场馆名称",
	            minlength: $.format("场馆名称至少 {0} 个汉字"),
	            remote: "此场馆名称已被占用"
	        },
	        adress: {
	            required: "请输入场馆地址",
	            minlength: $.format("场馆地址至少  {0} 个汉字")
	        },
	        contact: {
	            required: "请输入联系人",
	            minlength: $.format("联系人至少  {0} 个汉字")
	        }
	    },
        errorPlacement: $.common.plugin.validator.error,
        success: $.common.plugin.validator.success
    });
};

/**
 * 工具栏按钮事件
 */
function initToolbarBtns() {
	// 验证场馆 
	$("#verify").button({
        icons: {
            primary: 'ui-icon-check'
        }
	}).click(verify);
	
	// 取消验证场馆 
	$("#unverify").button({
        icons: {
            primary: 'ui-icon-close'
        }
	}).click(unverify);
	
	// 升级为协议用户
	$("#upgrade").button({
        icons: {
            primary: 'ui-icon-arrowthick-1-n'
        }
	}).click(upgrade);
	
	// 解除协议
	$("#unUpgrade").button({
        icons: {
            primary: 'ui-icon-arrowthick-1-s'
        }
	}).click(unUpgrade);
	
	// 图片上传
	$("#uploadImg").button({
        icons: {
            primary: 'ui-icon-home'
        }
	}).click(uploadImg);
}

/**
 * 场馆验证
 */
function verify() {
  	if ($.common.plugin.jqGrid.checkbox.getChecked('#list').length == 0) {
		$('<span title="注意">请选择记录</span>').dialog({modal: true, width:200, minHeight: 20});
		return;
	}
	
	var ids = $.common.plugin.jqGrid.checkbox.convertToString('#list', function(tid) {
		var rowData = $("#list").jqGrid('getRowData', tid);
		if ($(rowData.verification).hasClass('verified')) {
			return false;
		}
		return true;
	});
	
  	if (!confirm('确认验证选中的场馆？')) {
  		return;
  	}
 	//-- 发送验证请求 --//
	$.get('venue!verifyVenue.action', {
		ids : ids
	}, function(resp) {
		if ( resp == 'true' ) {
			var aids = ids.split(',');
			$.each(aids, function() {
				$('#list').jqGrid('setRowData', this, {verification: "<span class='verified'>已验证</span>"});
			});
		}
	});
}

/**
 * 取消场馆验证
 */
function unverify() {
  	if ($.common.plugin.jqGrid.checkbox.getChecked('#list').length == 0) {
		$('<span title="注意">请选择记录</span>').dialog({modal: true, width:200, minHeight: 20});
		return;
	}
	
	var ids = $.common.plugin.jqGrid.checkbox.convertToString('#list', function(tid) {
		var rowData = $("#list").jqGrid('getRowData', tid);
		if (!$(rowData.verification).hasClass('verified')) {
			return false;
		}
		return true;
	});
	
  	if (!confirm('确认[取消]验证选中的场馆？')) {
  		return;
  	}
 	//-- 发送验证请求 --//
	$.get('venue!unVerifyVenue.action', {
		ids : ids
	}, function(resp) {
		if ( resp == 'true' ) {
			var aids = ids.split(',');
			$.each(aids, function() {
				$('#list').jqGrid().trigger('reloadGrid');
			});
		}
	});
}

/**
 * 场馆升级为协议用户
 */
function upgrade() {
	var rowId = $("#list").jqGrid('getGridParam','selrow');
  	var rowData = $("#list").jqGrid('getRowData', rowId);
  	
  	if (!rowId) {
		$('<span title="注意">请选择场馆</span>').dialog({modal: true, width:200, minHeight: 20});
		return;
	}
  	if (rowData.isProtocol != '') {
  		alert('此场馆已经是协议用户了！');
  		return;
  	}
  	
  	$("<span title='升级确认'>确认<b>升级</b>[" + rowData.venueName + "]为<b>协议</b>用户吗？？</span>").dialog({
		resizable: false,
		height:240,
		modal: true,
		buttons: {
			升级: function() {
  				var _dialog = this;
    	  		//-- 发送升级请求 --//
				$.get('venue!upgrade.action', {
					id : rowId,
					opt : 'upgrade'
				}, function(resp) {
					$(_dialog).dialog('close');
					if ( resp == 'true' ) {
						$($jqGrid).jqGrid('setRowData', rowId, {isProtocol: "<img alt='协议用户' title='已签订协议' src='" + ctx + "/images/validitor/onCorrect.gif'/>"});
					}
				});
			},
			取消: function() {
				$(this).dialog('close');
			}
		}
	});
}

/**
 * 解除协议用户
 */
function unUpgrade() {
	var rowId = $("#list").jqGrid('getGridParam','selrow');
  	var rowData = $("#list").jqGrid('getRowData', rowId);
  	
  	if (!rowId) {
		$('<span title="注意">请选择场馆</span>').dialog({modal: true, width:200, minHeight: 20});
		return;
	}
  	if (rowData.isProtocol == '') {
  		alert('此场馆不是协议用户！');
  		return;
  	}
  	
  	$("<span title='解除协议确认'>确认<b>解除</b>[" + rowData.venueName + "]的协议吗？？</span>").dialog({
		resizable: false,
		height:240,
		modal: true,
		buttons: {
			解除协议: function() {
  				var _dialog = this;
    	  		//-- 发送升级请求 --//
				$.get('venue!upgrade.action', {
					id : rowId,
					opt : 'unUpgrade'
				}, function(resp) {
					$(_dialog).dialog('close');
					if ( resp == 'true' ) {
						$($jqGrid).jqGrid('setRowData', rowId, {isProtocol: ""});
					}
				});
			},
			取消: function() {
				$(this).dialog('close');
			}
		}
	});
};

/**
 * 打开查看、编辑图片对话框
 */
function viewAndEditPicture() {
	var width = $('#list').jqGrid('getGridParam', 'width') - 20;
	var height = $('#list').jqGrid('getGridParam', 'height');
	if ($.common.browser.isIE()) {
		height += 220;
	} else {
		height += 80;
	}
	
	var _a = this;
	$('#vePictureDialog').dialog({
		open : function() {
			$(this).find('iframe').attr('src', $(_a).attr('rel'));
		},
		width : width,
		height : height
	});
}

/**
 * 图片上传
 */
function uploadImg() {
	var rowId = $("#list").jqGrid('getGridParam','selrow');
	if (!rowId) {
		$('<span title="注意">请选择场馆再上传！</span>').dialog({modal: true, width:200, minHeight: 20});
		return;
	}
	$('#uploadPictureDialog').dialog({
		open : function() {
			var url = "venue-picture-upload.action?venueId=" + rowId;
			$(this).find('iframe').attr('src', url);
		},
		modal : true,
		width : 500,
		height : 400,
		buttons : {
			关闭: function() {
				$(this).dialog('close');
			}
		}
	});
}

/**
 * 上传完成后的回调函数
 */
function uploadComplete() {
	$('#uploadPictureDialog').dialog('close');
	$("#list").jqGrid().trigger('reloadGrid');
}

/**
 * 刷新列表
 */
function refreshGrid() {
	$("#list").jqGrid().trigger('reloadGrid');
}
