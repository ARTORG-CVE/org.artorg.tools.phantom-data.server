package org.artorg.tools.phantomData.server.model.specification;

import java.io.Serializable;
import java.util.Date;
import java.util.Enumeration;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

import org.artorg.tools.phantomData.server.model.Person;
import org.artorg.tools.phantomData.server.specification.DbPersistentUUID;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "itemClass")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public abstract class AbstractBaseEntity<ITEM> implements DbPersistentUUID<ITEM>, Serializable {
	private static final long serialVersionUID = -2814334933013431607L;
	
	@Id
	@Column(name = "ID", nullable = false)
	private UUID id = UUID.randomUUID();
	
	@Column(name = "USER_CREATOR")
	private Person creator;
	
	@Column(name = "USER_CHANGER")
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
	
	public abstract String createName();
	
	public static <T> Stream<T> enumerationAsStream(Enumeration<T> e) {
	    return StreamSupport.stream(
	        new Spliterators.AbstractSpliterator<T>(Long.MAX_VALUE, Spliterator.ORDERED) {
	            public boolean tryAdvance(Consumer<? super T> action) {
	                if(e.hasMoreElements()) {
	                    action.accept(e.nextElement());
	                    return true;
	                }
	                return false;
	            }
	            public void forEachRemaining(Consumer<? super T> action) {
	                while(e.hasMoreElements()) action.accept(e.nextElement());
	            }
	    }, false);
	}
	
	public void updateName() {
		changed(null);
	}
	
	public void changed(Person changer) {
		this.dateLastModified = new Date();
		this.changer = changer; 
	}

	@Override
	public int compareTo(ITEM o) {
		return Integer.compare(this.hashCode(), o.hashCode());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((changer == null) ? 0 : changer.hashCode());
		result = prime * result + ((creator == null) ? 0 : creator.hashCode());
		result = prime * result + ((dateAdded == null) ? 0 : dateAdded.hashCode());
		result = prime * result + ((dateLastModified == null) ? 0 : dateLastModified.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof AbstractBaseEntity))
			return false;
		AbstractBaseEntity<?> other = (AbstractBaseEntity<?>) obj;
		if (getItemClass() == null) {
			if (other.getItemClass() != null)
				return false;
		} else if (!getItemClass().equals(other.getItemClass()))
			return false;
		if (changer == null) {
			if (other.changer != null)
				return false;
		} else if (!changer.equals(other.changer))
			return false;
		if (creator == null) {
			if (other.creator != null)
				return false;
		} else if (!creator.equals(other.creator))
			return false;
		if (dateAdded == null) {
			if (other.dateAdded != null)
				return false;
		} else if (!dateAdded.equals(other.dateAdded))
			return false;
		if (dateLastModified == null) {
			if (other.dateLastModified != null)
				return false;
		} else if (!dateLastModified.equals(other.dateLastModified))
			return false;
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
