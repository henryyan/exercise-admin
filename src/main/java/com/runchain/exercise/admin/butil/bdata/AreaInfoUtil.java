package com.runchain.exercise.admin.butil.bdata;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import com.runchain.exercise.admin.entity.bdata.AreaInfo;
import com.runchain.arch.util.number.LongUtils;

/**
 * 地区信息工具类
 *
 * @author HenryYan
 *
 */
public class AreaInfoUtil {

	/**
	 * 地区信息最高级别标示
	 */
	public static final Integer TOP_LEVEL = 1;

	/**
	 *	所有地区信息，结构<主键ID, 完整信息>
	 */
	private static Map<Long, AreaInfo> allArea = new HashMap<Long, AreaInfo>(); 
	
	/**
	 *	根据级别分类地区信息集合
	 */
	private static Map<Integer, Map<Long, AreaInfo>> levelMap = new HashMap<Integer, Map<Long, AreaInfo>>();
	
	/**
	 * 保存每个地区ID对应的地区全名，例如：5218-朝阳门街道，则值为：北京市-东城区-朝阳门街道
	 * 通过：{@link #getFullAreaName(Long)}设置此值
	 */
	private static Map<Long, String> areaIdAndFullName = new HashMap<Long, String>();
	
	/**
	 * 清空所有的地区信息
	 */
	public static void clean() {
		allArea.clear();
		levelMap.clear();
		areaIdAndFullName.clear();
	}
	
	/**
	 * 获取所有地区信息
	 * @return	地区列表
	 */
	public static Map<Long, AreaInfo> getAllArea() {
		return allArea;
	}
	
	/**
	 * 获取单个地区信息
	 * @param areaId	地区ID
	 * @return	单个地区信息
	 */
	public static AreaInfo getArea(Long areaId) {
		return allArea.get(areaId);
	}
	
	/**
	 * 获取所有地区信息，根据级别分组
	 * @return	根据级别分组的地区列表
	 */
	public static Map<Integer, Map<Long, AreaInfo>> getLevelAreas() {
		return levelMap;
	}
	
	/**
	 * 添加地区信息
	 * @param area	地区信息
	 */
	public static void addArea(AreaInfo area) {
		Assert.notNull(area, "地区信息不能为空");
		allArea.put(area.getId(), area);
		Map<Long, AreaInfo> singleLevelMap = null;
		if (levelMap.get(area.getAreaLevel()) == null) {
			singleLevelMap = new HashMap<Long, AreaInfo>();
			levelMap.put(area.getAreaLevel(), singleLevelMap);
		}
		levelMap.get(area.getAreaLevel()).put(area.getId(), area);
	}
	
	/**
	 * 从缓存中移除地区信息
	 * @param areaId	地区信息ID
	 */
	public static void removeArea(Long areaId) {
		Assert.notNull(areaId, "地区信息ID不能为空");
		AreaInfo areaInfo = allArea.get(areaId);
		levelMap.get(areaInfo.getAreaLevel()).remove(areaId);
		allArea.remove(areaId);
	}
	
	/**
	 * 获取地区名称
	 * @param areaId	地区ID
	 * @return
	 */
	public static String getSingleName(Long areaId) {
		Assert.notNull(areaId, "地区ID不能为空");
		AreaInfo lastAreaInfo = getAllArea().get(areaId);
		if (lastAreaInfo == null) {
			return StringUtils.EMPTY;
		}
		return lastAreaInfo.getAreaName();
	}
	
	/**
	 * 根据地区ID获取地区名称
	 * @param areaId	地区ID
	 * @return	例如：查询地区ID为5218的信息，返回结果为：北京市-东城区-朝阳门街道
	 * 			<br/>找不到时返回空串{@link StringUtils.EMPTY}
	 */
	public static String getFullAreaName(Long areaId) {
		if (LongUtils.isEmpty(areaId)) {
			return StringUtils.EMPTY;
		}
		AreaInfo lastAreaInfo = getAllArea().get(areaId);
		if (lastAreaInfo == null) {
			return StringUtils.EMPTY;
		}
		
		String fullAreaName = areaIdAndFullName.get(areaId);
		if (fullAreaName != null) {
			return fullAreaName;
		}
		
		/*
		 * 拼接地区全名
		 */
		Integer areaLevel = lastAreaInfo.getAreaLevel();
		if (areaLevel == 3) {
			AreaInfo areaInfo2 = getLevelAreas().get(2).get(lastAreaInfo.getParentAreaId());
			AreaInfo topAreaInfo = getLevelAreas().get(1).get(areaInfo2.getParentAreaId());
			fullAreaName = topAreaInfo.getAreaName() + "-" + areaInfo2.getAreaName() + "-" + lastAreaInfo.getAreaName();
			areaIdAndFullName.put(areaId, fullAreaName);
		} else if (areaLevel == 2) {
			AreaInfo topAreaInfo = getLevelAreas().get(2).get(lastAreaInfo.getParentAreaId());
			fullAreaName = topAreaInfo.getAreaName() + "-" + "-" + lastAreaInfo.getAreaName();
		} else if (areaLevel == 1) {
			fullAreaName = getSingleName(areaId);
		}
		
		return fullAreaName;
	}
	
}
