/**
 * 系统属性JS
 * 功能：系统属性的列表以及CRUD操作
 * @author HenryYan 
 */
$(function() {
	listPropDatas();
});

/**
 * 加载系统属性列表
 * @return
 */
function listPropDatas() {
    $("#list").jqGrid({
        url: 'sysprop!list.action',
        datatype: "json",
        colNames: ['名称', '属性值', '描述', '是否显示'],
        colModel: [{
            name: 'propName',
            align: 'center',
			editable: true,
            edittype: 'text',
            editoptions: {
                size: 30,
                maxlength: 50
            }
        }, {
            name: 'propValue',
            align: 'center',
			editable: true,
            edittype: 'textarea',
            editoptions: {
	        	rows: '3',
	    		cols: '35',
	            maxlength: 200
            },
            formoptions: {
            	rowpos: 3
            },
            formatter: function(cellvalue, options, cellobject) {
            	return cellvalue;
            }
        }, {
            name: 'propDescribe',
            align: 'center',
            editable: true,
            edittype: 'textarea',
            editoptions: {
        		rows: '3',
        		cols: '35',
                maxlength: 200
            },
            formoptions: {
            	rowpos: 4
            }
        }, {
            name: 'visible',
            align: 'center',
            editable: true,
            edittype: 'select',
            editoptions: {
        		value: {'true': '是', 'false': '否'}
        	},
        	formoptions: {
        		rowpos: 2
        	}
        }],
        prmNames: $.common.plugin.jqGrid.prmNames,
        jsonReader: $.common.plugin.jqGrid.jsonReader,
        width: 770,
        height: 460,
        rowNum: 20,
        rowList: [20, 30, 40],
        pager: '#pager',
        viewrecords: true,
        sortname: 'propKey',
        sortorder: "desc",
        caption: "系统属性列表",
        rownumbers: true,
        editurl: 'sysprop!save.action'
    }).jqGrid('navGrid', '#pager', {
		add: true,
        edit: true,
		view: true,
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
    	url: 'sysprop!delete.action'
    }, {multipleSearch: true});
}