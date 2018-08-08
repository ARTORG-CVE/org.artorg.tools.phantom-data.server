package org.artorg.tools.phantomData.server.model.property;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.artorg.tools.phantomData.server.specification.DatabasePersistent;

@Entity
@Table(name = "DATE_PROPERTIES")
public class DateProperty implements Comparable<DateProperty>, Serializable, 
		DatabasePersistent<DateProperty, Integer> {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Integer id;
	
	@Column(name = "PROPETY_FIELD", nullable = false)
	private PropertyField propertyField;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "BOOL", nullable = false)
	private Date date;
	
	public DateProperty() {}
	
	public DateProperty(PropertyField propertyField, Date date) {
	this.propertyField = propertyField;
	this.date = date;
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
	
	public Date getDate() {
	return date;
	}
	
	public void setDate(Date date) {
	this.date = date;
	}
	
	@Override
	public int compareTo(DateProperty that) {
	int i = getPropertyField().compareTo(that.getPropertyField());
	if (i != 0) return i;
	i = getDate().compareTo(that.getDate());
	return i;
	}
	
	@Override
	public String toString() {
	return String.format("[id: %d, propertyField: %s, value: %s]", 
			getId(), getPropertyField().toString(), getDate().toString());
	}
	
	@Override
	public Integer stringToID(String id) {
		return Integer.valueOf(id);
	}

}
