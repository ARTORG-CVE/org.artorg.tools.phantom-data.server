package org.artorg.tools.phantomData.server.model.phantom;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.artorg.tools.phantomData.server.model.specification.AbstractBaseEntity;
import org.artorg.tools.phantomData.server.specification.DbPersistentUUID;

@Entity
@Table(name = "SPECIALS")
public class Special extends AbstractBaseEntity<Special> implements DbPersistentUUID<Special>, Serializable {
	private static final long serialVersionUID = 4838372606658297575L;
	
	@Column(name = "SHORTCUT", unique=true, nullable = false)
	private String shortcut;
	
	@Column(name = "DESCRIPTION", nullable = false)
	private String description;
	
	public Special() {}
	
	public Special(String shortcut, String description) {
		this.shortcut = shortcut;
		this.description = description;
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
		return String.format("Special [shortcut=%s, %s]", shortcut, super.toString());
	}

	@Override
	public int compareTo(Special that) {
		if (that == null) return -1;
		int result;
		result = getShortcut().compareTo(that.getShortcut());
		if (result != 0) return result;
		return super.compareTo(that);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof Special)) return false;
		Special other = (Special) obj;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
