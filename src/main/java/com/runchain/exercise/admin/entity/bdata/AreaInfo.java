package com.runchain.exercise.admin.entity.bdata;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.runchain.exercise.admin.butil.bdata.AreaInfoUtil;

/**
 * 城市实体.
 *
 * @author HenryYan
 *
 */
@Entity
@Table(name = "t_bd_areainfo")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AreaInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String areaCode;
	private Integer areaLevel;
	private String areaName;
	private String areaNumber;
	private String countryCode;
	private Boolean enabled;
	private Long parentAreaId;
	private String remark;
	private Integer sort;

	public AreaInfo() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "area_code")
	public String getAreaCode() {
		return this.areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	@Column(name = "area_level")
	public Integer getAreaLevel() {
		return this.areaLevel;
	}

	public void setAreaLevel(Integer areaLevel) {
		this.areaLevel = areaLevel;
	}

	@Column(name = "area_name")
	public String getAreaName() {
		return this.areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	@Column(name = "area_number")
	public String getAreaNumber() {
		return this.areaNumber;
	}

	public void setAreaNumber(String areaNumber) {
		this.areaNumber = areaNumber;
	}

	@Column(name = "country_code")
	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@Column(name = "enabled")
	public Boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Column(name = "parent_area_id")
	public Long getParentAreaId() {
		return parentAreaId;
	}

	public void setParentAreaId(Long parentAreaId) {
		this.parentAreaId = parentAreaId;
	}

	@Transient
	public AreaInfo getParent() {
		return AreaInfoUtil.getArea(parentAreaId);
	}

	@Column(name = "remark")
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "sort")
	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}