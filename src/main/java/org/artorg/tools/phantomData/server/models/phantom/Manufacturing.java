package org.artorg.tools.phantomData.server.models.phantom;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.artorg.tools.phantomData.server.model.AbstractPropertifiedEntity;
import org.artorg.tools.phantomData.server.model.DbPersistentUUID;
import org.artorg.tools.phantomData.server.models.base.DbFile;
import org.artorg.tools.phantomData.server.models.base.Note;
import org.artorg.tools.phantomData.server.util.EntityUtils;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "MANUFACTURINGS")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Manufacturing extends AbstractPropertifiedEntity<Manufacturing>
		implements Comparable<Manufacturing>, Serializable, DbPersistentUUID<Manufacturing> {
	private static final long serialVersionUID = -6968718300969800196L;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	@ManyToMany
//	@JsonIgnoreProperties({"manufacturings","phantoms"})
//	@JoinTable(name = "MANUFACTURING_FILES", joinColumns = @JoinColumn(name = "MANUFACTURINGS_ID"),
//	inverseJoinColumns = @JoinColumn(name = "FILES_ID"))
	private List<DbFile> files;

	@ManyToMany
	private List<Note> notes;

	{
		files = new ArrayList<DbFile>();
		notes = new ArrayList<Note>();
	}

	public Manufacturing() {}

	public Manufacturing(String name, String description) {
		this.name = name;
		this.description = description;
	}

	@Override
	public String toName() {
		return name;
	}

	@Override
	public Class<Manufacturing> getItemClass() {
		return Manufacturing.class;
	}

	@Override
	public String toString() {
		return String.format("Manufacturing [name=%s, description=%s, files=%s, notes=%s, %s]",
				name, description, files, notes, super.toString());
	}

	@Override
	public int compareTo(Manufacturing that) {
		if (that == null) return -1;
		int result;
		result = name.compareTo(that.name);
		if (result != 0) return result;
		result = description.compareTo(that.description);
		if (result != 0) return result;
		result = EntityUtils.compare(files, that.files);
		if (result != 0) return result;
		result = EntityUtils.compare(notes, that.notes);
		if (result != 0) return result;
		return super.compareTo(that);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!super.equals(obj)) return false;
		if (!(obj instanceof Manufacturing)) return false;
		Manufacturing other = (Manufacturing) obj;
		if (!EntityUtils.equals(name, other.name)) return false;
		if (!EntityUtils.equals(description, other.description)) return false;
		if (!EntityUtils.equals(files, other.files)) return false;
		if (!EntityUtils.equals(notes, other.notes)) return false;
		return true;
	}

	// Getters & Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
