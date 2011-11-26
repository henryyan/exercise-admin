package com.runchain.exercise.admin.entity.venue;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.runchain.exercise.admin.entity.field.type.VenueFieldtypeLink;
import com.runchain.arch.util.orm.BaseEntity;

/**
 * 场馆信息实体.
 *
 * @author HenryYan
 *
 */
@Entity
@Table(name = "t_venue_info")
@JsonIgnoreProperties(value = {"venueFieldTypeLinks", "hibernateLazyInitializer", "handler"})
public class VenueInfo extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String adress;
	private String area;
	private String authenticode;
	private String cell;
	private String city;
	private String closeTime;
	private String contact;
	private String district;
	private String email;
	private String fax;
	private String intraduction;
	private String openTime;
	private String phone;
	private String photoUrl;
	private Boolean sendSms;
	private String venueName;
	private Boolean verification;
	private String zipcode;
	private Boolean isProtocol;
	private List<VenueFieldtypeLink> venueFieldTypeLinks;

	public VenueInfo() {
	}

	public VenueInfo(Long id, Boolean isProtocol) {
		super();
		this.id = id;
		this.isProtocol = isProtocol;
	}

	public VenueInfo(Long id, String adress, String area, String authenticode, String cell, String city, String closeTime,
			String contact, String district, String email, String fax, String intraduction, String openTime,
			String phone, String photoUrl, Boolean sendSms, String venueName, Boolean verification, String zipcode,
			Boolean isProtocol) {
		super();
		this.id = id;
		this.adress = adress;
		this.area = area;
		this.authenticode = authenticode;
		this.cell = cell;
		this.city = city;
		this.closeTime = closeTime;
		this.contact = contact;
		this.district = district;
		this.email = email;
		this.fax = fax;
		this.intraduction = intraduction;
		this.openTime = openTime;
		this.phone = phone;
		this.photoUrl = photoUrl;
		this.sendSms = sendSms;
		this.venueName = venueName;
		this.verification = verification;
		this.zipcode = zipcode;
		this.isProtocol = isProtocol;
	}

	@Column(name = "ADRESS")
	public String getAdress() {
		return this.adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	@Column(name = "AREA")
	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Column(name = "AUTHENTICODE")
	public String getAuthenticode() {
		return this.authenticode;
	}

	public void setAuthenticode(String authenticode) {
		this.authenticode = authenticode;
	}

	@Column(name = "CELL")
	public String getCell() {
		return this.cell;
	}

	public void setCell(String cell) {
		this.cell = cell;
	}

	@Column(name = "CITY")
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "CLOSE_TIME")
	public String getCloseTime() {
		return this.closeTime;
	}

	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}

	@Column(name = "CONTACT")
	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@Column(name = "DISTRICT")
	public String getDistrict() {
		return this.district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	@Column(name = "EMAIL")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "FAX")
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Lob()
	@Column(name = "INTRADUCTION")
	public String getIntraduction() {
		return this.intraduction;
	}

	public void setIntraduction(String intraduction) {
		this.intraduction = intraduction;
	}

	@Column(name = "OPEN_TIME")
	public String getOpenTime() {
		return this.openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	@Column(name = "PHONE")
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Lob()
	@Column(name = "PHOTO_URL")
	public String getPhotoUrl() {
		return this.photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	@Column(name = "send_sms")
	public Boolean getSendSms() {
		return this.sendSms;
	}

	public void setSendSms(Boolean sendSms) {
		this.sendSms = sendSms;
	}

	@Column(name = "VENUE_NAME")
	public String getVenueName() {
		return this.venueName;
	}

	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}

	@Column(name = "VERIFICATION")
	public Boolean getVerification() {
		return this.verification;
	}

	public void setVerification(Boolean verification) {
		this.verification = verification;
	}

	@Column(name = "ZIPCODE")
	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	@OneToMany(mappedBy="venueInfo", fetch = FetchType.LAZY)
	//Fecth策略定义
	@Fetch(FetchMode.SUBSELECT)
	//集合按id排序.
	@OrderBy("id")
	//集合中对象id的缓存.
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<VenueFieldtypeLink> getVenueFieldTypeLinks() {
		return this.venueFieldTypeLinks;
	}

	public void setVenueFieldTypeLinks(List<VenueFieldtypeLink> venueFieldTypeLinks) {
		this.venueFieldTypeLinks = venueFieldTypeLinks;
	}
	
	@Column(name = "IS_PROTOCOL")
	public Boolean getIsProtocol() {
		return isProtocol == null ? false : isProtocol;
	}

	public void setIsProtocol(Boolean isProtocol) {
		this.isProtocol = isProtocol;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}