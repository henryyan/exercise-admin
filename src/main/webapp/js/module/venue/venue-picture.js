$(function() {
	
	// 上传图片按钮
	$('#uploadPicture').click(function(){
		location.href = 'venue-picture-upload.action?venueId=' + venueId;
	});
	
	// 效果提示
	$('.noneText,.picNameCt,.picRemarkCt').hover(function() {
		$(this).addClass('activeNoneText');
	}, function(){
		$(this).removeClass('activeNoneText');
	});
	
	// 显示编辑框
	$('.nshow').click(function(event){
		var $td = $(this).parents('td');
		var pid = $td.attr('id');
		$td.find('.nshow').hide();
		$td.find('.nedit').show().corner().html($('#picNameTemplate').html());
		$td.find('.picNameInput').val($td.find('.picNameCt').text()).focus().data('pid', pid);
	});
	
	// 保存图片名称
	$('.picNameOk').live('click', function(){
		var _this = this;
		var input = $(this).parent().parent().find('.picNameInput');
		$.post(ctx + '/venue/venue-picture!save.action', {
			id : input.data('pid'),
			pictureName : input.val()
		}, function(resp) {
			if (resp == 'success') {
				var $td = $(_this).parents('td');
				$td.find('.nedit').hide();
				if ($td.find('.nshow .noneText')) {
					$td.find('.nshow .noneText').remove();
					$('<span/>').addClass('picNameCt').insertBefore($td.find('.ui-icon-pencil'));
				}
				$td.find('.nshow').show().find('.picNameCt').text(input.val());
			} else {
				alert(resp);
			}
		});
	});
	
	// 取消编辑图片名称
	$('.picNameCancel').live('click', function(){
		var $td = $(this).parents('td');
		$td.find('.nshow').show();
		$td.find('.nedit').hide();
	});
	
	// 显示编辑框
	$('.rshow').click(function(event){
		var $td = $(this).parents('td');
		var pid = $td.attr('id');
		$td.find('.rshow').hide();
		$td.find('.redit').show().corner().html($('#picRemarkTemplate').html());
		$td.find('.picRemarkInput').val($td.find('.picRemarkCt').text()).focus().data('pid', pid);
	});
	
	// 保存图片名称
	$('.picRemarkOk').live('click', function(){
		var _this = this;
		var input = $(this).parent().parent().find('.picRemarkInput');
		$.post(ctx + '/venue/venue-picture!save.action', {
			id : input.data('pid'),
			pictureRemark : input.val()
		}, function(resp) {
			if (resp == 'success') {
				var $td = $(_this).parents('td');
				$td.find('.redit').hide();
				if ($td.find('.rshow .noneText')) {
					$td.find('.rshow .noneText').remove();
					$('<span/>').addClass('picRemarkCt').insertBefore($td.find('.ui-icon-pencil'));
				}
				$td.find('.rshow').show().find('.picRemarkCt').text(input.val());
			} else {
				alert(resp);
			}
		});
	});
	
	// 取消编辑图片描述
	$('.picRemarkCancel').live('click', function(){
		var $td = $(this).parents('td');
		$td.find('.rshow').show();
		$td.find('.redit').hide();
	});
	
	$('.deletePic').click(deletePic);
	$('.setMajor').click(setMajor);
	
});

/**
 * 删除图片
 */
function deletePic() {
	if (!confirm('确定删除此图片吗？')) {
		return;
	}
	if ($(this).parent().find('.majorPicture').length > 0) {
		if (!confirm('这是一张主图片，还要删除吗？')) {
			return;
		}
	}
	var _this = this;
	$.post(ctx + '/venue/venue-picture!delete.action', {
		id : $(this).parent().attr('id')
	}, function(resp) {
		if (resp == 'success') {
			$(_this).parents('tr').remove();
			parent.refreshGrid();
		} else {
			alert(resp);
		}
	});
}

/**
 * 设置为场馆主图片
 */
function setMajor() {
	var $_this = this;
	$.post(ctx + '/venue/venue-picture!setMajorPicture.action', {
		venueId : venueId,
		pictureRealName : $($_this).attr('prn')
	}, function(resp) {
		if (resp == 'success') {
			$('.majorPicDiv').show();
			$('.majorPicture').remove();
			$('<div class="majorPicture">主图片</div>').insertAfter($($_this).parent());
			$($_this).parent().hide();
			parent.refreshGrid();
		} else {
			alert(resp);
		}
	});
}
