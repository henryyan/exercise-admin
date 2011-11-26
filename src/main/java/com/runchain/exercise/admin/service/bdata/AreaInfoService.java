package com.runchain.exercise.admin.service.bdata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.PropertyFilter;

import com.runchain.exercise.admin.butil.bdata.AreaInfoUtil;
import com.runchain.exercise.admin.dao.bdata.AreaInfoDao;
import com.runchain.exercise.admin.entity.bdata.AreaInfo;
import com.runchain.arch.util.string.HtmlUtil;

/**
 * 地区信息Service
 *
 * @author HenryYan
 *
 */
@Service
@Transactional
public class AreaInfoService {

	private static Logger logger = LoggerFactory.getLogger(AreaInfoService.class);

	@Autowired
	AreaInfoDao areaDao;

	/**
	 * 获取所有地区信息
	 * @return	地区列表
	 */
	@Transactional(readOnly = true)
	public List<AreaInfo> getAllArea() {
		return areaDao.findBy("enabled", true);
	}

	/**
	 * 加载地区信息到内存中
	 */
	@Transactional(readOnly = true)
	public void init() {
		AreaInfoUtil.clean();
		List<AreaInfo> allArea = getAllArea();
		for (AreaInfo areaInfo : allArea) {
			AreaInfoUtil.addArea(areaInfo);
		}
	}

	/**
	 * 获取地区信息列表
	 * @param level 地区级别
	 * @return 地区信息集合
	 */
	@Transactional(readOnly = true)
	public List<AreaInfo> getAreaByLevel(Integer level) {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("EQB_enabled", "true"));
		filters.add(new PropertyFilter("EQI_areaLevel", level.toString()));

		return areaDao.find(filters);
	}

	/**
	 * 从parentName的下级显示，例如设置了”上海市“，则页面显示的是上海市下面的所有区县
	 * @param parentName	父级别的ID
	 * @return 地区信息集合
	 */
	@Transactional(readOnly = true)
	public List<AreaInfo> getArea(String parentName) {
		return areaDao.getAreaByParentName(parentName);
	}

	/**
	 * 获取地区信息列表
	 * @param parentId	父级ID
	 * @return 地区信息集合
	 */
	@Transactional(readOnly = true)
	public List<AreaInfo> getAreaByParent(Long parentId) {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("EQB_enabled", "true"));
		filters.add(new PropertyFilter("EQL_parentAreaId", parentId.toString()));
		return areaDao.find(filters);
	}

	/**
	 * 查询层级ID，比如一个3级地区的ID，返回结果为[顶级ID(0)，1级ID，2级ID]
	 * @param childId	子级ID
	 * @return	如果没有数据返回Long型空数组
	 */
	@Transactional(readOnly = true)
	public Long[] findLayerIds(Long childId) {
		List<Long> listIds = new ArrayList<Long>();
		if (listIds.size() == 0) {
			return new Long[] {0l};
		}

		logger.debug("使用子ID：{}，查询父级ID，结果：{}", childId);
		Long parentAreaInfoId = areaDao.findParentAreaInfoId(childId);
		Long lastId = parentAreaInfoId;

		if (parentAreaInfoId != null) {
			while (parentAreaInfoId != null && parentAreaInfoId > 1) {
				logger.debug("子ID：{}的父ID为：{}，继续查询", lastId, parentAreaInfoId);
				listIds.add(parentAreaInfoId);
				parentAreaInfoId = areaDao.findParentAreaInfoId(parentAreaInfoId);
			}
			listIds.add(0l);
			Long[] ids = new Long[listIds.size()];
			for (int i = 0; i < ids.length; i++) {
				ids[i] = listIds.get(ids.length - i - 1);
			}
			return ids;
		}
		return new Long[] {};
	}

	/**
	 * 根据@childId 生成完整的下拉框HTML代码
	 * @param childId	最小级别的地区ID
	 * @return	select元素的HTML代码
	 */
	@Transactional(readOnly = true)
	public String generateSelectHtmlCode(Long childId) {
		Long[] findLayerIds = findLayerIds(childId);
		String selectHtml = "";

		List<AreaInfo> areaByParent = null;
		Map<String, String> areas = null;
		for (int i = 0; i < findLayerIds.length; i++) {
			areaByParent = getAreaByParent(findLayerIds[i]);
			areas = new HashMap<String, String>();
			for (AreaInfo areaInfo : areaByParent) {
				areas.put(String.valueOf(areaInfo.getId()), areaInfo.getAreaName());
			}

			/*
			 * 设置每一个下拉框的默认值
			 */
			String defaultValue = "";
			if (i + 1 == findLayerIds.length) {
				defaultValue = childId.toString();
			} else {
				defaultValue = findLayerIds[i + 1].toString();
			}
			// 拼接每一个下拉框的HTML代码
			selectHtml += HtmlUtil.generateSelect(areas, false, defaultValue);
		}
		return selectHtml;
	}
	
	/**
	 * 重载地区信息
	 */
	public void reload() {
		init();
	} 

}
