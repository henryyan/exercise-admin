<?xml version="1.0" encoding="UTF-8" ?>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/global.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  	<head>
  		<%@ include file="/common/meta.jsp" %>
    	<title>会员卡类型设置</title>
    	
    	<link rel="stylesheet" type="text/css" href="${ctx }/css/global.css" />
		<link rel="stylesheet" type="text/css" href="${ctx }/css/tablecloth.css" />
		<link type="text/css" rel="stylesheet" href="${ctx }/css/all.css" />
		<link type="text/css" rel="stylesheet" href="${ctx }/css/style.css" />
		<link rel="stylesheet" type="text/css" href="${ctx }/js/common/plugins/tipTip/tipTip.css"/>
		<link rel="stylesheet" type="text/css" href="${ctx }/js/common/plugins/nyroModal/css/nyroModal.css"/>
		<link rel="stylesheet" type="text/css" href="${ctx }/js/common/plugins/validate/jquery.validate.css"/>
		<style type="text/css">
		#formDiv {display: none;width: 550px;height: 350px;}
		#btns {text-align: right;padding-top: .5em;}
		</style>
		
		<script type="text/javascript" src="${ctx }/js/common/jquery.js"></script>
		<script type="text/javascript" src="${ctx }/js/common/plugins/validate/jquery.validate.pack.js"></script>
		<script type="text/javascript" src="${ctx }/js/common/plugins/validate/messages_cn.js"></script>
		<script type="text/javascript" src="${ctx }/js/common/plugins/jquery.metadata.js"></script>
		<script type="text/javascript" src="${ctx }/js/common/plugins/tipTip/jquery.tipTip.js"></script>
		<script type="text/javascript" src="${ctx }/js/common/plugins/nyroModal/jquery.nyroModal.pack.js"></script>
        <script type="text/javascript" src="${ctx }/js/common/common.js"></script>
		<script type="text/javascript" src="${ctx }/js/common/datepicker/WdatePicker.js"></script>
        <script type="text/javascript" src="${ctx }/js/module/member/memberCardType.js"></script>
  	</head>
  
  	<body>
    	<center><h2>会员卡类型设置</h2></center>
    	<div id="message">
    		<s:actionmessage theme="custom" cssClass="msuccess"/>
    		<s:actionerror theme="custom" cssClass="merror"/>
    	</div>
    	<div id="formDiv">
        <form action="cardType!save.action" method="post" id="cardTypeForm" name="cardTypeForm" onsubmit="return $('#cardTypeForm').valid();">
            <input type="hidden" id="id" name="id"/>
            <table border="1" width="100%" cellspacing="0" class="row" align="center">
                <tr>
                    <td width="20%" class="label">类型名称：</td>
                    <td>
                        <input type="text" id="typeName" name="typeName" maxlength="50"/>
                    </td>
                </tr>
                <tr>
                    <td class="label">卡内金额：</td>
                    <td>
                        <input type="text" id="moneyAmount" name="moneyAmount"/>
                    </td>
                </tr>
                <tr>
                    <td class="label">协议服务费：</td>
                    <td>
                        <input type="text" id="paymentCommision" name="paymentCommision" maxlength="6"/>
                    </td>
                </tr>
                <tr>
                    <td class="label">有效期时长：</td>
                    <td>
                        <input type="text" id=periodMonth name="periodMonth" maxlength="4"/>（月）
                        <div>有效期：<b><span id="showYear"></span></b>&nbsp;年，<b><span id="showMonth"></span></b>&nbsp;月</div>
                    </td>
                </tr>
                <tr>
                    <td class="label">折扣方式：</td>
                    <td>
                        <select id="discountType" name="discountType" style="width: 150">
                        	<option value="1">折扣率</option>
                        	<option value="2">折扣额</option>
                        </select>
                    </td>
                </tr>
                <tr id="discountType1">
                    <td class="label">折扣率：</td>
                    <td>
                        <input type="text" id="discountRate" name="discountRate" maxlength="3"/>%
                    </td>
                </tr>
                <tr id="discountType2" style="display: none">
                    <td class="label">折扣单价</td>
                    <td>
                        <input type="text" id="discountPrice" name="discountPrice" size="6" maxlength="3"/> * 场次 <input type="text" id="discountSession" size="4" maxlength="4" readonly="readonly"/> = 
                        <span id="moneyAmountSpan"></span>
                    </td>
                </tr>
                <tr>
                    <td class="label">折扣时段：</td>
                    <td>
                    	<input type="checkbox" id="common" name="vdiscountTime" checked="checked"/><label for="common">非周末时段</label>
                        <input type="checkbox" id="weekday" name="vdiscountTime" /><label for="weekday">周末时段</label>
                        <input type="hidden" id="discountTime" name="discountTime"/>
                    </td>
                </tr>
                <tr>
                    <td class="label">会员卡描述：</td>
                    <td>
                    	<textarea rows="3" cols="30" id="describtion" name="describtion"></textarea>
                    </td>
                </tr>
                <tr>
                	<td>&nbsp;</td>
                    <td>
                        <input type='submit' name="submit" id='submit' title="保存" value="保 存" />
						<input type='button' id='resetBtn' title="重置表单" value="重 置" />
                    </td>
                </tr>
            </table>
        </form>
        </div>
		<table border="1" width="100%" cellspacing="0" class="row" align="center" style="text-align: center;">
			<thead>
				<tr>
					<th>会员卡<br/>类<span style="padding-left: 11px"></span>型</th>
					<th>打折<br/>方式</th>
					<th>卡内金额</th>
					<th>协议<br/>服务费</th>
					<th>有效期（月）</th>
					<th>折扣率</th>
					<th>折扣额</th>
					<th>折扣<br/>范围</th>
					<th>会员卡<br/>描<span style="padding-left: 11px"></span>述</th>
					<th>操作</th>
				</tr>
			</thead>
			<c:forEach var="type" items="${types}" varStatus="i">
			<tr>
				<td>${type.typeName }</td>
				<td>${type.discountZhType }</td>
				<td>${type.moneyAmount }</td>
				<td>${type.paymentCommision }</td>
				<td>${type.periodMonth }</td>
				<td>&nbsp;
					<c:if test="${type.discountRate > 0}">${type.discountRate}%</c:if>
				</td>
				<td>&nbsp;${type.discountPrice > 0 ? type.discountPrice : '' }</td>
				<td>
				<c:if test="${type.commonDay}">非周末</c:if>
				<c:if test="${type.weekDay}">，周末</c:if>
				</td>
				<td title="${type.describtion }">&nbsp;${fn:length(type.describtion) > 5 ? fn:substring(type.describtion, 0, 5) : type.describtion}</td>
				<td class="operate {idv: ${type.id }}">
					<a href="javascript:;" id="update">修改</a>&nbsp;
					<a href="javascript:;" id="delete">删除</a>
				</td>
			</tr>
			</c:forEach>
		</table>
		<div id="btns">
			<button type="button" id="addType">添加会员卡类型</button>
		</div>
  </body>
</html>
