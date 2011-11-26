<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta http-equiv="Cache-Control" content="no-store"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Expires" content="0"/>
    <title>Insert title here</title>
	<script src="${ctx }/js/common/jquery.min.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(function(){
			$("<option/>", {
				text : '加载中…'
			}).appendTo('#example1');
			$.getJSON('${ctx}/bmc/data-dict!findTypes.action', {
				type: 'iu'
			}, function(datas) {
				$('#example1 option:first').remove();
				for (i in datas) {
					$("<option/>", {
						text : datas[i].libraryvalue,
						value : datas[i].librarycode
					}).appendTo('#example1');
				}
				$('#example1').attr('title', '请选择：' + datas[0].libraryname);
			});

			$('#example2').load('${ctx}/bmc/data-dict!findTypeForSelect.action?type=cs', function(){
				$('select', this).attr('title', '我是title');
			});
		});
	</script>
</head>
<body>
	<fieldset>
		<legend>动态加载原始数据</legend>
		<select id="example1"></select>
	<hr/>
	</fieldset>
	
	<fieldset>
		<legend>动态生成</legend>
		<div id="example2"></div>
		<pre>
		$('#example2').load('${ctx}/bmc/data-dict!findTypeForSelect.action?type=cs');
		</pre>
	</fieldset>
</body>
</html>