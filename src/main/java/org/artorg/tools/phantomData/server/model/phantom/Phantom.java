package org.artorg.tools.phantomData.server.model.phantom;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.artorg.tools.phantomData.server.model.specification.AbstractBaseEntity;
import org.artorg.tools.phantomData.server.specification.DbPersistentUUID;

@Entity
@Table(name = "PHANTOMS")
public class Phantom extends AbstractBaseEntity<Phantom> implements Comparable<Phantom>, Serializable, DbPersistentUUID<Phantom> {
	private static final long serialVersionUID = -8429092809434766392L;
	
	@Column(name = "PRODUCT_ID", nullable = false)
	private String productId;
	
	@OneToOne
	private Phantomina phantomina;

	@Column(name = "NUMBER", nullable = false)
	private int number;
	
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
		setProductId(createProductId(getPhantomina(), getNumber()));
	}
	
	public static String createProductId(Phantomina phantomina, int number) {
		return String.format("%s-%s", 
			phantomina.getProductId(), number);
	}
	
	@Override
	public Class<Phantom> getItemClass() {
		return Phantom.class;
	}
	
	@Override
	public String toString() {
		return String.format(
			"Phantom [productId=%s, phantomina=%s, number=%s, files=%s, properties=%s, %s]",
			productId, phantomina, number, super.toString());
	}
	
	@Override
	public int compareTo(Phantom that) {
		if (that == null) return -1;
		int result;
		result = Phantomina.comparePid(getProductId(), that.getProductId());
		if (result != 0) return result;
		result = Integer.compare(getNumber(), that.getNumber());
		if (result != 0) return result;
		return super.compareTo(that);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!super.equals(obj)) return false;
		if (!(obj instanceof Phantom)) return false;
		Phantom other = (Phantom) obj;
		if (number != other.number) return false;
		if (phantomina == null) {
			if (other.phantomina != null) return false;
		} else if (!phantomina.equals(other.phantomina)) return false;
		if (productId == null) {
			if (other.productId != null) return false;
		} else if (!productId.equals(other.productId)) return false;
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
	
}
