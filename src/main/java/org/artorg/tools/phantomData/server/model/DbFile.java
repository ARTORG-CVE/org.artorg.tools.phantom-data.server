package org.artorg.tools.phantomData.server.model;

import java.io.File;
import java.io.IOException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.io.FileUtils;
import org.artorg.tools.phantomData.server.model.specification.AbstractBaseEntity;
import org.artorg.tools.phantomData.server.specification.DbPersistentUUID;

@Entity
@Table(name = "FILES")
public class DbFile extends AbstractBaseEntity<DbFile> implements DbPersistentUUID<DbFile> {
	private static final long serialVersionUID = 1575607671219807521L;

	private static String filesPath;
	
	public static String getFilesPath() {
		return filesPath;
	}

	public static void setFilesPath(String filesPath) {
		DbFile.filesPath = filesPath;
	}
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Column(name = "EXTENSION", nullable = false)
	private String extension;
	
	@Column(name = "FILE_TYPE", nullable = false)
	private FileType fileType;

	public DbFile() {}
	
	public DbFile(File srcFile, String name, String extension, FileType fileType) {
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
	public String toName() {
		return name;
	}
	
	@Override
	public Class<DbFile> getItemClass() {
		return DbFile.class;
	}
	
	@Override
	public String toString() {
		return String.format("DbFile [name=%s, extension=%s, fileType=%s, %s]", name,
			extension, fileType, super.toString());
	}

	@Override
	public int compareTo(DbFile that) {
		if (that == null) return -1;
		int result;
		result = getName().compareTo(that.getName());
		if (result != 0) return result;
		result = getExtension().compareTo(that.getExtension());
		if (result != 0) return result;
		result = getFileType().compareTo(that.getFileType());
		if (result != 0) return result;
		return super.compareTo(that);
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
		if (!(obj instanceof DbFile)) return false;
		DbFile other = (DbFile) obj;
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

	// Getters & Setters
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

}
