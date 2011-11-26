// 预览数据
var previewData = {
	venueName: '上海XXX体育馆',
	date: new Date().toLocaleDateString(),
	period: '10:00~11:00',
	code: 'DW3E',
	telphone: '021-88899937'
};

$(function() {
	
	$('.preview').click(function(){
		var smsContent = $(this).parents('tr').find('td:eq(1) pre').html();
		for ( var d in previewData) {
			var reg = RegExp('#' + d, 'g');
			smsContent = smsContent.replace(reg, previewData[d]);
		}
		alert(smsContent);
	});
	
});
