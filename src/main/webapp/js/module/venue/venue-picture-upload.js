$(function(){
	$('#fileInput').uploadify( {
		// 以下参数均是可选
		'script' :  ctx + '/venue/venue-picture!upload.action?venueId=' + venueId,
		'uploader' : ctx + '/js/common/plugins/uploadify/uploadify.swf',
		'cancelImg' : ctx + '/js/common/plugins/uploadify/cancel.png', // 指定取消上传的图片，默认‘cancel.png’
		'multi' : true, // 是否允许同时上传多文件，默认false
		'fileDesc': 'jpg,gif,png', // 出现在上传对话框中的文件类型描述
		'fileExt': '*.jpg', // 控制可上传文件的扩展名，启用本项时需同时声明fileDesc
		'sizeLimit': 15 * 1024 * 1024, // 控制上传文件的大小，单位byte=B
		'queueSizeLimit': 5,  // 多文件上传时，同时上传文件数目限制
		'buttonText' : '图片上传',
		'fileDataName':'uploadImages',
		onAllComplete : function() {
			parent.uploadComplete();
		}
	});
});
