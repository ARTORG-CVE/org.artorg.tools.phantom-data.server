package org.artorg.tools.phantomData.server.models.phantom;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.artorg.tools.phantomData.server.model.AbstractShortcutValueEntity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "ANNULUS_DIAMETERS")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class AnnulusDiameter extends AbstractShortcutValueEntity<AnnulusDiameter, Integer, Double> {
	private static final long serialVersionUID = -3588992364016922887L;

	public AnnulusDiameter() {}

	public AnnulusDiameter(Integer shortcut, Double value) {
		super(shortcut, value);
	}

	@Override
	public Class<AnnulusDiameter> getItemClass() {
		return AnnulusDiameter.class;
	}

	@Override
	public String toString(Double value) {
		return Double.toString(value);
	}

	@Override
	public Double fromStringToValue(String s) {
		return Double.valueOf(s);
	}

}
