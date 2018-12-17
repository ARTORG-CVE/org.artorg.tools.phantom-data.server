package org.artorg.tools.phantomData.server.models.base;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.apache.commons.io.FileUtils;
import org.artorg.tools.phantomData.server.model.AbstractPersonifiedEntity;
import org.artorg.tools.phantomData.server.model.DbPersistentUUID;
import org.artorg.tools.phantomData.server.models.measurement.ExperimentalSetup;
import org.artorg.tools.phantomData.server.models.measurement.Measurement;
import org.artorg.tools.phantomData.server.models.measurement.Project;
import org.artorg.tools.phantomData.server.models.phantom.AnnulusDiameter;
import org.artorg.tools.phantomData.server.models.phantom.FabricationType;
import org.artorg.tools.phantomData.server.models.phantom.LiteratureBase;
import org.artorg.tools.phantomData.server.models.phantom.Manufacturing;
import org.artorg.tools.phantomData.server.models.phantom.Phantom;
import org.artorg.tools.phantomData.server.models.phantom.Phantomina;
import org.artorg.tools.phantomData.server.models.phantom.Special;
import org.artorg.tools.phantomData.server.util.EntityUtils;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "FILES")
@JsonIdentityInfo(
	  generator = ObjectIdGenerators.PropertyGenerator.class, 
	  property = "id")
public class DbFile extends AbstractPersonifiedEntity<DbFile> implements DbPersistentUUID<DbFile> {
	private static final long serialVersionUID = 1575607671219807521L;
	private static String filesPath;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Column(name = "EXTENSION", nullable = false)
	private String extension;
	
	@ManyToMany
	private List<Note> notes = new ArrayList<>();
	
	@ManyToMany
	private List<FileTag> fileTags = new ArrayList<>();
	
	@ManyToMany (mappedBy="files")
	private List<Phantom> phantoms = new ArrayList<>();
	
	@ManyToMany (mappedBy="files")
	private List<Phantomina> phantominas = new ArrayList<>();
	
	@ManyToMany (mappedBy="files")
	private List<ExperimentalSetup> experimentalSetups = new ArrayList<>();
	
	@ManyToMany (mappedBy="files")
	private List<Measurement> measurements = new ArrayList<>();
	
	@ManyToMany (mappedBy="files")
	private List<Project> projects = new ArrayList<>();

	@ManyToMany (mappedBy="files")
	private List<AnnulusDiameter> annulusDiameter = new ArrayList<>();

	@ManyToMany (mappedBy="files")
	private List<FabricationType> fabricationTypes = new ArrayList<>();
	
	@ManyToMany (mappedBy="files")
	private List<LiteratureBase> literatureBases = new ArrayList<>();
	
	@ManyToMany (mappedBy="files")
	private List<Manufacturing> manufacturing = new ArrayList<>();
	
	@ManyToMany (mappedBy="files")
	private List<Special> specials = new ArrayList<>();

	public DbFile() {}
	
	public DbFile(File srcFile, String name, String extension) {
		this.name = name;
		extension = extension.toLowerCase();
		this.extension = extension;
		
		File destFile = new File(filesPath +"\\" +getId() +"." +extension);
		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public File getFile() {
		return new File(filesPath +"\\" +getId() +"." +extension);
	}
	
	@Override
	public String toName() {
		return name;
	}
	
	@Override
	public Class<DbFile> getItemClass() {
		return DbFile.class;
	}
	
	@Override
	public String toString() {
		return String.format("DbFile [name=%s, extension=%s, fileTags %s]", name,
			extension, fileTags, super.toString());
	}

	@Override
	public int compareTo(DbFile that) {
		if (that == null) return -1;
		int result;
		result = name.compareTo(that.name);
		if (result != 0) return result;
		result = extension.compareTo(that.extension);
		if (result != 0) return result;
		result = EntityUtils.compare(fileTags, that.fileTags);
		if (result != 0) return result;
		return super.compareTo(that);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!super.equals(obj)) return false;
		if (!(obj instanceof DbFile)) return false;
		DbFile other = (DbFile) obj;
		if (!EntityUtils.equals(extension, other.extension)) return false;
		if (!EntityUtils.equals(name, other.name)) return false;
		if (!EntityUtils.equals(fileTags, other.fileTags)) return false;
		if (!EntityUtils.equals(notes, other.notes)) return false;
		return true;
	}
	
	public static String getFilesPath() {
		return filesPath;
	}

	public static void setFilesPath(String filesPath) {
		DbFile.filesPath = filesPath;
	}

	// Getters & Setters
	public List<Phantom> getPhantoms() {
		return phantoms;
	}

	public void setPhantoms(List<Phantom> phantoms) {
		this.phantoms = phantoms;
	}
	
	public List<Phantomina> getPhantominas() {
		return phantominas;
	}

	public void setPhantominas(List<Phantomina> phantominas) {
		this.phantominas = phantominas;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	public List<FileTag> getFileTags() {
		return fileTags;
	}

	public void setFileTags(List<FileTag> fileTags) {
		this.fileTags = fileTags;
	}
	
	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}
	
	public List<ExperimentalSetup> getExperimentalSetups() {
		return experimentalSetups;
	}

	public void setExperimentalSetups(List<ExperimentalSetup> experimentalSetups) {
		this.experimentalSetups = experimentalSetups;
	}
	
	public List<Measurement> getMeasurements() {
		return measurements;
	}

	public void setMeasurements(List<Measurement> measurements) {
		this.measurements = measurements;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	
	public List<AnnulusDiameter> getAnnulusDiameter() {
		return annulusDiameter;
	}

	public void setAnnulusDiameter(List<AnnulusDiameter> annulusDiameter) {
		this.annulusDiameter = annulusDiameter;
	}

	public List<FabricationType> getFabricationTypes() {
		return fabricationTypes;
	}

	public void setFabricationTypes(List<FabricationType> fabricationTypes) {
		this.fabricationTypes = fabricationTypes;
	}

	public List<LiteratureBase> getLiteratureBases() {
		return literatureBases;
	}

	public void setLiteratureBases(List<LiteratureBase> literatureBases) {
		this.literatureBases = literatureBases;
	}

	public List<Manufacturing> getManufacturing() {
		return manufacturing;
	}

	public void setManufacturing(List<Manufacturing> manufacturing) {
		this.manufacturing = manufacturing;
	}

	public List<Special> getSpecials() {
		return specials;
	}

	public void setSpecials(List<Special> specials) {
		this.specials = specials;
	}
	
}
