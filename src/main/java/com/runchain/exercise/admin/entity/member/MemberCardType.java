package com.runchain.exercise.admin.entity.member;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.runchain.exercise.admin.butil.member.MemberUtil;
import com.runchain.arch.util.orm.BaseEntity;

/**
 * 会员卡类型实体.
 *
 * @author HenryYan
 *
 */
@Entity
@Table(name = "t_card_type")
@JsonIgnoreProperties(value = { "memberCards", "hibernateLazyInitializer", "handler" })
public class MemberCardType extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private Float discountPrice;
	private Integer discountRate;
	private String discountTime;
	private String discountType;
	private Float paymentCommision;
	private Float moneyAmount;
	private Integer periodMonth;
	private String typeName;
	private Long venueId;
	private String describtion;
	private List<MemberCard> memberCards;

	public MemberCardType() {
	}

	@Column(name = "DISCOUNT_PRICE")
	public Float getDiscountPrice() {
		return this.discountPrice;
	}

	public void setDiscountPrice(Float discountPrice) {
		this.discountPrice = discountPrice;
	}

	@Column(name = "discount_rate")
	public Integer getDiscountRate() {
		return this.discountRate;
	}

	public void setDiscountRate(Integer discountRate) {
		this.discountRate = discountRate;
	}

	@Column(name = "discount_time", length = 10)
	public String getDiscountTime() {
		return this.discountTime;
	}

	public void setDiscountTime(String discountTime) {
		this.discountTime = discountTime;
	}

	@Column(name = "DISCOUNT_TYPE", length = 2)
	public String getDiscountType() {
		return this.discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}

	@Column(name = "money_amount")
	public Float getMoneyAmount() {
		return this.moneyAmount;
	}

	public void setMoneyAmount(Float moneyAmount) {
		this.moneyAmount = moneyAmount;
	}

	@Column(name = "period_month")
	public Integer getPeriodMonth() {
		return this.periodMonth;
	}

	public void setPeriodMonth(Integer periodMonth) {
		this.periodMonth = periodMonth;
	}

	@Column(name = "type_name", length = 50)
	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Column(name = "VENUE_ID")
	public Long getVenueId() {
		return this.venueId;
	}

	public void setVenueId(Long venueId) {
		this.venueId = venueId;
	}
	
	@Column(name = "PAYMENT_COMMISION", precision = 5, scale = 2)
	public Float getPaymentCommision() {
		return paymentCommision;
	}

	public void setPaymentCommision(Float paymentCommision) {
		this.paymentCommision = paymentCommision;
	}

	@Column(name = "DESCRIBTION", length = 255)
	public String getDescribtion() {
		return describtion;
	}

	public void setDescribtion(String describtion) {
		this.describtion = describtion;
	}

	//bi-directional many-to-one association to MemberCard
	@OneToMany(mappedBy = "cardType")
	public List<MemberCard> getMemberCards() {
		return this.memberCards;
	}

	public void setMemberCards(List<MemberCard> memberCards) {
		this.memberCards = memberCards;
	}
	
	/**
	 * 返回中文的会员卡打折类型
	 * @return
	 */
	@Transient
	public String getDiscountZhType() {
		return MemberUtil.getDiscountType(discountType);
	}

	/**
	 * 折扣场次
	 * @return
	 */
	@Transient
	public int getDiscountSession() {
		if (StringUtils.isEmpty(discountType) || moneyAmount == null || moneyAmount == 0 || discountPrice == null
				|| discountPrice == 0) {
			return 0;
		}
		return (int) (moneyAmount / discountPrice);
	}

	@Transient
	public boolean getCommonDay() {
		return StringUtils.isNotEmpty(this.discountTime) && this.discountTime.startsWith("1");
	}

	@Transient
	public boolean getWeekDay() {
		return StringUtils.isNotEmpty(this.discountTime) && this.discountTime.indexOf("1", 1) == 1;
	}

}