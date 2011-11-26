<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/global.jsp" %>

<%@ include file="../field/type/includeFieldTypes.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/common/meta.jsp" %>
	<title>默认价格策略设置</title>
	<link href="${ctx }/css/yui.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx }/js/common/plugins/jui/themes/${themeName }/jquery-ui.css" type="text/css" rel="stylesheet" />
	<link href="${ctx }/css/style.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx }/css/jqGrid/ui.jqgrid.css" type="text/css" rel="stylesheet"/>
	
	<style type="text/css">
		body{ font: 8pt "Trebuchet MS", sans-serif; margin: 0px;}
		.divContent {height: 470px;}
		iframe {
			border-right: 0px; border-top: 0px; border-left: 0px; width: 96%; border-bottom: 0px; height:100%;
		}
	</style>
	
	<script src="${ctx }/js/common/jquery.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/jquery.metadata.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/jui/jquery-ui.min.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/common.js" type="text/javascript"></script>
	<script src="${ctx }/js/module/price/priceTab.js" type="text/javascript"></script>
</head>

<body>
	<div class="tabs" style="height: 580px">
		<ul>
			<c:forEach items="${fieldTypes}" var="type">
				<li><a href="#${type.typeId }"><span>${type.typeName }</span></a></li>
			</c:forEach>
		</ul>
		<c:forEach items="${fieldTypes}" var="type">
		<div id="${type.typeId }" class="divContent">
		<div class="tabs">
			<ul>
				<li><a href="#${type.typeId }-basic"><span>${type.typeName }->非周末价格</span></a></li>
				<li><a href="#${type.typeId }-weekend"><span>${type.typeName }->周末价格</span></a></li>
			</ul>
			<div id="${type.typeId }-basic" class="divContent">
				<iframe class="{src: '${type.typeId }-basic!list.action'}" frameborder="0">正在加载……</iframe>
			</div>
			<div id="${type.typeId }-weekend" class="divContent">
				<iframe class="{src: '${ctx }/price/${type.typeId }-weekend!list.action'}" frameborder="0">正在加载……</iframe>
			</div>
		</div>
		</div>
	</c:forEach>
	</div>
</body>
</html>