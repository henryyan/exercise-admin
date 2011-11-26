package com.runchain.exercise.admin.web.member;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import com.runchain.arch.web.base.CrudActionSupport;
import com.runchain.exercise.admin.butil.venue.VenueInfoUtil;
import com.runchain.exercise.admin.entity.member.MemberCardType;
import com.runchain.exercise.admin.entity.venue.VenueInfo;
import com.runchain.exercise.admin.service.member.MemberCardTypeManager;

/**
 * 会员卡类型Action
 *
 * @author HenryYan
 *
 */
@Namespace("/member")
@Action("cardType")
@Results( { @Result(location = "memberCardType.jsp"),
		@Result(name = CrudActionSupport.RELOAD, location = "cardType.action", type = "redirect") })
public class MemberCardTypeAction extends CrudActionSupport<MemberCardType> {

	private static final long serialVersionUID = 1L;

	@Resource
	MemberCardTypeManager manager;

	//-- 页面属性 --//
	private Long id;
	private MemberCardType entity;
	private List<MemberCardType> types;

	@Override
	public String delete() throws Exception {
		try {
			manager.deleteCardType(id);
			Struts2Utils.renderText("true");
		} catch (Exception e) {
			logger.error("删除会员卡类型时：ID=" + id, e);
			Struts2Utils.renderText("false");
		}
		return null;
	}

	@Override
	public String input() throws Exception {
		Struts2Utils.renderJson(entity);
		return null;
	}

	@Override
	public String list() throws Exception {
		try {
			VenueInfo venueInfo = VenueInfoUtil.getVenueFromSession();
			types = manager.findAllTypes(venueInfo.getId().toString());
		} catch (Exception e) {
			logger.error("获取特殊价格策略列表时：", e);
		}
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			entity = manager.getCardType(id);
		} else {
			entity = new MemberCardType();
			VenueInfo venueInfo = VenueInfoUtil.getVenueFromSession();
			entity.setVenueId(venueInfo.getId());
		}
	}

	@Override
	public String save() throws Exception {
		try {
			manager.saveCardType(entity);
			addActionMessage("保存会员卡类型成功");
		} catch (Exception e) {
			logger.error("保存会员卡类型时，" + entity, e);
		}
		return RELOAD;
	}

	//-- getter and setter --//
	public MemberCardType getModel() {
		return entity;
	}

	public List<MemberCardType> getTypes() {
		return types;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
