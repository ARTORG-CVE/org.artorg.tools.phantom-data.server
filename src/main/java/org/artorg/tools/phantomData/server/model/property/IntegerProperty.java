package org.artorg.tools.phantomData.server.model.property;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "INTEGER_PROPERTIES")
//@DiscriminatorValue(value = "4")
public class IntegerProperty extends Property<IntegerProperty, Integer> implements Serializable {
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

	@SuppressWarnings("unchecked")
	@Override
	public Class<IntegerProperty> getItemClass() {
		return (Class<IntegerProperty>) this.getClass();
	}
	
}