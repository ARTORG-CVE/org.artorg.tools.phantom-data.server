package org.artorg.tools.phantomData.server.model.property;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.artorg.tools.phantomData.server.specification.DbPersistent;

@Entity
@Table(name = "PROPERTY_FIELD")
public class PropertyField implements DbPersistent<PropertyField,UUID> {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", nullable = false)
	private UUID id = UUID.randomUUID();
	
	@Column(name = "NAME", unique=true, nullable = false)
	private String name;
	
	@Column(name = "DESCRIPTION", nullable = false)
	private String description;
	
	public PropertyField() {}
	
	public PropertyField(String name, String description) {
		this.name = name; 
		this.description = description;
	}
	
	@Override
	public UUID getId() {
		return id;
	}
	
	@Override
	public void setId(UUID id) {
		this.id = id;
	}
	
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
	
	@Override
	public int compareTo(PropertyField that) {
		return getName().compareTo(that.getName());
	}
	
	@Override
	public String toString() {
		return String.format("[name: %s, descrption: %s]", 
				getName(), getDescription());
	}

	@Override
	public Class<PropertyField> getItemClass() {
		return PropertyField.class;
	}
	
}
