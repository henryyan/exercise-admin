package com.runchain.exercise.admin.web.member;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.orm.Page;
import org.springside.modules.orm.PropertyFilter;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import com.opensymphony.xwork2.ActionSupport;
import com.runchain.arch.web.base.CrudActionSupport;
import com.runchain.exercise.admin.entity.activity.AllFieldActivity;
import com.runchain.exercise.admin.entity.member.MemberCardUsageRecord;
import com.runchain.exercise.admin.entity.order.FieldOrderItem;
import com.runchain.exercise.admin.service.activity.AllFieldActivityManager;
import com.runchain.exercise.admin.service.member.MemberCardUsageRecordManager;
import com.runchain.arch.util.orm.PropertyFilterUtils;

/**
 * 会员卡使用记录Action
 *
 * @author HenryYan
 *
 */
@Namespace("/member")
@Action("memberCardRecord")
@Result(type = CrudActionSupport.JSON)
public class MemberCardUsageRecordAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	protected MemberCardUsageRecordManager manager;

	@Autowired
	protected AllFieldActivityManager fieldActivityManager;

	private String cardId;
	private Long cardUsageId;

	protected Page<MemberCardUsageRecord> page = new Page<MemberCardUsageRecord>();

	@Override
	public String execute() throws Exception {
		try {
			List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(Struts2Utils.getRequest());
			PropertyFilterUtils.handleFilter(page, MemberCardUsageRecord.class, filters);

			filters.add(new PropertyFilter("EQL_cardId", cardId));

			page = manager.searchRecord(page, filters);
		} catch (Exception e) {
			logger.error("读取[会员卡使用记录]列表：", e);
		}
		return SUCCESS;
	}

	/**
	 * 查询会员卡使用记录
	 * @return
	 * @throws Exception
	 */
	public String loadCardUsageDetail() throws Exception {
		try {
			List<FieldOrderItem> fieldOrderItems = manager.findFieldOrderItems(cardUsageId);
			for (FieldOrderItem fieldOrderItem : fieldOrderItems) {
				AllFieldActivity findActivity = fieldActivityManager.findActivity(fieldOrderItem.getActivityId(),
						fieldOrderItem.getFieldType());
				findActivity.setFieldOrder(null);
				fieldOrderItem.setFieldActivity(findActivity);
			}
			Struts2Utils.renderJson(fieldOrderItems);
		} catch (Exception e) {
			logger.error("读取[会员卡使用记录, id={}]列表：", cardUsageId, e);
		}
		return null;
	}

	public Page<MemberCardUsageRecord> getPage() {
		return page;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public void setCardUsageId(Long cardUsageId) {
		this.cardUsageId = cardUsageId;
	}

}
