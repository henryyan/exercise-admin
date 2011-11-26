package com.runchain.exercise.admin.unit.service.field;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.runchain.exercise.admin.entity.field.Badmintoon;
import com.runchain.exercise.admin.service.field.FieldCommonManager;
import com.runchain.exercise.admin.unit.BaseTxTestCase;

/**
 * 
 *
 * @author HenryYan
 *
 */
public class FieldCommonManagerTest extends BaseTxTestCase {

	@Resource FieldCommonManager manager;

	@Test
	public void testField() {
		List<Object> findLessThanDefaultIssuedaysFields = manager.findLessThanDefaultIssuedaysFields(Badmintoon.class, 5l, 15);
		System.out.println(findLessThanDefaultIssuedaysFields);
	}
	
}
