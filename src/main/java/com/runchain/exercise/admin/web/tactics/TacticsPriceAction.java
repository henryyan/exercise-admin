package com.runchain.exercise.admin.web.tactics;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import com.opensymphony.xwork2.ActionSupport;
import com.runchain.exercise.admin.butil.venue.VenueInfoUtil;
import com.runchain.exercise.admin.entity.tactics.TacticsPrice;
import com.runchain.exercise.admin.entity.venue.VenueInfo;
import com.runchain.exercise.admin.service.tactics.TacticsManager;
import com.runchain.exercise.admin.service.tactics.TacticsPriceManager;
import com.runchain.arch.util.json.JSONUtil;

/**
 * 特殊策略价格Action
 *
 * @author HenryYan
 *
 */
@Namespace("/tactics")
@Result(location = "tacticsPriceList.jsp")
@Action("tacticsPrice")
public class TacticsPriceAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource TacticsPriceManager priceManager;
	@Resource TacticsManager tacticsManager;
	
	private Long id;
	private Long tacticsId;
	private List<TacticsPrice> allPrice;
	
	//-- 业务方法 --//
	public String delete() throws Exception {
		priceManager.deletePrice(id);
		return null;
	}

	/**
	 * 批量添加价格
	 * @return
	 */
	public String batchAdd() {
		try {
			HttpServletRequest request = Struts2Utils.getRequest();
			VenueInfo venueInfo = VenueInfoUtil.getVenueFromSession();
			Long venueId = venueInfo.getId();
			JSONObject jsonObject = JSONUtil.readJson(request);
			String tacticsId = jsonObject.getString("tacticsId");
			if (StringUtils.isEmpty(tacticsId)) {
				Struts2Utils.renderText("参数不完整tacticsId");
				return null;
			}
			priceManager.batchAdd(venueId, jsonObject);
			Struts2Utils.renderText("true");
		} catch (Exception e) {
			logger.error("批量添加特殊价格时：", e);
			Struts2Utils.renderText("false");
		}
		return null;
	}

	/**
	 * 更新价格
	 * @return
	 */
	public String updatePrice() {
		String strId = Struts2Utils.getParameter("id");
		String strPrice = Struts2Utils.getParameter("price");
		String priceType = Struts2Utils.getParameter("priceType");
		if (StringUtils.isNotEmpty(strId) && StringUtils.isNotEmpty(strPrice)) {
			TacticsPrice price = priceManager.getPrice(new Long(strId));
			if ("basic".equals(priceType)) {
				price.setPrice(new Integer(strPrice));
			} else if("protocol".equals(priceType)) {
				price.setPaymentCommision(new Float(strPrice));
			}
			priceManager.savePrice(price);
			Struts2Utils.renderText("true");
		} else {
			Struts2Utils.renderText("false");
		}
		return null;
	}
	
	@Override
	public String execute() throws Exception {
		allPrice = priceManager.findAllPrice(tacticsId);
		return SUCCESS;
	}

	//-- getter and setter --//
	public void setId(Long id) {
		this.id = id;
	}

	public void setTacticsId(Long tacticsId) {
		this.tacticsId = tacticsId;
	}

	public List<TacticsPrice> getAllPrice() {
		return allPrice;
	}

}
