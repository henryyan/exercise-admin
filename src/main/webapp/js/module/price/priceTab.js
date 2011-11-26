/**
 * 价格列表TAB页控制
 * 
 * @author HenryYan
 */

$(function() {
	$('.tabs').tabs( {
		select : function(event, ui) {
			var src = $('iframe', ui.panel).metadata().src;
			// TODO 解决点击网球周末价格时不加载页面问题，暂时每次点击都重新载入页面
			//if (!$('iframe', ui.panel).attr('src')) {
				$('iframe', ui.panel).attr('src', src);
			//}
		}
	});
	
	var currentFieldType = $('.ui-tabs-selected', '.tabs:eq(1)');
	var iframeDiv = $(currentFieldType).find('a').attr('href');
	$('iframe', iframeDiv).attr('src', $('iframe', iframeDiv).metadata().src);
});