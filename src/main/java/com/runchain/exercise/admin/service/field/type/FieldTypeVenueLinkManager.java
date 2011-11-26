package com.runchain.exercise.admin.service.field.type;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import com.runchain.exercise.admin.dao.field.type.FieldTypeDao;
import com.runchain.exercise.admin.dao.field.type.VenueFieldtypeLinkDao;
import com.runchain.exercise.admin.dao.venue.VenueInfoDao;
import com.runchain.exercise.admin.entity.field.type.FieldType;
import com.runchain.exercise.admin.entity.field.type.VenueFieldtypeLink;
import com.runchain.exercise.admin.entity.venue.VenueInfo;

/**
 * 场馆和场地类型关系实体管理类.
 *
 * @author HenryYan
 *
 */
@Component
@Transactional
public class FieldTypeVenueLinkManager {
	
	@Resource
	protected FieldTypeDao fieldTypeDao;
	
	@Resource
	protected VenueInfoDao venueInfoDao;
	
	@Resource
	protected VenueFieldtypeLinkDao linkDao;
	
	/**
	 * 删除关系
	 * @param id	主键ID
	 */
	public void deleteLink(Long id) {
		linkDao.delete(id);
	}
	
	/**
	 * 查询指定场馆开启的场地类型
	 * @param venueId	场馆ID
	 * @return
	 */
	public List<FieldType> findTypeByVenue(Long venueId) {
		return linkDao.findTypeByVenue(venueId);
	}
	
	/**
	 * 查询指定场地类型被哪些场馆开启
	 * @param fieldTypeId	场馆ID
	 * @return
	 */
	public List<VenueInfo> findVenueByType(Long fieldTypeId) {
		return linkDao.findVenueByType(fieldTypeId);
	}

	/**
	 * 为场馆开启一个场地类型
	 * @param venueId		场馆ID
	 * @param fieldTypeId	场地类型ID
	 */
	public void addLink(Long venueId, Long fieldTypeId) {
		VenueInfo venueInfo = venueInfoDao.get(venueId);
		FieldType fieldType = fieldTypeDao.get(fieldTypeId);
		VenueFieldtypeLink link = new VenueFieldtypeLink(venueInfo, fieldType);
		linkDao.save(link);
	}
	
	/**
	 * 为场馆开启一个场地类型
	 * @param venueId		场馆ID
	 * @param ids			场地类型ID
	 */
	public void addLink(Long venueId, String... ids) {
		VenueInfo venueInfo = venueInfoDao.get(venueId);
		for (String id : ids) {
			if (StringUtils.isNotEmpty(id)) {
				FieldType fieldType = fieldTypeDao.get(new Long(id));
				VenueFieldtypeLink link = new VenueFieldtypeLink(venueInfo, fieldType);
				linkDao.save(link);
			}
		}
	}
	
	/**
	 * 为场馆关闭一个场地类型
	 * @param venueId		场馆ID
	 * @param fieldTypeId	场地类型ID
	 */
	public void removeLink(Long venueId, Long fieldTypeId) {
		VenueFieldtypeLink link = linkDao.findLink(venueId, fieldTypeId);
		linkDao.delete(link);
	}
	
	/**
	 * 使用属性过滤条件查询实体.
	 */
	@Transactional(readOnly = true)
	public Page<VenueFieldtypeLink> searchLink(final Page<VenueFieldtypeLink> page, final List<PropertyFilter> filters) {
		return linkDao.findPage(page, filters);
	}
	
}
