package org.artorg.tools.phantomData.server.model.property;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import org.artorg.tools.phantomData.server.specification.DatabasePersistent;

@MappedSuperclass
public abstract class Property<U extends Comparable<U>> implements DatabasePersistent, Serializable {

	private static final long serialVersionUID = -6436598935465710135L;
	
	@Id
	@Column(name = "ID", nullable = false)
	private UUID id = UUID.randomUUID();
	
	@OneToOne
	private PropertyField propertyField;
	
	@Column(name = "VALUE", nullable = false)
	private U value;
	
	public Property() {}
	
	
	public abstract String toString(U value);
	
	public abstract U fromStringToValue(String s);
	
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
		return String.format("[propertyField: %s, value: %s]", getPropertyField().toString(), getValue().toString());
	}

	@Override
	public UUID getId() {
		return id;
	}

	@Override
	public void setId(UUID id) {
		this.id = id;
	}
	
}
