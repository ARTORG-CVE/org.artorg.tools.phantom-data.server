package org.artorg.tools.phantomData.server.model;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.io.FileUtils;
import org.artorg.tools.phantomData.server.boot.BootUtils;
import org.artorg.tools.phantomData.server.connector.FileConnector;
import org.artorg.tools.phantomData.server.specification.DatabasePersistent;
import org.artorg.tools.phantomData.server.specification.HttpDatabaseCrud;

@Entity
@Table(name = "FILES")
public class PhantomFile implements Comparable<PhantomFile>, Serializable, 
	DatabasePersistent<PhantomFile, Integer> {
	private static final long serialVersionUID = 1L;
	
	private static final HttpDatabaseCrud<PhantomFile, Integer> connector;
	
	static {
		connector  = FileConnector.get();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Integer id;
	
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
		File destFile = new File(BootUtils.ABSOLUTE_FILE_BASE_PATH +"/" +path +"/" +name +"." +extension);
		System.out.println(srcFile.toString());
		System.out.println(destFile.toString());
		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		updateNativeFileName();
		
	}
	
	public void updateNativeFileName() {
		File nativeFile = new File(BootUtils.ABSOLUTE_FILE_BASE_PATH +"/" +path +"/" +name +"." +extension);
		nativeFile.renameTo(new File(BootUtils.ABSOLUTE_FILE_BASE_PATH +"/" +path +"/" +getId() +"_" +name +"." +extension));
//		FileUtils.re
		
		System.err.println("update native file name");
		
		
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
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
		return String.format("[id: %d, path: %s, type: %s]", 
				getId(), getPath(), getFileType().toString());
	}

	@Override
	public HttpDatabaseCrud<PhantomFile, Integer> getConnector() {
		return connector;
	}
	
	@Override
	public Integer stringToID(String id) {
		return Integer.valueOf(id);
	}

}
