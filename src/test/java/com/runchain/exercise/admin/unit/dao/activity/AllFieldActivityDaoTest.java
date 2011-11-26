package com.runchain.exercise.admin.unit.dao.activity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import com.runchain.exercise.admin.dao.activity.AllFieldActivityDao;
import com.runchain.exercise.admin.entity.activity.AllFieldActivity;
import com.runchain.exercise.admin.unit.BaseTxTestCase;

public class AllFieldActivityDaoTest extends BaseTxTestCase {

	@Resource
	AllFieldActivityDao dao;

	@Test
	public void testSumb() {
		List<Double> sumProtocolFee = dao.sumProtocolFee(5l, Date.valueOf("2010-07-20"), null);
		System.out.println(sumProtocolFee);
	}

	@Test
	public void testList() {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("EQS_fieldOrder.phone", "0"));
		List<AllFieldActivity> find = dao.find(filters);
		System.out.println(find);
	}
	
	@Test
	public void testSearch() {
		Page<AllFieldActivity> search = dao.search(new Page<AllFieldActivity>(), 0f, 5l, Date.valueOf("2010-07-29"), null);
		System.out.println(search.getTotalCount());
	}

}
