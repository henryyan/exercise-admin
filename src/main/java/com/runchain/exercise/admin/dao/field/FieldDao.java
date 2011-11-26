package com.runchain.exercise.admin.dao.field;

import org.springside.modules.orm.hibernate.HibernateDao;


/**
 * 场地类型DAO抽象类
 *
 * @author HenryYan
 *
 */
public abstract class FieldDao<T> extends HibernateDao<T, Long> {

	/**
	 * 获得最大场地编号
	 * @param venueId	场馆ID
	 * @return	如果还没有属于场馆的场地返回1，否则返回场馆拥有场地的总个数加一
	 */
	public abstract Integer getMaxFieldNo(Long venueId);
	
}
