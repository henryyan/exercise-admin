package com.runchain.exercise.admin.dao.member;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.runchain.exercise.admin.entity.member.MemberCard;

/**
 * 会员卡DAO.
 *
 * @author HenryYan
 *
 */
@Component
public class MemberCardDao extends HibernateDao<MemberCard, Long> {

}
