package org.artorg.tools.phantomData.server.model.specification;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.artorg.tools.phantomData.server.specification.DbPersistentUUID;

@MappedSuperclass
public abstract class AbstractShortcutValueEntity<ITEM extends AbstractShortcutValueEntity<ITEM,U,V>, U extends Comparable<U>,V extends Comparable<V>> extends AbstractBaseEntity<ITEM> implements DbPersistentUUID<ITEM> {
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
	
	public abstract String toString(V value);

	public abstract V fromStringToValue(String s);
	
	@Override
	public String createName() {
		return "shortcut:" +shortcut +", value:" +toString(value);
	}
	
	public  int compareTo(ITEM that) {
		int result;
		result = getShortcut().compareTo(that.getShortcut());
		if (result != 0) return result;
		result = getValue().compareTo(that.getValue());
		if (result != 0) return result;	
		result = super.compareTo(that);
		
		return result;
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
