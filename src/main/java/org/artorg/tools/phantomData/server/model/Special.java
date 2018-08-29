package org.artorg.tools.phantomData.server.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.artorg.tools.phantomData.server.model.property.BooleanProperty;
import org.artorg.tools.phantomData.server.model.property.DateProperty;
import org.artorg.tools.phantomData.server.model.property.DoubleProperty;
import org.artorg.tools.phantomData.server.model.property.IntegerProperty;
import org.artorg.tools.phantomData.server.model.property.PropertyContainer;
import org.artorg.tools.phantomData.server.model.property.PropertyDistinguishable;
import org.artorg.tools.phantomData.server.model.property.StringProperty;
import org.artorg.tools.phantomData.server.specification.DatabasePersistent;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "SPECIALS")
public class Special implements Comparable<Special>, Serializable,
		DatabasePersistent<Integer> {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	protected Integer id;
	
	@Column(name = "SHORTCUT", unique=true, nullable = false)
	private String shortcut;
	
//	@ManyToMany
//	@JoinTable(name = "SPECIALS_BOOLEAN_PROPERTIES",
//			joinColumns=@JoinColumn(name = "SPECIALS_ID"),
//			inverseJoinColumns=@JoinColumn(name="BOOLEAN_PROPERTIES_ID"))
//	private Collection<BooleanProperty> booleanProperties = new ArrayList<BooleanProperty>();
//	
//	@ManyToMany
//	@JoinTable(name = "SPECIALS_DATE_PROPERTIES",
//			joinColumns=@JoinColumn(name = "SPECIALS_ID"),
//			inverseJoinColumns=@JoinColumn(name="DATE_PROPERTIES_ID"))
//	private Collection<DateProperty> dateProperties = new ArrayList<DateProperty>();
//	
//	@ManyToMany
//	@JoinTable(name = "SPECIALS_STRING_PROPERTIES",
//			joinColumns=@JoinColumn(name = "SPECIALS_ID"),
//			inverseJoinColumns=@JoinColumn(name="STRING_PROPERTIES_ID"))
//	private Collection<StringProperty> stringProperties = new ArrayList<StringProperty>();
//	
//	@ManyToMany
//	@JoinTable(name = "SPECIALS_INTEGER_PROPERTIES",
//			joinColumns=@JoinColumn(name = "SPECIALS_ID"),
//			inverseJoinColumns=@JoinColumn(name="INTEGER_PROPERTIES_ID"))
//	private Collection<IntegerProperty> integerProperties = new ArrayList<IntegerProperty>();
//	
//	@ManyToMany
//	@JoinTable(name = "SPECIALS_DOUBLE_PROPERTIES",
//			joinColumns=@JoinColumn(name = "SPECIALS_ID"),
//			inverseJoinColumns=@JoinColumn(name="DOUBLE_PROPERTIES_ID"))
//	private Collection<DoubleProperty> doubleProperties = new ArrayList<DoubleProperty>();
	
	@OneToOne (cascade = CascadeType.MERGE)
	private PropertyContainer propertyContainer;
	
//	@Autowired
//	private EntityManager entityManager;

	public Special() {}
	
	public Special(String shortcut, PropertyContainer propertyContainer) {
		this.shortcut = shortcut;
		
		this.propertyContainer = propertyContainer;
//		Session session = entityManager.unwrap(Session.class);
//		session.save(this.propertyContainer);
//		this.propertyContainer = new PropertyContainer();
//		this.propertyContainer.setBooleanProperties(booleanProperties);
//		this.booleanProperties = booleanProperties;
	}
	
	@Override
	public int compareTo(Special that) {
		return getId().compareTo(that.getId());
	}
	
	@Override
	public String toString() {
		return String.format("[id: %d, shortcut: %s, properties: %s]", 
				getId(), getShortcut()
				,
				""
//				propertyContainer.getAllProperties().stream()
//					.map(p -> p.toString())
//					.collect(Collectors.joining(", ", "[", "]"))
//				getBooleanProperties().stream()
//					.map(a -> a.toString())
//					.collect(Collectors.joining(", ", "[", "]"))
					);
	}
	
	@Override
	public Integer stringToID(String id) {
		return Integer.valueOf(id);
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getShortcut() {
		return shortcut;
	}

	public void setShortcut(String shortcut) {
		this.shortcut = shortcut;
	}
	
	public PropertyContainer getPropertyContainer() {
		return propertyContainer;
	}

	public void setPropertyContainer(PropertyContainer propertyContainer) {
		this.propertyContainer = propertyContainer;
	}
	
//	public Collection<BooleanProperty> getBooleanProperties() {
//		return booleanProperties;
//	}
//
//	public void setBooleanProperties(List<BooleanProperty> booleanProperties) {
//		this.booleanProperties = booleanProperties;
//	}
//
//	public Collection<DateProperty> getDateProperties() {
//		return dateProperties;
//	}
//
//	public void setDateProperties(List<DateProperty> dateProperties) {
//		this.dateProperties = dateProperties;
//	}
//	
//	public Collection<StringProperty> getStringProperties() {
//		return stringProperties;
//	}
//
//	public void setStringProperties(List<StringProperty> stringProperties) {
//		this.stringProperties = stringProperties;
//	}
//
//	public Collection<IntegerProperty> getIntegerProperties() {
//		return integerProperties;
//	}
//
//	public void setIntegerProperties(List<IntegerProperty> integerProperties) {
//		this.integerProperties = integerProperties;
//	}
//
//	public Collection<DoubleProperty> getDoubleProperties() {
//		return doubleProperties;
//	}
//
//	public void setDoubleProperties(List<DoubleProperty> doubleProperties) {
//		this.doubleProperties = doubleProperties;
//	}
	
}
