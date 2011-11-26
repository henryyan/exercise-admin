/**
 * 场地列表TAB页控制
 * 
 * @author HenryYan
 */

// 协议客户网上提前发布天数
var protocolVenueIssueDays;
$(function() {

	// 查询协议客户网上提前发布天数
	getIssueDays();
	
	// 加载场地列表
	loadFields();
	
});

// 查询协议客户网上提前发布天数
function getIssueDays() {
	$.common.system.getProp({
		params : {
			key : 'protocol-venue-issue-days'
		},
		callback : function(prop) {
			protocolVenueIssueDays = parseInt(prop.propValue);
		}
	});
}

/**
 * 加载场地列表
 * @return
 */
function loadFields() {
	$.getJSON('type/link!findFieldTypes.action', function(types) {
		$('#tabs').removeClass('loading-big');
		for ( var i in types) {
			var label = types[i].label;

			// 生成一个标签
			$('<li>', {
				html : "<a href='#" + label + "'>" + types[i].name + "</a>"
			}).data('type', label).appendTo('#tabs ul');

			// 生成场地列表DIV
			$('<div>', {
				id : types[i].label,
				html : $('#fieldDiv').html()
			}).addClass('jqGridListDiv').appendTo('#tabs')
					.find('table').attr('id', label + '-list')
					.end()
					.find('div').attr('id', label + '-pager')
					.end()
					.find('.createActivity').find('.Wdate').attr('id', label + 'LastDate').css({cursor: 'pointer'})
						.end().find(':button').attr('id', label + 'CreateActivity').button({icons: {primary: 'ui-icon-check'}})
						.end().find('.fieldType').text(types[i].name);

		}

		// 生成TAB，并且在选择的时候加载对于场地类型的JS
		var path = ctx + '/js/module/field/activity/';
		$('#tabs').tabs( {
			select : function(event, ui) {
				// TODO 面板中显示正在加载图标
				var fieldType = $(ui.tab).parent().data('type');
				$.loadScript( {
					src : path + fieldType + '-activity.js'
				});
			}
		});

		var currentFieldType = $('.ui-tabs-selected', '#tabs').data('type');
		$.loadScript( {
			src : path + currentFieldType + '-activity.js'
		});

	});
}