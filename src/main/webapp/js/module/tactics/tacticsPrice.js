$(function() {
	$('.row tr:even').addClass('even');
	$('.row tr:odd').addClass('odd');

	initPeriod();
	
	/*
	 * 根据时间段生成价格设置列表
	 */
	$('#next').bind('click', next);

	$('#cancel').click(function(){
		$('.configure').fadeIn();
		$('#priceWorkspace,#operation,#notice').fadeOut();
	});

	// 统一设置所有价格
	$('#templatePrice').keyup(function(){
		$(':text[name=price]').val($(this).val());
	});
	
	$('#templateProtocolPrice').keyup(function(){
		$(':text[name=protocolPrice]').val($(this).val());
	});

	 // 表单提交设置
    var formSubmitOptions = {
        beforeSubmit: showRequest,
        success: showResponse,
        clearForm: false
    };
    
    $('#priceForm').submit(function() {
        $(this).ajaxSubmit(formSubmitOptions);
        return false;
    });

	/**********************价格的CRUD-开始**************************/

	// 修改
	$('a[name=update]').bind('click', updatePrice);

	// 保存价格
	$('a[name=save]').bind('click', savePrice);

	// 取消
	$('a[name=cancel]').click(function(){
		$('.showPrice,:text', $(this).parent()).toggle();
		$(this).hide().parent().find('a[name=update]').show().end().find('a[name=save]').hide();
	});

	// 删除
	$('a[name=delete]').bind('click', deletePrice);

	/**********************价格的CRUD-结束**************************/
});

/**
 * 初始化时段
 */
function initPeriod() {
	var openTimeArray = openTime.split(':');
	var closeTimeArray = closeTime.split(':');
	
	$.common.custom.createHourSelect('#fromTimeHour', openTimeArray[0]);
	$.common.custom.createMinuteSelect('#fromTimeMinute', openTimeArray[1]);
    
	$.common.custom.createHourSelect('#toTimeHour', closeTimeArray[0]);
	$.common.custom.createMinuteSelect('#toTimeMinute', closeTimeArray[1]);
}

/**
 * 删除价格
 */
function deletePrice() {
	if(!confirm('确认删除价格吗？')) {
		return;
	}

	var trDom = $(this).parents('tr');
	var data = trDom.metadata();
	$.get(ctx + '/tactics/tacticsPrice!delete.action', {
		id: data.pid
	}, function(rep) {
		trDom.remove();
	});
}

/**
 * 修改控制DOM
 */
function updatePrice() {
	$('.showPrice,:text', $(this).parent()).toggle();
	$(':text', $(this).parent()).val($('.showPrice', $(this).parent()).text()).focus();
	$(this).hide().parent().find('a[name=cancel],a[name=save]').show();

	// 回车提交修改
	$(':text', $(this).parent()).keydown(function(event){
		if (event.keyCode == 13) {
			$(this).parent().find('a[name=save]').trigger('click');
			return false;
		}
	});
}

/**
 * 保存新价格
 */
function savePrice() {
	var srcEle = this;
	var price = $(':text', $(this).parent()).val();
	if(price == '') {
		return;
	}

	var data = $(this).parents('tr').metadata();
	var pt = $(this).parent().metadata().priceType;
	$.post(ctx + '/tactics/tacticsPrice!updatePrice.action', {
		id: data.pid,
		price: price,
		priceType: pt
	}, function(req){
		if(req == 'false') {
			alert('保存失败');
		} else {
			$('.showPrice', $(srcEle).parent()).text($(':text', $(srcEle).parent()).val());
		}
		$('.showPrice,:text', $(srcEle).parent()).toggle();
		$(srcEle).hide().parent().find('a[name=update]').show().end().find('a[name=cancel]').hide();
	});
}

function checkForm() {
	var pass = false;
	$(':text[name=price],:text[name=protocolPrice]').each(function(){
		if ($(this).val() == '') {
			alert('请把价格信息填写完整！');
			return false;
		}
	});

	pass = true;
	return pass;
}

/**
 * 表单提交前
 *
 * @return {Boolean}
 */
function showRequest(formData, jqForm, options) {
	// 表单验证
	return checkForm();
}

/**
 * 表单响应处理
 */
function showResponse(responseText, statusText) {
	if (statusText == 'success' && responseText == 'true') {
		alert('价格设置成功');
		location.reload();
	}
}

function next() {
	if($('#lowestTime').val() == '') {
		alert('请输入[最低计时单位]');
		return;
	}

	$('.configure').fadeOut('fast', function(){

		var fromTime = $('#fromTimeHour').val() + ":" + $('#fromTimeMinute').val();
		var toTime = $('#toTimeHour').val() + ":" + $('#toTimeMinute').val();
		
		var timeStep = $('#lowestTime').val() * 1;
		var tempStartHour = fromTime.substring(0, 2) * 1;
		var tempEndHour = toTime.substring(0, 2) * 1;
		var totalHours = tempEndHour - tempStartHour;
		
		var ct = totalHours / timeStep;
		var tempPriceWorkspace = "";
		var tempTime = addTime(fromTime, 0);
		for(var i = 0; i < ct; i++) {
			var singleTime = tempTime + "~";
			tempPriceWorkspace += "<tr><input type='hidden' name='start' value='" + tempTime + "'/>";
			tempTime = addTime(tempTime, timeStep);
			
			var tmpHour = tempTime.substring(0, 2) * 1;
			if(tmpHour >= tempEndHour) {
				tempTime = toTime;
			}
			tempPriceWorkspace += "<input type='hidden' name='end' value='" + tempTime + "'/>";
			
			singleTime += tempTime;
			tempPriceWorkspace += "<td width='90' style='text-align:center'>" + singleTime 
				+ "</td><td><input type='text' id='price' name='price' size='6' maxlength='3'/></td>" 
				+ "<td><input type='text' name='protocolPrice' size='6' maxlength='3'/></td></tr>";
		}
		$('#periodTable').append(tempPriceWorkspace).parent().fadeIn('fast');
		
		// 显示按钮、注意事项
		$('#operation,#notice').fadeIn();
		
		$('#priceWorkspace tr').hover(function() {
			$(this).addClass('active');
		}, function() {
			$(this).removeClass('active');
		});
	});
}

function addTime(tempTime, step) {
	var result = tempTime.split(':')[0] * 1 + step;
	result += ':' + tempTime.split(':')[1];
	return result;
}
