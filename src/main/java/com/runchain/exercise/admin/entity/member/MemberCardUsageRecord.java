package com.runchain.exercise.admin.entity.member;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.runchain.arch.util.orm.IdEntity;

/**
 * 会员卡使用记录实体类.
 *
 * @author HenryYan
 *
 */
@Entity
@Table(name = "t_card_usage_record")
public class MemberCardUsageRecord extends IdEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long cardId;
	private String cardNo;
	private Float optionTotal;
	private String signature;
	private Date usageDate;
	private String usageTimeSlice;
	private String usageType;
	private Long venueId;

	public MemberCardUsageRecord() {
	}

	@Column(name = "card_id")
	public Long getCardId() {
		return this.cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	@Column(name = "card_no")
	public String getCardNo() {
		return this.cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	@Column(name = "option_total")
	public Float getOptionTotal() {
		return this.optionTotal;
	}

	public void setOptionTotal(Float optionTotal) {
		this.optionTotal = optionTotal;
	}

	public String getSignature() {
		return this.signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "usage_date")
	public Date getUsageDate() {
		return this.usageDate;
	}

	public void setUsageDate(Date usageDate) {
		this.usageDate = usageDate;
	}

	@Column(name = "usage_time_slice")
	public String getUsageTimeSlice() {
		return this.usageTimeSlice;
	}

	public void setUsageTimeSlice(String usageTimeSlice) {
		this.usageTimeSlice = usageTimeSlice;
	}

	@Column(name = "usage_type")
	public String getUsageType() {
		return this.usageType;
	}

	public void setUsageType(String usageType) {
		this.usageType = usageType;
	}

	@Column(name = "VENUE_ID")
	public Long getVenueId() {
		return this.venueId;
	}

	public void setVenueId(Long venueId) {
		this.venueId = venueId;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}