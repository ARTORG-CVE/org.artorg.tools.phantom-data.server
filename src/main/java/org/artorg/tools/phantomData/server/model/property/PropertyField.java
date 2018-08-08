package org.artorg.tools.phantomData.server.model.property;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.artorg.tools.phantomData.server.specification.DatabasePersistent;

@Entity
@Table(name = "PROPERTY_FIELD")
public class PropertyField implements Comparable<PropertyField>, Serializable, 
		DatabasePersistent<PropertyField, Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Integer id;
	
	@Column(name = "NAME", unique=true, nullable = false)
	private String name;
	
	@Column(name = "DESCRIPTION", nullable = false)
	private String description;
	
	public PropertyField() {}
	
	public PropertyField(String name, String description) {
		this.name = name; 
		this.description = description;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
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
		return String.format("[id: %d, name: %s, descrption: %s]", 
				getId(), getName(), getDescription());
	}
	
	@Override
	public Integer stringToID(String id) {
		return Integer.valueOf(id);
	}

}
