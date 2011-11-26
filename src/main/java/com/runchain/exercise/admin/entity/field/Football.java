package com.runchain.exercise.admin.entity.field;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 场地类型-足球
 *
 * @author HenryYan
 *
 */
@Entity
@Table(name = "t_field_football")
public class Football extends FieldBasic implements Serializable {

private static final long serialVersionUID = 1L;

	public Football() {
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
