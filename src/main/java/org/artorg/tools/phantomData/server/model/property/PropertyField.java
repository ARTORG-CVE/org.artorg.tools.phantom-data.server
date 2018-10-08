package org.artorg.tools.phantomData.server.model.property;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.artorg.tools.phantomData.server.specification.DbPersistentUUID;

@Entity
@Table(name = "PROPERTY_FIELD")
public class PropertyField implements DbPersistentUUID<PropertyField>, Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", nullable = false)
	private UUID id = UUID.randomUUID();
	
	@Column(name = "NAME", unique=true, nullable = false)
	private String name;
	
	@Column(name = "DESCRIPTION", nullable = false)
	private String description;
	
	@Column(name = "PARENT_ITEM_CLASS", nullable = false)
	private String parentItemClass;
	
	public Class<?> getParentItemClass() {
		try {
			return Class.forName(parentItemClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		throw new RuntimeException();
	}

	public void setParentItemClass(Class<?> parentItemClass) {
		this.parentItemClass = parentItemClass.getName();
	}

	public PropertyField() {}
	
	public PropertyField(String name, String description, Class<?> parentItemClass) {
		this.name = name; 
		this.description = description;
		this.parentItemClass = parentItemClass.getName();
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
