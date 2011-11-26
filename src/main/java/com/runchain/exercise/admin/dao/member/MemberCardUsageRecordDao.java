package com.runchain.exercise.admin.dao.member;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.runchain.exercise.admin.entity.member.MemberCardUsageRecord;

/**
 * 会员卡使用记录DAO.
 *
 * @author HenryYan
 *
 */
@Component
public class MemberCardUsageRecordDao extends HibernateDao<MemberCardUsageRecord, Long> {

}
