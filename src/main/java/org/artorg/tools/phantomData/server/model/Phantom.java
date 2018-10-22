package org.artorg.tools.phantomData.server.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
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
@Table(name = "PHANTOMS")
public class Phantom extends AbstractBaseEntity<Phantom> implements Comparable<Phantom>, Serializable, DbPersistentUUID<Phantom>, IPropertyContainer<Phantom> {
	private static final long serialVersionUID = -8429092809434766392L;

	@Id
	@Column(name = "ID", nullable = false)
	private UUID id = UUID.randomUUID();
	
	@Column(name = "PRODUCT_ID", nullable = false)
	private String productId;
	
	@OneToOne
	private Phantomina phantomina;

	@Column(name = "NUMBER", nullable = false)
	private int number;

	@ManyToMany
	@JoinTable(name = "PHANTOM_FILES",
			joinColumns=@JoinColumn(name = "PHANTOM_ID"),
			inverseJoinColumns=@JoinColumn(name="FILE_ID"))
	private List<PhantomFile> files = new ArrayList<PhantomFile>();
	
	@ManyToMany
	@JoinTable(name = "PHANTOMS_BOOLEAN_PROPERTIES",
			joinColumns=@JoinColumn(name = "PHANTOM_ID"),
			inverseJoinColumns=@JoinColumn(name="BOOLEAN_PROPERTY_ID"))
	private List<BooleanProperty> booleanProperties = new ArrayList<BooleanProperty>();
	
	@ManyToMany
	@JoinTable(name = "PHANTOMS_DATE_PROPERTIES",
			joinColumns=@JoinColumn(name = "PHANTOM_ID"),
			inverseJoinColumns=@JoinColumn(name="DATE_PROPERTY_ID"))
	private List<DateProperty> dateProperties = new ArrayList<DateProperty>();
	
	@ManyToMany
	@JoinTable(name = "PHANTOMS_STRING_PROPERTIES",
			joinColumns=@JoinColumn(name = "PHANTOM_ID"),
			inverseJoinColumns=@JoinColumn(name="STRING_PROPERTY_ID"))
	private List<StringProperty> stringProperties = new ArrayList<StringProperty>();
	
	@ManyToMany
	@JoinTable(name = "PHANTOMS_INTEGER_PROPERTIES",
			joinColumns=@JoinColumn(name = "PHANTOM_ID"),
			inverseJoinColumns=@JoinColumn(name="INTEGER_PROPERTY_ID"))
	private List<IntegerProperty> integerProperties = new ArrayList<IntegerProperty>();
	
	@ManyToMany
	@JoinTable(name = "PHANTOMS_DOUBLE_PROPERTIES",
			joinColumns=@JoinColumn(name = "PHANTOM_ID"),
			inverseJoinColumns=@JoinColumn(name="DOUBLE_PROPERTY_ID"))
	private List<DoubleProperty> doubleProperties = new ArrayList<DoubleProperty>();
	
	public Phantom() {}
	
	public Phantom(Phantomina phantomina, int number) {
		this.phantomina = phantomina;
		this.number = number;
		updateProductId();
	}
	
	@Override
	public String createName() {
		return getProductId();
	}
	
	public void updateProductId() {
		getPhantomina().updateProductId();
		setProductId(String.format("%s-%s", 
			getPhantomina().getProductId(),
			helper(() -> ((Integer)number).toString(), "?")));
	}
	
	private String helper(Supplier<String> supplier, String orElse) {
		try {
			return supplier.get();
		} catch(Exception e) {
			return orElse;
		}
	}
	
	@Override
	public String toString() {
		return "";
//		String[] properties = this.getAllPropertiesAsString();
//		for (String property: properties)
//			sb.append(property);
//		if (!files.isEmpty())
//			sb.append(", files: " +getFiles().toString());
//		sb.append("]");

	}
	
	
	// Getters & Setters
	public UUID getId() {
		return id;
	}
	
	@Override
	public void setId(UUID id) {
		this.id = id;
	}
	
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Phantomina getPhantomina() {
		return phantomina;
	}

	public void setPhantomina(Phantomina phantomina) {
		this.phantomina = phantomina;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
		updateProductId();
	}
	
	public List<PhantomFile> getFiles() {
		return files;
	}

	public void setFiles(List<PhantomFile> files) {
		this.files = files;
	}

	@Override
	public Class<Phantom> getItemClass() {
		return Phantom.class;
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
