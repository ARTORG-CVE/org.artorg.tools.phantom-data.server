package org.artorg.tools.phantomData.server.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
import org.artorg.tools.phantomData.server.model.property.IPropertyContainer;
import org.artorg.tools.phantomData.server.model.property.IntegerProperty;
import org.artorg.tools.phantomData.server.model.property.StringProperty;
import org.artorg.tools.phantomData.server.model.specification.AbstractBaseEntity;
import org.artorg.tools.phantomData.server.specification.DbPersistentUUID;

@Entity
@Table(name = "SPECIALS")
public class Special extends AbstractBaseEntity<Special> implements DbPersistentUUID<Special>, Serializable, IPropertyContainer<Special> {
	private static final long serialVersionUID = 4838372606658297575L;

	@Id
	@Column(name = "ID", nullable = false)
	private UUID id = UUID.randomUUID();
	
	@Column(name = "SHORTCUT", unique=true, nullable = false)
	private String shortcut;
	
	@ManyToMany
	@JoinTable(name = "SPECIALS_BOOLEAN_PROPERTIES",
			joinColumns=@JoinColumn(name = "SPECIAL_ID"),
			inverseJoinColumns=@JoinColumn(name="BOOLEAN_PROPERTY_ID"))
	private List<BooleanProperty> booleanProperties = new ArrayList<BooleanProperty>();
	
	@ManyToMany
	@JoinTable(name = "SPECIALS_DATE_PROPERTIES",
			joinColumns=@JoinColumn(name = "SPECIAL_ID"),
			inverseJoinColumns=@JoinColumn(name="DATE_PROPERTY_ID"))
	private List<DateProperty> dateProperties = new ArrayList<DateProperty>();
	
	@ManyToMany
	@JoinTable(name = "SPECIALS_STRING_PROPERTIES",
			joinColumns=@JoinColumn(name = "SPECIAL_ID"),
			inverseJoinColumns=@JoinColumn(name="STRING_PROPERTY_ID"))
	private List<StringProperty> stringProperties = new ArrayList<StringProperty>();
	
	@ManyToMany
	@JoinTable(name = "SPECIALS_INTEGER_PROPERTIES",
			joinColumns=@JoinColumn(name = "SPECIAL_ID"),
			inverseJoinColumns=@JoinColumn(name="INTEGER_PROPERTY_ID"))
	private List<IntegerProperty> integerProperties = new ArrayList<IntegerProperty>();
	
	@ManyToMany
	@JoinTable(name = "SPECIALS_DOUBLE_PROPERTIES",
			joinColumns=@JoinColumn(name = "SPECIAL_ID"),
			inverseJoinColumns=@JoinColumn(name="DOUBLE_PROPERTY_ID"))
	private List<DoubleProperty> doubleProperties = new ArrayList<DoubleProperty>();
	
	public Special() {}
	
	public Special(String shortcut) {
		this.shortcut = shortcut;
	}
	
	@Override
	public String createName() {
		return shortcut;
	}
	
	@Override
	public int compareTo(Special that) {
		return getId().compareTo(that.getId());
	}
	
	@Override
	public String toString() {
		return String.format("shortcut: %s, properties: [%s]", 
			getShortcut()
				, getAllProperties().stream().map(p -> p.toString()).collect(Collectors.joining(", ")));
	}
	
	@Override
	public UUID getId() {
		return id;
	}
	
	@Override
	public void setId(UUID id) {
		this.id = id;
	}
	
	public String getShortcut() {
		return shortcut;
	}

	public void setShortcut(String shortcut) {
		this.shortcut = shortcut;
	}

	@Override
	public Class<Special> getItemClass() {
		return Special.class;
	}

	@Override
	public List<BooleanProperty> getBooleanProperties() {
		return booleanProperties;
	}

	@Override
	public List<DateProperty> getDateProperties() {
		return dateProperties;
	}

	@Override
	public List<StringProperty> getStringProperties() {
		return stringProperties;
	}

	@Override
	public List<IntegerProperty> getIntegerProperties() {
		return integerProperties;
	}

	@Override
	public List<DoubleProperty> getDoubleProperties() {
		return doubleProperties;
	}

	@Override
	public void setBooleanProperties(List<BooleanProperty> properties) {
		this.booleanProperties = properties;
	}

	@Override
	public void setDateProperties(List<DateProperty> properties) {
		this.dateProperties = properties;
	}

	@Override
	public void setStringProperties(List<StringProperty> properties) {
		this.stringProperties = properties;
	}

	@Override
	public void setIntegerProperties(List<IntegerProperty> properties) {
		this.integerProperties = properties;
	}

	@Override
	public void setDoubleProperties(List<DoubleProperty> properties) {
		 this.doubleProperties = properties;
	}

	
	
}
