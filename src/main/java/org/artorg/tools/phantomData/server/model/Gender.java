package org.artorg.tools.phantomData.server.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.artorg.tools.phantomData.server.specification.DbPersistentUUID;

@Entity
@Table(name = "GENDERS")
public class Gender implements Comparable<Gender>, Serializable, DbPersistentUUID<Gender> {
	private static final long serialVersionUID = -1258070941810525499L;

	@Id
	@Column(name = "ID", nullable = false)
	private UUID id = UUID.randomUUID();
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	public Gender() {}
	
	public Gender(String name) {
		this.name = name;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Gender))
			return false;
		Gender other = (Gender) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public Class<Gender> getItemClass() {
		return Gender.class;
	}

	@Override
	public int compareTo(Gender o) {
		return getName().compareTo(o.getName());
	}

}
