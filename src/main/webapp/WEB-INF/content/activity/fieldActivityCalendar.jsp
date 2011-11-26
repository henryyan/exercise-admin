<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/global.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="com.runchain.exercise.admin.butil.field.FieldUtil"%><html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/common/meta.jsp" %>
	<title>场地活动预定状况</title>
	<link rel="stylesheet" type="text/css" href="${ctx }/css/global.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx }/css/tablecloth.css" />
	<link rel="stylesheet" type="text/css" href="${ctx }/css/all.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx }/js/common/plugins/nyroModal/css/nyroModal.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx }/js/common/plugins/tipTip/tipTip.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx }/css/activity/activity.css"/>
	
	<script src="${ctx }/js/common/jquery.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/jquery.jsonp.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/nyroModal/jquery.nyroModal.pack.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/tipTip/jquery.tipTip.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/common.js" type="text/javascript"></script>
	<script src="${ctx }/js/module/activity/fieldActivityCalendar.js" type="text/javascript"></script>
</head>

<body>
	<input type="hidden" id="fieldType" value="${param.fieldType }"/>
	<div id="dateShow">
		<span id="year"></span>&nbsp;年
		<span id="month"></span>&nbsp;月
		<span id="day"></span>&nbsp;日
	</div>

	<div id="calendarBtns">
		<button id="preMonth" disabled="disabled">上个月</button>
		<button id="nextMonth">下个月</button>
		<button id="currentMonth">当  月</button>
	</div>
	<div id="tipInfo" class="onLoad">场地信息正在更新</div>
	<div id="venueInfo">
		<center><b>场馆：</b>${venue.venueName }
		<span style="padding-left: 2em"><b>场地类型：</b><%=FieldUtil.FIELD_EN_ZH_NAME.get(request.getParameter("fieldType")) %></span>
		</center>
	</div>

	<div id="bookingCalendar" style="text-align: center;"></div>

	<div class="notice">
		<ul>
			<li><span class="usable">&nbsp;</span>有空余场地</li>
			<li><span class="unusable">&nbsp;</span>无空余场地</li>
			<li><span class="timeout">&nbsp;</span>无空余场地</li>
		</ul>
	</div>
</body>
</html>