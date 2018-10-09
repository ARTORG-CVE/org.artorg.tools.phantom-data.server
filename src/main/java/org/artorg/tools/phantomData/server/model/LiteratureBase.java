package org.artorg.tools.phantomData.server.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.artorg.tools.phantomData.server.model.specification.AbstractShortcutValueEntity;

@Entity
@Table(name = "LITERATURE_BASES")
public class LiteratureBase extends AbstractShortcutValueEntity<LiteratureBase, String, String> {
	private static final long serialVersionUID = -3498410825088649494L;

	public LiteratureBase() {}
	
	public LiteratureBase(String shortcut, String value) {
		super(shortcut, value);
	}

	@Override
	public Class<LiteratureBase> getItemClass() {
		return LiteratureBase.class;
	}
	
}
