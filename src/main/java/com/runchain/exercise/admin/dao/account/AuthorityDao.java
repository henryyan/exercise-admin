package com.runchain.exercise.admin.dao.account;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.runchain.exercise.admin.entity.account.Authority;

/**
 * 授权对象的泛型DAO.
 * 
 * @author HenryYan
 */
@Component
public class AuthorityDao extends HibernateDao<Authority, Long> {
}
