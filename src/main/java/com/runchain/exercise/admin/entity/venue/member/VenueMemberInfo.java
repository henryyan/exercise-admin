package com.runchain.exercise.admin.entity.venue.member;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.runchain.arch.util.orm.BaseEntity;

/**
 * 场馆会员实体类.
 *
 * @author HenryYan
 *
 */
@Entity
@Table(name="t_venue_member_info")
public class VenueMemberInfo extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ADRESS")
	private String adress;

	private String bussinessTime;

	@Column(name="CITY")
	private String city;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	@Column(name="DISTRICT")
	private String district;

	@Column(name="FAX")
	private String fax;

	@Column(name="MEMBER_PRICE")
	private String memberPrice;

	@Column(name="MEMBER_YN")
	private String memberYn;

	@Column(name="MON_FRI")
	private String monFri;

	@Column(name="PEOPLE_NUM")
	private String peopleNum;

	@Column(name="PHONE1")
	private String phone1;

	@Column(name="PHONE2")
	private String phone2;

	@Column(name="TIMEI_PRICE1")
	private String timeiPrice1;

	@Column(name="TIMEI_PRICE2")
	private String timeiPrice2;

	@Column(name="TIMEI_PRICE3")
	private String timeiPrice3;

	@Column(name="TIMEI_PRICE4")
	private String timeiPrice4;

	@Column(name="VENUE_ICNO")
	private String venueIcno;

	@Column(name="VENUE_NAME")
	private String venueName;

	@Column(name="VENUE_PASSWORD")
	private String venuePassword;

	@Column(name="VENUE_REPASSWORD")
	private String venueRepassword;

	@Column(name="WEEKEND")
	private String weekend;

    public VenueMemberInfo() {
    }

	public String getAdress() {
		return this.adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	@Column(name="BUSSINESS_TIMEI")
	public String getBussinessTime() {
		return bussinessTime;
	}

	public void setBussinessTime(String bussinessTime) {
		this.bussinessTime = bussinessTime;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getDistrict() {
		return this.district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getMemberPrice() {
		return this.memberPrice;
	}

	public void setMemberPrice(String memberPrice) {
		this.memberPrice = memberPrice;
	}

	public String getMemberYn() {
		return this.memberYn;
	}

	public void setMemberYn(String memberYn) {
		this.memberYn = memberYn;
	}

	public String getMonFri() {
		return this.monFri;
	}

	public void setMonFri(String monFri) {
		this.monFri = monFri;
	}

	public String getPeopleNum() {
		return this.peopleNum;
	}

	public void setPeopleNum(String peopleNum) {
		this.peopleNum = peopleNum;
	}

	public String getPhone1() {
		return this.phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return this.phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getTimeiPrice1() {
		return this.timeiPrice1;
	}

	public void setTimeiPrice1(String timeiPrice1) {
		this.timeiPrice1 = timeiPrice1;
	}

	public String getTimeiPrice2() {
		return this.timeiPrice2;
	}

	public void setTimeiPrice2(String timeiPrice2) {
		this.timeiPrice2 = timeiPrice2;
	}

	public String getTimeiPrice3() {
		return this.timeiPrice3;
	}

	public void setTimeiPrice3(String timeiPrice3) {
		this.timeiPrice3 = timeiPrice3;
	}

	public String getTimeiPrice4() {
		return this.timeiPrice4;
	}

	public void setTimeiPrice4(String timeiPrice4) {
		this.timeiPrice4 = timeiPrice4;
	}

	public String getVenueIcno() {
		return this.venueIcno;
	}

	public void setVenueIcno(String venueIcno) {
		this.venueIcno = venueIcno;
	}

	public String getVenueName() {
		return this.venueName;
	}

	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}

	public String getVenuePassword() {
		return this.venuePassword;
	}

	public void setVenuePassword(String venuePassword) {
		this.venuePassword = venuePassword;
	}

	public String getVenueRepassword() {
		return this.venueRepassword;
	}

	public void setVenueRepassword(String venueRepassword) {
		this.venueRepassword = venueRepassword;
	}

	public String getWeekend() {
		return this.weekend;
	}

	public void setWeekend(String weekend) {
		this.weekend = weekend;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}