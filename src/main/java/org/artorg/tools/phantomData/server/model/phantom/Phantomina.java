package org.artorg.tools.phantomData.server.model.phantom;

import java.io.Serializable;
import java.util.function.Supplier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.artorg.tools.phantomData.server.model.specification.AbstractBaseEntity;
import org.artorg.tools.phantomData.server.specification.DbPersistentUUID;

@Entity
@Table(name = "PHANTOMINAS")
public class Phantomina extends AbstractBaseEntity<Phantomina>
	implements Comparable<Phantomina>, Serializable, DbPersistentUUID<Phantomina> {
	private static final long serialVersionUID = 8708084186934082241L;
	
	@Column(name = "PRODUCT_ID", nullable = false)
	private String productId;

	@OneToOne
	private AnnulusDiameter annulusDiameter;

	@OneToOne
	private FabricationType fabricationType;

	@OneToOne
	private LiteratureBase literatureBase;

	@OneToOne
	private Special special;

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
		setProductId(String.format("%s-%s-%s-%s",
			helper(() -> annulusDiameter.getShortcut().toString(), "??"),
			helper(() -> fabricationType.getShortcut(), "?"),
			helper(() -> literatureBase.getShortcut(), "?"),
			helper(() -> special.getShortcut(), "?")));
	}

	private String helper(Supplier<String> supplier, String orElse) {
		try {
			return supplier.get();
		} catch (Exception e) {
			return orElse;
		}
	}
	
	@Override
	public String toString() {
		return String.format(
			"Phantomina [productId=%s, annulusDiameter=%s, fabricationType=%s, literatureBase=%s, special=%s, %s]",
			productId, annulusDiameter, fabricationType, literatureBase, special, super.toString());
	}

	@Override
	public int compareTo(Phantomina that) {
		if (that == null) return -1;
		int result;
		result = comparePid(getProductId(),that.getProductId());
		if (result != 0) return result;
		result = getAnnulusDiameter().compareTo(that.getAnnulusDiameter());
		if (result != 0) return result;
		result = getFabricationType().compareTo(that.getFabricationType());
		if (result != 0) return result;
		result = getLiteratureBase().compareTo(that.getLiteratureBase());
		if (result != 0) return result;
		result = getSpecial().compareTo(that.getSpecial());
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
		result =
			prime * result + ((annulusDiameter == null) ? 0 : annulusDiameter.hashCode());
		result =
			prime * result + ((fabricationType == null) ? 0 : fabricationType.hashCode());
		result =
			prime * result + ((literatureBase == null) ? 0 : literatureBase.hashCode());
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
		result = prime * result + ((special == null) ? 0 : special.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!super.equals(obj)) return false;
		if (!(obj instanceof Phantomina)) return false;
		Phantomina other = (Phantomina) obj;
		if (annulusDiameter == null) {
			if (other.annulusDiameter != null) return false;
		} else if (!annulusDiameter.equals(other.annulusDiameter)) return false;
		if (fabricationType == null) {
			if (other.fabricationType != null) return false;
		} else if (!fabricationType.equals(other.fabricationType)) return false;
		if (literatureBase == null) {
			if (other.literatureBase != null) return false;
		} else if (!literatureBase.equals(other.literatureBase)) return false;
		if (productId == null) {
			if (other.productId != null) return false;
		} else if (!productId.equals(other.productId)) return false;
		if (special == null) {
			if (other.special != null) return false;
		} else if (!special.equals(other.special)) return false;
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

}
