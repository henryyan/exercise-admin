var ca = {
	colNames: ['场地名称', '场地环境', '状态', '提前生成天数', '提前发布天数', '最后生成日期', '网上发布到' ],
	colModel: [{
		name : 'name',
		align : 'center',
		searchoptions : {
			sopt : $.common.plugin.jqGrid.search.text
		}
	}, {
		name : 'envType',
		align : 'center',
		stype : 'select',
		searchoptions : {
			sopt : $.common.plugin.jqGrid.search.select
		}
	}, {
		name : 'status',
		align : 'center',
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
	}, {
		name : 'issueLastDate',
		align : 'center',
		search : false,
		formatter : function(cellvalue, options, rowObject) {
			return cellvalue == null ? '' : cellvalue.substr(0, 10);
		}
	}, {
		align : 'center',
		formatter : function(cellvalue, options, rowObject) {
			return $.common.custom.dateAdd(strCpSystemDate, protocolVenueIssueDays, 'd', 'string');
		}
	}]
};