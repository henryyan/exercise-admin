package com.runchain.exercise.admin.service.price.football;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.PropertyFilter;

import com.runchain.exercise.admin.dao.price.football.FootballBasicPriceDao;
import com.runchain.exercise.admin.entity.price.football.FootballBasicPrice;
import com.runchain.exercise.admin.service.price.IPriceManager;

/**
 * 足球-非周末价格实体管理类.
 *
 * @author HenryYan
 *
 */
@Component
@Transactional
public class FootballBasicPriceManager implements IPriceManager<FootballBasicPrice> {
	
	@Resource
	FootballBasicPriceDao dao;

	public void deletePrice(Long id) {
		dao.delete(id);
	}

	public int deleteAllPrice(Long venueId) {
		return dao.deleteAllPrice(venueId);
	}

	public void savePrice(FootballBasicPrice entity) {
		dao.save(entity);
	}

	@Transactional(readOnly = true)
	public FootballBasicPrice getPrice(Long id) {
		return dao.get(id);
	}

	@Transactional(readOnly = true)
	public List<FootballBasicPrice> findAllPrices(Long venueId) {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("EQL_venueId", venueId.toString()));
		List<FootballBasicPrice> all = dao.find(filters);
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
			FootballBasicPrice basicPrice = new FootballBasicPrice();
			basicPrice.setFromTime(starts.getString(i));
			basicPrice.setToTime(ends.getString(i));
			basicPrice.setPrice(new Integer(prices.getString(i)));
			basicPrice.setPaymentCommision(new Float(protocolPrices.getString(i)));
			basicPrice.setVenueId(venueId);
			savePrice(basicPrice);
		}
	}
	
}
