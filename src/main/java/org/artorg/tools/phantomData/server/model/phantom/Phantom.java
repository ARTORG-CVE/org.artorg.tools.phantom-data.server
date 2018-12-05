package org.artorg.tools.phantomData.server.model.phantom;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.artorg.tools.phantomData.server.model.base.DbFile;
import org.artorg.tools.phantomData.server.model.base.Note;
import org.artorg.tools.phantomData.server.model.measurement.Measurement;
import org.artorg.tools.phantomData.server.model.specification.AbstractPropertifiedEntity;
import org.artorg.tools.phantomData.server.specification.DbPersistentUUID;
import org.artorg.tools.phantomData.server.util.EntityUtils;

@Entity
@Table(name = "PHANTOMS")
public class Phantom extends AbstractPropertifiedEntity<Phantom>
	implements Comparable<Phantom>, Serializable, DbPersistentUUID<Phantom> {
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

	@ManyToMany
	private List<DbFile> files;

	@ManyToMany
	private List<Note> notes;

	{
		measurements = new ArrayList<Measurement>();
		files = new ArrayList<DbFile>();
		notes = new ArrayList<Note>();
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
		return String.format("%s-%s", phantomina.getProductId(), number);
	}

	@Override
	public Class<Phantom> getItemClass() {
		return Phantom.class;
	}

	@Override
	public String toString() {
		return String.format(
			"Phantom [productId=%s, phantomina=%s, number=%s, measurements=%s, files=%s, notes=%s %s]",
			productId, phantomina, number, measurements, files, notes, super.toString());
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
		result = EntityUtils.compare(files, that.files);
		if (result != 0) return result;
		result = EntityUtils.compare(notes, that.notes);
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
		if (!EntityUtils.equals(productId, other.productId)) return false;
		if (!EntityUtils.equals(phantomina, other.phantomina)) return false;
		if (!EntityUtils.equals(measurements, other.measurements)) return false;
		if (!EntityUtils.equals(files, other.files)) return false;
		if (!EntityUtils.equals(notes, other.notes)) return false;
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

	public List<DbFile> getFiles() {
		return files;
	}

	public void setFiles(List<DbFile> files) {
		this.files = files;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

}
