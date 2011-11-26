/**
 * 首页函数
 * 下面用到的cvi和cvn分别代表当前管理的场馆ID和场馆名称，在global.jsp中定义
 * @author HenryYan
 */
var lastClick = null;
var manualChangeVenue = false;
var selectVenueCallback;
$(function() {
	
	$('li.security').click(function(event){
		var $li = this;
		if (cvi == '') {
			selectVenue();
			event.stopPropagation();
			return false;
		}
	});

	// 菜单的展开、收缩
	addMenuOptions();
	
	// 菜单切换
	changeMenu();

	// 显示切换场地界面
	$('#showAutoDiv').bind('click', showAutoComplete);

	// 自动完成搜索场馆
	autoComplete();
	
	// 加载场地管理菜单
	if (cvi != '') {
		loadFieldTypeForLeftMenu();
	}
});

/**
 * 获取本场馆已启用的场地类型并设置左边的菜单
 */
function loadFieldTypeForLeftMenu() {
	// 切换时清空之前一个场馆的场地类型
	$('#bookFieldType,#localeFieldType').html('');
	
	// 获取本场馆开启的场地类型
	$.getJSON(ctx + '/field/type/link!findFieldTypes.action', function(types){
		$('#bookFieldType').html('');
		for (var i in types) {
			// 预订菜单
			var bookUrl = ctx + "/activity/fieldActivityCalendar.action?fieldType=" + types[i].label;
			var localeUrl = ctx + "/activity/fieldActivityTab.action?fieldType=" + types[i].label;
			$('<li/>', {
				html : function() {
					return $('<a/>', {
						href : bookUrl,
						target : 'main',
						text : types[i].name
					}).addClass('security');
				}
			}).addClass('sc-item').appendTo('#bookFieldType');
			
			$('<li/>', {
				html : function() {
					return $('<a/>', {
						href : localeUrl,
						target : 'main',
						text : types[i].name
					}).addClass('security');
				}
			}).addClass('sc-item').appendTo('#localeFieldType');
		}
	});
}


/**
 * 注册自动完成功能
 * 
 * @return
 */
function autoComplete() {
	$.widget("custom.catcomplete", $.ui.autocomplete, {
		_renderMenu : function(ul, items) {
			var self = this, currentCategory = "";
			$.each(items, function(index, item) {
				if (item.category != currentCategory) {
					ul.append("<li class='ui-autocomplete-category'>"
							+ item.category + "</li>");
					currentCategory = item.category;
				}
				self._renderItem(ul, item);
			});
		}
	});

	$('#autoSearchVenue,#autoSearchSelectVenue').catcomplete( {
		delay : 0,
		source : ctx + '/venue/venue!autoSearch.action',
		select : function(event, ui) {
			if ($.isFunction(selectVenueCallback)) {
				selectVenueCallback();
			}
			$.nyroModalRemove();
			changeVenue(ui.item);
		}
	});
}

/**
 * 如果还未选择场馆自动打开选择界面
 * 
 * @return
 */
function selectVenue() {
	$('#autoSearchSelectVenue').val('');
	$.nyroModalManual( {
		title : '选择您要管理场馆',
		url : '#selectVenue',
		minHeight : 20,
		modal : true,
		endShowContent : function() {
			$('#autoSearchSelectVenue').focus();
		}
	});
}

/**
 * 打开切换场馆界面
 * 
 * @return
 */
function showAutoComplete() {
	manualChangeVenue = true;
	$('#autoSearchVenue').val('');
	$.nyroModalManual( {
		title : '切换当前管理的场馆',
		url : '#autoComplete',
		minHeight : 20,
		endShowContent : function() {
			$('#autoSearchVenue').focus();
		}
	});
}

/**
 * 切换场馆
 * 
 * @return
 */
function changeVenue(item) {
	cvi = item.id;
	if (!cvi || cvi == '') {
		return;
	}
	$.getJSON(ctx + '/venue/venue!changeVenue.action', {
		id : cvi
	}, function(resp) {
		if (resp && resp.id) {
			
			// 更换场地类型菜单
			loadFieldTypeForLeftMenu();
			
			// 切换显示/隐藏[协议服务费]
			if (!resp.isProtocol) {
				$('#protocolFee').hide();
			} else {
				$('#protocolFee').show();
			}
			
			$('#currentVenue').html(item.value);
			cvi = item.id;
			cvn = item.value;
			
			$('#main').attr('src', $('#main').attr('src'));
		}
	});
}

function addMenuOptions() {
	// 在右边的iframe中打开连接
	$('.sc-item a').live('click', function(event){
		$('.current-menu').removeClass('current-menu');
		$(this).addClass('current-menu');
		$('#main').attr('src', $(this).attr('href'));
		event.stopPropagation();
		return false;
	});
	
	// 需要选择场馆的链接
	$('.sc-item a.security').live('click', function(event){
		if (cvn == '') {
			lastClick = this;
			selectVenue();
			event.stopPropagation();
			return false;
		}
	});
	
	function _inner_deal(menuType) {
		$('.sc').hide();
		$('.' + menuType).show();
		// 全部折叠场馆管理
	    $(".sc-title").each(function(i) {
	        if (i > 0) {
	            $(this).next().slideUp("normal");
	        }
	    });
	}
	
	// 切换大模块
	$('.change-left-menu').click(function(){
		var menuType = $(this).attr('name');
		if (menuType == 'venue' && !cvn) {
			selectVenueCallback = function(){
				_inner_deal(menuType);
			};
			selectVenue();
			return;
		}
		_inner_deal(menuType);
	});
	
	// 展开子菜单
    $(".sc-title").click(function(){
		if ($(this).next().hasClass('ct')) {
			return;
		}
		$('.venue .ct').slideUp('normal').removeClass('ct');
        $(this).next().slideDown('normal').addClass('ct');
	});

	
}

/**
 * 点击大菜单切换左边的菜单
 */
function changeMenu() {
	$('#topmenu a.menu').click(function(){
		$('ul.mc').hide();
		$('ul.' + $(this).attr('id')).show();
	});
}
