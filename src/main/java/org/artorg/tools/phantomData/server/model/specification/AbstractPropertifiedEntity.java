package org.artorg.tools.phantomData.server.model.specification;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;

import org.artorg.tools.phantomData.server.model.base.property.BooleanProperty;
import org.artorg.tools.phantomData.server.model.base.property.DateProperty;
import org.artorg.tools.phantomData.server.model.base.property.DoubleProperty;
import org.artorg.tools.phantomData.server.model.base.property.IntegerProperty;
import org.artorg.tools.phantomData.server.model.base.property.StringProperty;
import org.artorg.tools.phantomData.server.specification.DbPersistentUUID;
import org.artorg.tools.phantomData.server.util.EntityUtils;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class AbstractPropertifiedEntity<ITEM extends AbstractPropertifiedEntity<ITEM>>
	extends AbstractPersonifiedEntity<ITEM>
	implements DbPersistentUUID<ITEM>, Serializable, NameGeneratable {
	private static final long serialVersionUID = -2814334933013431607L;

	

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

	public AbstractPropertifiedEntity() {
		
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
			dateProperties, super.toString());
	}

	@Override
	public int compareTo(ITEM item) {
		if (item == null) return -1;
		AbstractPropertifiedEntity<?> that = (AbstractPropertifiedEntity<?>) item;
		int result;
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
		return super.compareToId(that);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!super.equals(obj)) return false;
		if (!(obj instanceof AbstractPropertifiedEntity)) return false;
		AbstractPropertifiedEntity<?> other = (AbstractPropertifiedEntity<?>) obj;
		
		if (!EntityUtils.equals(booleanProperties, other.booleanProperties)) return false;
		if (!EntityUtils.equals(integerProperties, other.integerProperties)) return false;
		if (!EntityUtils.equals(doubleProperties, other.doubleProperties)) return false;
		if (!EntityUtils.equals(stringProperties, other.stringProperties)) return false;
		return EntityUtils.equals(dateProperties, other.dateProperties);
	}

	// Getters & Setters
	

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