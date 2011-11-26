package com.runchain.exercise.admin.unit.dao.field.type;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springside.modules.orm.PropertyFilter;

import com.runchain.exercise.admin.dao.field.badmintoon.BadmintoonDao;
import com.runchain.exercise.admin.entity.field.Badmintoon;
import com.runchain.exercise.admin.unit.BaseTxTestCase;

public class BadmintoonDaoTest extends BaseTxTestCase {

	@Resource BadmintoonDao dao;
	
	@Test
	public void testList() {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		List<Badmintoon> find = dao.find(filters);
		System.out.println(find);
	}
	
}
