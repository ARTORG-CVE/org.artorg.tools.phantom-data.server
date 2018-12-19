package org.artorg.tools.phantomData.server.models.measurement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.artorg.tools.phantomData.server.model.AbstractPropertifiedEntity;
import org.artorg.tools.phantomData.server.model.BackReference;
import org.artorg.tools.phantomData.server.model.DbPersistentUUID;
import org.artorg.tools.phantomData.server.models.base.DbFile;
import org.artorg.tools.phantomData.server.models.base.Note;
import org.artorg.tools.phantomData.server.models.base.person.Person;
import org.artorg.tools.phantomData.server.util.EntityUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "PROJECTS")
public class Project extends AbstractPropertifiedEntity<Project>
		implements Comparable<Project>, Serializable, DbPersistentUUID<Project> {
	private static final long serialVersionUID = 6517832474752854870L;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	@Column(name = "START_YEAR", nullable = false)
	private short startYear;

	@ManyToOne
	@JoinColumn(nullable = false)
	@NotNull
	private Person leader;

	@ManyToMany
	private List<Person> members = new ArrayList<>();

	@ManyToMany
	private List<DbFile> files = new ArrayList<>();

	@ManyToMany
	private List<Note> notes = new ArrayList<>();

	@JsonIgnoreProperties({"project","phantoms"})
	@OneToMany(mappedBy = "project")
	private List<Measurement> measurements = new ArrayList<>();

	public Project() {}

	public Project(String name, String description, short startYear, Person leader) {
		this.name = name;
		this.description = description;
		this.startYear = startYear;
		this.leader = leader;
	}

	@Override
	public String toName() {
		return name;
	}

	@Override
	public Class<Project> getItemClass() {
		return Project.class;
	}

	@Override
	public String toString() {
		return String.format(
				"Project [name=%s, description=%s, startYear=%s, leader=%s, members=%s, files=%s, notes=%s, %s]",
				name, description, startYear, leader, members, files, notes, super.toString());
	}

	@Override
	public int compareTo(Project that) {
		if (that == null) return -1;
		int result;
		result = name.compareTo(that.name);
		if (result != 0) return result;
		result = Short.compare(startYear, that.startYear);
		if (result != 0) return result;
		result = leader.compareTo(that.leader);
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
		if (!(obj instanceof Project)) return false;
		Project other = (Project) obj;
		if (!EntityUtils.equals(name, other.name)) return false;
		if (!EntityUtils.equals(startYear, other.startYear)) return false;
		if (!EntityUtils.equals(leader, other.leader)) return false;
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

	public short getStartYear() {
		return startYear;
	}

	public void setStartYear(short startYear) {
		this.startYear = startYear;
	}

	public Person getLeader() {
		return leader;
	}

	public void setLeader(Person leader) {
		this.leader = leader;
	}

	public List<Person> getMembers() {
		return members;
	}

	public void setMembers(List<Person> members) {
		this.members = members;
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
