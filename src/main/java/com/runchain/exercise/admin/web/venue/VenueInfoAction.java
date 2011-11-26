package com.runchain.exercise.admin.web.venue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import com.runchain.arch.web.base.CrudActionSupport;
import com.runchain.arch.web.base.JqGridCrudActionSupport;
import com.runchain.exercise.admin.butil.venue.VenueInfoUtil;
import com.runchain.exercise.admin.entity.common.AutoComplete;
import com.runchain.exercise.admin.entity.venue.VenueInfo;
import com.runchain.exercise.admin.service.venue.VenueInfoManager;
import com.runchain.arch.util.number.LongUtils;
import com.runchain.arch.util.orm.PropertyFilterUtils;

/**
 * 场馆信息Action.
 *
 * @author HenryYan
 *
 */
@Namespace("/venue")
@Action(value = "venue")
@Results( { @Result(name = CrudActionSupport.JSON, type = CrudActionSupport.JSON) })
public class VenueInfoAction extends JqGridCrudActionSupport<VenueInfo> {

	private static final long serialVersionUID = 1L;

	@Resource
	protected VenueInfoManager venueInfoManager;
	
	private String ids;
	private String[] allFields = new String[] { "id", "adress", "area", "authenticode", "cell", "city", "closeTime",
			"contact", "district", "email", "fax", "intraduction", "openTime", "phone", "photoUrl", "sendSms",
			"venueName", "verification", "zipcode", "isProtocol" };

	//-- ModelDriven 与 Preparable函数 --//

	public VenueInfo getModel() {
		return entity;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			entity = venueInfoManager.getVenueInfo(id);
		} else {
			entity = new VenueInfo();
		}
	}

	//-- CRUD Action 函数 --//
	@Override
	public String save() throws Exception {
		try {
			venueInfoManager.saveVenueInfo(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String delete() throws Exception {
		try {
			VenueInfo venueInfo = venueInfoManager.getVenueInfo(id);
			venueInfoManager.deleteVenueInfo(id);
			logger.info("管理员：{}，删除场馆信息：{}", VenueInfoUtil.getVenueFromSession(), venueInfo.getVenueName());
		} catch (Exception e) {
			logger.error("删除场馆信息出错：", e);
		}
		return null;
	}

	@Override
	public String list() throws Exception {
		List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
		PropertyFilterUtils.handleFilter(page, VenueInfo.class, filters);
		
		page = venueInfoManager.searchProperty(page, filters, allFields);

		return JSON;
	}

	//-- 其他Action函数 --//
	/**
	 * 验证场馆
	 */
	public String verifyVenue() {
		try {
			Long[] venueIds = LongUtils.convertArray(ids);
			int count = venueInfoManager.verifyVenue(venueIds, true);
			logger.debug("成功验证{}个场馆，ID={}", count, ids);
			Struts2Utils.renderText("true");
		} catch (Exception e) {
			logger.error("验证场馆，ID=" + ids, e);
			Struts2Utils.renderText("false");
		}

		return null;
	}
	
	/**
	 * 取消验证场馆
	 */
	public String unVerifyVenue() {
		try {
			Long[] venueIds = LongUtils.convertArray(ids);
			int count = venueInfoManager.verifyVenue(venueIds, false);
			logger.debug("成功取消验证{}个场馆，ID={}", count, ids);
			Struts2Utils.renderText("true");
		} catch (Exception e) {
			logger.error("取消验证场馆，ID=" + ids, e);
			Struts2Utils.renderText("false");
		}

		return null;
	}

	/**
	 * 自动完成
	 * @return
	 */
	public String autoSearch() {
		try {
			String term = readJson().getString("term");
			System.out.println(term);
			List<AutoComplete> autoSearch = venueInfoManager.autoSearch(term);
			Struts2Utils.renderJson(autoSearch);
		} catch (Exception e) {
			logger.error("自动完成[场馆名称]：", e);
		}
		return null;
	}

	/**
	 * 切换场馆
	 * @return
	 */
	public String changeVenue() {
		try {
			List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
			filters.add(new PropertyFilter("EQL_id", id.toString()));
			VenueInfo venueInfo = venueInfoManager.getVenueInfo(filters, allFields);
			VenueInfoUtil.saveVenue2Session(venueInfo);
			Struts2Utils.renderJson(venueInfo);
		} catch (Exception e) {
			logger.error("切换场馆：msg={}", e.getMessage(), e);
		}

		return null;
	}

	/**
	 * 升级场馆为协议用户
	 * @return
	 */
	public String upgrade() {
		try {
			VenueInfo venueInfo = null;
			String optType = Struts2Utils.getParameter("opt");
			if ("upgrade".equals(optType)) {
				venueInfo = venueInfoManager.upgrade(id);
				VenueInfoUtil.saveVenue2Session(venueInfo);
				logger.info("管理员：" + SpringSecurityUtils.getCurrentUserName() + "[升级]场馆[" + venueInfo + "为协议用户]");
			} else if ("unUpgrade".equals(optType)) {
				venueInfo = venueInfoManager.unUpgrade(id);
				VenueInfoUtil.saveVenue2Session(venueInfo);
				logger.info("管理员：" + SpringSecurityUtils.getCurrentUserName() + "[取消]场馆[" + venueInfo + "的协议]");
			}
			Struts2Utils.renderText("true");
		} catch (Exception e) {
			logger.info("操作失败：管理员：" + SpringSecurityUtils.getCurrentUserName() + "[升级]场馆[" + id + "为协议用户]", e);
			Struts2Utils.renderText("false");
		}
		return null;
	}

	/**
	 * 检测场馆名称是否重复
	 * @return
	 */
	public String checkVenueNameExist() {
		String venueName = Struts2Utils.getParameter("venueName");
		try {
			VenueInfo venueInfo = venueInfoManager.findVenueInfoByVenueName(venueName);
			Map<String, Object> result = new HashMap<String, Object>();
			if (venueInfo == null) {
				result.put("exist", false);
			} else {
				result.put("exist", true);
				result.put("infoId", venueInfo.getId());
			}
			Struts2Utils.renderJson(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//-- 页面属性访问函数 --//
	/**
	 * list页面显示用户分页列表.
	 */
	public Page<VenueInfo> getPage() {
		return page;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}
