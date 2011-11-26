$(function(){
	listMemberDatas();
	
	listRecordDatas();
	
	// 查看详细使用记录
	$('.view-detail').live('click', viewCardUsageDetail);
});

/**
 * 加载会员卡列表
 * @return
 */
function listMemberDatas() {
	$jqGrid = $("#list").jqGrid({
        url: 'memberCard.action',
        datatype: "json",
        autowidth: true,
        colNames: ['会员卡号', '会员姓名', '会员卡类型', '生效日期', '有效期',
					'入会日期', '手机号码', '卡内余额', '状态'],
        colModel: [{
            name: 'cardNumber',
			searchoptions : {
				sopt : $.common.plugin.jqGrid.search.text
			}
        }, {
            name: 'name',
			searchoptions : {
				sopt : $.common.plugin.jqGrid.search.text
			}
        }, {
            name: 'cardType.typeName',
            sortable : false
        }, {
            name: 'effectDate',
            formatter : function(cellvalue, options, rowObject) {
				return cellvalue == null ? '' : cellvalue.substr(0, 10);
			},
			search : false
        }, {
            name: 'periodValidity',
            formatter : function(cellvalue, options, rowObject) {
				return cellvalue == null ? '' : cellvalue.substr(0, 10);
			},
			search : false
        }, {
            name: 'createDate',
            formatter : function(cellvalue, options, rowObject) {
				return cellvalue == null ? '' : cellvalue.substr(0, 10);
			},
			search : false
        }, {
            name: 'mobilePhone',
			searchoptions : {
				sopt : $.common.plugin.jqGrid.search.text
			}
        }, {
            name: 'balance',
			searchoptions : {
				sopt : $.common.plugin.jqGrid.search.integer
			}
        }, {
            name: 'cardStatus',
            sortable : false,
            search : false
        }],
        prmNames: $.common.plugin.jqGrid.prmNames,
        jsonReader: $.common.plugin.jqGrid.jsonReader,
        width: 770,
        height: 250,
        rowNum: 20,
        rowList: [20, 30, 40],
		sortorder: "desc",
        sortname: 'createDate',
        pager: '#pager',
        viewrecords: true,
        caption: "会员卡列表",
        rownumbers: true,
        onSelectRow: function(ids) {
            if (ids == null) {
                ids = 0;
                if ($("#recordList").jqGrid('getGridParam', 'records') > 0) {
                    $("#recordList").jqGrid('setGridParam', {
                        url: "memberCardRecord.action?cardId=" + ids,
                        page: 1
                    });
                    $("#recordList").jqGrid().trigger('reloadGrid');
                }
            } else {
                $("#recordList").jqGrid('setGridParam', {
                    url: "memberCardRecord.action?cardId=" + ids,
                    page: 1
                });
                $("#recordList").jqGrid().trigger('reloadGrid');
            }
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

/**
 * 加载会员卡列表
 * @return
 */
function listRecordDatas() {
	$jqGrid = $("#recordList").jqGrid({
        url: 'memberCardRecord.action?cardId=0',
        datatype: "json",
        autowidth: true,
        colNames: ['使用日期', '使用时段', '用卡类型', '操作金额', '详细信息'],
        colModel: [{
            name: 'usageDate',
			width: 80,
			align: 'center',
			formatter: 'date',
            formatoptions : {
				srcformat : 'Y-m-dTH:i:s',
				newformat : 'Y-m-d H:i:s'
			}
        }, {
            name: 'usageTimeSlice',
			align: 'center'
        }, {
            name: 'usageType',
			width: 60,
			align: 'center'
        }, {
            name: 'optionTotal',
			width: 60,
			align: 'center'
        }, {
			name: 'detail',
			width: 50,
			align: 'center',
			formatter: function(cellvalue, options, rowObject) {
				if (rowObject.usageType != '支付') {
					return "";
				}
				return "<a href='#' cui='" + rowObject.id + "' class='view-detail'>查看</a>";
			}
		}],
        prmNames: $.common.plugin.jqGrid.prmNames,
        jsonReader: $.common.plugin.jqGrid.jsonReader,
        width: 770,
        height: 160,
        rowNum: 20,
        rowList: [20, 30, 40],
		sortorder: "desc",
        sortname: 'usageDate',
        pager: '#recordPager',
        viewrecords: true,
        caption: "会员卡使用记录",
        rownumbers: true
    }).jqGrid('navGrid', '#recordPager', {
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


/**
 * 查看详细使用记录 
 */
function viewCardUsageDetail() {
	var usageId = $(this).attr('cui');
	$('#cardUsageDetailTemplate').dialog({
		modal: true,
		title: '查看会员卡使用记录详细信息',
		width: 750,
		height: 300,
		open: function() {
			$('.loading').show();
			$('#cardUsageDetailTable').hide();
			$('#cardUsageDetailTable .data').remove();
			$.getJSON(ctx + '/member/memberCardRecord!loadCardUsageDetail.action', {
				cardUsageId: usageId
			}, function(orders) {
				$('.loading').hide();
				$('#cardUsageDetailTable').show();
				$.each(orders, function(i) {
					var $tr = $('<tr/>', {'class' : 'data'});
					$('<td/>', {align: 'center', html: i + 1}).appendTo($tr);
					$('<td/>', {html: this.contact}).appendTo($tr);
					$('<td/>', {
						title: '场地类型/场地编号',
						html: this.fieldZhType + "<span class='separator'>/</span>"
							+ this.fieldActivity.fieldName
					}).appendTo($tr);
					$('<td/>', {
						title: '活动日期/活动时段',
						html: this.fieldActivity.usableDate + "<span class='separator'>/</span>"
							+ this.fieldActivity.period
					}).appendTo($tr);
					$('<td/>', {
						title: '标准/优惠',
						html: this.standardPrice + "<span class='separator'>/</span>" + this.paymentSum
					}).appendTo($tr);
					$('<td/>', {
						align: 'center',
						width: '80px',
						html: this.bookTime.replace(' ', '<br/>')
					}).appendTo($tr);
					$('<td/>', {
						align: 'center',
						width: '80px',
						html: this.paymentTime.replace(' ', '<br/>')
					}).appendTo($tr);
					$('#cardUsageDetailTable').append($tr);
				});
				
				// 高亮
				$('#cardUsageDetailTable tr').hover(function() {
					$('td', this).addClass('ui-state-hover');
				}, function() {
					$('td', this).removeClass('ui-state-hover');
				});
			});
		}
	});
}