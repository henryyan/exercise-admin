/**
 * 羽毛球场地信息设置
 * 
 * @author HenryYan
 */
var $_badmintoon_jqGrid;
var badmintoon_lastsel;
var badmintoon_validator;
var badmintoon_formListId = "FrmGrid_badmintoon-list";
var badmintoon_maxIssueDate = null;
var adminUrl = null;
$(function() {
	listBadmintoonDatas();
	createBadmintoonActivity();
});

/**
 * 选择活动生成日期
 * @return
 */
function lastDateBadmintoonPick() {
	if (badmintoon_maxIssueDate == null) {
		if (adminUrl == null) {
			$.common.system.getExerciseUrl({
				callback : function(exerciseUrl) {
					adminUrl = exerciseUrl;
					badmintoon_jsonpGet();
				}
			});
		} else {
			badmintoon_jsonpGet();
		}
	} else {
		WdatePicker({
			el: 'badmintoonLastDate',
			minDate: systemDate,
			maxDate: badmintoon_maxIssueDate
		});
	}
	
}

//通过JSONP技术获取最大生成天数
function badmintoon_jsonpGet() {
	$.jsonp( {
		url : adminUrl + "/JsonpServlet?callback=?",
		data : {
			action : 'getMaxIssueDays',
			fieldType : 'badmintoon',
			venueId : cvi
		},
		success : function(repMaxDate) {
			var maxDate = parseInt(repMaxDate) + 30;
			badmintoon_maxIssueDate = $.common.custom.dateAdd(systemDate, maxDate, 'd');
			WdatePicker({
				el: 'badmintoonLastDate',
				minDate: systemDate,
				maxDate: badmintoon_maxIssueDate
			});
			
		},
		error : function(d, msg) {
			alert("error");
		}
	});
}

/**
 * 生成活动
 * @return
 */
function createBadmintoonActivity() {
	$('#badmintoonCreateActivity').click(function(){
		if (adminUrl == null) {
			$.common.system.getExerciseUrl({
				callback : function(exerciseUrl) {
					adminUrl = exerciseUrl;
					inner_createBadmintoonActivity();
				}
			});
		} else {
			inner_createBadmintoonActivity();
		}
	});
	
	function inner_createBadmintoonActivity() {
		$.blockUI({
	        message:  '<h5><img src="' + ctx + '/images/ajaxLoader.gif" class="status"/>正在生成活动，请稍候……</h5>',
			css: { width: '400px' }
	    });
		$.jsonp( {
			url : adminUrl + "/JsonpServlet?callback=?",
			data : {
				action : 'createActivity',
				fieldType : 'badmintoon',
				lastDate : $('#badmintoonLastDate').val(),
				venueId : cvi
			},
			success : function(resp) {
				if (resp == 'true') {
					$('#badmintoonLastDate').val('');
					$('#badmintoon-list').trigger("reloadGrid");
					$.unblockUI();
					alert('活动生成成功！');
				}
			},
			error : function(d, msg) {
				alert("活动生成失败！");
			}
		});
	}
}

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
		colNames : ca.colNames,
		colModel : ca.colModel,
		prmNames : $.common.plugin.jqGrid.prmNames,
		jsonReader : $.common.plugin.jqGrid.jsonReader,
		height : 390,
		rowNum : 20,
		rowList : [ 20, 30, 40 ],
		pager : '#badmintoon-pager',
		viewrecords : true,
		sortname : 'name',
		sortorder : "desc",
		caption : "[羽毛球]场地活动信息设置（当前场馆：" + cvn + "）",
		rownumbers : true,
		editurl : 'badmintoon!save.action',
		gridComplete : function() {
			$('#badmintoonLastDate').unbind('click').bind('click', lastDateBadmintoonPick);
		}
	}).jqGrid('navGrid', '#badmintoon-pager', {
		add : false,
		edit : true,
		view : false,
		del : false,
		edittext : '设置天数',
		editicon : 'ui-icon-wrench',
		edittitle : '设置[生成][发布]天数',
		searchtext : '查询',
		refreshtext : '刷新'
	}, { // edit options
		editCaption : '设置[生成][发布]天数',
		width : 300,
		bSubmit : '保存设置',
		recreateForm : true,
		closeAfterEdit : true,
		bottominfo: '修改任意一项更新设置',
		beforeShowForm: function(formid) {
			// 验证
			badmintoon_validatorForEdit();
		},
		beforeSubmit: function() {
			var valid = $("#" + badmintoon_formListId).valid();
			return [valid, '设置有 ' + badmintoon_validator.numberOfInvalids() + ' 项错误，请检查！'];
		}
	}, { // add options
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