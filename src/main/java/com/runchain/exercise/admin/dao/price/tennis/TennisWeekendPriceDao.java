package com.runchain.exercise.admin.dao.price.tennis;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.runchain.exercise.admin.dao.price.IPriceDao;
import com.runchain.exercise.admin.entity.price.tennis.TennisWeekendPrice;

/**
 * 价格DAO：网球-周末
 *
 * @author HenryYan
 *
 */
@Component
public class TennisWeekendPriceDao extends HibernateDao<TennisWeekendPrice, Long> implements IPriceDao {

	public int deleteAllPrice(Long venueId) {
		String hql = "delete TennisWeekendPrice where venueId=?";
		int batchExecute = super.batchExecute(hql, venueId);
		return batchExecute;
	}
	
}
