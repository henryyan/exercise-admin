package com.runchain.exercise.admin.dao.tactics;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.runchain.exercise.admin.entity.tactics.Tactics;

/**
 * 策略实体DAO.
 *
 * @author HenryYan
 *
 */
@Component
public class TacticsDao extends HibernateDao<Tactics, Long> {

	/**
	 * 查询特殊策略
	 * @param fieldType	场地类型
	 * @param venueId	场馆ID
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Tactics> getTactics(String fieldType, Long venueId) {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("EQS_fieldType", fieldType));
		filters.add(new PropertyFilter("EQL_venueId", venueId.toString()));
		List<Tactics> find = find(filters);
		return find;
	}
	
}
