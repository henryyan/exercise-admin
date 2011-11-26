package com.runchain.exercise.admin.web.venue.member;

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
import com.runchain.exercise.admin.entity.venue.member.VenueMemberInfo;
import com.runchain.exercise.admin.service.venue.member.VenueMemberManager;
import com.runchain.arch.util.orm.PropertyFilterUtils;

/**
 * 系统属性管理Action
 *
 * @author HenryYan
 *
 */
@Namespace("/venue/member")
@Action(value = "member")
@Results( { @Result(name = CrudActionSupport.JSON, type = CrudActionSupport.JSON) })
public class VenueMemberAction extends JqGridCrudActionSupport<VenueMemberInfo> {

	private static final long serialVersionUID = 1L;

	@Resource
	protected VenueMemberManager memberManager;

	//-- 页面属性 --//

	//-- ModelDriven 与 Preparable函数 --//
	
	public VenueMemberInfo getModel() {
		return entity;
	}
	
	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			entity = memberManager.getMemberInfo(id);
		} else {
			entity = new VenueMemberInfo();
		}
	}

	//-- CRUD Action 函数 --//
	@Override
	public String save() throws Exception {
		try {
			memberManager.saveMemberInfo(entity);
		} catch (Exception e) {
			logger.error("保存场馆会员", e);
		}
		return null;
	}

	@Override
	public String delete() throws Exception {
		try {
			memberManager.deleteMemberInfo(id);
		} catch (Exception e) {
			logger.error("删除场馆会员", e);
		}
		return null;
	}

	@Override
	public String list() throws Exception {
		try {
			List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
			PropertyFilterUtils.handleFilter(page, VenueMemberInfo.class, filters);
			
			page = memberManager.searchMemberInfo(page, filters);
		} catch (Exception e) {
			logger.error("场馆会员列表", e);
		}
		return JSON;
	}
	
	//-- 页面属性访问函数 --//
	/**
	 * list页面显示用户分页列表.
	 */
	public Page<VenueMemberInfo> getPage() {
		return page;
	}

}
