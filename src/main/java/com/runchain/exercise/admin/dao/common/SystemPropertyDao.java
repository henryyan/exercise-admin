package com.runchain.exercise.admin.dao.common;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.runchain.exercise.admin.entity.common.SystemProperty;

/**
 * <p><b>Title：</b>系统属性DAO</p>
 * <p><b>Description：</b></p>
 *
 * @author HenryYan
 *
 */
@Component
public class SystemPropertyDao extends HibernateDao<SystemProperty, Long> {

}
