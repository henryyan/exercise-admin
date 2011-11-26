package com.runchain.exercise.admin.service.venue.member;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;

import com.runchain.exercise.admin.dao.venue.member.VenueMemberDao;
import com.runchain.exercise.admin.entity.venue.member.VenueMemberInfo;

/**
 * 场馆会员管理类型.
 *
 * @author HenryYan
 *
 */
@Component
@Transactional
public class VenueMemberManager {

	@Resource
	protected VenueMemberDao memberDao;
	
	/**
	 * 保存场馆会员信息
	 * @param entity
	 */
	public void saveMemberInfo(VenueMemberInfo entity) {
		memberDao.save(entity);
	}
	
	public void deleteMemberInfo(Long id) {
		// TODO 管理员权限验证
		memberDao.delete(id);
	}
	
	@Transactional(readOnly = true)
	public VenueMemberInfo getMemberInfo(Long id) {
		return memberDao.get(id);
	}
	
	/**
	 * 使用属性过滤条件查询属性.
	 */
	@Transactional(readOnly = true)
	public Page<VenueMemberInfo> searchMemberInfo(final Page<VenueMemberInfo> page, final List<PropertyFilter> filters) {
		return memberDao.findPage(page, filters);
	}
	
}
