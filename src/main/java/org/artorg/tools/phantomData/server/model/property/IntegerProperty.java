package org.artorg.tools.phantomData.server.model.property;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "INTEGER_PROPERTIES")
public class IntegerProperty extends Property<Integer, Integer> implements ComparableProperty<IntegerProperty,Integer, Integer> {
	private static final long serialVersionUID = -2138623619328236280L;

	public IntegerProperty() {}
	
	public IntegerProperty(PropertyField propertyField, Integer value) {
		super(propertyField, value);
	}
	
	@Override
	public Integer stringToID(String id) {
		return Integer.valueOf(id);
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
	public Integer fromStringToId(String s) {
		return Integer.valueOf(s);
	}
	
}