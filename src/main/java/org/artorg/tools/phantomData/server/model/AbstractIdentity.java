package org.artorg.tools.phantomData.server.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.artorg.tools.phantomData.server.specification.DatabasePersistent;

@MappedSuperclass
public abstract class AbstractIdentity<T> implements DatabasePersistent<T> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private T id;
	
	
	public AbstractIdentity() {}
	
	public T getId() {
		return id;
	}
	
	public void setId(T id) {
		this.id = id;
	}
	
}
