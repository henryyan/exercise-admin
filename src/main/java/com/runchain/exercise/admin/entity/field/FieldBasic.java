package com.runchain.exercise.admin.entity.field;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.runchain.arch.util.orm.IdEntity;

/**
 * 所以场地类型实体集成本类
 *
 * @author HenryYan
 *
 */
@MappedSuperclass
public abstract class FieldBasic extends IdEntity {

	protected int advance;
	protected Date createDate;
	protected String envType;
	protected String fieldCode;
	protected int issueAdvance;
	protected Date issueLastDate;
	protected String name;
	protected String status;
	protected Long venueId;

	@Column(name = "ADVANCE")
	public int getAdvance() {
		return this.advance;
	}

	public void setAdvance(int advance) {
		this.advance = advance;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_DATE", updatable = false)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "ENV_TYPE", length = 20)
	public String getEnvType() {
		return this.envType;
	}

	public void setEnvType(String envType) {
		this.envType = envType;
	}

	@Column(name = "FIELD_CODE", length = 255)
	public String getFieldCode() {
		return this.fieldCode;
	}

	public void setFieldCode(String fieldCode) {
		this.fieldCode = fieldCode;
	}

	@Column(name = "ISSUE_ADVANCE")
	public int getIssueAdvance() {
		return this.issueAdvance;
	}

	public void setIssueAdvance(int issueAdvance) {
		this.issueAdvance = issueAdvance;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ISSUE_LAST_DATE")
	public Date getIssueLastDate() {
		return this.issueLastDate;
	}

	public void setIssueLastDate(Date issueLastDate) {
		this.issueLastDate = issueLastDate;
	}

	@Column(name = "NAME", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "STATUS", length = 2)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "VENUE_ID")
	public Long getVenueId() {
		return this.venueId;
	}

	public void setVenueId(Long venueId) {
		this.venueId = venueId;
	}
}
