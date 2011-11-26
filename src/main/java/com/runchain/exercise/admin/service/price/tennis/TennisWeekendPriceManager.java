package com.runchain.exercise.admin.service.price.tennis;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.PropertyFilter;

import com.runchain.exercise.admin.dao.price.tennis.TennisWeekendPriceDao;
import com.runchain.exercise.admin.entity.price.tennis.TennisWeekendPrice;
import com.runchain.exercise.admin.service.price.IPriceManager;

/**
 * 网球-周末价格实体管理类.
 *
 * @author HenryYan
 *
 */
@Component
@Transactional
public class TennisWeekendPriceManager implements IPriceManager<TennisWeekendPrice> {

	@Resource
	TennisWeekendPriceDao dao;

	public void deletePrice(Long id) {
		dao.delete(id);
	}

	public int deleteAllPrice(Long venueId) {
		return dao.deleteAllPrice(venueId);
	}

	public void savePrice(TennisWeekendPrice entity) {
		dao.save(entity);
	}

	@Transactional(readOnly = true)
	public TennisWeekendPrice getPrice(Long id) {
		return dao.get(id);
	}

	@Transactional(readOnly = true)
	public List<TennisWeekendPrice> findAllPrices(Long venueId) {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("EQL_venueId", venueId.toString()));
		List<TennisWeekendPrice> all = dao.find(filters);
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
			TennisWeekendPrice basicPrice = new TennisWeekendPrice();
			basicPrice.setFromTime(starts.getString(i));
			basicPrice.setToTime(ends.getString(i));
			basicPrice.setPrice(new Integer(prices.getString(i)));
			basicPrice.setPaymentCommision(new Float(protocolPrices.getString(i)));
			basicPrice.setVenueId(venueId);
			savePrice(basicPrice);
		}
	}
	
}
