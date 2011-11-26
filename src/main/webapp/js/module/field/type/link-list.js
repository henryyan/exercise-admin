/**
 * 场地类型列表
 * 功能：场馆类型的列表以及CRUD操作
 * @author HenryYan 
 */
var $jqGrid;
$(function() {
	
	// 数据列表
	listDatas();
	
	// 开启、关闭
	$('.enable :radio').live('change', changeEnable);
	
	// 初始化对话框
	showAddFieldTypeDialog();
});

/**
 * 启用、禁用场地类型
 * @return
 */
function changeEnable() {
	var rowId = $("#list").jqGrid('getGridParam','selrow');
	var srcEle = this;
	$(this).ajaxStart(function(){
		$('#list').block();
	}).ajaxStop(function(){
		$('#list').unblock();
	});
	
}

/**
 * 场地类型列表
 * @return
 */
function listDatas() {
	$jqGrid = $("#list").jqGrid({
        url: 'link!list.action',
        datatype: "json",
        autowidth: true,
        colNames: ['场地名称'],
        colModel: [{
            name: 'fieldType.typeName',
            align: 'center'
        }],
        prmNames: $.common.plugin.jqGrid.prmNames,
        jsonReader: $.common.plugin.jqGrid.jsonReader,
        width: 770,
        height: 200,
        rowNum: 20,
        rowList: [20, 30, 40],
        pager: '#pager',
        viewrecords: true,
        sortname: 'venueInfo.id',
        sortorder: "desc",
        caption: "设置场馆拥有的场地类型（当前场馆：" + cvn + "）",
        rownumbers: true,
        gridComplete: function() {
			$('.enable').buttonset();
		}
    }).jqGrid('navGrid', '#pager', {
    	add: false,
    	edit: false,
		view: false,
        del: true,
        deltext: '删除',
        deltitle: '从当前场馆拥有的场地类型中删除',
        searchtext: '查询',
        refreshtext: '刷新'
    }, 
    { // edit options
    }, 
    { // add options
    }, 
    { // delete options
    	caption: '删除此场馆拥有的场地类型',
    	msg: '<h3>执行此操作后此场馆将<span style="color:red">不再支持</span><br/>此场地类型的预定操作，确认吗？</h3>',
    	url: 'link!delete.action'
    },
    { // search options
    	multipleSearch: true,
    	searchhidden: true
    },
    { // view options
    }).jqGrid('navButtonAdd', '#pager', {
    	caption : "添加",
		title : "为此场馆添加一个场地类型",
		buttonicon : "ui-icon-plus",
		onClickButton : function() {
    		$("#addFieldTypeDialog").dialog('open');
		}
    });
}

/**
 * 打开添加场地类型的对话框
 * @return
 */
function showAddFieldTypeDialog() {
	$("#addFieldTypeDialog").dialog({
		autoOpen: false,
		height: 250,
		width: 300,
		modal: true,
		open: function(event, ui) {
			$('#fieldTypeList').addClass('loading-big').html('');
			// 获取当前场馆开通的场地类型
			$.getJSON('link!findFieldTypes.action', function(jsonType){
				var nowTypeIds = new Array();
				for(var i in jsonType) {
					nowTypeIds[i] = jsonType[i].id;
				}
				
				// 显示列表，添加类型 
				$.getJSON('type!findEnableFieldType.action', {}, function(types){
					$('#fieldTypeList').removeClass('loading-big');
					var inNowTypes;
					for (var i in types) {
						inNowTypes = $.inArray(types[i].id, nowTypeIds) != -1;
						//alert(types[i].id + "=" + nowTypeIds + "=" + inNowTypes);
						var $_li = $('<li>', {
							html: "<input type='checkbox' id='" + types[i].id + "' name='fieldTypes' value='" + types[i].id + "'"
									+ (inNowTypes ? "disabled" : "") + "/><label for='" + types[i].id + "'>" + types[i].typeName + "</label>",
							title: inNowTypes ? '已有此类型' : '点击选择'
						}).appendTo('#fieldTypeList');
						if (inNowTypes) {
							$_li.addClass('has');
						}
					}
					
					// 鼠标移动特效
					$('#fieldTypeList li').hover(function(){
						var chk = $(this).find(':checkbox');
						if (chk.attr('disabled')) {
							$(this).animate({backgroundColor: '#aa0000'}, 200);
						} else {
							$(this).animate({backgroundColor: '#aa0000', color: '#fff'}, 200);
						}
					}, function() {
						var chk = $(this).find(':checkbox');
						if (chk.attr('disabled')) {
							$(this).animate({backgroundColor: '#fff'}, 200);
						} else {
							$(this).animate({backgroundColor: '#fff', color: '#000'}, 200);
						}
					});
					
				});
				
			});
			
		},
		buttons: {
			添加: function(){
				var chks = $('#fieldTypeList :checkbox:checked');
				var ids = new Array();
				$(chks).each(function(i, obj){
					ids[ids.length] = $(obj).val();
				});
				$.post('link!addLink.action', {
					ids: ids
				}, function(resp) {
					if (resp == 'true') {
						$('#list').trigger("reloadGrid");
						$("#addFieldTypeDialog").dialog('close');
					}
				});
			},
			取消: function() {
				$(this).dialog('close');
			}
		}
	});
};