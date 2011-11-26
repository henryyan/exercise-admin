package com.runchain.exercise.admin.web.tactics;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import com.runchain.arch.web.base.CrudActionSupport;
import com.runchain.exercise.admin.butil.venue.VenueInfoUtil;
import com.runchain.exercise.admin.entity.tactics.Tactics;
import com.runchain.exercise.admin.entity.tactics.TacticsDate;
import com.runchain.exercise.admin.entity.venue.VenueInfo;
import com.runchain.exercise.admin.service.tactics.TacticsDateManager;
import com.runchain.exercise.admin.service.tactics.TacticsManager;

/**
 * 特殊策略日期Action
 *
 * @author HenryYan
 *
 */
@Namespace("/tactics")
@Action("tacticsDate")
public class TacticsDateAction extends CrudActionSupport<TacticsDate> {

	private static final long serialVersionUID = 1L;

	@Resource
	TacticsDateManager dateManager;
	
	@Resource TacticsManager tacticsManager;

	//-- 页面属性 --//
	private Long id;
	private Long tacticsId;
	private TacticsDate tacticsDate;

	@Override
	public String delete() throws Exception {
		try {
			dateManager.deleteTacticsDate(id);
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
			List<TacticsDate> findAllTacticsDate = dateManager.findAllTacticsDate(tacticsId);
			Struts2Utils.renderJson(findAllTacticsDate);
		} catch (Exception e) {
			logger.error("获取特殊价格策略列表时：", e);
		}
		return null;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (id != null) {
			tacticsDate = dateManager.getTacticsDate(id);
		} else {
			tacticsDate = new TacticsDate();
			VenueInfo venueInfo = VenueInfoUtil.getVenueFromSession();
			tacticsDate.setVenueId(venueInfo.getId());
		}
	}

	@Override
	public String save() {
		try {
			Tactics tactics = null;
			if (tacticsId == null) {
				Struts2Utils.renderText("没有找到相应策略");
				return null;
			} else {
				tactics = tacticsManager.getTactics(tacticsId);
			}
			if (tacticsDate.getFromDate() == null || tacticsDate.getToDate() == null) {
				Struts2Utils.renderText("日期段不能为空");
				return null;
			}
			
			// 检查是否有包含此策略日期的策略
			Long venueId = VenueInfoUtil.getVenueFromSession().getId();
			JSONObject checkRepeatDates = dateManager.checkRepeatDates(venueId, tactics, tacticsDate);
			if (checkRepeatDates.getBoolean("repeat")) {
				Struts2Utils.renderJson(checkRepeatDates.toString());
				return null;
			}
			
			// 保存策略日期和修改策略状态
			tacticsDate.setTactics(tactics);
			dateManager.saveTacticsDate(tacticsDate);
			// 设置更新状态
			if (!tactics.getIsModify()) {
				tactics.setIsModify(true);
				tacticsManager.saveTactics(tactics);
			}

			Struts2Utils.renderText("true");
			
		} catch (Exception e) {
			logger.error("保存特殊策略时，" + tacticsDate, e);
			Struts2Utils.renderText("false");
		}
		return null;
	}

	//-- getter and setter --//
	public void setId(Long id) {
		this.id = id;
	}

	public void setTacticsId(Long tacticsId) {
		this.tacticsId = tacticsId;
	}

	public TacticsDate getModel() {
		return tacticsDate;
	}

}
