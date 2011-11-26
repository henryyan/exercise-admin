package com.runchain.exercise.admin.service.tactics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.PropertyFilter;

import com.runchain.exercise.admin.dao.tactics.TacticsDao;
import com.runchain.exercise.admin.dao.tactics.TacticsDateDao;
import com.runchain.exercise.admin.entity.tactics.Tactics;
import com.runchain.exercise.admin.entity.tactics.TacticsDate;
import com.runchain.arch.util.date.DateUtil;

/**
 * 特殊策略日期实体管理类.
 *
 * @author HenryYan
 *
 */
@Component
@Transactional
public class TacticsDateManager {
	
	@Resource TacticsDateDao dateDao;
	@Resource TacticsDao tacticsDao;

	/**
	 * 查询策略日期
	 * @param venueId
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<TacticsDate> findAllTacticsDate(Long tacticsId) {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("EQL_tactics.id", tacticsId.toString()));
		List<TacticsDate> dates = dateDao.find(filters);
		return dates;
	}

	public void deleteTacticsDate(Long id) {
		dateDao.delete(id);
	}

	@Transactional(readOnly = true)
	public TacticsDate getTacticsDate(Long id) {
		return dateDao.get(id);
	}

	public void saveTacticsDate(TacticsDate entity) {
		dateDao.save(entity);
	}
	
	/**
	 * 检查指定的策略日期是否和本场馆已存在的策略日期重叠
	 * @param venueId		场馆ID
	 * @param tactics		特殊策略
	 * @param tacticsDate	特殊策略日期
	 * @return	<strong>JSON</strong>格式的结果<br/>
	 * 设置重复标志<strong><em>repeat</em></strong>[true, false]，
	 * 为true时可以获取重复的特殊策略名称和日期段
	 * @throws TacticsDateException	日期为空时
	 */
	@Transactional(readOnly = true)
	public JSONObject checkRepeatDates(Long venueId, Tactics tactics, TacticsDate tacticsDate) {
		boolean repeat = false;
		JSONObject result = new JSONObject();

		// 存放策略名称和重复的日期段
		Map<String, List<String[]>> tacticsAndRepeatDates = new HashMap<String, List<String[]>>();

		// 查找本场馆的特殊策略
		List<Tactics> tacticses = tacticsDao.getTactics(tactics.getFieldType(), venueId);

		for (Tactics tobj : tacticses) {

			String tacticsName = tobj.getTacticsName();
			List<TacticsDate> tacticsDates = tobj.getTacticsDates();

			for (TacticsDate td : tacticsDates) {
				boolean fromBetweenDays = DateUtil.betweenDays(td.getFromDate(), td.getToDate(), tacticsDate.getFromDate());
				boolean toBetweenDays = DateUtil.betweenDays(td.getFromDate(), td.getToDate(), tacticsDate.getToDate());
				if (fromBetweenDays || toBetweenDays) {

					// 设置重复标志
					repeat = true;

					/*
					 * 把与之重复的策略放到集合里面
					 */
					if (!tacticsAndRepeatDates.containsKey(tacticsName)) {
						tacticsAndRepeatDates.put(tacticsName, new ArrayList<String[]>());
					}
					// 重复的日期段
					String[] tempRepeatDates = new String[] {DateUtil.format(td.getFromDate(), DateUtil.FORMAT_DATE),
															 DateUtil.format(td.getToDate(), DateUtil.FORMAT_DATE)};
					tacticsAndRepeatDates.get(tacticsName).add(tempRepeatDates);
				}
			}
		}

		/**
		 * 处理结果，如果有重复的日期抛出异常，返回重复的日期段
		 */
		String repeatLabel = "repeat";
		if (repeat) {
			result.accumulate(repeatLabel, true);
			Set<String> keySet = tacticsAndRepeatDates.keySet();
			JSONObject tempJson = new JSONObject();
			for (String key : keySet) {
				if (!tempJson.containsKey(key)) {
					tempJson = new JSONObject();
				}
				tempJson.accumulate("name", key);
				tempJson.accumulate("dates", tacticsAndRepeatDates.get(key));

				result.accumulate("datas", tempJson);
			}
		} else {
			result.accumulate(repeatLabel, false);
		}

		return result;
	}
	
}
