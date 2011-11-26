package com.runchain.exercise.admin.unit.service.price.badmintoon;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.runchain.exercise.admin.entity.price.badmintoon.BadmintoonBasicPrice;
import com.runchain.exercise.admin.service.price.badmintoon.BadmintoonBasicPriceManager;
import com.runchain.exercise.admin.unit.BaseTxTestCase;

public class BadmintoonBasicPriceTest extends BaseTxTestCase {

	@Resource
	BadmintoonBasicPriceManager manager;
	
	@Test
	public void findAllPrice() {
		List<BadmintoonBasicPrice> findAllPrices = manager.findAllPrices(5l);
		System.out.println(findAllPrices.get(1));
	}
	
}
