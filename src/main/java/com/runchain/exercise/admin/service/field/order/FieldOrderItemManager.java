package com.runchain.exercise.admin.service.field.order;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.runchain.arch.orm.dao.BaseHibernateDao;
import com.runchain.arch.service.base.BaseEntityManager;
import com.runchain.exercise.admin.dao.field.order.FieldOrderItemDao;
import com.runchain.exercise.admin.entity.order.FieldOrderItem;

/**
 * 活动订单Manager
 *
 * @author HenryYan
 *
 */
@Service
@Transactional(readOnly = true)
public class FieldOrderItemManager extends BaseEntityManager<FieldOrderItem, Long> {
	
	@Autowired
	FieldOrderItemDao itemDao;
	
	/**
	 * 统计付款列表的活动订单数量
	 * @param accountOrderIds	付款订单ID集合
	 * @return	Map<付款订单ID, 活动订单数量>
	 */
	public Map<Long, Integer> countFieldOrderItemSizeByAccountOrder(Collection<Long> accountOrderIds) {
		return itemDao.countFieldOrderItemSizeByAccountOrder(accountOrderIds);
	}

	@Override
	public BaseHibernateDao<FieldOrderItem, Long> getDao() {
		return itemDao;
	}

}
