package com.runchain.exercise.admin.unit.dao.tactics;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springside.modules.orm.PropertyFilter;

import com.runchain.exercise.admin.dao.tactics.TacticsDateDao;
import com.runchain.exercise.admin.entity.tactics.TacticsDate;
import com.runchain.exercise.admin.unit.BaseTxTestCase;

public class TacticsDateDaoTest extends BaseTxTestCase {

	@Resource TacticsDateDao dateDao;
	
	@Test
	public void testDate() {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("EQS_tactics.remark", "abc"));
		List<TacticsDate> find = dateDao.find(filters);
		System.out.println(find.size());
	}
	
}
