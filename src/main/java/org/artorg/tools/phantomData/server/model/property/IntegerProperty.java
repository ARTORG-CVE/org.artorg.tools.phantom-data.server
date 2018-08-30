package org.artorg.tools.phantomData.server.model.property;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.artorg.tools.phantomData.server.specification.DatabasePersistent;

@Entity
@Table(name = "INTEGER_PROPERTIES")
public class IntegerProperty implements Comparable<IntegerProperty>, Serializable, 
		DatabasePersistent<Integer> {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Integer id;

	@OneToOne
	private PropertyField propertyField;

	@Column(name = "VALUE", nullable = false)
	private Integer value;
	
	public IntegerProperty() {}
	
	public IntegerProperty(PropertyField propertyField, Integer value) {
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

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
	
	@Override
	public int compareTo(IntegerProperty that) {
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