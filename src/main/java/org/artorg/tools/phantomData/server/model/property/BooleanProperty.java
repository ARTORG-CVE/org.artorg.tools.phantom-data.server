package org.artorg.tools.phantomData.server.model.property;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "BOOLEAN_PROPERTIES")
public class BooleanProperty extends Property<BooleanProperty, Boolean> {
	private static final long serialVersionUID = 4690035196217371829L;
	
	public BooleanProperty() {}
	
	public BooleanProperty(PropertyField propertyField, Boolean value) {
		super(propertyField, value);
	}

	@Override
	public String toString(Boolean value) {
		return Boolean.toString(value);
	}

	@Override
	public Boolean fromStringToValue(String s) {
		return Boolean.valueOf(s);
	}
	
}