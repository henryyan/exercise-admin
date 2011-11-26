<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/global.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>短信模板</title>
	<%@ include file="/common/meta.jsp" %>
	<link href="${ctx }/css/yui.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx }/css/style.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx }/css/nyroModal/nyroModal.css" type="text/css" rel="stylesheet"/>
	<script src="${ctx }/js/common/jquery.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/table.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/jquery.metadata.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/nyroModal/jquery.nyroModal.pack.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/form/jquery.form.js" type="text/javascript"></script>
	<script src="${ctx }/js/module/common/sms/sms-list.js" type="text/javascript"></script>
</head>

<body>
<div id="doc3">
<div id="bd">
	<div id="yui-main">
		<div class="yui-b">
		<form id="mainForm" action="sms.action" method="get">
			<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
			<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
			<input type="hidden" name="page.order" id="order" value="${page.order}"/>
	
			<div id="message"><s:actionmessage theme="custom" cssClass="success"/></div>
			<div id="filter">
				模板名称: <input type="text" name="filter_LIKES_propName" value="${param['filter_LIKES_propName']}" size="15"/>&nbsp;
				模板内容: <input type="text" name="filter_LIKES_propValue" value="${param['filter_LIKES_propValue']}" size="15"/>
				<input type="button" value="搜索" onclick="search();"/>
			</div>
			<div id="content">
				<table id="contentTable">
					<tr>
						<th width="80"><a href="javascript:sort('propName','asc')">模板名称</a></th>
						<th><a href="javascript:sort('propValue','asc')">模板内容</a></th>
						<th>模板说明</th>
						<th width="100">操作</th>
					</tr>
	
					<s:iterator value="page.result">
						<tr>
							<td>${propName}&nbsp;</td>
							<td>
								<c:if test="${fn:length(propValue) > 45 }">${fn:substring(propValue, 0, 45)}...</c:if>
								<c:if test="${fn:length(propValue) < 45 }">${propValue}</c:if>&nbsp;
								<pre style="display: none;">${propValue }</pre>
							</td>
							<td>
								<c:if test="${fn:length(propDescribe) > 45 }">${fn:substring(propDescribe, 0, 45)}...</c:if>
								<c:if test="${fn:length(propDescribe) < 45 }">${propDescribe}</c:if>&nbsp;
							</td>
							<td>&nbsp;
								<a class="preview" href="javascript:;" title="预览此模板的实际效果">预览</a>
								<security:authorize ifAnyGranted="ROLE_浏览用户">
									<security:authorize ifNotGranted="ROLE_修改用户">
										<a href="sms!input.action?id=${id}">查看</a>&nbsp;
									</security:authorize>
								</security:authorize>
	
								<security:authorize ifAnyGranted="ROLE_修改用户">
									<a href="sms!input.action?id=${id}">修改</a>&nbsp;
									<a href="sms!delete.action?id=${id}">删除</a>
								</security:authorize>
							</td>
						</tr>
					</s:iterator>
				</table>
			</div>
	
			<div>
				第${page.pageNo}页, 共${page.totalPages}页
				<a href="javascript:jumpPage(1)">首页</a>
				<s:if test="page.hasPre"><a href="javascript:jumpPage(${page.prePage})">上一页</a></s:if>
				<s:if test="page.hasNext"><a href="javascript:jumpPage(${page.nextPage})">下一页</a></s:if>
				<a href="javascript:jumpPage(${page.totalPages})">末页</a>
	
				<security:authorize ifAnyGranted="ROLE_修改用户">
					<a id="adds" href="sms!input.action" title="添加短信模板">增加新模板</a>
				</security:authorize>
			</div>
		</form>
		</div>
	</div>
</div>
</div>
</body>
</html>
