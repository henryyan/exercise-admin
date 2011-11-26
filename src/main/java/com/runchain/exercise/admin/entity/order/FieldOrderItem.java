package com.runchain.exercise.admin.entity.order;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.runchain.arch.util.json.CustomDateTimeSerializer;
import com.runchain.arch.util.orm.IdEntity;
import com.runchain.exercise.admin.butil.field.FieldUtil;
import com.runchain.exercise.admin.entity.activity.AllFieldActivity;

/**
 * 活动订单项实体.
 *
 * @author HenryYan
 *
 */
@Entity
@Table(name = "t_field_order")
public class FieldOrderItem extends IdEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long accountOrderId;
	private Date bookTime;
	private Long cardId;
	private String contact;
	private Long fieldId;
	private String fieldType;
	private Boolean patch;
	private Float paymentCommision;
	private Boolean paymentStatus;
	private String paymentStyle;
	private Double paymentSum;
	private Date paymentTime;
	private String phone;
	private double standardPrice;
	private String userCode;
	private Long venueId;
	private Long activityId;
	private Long cardUsageId;
	private AllFieldActivity fieldActivity;

	public FieldOrderItem() {
	}

	@Column(name = "ACCOUNT_ORDER_ID")
	public Long getAccountOrderId() {
		return this.accountOrderId;
	}

	public void setAccountOrderId(Long accountOrderId) {
		this.accountOrderId = accountOrderId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BOOK_TIME")
	@JsonSerialize(using = CustomDateTimeSerializer.class)
	public Date getBookTime() {
		return this.bookTime;
	}

	public void setBookTime(Date bookTime) {
		this.bookTime = bookTime;
	}

	@Column(name = "CARD_ID")
	public Long getCardId() {
		return this.cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	@Column(name = "CONTACT")
	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@Column(name = "FIELD_ID")
	public Long getFieldId() {
		return this.fieldId;
	}

	public void setFieldId(Long fieldId) {
		this.fieldId = fieldId;
	}

	@Column(name = "FIELD_TYPE")
	public String getFieldType() {
		return this.fieldType;
	}
	
	@Transient
	public String getFieldZhType() {
		return FieldUtil.FIELD_EN_ZH_NAME.get(fieldType);
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	@Column(name = "PATCH")
	public Boolean getPatch() {
		return this.patch;
	}

	public void setPatch(Boolean patch) {
		this.patch = patch;
	}

	@Column(name = "PAYMENT_COMMISION")
	public Float getPaymentCommision() {
		return this.paymentCommision;
	}

	public void setPaymentCommision(Float paymentCommision) {
		this.paymentCommision = paymentCommision;
	}

	@Column(name = "PAYMENT_STATUS")
	public Boolean getPaymentStatus() {
		return this.paymentStatus;
	}

	public void setPaymentStatus(Boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	@Column(name = "PAYMENT_STYLE")
	public String getPaymentStyle() {
		return this.paymentStyle;
	}

	public void setPaymentStyle(String paymentStyle) {
		this.paymentStyle = paymentStyle;
	}

	@Column(name = "PAYMENT_SUM")
	public Double getPaymentSum() {
		return this.paymentSum;
	}

	public void setPaymentSum(Double paymentSum) {
		this.paymentSum = paymentSum;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PAYMENT_TIME")
	@JsonSerialize(using = CustomDateTimeSerializer.class)
	public Date getPaymentTime() {
		return this.paymentTime;
	}

	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}

	@Column(name = "PHONE")
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "STANDARD_PRICE")
	public double getStandardPrice() {
		return this.standardPrice;
	}

	public void setStandardPrice(double standardPrice) {
		this.standardPrice = standardPrice;
	}

	@Column(name = "USER_CODE")
	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	@Column(name = "VENUE_ID")
	public Long getVenueId() {
		return this.venueId;
	}

	public void setVenueId(Long venueId) {
		this.venueId = venueId;
	}

	@Column
	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	@Column
	public Long getCardUsageId() {
		return cardUsageId;
	}

	public void setCardUsageId(Long cardUsageId) {
		this.cardUsageId = cardUsageId;
	}

	@Transient
	public AllFieldActivity getFieldActivity() {
		return fieldActivity;
	}

	public void setFieldActivity(AllFieldActivity fieldActivity) {
		this.fieldActivity = fieldActivity;
	}
	
	

}