/**
 * 足球场地列表 功能：足球场地的列表以及CRUD操作
 * 
 * @author HenryYan
 */
var $_football_jqGrid;
var football_lastsel;
var football_validator;
var football_formListId = "FrmGrid_football-list";
$(function() {
	listFootballDatas();
});

/**
 * 足球场地列表
 * 
 * @return
 */
function listFootballDatas() {
	$_football_jqGrid = $("#football-list").jqGrid( {
		url : 'football!list.action',
		datatype : "json",
		autowidth : true,
		colNames : [ '场地数量', '场地名称', '场地环境', '创建日期', '状态', '提前生成天数', '提前发布天数' ],
		colModel : [ {
			name : 'number',
			editable: true,
			hidden : true
		},{
			name : 'name',
			align : 'center',
			editable : true,
			searchoptions : {
				sopt : $.common.plugin.jqGrid.search.text
			}
		}, {
			name : 'envType',
			align : 'center',
			editable : true,
			edittype : 'select',
			editoptions : {
				value : {
					'室内 ' : '室内 ',
					'室外 ' : '室外',
					'半露天' : '半露天'
				}
			},
			stype : 'select',
			searchoptions : {
				sopt : $.common.plugin.jqGrid.search.select
			}
		}, {
			name : 'createDate',
			align : 'center',
			search : false,
			formatter : function(cellvalue, options, rowObject) {
				return cellvalue == null ? '' : cellvalue.substr(0, 10);
			}
		}, {
			name : 'status',
			align : 'center',
			stype : 'select',
			editable : true,
			edittype : 'select',
			editoptions : {
				value : {
					'启用' : '启用',
					'维护' : '维护',
					'关闭' : '关闭'
				}
			},
			stype : 'select',
			searchoptions : {
				sopt : $.common.plugin.jqGrid.search.select
			}
		}, {
			name : 'advance',
			align : 'center',
			editable : true,
			formatter : 'integer',
			searchoptions : {
				sopt : $.common.plugin.jqGrid.search.integer
			}
		}, {
			name : 'issueAdvance',
			align : 'center',
			editable : true,
			formatter : 'integer',
			searchoptions : {
				sopt : $.common.plugin.jqGrid.search.integer
			}
		}],
		prmNames : $.common.plugin.jqGrid.prmNames,
		jsonReader : $.common.plugin.jqGrid.jsonReader,
		height : 420,
		rowNum : 20,
		rowList : [ 20, 30, 40 ],
		pager : '#football-pager',
		viewrecords : true,
		sortname : 'name',
		sortorder : "desc",
		caption : "[足球]场地列表（当前场馆：" + cvn + "）",
		rownumbers : true,
		editurl : 'football!save.action',
		gridComplete : function() {
		}
	}).jqGrid('navGrid', '#football-pager', {
		add : true,
		edit : true,
		view : false,
		del : true,
		searchtext : '查询',
		refreshtext : '刷新'
	}, { // edit options
		editCaption : '编辑[足球]场地',
		width : 500,
		recreateForm : true,
		closeAfterEdit : true,
		bottominfo: $('#issueDayRule').html(),
		savekey : [true, 13],
		beforeShowForm: function(formid) {
			// 验证
			football_validatorForEdit();
		},
		beforeSubmit: function() {
			var valid = $("#" + football_formListId).valid();
			return [valid, '表单有 ' + football_validator.numberOfInvalids() + ' 项错误，请检查！'];
		}
	}, { // add options
		addCaption : '添加[足球]场地',
		width : 400,
		closeAfterAdd : true,
		recreateForm : true,
		bottominfo: $('#issueDayRule').html(),
		savekey : [true, 13],
		editData : {
			batch : true
		},
    	beforeShowForm: function(formid) {
			// 隐藏场地名称
			$('#tr_name', formid).hide();
			
			// 显示批量输入框
			$('#tr_number', formid).show();
			
			// 验证
			football_validatorForAdd();
			
			// 设置默认值
			if (protocol) {
				$('#issueAdvance', formid).val(protocolDays);
			} else {
				$('#issueAdvance', formid).val(normalDays);
			}
	    },
	    beforeSubmit: function() {
			var valid = $("#" + football_formListId).valid();
			return [valid, '表单有 ' + football_validator.numberOfInvalids() + ' 项错误，请检查！'];
		}
	}, { // delete options
		caption : '删除[足球]场地',
		url : 'football!delete.action'
	}, { // search options
		multipleSearch : true,
		searchhidden : true
	}, { // view options
	});
};

// 添加和修改公用验证规则
var football_addAndEditRules = {
	envType: {
		required: true
	},
	status: {
		required: true
	},
	advance: {
		required: true,
		rangelength: [1, 2],
		number: true
	},
	issueAdvance: {
		required: true,
		rangelength: [1, 2],
		number: true
	}
};

/**
 * 添加时验证
 * @return
 */
function football_validatorForAdd() {
	var tempRules = $.extend({
		number: {
		required: true,
		number: true,
		range : [1, 10],
		rangelength: [1, 2]
	}}, football_addAndEditRules);
	football_validator = $("#" + football_formListId).validate({
        rules: tempRules,
        errorPlacement: $.common.plugin.validator.error,
        success: $.common.plugin.validator.success
    });
}

/**
 * 编辑时验证
 * @return
 */
function football_validatorForEdit() {
	football_validator = $("#" + football_formListId).validate({
        rules: football_addAndEditRules,
        errorPlacement: $.common.plugin.validator.error,
        success: $.common.plugin.validator.success
    });
}