package com.runchain.exercise.admin.entity.tactics;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.runchain.arch.util.orm.IdEntity;

/**
 * 策略日期实体.
 *
 * @author HenryYan
 *
 */
@Entity
@Table(name = "t_tactics_date")
public class TacticsDate extends IdEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date fromDate;
	private Date toDate;
	private Long venueId;
	private Tactics tactics;

	public TacticsDate() {
	}

	@Column(name = "FROM_DATE")
	public Date getFromDate() {
		return this.fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	@Column(name = "TO_DATE")
	public Date getToDate() {
		return this.toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
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
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}