package com.runchain.exercise.admin.service.member;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import com.runchain.exercise.admin.dao.member.MemberCardDao;
import com.runchain.exercise.admin.entity.member.MemberCard;

/**
 * 会员卡实体管理类.
 *
 * @author HenryYan
 *
 */
@Component
public class MemberCardManager {

	@Resource
	MemberCardDao entityDao;

	@Transactional(readOnly = true)
	public Page<MemberCard> searchCard(final Page<MemberCard> page, final List<PropertyFilter> filters) {
		return entityDao.findPage(page, filters);
	}

}
