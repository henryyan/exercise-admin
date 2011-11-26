package com.runchain.exercise.admin.service.bdata;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runchain.exercise.admin.butil.bdata.DataLibraryUtil;
import com.runchain.exercise.admin.entity.bdata.Datalibrary;

/**
 * 数据字典初始化类
 * 
 * @author fangjian
 * 
 */
@Service
public class DataLibraryList {

	@Autowired
	private DatalibraryManager datalibraryManager;

	private DataLibraryList() {

	}

	/**
	 * 数据字典初始化：将数据写入缓存
	 */
	public void init() {
		List<Datalibrary> dataLibraryList = datalibraryManager.getAllDatalibrarys();
		for (int i = 0; i < dataLibraryList.size(); i++) {
			Datalibrary datalibrary = dataLibraryList.get(i);
			String key = datalibrary.getLibrarycode();
			
			/*
			 * 所有的数组字典
			 */
			DataLibraryUtil.getDataLibraryList().put(key, datalibrary);
			
			/*
			 * 根据library类型分组添加到Map中
			 */
			DataLibraryUtil.addToTypeMap(datalibrary);
		}
	}

	/**
	 * 重载数据字典
	 */
	public void reload() {
		DataLibraryUtil.clearDatas();
		init();
	}
	
}
