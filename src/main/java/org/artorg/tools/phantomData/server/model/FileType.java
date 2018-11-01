package org.artorg.tools.phantomData.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.artorg.tools.phantomData.server.model.specification.AbstractBaseEntity;
import org.artorg.tools.phantomData.server.specification.DbPersistentUUID;

@Entity
@Table(name = "FILE_TYPES")
public class FileType extends AbstractBaseEntity<FileType> implements DbPersistentUUID<FileType> {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	public FileType() {}
	
	public FileType(String name) {
		this.name = name;
	}
	
	@Override
	public String toName() {
		return name;
	}
	
	@Override
	public Class<FileType> getItemClass() {
		return FileType.class;
	}
	
	@Override
	public String toString() {
		return String.format("FileType [name=%s, %s]", name, super.toString());
	}

	@Override
	public int compareTo(FileType that) {
		if (that == null) return -1;
		int result;
		result = getName().compareTo(that.getName());
		if (result != 0) return result;
		return super.compareTo(that);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!super.equals(obj)) return false;
		if (!(obj instanceof FileType)) return false;
		FileType other = (FileType) obj;
		if (name == null) {
			if (other.name != null) return false;
		} else if (!name.equals(other.name)) return false;
		return true;
	}

	// Getters & Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
