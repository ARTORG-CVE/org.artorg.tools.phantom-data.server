package org.artorg.tools.phantomData.server.model.property;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "DATE_PROPERTIES")
public class DateProperty extends Property<DateProperty, Date> {
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

}
