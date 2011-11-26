package com.runchain.exercise.admin.unit.dao.field.type;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.runchain.exercise.admin.dao.field.type.VenueFieldtypeLinkDao;
import com.runchain.exercise.admin.entity.field.type.FieldType;
import com.runchain.exercise.admin.unit.BaseTxTestCase;

public class VenueFieldtypeLinkDaoTest extends BaseTxTestCase {

	@Resource VenueFieldtypeLinkDao entityDao;
	
	@Test
	public void find() {
		entityDao.findLink(5l, null);
	}
	
	@Test
	public void findTypeByVenue() {
		List<FieldType> findTypeByVenue = entityDao.findTypeByVenue(5l);
		System.out.println(findTypeByVenue);
	}
	
}
