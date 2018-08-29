package org.artorg.tools.phantomData.server.model.property;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class PropertyContainer implements PropertyDistinguishable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Integer id;
	
	@ManyToMany
	@JoinTable(name = "PROPERTY_CONTAINER_BOOLEAN_PROPERTIES",
			joinColumns=@JoinColumn(name = "PROPERTY_CONTAINER_ID"),
			inverseJoinColumns=@JoinColumn(name="BOOLEAN_PROPERTIES_ID"))
	private List<BooleanProperty> booleanProperties = new ArrayList<BooleanProperty>();
	
	@ManyToMany
	@JoinTable(name = "PROPERTY_CONTAINER_DATE_PROPERTIES",
			joinColumns=@JoinColumn(name = "PROPERTY_CONTAINER_ID"),
			inverseJoinColumns=@JoinColumn(name="DATE_PROPERTIES_ID"))
	private List<DateProperty> dateProperties = new ArrayList<DateProperty>();
	
	@ManyToMany
	@JoinTable(name = "PROPERTY_CONTAINER_STRING_PROPERTIES",
			joinColumns=@JoinColumn(name = "PROPERTY_CONTAINER_ID"),
			inverseJoinColumns=@JoinColumn(name="STRING_PROPERTIES_ID"))
	private List<StringProperty> stringProperties = new ArrayList<StringProperty>();
	
	@ManyToMany
	@JoinTable(name = "PROPERTY_CONTAINER_INTEGER_PROPERTIES",
			joinColumns=@JoinColumn(name = "PROPERTY_CONTAINER_ID"),
			inverseJoinColumns=@JoinColumn(name="INTEGER_PROPERTIES_ID"))
	private List<IntegerProperty> integerProperties = new ArrayList<IntegerProperty>();
	
	@ManyToMany
	@JoinTable(name = "PROPERTY_CONTAINER_DOUBLE_PROPERTIES",
			joinColumns=@JoinColumn(name = "PROPERTY_CONTAINER_ID"),
			inverseJoinColumns=@JoinColumn(name="DOUBLE_PROPERTIES_ID"))
	private List<DoubleProperty> doubleProperties = new ArrayList<DoubleProperty>();
	
	public PropertyContainer() {}

	public <T> void addProperties(List<Property<T>> properties) {
		for (Property<T> property: properties)
			addProperty(property);
	}
	
	public <T> boolean addProperty(Property<T> property) {
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

	public <T> boolean removeProperty(Property<T> property) {
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
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public List<BooleanProperty> getBooleanProperties() {
		return booleanProperties;
	}

	public void setBooleanProperties(List<BooleanProperty> booleanProperties) {
		this.booleanProperties = booleanProperties;
	}

	public List<DateProperty> getDateProperties() {
		return dateProperties;
	}

	public void setDateProperties(List<DateProperty> dateProperties) {
		this.dateProperties = dateProperties;
	}
	
	public List<StringProperty> getStringProperties() {
		return stringProperties;
	}

	public void setStringProperties(List<StringProperty> stringProperties) {
		this.stringProperties = stringProperties;
	}

	public List<IntegerProperty> getIntegerProperties() {
		return integerProperties;
	}

	public void setIntegerProperties(List<IntegerProperty> integerProperties) {
		this.integerProperties = integerProperties;
	}

	public List<DoubleProperty> getDoubleProperties() {
		return doubleProperties;
	}

	public void setDoubleProperties(List<DoubleProperty> doubleProperties) {
		this.doubleProperties = doubleProperties;
	}

}
