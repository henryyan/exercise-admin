package com.runchain.exercise.admin.web.bdata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springside.modules.utils.web.struts2.Struts2Utils;

import com.runchain.arch.web.base.BaseActionSupport;
import com.runchain.arch.web.base.CrudActionSupport;
import com.runchain.exercise.admin.entity.bdata.AreaInfo;
import com.runchain.exercise.admin.service.bdata.AreaInfoManager;

/**
 * 地区信息树读取Action
 *
 * @author HenryYan
 *
 */
@Controller
@Results(value = { @Result(name = CrudActionSupport.JSON, type = CrudActionSupport.JSON) })
public class AreaInfoTreeAction extends BaseActionSupport {

	private static final long serialVersionUID = 1L;
	
	@Autowired AreaInfoManager manager;
	
	private Long parentId;

	@Override
	public String execute() throws Exception {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			List<AreaInfo> findChildAreaInfo = manager.findChildAreaInfo(parentId);
			Map<String, Object> attrTempMap = null;
			
			for (AreaInfo area : findChildAreaInfo) {
				Map<String, Object> tempMap = new HashMap<String, Object>();
				Long countChildAreaInfo = manager.countChildAreaInfo(area.getId());
				
				// 分为两种情况：有下属区县、没有下属区县
				if (countChildAreaInfo > 0) {
					// 关闭状态，可以触发ajax load 事件
					tempMap.put("state", "closed");
					
					Map<String, Object> subMap = new HashMap<String, Object>();
					// 节点的显示文本
					subMap.put("title", area.getAreaName());
					
					// 设置属性集合
					Map<String, Object> singleAttr = new HashMap<String, Object>();
					// html元素的title
					singleAttr.put("title", "共 " + countChildAreaInfo + " 个下属地区，备注：" + area.getRemark());
					subMap.put("attr", singleAttr);
					tempMap.put("data", subMap);
				} else {
					Map<String, Object> subMap = new HashMap<String, Object>();
					// 节点的显示文本
					subMap.put("title", area.getAreaName());
					
					// 设置属性集合
					Map<String, Object> singleAttr = new HashMap<String, Object>();
					// html元素的title
					singleAttr.put("title", area.getRemark());
					// 标志为叶子节点
					singleAttr.put("leaf", true);
					subMap.put("attr", singleAttr);
					tempMap.put("data", subMap);
					
				}
				
				// 设置属性
				attrTempMap = setLabelAttrs(area);
				tempMap.put("attr", attrTempMap);
				
				list.add(tempMap);
			}
			logger.debug("获取树结果：{}", list);
			Struts2Utils.renderJson(list);
		} catch (Exception e) {
			logger.error("获取下属区县出错：{}", e.getMessage(), e);
		}
		return CrudActionSupport.JSON;
	}
	
	/**
	 * 设置Label标签的属性
	 * @param area	地区
	 */
	private Map<String, Object> setLabelAttrs(AreaInfo area) {
		Map<String, Object> tempMap = new HashMap<String, Object>();
		tempMap.put("id", area.getId());
		tempMap.put("level", area.getAreaLevel());
		return tempMap;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
}
