<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/common/global.jsp"%>
<%@ page
	import="org.springside.modules.security.springsecurity.SpringSecurityUtils"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=7" />
	<%@ include file="/common/meta.jsp"%>
	<!--CSS-->
	<link rel="stylesheet" type="text/css" href="${ctx }/css/global.css" />
	<link rel="stylesheet" type="text/css" href="${ctx }/js/common/plugins/nyroModal/css/nyroModal.css" />
	<link rel="stylesheet" type="text/css" href="${ctx }/js/common/plugins/jui/themes/${themeName }/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="${ctx }/css/main.css" />
	
	<!--Character Encoding-->
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	<title>我要锻炼管理系统</title>
	<script src="${ctx }/js/common/jquery.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/jui/jquery-ui.min.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/tip/jquery.qtip.min.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/nyroModal/jquery.nyroModal.pack.js" type="text/javascript"></script>
	<script type="text/javascript">
		var mainPage = true;
	</script>
	<script src="${ctx }/js/module/main.js" type="text/javascript"></script>
</head>
<body>
	<!--Main Container - Centers Everything-->
	<div id="mainContainer"><!--Header -->
		<div id="header">
		<div id="logo1"><img src="${ctx }/images/logo1.gif" /></div>
		<div id="logo2"><img src="${ctx }/images/logo3.png" /></div>
		</div>
		<!--Main Content-->
		<div id="middlecontent"><!--Nav-->
		<div id="topmenu">
			<ul>
				<li>欢迎 <span style="font-weight: bold;"><%=SpringSecurityUtils.getCurrentUserName()%></span>管理员</li>
				<li><a id="showAutoDiv" href="javascript:;" title="当前管理的场馆，点击切换">切换场馆（<span
					id="currentVenue">${venue.venueName }</span>）</a></li>
				<li><a href="${ctx }/main/main.action">首页</a></li>
				<li><a href="javascript:;" name="common" class="change-left-menu">公共设置</a></li>
				<li><a href="javascript:;" name="venue" class="change-left-menu">场馆管理</a></li>
				<li><a href="${ctx }/help/help.jsp">帮助中心</a></li>
				<li><a href="${ctx }/j_spring_security_logout">退出</a></li>
			</ul>
		</div>
		<div id="siderbar" class="mainsiderbar">
			<div id="si-content">
				<ul class="sc common">
					<li class="sc-title">公共设置</li>
					<li>
					<ul style="margin-left: -20px;">
						<li class="sc-item"><a
							href='${ctx }/common/sysprop-list.action'>系统属性</a></li>
						<li class="sc-item"><a
							href='${ctx }/bdata/area-info-list.action'>地区信息</a></li>
						<li class="sc-item"><a href='${ctx }/common/sms/sms.action'>短信模板</a></li>
						<%-- <li class="sc-item"><a
							href='${ctx }/venue/member/venue-member-list.action'>场馆会员</a></li> --%>
						<li class="sc-item"><a
							href='${ctx }/venue/venue-list.action'>场馆管理</a></li>
						<li class="sc-item"><a
							href='${ctx }/field/type/fieldtype-list.action'>场地类型管理</a></li>
						<li class="sc-item">
							<a href="${ctx }/account/user.action">用户权限管理</a>
						</li>
					</ul>
					</li>
				</ul>
				<ul class="sc venue">
					<li class="sc-title security">场地基本信息</li>
					<li>
					<ul style="margin-left: -20px;">
						<li class="sc-item" title="设置当前管理场馆拥有的场地类型"><a
							href='${ctx }/field/type/link-list.action' class='security'>设置场地类型</a>
						</li>
						<li class="sc-item"><a href='${ctx }/field/fieldTab.action'
							class='security'>设置场地信息</a></li>
						<li class="sc-item"><a href='${ctx }/price/priceTab.action'
							class='security'>默认价格策略</a></li>
						<li class="sc-item"><a
							href='${ctx }/tactics/tactics.action' class='security'>特殊价格策略</a></li>
						<li class="sc-item"><a
							href='${ctx }/field/createActivityTab.action' class='security'>场地生成策略</a></li>
					</ul>
					</li>
				</ul>
				<ul class="sc venue">
					<li class="sc-title security">活动预定信息</li>
					<li>
					<ul style="margin-left: -20px;">
						<li class="sc-item">
						<ul id="bookFieldType" style="margin-left: -20px;"></ul>
						</li>
					</ul>
					</li>
				</ul>
				<ul class="sc venue">
					<li class="sc-title security">活动现场信息</li>
					<li>
					<ul style="margin-left: -20px;">
						<li class="sc-item">
						<ul id="localeFieldType" style="margin-left: -20px;"></ul>
						</li>
					</ul>
					</li>
				</ul>
				<ul class="sc venue">
					<li class="sc-title">会员卡管理</li>
					<li>
					<ul style="margin-left: -20px;">
						<li class="sc-item"><a
							href='${ctx }/member/cardType.action' class='security'>会员卡类型</a></li>
						<li class="sc-item"><a
							href='${ctx }/member/memberCardList.action' class='security'>会员卡查询</a></li>
					</ul>
					</li>
				</ul>
				<ul class="sc venue">
					<li class="sc-title security">场馆报表</li>
					<li>
					<ul style="margin-left: -20px;">
						<li id="protocolFee" class="sc-item" style="display:${venue.isProtocol ? "" : 'none' }"><a href='${ctx }/report/protocolFee.action' class='security'>协议服务费</a></li>
						<li id="accountOrder" class="sc-item"><a href='${ctx }/report/account-order-list.action' class='security'>付款订单查询</a></li>
					</ul>
					</li>
				</ul>
			</div>
		</div>
		<div id="content">
			<iframe id="main" name="main" frameborder="0" width="100%" height="100%" src=""></iframe>
		</div>
		</div>
		<div id="footer">
		  <div id="di1"></div>
		  <div id="di2">
		    <div style="padding-left:385px; padding-top:5px;"><a href="${ctx }/about.jsp">关于我们</a> | <a href="${ctx }/contact.jsp">联系方式</a> | <a href="mailto:info@remote-click.com">意见反馈</a> | 帮助中心 | 2009-2011 51duanlian.com <a href="http://www.miibeian.gov.cn">沪ICP备09057643号</a></div>
		  </div>
		</div>
	</div>
	<div id="autoComplete" style="display: none">
		<label for="autoSearchVenue">场馆名称：</label>
		<input type="text" id="autoSearchVenue" size="50"/>
		<span class="venueTip">请<b>输入</b><em>场馆名称</em>，支持自动完成，<b>回车</b>选择场馆！</span>
	</div>
	
	<div id="selectVenue" style="display: none">
		<label for="autoSearchSelectVenue">场馆名称：</label>
		<input type="text" id="autoSearchSelectVenue" size="50"/>
		<span class="venueTip">请<b>输入</b><em>场馆名称</em>，支持自动完成，<b>回车</b>选择场馆！</span>
	</div>
</body>
</html>
