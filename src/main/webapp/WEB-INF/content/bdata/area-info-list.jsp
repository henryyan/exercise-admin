<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/global.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/common/meta.jsp" %>
    <title>地区信息管理 </title>
    <link href="${ctx}/css/yui.css" type="text/css" rel="stylesheet"/>
    <style type="text/css">
    #areaInfoTree {text-align: left; width: 200px; height:550px; float: left; overflow: scroll;}
    #vakata-contextmenu {text-align: left;}
    td {vertical-align: top;}
    td, th {border: none;}
    </style>
    
	<script src="${ctx }/js/common/jquery.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/jstree/jquery.jstree.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/tools/jquery.cookie.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/tools/jquery.hotkeys.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/common.js" type="text/javascript"></script>
	<script src="${ctx }/js/module/bdata/area-info-list.js" type="text/javascript"></script>
</head>
<body style="overflow: hidden;">
	<table width="100%" style="padding: 0px;margin: 0px;" border="0">
		<tr>
			<td width="200px" style="padding: 0px;"><b>说明</b>：点击地区名称打开右侧的列表</td>
			<td style="padding: 0px;"><b>说明</b>：添加和列表同级的地区请点击列表左下角的 + ，添加下级地区请点击[添加下级地区]按钮，操作完成后会自动更新左边的地区树。</td>
		</tr>
		<tr>
			<td>
				<div id="areaInfoTree"></div>
			</td>
			<td style="vertical-align: top; padding: 0;margin: 0;">
				<iframe src="${ctx }/bdata/area-info-manage-list.action?parentId=0" frameborder="0" width="100%"></iframe>
			</td>
		</tr>
	</table>
</body>
</html>