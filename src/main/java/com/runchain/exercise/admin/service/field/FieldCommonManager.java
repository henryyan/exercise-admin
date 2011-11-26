package com.runchain.exercise.admin.service.field;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.runchain.exercise.admin.butil.field.FieldUtil;
import com.runchain.exercise.admin.entity.common.SystemProperty;
import com.runchain.exercise.admin.entity.field.type.FieldType;
import com.runchain.exercise.admin.service.common.SystemPropertyManager;
import com.runchain.exercise.admin.service.field.type.FieldTypeManager;
import com.runchain.arch.util.orm.BaseDao;

/**
 * 
 *
 * @author HenryYan
 *
 */
@Component
public class FieldCommonManager {

	@Resource
	BaseDao baseDao;

	@Resource
	FieldTypeManager fieldTypeManager;
	@Resource
	SystemPropertyManager propManager;

	public List<Object> findLessThanDefaultIssuedaysFields(Class<?> clazz, Long venueId, Integer issueDays) {
		List<Object> find = baseDao.find("select o from " + clazz.getSimpleName()
				+ " o where o.status=? and o.venueId=? and o.issueAdvance < ?", new Object[] {FieldUtil.FieldStatus.启用.toString(),
				venueId, issueDays });
		return find;
	}

	public List<Object> findLessThanDefaultIssuedaysFields(Long venueId) {
		List<Object> fields = new ArrayList<Object>();
		List<FieldType> enableFieldTypes = fieldTypeManager.getEnableFieldType();

		List<SystemProperty> props = propManager.findPropertyByKey("protocol-venue-issue-days");
		SystemProperty property = props.get(0);
		Integer issueDays = new Integer(property.getPropValue());

		for (FieldType fieldType : enableFieldTypes) {
			String typeId = fieldType.getTypeId();
			Class<?> fieldClass = FieldUtil.FIELD_CLASS.get(typeId);
			List<Object> list = findLessThanDefaultIssuedaysFields(fieldClass, venueId, issueDays);
			fields.addAll(list);
		}
		return fields;
	}

}
