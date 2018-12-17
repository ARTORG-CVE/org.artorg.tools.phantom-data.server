package org.artorg.tools.phantomData.server.models.phantom;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.artorg.tools.phantomData.server.model.AbstractPropertifiedEntity;
import org.artorg.tools.phantomData.server.model.DbPersistentUUID;
import org.artorg.tools.phantomData.server.models.base.DbFile;
import org.artorg.tools.phantomData.server.models.base.Note;
import org.artorg.tools.phantomData.server.models.measurement.Measurement;
import org.artorg.tools.phantomData.server.util.EntityUtils;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "PHANTOMS")
@JsonIdentityInfo(
	  generator = ObjectIdGenerators.PropertyGenerator.class, 
	  property = "id")
public class Phantom extends AbstractPropertifiedEntity<Phantom>
		implements Comparable<Phantom>, Serializable, DbPersistentUUID<Phantom> {
	private static final long serialVersionUID = -8429092809434766392L;

	@Column(name = "PRODUCT_ID", nullable = false)
	private String productId;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Phantomina phantomina;

	@Column(name = "NUMBER", nullable = false)
	private int number;

	@Column(name = "THICKNESS", nullable = false)
	private float thickness;

	@OneToOne
	@JoinColumn(nullable = false)
	@NotNull
	private Manufacturing manufacturing;

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

	public Phantom(Phantomina phantomina, int number, Manufacturing manufacturing,
			float thickness) {
		this.phantomina = phantomina;
		this.number = number;
		this.manufacturing = manufacturing;
		this.thickness = thickness;
		updateProductId();
	}

	@Override
	public String toName() {
		List<String> list = new ArrayList<>();
		if (!files.isEmpty()) 
			list.add("files: " +files.size());
		if (!measurements.isEmpty()) 
			list.add("meas.: " +measurements.size());
		if (!notes.isEmpty()) 
			list.add("notes: " +notes.size());
		String suffix = "";
		if (!list.isEmpty())
			suffix = list.stream().collect(Collectors.joining(", ", " (", ")"));
		
		return getProductId() +suffix;
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
				"Phantom [productId=%s, phantomina=%s, number=%s, thickness=%s, manufacturing=%s, measurements=%s, files=%s, notes=%s, %s]",
				productId, phantomina, number, thickness, manufacturing, measurements, files, notes,
				super.toString());
	}

	@Override
	public int compareTo(Phantom that) {
		if (that == null) return -1;
		int result;
		result = Phantomina.comparePid(productId, that.productId);
		if (result != 0) return result;
		result = Integer.compare(number, that.number);
		if (result != 0) return result;
		result = Float.compare(thickness, that.thickness);
		if (result != 0) return result;
		result = manufacturing.compareTo(that.manufacturing);
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
		if (thickness != other.thickness) return false;
		if (!EntityUtils.equals(manufacturing, other.manufacturing)) return false;
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

	public float getThickness() {
		return thickness;
	}

	public void setThickness(float thickness) {
		this.thickness = thickness;
	}

	public Manufacturing getManufacturing() {
		return manufacturing;
	}

	public void setManufacturing(Manufacturing manufacturing) {
		this.manufacturing = manufacturing;
	}

}
