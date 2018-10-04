package org.artorg.tools.phantomData.server.model.property;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@MappedSuperclass
@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.PROPERTY, property="@class")
public abstract class PropertyContainer implements Serializable {
	private static final long serialVersionUID = -7904409478126499598L;

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

//	public <T extends Property<T,U>, U extends Comparable<U>> void addProperties(Collection<T> properties) {
//		for (T property: properties)
//			addProperty(property);
//	}
	
	public boolean addProperty(BooleanProperty property) {
		return getBooleanProperties().add(((BooleanProperty)property));
	}
	
	public boolean addProperty(DateProperty property) {
		return getDateProperties().add(((DateProperty)property));
	}
	
	public boolean addProperty(StringProperty property) {
		return getStringProperties().add(((StringProperty)property));
	}
	
	public boolean addProperty(IntegerProperty property) {
		return getIntegerProperties().add(((IntegerProperty)property));
	}
	
	public boolean addProperty(DoubleProperty property) {
		return getDoubleProperties().add(((DoubleProperty)property));
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
	
	public List<Property<?,?>> getAllProperties() {
		List<Property<?,?>> results = new ArrayList<Property<?,?>>();
		results.addAll(getBooleanProperties());
		results.addAll(getDateProperties());
		results.addAll(getStringProperties());
		results.addAll(getIntegerProperties());
		results.addAll(getDoubleProperties());
		
		return results;
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