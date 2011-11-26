package com.runchain.exercise.admin.dao.tactics;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.runchain.exercise.admin.entity.tactics.TacticsDate;

/**
 * 特殊策略日期DAO
 *
 * @author HenryYan
 *
 */
@Component
public class TacticsDateDao extends HibernateDao<TacticsDate, Long> {
	
}
