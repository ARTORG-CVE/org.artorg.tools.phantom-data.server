package org.artorg.tools.phantomData.server.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.artorg.tools.phantomData.server.model.property.BooleanProperty;
import org.artorg.tools.phantomData.server.model.property.DateProperty;
import org.artorg.tools.phantomData.server.model.property.DoubleProperty;
import org.artorg.tools.phantomData.server.model.property.IntegerProperty;
import org.artorg.tools.phantomData.server.model.property.StringProperty;
import org.artorg.tools.phantomData.server.model.specification.AbstractBaseEntity;
import org.artorg.tools.phantomData.server.specification.AbstractEntity;
import org.artorg.tools.phantomData.server.specification.DbPersistentUUID;

@Entity
@AbstractEntity
@Table(name = "PROPERTIES")
public class Properties extends AbstractBaseEntity<Properties>
	implements Comparable<Properties>, Serializable, DbPersistentUUID<Properties> {
	private static final long serialVersionUID = 9191592162274332907L;

	@Id
	@Column(name = "ID", nullable = false)
	private UUID id = UUID.randomUUID();

	@ManyToMany
	@JoinTable(name = "PROPERTIES_BOOLEAN_PROPERTIES",
		joinColumns = @JoinColumn(name = "PROPERTIES_ID"),
		inverseJoinColumns = @JoinColumn(name = "BOOLEAN_PROPERTY_ID"))
	private List<BooleanProperty> booleanProperties = new ArrayList<BooleanProperty>();

	@ManyToMany
	@JoinTable(name = "PROPERTIES_DATE_PROPERTIES",
		joinColumns = @JoinColumn(name = "PROPERTIES_ID"),
		inverseJoinColumns = @JoinColumn(name = "DATE_PROPERTY_ID"))
	private List<DateProperty> dateProperties = new ArrayList<DateProperty>();

	@ManyToMany
	@JoinTable(name = "PROPERTIES_STRING_PROPERTIES",
		joinColumns = @JoinColumn(name = "PROPERTIES_ID"),
		inverseJoinColumns = @JoinColumn(name = "STRING_PROPERTY_ID"))
	private List<StringProperty> stringProperties = new ArrayList<StringProperty>();

	@ManyToMany
	@JoinTable(name = "PROPERTIES_INTEGER_PROPERTIES",
		joinColumns = @JoinColumn(name = "PROPERTIES_ID"),
		inverseJoinColumns = @JoinColumn(name = "INTEGER_PROPERTY_ID"))
	private List<IntegerProperty> integerProperties = new ArrayList<IntegerProperty>();

	@ManyToMany
	@JoinTable(name = "PROPERTIES_DOUBLE_PROPERTIES",
		joinColumns = @JoinColumn(name = "PROPERTIES_ID"),
		inverseJoinColumns = @JoinColumn(name = "DOUBLE_PROPERTY_ID"))
	private List<DoubleProperty> doubleProperties = new ArrayList<DoubleProperty>();

	public Properties() {}

	@Override
	public String createName() {
		StringBuilder sb = new StringBuilder();
		sb.append("properties{");
		int nLists = 0;
		if (getBooleanProperties().size()>0) {
			sb.append("bools:" +getBooleanProperties().size());
			nLists++;
		}
		if (getIntegerProperties().size()>0) {
			if (nLists > 0) sb.append(", ");
			sb.append("ints:" +getIntegerProperties().size());
			nLists++;
		}
		if (getDoubleProperties().size()>0) {
			if (nLists > 0) sb.append(", ");
			sb.append("doubles:" +getDoubleProperties().size());
			nLists++;
		}
		if (getStringProperties().size()>0) {
			if (nLists > 0) sb.append(", ");
			sb.append("strings:" +getStringProperties().size());
			nLists++;
		}
		if (getDateProperties().size()>0) {
			if (nLists > 0) sb.append(", ");
			sb.append("dates:" +getDateProperties().size());
			nLists++;
		}
		if (nLists == 0)
			return "";
		sb.append("}");
		return sb.toString();
	}

	@Override
	public Class<Properties> getItemClass() {
		return Properties.class;
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
