package com.runchain.exercise.admin.butil.bdata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.runchain.exercise.admin.entity.bdata.Datalibrary;

/**
 * 数据字典工具类
 *
 * @author HenryYan
 *
 */
public class DataLibraryUtil {

	private static Map<String, Datalibrary> allDatas = new HashMap<String, Datalibrary>();
	
	private static Map<String, String> allDataNameValues = new HashMap<String, String>();

	private static Map<String, List<Datalibrary>> groupByType = new HashMap<String, List<Datalibrary>>();
	
	private static Map<String, Map<String, String>> groupByTypeNameValues = new HashMap<String, Map<String, String>>();
	
	/**
	 * 清空数据字典对象
	 */
	public static void clearDatas() {
		allDatas = new HashMap<String, Datalibrary>();
		allDataNameValues = new HashMap<String, String>();
		groupByType = new HashMap<String, List<Datalibrary>>();
		groupByTypeNameValues = new HashMap<String, Map<String, String>>();
	}

	/**
	 * 根据library类型分组添加到Map中
	 * @param datalibrary
	 * @param key
	 */
	public static void addToTypeMap(Datalibrary datalibrary) {
		String librarytype = datalibrary.getLibrarytype();
		List<Datalibrary> list = groupByType.get(librarytype);
		if (list == null) {
			list = new ArrayList<Datalibrary>();
			groupByType.put(librarytype, list);
		}
		list.add(datalibrary);
		
		// 大类
		Map<String, String> map = groupByTypeNameValues.get(librarytype);
		if (map == null) {
			map = new HashMap<String, String>();
			groupByTypeNameValues.put(librarytype, map);
		}
		map.put(datalibrary.getLibrarycode(), datalibrary.getLibraryvalue());
	}

	/**
	 * 从分组的Map中删除
	 * @param code
	 */
	private static void remoteFromTypeMap(String code) {
		Datalibrary datalibrary = allDatas.get(code);
		List<Datalibrary> list = groupByType.get(datalibrary.getLibrarytype());
		Iterator<Datalibrary> iterator = list.iterator();
		while (iterator.hasNext()) {
			if (iterator.next().getLibrarycode().equals(code)) {
				iterator.remove();
			}
		}
	}

	/**
	 * 获取数据字典列表
	 * @param type	字典类型
	 * @return
	 */
	public static List<Datalibrary> getTypeGroupDataLibraryList(String type) {
		return groupByType.get(type);
	}

	/**
	 * 获取数据字典列表
	 * @return	Map<字典code, Datalibrary对象>
	 */
	public synchronized static Map<String, Datalibrary> getDataLibraryList() {
		return allDatas;
	}

	/**
	 * 获取数据字典列表
	 * @return	Map<字典code, 字典中文名称>
	 */
	public static Map<String, String> getAllDataNameValues() {
		return allDataNameValues;
	}

	/**
	 * 根据类型分组的列表
	 * @return	 Map<组名, List<Datalibrary对象>>
	 */
	public synchronized static Map<String, List<Datalibrary>> getTypeGroupDataLibrary() {
		return groupByType;
	}

	/**
	 * 根据类型分组的列表
	 * @return	Map<String, Map<字典code, 字典中文名称>>
	 */
	public static Map<String, Map<String, String>> getGroupByTypeNameValues() {
		return groupByTypeNameValues;
	}

	/**
	 * 通过code获取数组字典
	 * @param code	library code
	 * @return
	 */
	public static Datalibrary getDatalibraryByCode(String code) {
		return allDatas.get(code);
	}
	
	/**
	 * 通过code获取数据字典名称
	 * @param code	library code
	 * @return 如果没有返回空字符串
	 */
	public static String getDataLibraryNameByCode(String code) {
		Datalibrary datalibrary = allDatas.get(code);
		if (datalibrary == null) {
			return StringUtils.EMPTY;
		}
		return StringUtils.defaultIfEmpty(datalibrary.getLibraryvalue(), "");
	}
	
	/**
	 * 获取一组数据字典
	 * @param type	字典类型
	 * @return	Map<字典code, 字典中文名称>
	 */
	public static Map<String, String> getByGroup(String type) {
		return groupByTypeNameValues.get(type);
	}

	/**
	 * 向缓存内添加数据字典
	 * 
	 * @param datalibrary
	 */
	public static void addDatalibrary(Datalibrary datalibrary) {
		String key = datalibrary.getLibrarycode();
		allDatas.put(key, datalibrary);
		allDataNameValues.put(datalibrary.getLibrarycode(), datalibrary.getLibraryvalue());
		addToTypeMap(datalibrary);
	}

	/**
	 * 删除缓存内 key为 code的数据字典
	 * 
	 * @param code
	 */
	public static void deleteDatalibrary(String code) {
		remoteFromTypeMap(code);
		allDatas.remove(code);
	}

	/**
	 * 判断缓存内是否存在值为code的键值
	 * 
	 * @param code
	 * @return
	 */
	public static boolean existDatalibrary(String code) {
		return allDatas.containsKey(code);
	}

	/**
	 * 判断缓存内是否存在datalibrary对象
	 * 
	 * @param datalibrary
	 * @return
	 */
	public static boolean existDatalibrary(Datalibrary datalibrary) {
		return allDatas.containsValue(datalibrary);
	}

	/**
	 * 转换成Map格式数据
	 * @param dls
	 * @return	Map格式数据
	 */
	public static Map<String, String> parseToJson(List<Datalibrary> dls) {
		Map<String, String> result = new HashMap<String, String>();
		for (Datalibrary dl : dls) {
			result.put(dl.getLibrarycode(), dl.getLibraryvalue());
		}
		return result;
	}
	
	/**
	 * 使用数据字典类型和编码查询是否存在于数据字典
	 * @param type	字典类型
	 * @param code	字典编码
	 * @return	存在true，否则false
	 */
	public static boolean checkExist(String type, String code) {
		List<Datalibrary> types = getTypeGroupDataLibraryList(type);
		if (types.isEmpty()) {
			return false;
		}
		for (Datalibrary datalibrary : types) {
			if (code.equals(datalibrary.getLibrarycode())) {
				return true;
			}
		}
		return false;
	}

}
