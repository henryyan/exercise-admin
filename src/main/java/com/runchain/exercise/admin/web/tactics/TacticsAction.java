package com.runchain.exercise.admin.web.tactics;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import com.runchain.arch.web.base.CrudActionSupport;
import com.runchain.exercise.admin.butil.venue.VenueInfoUtil;
import com.runchain.exercise.admin.entity.tactics.Tactics;
import com.runchain.exercise.admin.entity.venue.VenueInfo;
import com.runchain.exercise.admin.service.tactics.TacticsManager;

/**
 * 特殊策略Action
 *
 * @author HenryYan
 *
 */
@Namespace("/tactics")
@Results( { @Result(name = CrudActionSupport.RELOAD, location = "tactics.action", type = "redirect") })
public class TacticsAction extends CrudActionSupport<Tactics> {

	private static final long serialVersionUID = 1L;
	
	@Resource TacticsManager tacticsManager;
	
	//-- 页面属性 --//
	private Long id;
	private Tactics entity;
	private List<Tactics> tacticses;

	@Override
	public String delete() throws Exception {
		try {
			tacticsManager.deleteTactics(id);
			Struts2Utils.renderText("true");
		} catch (Exception e) {
			logger.error("删除特殊策略时：ID=" + id, e);
		}
		return null;
	}

	@Override
	public String input() throws Exception {
		return null;
	}

	@Override
	public String list() throws Exception {
		try {
			VenueInfo venueInfo = VenueInfoUtil.getVenueFromSession();
			tacticses = tacticsManager.findAllTacticses(venueInfo.getId());
		} catch (Exception e) {
			logger.error("获取特殊价格策略列表时：", e);
		}
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			entity = tacticsManager.getTactics(id);
		} else {
			entity = new Tactics();
			VenueInfo venueInfo = VenueInfoUtil.getVenueFromSession();
			entity.setVenueId(venueInfo.getId());
		}
	}

	@Override
	public String save() {
		try {
			tacticsManager.saveTactics(entity);
		} catch (Exception e) {
			logger.error("保存特殊策略时，" + entity, e);
		}
		return RELOAD;
	}
	
	public String findTactics() {
		try {
			prepareModel();
			Struts2Utils.renderJson(entity);
		} catch (Exception e) {
			logger.error("查询特殊策略时：ID=" + id, e);
		}
		return null;
	}

	//-- getter and setter --//
	public void setId(Long id) {
		this.id = id;
	}

	public Tactics getModel() {
		return entity;
	}

	public List<Tactics> getTacticses() {
		return tacticses;
	}

}
