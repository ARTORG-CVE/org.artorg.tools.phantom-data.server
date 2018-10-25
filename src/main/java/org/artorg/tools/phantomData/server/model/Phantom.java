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

import org.artorg.tools.phantomData.server.model.property.IProperties;
import org.artorg.tools.phantomData.server.model.specification.AbstractBaseEntity;
import org.artorg.tools.phantomData.server.specification.DbPersistentUUID;

@Entity
@Table(name = "PHANTOMS")
public class Phantom extends AbstractBaseEntity<Phantom> implements Comparable<Phantom>, Serializable, DbPersistentUUID<Phantom>, IProperties {
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
	
	@Column(name = "PROPERTIES")
	private Properties properties;

	

	@ManyToMany
	@JoinTable(name = "PHANTOM_FILES",
			joinColumns=@JoinColumn(name = "PHANTOM_ID"),
			inverseJoinColumns=@JoinColumn(name="FILE_ID"))
	private List<PhantomFile> files = new ArrayList<PhantomFile>();
	
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
	
	@Override
	public Class<Phantom> getItemClass() {
		return Phantom.class;
	}
	
	// Getters & Setters
	@Override
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
	
	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	
	public List<PhantomFile> getFiles() {
		return files;
	}

	public void setFiles(List<PhantomFile> files) {
		this.files = files;
	}	
	
}
