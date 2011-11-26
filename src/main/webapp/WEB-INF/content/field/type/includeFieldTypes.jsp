<%@page import="java.util.*"%>
<%@page import="com.runchain.exercise.admin.butil.venue.VenueInfoUtil"%>
<%@page import="com.runchain.exercise.admin.entity.field.type.FieldType"%>
<%@page import="org.springside.modules.utils.web.struts2.Struts2Utils"%><%@ page
	import="org.springframework.context.ApplicationContext,org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page
	import="com.runchain.exercise.admin.service.field.type.FieldTypeVenueLinkManager"%>
<%
	ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getSession()
			.getServletContext());
	FieldTypeVenueLinkManager linkManager = (FieldTypeVenueLinkManager) context
			.getBean("fieldTypeVenueLinkManager");
	List<FieldType> fieldTypes = linkManager.findTypeByVenue(VenueInfoUtil.getVenueFromSession().getId());
	pageContext.setAttribute("fieldTypes", fieldTypes);
%>