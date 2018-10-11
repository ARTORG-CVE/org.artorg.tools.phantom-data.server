package org.artorg.tools.phantomData.server.model.property;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "STRING_PROPERTIES")
@DiscriminatorValue(value = "STRING")
public class StringProperty extends Property<StringProperty,String> implements Serializable {
	private static final long serialVersionUID = 79674080687314415L;

	public StringProperty() {}
	
	public StringProperty(PropertyField propertyField, String value) {
		super(propertyField, value);
	}

	@Override
	public String toString(String value) {
		return value;
	}

	@Override
	public String fromStringToValue(String s) {
		return s;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class<StringProperty> getItemClass() {
		return (Class<StringProperty>) this.getClass();
	}
	
}