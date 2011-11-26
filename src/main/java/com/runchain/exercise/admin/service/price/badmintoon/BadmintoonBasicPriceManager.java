package com.runchain.exercise.admin.service.price.badmintoon;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.PropertyFilter;

import com.runchain.exercise.admin.dao.price.badmintoon.BadmintoonBasicPriceDao;
import com.runchain.exercise.admin.entity.price.badmintoon.BadmintoonBasicPrice;
import com.runchain.exercise.admin.service.price.IPriceManager;

/**
 * 羽毛球-非周末价格实体管理类.
 *
 * @author HenryYan
 *
 */
@Component
@Transactional
public class BadmintoonBasicPriceManager implements IPriceManager<BadmintoonBasicPrice> {
	
	@Resource BadmintoonBasicPriceDao dao;

	public void deletePrice(Long id) {
		dao.delete(id);
	}
	
	public int deleteAllPrice(Long venueId) {
		return dao.deleteAllPrice(venueId);
	}
	
	public void savePrice(BadmintoonBasicPrice entity) {
		dao.save(entity);
	}
	
	@Transactional(readOnly = true)
	public BadmintoonBasicPrice getPrice(Long id) {
		return dao.get(id);
	}

	@Transactional(readOnly = true)
	public List<BadmintoonBasicPrice> findAllPrices(Long venueId) {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("EQL_venueId", venueId.toString()));
		List<BadmintoonBasicPrice> all = dao.find(filters);
		return all;
	}
	
	public void batchAdd(Long venueId, JSONObject params) {
		JSONArray starts = (JSONArray) params.get("start");
		JSONArray ends = (JSONArray) params.get("end");
		JSONArray prices = (JSONArray) params.get("price");
		JSONArray protocolPrices = (JSONArray) params.get("protocolPrice");

		// 1、删除所有已设置的价格
		deleteAllPrice(venueId);

		for (int i = 0; i < starts.size(); i++) {
			BadmintoonBasicPrice basicPrice = new BadmintoonBasicPrice();
			basicPrice.setFromTime(starts.getString(i));
			basicPrice.setToTime(ends.getString(i));
			basicPrice.setPrice(new Integer(prices.getString(i)));
			basicPrice.setPaymentCommision(new Float(protocolPrices.getString(i)));
			basicPrice.setVenueId(venueId);
			savePrice(basicPrice);
		}
	}
	
}
