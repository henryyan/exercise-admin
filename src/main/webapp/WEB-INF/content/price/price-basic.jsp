<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/global.jsp"%>

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
			legend {color: white;}
		</style>
		
		<script src="${ctx }/js/common/jquery.js" type="text/javascript"></script>
		<script src="${ctx }/js/common/plugins/jquery.metadata.js" type="text/javascript"></script>
		<script src="${ctx }/js/common/plugins/form/jquery.form.js" type="text/javascript"></script>
		<script src="${ctx }/js/module/price/price.js" type="text/javascript"></script>
		<script type="text/javascript">
			var startTime = '${venue.openTime }';
			var endTime = '${venue.closeTime }';
			var fieldType = '${fieldType }';
			var priceType = '${priceType }';
		</script>
	</head>

<body>
	<form action="${ctx }/price/${fieldType }-${priceType }!batchAdd.action" method="post" id="priceForm" name="priceForm">
		<div class="configure" align="center">
			<fieldset style="width: 40%">
				<legend>设置[<s:property value="@com.runchain.exercise.admin.butil.field.FieldUtil@FIELD_EN_ZH_NAME.get(#request.fieldType)" />]
				[<s:property value="@com.runchain.exercise.admin.butil.field.FieldUtil@PRICE_EN_ZH_NAME.get(#request.priceType)" />]详细价格</legend>
				<table border="0" width="100%" cellspacing="0" class="row">
					<tr>
						<td width="40%" class="label">最低计时单位：</td>
						<td align="left">
							<input type="text" id="lowestTime" name="lowestTime" size="5" />
							<select id="lowestTimeType" name="lowestTimeType">
								<option value="小时">小时</option>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="button" id="next" value="设置详细价格" />
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
				<s:iterator value="prices">
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
			<hr/>&nbsp;&nbsp;全部设置为：每单位收费：<input type="text" id="templatePrice" maxlength="6" size="6"/>&nbsp;元，
			协议服务费：<input type="text" id="templateProtocolPrice" maxlength="6" size="6"/><br/>
			<input type="submit" id="submit" value="保存价格设置"/>
			<input type="button" id="cancel" value="取消设置"/>
		</div>

		<div id="notice" style="display: none"></div>
	</form>
</body>
</html>