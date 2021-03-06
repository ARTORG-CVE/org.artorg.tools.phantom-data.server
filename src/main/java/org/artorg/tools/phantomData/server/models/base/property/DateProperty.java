package org.artorg.tools.phantomData.server.models.base.property;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.artorg.tools.phantomData.server.model.AbstractProperty;

@Entity
@Table(name = "DATE_PROPERTIES")
public class DateProperty extends AbstractProperty<DateProperty, Date> implements Serializable {
	private static final long serialVersionUID = -6242701549246630297L;

	public DateProperty() {}
	
	public DateProperty(PropertyField propertyField, Date value) {
		super(propertyField, value);
	}

	@Override
	public String toString(Date value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Date fromStringToValue(String s) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public Class<DateProperty> getItemClass() {
		return DateProperty.class;
	}

	@Override
	public Class<Date> getValueClass() {
		return Date.class;
	}

}
