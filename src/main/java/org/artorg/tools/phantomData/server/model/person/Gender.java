package org.artorg.tools.phantomData.server.model.person;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.artorg.tools.phantomData.server.model.specification.NameGeneratable;
import org.artorg.tools.phantomData.server.specification.DbPersistentUUID;

@Entity
@Table(name = "GENDERS")
public class Gender
	implements Comparable<Gender>, Serializable, DbPersistentUUID<Gender>, NameGeneratable {
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
	
	@Override
	public String toName() {
		return name;
	}
	
	@Override
	public String toString() {
		return String.format("Gender [name=%s, id=%s]", name, id);
	}

	@Override
	public int compareTo(Gender that) {
		if (that == null) return -1;
		int result;
		result = getName().compareTo(that.getName());
		if (result != 0) return result;
		result = getId().compareTo(that.getId());
		if (result != 0) return result;
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof Gender)) return false;
		Gender other = (Gender) obj;
		if (name == null) {
			if (other.name != null) return false;
		} else if (!name.equals(other.name)) return false;
		return true;
	}

	@Override
	public Class<Gender> getItemClass() {
		return Gender.class;
	}
	
	// Getters & Setters
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

	

}
