package org.artorg.tools.phantomData.server.models.phantom;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.artorg.tools.phantomData.server.model.AbstractPropertifiedEntity;
import org.artorg.tools.phantomData.server.model.DbPersistentUUID;
import org.artorg.tools.phantomData.server.models.base.DbFile;
import org.artorg.tools.phantomData.server.models.base.Note;
import org.artorg.tools.phantomData.server.util.EntityUtils;

@Entity
@Table(name = "PHANTOMINAS")
public class Phantomina extends AbstractPropertifiedEntity<Phantomina>
		implements Comparable<Phantomina>, Serializable, DbPersistentUUID<Phantomina> {
	private static final long serialVersionUID = 8708084186934082241L;

	@Column(name = "PRODUCT_ID", unique = true, nullable = false)
	private String productId;

	@OneToOne
	@JoinColumn(nullable = false)
	@NotNull
	private AnnulusDiameter annulusDiameter;

	@OneToOne
	@JoinColumn(nullable = false)
	@NotNull
	private FabricationType fabricationType;

	@OneToOne
	@JoinColumn(nullable = false)
	@NotNull
	private LiteratureBase literatureBase;

	@OneToOne
	@JoinColumn(nullable = false)
	@NotNull
	private Special special;

	@ManyToMany
	private List<DbFile> files = new ArrayList<>();

	@ManyToMany
	private List<Note> notes = new ArrayList<>();

	public Phantomina() {}

	public Phantomina(AnnulusDiameter annulusDiameter, FabricationType fType,
			LiteratureBase litBase, Special special) {
		this.annulusDiameter = annulusDiameter;
		this.fabricationType = fType;
		this.literatureBase = litBase;
		this.special = special;
		updateProductId();
	}

	@Override
	public String toName() {
		return getProductId();
	}

	public void updateProductId() {
		setProductId(createProductId(annulusDiameter, fabricationType, literatureBase, special));
	}

	public static String createProductId(Phantomina phantomina) {
		if (phantomina == null)
			return "??-?-?-?";
		return createProductId(phantomina.getAnnulusDiameter(), phantomina.getFabricationType(),
				phantomina.getLiteratureBase(), phantomina.getSpecial());
	}

	public static String createProductId(AnnulusDiameter annulusDiameter,
			FabricationType fabricationType, LiteratureBase literatureBase, Special special) {
		return String.format("%s-%s-%s-%s",
				helper(() -> annulusDiameter.getShortcut().toString(), "??"),
				helper(() -> fabricationType.getShortcut(), "?"),
				helper(() -> literatureBase.getShortcut(), "?"),
				helper(() -> special.getShortcut(), "?"));
	}

	private static String helper(Supplier<String> supplier, String orElse) {
		try {
			return supplier.get();
		} catch (Exception e) {
			return orElse;
		}
	}

	@Override
	public String toString() {
		return String.format(
				"Phantomina [productId=%s, annulusDiameter=%s, fabricationType=%s, literatureBase=%s, special=%s, files=%s, notes=%s, %s]",
				productId, annulusDiameter, fabricationType, literatureBase, special, files, notes,
				super.toString());
	}

	@Override
	public int compareTo(Phantomina that) {
		if (that == null) return -1;
		int result;
		result = comparePid(getProductId(), that.getProductId());
		if (result != 0) return result;
		result = getAnnulusDiameter().compareTo(that.getAnnulusDiameter());
		if (result != 0) return result;
		result = getFabricationType().compareTo(that.getFabricationType());
		if (result != 0) return result;
		result = getLiteratureBase().compareTo(that.getLiteratureBase());
		if (result != 0) return result;
		result = getSpecial().compareTo(that.getSpecial());
		if (result != 0) return result;
		result = EntityUtils.compare(files, that.files);
		if (result != 0) return result;
		result = EntityUtils.compare(notes, that.notes);
		if (result != 0) return result;
		return super.compareTo(that);
	}

	public static int comparePid(String pid1, String pid2) {
		String[] splits1 = pid1.split("-");
		String[] splits2 = pid2.split("-");
		int n = Math.min(splits1.length, splits2.length);
		int result;
		for (int i = 0; i < n; i++) {
			final Pattern pattern = Pattern.compile("\\d+");
			if (pattern.matcher(splits1[i]).matches())
				result = Integer.valueOf(splits1[i]).compareTo(Integer.valueOf(splits2[i]));
			else
				result = splits1[i].compareTo(splits2[i]);
			if (result != 0) return result;
		}
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof Phantomina)) return false;
		Phantomina other = (Phantomina) obj;
		if (!EntityUtils.equals(productId, other.productId)) return false;
		if (!EntityUtils.equals(files, other.files)) return false;
		if (!EntityUtils.equals(notes, other.notes)) return false;
		return true;
	}

	@Override
	public Class<Phantomina> getItemClass() {
		return Phantomina.class;
	}

	// Getters & Setters
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public AnnulusDiameter getAnnulusDiameter() {
		return annulusDiameter;
	}

	public void setAnnulusDiameter(AnnulusDiameter annulusDiameter) {
		this.annulusDiameter = annulusDiameter;
		updateProductId();
	}

	public FabricationType getFabricationType() {
		return fabricationType;
	}

	public void setFabricationType(FabricationType fabricationType) {
		this.fabricationType = fabricationType;
		updateProductId();
	}

	public LiteratureBase getLiteratureBase() {
		return literatureBase;
	}

	public void setLiteratureBase(LiteratureBase literatureBase) {
		this.literatureBase = literatureBase;
		updateProductId();
	}

	public Special getSpecial() {
		return special;
	}

	public void setSpecial(Special special) {
		this.special = special;
		updateProductId();
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
