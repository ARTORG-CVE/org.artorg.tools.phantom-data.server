package org.artorg.tools.phantomData.server.model.specification;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import org.artorg.tools.phantomData.server.model.person.Person;
import org.artorg.tools.phantomData.server.specification.DbPersistentUUID;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY,
	property = "itemClass")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class,
	property = "@id")
public abstract class AbstractBaseEntity<ITEM extends AbstractBaseEntity<ITEM>>
	implements DbPersistentUUID<ITEM>, Serializable {
	private static final long serialVersionUID = -2814334933013431607L;

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

	public AbstractBaseEntity() {
		this.dateAdded = new Date();
		this.dateLastModified = dateAdded;
		this.creator = null;
		this.changer = null;
	}

	public abstract String toName();

	public void changed(Person changer) {
		this.dateLastModified = new Date();
		this.changer = changer;
	}

	@Override
	public String toString() {
		return String.format(
			"dateLastModified=%s, creator=%s, dateAdded=%s, changer=%s, id=%s",
			dateLastModified, creator, dateAdded, changer, id);
	}

	@Override
	public int compareTo(ITEM that) {
		if (that == null) return -1;
		int result;
		result = getDateLastModified().compareTo(that.getDateLastModified());
		if (result != 0) return result;
		result = getChanger().compareTo(that.getChanger());
		if (result != 0) return result;
		result = getDateAdded().compareTo(that.getDateAdded());
		if (result != 0) return result;
		result = getCreator().compareTo(that.getCreator());
		if (result != 0) return result;
		return getId().compareTo(that.getId());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((changer == null) ? 0 : changer.hashCode());
		result = prime * result + ((creator == null) ? 0 : creator.hashCode());
		result = prime * result + ((dateAdded == null) ? 0 : dateAdded.hashCode());
		result = prime * result
			+ ((dateLastModified == null) ? 0 : dateLastModified.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (!(obj instanceof AbstractBaseEntity)) return false;
		AbstractBaseEntity<?> other = (AbstractBaseEntity<?>) obj;
		if (changer == null) {
			if (other.changer != null) return false;
		} else if (!changer.equals(other.changer)) return false;
		if (creator == null) {
			if (other.creator != null) return false;
		} else if (!creator.equals(other.creator)) return false;
		if (dateAdded == null) {
			if (other.dateAdded != null) return false;
		} else if (!dateAdded.equals(other.dateAdded)) return false;
		if (dateLastModified == null) {
			if (other.dateLastModified != null) return false;
		} else if (!dateLastModified.equals(other.dateLastModified)) return false;
		if (id == null) {
			if (other.id != null) return false;
		} else if (!id.equals(other.id)) return false;
		return true;
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
