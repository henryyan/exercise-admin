/**
 * 数据字典JavaScript 功能：数据字典的列表以及CRUD操作
 * 
 * @author HenryYan
 */
$(function() {
	// 自动根据窗口大小改变数据列表大小
	$.common.plugin.jqGrid.autoResize({
		dataGrid: '#list',
		callback: listPropDatas
	});
});

var validator;

/**
 * 加载系统属性列表
 * 
 * @return
 */
function listPropDatas(size) {
    $("#list").jqGrid({
        url: 'data-dict.action',
        datatype: "json",
        colNames: ['主键ID', '数据字典类型', '数据字典名称', '数据字典代码', '数据字典值','有效性', '排序号'],
        colModel: [{
            name: 'id',
            hidden: true,
			editable: true,
			editoptions:{
    			hidden: true
    		}
        }, {
            name: 'librarytype',
            align: 'center',
			editable: true,
            edittype: 'text',
            editoptions: {
	        	size :20,
	            maxlength: 200
            },
            searchoptions : {
    			sopt : $.common.plugin.jqGrid.search.text
    		}
        }, {
            name: 'libraryname',
            align: 'center',
			editable: true,
			edittype: 'text'
        }, {
            name: 'librarycode',
            align: 'center',
			editable: true,
            edittype: 'text',
            editoptions: {
                size: 20,
                maxlength: 50
            },
            formoptions: {
            	elmsuffix: $.common.plugin.jqGrid.form.must
            },
            searchoptions : {
    			sopt : $.common.plugin.jqGrid.search.text
    		}
        }, {
            name: 'libraryvalue',
            align: 'center',
			editable: true,
			edittype: 'text',
			editoptions: {
	            size: 20,
	            maxlength: 50
	        }
        }, {
            name: 'enabled',
            align: 'center',
			editable: true,
			edittype: "select",
			editoptions:{
        		value: {'true' : "是", 'false' : "否"}
        	},
        	stype : 'select',
        	searchoptions : {
        		value : {1 : '是', 0 : '否'}
        	},
			formatter : $.common.plugin.jqGrid.formatter.trueOrfalse
        }, {
            name: 'sort',
            align: 'center',
			editable: true,
			edittype: "text"
        }],
        prmNames: $.common.plugin.jqGrid.prmNames,
        jsonReader: $.common.plugin.jqGrid.jsonReader,
        width: size.width,
        height: size.height,
        rowNum: 20,
        rowList: [20, 30, 40],
        pager: '#pager',
        viewrecords: true,
        caption: "数据字典管理",
        rownumbers: true,
        editurl: 'data-dict!save.action',
        grouping: true,
       	groupingView : {
       		groupField : ['libraryname'],
       		groupText : ['<b>{0} - {1} 项</b>']
       	},
       	gridComplete : function() {
       		$('#t_list').height(30);
       		$('<button/>', {
       			text : '测控是'
       		}).button({
       			icons : {
       				primary : 'ui-icon-heart'
	       		}
       		}).appendTo('#t_list');
       	}
    }).jqGrid('navGrid', '#pager', {
		add: true,
        edit: true,
		view: true,
        del: true,
		searchtext: '查询',
		refreshtext: '刷新'
    }, 
    { // edit options
    	recreateForm: false,
    	closeAfterEdit: false,
    	width : 500,
    	savekey : $.common.plugin.jqGrid.keys.svekey,
    	navkeys : $.common.plugin.jqGrid.keys.navkeys,
    	beforeShowForm: function(formid) {
    		// 注册表单验证事件
    		validatorForm();
	    },
    	beforeSubmit: function() {
			var valid = $("#FrmGrid_list").valid();
			return [valid, '表单有 ' + validator.numberOfInvalids() + ' 项错误，请检查！'];
		}
    }, 
    { // add options
    	recreateForm: true,
    	clearAfterAdd: false,
    	closeAfterEdit: false,
    	width : 500,
    	savekey : $.common.plugin.jqGrid.keys.svekey,
    	navkeys : $.common.plugin.jqGrid.keys.navkeys,
    	beforeShowForm: function(formid) {
    		// 注册表单验证事件
    		validatorForm();
	    },
    	beforeSubmit: function() {
			var valid = $("#FrmGrid_list").valid();
			return [valid, '表单有 ' + validator.numberOfInvalids() + ' 项错误，请检查！'];
		}
    }, 
    { // delete options
    	url: 'data-dict!delete.action'
    }, {
    	// search
    	multipleSearch: true
    }, {
    	// view optinos
    	beforeShowForm: function(formid) {
    		$.common.plugin.jqGrid.navGrid.showAllField(formid);
	    }
    }).jqGrid('navButtonAdd', '#pager', {
		caption: "重载字典",
		title: "重新设置内存数据字典",
	   	buttonicon: "ui-icon-arrowrefresh-1-s",
	   	onClickButton: function(){
			reloadDatalibrary();
	   	}
	});
       
}

/**
 * 表单验证
 * 
 * @return
 */
function validatorForm() {
	validator = $("#FrmGrid_list").validate({
        rules: {
			librarytype: {
				required: true
			},
			libraryname: {
				required: true
			},
			librarycode: {
				required: true,
				maxlength: 32,
				remote : {
					url: "data-dict!validateLibrarycode.action",
					type: "post",
					dataType:"json",
					data: {
						newId : function()	{
							return $("#id").val();
						},
						librarycode: function(){
							return $("#librarycode").val();
						}
					}
				}
			},
			libraryvalue: {
				required: true
			},
			sort: {
				required: true,
				number: true,
				digits: true
			}
		},
		messages:{
			librarycode : {
				remote:"数据字典代码已存在，请重新输入！"
			}
		},
        errorPlacement: $.common.plugin.validator.error,
        success: $.common.plugin.validator.success
    });
}

function reloadDatalibrary() {
	var reloadDialog = $('<div title="重载数据字典">正在重载数据字典，请稍等……</div>').dialog({
		height: 100,
		modal: true
	});
	$.get('data-dict!reload.action', function(resp){
		reloadDialog.dialog('close');
		if (resp == 'success') {
			alert('重载数据字典完毕！');
		} else {
			alert(resp);
		}
	});
}
