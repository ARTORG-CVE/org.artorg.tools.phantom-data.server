package org.artorg.tools.phantomData.server.model.measurement;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.artorg.tools.phantomData.server.model.specification.AbstractPersonifiedEntity;
import org.artorg.tools.phantomData.server.util.EntityUtils;

@Entity
@Table(name = "PHYSICAL_QUANTITIES")
public class PhysicalQuantity extends AbstractPersonifiedEntity<PhysicalQuantity> implements Serializable {
	private static final long serialVersionUID = -2583441555930478789L;

	@Column(name = "NAME", unique=true, nullable = false)
	private String name;
	
	@Column(name = "COMMON_SYMBOLS", unique=false, nullable = false)
	private String commonSymbols;
	
	@Column(name = "DESCRIPTION", unique=false, nullable = false)
	private String description;
	
	public PhysicalQuantity() {}
	
	public PhysicalQuantity(String name, String commonSymboles, String description) {
		this.name = name;
		this.commonSymbols = commonSymboles;
		this.description = description;
	}
	
	@Override
	public Class<PhysicalQuantity> getItemClass() {
		return PhysicalQuantity.class;
	}

	@Override
	public String toName() {
		return name;
	}
	
	@Override
	public String toString() {
		return String.format(
			"PhysicalQuantity [name=%s, commonSymbols=%s, description=%s, %s]", name,
			commonSymbols, description, super.toString());
	}

	@Override
	public int compareTo(PhysicalQuantity that) {
		if (that == null) return -1;
		int result;
		result = name.compareTo(that.name);
		if (result != 0) return result;
		result = commonSymbols.compareTo(that.commonSymbols);
		if (result != 0) return result;
		result = description.compareTo(that.description);
		if (result != 0) return result;
		return super.compareTo(that);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!super.equals(obj)) return false;
		if (!(obj instanceof PhysicalQuantity)) return false;
		PhysicalQuantity other = (PhysicalQuantity) obj;
		if (!EntityUtils.equals(name, other.name)) return false;
		if (!EntityUtils.equals(commonSymbols, other.commonSymbols)) return false;
		if (!EntityUtils.equals(description, other.description)) return false;
		return true;
	}

	// Getters & Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCommonSymbols() {
		return commonSymbols;
	}

	public void setCommonSymbols(String commonSymbols) {
		this.commonSymbols = commonSymbols;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
