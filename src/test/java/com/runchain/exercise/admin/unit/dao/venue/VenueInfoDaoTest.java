package com.runchain.exercise.admin.unit.dao.venue;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.runchain.exercise.admin.dao.venue.VenueInfoDao;
import com.runchain.exercise.admin.data.VenueInfoData;
import com.runchain.exercise.admin.entity.venue.VenueInfo;
import com.runchain.exercise.admin.unit.BaseTxTestCase;

public class VenueInfoDaoTest extends BaseTxTestCase {
	
	@Resource VenueInfoDao entityDao;
	
	@Test
	public void getEntity() {
		VenueInfo venueInfo = entityDao.get(5l);
		System.out.println(venueInfo.getVenueFieldTypeLinks().size());
		System.out.println(venueInfo);
	}

	@Test
	@Rollback(false) 
	public void addEntity() {
		for (int i = 0; i < 10; i++) {
			VenueInfo bean = VenueInfoData.getRandomVenueInfo();
			entityDao.save(bean);
			entityDao.flush();
		}
	}
	
}
