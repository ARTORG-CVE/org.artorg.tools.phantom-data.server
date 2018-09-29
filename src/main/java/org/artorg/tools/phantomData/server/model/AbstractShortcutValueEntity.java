package org.artorg.tools.phantomData.server.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.artorg.tools.phantomData.server.specification.DbPersistent;

@MappedSuperclass
public abstract class AbstractShortcutValueEntity<ITEM, U extends Comparable<U>,V extends Comparable<V>>  implements DbPersistent<ITEM,UUID> {
	private static final long serialVersionUID = -628994366624557217L;
	
	@Id
	@Column(name = "ID", nullable = false)
	private UUID id = UUID.randomUUID();
	
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
		return String.format("shortcut: %s, fabricationType: %s", getShortcut(), getValue());
	}

	@Override
	public UUID getId() {
		return id;
	}
	
	@Override
	public void setId(UUID id) {
		this.id = id;
	}
	
}
