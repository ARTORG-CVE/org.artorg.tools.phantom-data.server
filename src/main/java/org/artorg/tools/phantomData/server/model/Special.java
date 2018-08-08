package org.artorg.tools.phantomData.server.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.artorg.tools.phantomData.server.model.property.BooleanProperty;
import org.artorg.tools.phantomData.server.specification.DatabasePersistent;

@Entity
@Table(name = "SPECIALS")
public class Special implements Comparable<Special>, Serializable,
		DatabasePersistent<Special, Integer> {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	protected Integer id;
	
	@Column(name = "SHORTCUT", unique=true, nullable = false)
	private String shortcut;
	
	@ManyToMany
	@JoinTable(name = "SPECIALS_BOOLEAN_PROPERTIES",
			joinColumns=@JoinColumn(name = "SPECIALS_ID"),
			inverseJoinColumns=@JoinColumn(name="BOOLEAN_PROPERTIES_ID"))
	private List<BooleanProperty> booleanProperties = new ArrayList<BooleanProperty>();
	
	
	public Special() {}
	
	public Special(String shortcut, List<BooleanProperty> booleanProperties) {
		this.shortcut = shortcut;
		this.booleanProperties = booleanProperties;
	}
	
	public boolean addAttribute(BooleanProperty booleanProperties) {
		return this.booleanProperties.add(booleanProperties);
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
	
	public List<BooleanProperty> getBooleanProperties() {
		return booleanProperties;
	}

	public void setBooleanProperties(List<BooleanProperty> booleanProperties) {
		this.booleanProperties = booleanProperties;
	}

	@Override
	public int compareTo(Special that) {
		return getId().compareTo(that.getId());
	}
	
	@Override
	public String toString() {
		return String.format("[id: %d, shortcut: %s, properties: %s]", 
				getId(), getShortcut(), 
				getBooleanProperties().stream()
					.map(a -> a.toString())
					.collect(Collectors.joining(", ", "[", "]")));
	}
	
	@Override
	public Integer stringToID(String id) {
		return Integer.valueOf(id);
	}
	
}
