$(function(){
	loadMixData();
});

function loadMixData() {
	$("#mixfield-list").jqGrid( {
		url : 'common!mixList.action',
		datatype : "json",
		autowidth : true,
		colNames : [ '场地名称', '场地环境', '创建日期', '状态', '提前生成天数', '提前发布天数' ],
		colModel : [{
			name : 'name',
			align : 'center'
		}, {
			name : 'envType',
			align : 'center'
		}, {
			name : 'createDate',
			align : 'center',
			formatter : function(cellvalue, options, rowObject) {
				return cellvalue == null ? '' : cellvalue.substr(0, 10);
			}
		}, {
			name : 'status',
			align : 'center'
		}, {
			name : 'advance',
			align : 'center'
		}, {
			name : 'issueAdvance',
			align : 'center'
		}],
		prmNames : $.common.plugin.jqGrid.prmNames,
		jsonReader : $.common.plugin.jqGrid.jsonReader,
		height : 420,
		rowNum : 20,
		pager : '#mixfield-pager',
		viewrecords : true,
		sortname : 'name',
		sortorder : "desc",
		caption : "本场馆所有低于协议客户默认发布天数的场地",
		rownumbers : true
	}).jqGrid('navGrid', '#mixfield-pager', {
		add : false,
		edit : false,
		view : false,
		del : false,
		search: false,
		searchtext : '查询',
		refreshtext : '刷新'
	}, { // search options
		multipleSearch : true,
		searchhidden : true
	}, { // view options
	});
}