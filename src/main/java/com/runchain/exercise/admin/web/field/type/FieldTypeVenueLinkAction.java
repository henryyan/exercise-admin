package com.runchain.exercise.admin.web.field.type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import com.runchain.arch.web.base.CrudActionSupport;
import com.runchain.arch.web.base.JqGridCrudActionSupport;
import com.runchain.exercise.admin.butil.venue.VenueInfoUtil;
import com.runchain.exercise.admin.entity.field.type.FieldType;
import com.runchain.exercise.admin.entity.field.type.VenueFieldtypeLink;
import com.runchain.exercise.admin.entity.venue.VenueInfo;
import com.runchain.exercise.admin.service.field.type.FieldTypeVenueLinkManager;
import com.runchain.arch.util.orm.PropertyFilterUtils;

/**
 * 场馆和场地类型关系管理Action
 *
 * @author HenryYan
 *
 */
@Namespace("/field/type")
@Action(value = "link")
@Results( { @Result(name = CrudActionSupport.JSON, type = CrudActionSupport.JSON) })
public class FieldTypeVenueLinkAction extends JqGridCrudActionSupport<VenueFieldtypeLink> {

	private static final long serialVersionUID = 1L;
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	protected FieldTypeVenueLinkManager linkManager;
	
	@Override
	public String delete() throws Exception {
		linkManager.deleteLink(id);
		return null;
	}

	@Override
	public String list() throws Exception {
		try {
			List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
			PropertyFilterUtils.handleFilter(page, VenueInfo.class, filters);
			
			// 添加当前管理场馆的ID条件
			VenueInfo venueInfo = VenueInfoUtil.getVenueFromSession();
			filters.add(new PropertyFilter("EQL_venueInfo.id", venueInfo.getId().toString()));
			
			page = linkManager.searchLink(page, filters);
		} catch (Exception e) {
			logger.error("读取场馆和场地类型关联表时：", e);
		}
		return JSON;
	}

	@Override
	protected void prepareModel() throws Exception {
	}

	@Override
	public String save() throws Exception {
		return null;
	}

	public VenueFieldtypeLink getModel() {
		return entity;
	}

	//-- 业务方法 --//
	/**
	 * 为场馆和场地类型设置关联关系
	 * @return
	 */
	public String addLink() {
		String[] ids = Struts2Utils.getRequest().getParameterValues("ids[]");
		VenueInfo venueInfo = VenueInfoUtil.getVenueFromSession();
		try {
			linkManager.addLink(venueInfo.getId(), ids);
			logger.debug("为场馆<" + venueInfo.getId() + ">，场地<" + ids + ">设置关系--成功");
			Struts2Utils.renderText("true");
		} catch (Exception e) {
			logger.error("为场馆<" + venueInfo.getId() + ">，场地<" + ids + ">设置关系时：", e);
			Struts2Utils.renderText("false");
		}
		return null;
	}

	/**
	 * 为场馆和场地类型移除关联关系
	 * @return
	 */
	public String removeLink() {
		VenueInfo venueInfo = VenueInfoUtil.getVenueFromSession();
		String strId = Struts2Utils.getParameter("fieldTypeId");
		Long fieldTypeId = new Long(strId);
		try {
			linkManager.removeLink(venueInfo.getId(), fieldTypeId);
			logger.debug("为场馆<" + venueInfo.getId() + ">，场地<" + fieldTypeId + ">移除关系--成功");
			Struts2Utils.renderText("true");
		} catch (Exception e) {
			logger.error("为场馆<" + venueInfo.getId() + ">，场地<" + fieldTypeId + ">移除关系时：", e);
			Struts2Utils.renderText("false");
		}
		return null;
	}
	
	/**
	 * 根据当前管理的用户获取已开通的场地类型
	 * @return
	 */
	public String findFieldTypes() {
		try {
			Long venueId = VenueInfoUtil.getVenueFromSession().getId();
			List<FieldType> findTypeByVenue = linkManager.findTypeByVenue(venueId);
			List<Map<String, Object>> types = new ArrayList<Map<String, Object>>();
			for (FieldType fieldType : findTypeByVenue) {
				Map<String, Object> tempType = new HashMap<String, Object>();
				tempType.put("id", fieldType.getId());
				tempType.put("name", fieldType.getTypeName());
				tempType.put("label", fieldType.getTypeId());
				types.add(tempType);
			}
			Struts2Utils.renderJson(types);
		} catch (Exception e) {
			logger.error("获取当前场馆开通的场地类型时：", e);
		}
		return null;
	}

	@Override
	public Page<VenueFieldtypeLink> getPage() {
		return page;
	}

}
