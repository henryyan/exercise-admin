package com.runchain.exercise.admin.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.runchain.exercise.admin.service.bdata.AreaInfoService;

/**
 * 初始化地区信息到内存Servlet
 *
 * @author HenryYan
 *
 */
public class AreaInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Logger logger = LoggerFactory.getLogger(AreaInfoServlet.class);

	public AreaInfoServlet() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		WebApplicationContext wct = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
		AreaInfoService areaInfoService = (AreaInfoService) wct.getBean("areaInfoService");

		logger.info("++++ 开始加载地区信息 ++++");
		areaInfoService.init();
		logger.info("++++ 地区信息加载完毕  ++++");
	}

}
