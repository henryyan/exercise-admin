package com.runchain.exercise.admin.web.bdata;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import com.runchain.arch.web.base.BaseActionSupport;
import com.runchain.arch.web.base.CrudActionSupport;
import com.runchain.exercise.admin.butil.bdata.AreaInfoUtil;
import com.runchain.exercise.admin.entity.bdata.AreaInfo;
import com.runchain.exercise.admin.service.bdata.AreaInfoService;

/**
 * 地区信息Action
 *
 * @author HenryYan
 *
 */
@Controller
@Result(name = CrudActionSupport.JSON, type = CrudActionSupport.JSON)
public class AreaInfoAction extends BaseActionSupport {

private static final long serialVersionUID = 1L;
	
	private Logger logger = LoggerFactory.getLogger(AreaInfoAction.class);

	@Autowired AreaInfoService areaService;
	
	private Integer level;
	private Long parentId;
	private Long childId;
	private String parentName;
	
	/**
	 * 根据级别、父ID信息输出JSON格式的地区信息
	 * @return
	 */
	public String findArea() {
		List<AreaInfo> areas = null;
		// 第一级
		if (level != null && level == AreaInfoUtil.TOP_LEVEL) {
			areas = areaService.getAreaByLevel(level);
			logger.debug("获取第 {} 级地区，个数：{}", level, areas.size());
		}
		// 从以此名字的下级显示，例如设置了”上海市“，则页面显示的是上海市下面的所有区县
		else if (StringUtils.isNotBlank(parentName)) {
			areas = areaService.getArea(parentName);
		} 
		// 读取parent_id为parentId的地区信息
		else if (parentId != null) {
			// 子级别
			areas = areaService.getAreaByParent(parentId);
			logger.debug("获取父级：{}，地区，个数：{}", parentId, areas.size());
		} else {
			areas = new ArrayList<AreaInfo>();
		}
		Struts2Utils.renderJson(areas);
		return null;
	}
	
	/**
	 * 生成完整的带默认值的select元素HTML代码
	 */
	public String htmlCode() {
		try {
			String htmlCode = areaService.generateSelectHtmlCode(childId);
			Struts2Utils.renderHtml(htmlCode);
		} catch (Exception e) {
			logger.error("生成完整的带默认值的select元素HTML代码：", e);
		}
		return null;
	}
	
	/* getters and setters */
	public void setLevel(Integer level) {
		this.level = level;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public void setChildId(Long childId) {
		this.childId = childId;
	}

	public void setParentName(String parentName) throws UnsupportedEncodingException {
		if (StringUtils.isNotBlank(parentName)) {
			parentName = URLDecoder.decode(parentName, "UTF-8");
		}
		this.parentName = parentName;
	}
	
}
