package org.artorg.tools.phantomData.server.model.measurement;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.artorg.tools.phantomData.server.model.specification.AbstractPersonifiedEntity;
import org.artorg.tools.phantomData.server.util.EntityUtils;

@Entity
@Table(name = "UNITS")
public class Unit extends AbstractPersonifiedEntity<Unit> implements Serializable {
	private static final long serialVersionUID = -9084678302334080387L;

	@Column(name = "SHORTCUT", unique=false, nullable = false)
	private String shortcut;
	
	@Column(name = "DESCRIPTION", unique=false, nullable = false)
	private String description;
	
	@OneToOne
	@JoinColumn(nullable = false)
	private PhysicalQuantity physicalQuantity;
	
	@OneToOne
	@JoinColumn(nullable = false)
	private UnitPrefix unitPrefix;

	public Unit() {}
	
	public Unit(String shortcut, String description, PhysicalQuantity physicalQuantity, UnitPrefix unitPrefix) {
		this.shortcut = shortcut;
		this.description = description;
		this.physicalQuantity = physicalQuantity;
		this.unitPrefix = unitPrefix;
	}
	
	@Override
	public Class<Unit> getItemClass() {
		return Unit.class;
	}

	@Override
	public String toName() {
		return unitPrefix.getPrefix() +shortcut;
	}

	@Override
	public String toString() {
		return String.format(
			"Unit [shortcut=%s, physicalQuantity=%s, unitPrefix=%s, description=%s, %s]",
			shortcut, physicalQuantity, unitPrefix, description, super.toString());
	}

	@Override
	public int compareTo(Unit that) {
		if (that == null) return -1;
		int result;
		result = shortcut.compareTo(that.shortcut);
		if (result != 0) return result;
		result = physicalQuantity.compareTo(that.physicalQuantity);
		if (result != 0) return result;
		result = unitPrefix.compareTo(that.unitPrefix);
		if (result != 0) return result;
		result = description.compareTo(that.description);
		if (result != 0) return result;
		return super.compareTo(that);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!super.equals(obj)) return false;
		if (!(obj instanceof Unit)) return false;
		Unit other = (Unit) obj;
		if (!EntityUtils.equals(physicalQuantity, other.physicalQuantity)) return false;
		if (!EntityUtils.equals(unitPrefix, other.unitPrefix)) return false;
		if (!EntityUtils.equals(description, other.description)) return false;
		return true;
	}

	// Getters & Setters
	public PhysicalQuantity getPhysicalQuantity() {
		return physicalQuantity;
	}

	public void setPhysicalQuantity(PhysicalQuantity physicalQuantity) {
		this.physicalQuantity = physicalQuantity;
	}

	public UnitPrefix getUnitPrefix() {
		return unitPrefix;
	}

	public void setUnitPrefix(UnitPrefix unitPrefix) {
		this.unitPrefix = unitPrefix;
	}

	public String getShortcut() {
		return shortcut;
	}

	public void setShortcut(String shortcut) {
		this.shortcut = shortcut;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
