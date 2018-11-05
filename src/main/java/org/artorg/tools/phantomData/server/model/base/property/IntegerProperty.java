package org.artorg.tools.phantomData.server.model.base.property;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.artorg.tools.phantomData.server.model.specification.AbstractProperty;

@Entity
@Table(name = "INTEGER_PROPERTIES")
public class IntegerProperty extends AbstractProperty<IntegerProperty, Integer> implements Serializable {
	private static final long serialVersionUID = -2138623619328236280L;

	public IntegerProperty() {}
	
	public IntegerProperty(PropertyField propertyField, Integer value) {
		super(propertyField, value);
	}

	@Override
	public String toString(Integer value) {
		return Integer.toString(value);
	}

	@Override
	public Integer fromStringToValue(String s) {
		return Integer.valueOf(s);
	}
	
	@Override
	public Class<IntegerProperty> getItemClass() {
		return IntegerProperty.class;
	}
	
}