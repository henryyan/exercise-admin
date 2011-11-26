<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/global.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/meta.jsp"%>
<title>价格列表</title>
</head>

<body>
<table id="contentTable">
	<tr>
		<th>开始时间</a></th>
		<th>结束时间</th>
		<th>每单位收费</th>
		<th>操作</th>
	</tr>

	<s:iterator value="prices">
		<tr>
			<td>${fromTime}&nbsp;</td>
			<td>${toTime}&nbsp;</td>
			<td>${price}&nbsp;</td>
		</tr>
	</s:iterator>
</table>
</body>
</html>