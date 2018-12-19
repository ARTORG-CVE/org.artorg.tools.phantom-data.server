package org.artorg.tools.phantomData.server.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import org.artorg.tools.phantomData.server.models.base.person.Person;
import org.artorg.tools.phantomData.server.util.EntityUtils;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class AbstractPersonifiedEntity<T> implements DbPersistentUUID<T>, Serializable, NameGeneratable {
	private static final long serialVersionUID = 2549825982365608082L;

	@Id
	@Column(name = "ID", nullable = false)
	private UUID id = UUID.randomUUID();

	@OneToOne
	private Person creator;

	@OneToOne
	private Person changer;

	@Column(name = "DATE_ADDED", nullable = false)
	private Date dateAdded;

	@Column(name = "DATE_LAST_MODIFIED", nullable = false)
	private Date dateLastModified;
	
	public AbstractPersonifiedEntity() {
		this.dateAdded = new Date();
		this.dateLastModified = dateAdded;
		this.creator = null;
		this.changer = null;
	}
	
	public void changed(Person changer) {
		this.dateLastModified = new Date();
		this.changer = changer;
	}
	
	@Override
	public String toString() {
		return String.format(
			"dateLastModified=%s, changer=%s, "
			+ "dateAdded=%s, creator=%s, id=%s",
			dateLastModified, changer, dateAdded, creator, id);
	}

	@Override
	public int compareTo(T item) {
		if (item == null) return -1;
		AbstractPersonifiedEntity<?> that = (AbstractPersonifiedEntity<?>) item;
		int result;
		result = dateLastModified.compareTo(that.dateLastModified);
		if (result != 0) return result;
		result = changer.compareTo(that.changer);
		if (result != 0) return result;
		result = dateAdded.compareTo(that.dateAdded);
		if (result != 0) return result;
		result = creator.compareTo(that.creator);
		if (result != 0) return result;
		return id.compareTo(that.id);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof AbstractPersonifiedEntity)) return false;
		AbstractPersonifiedEntity<?> other = (AbstractPersonifiedEntity<?>) obj;
		if (!EntityUtils.equals(changer, other.changer)) return false;
		if (!EntityUtils.equals(creator, other.creator)) return false;
		if (!EntityUtils.equals(dateAdded, other.dateAdded)) return false;
		return EntityUtils.equals(dateLastModified, other.dateLastModified);
	}
	
	// Getters & Setters
	@Override
	public UUID getId() {
		return id;
	}

	@Override
	public void setId(UUID id) {
		this.id = id;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public Date getDateLastModified() {
		return dateLastModified;
	}

	public void setDateLastModified(Date dateLastModified) {
		this.dateLastModified = dateLastModified;
	}

	public Person getCreator() {
		return creator;
	}

	public void setCreator(Person creator) {
		this.creator = creator;
	}

	public Person getChanger() {
		return changer;
	}

	public void setChanger(Person changer) {
		this.changer = changer;
	}
	
}
