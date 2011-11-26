package com.runchain.exercise.admin.unit.dao.field.type;

import javax.annotation.Resource;

import com.runchain.exercise.admin.dao.field.type.FieldTypeDao;
import com.runchain.exercise.admin.entity.field.type.FieldType;
import com.runchain.exercise.admin.unit.BaseTxTestCase;

public class FieldTypeDaoTest extends BaseTxTestCase {
	
	@Resource FieldTypeDao dao;
	
	//@Test
	//@Rollback(false)
	public void addType() {
		FieldType entity = new FieldType();
		entity.setTypeName("测试");
		entity.setTypeId("test");
		dao.save(entity);
	}
	
}
