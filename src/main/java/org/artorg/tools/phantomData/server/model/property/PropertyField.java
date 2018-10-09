package org.artorg.tools.phantomData.server.model.property;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.DiscriminatorType;
import javax.persistence.Table;

import org.artorg.tools.phantomData.server.specification.DbPersistentUUID;

@Entity
@Table(name = "PROPERTY_FIELD")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "CLASS_TYPE", discriminatorType = DiscriminatorType.STRING)
public class PropertyField implements DbPersistentUUID<PropertyField>, Serializable {
	private static final long serialVersionUID = -1078447486967118366L;

	@Id
	@Column(name = "ID", nullable = false)
	private UUID id = UUID.randomUUID();
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Column(name = "DESCRIPTION", nullable = false)
	private String description;
	
	@Column(name = "PARENT_ITEM_CLASS", nullable = false)
	private String parentItemClass;
	
	
	
//	@SuppressWarnings("unchecked")
//	public Class<ITEM> getParentItemClass() {
//		try {
//			return (Class<ITEM>) Class.forName(parentItemClass);
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//		throw new RuntimeException();
//	}
//	
//	public void setParentItemClass(Class<ITEM> parentItemClass) {
//		this.parentItemClass = parentItemClass.getName();
//	}

	public String getParentItemClass() {
		return parentItemClass;
	}

	public void setParentItemClass(String parentItemClass) {
		this.parentItemClass = parentItemClass;
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

	@SuppressWarnings("unchecked")
	@Override
	public Class<PropertyField> getItemClass() {
		return (Class<PropertyField>) this.getClass();
	}
	

	
}
