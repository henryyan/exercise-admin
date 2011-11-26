package com.runchain.exercise.admin.dao.bdata;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.runchain.exercise.admin.entity.bdata.Datalibrary;

/**
 * 数据字典 对象泛型Dao
 * 
 * @author fangjian
 * 
 */
@Component
public class DatalibraryDao extends HibernateDao<Datalibrary, Long> {

}
