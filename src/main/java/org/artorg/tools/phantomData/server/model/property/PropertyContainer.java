package org.artorg.tools.phantomData.server.model.property;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.artorg.tools.phantomData.server.specification.DatabasePersistent;

@Entity
@Table(name = "PROPERTY_CONTAINERS")
public class PropertyContainer implements Serializable, DatabasePersistent {
	private static final long serialVersionUID = 7233338141885924205L;

	@Id
	@Column(name = "ID", nullable = false)
	private UUID id = UUID.randomUUID();
	
	@ManyToMany
	@JoinTable(name = "PROPERTY_CONTAINER_BOOLEAN_PROPERTIES",
			joinColumns=@JoinColumn(name = "PROPERTY_CONTAINER_ID"),
			inverseJoinColumns=@JoinColumn(name="BOOLEAN_PROPERTIES_ID"))
	private Collection<BooleanProperty> booleanProperties = new ArrayList<BooleanProperty>();
	
	@ManyToMany
	@JoinTable(name = "PROPERTY_CONTAINER_DATE_PROPERTIES",
			joinColumns=@JoinColumn(name = "PROPERTY_CONTAINER_ID"),
			inverseJoinColumns=@JoinColumn(name="DATE_PROPERTIES_ID"))
	private Collection<DateProperty> dateProperties = new ArrayList<DateProperty>();
	
	@ManyToMany
	@JoinTable(name = "PROPERTY_CONTAINER_STRING_PROPERTIES",
			joinColumns=@JoinColumn(name = "PROPERTY_CONTAINER_ID"),
			inverseJoinColumns=@JoinColumn(name="STRING_PROPERTIES_ID"))
	private Collection<StringProperty> stringProperties = new ArrayList<StringProperty>();
	
	@ManyToMany
	@JoinTable(name = "PROPERTY_CONTAINER_INTEGER_PROPERTIES",
			joinColumns=@JoinColumn(name = "PROPERTY_CONTAINER_ID"),
			inverseJoinColumns=@JoinColumn(name="INTEGER_PROPERTIES_ID"))
	private Collection<IntegerProperty> integerProperties = new ArrayList<IntegerProperty>();
	
	@ManyToMany
	@JoinTable(name = "PROPERTY_CONTAINER_DOUBLE_PROPERTIES",
			joinColumns=@JoinColumn(name = "PROPERTY_CONTAINER_ID"),
			inverseJoinColumns=@JoinColumn(name="DOUBLE_PROPERTIES_ID"))
	private Collection<DoubleProperty> doubleProperties = new ArrayList<DoubleProperty>();
	
	public PropertyContainer() {}

	public <T> void addProperties(Collection<T> properties) {
		for (T property: properties)
			addProperty(property);
	}
	
	public <T> boolean addProperty(T property) {
		if (property instanceof BooleanProperty)
			return getBooleanProperties().add(((BooleanProperty)property));
		if (property instanceof DateProperty)
			return getDateProperties().add(((DateProperty)property));
		if (property instanceof StringProperty)
			return getStringProperties().add(((StringProperty)property));
		if (property instanceof IntegerProperty)
			return getIntegerProperties().add(((IntegerProperty)property));
		if (property instanceof DoubleProperty)
			return getDoubleProperties().add(((DoubleProperty)property));
		return false;
	}

	public <T> boolean removeProperty(T property) {
		if (property instanceof BooleanProperty)
			return getBooleanProperties().remove(((BooleanProperty)property));
		if (property instanceof DateProperty)
			return getDateProperties().remove(((DateProperty)property));
		if (property instanceof StringProperty)
			return getStringProperties().remove(((StringProperty)property));
		if (property instanceof IntegerProperty)
			return getIntegerProperties().remove(((IntegerProperty)property));
		if (property instanceof DoubleProperty)
			return getDoubleProperties().remove(((DoubleProperty)property));
		return false;
	}
	
	public String[] getAllPropertiesAsString() {
		String[] results = new String[5];
		results[0] = getBooleanProperties().toString();
		results[1] = getDateProperties().toString();
		results[2] = getStringProperties().toString();
		results[3] = getIntegerProperties().toString();
		results[4] = getDoubleProperties().toString();
		
		return results;
	}
	
	@Override
	public UUID getId() {
		return id;
	}
	
	@Override
	public void setId(UUID id) {
		this.id = id;
	}
	
	public Collection<BooleanProperty> getBooleanProperties() {
		return booleanProperties;
	}

	public void setBooleanProperties(Collection<BooleanProperty> booleanProperties) {
		this.booleanProperties = booleanProperties;
	}

	public Collection<DateProperty> getDateProperties() {
		return dateProperties;
	}

	public void setDateProperties(Collection<DateProperty> dateProperties) {
		this.dateProperties = dateProperties;
	}
	
	public Collection<StringProperty> getStringProperties() {
		return stringProperties;
	}

	public void setStringProperties(Collection<StringProperty> stringProperties) {
		this.stringProperties = stringProperties;
	}

	public Collection<IntegerProperty> getIntegerProperties() {
		return integerProperties;
	}

	public void setIntegerProperties(Collection<IntegerProperty> integerProperties) {
		this.integerProperties = integerProperties;
	}

	public Collection<DoubleProperty> getDoubleProperties() {
		return doubleProperties;
	}

	public void setDoubleProperties(Collection<DoubleProperty> doubleProperties) {
		this.doubleProperties = doubleProperties;
	}

	

}
