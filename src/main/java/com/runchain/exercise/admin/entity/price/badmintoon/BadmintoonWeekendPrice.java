package com.runchain.exercise.admin.entity.price.badmintoon;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.runchain.exercise.admin.entity.price.PriceEntity;

/**
 * 价格实体：羽毛球-周末
 *
 * @author HenryYan
 *
 */
@Entity
@Table(name = "t_field_badmintoon_weekend_price")
public class BadmintoonWeekendPrice extends PriceEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	public BadmintoonWeekendPrice() {
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}