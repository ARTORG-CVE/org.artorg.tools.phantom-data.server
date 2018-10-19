package org.artorg.tools.phantomData.server.model;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.io.FileUtils;
import org.artorg.tools.phantomData.server.model.specification.AbstractBaseEntity;
import org.artorg.tools.phantomData.server.specification.DbPersistentUUID;

@Entity
@Table(name = "FILES")
public class PhantomFile extends AbstractBaseEntity<PhantomFile> implements DbPersistentUUID<PhantomFile> {
	private static final long serialVersionUID = 1L;
	private static String filesPath;
	
	
	public static String getFilesPath() {
		return filesPath;
	}

	public static void setFilesPath(String filesPath) {
		PhantomFile.filesPath = filesPath;
	}

	@Id
	@Column(name = "ID", nullable = false)
	private UUID id = UUID.randomUUID();
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Column(name = "EXTENSION", nullable = false)
	private String extension;
	
	@Column(name = "FILE_TYPE", nullable = false)
	private FileType fileType;

	public PhantomFile() {}
	
	public PhantomFile(File srcFile, String name, String extension, FileType fileType) {
		this.name = name;
		extension = extension.toLowerCase();
		this.extension = extension;
		this.fileType = fileType;
		
		File destFile = new File(filesPath +"\\" +getId() +"." +extension);
		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public File getFile() {
		return new File(filesPath +"\\" +getId() +"." +extension);
	}
	
	
	@Override
	public String createName() {
		return name;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((extension == null) ? 0 : extension.hashCode());
		result = prime * result + ((fileType == null) ? 0 : fileType.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!super.equals(obj)) return false;
		if (!(obj instanceof PhantomFile)) return false;
		PhantomFile other = (PhantomFile) obj;
		if (extension == null) {
			if (other.extension != null) return false;
		} else if (!extension.equals(other.extension)) return false;
		if (fileType == null) {
			if (other.fileType != null) return false;
		} else if (!fileType.equals(other.fileType)) return false;
		if (name == null) {
			if (other.name != null) return false;
		} else if (!name.equals(other.name)) return false;
		return true;
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

	public FileType getFileType() {
		return fileType;
	}

	public void setFileType(FileType fileType) {
		this.fileType = fileType;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	@Override
	public int compareTo(PhantomFile that) {
		return Integer.compare(this.hashCode(), that.hashCode());
	}
	
	@Override
	public String toString() {
		return String.format("name: %s, type: %s, path: ", 
				getName() +"." +getExtension(), getFileType().toString(), filesPath +"\\" +getId() +"." +extension);
	}

	@Override
	public Class<PhantomFile> getItemClass() {
		return PhantomFile.class;
	}

	

}
