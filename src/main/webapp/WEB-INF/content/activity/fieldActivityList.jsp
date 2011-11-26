<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/global.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/common/meta.jsp" %>
	<meta http-equiv="refresh" content="<%=10 * 60 %>" />
	<title>场馆活动列表</title>
	<link href="${ctx }/css/global.css" rel="stylesheet" type="text/css" />
	<link href="${ctx }/css/tablecloth.css" rel="stylesheet" type="text/css" />
	<link href="${ctx }/css/all.css" rel="stylesheet" type="text/css" />
	<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="${ctx }/js/common/plugins/tipTip/tipTip.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx }/css/activity/activity.css"/>
	<style type="text/css">
	.row {text-align: center;}
	.correct {
		text-align: center;
		background-image: url("../../images/validitor/onCorrect.gif");
		background-repeat: no-repeat;
		background-position: center;
	}
	</style>
	
	<script src="${ctx }/js/common/jquery.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/tipTip/jquery.tipTip.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/jquery.jsonp.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/jquery.blockUI.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/datepicker/WdatePicker.js"type="text/javascript"></script>
	<script type="text/javascript">
	    var fieldType = "${param.fieldType }";
	    var fieldId = "${param.fieldId }";
    </script>
	<script src="${ctx }/js/common/common.js" type="text/javascript"></script>
	<script src="${ctx }/js/module/activity/fieldActivityList.js" type="text/javascript"></script>
	<script src="${ctx }/js/module/field/activity/common.activity.js" type="text/javascript"></script>
</head>

<body>
	<input type="hidden" id="advance" />
	
	<div class="pageStep">
    	<a href="javascript:;" id="today"><span class="today">今天</span></a>

		<a href="javascript:;" id="previous">
			<img src="${ctx }/images/arrow_left.png" border="0" />前一天(<span id="previousDate"></span>)
		</a>

		<span id="pickTip" style="font-size: 13;padding-right: 10px;">
			选择预定日期：<input type="text" id="usableDate" size="12" class="Wdate" value="" style="cursor:pointer" />
		</span>

		<a href="javascript:;" id="later">
			后一天(<span id="laterDate"></span>)<img src="${ctx }/images/arrow_right.png" border="0" />
		</a>
  	</div>
	
	<table border="0" cellspacing="0" width="100%" id="row" class="row">
  		<thead>
  			<tr>
  				<th width="13%">时段</th>
  				<th width="8%">价格</th>
  				<th width="35%">预订情况</th>
  				<th width="10%">验证码</th>
  				<th width="13%">验证结果</th>
  				<th width="13%">锻炼情况</th>
  			</tr>
  		</thead>
  	</table>
  	<div id="orderInfo" class="template">
    	<br/>
		<div>订单号码：<span id="did">正在加载……</span></div>
		<div>会员号码：<span id="duserCode"></span></div>
    	<div style="padding-left:15px;">联系人：<span id="dcontact"></span></div>
		<div>联系电话：<span id="dphone"></span></div>
   	</div>
</body>
</html>