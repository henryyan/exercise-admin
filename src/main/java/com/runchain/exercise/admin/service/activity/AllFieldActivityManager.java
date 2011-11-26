package com.runchain.exercise.admin.service.activity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import com.runchain.exercise.admin.dao.activity.AllFieldActivityDao;
import com.runchain.exercise.admin.dao.field.order.FieldOrderItemDao;
import com.runchain.exercise.admin.entity.activity.AllFieldActivity;

/**
 * 运动项目活动查询管理类.
 *
 * @author HenryYan
 *
 */
@Component
@Transactional(readOnly = true)
public class AllFieldActivityManager {

	@Resource
	AllFieldActivityDao entityDao;
	
	@Autowired
	FieldOrderItemDao fieldOrderItemDao;

	/**
	 * 使用属性过滤条件查询实体.
	 */
	@Transactional(readOnly = true)
	public Page<AllFieldActivity> searchActivity(final Page<AllFieldActivity> page, final List<PropertyFilter> filters) {
		return entityDao.findPage(page, filters);
	}

	/**
	 * 使用属性过滤条件查询实体.
	 */
	@Transactional(readOnly = true)
	public Page<AllFieldActivity> searchActivity(final Page<AllFieldActivity> page, final Float paymentCommision,
			final Long venueId, final Date startDate, final Date endDate) {
		return entityDao.search(page, paymentCommision, venueId, startDate, endDate);
	}

	/**
	 * 统计协议费
	 * @param venueId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Double> sumProtocolFee(Long venueId, Date startDate, Date endDate) {
		return entityDao.sumProtocolFee(venueId, startDate, endDate);
	}

	/**
	 * 统计活动费
	 * @param venueId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Double> sumActivityPrice(Long venueId, Date startDate, Date endDate) {
		return entityDao.sumActivityPrice(venueId, startDate, endDate);
	}

	/**
	 * 查询活动对象
	 * @param activityId	活动ID
	 * @param fieldType		场地类型
	 * @return
	 */
	@Transactional(readOnly = true)
	public AllFieldActivity findActivity(Long activityId, String fieldType) {
		Criteria criteria = entityDao.createCriteria(Restrictions.eq("id", activityId),
				Restrictions.eq("fieldType", fieldType));
		Object uniqueResult = criteria.uniqueResult();
		return uniqueResult == null ? null : (AllFieldActivity)uniqueResult;
	}

	/**
	 * 根据付款订单查询活动列表
	 * @param accountOrderId	付款订单ID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<AllFieldActivity> listByAccountOrder(Long accountOrderId) {
		// 1、根据付款订单ID查询管理的活动订单
		Criteria criteria = fieldOrderItemDao.createCriteria(Restrictions.eq("accountOrderId", accountOrderId));
		criteria.add(Restrictions.eq("accountOrderId", accountOrderId));
		ProjectionList projection = Projections.projectionList();
		projection.add(Projections.property("id"));
		List<Long> fieldOrderIdList = criteria.setProjection(projection).list();
		if (fieldOrderIdList.isEmpty()) {
			return new ArrayList<AllFieldActivity>();
		}
		
		criteria = entityDao.createCriteria();
		criteria.add(Restrictions.in("fieldOrder.id", fieldOrderIdList));
		return criteria.list();
	}

}
