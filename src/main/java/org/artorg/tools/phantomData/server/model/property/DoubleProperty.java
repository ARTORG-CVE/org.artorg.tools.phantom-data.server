package org.artorg.tools.phantomData.server.model.property;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "DOUBLE_PROPERTIES")
public class DoubleProperty extends Property<DoubleProperty, Double> implements Serializable {
	private static final long serialVersionUID = -4538457475852600572L;

	public DoubleProperty() {}
	
	public DoubleProperty(PropertyField propertyField, Double value) {
		super(propertyField, value);
	}

	@Override
	public String toString(Double value) {
		return Double.toString(value);
	}

	@Override
	public Double fromStringToValue(String s) {
		return Double.valueOf(s);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class<DoubleProperty> getItemClass() {
		return (Class<DoubleProperty>) this.getClass();
	}
	
}