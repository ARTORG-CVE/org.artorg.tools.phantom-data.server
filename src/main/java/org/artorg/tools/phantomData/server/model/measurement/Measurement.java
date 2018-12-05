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

import org.artorg.tools.phantomData.server.model.base.DbFile;
import org.artorg.tools.phantomData.server.model.base.Note;
import org.artorg.tools.phantomData.server.model.base.person.Person;
import org.artorg.tools.phantomData.server.model.phantom.Phantom;
import org.artorg.tools.phantomData.server.model.phantom.Phantomina;
import org.artorg.tools.phantomData.server.model.specification.AbstractPropertifiedEntity;
import org.artorg.tools.phantomData.server.util.EntityUtils;

@Entity
@Table(name = "MEASUREMENTS")
public class Measurement extends AbstractPropertifiedEntity<Measurement> implements Serializable, Comparable<Measurement> {
	private static final long serialVersionUID = 3949155160834848919L;

	@Column(name = "NAME")
	private String name;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "START_DATE")
	private Date startDate;
	
	@Column(name = "DATE_FORMAT")
	private String dateFormat;
	
	@OneToOne
	@JoinColumn(nullable = false)
	private Person person;
	
	@ManyToMany
	private List<DbFile> files;

	@ManyToMany
	private List<Note> notes;
	
	{
		files = new ArrayList<DbFile>();
		notes = new ArrayList<Note>();
	}
	
	public Measurement() {}
	
	public Measurement(String name, String description, Date startDate, String dateFormat, Person person) {
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.dateFormat = dateFormat;
		this.person = person;
	}
	
	@Override
	public Class<Measurement> getItemClass() {
		return Measurement.class;
	}

	@Override
	public String toName() {
		return new SimpleDateFormat(dateFormat).format(startDate) +", " +name;
	}
	
	@Override
	public String toString() {
		return String.format(
			"Measurement [name=%s, description=%s, startDate=%s, dateFormat=%s, person=%s, files=%s, notes=%s, %s]",
			name, description, startDate, dateFormat, person, files, notes, super.toString());
	}
	
	@Override
	public int compareTo(Measurement that) {
		if (that == null) return -1;
		int result;
		result = name.compareTo(that.name);
		if (result != 0) return result;
		result = description.compareTo(that.description);
		if (result != 0) return result;
		result = startDate.compareTo(that.startDate);
		if (result != 0) return result;
		result = dateFormat.compareTo(that.dateFormat);
		if (result != 0) return result;
		result = person.compareTo(that.person);
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
		if (!EntityUtils.equals(name, other.name)) return false;
		if (!EntityUtils.equals(description, other.description)) return false;
		if (!EntityUtils.equals(startDate, other.startDate)) return false;
		if (!EntityUtils.equals(dateFormat, other.dateFormat)) return false;
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
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