/**
 * 会员卡类型JS
 * 
 * @author HenryYan
 */

$(function() {

	$('.row tr:even').addClass('even');
	$('.row tr:odd').addClass('odd');
	
	// 验证表单
	validateForm();
	$('#discountType').bind('change', changeDiscountType);
	
	// 类型描述提示
	$('.row td[title]').tipTip();
	
	// 新增会员卡类型
	$('#addType').click(function(){
		$.nyroModalManual({
	        title: '添加会员卡类型',
	        url: '#formDiv'
	    });
	});
	
	// 删除和修改
	editOperation();
	
	// 提交时设置折扣时段
	$(':submit').click(function() {
        var s = "";
        $(':checkbox').each(function() {
            s += $(this).attr('checked') ? "1" : "0";
        });
        $('#discountTime').val(s);
    });
    
    $('#periodMonth').bind('keyup', function(){
    	$('#showYear').text(parseInt(parseInt($(this).val()) / 12));
    	$('#showMonth').text(parseInt(parseInt($(this).val()) % 12));
    });
	
});

/**
 * 修改和删除
 * @return
 */
function editOperation() {
	$('.operate a').click(function(event) {
        var optionTag = $(this).text();
        var cardTypeId = $(this).parent().metadata().idv;
        if (optionTag == '修改') {
        	$.nyroModalManual({
    	        title: '修改会员卡类型',
    	        url: '#formDiv'
    	    });
            $.getJSON("cardType!input.action", {
                id: cardTypeId
            }, function(type) {
                for (key in type) {
                    if (key == 'discountTime') {
                        var val = type[key];
                        if (/1[0-1]/i.test(val)) {
                            $('#common').attr('checked', 'checked');
                        } else {
                        	$('#common').removeAttr("checked");
                        }
                        if (/[0-1]1/i.test(val)) {
                            $('#weekday').attr('checked', 'checked');
                        } else {
                        	$('#weekday').removeAttr("checked");
                        }
                    } else {
                        $('#' + key).val(type[key]);
                    }
                }
                
                // 根据折扣类型相应设置
                if (type.discountType == '1') {
                    $('#discountType1').show();
                    $('#discountType2').hide();
                } else if (type.discountType == '2') {
                    $('#discountType1').hide();
                    $('#discountType2').show();
                    $('#moneyAmountSpan').text(type.moneyAmount);
                    var session = calcDiscount(type.moneyAmount, type.discountPrice);
                    $('#discountSession').val(session);
                    
                    // 绑定事件
                    $('#moneyAmount').unbind('keyup').bind('keyup', changeMoney);
                    $('#discountPrice').unbind('keyup').bind('keyup', changePrice);
                }
            });
        } else if (optionTag == '删除') {
        
            if (!confirm('确认删除该类型吗？')) {
                return;
            }
            
            var trDom = $(this).parent().parent();
            $.get('cardType!delete.action', {
                id: cardTypeId
            }, function(rep) {
                if (rep == 'true') {
                    trDom.remove();
                    
                    // 重置表单
                    if ($('#id').val() == cardTypeId) {
                        form.clearForm('cardTypeForm');
                    }
                } else {
                    alert('删除失败！');
                }
            });
        }
        
    });
}

/**
 * 更换折扣方式
 */
function changeDiscountType() {
    var type = $('#discountType').val();
    var _this = this;
    if (type == '1') {
        // 清空折扣单价
        $('#discountPrice').val('');
        $('#discountType1').show();
        $('#discountType2').hide();
        
        // 验证
        $('#discountPrice').rules("remove");
		$('#discountRate').rules("add", {
			required: true,
			number: true,
			range: [0, 100]
		});
		if ($(_this).attr('id') == 'discountType') {
			$('#cardTypeForm').valid();
		}
    } else if (type == '2') {
        // 清空折扣率
        $('#discountRate').val('');
        
        $('#discountType1').hide();
        $('#discountType2').show();
        
        $('#moneyAmountSpan').text($('#moneyAmount').val());
        $('#moneyAmount').unbind('keyup').bind('keyup', changeMoney);
        $('#discountPrice').unbind('keyup').bind('keyup', changePrice);
        
        // 验证
        $('#discountRate').rules("remove");
		$('#discountPrice').rules("add", {
			required: true,
			number: true
		});
		if ($(_this).attr('id') == 'discountType') {
			$('#cardTypeForm').valid();
		}
    }
}

/**
 * 改变卡内余额
 */
function changeMoney() {
    $('#moneyAmountSpan').text($(this).val());
}

/**
 * 改变单价计算可用场次
 */
function changePrice() {
    var tempAmount = $('#moneyAmount').val();
    var tempPrice = $(this).val();
    if (!tempAmount || !tempPrice) {
        $('#discountSession').val('');
        return;
    }
    var moneyAmount = parseFloat(tempAmount);
    var discountPrice = parseFloat(tempPrice);
    var session = calcDiscount(moneyAmount, discountPrice);
    $('#discountSession').val(session);
}

/**
 * 计算场次
 * @param {Integer} moneyAmount		总额
 * @param {Integer} discountPrice	单价
 * @return 可以使用场次
 */
function calcDiscount(moneyAmount, discountPrice) {
    return parseInt(moneyAmount / discountPrice);
}

/**
 * 验证表单完整性
 * @return
 */
function validateForm() {
	$("#cardTypeForm").validate({
        rules: {
			typeName: {
				required: true,
				rangelength: [1, 50]
			},
			moneyAmount: {
				required: true,
				number: true,
				range: [0, 100000]
			},
			paymentCommision: {
				required: true,
				number: true,
				range: [0, 10000]
			},
			periodMonth: {
				required: true
			},
			discountType: {
				required: true
			},
			discountRate: {
				number: true,
				range: [0, 100]
			},
			discountPrice: {
				number: true,
				range: [1, 999]
			},
			vdiscountTime: {
				required: true
			}
		},
        errorPlacement: $.common.plugin.validator.error,
        success: $.common.plugin.validator.success
    });
	
	// 添加折扣验证
	changeDiscountType();
}
