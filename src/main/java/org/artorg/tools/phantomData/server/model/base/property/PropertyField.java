package org.artorg.tools.phantomData.server.model.base.property;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.artorg.tools.phantomData.server.model.specification.AbstractPersonifiedEntity;
import org.artorg.tools.phantomData.server.specification.DbPersistentUUID;

@Entity
@Table(name = "PROPERTY_FIELD")
public class PropertyField extends AbstractPersonifiedEntity<PropertyField>
	implements DbPersistentUUID<PropertyField>, Serializable {
	private static final long serialVersionUID = -1078447486967118366L;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	@Column(name = "TYPE", nullable = false)
	private String type;

	public PropertyField() {}

	public PropertyField(String name, String description, Class<?> type) {
		this.name = name;
		this.description = description;
		this.type = type.getName();
	}

	@Override
	public String toName() {
		String type = this.type;
		try {
			type = Class.forName(type).getSimpleName();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return String.format("type=%s, name=%s", type, name);
	}

	@Override
	public Class<PropertyField> getItemClass() {
		return PropertyField.class;
	}

	@Override
	public String toString() {
		String type = this.type;
		try {
			type = Class.forName(type).getSimpleName();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return String.format("PropertyField [type=%s, name=%s, description=%s, %s]", type,
			name, description, super.toString());
	}

	@Override
	public int compareTo(PropertyField that) {
		if (that == null) return -1;
		int result;
		result = getType().compareTo(that.getType());
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
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		if (type == null) {
			if (other.type != null) return false;
		} else if (!type.equals(other.type)) return false;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
