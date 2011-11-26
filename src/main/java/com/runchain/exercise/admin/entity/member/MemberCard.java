package com.runchain.exercise.admin.entity.member;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.runchain.arch.util.orm.IdEntity;

/**
 * 场馆会员卡实体.
 *
 * @author HenryYan
 *
 */
@Entity
@Table(name = "t_member_card")
public class MemberCard extends IdEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private String address;
	private double balance;
	private String cardNumber;
	private Date createDate;
	private Date effectDate;
	private String idNo;
	private String mobilePhone;
	private String name;
	private Date periodValidity;
	private Long venueId;
	private MemberCardType cardType;

	public MemberCard() {
	}

	@Column(name = "ADDRESS", length = 200)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "BALANCE")
	public double getBalance() {
		return this.balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Column(name = "CARD_NUMBER", length = 10)
	public String getCardNumber() {
		return this.cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE")
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "EFFECT_DATE")
	public Date getEffectDate() {
		return this.effectDate;
	}

	public void setEffectDate(Date effectDate) {
		this.effectDate = effectDate;
	}

	@Column(name = "ID_NO", length = 50)
	public String getIdNo() {
		return this.idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	@Column(name = "MOBILE_PHONE", length = 11)
	public String getMobilePhone() {
		return this.mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	@Column(name = "NAME", length = 15)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PERIOD_VALIDITY")
	public Date getPeriodValidity() {
		return this.periodValidity;
	}

	public void setPeriodValidity(Date periodValidity) {
		this.periodValidity = periodValidity;
	}

	@Column(name = "VENUE_ID")
	public Long getVenueId() {
		return this.venueId;
	}

	public void setVenueId(Long venueId) {
		this.venueId = venueId;
	}

	@Transient
	public String getCardStatus() {
		long millis = System.currentTimeMillis();
		if (this.getEffectDate() != null) {
			if (this.getEffectDate().getTime() > millis) {
				return "未生效";
			}
		}
		if (this.getPeriodValidity() != null) {
			if (this.getPeriodValidity().getTime() < millis) {
				return "过期";
			} else if (this.getPeriodValidity().getTime() >= millis) {
				return "正常";
			}
		}
		return "未知";
	}

	//bi-directional many-to-one association to CardType
	@ManyToOne
	@JoinColumn(name = "CARD_TYPE_ID")
	public MemberCardType getCardType() {
		return this.cardType;
	}

	public void setCardType(MemberCardType cardType) {
		this.cardType = cardType;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}