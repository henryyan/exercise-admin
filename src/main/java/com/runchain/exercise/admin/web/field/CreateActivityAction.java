package com.runchain.exercise.admin.web.field;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import com.opensymphony.xwork2.ActionSupport;
import com.runchain.arch.web.base.CrudActionSupport;
import com.runchain.exercise.admin.butil.field.FieldUtil;
import com.runchain.exercise.admin.butil.venue.VenueInfoUtil;
import com.runchain.exercise.admin.service.field.badmintoon.BadmintoonManager;
import com.runchain.exercise.admin.service.field.football.FootballManager;
import com.runchain.exercise.admin.service.field.tennis.TennisManager;

/**
 * 场地活动创建Action
 *
 * @author HenryYan
 *
 */
@Namespace("/field")
@Action("createActivity")
@Result(name = CrudActionSupport.JSON, location = "createActivity.jsp")
public class CreateActivityAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource BadmintoonManager badmintoonManager;
	@Resource FootballManager footballManager;
	@Resource TennisManager tennisManager;
	
	private List<? extends Object> fields = null;

	public String list() {
		String fieldType = Struts2Utils.getParameter("fieldType");
		try {
			Long venueId = VenueInfoUtil.getVenueFromSession().getId();
			if (FieldUtil.FIELD_TYPE_BADMINTOON.equals(fieldType)) {
				fields = badmintoonManager.findAll(venueId);
			} else if (FieldUtil.FIELD_TYPE_FOOTBALL.equals(fieldType)) {
				fields = footballManager.findAll(venueId);
			} else if (FieldUtil.FIELD_TYPE_TENNIS.equals(fieldType)) {
				fields = tennisManager.findAll(venueId);
			}
		} catch (Exception e) {
			logger.error("读取场地列表：fieldType=" + fieldType, e);
		}
		return CrudActionSupport.JSON;
	}

	public List<? extends Object> getFields() {
		return fields;
	}
	
}
