package com.runchain.exercise.admin.service.tactics;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.PropertyFilter;

import com.runchain.exercise.admin.dao.tactics.TacticsDao;
import com.runchain.exercise.admin.dao.tactics.TacticsPriceDao;
import com.runchain.exercise.admin.entity.tactics.Tactics;
import com.runchain.exercise.admin.entity.tactics.TacticsPrice;

/**
 * 特殊策略价格实体管理类.
 *
 * @author HenryYan
 *
 */
@Component
@Transactional
public class TacticsPriceManager {
	
	@Resource TacticsPriceDao priceDao;
	@Resource TacticsDao tacticsDao;

	@Transactional(readOnly = true)
	public List<TacticsPrice> findAllPrice(Long tacticsId) {
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("EQL_tactics.id", tacticsId.toString()));
		List<TacticsPrice> prices = priceDao.find(filters);
		return prices;
	}

	public void deletePrice(Long id) {
		priceDao.delete(id);
	}
	
	public int deleteAllPrice(Long venueId) {
		return priceDao.deleteAllPrice(venueId);
	}

	public void batchAdd(Long venueId, JSONObject params) {
		JSONArray starts = (JSONArray) params.get("start");
		JSONArray ends = (JSONArray) params.get("end");
		JSONArray prices = (JSONArray) params.get("price");
		JSONArray protocolPrices = (JSONArray) params.get("protocolPrice");

		String tacticsId = params.getString("tacticsId");
		Tactics tactics = tacticsDao.get(new Long(tacticsId));
		
		// 1、删除所有已设置的价格
		deleteAllPrice(venueId);

		for (int i = 0; i < starts.size(); i++) {
			TacticsPrice priceObj = new TacticsPrice();
			priceObj.setTactics(tactics);
			priceObj.setFromTime(starts.getString(i));
			priceObj.setToTime(ends.getString(i));
			priceObj.setPrice(new Integer(prices.getString(i)));
			priceObj.setPaymentCommision(new Float(protocolPrices.getString(i)));
			priceObj.setVenueId(venueId);
			priceDao.save(priceObj);
		}
		
		// 设置更新状态
		if (!tactics.getIsModify()) {
			tactics.setIsModify(true);
			tacticsDao.save(tactics);
		}
	}

	public TacticsPrice getPrice(Long id) {
		return priceDao.get(id);
	}

	public void savePrice(TacticsPrice price) {
		priceDao.save(price);
	}
	
}
