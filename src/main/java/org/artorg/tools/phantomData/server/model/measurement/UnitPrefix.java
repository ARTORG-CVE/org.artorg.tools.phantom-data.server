package org.artorg.tools.phantomData.server.model.measurement;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.artorg.tools.phantomData.server.model.specification.AbstractPersonifiedEntity;
import org.artorg.tools.phantomData.server.util.EntityUtils;

@Entity
@Table(name = "UNIT_PREFIXES")
public class UnitPrefix extends AbstractPersonifiedEntity<UnitPrefix> implements Serializable {
	private static final long serialVersionUID = 2317747849691533082L;

	@Column(name = "NAME", unique=true, nullable = false)
	private String name;
	
	@Column(name = "PREFIX", unique=false, nullable = false)
	private String prefix;
	
	@Column(name = "EXPONENT", unique=false, nullable = false)
	private Integer exponent;

	public UnitPrefix() {}
	
	public UnitPrefix(String name, String prefix, int exponent) {
		this.name = name;
		this.prefix = prefix;
		this.exponent = exponent;
	}
	
	@Override
	public Class<UnitPrefix> getItemClass() {
		return UnitPrefix.class;
	}

	@Override
	public String toName() {
		return name;
	}
	
	@Override
	public int compareTo(UnitPrefix that) {
		if (that == null) return -1;
		int result;
		result = exponent.compareTo(that.exponent);
		if (result != 0) return result;
		result = name.compareTo(that.name);
		if (result != 0) return result;
		result = prefix.compareTo(that.prefix);
		if (result != 0) return result;
		return super.compareTo(that);
	}

	@Override
	public String toString() {
		return String.format("UnitPrefix [name=%s, prefix=%s, exponent=%s, %s]", name, prefix,
			exponent, super.toString());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!super.equals(obj)) return false;
		if (!(obj instanceof UnitPrefix)) return false;
		UnitPrefix other = (UnitPrefix) obj;
		if (!EntityUtils.equals(name, other.name)) return false;
		if (!EntityUtils.equals(prefix, other.prefix)) return false;
		if (!EntityUtils.equals(exponent, other.exponent)) return false;
		return true;
	}

	// Getters & Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public Integer getExponent() {
		return exponent;
	}

	public void setExponent(Integer exponent) {
		this.exponent = exponent;
	}
	
}
