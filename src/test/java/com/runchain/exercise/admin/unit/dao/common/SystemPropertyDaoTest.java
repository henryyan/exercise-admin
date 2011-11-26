package com.runchain.exercise.admin.unit.dao.common;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.runchain.exercise.admin.dao.common.SystemPropertyDao;
import com.runchain.exercise.admin.data.SystemPropertyData;
import com.runchain.exercise.admin.entity.common.SystemProperty;
import com.runchain.exercise.admin.unit.BaseTxTestCase;

public class SystemPropertyDaoTest extends BaseTxTestCase {

	@Resource
	private SystemPropertyDao entityDao;

	@Test
	//如果你需要真正插入数据库,将Rollback设为false
	//	@Rollback(false) 
	public void crudEntity() {
		SystemProperty bean = SystemPropertyData.getRandomProperty();
		entityDao.save(bean);
		entityDao.flush();

		//获取属性
		bean = entityDao.findUniqueBy("id", bean.getId());
		assertNotNull(bean);

		// 获取列表
		List<SystemProperty> all = entityDao.getAll();
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
			SystemProperty bean = SystemPropertyData.getRandomProperty();
			entityDao.save(bean);
			entityDao.flush();
		}
	}

}
