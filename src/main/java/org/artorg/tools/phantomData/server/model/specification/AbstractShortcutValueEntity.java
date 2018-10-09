package org.artorg.tools.phantomData.server.model.specification;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.artorg.tools.phantomData.server.specification.DbPersistentUUID;

@MappedSuperclass
public abstract class AbstractShortcutValueEntity<ITEM, U extends Comparable<U>,V extends Comparable<V>>  implements DbPersistentUUID<ITEM> {
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
	
	@SuppressWarnings("unchecked")
	public  int compareTo(ITEM that) {
		return ((AbstractShortcutValueEntity<ITEM,U,V>)this).getShortcut().compareTo(
				((AbstractShortcutValueEntity<ITEM,U,V>)that).getShortcut());
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
