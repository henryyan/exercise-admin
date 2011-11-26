package com.runchain.exercise.admin.entity.field.type;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.runchain.exercise.admin.entity.venue.VenueInfo;
import com.runchain.arch.util.orm.BaseEntity;

/**
 * 场馆和场地类型关联中间实体.
 *
 * @author HenryYan
 *
 */
@Entity
@Table(name="t_link_venue_fieldtype")
public class VenueFieldtypeLink extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private FieldType fieldType;
	private VenueInfo venueInfo;

    public VenueFieldtypeLink() {
    }

    public VenueFieldtypeLink(VenueInfo venueInfo, FieldType fieldType) {
		super();
		this.venueInfo = venueInfo;
		this.fieldType = fieldType;
	}

	@ManyToOne
	@JoinColumn(name="field_type_id", nullable=false)
	public FieldType getFieldType() {
		return this.fieldType;
	}

	public void setFieldType(FieldType TFieldType) {
		this.fieldType = TFieldType;
	}
	

    @ManyToOne
	@JoinColumn(name="venue_id", nullable=false)
	public VenueInfo getVenueInfo() {
		return this.venueInfo;
	}

	public void setVenueInfo(VenueInfo TVenueInfo) {
		this.venueInfo = TVenueInfo;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}