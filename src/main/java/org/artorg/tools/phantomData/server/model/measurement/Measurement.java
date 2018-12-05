package org.artorg.tools.phantomData.server.model.measurement;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.artorg.tools.phantomData.server.model.base.DbFile;
import org.artorg.tools.phantomData.server.model.base.Note;
import org.artorg.tools.phantomData.server.model.base.person.Person;
import org.artorg.tools.phantomData.server.model.specification.AbstractPropertifiedEntity;
import org.artorg.tools.phantomData.server.util.EntityUtils;

@Entity
@Table(name = "MEASUREMENTS")
public class Measurement extends AbstractPropertifiedEntity<Measurement>
	implements Serializable, Comparable<Measurement> {
	private static final long serialVersionUID = 3949155160834848919L;
	private static final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

	@Column(name = "START_DATE")
	private Date startDate;

	@OneToOne
	@JoinColumn(nullable = false)
	@NotNull
	private Person person;

	@OneToOne
	@JoinColumn(nullable = false)
	@NotNull
	private Project project;

	@OneToOne
	@JoinColumn(nullable = false)
	@NotNull
	private ExperimentalSetup experimentalSetup;

	@ManyToMany
	private List<DbFile> files;

	@ManyToMany
	private List<Note> notes;

	{
		files = new ArrayList<DbFile>();
		notes = new ArrayList<Note>();
	}

	public Measurement() {}

	public Measurement(Date startDate, Person person, Project project,
		ExperimentalSetup setup) {
		this.startDate = startDate;
		this.person = person;
		this.project = project;
		this.experimentalSetup = setup;
	}

	@Override
	public Class<Measurement> getItemClass() {
		return Measurement.class;
	}

	@Override
	public String toName() {
		return format.format(startDate) + ": " + experimentalSetup.getShortName() + ", "
			+ project.toName();
	}

	@Override
	public String toString() {
		return String.format(
			"Measurement [startDate=%s, person=%s, project=%s, experimentalSetup=%s, files=%s, notes=%s, %s]",
			startDate, person, project, experimentalSetup, files, notes, super.toString());
	}

	@Override
	public int compareTo(Measurement that) {
		if (that == null) return -1;
		int result;
		result = startDate.compareTo(that.startDate);
		if (result != 0) return result;
		result = person.compareTo(that.person);
		if (result != 0) return result;
		result = project.compareTo(that.project);
		if (result != 0) return result;
		result = experimentalSetup.compareTo(that.experimentalSetup);
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
		if (!(obj instanceof Measurement)) return false;
		Measurement other = (Measurement) obj;
		if (!EntityUtils.equals(startDate, other.startDate)) return false;
		if (!EntityUtils.equals(person, other.person)) return false;
		if (!EntityUtils.equals(project, other.project)) return false;
		if (!EntityUtils.equals(experimentalSetup, other.experimentalSetup)) return false;
		if (!EntityUtils.equals(files, other.files)) return false;
		if (!EntityUtils.equals(notes, other.notes)) return false;
		return true;
	}

	// Getters & Setters
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
	public ExperimentalSetup getExperimentalSetup() {
		return experimentalSetup;
	}

	public void setExperimentalSetup(ExperimentalSetup experimentalSetup) {
		this.experimentalSetup = experimentalSetup;
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