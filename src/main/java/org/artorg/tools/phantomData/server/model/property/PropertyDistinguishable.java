package org.artorg.tools.phantomData.server.model.property;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface PropertyDistinguishable {
	
	Collection<BooleanProperty> getBooleanProperties();
	
	Collection<DateProperty> getDateProperties();
	
	Collection<StringProperty> getStringProperties();
	
	Collection<IntegerProperty> getIntegerProperties();

	Collection<DoubleProperty> getDoubleProperties();

	default List<Object> getAllProperties() {
		List<Object> properties = new ArrayList<Object>();
		properties.addAll(getBooleanProperties());
		properties.addAll(getDateProperties());
		properties.addAll(getStringProperties());
		properties.addAll(getIntegerProperties());
		properties.addAll(getDoubleProperties());
		
		return properties;
	}
	
	default <T> boolean addProperty(T property) {
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

	default <T> boolean removeProperty(T property) {
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
	
	default String[] getAllPropertiesAsString() {
		String[] results = new String[5];
		results[0] = getBooleanProperties().toString();
		results[1] = getDateProperties().toString();
		results[2] = getStringProperties().toString();
		results[3] = getIntegerProperties().toString();
		results[4] = getDoubleProperties().toString();
		
		return results;
	}

}
