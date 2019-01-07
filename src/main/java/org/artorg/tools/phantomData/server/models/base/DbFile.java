package org.artorg.tools.phantomData.server.models.base;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.apache.commons.io.FileUtils;
import org.artorg.tools.phantomData.server.model.AbstractPersonifiedEntity;
import org.artorg.tools.phantomData.server.model.DbPersistentUUID;
import org.artorg.tools.phantomData.server.util.EntityUtils;

@Entity
@Table(name = "FILES")
public class DbFile extends AbstractPersonifiedEntity<DbFile> implements DbPersistentUUID<DbFile> {
	private static final long serialVersionUID = 1575607671219807521L;
	private static String filesPath;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "EXTENSION", nullable = false)
	private String extension;

	@ManyToMany
	private List<Note> notes = new ArrayList<>();

	@ManyToMany
	private List<FileTag> fileTags = new ArrayList<>();

	public DbFile() {}

	public DbFile(File srcFile) {
		String[] splits = splitOffFileExtension(srcFile.getName());
		setName(splits[0]);
		setExtension(splits[1]);
	}

	public void putFile(File srcFile) {
		String[] splits = splitOffFileExtension(srcFile.getName());
		setName(splits[0]);
		setExtension(splits[1]);

		File destFile = Paths.get(filesPath, getId() + "." + getExtension()).toFile();
		if (!srcFile.getPath().equals(destFile.getPath())) {
			try {
				FileUtils.copyFile(srcFile, destFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private String[] splitOffFileExtension(String name) {
		int index = name.lastIndexOf('.');
		String[] splits = new String[2];
		splits[0] = name.substring(0, index);
		splits[1] = name.substring(index + 1, name.length());
		return splits;
	}

	public File createFile() {
		return Paths.get(filesPath, getId() + "." + extension).toFile();
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
		return String.format("DbFile [name=%s, extension=%s, fileTags %s]", name, extension,
				fileTags, super.toString());
	}

	@Override
	public int compareTo(DbFile that) {
		if (that == null) return -1;
		int result;
		result = name.compareTo(that.name);
		if (result != 0) return result;
		result = extension.compareTo(that.extension);
		if (result != 0) return result;
		result = EntityUtils.compare(fileTags, that.fileTags);
		if (result != 0) return result;
		return super.compareTo(that);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!super.equals(obj)) return false;
		if (!(obj instanceof DbFile)) return false;
		DbFile other = (DbFile) obj;
		if (!EntityUtils.equals(extension, other.extension)) return false;
		if (!EntityUtils.equals(name, other.name)) return false;
		if (!EntityUtils.equals(fileTags, other.fileTags)) return false;
		if (!EntityUtils.equals(notes, other.notes)) return false;
		return true;
	}

	public static String getFilesPath() {
		return filesPath;
	}

	public static void setFilesPath(String filesPath) {
		DbFile.filesPath = filesPath;
	}

	// Getters & Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension.toLowerCase();
	}

	public List<FileTag> getFileTags() {
		return fileTags;
	}

	public void setFileTags(List<FileTag> fileTags) {
		this.fileTags = fileTags;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

}
