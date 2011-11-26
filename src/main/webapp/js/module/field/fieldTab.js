/**
 * 场地列表TAB页控制
 * 
 * @author HenryYan
 */
var protocolDays;
var normalDays;
$(function() {
	
	// 获取协议和非协议用户的提前发布天数
	getDays();
	
	$.getJSON('type/link!findFieldTypes.action', function(types) {
		$('#tabs').removeClass('loading-big');
		for ( var i in types) {
			var label = types[i].label;

			// 生成一个标签
			$('<li>', {
				html : "<a href='#" + label + "'>" + types[i].name + "</a>"
			}).data('type', label).appendTo('#tabs ul');

			// 生成对应的DIV
			$('<div>', {
				id : types[i].label,
				html : $('#fieldDiv').html()
			}).addClass('jqGridListDiv').appendTo('#tabs').find('table').attr(
					'id', label + '-list').end().find('div').attr('id',
					label + '-pager');

		}
		
		if (protocol == true) {
			// 添加显示所有场地的“提前发布天数”小于默认值的场地
			$('<li>', {
				html : "<a href='#mixfield'>不符合标准的场地</a>"
			}).data('type', 'mixfield').appendTo('#tabs ul');
			
			$('<div>', {
				id : 'mixfield',
				html : $('#fieldDiv').html()
			}).addClass('jqGridListDiv').appendTo('#tabs').find('table').attr(
					'id', 'mixfield-list').end().find('div').attr('id', 'mixfield-pager');
		}

		// 生成TAB，并且在选择的时候加载对于场地类型的JS
		var path = ctx + '/js/module/field/';
		$('#tabs').tabs( {
			select : function(event, ui) {
				// TODO 面板中显示正在加载图标
				var fieldType = $(ui.tab).parent().data('type');
				$.loadScript({
					src : path + fieldType + '.js'
				});
			}
		});
		
		var currentFieldType = $('.ui-tabs-selected', '#tabs').data('type');
		$.loadScript({
			src : path + currentFieldType + '.js'
		});
		
	});
	
	
});

function getDays() {
	$.common.system.getProp({
		params : {
			key : ['protocol-venue-issue-days', 'normal-venue-issue-days']
		},
		callback: function(props) {
			if (props.length > 1) {
				for ( var i in props ) {
					innerSetProp(props[i]);
				}
			} else {
				innerSetProp(props[i]);
			}
		}
	});
	
	function innerSetProp(prop) {
		if (prop.propKey == 'protocol-venue-issue-days') {
			protocolDays = prop.propValue;
			$('#issueDayRule .protocolDays').text(protocolDays);
		} else if (prop.propKey == 'normal-venue-issue-days') {
			normalDays = prop.propValue;
			$('.normalDays').text(normalDays);
		}
		
	}
}