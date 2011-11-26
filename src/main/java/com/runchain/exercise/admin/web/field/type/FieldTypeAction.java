package com.runchain.exercise.admin.web.field.type;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import com.runchain.arch.web.base.CrudActionSupport;
import com.runchain.arch.web.base.JqGridCrudActionSupport;
import com.runchain.exercise.admin.entity.field.type.FieldType;
import com.runchain.exercise.admin.service.field.type.FieldTypeManager;
import com.runchain.arch.util.orm.PropertyFilterUtils;

/**
 * 场地类型管理Action.
 *
 * @author HenryYan
 *
 */
@Namespace("/field/type")
@Action(value = "type")
@Results( { @Result(name = CrudActionSupport.JSON, type = CrudActionSupport.JSON) })
public class FieldTypeAction extends JqGridCrudActionSupport<FieldType> {

	private static final long serialVersionUID = 1L;
	
	@Resource FieldTypeManager fieldTypeManager;
	
	private Boolean status;
	
	@Override
	public Page<FieldType> getPage() {
		return page;
	}

	@Override
	public String delete() throws Exception {
		try {
			fieldTypeManager.deleteFieldType(id);
		} catch (Exception e) {
			logger.error("删除场地类型时：", e);
		}
		return null;
	}

	@Override
	public String list() throws Exception {
		try {
			List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
			PropertyFilterUtils.handleFilter(page, FieldType.class, filters);
			
			String[] fields = new String[] {"id", "typeId", "typeName", "enable", "tablename"};
			page = fieldTypeManager.searchFieldType(page, filters, fields);
		} catch (Exception e) {
			logger.error("读取场地类型列表时：", e);
		}
		return JSON;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			entity = fieldTypeManager.getFieldType(id);
		} else {
			entity = new FieldType();
		}
	}

	@Override
	public String save() throws Exception {
		try {
			fieldTypeManager.saveFieldType(entity);
		} catch (Exception e) {
			logger.error("保存场地类型时：", e);
		}
		return null;
	}

	public FieldType getModel() {
		return entity;
	}

	/**
	 * 获取本系统支持的场地类型
	 * @return
	 */
	public String findEnableFieldType() {
		try {
			List<FieldType> enableFieldType = fieldTypeManager.getEnableFieldType();
			Struts2Utils.renderJson(enableFieldType);
		} catch (Exception e) {
			logger.error("获取系统支持的场地类型时：", e);
		}
		return null;
	}
	
	/**
	 * 设置场地状态
	 * @return
	 */
	public String changeEnable() {
		try {
			fieldTypeManager.changeEnable(id, status);
			Struts2Utils.renderText(SUCCESS);
		} catch (Exception e) {
			logger.error("设置场地状态：", e);
		}
		return null;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	
}
