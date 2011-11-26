/**
 * 地区信息单级管理JavaScript
 * 
 * @author HenryYan
 */
$(function() {
	// 自动根据窗口大小改变数据列表大小
	$.common.plugin.jqGrid.autoResize({
		dataGrid: '#list',
		callback: listDatas
	});
	
});


var validator;
var currentLevel = 1;
var moduleAction = ctx + "/bdata/area-info-manager";

/**
 * 加载列表
 * 
 * @return
 */
function listDatas(size) {
	$("#list").jqGrid(
	$.extend($.common.plugin.jqGrid.settings({size: size}), {
		url: moduleAction + '.action?filter_EQL_parentAreaId=' + parentAreaId,
		scroll: 1,
		height : size.height - 25,
		width : size.width + 10,
		colNames: ['地区名称', '地区层级', '上级地区', '国家编码', '地区编码', '操作'],
        colModel: [{
            name: 'areaName',
            align: 'center',
			editable: true,
			formoptions: {
            	elmsuffix: $.common.plugin.jqGrid.form.must
            },
            searchoptions : {
    			sopt : $.common.plugin.jqGrid.search.text
    		}
        }, {
        	name: 'remark',
            align: 'center',
			editable: false,
            searchoptions : {
    			sopt : $.common.plugin.jqGrid.search.select
    		}
        }, {
            name: 'parent.areaName',
            align: 'center',
			editable: false,
            searchoptions : {
    			sopt : $.common.plugin.jqGrid.search.text
    		},
			formatter : function(cellvalue, options, rowObject) {
				if(!cellvalue) {
					return "无";
				} else {
					return cellvalue;
				}
			}
        }, {
			name : 'countryCode'
		}, {
			name : 'areaCode',
			editable : true
		}, {
            name: 'option',
			editable: false,
			search: false,
			formatter : function(cellvalue, options, rowObject) {
				return "<button class='addSubArea' title='添加下级地区' idv='" + rowObject.id + "' level='" + rowObject.areaLevel + "'>添加下级</button>";
			}
        }],
		caption: "地区信息列表-[中国]",
		editurl: moduleAction + '!save.action',
		gridComplete : function() {
			$('.addSubArea').button().click(addSubArea);
		}
	})
	).jqGrid('navGrid', '#pager', $.extend($.common.plugin.jqGrid.pager, {
		searchtext: '',
		refreshtext: ''
	}), 
    $.extend($.common.plugin.jqGrid.form.edit, {
		width : 450,
		editCaption: '编辑地区信息',
		beforeShowForm: function(formid) {
			commonBeforeShowForm();
		},
    	beforeSubmit: beforeSubmit
	}),
	
	// add option
    $.extend($.common.plugin.jqGrid.form.add, {
		width : 450,
		addCaption: '添加下级地区信息',
		beforeShowForm: function() {
			commonBeforeShowForm();
		},
    	beforeSubmit: beforeSubmit,
		editData : {
			parentAreaId : function() {
				return parentAreaId; 
			},
			areaLevel : function() {
				return currentLevel;
			}
		},
		afterComplete : function(response, postdata, formid) {
			if (response.responseText == 'success') {
				parent.refreshTree(parentAreaId);
			}
		}
	}), 
	
    // delete options
    $.extend($.common.plugin.jqGrid.form.remove, {
		url: moduleAction + '!delete.action',
		afterComplete : function(response, postdata, formid) {
			if (response.responseText == 'success') {
				parent.refreshTree(parentAreaId);
			}
		}
	}),
	
	// search optios
	$.extend($.common.plugin.jqGrid.form.search, {}), 
	
	// view options
	$.extend($.common.plugin.jqGrid.form.view, {
		beforeShowForm: function(formid) {
    		$.common.plugin.jqGrid.navGrid.showAllField(formid);
	    }
	}));
};

/**
 * 添加下级地区
 */
function addSubArea() {
	parentAreaId = $(this).attr('idv');
	currentLevel = parseInt($(this).attr('level')) + 1;
	$('#add_list').trigger('click');
}

/**
 * 刷新数据列表
 */
function refreshData(params) {
	parentAreaId = params.areaId;
	currentLevel = params.level;
	
	$("#list").jqGrid('setGridParam', {
		url : moduleAction + '.action?filter_EQL_parentAreaId=' + params.areaId
	}).jqGrid('setCaption', '地区信息-[' + params.areaName + ']');
	$("#list").jqGrid().trigger('reloadGrid');
}

/**
 * 显示新增、编辑表单前处理
 * @param {Object} formid
 */
function commonBeforeShowForm(formid) {
	// 注册表单验证事件
	validatorForm();
	$('.CaptionTD').width(80);
}

/**
 * 提交表单前
 */
function beforeSubmit() {
	var valid = $("#FrmGrid_list").valid();
	return [valid, '表单有 ' + validator.numberOfInvalids() + ' 项错误，请检查！'];
}

/**
 * 表单验证
 * 
 * @return
 */
function validatorForm() {
	validator = $("#FrmGrid_list").validate({
        rules: {
			areaName : {
				required : true,
				remote: {
					url: moduleAction + "!checkRepeat.action",
					type: "post",
					cache: false,
					dataType:"json",
					data: {
						newId : function() {
							return $("#id_g").val();
						},
						areaName : function() {
							return $("#areaName").val();
						},
						parentAreaId : function() {
							return parentAreaId;
						}
					}
				}
			}
		},
		messages: {
			areaName : {
				remote: '地区名称重复'
			}
		},
        errorPlacement: $.common.plugin.validator.error,
        success: $.common.plugin.validator.success
    });
};
