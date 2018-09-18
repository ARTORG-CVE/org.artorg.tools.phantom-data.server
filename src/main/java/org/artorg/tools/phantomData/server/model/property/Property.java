package org.artorg.tools.phantomData.server.model.property;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import org.artorg.tools.phantomData.server.model.AbstractIdentity;

@MappedSuperclass
public abstract class Property<U extends Comparable<U>, ID_TYPE> 
		extends AbstractIdentity<ID_TYPE>
		implements 
		Serializable {

	private static final long serialVersionUID = -6436598935465710135L;
	
	@OneToOne
	private PropertyField propertyField;
	
	@Column(name = "VALUE", nullable = false)
	private U value;
	
	public Property() {}
	
	
	public abstract String toString(U value);
	
	public abstract U fromStringToValue(String s);
	
	public abstract ID_TYPE fromStringToId(String s);
	
	public Property(PropertyField propertyField, U value) {
		this.propertyField = propertyField;
		this.value = value;
	}
	
	public PropertyField getPropertyField() {
		return propertyField;
	}

	public void setPropertyField(PropertyField propertyField) {
		this.propertyField = propertyField;
	}
	
	public U getValue() {
		return value;
	}

	public void setValue(U value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return String.format("[id: %d, propertyField: %s, value: %s]", 
				getId(), getPropertyField().toString(), getValue().toString());
	}
	
}
