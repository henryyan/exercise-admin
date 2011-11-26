package com.runchain.exercise.admin.dao.tactics;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.runchain.exercise.admin.entity.tactics.TacticsPrice;

/**
 * 特殊策略价格DAO
 *
 * @author HenryYan
 *
 */
@Component
public class TacticsPriceDao extends HibernateDao<TacticsPrice, Long> {
	
	public int deleteAllPrice(Long venueId) {
		String hql = "delete TacticsPrice where venueId=?";
		int batchExecute = super.batchExecute(hql, venueId);
		return batchExecute;
	}
	
}
