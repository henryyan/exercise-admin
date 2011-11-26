package com.runchain.exercise.admin.entity.price.football;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.runchain.exercise.admin.entity.price.PriceEntity;

/**
 * 价格实体：足球-周末
 *
 * @author HenryYan
 *
 */
@Entity
@Table(name = "t_field_football_weekend_price")
public class FootballWeekendPrice extends PriceEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public FootballWeekendPrice() {
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}