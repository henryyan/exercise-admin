package com.runchain.exercise.admin.unit.dao.member;

import javax.annotation.Resource;

import org.junit.Test;

import com.runchain.exercise.admin.dao.member.MemberCardDao;
import com.runchain.exercise.admin.entity.member.MemberCard;
import com.runchain.exercise.admin.unit.BaseTxTestCase;

/**
 * 
 *
 * @author HenryYan
 *
 */
public class MemberCardDaoTest extends BaseTxTestCase {
	
	@Resource MemberCardDao dao;

	@Test
	public void testFind() {
		MemberCard memberCard = dao.get(157l);
		System.out.println(memberCard);
	}
}
