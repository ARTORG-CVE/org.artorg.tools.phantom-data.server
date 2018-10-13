package org.artorg.tools.phantomData.server.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.artorg.tools.phantomData.server.BootApplication;
import org.artorg.tools.phantomData.server.beans.BeanMap;
import org.artorg.tools.phantomData.server.model.specification.AbstractShortcutValueEntity;

@Entity
@Table(name = "ANNULUS_DIAMETERS")
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
}
