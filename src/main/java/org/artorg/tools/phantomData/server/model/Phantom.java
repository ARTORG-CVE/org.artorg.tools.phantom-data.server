package org.artorg.tools.phantomData.server.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.artorg.tools.phantomData.server.model.property.BooleanProperty;
import org.artorg.tools.phantomData.server.specification.DatabasePersistent;

@Entity
@Table(name = "PHANTOMS")
public class Phantom implements Comparable<Phantom>, Serializable,
		DatabasePersistent<Phantom, Integer> {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Integer id;
	
	@OneToOne
	private AnnulusDiameter annulusDiameter;
	
	@OneToOne
	private FabricationType fabricationType;
	
	@OneToOne
	private LiteratureBase literatureBase;
	
	@OneToOne
	private Special special;
	
	@Column(name = "NUMBER", nullable = false)
	private int number;

	@ManyToMany
	@JoinTable(name = "PHANTOM_BOOLEAN_PROPERTIES",
			joinColumns=@JoinColumn(name = "PHANTOMS_ID"),
			inverseJoinColumns=@JoinColumn(name="BOOLEAN_PROPERTIES_ID"))
	private Collection<BooleanProperty> booleanProperties = new ArrayList<BooleanProperty>();
	
	@ManyToMany
	@JoinTable(name = "PHANTOM_DATE_PROPERTIES",
			joinColumns=@JoinColumn(name = "PHANTOMS_ID"),
			inverseJoinColumns=@JoinColumn(name="DATE_PROPERTIES_ID"))
	private Collection<BooleanProperty> dateProperties = new ArrayList<BooleanProperty>();
	
	@ManyToMany
	@JoinTable(name = "PHANTOM_FILES",
			joinColumns=@JoinColumn(name = "PHANTOMS_ID"),
			inverseJoinColumns=@JoinColumn(name="FILES_ID"))
	private Collection<PhantomFile> files = new ArrayList<PhantomFile>();
	
	public Phantom() {}
	
//	public Phantom(int annulusDiameter, String fType, String litBase, String special, int number) {
//		this.annulusDiameter = AnnulusDiameterConnector.get().readByShortcut(annulusDiameter);
//		this.fType = FabricationTypeConnector.get().readByShortcut(fType);
//		this.literatureBase = LiteratureBaseConnector.get().readByShortcut(litBase);
//		this.special = SpecialConnector.get().readByShortcut(special);
//		this.number = number;
//	}
	
	public Phantom(AnnulusDiameter annulusDiameter, FabricationType fType, LiteratureBase litBase, Special special, int number) {
		this.annulusDiameter = annulusDiameter;
		this.fabricationType = fType;
		this.literatureBase = litBase;
		this.special = special;
		this.number = number;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Phantom)
			if (this.id.equals(((Phantom)o).id))
					return true;
		return false;
	}
	
	@Override
	public int compareTo(Phantom p) {
		int i;
		i = getAnnulusDiameter().compareTo(p.getAnnulusDiameter());
		if (i != 0) return i;
		i = getFabricationType().getId().compareTo(p.getFabricationType().getId());
		if (i != 0) return i;
		i = getLiteratureBase().getId().compareTo(p.getLiteratureBase().getId());
		if (i != 0) return i;
		i = getSpecial().getId().compareTo(p.getSpecial().getId());
		if (i != 0) return i;
		return ((Integer)getNumber()).compareTo(p.getNumber());
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(String.format("[id: %d, annulus diameter: %f, fabrication type: %s, "
				+ "literature base: %s, special: %s, number: %d", 
				getId(), getAnnulusDiameter().getValue(), getFabricationType().getValue(), 
				getLiteratureBase().getValue(), getSpecial().toString(), getNumber()));
		if (!booleanProperties.isEmpty())
			sb.append(", boolean properties: " +getBooleanProperties().toString());
		if (!dateProperties.isEmpty())
			sb.append(", date properties: " +getDateProperties().toString());
		if (!files.isEmpty())
			sb.append(", files: " +getFiles().toString());
		sb.append("]");
		return sb.toString();
	}
	
	@Override
	public Integer stringToID(String id) {
		return Integer.valueOf(id);
	}
	
	// Getters & Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AnnulusDiameter getAnnulusDiameter() {
		return annulusDiameter;
	}

	public void setAnnulusDiameter(AnnulusDiameter annulusDiameter) {
		this.annulusDiameter = annulusDiameter;
	}
	
	public FabricationType getFabricationType() {
		return fabricationType;
	}

	public void setFabricationType(FabricationType fabricationType) {
		this.fabricationType = fabricationType;
	}

	public LiteratureBase getLiteratureBase() {
		return literatureBase;
	}

	public void setLiteratureBase(LiteratureBase literatureBase) {
		this.literatureBase = literatureBase;
	}

	public Special getSpecial() {
		return special;
	}

	public void setSpecial(Special special) {
		this.special = special;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	public Collection<BooleanProperty> getBooleanProperties() {
		return booleanProperties;
	}

	public void setBooleanProperties(Collection<BooleanProperty> booleanProperties) {
		this.booleanProperties = booleanProperties;
	}

	public Collection<BooleanProperty> getDateProperties() {
		return dateProperties;
	}

	public void setDateProperties(Collection<BooleanProperty> dateProperties) {
		this.dateProperties = dateProperties;
	}

	public Collection<PhantomFile> getFiles() {
		return files;
	}

	public void setFiles(Collection<PhantomFile> files) {
		this.files = files;
	}

}
