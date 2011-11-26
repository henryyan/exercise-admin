// 预览数据
var previewData = {
	venueName: '上海XXX体育馆',
	date: new Date().toLocaleDateString(),
	period: '10:00~11:00',
	code: 'DW3E',
	telphone: '021-88899937'
};

$(document).ready(function() {
	// 聚焦第一个输入框
	$("#propName").focus();
	// 为inputForm注册validate函数
	$("#inputForm").validate({
		rules : {
			propName : {
				required : true,
				remote : "sms!checkPropName.action?oldPropName="
						+ encodeURIComponent('${propName}')
			},
			propValue: {
				required : true
			}
		},
		messages : {
			propName : {
				remote : "此短信模板已存在"
			}
		},/* 设置验证触发事件 */
		errorPlacement : function(error, element) {
			var type = element.get(0).type;
			if (type == 'textarea') {
				element.attr('title', error.html());
			} else {
				error.appendTo(element.parent());
			}
		}
	});
	
	// 修改时禁用模板名称框
	if ($('#id').val() != '' && $('#id').val != '0') {
		$('#propName').attr('readonly', 'readonly').rules('remove', 'remote');
	}

	// 标签圆角、绑定事件
	$('#properties a').corner().bind('click', insertVar);
	
	// 预览
	$('#preview').click(preview);
	
	// 取消预览
	$('#closePreive').click(function() {
		$('#sms-preview').hide(500);
	});
});

/**
 * 设置插入内容
 * @return
 */
function insertVar() {
	$('#propValue').cursorInsert( {
		content : '#' + $(this).metadata().pn
	});
}

/**
 * 预览短信模板
 * @return
 */
function preview() {
	var smsContent = $('#propValue').val();
	for ( var d in previewData) {
		var reg = RegExp('#' + d, 'g');
		smsContent = smsContent.replace(reg, previewData[d]);
	}
	
	$('#preview-content').html(smsContent);
	
	// 绝对定位预览位置
	var $propOffset = $('#preview').parent().offset();
	var top = $propOffset.top + $('#preview').parent().height() + 20;
	$('#sms-preview').corner().fadeIn().css({left: $propOffset.left, top: top });
}