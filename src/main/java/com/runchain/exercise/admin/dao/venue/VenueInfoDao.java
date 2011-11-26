package com.runchain.exercise.admin.dao.venue;

import org.hibernate.Query;
import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.runchain.exercise.admin.entity.venue.VenueInfo;

/**
 * 场馆信息DAO.
 *
 * @author HenryYan
 *
 */
@Component
public class VenueInfoDao extends HibernateDao<VenueInfo, Long> {

	public int updateVenueVerifyStatus(Long[] venueIds, Boolean status) {
		String hql = "update VenueInfo set verification = ? where id in(:ids)";
		Query query = super.getSession()
			.createQuery(hql)
			.setBoolean(0, status)
			.setParameterList("ids", venueIds);
		int executeUpdate = query.executeUpdate();
		return executeUpdate;
	}
	
}
