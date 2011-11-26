/**
 * 场馆信息
 * 功能：场馆信息的列表以及CRUD操作
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
        url: 'member!list.action',
        datatype: "json",
        //autowidth: true,
        colNames: ['场馆名称', '场馆密码', '密码校对', '场地工商号', '所在地', 
                   '服务区域', '场地地址', '场地电话1', '场地电话2', '场地传真', 
                   '人制', '营业时间', '时间段价格1', '时间段价格2', '时间段价格3', 
                   '时间段价格4', '周一至周五', '周末', '有无会员', '会员价格', '创建时间'],
        colModel: [{
            name: 'venueName',
            align: 'center',
			editable: true,
            edittype: 'text',
            editoptions: {
                size: 15,
                maxlength: 20
            }
        }, {
            name: 'venuePassword',
            align: 'center',
            hidden: true,
			editable: true,
            edittype: 'text',
            editoptions: {
                size: 15,
                maxlength: 20
            }
        }, {
            name: 'venueRepassword',
            align: 'center',
            hidden: true,
			editable: true,
            edittype: 'text',
            editoptions: {
                size: 15,
                maxlength: 20
            }
        }, {
            name: 'venueIcno',
            align: 'center',
            hidden: true,
			editable: true,
            edittype: 'text',
            editoptions: {
                size: 15,
                maxlength: 20
            }
        }, {
            name: 'city',
            align: 'center',
			editable: true
        }, {
            name: 'district',
            align: 'center',
			editable: true
        }, {
            name: 'adress',
            align: 'center',
            hidden: true,
			editable: true
        }, {
            name: 'phone1',
            align: 'center',
            hidden: true,
			editable: true
        }, {
            name: 'phone2',
            align: 'center',
            hidden: true,
			editable: true
        }, {
            name: 'fax',
            align: 'center',
            hidden: true,
			editable: true
        }, {
            name: 'peopleNum',
            align: 'center',
            hidden: true,
			editable: true
        }, {
            name: 'bussinessTime',
            align: 'center',
			editable: true
        }, {
            name: 'timeiPrice1',
            align: 'center',
            hidden: true,
			editable: true
        }, {
            name: 'timeiPrice2',
            align: 'center',
            hidden: true,
			editable: true
        }, {
            name: 'timeiPrice3',
            align: 'center',
            hidden: true,
			editable: true
        }, {
            name: 'timeiPrice4',
            align: 'center',
            hidden: true,
			editable: true
        }, {
            name: 'monFri',
            align: 'center',
			editable: true
        }, {
            name: 'weekend',
            align: 'center',
			editable: true
        }, {
            name: 'memberYn',
            index: 'memberYn',
            align: 'center',
			editable: true,edittype: 'select',
            editoptions: {
        		value: {'N': '否', 'Y': '是'}
        	},
			formatter: function(cellvalue, options, cellobject) {
        		if (cellvalue == 'Y') return '是';
        		if (cellvalue == 'N') return '否';
        		return '';
        	}
        }, {
            name: 'memberPrice',
            index: 'memberPrice',
            align: 'center',
            hidden: true,
			editable: true
        }, {
            name: 'createdAt',
            index: 'createdAt',
            hidden: true,
            align: 'center'
        }],
        prmNames: $.common.plugin.jqGrid.prmNames,
        jsonReader: $.common.plugin.jqGrid.jsonReader,
        width: 770,
        height: 445,
        rowNum: 20,
        rowList: [20, 30, 40],
        pager: '#pager',
        viewrecords: true,
        sortname: 'venueName',
        sortorder: "desc",
        caption: "场馆会员列表",
        rownumbers: true,
        editurl: 'member!save.action'
    }).jqGrid('navGrid', '#pager', {
		add: true,
        edit: true,
		view: true,
        del: true,
		searchtext: '查询',
		refreshtext: '刷新'
    }, 
    { // edit options
    	caption: '编辑场馆信息',
    	width: 400,
    	closeAfterEdit: true,
    	recreateForm: true,
    	dataheight: 400,
    	beforeShowForm: $.common.plugin.jqGrid.navGrid.showAllField
    }, 
    { // add options
    	caption: '添加场馆信息',
    	width: 400,
    	closeAfterAdd: true,
    	recreateForm: true,
    	dataheight: 400,
    	beforeShowForm: $.common.plugin.jqGrid.navGrid.showAllField
    }, 
    { // delete options
    	caption: '删除场馆信息',
    	url: 'member!delete.action'
    },
    { // search options
    	multipleSearch: true,
    	searchhidden: true
    },
    { // view options
    	caption: '查看场馆信息',
    	width: 400,
    	navkeys: [true, 38, 40],
    	closeOnEscape: true,
    	dataheight: 400,
    	beforeShowForm: $.common.plugin.jqGrid.navGrid.showAllField
    }).jqGrid('navButtonAdd', '#pager', $.common.plugin.jqGrid.navButtonAdd.setColumns);
}