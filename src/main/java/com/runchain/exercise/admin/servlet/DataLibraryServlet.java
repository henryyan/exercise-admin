package com.runchain.exercise.admin.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.runchain.exercise.admin.service.bdata.DataLibraryList;

/**
 * 数据字典初始化Servlet，加载到内存中
 *
 * @author HenryYan
 *
 */
public class DataLibraryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Logger logger = LoggerFactory.getLogger(DataLibraryServlet.class);

	public DataLibraryServlet() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		WebApplicationContext wct = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
		DataLibraryList dll = (DataLibraryList) wct.getBean("dataLibraryList");

		logger.info("++++ 开始加载数据字典 ++++");
		dll.init();
		logger.info("++++ 数据字典加载完毕 ++++");
	}

}
