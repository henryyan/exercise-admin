package com.runchain.exercise.admin.entity.order;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.runchain.arch.util.orm.IdEntity;

/**
 * 付款订单
 *
 * @author HenryYan
 *
 */
@Entity
@Table(name = "t_account_order")
public class AccountOrder extends IdEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private Double payTotal;
	private String contact;
	private String phone;
	private Date payTime;
	private String payPlatform;
	private String platformAccount;
	private Long userId;
	private Boolean paymentStatus;
	private Long venueId;
	
	// 临时属性
	private Integer fieldOrderSize;

	@Column
	public Double getPayTotal() {
		return payTotal;
	}

	public void setPayTotal(Double payTotal) {
		this.payTotal = payTotal;
	}

	@Column
	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@Column
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column
	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	@Column
	public String getPayPlatform() {
		return payPlatform;
	}

	public void setPayPlatform(String payPlatform) {
		this.payPlatform = payPlatform;
	}

	@Column
	public String getPlatformAccount() {
		return platformAccount;
	}

	public void setPlatformAccount(String platformAccount) {
		this.platformAccount = platformAccount;
	}

	@Column
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column
	public Boolean getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(Boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	@Column
	public Long getVenueId() {
		return venueId;
	}

	public void setVenueId(Long venueId) {
		this.venueId = venueId;
	}
	
	/**
	 * 对应的活动订单数量
	 * @return
	 */
	@Transient
	public Integer getFieldOrderSize() {
		return fieldOrderSize;
	}

	public void setFieldOrderSize(Integer fieldOrderSize) {
		this.fieldOrderSize = fieldOrderSize;
	}

}
