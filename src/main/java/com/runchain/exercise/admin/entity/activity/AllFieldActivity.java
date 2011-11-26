package com.runchain.exercise.admin.entity.activity;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.runchain.exercise.admin.butil.field.FieldUtil;
import com.runchain.exercise.admin.entity.order.FieldOrderItem;

/**
 * The persistent class for the t_v_all_activity database table.
 * 
 */
@Entity
@Table(name = "t_v_all_activity")
public class AllFieldActivity implements Serializable {
	private static final long serialVersionUID = 1L;
	private String activity;
	private String authenticode;
	private Long fieldId;
	private String fieldName;
	private String fieldType;
	private Time fromTime;
	private Long id;
	private FieldOrderItem fieldOrder;
	private String orderUser;
	private String period;
	private Float price;
	private Long tacticsId;
	private Time toTime;
	private Date usableDate;
	private Long venueId;
	private String verification;
	private Float paymentCommision;

	public AllFieldActivity() {
	}

	@Column(name = "ACTIVITY")
	public String getActivity() {
		return this.activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	@Column(name = "AUTHENTICODE")
	public String getAuthenticode() {
		return this.authenticode;
	}

	public void setAuthenticode(String authenticode) {
		this.authenticode = authenticode;
	}

	@Column(name = "FIELD_ID")
	public Long getFieldId() {
		return this.fieldId;
	}

	public void setFieldId(Long fieldId) {
		this.fieldId = fieldId;
	}

	@Column(name = "FIELD_NAME")
	public String getFieldName() {
		return this.fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	@Column(name = "FIELD_TYPE")
	public String getFieldType() {
		return this.fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	@Column(name = "FROM_TIME")
	public Time getFromTime() {
		return this.fromTime;
	}

	public void setFromTime(Time fromTime) {
		this.fromTime = fromTime;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id")
	public FieldOrderItem getFieldOrder() {
		return fieldOrder;
	}

	public void setFieldOrder(FieldOrderItem fieldOrder) {
		this.fieldOrder = fieldOrder;
	}

	@Column(name = "ORDER_USER")
	public String getOrderUser() {
		return this.orderUser;
	}

	public void setOrderUser(String orderUser) {
		this.orderUser = orderUser;
	}

	@Column(name = "PERIOD")
	public String getPeriod() {
		return this.period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	@Column(name = "PRICE")
	public Float getPrice() {
		return this.price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	@Column(name = "TACTICS_ID")
	public Long getTacticsId() {
		return this.tacticsId;
	}

	public void setTacticsId(Long tacticsId) {
		this.tacticsId = tacticsId;
	}

	@Column(name = "TO_TIME")
	public Time getToTime() {
		return this.toTime;
	}

	public void setToTime(Time toTime) {
		this.toTime = toTime;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "USABLE_DATE")
	public Date getUsableDate() {
		return this.usableDate;
	}

	public void setUsableDate(Date usableDate) {
		this.usableDate = usableDate;
	}

	@Column(name = "VENUE_ID")
	public Long getVenueId() {
		return this.venueId;
	}

	public void setVenueId(Long venueId) {
		this.venueId = venueId;
	}

	@Column(name = "VERIFICATION")
	public String getVerification() {
		return this.verification;
	}

	public void setVerification(String verification) {
		this.verification = verification;
	}

	public Float getPaymentCommision() {
		return paymentCommision;
	}

	public void setPaymentCommision(Float paymentCommision) {
		this.paymentCommision = paymentCommision;
	}

	@Transient
	public String getZhFieldType() {
		return FieldUtil.FIELD_EN_ZH_NAME.get(fieldType);
	}
	
}