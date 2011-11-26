package com.runchain.exercise.admin.dao.activity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.runchain.exercise.admin.entity.activity.AllFieldActivity;

/**
 * 所有运动项目活动DAO
 *
 * @author HenryYan
 *
 */
@Component
public class AllFieldActivityDao extends HibernateDao<AllFieldActivity, Long> {

	public List<Double> sumProtocolFee(Long venueId, Date startDate, Date endDate) {
		String hql = "select sum(o.fieldOrder.paymentCommision) from AllFieldActivity o where o.venueId=? and o.usableDate>=?"
				+ " and o.fieldOrder.id > 0";
		Object[] objects = new Object[] { venueId, startDate };
		if (endDate != null) {
			hql += " and o.usableDate <=?";
			objects = ArrayUtils.add(objects, endDate);
		}
		List<Double> find = find(hql, objects);
		return find;
	}
	
	public List<Double> sumActivityPrice(Long venueId, Date startDate, Date endDate) {
		String hql = "select sum(o.fieldOrder.standardPrice) from AllFieldActivity o where o.venueId=? and o.usableDate>=?"
				+ " and o.fieldOrder.id > 0 and o.fieldOrder.paymentCommision > 0";
		Object[] objects = new Object[] { venueId, startDate };
		if (endDate != null) {
			hql += " and o.usableDate <=?";
			objects = ArrayUtils.add(objects, endDate);
		}
		List<Double> find = find(hql, objects);
		return find;
	}

	@SuppressWarnings("unchecked")
	public Page<AllFieldActivity> search(final Page<AllFieldActivity> page, final Float paymentCommision, final Long venueId,
			final Date startDate, final Date endDate) {
		/**
		 * 查询条件集合
		 */
		List<Criterion> criterionList = new ArrayList<Criterion>();
		
		/**
		  * 场馆ID
		  */
		Criterion ct = null;
		ct = Restrictions.eq("venueId", venueId);
		criterionList.add(ct);
		
		/**
		  * 活动日期
		  */
		if (startDate != null) {
			ct = Restrictions.ge("usableDate", startDate);
			criterionList.add(ct);
			
			if (endDate != null) {
				ct = Restrictions.le("usableDate", endDate);
				criterionList.add(ct);
			}
		}
		
		/**
		  * 服务费
		  */
		if (paymentCommision != null) {
			ct = Restrictions.gt("fo.paymentCommision", paymentCommision);
			criterionList.add(ct);
		}
		
		Criterion[] vCriterion = criterionList.toArray(new Criterion[criterionList.size()]);

		/**
		 * 关联查询 
		 */
		Criteria criteria = getSession().createCriteria(AllFieldActivity.class);
		criteria.createAlias("fieldOrder", "fo");

		for (Criterion c : vCriterion) {
			criteria.add(c);
		}
		/**
		  * 定义分页
		  */
		if (page.isAutoCount()) {
			long totalCount = countCriteriaResult(criteria);
			page.setTotalCount(totalCount);
		}

		setPageParameterToCriteria(criteria, page);
		List<AllFieldActivity> result = criteria.list();
		page.setResult(result);
		return page;
	}

}
