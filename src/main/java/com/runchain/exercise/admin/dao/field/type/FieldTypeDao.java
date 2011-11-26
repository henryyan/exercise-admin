package com.runchain.exercise.admin.dao.field.type;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.runchain.exercise.admin.entity.field.type.FieldType;

/**
 * 场地类型DAO
 *
 * @author HenryYan
 *
 */
@Component
public class FieldTypeDao extends HibernateDao<FieldType, Long> {
	
}
