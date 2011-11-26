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
	.fieldType {font-weight: bold;padding-left: 3px;padding-right: 3px;}
	</style>
	
	<script src="${ctx }/js/common/jquery.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/jquery.jsonp.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/jquery.cookie.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/jui/jquery-ui.min.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/jquery.blockUI.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/validate/jquery.validate.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/validate/messages_cn.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/jqGrid/i18n/grid.locale-cn.js" type="text/javascript"></script>
	<script type="text/javascript">
	    $.jgrid.no_legacy_api = true;
	    $.jgrid.useJSON = true;
    </script>
	<script src="${ctx }/js/common/plugins/jqGrid/jquery.jqGrid.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="${ctx }/js/common/datepicker/WdatePicker.js"></script>
	<script src="${ctx }/js/common/common.js" type="text/javascript"></script>
	<script src="${ctx }/js/module/field/activity/createActivityTab.js" type="text/javascript"></script>
	<script src="${ctx }/js/module/field/activity/common.activity.js" type="text/javascript"></script>
	
</head>

<body>
	<div id="tabs" class="loading-big">
		<ul></ul>
	</div>
	<div id="fieldDiv" class="template">
		<span class="createActivity">
			生成<span class="fieldType"></span>活动至：<input type="text" class="Wdate" size="15" title="点击选择生成活动的最后日期"/>
			<button type="button" title="生成活动到已选择的日期">生成活动</button>
		</span>
		<table></table>
		<div></div>
	</div>
</body>
</html>