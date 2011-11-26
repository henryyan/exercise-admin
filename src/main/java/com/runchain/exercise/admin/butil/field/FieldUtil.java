package com.runchain.exercise.admin.butil.field;

import java.util.HashMap;
import java.util.Map;

import com.runchain.exercise.admin.entity.field.Badmintoon;
import com.runchain.exercise.admin.entity.field.Football;
import com.runchain.exercise.admin.entity.field.Tennis;

public class FieldUtil {
	
	/**
	 * 场地状态
	 *
	 * @author HenryYan
	 *
	 */
	public enum FieldStatus {
		启用, 关闭, 维护;
	}

	/**
	 * 场地活动表<英文名称, 中文名称>
	 */
	public static Map<String, String> FIELD_EN_ZH_NAME = new HashMap<String, String>();
	
	/**
	 * 价格类型<英文名称, 中文名称>
	 */
	public static Map<String, String> PRICE_EN_ZH_NAME = new HashMap<String, String>();
	
	/**
	 * 场地类型(英文)和对应class对象
	 */
	public static Map<String, Class<?>> FIELD_CLASS = new HashMap<String, Class<?>>();
	
	/**
	 * 场地类型.羽毛球
	 */
	public static final String FIELD_TYPE_BADMINTOON = "badmintoon";

	/**
	 * 场地类型.网球
	 */
	public static final String FIELD_TYPE_TENNIS = "tennis";
	
	/**
	 * 场地类型.足球
	 */
	public static final String FIELD_TYPE_FOOTBALL = "football";
	
	static {
		FIELD_EN_ZH_NAME.put(FIELD_TYPE_BADMINTOON, "羽毛球");
		FIELD_EN_ZH_NAME.put(FIELD_TYPE_TENNIS, "网球");
		FIELD_EN_ZH_NAME.put(FIELD_TYPE_FOOTBALL, "足球");
		
		PRICE_EN_ZH_NAME.put("basic", "非周末");
		PRICE_EN_ZH_NAME.put("weekend", "周末");
		
		FIELD_CLASS.put(FIELD_TYPE_BADMINTOON, Badmintoon.class);
		FIELD_CLASS.put(FIELD_TYPE_TENNIS, Tennis.class);
		FIELD_CLASS.put(FIELD_TYPE_FOOTBALL, Football.class);
	}
	
}
