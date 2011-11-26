package com.runchain.exercise.admin.service.common;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import com.runchain.exercise.admin.dao.common.SystemPropertyDao;
import com.runchain.exercise.admin.entity.common.SystemProperty;

/**
 * 系统属性管理类.
 *
 * @author HenryYan
 *
 */
@Component
@Transactional
public class SystemPropertyManager {

	@Resource
	private SystemPropertyDao systemDao;
	
	/**
	 * 添加属性
	 * @param entity
	 */
	public void saveProperty(SystemProperty entity) {
		systemDao.save(entity);
	}
	
	public void deleteProperty(Long id) {
		// TODO 管理员权限验证
		systemDao.delete(id);
	}
	
	@Transactional(readOnly = true)
	public SystemProperty getProperty(Long id) {
		return systemDao.get(id);
	}
	
	@Transactional(readOnly = true)
	public List<SystemProperty> findPropertyByKey(String... keys) {
		List<SystemProperty> result = systemDao.find(Restrictions.in("propKey", keys));
		return result;
	}
	
	/**
	 * 使用属性过滤条件查询属性.
	 */
	@Transactional(readOnly = true)
	public Page<SystemProperty> searchProperty(final Page<SystemProperty> page, final List<PropertyFilter> filters) {
		return systemDao.findPage(page, filters);
	}
	
	/**
	 * 检查属性名称是否唯一.
	 *
	 * @return PropName在数据库中唯一或等于oldPropName时返回true.
	 */
	@Transactional(readOnly = true)
	public boolean isPropNameUnique(String newPropName, String oldPropName) {
		return systemDao.isPropertyUnique("propName", newPropName, oldPropName);
	}
	
	
}
