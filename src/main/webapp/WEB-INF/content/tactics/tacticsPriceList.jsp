<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/common/global.jsp"%>
<%@page import="com.runchain.exercise.admin.butil.field.FieldUtil"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/meta.jsp"%>
		<title>价格列表</title>
		<link rel="stylesheet" type="text/css" href="${ctx }/css/global.css" />
		<link rel="stylesheet" type="text/css" href="${ctx }/css/tablecloth.css" />
		<link rel="stylesheet" type="text/css" href="${ctx }/css/all.css" />
		<style type="text/css">
			.configure,#priceWordspace{ text-align: center;}
			.active {background-color: yellow;font-weight: bold;}
			#periodTable {padding-top: 10px;text-align: center;}
			#periodTable td {color: #000000}
			.field {font-size: 10pt; font-weight: bold;}
			.tennis {}
			.empty {border: 1px solid red;}
			input[type=text] {font-weight: bold;}
			#warning {
			    background-color: #FAFAD2;
			    border: 2px #2E8B57 solid;
			    text-align: center;
			    color: red;
			}
		</style>
		
		<script src="${ctx }/js/common/jquery.js" type="text/javascript"></script>
		<script src="${ctx }/js/common/plugins/jquery.metadata.js" type="text/javascript"></script>
		<script src="${ctx }/js/common/plugins/form/jquery.form.js" type="text/javascript"></script>
		<script src="${ctx }/js/common/common.js" type="text/javascript"></script>
		<script src="${ctx }/js/module/tactics/tacticsPrice.js" type="text/javascript"></script>
		<script type="text/javascript">
			var openTime = '${venue.openTime }';
			var closeTime = '${venue.closeTime }';
		</script>
	</head>

<body>
	<form action="${ctx }/tactics/tacticsPrice!batchAdd.action" method="post" id="priceForm" name="priceForm">
		<input type="hidden" id="tacticsId" name="tacticsId" value="${param.tacticsId }"/>
		<input type="hidden" id="fieldType" name="fieldType"/>
		<input type="hidden" id="venueId" name="venueId" value="${venue.id }" />
		<div class="configure" align="center">
			<fieldset style="width: 40%">
				<legend>设置[<%=FieldUtil.FIELD_EN_ZH_NAME.get(request.getParameter("fieldType")) %>]详细价格</legend>
				<table border="0" width="100%" cellspacing="0" class="row">
					<tr>
						<td width="40%" class="label">场地类型：</td>
						<td align="left" class="field tennis"><%=FieldUtil.FIELD_EN_ZH_NAME.get(request.getParameter("fieldType")) %></td>
					</tr>
					<tr>
						<td class="label">最低计时单位：</td>
						<td align="left">
							<input type="text" id="lowestTime" name="lowestTime" size="5" />
							<select id="lowestTimeType" name="lowestTimeType">
								<option value="小时">小时</option>
							</select>
						</td>
					</tr>
					<tr>
						<td class="label">开始时间：</td>
						<td>
							<select id='fromTimeHour' name='fromTimeHour'><option>请选择</option></select>时
							<select id='fromTimeMinute' name='fromTimeMinute'><option>请选择</option></select>分
							<font color='red'>*</font>
						</td>
						<td class="validatorTip"></td>
					</tr>
					<tr>
						<td class="label">结束时间：</td>
						<td>
							<select id='toTimeHour' name='toTimeHour'><option>请选择</option></select>时
							<select id='toTimeMinute' name='toTimeMinute'><option>请选择</option></select>分
							<font color='red'>*</font>
						</td>
						<td class="validatorTip"></td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="button" id="next" value="设置详细价格" />
							<input type="button" value="返回列表" onclick="history.back()" />
						</td>
					</tr>
				</table>
			</fieldset>
			<table border="1" width="90%" cellspacing="0" id="row" class="row" style="text-align: center;">
				<thead>
					<tr>
						<th width="10%">开始时间</th>
						<th width="10%">结束时间</th>
						<th width="25%">每单位收费</th>
						<th width="25%">协议服务费</th>
						<th width="10%">操作</th>
					</tr>
				</thead>
				<s:iterator value="allPrice">
					<tr class="{pid:${id }}">
						<td>${fromTime}&nbsp;</td>
						<td>${toTime}&nbsp;</td>
						<td class="{priceType:'basic'}">
							<span class="showPrice">${price }</span>
							<input id="price" size="7" maxlength="6" style="display:none"/>
							<a href="javascript:;" name="update">修改</a>
							<a href="javascript:;" name="save" style="display:none">保存</a>
							<a href="javascript:;" name="cancel" style="display:none">取消</a>
						</td>
						<td class="{priceType:'protocol'}">
							<span class="showPrice">${paymentCommision }</span>
							<input id="protocolPrice" size="7" maxlength="6" style="display:none"/>
							<a href="javascript:;" name="update">修改</a>
							<a href="javascript:;" name="save" style="display:none">保存</a>
							<a href="javascript:;" name="cancel" style="display:none">取消</a>
						</td>
						<td class="operate">
							<a href="javascript:;" name="delete">删除</a>
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>


		<div id="priceWorkspace" style="display: none">
			<h2>特殊策略价格设置</h2>
			<span id="warning">重新设置价格会清空已设置价格信息</span>
			<table id="periodTable" border="0" width="500" cellpadding="0" cellspacing="0" class="tableForm">
				<tr>
					<th>时段</th>
					<th>每单位收费</th>
					<th>协议服务费</th>
				</tr>
			</table>
		</div>

		<div id="operation" style="display: none">
			<hr/>全部设置为：每单位收费：<input type="text" id="templatePrice" maxlength="6" size="6"/>&nbsp;元，
			协议服务费：<input type="text" id="templateProtocolPrice" maxlength="6" size="6"/><br/>
			<input type="submit" id="submit" value="保存价格设置"/>
			<input type="button" id="cancel" value="取消设置"/>
		</div>

		<div id="notice" style="display: none"></div>
	</form>
</body>
</html>