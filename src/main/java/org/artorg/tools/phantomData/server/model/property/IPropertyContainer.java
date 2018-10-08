package org.artorg.tools.phantomData.server.model.property;

import java.util.ArrayList;
import java.util.List;

public interface IPropertyContainer {
	
	List<BooleanProperty> getBooleanProperties();

	List<DateProperty> getDateProperties();
	
	List<StringProperty> getStringProperties();

	List<IntegerProperty> getIntegerProperties();
	
	List<DoubleProperty> getDoubleProperties();
	
	void setBooleanProperties(List<BooleanProperty> properties);

	void setDateProperties(List<DateProperty> properties);
	
	void setStringProperties(List<StringProperty> properties);

	void setIntegerProperties(List<IntegerProperty> properties);
	
	void setDoubleProperties(List<DoubleProperty> properties);
	
	default boolean removeProperty(Property<?,?> property) {
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
	
	default List<Property<?,?>> getAllProperties() {
		List<Property<?,?>> results = new ArrayList<Property<?,?>>();
		results.addAll(getBooleanProperties());
		results.addAll(getDateProperties());
		results.addAll(getStringProperties());
		results.addAll(getIntegerProperties());
		results.addAll(getDoubleProperties());
		
		return results;
	}
	
	default boolean addProperty(BooleanProperty property) {
		return getBooleanProperties().add(((BooleanProperty)property));
	}
	
	default boolean addProperty(DateProperty property) {
		return getDateProperties().add(((DateProperty)property));
	}
	
	default boolean addProperty(StringProperty property) {
		return getStringProperties().add(((StringProperty)property));
	}
	
	default boolean addProperty(IntegerProperty property) {
		return getIntegerProperties().add(((IntegerProperty)property));
	}
	
	default boolean addProperty(DoubleProperty property) {
		return getDoubleProperties().add(((DoubleProperty)property));
	}

}
