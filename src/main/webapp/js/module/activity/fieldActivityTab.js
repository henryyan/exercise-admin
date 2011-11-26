
/**
 * tab页面切换功能，为了达到延迟加载iframe页面的效果，先设置非当前标签的iframe的osrc属性，然后绑定标签的单击事件
 */
$(function() {
	var bodyHeight = $(document).height() - 48;
	$.common.system.getExerciseUrl({
		callback : function(exerciseUrl) {
			$.jsonp( {
				url : exerciseUrl + "/JsonpServlet?callback=?",
				data : {
					action : 'getEnableFieldNameId',
					fieldType : fieldType,
					venueId : cvi
				},
				success : function(result) {
					var listDom = "";
			        var iframeStr = "";
			        var selectedTab = 0;
			        var count = 0;
			        var src = "";
			        
			        for (k in result) {
			            listDom += "<li><a href='#field" + k + "'>" + result[k] + "</a></li> ";
			            src = "fieldActivityList.action?fieldId=" + k
			            + "&fieldType=" + fieldType;
			            
			            iframeStr += "<div id='field" + k + "' name='fieldDiv' style='height:" + bodyHeight + "px'>" +
			            $('#iframeTemplate').html().replace('#osrc', src) +"</div>";
			            if (selectedTab == 0) {
			                if (k == fieldId) {
			                    selectedTab = count;
			                }
			            }
			            count++;
			        }
			        $('#tabs > ul').append(listDom).parent().append(iframeStr).tabs({
			            selected: selectedTab
			        });
			        
			     // 延迟加载iframe页面
					var $iframe;

					// 1、默认显示的标签
					if(fieldId == "") {
						$iframe = $('div[name=fieldDiv]:first iframe');
			            $iframe.attr('src', $iframe.attr('osrc'));
					} else {
						$iframe = $('#field' + fieldId + ' iframe');
			        	$iframe.attr('src', $iframe.attr('osrc'));
					}

					// 单击后加载iframe
					$('#tabs li A').one('click', function() {
			            var $iframe = $($(this).attr('href') + ' iframe');
						if(!$iframe.attr('src')) {
				            $iframe.attr('src', $iframe.attr('osrc'));
						}
			        });
			        
				},
				error : function(d, msg) {
					alert("error");
				}
			});
		}
	});
	
});
