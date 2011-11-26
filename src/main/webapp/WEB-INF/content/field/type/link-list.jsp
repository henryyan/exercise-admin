<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/global.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/common/meta.jsp" %>
	<title>设置场馆拥有的场地类型</title>
	<link href="${ctx }/css/yui.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx }/css/style.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx }/js/common/plugins/jui/themes/${themeName }/jquery-ui.css" type="text/css" rel="stylesheet" />
	<link href="${ctx }/css/jqGrid/ui.jqgrid.css" type="text/css" rel="stylesheet"/>
	<style type="text/css">
		#fieldTypeList {text-align: left;}
		#fieldTypeList li {cursor: pointer; list-style: none; line-height: 1.5em;}
		label {cursor: pointer; padding-left: .5em;}
		#fieldTypeList li.has {color: #BDBDBD;}
	</style>
	
	
	<script src="${ctx }/js/common/jquery.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/jui/jquery-ui.min.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/jqGrid/i18n/grid.locale-cn.js" type="text/javascript"></script>
	<script type="text/javascript">
	    $.jgrid.no_legacy_api = true;
	    $.jgrid.useJSON = true;
    </script>
	<script src="${ctx }/js/common/plugins/jqGrid/jquery.jqGrid.min.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/common.js" type="text/javascript"></script>
	<script src="${ctx }/js/module/field/type/link-list.js" type="text/javascript"></script>
</head>

<body>
	<table id="list"></table>
	<div id="pager"></div>
	<div id="addFieldTypeDialog" title="添加场地类型">
		<ol id="fieldTypeList"></ol>
	</div>
</body>
</html>