package org.artorg.tools.phantomData.server.model;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.io.FileUtils;
import org.artorg.tools.phantomData.server.specification.DbPersistentUUID;

@Entity
@Table(name = "FILES")
public class PhantomFile implements	DbPersistentUUID<PhantomFile> {
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
	
	@Column(name = "PATH", nullable = false)
	private String path;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Column(name = "EXTENSION", nullable = false)
	private String extension;
	
	@Column(name = "FILE_TYPE", nullable = false)
	private FileType fileType;

	public PhantomFile() {}
	
	public PhantomFile(String path, String name, String extension, FileType fileType) {
		this.path = path;
		this.name = name;
		this.extension = extension;
		this.fileType = fileType;
	}
	
	public void create(String absolutOriginalPath) {
		File srcFile = new File(absolutOriginalPath);
		File destFile = new File(filesPath 
				+"\\" +path +"\\" +name +"." +extension);
		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		updateNativeFileName();
		
	}
	
	public void updateNativeFileName() {
		File nativeFile = new File(filesPath 
				+"/" +path +"/" +name +"." +extension);
		nativeFile.renameTo(new File(filesPath
				+"/" +path +"/" +getId() +"_" +name +"." +extension));
	}
	
	public UUID getId() {
		return id;
	}
	
	public void setId(UUID id) {
		this.id = id;
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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
		return getPath().compareTo(that.getPath());
	}
	
	@Override
	public String toString() {
		return String.format("path: %s, type: %s", 
				getPath(), getFileType().toString());
	}

	@Override
	public Class<PhantomFile> getItemClass() {
		return PhantomFile.class;
	}

}
