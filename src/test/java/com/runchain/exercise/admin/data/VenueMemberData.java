package com.runchain.exercise.admin.data;

import org.springside.modules.test.utils.DataUtils;

import com.runchain.exercise.admin.entity.venue.member.VenueMemberInfo;

public class VenueMemberData {

	/**
	 * 返回一个随机的系统属性对象
	 * @return
	 */
	public static VenueMemberInfo getRandomMemberInfo() {
		String randomProp = DataUtils.randomName("Member");

		VenueMemberInfo member = new VenueMemberInfo();
		member.setVenueName(randomProp + "-场馆名称");
		member.setCity("上海市");
		member.setAdress(randomProp + "-地址");

		return member;
	}
	
}
