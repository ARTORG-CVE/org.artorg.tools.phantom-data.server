package org.artorg.tools.phantomData.server.models.base.person;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.artorg.tools.phantomData.server.model.DbPersistentUUID;
import org.artorg.tools.phantomData.server.model.NameGeneratable;
import org.artorg.tools.phantomData.server.models.measurement.Measurement;
import org.artorg.tools.phantomData.server.models.measurement.Project;

@Entity
@Table(name = "USERS")
public class Person implements Comparable<Person>, Serializable, DbPersistentUUID<Person>, NameGeneratable {
	private static final long serialVersionUID = 8153106662017090155L;

	@Id
	@Column(name = "ID", nullable = false)
	private UUID id = UUID.randomUUID();
	
	@OneToOne
	private AcademicTitle academicTitle;

	@Column(name = "FIRSTNAME", nullable = false)
	private String firstname;

	@Column(name = "LASTNAME", nullable = false)
	private String lastname;

	@OneToOne
	private Gender gender;
	
	@OneToMany (mappedBy="person")
	private List<Measurement> measurements = new ArrayList<>();
	
	@OneToMany (mappedBy="leader")
	private List<Project> leaderProjects = new ArrayList<>();
	
	@ManyToMany (mappedBy="members")
	private List<Project> memberProjects = new ArrayList<>();

	public Person() {}

	public Person(AcademicTitle academicTitle, String firstname, String lastname,
		Gender gender) {
		this.academicTitle = academicTitle;
		this.firstname = firstname;
		this.lastname = lastname;
		this.gender = gender;
	}

	public String getAcademicName() {
		return academicTitle.getPrefix() + " " + getName();
	}

	public String getSimpleAcademicName() {
		return academicTitle.getPrefix() + " " + getSimpleName();
	}

	public String getName() {
		return firstname + " " + lastname;
	}

	public String getSimpleName() {
		String firstnameAbreviation = getFirstnameInitials().stream()
			.map(c -> String.valueOf(c) + ". ").collect(Collectors.joining());
		return firstnameAbreviation + lastname;
	}

	public String getInitialsName() {
		String firstnameAbreviation = getFirstnameInitials().stream()
			.map(c -> String.valueOf(c) + ". ").collect(Collectors.joining());
		String lastnameAbreviation = getLastnameInitials().stream()
			.map(c -> String.valueOf(c)).collect(Collectors.joining(". "));
		return firstnameAbreviation + lastnameAbreviation + ".";
	}

	public List<Character> getInitials() {
		List<Character> initials = getFirstnameInitials();
		initials.addAll(getLastnameInitials());
		return initials;
	}

	public List<Character> getFirstnameInitials() {
		return getInitials(firstname);
	}

	public List<Character> getLastnameInitials() {
		return getInitials(lastname);
	}

	public List<Character> getInitials(String name) {
		return Arrays.asList(name.split(" ")).stream().filter(s -> s.length() != 0)
			.map(s -> s.trim()).map(s -> s.charAt(0)).collect(Collectors.toList());
	}

	@Override
	public String toName() {
		return getSimpleAcademicName();
	}
	
	@Override
	public Class<Person> getItemClass() {
		return Person.class;
	}

	@Override
	public String toString() {
		return String.format(
			"Person [academicTitle=%s, firstname=%s, lastname=%s, gender=%s]",
			academicTitle, firstname, lastname, gender);
	}

	@Override
	public int compareTo(Person that) {
		if (that == null) return -1;
		int result;
		result = getLastname().compareTo(that.getLastname());
		if (result != 0) return result;
		result = getFirstname().compareTo(that.getFirstname());
		if (result != 0) return result;
		result = getAcademicTitle().compareTo(that.getAcademicTitle());
		if (result != 0) return result;
		return getGender().compareTo(that.getGender());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof Person)) return false;
		Person other = (Person) obj;
		if (academicTitle == null) {
			if (other.academicTitle != null) return false;
		} else if (!academicTitle.equals(other.academicTitle)) return false;
		if (firstname == null) {
			if (other.firstname != null) return false;
		} else if (!firstname.equals(other.firstname)) return false;
		if (gender == null) {
			if (other.gender != null) return false;
		} else if (!gender.equals(other.gender)) return false;
		if (lastname == null) {
			if (other.lastname != null) return false;
		} else if (!lastname.equals(other.lastname)) return false;
		return true;
	}

	// Getters & Setters
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
	
	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public AcademicTitle getAcademicTitle() {
		return academicTitle;
	}

	public void setAcademicTitle(AcademicTitle academicTitle) {
		this.academicTitle = academicTitle;
	}

	public List<Measurement> getMeasurements() {
		return measurements;
	}

	public void setMeasurements(List<Measurement> measurements) {
		this.measurements = measurements;
	}

	public List<Project> getLeaderProjects() {
		return leaderProjects;
	}

	public void setLeaderProjects(List<Project> leaderProjects) {
		this.leaderProjects = leaderProjects;
	}

	public List<Project> getMemberProjects() {
		return memberProjects;
	}

	public void setMemberProjects(List<Project> memberProjects) {
		this.memberProjects = memberProjects;
	}	

}
