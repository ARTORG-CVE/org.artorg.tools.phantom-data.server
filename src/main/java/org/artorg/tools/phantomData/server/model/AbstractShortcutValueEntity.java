package org.artorg.tools.phantomData.server.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractShortcutValueEntity<U extends Comparable<U>,V extends Comparable<V>> 
	extends AbstractIntegerIdentity implements Serializable {
	private static final long serialVersionUID = -628994366624557217L;
	
	@Column(name = "SHORTCUT", unique=true, nullable = false)
	private U shortcut;

	@Column(name = "VALUE", nullable = false)
	private V value;
	
	public AbstractShortcutValueEntity() {}
	
	public AbstractShortcutValueEntity(U shortcut, V value) {
		this.shortcut = shortcut;
		this.value = value;
	}

	public U getShortcut() {
		return shortcut;
	}

	public void setShortcut(U shortcut) {
		this.shortcut = shortcut;
	}
	
	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return String.format("id: %d, shortcut: %s, fabricationType: %s", 
				getId(), getShortcut(), getValue());
	}
	
}
