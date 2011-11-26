package com.runchain.exercise.admin.dao.member;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.runchain.exercise.admin.entity.member.MemberCardType;

/**
 * 会员卡类型DAO.
 *
 * @author HenryYan
 *
 */
@Component
public class MemberCardTypeDao extends HibernateDao<MemberCardType, Long> {

}
