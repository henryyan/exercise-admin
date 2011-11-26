package com.runchain.exercise.admin.data;

import org.springside.modules.test.utils.DataUtils;

import com.runchain.exercise.admin.entity.venue.VenueInfo;

public class VenueInfoData {

	/**
	 * 返回一个随机的场馆信息对象
	 * @return
	 */
	public static VenueInfo getRandomVenueInfo() {
		String randomProp = DataUtils.randomName("场馆Member");

		VenueInfo member = new VenueInfo();
		member.setVenueName(randomProp);
		member.setCity("上海市");
		member.setAdress(randomProp + "-地址");

		return member;
	}
	
}
