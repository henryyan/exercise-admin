package com.runchain.exercise.admin.service.field.tennis;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import com.runchain.arch.service.ServiceException;
import com.runchain.exercise.admin.dao.field.tennis.TennisDao;
import com.runchain.exercise.admin.entity.field.Tennis;
import com.runchain.exercise.admin.service.field.IFieldManager;

/**
 * 网球实体管理实现类.
 *
 * @author HenryYan
 *
 */
@Component
@Transactional
public class TennisManager extends IFieldManager<Tennis> {

	@Resource TennisDao entityDao;

	public void saveField(Tennis entity) {
		entityDao.save(entity);
	}
	
	public void addBatchField(Tennis entity, Integer number) throws ServiceException {
		try {
			for (int i = 0; i < number; i++) {
				Tennis newEntity = new Tennis();
				PropertyUtils.copyProperties(newEntity, entity);
				Integer maxFieldNo = entityDao.getMaxFieldNo(entity.getVenueId());
				newEntity.setName(maxFieldNo + "号网球场地");
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
	public Tennis getField(Long id) {
		return entityDao.get(id);
	}
	
	@Transactional(readOnly = true)
	public Page<Tennis> searchField(final Page<Tennis> page, final List<PropertyFilter> filters) {
		return entityDao.findPage(page, filters);
	}
	
	public List<Tennis> findAll(Long venueId) {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("EQL_venueId", venueId.toString()));
		List<Tennis> fields = entityDao.find(filters );
		return fields;
	}
}
