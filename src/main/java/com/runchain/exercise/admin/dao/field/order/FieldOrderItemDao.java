package com.runchain.exercise.admin.dao.field.order;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.runchain.arch.orm.dao.BaseHibernateDao;
import com.runchain.exercise.admin.entity.order.FieldOrderItem;

/**
 * 场地活动订单项Dao
 *
 * @author HenryYan
 *
 */
@Repository
public class FieldOrderItemDao extends BaseHibernateDao<FieldOrderItem, Long> {

	/**
	 * 统计付款列表的活动订单数量
	 * @param accountOrderIds	付款订单ID集合
	 * @return	Map<付款订单ID, 活动订单数量>
	 */
	@SuppressWarnings("unchecked")
	public Map<Long, Integer> countFieldOrderItemSizeByAccountOrder(Collection<Long> accountOrderIds) {
		String sql = "select tao.id, (select count(tfo.id) from t_field_order tfo where tfo.account_order_id = tao.id) ct from t_account_order tao"
				+ " where tao.id in (:ids) and tao.payment_status = '1'";
		Query query = super.getSession().createSQLQuery(sql).setParameterList("ids", accountOrderIds);
		List<Object[]> list = query.list();
		Map<Long, Integer> result = new HashMap<Long, Integer>();
		if (list.isEmpty()) {
			return result;
		}
		for (Object[] objects : list) {
			Long accountOrderId = Long.valueOf(objects[0].toString());
			Integer fieldOrderSize = Integer.valueOf(objects[1].toString());
			result.put(accountOrderId, fieldOrderSize);
		}
		return result;
	}

}
