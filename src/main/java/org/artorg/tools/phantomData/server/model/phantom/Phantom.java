package org.artorg.tools.phantomData.server.model.phantom;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.artorg.tools.phantomData.server.model.DbFile;
import org.artorg.tools.phantomData.server.model.property.Properties;
import org.artorg.tools.phantomData.server.model.specification.AbstractBaseEntity;
import org.artorg.tools.phantomData.server.model.specification.IProperties;
import org.artorg.tools.phantomData.server.specification.DbPersistentUUID;

@Entity
@Table(name = "PHANTOMS")
public class Phantom extends AbstractBaseEntity<Phantom> implements Comparable<Phantom>, Serializable, DbPersistentUUID<Phantom>, IProperties {
	private static final long serialVersionUID = -8429092809434766392L;
	
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
	private List<DbFile> files = new ArrayList<DbFile>();
	
	public Phantom() {}
	
	public Phantom(Phantomina phantomina, int number) {
		this.phantomina = phantomina;
		this.number = number;
		updateProductId();
	}
	
	@Override
	public String toName() {
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
	public Class<Phantom> getItemClass() {
		return Phantom.class;
	}
	
	@Override
	public String toString() {
		return String.format(
			"Phantom [productId=%s, phantomina=%s, number=%s, files=%s, properties=%s, %s]",
			productId, phantomina, number, files, properties, super.toString());
	}
	
	@Override
	public int compareTo(Phantom that) {
		if (that == null) return -1;
		int result;
		result = comparePid(getProductId(), that.getProductId());
		if (result != 0) return result;
		result = getProperties().compareTo(that.getProperties());
		if (result != 0) return result;
		return super.compareTo(that);
	}
	
	public int comparePid(String pid1, String pid2) {
		String[] splits1 = pid1.split("-");
		String[] splits2 = pid2.split("-");
		int n = Math.min(splits1.length, splits2.length);
		int result;
		for (int i = 0; i < n - 1; i++) {
			result = splits1[i].compareTo(splits2[i]);
			if (result != 0)
				return result;
		}
		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((files == null) ? 0 : files.hashCode());
		result = prime * result + number;
		result = prime * result + ((phantomina == null) ? 0 : phantomina.hashCode());
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
		result = prime * result + ((properties == null) ? 0 : properties.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!super.equals(obj)) return false;
		if (!(obj instanceof Phantom)) return false;
		Phantom other = (Phantom) obj;
		if (files == null) {
			if (other.files != null) return false;
		} else if (!files.equals(other.files)) return false;
		if (number != other.number) return false;
		if (phantomina == null) {
			if (other.phantomina != null) return false;
		} else if (!phantomina.equals(other.phantomina)) return false;
		if (productId == null) {
			if (other.productId != null) return false;
		} else if (!productId.equals(other.productId)) return false;
		if (properties == null) {
			if (other.properties != null) return false;
		} else if (!properties.equals(other.properties)) return false;
		return true;
	}
	
	// Getters & Setters
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
		updateProductId();
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
	
	public List<DbFile> getFiles() {
		return files;
	}

	public void setFiles(List<DbFile> files) {
		this.files = files;
	}	
	
}
