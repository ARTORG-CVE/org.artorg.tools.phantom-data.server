package org.artorg.tools.phantomData.server.models.phantom;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.artorg.tools.phantomData.server.model.AbstractShortcutValueEntity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "FABRICATION_TYPES")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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
