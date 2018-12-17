package org.artorg.tools.phantomData.server.models.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.artorg.tools.phantomData.server.model.AbstractPersonifiedEntity;

@Entity
@Table(name = "NOTES")
public class Note extends AbstractPersonifiedEntity<Note> {
	private static final long serialVersionUID = -7908541159889447936L;

	@Column(name = "NAME")
	private String name;

	public Note() {}
	
	public Note(String name) {
		this.name = name;
	}
	
	@Override
	public String toName() {
		return getName(); 
	}
	
	@Override
	public Class<Note> getItemClass() {
		return Note.class;
	}
	
	@Override
	public String toString() {
		return String.format("Note [name=%s, %s]", name, super.toString());
	}
	
	@Override
	public int compareTo(Note that) {
		if (that == null) return -1;
		int result;
		result = getName().compareTo(that.getName());
		if (result != 0) return result;
		return super.compareTo(that);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!super.equals(obj)) return false;
		if (!(obj instanceof Note)) return false;
		Note other = (Note) obj;
		if (name == null) {
			if (other.name != null) return false;
		} else if (!name.equals(other.name)) return false;
		return true;
	}

	// Getters & Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
