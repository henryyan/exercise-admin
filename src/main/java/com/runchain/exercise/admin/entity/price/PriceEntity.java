package com.runchain.exercise.admin.entity.price;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.runchain.arch.util.orm.IdEntity;

@MappedSuperclass
public class PriceEntity extends IdEntity {

	protected String fromTime;
	protected Integer price;
	protected String toTime;
	protected Long venueId;
	protected Float paymentCommision;

	@Column(name = "FROM_TIME", length = 5)
	public String getFromTime() {
		return this.fromTime;
	}

	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}

	@Column(name = "PRICE")
	public Integer getPrice() {
		return this.price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@Column(name = "TO_TIME", length = 5)
	public String getToTime() {
		return this.toTime;
	}

	public void setToTime(String toTime) {
		this.toTime = toTime;
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
	
}
