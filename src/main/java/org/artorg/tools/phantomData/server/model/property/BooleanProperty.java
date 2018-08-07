package org.artorg.tools.phantomData.server.model.property;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.artorg.tools.phantomData.server.connector.AnnulusDiameterConnector;
import org.artorg.tools.phantomData.server.connector.property.BooleanPropertyConnector;
import org.artorg.tools.phantomData.server.model.AnnulusDiameter;
import org.artorg.tools.phantomData.server.specification.DatabasePersistent;
import org.artorg.tools.phantomData.server.specification.HttpDatabaseCrud;

@Entity
@Table(name = "BOOLEAN_PROPERTIES")
public class BooleanProperty implements Comparable<BooleanProperty>, Serializable, 
		DatabasePersistent<BooleanProperty, Integer> {
	private static final long serialVersionUID = 1L;

	private static final HttpDatabaseCrud<BooleanProperty, Integer> connector;

	static {
		connector  = BooleanPropertyConnector.get();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Integer id;

	@Column(name = "PROPERTY_FIELD", nullable = false)
	private PropertyField propertyField;

	@Column(name = "BOOL", nullable = false)
	private Boolean bool;
	
	public BooleanProperty() {}
	
	public BooleanProperty(PropertyField propertyField, Boolean bool) {
		this.propertyField = propertyField;
		this.bool = bool;
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

	public Boolean getBool() {
		return bool;
	}

	public void setBool(Boolean bool) {
		this.bool = bool;
	}
	
	@Override
	public int compareTo(BooleanProperty that) {
		int i = getPropertyField().compareTo(that.getPropertyField());
		if (i != 0) return i;
		i = getBool().compareTo(that.getBool());
		return i;
	}
	
	@Override
	public String toString() {
		return String.format("[id: %d, propertyField: %s, value: %s]", 
				getId(), getPropertyField().toString(), getBool().toString());
	}

	@Override
	public HttpDatabaseCrud<BooleanProperty, Integer> getConnector() {
		return connector;
	}

	@Override
	public Integer stringToID(String id) {
		return Integer.valueOf(id);
	}
	
}