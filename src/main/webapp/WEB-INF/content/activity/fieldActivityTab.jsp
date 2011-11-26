<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/global.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/common/meta.jsp" %>
	<title>场地活动索引</title>
	<link href="${ctx }/css/yui.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx }/css/style.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx }/js/common/plugins/jui/themes/${themeName }/jquery-ui.css" type="text/css" rel="stylesheet" />
	<link href="${ctx }/css/jqGrid/ui.jqgrid.css" type="text/css" rel="stylesheet"/>
	
	<style type="text/css">
	.ui-tabs .ui-tabs-panel {
		padding-left: 0px;
		padding-top: 0px;
	}
	</style>
	
	<script src="${ctx }/js/common/jquery.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/jquery.cookie.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/jui/jquery-ui.min.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/jquery.jsonp.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/jquery.cookie.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/jqGrid/i18n/grid.locale-cn.js" type="text/javascript"></script>
	<script type="text/javascript">
	    $.jgrid.no_legacy_api = true;
	    $.jgrid.useJSON = true;

	    var fieldType = '${param.fieldType }';
	    var pickedDate = '${param.pickedDate }';
	    var fieldId = '${param.fieldId }';
    </script>
	<script src="${ctx }/js/common/plugins/jqGrid/jquery.jqGrid.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="${ctx }/js/common/datepicker/WdatePicker.js"></script>
	<script src="${ctx }/js/common/common.js" type="text/javascript"></script>
	<script src="${ctx }/js/module/activity/fieldActivityTab.js" type="text/javascript"></script>
	
</head>

<body>
	<div id="tabs" class="loading-big">
		<ul></ul>
	</div>
	<div id="iframeTemplate" class="template">
    	<iframe src="" osrc="#osrc" style='width:100%;height:100%' frameborder='0' marginwidth='0' marginheight='0'></iframe>
    </div>
</body>
</html>