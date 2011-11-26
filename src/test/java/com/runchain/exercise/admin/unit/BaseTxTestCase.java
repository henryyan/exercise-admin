package com.runchain.exercise.admin.unit;

import org.junit.Before;
import org.junit.Ignore;
import org.springframework.test.context.ContextConfiguration;
import org.springside.modules.test.spring.SpringTxTestCase;
import org.springside.modules.test.utils.DbUnitUtils;

/**
 * 数据库访问测试基类。
 * 
 * 继承SpringTxTestCase的所有方法, 并在第一个测试方法前初始化数据.
 * 
 * @see SpringTxTestCase
 * 
 * @author HenryYan
 */
@Ignore
@ContextConfiguration(locations = { "/applicationContext-test.xml" })
public class BaseTxTestCase extends SpringTxTestCase {

	@Before
	public void loadDefaultDatae() throws Exception {
		DbUnitUtils.loadData(dataSource, "/data/default-data.xml");
	}
}
