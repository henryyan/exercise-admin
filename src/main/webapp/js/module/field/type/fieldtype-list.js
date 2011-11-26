/**
 * 场地类型列表
 * 功能：场馆类型的列表以及CRUD操作
 * @author HenryYan 
 */
var $jqGrid;
$(function() {
	
	listDatas();
	
	// 开启、关闭
	$('.enable :radio').live('change', changeEnable);
});

/**
 * 启用、禁用场地类型
 * @return
 */
function changeEnable() {
	var rowId = $("#list").jqGrid('getGridParam','selrow');
	var srcEle = this;
	
	$.ajax({
		url : ctx + '/field/type/type!changeEnable.action',
		data : {
			id : rowId,
			status : $(srcEle).val()
		},
		success : function(resp) {
			if (resp == 'success') {
				alert('设置成功!');
			} else {
				alert('设置失败！');
			}
		}
	});
	
}

/**
 * 场地类型列表
 * @return
 */
function listDatas() {
	$jqGrid = $("#list").jqGrid({
        url: 'type!list.action',
        datatype: "json",
        autowidth: true,
        colNames: ['场地名称', '英文标示', '是否启用'],
        colModel: [{
            name: 'typeName',
            align: 'center',
            editable: true
        }, {
            name: 'typeId',
            align: 'center',
            editable: true
        }, {
        	name: 'enable',
        	align: 'center',
            editable: true,
        	classes: 'enable',
        	edittype: 'select',
            editoptions: {
        		value: {'true': '启用', 'false': '禁用'}
        	},
        	formatter: function(cellvalue, options, cellobject) {
        		var cellId = cellobject.id;
        		var checked = cellobject.enable ? " checked" : "";
        		var unChecked = !cellobject.enable ? " checked" : "";
        		return "<input type='radio' id='eanble" + cellId + "1' name='eanble" + cellId 
        				+ "' value='true'" + checked + "/><label for='eanble" + cellId + "1' title='启用此类型'>启 用</label>"
        				+ "<input type='radio' id='eanble" + cellId + "2' name='eanble" + cellId 
        				+ "' value='false'" + unChecked + "/><label for='eanble" + cellId + "2' title='禁用此类型'>禁 用</label>";
        	}
        }],
        prmNames: $.common.plugin.jqGrid.prmNames,
        jsonReader: $.common.plugin.jqGrid.jsonReader,
        width: 770,
        height: 500,
        rowNum: 20,
        rowList: [20, 30, 40],
        pager: '#pager',
        viewrecords: true,
        sortname: 'typeName',
        sortorder: "desc",
        caption: "场地类型设置",
        rownumbers: true,
        editurl: 'type!save.action',
        gridComplete: function() {
			$('.enable').buttonset();
		}
    }).jqGrid('navGrid', '#pager', {
    	add: true,
    	edit: true,
		view: false,
        del: true,
        searchtext: '查询',
        refreshtext: '刷新'
    }, 
    { // edit options
    	recreateForm: true,
    	closeAfterEdit: true
    }, 
    { // add options
    	closeAfterAdd: true,
    	recreateForm: true
    }, 
    { // delete options
    	caption: '删除场地类型',
    	url: 'type!delete.action'
    },
    { // search options
    	multipleSearch: true,
    	searchhidden: true
    },
    { // view options
    });
}