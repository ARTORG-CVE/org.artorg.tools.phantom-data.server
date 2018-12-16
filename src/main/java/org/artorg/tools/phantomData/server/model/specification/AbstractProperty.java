package org.artorg.tools.phantomData.server.model.specification;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import org.artorg.tools.phantomData.server.model.base.property.PropertyField;
import org.artorg.tools.phantomData.server.specification.DbPersistentUUID;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY,
		property = "itemClass")
public abstract class AbstractProperty<T, R> extends AbstractPersonifiedEntity<T>
		implements DbPersistentUUID<T>, Serializable {
	private static final long serialVersionUID = -6436598935465710135L;

	@OneToOne
	private PropertyField propertyField;

	@Column(name = "VALUE", nullable = false)
	private R value;

	public abstract String toString(R value);

	public abstract R fromStringToValue(String s);

	public AbstractProperty() {}

	public AbstractProperty(PropertyField propertyField, R value) {
		this.propertyField = propertyField;
		this.value = value;
	}

	@Override
	public String toName() {
		return propertyField.getName() + ": " + toString(value);
	}

	@Override
	public String toString() {
		return String.format("%s [propertyField=%s, value=%s, %s]", getItemClass().getSimpleName(),
				propertyField, value, super.toString());
	}

	@SuppressWarnings("unchecked")
	@Override
	public int compareTo(T that) {
		if (that == null) return -1;
		int result = 0;
		result = getPropertyField().compareTo(((AbstractProperty<T, ?>) that).getPropertyField());
		if (result != 0) return result;
		result = ((Comparable<T>) getValue())
				.compareTo((T) ((AbstractProperty<T, ?>) that).getValue());
		if (result != 0) return result;
		result = super.compareTo(that);
		if (result != 0) return result;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!super.equals(obj)) return false;
		if (!(obj instanceof AbstractProperty)) return false;
		AbstractProperty<?, ?> other = (AbstractProperty<?, ?>) obj;
		if (propertyField == null) {
			if (other.propertyField != null) return false;
		} else if (!propertyField.equals(other.propertyField)) return false;
		if (value == null) {
			if (other.value != null) return false;
		} else if (!value.equals(other.value)) return false;
		return true;
	}

	// Getters & Setters
	public PropertyField getPropertyField() {
		return propertyField;
	}

	public void setPropertyField(PropertyField propertyField) {
		this.propertyField = propertyField;
	}

	public R getValue() {
		return value;
	}

	public void setValue(R value) {
		this.value = value;
	}

}
