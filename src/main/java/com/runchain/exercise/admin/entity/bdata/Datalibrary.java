package com.runchain.exercise.admin.entity.bdata;

import java.io.Serializable;
import javax.persistence.*;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 实体：数据字典
 * 
 * @author HenryYan
 * 
 */
@Entity
@Table(name = "t_bd_datalibrary")
public class Datalibrary implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Boolean enabled;
	private String librarycode;
	private String libraryname;
	private String librarytype;
	private String libraryvalue;
	private String remark;
	private Integer sort;

	public Datalibrary() {
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "enabled")
	public Boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Column(name = "library_code")
	public String getLibrarycode() {
		return this.librarycode;
	}

	public void setLibrarycode(String librarycode) {
		this.librarycode = librarycode;
	}

	@Column(name = "library_name")
	public String getLibraryname() {
		return this.libraryname;
	}

	public void setLibraryname(String libraryname) {
		this.libraryname = libraryname;
	}

	@Column(name = "library_type")
	public String getLibrarytype() {
		return this.librarytype;
	}

	public void setLibrarytype(String librarytype) {
		this.librarytype = librarytype;
	}

	@Column(name = "library_value")
	public String getLibraryvalue() {
		return this.libraryvalue;
	}

	public void setLibraryvalue(String libraryvalue) {
		this.libraryvalue = libraryvalue;
	}

	@Column(name = "remark")
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "sort")
	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}