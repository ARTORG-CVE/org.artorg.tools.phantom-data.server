package org.artorg.tools.phantomData.server.model;

import java.io.Serializable;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.artorg.tools.phantomData.server.model.property.PropertyContainer;
import org.artorg.tools.phantomData.server.specification.DbPersistentUUID;

@Entity
@Table(name = "SPECIALS")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "TYPE")
public class Special extends PropertyContainer implements DbPersistentUUID<Special>, Serializable {
	private static final long serialVersionUID = 4838372606658297575L;

	@Id
	@Column(name = "ID", nullable = false)
	private UUID id = UUID.randomUUID();
	
	@Column(name = "SHORTCUT", unique=true, nullable = false)
	private String shortcut;
	
	public Special() {}
	
	public Special(String shortcut) {
		this.shortcut = shortcut;
	}
	
	@Override
	public int compareTo(Special that) {
		return getId().compareTo(that.getId());
	}
	
	@Override
	public String toString() {
		return String.format("shortcut: %s, properties: [%s]", 
			getShortcut()
				, getAllProperties().stream().map(p -> p.toString()).collect(Collectors.joining(", ")));
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

	@Override
	public Class<Special> getItemClass() {
		return Special.class;
	}
	
}
