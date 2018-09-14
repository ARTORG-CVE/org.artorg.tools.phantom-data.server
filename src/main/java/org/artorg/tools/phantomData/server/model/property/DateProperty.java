package org.artorg.tools.phantomData.server.model.property;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "DATE_PROPERTIES")
public class DateProperty extends Property<Date, Integer> implements ComparableProperty<DateProperty, Date, Integer> {
	private static final long serialVersionUID = -6242701549246630297L;

	public DateProperty() {}
	
	public DateProperty(PropertyField propertyField, Date value) {
		super(propertyField, value);
	}
	
	@Override
	public Integer stringToID(String id) {
		return Integer.valueOf(id);
	}

}
