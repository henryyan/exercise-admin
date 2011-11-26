package com.runchain.exercise.admin.entity.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.runchain.arch.util.orm.BaseEntity;

/**
 * <p><b>Title：</b>系统属性配置</p>
 * <p><b>Description：</b>键值对的形式</p>
 *
 * @author HenryYan
 *
 */
@Entity
@Table(name = "T_ADMIN_SYSTEM_PROPERTY")
//默认的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SystemProperty extends BaseEntity {
	
	// 属性名称
	private String propName;

	// 键
	private String propKey;

	// 值
	private String propValue;
	
	// 是否可见
	private boolean visible = true;
	
	// 是否启用
	private boolean enable = true;

	// 描述
	private String propDescribe;

	@Column(nullable = false, unique = true, length = 20)
	public String getPropName() {
		return propName;
	}

	public void setPropName(String propName) {
		this.propName = propName;
	}

	@Column(nullable = false, unique = true, length = 20)
	public String getPropKey() {
		return propKey;
	}

	public void setPropKey(String propKey) {
		this.propKey = propKey;
	}

	@Column(nullable = false)
	public String getPropValue() {
		return propValue;
	}

	public void setPropValue(String propValue) {
		this.propValue = propValue;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public String getPropDescribe() {
		return propDescribe;
	}

	public void setPropDescribe(String propDescribe) {
		this.propDescribe = propDescribe;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
