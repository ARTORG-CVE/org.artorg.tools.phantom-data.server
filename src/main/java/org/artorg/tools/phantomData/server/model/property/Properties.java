package org.artorg.tools.phantomData.server.model.property;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.artorg.tools.phantomData.server.model.specification.AbstractBaseEntity;
import org.artorg.tools.phantomData.server.specification.AbstractEntity;
import org.artorg.tools.phantomData.server.specification.DbPersistentUUID;

@Entity
@AbstractEntity
@Table(name = "PROPERTIES")
public class Properties extends AbstractBaseEntity<Properties>
	implements Comparable<Properties>, Serializable, DbPersistentUUID<Properties> {
	private static final long serialVersionUID = 9191592162274332907L;
	
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
	public String toName() {
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
	
	@Override
	public String toString() {
		return String.format(
			"Properties [booleanProperties=%s, integerProperties=%s, doubleProperties=%s, stringProperties=%s, dateProperties=%s, %s]",
			booleanProperties, integerProperties, doubleProperties, stringProperties,
			dateProperties, super.toString());
	}

	@Override
	public int compareTo(Properties that) {
		int result;
		result = compare(getBooleanProperties(), that.getBooleanProperties());
		if (result != 0) return result;
		result = compare(getIntegerProperties(), that.getIntegerProperties());
		if (result != 0) return result;
		result = compare(getDoubleProperties(), that.getDoubleProperties());
		if (result != 0) return result;
		result = compare(getStringProperties(), that.getStringProperties());
		if (result != 0) return result;
		result = compare(getDateProperties(), that.getDateProperties());
		if (result != 0) return result;
		return super.compareTo(that);
	}
	
	private <E extends Comparable<E>> int compare(List<E> list1, List<E> list2) {
		int result;
		result = Integer.compare(list2.size(),list1.size());
		if (result != 0) return result;
		list1 = list1.stream().sorted().collect(Collectors.toList());
		list2 = list2.stream().sorted().collect(Collectors.toList());
		for (int i=0; i<list1.size();i++) {
			if (list1.get(i) == null)
				return 1;
			if (list2.get(i) == null)
				return -1;
			result = list1.get(i).compareTo(list2.get(i));
			if (result != 0) return result;
		}
		return 0;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
			+ ((booleanProperties == null) ? 0 : booleanProperties.hashCode());
		result =
			prime * result + ((dateProperties == null) ? 0 : dateProperties.hashCode());
		result = prime * result
			+ ((doubleProperties == null) ? 0 : doubleProperties.hashCode());
		result = prime * result
			+ ((integerProperties == null) ? 0 : integerProperties.hashCode());
		result = prime * result
			+ ((stringProperties == null) ? 0 : stringProperties.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!super.equals(obj)) return false;
		if (!(obj instanceof Properties)) return false;
		Properties other = (Properties) obj;
		if (booleanProperties == null) {
			if (other.booleanProperties != null) return false;
		} else if (!booleanProperties.equals(other.booleanProperties)) return false;
		if (dateProperties == null) {
			if (other.dateProperties != null) return false;
		} else if (!dateProperties.equals(other.dateProperties)) return false;
		if (doubleProperties == null) {
			if (other.doubleProperties != null) return false;
		} else if (!doubleProperties.equals(other.doubleProperties)) return false;
		if (integerProperties == null) {
			if (other.integerProperties != null) return false;
		} else if (!integerProperties.equals(other.integerProperties)) return false;
		if (stringProperties == null) {
			if (other.stringProperties != null) return false;
		} else if (!stringProperties.equals(other.stringProperties)) return false;
		return true;
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
