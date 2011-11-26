package com.runchain.exercise.admin.web.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import com.runchain.arch.service.ServiceException;
import com.runchain.arch.web.base.CrudActionSupport;

/**
 * 短信内容设置服务
 *
 * @author HenryYan
 *
 */
@Namespace("/common/sms")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "sms.action", type = "redirect") })
public class SmsAction extends SystemPropertyAction {

	private static final long serialVersionUID = 1L;
	
	@Override
	public String input() throws Exception {
		return INPUT;
	}
	
	@Override
	public String save() {
		entity.setPropKey("sms." + entity.getPropName());
		entity.setVisible(false);
		propertyManager.saveProperty(entity);
		addActionMessage("保存短信模板成功");
		return RELOAD;
	}
	
	@Override
	public String delete() {
		try {
			propertyManager.deleteProperty(id);
			addActionMessage("删除短信模板成功");
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
			addActionMessage("删除短信模板失败");
		}
		return RELOAD;
	}

	@Override
	public String list() {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
		//-- 添加条件 --//
		filters.add(new PropertyFilter("LIKES_propKey", "sms")); // 以短信开头的
		//filters.add(new PropertyFilter("EQB_visible", "true")); // 普通用户可以看到的 
		
		// 设置每页显示数
		page.setPageSize(10);
		
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.ASC);
		}
		
		page = propertyManager.searchProperty(page, filters);
		return SUCCESS;
	}
	
	//-- 其他Action函数 --//
	/**
	 * 支持使用Jquery.validate Ajax检验用户名是否重复.
	 */
	public String checkPropName() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String newPropName = request.getParameter("propName");
		String oldPropName = request.getParameter("oldPropName");

		if (propertyManager.isPropNameUnique(newPropName, oldPropName)) {
			Struts2Utils.renderText("true");
		} else {
			Struts2Utils.renderText("false");
		}
		//因为直接输出内容而不经过jsp,因此返回null.
		return null;
	}

}
