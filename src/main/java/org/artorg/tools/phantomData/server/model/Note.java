package org.artorg.tools.phantomData.server.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.artorg.tools.phantomData.server.model.specification.AbstractBaseEntity;

@Entity
@Table(name = "NOTES")
public class Note extends AbstractBaseEntity<Note> {
	private static final long serialVersionUID = -7908541159889447936L;

	public Note(String name, Person creator) {
		super(name, creator);
	}
	
	@Override
	protected String createName() {
		return super.getFormattedDateAdded() +"   " +super.getCreator().getSimpleName() +"   " +super.getName(); 
	}
	
	@Override
	public Class<Note> getItemClass() {
		return Note.class;
	}
	
	@Override
	public int compareTo(Note o) {
		return super.compareTo(o);
	}
	
	

	

}
