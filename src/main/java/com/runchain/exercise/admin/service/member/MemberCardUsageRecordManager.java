package com.runchain.exercise.admin.service.member;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import com.runchain.exercise.admin.dao.field.order.FieldOrderItemDao;
import com.runchain.exercise.admin.dao.member.MemberCardUsageRecordDao;
import com.runchain.exercise.admin.entity.member.MemberCardUsageRecord;
import com.runchain.exercise.admin.entity.order.FieldOrderItem;

/**
 * 会员卡使用记录实体管理类.
 *
 * @author HenryYan
 *
 */
@Service
public class MemberCardUsageRecordManager {

	@Resource
	MemberCardUsageRecordDao entityDao;

	@Autowired
	FieldOrderItemDao fieldOrderItemDao;

	@Transactional(readOnly = true)
	public Page<MemberCardUsageRecord> searchRecord(final Page<MemberCardUsageRecord> page,
			final List<PropertyFilter> filters) {
		return entityDao.findPage(page, filters);
	}

	/**
	 * 根据会员卡使用记录查询场地活动订单项
	 * @param cardUsageId	会员卡使用记录ID
	 */
	@SuppressWarnings("unchecked")
	public List<FieldOrderItem> findFieldOrderItems(Long cardUsageId) {
		Criteria criteria = fieldOrderItemDao.createCriteria(Restrictions.eq("cardUsageId", cardUsageId));
		criteria.addOrder(Order.asc("bookTime"));
		return criteria.list();
	}

}
