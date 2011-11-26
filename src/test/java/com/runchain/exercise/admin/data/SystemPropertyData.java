package com.runchain.exercise.admin.data;

import org.springside.modules.test.utils.DataUtils;

import com.runchain.exercise.admin.entity.common.SystemProperty;

/**
 * 
 * <p><b>Title：</b>系统属性实体测试数据生成</p>
 * <p><b>Description：</b></p>
 *
 * @author HenryYan
 *
 */
public class SystemPropertyData {

	/**
	 * 返回一个随机的系统属性对象
	 * @return
	 */
	public static SystemProperty getRandomProperty() {
		String randomProp = DataUtils.randomName("Property");

		SystemProperty property = new SystemProperty();
		property.setPropName(randomProp + "-name");
		property.setPropKey(randomProp + "-key");
		property.setPropValue(randomProp + "-value");
		property.setPropDescribe(randomProp + "-describe");

		return property;
	}
	
}
