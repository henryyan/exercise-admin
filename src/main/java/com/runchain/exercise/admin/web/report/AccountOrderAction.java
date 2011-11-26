package com.runchain.exercise.admin.web.report;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.utils.reflection.ConvertUtils;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import com.runchain.arch.util.orm.PropertyFilterUtils;
import com.runchain.arch.web.base.BaseActionSupport;
import com.runchain.exercise.admin.butil.venue.VenueInfoUtil;
import com.runchain.exercise.admin.entity.order.AccountOrder;
import com.runchain.exercise.admin.entity.venue.VenueInfo;
import com.runchain.exercise.admin.service.field.order.FieldOrderItemManager;
import com.runchain.exercise.admin.service.report.AccountOrderManager;

/**
 * 付款订单Action
 *
 * @author HenryYan
 *
 */
public class AccountOrderAction extends BaseActionSupport {

	private static final long serialVersionUID = 1L;

	private Page<AccountOrder> page = new Page<AccountOrder>();

	@Autowired
	AccountOrderManager manager;

	@Autowired
	FieldOrderItemManager fieldOrderItemManager;

	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception {
		try {
			List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
			PropertyFilterUtils.handleFilter(page, AccountOrder.class, filters);

			VenueInfo venueInfo = VenueInfoUtil.getVenueFromSession();
			filters.add(new PropertyFilter("EQL_venueId", venueInfo.getId().toString()));

			page = manager.searchProperty(page, filters);

			List<AccountOrder> result = page.getResult();
			List<Long> accountOrderIds = ConvertUtils.convertElementPropertyToList(result, "id");
			Map<Long, Integer> orderItemSizeByAccountOrder = fieldOrderItemManager
					.countFieldOrderItemSizeByAccountOrder(accountOrderIds);
			for (AccountOrder accountOrder : result) {
				accountOrder.setFieldOrderSize(orderItemSizeByAccountOrder.get(accountOrder.getId()));
			}

		} catch (Exception e) {
			logger.error("读取[付款订单]列表：", e);
		}
		return JSON;
	}

	public Page<AccountOrder> getPage() {
		return page;
	}

}
