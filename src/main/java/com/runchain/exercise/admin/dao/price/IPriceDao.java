package com.runchain.exercise.admin.dao.price;

/**
 * 价格DAO接口
 *
 * @author HenryYan
 *
 */
public interface IPriceDao {

	/**
	 * 删除所有价格
	 * @param venueId	场馆ID
	 * @return	删除的条数
	 */
	public int deleteAllPrice(Long venueId);
	
}
