package org.artorg.tools.phantomData.server.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "FABRICATION_TYPES")
public class FabricationType extends AbstractShortcutValueEntity<String, String>
		implements ComparableShortcutValueEntity<FabricationType, String, String> {
	private static final long serialVersionUID = -6058458914083152436L;

	public FabricationType() {}
	
	public FabricationType(String shortcut, String value) {
		super(shortcut, value);
	}
	
}
