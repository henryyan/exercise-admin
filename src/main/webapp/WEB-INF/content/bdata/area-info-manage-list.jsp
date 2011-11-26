<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/global.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/common/meta.jsp" %>
	<title>地区信息</title>
	<link href="${ctx }/css/yui.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx }/js/common/plugins/jui/themes/${themeName }/jquery-ui.css" type="text/css" rel="stylesheet" />
	<link href="${ctx }/js/common/plugins/jqGrid/css/ui.jqgrid.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx }/js/common/plugins/validate/jquery.validate.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx }/css/style.css" type="text/css" rel="stylesheet"/>
	
	<script src="${ctx }/js/common/jquery.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/jui/jquery-ui.min.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/jqGrid/i18n/grid.locale-cn.js" type="text/javascript"></script>
	<script type="text/javascript">
	    $.jgrid.no_legacy_api = true;
	    $.jgrid.useJSON = true;
	    var parentAreaId = '${param.parentId}';
	    if (!parentAreaId) {
	    	parentAreaId = 1;
		}
    </script>
	<script src="${ctx }/js/common/plugins/jqGrid/jquery.jqGrid.min.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/validate/jquery.validate.pack.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/validate/messages_cn.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/common.js" type="text/javascript"></script>
	<script src="${ctx }/js/module/bdata/area-info-manager.js" type="text/javascript"></script>
</head>

<body style="padding: 0px; margin: 0px;">
	<table id="list"></table>
	<div id="pager"></div>
</body>
</html>