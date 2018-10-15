package org.artorg.tools.phantomData.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.artorg.tools.phantomData.server.model.specification.AbstractBaseEntity;

@Entity
@Table(name = "NOTES")
public class Note extends AbstractBaseEntity<Note> {
	private static final long serialVersionUID = -7908541159889447936L;

	@Column(name = "NAME")
	private String name;

	public Note() {}
	
	public Note(String name) {
		this.name = name;
	}
	
	@Override
	public String createName() {
		return getName(); 
	}
	
	@Override
	public Class<Note> getItemClass() {
		return Note.class;
	}
	
	@Override
	public int compareTo(Note o) {
		return super.compareTo(o);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
