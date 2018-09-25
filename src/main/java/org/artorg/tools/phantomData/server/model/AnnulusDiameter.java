package org.artorg.tools.phantomData.server.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ANNULUS_DIAMETERS")
public class AnnulusDiameter extends AbstractShortcutValueEntity<AnnulusDiameter, Integer, Double>
		implements ComparableShortcutValueEntity<AnnulusDiameter, Integer, Double>{
	private static final long serialVersionUID = -3588992364016922887L;

	public AnnulusDiameter() {}
	
	public AnnulusDiameter(Integer shortcut, Double value) {
		super(shortcut, value);
	}
}
