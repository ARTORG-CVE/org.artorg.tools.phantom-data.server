package org.artorg.tools.phantomData.server.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.artorg.tools.phantomData.server.connector.FileTypeConnector;
import org.artorg.tools.phantomData.server.specification.DatabasePersistent;
import org.artorg.tools.phantomData.server.specification.HttpDatabaseCrud;

@Entity
@Table(name = "FILE_TYPES")
public class FileType implements Comparable<FileType>, Serializable, 
	DatabasePersistent<FileType, Integer> {
	private static final long serialVersionUID = 1L;
	
	private static final HttpDatabaseCrud<FileType, Integer> connector;
	
	static {
	connector  = FileTypeConnector.get();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Integer id;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	public FileType() {}
	
	public FileType(String name) {
		this.name = name;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
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
		return String.format("[id: %d, name: %s]", 
				getId(), getName());
	}

	@Override
	public HttpDatabaseCrud<FileType, Integer> getConnector() {
		return connector;
	}
	
	@Override
	public Integer stringToID(String id) {
		return Integer.valueOf(id);
	}

}
