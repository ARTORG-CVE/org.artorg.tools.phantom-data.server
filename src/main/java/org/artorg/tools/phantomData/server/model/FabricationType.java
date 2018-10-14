package org.artorg.tools.phantomData.server.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.artorg.tools.phantomData.server.model.specification.AbstractShortcutValueEntity;

@Entity
@Table(name = "FABRICATION_TYPES")
public class FabricationType extends AbstractShortcutValueEntity<FabricationType, String, String> {
	private static final long serialVersionUID = -6058458914083152436L;

	public FabricationType() {}
	
	public FabricationType(String shortcut, String value) {
		super(shortcut, value);
	}

	@Override
	public Class<FabricationType> getItemClass() {
		return FabricationType.class;
	}

	@Override
	public String toString(String value) {
		return value;
	}

	@Override
	public String fromStringToValue(String s) {
		return s;
	}
	
}
