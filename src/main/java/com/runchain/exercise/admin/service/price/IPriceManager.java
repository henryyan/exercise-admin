package com.runchain.exercise.admin.service.price;

import java.util.List;

import net.sf.json.JSONObject;

public interface IPriceManager<T> {

	/**
	 * 删除价格
	 * @param id	主键ID
	 */
	public abstract void deletePrice(Long id);

	/**
	 * 删除指定场馆的所有价格
	 * @param venueId	场馆ID
	 * @return
	 */
	public abstract int deleteAllPrice(Long venueId);

	/**
	 * 保存价格
	 * @param entity
	 */
	public abstract void savePrice(T entity);

	/**
	 * 获取价格
	 * @param id
	 * @return
	 */
	public abstract T getPrice(Long id);

	/**
	 * 获取所有价格
	 * @param venueId
	 * @return
	 */
	public abstract List<T> findAllPrices(Long venueId);

	/**
	 * 批量添加价格
	 * @param venueId	场馆ID
	 * @param params	JSON对象，需要保护venueId属性和start,end,price三个JSON数组对象
	 */
	public abstract void batchAdd(Long venueId, JSONObject params);

}