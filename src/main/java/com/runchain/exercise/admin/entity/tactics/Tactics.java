package com.runchain.exercise.admin.entity.tactics;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.runchain.exercise.admin.butil.field.FieldUtil;
import com.runchain.arch.util.orm.IdEntity;

/**
 * 策略实体.
 *
 * @author HenryYan
 *
 */
@Entity
@Table(name = "t_tactics")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "tacticsDates", "tacticsPrices"})
public class Tactics extends IdEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String fieldType;
	private Boolean isModify = false;
	private String remark;
	private String tacticsName;
	private Long venueId;
	private List<TacticsDate> tacticsDates;
	private List<TacticsPrice> tacticsPrices;

	public Tactics() {
	}

	@Column(name = "FIELD_TYPE", length = 20)
	public String getFieldType() {
		return this.fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	@Column(name = "IS_MODIFY")
	public Boolean getIsModify() {
		return this.isModify;
	}

	public void setIsModify(Boolean isModify) {
		this.isModify = isModify;
	}

	@Lob()
	@Column(name = "REMARK")
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "TACTICS_NAME", length = 50)
	public String getTacticsName() {
		return this.tacticsName;
	}

	public void setTacticsName(String tacticsName) {
		this.tacticsName = tacticsName;
	}

	@Column(name = "VENUE_ID")
	public Long getVenueId() {
		return this.venueId;
	}

	public void setVenueId(Long venueId) {
		this.venueId = venueId;
	}

	//bi-directional many-to-one association to TacticsDate
	@OneToMany(mappedBy = "tactics", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	public List<TacticsDate> getTacticsDates() {
		return this.tacticsDates;
	}

	public void setTacticsDates(List<TacticsDate> tacticsDates) {
		this.tacticsDates = tacticsDates;
	}

	//bi-directional many-to-one association to TacticsPrice
	@OneToMany(mappedBy = "tactics", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	public List<TacticsPrice> getTacticsPrices() {
		return this.tacticsPrices;
	}

	public void setTacticsPrices(List<TacticsPrice> tacticsPrices) {
		this.tacticsPrices = tacticsPrices;
	}
	
	@Transient
	public String getFieldTypeZh() {
		return FieldUtil.FIELD_EN_ZH_NAME.get(fieldType);
	}
	
	@Transient
	public long getTacticsDatesSize() {
		return tacticsDates.size();
	}

	@Transient
	public long getTacticsPricesSize() {
		return tacticsPrices.size();
	}
	
	/**
	 * 本策略是否设置了策略日期和价格
	 * @return	设置了返回true，否则false
	 */
	@Transient
	public boolean hasDateAndPrice() {
		// 判断是否有策略日期
		boolean hasDateAndPrice = true;
		
		if (this.getTacticsDates().isEmpty() || this.getTacticsPrices().isEmpty()) {
			hasDateAndPrice = false;
		}
		return hasDateAndPrice;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}