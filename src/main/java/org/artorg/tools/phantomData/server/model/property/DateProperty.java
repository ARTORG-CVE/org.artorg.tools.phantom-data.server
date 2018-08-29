package org.artorg.tools.phantomData.server.model.property;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.artorg.tools.phantomData.server.specification.DatabasePersistent;

@Entity
@Table(name = "DATE_PROPERTIES")
public class DateProperty extends Property<Date> implements Comparable<DateProperty>, Serializable, 
		DatabasePersistent<Integer> {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Integer id;
	
	@OneToOne
	private PropertyField propertyField;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "VALUE", nullable = false)
	private Date value;
	
	public DateProperty() {}
	
	public DateProperty(PropertyField propertyField, Date value) {
	this.propertyField = propertyField;
	this.value = value;
	}
	
	public Integer getId() {
	return id;
	}
	
	public void setId(Integer id) {
	this.id = id;
	}
	
	public PropertyField getPropertyField() {
	return propertyField;
	}
	
	public void setPropertyField(PropertyField propertyField) {
	this.propertyField = propertyField;
	}
	
	public Date getValue() {
	return value;
	}
	
	public void setValue(Date date) {
	this.value = date;
	}
	
	@Override
	public int compareTo(DateProperty that) {
	int i = getPropertyField().compareTo(that.getPropertyField());
	if (i != 0) return i;
	i = getValue().compareTo(that.getValue());
	return i;
	}
	
	@Override
	public String toString() {
	return String.format("[id: %d, propertyField: %s, value: %s]", 
			getId(), getPropertyField().toString(), getValue().toString());
	}
	
	@Override
	public Integer stringToID(String id) {
		return Integer.valueOf(id);
	}

}
