package org.artorg.tools.phantomData.server.model.specification;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.artorg.tools.phantomData.server.specification.DbPersistentUUID;

@MappedSuperclass
public abstract class AbstractShortcutValueEntity<
	ITEM extends AbstractShortcutValueEntity<ITEM, U, V>, U extends Comparable<U>,
	V extends Comparable<V>> extends AbstractBaseEntity<ITEM>
	implements DbPersistentUUID<ITEM> {
	private static final long serialVersionUID = -628994366624557217L;

	@Column(name = "SHORTCUT", unique = true, nullable = false)
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
	public String toName() {
		return String.format("shortcut: %s, value: %s", shortcut, toString(value));
	}
	
	@Override
	public String toString() {
		return String.format("%s [shortcut=%s, value=%s, %s]", getItemClass().getSimpleName(),
			shortcut, value, super.toString());
	}

	@Override
	public int compareTo(ITEM that) {
		if (that == null) return -1;
		int result;
		result = getShortcut().compareTo(that.getShortcut());
		if (result != 0) return result;
		result = getValue().compareTo(that.getValue());
		if (result != 0) return result;
		return super.compareTo(that);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!super.equals(obj)) return false;
		if (!(obj instanceof AbstractShortcutValueEntity)) return false;
		AbstractShortcutValueEntity<?,?,?> other = (AbstractShortcutValueEntity<?,?,?>) obj;
		if (shortcut == null) {
			if (other.shortcut != null) return false;
		} else if (!shortcut.equals(other.shortcut)) return false;
		if (value == null) {
			if (other.value != null) return false;
		} else if (!value.equals(other.value)) return false;
		return true;
	}

	// Getters & Setters
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

}
