package com.runchain.exercise.admin.service.field.badmintoon;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import com.runchain.arch.service.ServiceException;
import com.runchain.exercise.admin.dao.field.badmintoon.BadmintoonDao;
import com.runchain.exercise.admin.entity.field.Badmintoon;
import com.runchain.exercise.admin.service.field.IFieldManager;

/**
 * 羽毛球业务管理类.
 *
 * @author HenryYan
 *
 */
@Component
@Transactional
public class BadmintoonManager extends IFieldManager<Badmintoon> {

	@Resource BadmintoonDao entityDao;

	public void saveField(Badmintoon entity) {
		entityDao.save(entity);
	}
	
	public void addBatchField(Badmintoon entity, Integer number) throws ServiceException {
		try {
			for (int i = 0; i < number; i++) {
				Badmintoon newEntity = new Badmintoon();
				PropertyUtils.copyProperties(newEntity, entity);
				Integer maxFieldNo = entityDao.getMaxFieldNo(entity.getVenueId());
				newEntity.setName(maxFieldNo + "号羽毛球场地");
				newEntity.setFieldCode(maxFieldNo.toString());
				entityDao.save(newEntity);
				entityDao.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}
	
	public void deleteField(Long id) {
		entityDao.delete(id);
	}
	
	@Transactional(readOnly = true)
	public Badmintoon getField(Long id) {
		return entityDao.get(id);
	}
	
	@Transactional(readOnly = true)
	public Page<Badmintoon> searchField(final Page<Badmintoon> page, final List<PropertyFilter> filters) {
		return entityDao.findPage(page, filters);
	}
	
	public List<Badmintoon> findAll(Long venueId) {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("EQL_venueId", venueId.toString()));
		List<Badmintoon> fields = entityDao.find(filters );
		return fields;
	}
	
}
