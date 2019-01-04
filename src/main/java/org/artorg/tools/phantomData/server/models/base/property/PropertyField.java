package org.artorg.tools.phantomData.server.models.base.property;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.artorg.tools.phantomData.server.model.AbstractPersonifiedEntity;
import org.artorg.tools.phantomData.server.model.DbPersistentUUID;

@Entity
@Table(name = "PROPERTY_FIELD")
public class PropertyField extends AbstractPersonifiedEntity<PropertyField>
		implements DbPersistentUUID<PropertyField>, Serializable {
	private static final long serialVersionUID = -1078447486967118366L;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	@Column(name = "ENTITY_TYPE", nullable = false)
	private String entityType;

	@Column(name = "PROPERTY_TYPE", nullable = false)
	private String propertyType;

	public PropertyField() {}

	public PropertyField(String name, String description, Class<?> entityType,
			Class<?> propertyType) {
		this.name = name;
		this.description = description;
		this.entityType = entityType.getSimpleName();
		this.propertyType = propertyType.getSimpleName();
	}

	@Override
	public String toName() {
		return String.format("entityType=%s, propertyType=%s, name=%s", entityType, propertyType,
				name);
	}

	@Override
	public Class<PropertyField> getItemClass() {
		return PropertyField.class;
	}

	@Override
	public String toString() {
		return String.format(
				"PropertyField [entityType=%s, propertyType=%s, name=%s, description=%s, %s]",
				entityType, propertyType, name, description, super.toString());
	}

	@Override
	public int compareTo(PropertyField that) {
		if (that == null) return -1;
		int result;
		result = getEntityType().compareTo(that.getEntityType());
		if (result != 0) return result;
		result = getPropertyType().compareTo(that.getPropertyType());
		if (result != 0) return result;
		result = getName().compareTo(that.getName());
		if (result != 0) return result;
		result = getDescription().compareTo(that.getDescription());
		if (result != 0) return result;
		return super.compareTo(that);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((entityType == null) ? 0 : entityType.hashCode());
		result = prime * result + ((propertyType == null) ? 0 : propertyType.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!super.equals(obj)) return false;
		if (!(obj instanceof PropertyField)) return false;
		PropertyField other = (PropertyField) obj;
		if (description == null) {
			if (other.description != null) return false;
		} else if (!description.equals(other.description)) return false;
		if (name == null) {
			if (other.name != null) return false;
		} else if (!name.equals(other.name)) return false;
		if (entityType == null) {
			if (other.entityType != null) return false;
		} else if (!entityType.equals(other.entityType)) return false;
		if (propertyType == null) {
			if (other.propertyType != null) return false;
		} else if (!propertyType.equals(other.propertyType)) return false;
		return true;
	}

	// Getters & Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String type) {
		this.entityType = type;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

}
