package com.runchain.exercise.admin.service.venue;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import com.runchain.exercise.admin.dao.venue.VenueInfoDao;
import com.runchain.exercise.admin.dao.venue.VenueInfoJdbcDao;
import com.runchain.exercise.admin.entity.common.AutoComplete;
import com.runchain.exercise.admin.entity.venue.VenueInfo;
import com.runchain.arch.util.orm.PropertyFilterUtils;
import com.runchain.arch.util.string.StringUtil;

/**
 * 场馆信息实体管理类. 
 *
 * @author HenryYan
 *
 */
@Component
@Transactional
public class VenueInfoManager {

	@Resource
	protected VenueInfoDao entityDao;
	
	@Resource
	protected VenueInfoJdbcDao venueInfoJdbcDao;
	
	/**
	 * 保存场馆信息
	 * @param entity
	 */
	public void saveVenueInfo(VenueInfo entity) {
		entityDao.save(entity);
	}
	
	/**
	 * 删除场馆所有信息，各种设置、场地、订单、会员<br/>
	 * <b>谨慎使用</b>
	 * @param id	场馆ID
	 */
	public void deleteVenueInfo(Long id) {
		venueInfoJdbcDao.deleteVenueInfos(id);
	}
	
	@Transactional(readOnly = true)
	public VenueInfo getVenueInfo(Long id) {
		return entityDao.get(id);
	}
	
	/**
	 * 获取图片路径
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public String getVenuePicture(Long id) {
		String hql = "select photoUrl from VenueInfo where id=?";
		return entityDao.findUnique(hql, id);
	}
	
	/**
	 * 设置图片路径
	 * @param id
	 * @return
	 */
	public int setVenuePicture(Long id, String pictureRealName) {
		String hql = "update VenueInfo set photoUrl=? where id=?";
		return entityDao.batchExecute(hql, pictureRealName, id);
	}
	
	/**
	 * 使用属性过滤条件查询实体.
	 */
	@Transactional(readOnly = true)
	public Page<VenueInfo> searchVenueInfo(final Page<VenueInfo> page, final List<PropertyFilter> filters) {
		return entityDao.findPage(page, filters);
	}
	
	/**
	 * 根据构造参数获取商户集合
	 * @param page		分页对象
	 * @param filters	条件过滤
	 * @param fields	要查询的字段，需要有相同的实体构造方法
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<VenueInfo> searchProperty(Page<VenueInfo> page, List<PropertyFilter> filters, String[] fields) {
		String selectFields = StringUtil.arrayToString(fields);
		String hql = "select new VenueInfo(" + selectFields + ") from VenueInfo o where 1=1";
		List<Object> values = new ArrayList<Object>();
		hql += PropertyFilterUtils.jointHql(filters, values);
		if (page.isOrderBySetted()) {
			hql += " order by " + page.getOrderBy() + " " + page.getOrder();
		}
		return entityDao.findPage(page, hql, values.toArray());
	}
	
	/**
	 * 根据指定条件、字段获取场馆对象
	 * @param venueId	场馆ID
	 * @param filters	过滤器
	 * @param fields	获取的字段
	 * @return	场馆对象
	 */
	@Transactional(readOnly = true)
	public VenueInfo getVenueInfo(List<PropertyFilter> filters, String[] fields) {
		String selectFields = StringUtil.arrayToString(fields);
		String hql = "select new VenueInfo(" + selectFields + ") from VenueInfo o where 1=1";
		List<Object> values = new ArrayList<Object>();
		hql += PropertyFilterUtils.jointHql(filters, values);
		return entityDao.findUnique(hql, values.toArray());
	}
	
	/**
	 * 验证场馆信息，如果没有验证通过设置验证标志位1
	 * @param id	场馆信息主键ID
	 * @return 
	 */
	public int verifyVenue(Long[] venueIds, Boolean status) {
		int count = entityDao.updateVenueVerifyStatus(venueIds, status);
		return count;
	}
	
	/**
	 * 根据场馆名称查找场馆信息
	 * @param venueName
	 * @return
	 */
	@Transactional(readOnly = true)
	public VenueInfo findVenueInfoByVenueName(String venueName) {
		VenueInfo findUniqueBy = entityDao.findUniqueBy("venueName", venueName);
		return findUniqueBy;
	}
	
	/**
	 * 取消验证场馆信息
	 * @param id	场馆信息主键ID
	 */
	public void unVerifyVenue(Long id) {
	}
	
	/**
	 * 升级场馆为协议用户
	 * @param id
	 */
	public VenueInfo upgrade(Long id) {
		VenueInfo venueInfo = getVenueInfo(id);
		if (!venueInfo.getIsProtocol()) {
			venueInfo.setIsProtocol(true);
			saveVenueInfo(venueInfo);
		}
		return venueInfo;
	}
	
	/**
	 * 取消升级场馆为协议用户
	 * @param id
	 */
	public VenueInfo unUpgrade(Long id) {
		VenueInfo venueInfo = getVenueInfo(id);
		if (venueInfo.getIsProtocol()) {
			venueInfo.setIsProtocol(false);
			saveVenueInfo(venueInfo);
		}
		return venueInfo;
	}
	
	/**
	 * 根据场馆名称查询
	 * @param term	场馆名称
	 * @return
	 */
	public List<AutoComplete> autoSearch(String term) {
		if (StringUtils.isEmpty(term)) {
			return new ArrayList<AutoComplete>();
		}
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("LIKES_venueName", term));
		List<VenueInfo> venueInfos = entityDao.find(filters);
		List<AutoComplete> autoBeans = new ArrayList<AutoComplete>();
		for (VenueInfo venueInfo : venueInfos) {
			String venueInfoId = venueInfo.getId().toString(); // 场馆ID
			String venueName = venueInfo.getVenueName(); // 场馆名称
			String district = venueInfo.getDistrict(); // 区县
			AutoComplete autoBean = new AutoComplete(venueInfoId, venueName, venueName, district);
			autoBeans.add(autoBean);
		}
		return autoBeans;
	}
	
}
