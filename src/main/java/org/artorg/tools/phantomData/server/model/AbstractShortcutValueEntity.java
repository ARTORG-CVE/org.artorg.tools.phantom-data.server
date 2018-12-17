package org.artorg.tools.phantomData.server.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;

import org.artorg.tools.phantomData.server.models.base.DbFile;
import org.artorg.tools.phantomData.server.models.base.Note;
import org.artorg.tools.phantomData.server.util.EntityUtils;

@MappedSuperclass
public abstract class AbstractShortcutValueEntity<
	ITEM extends AbstractShortcutValueEntity<ITEM, U, V>, U extends Comparable<U>,
	V extends Comparable<V>> extends AbstractPropertifiedEntity<ITEM>
	implements DbPersistentUUID<ITEM> {
	private static final long serialVersionUID = -628994366624557217L;

	@Column(name = "SHORTCUT", unique = true, nullable = false)
	private U shortcut;

	@Column(name = "VALUE", nullable = false)
	private V value;

	@ManyToMany
	private List<DbFile> files;

	@ManyToMany
	private List<Note> notes;

	{
		files = new ArrayList<DbFile>();
		notes = new ArrayList<Note>();
	}

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
		return String.format("%s [shortcut=%s, value=%s, files=%s, notes=%S %s]",
			getItemClass().getSimpleName(), shortcut, value, files, notes,
			super.toString());
	}

	@Override
	public int compareTo(ITEM that) {
		if (that == null) return -1;
		int result;
		result = getShortcut().compareTo(that.getShortcut());
		if (result != 0) return result;
		result = getValue().compareTo(that.getValue());
		if (result != 0) return result;
		result = EntityUtils.compare(files, that.getFiles());
		if (result != 0) return result;
		result = EntityUtils.compare(notes, that.getNotes());
		if (result != 0) return result;
		return super.compareTo(that);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!super.equals(obj)) return false;
		if (!(obj instanceof AbstractShortcutValueEntity)) return false;
		AbstractShortcutValueEntity<ITEM, U, V> other;
		try {
			other = (AbstractShortcutValueEntity<ITEM, U, V>) obj;
		} catch (Exception e) {
			return false;
		}
		if (shortcut == null) {
			if (other.shortcut != null) return false;
		} else if (!shortcut.equals(other.shortcut)) return false;

		if (!EntityUtils.equals(shortcut, other.shortcut)) return false;
		if (!EntityUtils.equals(value, other.value)) return false;

		if (!EntityUtils.equals(files, other.files)) return false;
		if (!EntityUtils.equals(notes, other.notes)) return false;
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

	public List<DbFile> getFiles() {
		return files;
	}

	public void setFiles(List<DbFile> files) {
		this.files = files;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

}
