package org.artorg.tools.phantomData.server.model.phantom;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.artorg.tools.phantomData.server.model.measurement.Measurement;
import org.artorg.tools.phantomData.server.model.specification.AbstractBaseEntity;
import org.artorg.tools.phantomData.server.specification.DbPersistentUUID;
import org.artorg.tools.phantomData.server.util.EntityUtils;

@Entity
@Table(name = "PHANTOMS")
public class Phantom extends AbstractBaseEntity<Phantom> implements Comparable<Phantom>, Serializable, DbPersistentUUID<Phantom> {
	private static final long serialVersionUID = -8429092809434766392L;
	
	@Column(name = "PRODUCT_ID", nullable = false)
	private String productId;
	
	@OneToOne
	@JoinColumn(nullable = false)
	private Phantomina phantomina;

	@Column(name = "NUMBER", nullable = false)
	private int number;
	
	@ManyToMany
	private List<Measurement> measurements;
	
	{
		measurements = new ArrayList<Measurement>();
	}
	
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
			"Phantom [productId=%s, phantomina=%s, number=%s, measurements=%s, %s]",
			productId, phantomina, number, measurements, super.toString());
	}
	
	@Override
	public int compareTo(Phantom that) {
		if (that == null) return -1;
		int result;
		result = Phantomina.comparePid(productId, that.productId);
		if (result != 0) return result;
		result = Integer.compare(number, that.number);
		if (result != 0) return result;
		result = EntityUtils.compare(measurements, that.measurements);
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
		if (!EntityUtils.equals(productId, productId)) return false;
		if (!EntityUtils.equals(phantomina, phantomina)) return false;
		if (!EntityUtils.equals(measurements, measurements)) return false;
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

	public List<Measurement> getMeasurements() {
		return measurements;
	}

	public void setMeasurements(List<Measurement> measurements) {
		this.measurements = measurements;
	}
	
}
