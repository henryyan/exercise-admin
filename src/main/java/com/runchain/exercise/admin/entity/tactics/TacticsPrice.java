package com.runchain.exercise.admin.entity.tactics;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.runchain.arch.util.orm.IdEntity;

/**
 * 策略价格实体.
 *
 * @author HenryYan
 *
 */
@Entity
@Table(name = "t_tactics_price")
public class TacticsPrice extends IdEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String fromTime;
	private Integer price;
	private Float paymentCommision;
	private String toTime;
	private Long venueId;
	private Tactics tactics;

	public TacticsPrice() {
	}

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

	//bi-directional many-to-one association to Tactic
	@ManyToOne
	@JoinColumn(name = "TACTICS_ID")
	public Tactics getTactics() {
		return this.tactics;
	}

	public void setTactics(Tactics tactics) {
		this.tactics = tactics;
	}
	
	@Column(name = "PAYMENT_COMMISION", precision = 5, scale = 2)
	public Float getPaymentCommision() {
		return paymentCommision;
	}

	public void setPaymentCommision(Float paymentCommision) {
		this.paymentCommision = paymentCommision;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}