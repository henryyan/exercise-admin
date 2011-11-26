package com.runchain.exercise.admin.web.member;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import com.opensymphony.xwork2.ActionSupport;
import com.runchain.arch.web.base.CrudActionSupport;
import com.runchain.exercise.admin.butil.venue.VenueInfoUtil;
import com.runchain.exercise.admin.entity.member.MemberCard;
import com.runchain.exercise.admin.entity.venue.VenueInfo;
import com.runchain.exercise.admin.service.member.MemberCardManager;
import com.runchain.arch.util.orm.PropertyFilterUtils;

/**
 * 会员卡Action.
 *
 * @author HenryYan
 *
 */
@Namespace("/member")
@Action("memberCard")
@Result(type = CrudActionSupport.JSON)
public class MemberCardAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	protected MemberCardManager manager;
	
	protected Page<MemberCard> page = new Page<MemberCard>();

	@Override
	public String execute() throws Exception {
		try {
			VenueInfo venueInfo = VenueInfoUtil.getVenueFromSession();
			List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
			PropertyFilterUtils.handleFilter(page, MemberCard.class, filters);
			
			filters.add(new PropertyFilter("EQL_venueId", venueInfo.getId().toString()));
			
			page = manager.searchCard(page, filters);
		} catch (Exception e) {
			logger.error("读取[会员卡]列表：", e);
		}
		return SUCCESS;
	}

	public Page<MemberCard> getPage() {
		return page;
	}

}
