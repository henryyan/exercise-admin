package com.runchain.exercise.admin.service.member;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.PropertyFilter;

import com.runchain.exercise.admin.dao.member.MemberCardTypeDao;
import com.runchain.exercise.admin.entity.member.MemberCardType;

/**
 * 会员卡类型实体管理类.
 *
 * @author HenryYan
 *
 */
@Component
@Transactional
public class MemberCardTypeManager {

	@Resource
	MemberCardTypeDao typeDao;
	
	/**
	 * 获取所有会员卡类型
	 * @param venueId	场馆ID
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<MemberCardType> findAllTypes(String venueId) {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("EQL_venueId", venueId));
		List<MemberCardType> types = typeDao.find(filters);
		return types;
	}
	
	public MemberCardType getCardType(Long id) {
		return typeDao.get(id);
	}
	
	public void saveCardType(MemberCardType cardType) {
		typeDao.save(cardType);
	}
	
	public void deleteCardType(Long id) {
		typeDao.delete(id);
	}

}
