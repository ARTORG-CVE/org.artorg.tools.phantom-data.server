package org.artorg.tools.phantomData.server.model.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.artorg.tools.phantomData.server.model.specification.AbstractNameEntity;

@Entity
@Table(name = "TAGS")
public class FileTag extends AbstractNameEntity<FileTag,String> {
	private static final long serialVersionUID = -7908541159889447936L;

	@Column(name = "NAME", unique = true, nullable = false)
	private String name;

	public FileTag() {}
	
	public FileTag(String name) {
		this.name = name;
	}
	
	@Override
	public Class<FileTag> getItemClass() {
		return FileTag.class;
	}

	// Getters & Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}