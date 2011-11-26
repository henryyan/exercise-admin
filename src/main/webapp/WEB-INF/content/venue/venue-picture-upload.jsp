<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/global.jsp" %>

<%@ page import="org.apache.struts2.ServletActionContext,java.io.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/common/meta.jsp" %>
	<title>场馆图片上传</title>
	<link href="${ctx }/js/common/plugins/uploadify/uploadify.css" type="text/css" rel="stylesheet"/>
	
	<script src="${ctx }/js/common/jquery.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/uploadify/swfobject.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/uploadify/jquery.uploadify.js" type="text/javascript"></script>
	<script type="text/javascript">
	var venueId = '${param.venueId}';
	</script>
	<script src="${ctx }/js/module/venue/venue-picture-upload.js" type="text/javascript"></script>
</head>

<body>
	<div id="uploadPicture" title="图片上传">
		<table>
			<tr>
				<td><input type="file" name="uploadImages" id="fileInput" /></td>
			</tr>
			<tr>
				<td>
					<input type="button" onclick="$('#fileInput').uploadifyUpload();" value="选择好了，上传吧"/>
					<input type="button" onclick="$('#fileInput').uploadifyClearQueue();" value="清空图片队列"/>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>