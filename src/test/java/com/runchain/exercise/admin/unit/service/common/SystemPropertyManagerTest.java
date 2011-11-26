package com.runchain.exercise.admin.unit.service.common;

import javax.annotation.Resource;

import org.junit.Test;

import com.runchain.exercise.admin.service.common.SystemPropertyManager;
import com.runchain.exercise.admin.unit.BaseTxTestCase;

/**
 * SystemPropertyManager的测试用例
 *
 * @author HenryYan
 *
 */
public class SystemPropertyManagerTest extends BaseTxTestCase {
	
	@Resource
	private SystemPropertyManager propManager;
	
	@Test
	public void testManager() {
		System.out.println(propManager);
	}
}
