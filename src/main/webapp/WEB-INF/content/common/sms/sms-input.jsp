<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/global.jsp" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>添加短信模板</title>
	<%@ include file="/common/meta.jsp" %>
	<link href="${ctx }/css/yui.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx }/css/style.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx }/js/common/plugins/jui/themes/${themeName }/jquery-ui.css" type="text/css" rel="stylesheet" />
	<link href="${ctx }/js/common/plugins/validate/jquery.validate.css" type="text/css" rel="stylesheet"/>
	
	<style type="text/css">
		#properties a {
			background-color: #DCE6F4;
			padding: .5em;
			text-decoration: none;
		}
		#properties a:HOVER {
			font-weight: bold;
		}
		#sms-preview {
			text-align: left;
			position: absolute;
			padding: .5em;
			display: none;
			background-color: #95B3D7;
		}
		#preview-content {padding-top: .5em; padding-bottom: .8em; line-height: 1.5em;}
	</style>
	
	<script src="${ctx }/js/common/jquery.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/jquery.metadata.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/jquery.corner.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/validate/jquery.validate.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/validate/messages_cn.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/common.js" type="text/javascript"></script>
	<script src="${ctx }/js/module/common/sms/sms-input.js" type="text/javascript"></script>
</head>
<body>
<div id="doc3">
<div id="bd">
	<div id="yui-main">
	<div class="yui-b">
	<h2><s:if test="id == null">创建</s:if><s:else>修改</s:else>短信模板</h2>
		<form id="inputForm" action="sms!save.action" method="post">
			<input type="hidden" id="id" name="id" value="${id}"/>
			<table class="noborder">
				<tr>
					<td>模板名称:</td>
					<td><input type="text" id="propName" name="propName" size="20" value="${propName}"/></td>
					<td></td>
				</tr>
				<tr>
					<td>模板内容:</td>
					<td>
						<textarea id="propValue" name="propValue" rows="3" cols="40">${propValue }</textarea>
					</td>
					<td id="properties">
						<a href="javascript:;" class="{pn:'venueName'}" title="活动场馆名称">场馆名称</a>
						<a href="javascript:;" class="{pn:'date'}" title="活动的预定日期">预定日期</a>
						<a href="javascript:;" class="{pn:'period'}" title="活动时段">时段</a>
						<a href="javascript:;" class="{pn:'code'}" title="活动验证码">验证码</a>
						<a href="javascript:;" class="{pn:'telphone'}" title="活动场馆联系电话">场馆电话</a><br/><br/>
						<input id="preview" type="button" value="预览模板" />&nbsp;<b>提示</b>：点击标签插入
					</td>
				</tr>
				<tr>
					<td>模板说明:</td>
					<td><textarea id="propDescribe" name="propDescribe" rows="3" cols="40">${propDescribe}</textarea></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;">
						<input class="button" type="submit" value="提交"/>&nbsp;
						<input class="button" type="button" value="返回" onclick="history.back();"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
	</div>
</div>
</div>
<div id="sms-preview">
	<b>短信模板预览：</b>
	<div id="preview-content">内容</div>
	<div>
		<a id="closePreive" href="javascript:;">关闭</a>
	</div>
</div>
</body>
</html>