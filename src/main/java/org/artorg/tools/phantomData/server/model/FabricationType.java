package org.artorg.tools.phantomData.server.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.artorg.tools.phantomData.server.specification.DatabasePersistent;

@Entity
@Table(name = "FABRICATION_TYPES")
public class FabricationType implements Comparable<FabricationType>, Serializable,
		DatabasePersistent<Integer> {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Integer id;
	
	@Column(name = "SHORTCUT", unique=true, nullable = false)
	private String shortcut;

	@Column(name = "VALUE", nullable = false)
	private String value;

	
	public FabricationType() {}
	
	public FabricationType(String shortcut, String value) {
		this.shortcut = shortcut;
		this.value = value;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getShortcut() {
		return shortcut;
	}

	public void setShortcut(String shortcut) {
		this.shortcut = shortcut;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int compareTo(FabricationType that) {
		return getShortcut().compareTo(that.getShortcut());
	}
	
	@Override
	public String toString() {
		return String.format("id: %d, shortcut: %s, fabricationType: %s", 
				getId(), getShortcut(), getValue());
	}
	
	@Override
	public Integer stringToID(String id) {
		return Integer.valueOf(id);
	}
}
