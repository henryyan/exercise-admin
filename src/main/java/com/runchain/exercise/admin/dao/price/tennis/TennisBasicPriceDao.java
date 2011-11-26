package com.runchain.exercise.admin.dao.price.tennis;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.runchain.exercise.admin.dao.price.IPriceDao;
import com.runchain.exercise.admin.entity.price.tennis.TennisBasicPrice;

/**
 * 价格DAO：网球-非周末
 *
 * @author HenryYan
 *
 */
@Component
public class TennisBasicPriceDao extends HibernateDao<TennisBasicPrice, Long> implements IPriceDao {

	public int deleteAllPrice(Long venueId) {
		String hql = "delete TennisBasicPrice where venueId=?";
		int batchExecute = super.batchExecute(hql, venueId);
		return batchExecute;
	}
	
}
