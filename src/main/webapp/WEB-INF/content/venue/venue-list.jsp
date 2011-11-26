<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/global.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/common/meta.jsp" %>
	<title>场馆信息列表</title>
	<link href="${ctx }/css/yui.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx }/js/common/plugins/jui/themes/${themeName }/jquery-ui.css" type="text/css" rel="stylesheet" />
	<link href="${ctx }/css/jqGrid/ui.jqgrid.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx }/js/common/plugins/validate/jquery.validate.css" type="text/css" rel="stylesheet"/>
	<link rel="stylesheet" type="text/css" href="${ctx }/js/common/plugins/nyroModal/css/nyroModal.css"/>
	<link href="${ctx }/css/style.css" type="text/css" rel="stylesheet"/>
	
	<style type="text/css">
		body {font-size: 65%;}
		.verified {color: green;font-weight: bold;}
		#t_list {height: auto; padding: 5px 0 5px 0; text-align: center;}
		.ui-jqgrid tr.jqgrow td { height : 60px; }
	</style>
	
	<script src="${ctx }/js/common/jquery.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/validate/jquery.validate.pack.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/extends/jquery.validate.methods.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/validate/messages_cn.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/nyroModal/jquery.nyroModal.pack.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/jqGrid/i18n/grid.locale-cn.js" type="text/javascript"></script>
	<script type="text/javascript">
	    $.jgrid.no_legacy_api = true;
	    $.jgrid.useJSON = true;
    </script>
	<script src="${ctx }/js/common/plugins/jui/jquery-ui.min.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/jqGrid/jquery.jqGrid.min.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/datepicker/WdatePicker.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/common.js" type="text/javascript"></script>
	<script src="${ctx }/js/module/venue/venue-list.js" type="text/javascript"></script>
</head>

<body>
	<table id="list"></table>
	<div id="pager"></div>
	<div id="btns" class="template">
		<button id="verify" type="button">验证场馆</button>
		<button id="unverify" type="button">取消验证场馆</button>
		|
		<button id="upgrade" type="button">升级为协议用户</button>
		<button id="unUpgrade" type="button">解除协议</button>
		|
		<button id="uploadImg" type="button">上传图片</button>
	</div>
	<div id="uploadPictureDialog" title="上传场馆图片" class="template">
		<iframe width="100%" height="100%" frameborder="0" marginheight="0" marginwidth="0"></iframe>
	</div>
	<div id="vePictureDialog" title="查看/编辑场馆图片" class="template">
		<iframe width="100%" height="100%" frameborder="0" marginheight="0" marginwidth="0"></iframe>
	</div>
</body>
</html>