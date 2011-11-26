<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Insert title here</title>
	<link rel="stylesheet" type="text/css" href="${ctx }/js/plugin/upload/uploadify.css"/>
	
	<script type="text/javascript" src="${ctx }/js/common/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/plugin/upload/swfobject.js"></script>
	<script type="text/javascript" src="${ctx }/js/plugin/upload/jquery.uploadify.js"></script>
	<script type="text/javascript">
		$(function(){
			$("#fileInput").uploadify({
				'uploader'       : '${ctx }/js/plugin/upload/uploadify.swf',
				'script'         : '/_scripts/uploadify.php',
				'cancelImg'      : '/_images/cancel.png',
				'folder'         : '/_uploads',
				'multi'          : true
			});
			$('#clear').click(function(){
				$("#fileInput").uploadifyClearQueue();
			});
		});
	</script>
</head>

<body>
	<input id="fileInput" type="file" />
	<button id="clear">取消</button>
</body>
</html>