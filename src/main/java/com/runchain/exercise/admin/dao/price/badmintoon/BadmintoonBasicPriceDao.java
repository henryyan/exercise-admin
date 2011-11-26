package com.runchain.exercise.admin.dao.price.badmintoon;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.runchain.exercise.admin.dao.price.IPriceDao;
import com.runchain.exercise.admin.entity.price.badmintoon.BadmintoonBasicPrice;

/**
 * 价格DAO：羽毛球-非周末
 *
 * @author HenryYan
 *
 */
@Component
public class BadmintoonBasicPriceDao extends HibernateDao<BadmintoonBasicPrice, Long> implements IPriceDao {

	public int deleteAllPrice(Long venueId) {
		String hql = "delete BadmintoonBasicPrice where venueId=?";
		int batchExecute = super.batchExecute(hql, venueId);
		return batchExecute;
	}

}
