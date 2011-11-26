package com.runchain.exercise.admin.service.field.type;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import com.runchain.arch.service.ServiceException;
import com.runchain.arch.util.orm.PropertyFilterUtils;
import com.runchain.exercise.admin.dao.field.type.FieldTypeDao;
import com.runchain.exercise.admin.entity.field.type.FieldType;

/**
 * 场地类型实体管理类.
 *
 * @author HenryYan
 *
 */
@Component
@Transactional
public class FieldTypeManager {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	protected FieldTypeDao entityDao;
	
	/**
	 * 保存场地类型
	 * @param entity	实体对象
	 */
	public void saveFieldType(FieldType entity) {
		entityDao.save(entity);
	}

	/**
	 * 删除场地类型
	 * @param id	主键ID
	 */
	public void deleteFieldType(Long id) {
		entityDao.delete(id);
	}

	/**
	 * 获取场地类型实体
	 * @param id	主键ID
	 * @return
	 */
	@Transactional(readOnly = true)
	public FieldType getFieldType(Long id) {
		return entityDao.get(id);
	}

	/**
	 * 使用属性过滤条件查询实体.
	 */
	@Transactional(readOnly = true)
	public Page<FieldType> searchFieldType(Page<FieldType> page, List<PropertyFilter> filters, String[] fields) {
		List<Object> values = new ArrayList<Object>();
		String hql = PropertyFilterUtils.createHqlForCustomFields(page, filters, FieldType.class, fields, values);
		return entityDao.findPage(page, hql, values.toArray());
	}

	/**
	 * 获取系统支持的场地类型
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<FieldType> getEnableFieldType() {
		return entityDao.findBy("enable", true);
	}
	
	/**
	 * 更改场地启用状态
	 * @param id		类型ID
	 * @param status	状态true or false
	 * @throws ServiceException
	 */
	public void changeEnable(Long id, Boolean status) throws ServiceException {
		entityDao.batchExecute("update FieldType set enable=? where id=?", new Object[] {status, id});
		logger.debug("设置场地：{}，状态为：{}", id, status);
	}

}
