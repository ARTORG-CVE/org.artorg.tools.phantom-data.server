package org.artorg.tools.phantomData.server.model.specification;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;

import org.artorg.tools.phantomData.server.model.DbFile;
import org.artorg.tools.phantomData.server.model.Note;
import org.artorg.tools.phantomData.server.model.property.BooleanProperty;
import org.artorg.tools.phantomData.server.model.property.DateProperty;
import org.artorg.tools.phantomData.server.model.property.DoubleProperty;
import org.artorg.tools.phantomData.server.model.property.IntegerProperty;
import org.artorg.tools.phantomData.server.model.property.StringProperty;
import org.artorg.tools.phantomData.server.specification.DbPersistentUUID;
import org.artorg.tools.phantomData.server.util.EntityUtils;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class AbstractBaseEntity<ITEM extends AbstractBaseEntity<ITEM>>
	extends AbstractPersonifiedEntity<ITEM>
	implements DbPersistentUUID<ITEM>, Serializable, NameGeneratable {
	private static final long serialVersionUID = -2814334933013431607L;

	@ManyToMany
	private List<DbFile> files;

	@ManyToMany
	private List<Note> notes;

	@ManyToMany
	private List<BooleanProperty> booleanProperties;

	@ManyToMany
	private List<IntegerProperty> integerProperties;
	
	@ManyToMany
	private List<DoubleProperty> doubleProperties;
	
	@ManyToMany
	private List<StringProperty> stringProperties;
	
	@ManyToMany
	private List<DateProperty> dateProperties;

	public AbstractBaseEntity() {
		files = new ArrayList<DbFile>();
		notes = new ArrayList<Note>();
		booleanProperties = new ArrayList<BooleanProperty>();
		dateProperties = new ArrayList<DateProperty>();
		stringProperties = new ArrayList<StringProperty>();
		integerProperties = new ArrayList<IntegerProperty>();
		doubleProperties = new ArrayList<DoubleProperty>();
	}

	@Override
	public String toString() {
		return String.format(
			"booleanProperties=%s, integerProperties=%s, doubleProperties=%s, "
				+ "stringProperties=%s, dateProperties=%s, files=%s, notes=%s, %s",
			booleanProperties, integerProperties, doubleProperties, stringProperties,
			dateProperties, files, notes, super.toString());
	}

	@Override
	public int compareTo(ITEM item) {
		if (item == null) return -1;
		AbstractBaseEntity<?> that = (AbstractBaseEntity<?>) item;
		int result;
		result = EntityUtils.compare(files, that.files);
		if (result != 0) return result;
		result = EntityUtils.compare(notes, that.notes);
		if (result != 0) return result;
		result = EntityUtils.compare(booleanProperties, that.booleanProperties);
		if (result != 0) return result;
		result = EntityUtils.compare(integerProperties, that.integerProperties);
		if (result != 0) return result;
		result = EntityUtils.compare(doubleProperties, that.doubleProperties);
		if (result != 0) return result;
		result = EntityUtils.compare(stringProperties, that.stringProperties);
		if (result != 0) return result;
		result = EntityUtils.compare(dateProperties, that.dateProperties);
		if (result != 0) return result;
		return super.compareTo(that);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!super.equals(obj)) return false;
		if (!(obj instanceof AbstractBaseEntity)) return false;
		AbstractBaseEntity<?> other = (AbstractBaseEntity<?>) obj;
		if (!EntityUtils.equals(files, other.files)) return false;
		if (!EntityUtils.equals(notes, other.notes)) return false;
		if (!EntityUtils.equals(booleanProperties, other.booleanProperties)) return false;
		if (!EntityUtils.equals(integerProperties, other.integerProperties)) return false;
		if (!EntityUtils.equals(doubleProperties, other.doubleProperties)) return false;
		if (!EntityUtils.equals(stringProperties, other.stringProperties)) return false;
		return EntityUtils.equals(dateProperties, other.dateProperties);
	}

	// Getters & Setters
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

	public List<BooleanProperty> getBooleanProperties() {
		return booleanProperties;
	}

	public List<DateProperty> getDateProperties() {
		return dateProperties;
	}

	public List<StringProperty> getStringProperties() {
		return stringProperties;
	}

	public List<IntegerProperty> getIntegerProperties() {
		return integerProperties;
	}

	public List<DoubleProperty> getDoubleProperties() {
		return doubleProperties;
	}

	public void setBooleanProperties(List<BooleanProperty> properties) {
		this.booleanProperties = properties;
	}

	public void setDateProperties(List<DateProperty> properties) {
		this.dateProperties = properties;
	}

	public void setStringProperties(List<StringProperty> properties) {
		this.stringProperties = properties;
	}

	public void setIntegerProperties(List<IntegerProperty> properties) {
		this.integerProperties = properties;
	}

	public void setDoubleProperties(List<DoubleProperty> properties) {
		this.doubleProperties = properties;
	}

}