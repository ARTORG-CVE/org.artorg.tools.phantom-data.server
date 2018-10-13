package org.artorg.tools.phantomData.server.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.artorg.tools.phantomData.server.BootApplication;
import org.artorg.tools.phantomData.server.beans.BeanMap;
import org.artorg.tools.phantomData.server.model.specification.Gender;
import org.artorg.tools.phantomData.server.specification.DbPersistentUUID;

@Entity
@Table(name = "USERS")
public class Person implements Comparable<Person>, Serializable, DbPersistentUUID<Person> {
	private static final long serialVersionUID = 8153106662017090155L;
	
	@Id
	@Column(name = "ID", nullable = false)
	private UUID id = UUID.randomUUID();
	
	@Column(name = "ACADEMIC_TITLE", nullable = false)
	private AcademicTitle academicTitle;
	
	@Column(name = "FIRSTNAME", nullable = false)
	private String firstname;
	
	@Column(name = "LASTNAME", nullable = false)
	private String lastname;
	
	@Column(name = "GENDER", nullable = false)
	private String genderString;
	
	
	public Person() {
		firstname = "";
		lastname = "";
		genderString = "";
	}
	
	public Person(AcademicTitle academicTitle, String firstname, String lastname, Gender gender) {
		this.academicTitle = academicTitle;
		this.firstname = firstname;
		this.lastname = lastname;
		if (gender == Gender.MALE)
			this.genderString = "Male";
		else 
			this.genderString = "Female";
	}
	
	
	public String getAcademicName() {
		return academicTitle.getPrefix() +" " +getName();
	}
	
	public String getSimpleAcademicName() {
		return academicTitle.getPrefix() +" " +getSimpleName();
	}
	
	public String getName() {
		return firstname +" " +lastname;
	}
	
	public String getSimpleName() {
		String firstnameAbreviation = getFirstnameInitials().stream()
				.map(c -> String.valueOf(c) +". ").collect(Collectors.joining());
		return firstnameAbreviation +lastname;
	}
	
	public String getInitialsName() {
		String firstnameAbreviation = getFirstnameInitials().stream()
				.map(c -> String.valueOf(c) +". ").collect(Collectors.joining());
		String lastnameAbreviation = getLastnameInitials().stream()
				.map(c -> String.valueOf(c)).collect(Collectors.joining(". "));
		return firstnameAbreviation +lastnameAbreviation +".";
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
		return Arrays.asList(name.split(" ")).stream()
				.filter(s -> s.length()!=0)
				.map(s -> s.trim())
				.map(s -> s.charAt(0))
				.collect(Collectors.toList());
	}
	
	@Override
	public int compareTo(Person o) {
		return Integer.compare(this.hashCode(), o.hashCode());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((academicTitle == null) ? 0 : academicTitle.hashCode());
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result + ((genderString == null) ? 0 : genderString.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (academicTitle == null) {
			if (other.academicTitle != null)
				return false;
		} else if (!academicTitle.equals(other.academicTitle))
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (genderString == null) {
			if (other.genderString != null)
				return false;
		} else if (!genderString.equals(other.genderString))
			return false;
		return true;
	}

	@Override
	public Class<Person> getItemClass() {
		return Person.class;
	}
	
	public Gender getGender() {
		if (this.genderString.equals(Gender.MALE))
			return Gender.MALE;
		else if (this.genderString.equals(Gender.FEMALE))
			return Gender.FEMALE;
		return null;
	}
	
	public void setGender(Gender gender) {
		this.genderString = gender.toString();
	}
	
	@Override
	public UUID getId() {
		return id;
	}
	
	@Override
	public void setId(UUID id) {
		this.id = id;
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
	
	public String getGenderString() {
		return genderString;
	}

	public void setGenderString(String gender) {
		this.genderString = gender;
	}

}
