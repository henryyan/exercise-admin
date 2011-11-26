$(function() {
	
	listData();
	
});

/**
 * 加载数据列表
 * @return
 */
function listData() {
	$jqGrid = $("#list").jqGrid({
        url: ctx + '/report/account-order.action?filter_EQB_paymentStatus=true',
        datatype: "json",
        autowidth: true,
        colNames: ['付款编号', '联系人', '电话', '付款金额', '付款时间', '付款方式', '帐号', '操作'],
        colModel: [{
			name: 'id',
			width: 100
		}, {
			name: 'contact',
			align: 'center',
			width: 70,
		}, {
			name: 'phone',
			align: 'center',
			width: 80
		}, {
			name: 'payTotal',
			width: 80
		}, {
			name: 'payTime',
			align: 'center',
			searchoptions: {
				dataInit: function(elem) {
					$(elem).addClass('Wdate').click(WdatePicker);
				},
				sopt: ['cn']
			},
			formatter: 'date',
			formatoptions : {
				srcformat : 'Y-m-dTH:i:s',
				newformat : 'Y-m-d H:i:s'
			}
		}, {
			name: 'payPlatform',
			align: 'center',
			width: 80
		}, {
			name: 'platformAccount',
			width: 90
		}, {
			name: 'options',
			align: 'center',
			width: 80,
			formatter: function(cellvalue, options, rowObject) {
				if (rowObject.fieldOrderSize && rowObject.fieldOrderSize > 0) {
					return "<a href='#' aoid='" + rowObject.id + "' class='view-field-order' title='有" + rowObject.fieldOrderSize + "个和此付款订单有关的活动'>查看活动订单</a>";
				} else {
					return '';
				}
			}
		}],
        prmNames: $.common.plugin.jqGrid.prmNames,
        jsonReader: $.common.plugin.jqGrid.jsonReader,
        width: 770,
        height: 525,
        rowNum: 20,
        rowList: [20, 30, 40],
		sortorder: "desc",
        sortname: 'payTime',
        pager: '#pager',
        viewrecords: true,
        caption: "付款订单列表",
        rownumbers: true,
		gridComplete: function() {
			
			// 查看活动订单列表
			$('.view-field-order').unbind('click').click(function() {
				var accountOrderId = $(this).attr('aoid');
				$('#fieldOrderTemplate').dialog({
					title: '查看活动订单详细',
					modal: true,
					width: 600,
					height: 300,
					open: function() {
						$('#fieldOrderTable .data-item').remove();
						$('#loading').show();
						$.getJSON(ctx + '/report/allActivity!listByAccountOrder.action', {
							accountOrderId: accountOrderId
						}, function(data) {
							$('#loading').hide();
							$.each(data, function(i, v) {
								var $tr = $('<tr/>', {
									'class' : 'data-item'
								});
								
								$('<td/>', {
									html: v.zhFieldType
								}).appendTo($tr);
								
								$('<td/>', {
									html: v.fieldName
								}).appendTo($tr);
								
								$('<td/>', {
									html: v.usableDate
								}).appendTo($tr);
								
								$('<td/>', {
									html: v.period
								}).appendTo($tr);
								
								$('<td/>', {
									html: v.fieldOrder.contact
								}).appendTo($tr);
								
								$('<td/>', {
									html: v.fieldOrder.phone
								}).appendTo($tr);
								
								$tr.hover(function() {
									$(this).addClass('ui-state-hover');
								}, function() {
									$(this).removeClass('ui-state-hover');
								}).appendTo('#fieldOrderTable');
							});
						});
					}
				});
			});
		}
    }).jqGrid('navGrid', '#pager', {
    	add: false,
    	edit: false,
		view: true,
        del: false,
        viewtext: '查看',
        searchtext: '查询',
        refreshtext: '刷新'
    }, 
    { // edit options
    }, 
    { // add options
    }, 
    { // delete options
    },
    { // search options
    	multipleSearch: true,
    	searchhidden: true,
    	closeAfterSearch : true
    },
    { // view options
    });
}