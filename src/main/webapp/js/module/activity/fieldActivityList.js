/**
 * 场馆信息 功能：场馆信息的列表以及CRUD操作
 * 
 * @author HenryYan
 */
var exerciseUrl = "";

//一周之前，从今天向前推一周
var minDate = $.common.custom.dateAdd(systemDate, -7, 'd', 'string');
var maxDate = "";
$(function() {
	
	// 加载数据列表
	$.common.system.getExerciseUrl( {
		callback : function(_exerciseUrl) {
			exerciseUrl = _exerciseUrl;
			listDatas();
		}
	});

	/**
	 * 注册显示日期
	 */
	$('.Wdate').bind('click', pickDate);
	
	// 日期导航
	selectDateEvent();

});

/**
 * 加载数据列表
 * 
 * @return
 */
function listDatas() {
	$('#row tbody').remove();
	$.blockUI({
        message:  '<h5 class="loading">正在载入活动列表，请稍候……</h5>',
		css: { width: '370px' }
    });
	$.jsonp( {
		url : exerciseUrl + "/JsonpServlet?callback=?",
		data : {
			action : 'fieldActivityList',
			fieldType : fieldType,
			fieldId : fieldId,
			venueId : cvi,
			pickedDate : $('#usableDate').val()
		},
		success : function(data) {
			$.unblockUI();
			$('#advance').val(data.advance);
			maxDate = $.common.custom.dateAdd(systemDate, parseInt(data.advance) - 1 , 'd');
			$('#pickedDate').val(data.pickedDate);
			$('#previousDate').text(data.preDate);
			$('#laterDate').text(data.lasterDate);

			var activities = data.activities;
			for (i in activities) {
				var $tr = $('<tr/>');
				joinActivityTd($tr, activities[i]);
				$tr.appendTo('#row');
			}
			$('.row tr:even').addClass('even');
			$('.row tr:odd').addClass('odd');

			tipOrderInfo();
		},
		error : function(d, msg) {
			alert("error");
		}
	});
}

function tipOrderInfo() {
	// 提示
	$('.order').tipTip( {
		content : $('#orderInfo').html(),
		enter : function(tipObj) {
			var orderId = $(tipObj).attr('orderId');
			$.jsonp( {
				url : exerciseUrl + "/JsonpServlet?callback=?",
				data : {
					action : 'loadOrder',
					orderId : orderId
				},
				success : function(order) {
					for (key in order) {
						$('#d' + key, '#tiptip_content').html(order[key]);
					}
				},
				error : function(d, msg) {
					alert("error");
				}
			});
		},
		exit : function(tipObj) {
			$('span', '#tiptip_content').text('');
		}
	});
}

function joinActivityTd($tr, activity) {
	$('<td/>', {
		text : activity.period
	}).appendTo($tr);

	$('<td/>', {
		text : activity.price
	}).appendTo($tr);

	// 预定情况
	$('<td/>',
	{
		html : function() {
			var ct = "";
			if (activity.fieldOrder && activity.fieldOrder.patch) {
				ct += "<span class='patch'>补登计</span>";
			}

			if (activity.activity == '保留') {
				ct += "<span id='status'>已保留</span>";
			}

			if (activity.activity == '作废') {
				ct += "<span id='status'>已作废</span>";
			}

			if (activity.activity == '未开始' || activity.activity == '锻炼') {

				ct += "&nbsp;<span id='status'>" + activity.orderUser
						+ "<a class='order' orderId='"
						+ activity.fieldOrder.id
						+ "' href='javascript:;'>已预订</a></span>";
			}
			
			if (activity.isOrderSite) {
				ct += "&nbsp;<span class='orderSite'>我要锻炼网预定</span>";
			}

			return ct;
		}
	}).appendTo($tr);

	$('<td/>', {
		text : activity.authenticode
	}).appendTo($tr);

	$('<td/>', {
		html : '&nbsp;'
	}).addClass(activity.verification == '1' ? 'correct' : '').appendTo($tr);

	$('<td/>', {
		text : activity.activityStatus
	}).appendTo($tr);
}

/**
 * 选择预订日期
 */
function pickDate() {
	WdatePicker( {
		minDate : minDate,
		maxDate : maxDate,
		onpicked : function(dp) {
			listDatas();
			controlDateNav();
		}
	});
}

/**
 * 今天、前一天、后一天控制
 * @return
 */
function selectDateEvent() {
	// 今天
	$('#today').click(function(){
		$('#usableDate').val('');
		listDatas();
		controlDateNav();
	});
	
	// 前一天
	$('#previous').click(function(){
		$('#usableDate').val($('#previousDate').text());
		listDatas();
		controlDateNav();
	});
	
	// 后一天
	$('#later').click(function(){
		$('#usableDate').val($('#laterDate').text());
		listDatas();
		controlDateNav();
	});
	
}

function controlDateNav() {
	if (minDate == $('#usableDate').val().replace(/-0/, '-')) {
		$('#previous').hide();
	}
	if (maxDate != $('#usableDate').val().replace(/-0/, '-')) {
		$('#later').show();
	}
	if (maxDate == $('#usableDate').val().replace(/-0/, '-')) {
		$('#later').hide();
	}
	if (minDate != $('#usableDate').val().replace(/-0/, '-')) {
		$('#previous').show();
	}
}