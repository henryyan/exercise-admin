package com.runchain.exercise.admin.service.field;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import com.runchain.arch.service.ServiceException;

/**
 * 场地管理业务接口，CRUD 
 *
 * @author HenryYan
 *
 */
public abstract class IFieldManager<T> {

	/**
	 * 保存场地
	 * @param entity	实体对象
	 */
	public abstract void saveField(T entity);

	/**
	 * 批量添加场地，根据给定的生成场地的数量循环添加场地，累加设置场地的名称
	 * @param fieldTemplate		实体模板，包含了必要的字段
	 * @param number			要添加的场地数量
	 * @throws ServiceException	根据模板对象克隆时
	 */
	public abstract void addBatchField(T fieldTemplate, Integer number) throws ServiceException;

	/**
	 * 删除场地
	 * @param id	主键ID
	 */
	public abstract void deleteField(Long id);

	/**
	 * 获取场地实体
	 * @param id	主键ID
	 * @return
	 */
	@Transactional(readOnly = true)
	public abstract T getField(Long id);

	/**
	 * 使用属性过滤条件查询实体.
	 */
	@Transactional(readOnly = true)
	public abstract Page<T> searchField(final Page<T> page, final List<PropertyFilter> filters);
	
	/**
	 * 查询所有场地
	 * @param venueId	场馆ID
	 * @return
	 */
	@Transactional(readOnly = true)
	public abstract List<T> findAll(Long venueId);

}