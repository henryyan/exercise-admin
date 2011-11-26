<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/global.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/common/meta.jsp" %>
	<title>场馆会员信息列表</title>
	<link href="${ctx }/css/yui.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx }/js/common/plugins/jui/themes/${themeName }/jquery-ui.css" type="text/css" rel="stylesheet" />
	<link href="${ctx }/css/jqGrid/ui.jqgrid.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx }/css/style.css" type="text/css" rel="stylesheet"/>
	
	<script src="${ctx }/js/common/jquery.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/jui/jquery-ui.min.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/jqGrid/i18n/grid.locale-cn.js" type="text/javascript"></script>
	<script type="text/javascript">
	    $.jgrid.no_legacy_api = true;
	    $.jgrid.useJSON = true;
    </script>
	<script src="${ctx }/js/common/plugins/jqGrid/jquery.jqGrid.min.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/common.js" type="text/javascript"></script>
	<script src="${ctx }/js/module/member/member-list.js" type="text/javascript"></script>
</head>

<body>
	<table id="list"></table>
	<div id="pager"></div>
	<hr />
	<table id="recordList"></table>
	<div id="recordPager"></div>
	
	<div id="cardUsageDetailTemplate" class="template">
		<div class="loading">正在加载信息，请稍候……</div>
		<table id="cardUsageDetailTable" cellspacing="0" border="0" width="100%" style="margin: 0px;">
			<tr>
				<th>序号</th>
				<%-- <th>卡号</th> --%>
				<th>联系人</th>
				<%-- <th>手机号码</th> --%>
				<th>场地信息</th>
				<th>活动信息</th>
				<th>价格</th>
				<th>预订时间</th>
				<th>付款时间</th>
			</tr>
		</table>
	</div>
</body>
</html>