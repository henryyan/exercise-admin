package com.runchain.exercise.admin.web.activity;

import java.sql.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.Hibernate;
import org.springside.modules.orm.Page;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import com.runchain.arch.util.date.DateUtil;
import com.runchain.arch.web.base.CrudActionSupport;
import com.runchain.arch.web.base.JqGridCrudActionSupport;
import com.runchain.exercise.admin.butil.venue.VenueInfoUtil;
import com.runchain.exercise.admin.entity.activity.AllFieldActivity;
import com.runchain.exercise.admin.entity.venue.VenueInfo;
import com.runchain.exercise.admin.service.activity.AllFieldActivityManager;

/**
 * 运动项目活动查询Action
 *
 * @author HenryYan
 *
 */
@Namespace("/report")
@Action("allActivity")
@Results( { @Result(name = CrudActionSupport.JSON, type = CrudActionSupport.JSON) })
public class AllFieldActivityAction extends JqGridCrudActionSupport<AllFieldActivity> {

	private static final long serialVersionUID = 1L;

	@Resource
	AllFieldActivityManager activityManager;

	private String startDate;
	private String endDate;
	
	// 活动查询
	private Long accountOrderId;

	@Override
	public String delete() throws Exception {
		return null;
	}

	@Override
	public String list() throws Exception {
		try {
			VenueInfo venueInfo = VenueInfoUtil.getVenueFromSession();
			
			if (StringUtils.isEmpty(startDate)) {
				startDate = DateUtil.getSysdate();
			}
			Date dStartDate = Date.valueOf(startDate);
			
			Date dEndDate = null;
			if (StringUtils.isNotEmpty(endDate)) {
				dEndDate = Date.valueOf(endDate);
			}
			
			activityManager.searchActivity(page, 0f, venueInfo.getId(), dStartDate, dEndDate);
		} catch (Exception e) {
			logger.error("获取协议服务费订单列表:", e);
		}
		return JSON;
	}
	
	/**
	 * 根据付款订单查询活动列表
	 * @return
	 * @throws Exception
	 */
	public String listByAccountOrder() throws Exception {
		try {
			List<AllFieldActivity> activities = activityManager.listByAccountOrder(accountOrderId);
			for (AllFieldActivity allFieldActivity : activities) {
				Hibernate.initialize(allFieldActivity.getFieldOrder());
			}
			Struts2Utils.renderJson(activities);
		} catch (Exception e) {
			logger.error("根据付款订单查询活动列表:", e);
		}
		return null;
	}

	/**
	 * 统计协议费
	 * @return
	 */
	public String sumProtocolFee() {
		try {
			VenueInfo venueInfo = VenueInfoUtil.getVenueFromSession();
			Date dStartDate = java.sql.Date.valueOf(startDate == null ? DateUtil.getSysdate() : startDate);
			Date dEndDate = null;
			if (StringUtils.isNotEmpty(endDate)) {
				dEndDate = Date.valueOf(endDate);
			}
			List<Double> sumProtocolFee = activityManager.sumProtocolFee(venueInfo.getId(), dStartDate, dEndDate);
			String result = sumProtocolFee.get(0) == null ? "0" : sumProtocolFee.get(0).toString();
			Struts2Utils.renderText(result);
		} catch (Exception e) {
			Struts2Utils.renderText("false");
			logger.error("统计协议服务费", e);
		}
		return null;
	}
	
	/**
	 * 统计活动价格
	 * @return
	 */
	public String sumActivityPrice() {
		try {
			VenueInfo venueInfo = VenueInfoUtil.getVenueFromSession();
			Date dStartDate = java.sql.Date.valueOf(startDate == null ? DateUtil.getSysdate() : startDate);
			Date dEndDate = null;
			if (StringUtils.isNotEmpty(endDate)) {
				dEndDate = Date.valueOf(endDate);
			}
			List<Double> sumProtocolFee = activityManager.sumActivityPrice(venueInfo.getId(), dStartDate, dEndDate);
			String result = sumProtocolFee.get(0) == null ? "0" : sumProtocolFee.get(0).toString();
			Struts2Utils.renderText(result);
		} catch (Exception e) {
			Struts2Utils.renderText("false");
			logger.error("统计活动原始价格", e);
		}
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
	}

	@Override
	public String save() throws Exception {
		return null;
	}

	@Override
	public Page<AllFieldActivity> getPage() {
		return page;
	}

	public AllFieldActivity getModel() {
		return entity;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public void setAccountOrderId(Long accountOrderId) {
		this.accountOrderId = accountOrderId;
	}

}
