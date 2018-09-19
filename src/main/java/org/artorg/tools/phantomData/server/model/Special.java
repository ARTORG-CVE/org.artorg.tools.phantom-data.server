package org.artorg.tools.phantomData.server.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.artorg.tools.phantomData.server.model.property.PropertyContainer;
import org.artorg.tools.phantomData.server.specification.DatabasePersistent;

@Entity
@Table(name = "SPECIALS")
public class Special implements Comparable<Special>, Serializable,
		DatabasePersistent {
	private static final long serialVersionUID = 4838372606658297575L;

	@Id
	@Column(name = "ID", nullable = false)
	private UUID id = UUID.randomUUID();
	
	@Column(name = "SHORTCUT", unique=true, nullable = false)
	private String shortcut;
	
	@OneToOne (cascade = CascadeType.MERGE)
	private PropertyContainer propertyContainer;
	
	public Special() {}
	
	public Special(String shortcut, PropertyContainer propertyContainer) {
		this.shortcut = shortcut;
		
		this.propertyContainer = propertyContainer;
	}
	
	@Override
	public int compareTo(Special that) {
		return getId().compareTo(that.getId());
	}
	
	@Override
	public String toString() {
		return String.format("shortcut: %s, properties: %s", 
			getShortcut()
				,
				""
//				propertyContainer.getAllProperties().stream()
//					.map(p -> p.toString())
//					.collect(Collectors.joining(", ", "[", "]"))
//				getBooleanProperties().stream()
//					.map(a -> a.toString())
//					.collect(Collectors.joining(", ", "[", "]"))
					);
	}
	
	@Override
	public UUID getId() {
		return id;
	}
	
	@Override
	public void setId(UUID id) {
		this.id = id;
	}
	
	public String getShortcut() {
		return shortcut;
	}

	public void setShortcut(String shortcut) {
		this.shortcut = shortcut;
	}
	
	public PropertyContainer getPropertyContainer() {
		return propertyContainer;
	}

	public void setPropertyContainer(PropertyContainer propertyContainer) {
		this.propertyContainer = propertyContainer;
	}

	
	
}
