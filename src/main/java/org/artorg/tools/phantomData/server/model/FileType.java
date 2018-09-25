package org.artorg.tools.phantomData.server.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.artorg.tools.phantomData.server.specification.DbPersistent;

@Entity
@Table(name = "FILE_TYPES")
public class FileType implements DbPersistent<FileType> {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID", nullable = false)
	private UUID id = UUID.randomUUID();
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	public FileType() {}
	
	public FileType(String name) {
		this.name = name;
	}
	
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
	
	@Override
	public int compareTo(FileType that) {
		return getName().compareTo(that.getName());
	}
	
	@Override
	public String toString() {
		return String.format("[name: %s]", 
				getName());
	}

	@Override
	public Class<FileType> getItemClass() {
		return FileType.class;
	}

}
