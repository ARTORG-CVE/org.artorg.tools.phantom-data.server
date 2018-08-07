package org.artorg.tools.phantomData.server.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.artorg.tools.phantomData.server.connector.AnnulusDiameterConnector;
import org.artorg.tools.phantomData.server.specification.DatabasePersistent;
import org.artorg.tools.phantomData.server.specification.HttpDatabaseCrud;

@Entity
@Table(name = "ANNULUS_DIAMETERS")
public class AnnulusDiameter implements Comparable<AnnulusDiameter>, Serializable, 
		DatabasePersistent<AnnulusDiameter, Integer> {
	private static final long serialVersionUID = 1L;
	
	private static final HttpDatabaseCrud<AnnulusDiameter, Integer> connector;

	static {
		connector  = AnnulusDiameterConnector.get();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Integer id;
	
	@Column(name = "SHORTCUT", nullable = false)
	private Integer shortcut;

	@Column(name = "VALUE", nullable = false)
	private Double value;

	public AnnulusDiameter() {}
	
	public AnnulusDiameter(Integer shortcut, Double value) {
		this.shortcut = shortcut;
		this.value = value;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getShortcut() {
		return shortcut;
	}
	
	public void setShortcut(Integer shortcut) {
		this.shortcut = shortcut;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
	
	@Override
	public int compareTo(AnnulusDiameter that) {
		return getShortcut().compareTo(that.getShortcut());
	}
	
	@Override
	public String toString() {
		return String.format("[id: %d, shortcut: %s, value: %f]", 
				getId(), getShortcut(), getValue());
	}

	@Override
	public HttpDatabaseCrud<AnnulusDiameter, Integer> getConnector() {
		return connector;
	}
	
	@Override
	public Integer stringToID(String id) {
		return Integer.valueOf(id);
	}
	 
}
