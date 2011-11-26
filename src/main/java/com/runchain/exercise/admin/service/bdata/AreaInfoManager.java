package com.runchain.exercise.admin.service.bdata;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import com.runchain.arch.service.ServiceException;
import com.runchain.exercise.admin.butil.bdata.AreaInfoUtil;
import com.runchain.exercise.admin.dao.bdata.AreaInfoDao;
import com.runchain.exercise.admin.entity.bdata.AreaInfo;

/**
 * 地区信息实体管理类
 *
 * @author HenryYan
 *
 */
@Service
@Transactional
public class AreaInfoManager {

	@Autowired AreaInfoDao areaDao;
		
	/**
	 * 根据父级ID查询所有的下级地区
	 * @param parentId	父级ID
	 * @return	下级地区集合
	 */
	@Transactional(readOnly = true)
	public List<AreaInfo> findChildAreaInfo(Long parentId) {
		return areaDao.findBy("parentAreaId", parentId);
	}
	
	/**
	 * 统计下级地区的个数
	 * @param childId	下级地区
	 * @return 下级地区的个数
	 */
	@Transactional(readOnly = true)
	public Long countChildAreaInfo(Long areaId) {
		return areaDao.countChildAreaInfo(areaId);
	}
	
	/**
	 * 使用属性过滤条件查询属性.
	 */
	@Transactional(readOnly = true)
	public Page<AreaInfo> searchProperty(final Page<AreaInfo> page, final List<PropertyFilter> filters) {
		return areaDao.findPage(page, filters);
	}
	
	/**
	 * 获取地区信息详细
	 * @param id
	 */
	public AreaInfo getAreaInfo(Long id) {
		return areaDao.get(id);
	}

	/**
	 * 保存地区信息
	 * @param entity
	 */
	public void saveAreaInfo(AreaInfo entity) {
		areaDao.save(entity);
		AreaInfoUtil.addArea(entity);
	}

	/**
	 * 删除地区信息
	 * @param id
	 */
	public void deleteAreaInfo(Long id) {
		areaDao.delete(id);
	}
	
	/**
	 * 查询指定合作渠道单位名称是否存在
	 * @param channelUnitName	合作渠道单位名称
	 * @return	存在true，否则false
	 */
	@Transactional(readOnly = true)
	public boolean existArea(String newId, Long parentAreaId, String areaName) throws ServiceException {
		AreaInfo areaInfo = areaDao.findAreaInfo(areaName, parentAreaId);
		
		// 添加时无id时，做唯一验证
		if (StringUtils.isEmpty(newId) || "_empty".equals(newId)) {
			if (areaInfo != null) {
				return true;
			} else {
				return false;
			}
		} else {
			// 修改时id已存在，做唯一验证
			if (areaInfo == null) {
				return false;
			} else {
				// 当对象存在时，判断原id与新id值是否一致
				Long oldId = areaInfo.getId();
				if (oldId.equals(new Long(newId))) {
					return false;
				} else {
					return true;
				}
			}
		}
	}
	
}
