package com.runchain.exercise.admin.dao.bdata;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.runchain.exercise.admin.entity.bdata.AreaInfo;

/**
 * 地区信息泛型DAO
 *
 * @author HenryYan
 *
 */
@Repository
public class AreaInfoDao extends HibernateDao<AreaInfo, Long> {
	
	/**
	 * 查询父级ID
	 * @param childId	子地区ID
	 * @return 父级ID
	 */
	public Long findParentAreaInfoId(Long childId) {
		String hql = "select parentAreaId from AreaInfo where id = ?";
		return super.findUnique(hql, childId);
	}

	/**
	 * 根据名称查询下一级的地区信息
	 * @param topLevelParent	地区名称
	 * @return
	 */
	public List<AreaInfo> getAreaByParentName(String parentName) {
		String byNameHql = "select id from AreaInfo where areaName = ?";
		String hql = "from AreaInfo where parentAreaId = (" + byNameHql + ")";
		return super.find(hql, parentName);
	}
	
	/**
	 * 统计下级地区的个数
	 * @param childId	下级地区
	 * @return 下级地区的个数
	 */
	public Long countChildAreaInfo(Long childId) {
		String hql = "select count(*) from AreaInfo where parentAreaId = ?";
		return super.findUnique(hql, childId);
	}
	
	/**
	 * 根据地区名称和父级ID查询
	 * @param areaName		地区名称
	 * @param parentAreaId	父级ID
	 * @return	查询结果为空返回NULL
	 */
	public AreaInfo findAreaInfo(String areaName, Long parentAreaId) {
		String hql = "from AreaInfo where areaName = ? and parentAreaId = ?";
		return super.findUnique(hql, areaName, parentAreaId);
	}
}
