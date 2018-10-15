package org.artorg.tools.phantomData.server.model.specification;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "itemClass")
public abstract class AbstractBaseEntity<ITEM> implements DbPersistentUUID<ITEM>, Serializable {
	private static final long serialVersionUID = -2814334933013431607L;
	
	@Id
	@Column(name = "ID", nullable = false)
	private UUID id = UUID.randomUUID();
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Column(name = "USER_CREATOR")
	private Person creator;
	
	@Column(name = "USER_CHANGER")
	private Person changer;
	
	@Column(name = "DATE_ADDED", nullable = false)
	private Date dateAdded;
	
	@Column(name = "DATE_LAST_MODIFIED", nullable = false)
	private Date dateLastModified;
	
	@Column(name = "DATE_FORMAT_ADDED", nullable = false)
	private String dateFormatAdded;
	
	@Column(name = "DATE_FORMAT_LAST_MODIFIED", nullable = false)
	private String dateFormatLastModified;
	
	public AbstractBaseEntity() {
		this("", null);
	}
	
	public AbstractBaseEntity(String name, Person creator) {
		this.name = name;
		this.dateAdded = new Date();
		this.dateLastModified = dateAdded;
		this.dateFormatAdded = "yyyy-MM-dd";
		this.dateFormatLastModified = "yyyy-MM-dd";
		this.creator = creator;
		this.changer = creator;
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
		changed(createName(), null);
	}
	
	public void changed(String name, Person changer) {
		this.dateLastModified = new Date();
		this.name = name;
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
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((changer == null) ? 0 : changer.hashCode());
		result = prime * result + ((creator == null) ? 0 : creator.hashCode());
		result = prime * result + ((dateAdded == null) ? 0 : dateAdded.hashCode());
		result = prime * result + ((dateFormatAdded == null) ? 0 : dateFormatAdded.hashCode());
		result = prime * result + ((dateFormatLastModified == null) ? 0 : dateFormatLastModified.hashCode());
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
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
		if (dateFormatAdded == null) {
			if (other.dateFormatAdded != null)
				return false;
		} else if (!dateFormatAdded.equals(other.dateFormatAdded))
			return false;
		if (dateFormatLastModified == null) {
			if (other.dateFormatLastModified != null)
				return false;
		} else if (!dateFormatLastModified.equals(other.dateFormatLastModified))
			return false;
		if (dateLastModified == null) {
			if (other.dateLastModified != null)
				return false;
		} else if (!dateLastModified.equals(other.dateLastModified))
			return false;
		return true;
	}
	
	public String getFormattedDateAdded() {
		return new SimpleDateFormat(this.dateFormatAdded).format(this.dateAdded);
	}
	
	public String getFormattedDateLastModified() {
		return new SimpleDateFormat(this.dateFormatLastModified).format(this.dateLastModified);
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public String getDateFormatAdded() {
		return dateFormatAdded;
	}

	public void setDateFormatAdded(String dateFormatAdded) {
		this.dateFormatAdded = dateFormatAdded;
	}

	public Date getDateLastModified() {
		return dateLastModified;
	}

	public void setDateLastModified(Date dateLastModified) {
		this.dateLastModified = dateLastModified;
	}

	public String getDateFormatLastModified() {
		return dateFormatLastModified;
	}

	public void setDateFormatLastModified(String dateFormatLastModified) {
		this.dateFormatLastModified = dateFormatLastModified;
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
