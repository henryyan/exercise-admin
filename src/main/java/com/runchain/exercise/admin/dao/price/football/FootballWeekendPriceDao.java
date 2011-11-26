package com.runchain.exercise.admin.dao.price.football;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.runchain.exercise.admin.dao.price.IPriceDao;
import com.runchain.exercise.admin.entity.price.football.FootballWeekendPrice;

/**
 * 价格DAO：足球-周末
 *
 * @author HenryYan
 *
 */
@Component
public class FootballWeekendPriceDao extends HibernateDao<FootballWeekendPrice, Long> implements IPriceDao {

	public int deleteAllPrice(Long venueId) {
		String hql = "delete FootballWeekendPrice where venueId=?";
		int batchExecute = super.batchExecute(hql, venueId);
		return batchExecute;
	}
	
}
