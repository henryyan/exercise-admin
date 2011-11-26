package com.runchain.exercise.admin.web.bdata;

import java.util.List;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import com.runchain.arch.service.ServiceException;
import com.runchain.arch.util.orm.EntityUtils;
import com.runchain.arch.util.orm.PropertyFilterUtils;
import com.runchain.arch.web.base.CrudActionSupport;
import com.runchain.arch.web.base.JqGridCrudActionSupport;
import com.runchain.exercise.admin.entity.bdata.AreaInfo;
import com.runchain.exercise.admin.service.bdata.AreaInfoManager;
import com.runchain.exercise.admin.service.bdata.AreaInfoService;

/**
 *地区信息管理 Action
 * 
 * @author HenryYan
 * 
 */
@Controller
@Results( { @Result(name = CrudActionSupport.JSON, type = CrudActionSupport.JSON) })
public class AreaInfoManagerAction extends JqGridCrudActionSupport<AreaInfo> {
	private static final long serialVersionUID = 1L;

	@Autowired protected AreaInfoManager manager;
	@Autowired protected AreaInfoService service;
	
	private String newId;
	private Long parentAreaId;
	private String areaName;

	// -- ModelDriven 与 Preparable函数 --//

	public AreaInfo getModel() {
		return entity;
	}

	@Override
	protected void prepareModel() {
		if (id != null) {
			entity = manager.getAreaInfo(id);
		} else {
			entity = new AreaInfo();
		}
	}

	// -- CRUD Action 函数 --//
	@Override
	public String save() {
		try {
			if (EntityUtils.isNew(entity.getId())) {
				entity.setCountryCode("0086");
				entity.setEnabled(true);
				String areaLevelZhName = "";
				if (entity.getAreaLevel() == 1) {
					areaLevelZhName = "省级";
				} else if (entity.getAreaLevel() == 2) {
					areaLevelZhName = "市级";
				} else if (entity.getAreaLevel() == 3) {
					areaLevelZhName = "区县级";
				}
				entity.setRemark(areaLevelZhName);
			}
			manager.saveAreaInfo(entity);
			Struts2Utils.renderText(SUCCESS);
		} catch (Exception e) {
			logger.error("保存单个地区信息", e);
		}
		return null;
	}

	@Override
	public String delete() {
		try {
			manager.deleteAreaInfo(id);
			Struts2Utils.renderText(SUCCESS);
		} catch (Exception e) {
			logger.error("删除单个地区信息", e);
		}
		return null;
	}

	@Override
	public String list() {
		try {
			List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
			PropertyFilterUtils.handleFilter(page, AreaInfo.class, filters);

			page = manager.searchProperty(page, filters);
		} catch (Exception e) {
			logger.error("获取地区信息列表： ", e);
		}
		return JSON;
	}

	/**
	 * 重载地区信息
	 * @return
	 */
	public String reload() {
		try {
			service.reload();
			logger.info("重载地区信息成功！");
			Struts2Utils.renderText(SUCCESS);
		} catch (Exception e) {
			logger.error("重载地区信息出错！", e);
			Struts2Utils.renderText("重载失败！");
		}
		return null;
	}
	
	/**
	 * 检查合作渠道单位名称是否重复
	 * @return
	 */
	public String checkRepeat() {
		try {
			boolean existArea = manager.existArea(newId, parentAreaId, areaName);
			Struts2Utils.renderText(String.valueOf(!existArea));
		} catch (ServiceException e) {
			Struts2Utils.renderText("false");
		} catch (Exception e) {
			logger.error("检查地区信息是否重复：", e);
		}
		return null;
	}

	// -- 页面属性访问函数 --//

	/**
	 * list页面显示用户分页列表.
	 */
	public Page<AreaInfo> getPage() {
		return page;
	}

	public void setNewId(String newId) {
		this.newId = newId;
	}

	public void setParentAreaId(Long parentAreaId) {
		this.parentAreaId = parentAreaId;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

}
