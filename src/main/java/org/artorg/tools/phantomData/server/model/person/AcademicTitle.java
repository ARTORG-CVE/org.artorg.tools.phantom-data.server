package org.artorg.tools.phantomData.server.model.person;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.artorg.tools.phantomData.server.specification.DbPersistentUUID;

@Entity
@Table(name = "ACADEMIC_TITLE")
public class AcademicTitle implements DbPersistentUUID<AcademicTitle> {
	private static final long serialVersionUID = -8901917059076169353L;
	
	@Id
	@Column(name = "ID", nullable = false)
	private UUID id = UUID.randomUUID();
	
	@Column(name = "PREFIX", nullable = false)
	private String prefix;

	@Column(name = "DESCRIPTION", nullable = false)
	private String description;
	
	public AcademicTitle() {}
	
	public AcademicTitle(String prefix, String description) {
		this.prefix = prefix;
		this.description = description;
	}
	
	@Override
	public String toString() {
		return String.format("AcademicTitle [prefix=%s, description=%s, %s]", prefix,
			description, super.toString());
	}

	@Override
	public int compareTo(AcademicTitle that) {
		if (that == null) return -1;
		int result;
		result = getPrefix().compareTo(that.getPrefix());
		if (result != 0) return result;
		return getDescription().compareTo(that.getDescription());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((prefix == null) ? 0 : prefix.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!super.equals(obj)) return false;
		if (!(obj instanceof AcademicTitle)) return false;
		AcademicTitle other = (AcademicTitle) obj;
		if (description == null) {
			if (other.description != null) return false;
		} else if (!description.equals(other.description)) return false;
		if (prefix == null) {
			if (other.prefix != null) return false;
		} else if (!prefix.equals(other.prefix)) return false;
		return true;
	}

	@Override
	public Class<AcademicTitle> getItemClass() {
		return AcademicTitle.class;
	}
	
	// Getters & Setters
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
	
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
