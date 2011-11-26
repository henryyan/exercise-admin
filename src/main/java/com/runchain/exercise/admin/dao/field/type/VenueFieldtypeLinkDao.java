package com.runchain.exercise.admin.dao.field.type;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.runchain.exercise.admin.entity.field.type.FieldType;
import com.runchain.exercise.admin.entity.field.type.VenueFieldtypeLink;
import com.runchain.exercise.admin.entity.venue.VenueInfo;

/**
 * 场馆和场地类型关系DAO.
 *
 * @author HenryYan
 *
 */
@Component
public class VenueFieldtypeLinkDao extends HibernateDao<VenueFieldtypeLink, Long> {

	/**
	 * 查询指定场馆开启的场地类型
	 * @param venueId	场馆ID
	 * @return
	 */
	public List<FieldType> findTypeByVenue(Long venueId) {
		List<FieldType> types = new ArrayList<FieldType>();
		String hql = "select o.fieldType from VenueFieldtypeLink o where o.venueInfo.id = ?";
		List<Object> results = find(hql, new Object[] {venueId});
		if (!results.isEmpty()) {
			for (Object objType : results) {
				types.add((FieldType) objType);
			}
		}
		return types;
	}
	
	/**
	 * 查询指定场地类型被哪些场馆开启
	 * @param fieldTypeId	场馆ID
	 * @return
	 */
	public List<VenueInfo> findVenueByType(Long fieldTypeId) {
		List<VenueInfo> venues = new ArrayList<VenueInfo>();
		String hql = "select o.venueInfo from VenueFieldtypeLink o where o.fieldType.id = ?";
		List<Object> results = find(hql, new Object[] {fieldTypeId});
		if (!results.isEmpty()) {
			for (Object objType : results) {
				venues.add((VenueInfo) objType);
			}
		}
		return venues;
	}
	
	/**
	 * 以场馆ID和场地类型ID为XY坐标查询关系对象
	 * @param venueId		场馆ID
	 * @param fieldTypeId	场地类型ID
	 */
	public VenueFieldtypeLink findLink(Long venueId, Long fieldTypeId) {
		String hql = "select o from VenueFieldtypeLink o where o.venueInfo.id = ? and o.fieldType.id = ?";
		Object findUnique = findUnique(hql, new Object[] {venueId, fieldTypeId});
		if (findUnique != null) {
			return (VenueFieldtypeLink) findUnique;
		}
		return null;
	}
	
}
