<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/global.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/common/meta.jsp" %>
	<title>场地设置</title>
	<link href="${ctx }/css/yui.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx }/css/style.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx }/js/common/plugins/jui/themes/${themeName }/jquery-ui.css" type="text/css" rel="stylesheet" />
	<link href="${ctx }/css/jqGrid/ui.jqgrid.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx }/js/common/plugins/validate/jquery.validate.css" type="text/css" rel="stylesheet"/>
	
	<style type="text/css">
	.jqGridListDiv {margin: 0; padding: 0;}
	.days {font-weight: bold; padding: 0px 3px 0px 3px; color: red;}
	</style>
	
	<script src="${ctx }/js/common/jquery.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/jquery.cookie.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/jui/jquery-ui.min.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/validate/jquery.validate.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/validate/messages_cn.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/jqGrid/i18n/grid.locale-cn.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/jqGrid/jquery.jqGrid.min.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/common.js" type="text/javascript"></script>
	<script type="text/javascript">
		// 是否为协议用户
		var protocol = ${venue.isProtocol};
	</script>
	<script src="${ctx }/js/module/field/fieldTab.js" type="text/javascript"></script>
</head>

<body>
	<div id="tabs" class="loading-big">
		<ul></ul>
	</div>
	<div id="fieldDiv" class="template">
		<table></table>
		<div></div>
	</div>
	<div id="issueDayRule" class="template">
	[提前发布天数]默认设置规则：
		<ul>
			<li><b>协议客户</b>默认提前发布天数为<span class="days protocolDays"></span>天</li>
			<li><b>普通客户</b>默认提前发布天数为<span class="days normalDays"></span>天</li>
		</ul>
	</div>
</body>
</html>