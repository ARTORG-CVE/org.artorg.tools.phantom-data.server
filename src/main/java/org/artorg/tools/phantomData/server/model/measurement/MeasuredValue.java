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
@Table(name = "MEASURED_VALUE")
public class MeasuredValue extends AbstractPersonifiedEntity<MeasuredValue>
	implements Serializable {
	private static final long serialVersionUID = -7231263093475350032L;

	@OneToOne
	@JoinColumn(nullable = false)
	private Unit unit;

	@Column(name = "VALUE", unique = false, nullable = false)
	private Double value;
	
	@Column(name = "DESCRIPTION", unique=false, nullable = false)
	private String description;

	public MeasuredValue() {}

	public MeasuredValue(Unit unit, Double value) {
		this.unit = unit;
		this.value = value;
	}

	@Override
	public Class<MeasuredValue> getItemClass() {
		return MeasuredValue.class;
	}

	@Override
	public String toName() {
		return String.format("unit: %s, value: %s", unit.toName(), value.toString());
	}

	@Override
	public String toString() {
		return String.format("MeasuredValue [unit=%s, value=%s, description=%s, %s]", unit,
			value, description, super.toString());
	}

	@Override
	public int compareTo(MeasuredValue that) {
		if (that == null) return -1;
		int result;
		result = unit.compareTo(that.unit);
		if (result != 0) return result;
		result = value.compareTo(that.value);
		if (result != 0) return result;
		result = description.compareTo(that.description);
		if (result != 0) return result;
		return super.compareTo(that);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!super.equals(obj)) return false;
		if (!(obj instanceof MeasuredValue)) return false;
		MeasuredValue other = (MeasuredValue) obj;
		if (!EntityUtils.equals(unit, other.unit)) return false;
		if (!EntityUtils.equals(value, other.value)) return false;
		if (!EntityUtils.equals(description, other.description)) return false;
		return true;
	}

	// Getters & Setters
	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}