package com.runchain.exercise.admin.unit.dao.venue.member;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.runchain.exercise.admin.dao.venue.member.VenueMemberDao;
import com.runchain.exercise.admin.data.VenueMemberData;
import com.runchain.exercise.admin.entity.venue.member.VenueMemberInfo;
import com.runchain.exercise.admin.unit.BaseTxTestCase;

/**
 * 
 *
 * @author HenryYan
 *
 */
public class VenueMemberDaoTest extends BaseTxTestCase {

	@Resource
	VenueMemberDao entityDao;

	@Test
	//如果你需要真正插入数据库,将Rollback设为false
	//	@Rollback(false) 
	public void crudEntity() {
		VenueMemberInfo bean = VenueMemberData.getRandomMemberInfo();
		entityDao.save(bean);
		entityDao.flush();

		//获取属性
		bean = entityDao.findUniqueBy("id", bean.getId());
		assertNotNull(bean);

		// 获取列表
		List<VenueMemberInfo> all = entityDao.getAll();
		assertEquals(1, all.size());

		//删除属性
		entityDao.delete(bean.getId());
		entityDao.flush();
		bean = entityDao.findUniqueBy("id", bean.getId());
		assertNull(bean);
	}

	@Test
	@Rollback(false)
	public void addEntity() {
		for (int i = 0; i < 10; i++) {
			VenueMemberInfo bean = VenueMemberData.getRandomMemberInfo();
			entityDao.save(bean);
			entityDao.flush();
		}
	}

}
