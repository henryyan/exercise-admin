/**
 * 足球场地信息设置
 * 
 * @author HenryYan
 */
var $_football_jqGrid;
var football_lastsel;
var football_validator;
var football_formListId = "FrmGrid_football-list";
var football_maxIssueDate = null;
var adminUrl = null;
$(function() {
	listFootballDatas();
	createFootballActivity();
});

/**
 * 选择活动生成日期
 * @return
 */
function lastDateFootballPick() {
	if (football_maxIssueDate == null) {
		if (adminUrl == null) {
			$.common.system.getExerciseUrl({
				callback : function(exerciseUrl) {
					adminUrl = exerciseUrl;
					football_jsonpGet();
				}
			});
		} else {
			football_jsonpGet();
		}
	} else {
		WdatePicker({
			el: 'footballLastDate',
			minDate: systemDate,
			maxDate: football_maxIssueDate
		});
	}
	
}

//通过JSONP技术获取最大生成天数
function football_jsonpGet() {
	$.jsonp( {
		url : adminUrl + "/JsonpServlet?callback=?",
		data : {
			action : 'getMaxIssueDays',
			fieldType : 'football',
			venueId : cvi
		},
		success : function(repMaxDate) {
			var maxDate = parseInt(repMaxDate) + 30;
			football_maxIssueDate = $.common.custom.dateAdd(systemDate, maxDate, 'd');
			WdatePicker({
				el: 'footballLastDate',
				minDate: systemDate,
				maxDate: football_maxIssueDate
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
function createFootballActivity() {
	$('#footballCreateActivity').click(function(){
		if (adminUrl == null) {
			$.common.system.getExerciseUrl({
				callback : function(exerciseUrl) {
					adminUrl = exerciseUrl;
					inner_createFootballActivity();
				}
			});
		} else {
			inner_createFootballActivity();
		}
	});
	
	function inner_createFootballActivity() {
		$.blockUI({
	        message:  '<h5><img src="' + ctx + '/images/ajaxLoader.gif" class="status"/>正在生成活动，请稍候……</h5>',
			css: { width: '400px' }
	    });
		$.jsonp( {
			url : adminUrl + "/JsonpServlet?callback=?",
			data : {
				action : 'createActivity',
				fieldType : 'football',
				lastDate : $('#footballLastDate').val(),
				venueId : cvi
			},
			success : function(resp) {
				if (resp == 'true') {
					$('#footballLastDate').val('');
					$('#football-list').trigger("reloadGrid");
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
 * 足球场地列表
 * 
 * @return
 */
function listFootballDatas() {
	$_football_jqGrid = $("#football-list").jqGrid( {
		url : 'football!list.action',
		datatype : "json",
		autowidth : true,
		colNames : ca.colNames,
		colModel : ca.colModel,
		prmNames : $.common.plugin.jqGrid.prmNames,
		jsonReader : $.common.plugin.jqGrid.jsonReader,
		height : 390,
		rowNum : 20,
		rowList : [ 20, 30, 40 ],
		pager : '#football-pager',
		viewrecords : true,
		sortname : 'name',
		sortorder : "desc",
		caption : "[足球]场地活动信息设置（当前场馆：" + cvn + "）",
		rownumbers : true,
		editurl : 'football!save.action',
		gridComplete : function() {
			$('#footballLastDate').unbind('click').bind('click', lastDateFootballPick);
		}
	}).jqGrid('navGrid', '#football-pager', {
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
			football_validatorForEdit();
		},
		beforeSubmit: function() {
			var valid = $("#" + football_formListId).valid();
			return [valid, '设置有 ' + football_validator.numberOfInvalids() + ' 项错误，请检查！'];
		}
	}, { // add options
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
function football_validatorForEdit() {
	football_validator = $("#" + football_formListId).validate({
        rules: football_addAndEditRules,
        errorPlacement: $.common.plugin.validator.error,
        success: $.common.plugin.validator.success
    });
}