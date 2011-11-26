<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/global.jsp" %>

<%@ page import="org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter" %>
<%@ page import="org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter" %>
<%@ page import="org.springframework.security.core.AuthenticationException" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>我要锻炼场馆管理员->管理系统->登录页</title>
	<link href="${ctx }/css/login.css" type="text/css" rel="stylesheet"/>
	<script src="${ctx }/js/common/jquery.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/validate/jquery.validate.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/plugins/validate/messages_cn.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/common.js" type="text/javascript"></script>
	<script>
		$(function() {
			$('#j_username').focus();
			$('#denglu').click(function(){
				$("#loginForm").submit();
			});
			$('#loginForm').bind('keydown', function(event){
				if (event.keyCode == 13) {
					$("#loginForm").submit();
				}
			});
			if (parent != window) {
				$.common.window.getTopWin().location.href = '${ctx }/login.action';
			}
			$("#loginForm").validate();
		});
	</script>
</head>

<body>
<div id="maincontent">
  <div id="header">
     <div id="logo1"><img src="images/logo1.gif"/></div>
    <div id="logo2"><img src="images/logo3.png"/></div>
  </div>
  <div id="middlecontent">
   <div id="topmenu">
    <ul>
       <li><a href="#">首页</a></li>
       <li><a href="#">关于我们</a></li>
       <li><a href="#">联系我们</a></li>
       <li><a href="#">帮助中心</a></li>
    </ul>
   </div>
    <div id="siderbar"> <img src="images/A-1-1.png" width="201" height="352"/></div>
    <div id="content">
      <form id="loginForm" action="${ctx }/j_spring_security_check" method="post" style="margin-top:1em">
      <div id="sign">
      	<%
		if (session.getAttribute(AbstractAuthenticationProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY) != null) {
		%> 
		<p style="color:red"> 登录失败，请重试.</p> 
		<%}%>
        <p>访问您的账户</p>
        <div id="memberName">
        <label for="j_username" style="width:80px;font-size:13px; font-family:微软雅黑;">用户名：</label>
        	<input type='text' id='j_username' name='j_username' class="required" style="width:120px"
					<s:if test="not empty param.error">
						value='<%=session.getAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_LAST_USERNAME_KEY)%>'</s:if> />
        </div>
       <div id="pwd"> 
       <label for="j_password" style="width:80px;font-size:13px; font-family:微软雅黑;">密&nbsp;&nbsp;&nbsp;码：</label>        
        <input type='password' id='j_password' name='j_password' class="required" style="width:120px"/>
       </div>
       <div id="denglu">登录 Sigh In</div><div id="remember"><input type="checkbox" name="remember"/>两周内记住我</div>
      </div>
      </form>
    </div>
  </div>
	<div id="footer">
		<div id="di2">
			<div style="padding-left:315px; padding-top:5px;">
			<a href="about.html">关于我们</a> | <a href="contact.html">联系方式</a> | 
			<a href="#">意见反馈</a> | <a href="#">帮助中心</a> | 2009-2011 51duanlian.com 
			<a href="http://www.miibeian.gov.cn">沪ICP备09057643号</a></div>
		</div>
	</div>
</div>

</body>
</html>