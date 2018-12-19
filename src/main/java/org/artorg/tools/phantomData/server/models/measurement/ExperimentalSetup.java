package org.artorg.tools.phantomData.server.models.measurement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.artorg.tools.phantomData.server.model.AbstractPropertifiedEntity;
import org.artorg.tools.phantomData.server.model.BackReference;
import org.artorg.tools.phantomData.server.model.DbPersistentUUID;
import org.artorg.tools.phantomData.server.models.base.DbFile;
import org.artorg.tools.phantomData.server.models.base.Note;
import org.artorg.tools.phantomData.server.util.EntityUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "SETUPS")
public class ExperimentalSetup extends AbstractPropertifiedEntity<ExperimentalSetup> implements
		Comparable<ExperimentalSetup>, Serializable, DbPersistentUUID<ExperimentalSetup> {
	private static final long serialVersionUID = 3415494342551630885L;

	@Column(name = "SHORT_NAME", nullable = false)
	private String shortName;

	@Column(name = "LONG_NAME", nullable = false)
	private String longName;

	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	@ManyToMany
	private List<DbFile> files = new ArrayList<>();

	@ManyToMany
	private List<Note> notes = new ArrayList<>();

	@JsonIgnoreProperties({"experimentalSetup","project","phantoms"})
	@OneToMany(mappedBy = "experimentalSetup")
	private List<Measurement> measurements = new ArrayList<>();
	
	public ExperimentalSetup() {}

	public ExperimentalSetup(String shortName, String longName, String description) {
		this.shortName = shortName;
		this.longName = longName;
		this.description = description;
	}

	@Override
	public String toName() {
		return longName;
	}

	@Override
	public Class<ExperimentalSetup> getItemClass() {
		return ExperimentalSetup.class;
	}

	@Override
	public String toString() {
		return String.format(
				"ExperimentalSetup [shortName=%s, longName=%s, description=%s, files=%s, notes=%s, %s]",
				shortName, longName, description, files, notes, super.toString());
	}

	@Override
	public int compareTo(ExperimentalSetup that) {
		if (that == null) return -1;
		int result;
		result = shortName.compareTo(that.shortName);
		if (result != 0) return result;
		result = longName.compareTo(that.longName);
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
		if (!(obj instanceof ExperimentalSetup)) return false;
		ExperimentalSetup other = (ExperimentalSetup) obj;
		if (!EntityUtils.equals(shortName, other.shortName)) return false;
		if (!EntityUtils.equals(longName, other.longName)) return false;
		if (!EntityUtils.equals(description, other.description)) return false;
		if (!EntityUtils.equals(files, other.files)) return false;
		if (!EntityUtils.equals(notes, other.notes)) return false;
		return true;
	}

	// Getters & Setters
	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getLongName() {
		return longName;
	}

	public void setLongName(String longName) {
		this.longName = longName;
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
	
	@BackReference
	public List<Measurement> getMeasurements() {
		return measurements;
	}

	@BackReference
	public void setMeasurements(List<Measurement> measurements) {
		this.measurements = measurements;
	}
	
}
