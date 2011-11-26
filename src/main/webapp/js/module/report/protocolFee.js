$(function() {

	// 设置默认日期
    if ($('#startDate').val() == '') {
        $('#startDate').val(strSystemDate);
    }

    // 报表日期选择
    $('.Wdate').click(function() {
        WdatePicker({
            onpicked: function(dp) {
        		if (dp.el.id == 'startDate') {
        			WdatePicker({
                        el: 'endDate',
                        minDate: $('#startDate').val()
                    });
        		}
            }
        });
    });
    
    $('#submit').click(function(){
    	var url = ctx + "/report/protocolFee.action?startDate=" + $('#startDate').val();
		if ($('#endDate').val() != '') {
			url += "&endDate=" + $('#endDate').val();
		}
		location.href = url;
    });
    
    sumProtocolFee();

	// 加载数据列表
    loadOrderData();

});

/**
 * 统计协议服务费
 * @return
 */
function sumProtocolFee() {
	// 汇总协议服务费
    var sumUrl = ctx + "/report/allActivity!sumProtocolFee.action?startDate=" + $('#startDate').val();
	if ($('#endDate').val() != '') {
		sumUrl += "&endDate=" + $('#endDate').val();
	}
    $.get(sumUrl, function(sum){
    	$('#feeSum').removeClass("onLoad").text(sum);
    });
    
    var sumUrl = ctx + "/report/allActivity!sumActivityPrice.action?startDate=" + $('#startDate').val();
	if ($('#endDate').val() != '') {
		sumUrl += "&endDate=" + $('#endDate').val();
	}
    $.get(sumUrl, function(sum){
    	$('#priceSum').removeClass("onLoad").text(sum);
    });
}

function loadOrderData() {
	var url = "allActivity.action?startDate=" + $('#startDate').val();
	if ($('#endDate').val() != '') {
		url += "&endDate=" + $('#endDate').val();
	}
    $("#list").jqGrid({
        url: url,
        datatype: "json",
        colNames: ['订单号', '订单时间', '联系人', '场地信息', '锻炼日期', '时段', '原价', '实付', '协议服务费', '付款方式'],
        colModel: [{
        	name: 'fieldOrder.id',
        	align: 'center'
        }, {
            name: 'fieldOrder.bookTime',
            align: 'center',
            search: false,
            formatter: function(cellvalue) {
        		if (!cellvalue) {
        			return "";
        		}
        		var dateAndTime = cellvalue.split('T');
	        	return dateAndTime[1] + "<br/>" + dateAndTime[0];
	        }
        }, {
        	name: 'fieldOrder.contact',
        	align: 'center',
        	hidden: true
        }, {
            name: 'fieldInfo',
            align: 'center',
            search: false,
            width: 200,
            formatter: function(cellvalue, options, rowObject) {
	        	return "<img src='" + ctx + "/images/field/" + rowObject.fieldType + ".gif' align='absmiddle' /><br/>" + rowObject.fieldName; 
	        }
        }, {
            name: 'usableDate',
            search: false,
			formatter: function ( cellvalue, options, rowObject ) {
				return cellvalue == null ? '' : cellvalue.substr(0, 10);
			}
        }, {
            name: 'period',
            search: false
        }, {
            name: 'fieldOrder.standardPrice',
            formatter: 'number',
            width: 80,
            align: 'right'
        }, {
            name: 'fieldOrder.paymentSum',
            formatter: 'number',
            width: 80,
            align: 'right'
        }, {
            name: 'fieldOrder.paymentCommision',
            formatter: 'number',
            align: 'right'
        }, {
            name: 'fieldOrder.paymentStyle',
            align: 'center',
            search: false
        }],
        prmNames: $.common.plugin.jqGrid.prmNames,
        jsonReader: $.common.plugin.jqGrid.jsonReader,
        width: 780,
        height: 360,
        rowNum: 20,
        rowList: [20, 30, 40],
        pager: '#pager',
        viewrecords: true,
        rownumbers: true,
        caption: "协议服务费统计列表"
    }).jqGrid('navGrid', '#pager', {
		add: false,
        edit: false,
		view: true,
        del: false,
        search: false,
        viewtext: '详细',
		searchtext: '查询',
		refreshtext: '刷新',
		afterRefresh: function() {
    		sumProtocolFee();
	    }
    }, {}, {}, {}, {multipleSearch: true}, {
    	beforeShowForm : function(formid) {
    		$('tr[id^=tr].FormData', formid).show();
	    }
    }).ajaxComplete(function(evt, request, settings){
    	var json = request.responseText.toJson();
    	if (json.page) {
    		$('#orderNumber').removeClass('onLoad').text(json.page.totalCount);
    	}
    }).jqGrid('navButtonAdd', '#pager', {
    	caption : "字段",
		title : "设置列表显示的字段",
		buttonicon : "ui-icon-wrench",
		onClickButton : function() {
			$(this).jqGrid('setColumns', {
				width : 280,
				ShrinkToFit : true
			});
		}
    });
}
