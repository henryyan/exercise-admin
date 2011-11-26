/**
 * 羽毛球场地列表 功能：羽毛球场地的列表以及CRUD操作
 * 
 * @author HenryYan
 */
var $_badmintoon_jqGrid;
var badmintoon_lastsel;
var badmintoon_validator;
var badmintoon_formListId = "FrmGrid_badmintoon-list";
$(function() {
	listBadmintoonDatas();
});

/**
 * 羽毛球场地列表
 * 
 * @return
 */
function listBadmintoonDatas() {
	$_badmintoon_jqGrid = $("#badmintoon-list").jqGrid( {
		url : 'badmintoon!list.action',
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
		pager : '#badmintoon-pager',
		viewrecords : true,
		sortname : 'name',
		sortorder : "desc",
		caption : "[羽毛球]场地列表（当前场馆：" + cvn + "）",
		rownumbers : true,
		editurl : 'badmintoon!save.action',
		gridComplete : function() {
		}
	}).jqGrid('navGrid', '#badmintoon-pager', {
		add : true,
		edit : true,
		view : false,
		del : true,
		searchtext : '查询',
		refreshtext : '刷新'
	}, { // edit options
		editCaption : '编辑[羽毛球]场地',
		width : 500,
		recreateForm : true,
		closeAfterEdit : true,
		bottominfo: $('#issueDayRule').html(),
		savekey : [true, 13],
		beforeShowForm: function(formid) {
			// 验证
			badmintoon_validatorForEdit();
		},
		beforeSubmit: function() {
			var valid = $("#" + badmintoon_formListId).valid();
			return [valid, '表单有 ' + badmintoon_validator.numberOfInvalids() + ' 项错误，请检查！'];
		}
	}, { // add options
		addCaption : '添加[羽毛球]场地',
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
			badmintoon_validatorForAdd();
			
			// 设置默认值
			if (protocol) {
				$('#issueAdvance', formid).val(protocolDays);
			} else {
				$('#issueAdvance', formid).val(normalDays);
			}
	    },
	    beforeSubmit: function() {
			var valid = $("#" + badmintoon_formListId).valid();
			return [valid, '表单有 ' + badmintoon_validator.numberOfInvalids() + ' 项错误，请检查！'];
		}
	}, { // delete options
		caption : '删除[羽毛球]场地',
		url : 'badmintoon!delete.action'
	}, { // search options
		multipleSearch : true,
		searchhidden : true
	}, { // view options
	});
};

// 添加和修改公用验证规则
var badmintoon_addAndEditRules = {
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
function badmintoon_validatorForAdd() {
	var tempRules = $.extend({
		number: {
		required: true,
		number: true,
		range : [1, 10],
		rangelength: [1, 2]
	}}, badmintoon_addAndEditRules);
	badmintoon_validator = $("#" + badmintoon_formListId).validate({
        rules: tempRules,
        errorPlacement: $.common.plugin.validator.error,
        success: $.common.plugin.validator.success
    });
}

/**
 * 编辑时验证
 * @return
 */
function badmintoon_validatorForEdit() {
	badmintoon_validator = $("#" + badmintoon_formListId).validate({
        rules: badmintoon_addAndEditRules,
        errorPlacement: $.common.plugin.validator.error,
        success: $.common.plugin.validator.success
    });
}