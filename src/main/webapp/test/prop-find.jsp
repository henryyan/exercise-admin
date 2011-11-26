<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/global.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<script src="${ctx }/js/common/jquery.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/common.js" type="text/javascript"></script>
	<title>Insert title here</title>
	<script type="text/javascript">
	$(function(){

		$(':button').click(function(){
			$.common.system.getProp({
				params : {
					key : ['protocol-venue-issue-days', 'normal-venue-issue-days']
				},
				callback: function(props) {
					$('#val').val('');
					if (props.length > 1) {
						for ( var i in props ) {
							$('#val').val('id=' + props[i].id + '，key=' + props[i].propKey + '，value=' + props[i].propValue + "\n" + $('#val').val());
						}
					} else {
						$('#val').val('id=' + props.id + '，key=' + props.propKey + '，value=' + props.propValue);
					}
				}
			});
		});
		
	});
	</script>
</head>

<body>
	<h2>系统属性ajax查询测试</h2>
	键：<input type="text" size="50" id="key"/>
	<button type="button">查询属性</button><hr/>
	值：<textarea id="val" rows="3" cols="100"></textarea>
</body>
</html>