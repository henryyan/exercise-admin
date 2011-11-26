package com.runchain.exercise.admin.entity.field.type;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.runchain.arch.util.orm.BaseEntity;

/**
 * 场地类型实体.
 *
 * @author HenryYan
 *
 */
@Entity
@Table(name = "t_field_type")
@JsonIgnoreProperties(value = {"venueFieldTypeLinks"})
public class FieldType extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String typeId;
	private String typeName;
	private Boolean enable = true;
	private String tablename;
	private List<VenueFieldtypeLink> venueFieldTypeLinks;

	public FieldType() {
	}

	public FieldType(Long id, String typeId, String typeName, Boolean enable, String tablename) {
		super();
		this.id = id;
		this.typeId = typeId;
		this.typeName = typeName;
		this.enable = enable;
		this.tablename = tablename;
	}

	@Column(name = "ENABLE")
	public Boolean getEnable() {
		return this.enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	@Column(name = "TYPE_ID", length = 20)
	public String getTypeId() {
		return this.typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	@Column(name = "TYPE_NAME", length = 20)
	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}


	@Column(name = "TABLE_NAME", length = 50)
	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	//bi-directional many-to-one association to VenueFieldtypeLink
	@OneToMany(mappedBy = "fieldType", fetch = FetchType.LAZY)
	public List<VenueFieldtypeLink> getVenueFieldTypeLinks() {
		return this.venueFieldTypeLinks;
	}

	public void setVenueFieldTypeLinks(List<VenueFieldtypeLink> venueFieldTypeLinks) {
		this.venueFieldTypeLinks = venueFieldTypeLinks;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}