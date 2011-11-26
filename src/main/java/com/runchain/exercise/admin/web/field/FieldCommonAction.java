package com.runchain.exercise.admin.web.field;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springside.modules.orm.Page;

import com.runchain.arch.web.base.CrudActionSupport;
import com.runchain.exercise.admin.butil.venue.VenueInfoUtil;
import com.runchain.exercise.admin.entity.venue.VenueInfo;
import com.runchain.exercise.admin.service.field.FieldCommonManager;

/**
 * 公共场地Action
 *
 * @author HenryYan
 *
 */
@Namespace("/field")
@Action(value = "common")
@Results( { @Result(name = "mixList", type = CrudActionSupport.JSON) })
public class FieldCommonAction {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	FieldCommonManager fcManager;

	protected Page<Object> page = new Page<Object>();
	List<Object> fields = null;

	/**
	 * 根据当前场馆开通的场地类型读取混合列表
	 * @return
	 */
	public String mixList() {
		try {
			VenueInfo venueInfo = VenueInfoUtil.getVenueFromSession();
			Long venueId = venueInfo.getId();
			fields = fcManager.findLessThanDefaultIssuedaysFields(venueId);
			page.setResult(fields);
			page.setTotalCount(fields.size());
			page.setPageNo(1);
			page.setPageSize(1000);
		} catch (Exception e) {
			logger.error("读取混合场地列表：", e);
		}
		return "mixList";
	}

	public Page<Object> getPage() {
		return page;
	}

}
