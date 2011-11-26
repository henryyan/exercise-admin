package com.runchain.exercise.admin.entity.common;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 自动完成类，封装对应于jQuery.ui的自动完成功能
 *
 * @author HenryYan
 *
 */
public class AutoComplete {

	private String id;
	private String label;
	private String value;
	private String category;

	public AutoComplete() {
		super();
	}

	public AutoComplete(String id, String label, String value, String category) {
		super();
		this.id = id;
		this.label = label;
		this.value = value;
		this.category = category;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
