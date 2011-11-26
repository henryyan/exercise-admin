/**
 * 地区信息维护Javascript
 * @author HenryYan
 */

var $jstree = null;
$(function(){
	var iframeHeight = parent.document.body.clientHeight;
	$('iframe').height(iframeHeight - 150);
    $jstree = $("#areaInfoTree").jstree({
        json_data: {
            ajax: {
                url: ctx + "/bdata/area-info-tree.action",
                data: function(n){
                    return {
                        parentId: n.attr ? n.attr("id") : 0
                    };
                }
            }
        },
        themes: {
            theme: 'apple'
        },
        plugins: ["themes", "json_data", "ui", "crrm", "search", "cookies", "hotkeys"]
    });
    
    // 绑定事件 -- 点击名称展开节点
    //$.common.plugin.jstree.clickNameToUnfold('#areaInfoTree');
    
	// 单击事件，打开列表
	bindClickJstree();
	
    autoSize();
	
});

/**
 * 刷新树节点
 */
function refreshTree(areaId) {
	$('#areaInfoTree').jstree('refresh', areaId);
}

/**
 * 自动根据窗口大小调整
 */
function autoSize() {
	window.onresize = function() {
		var iframeHeight = parent.document.body.clientHeight;
		$('#areaInfoTree,iframe').height(iframeHeight - 150);
	};
}

/**
 * 单击事件，打开列表
 */
function bindClickJstree() {
	$jstree.bind('click.jstree', function(event){
		var $city = $(event.target);
		var eventNodeName = event.target.nodeName;
		if (eventNodeName == 'INS') {
			return;
		} else {
			if (eventNodeName == 'A') {
				if (!$city.attr('leaf')) {
					var params = {
						areaId : $city.parent().attr('id'),
						areaName : $city.text(),
						level : $city.parent().attr('level')
					};
					window.frames[0].refreshData(params);
				}
			}
		}
	});
}
