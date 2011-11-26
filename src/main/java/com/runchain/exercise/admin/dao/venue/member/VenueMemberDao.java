package com.runchain.exercise.admin.dao.venue.member;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.runchain.exercise.admin.entity.venue.member.VenueMemberInfo;

/**
 * 场馆会员卡DAO
 *
 * @author HenryYan
 *
 */
@Component
public class VenueMemberDao extends HibernateDao<VenueMemberInfo, Long>{

}
