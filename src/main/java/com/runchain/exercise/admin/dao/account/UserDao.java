package com.runchain.exercise.admin.dao.account;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.runchain.exercise.admin.entity.account.User;

/**
 * 用户对象的泛型DAO类.
 * 
 * @author HenryYan
 */
@Component
public class UserDao extends HibernateDao<User, Long> {
}
