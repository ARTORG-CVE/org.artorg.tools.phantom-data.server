package org.artorg.tools.phantomData.server.model.property;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "DOUBLE_PROPERTIES")
public class DoubleProperty extends Property<Double, Integer> implements ComparableProperty<DoubleProperty,Double, Integer> {
	private static final long serialVersionUID = -4538457475852600572L;

	public DoubleProperty() {}
	
	public DoubleProperty(PropertyField propertyField, Double value) {
		super(propertyField, value);
	}
	
	@Override
	public Integer stringToID(String id) {
		return Integer.valueOf(id);
	}

	@Override
	public String toString(Double value) {
		return Double.toString(value);
	}

	@Override
	public Double fromStringToValue(String s) {
		return Double.valueOf(s);
	}

	@Override
	public Integer fromStringToId(String s) {
		return Integer.valueOf(s);
	}
	
}