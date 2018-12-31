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
import javax.persistence.Table;

import org.artorg.tools.phantomData.server.model.AbstractPropertifiedEntity;
import org.artorg.tools.phantomData.server.model.DbPersistentUUID;
import org.artorg.tools.phantomData.server.models.base.DbFile;
import org.artorg.tools.phantomData.server.models.base.Note;
import org.artorg.tools.phantomData.server.models.measurement.Simulation;
import org.artorg.tools.phantomData.server.util.EntityUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "SIMULATION_PHANTOMS")
public class SimulationPhantom extends AbstractPropertifiedEntity<SimulationPhantom> implements
		Comparable<SimulationPhantom>, Serializable, DbPersistentUUID<SimulationPhantom> {
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

	@JsonIgnoreProperties({"experimentalSetup","project","phantoms", "simulationPhantoms"})
	@ManyToMany
	private List<Simulation> simulations = new ArrayList<>();

	@ManyToMany
	private List<DbFile> files = new ArrayList<>();

	@ManyToMany
	private List<Note> notes = new ArrayList<>();

	public SimulationPhantom() {}

	public SimulationPhantom(Phantomina phantomina, int number, float thickness) {
		this.phantomina = phantomina;
		this.number = number;
		this.thickness = thickness;
		updateProductId();
	}

	@Override
	public String toName() {
		List<String> list = new ArrayList<>();
		if (!files.isEmpty()) list.add("files: " + files.size());
		if (!simulations.isEmpty()) list.add("meas.: " + simulations.size());
		if (!notes.isEmpty()) list.add("notes: " + notes.size());
		String suffix = "";
		if (!list.isEmpty()) suffix = list.stream().collect(Collectors.joining(", ", " (", ")"));

		return getProductId() + suffix;
	}

	public void updateProductId() {
		setProductId(createProductId(Phantomina.createProductId(getPhantomina()), getNumber()));
	}

	public static String createProductId(String phantominaProductId, int number) {
		return String.format("%s-%s", phantominaProductId, number);
	}

	@Override
	public Class<SimulationPhantom> getItemClass() {
		return SimulationPhantom.class;
	}

	@Override
	public String toString() {
		return String.format(
				"SimulationPhantom [productId=%s, phantomina=%s, number=%s, thickness=%s, measurements=%s, files=%s, notes=%s, %s]",
				productId, phantomina, number, thickness, simulations, files, notes,
				super.toString());
	}

	@Override
	public int compareTo(SimulationPhantom that) {
		if (that == null) return -1;
		int result;
		result = Phantomina.comparePid(productId, that.productId);
		if (result != 0) return result;
		result = Integer.compare(number, that.number);
		if (result != 0) return result;
		result = Float.compare(thickness, that.thickness);
		if (result != 0) return result;
		result = EntityUtils.compare(simulations, that.simulations);
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
		if (!(obj instanceof SimulationPhantom)) return false;
		SimulationPhantom other = (SimulationPhantom) obj;
		if (number != other.number) return false;
		if (!EntityUtils.equals(productId, other.productId)) return false;
		if (!EntityUtils.equals(phantomina, other.phantomina)) return false;
		if (thickness != other.thickness) return false;
		if (!EntityUtils.equals(simulations, other.simulations)) return false;
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

	public List<Simulation> getSimulations() {
		return simulations;
	}

	public void setSimulations(List<Simulation> simulations) {
		this.simulations = simulations;
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

}
