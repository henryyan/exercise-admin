package com.runchain.exercise.admin.dao.field.football;

import java.util.List;

import org.springframework.stereotype.Component;

import com.runchain.exercise.admin.dao.field.FieldDao;
import com.runchain.exercise.admin.entity.field.Football;

/**
 * 足球场地DAO
 *
 * @author HenryYan
 *
 */
@Component
public class FootballDao extends FieldDao<Football> {

	/**
	 * 获得最大场地编号
	 * @param venueId	场馆ID
	 * @return	如果还没有属于场馆的场地返回1，否则返回场馆拥有场地的总个数加一
	 */
	public Integer getMaxFieldNo(Long venueId) {
		String hql = "select count(fieldCode) from Football where venueId=?";
		List<Object> find = find(hql, venueId);
		return find.isEmpty() ? 1 : new Integer(find.get(0).toString()) + 1;
	}

	@Override
	public boolean isPropertyUnique(String propertyName, Object newValue, Object oldValue) {
		return false;
	}
	
}
