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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.artorg.tools.phantomData.server.model.AbstractPropertifiedEntity;
import org.artorg.tools.phantomData.server.model.BackReference;
import org.artorg.tools.phantomData.server.models.base.DbFile;
import org.artorg.tools.phantomData.server.models.base.Note;
import org.artorg.tools.phantomData.server.models.base.person.Person;
import org.artorg.tools.phantomData.server.models.phantom.SimulationPhantom;
import org.artorg.tools.phantomData.server.util.EntityUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "SIMULATIONS")
public class Simulation extends AbstractPropertifiedEntity<Simulation>
		implements Serializable, Comparable<Simulation> {
	private static final long serialVersionUID = 3949155160834848919L;
	private static final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

	@Column(name = "START_DATE")
	private Date startDate;

	@ManyToOne
	@JoinColumn(nullable = false)
	@NotNull
	private Person person;

	@JsonIgnoreProperties("simulations")
	@ManyToOne
	@JoinColumn(nullable = false)
	@NotNull
	private Project project;
	
	@ManyToMany
	private List<DbFile> files = new ArrayList<>();

	@ManyToMany
	private List<Note> notes = new ArrayList<>();

	@JsonIgnoreProperties("simulations")
	@ManyToMany(mappedBy = "simulations")
	private List<SimulationPhantom> simulationPhantoms = new ArrayList<>();

	public Simulation() {}

	public Simulation(Date startDate, Person person, Project project) {
		this.startDate = startDate;
		this.person = person;
		this.project = project;
	}

	@Override
	public Class<Simulation> getItemClass() {
		return Simulation.class;
	}

	@Override
	public String toName() {
		if (project == null) return format.format(startDate);
		else
			return format.format(startDate) + ": " + project.toName();
	}

	@Override
	public String toString() {
		return String.format(
				"Simulation [startDate=%s, person=%s, project=%s, files=%s, notes=%s, %s]",
				startDate, person, project, files, notes,
				super.toString());
	}

	@Override
	public int compareTo(Simulation that) {
		if (that == null) return -1;
		int result;
		result = startDate.compareTo(that.startDate);
		if (result != 0) return result;
		result = person.compareTo(that.person);
		if (result != 0) return result;
		result = project.compareTo(that.project);
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
		if (!(obj instanceof Simulation)) return false;
		Simulation other = (Simulation) obj;
		if (!EntityUtils.equals(startDate, other.startDate)) return false;
		if (!EntityUtils.equals(person, other.person)) return false;
		if (!EntityUtils.equals(project, other.project)) return false;
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
	public List<SimulationPhantom> getSimulationPhantoms() {
		return simulationPhantoms;
	}

	@BackReference
	public void setSimulationPhantoms(List<SimulationPhantom> simulationPhantoms) {
		this.simulationPhantoms = simulationPhantoms;
	}

}