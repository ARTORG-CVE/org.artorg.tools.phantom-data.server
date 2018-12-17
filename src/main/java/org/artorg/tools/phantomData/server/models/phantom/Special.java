package org.artorg.tools.phantomData.server.models.phantom;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.artorg.tools.phantomData.server.model.AbstractPropertifiedEntity;
import org.artorg.tools.phantomData.server.model.DbPersistentUUID;
import org.artorg.tools.phantomData.server.models.base.DbFile;
import org.artorg.tools.phantomData.server.models.base.Note;
import org.artorg.tools.phantomData.server.util.EntityUtils;

@Entity
@Table(name = "SPECIALS")
public class Special extends AbstractPropertifiedEntity<Special>
	implements DbPersistentUUID<Special>, Serializable {
	private static final long serialVersionUID = 4838372606658297575L;

	@Column(name = "SHORTCUT", unique = true, nullable = false)
	private String shortcut;

	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	@ManyToMany
	private List<DbFile> files;

	@ManyToMany
	private List<Note> notes;

	{
		files = new ArrayList<DbFile>();
		notes = new ArrayList<Note>();
	}

	public Special() {}

	public Special(String shortcut, String description) {
		this.shortcut = shortcut;
		this.description = description;
	}

	@Override
	public String toName() {
		return shortcut;
	}

	@Override
	public Class<Special> getItemClass() {
		return Special.class;
	}

	@Override
	public String toString() {
		return String.format("Special [shortcut=%s, files=%s, notes=%s %s]", shortcut,
			files, notes, super.toString());
	}

	@Override
	public int compareTo(Special that) {
		if (that == null) return -1;
		int result;
		result = getShortcut().compareTo(that.getShortcut());
		if (result != 0) return result;
		result = EntityUtils.compare(files, that.getFiles());
		if (result != 0) return result;
		result = EntityUtils.compare(notes, that.getNotes());
		if (result != 0) return result;
		return super.compareTo(that);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof Special)) return false;
		Special other = (Special) obj;
		if (!EntityUtils.equals(shortcut, other.shortcut)) return false;
		if (!EntityUtils.equals(files, other.files)) return false;
		if (!EntityUtils.equals(notes, other.notes)) return false;
		return true;
	}

	// Getters & Setters
	public String getShortcut() {
		return shortcut;
	}

	public void setShortcut(String shortcut) {
		this.shortcut = shortcut;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<DbFile> getFiles() {
		return files;
	}

	public void setFiles(List<DbFile> files) {
		this.files = files;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}
	
}
