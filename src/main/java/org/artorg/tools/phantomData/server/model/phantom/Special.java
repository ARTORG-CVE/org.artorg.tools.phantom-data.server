package org.artorg.tools.phantomData.server.model.phantom;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.artorg.tools.phantomData.server.model.property.Properties;
import org.artorg.tools.phantomData.server.model.specification.AbstractBaseEntity;
import org.artorg.tools.phantomData.server.model.specification.IProperties;
import org.artorg.tools.phantomData.server.specification.DbPersistentUUID;

@Entity
@Table(name = "SPECIALS")
public class Special extends AbstractBaseEntity<Special> implements DbPersistentUUID<Special>, Serializable, IProperties {
	private static final long serialVersionUID = 4838372606658297575L;
	
	@Column(name = "SHORTCUT", unique=true, nullable = false)
	private String shortcut;
	
	@OneToOne
	private Properties properties;
	
	public Special() {}
	
	public Special(String shortcut) {
		this.shortcut = shortcut;
	}
	
	@Override
	public String toName() {
		return shortcut;
	}
	
	@Override
	public Class<Special> getItemClass() {
		return Special.class;
	}
	
	@Override
	public String toString() {
		return String.format("Special [shortcut=%s, properties=%s]", shortcut,
			properties);
	}

	@Override
	public int compareTo(Special that) {
		if (that == null) return -1;
		int result;
		result = getShortcut().compareTo(that.getShortcut());
		if (result != 0) return result;
		result = getProperties().compareTo(that.getProperties());
		if (result != 0) return result;
		return super.compareTo(that);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((properties == null) ? 0 : properties.hashCode());
		result = prime * result + ((shortcut == null) ? 0 : shortcut.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!super.equals(obj)) return false;
		if (!(obj instanceof Special)) return false;
		Special other = (Special) obj;
		if (properties == null) {
			if (other.properties != null) return false;
		} else if (!properties.equals(other.properties)) return false;
		if (shortcut == null) {
			if (other.shortcut != null) return false;
		} else if (!shortcut.equals(other.shortcut)) return false;
		return true;
	}

	// Getters & Setters
	public String getShortcut() {
		return shortcut;
	}

	public void setShortcut(String shortcut) {
		this.shortcut = shortcut;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	
}
