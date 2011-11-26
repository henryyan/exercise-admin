package com.runchain.exercise.admin.service.field.football;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import com.runchain.arch.service.ServiceException;
import com.runchain.exercise.admin.dao.field.football.FootballDao;
import com.runchain.exercise.admin.entity.field.Football;
import com.runchain.exercise.admin.service.field.IFieldManager;

/**
 * 足球实体管理实现类.
 *
 * @author HenryYan
 *
 */
@Component
@Transactional
public class FootballManager extends IFieldManager<Football> {

	@Resource FootballDao entityDao;

	public void saveField(Football entity) {
		entityDao.save(entity);
	}
	
	public void addBatchField(Football entity, Integer number) throws ServiceException {
		try {
			for (int i = 0; i < number; i++) {
				Football newEntity = new Football();
				PropertyUtils.copyProperties(newEntity, entity);
				Integer maxFieldNo = entityDao.getMaxFieldNo(entity.getVenueId());
				newEntity.setName(maxFieldNo + "号足球场地");
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
	public Football getField(Long id) {
		return entityDao.get(id);
	}
	
	@Transactional(readOnly = true)
	public Page<Football> searchField(final Page<Football> page, final List<PropertyFilter> filters) {
		return entityDao.findPage(page, filters);
	}
	
	public List<Football> findAll(Long venueId) {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("EQL_venueId", venueId.toString()));
		List<Football> fields = entityDao.find(filters );
		return fields;
	}
	
}
