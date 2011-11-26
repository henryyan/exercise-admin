package com.runchain.exercise.admin.service.tactics;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.PropertyFilter;

import com.runchain.exercise.admin.dao.tactics.TacticsDao;
import com.runchain.exercise.admin.entity.tactics.Tactics;

/**
 * 策略实体管理类.
 *
 * @author HenryYan
 *
 */
@Component
@Transactional
public class TacticsManager {

	@Resource TacticsDao entityDao;
	
	public void deleteTactics(Long id) {
		entityDao.delete(id);
	}
	
	public void saveTactics(Tactics entity) {
		entityDao.save(entity);
	}
	
	@Transactional(readOnly = true)
	public Tactics getTactics(Long id) {
		return entityDao.get(id);
	}
	
	@Transactional(readOnly = true)
	public List<Tactics> findAllTacticses(Long venueId) {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("EQL_venueId", venueId.toString()));
		List<Tactics> all = entityDao.find(filters);
		return all;
	}
	
}
