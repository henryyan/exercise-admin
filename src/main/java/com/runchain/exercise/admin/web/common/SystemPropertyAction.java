package com.runchain.exercise.admin.web.common;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import com.runchain.arch.web.base.CrudActionSupport;
import com.runchain.arch.web.base.JqGridCrudActionSupport;
import com.runchain.exercise.admin.entity.common.SystemProperty;
import com.runchain.exercise.admin.service.common.SystemPropertyManager;
import com.runchain.arch.util.json.JSONUtil;
import com.runchain.arch.util.orm.PropertyFilterUtils;

/**
 * 系统属性管理Action
 *
 * @author HenryYan
 *
 */
@Namespace("/common")
@Action(value = "sysprop")
@Results( { @Result(name = CrudActionSupport.JSON, type = CrudActionSupport.JSON) })
public class SystemPropertyAction extends JqGridCrudActionSupport<SystemProperty> {

	private static final long serialVersionUID = 1L;

	@Resource
	protected SystemPropertyManager propertyManager;

	//-- ModelDriven 与 Preparable函数 --//

	public SystemProperty getModel() {
		return entity;
	}

	@Override
	protected void prepareModel() {
		if (id != null) {
			entity = propertyManager.getProperty(id);
		} else {
			entity = new SystemProperty();
		}
	}

	//-- CRUD Action 函数 --//
	@Override
	public String save() {
		try {
			entity.setPropKey(entity.getPropName());
			propertyManager.saveProperty(entity);
		} catch (Exception e) {
			logger.error("保存属性", e);
		}
		return null;
	}

	@Override
	public String delete() {
		try {
			propertyManager.deleteProperty(id);
		} catch (Exception e) {
			logger.error("删除属性", e);
		}
		return null;
	}

	@Override
	public String list() {
		try {
			List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
			PropertyFilterUtils.handleFilter(page, SystemProperty.class, filters);

			filters.add(new PropertyFilter("EQB_visible", "1"));

			page = propertyManager.searchProperty(page, filters);
		} catch (Exception e) {
			logger.error("属性列表 ", e);
		}
		return JSON;
	}
	
	/**
	 * 查询属性
	 * @return
	 */
	public String findProp() {
		try {
			JSONUtil.readJson(Struts2Utils.getRequest());
			String key = Struts2Utils.getParameter("key");
			String[] keys = Struts2Utils.getRequest().getParameterValues("key[]");
			
			if (StringUtils.isNotEmpty(key)) {
				if (ArrayUtils.isEmpty(keys)) {
					keys = new String[]{key};
				}
			} else if(StringUtils.isEmpty(key) && ArrayUtils.isEmpty(keys)) {
				Struts2Utils.renderJson("");
				return null;
			}
			List<SystemProperty> props = propertyManager.findPropertyByKey(keys);
			if (props.size() == 1) {
				Struts2Utils.renderJson(props.get(0));
			} else if (props.size() > 1) {
				Struts2Utils.renderJson(props);
			} else {
				Struts2Utils.renderJson("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//-- 页面属性访问函数 --//

	/**
	 * list页面显示用户分页列表.
	 */
	public Page<SystemProperty> getPage() {
		return page;
	}

}
