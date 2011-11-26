package com.runchain.exercise.admin.service.bdata;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.security.springsecurity.SpringSecurityUtils;

import com.runchain.exercise.admin.butil.bdata.DataLibraryUtil;
import com.runchain.exercise.admin.dao.bdata.DatalibraryDao;
import com.runchain.exercise.admin.entity.bdata.Datalibrary;

/**
 * 
 * 数据字典管理类，包含添加，删除，修改，查询，条件查询
 * 
 * @author fangjian
 * 
 */
@Service
@Transactional
public class DatalibraryManager implements Serializable {

	private static final long serialVersionUID = 1L;

	private static Logger logger = LoggerFactory.getLogger(DatalibraryManager.class);

	@Autowired
	private DatalibraryDao datalibraryDao;

	/**
	 * 根据id获取某个数据字典
	 * 
	 * @param id
	 *            数据字典id
	 * @return
	 */
	public Datalibrary getDatalibrary(Long id) {
		return datalibraryDao.get(id);
	}

	/**
	 * @return 获取所有数据字典集合
	 */
	@Transactional(readOnly = true)
	public List<Datalibrary> getAllDatalibrarys() {
		return datalibraryDao.getAll();
	}

	/**
	 * 添加数据字典
	 * 
	 * @param entity
	 */
	public void saveDatalibrary(Datalibrary entity) {
		datalibraryDao.save(entity);
		DataLibraryUtil.addDatalibrary(entity);
	}

	/**
	 * 删除标识为id值的数据字典
	 * 
	 * @param id
	 *            数据字典标识
	 */
	public void deleteDatalibrary(Long id) {
		Datalibrary datalibrary = datalibraryDao.get(id);
		logger.info("id:{}数据字典被用户{}删除!", new Object[] { id, SpringSecurityUtils.getCurrentUserName() });
		datalibraryDao.delete(id);
		DataLibraryUtil.deleteDatalibrary(datalibrary.getLibrarycode());
	}

	/**
	 * 使用属性过滤条件查询属性.
	 */
	@Transactional(readOnly = true)
	public Page<Datalibrary> searchProperty(final Page<Datalibrary> page, final List<PropertyFilter> filters) {
		return datalibraryDao.findPage(page, filters);
	}

	/**
	 * 验证规则名称的唯一性， 存在返回false,不存在返回true
	 */
	public boolean existLibrarycode(String id, String librarycode) {
		Datalibrary datalibrary = datalibraryDao.findUniqueBy("librarycode", librarycode);
		// 添加时无id时，做唯一验证
		if (StringUtils.isEmpty(id) || "_empty".equals(id)) {
			if (datalibrary != null) {
				return true;
			} else {
				return false;
			}
		} else {
			// 修改时id已存在，做唯一验证
			if (datalibrary == null) {
				return false;
			} else {
				// 当对象存在时，判断原id与新id值是否一致
				Long newId = Long.valueOf(id);
				Long oldId = datalibrary.getId();
				if (oldId.equals(newId)) {
					return false;
				} else {
					return true;
				}
			}
		}
	}

}
