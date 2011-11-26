package com.runchain.exercise.admin.dao.price.badmintoon;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.runchain.exercise.admin.dao.price.IPriceDao;
import com.runchain.exercise.admin.entity.price.badmintoon.BadmintoonWeekendPrice;

/**
 * 价格DAO：羽毛球-周末
 *
 * @author HenryYan
 *
 */
@Component
public class BadmintoonWeekendPriceDao extends HibernateDao<BadmintoonWeekendPrice, Long> implements IPriceDao {

	public int deleteAllPrice(Long venueId) {
		String hql = "delete BadmintoonWeekendPrice where venueId=?";
		int batchExecute = super.batchExecute(hql, venueId);
		return batchExecute;
	}
	
}
