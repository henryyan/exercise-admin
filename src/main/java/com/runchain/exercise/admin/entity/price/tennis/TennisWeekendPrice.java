package com.runchain.exercise.admin.entity.price.tennis;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.runchain.exercise.admin.entity.price.PriceEntity;

/**
 * 价格实体：网球-周末
 *
 * @author HenryYan
 *
 */
@Entity
@Table(name = "t_field_tennis_weekend_price")
public class TennisWeekendPrice extends PriceEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public TennisWeekendPrice() {
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}