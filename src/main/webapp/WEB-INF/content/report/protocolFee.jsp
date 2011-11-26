<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/global.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/common/meta.jsp" %>
	<title>场馆信息列表</title>
	<link href="${ctx }/css/yui.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx }/css/style.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx }/js/common/plugins/jui/themes/${themeName }/jquery-ui.css" type="text/css" rel="stylesheet" />
	<link href="${ctx }/css/jqGrid/ui.jqgrid.css" type="text/css" rel="stylesheet"/>
	
	<style type="text/css">
		#totalDiv {
        	margin: 0 auto;
        	width: auto;
        	text-align: center;
        	padding: .5em 0 .5em 0;
        }
        #orderNumber {
        	margin-left: .5em;
        	margin-right: .5em;
        }
        .ui-jqgrid tr.jqgrow td {
			height : 50px;
		}
	</style>
	
	<script src="${ctx }/js/common/jquery.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/jqGrid/i18n/grid.locale-cn.js" type="text/javascript"></script>
	<script type="text/javascript">
	    $.jgrid.no_legacy_api = true;
	    $.jgrid.useJSON = true;
    </script>
	<script src="${ctx }/js/common/plugins/jqGrid/jquery.jqGrid.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="${ctx }/js/common/datepicker/WdatePicker.js"></script>
	<script src="${ctx }/js/common/common.js" type="text/javascript"></script>
	<script src="${ctx }/js/module/report/protocolFee.js" type="text/javascript"></script>
</head>

<body>
	<div>
		<font size="4">协议服务费统计日期：</font>
		<input type="text" id="startDate" class="Wdate" size="15" value="${param.startDate }" readonly="readonly" />
		至
		<input type="text" id="endDate" class="Wdate" size="15" value="${param.endDate }" readonly="readonly" />
		<button id="submit" type="button">统计</button>
	</div>
	<div id="totalDiv">
		<div>共<span id="orderNumber" class="onLoad"></span>项订单，
			<span>
				活动价格共计：<span id="priceSum" class="onLoad"></span> 元，
			</span>
			<span>服务费共计：<span id="feeSum" class="onLoad"></span> 元</span>
		</div>
	</div>
	<table id="list"></table>
	<div id="pager"></div>
</body>
</html>