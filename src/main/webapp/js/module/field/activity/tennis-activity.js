/**
 * 网球场地信息设置
 * 
 * @author HenryYan
 */
var $_tennis_jqGrid;
var tennis_lastsel;
var tennis_validator;
var tennis_formListId = "FrmGrid_tennis-list";
var tennis_maxIssueDate = null;
var adminUrl = null;
$(function() {
	listTennisDatas();
	createTennisActivity();
});

/**
 * 选择活动生成日期
 * @return
 */
function lastDateTennisPick() {
	if (tennis_maxIssueDate == null) {
		if (adminUrl == null) {
			$.common.system.getExerciseUrl({
				callback : function(exerciseUrl) {
					adminUrl = exerciseUrl;
					tennis_jsonpGet();
				}
			});
		} else {
			tennis_jsonpGet();
		}
	} else {
		WdatePicker({
			el: 'tennisLastDate',
			minDate: systemDate,
			maxDate: tennis_maxIssueDate
		});
	}
	
}

//通过JSONP技术获取最大生成天数
function tennis_jsonpGet() {
	$.jsonp( {
		url : adminUrl + "/JsonpServlet?callback=?",
		data : {
			action : 'getMaxIssueDays',
			fieldType : 'tennis',
			venueId : cvi
		},
		success : function(repMaxDate) {
			var maxDate = parseInt(repMaxDate) + 30;
			tennis_maxIssueDate = $.common.custom.dateAdd(systemDate, maxDate, 'd');
			WdatePicker({
				el: 'tennisLastDate',
				minDate: systemDate,
				maxDate: tennis_maxIssueDate
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
function createTennisActivity() {
	$('#tennisCreateActivity').click(function(){
		if (adminUrl == null) {
			$.common.system.getExerciseUrl({
				callback : function(exerciseUrl) {
					adminUrl = exerciseUrl;
					inner_createTennisActivity();
				}
			});
		} else {
			inner_createTennisActivity();
		}
	});
	
	function inner_createTennisActivity() {
		$.blockUI({
	        message:  '<h5><img src="' + ctx + '/images/ajaxLoader.gif" class="status"/>正在生成活动，请稍候……</h5>',
			css: { width: '400px' }
	    });
		$.jsonp( {
			url : adminUrl + "/JsonpServlet?callback=?",
			data : {
				action : 'createActivity',
				fieldType : 'tennis',
				lastDate : $('#tennisLastDate').val(),
				venueId : cvi
			},
			success : function(resp) {
				if (resp == 'true') {
					$('#tennisLastDate').val('');
					$('#tennis-list').trigger("reloadGrid");
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
 * 网球场地列表
 * 
 * @return
 */
function listTennisDatas() {
	$_tennis_jqGrid = $("#tennis-list").jqGrid( {
		url : 'tennis!list.action',
		datatype : "json",
		autowidth : true,
		colNames : ca.colNames,
		colModel : ca.colModel,
		prmNames : $.common.plugin.jqGrid.prmNames,
		jsonReader : $.common.plugin.jqGrid.jsonReader,
		height : 390,
		rowNum : 20,
		rowList : [ 20, 30, 40 ],
		pager : '#tennis-pager',
		viewrecords : true,
		sortname : 'name',
		sortorder : "desc",
		caption : "[网球]场地活动信息设置（当前场馆：" + cvn + "）",
		rownumbers : true,
		editurl : 'tennis!save.action',
		gridComplete : function() {
			$('#tennisLastDate').unbind('click').bind('click', lastDateTennisPick);
		}
	}).jqGrid('navGrid', '#tennis-pager', {
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
			tennis_validatorForEdit();
		},
		beforeSubmit: function() {
			var valid = $("#" + tennis_formListId).valid();
			return [valid, '设置有 ' + tennis_validator.numberOfInvalids() + ' 项错误，请检查！'];
		}
	}, { // add options
	}, { // delete options
		caption : '删除[网球]场地',
		url : 'tennis!delete.action'
	}, { // search options
		multipleSearch : true,
		searchhidden : true
	}, { // view options
	});
};

// 添加和修改公用验证规则
var tennis_addAndEditRules = {
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
function tennis_validatorForEdit() {
	tennis_validator = $("#" + tennis_formListId).validate({
        rules: tennis_addAndEditRules,
        errorPlacement: $.common.plugin.validator.error,
        success: $.common.plugin.validator.success
    });
}