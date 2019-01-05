package org.artorg.tools.phantomData.server.models.base.property;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.artorg.tools.phantomData.server.model.AbstractProperty;

@Entity
@Table(name = "BOOLEAN_PROPERTIES")
public class BooleanProperty extends AbstractProperty<BooleanProperty, Boolean> implements Serializable {
	private static final long serialVersionUID = 4690035196217371829L;
	
	public BooleanProperty() {}
	
	public BooleanProperty(PropertyField propertyField, Boolean value) {
		super(propertyField, value);
	}
	
	@Override
	public String toString(Boolean value) {
		return Boolean.toString(value);
	}

	@Override
	public Boolean fromStringToValue(String s) {
		return Boolean.valueOf(s);
	}

	@Override
	public Class<BooleanProperty> getItemClass() {
		return BooleanProperty.class;
	}

	@Override
	public Class<Boolean> getValueClass() {
		return Boolean.class;
	}
	
}