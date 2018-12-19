package org.artorg.tools.phantomData.server.models.measurement;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.artorg.tools.phantomData.server.model.AbstractPropertifiedEntity;
import org.artorg.tools.phantomData.server.models.base.DbFile;
import org.artorg.tools.phantomData.server.models.base.Note;
import org.artorg.tools.phantomData.server.models.base.person.Person;
import org.artorg.tools.phantomData.server.util.EntityUtils;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "MEASUREMENTS")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Measurement extends AbstractPropertifiedEntity<Measurement>
		implements Serializable, Comparable<Measurement> {
	private static final long serialVersionUID = 3949155160834848919L;
	private static final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

	@Column(name = "START_DATE")
	private Date startDate;

	@ManyToOne
	@JoinColumn(nullable = false)
	@NotNull
	private Person person;

	@ManyToOne
	@JoinColumn(nullable = false)
	@NotNull
	private Project project;

	@ManyToOne
	@JoinColumn(nullable = false)
	@NotNull
	private ExperimentalSetup experimentalSetup;

	@OneToOne
	@JoinColumn(nullable = true)
	@NotNull
	private DbFile protocolFile;

	@ManyToMany
	private List<DbFile> files = new ArrayList<>();

	@ManyToMany
	private List<Note> notes = new ArrayList<>();

	public Measurement() {}

	public Measurement(Date startDate, Person person, Project project, ExperimentalSetup setup,
			DbFile protocolFile) {
		this.startDate = startDate;
		this.person = person;
		this.project = project;
		this.experimentalSetup = setup;
		this.protocolFile = protocolFile;
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
				"Measurement [startDate=%s, person=%s, project=%s, experimentalSetup=%s, protocolFile=%s, files=%s, notes=%s, %s]",
				startDate, person, project, experimentalSetup, protocolFile, files, notes,
				super.toString());
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
		result = protocolFile.compareTo(that.protocolFile);
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
		if (!EntityUtils.equals(protocolFile, other.protocolFile)) return false;
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

	public DbFile getProtocolFile() {
		return protocolFile;
	}

	public void setProtocolFile(DbFile protocolFile) {
		this.protocolFile = protocolFile;
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